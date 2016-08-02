#include "xltlib.h"
//#include <QDebug>
//#include <QTime>
//#include <QMessageBox>
//#include <QString>

XLTLib::XLTLib()
{
//	initSocket();
    createSocket();
}

int XLTLib::only_startdma()
{
    startDMA();
    usleep(100000);

}
int XLTLib::only_startut()
{
    int WorkMode = 1;
    usleep(100000);
    switch(WorkMode)
    {
    case 0:
        sendPara(0x1001,1); //设置CHAN_EN_UT_NUM
        break;
    case 1:
        sendPara(0x1001,3); //设置CHAN_EN_UT_NUM

        break;
    case 2:
        sendPara(0x1001,5); //设置CHAN_EN_UT_NUM
        break;
    default:
        return 0;
        break;
    }
}

int XLTLib::only_startsystem()
{
    sendPara(3, 1);


//    usleep(100000);
//    startDMA();
//    usleep(100000);

}


int XLTLib::SetMode(int WorkMode, int Level)  //Level-1(50Hz),2(100Hz),3(200Hz),只在workmode=2时有效
{

    StopWork();    //复位ARM、UT、PA
	//choose work mode
	sendPara(1, WorkMode); 
    m_iUT_TRMode=0;
    m_iUT_TGCEn=0;


    switch(WorkMode)
    {
        case MODE_TEST://测试
     //   setCrystaltype(WorkMode,0,);
        MAX_PKT_LEN = 182*626*2;
        setPackLen(MAX_PKT_LEN);
        break;

    case MODE_MATERIA://母材
//        myxltlib.setCrystaltype(WorkMode,0,1);
//        myxltlib.setCrystaltype(WorkMode,1,1);
//        myxltlib.setCrystaltype(WorkMode,2,1);
        MAX_PKT_LEN = 180*626*2;
        setPackLen(MAX_PKT_LEN);
        break;
    case MODE_WELD://焊缝
        MAX_PKT_LEN = 180*626*2;
        setPackLen(MAX_PKT_LEN);
        break;
    case MODE_PA://精细
        MAX_PKT_LEN = 180*626*2;
        setPackLen(MAX_PKT_LEN);
        break;
    default:
        break;
    }

    if((WorkMode==1)||(WorkMode==3))//母材、精细
    {//相控阵设置

    //PA2: 17号晶片起始连续16个晶片；
        sendPara(0x2105,0x2222);

    //  PA1:1号晶片起始连续16个晶片；
        sendPara(0x2005,0x1111);
    }
    //sendPara(3, 1);

    SetWeldLevel(WorkMode, Level);
}

int XLTLib::Startwork(int WorkMode, int slotNum)
{

        usleep(100000);
        int ret=-1;
        int ret1=-1,ret2=-1,ret3=-1;
        switch(WorkMode)
        {
        case 0:
            ret=sendPara(0x1001,1); //设置CHAN_EN_UT_NUM
            break;
        case 1:
            ret=sendPara(0x1001,3); //设置CHAN_EN_UT_NUM

            break;
        case 2:
            ret=sendPara(0x1001,6); //设置CHAN_EN_UT_NUM
            break;
       case 3:
            if ((slotNum<50)&&(slotNum>0))
                ret=sendPara(0x0004,slotNum*2);
            break;
        default:
            cout<<"startwork return!"<<endl;
            return ret;
            break;
        }

        if (!(ret==-1))
        {
            ret1=startDMA();
//            cout<<"startdma"<<endl;
        }

//        QMessageBox msg;
//        msg.setText("wait");
//        msg.exec();

        //系统工作
        if (!(ret1==-1))
        {
            ret2=sendPara(3, 1);
            cout<<"statsys"<<endl;
        }
        char * buff;
        buff=new char[MAX_PKT_LEN];
        if (!(ret2==-1))
        {
            ret3=OpenDataStream(buff);
            cout<<"opendatastream"<<endl;
        }
        if (!(ret3==-1))
            cout<<"Start work fail!"<<endl;
        cout<<"   Start work ret   "<<ret<<"  ret1  "<< ret1 <<" ret2 "<<ret2<<"    ret3   "<<ret3 <<endl;
        return ret3;
}


