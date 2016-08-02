#include <stdio.h>
#include <stdlib.h>
#include "com_sheenline_muis_common_TestJNI.h"
#include "xltlib.h"

XLTLib *pCAdd = NULL;
char* cpw;
char Package_Data[450720];
int focuslen = 16;

int16_t ** channel_data;

int16_t Data[50][626];

JNIEXPORT jboolean JNICALL Java_com_sheenline_muis_common_TestJNI_InitXLTLib(
		JNIEnv * env, jobject obj) {
	cpw = Package_Data;
	channel_data = new (int16_t*[150]);

	for (int i = 0; i < 150; i++) {
		channel_data[i] = Data[i];
	}

	if (pCAdd == NULL) {
		pCAdd = new XLTLib;
	}
	return pCAdd != NULL;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_OpenDevice(
		JNIEnv *env, jobject obj, jstring ip, jint port) {

	const char* ipstr = env->GetStringUTFChars(ip, NULL);
	env->ReleaseStringUTFChars(ip, ipstr);

	int i = -1;
	i = pCAdd->OpenDevice(ipstr, port);
	return i;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_CloseDevice(
		JNIEnv *env, jobject obj) {

	int i = -1;
	i = pCAdd->CloseDevice();
	return 1;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_SetMode(
		JNIEnv *env, jobject obj, jint x, jint y) {
	int i = -1;
	i = pCAdd->SetMode(x, y);
	return i;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_StartWork(
		JNIEnv *env, jobject obj, jint x, jint y) {

	int i = -1;
	i = pCAdd->Startwork(x, y);
	return i;

}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_StopWork(
		JNIEnv *env, jobject obj) {

	int i = -1;
	i = pCAdd->StopWork();
	return 1;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_SetTriggerMode(
		JNIEnv *env, jobject obj, jint x, jint y) {

	int i = -1;
	i = pCAdd->SetTriggerMode(x, y);
	return 1;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_setCrystaltype(
		JNIEnv *env, jobject obj, jint x, jint y, jint z) {

	int i = -1;
	i = pCAdd->setCrystaltype(x, y, z);
	return i;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_SetDelay(
		JNIEnv *env, jobject obj, jint x, jint y, jint z) {

	int i = -1;
	i = pCAdd->SetDelay(x, y, z);
	return i;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_SetDetectionType(
		JNIEnv *env, jobject obj, jint x, jint y, jint z) {

	int i = -1;
	i = pCAdd->SetDetectionType(x, y, z);
	return 1;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_SetGain(
		JNIEnv *env, jobject obj, jint x, jint y, jdouble z) {

	int i = -1;
	i = pCAdd->SetGain(x, y, z);
	return i;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_SetSampleTime(
		JNIEnv *env, jobject obj, jint x, jint y, jdouble z) {

	int i = -1;
	i = pCAdd->SetSampleTime(x, y, z);
	return 1;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_SetWeldLevel(
		JNIEnv *env, jobject obj, jint x, jint y) {

	int i = -1;
	i = pCAdd->SetWeldLevel(x, y);
	return 1;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_SetFilter(
		JNIEnv *env, jobject obj, jint x, jint y, jint z) {

	int i = -1;
	i = pCAdd->SetFilter(x, y, z);
	return 1;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_SetPulseWidth(
		JNIEnv *env, jobject obj, jint x, jint y, jint z) {

	int i = -1;
	i = pCAdd->SetPulseWidth(x, y, z);
	return i;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_SetFocusLaw(
		JNIEnv *env, jobject obj, jint wm, jfloatArray dt1, jint sn1, jint en1,
		jfloatArray dt2, jint sn2, jint en2) {

	jfloat* focusdt1 = env->GetFloatArrayElements(dt1, JNI_FALSE);
	jsize focusdt1size = env->GetArrayLength(dt1);

	jfloat * focusdt2 = env->GetFloatArrayElements(dt2, JNI_FALSE);
	jsize focusdt2size = env->GetArrayLength(dt2);

	int len = focusdt1size;

	int slotlen = focusdt1size / focuslen;
	float** prfocus = new float*[slotlen];

	int k = 0;

	for (int i = 0; i < slotlen; i++) {

		prfocus[i] = new float[focuslen];
		for (int j = 0; j < focuslen; j++) {
			prfocus[i][j] = (float) focusdt1[k];
			k++;

		}
	}

	//return k;

	int i = -1;
	i = pCAdd->SetFocusLaw(wm, prfocus, sn1, en1, prfocus, sn2, en2);
	return i;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_OpenDataStream(
		JNIEnv *env, jobject obj, jcharArray xchar) {

	memset(Package_Data, 0, 450720 * sizeof(char));
	int i = pCAdd->OpenDataStream(cpw);

	return i;
}

JNIEXPORT jintArray JNICALL Java_com_sheenline_muis_common_TestJNI_getData(
		JNIEnv *env, jobject obj, jint wm, jcharArray aa, jint chnr,
		jint slotnr, jint pklen, jint datalen, jint acannum) {

	int i = -1;
	memset(Data, 0, 50 * 626 * sizeof(char));

	jintArray arr = env->NewIntArray(624);

	jint arrdata[624];

	i = pCAdd->getData(wm, cpw, chnr, slotnr, pklen, channel_data, acannum);

	for (int i = 0; i < 624; i++) {
		arrdata[i] = (jint) channel_data[0][i];
	}

	env->SetIntArrayRegion(arr, (jsize) 0, (jsize) 624, arrdata);

	return arr;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_CloseDataStream(
		JNIEnv * env, jobject obj) {

	int i = -1;
	i = pCAdd->CloseDataStream();
	return i;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_GetEncoder(
		JNIEnv *env, jobject obj) {

	int i = -1;
		i = pCAdd->GetEncoder();
		return i;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_Alarm(JNIEnv *env,
		jobject obj, jint type) {

	int i = -1;
	i = pCAdd->Alarm((int) type);
	return i;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_SetTGCEnable(
		JNIEnv *env, jobject obj, jint x, jboolean y) {

	    int i = -1;
		i = pCAdd->SetTGCEnable(x,y);
		return i;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_SetTGCCurve(
		JNIEnv *env, jobject obj, jint x, jint y) {


            double m_TimeFrom[4] ={ 20,50,80,110};
            double TGC_gain[4]={10,20,40,40};

	        int i = -1;
	 		i = pCAdd->SetTGCCurve(x,y,m_TimeFrom,TGC_gain,4);
			return i;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_only_1startdma(
		JNIEnv *, jobject) {

	return 1;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_only_1startsystem(
		JNIEnv *, jobject) {

	return 1;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_only_1startut(
		JNIEnv *, jobject) {

	return 1;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_sendPara(JNIEnv *,
		jobject, jint, jint) {

	return 1;
}

JNIEXPORT jint JNICALL Java_com_sheenline_muis_common_TestJNI_sendInt(JNIEnv *,
		jobject, jint) {

	return 1;
}

JNIEXPORT jboolean JNICALL Java_com_sheenline_muis_common_TestJNI_DestroyXLTLib(
		JNIEnv *, jobject) {

	return 1;
}

