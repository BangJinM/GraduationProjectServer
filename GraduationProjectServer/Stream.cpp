#include "Stream.h"

namespace network{

	IStream::IStream(char* buf, std::size_t size) :
		_bytes(buf),
		_size(size),
		_cursor(0)
	{

	}

	IStream::IStream(const IStream& other) : IStream(nullptr, 0)
	{
		copy(other._bytes, other._size);
	}

	IStream::IStream(IStream&& other) : IStream(nullptr, 0)
	{
		move(other);
	}

	IStream::~IStream()
	{
		clear();
	}

	void IStream::copy(const char* bytes, const std::size_t size)
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

	void IStream::move(IStream& other)
	{
		clear();
		_bytes = other._bytes;
		_size = other._size;
		_cursor = other._cursor;

		other._bytes = nullptr;
		other._size = 0;
		other._cursor = 0;
	}

	void IStream::clear()
	{
		if (_bytes)
		{
			free(_bytes);
			_bytes = nullptr;
		}
		_size = 0;
		_cursor = 0;
	}

	void IStream::fastSet(char* bytes, const std::size_t size)
	{
		_bytes = bytes;
		_size = size;
		_cursor = 0;
	}

	IStream& IStream::operator= (const IStream& other)
	{
		copy(other._bytes, other._size);
		return *this;
	}

	IStream& IStream::operator= (IStream&& other)
	{
		move(other);
		return *this;
	}

	std::size_t IStream::getSize() const
	{
		return _size;
	}

	std::size_t IStream::getLength() const
	{
		return _cursor;
	}

	std::size_t IStream::getAvailableSize() const
	{
		return _size - _cursor;
	}

	char* IStream::getBytes() const
	{
		return _bytes;
	}

	void IStream::resetCursor()
	{
		_cursor = 0;
	}

	void IStream::seek(long offset, StreamSeekDir dir /* = StreamSeekDir::cur */)
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

	IStream& IStream::write(const char* buf, std::size_t size)
	{
		if (getAvailableSize() >= size)
		{
			memcpy(_bytes + _cursor, buf, size);
			_cursor += size;
		}
		return *this;
	}

	void IStream::writeStrLen(const std::size_t& len)
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

	IStream& IStream::operator<<(char v)
	{
		return write(&v, sizeof(v));
	}

	IStream& IStream::operator<<(unsigned char v)
	{
		return write((const char*)&v, sizeof(v));
	}

	IStream& IStream::operator<<(short v)
	{
		return write((const char*)&v, sizeof(v));
	}

	IStream& IStream::operator<<(unsigned short v)
	{
		return write((const char*)&v, sizeof(v));
	}

	IStream& IStream::operator<<(int v)
	{
		return write((const char*)&v, sizeof(v));
	}

	IStream& IStream::operator<<(unsigned int v)
	{
		return write((const char*)&v, sizeof(v));
	}

	IStream& IStream::operator<<(long long v)
	{
		return write((const char*)&v, sizeof(v));
	}

	IStream& IStream::operator<<(unsigned long long v)
	{
		return write((const char*)&v, sizeof(v));
	}

	IStream& IStream::operator<<(const char* v)
	{
		std::size_t len = strlen(v);
		writeStrLen(len);
		if (len == 0)
			return *this;
		return write(v, len);
	}

	IStream& IStream::operator<<(const std::string& v)
	{
		std::size_t len = v.length();
		writeStrLen(len);
		if (len == 0)
			return *this;
		return write(v.data(), len);
	}

	IStream& IStream::operator<<(float v)
	{
		return write((const char*)&v, sizeof(v));
	}

	IStream& IStream::operator<<(double v)
	{
		return write((const char*)&v, sizeof(v));
	}

	IStream& IStream::operator<<(long double v)
	{
		return write((const char*)&v, sizeof(v));
	}

	IStream& IStream::operator<<(bool v)
	{
		return write((const char*)&v, sizeof(v));
	}

