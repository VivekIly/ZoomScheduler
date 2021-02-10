#ifndef ZOOMEVENT
#define ZOOMEVENT

class ZoomEvent {
private:
	std::string m_name;
	std::string m_url;
	std::string m_date;
	std::string m_time;

	void setAttributes(std::string name, std::string url, std::string date, std::string time);

public:
	ZoomEvent();
	ZoomEvent(std::string name, std::string url, std::string date, std::string time);

	void openLink();
	void serialize();
	ZoomEvent fetch(std::string nameOfFile);

	std::string getName() { return m_name; }
	std::string getDate() { return m_date; }
	std::string getTime() { return m_time; }
	std::string getURL() { return m_url; }
};

#endif