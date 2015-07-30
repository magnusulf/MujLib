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

/**
 * A utility class made to handle illegal arguments.
 * Illegal arguments could include null, NaN and infinity.
 *
 * To handle null you should do {@code Argument.handleNull(nonNullable, "nameOfNonNullable"); }
 * and it will throw an exception if nonNullable is null.
 *
 * @author Magnus Ulf Jørgensen
 */
public final class Argument
{
	// -------------------------------------------- //
	// CONSTRUCTOR (FORBIDDEN)
	// -------------------------------------------- //

	private Argument()
	{
		throw new AssertionError();
	}

	// -------------------------------------------- //
	// NULL
	// -------------------------------------------- //

	/**
	 * If the passed Object is null
	 * throws an {@code ArgumentNullException }
	 *
	 * @param obj
	 * The object to check whether or not it is null.
	 * @param message
	 * The message to use in an exception
	 * if the passed Object is null.
	 * @throws ArgumentNullException
	 * If and only if obj is null.
	 */
	public static void handleNull(Object obj, String message) throws ArgumentNullException
	{
		if (obj == null) throw new ArgumentNullException(message);
	}

	// -------------------------------------------- //
	// STRANGE DOUBLE
	// -------------------------------------------- //

	/**
	 * If the passed double is strange
	 * throws an {@code ArgumentStrangeException }
	 *
	 * A {@code ArgumentStrangeException} will not be thrown
	 * explicitly, but one of it's subtypes will.
	 *
	 * Strange means that it is NaN or infinite.
	 *
	 * @param d
	 * The double to check whether or not it is strange.
	 * @param message
	 * The message to use in an exception
	 * if the passed double is strange.
	 * @throws ArgumentStrangeException
	 * if and only if d is strange.
	 */
	public static void handleStrange(double d, String message) throws ArgumentStrangeException
	{
		handleInfinite(d, message);
		handleNaN(d, message);
	}

	/**
	 * If the passed double is infinite
	 * throws an {@code ArgumentInfiniteException }
	 *
	 * @param d
	 * The double to check whether or not it is infinite.
	 * @param message
	 * The message to use in an exception
	 * if the passed double is infinite.
	 * @throws ArgumentInfiniteException
	 * if and only if d is infinite.
	 */
	public static void handleInfinite(double d, String message) throws ArgumentInfiniteException
	{
		if (Double.isInfinite(d)) throw new ArgumentInfiniteException(message);
	}

	/**
	 * If the passed double is NaN
	 * throws an {@code ArgumentNanException }
	 *
	 * @param d
	 * The double to check whether or not it is NaN.
	 * @param message
	 * The message to use in an exception
	 * if the passed double is NaN.
	 * @throws ArgumentNanException
	 * if and only if d is NaN.
	 */
	public static void handleNaN(double d, String message) throws ArgumentNanException
	{
		if (Double.isNaN(d)) throw new ArgumentNanException(message);
	}

	// -------------------------------------------- //
	// STRANGE FLOAT
	// -------------------------------------------- //

	/**
	 * If the passed double is strange
	 * throws an {@code ArgumentStrangeException }
	 *
	 * A {@code ArgumentStrangeException} will not be thrown
	 * explicitly, but one of it's subtypes will.
	 *
	 * Strange means that it is NaN or infinite.
	 *
	 * @param f
	 * The float to check whether or not it is strange.
	 * @param message
	 * The message to use in an exception
	 * if the passed double is strange.
	 * @throws ArgumentStrangeException
	 * if and only if d is strange.
	 */
	public static void handleStrange(float f, String message) throws ArgumentStrangeException
	{
		handleInfinite(f, message);
		handleNaN(f, message);
	}

	/**
	 * If the passed double is infinite
	 * throws an {@code ArgumentInfiniteException }
	 *
	 * @param f
	 * The float to check whether or not it is infinite.
	 * @param message
	 * The message to use in an exception
	 * if the passed double is infinite.
	 * @throws ArgumentInfiniteException
	 * if and only if d is infinite.
	 */
	public static void handleInfinite(float f, String message) throws ArgumentInfiniteException
	{
		if (Float.isInfinite(f)) throw new ArgumentInfiniteException(message);
	}

	/**
	 * If the passed double is NaN
	 * throws an {@code ArgumentNanException }
	 *
	 * @param f
	 * The float to check whether or not it is NaN.
	 * @param message
	 * The message to use in an exception
	 * if the passed double is NaN.
	 * @throws ArgumentNanException
	 * if and only if d is NaN.
	 */
	public static void handleNaN(float f, String message) throws ArgumentNanException
	{
		if (Float.isNaN(f)) throw new ArgumentNanException(message);
	}

}
