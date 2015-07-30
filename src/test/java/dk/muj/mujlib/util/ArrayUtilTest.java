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

import static dk.muj.mujlib.util.ArrayUtil.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Objects;

public class ArrayUtilTest extends TestCase
{
	// -------------------------------------------- //
	// OBJECTS
	// -------------------------------------------- //

	// NOTE: Changing these, will break some tests. Be careful.
	private static final Object[] objArr = {new Object(), new Object(), new Object(), new Object(), null};
	private static final boolean[] boolArr1 = {true, false};
	private static final boolean[] boolArr2 = {false};
	private static final byte[] byteArr = {1, 2, -17, 127};
	private static final char[] charArr = {'A', 0, ' ', '!'};
	private static final short[] shortArr = {1, 6542, 4265, -654, -3465};
	private static final int[] intArr = {1, 6542, 4265, -1, -3465};
	private static final long[] longArr = {1, 6542, 4265, -1, -3465};
	private static final float[] floatArr = {1, 6542, 4265, -1, -3465};
	private static final double[] doubleArr = {1, 6542, 4265, -1, -3465};

	// -------------------------------------------- //
	// INDEX OF (EQUALITY)
	// -------------------------------------------- //

	@Test
	public void testIndexOfObject() throws Exception
	{
		for (int i = 0; i < objArr.length; i++)
		{
			assertEquals(i, objArr[i] != null ? indexOfElement(objArr, objArr[i]) : indexOfNull(objArr));
		}
		assertEquals(INDEX_NOT_FOUND, indexOfElement(objArr, new Object()));
		assertEquals(4, indexOfNull(objArr));
	}

	@Test
	public void testIndexOfBoolean() throws Exception
	{
		for (int i = 0; i < boolArr1.length; i++)
		{
			assertEquals(i, indexOfElement(boolArr1, boolArr1[i]));
		}
		assertEquals(INDEX_NOT_FOUND, indexOfElement(boolArr2, true));
	}

	@Test
	public void testIndexOfByte() throws Exception
	{
		for (int i = 0; i < byteArr.length; i++)
		{
			assertEquals(i, indexOfElement(byteArr, byteArr[i]));
		}
		assertEquals(INDEX_NOT_FOUND, indexOfElement(byteArr, (byte) 3));
	}

	@Test
	public void testIndexOfChar() throws Exception
	{
		for (int i = 0; i < charArr.length; i++)
		{
			assertEquals(i, indexOfElement(charArr, charArr[i]));
		}
		assertEquals(INDEX_NOT_FOUND, indexOfElement(charArr, 'B'));
	}

	@Test
	public void testIndexOfShort() throws Exception
	{
		for (int i = 0; i < shortArr.length; i++)
		{
			assertEquals(i, indexOfElement(shortArr, shortArr[i]));
		}
		assertEquals(INDEX_NOT_FOUND, indexOfElement(shortArr, (short) 10));
	}

	@Test
	public void testIndexOfInt() throws Exception
	{
		for (int i = 0; i < intArr.length; i++)
		{
			assertEquals(i, indexOfElement(intArr, intArr[i]));
		}
		assertEquals(INDEX_NOT_FOUND, indexOfElement(intArr, 10));
	}

	@Test
	public void testIndexOfLong() throws Exception
	{
		for (int i = 0; i < longArr.length; i++)
		{
			assertEquals(i, indexOfElement(longArr, longArr[i]));
		}
		assertEquals(INDEX_NOT_FOUND, indexOfElement(longArr, 10));
	}

	@Test
	public void testIndexOfFloat() throws Exception
	{
		for (int i = 0; i < floatArr.length; i++)
		{
			assertEquals(i, indexOfElement(floatArr, floatArr[i]));
		}
		assertEquals(INDEX_NOT_FOUND, indexOfElement(floatArr, 10));
	}

	@Test
	public void testIndexOfDouble() throws Exception
	{
		for (int i = 0; i < doubleArr.length; i++)
		{
			assertEquals(i, indexOfElement(doubleArr, doubleArr[i]));
		}
		assertEquals(INDEX_NOT_FOUND, indexOfElement(doubleArr, 10));
	}

	// -------------------------------------------- //
	// INDEX OF (PREDICATE)
	// -------------------------------------------- //

