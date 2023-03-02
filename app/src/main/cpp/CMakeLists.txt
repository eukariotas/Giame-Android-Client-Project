cmake_minimum_required(VERSION 3.18.1)
project(stockfish-android)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++17 -stdlib=libc++")
add_library(stockfish SHARED
        evaluate.cpp
        benchmark.cpp
        bitbase.cpp
        bitboard.cpp
        endgame.cpp
        main.cpp
        material.cpp
        misc.cpp
        movegen.cpp
        movepick.cpp
        pawns.cpp
        position.cpp
        psqt.cpp
        search.cpp
        thread.cpp
        timeman.cpp
        tt.cpp
        tune.cpp
        uci.cpp
        ucioption.cpp
        nnue/evaluate_nnue.cpp
        nnue/features/half_ka_v2_hm.cpp
        syzygy/tbprobe.cpp
        )

find_library(log-lib log)
add_library(native SHARED native.cpp)
add_library(c++_shared SHARED IMPORTED)
INCLUDE_DIRECTORIES(nnue)

target_link_libraries(stockfish c++ c++abi)
target_compile_options(stockfish PRIVATE -DNO_PREFETCH=1)
set_target_properties(c++_shared PROPERTIES IMPORTED_LOCATION ${ANDROID_SYSROOT}/usr/lib/${ANDROID_ABI}/libc++_shared.so)


