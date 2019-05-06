#include "Stream.h"

namespace network{

	NIStream::NIStream(char* buf, std::size_t size) :
		_bytes(buf),
		_size(size),
		_cursor(0)
	{

	}

	NIStream::NIStream(const NIStream& other) : NIStream(nullptr, 0)
	{
		copy(other._bytes, other._size);
	}

	NIStream::NIStream(NIStream&& other) : NIStream(nullptr, 0)
	{
		move(other);
	}

	NIStream::~NIStream()
	{
		clear();
	}

	void NIStream::copy(const char* bytes, const std::size_t size)
	{
		clear();
		if (size > 0)
		{
			_size = size;
			_cursor = 0;
			_bytes = (char*)malloc(sizeof(char) * size);
			memcpy(_bytes, bytes, size);
		}
	}

	void NIStream::move(NIStream& other)
	{
		clear();
		_bytes = other._bytes;
		_size = other._size;
		_cursor = other._cursor;

		other._bytes = nullptr;
		other._size = 0;
		other._cursor = 0;
	}

	void NIStream::clear()
	{
		if (_bytes)
		{
			free(_bytes);
			_bytes = nullptr;
		}
		_size = 0;
		_cursor = 0;
	}

	void NIStream::fastSet(char* bytes, const std::size_t size)
	{
		_bytes = bytes;
		_size = size;
		_cursor = 0;
	}

	NIStream& NIStream::operator= (const NIStream& other)
	{
		copy(other._bytes, other._size);
		return *this;
	}

	NIStream& NIStream::operator= (NIStream&& other)
	{
		move(other);
		return *this;
	}

	std::size_t NIStream::getSize() const
	{
		return _size;
	}

	std::size_t NIStream::getLength() const
	{
		return _cursor;
	}

	std::size_t NIStream::getAvailableSize() const
	{
		return _size - _cursor;
	}

	char* NIStream::getBytes() const
	{
		return _bytes;
	}

	void NIStream::resetCursor()
	{
		_cursor = 0;
	}

	void NIStream::seek(long offset, StreamSeekDir dir /* = StreamSeekDir::cur */)
	{
		switch (dir)
		{
		case StreamSeekDir::beg:
			_cursor = offset;
			break;

		case StreamSeekDir::cur:
			_cursor += offset;
			break;

		case StreamSeekDir::end:
			_cursor = _size + offset;
			break;
		}

		if (_cursor < 0) {
			_cursor = 0;
		}

		if (_cursor > _size)
		{
			_cursor = _size;
		}
	}

	NIStream& NIStream::write(const char* buf, std::size_t size)
	{
		if (getAvailableSize() >= size)
		{
			memcpy(_bytes + _cursor, buf, size);
			_cursor += size;
		}
		return *this;
	}

	void NIStream::writeStrLen(const std::size_t& len)
	{
		if (len < 0xff)
		{
			operator<<((unsigned char)len);
		}
		else if (len < 0xffff)
		{
			operator<<((unsigned char)0xff);
			operator<<((unsigned short)len);
		}
		else
		{
			operator<<((unsigned char)0xff);
			operator<<((unsigned short)0xffff);
			operator<<((unsigned int)len);
		}
	}

	NIStream& NIStream::operator<<(char v)
	{
		return write(&v, sizeof(v));
	}

	NIStream& NIStream::operator<<(unsigned char v)
	{
		return write((const char*)&v, sizeof(v));
	}

	NIStream& NIStream::operator<<(short v)
	{
		return write((const char*)&v, sizeof(v));
	}

	NIStream& NIStream::operator<<(unsigned short v)
	{
		return write((const char*)&v, sizeof(v));
	}

	NIStream& NIStream::operator<<(int v)
	{
		return write((const char*)&v, sizeof(v));
	}

	NIStream& NIStream::operator<<(unsigned int v)
	{
		return write((const char*)&v, sizeof(v));
	}

	NIStream& NIStream::operator<<(long long v)
	{
		return write((const char*)&v, sizeof(v));
	}

	NIStream& NIStream::operator<<(unsigned long long v)
	{
		return write((const char*)&v, sizeof(v));
	}

	NIStream& NIStream::operator<<(const char* v)
	{
		std::size_t len = strlen(v);
		writeStrLen(len);
		if (len == 0)
			return *this;
		return write(v, len);
	}

	NIStream& NIStream::operator<<(const std::string& v)
	{
		std::size_t len = v.length();
		writeStrLen(len);
		if (len == 0)
			return *this;
		return write(v.data(), len);
	}

	NIStream& NIStream::operator<<(float v)
	{
		return write((const char*)&v, sizeof(v));
	}

	NIStream& NIStream::operator<<(double v)
	{
		return write((const char*)&v, sizeof(v));
	}

	NIStream& NIStream::operator<<(long double v)
	{
		return write((const char*)&v, sizeof(v));
	}

	NIStream& NIStream::operator<<(bool v)
	{
		return write((const char*)&v, sizeof(v));
	}

	// OStream
	NOStream::NOStream(char* buf, std::size_t size) :
		_bytes(buf),
		_size(size),
		_cursor(0)
	{

	}

	NOStream::NOStream(const NOStream& other) : NOStream(nullptr, 0)
	{
		copy(other._bytes, other._size);
	}

