package com.sheenline.muis.common;

public class TestJNI {

	public native int Alarm(int type);

	public native int CloseDataStream();

	public native int CloseDevice();

	public native boolean DestroyXLTLib();

	public native int[] getData(int WorkMode, char[] bufferSrc, int chnnelnr, int slotNo, int PackLen, int len,
			int AScanNum);

	public native int GetEncoder();

	public native boolean InitXLTLib();

	public native int only_startdma();

	public native int only_startsystem();

	public native int only_startut();

	public native int OpenDataStream(char[] buffer);

	public native int OpenDevice(String IP, int Port);

	public native int sendInt(int sign);

	public native int sendPara(int sign, int value);

	public native int setCrystaltype(int WorkMode, int channelnr, int type);

	public native int SetDelay(int WorkMode, int channelnr, int Delaytime);

	public native int SetDetectionType(int WorkMode, int channelnr, int detectiontype);

	public native int SetFilter(int WorkMode, int channelnr, int filtertype);

	public native int SetFocusLaw(int WorkMode, float[] delaytime1, int SlotNum1, int ElementNum1,
			float[] delaytime2, int SlotNum2, int ElementNum2);

	public native int SetGain(int WorkMode, int channelnr, double gain);

	public native int SetMode(int WorkMode, int Level);

	public native int SetPulseWidth(int WorkMode, int channelnr, int pluswidth);

	public native int SetSampleTime(int WorkMode, int channelnr, double sampletime);

	public native int SetTGCCurve(int WorkMode, int channelnr);

	public native int SetTGCEnable(int channelnr, boolean flag);

	public native int SetTriggerMode(int TriggerMode, int Div);

	public native int SetWeldLevel(int WorkMode, int level);

	public native int StartWork(int WorkMode, int slotNum);

	public native int StopWork();

}
