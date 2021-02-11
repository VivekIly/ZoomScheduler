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
void startMainZS();
void checkSyntax(std::string command);
void runCommand(std::string& command);
void getCommand(bool displayInd);
inline bool fileExists(const std::string& name);
bool IsMainRunning();
bool killMain();
bool killCP();
void hideConsole();
bool toggleMainVisOut();
bool toggleDefMainVisOut();
void showConsole();
std::vector<std::string> getFiles();
inline bool endsIn(std::string const& value, std::string const& ending);

#endif