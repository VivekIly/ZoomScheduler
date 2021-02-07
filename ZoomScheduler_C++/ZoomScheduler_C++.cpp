#ifdef _WIN32
#include <Windows.h>
#else
#include <unistd.h>
#endif
#include <iostream>
#include <cstdlib>
#include <chrono>
#include <thread>

int main()
{
    std::cout << "Hello, ";
    std::cout.flush();
    //std::this_thread::sleep_for(std::chrono::milliseconds(1000));
    std::cout << "World!";
    std::cout << std::endl;

    for (int i = 0; i < 1; i--) {
        time_t rawtime;
        struct tm* timeinfo;
        char buffer[1];
        time(&rawtime);
        timeinfo = localtime(&rawtime);

        strftime(buffer, 80, "%Y/%m/%d %H:%M:%S> ", timeinfo);

        std::cout << '\r' << buffer;
        std::cout.flush();

        std::this_thread::sleep_for(std::chrono::milliseconds(1000));
    }

    system("start /max chrome.exe https://naperville203.zoom.us/j/96493406080");

    return 0;
}