int XLTLib::GetEncoder()
{
	unsigned int mark = 0xABCDEF;
    int res = sendInt(mark);
    usleep(200000);
    recv(m_serverSocket, (char*)&m_coderPara, sizeof(int), 0);
    return m_coderPara;
}

int XLTLib::CloseDevice()
{
    close(m_serverSocket);
}

int XLTLib::OpenDevice(string IP, int Port)
{
    if( connectToServer(IP, Port) >0)
    {
        StopWork();

        setPackLen(MAX_PKT_LEN);
        return 1;
    }
//    startDMA();

}
int XLTLib::StopWork()
{

    m_iUT_TRMode=0;
    m_iUT_TGCEn=0;

    //stop ARM,reset DMA
    cout<<"sendPara(3, 0)" <<sendPara(3, 0)<<endl;
	//reset UT
    usleep(50000);  //等待DMA复位
    cout <<"sendPara(0x1007, 1);"<<sendPara(0x1007, 1)<<endl;
    usleep(50000);  //等待DMA复位
	//resetPA
    cout<<"sendPara(0x2006, 1);"<<sendPara(0x2006, 1)<<endl;
    usleep(50000);  //等待DMA复位
//    QMessageBox box;
//    box.setText("sss");
//    box.exec();
    return 0;

}

int XLTLib::setCrystaltype(int WorkMode, int channelnr, int type) //channelnr=0,1,2...7,type=0,PC 双 mode;type=1,PE 单 mode
{
    if(type)
        m_iUT_TRMode = (1<<channelnr) | m_iUT_TRMode;
    else
        m_iUT_TRMode = (!(1<<channelnr)) & m_iUT_TRMode;

    int ret=sendPara(0x1002, m_iUT_TRMode);
    return ret;
}


int XLTLib::OpenDataStream(char* buffer)
{
    fd_set set;
    int ret_rc = 0;
    sendInt(0xEEEE);
    //QTime tt,tt1;
    int ttime1=0;
    //tt.start();
    //tt1.start();
    int16_t *ptr = (int16_t *)buffer;
//  QFile file("data.csv");
//  file.open(QIODevice::WriteOnly | QIODevice::Text);
//  QTextStream in(&file);
    int times=0;
    while (ret_rc < MAX_PKT_LEN)
    {
        FD_ZERO(&set);
        FD_SET(m_serverSocket, &set);
        struct timeval to = {2, 0};
        int res = select(m_serverSocket + 1, &set, NULL, NULL, &to);
        //tt1.restart();
        if (res < 0)
        {
            cout << "select error"<<endl;

            break;
        }
        if (res == 0)
        {
            cout << "write time out"<<endl;

            break;
        }
        int tempNum = recv(m_serverSocket, buffer + ret_rc, MAX_PKT_LEN - ret_rc, 0);
        //ttime1=ttime1 + tt1.elapsed();
        sendInt(0xDDDD);
//        for(int j=ret_rc; j<ret_rc+tempNum; j++)
//        {
//            in<<QString("%1").arg(*(ptr+j/4),16)<<",";
//            if(j%626 == 625)
//                in<<endl;
//        }
        ret_rc = ret_rc + tempNum;
        times++;
    }

    //int ttime=tt.elapsed();

//    QFile file("data.csv");
//    file.open(QIODevice::WriteOnly | QIODevice::Text);
//    QTextStream in(&file);
////           for(int i=0; i<min(100,MAX_PKT_LEN/1252); i=i++)
//        for(int i=0; i<180; i=i++)
//        {
//            for(int j=0; j<626; j++)
//            {
//                 in<<QString("%1").arg(*(ptr+i*626+j),16)<<",";
//            }
//            in<<endl;
//        }
//        file.close();

    int16_t *count;
    count = (int16_t *)(buffer+4);
    //qDebug()<<"opendatastream:"<<ret_rc<<"   count="<<*(count-2)<<"  "<<*(count-1)<<"  "<<(int16_t)*(buffer+5)<<"   "<<(int16_t)*(buffer+4)<<"   time="<<ttime<<"   tim31"<<ttime1<<"  times   "<<times<<endl;
    if (ret_rc<=0)
        return -1;
    else
        return ret_rc;
}

