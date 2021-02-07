#include <Windows.h>

//--------------------------------------------------------------------- hideConsole
void hideConsole() {
	::ShowWindow(::GetConsoleWindow(), SW_HIDE);
}

//--------------------------------------------------------------------- showConsole
void showConsole() {
	::ShowWindow(::GetConsoleWindow(), SW_SHOW);
}