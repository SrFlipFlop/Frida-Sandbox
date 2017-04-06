#include <jni.h>
#include <string>
#include <stdlib.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_srflipflop_fridasandbox_ImplementActivity_implementJNI(JNIEnv *env, jobject instance) {
    int random;
    char result[5] = "";
    char values[10] = {'0','1','2','3','4','5','6','7','8','9'};

    srand(time(NULL));
    for (int i = 0; i < 4; i++) {
        random = rand() % 10;
        result[i] = values[random];
    }

    return env->NewStringUTF(result);
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