//获取channelnr通道的第slotNo声束的全部A扫波形
// bufferSrc:执行OpenStream带入的地址
//channelnr:读取数据的通道号
// bufferDst:目标的地址
//返回值：获取的A扫数据条数
int XLTLib::getData(int WorkMode,char *bufferSrc, int chnnelnr, int slotNo, int PackLen, int16_t ** bufferDst, int AScanNum)
{
    PackLen =  MAX_PKT_LEN/2;
    int16_t *src = (int16_t *)bufferSrc;
    int headPtr[MAX_PKT_LEN/626];
    int headNum=0;
    //获得实际数据长度
    int i = 0;
//    for (; i < PackLen-624; i++)
//    {
//        if((*(src + i) == -13091)&&(i%626!=0))
//        {
//            QMessageBox box;

//            box.setText("********package lost!*********"+ QString::number(i,10));
//            box.exec();
//        }
//    }

    for (; i < PackLen-624; i++)
    {
        if (*(src + i) == -13091)
        {

           headPtr[headNum]=i;

           headNum++;
//           qDebug()<<"getdata_i:"<<i<<"_"<<src[i+0]<<"_"<<src[i+1]<<"_"<<src[i+2]<<"_"<<src[i+3]<<endl;
        }
    }

//    int m_len = PackLen - i;
//    src = src + i;
//    qDebug()<<"getdata_i:"<<i<<"_"<<src[0]<<"_"<<src[1]<<"_"<<src[2]<<"_"<<src[3]<<*(src + i)<<endl;

    int ratio = 1;
    int Index = 0;
//    int Index_package = 0;
    if(WorkMode == 0)
    {
        //在TEST Mode下 bufferDst的长度 为26 个
        int Last_slot_no = 0;
        int getNum;
        if (!AScanNum)
            getNum=headNum;
        else
            getNum=max(1,min(AScanNum,headNum));
        Index=-1;
        int islot = 0;
        bool bNewAScan=false;
        for (int i = 0; i < headNum; i+=ratio)
        {
//            int Test_channle_no = *(src + (i * ASCAN_LENGTH) + 1)>>8;
            int chan_no = *(src + (headPtr[i] ) + 1)>>8;
            int slot_no = *(src + (headPtr[i] ) + 1) & 0x00FF;

            if (chan_no == 3)
            {
                if(slot_no == 0)
                //if(Last_slot_no>=slot_no)
                {
                      Index ++;
                      bNewAScan=true;
                      islot = 0;
                }
                Last_slot_no = slot_no;
                if (Index>=getNum)
                    break;

                if(bNewAScan){
                    //::copy(src + headPtr[i]  + 2, src + (headPtr[i] + 625) , bufferDst[Index]+slot_no*(ASCAN_LENGTH-2));
                    ::copy(src + headPtr[i]  + 2, src + (headPtr[i] + 625) , bufferDst[Index]+islot*(ASCAN_LENGTH-2));
                    islot++;
                }
            }
        }

    }
    else
    {
            int getNum;
            if (!AScanNum)
            {
                getNum =headNum;
            }
            else
            {
                getNum = max(1,min(AScanNum,headNum));
            }

            for (int i = 0; i < headNum; i+=ratio)
            {
//                 qDebug()<<"-"<<src[headPtr[i]  + 1]<<src[headPtr[i] + 2]<<headPtr[i];
                int chan_no = *(src + (headPtr[i] ) + 1)>>8;
                int slot_no = *(src + (headPtr[i] ) + 1) & 0x00FF;

//                qDebug()<<chnnelnr<<slotNo;

                if ((chan_no == chnnelnr) && (slot_no == slotNo))
                {
                    ::copy(src + headPtr[i]  + 2, src + (headPtr[i]+ 625) , bufferDst[Index]);
                    Index++;

//                    qDebug()<<"index_"<<Index<<endl;
                    if (Index>=getNum)
                        break;
                }
            }
    }
//    QFile file("data_singleCh.csv");
//    file.open(QIODevice::WriteOnly | QIODevice::Text);
//    QTextStream in(&file);

//           //for(int i=0; i<min(100,MAX_PKT_LEN/1252); i=i++)
//           for(int i=0; i<Index; i=i++)
//        {
//            for(int j=0; j<624*26; j++)
//            {
//                in<<QString("%1").arg(bufferDst[i][j])<<endl;
//            }
//            in<<endl;
//        }

//    file.close();


    return Index;
}


