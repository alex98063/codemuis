LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)



LOCAL_MODULE := TestJNI

LOCAL_SRC_FILES := com_sheenline_muis_common_TestJNI.cpp
LOCAL_SRC_FILES += xltlib.cpp



include $(BUILD_SHARED_LIBRARY)