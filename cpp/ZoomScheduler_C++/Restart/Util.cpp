#include <Windows.h>
#include <iostream>

void killCP() {
    system("cd \"C:\\Windows\\System32\" && taskkill /IM \"Control_Panel.exe\" /F");
}

void killMain() {
    system("cd \"C:\\Windows\\System32\" && taskkill /IM \"ZoomScheduler_C++.exe\" /F");
}

void startCP() {
    system("start C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\Control_Panel\\Release\\Control_Panel.exe");
}

void startMain() {
    system("start C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\Main\\Release\\ZoomScheduler_C++.exe");
}