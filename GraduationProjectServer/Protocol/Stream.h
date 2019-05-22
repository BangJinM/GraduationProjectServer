#ifndef  _Protocol_Stream_H_
#define  _Protocol_Stream_H_
#include <string>
namespace network {
	enum StreamSeekDir
	{
		beg = 0,
		cur,
		end
	};

	class NIStream
	{
	public:
		NIStream(char* buf, std::size_t size);
		NIStream(const NIStream& other);
		NIStream(NIStream&& other);
		~NIStream();

		NIStream& operator= (const NIStream& other);
		NIStream& operator= (NIStream&& other);

		void copy(const char* bytes, const std::size_t size);
		void clear();
		void fastSet(char* bytes, const std::size_t size);

		std::size_t getSize() const;
		std::size_t getLength() const;
		std::size_t getAvailableSize() const;
		char* getBytes() const;
		void resetCursor();
		void seek(long offset, StreamSeekDir dir = StreamSeekDir::cur);

		NIStream& operator<<(char v);
		NIStream& operator<<(unsigned char v);
		NIStream& operator<<(short v);
		NIStream& operator<<(unsigned short v);
		NIStream& operator<<(int v);
		NIStream& operator<<(unsigned int v);
		NIStream& operator<<(long long v);
		NIStream& operator<<(unsigned long long v);
		NIStream& operator<<(const char* v);
		NIStream& operator<<(const std::string& v);
		NIStream& operator<<(float v);
		NIStream& operator<<(double v);
		NIStream& operator<<(long double v);
		NIStream& operator<<(bool v);

		NIStream& write(const char* buf, std::size_t size);

	private:
		void move(NIStream& other);
		inline void writeStrLen(const std::size_t& len);

	private:
		char* _bytes;
		std::size_t _size;
		std::size_t _cursor;
	};

	class NOStream
	{
	public:
		NOStream(char* buf, std::size_t size);
		NOStream(const NOStream& other);
		NOStream(NOStream&& other);
		~NOStream();

		NOStream& operator= (const NOStream& other);
		NOStream& operator= (NOStream&& other);

		void copy(const char* bytes, const std::size_t size);
		void clear();
		void fastSet(char* bytes, const std::size_t size);

		std::size_t getSize() const;
		std::size_t getAvailableSize() const;
		char* getBytes() const;
		char* getReadData() const;
		void resetCursor();
		void seek(long offset, StreamSeekDir dir = StreamSeekDir::cur);
		void pop(std::size_t size);

		NOStream& operator>>(char& v);
		NOStream& operator>>(unsigned char& v);
		NOStream& operator>>(short& v);
		NOStream& operator>>(unsigned short& v);
		NOStream& operator>>(int& v);
		NOStream& operator>>(unsigned int& v);
		NOStream& operator>>(long long& v);
		NOStream& operator>>(unsigned long long& v);
		NOStream& operator>>(char* v);
		NOStream& operator>>(std::string& v);
		NOStream& operator>>(float& v);
		NOStream& operator>>(double& v);
		NOStream& operator>>(long double& v);
		NOStream& operator>>(bool& v);

		NOStream& read(char* buf, std::size_t size);
		bool readCString(char* buf, std::size_t size);

	private:
		void move(NOStream& other);
		std::size_t readStrLen();

	private:
		char* _bytes;
		std::size_t _size;
		std::size_t _cursor;
	};
}
#endif