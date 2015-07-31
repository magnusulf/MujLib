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
import dk.muj.mujlib.arg.ArgumentNullException;
import dk.muj.mujlib.doc.Hacky;
import dk.muj.mujlib.doc.Pure;

import java.util.Objects;

/**
 * This class is a bunch of assorted utility methods.
 * Which don't belong anywhere else, yet.
 *
 * IMPORTANT SUBJECT: EQUALITY
 * This class defines a bunch of methods, that checks for equality.
 * There is methods defined for {@code Object}
 * and special methods for all primitive types.
 * Not all methods for primitive types, will yield the
 * same result as an equality comparator {@code ==}.
 * The definitions used in this class, is also
 * the equality definitions used in the rest of MujLib.
 *
 * @author Magnus Ulf Jørgensen
 */
public final class Mujtil
{
	// -------------------------------------------- //
	// CONSTRUCTOR (FORBIDDEN)
	// -------------------------------------------- //

	private Mujtil()
	{
		throw new AssertionError();
	}

	// -------------------------------------------- //
	// SNEAKY THROW
	// -------------------------------------------- //

	/**
	 * This will throw a checked exception,
	 * as if it was unchecked.
	 *
	 * NOTE: This relies on two hacky features.
	 * 1. The JVM doesn't know about checked exceptions.
	 * 2. Type erasure.
	 * If one of these things gets changed,
	 * this feature might be rendered unusable.
	 *
	 * However it is unlikely that this will get broken,
	 * in the near future.
	 * Even if the 'hacks' used to provide this method
	 * are fixed. We will just wrap it inside a RuntimeException
	 * so that this method will always be available.
	 *
	 * @param t
	 * The throwable to throw sneakily
	 * @throws ArgumentNullException
	 * If and only if t is null.
	 */
	@Hacky
	public static void sneakyThrow(Throwable t) throws ArgumentNullException
	{
		Argument.handleNull(t, "t");
		Mujtil.<RuntimeException>sneakyThrow0(t);
	}

	@SuppressWarnings("unchecked")
	private static <T extends Throwable> void sneakyThrow0(Throwable t) throws T
	{
		throw (T) t;
	}

	// -------------------------------------------- //
	// EQUALS
	// -------------------------------------------- //

	/**
	 * Returns true if both objects are equal, by using the equals method.
	 * Which object the method is called on, doesn't matter,
	 * because of the symmetry rule, defined in the equals contract.
	 * If both are null, they are also considered equal.
	 * If just one of them is null, they are not considered equal.
	 * @param one
	 * The first nullable object to be tested for equality.
	 * @param two
	 * The second nullable object to be tested for equality.
	 * @return equality
	 * True if and only if the objects are equal to each other.
	 * False otherwise.
	 */
	@Pure
	public static boolean equals(Object one, Object two)
	{
		boolean ret = (one != null ? one.equals(two) : two == null);
		assert ret == Objects.equals(one, two);
		return ret;
	}

	/**
	 * Check whether the passed booleans are considered equal.
	 * @param one
	 * First boolean to be tested for equality.
	 * @param two
	 * Second boolean to be tested for equality.
	 * @return equality
	 * True if both booleans are equal.
	 *
	 * Two booleans x and y are considered equal if
	 * x == y
	 */
	@Pure
	public static boolean equals(boolean one, boolean two)
	{
		boolean ret = (one == two);
		assert Boolean.valueOf(one).equals(Boolean.valueOf(two)) == ret : "one: " + one + " two: " + two + " ret: " + ret;
		return ret;
	}

	/**
	 * Check whether the passed bytes are considered equal.
	 * @param one
	 * First byte to be tested for equality.
	 * @param two
	 * Second byte to be tested for equality.
	 * @return equality
	 * True if both bytes are equal.
	 *
	 * Two bytes x and y are considered equal if
	 * x == y
	 */
	@Pure
	public static boolean equals(byte one, byte two)
	{
		boolean ret = (one == two);
		assert Byte.valueOf(one).equals(Byte.valueOf(two)) == ret : "one: " + one + " two: " + two + " ret: " + ret;
		return ret;
	}

