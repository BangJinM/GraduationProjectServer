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

	class IStream
	{
	public:
		IStream(char* buf, std::size_t size);
		IStream(const IStream& other);
		IStream(IStream&& other);
		~IStream();

		IStream& operator= (const IStream& other);
		IStream& operator= (IStream&& other);

		void copy(const char* bytes, const std::size_t size);
		void clear();
		void fastSet(char* bytes, const std::size_t size);

		std::size_t getSize() const;
		std::size_t getLength() const;
		std::size_t getAvailableSize() const;
		char* getBytes() const;
		void resetCursor();
		void seek(long offset, StreamSeekDir dir = StreamSeekDir::cur);

		IStream& operator<<(char v);
		IStream& operator<<(unsigned char v);
		IStream& operator<<(short v);
		IStream& operator<<(unsigned short v);
		IStream& operator<<(int v);
		IStream& operator<<(unsigned int v);
		IStream& operator<<(long long v);
		IStream& operator<<(unsigned long long v);
		IStream& operator<<(const char* v);
		IStream& operator<<(const std::string& v);
		IStream& operator<<(float v);
		IStream& operator<<(double v);
		IStream& operator<<(long double v);
		IStream& operator<<(bool v);

		IStream& write(const char* buf, std::size_t size);

	private:
		void move(IStream& other);
		inline void writeStrLen(const std::size_t& len);

	private:
		char* _bytes;
		std::size_t _size;
		std::size_t _cursor;
	};

	class OStream
	{
	public:
		OStream(char* buf, std::size_t size);
		OStream(const OStream& other);
		OStream(OStream&& other);
		~OStream();

		OStream& operator= (const OStream& other);
		OStream& operator= (OStream&& other);

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

		OStream& operator>>(char& v);
		OStream& operator>>(unsigned char& v);
		OStream& operator>>(short& v);
		OStream& operator>>(unsigned short& v);
		OStream& operator>>(int& v);
		OStream& operator>>(unsigned int& v);
		OStream& operator>>(long long& v);
		OStream& operator>>(unsigned long long& v);
		OStream& operator>>(char* v);
		OStream& operator>>(std::string& v);
		OStream& operator>>(float& v);
		OStream& operator>>(double& v);
		OStream& operator>>(long double& v);
		OStream& operator>>(bool& v);

		OStream& read(char* buf, std::size_t size);
		bool readCString(char* buf, std::size_t size);

	private:
		void move(OStream& other);
		std::size_t readStrLen();

	private:
		char* _bytes;
		std::size_t _size;
		std::size_t _cursor;
	};
}
#endif