	NOStream::NOStream(NOStream&& other) : NOStream(nullptr, 0)
	{
		move(other);
	}

	NOStream::~NOStream()
	{
		clear();
	}

	void NOStream::copy(const char* bytes, const std::size_t size)
	{
		clear();
		if (size > 0)
		{
			_size = size;
			_cursor = 0;
			_bytes = (char*)malloc(sizeof(char) * size);
			memcpy(_bytes, bytes, size);
		}
	}

	void NOStream::move(NOStream& other)
	{
		clear();
		_bytes = other._bytes;
		_size = other._size;
		_cursor = other._cursor;

		other._bytes = nullptr;
		other._size = 0;
		other._cursor = 0;
	}

	void NOStream::clear()
	{
		if (_bytes)
		{
			free(_bytes);
			_bytes = nullptr;
		}
		_size = 0;
		_cursor = 0;
	}

	void NOStream::fastSet(char* bytes, const std::size_t size)
	{
		_bytes = bytes;
		_size = size;
		_cursor = 0;
	}

	NOStream& NOStream::operator= (const NOStream& other)
	{
		copy(other._bytes, other._size);
		return *this;
	}

	NOStream& NOStream::operator= (NOStream&& other)
	{
		move(other);
		return *this;
	}

	std::size_t NOStream::getSize() const
	{
		return _size;
	}

	std::size_t NOStream::getAvailableSize() const
	{
		return _size - _cursor;
	}

	char* NOStream::getBytes() const
	{
		return _bytes;
	}

	char* NOStream::getReadData() const
	{
		return _bytes + _cursor;
	}

	void NOStream::resetCursor()
	{
		_cursor = 0;
	}

	void NOStream::seek(long offset, StreamSeekDir dir /* = StreamSeekDir::cur */)
	{
		switch (dir)
		{
		case StreamSeekDir::beg:
			_cursor = offset;
			break;

		case StreamSeekDir::cur:
			_cursor += offset;
			break;

		case StreamSeekDir::end:
			_cursor = _size + offset;
			break;
		}

		if (_cursor < 0) {
			_cursor = 0;
		}

		if (_cursor > _size)
		{
			_cursor = _size;
		}
	}

	void NOStream::pop(std::size_t size)
	{
		if (size <= 0) return;
		_cursor += size;
		if (_cursor > _size) _cursor = _size;
	}

	NOStream& NOStream::read(char* buf, std::size_t size)
	{
		if (getAvailableSize() >= size)
		{
			memcpy(buf, _bytes + _cursor, size);
			_cursor += size;
		}


		return *this;
	}

	bool NOStream::readCString(char* buf, std::size_t size)
	{
		if (size <= 0)
			return false;
		memset(buf, 0, size);

		std::size_t len = readStrLen();
		if (len == 0)
			return true;

		if (len >= size)
			return false;

		read(buf, len);
		return true;
	}

	std::size_t NOStream::readStrLen()
	{
		std::size_t len = -1;
		{
			unsigned char slen = 0;
			operator>>(slen);
			len = slen;
		}

		if (len == -1) return 0;
		if (len == 0xff)
		{
			{
				unsigned short slen = 0;
				operator>>(slen);
				len = slen;
			}

			if (len == -1) return 0;
			if (len == 0xffff)
			{
				{
					unsigned int slen = 0;
					operator>>(slen);
					len = slen;
				}

				if (len == -1) return 0;
			}
		}

		return len;
	}

	NOStream& NOStream::operator>>(char& v)
	{
		return read(&v, sizeof(v));
	}

	NOStream& NOStream::operator>>(unsigned char& v)
	{
		return read((char*)&v, sizeof(v));
	}

	NOStream& NOStream::operator>>(short& v)
	{
		return read((char*)&v, sizeof(v));
	}

	NOStream& NOStream::operator>>(unsigned short& v)
	{
		return read((char*)&v, sizeof(v));
	}

	NOStream& NOStream::operator>>(int& v)
	{
		return read((char*)&v, sizeof(v));
	}

	NOStream& NOStream::operator>>(unsigned int& v)
	{
		return read((char*)&v, sizeof(v));
	}

	NOStream& NOStream::operator>>(long long& v)
	{
		return read((char*)&v, sizeof(v));
	}

	NOStream& NOStream::operator>>(unsigned long long& v)
	{
		return read((char*)&v, sizeof(v));
	}

	NOStream& NOStream::operator>>(char* v)
	{
		std::size_t len = readStrLen();
		if (len == 0)
			return *this;
		return read(v, len);
	}

	NOStream& NOStream::operator>>(std::string& v)
	{
		std::size_t len = readStrLen();
		if (len == 0)
			return *this;
		v.resize(len);
		return read((char*)v.data(), v.size());
	}

	NOStream& NOStream::operator>>(float& v)
	{
		return read((char*)&v, sizeof(v));
	}

	NOStream& NOStream::operator>>(double& v)
	{
		return read((char*)&v, sizeof(v));
	}

	NOStream& NOStream::operator>>(long double& v)
	{
		return read((char*)&v, sizeof(v));
	}

	NOStream& NOStream::operator>>(bool& v)
	{
		return read((char*)&v, sizeof(v));
	}

}