	/**
	 * Check whether the passed chars are considered equal.
	 * @param one
	 * First char to be tested for equality.
	 * @param two
	 * Second char to be tested for equality.
	 * @return equality
	 * True if both chars are equal.
	 *
	 * Two chars x and y are considered equal if
	 * x == y
	 */
	@Pure
	public static boolean equals(char one, char two)
	{
		boolean ret = (one == two);
		assert Character.valueOf(one).equals(Character.valueOf(two)) == ret : "one: " + one + " two: " + two + " ret: " + ret;
		return ret;
	}

	/**
	 * Check whether the passed shorts are considered equal.
	 * @param one
	 * First short to be tested for equality.
	 * @param two
	 * Second short to be tested for equality.
	 * @return equality
	 * True if both shorts are equal.
	 *
	 * Two shorts x and y are considered equal if
	 * x == y
	 */
	@Pure
	public static boolean equals(short one, short two)
	{
		boolean ret = (one == two);
		assert Short.valueOf(one).equals(Short.valueOf(two)) == ret : "one: " + one + " two: " + two + " ret: " + ret;
		return ret;
	}

	/**
	 * Check whether the passed ints are considered equal.
	 * @param one
	 * First int to be tested for equality.
	 * @param two
	 * Second int to be tested for equality.
	 * @return equality
	 * True if both ints are equal.
	 *
	 * Two ints x and y are considered equal if
	 * x == y
	 */
	@Pure
	public static boolean equals(int one, int two)
	{
		boolean ret = (one == two);
		assert Integer.valueOf(one).equals(Integer.valueOf(two)) == ret : "one: " + one + " two: " + two + " ret: " + ret;
		return ret;
	}

	/**
	 * Check whether the passed longs are considered equal.
	 * @param one
	 * First long to be tested for equality.
	 * @param two
	 * Second long to be tested for equality.
	 * @return equality
	 * True if both longs are equal.
	 *
	 * Two longs x and y are considered equal if
	 * x == y
	 */
	@Pure
	public static boolean equals(long one, long two)
	{
		boolean ret = (one == two);
		assert Long.valueOf(one).equals(Long.valueOf(two)) == ret : "one: " + one + " two: " + two + " ret: " + ret;
		return ret;
	}

	/**
	 * Check whether the passed floats are considered equal.
	 * @param one
	 * First float to be tested for equality.
	 * @param two
	 * Second float to be tested for equality.
	 * @return equality
	 * True if both floats are equal.
	 *
	 * Two floats x and y are considered equal if
	 * Float.floatToIntBits(x) == Float.floatToIntBits(y)
	 *
	 * This means that Mujtil.equals(0.0F, -0.0F);
	 * will yield false, which is different from the equality operators
	 * and that Mujtil.equals(Float.NaN, Float.NaN);
	 * will yield true, which is different from the equality operators
	 */
	@Pure
	public static boolean equals(float one, float two)
	{
		boolean ret = (Float.floatToIntBits(one) == Float.floatToIntBits(two));
		assert Float.valueOf(one).equals(Float.valueOf(two)) == ret : "one: " + one + " two: " + two + " ret: " + ret;
		return ret;
	}

	/**
	 * Check whether the passed doubles are considered equal.
	 * @param one
	 * First double to be tested for equality.
	 * @param two
	 * Second double to be tested for equality.
	 * @return equality
	 * True if both doubles are equal.
	 *
	 * Two doubles x and y are considered equal if
	 * Double.doubleToLongBits(x) == Double.doubleToLongBits(y)
	 *
	 * This means that Mujtil.equals(0.0D, -0.0D);
	 * will yield false, which is different from the equality operators
	 * and that Mujtil.equals(Double.NaN, Double.NaN);
	 * will yield true, which is different from the equality operators
	 */
	@Pure
	public static boolean equals(double one, double two)
	{
		boolean ret = (Double.doubleToLongBits(one) == Double.doubleToLongBits(two));
		assert Double.valueOf(one).equals(Double.valueOf(two)) == ret : "one: " + one + " two: " + two + " ret: " + ret;
		return ret;
	}

}
