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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestWriteRead
{

	public static void main(String[] args) throws IOException
	{

		int n = 10000;

		Random random = new Random();
		List<Long> nums = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			long num = random.nextLong();
			System.out.println(num);
			nums.add(num);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		OutputStreamCompactWriter writer = new OutputStreamCompactWriter(baos);
		for (int i = 0; i < n; i++) {
			long num = nums.get(i);
			writer.writeVariableLengthSignedInteger(num);
		}

		byte[] buffer = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
		InputStreamCompactReader reader = new InputStreamCompactReader(bais);
		for (int i = 0; i < n; i++) {
			long org = nums.get(i);
			long num = reader.readVariableLengthSignedInteger();
			if (org != num) {
				System.out.println(String.format("error: %d -> %d", org, num));
			}
		}
		System.out.println("ok");
	}

}
