#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_slin_keyboard_BlindIME_getStringFromNative(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "Hello Word!");
}