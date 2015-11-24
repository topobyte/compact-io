// Copyright 2015 Sebastian Kuerten
//
// This file is part of compact-io.
//
// compact-io is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// compact-io is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with compact-io. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.compactio;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileCompactReader extends CompactReader
{

	private final RandomAccessFile file;

	public RandomAccessFileCompactReader(RandomAccessFile file)
	{
		this.file = file;
	}

	@Override
	public int readByte() throws IOException
	{
		int r = file.read();
		if (r < 0) {
			throw new EOFException();
		}
		return r;
	}

	@Override
	public int read(byte[] buffer, int off, int len) throws IOException
	{
		int r = file.read(buffer, off, len);
		if (r < 0) {
			throw new EOFException();
		}
		return r;
	}

	@Override
	public void skip(long len) throws IOException
	{
		file.seek(file.getFilePointer() + len);
	}

}
