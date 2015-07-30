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

package dk.muj.mujlib.arg;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArgumentTest extends TestCase
{
	
	@Test
	public void testHandleNull() throws Exception
	{
		// Ensure exception is thrown on null
		try
		{
			Argument.handleNull(null, "");
			assert false;
		}
		catch (ArgumentNullException ex) {}

		// Ensure exception is not thrown on non-null
		Argument.handleNull(new Object(), "");
		Argument.handleNull("NON_NULL STRING", "");
		Argument.handleNull(Integer.valueOf(1), "");
	}

	@Test
	public void testHandleStrangeDouble() throws Exception
	{
		// Ensure exception is thrown on NaN
		try
		{
			Argument.handleStrange(Double.NaN, "");
			assert false;
		}
		catch (ArgumentStrangeException ex) {}

		// Ensure exception is thrown on positive infinity
		try
		{
			Argument.handleStrange(Double.POSITIVE_INFINITY, "");
			assert false;
		}
		catch (ArgumentStrangeException ex) {}

		// Ensure exception is thrown on negative infinity
		try
		{
			Argument.handleStrange(Double.NEGATIVE_INFINITY, "");
			assert false;
		}
		catch (ArgumentStrangeException ex) {}

		// Ensure exception is not thrown on valid doubles
		Argument.handleStrange(-1D, "");
		Argument.handleStrange(+1D, "");
		Argument.handleStrange(0D, "");
		Argument.handleStrange(Double.MAX_VALUE, "");
		Argument.handleStrange(Double.MIN_VALUE, "");
	}

	@Test
	public void testHandleInfiniteDouble() throws Exception
	{
		// Ensure exception is thrown on positive infinity
		try
		{
			Argument.handleInfinite(Double.POSITIVE_INFINITY, "");
			assert false;
		}
		catch (ArgumentInfiniteException ex) {}

		// Ensure exception is thrown on negative infinity
		try
		{
			Argument.handleInfinite(Double.NEGATIVE_INFINITY, "");
			assert false;
		}
		catch (ArgumentInfiniteException ex) {}

		// Ensure exception is not thrown on valid doubles
		Argument.handleInfinite(-1D, "");
		Argument.handleInfinite(+1D, "");
		Argument.handleInfinite(0D, "");
		Argument.handleInfinite(Double.MAX_VALUE, "");
		Argument.handleInfinite(Double.MIN_VALUE, "");

		// Ensure exception is not thrown on NaN
		Argument.handleInfinite(Double.NaN, "");
	}

	@Test
	public void testHandleNaNDouble() throws Exception
	{
		// Ensure exception is thrown on NaN
		try
		{
			Argument.handleNaN(Double.NaN, "");
			assert false;
		}
		catch (ArgumentNanException ex) {}

		// Ensure exception is not thrown on valid doubles
		Argument.handleNaN(-1D, "");
		Argument.handleNaN(+1D, "");
		Argument.handleNaN(0D, "");
		Argument.handleNaN(Double.MAX_VALUE, "");
		Argument.handleNaN(Double.MIN_VALUE, "");

		// Ensure exception is not thrown on infinity
		Argument.handleNaN(Double.POSITIVE_INFINITY, "");
		Argument.handleNaN(Double.NEGATIVE_INFINITY, "");
	}

	@Test
	public void testHandleStrangeFloat() throws Exception
	{
		// Ensure exception is thrown on NaN
		try
		{
			Argument.handleStrange(Float.NaN, "");
			assert false;
		}
		catch (ArgumentStrangeException ex) {}

		// Ensure exception is thrown on positive infinity
		try
		{
			Argument.handleStrange(Float.POSITIVE_INFINITY, "");
			assert false;
		}
		catch (ArgumentStrangeException ex) {}

		// Ensure exception is thrown on negative infinity
		try
		{
			Argument.handleStrange(Float.NEGATIVE_INFINITY, "");
			assert false;
		}
		catch (ArgumentStrangeException ex) {}

		// Ensure exception is not thrown on valid doubles
		Argument.handleStrange(-1F, "");
		Argument.handleStrange(+1F, "");
		Argument.handleStrange(0F, "");
		Argument.handleStrange(Float.MAX_VALUE, "");
		Argument.handleStrange(Float.MIN_VALUE, "");
	}

	@Test
	public void testHandleInfiniteFloat() throws Exception
	{
		try
		{
			Argument.handleInfinite(Float.POSITIVE_INFINITY, "");
			assert false;
		}
		catch (ArgumentInfiniteException ex) {}

		// Ensure exception is thrown on negative infinity
		try
		{
			Argument.handleInfinite(Float.NEGATIVE_INFINITY, "");
			assert false;
		}
		catch (ArgumentInfiniteException ex) {}

		// Ensure exception is not thrown on valid doubles
		Argument.handleInfinite(-1F, "");
		Argument.handleInfinite(+1F, "");
		Argument.handleInfinite(0F, "");
		Argument.handleInfinite(Float.MAX_VALUE, "");
		Argument.handleInfinite(Float.MIN_VALUE, "");

		// Ensure exception is not thrown on NaN
		Argument.handleInfinite(Float.NaN, "");
	}

	@Test
	public void testHandleNaNFloat() throws Exception
	{
		// Ensure exception is thrown on NaN
		try
		{
			Argument.handleNaN(Float.NaN, "");
			assert false;
		}
		catch (ArgumentNanException ex) {}

		// Ensure exception is not thrown on valid doubles
		Argument.handleNaN(-1F, "");
		Argument.handleNaN(+1F, "");
		Argument.handleNaN(0F, "");
		Argument.handleNaN(Float.MAX_VALUE, "");
		Argument.handleNaN(Float.MIN_VALUE, "");

		// Ensure exception is not thrown on infinity
		Argument.handleNaN(Float.POSITIVE_INFINITY, "");
		Argument.handleNaN(Float.NEGATIVE_INFINITY, "");
	}

}
