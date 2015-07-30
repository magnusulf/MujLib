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

package dk.muj.mujlib.hash;

import dk.muj.mujlib.ExceptionHandler;
import dk.muj.mujlib.MujLib;
import dk.muj.mujlib.util.ReflectionUtil;

import java.lang.reflect.Field;

/**
 * All parameters to method in this class are nullable, unless otherwise stated.
 * That is different from most of MujLib which is strictly against null.
 *
 * This class can help to generate appropriate
 * hash values, for all kinds of fields, using it's hash() methods.
 * For any values x and y, being objects, primitives or arrays.
 * Their hashcode will be the same if they are equal, as defined in Mujtil.
 *
 * It can also add a field to an already existing hashCode,
 * in a manner such that the order the order for field variations
 * is important so that the hashcodes will be spread out.
 * It can be done as follows. Where result is the hashcode, and f is the field to add.
 * result = resultAddField(result, f);
 *
 * A whole hashcode could be generated as this.
 * int result = HashUtil.HASHCODE_START
 * result = HashUtil.resultAddField(result, field1)
 * result = HashUtil.resultAddField(result, field2)
 * result = HashUtil.resultAddField(result, field3)
 * result = HashUtil.resultAddField(result, field4)
 *
 * The absolutely easiest way to generate a hashcode
 * for a class, and it's fields. Is hashcode(field1, field2, etc).
 *
 * @author Magnus Ulf Jørgensen
 */
public final class HashUtil
{
	// -------------------------------------------- //
	// CONSTRUCTOR (FORBIDDEN)
	// -------------------------------------------- //

	private HashUtil()
	{
		throw new AssertionError();
	}

	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //

	/**
	 * The initial value for a hashcode
	 * before any fields is added to it.
	 *
	 * Using this value is by no means a requirement,
	 * but that is done all over MujLib.
	 */
	public static final int HASHCODE_START = 17;

	/**
	 * The value used for null elements.
	 * So that is a value is null, this will be returned instead.
	 */
	public static final int HASHCODE_NULL = 0;

	/**
	 * When adding a field to a hash code,
	 * multiplication is done with the current hash code value.
	 * Such as:
	 * result = result * HASHCODE_MULTIPLIER + hash(field)
	 *
	 * This is the value used, it is guaranteed to be a prime number.
	 */
	public static final int HASHCODE_MULTIPLIER = 31;

	// -------------------------------------------- //
	// RESULT ADD FIELD
	// -------------------------------------------- //

	public static int resultAddField(int result, Object f) { return HASHCODE_MULTIPLIER * result + hash(f); }
	public static int resultAddField(int result, boolean f) { return HASHCODE_MULTIPLIER * result + hash(f); }
	public static int resultAddField(int result, byte f) { return HASHCODE_MULTIPLIER * result + hash(f); }
	public static int resultAddField(int result, char f) { return HASHCODE_MULTIPLIER * result + hash(f); }
	public static int resultAddField(int result, short f) { return HASHCODE_MULTIPLIER * result + hash(f); }
	public static int resultAddField(int result, int f) { return HASHCODE_MULTIPLIER * result + hash(f); }
	public static int resultAddField(int result, long f) { return HASHCODE_MULTIPLIER * result + hash(f); }
	public static int resultAddField(int result, float f) { return HASHCODE_MULTIPLIER * result + hash(f); }
	public static int resultAddField(int result, double f) { return HASHCODE_MULTIPLIER * result + hash(f); }

	// -------------------------------------------- //
	// CORE LOGIC
	// -------------------------------------------- //

	public static int hash(Object f) { return (f == null ? HASHCODE_NULL : f.hashCode()); }
	public static int hash(boolean f) { return Boolean.hashCode(f); }
	public static int hash(byte f) { return Byte.hashCode(f); }
	public static int hash(char f) { return Character.hashCode(f); }
	public static int hash(short f) { return Short.hashCode(f); }
	public static int hash(int f) { return Integer.hashCode(f); }
	public static int hash(long f) { return Long.hashCode(f); }
	public static int hash(float f) { return Float.hashCode(f); }
	public static int hash(double f) { return Double.hashCode(f); }

	// -------------------------------------------- //
	// SIMPLE ARRAY LOGIC
	// -------------------------------------------- //
	// TODO: Should we use a reduce function to do this, or will that be too costly?

	public static int hash(Object[] arr)
	{
		if (arr == null) return HASHCODE_NULL;

		int result = HASHCODE_START;
		for (Object f : arr)
		{
			result = resultAddField(result, f);
		}
		return result;
	}

	public static int hash(boolean[] arr)
	{
		if (arr == null) return HASHCODE_NULL;

		int result = HASHCODE_START;
		for (boolean f : arr)
		{
			result = resultAddField(result, f);
		}
		return result;
	}

	public static int hash(byte[] arr)
	{
		if (arr == null) return HASHCODE_NULL;

		int result = HASHCODE_START;
		for (byte f : arr)
		{
			result = resultAddField(result, f);
		}
		return result;
	}

