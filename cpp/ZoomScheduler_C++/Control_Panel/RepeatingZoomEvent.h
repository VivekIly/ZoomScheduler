#ifndef REPEATINGZOOMEVENT
#define REPEATINGZOOMEVENT

class RepeatingZoomEvent {
private:
	std::string m_name;
	std::string m_url;
	std::string m_time;
	std::string m_days;

	void setAttributes(std::string name, std::string url, std::string time, std::string days);

public:
	RepeatingZoomEvent();
	RepeatingZoomEvent(std::string name, std::string url, std::string time, std::string days);

	void openLink();
	bool serialize();
	RepeatingZoomEvent fetch(std::string nameOfFile);

	std::string getName() { return m_name; }
	std::string getURL() { return m_url; }
	std::string getTime() { return m_time; }
	std::string getDays() { return m_days; }
};

#endif