int XLTLib::CloseDataStream()
{
	StopWork();
}

int XLTLib::SetDelay(int WorkMode, int channelnr, int Delaytime)  //Delaytime=us,//channelnr:0~7
{

    Delaytime=max(ADDELAY_MIN ,min(Delaytime,ADDELAY_MAX));
    int h;
    int ret=0;
    if(channelnr >=0 && channelnr <8)
    {
        h = 0x1005;
        h = h | (channelnr << 8);
    }
    else if(channelnr >=16 && channelnr <18)
    {
        h = 0x2003;
    }
    else
        return ret;

    ret = sendPara(h, Delaytime*50);

    if(ret!=-1)
        return Delaytime;
    else
        return ret;

}

int XLTLib::SetDetectionType(int WorkMode, int channelnr, int detectiontype)
{
	return 1;
}

int XLTLib::SetGain(int WorkMode, int channelnr, double gain)//channelnr:0~7
{
    if(gain>GAIN_MAX)
    {
        gain = GAIN_MAX;
    }
    else if(gain<GAIN_MIN)
    {
        gain = GAIN_MIN;
    }
//    gain = max(GAIN_MIN,min(gain,GAIN_MAX));
    SetTGCEnable(channelnr, false);

  // int value=max(min(255,(int)(255*(abs((int)(gain+8)))/96)),0);
    float dlt=255.0*20/1200;
    int   value=max(min(255.0,(gain/80+0.1)/1.2*255),0.0);//+dlt;//tmp:0.02v Bias
   // qDebug()<<"------------------------------------Gain= "<<gain<<"   D Num=  "<<value<<"------------------------_______";
    int para=(value<<4) | (channelnr<<12);
//    cout<<"val="<<value<<hex<<"  para="<<para<<endl;
    int h=0;
    int ret = -1;
    if(channelnr >=0 && channelnr <8)
    {
        int iTR=m_iUT_TRMode&(1<<channelnr);
        h=0x1004;

        if ((WorkMode==2)&&(channelnr==0&&(iTR==0)))//焊缝模式
        {
            h=h|((channelnr+1) << 8);
            para=(value<<4)+((channelnr+1)<<12);

        }
        else
        {

            if (iTR==0)//PC
            {
                h=h|((channelnr+8) << 8);

            }
            else
            {
                h=h|((channelnr) << 8);
            }

        }
    }
    else if((channelnr >=16) && (channelnr <18))
    {
        h = 0x2002;
        para=(value<<4) ;
    }
    else
        return ret;

    ret = sendPara(h, para);
    cout<<"  setgain_ "<<h<<"_"<<((para&0xf000)>>12)<<"_"<<((para&0x0ff0)>>4)<<"     "<<channelnr<<endl;
    return ret;

}

int XLTLib::SetWeldLevel(int WorkMode, int level)
{
	int ret = sendPara(0x0002, level);
	return ret;
}

int XLTLib::SetSampleTime(int WorkMode, int channelnr, double sampletime)
{
	return 1;
}

int XLTLib::SetFilter(int WorkMode, int channelnr, int filtertype)
{
    int h;
    int ret = -1;
    if(channelnr >=0 && channelnr <8)
    {
        h = 0x1006 | (channelnr << 8);
    }
    else if(channelnr >=16 && channelnr <18)
    {
        h = 0x2004;
    }
    else
        return ret;

    ret = sendPara(h, filtertype);

    return ret;
}

int XLTLib::SetPulseWidth(int WorkMode ,int channelnr, int pulsewidth) //pluswidth,ns
{
    pulsewidth = max(PULSEWIDTH_MIN,min(PULSEWIDTH_MAX,pulsewidth));
    int ret = -1;
    if(channelnr < 8 && channelnr >= 0)
    {
        int h = 0x1003 | (channelnr << 8);
        ret = sendPara(h, pulsewidth/5);
    }
    else if(channelnr < 18 && channelnr >= 16)
    {
        int h = 0x2001;
        ret = sendPara(h, pulsewidth/5);
    }
	return ret;
}