	// OStream
	OStream::OStream(char* buf, std::size_t size) :
		_bytes(buf),
		_size(size),
		_cursor(0)
	{

	}

	OStream::OStream(const OStream& other) : OStream(nullptr, 0)
	{
		copy(other._bytes, other._size);
	}

	OStream::OStream(OStream&& other) : OStream(nullptr, 0)
	{
		move(other);
	}

	OStream::~OStream()
	{
		clear();
	}

	void OStream::copy(const char* bytes, const std::size_t size)
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

	void OStream::move(OStream& other)
	{
		clear();
		_bytes = other._bytes;
		_size = other._size;
		_cursor = other._cursor;

		other._bytes = nullptr;
		other._size = 0;
		other._cursor = 0;
	}

	void OStream::clear()
	{
		if (_bytes)
		{
			free(_bytes);
			_bytes = nullptr;
		}
		_size = 0;
		_cursor = 0;
	}

	void OStream::fastSet(char* bytes, const std::size_t size)
	{
		_bytes = bytes;
		_size = size;
		_cursor = 0;
	}

	OStream& OStream::operator= (const OStream& other)
	{
		copy(other._bytes, other._size);
		return *this;
	}

	OStream& OStream::operator= (OStream&& other)
	{
		move(other);
		return *this;
	}

	std::size_t OStream::getSize() const
	{
		return _size;
	}

	std::size_t OStream::getAvailableSize() const
	{
		return _size - _cursor;
	}

	char* OStream::getBytes() const
	{
		return _bytes;
	}

	char* OStream::getReadData() const
	{
		return _bytes + _cursor;
	}

	void OStream::resetCursor()
	{
		_cursor = 0;
	}

	void OStream::seek(long offset, StreamSeekDir dir /* = StreamSeekDir::cur */)
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

	void OStream::pop(std::size_t size)
	{
		if (size <= 0) return;
		_cursor += size;
		if (_cursor > _size) _cursor = _size;
	}

	OStream& OStream::read(char* buf, std::size_t size)
	{
		if (getAvailableSize() >= size)
		{
			memcpy(buf, _bytes + _cursor, size);
			_cursor += size;
		}


		return *this;
	}

	bool OStream::readCString(char* buf, std::size_t size)
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

	std::size_t OStream::readStrLen()
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

	OStream& OStream::operator>>(char& v)
	{
		return read(&v, sizeof(v));
	}

	OStream& OStream::operator>>(unsigned char& v)
	{
		return read((char*)&v, sizeof(v));
	}

	OStream& OStream::operator>>(short& v)
	{
		return read((char*)&v, sizeof(v));
	}

	OStream& OStream::operator>>(unsigned short& v)
	{
		return read((char*)&v, sizeof(v));
	}

	OStream& OStream::operator>>(int& v)
	{
		return read((char*)&v, sizeof(v));
	}

	OStream& OStream::operator>>(unsigned int& v)
	{
		return read((char*)&v, sizeof(v));
	}

	OStream& OStream::operator>>(long long& v)
	{
		return read((char*)&v, sizeof(v));
	}

	OStream& OStream::operator>>(unsigned long long& v)
	{
		return read((char*)&v, sizeof(v));
	}

	OStream& OStream::operator>>(char* v)
	{
		std::size_t len = readStrLen();
		if (len == 0)
			return *this;
		return read(v, len);
	}

	OStream& OStream::operator>>(std::string& v)
	{
		std::size_t len = readStrLen();
		if (len == 0)
			return *this;
		v.resize(len);
		return read((char*)v.data(), v.size());
	}

	OStream& OStream::operator>>(float& v)
	{
		return read((char*)&v, sizeof(v));
	}

	OStream& OStream::operator>>(double& v)
	{
		return read((char*)&v, sizeof(v));
	}

	OStream& OStream::operator>>(long double& v)
	{
		return read((char*)&v, sizeof(v));
	}

	OStream& OStream::operator>>(bool& v)
	{
		return read((char*)&v, sizeof(v));
	}

}