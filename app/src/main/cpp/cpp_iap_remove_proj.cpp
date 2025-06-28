#include <jni.h>
#include <android/log.h>

#define LOG_TAG "ptg"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_cpp_1iap_1remove_1proj_MainActivity_secondStepCalc(
        JNIEnv* env,
        jobject thiz,
        jint input) { // 👈 принимаем параметр из Java

    int intermediate = input * 2; // Пример расчёта

    // Логика: если intermediate > 10 → true, иначе → false
    if (intermediate > 10) {
        LOGD("true");
    } else {
        LOGD("false");
    }

    return intermediate; // Возвращаем результат в Java
}
