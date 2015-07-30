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

import dk.muj.mujlib.doc.Pure;

import java.math.BigInteger;

/**
 * This class has some utility methods,
 * for dealing with math.
 *
 * It has naming conventions.
 *
 * Methods ending with "exact" are using primitives,
 * but they will throw an {@code ArithmeticException}
 * on overflows, or other issues.
 * These methods would never return an invalid result,
 * but would rather fail.
 *
 * Methods ending with "precise" use BigInteger and BigDecimal
 * this makes them extremely expensive, but they won't fail,
 * and they will always yield accurate results.
 *
 * Methods not having such an ending, will use just primitives
 * and they can inaccurate result due to overflow.
 * These methods should rarely be used, but are
 * definitely the fastest.
 *
 * @author Magnus Ulf Jørgensen
 */
public final class Mth
{
	// -------------------------------------------- //
	// CONSTRUCTOR (FORBIDDEN)
	// -------------------------------------------- //

	private Mth()
	{
		throw new AssertionError();
	}

	// -------------------------------------------- //
	// FACTORIAL
	// -------------------------------------------- //

	/**
	 * Calculates factorial of n.
	 * This method uses BigInteger, it so is slow,
	 * but can handle very large numbers.
	 * @param n
	 * The number to calculate factorial for.
	 * @return
	 * Factorial of n
	 * @throws IllegalArgumentException
	 * If n is not positive.
	 */
	@Pure
	public static BigInteger factorialPrecise(long n) throws IllegalArgumentException
	{
		if (n < 0) throw new IllegalArgumentException("n must be positive.");
		if (n == 0 || n == 1) return BigInteger.ONE;
		BigInteger ret = BigInteger.valueOf(n);

		while (n-- > 1)
		{
			ret = ret.multiply(BigInteger.valueOf(n));
		}

		assert n == 0 : n;

		return ret;
	}

	/**
	 * Calculates factorial of n
	 * This method uses long, so it is fast,
	 * but can't handle very large numbers.
	 * @param n
	 * The number to calculate factorial for.
	 * @return
	 * Factorial of n
	 * @throws IllegalArgumentException
	 * If n is not positive.
	 * @throws ArithmeticException
	 * If an overflow occurs.
	 * An overflow will occur if n is more than 20
	 */
	@Pure
	public static long factorialExact(int n) throws IllegalArgumentException, ArithmeticException
	{
		if (n < 0) throw new IllegalArgumentException("n:" + n);
		if (n > 20) throw new ArithmeticException("Cannot calculate factorial for n higher than 20.");
		if (n == 0 || n == 1) return 1;
		long ret = n;

		while (n-- > 1)
		{
			ret *= n;
		}

		assert ret > 0 : ret;
		assert n == 0 : n;

		return ret;
	}

	// -------------------------------------------- //
	// BINOMIAL COEFFICIENT
	// -------------------------------------------- //

	/**
	 * Calculates binomial coefficient
	 * for n over r.
	 * This method uses BigInteger, so it is slow,
	 * but can handle very large numbers.
	 * @param n
	 * n in binomial coefficient
	 * @param r
	 * r in binomial coefficient
	 * @return
	 * Binomial coefficient for n over r.
	 * @throws IllegalArgumentException
	 * If r is higher than n, or if n or r is not positive.
	 */
	@Pure
	public static BigInteger binomialPrecise(int n, int r) throws IllegalArgumentException
	{
		if (r > n || n <= 0 || r <= 0) throw new IllegalArgumentException("r: " + r + " n: " + n);
		if (n == r) return BigInteger.ONE;
		if (r == n - 1 || r == 1) return BigInteger.valueOf(n);

		//TODO: Optimise
		return factorialPrecise(n).divide(factorialPrecise(r).multiply(factorialPrecise(n - r)));
	}

	/**
	 * Calculates binomial coefficient
	 * for n over r.
	 * This uses long, so is fast,
	 * but can't handle very large numbers.
	 * It is calculated as
	 * n! / ( r! * (n-r)! )
	 * keep that in mind, when looking for overflows.
	 * @param n
	 * n in binomial coefficient
	 * @param r
	 * r in binomial coefficient
	 * @return
	 * Binomial coefficient for n over r.
	 * @throws IllegalArgumentException
	 * if r is higher than n, or if n or r is not positive.
	 * @throws ArithmeticException
	 * if an overflow occurs.
	 */
	@Pure
	public static long binomialExact(int n, int r) throws IllegalArgumentException, ArithmeticException
	{
		if (r > n || n <= 0 || r <= 0) throw new IllegalArgumentException("r: " + r + " n: " + n);
		if (n == r) return 1;
		if (r == n - 1 || r == 1) return n;

		//TODO: Optimise
		return factorialExact(n)/Math.multiplyExact(factorialExact(r), factorialExact(n - r));
	}

