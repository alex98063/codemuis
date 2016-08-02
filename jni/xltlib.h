#include <sys/types.h>
//linux网络传输头文件
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <cstdlib>
//#include <cstring>
#include <string.h>
#include <stdio.h>
#include <iostream>
#include <unistd.h>
using namespace std;
using std::string;
//#define MAX_PKT_LEN_TEMP 450720    //char; max:450720   共360条slot
//#define MAX_PKT_LEN 182*626*2     //450720/2
#define TGC_LEN 100
#define ASCAN_LENGTH 626 // 字头两个16位，数据624个


#define ADDELAY_MAX 100//us
#define ADDELAY_MIN 0 //us
#define GAIN_MAX 80//us
#define GAIN_MIN 0 //us
#define PULSEWIDTH_MAX 500//ns
#define PULSEWIDTH_MIN 50 //ns
#define MODE_WELD 2// 焊缝模式
#define MODE_MATERIA 1 //母材模式
#define MODE_PA 3//相控阵模式
#define MODE_TEST 0//测试模式





class XLTLib
{
private:
    int m_iUT_TRMode;  //UT通道单双晶模式，1~8通道作为激发，上位机发“0000 0000 0000 0001”时表示通道1为单晶，发“0000 0000 0000 0000”时表示通道1为双晶；    上位机发“0000 0000 0000 0100”时表示通道3为单晶，发“0000 0000 0000 0000”时表示通道3为双晶。
    int m_iUT_TGCEn;        //UT通道TCG使能

public:
	XLTLib();
	int OpenDevice(string IP, int Port);
	int CloseDevice();
    int SetMode(int WorkMode, int Level);
    int Startwork(int WorkMode, int slotNum);//设置通道的个数，之后fpga开始采集数据
	int StopWork();
	int SetTriggerMode(int TriggerMode, int Div);
	int setCrystaltype(int WorkMode, int channelnr, int type);
	int SetDelay(int WorkMode, int channelnr, int Delaytime);
	int SetDetectionType(int WorkMode, int channelnr, int detectiontype);
	int SetGain(int WorkMode, int channelnr, double gain);
	int SetSampleTime(int WorkMode, int channelnr, double sampletime);
	int SetWeldLevel(int WorkMode, int level);
	int SetFilter(int WorkMode, int channelnr, int filtertype);
	int SetPulseWidth(int WorkMode , int channelnr, int pulsewidth);
//	int SetFocusLaw(int WorkMode ,int* channelnr, long*** delaytime, int len_1, int len_2, int len_3);
    int SetFocusLaw(int WorkMode ,float** delaytime1, int SlotNum1, int ElementNum1,float** delaytime2, int SlotNum2, int ElementNum2);
	int OpenDataStream(char* buffer);
    int getData(int WorkMode, char *bufferSrc, int chnnelnr, int slotNo, int PackLen, int16_t **bufferDst, int AScanNum);
	int CloseDataStream();
	int GetEncoder();
	int Alarm(int type);
    int SetTGCEnable(int channelnr, bool flag);
	int SetTGCCurve(int WorkMode,int channelnr, double *timeList, double* gainList, int Len) ;
    int only_startdma();
    int only_startsystem();
    int only_startut();
    //测试
    int sendPara(int sign, int value);
    //·¢ËÍÒ»žöÕûÐÍsign
    int sendInt(int sign);
    int setPackLen(int PackLen);
    int GetPackLen();

private:
	//	int initSocket();
	int createSocket();
	int connectToServer(string IP, int Port);
	int startDMA();
	//·¢ËÍ²ÎÊý(sign << 16)+value
//	int sendPara(int sign, int value);
//	//·¢ËÍÒ»žöÕûÐÍsign
//	int sendInt(int sign);
	//·¢ËÍÒ»žöÕûÐÍÊý×é
	int sendInt(int *data, int len); 
	//TCG
	int SetTGCTime(int ChannelNo, double time);
	int SetTGCData(int WorkMode, int ChannelNo, double data);
	
private:
	int m_serverSocket;
	int m_coderPara;
    int MAX_PKT_LEN;
};

