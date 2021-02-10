#ifndef UTIL
#define UTIL

#include <vector>
char* getCurrentTime();
std::string getCurrentTimeComp();
std::string getCurrentDateComp();
void hideConsole();
void showConsole();
std::vector<std::string> getFiles();
bool isVisible();
inline bool endsIn(std::string const& value, std::string const& ending);
inline bool fileExists(const std::string& name);

#endif