	// -------------------------------------------- //
	// PREDICATE POSITIVE
	// -------------------------------------------- //

	/**
	 * This is equal to {@code 0 < 1}
	 * Can be used as a predicate Mth::isPositive
	 * @param i
	 * Value to check.
	 * @return
	 * True if and only if i is positive.
	 * False otherwise
	 * @see java.util.function.IntPredicate
	 */
	@Pure
	public static boolean isPositive(int i)
	{
		return 0 < i;
	}

	/**
	 * This is equal to {@code 0L < 1}
	 * Can be used as a predicate Mth::isPositive
	 * @param i
	 * Value to check.
	 * @return
	 * True if and only if i is positive.
	 * False otherwise
	 * @see java.util.function.LongPredicate
	 */
	@Pure
	public static boolean isPositive(long i)
	{
		return 0L < i;
	}

	/**
	 * This is equal to {@code 0D < i}
	 * Can be used as a predicate Mth::isPositive
	 * @param i
	 * Value to check.
	 * @return
	 * True if and only if i is positive.
	 * False otherwise
	 * @see java.util.function.DoublePredicate
	 */
	@Pure
	public static boolean isPositive(double i)
	{
		return 0D < i;
	}

	// -------------------------------------------- //
	// PREDICATE NEGATIVE
	// -------------------------------------------- //

	/**
	 * This is equal to {@code i < 0}
	 * Can be used as a predicate Mth::isPositive
	 * @param i
	 * Value to check.
	 * @return
	 * True if and only if i is negative.
	 * False otherwise
	 * @see java.util.function.IntPredicate
	 */
	@Pure
	public static boolean isNegative(int i)
	{
		return 0 > i;
	}

	/**
	 * This is equal to {@code i < 0L}
	 * Can be used as a predicate Mth::isPositive
	 * @param i
	 * Value to check.
	 * @return
	 * True if and only if i is negative.
	 * False otherwise
	 * @see java.util.function.LongPredicate
	 */
	@Pure
	public static boolean isNegative(long i)
	{
		return 0L > i;
	}

	/**
	 * This is equal to {@code i < 0D}
	 * Can be used as a predicate Mth::isPositive
	 * @param i
	 * Value to check.
	 * @return
	 * True if and only if i is negative.
	 * False otherwise
	 * @see java.util.function.DoublePredicate
	 */
	@Pure
	public static boolean isNegative(double i)
	{
		return 0D > i;
	}

	// -------------------------------------------- //
	// PREDICATE EVEN
	// -------------------------------------------- //

	/**
	 * This is equal to {@code i % 2 == 0}
	 * Can be used as a predicate Mth::isEven
	 * @param i
	 * Value to check
	 * @return
	 * True if and only if i is even.
	 * False otherwise
	 * @see java.util.function.IntPredicate
	 */
	@Pure
	public static boolean isEven(int i)
	{
		return (i & 1) == 0;
	}

	/**
	 * This is equal to {@code i % 2 == 0}
	 * Can be used as a predicate Mth::isEven
	 * @param i
	 * Value to check
	 * @return
	 * True if and only if i is even.
	 * False otherwise
	 * @see java.util.function.LongPredicate
	 */
	@Pure
	public static boolean isEven(long i)
	{
		return (i & 1) == 0;
	}

	// -------------------------------------------- //
	// PREDICATE ODD
	// -------------------------------------------- //

	/**
	 * This is equal to {@code i % 2 != 0}
	 * Can be used as a predicate Mth::isEven
	 * @param i
	 * Value to check
	 * @return
	 * True if and only if i is odd.
	 * False otherwise
	 * @see java.util.function.IntPredicate
	 */
	@Pure
	public static boolean isOdd(int i)
	{
		return ! isEven(i);
	}

	/**
	 * This is equal to {@code i % 2 != 0}
	 * Can be used as a predicate Mth::isEven
	 * @param i
	 * Value to check
	 * @return
	 * True if and only if i is odd.
	 * False otherwise
	 * @see java.util.function.LongPredicate
	 */
	@Pure
	public static boolean isOdd(long i)
	{
		return ! isEven(i);
	}

}
