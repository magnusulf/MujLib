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

import java.math.BigInteger;

public class MthTest extends TestCase
{
	// -------------------------------------------- //
	// FACTORIAL
	// -------------------------------------------- //

	@Test
	public void testFactorialPrecise() throws Exception
	{
		assertEquals(BigInteger.valueOf(1), Mth.factorialPrecise(0));
		assertEquals(BigInteger.valueOf(1), Mth.factorialPrecise(1));
		assertEquals(BigInteger.valueOf(2), Mth.factorialPrecise(2));
		assertEquals(BigInteger.valueOf(2*3), Mth.factorialPrecise(3));
		assertEquals(BigInteger.valueOf(2*3*4), Mth.factorialPrecise(4));
		assertEquals(BigInteger.valueOf(2*3*4*5), Mth.factorialPrecise(5));
		assertEquals(BigInteger.valueOf(2*3*4*5*6), Mth.factorialPrecise(6));
		assertEquals(BigInteger.valueOf(2*3*4*5*6*7), Mth.factorialPrecise(7));
		assertEquals(BigInteger.valueOf(2*3*4*5*6*7*8), Mth.factorialPrecise(8));
		assertEquals(BigInteger.valueOf(2*3*4*5*6*7*8*9), Mth.factorialPrecise(9));
		assertEquals(BigInteger.valueOf(2*3*4*5*6*7*8*9*10), Mth.factorialPrecise(10));

	}

	@Test
	public void testFactorialExact() throws Exception
	{
		assertEquals(1, Mth.factorialExact(0));
		assertEquals(1, Mth.factorialExact(1));
		assertEquals(2, Mth.factorialExact(2));
		assertEquals(2*3, Mth.factorialExact(3));
		assertEquals(2*3*4, Mth.factorialExact(4));
		assertEquals(2*3*4*5, Mth.factorialExact(5));
		assertEquals(2*3*4*5*6, Mth.factorialExact(6));
		assertEquals(2*3*4*5*6*7, Mth.factorialExact(7));
		assertEquals(2*3*4*5*6*7*8, Mth.factorialExact(8));
		assertEquals(2*3*4*5*6*7*8*9, Mth.factorialExact(9));
		assertEquals(2*3*4*5*6*7*8*9*10, Mth.factorialExact(10));
	}

	// -------------------------------------------- //
	// BINOMIAL COEFFICIENT
	// -------------------------------------------- //

	@Test
	public void testBinomialPrecise() throws Exception
	{
		assertEquals(BigInteger.valueOf(10), Mth.binomialPrecise(5, 2));
		assertEquals(BigInteger.valueOf(10), Mth.binomialPrecise(5, 3));
		assertEquals(BigInteger.valueOf(5), Mth.binomialPrecise(5, 4));
		assertEquals(BigInteger.valueOf(1), Mth.binomialPrecise(317, 317));
		assertEquals(BigInteger.valueOf(15504), Mth.binomialPrecise(20, 5));
		assertEquals(BigInteger.valueOf(680), Mth.binomialPrecise(17, 14));
		assertEquals(BigInteger.valueOf(190), Mth.binomialPrecise(20, 2));
		assertEquals(BigInteger.valueOf(120), Mth.binomialPrecise(10, 3));
		assertEquals(BigInteger.valueOf(27132), Mth.binomialPrecise(19, 6));
		assertEquals(BigInteger.valueOf(48620), Mth.binomialPrecise(18, 9));
		assertEquals(BigInteger.valueOf(1716), Mth.binomialPrecise(13, 6));

		// Special
		assertEquals(BigInteger.valueOf(741), Mth.binomialPrecise(39, 2));
		assertEquals(BigInteger.valueOf(348330136), Mth.binomialPrecise(37, 10));
		assertEquals(BigInteger.valueOf(666), Mth.binomialPrecise(37, 2));
	}

	@Test
	public void testBinomialExact() throws Exception
	{
		assertEquals(10, Mth.binomialExact(5, 2));
		assertEquals(10, Mth.binomialExact(5, 3));
		assertEquals(5, Mth.binomialExact(5, 4));
		assertEquals(1, Mth.binomialExact(317, 317));
		assertEquals(15504, Mth.binomialExact(20, 5));
		assertEquals(680, Mth.binomialExact(17, 14));
		assertEquals(190, Mth.binomialExact(20, 2));
		assertEquals(120, Mth.binomialExact(10, 3));
		assertEquals(27132, Mth.binomialExact(19, 6));
		assertEquals(48620, Mth.binomialExact(18, 9));
		assertEquals(1716, Mth.binomialExact(13, 6));
	}

	// -------------------------------------------- //
	// PREDICATE POSITIVE
	// -------------------------------------------- //

	@Test
	public void testPredicatePositiveInt()
	{
		for (int i = -100; i < 100; i++)
		{
			assertEquals(i > 0, Mth.isPositive(i));
		}
	}

	@Test
	public void testPredicatePositiveLong()
	{
		for (long i = -100; i < 100; i++)
		{
			assertEquals(i > 0, Mth.isPositive(i));
		}
	}

	@Test
	public void testPredicatePositiveDouble()
	{
		for (double i = -100; i < 100; i++)
		{
			assertEquals(i > 0, Mth.isPositive(i));
		}
	}

	// -------------------------------------------- //
	// PREDICATE NEGATIVE
	// -------------------------------------------- //

	@Test
	public void testPredicateNegativeInt()
	{
		for (int i = -100; i < 100; i++)
		{
			assertEquals(i < 0, Mth.isNegative(i));
		}
	}

	@Test
	public void testPredicateNegativeLong()
	{
		for (long i = -100; i < 100; i++)
		{
			assertEquals(i < 0, Mth.isNegative(i));
		}
	}

	@Test
	public void testPredicateNegativeDouble()
	{
		for (double i = -100; i < 100; i++)
		{
			assertEquals(i < 0, Mth.isNegative(i));
		}
	}

	// -------------------------------------------- //
	// PREDICATE EVEN
	// -------------------------------------------- //

	@Test
	public void testPredicateEvenInt()
	{
		for (int i = -100; i < 100; i += 2)
		{
			assertTrue(Mth.isEven(i));
		}
		for (int i = -101; i < 100; i += 2)
		{
			assertFalse(Mth.isEven(i));
		}
	}

	@Test
	public void testPredicateEvenLong()
	{
		for (long i = -100; i < 100; i += 2)
		{
			assertTrue(Mth.isEven(i));
		}
		for (long i = -101; i < 100; i += 2)
		{
			assertFalse(Mth.isEven(i));
		}
	}

	@Test
	public void testPredicateOddInt()
	{
		for (int i = -100; i < 100; i += 2)
		{
			assertFalse(Mth.isOdd(i));
		}
		for (int i = -101; i < 100; i += 2)
		{
			assertTrue(Mth.isOdd(i));
		}
	}

	@Test
	public void testPredicateOddLong()
	{
		for (long i = -100; i < 100; i += 2)
		{
			assertFalse(Mth.isOdd(i));
		}
		for (long i = -101; i < 100; i += 2)
		{
			assertTrue(Mth.isOdd(i));
		}
	}

}
