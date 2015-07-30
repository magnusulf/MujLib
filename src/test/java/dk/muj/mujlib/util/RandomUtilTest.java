/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Magnus Ulf Jørgensen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package dk.muj.mujlib.util;

import junit.framework.TestCase;
import org.junit.Test;

public class RandomUtilTest extends TestCase
{
	@Test
	public void testRandomDouble() throws Exception
	{
		for (int i = 0; i < 10; i++)
		{
			double total = 0;
			for (int j = 0; j < 100000; j++)
			{
				double d = RandomUtil.randomDouble();
				assertTrue(String.valueOf(d), d >= 0.0D);
				assertTrue(String.valueOf(d), d < 1.0D);
				total += d;
			}
			total = Math.abs(total);
			double average = total / 100000;
			assertTrue(String.valueOf(average), average > 0.49D);
			assertTrue(String.valueOf(average), average < 0.51D);
		}
	}

	@Test
	public void testRandomBoolean() throws Exception
	{
		for (int i = 0; i < 10; i++)
		{
			int diff = 0;
			for (int j = 0; j < 10_000_000; j++)
			{
				boolean b = RandomUtil.randomBoolean();
				if (b) diff++;
				else diff--;
			}
			diff = Math.abs(diff);
			assertTrue(String.valueOf(diff), diff < 100_000);
		}
	}

	@Test
	public void testRandomDoubleBetween() throws Exception
	{
		for (int i = 0; i < 100_000; i++)
		{
			double start = RandomUtil.RANDOM.nextInt();
			double end = RandomUtil.RANDOM.nextInt();
			if (start >= end) continue;

			try
			{
				// This method has assertions which makes it self testing.
				double random = RandomUtil.randomDouble(start, end);
			}
			catch (ArithmeticException ex)
			{
				// The input was invalid.
			}

		}
	}

	@Test
	public void testRandomIntBetween() throws Exception
	{
		for (int i = 0; i < 100_000; i++)
		{
			int start = RandomUtil.RANDOM.nextInt();
			int end = RandomUtil.RANDOM.nextInt();
			if (start >= end) continue;

			try
			{
				// This method has assertions which makes it self testing.
				int random = RandomUtil.randomInt(start, end);
			}
			catch (ArithmeticException ex)
			{
				// The input was invalid.
			}

		}
	}

	@Test
	public void testRandomLongBetween() throws Exception
	{
		long multiply = Long.MAX_VALUE / Integer.MAX_VALUE;
		for (int i = 0; i < 100_000; i++)
		{
			long start = RandomUtil.RANDOM.nextInt()*multiply;
			long end = RandomUtil.RANDOM.nextInt()*multiply;
			if (start >= end) continue;

			try
			{
				// This method has assertions which makes it self testing.
				long random = RandomUtil.randomLong(start, end);
			}
			catch (ArithmeticException ex)
			{
				// The input was invalid.
			}

		}
	}
}