//SlotNum 声束数； ElementNum 阵元数,必须PA1、PA2同时顺序发完！//delaytime  us
int XLTLib::SetFocusLaw(int WorkMode ,float** delaytime1, int SlotNum1, int ElementNum1,float** delaytime2, int SlotNum2, int ElementNum2)
{

   // qDebug()<<"WorkMode  "<<WorkMode<<".SlotNum1   "<< SlotNum1<<"  ElementNum1  "<<ElementNum1   <<"SlotNum2  "<<SlotNum2;
    int len=1+(1+SlotNum1*ElementNum1)+(1+SlotNum2*ElementNum2);
    int* buffer = new int[len];
    int ex = 0;
    ex = ((0x4 << 6) + (0xE << 2) + 2) << 22;
    int index = 0;
    buffer[index++] = ex;

    int group = 0;
    int i = 0;//PA1

    group = ((((((((((0x4 << 4) + 0xD) << 2) + i + 1) << 5) + SlotNum1) << 6) + 1) << 5) + ElementNum1) << 6;
    buffer[index++] = group;
    for (int j = 0; j < SlotNum1; j++)
    {
        for (int k = 0; k <  ElementNum1; k++)
        {
            //j + 1, k + 1, m_delay[i][j][k]
            int slot = 0;
            int16_t time = 50*delaytime1[j][k];
            slot = ((((((((0x4 << 4) + 0xC) << 5) + j + 1) << 6) + k + 1) << 10) + time) << 3;
            buffer[index++] = slot;
        }
    }
    cout<<"Focus Law: First  "<<hex<<buffer[0]<<"    Group1   "<<hex<<buffer[1];

    i=1; //PA2
    group = ((((((((((0x4 << 4) + 0xD) << 2) + i + 1) << 5) + SlotNum2) << 6) + 1) << 5) + ElementNum2) << 6;
    buffer[index++] = group;

    cout <<"   Group2   "<<hex<<buffer[index-1]<<"   ";

    for (int j = 0; j < SlotNum2; j++)
    {
        for (int k = 0; k <  ElementNum2; k++)
        {
            //j + 1, k + 1, m_delay[i][j][k]
            int slot = 0;
            int16_t time = 50*delaytime2[j][k];
            slot = ((((((((0x4 << 4) + 0xC) << 5) + j + 1) << 6) + k + 1) << 10) + time) << 3;
            buffer[index++] = slot;
        }
    }
    //qDebug()<<"  Focus Law End:  "<<index<<"\n";
    //发送给arm等待。

//    QFile file("focauslaw.csv");
//    file.open(QIODevice::WriteOnly | QIODevice::Text);
 //   QTextStream in(&file);

 //   for(int i = 0;i<=index/16;i++)
 //   {
 //       for(int j = 0 ;j<16;j++)
 //       {
//        in<<(buffer[i*16]+j) <<",";
//        }
//        in<<"\n";
//    }

//    file.close();

    sendInt(0x78);
    sendInt(index);
    int ret = sendInt(buffer, index);
    delete[] buffer;
    return ret;

}


/*int XLTLib::SetFocusLaw(int WorkMode ,int* channelnr, long*** delaytime, int len_1, int len_2, int len_3)
{
    len_1=2;
    int len = 1 + len_1 * (1 + len_2 * len_3);
	int* buffer = new int[len];
	int ex = 0;
    ex = ((0x4 << 6) + (0xE << 2) + 2) << 22;
    int index = 0;
    buffer[index++] = ex;
    for (int i = 0; i < len_1; i++)
    {
    	int group = 0;
    	group = ((((((((((0x4 << 4) + 0xD) << 2) + i + 1) << 5) + len_2) << 6) + 1) << 5) + len_3) << 6;
    	buffer[index++] = group;
		for (int j = 0; j < len_2; j++)
    	{
    		for (int k = 0; k < len_3; k++)
    		{
    			//j + 1, k + 1, m_delay[i][j][k]
    			int slot = 0;
    			slot = ((((((((0x4 << 4) + 0xC) << 5) + j + 1) << 6) + k + 1) << 10) + delaytime[i][j][k]) << 3;
				buffer[index++] = slot;
			}
		}
	}
	int ret = sendInt(buffer, len);
    delete[] buffer;
    return ret;
}*/

