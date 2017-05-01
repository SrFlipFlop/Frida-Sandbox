#include <jni.h>
#include <string>
#include <stdlib.h>

char * get_secret_db() {
    char *secret;
    strcpy(secret, "h4x0r_s3kr3t");
    return secret;
}

bool is_correct(char *secret) {
    return (sizeof(secret) == 12);
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_srflipflop_fridasandbox_ChecksActivity_vmCheckJNI(JNIEnv *env, jobject instance) {
    return true
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_srflipflop_fridasandbox_ChecksActivity_rootCheckJNI(JNIEnv *env, jobject instance) {
    return true;
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_srflipflop_fridasandbox_ChecksActivity_tamperCheckJNI(JNIEnv *env, jobject instance) {
    return true;
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_srflipflop_fridasandbox_ChecksActivity_pinningCheckJNI(JNIEnv *env, jobject instance) {
    return true;
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_srflipflop_fridasandbox_HookActivity_sendInfoJNI(JNIEnv *env, jobject instance) {
    char *secret = get_secret_db();
    return is_correct(secret);
}

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