	@Test
	public void testIndexOfObjectPredicate() throws Exception
	{
		// TODO: Add more predicates to test with.
		Object obj = objArr[0];
		assertEquals(0, indexOfMatching(objArr, obj::equals));
		assertEquals(0, indexOfMatching(objArr, Objects::nonNull));
		assertEquals(4, indexOfMatching(objArr, Objects::isNull));
	}

	@Test
	public void testIndexOfIntPredicate() throws Exception
	{
		assertEquals(0, indexOfMatching(intArr, Mth::isPositive));
		assertEquals(3, indexOfMatching(intArr, Mth::isNegative));
	}

	@Test
	public void testIndexOfLongPredicate() throws Exception
	{
		assertEquals(0, indexOfMatching(longArr, Mth::isPositive));
		assertEquals(3, indexOfMatching(longArr, Mth::isNegative));
	}

	@Test
	public void testIndexOfDoublePredicate() throws Exception
	{
		assertEquals(0, indexOfMatching(doubleArr, Mth::isPositive));
		assertEquals(3, indexOfMatching(doubleArr, Mth::isNegative));
	}

	// -------------------------------------------- //
	// CONTAINS (EQUALITY)
	// -------------------------------------------- //

	/*
	private static final Object[] objArr = {new Object(), new Object(), new Object(), new Object(), null};
	private static final boolean[] boolArr1 = {true, false};
	private static final boolean[] boolArr2 = {false};
	private static final byte[] byteArr = {1, 2, -17, 127};
	private static final char[] charArr = {'A', 0, ' ', '!'};
	private static final short[] shortArr = {1, 6542, 4265, -654, -3465};
	private static final int[] intArr = {1, 6542, 4265, -1, -3465};
	private static final long[] longArr = {1, 6542, 4265, -1, -3465};
	private static final float[] floatArr = {1, 6542, 4265, -1, -3465};
	private static final double[] doubleArr = {1, 6542, 4265, -1, -3465};
	*/

	@Test
	public void testContainsObject() throws Exception
	{
		for (int i = 0; i < objArr.length; i++)
		{
			assertTrue(objArr[i] != null ? containsElement(objArr, objArr[i]) : containsNull(objArr));
		}
		assertFalse(containsElement(objArr, new Object()));
	}

	@Test
	public void testContainsBoolean() throws Exception
	{
		for (int i = 0; i < boolArr1.length; i++)
		{
			assertEquals(true, containsElement(boolArr1, boolArr1[i]));
		}
		assertEquals(false, containsElement(boolArr2, true));
	}

	@Test
	public void testContainsByte() throws Exception
	{
		for (int i = 0; i < byteArr.length; i++)
		{
			assertEquals(true, containsElement(byteArr, byteArr[i]));
		}
		assertEquals(false, containsElement(byteArr, (byte) 3));
	}

	@Test
	public void testContainsChar() throws Exception
	{
		for (int i = 0; i < charArr.length; i++)
		{
			assertEquals(true, containsElement(charArr, charArr[i]));
		}
		assertEquals(false, containsElement(charArr, 'B'));
	}

	@Test
	public void testContainsShort() throws Exception
	{
		for (int i = 0; i < shortArr.length; i++)
		{
			assertEquals(true, containsElement(shortArr, shortArr[i]));
		}
		assertEquals(false, containsElement(shortArr, (short) 10));
	}

	@Test
	public void testContainsInt() throws Exception
	{
		for (int i = 0; i < intArr.length; i++)
		{
			assertEquals(true, containsElement(intArr, intArr[i]));
		}
		assertEquals(false, containsElement(intArr, 10));
	}

	@Test
	public void testContainsLong() throws Exception
	{
		for (int i = 0; i < longArr.length; i++)
		{
			assertEquals(true, containsElement(longArr, longArr[i]));
		}
		assertEquals(false, containsElement(longArr, 10));
	}

	@Test
	public void testContainsFloat() throws Exception
	{
		for (int i = 0; i < floatArr.length; i++)
		{
			assertEquals(true, containsElement(floatArr, floatArr[i]));
		}
		assertEquals(false, containsElement(floatArr, 10));
	}

