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

import dk.muj.mujlib.arg.Argument;
import dk.muj.mujlib.arg.ArgumentStrangeException;

import java.util.Random;

/**
 * This class is a helping utility class for randomness.
 * It provides a static access to java.Util.Random
 * There is also some methods which will generate a
 * random number between to other numbers.
 * Those have a lot of checks to make sure they fail
 * instead of having abnormal behaviour.
 *
 * @see java.util.Random
 *
 * @author Magnus Ulf Jørgensen
 */
public final class RandomUtil
{
	// -------------------------------------------- //
	// CONSTRUCTOR (FORBIDDEN)
	// -------------------------------------------- //

	private RandomUtil()
	{
		throw new AssertionError();
	}

	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //

	/**
	 * An object for generating random values.
	 * Currently this object is used to generate
	 * the values for the methods this class provides,
	 * but that is not guaranteed.
	 */
	public static final Random RANDOM = new Random();

	// -------------------------------------------- //
	// RANDOM DEFAULT
	// -------------------------------------------- //

	/**
	 * Returns a pseudo random double between
	 * 0.0D (inclusive) and 1.0D (exclusive)
	 *
	 * @return
	 * A pseudo random double
	 */
	public static double randomDouble()
	{
		return RANDOM.nextDouble();
	}

	/**
	 * Returns a pseudo random boolean.
	 * I will approximately be false 50% of the time
	 * and true the rest of the time.
	 *
	 * @return
	 * A pseudo random boolean
	 */
	public static boolean randomBoolean()
	{
		return RANDOM.nextBoolean();
	}

	// -------------------------------------------- //
	// RANDOM BETWEEN
	// -------------------------------------------- //

	/**
	 * Returns a pseudo random double between two passed doubles.
	 *
	 * @param start
	 * The smallest (closest to negative infinity) the return value can be (inclusive).
	 * @param end
	 * The smallest (closest to negative infinity) the return value can not be (exclusive).
	 * @return
	 * A pseudo random double between the two passed doubles
	 * @throws ArgumentStrangeException
	 * if any of the passed doubles are infinite or NaN
	 * @throws IllegalArgumentException
	 * If start is equal to or higher than end.
	 * @throws ArithmeticException
	 * If difference between start and end cannot be calculated.
	 */
	public static double randomDouble(double start, double end) throws IllegalArgumentException, ArgumentStrangeException, ArithmeticException
	{
		// Checks
		Argument.handleStrange(start, "start");
		Argument.handleStrange(start, "end");

		if (start >= end) throw new IllegalArgumentException("start: " + start + " end:" + end);

		// Calculate ret
		double diff = end - start;
		if ( ! Double.isFinite(diff)) throw new ArithmeticException("Couldn't calculate difference between " + start + " and " + end);
		double random = randomDouble() * diff;
		double ret =  start + random;

		// Assert ret
		// These assertions, is required for proper testing. If these are removed, they should be reimplemented in a unit test.
		assert ret >= start : "ret: " + ret + " start: " + start + " end: " + end + " random: " + random + " diff: " + diff;
		assert ret < end : "ret: " + ret + " start: " + start + " end: " + end + " random: " + random + " diff: " + diff;

		// Return ret
		return ret;
	}

	/**
	 * Returns a pseudo random int between two passed ints.
	 *
	 * @param start
	 * The smallest (closest to Integer.MIN_VALUE) the return value can be (inclusive).
	 * @param end
	 * The smallest (closts to Integer.MIN_VALUE) the return value can not be (exclusive).
	 * @return
	 * A pseudo random int between the two passed ints.
	 * @throws IllegalArgumentException
	 * if starts is equal to or higher than end.
     * @throws ArithmeticException
     * If the difference between start and end, can't be calculated.
	 */
	public static int randomInt(final int start, final int end) throws IllegalArgumentException, ArithmeticException
	{
		// Checks
		if (start >= end) throw new IllegalArgumentException("start: " + start + " end:" + end);

		// Calculate ret
		final int diff = Math.subtractExact(end, start);
		final int random = (int) (randomDouble() * diff);
		final int ret =  start + random;

		// Assert ret
		// These assertions, is required for proper testing. If these are removed, they should be reimplemented in a unit test.
		assert ret >= start : "ret: " + ret + " start: " + start;
		assert ret < end : "ret: " + ret + " end: " + end;

		// Return ret
		return ret;
	}

	/**
	 * Returns a pseudo random long between two passed longs.
	 *
	 * @param start
	 * The smallest (closest to Long.MIN_VALUE) the return value can be (inclusive).
	 * @param end
	 * The smallest (closets to Long.MIN_VALUE) the return value can not be (exclusive).
	 * @return
	 * A pseudo random long between the two passed longs.
	 * @throws IllegalArgumentException
	 * If start is equal to or higher than end.
	 * @throws ArithmeticException
	 * If the difference between start and end, can't be calculated.
	 */
	public static long randomLong(final long start, final long end) throws IllegalArgumentException, ArithmeticException
	{
		// Checks
		if (start >= end) throw new IllegalArgumentException("start: " + start + " end:" + end);

		// Calculate ret
		final long diff = Math.subtractExact(end, start);
		final long random = (long) (randomDouble() * diff);
		final long ret =  start + random;

		// Assert ret
		// These assertions, is required for proper testing. If these are removed, they should be reimplemented in a unit test.
		assert ret >= start : "ret: " + ret + " start: " + start;
		assert ret < end : "ret: " + ret + " end: " + end;

		// Return ret
		return ret;
	}

}
