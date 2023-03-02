#include <jni.h>
#include <string>
#include <iostream>

#include "bitboard.h"
#include "endgame.h"
#include "position.h"
#include "psqt.h"
#include "search.h"
#include "syzygy/tbprobe.h"
#include "thread.h"
#include "tt.h"
#include "uci.h"
#include "misc.h"

extern "C" JNIEXPORT jint JNICALL Java_es_eukariotas_giame_ui_MainActivity_sumar(JNIEnv *env, jobject obj, jint a, jint b) {
    return a + b;
}