	@Test
	public void testContainsDouble() throws Exception
	{
		for (int i = 0; i < doubleArr.length; i++)
		{
			assertEquals(true, containsElement(doubleArr, doubleArr[i]));
		}
		assertEquals(false, containsElement(doubleArr, 10));
	}

	// -------------------------------------------- //
	// CONTAINS (PREDICATE)
	// -------------------------------------------- //

	@Test
	public void testContainsObjectPredicate() throws Exception
	{
		assertTrue(containsMatching(objArr, Objects::isNull));
		assertTrue(containsMatching(objArr, Objects::nonNull));
		assertFalse(containsMatching(objArr, s -> s instanceof String));
	}

	@Test
	public void testContainsIntPredicate() throws Exception
	{
		assertTrue(containsMatching(intArr, Mth::isPositive));
		assertTrue(containsMatching(intArr, Mth::isNegative));
		assertFalse(containsMatching(intArr, i -> i == Integer.MAX_VALUE));
	}

	@Test
	public void testContainsLongPredicate() throws Exception
	{
		assertTrue(containsMatching(longArr, Mth::isPositive));
		assertTrue(containsMatching(longArr, Mth::isNegative));
		assertFalse(containsMatching(longArr, i -> i == Long.MAX_VALUE));
	}

	@Test
	public void testContainsDoublePredicate() throws Exception
	{
		assertTrue(containsMatching(doubleArr, Mth::isPositive));
		assertTrue(containsMatching(doubleArr, Mth::isNegative));
		assertFalse(containsMatching(doubleArr, i -> i == Double.MAX_VALUE));
	}

	// -------------------------------------------- //
	// FIRST MATCHING (PREDICATE)
	// -------------------------------------------- //

	/*
	private static final Object[] objArr = {new Object(), new Object(), new Object(), new Object(), null};
	private static final boolean[] boolArr1 = {true, false};
	private static final boolean[] boolArr2 = {false};
	private static final byte[] byteArr = {1, 2, -17, 127};
	private static final char[] charArr = {'A', 0, ' ', '!'};
	private static final short[] shortArr = {1, 6542, 4265, -654, -3465};
	private static final int[] intArr = {1, 6542, 4265, -1, -3465};
	private static final long[] longArr = {1, 6542, 4265, -1, -3465};
	private static final float[] floatArr = {1, 6542, 4265, -1, -3465};
	private static final double[] doubleArr = {1, 6542, 4265, -1, -3465};
	*/

	@Test
	public void testFirstMatchingObject()
	{
		assertEquals(objArr[0], firstMatching(objArr, Objects::nonNull).get());
		assertFalse(firstMatching(objArr, s -> s instanceof String).isPresent());
	}

	@Test
	public void testFirstMatchingInt()
	{
		assertEquals(1, firstMatching(intArr, Mth::isPositive).getAsInt());
		assertEquals(-1, firstMatching(intArr, Mth::isNegative).getAsInt());
	}

	@Test
	public void testFirstMatchingLong()
	{
		assertEquals(1L, firstMatching(longArr, Mth::isPositive).getAsLong());
		assertEquals(-1L, firstMatching(longArr, Mth::isNegative).getAsLong());
	}

	@Test
	public void testFirstMatchingDouble()
	{
		assertEquals(1D, firstMatching(doubleArr, Mth::isPositive).getAsDouble());
		assertEquals(-1D, firstMatching(doubleArr, Mth::isNegative).getAsDouble());
	}

	// -------------------------------------------- //‚
	// FIRST MATCHING (PREDICATE)
	// -------------------------------------------- //

	@Test
	public void testFilterObject()
	{
		Object[] filtered = filter(objArr, Objects::nonNull);
		assertEquals(4, filtered.length);

		for (int i = 0; i < filtered.length; i++)
		{
			assertEquals(filtered[i], objArr[i]);
		}
	}

	@Test
	public void testFilterInt()
	{
		assertArrayEquals(new int[]{6542}, filter(intArr, Mth::isEven));
	}

	@Test
	public void testFilterLong()
	{
		assertArrayEquals(new long[]{6542}, filter(longArr, Mth::isEven));
	}

	@Test
	public void testFilterDouble()
	{
		assertTrue(Arrays.equals(new double[]{- 1, - 3465}, filter(doubleArr, Mth::isNegative)));
	}

}
