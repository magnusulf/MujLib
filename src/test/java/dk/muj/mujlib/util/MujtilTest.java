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

public class MujtilTest extends TestCase
{
	
	@Test
	public void testSneakyThrow() throws Exception
	{
		try
		{
			Mujtil.sneakyThrow(new Exception());
			assert false;
		}
		catch (Exception ex)
		{
			// we succeeded.
		}
	}

	// All of the equal methods has assertions, which makes them self testing.
	@Test
	public void testEqualsObject() throws Exception
	{
		Object o1 = new Object();
		Object o2 = new Object();
		Mujtil.equals(null, o1);
		Mujtil.equals(o1, null);
		Mujtil.equals(o1, o2);
		Mujtil.equals(o2, o1);
		Mujtil.equals(o1, o1);
		Mujtil.equals(o2, o2);
	}

	@Test
	public void testEqualsBoolean() throws Exception
	{
		Mujtil.equals(true, true);
		Mujtil.equals(true, false);
		Mujtil.equals(false, false);
		Mujtil.equals(false, true);
	}

	@Test
	public void testEqualsByte() throws Exception
	{
		Mujtil.equals((byte) 1, (byte) 2);
		Mujtil.equals((byte) 127, (byte) 127);
		Mujtil.equals((byte) -3, (byte) -3);
		Mujtil.equals((byte) 54, (byte) -54);
	}

	@Test
	public void testEqualsChar() throws Exception
	{
		Mujtil.equals('A', 'A');
		Mujtil.equals('B', 'b');
		Mujtil.equals('C', 'c');
		Mujtil.equals('D', ' ');
		Mujtil.equals('\r', '\n');
	}

	@Test
	public void testEqualsShort() throws Exception
	{
		Mujtil.equals((short) 1, (short) 2);
		Mujtil.equals((short) 127, (short) 127);
		Mujtil.equals((short) -3, (short) -3);
		Mujtil.equals((short) 54, (short) -54);
	}

	@Test
	public void testEqualsInt() throws Exception
	{
		Mujtil.equals(1, 2);
		Mujtil.equals(1, 1);
		Mujtil.equals(-0, 0);
		Mujtil.equals(64, 347);
		Mujtil.equals(-2, 2);
	}

	@Test
	public void testEqualsLong() throws Exception
	{
		Mujtil.equals(1L, 2L);
		Mujtil.equals(1L, 1L);
		Mujtil.equals(-0L, 0L);
		Mujtil.equals(64L, 347L);
		Mujtil.equals(-2L, 2L);
	}

	@Test
	public void testEqualsFloat() throws Exception
	{
		Mujtil.equals(Float.NaN, Float.NaN);
		Mujtil.equals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
		Mujtil.equals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
		Mujtil.equals(1F, 1F);
		Mujtil.equals(-2F, 2F);
		Mujtil.equals(2.0F, 2F);
		Mujtil.equals(0.0F, -0.0F);
	}

	@Test
	public void testEqualsDouble() throws Exception
	{
		Mujtil.equals(Double.NaN, Double.NaN);
		Mujtil.equals(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		Mujtil.equals(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
		Mujtil.equals(1D, 1D);
		Mujtil.equals(-2D, 2D);
		Mujtil.equals(2.0D, 2D);
		Mujtil.equals(0.0D, -0.0D);
	}

}