int XLTLib::Alarm(int type)
{
    int h = 0x300C;
    int ret = sendPara(h, type);
    return ret;
}

//bit0~bit7 -- 0~7通道接收
int XLTLib::SetTGCEnable(int channelnr, bool flag)
{
	int ret, h;
	h = 0x1008;
    //qDebug()<<"TGC Enable  "<<hex<<h<<hex<<m_iUT_TGCEn<<"   channelnr="<<channelnr;
	if(channelnr>=0 && channelnr<8)
	{
        if(flag)
          m_iUT_TGCEn = (1<<channelnr) | m_iUT_TGCEn;
        else
           m_iUT_TGCEn = (!(1<<channelnr)) & m_iUT_TGCEn;
	}
	else
        return 0;
    //qDebug()<<"TGC Enable  "<<hex<<h<<hex<<m_iUT_TGCEn;
    ret = sendPara(h, m_iUT_TGCEn);

	return ret;
}

int XLTLib::SetTGCCurve(int WorkMode,int channelnr, double *timeList, double* gainList, int Len)  //channelnr=0~7
{
	int ret = sendPara(0x1009, TGC_LEN);
	if(!ret)
	return 0;

//	SetTGCEnable(channelnr, false);

	double tempValue;

	//排序
	int ptNum;
    double *gainList_pt;
    for(int i=0; i<Len-1; i++)
    {
        for(int j=i; j<Len-1; j++)
        {
            if(timeList[j]>timeList[j+1])
            {
                tempValue = timeList[j+1];
                timeList[j+1] = timeList[j];
                timeList[j] = tempValue;

                tempValue = gainList[j+1];
                gainList[j+1] = timeList[j];
                gainList[j] = tempValue;
            }
        }
    }
	ptNum = (timeList[Len-1]+1)/2;
	gainList_pt = new double[TGC_LEN];

	int x0, x1, x2, j;
	double y1, y2;

    //2us 步进
    x0 = (timeList[0]+1)/2;
	for(int i=0; i<Len-1; i++)
    {
        x1 = (timeList[i]+1)/2;
        x2 = (timeList[i+1]+1)/2;
        y1 = gainList[i];
        y2 = gainList[i+1];

        float k = (y2-y1)/(timeList[i+1]-timeList[i]);
        for(j=x1-x0; j<=x2-x0; j++)
        {
            gainList_pt[j] = y1 + k*(2.0*(j+x0)-timeList[i]);
        }
    }

    for(int i=j; i<TGC_LEN; i++)
    {
        gainList_pt[i] = gainList_pt[j-1];
    }


    //
    ret = SetTGCTime(channelnr, timeList[0]+2-0.64);   //AD5308 25MHz串行配置用时640ns

    if(!ret)
        return ret;
    for(int i=0; i<TGC_LEN; i++)
    {
        ret = SetTGCData(WorkMode,channelnr, gainList_pt[i]);
        //qDebug()<<"i="<<i<<"gainList_pt"<<gainList_pt[i];
        if(!ret)
            return ret;
    }
    ret = SetTGCEnable(channelnr, true);
    return ret;
}

int XLTLib::SetTGCTime(int ChannelNo, double time)  //ChannelNo:0-7, time是us
{
	int ret, h;

	if(ChannelNo < 8 && ChannelNo>=0)
	{
        h = 0x100A | (ChannelNo<<8);
	}

	int para = time*50;
    ret = sendPara(h, para);
    //qDebug()<<"h="<<h<<"   ChannelNo"<<ChannelNo;
	return ret;
	}

