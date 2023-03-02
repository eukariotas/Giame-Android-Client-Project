cmake_minimum_required(VERSION 3.18.1)
project(stockfish)

set(CMAKE_CXX_STANDARD 17)

add_executable(stockfish
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
        )
INCLUDE_DIRECTORIES(nnue)

target_link_libraries(stockfish PRIVATE stlport)

target_compile_options(stockfish PRIVATE -DNO_PREFETCH=1)


