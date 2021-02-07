cmake_minimum_required(VERSION 3.17)
project(Control_Panel)

set(CMAKE_CXX_STANDARD 20)

add_executable(Control_Panel main.cpp Util.cpp Commands.cpp Commands.h Util.h)