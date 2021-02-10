#ifndef UTIL
#define UTIL

#include <iostream>
#include <string>
#include <Windows.h>
#include <thread>
#include <array>
#include <fstream>
#include "Commands.h"

char* getCurrentDateTime();
char* getCurrentDateTimeName();
char* getCurrentTime();
void initialize();
void startMain();
void checkSyntax(std::string command);
void runCommand(std::string& command);
void getCommand(bool displayInd);
inline bool fileExists(const std::string& name);
bool IsMainRunning();
void hideConsole();
void showConsole();
char* getCurrentTime();

#endif