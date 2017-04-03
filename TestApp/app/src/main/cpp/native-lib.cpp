#include <jni.h>
#include <string>
#include <stdlib.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_srflipflop_fridasandbox_ImplementActivity_implementJNI(JNIEnv *env, jobject instance) {
    int n;
    jstring result = (jstring) "";
    char values[5] = {'a', 'b', 'c', 'd', 'e'};

    for (int i = 0; i < 4; ++i) {
        n = rand() % 5;
        result = result + values[n];
    }

    return result;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_srflipflop_fridasandbox_BruteActivity_bruteForceJNI(JNIEnv *env, jobject instance, jstring password_) {
    const char *result = "null";
    const char *secret = "4321";
    const char *password = env->GetStringUTFChars(password_, 0);

    int res = strncmp(secret, password, strlen(secret));
    if (res == 0) {
        result = "s3cr3tJnI!";
    }

    env->ReleaseStringUTFChars(password_, password);
    return env->NewStringUTF(result);
}