	public static int hash(char[] arr)
	{
		if (arr == null) return HASHCODE_NULL;

		int result = HASHCODE_START;
		for (char f : arr)
		{
			result = resultAddField(result, f);
		}
		return result;
	}

	public static int hash(short[] arr)
	{
		if (arr == null) return HASHCODE_NULL;

		int result = HASHCODE_START;
		for (short f : arr)
		{
			result = resultAddField(result, f);
		}
		return result;
	}

	public static int hash(int[] arr)
	{
		if (arr == null) return HASHCODE_NULL;

		int result = HASHCODE_START;
		for (int f : arr)
		{
			result = resultAddField(result, f);
		}
		return result;
	}

	public static int hash(long[] arr)
	{
		if (arr == null) return HASHCODE_NULL;

		int result = HASHCODE_START;
		for (long f : arr)
		{
			result = resultAddField(result, f);
		}
		return result;
	}

	public static int hash(float[] arr)
	{
		if (arr == null) return HASHCODE_NULL;

		int result = HASHCODE_START;
		for (float f : arr)
		{
			result = resultAddField(result, f);
		}
		return result;
	}

	public static int hash(double[] arr)
	{
		if (arr == null) return HASHCODE_NULL;

		int result = HASHCODE_START;
		for (double f : arr)
		{
			result = resultAddField(result, f);
		}
		return result;
	}

	// -------------------------------------------- //
	// DEEP HASH
	// -------------------------------------------- //

	// TODO: What to do with this?
	/*
	public static int deepHash(Object obj)
	{
		Arrays.deepHashCode()
		if (obj == null) return HASHCODE_NULL;

		if (obj instanceof Object[]) return deepHash((Object[]) obj);
		if (obj instanceof boolean[]) return hash((boolean[]) obj);
		if (obj instanceof byte[]) return hash((byte[]) obj);
		if (obj instanceof char[]) return hash((char[]) obj);
		if (obj instanceof short[]) return hash((short[]) obj);
		if (obj instanceof int[]) return hash((int[]) obj);
		if (obj instanceof long[]) return hash((long[]) obj);
		if (obj instanceof float[]) return hash((float[]) obj);
		if (obj instanceof double[]) return hash((double[]) obj);
		return hash(obj);
	}

	public static int deepHash(Object[] obj)
	{
		if (obj == null) return HASHCODE_NULL;
		int result = HASHCODE_START;

		for (Object element : obj)
		{
			result = resultAddField(result, deepHash(element));
		}

		return result;
	}*/

	// -------------------------------------------- //
	// EASY HASH
	// -------------------------------------------- //

	/**
	 * Calculates a hashcode based on all the passed elements.
	 * That can be used to easily calculate a hashcode on a single line, such as:
	 * {@code HashUtil.hashcode(field1, field2, field3, field4)}
	 * Which is equivalent to:
	 * int result = HashUtil.HASHCODE_START
	 * result = HashUtil.resultAddField(result, field1)
	 * result = HashUtil.resultAddField(result, field2)
	 * result = HashUtil.resultAddField(result, field3)
	 * result = HashUtil.resultAddField(result, field4)
	 *
	 * Array creation, and boxing of primitives
	 * cause this to not be the fastest hashcode implementation.
	 * But it is extremely easy to use.
	 * @param elements
	 * The elements you want to calculate a hashcode for.
	 * @return
	 * A hashcode based on all the passed elements.
	 */
	public static int hashcode(Object... elements)
	{
		return hash(elements);
	}

	// -------------------------------------------- //
	// REFLECTIVE HASH
	// -------------------------------------------- //

	/**
	 * Calculates the hashCode reflectively.
	 * The hashCode is based on ALL fields
	 * declared in the object.
	 *
	 * Fields with the annotation {@code ExcludeFromHashcode} however,
	 * are excluded from the hashCode calculation.
	 *
	 * If this method is used as the Object#hashcode implementation in a class
	 * all objects not used in the equals method, must be marked
	 * with {@code ExcludeFromHashcode} in order for the
	 * equals and hashcode contracts to be followed.
	 * 
	 * @param obj
	 * The object to calculate hashcode for.
	 * @return
	 * Reflectively generated hashcode for object.
	 */
	public static int reflectionHash(Object obj)
	{
		if (obj == null) return HASHCODE_NULL;

		int result = HASHCODE_START;

		for (Field field : obj.getClass().getDeclaredFields())
		{
			// Is it excluded?
			if (ReflectionUtil.hasAnnotation(field, ExcludeFromHashcode.class)) continue;

			// Must be accessible.
			// TODO: Should this be done here?
			field.setAccessible(true);

			// Fetch object.
			ExceptionHandler<AssertionError> action = MujLib.isDebugEnabled() ? ExceptionHandler.PRINT : ExceptionHandler.SILENT;
			Object f = ReflectionUtil.getFieldValue(field, obj, action);

			// Add to result
			result = resultAddField(result, f);
		}

		return result;
	}

}
