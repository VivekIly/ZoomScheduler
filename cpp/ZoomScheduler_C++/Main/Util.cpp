#include <Windows.h>
#include <chrono>

//--------------------------------------------------------------------- getCurrentTime
char* getCurrentTime() {
	time_t rawtime;
	struct tm* timeinfo;
	char buffer[1];
	time(&rawtime);
	timeinfo = localtime(&rawtime);

	strftime(buffer, 80, "%Y/%m/%d %H:%M:%S> ", timeinfo);

	return buffer;
}

//--------------------------------------------------------------------- hideConsole
void hideConsole() {
	::ShowWindow(::GetConsoleWindow(), SW_HIDE);
}

//--------------------------------------------------------------------- showConsole
void showConsole() {
	::ShowWindow(::GetConsoleWindow(), SW_SHOW);
}