int XLTLib::SetTGCData(int WorkMode, int ChannelNo, double data)  //ChannelNo:0-7
{
    int ret, h, No, value;
    ret = -1;

    int iTR=m_iUT_TRMode&(1<<ChannelNo);
    if ((WorkMode==2)&&(ChannelNo==0&&(iTR==0)))//焊缝模式
    {
        No = 1;
    }
    else if(iTR==0)
        No = ChannelNo + 8;
    else
        No = ChannelNo;

    if(ChannelNo < 8 && ChannelNo>=0)
    {
        h = 0x100B | (ChannelNo<<8);

        value=max(min(255,(int)(255*(abs((int)(data+8)))/96)),0);
        int para = 0xF000 & (No<<12);
        para = para | (value<<4);

        ret = sendPara(h, para);
       // qDebug()<<"tcg_data_h="<<h<<"   tgc_data_para="<<para;
    }

    return ret;
}


int XLTLib::SetTriggerMode(int TriggerMode, int Div)
{
	int ret;
	if(TriggerMode == 0)
	ret = sendPara(0x3001, TriggerMode);
	else
	{
	ret = sendPara(0x3001, 1);
	if(ret)
	    ret = sendPara(0x3002, Div);
	}
	return ret;
}
int XLTLib::startDMA()
{

    int para = 0xcde<<20 | MAX_PKT_LEN;
    int ret = sendInt(para);
    if (ret == -1)
    {
        cout << "set PKT_LEN ERROR" << endl;
        return -1;
    }
    usleep(10000);
    ret = sendInt(97);
	if (ret == -1)
	{
		cout << "StartDMA ERROR" << endl;
        return -1;
	}
	else
	{
		cout << "StartDMA" << endl;
		return 1;
	}
    usleep(50000);  //等待DMA复位
    return ret;
}

int XLTLib::sendPara(int sign, int value)
{

	int para = (sign << 16) + value;
//    cout<<"sendint__"<<para<<endl;
	int ret = sendInt(para);
	return ret;
}

int XLTLib::sendInt(int sign)
{
    int ret = ::write(m_serverSocket, &sign, sizeof(int));
    return ret;
}

int XLTLib::setPackLen(int PackLen)
{
    int para = 0xcde<<20 | MAX_PKT_LEN;
    int ret = sendInt(para);
    if (ret == -1)
    {
        cout << "set PKT_LEN ERROR" << endl;
        return -1;
    }
    usleep(10000);
}

int XLTLib::sendInt(int *data, int len)
{

//    int ret = ::write(m_serverSocket, data, len*sizeof(int));
    int ret = 0;
    while(ret<len*sizeof(int))
    {
        ret += ::write(m_serverSocket, data+ret/4, (len-ret/4)*sizeof(int));
    }
    //qDebug()<<"_______________**********ret/4***********_______________"<<ret;
    return ret;
}

int XLTLib::connectToServer(string IP, int Port)
{
	int recv_buffer = 1024 * 1024 * 64;
	setsockopt(m_serverSocket, SOL_SOCKET, SO_RCVBUF, (const char*)(&recv_buffer), sizeof(int));
	short port = Port;
	struct sockaddr_in serverAddr;
	serverAddr.sin_family = AF_INET;
	serverAddr.sin_addr.s_addr = inet_addr(IP.data());
	serverAddr.sin_port = htons(port);
	memset(serverAddr.sin_zero, 0x00, 8);
	//cout <<"ip"<<IP;
	int ret = connect(m_serverSocket, (struct sockaddr*)(&serverAddr), sizeof (serverAddr));
	if (ret == -1)
	{
		cout << "Connect Error: " << endl;
		return -1;
	}
	else
	{
		cout << "Connect Success!" << endl;

		return 1; 
	}

}

int XLTLib::createSocket()
{
	m_iUT_TRMode=0;
	m_iUT_TGCEn=0;
	MAX_PKT_LEN = 180*626*2;
	m_serverSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	if (m_serverSocket == -1)
	{
		cout << "Create Socket Failed: "  << endl;
		return -1;
	}
	return 1;
}

int XLTLib::GetPackLen()
{
    return MAX_PKT_LEN;
}

//int XLTLib::initSocket()
//{
//	WSADATA ws;
//	if (WSAStartup(MAKEWORD(2, 2), &ws) != 0)
//	{
//		cout << "Init Windows Socket Failed: " << GetLastError() << endl;
//		return -1;
//	}
//	return 1;
//}
