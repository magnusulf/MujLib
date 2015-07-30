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
import dk.muj.mujlib.doc.Pure;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.*;

/**
 * This class provides some utility methods
 * for dealing with arrays.
 * This class aims to make it easier to use arrays,
 * so that arrays can be used instead of lists.
 * That might seem like a weird goal, considering
 * how much more useful and flexible a list can be.
 * Arrays however can be significantly faster than lists
 * under many circumstances.
 *
 * @author Magnus Ulf Jørgensen
 */
public final class ArrayUtil
{
	// -------------------------------------------- //
	// CONSTRUCTOR (FORBIDDEN)
	// -------------------------------------------- //

	private ArrayUtil()
	{
		throw new AssertionError();
	}

	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //

	/**
	 * The index used, if no matching element was used.
	 * This is {@code -1} because that is the java standard
	 * for similar operations on e.g string and list.
	 */
	public static final int INDEX_NOT_FOUND = -1;

	// -------------------------------------------- //
	// INDEX OF (EQUALITY)
	// -------------------------------------------- //

	/**
	 * Returns the index, for the first null element.
	 * In the passed object array.
	 * @param elements
	 * The array to look for a null object in.
	 * @return
	 * Index of the first null element in the passed array.
	 * If none is present then {@code INDEX_NOT_FOUND} is returned.
	 * @throws ArgumentNullException
	 * If elements is null
	 */
	@Pure
	public static int indexOfNull(Object[] elements) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		for (int i = 0; i < elements.length; i++)
		{
			if (elements[i] == null) return i;
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * Returns the index for the first occurrence
	 * of an element equal to the specified object.
	 * @param elements
	 * The array to look for the occurrence in.
	 * @param element
	 * The specified element to find the indexOf.
	 * @return
	 * The index of the first occurrence of an object equal to element, as defined in Mujtil.
	 * If none is present then {@code INDEX_NOT_FOUND} is returned.
	 * @throws ArgumentNullException
	 * If elements or element is null.
	 */
	@Pure
	public static int  indexOfElement(Object[] elements, Object element) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		Argument.handleNull(element, "element");
		for (int i = 0; i < elements.length; i++)
		{
			if (element.equals(elements[i])) return i;
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * Returns the index for the first occurrence
	 * of an element equal to the specified object.
	 * @param elements
	 * The array to look for the occurrence in.
	 * @param element
	 * The specified element to find the indexOf.
	 * @return
	 * The index of the first occurrence of an object equal to element, as defined in Mujtil.
	 * If none is present then {@code INDEX_NOT_FOUND} is returned.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static int indexOfElement(boolean[] elements, boolean element) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		for (int i = 0; i < elements.length; i++)
		{
			if (Mujtil.equals(element, elements[i])) return i;
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * Returns the index for the first occurrence
	 * of an element equal to the specified object.
	 * @param elements
	 * The array to look for the occurrence in.
	 * @param element
	 * The specified element to find the indexOf.
	 * @return
	 * The index of the first occurrence of an object equal to element, as defined in Mujtil.
	 * If none is present then {@code INDEX_NOT_FOUND} is returned.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static int indexOfElement(byte[] elements, byte element) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		for (int i = 0; i < elements.length; i++)
		{
			if (Mujtil.equals(element, elements[i])) return i;
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * Returns the index for the first occurrence
	 * of an element equal to the specified object.
	 * @param elements
	 * The array to look for the occurrence in.
	 * @param element
	 * The specified element to find the indexOf.
	 * @return
	 * The index of the first occurrence of an object equal to element, as defined in Mujtil.
	 * If none is present then {@code INDEX_NOT_FOUND} is returned.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static int indexOfElement(char[] elements, char element) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		for (int i = 0; i < elements.length; i++)
		{
			if (Mujtil.equals(element, elements[i])) return i;
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * Returns the index for the first occurrence
	 * of an element equal to the specified object.
	 * @param elements
	 * The array to look for the occurrence in.
	 * @param element
	 * The specified element to find the indexOf.
	 * @return
	 * The index of the first occurrence of an object equal to element, as defined in Mujtil.
	 * If none is present then {@code INDEX_NOT_FOUND} is returned.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static int indexOfElement(short[] elements, short element) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		for (int i = 0; i < elements.length; i++)
		{
			if (Mujtil.equals(element, elements[i])) return i;
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * Returns the index for the first occurrence
	 * of an element equal to the specified object.
	 * @param elements
	 * The array to look for the occurrence in.
	 * @param element
	 * The specified element to find the indexOf.
	 * @return
	 * The index of the first occurrence of an object equal to element, as defined in Mujtil.
	 * If none is present then {@code INDEX_NOT_FOUND} is returned.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static int indexOfElement(int[] elements, int element) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		for (int i = 0; i < elements.length; i++)
		{
			if (Mujtil.equals(element, elements[i])) return i;
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * Returns the index for the first occurrence
	 * of an element equal to the specified object.
	 * @param elements
	 * The array to look for the occurrence in.
	 * @param element
	 * The specified element to find the indexOf.
	 * @return
	 * The index of the first occurrence of an object equal to element, as defined in Mujtil.
	 * If none is present then {@code INDEX_NOT_FOUND} is returned.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static int indexOfElement(long[] elements, long element) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		for (int i = 0; i < elements.length; i++)
		{
			if (Mujtil.equals(element, elements[i])) return i;
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * Returns the index for the first occurrence
	 * of an element equal to the specified object.
	 * @param elements
	 * The array to look for the occurrence in.
	 * @param element
	 * The specified element to find the indexOf.
	 * @return
	 * The index of the first occurrence of an object equal to element, as defined in Mujtil.
	 * If none is present then {@code INDEX_NOT_FOUND} is returned.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static int indexOfElement(float[] elements, float element) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		for (int i = 0; i < elements.length; i++)
		{
			if (Mujtil.equals(element, elements[i])) return i;
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * Returns the index for the first occurrence
	 * of an element equal to the specified object.
	 * @param elements
	 * The array to look for the occurrence in.
	 * @param element
	 * The specified element to find the indexOf.
	 * @return
	 * The index of the first occurrence of an object equal to element, as defined in Mujtil.
	 * If none is present then {@code INDEX_NOT_FOUND} is returned.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static int indexOfElement(double[] elements, double element) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		for (int i = 0; i < elements.length; i++)
		{
			if (Mujtil.equals(element, elements[i])) return i;
		}
		return INDEX_NOT_FOUND;
	}

	// -------------------------------------------- //
	// INDEX OF (PREDICATE)
	// -------------------------------------------- //

	/**
	 * Returns the index for the first occurrence
	 * of an element matching the specified predicate.
	 * @param elements
	 * The array to look for the occurrence in.
	 * @param predicate
	 * The predicate used to test elements in the array.
	 * @param <T>
	 * Type of the elements in the array.
	 * @return
	 * The index of the first occurrence that fulfils the predicates requirements.
	 * If none is present then {@code INDEX_NOT_FOUND} is returned.
	 * @throws ArgumentNullException
	 * If elements or predicate is null.
	 */
	@Pure
	public static <T> int indexOfMatching(T[] elements, Predicate<? super T> predicate) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		Argument.handleNull(predicate, "predicate");
		for (int i = 0; i < elements.length; i++)
		{
			if (predicate.test(elements[i])) return i;
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * Returns the index for the first occurrence
	 * of an element matching the specified predicate.
	 * @param elements
	 * The array to look for the occurrence in.
	 * @param predicate
	 * The predicate used to test elements in the array.
	 * @return
	 * The index of the first occurrence that fulfils the predicates requirements.
	 * If none is present then {@code INDEX_NOT_FOUND} is returned.
	 * @throws ArgumentNullException
	 * If elements or predicate is null.
	 */
	@Pure
	public static int indexOfMatching(int elements[], IntPredicate predicate) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		Argument.handleNull(predicate, "predicate");
		for (int i = 0; i < elements.length; i++)
		{
			if (predicate.test(elements[i])) return i;
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * Returns the index for the first occurrence
	 * of an element matching the specified predicate.
	 * @param elements
	 * The array to look for the occurrence in.
	 * @param predicate
	 * The predicate used to test elements in the array.
	 * @return
	 * The index of the first occurrence that fulfils the predicates requirements.
	 * If none is present then {@code INDEX_NOT_FOUND} is returned.
	 * @throws ArgumentNullException
	 * If elements or predicate is null.
	 */
	@Pure
	public static int indexOfMatching(long elements[], LongPredicate predicate) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		Argument.handleNull(predicate, "predicate");
		for (int i = 0; i < elements.length; i++)
		{
			if (predicate.test(elements[i])) return i;
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * Returns the index for the first occurrence
	 * of an element matching the specified predicate.
	 * @param elements
	 * The array to look for the occurrence in.
	 * @param predicate
	 * The predicate used to test elements in the array.
	 * @return
	 * The index of the first occurrence that fulfils the predicates requirements.
	 * If none is present then {@code INDEX_NOT_FOUND} is returned.
	 * @throws ArgumentNullException
	 * If elements or predicate is null.
	 */
	@Pure
	public static int indexOfMatching(double elements[], DoublePredicate predicate) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		Argument.handleNull(predicate, "predicate");
		for (int i = 0; i < elements.length; i++)
		{
			if (predicate.test(elements[i])) return i;
		}
		return INDEX_NOT_FOUND;
	}

	// -------------------------------------------- //
	// CONTAINS (EQUALITY)
	// -------------------------------------------- //

	/**
	 * Tests whether or not the specified array has a null element.
	 * @param elements
	 * The array to look for null in.
	 * @return
	 * True if and only if the specified array contains the specified element.
	 * False otherwise.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static boolean containsNull(Object[] elements) throws ArgumentNullException
	{
		return indexOfNull(elements) != INDEX_NOT_FOUND;
	}

	/**
	 * Tests whether or not the specified element occurs
	 * in the specified array.
	 * @param elements
	 * The array to look for occurrences in.
	 * @param element
	 * The element to check look for.
	 * @return
	 * True if and only if the specified array contains the specified element.
	 * False otherwise.
	 * @throws ArgumentNullException
	 * If elements or element is null.
	 */
	@Pure
	public static boolean containsElement(Object[] elements, Object element) throws ArgumentNullException
	{
		return indexOfElement(elements, element) != INDEX_NOT_FOUND;
	}

	/**
	 * Tests whether or not the specified element occurs
	 * in the specified array.
	 * @param elements
	 * The array to look for occurrences in.
	 * @param element
	 * The element to check look for.
	 * @return
	 * True if and only if the specified array contains the specified element.
	 * False otherwise.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static boolean containsElement(boolean[] elements, boolean element) throws ArgumentNullException
	{
		return indexOfElement(elements, element) != INDEX_NOT_FOUND;
	}

	/**
	 * Tests whether or not the specified element occurs
	 * in the specified array.
	 * @param elements
	 * The array to look for occurrences in.
	 * @param element
	 * The element to check look for.
	 * @return
	 * True if and only if the specified array contains the specified element.
	 * False otherwise.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static boolean containsElement(byte[] elements, byte element) throws ArgumentNullException
	{
		return indexOfElement(elements, element) != INDEX_NOT_FOUND;
	}

	/**
	 * Tests whether or not the specified element occurs
	 * in the specified array.
	 * @param elements
	 * The array to look for occurrences in.
	 * @param element
	 * The element to check look for.
	 * @return
	 * True if and only if the specified array contains the specified element.
	 * False otherwise.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static boolean containsElement(char[] elements, char element) throws ArgumentNullException
	{
		return indexOfElement(elements, element) != INDEX_NOT_FOUND;
	}

	/**
	 * Tests whether or not the specified element occurs
	 * in the specified array.
	 * @param elements
	 * The array to look for occurrences in.
	 * @param element
	 * The element to check look for.
	 * @return
	 * True if and only if the specified array contains the specified element.
	 * False otherwise.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static boolean containsElement(short[] elements, short element) throws ArgumentNullException
	{
		return indexOfElement(elements, element) != INDEX_NOT_FOUND;
	}

	/**
	 * Tests whether or not the specified element occurs
	 * in the specified array.
	 * @param elements
	 * The array to look for occurrences in.
	 * @param element
	 * The element to check look for.
	 * @return
	 * True if and only if the specified array contains the specified element.
	 * False otherwise.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static boolean containsElement(int[] elements, int element) throws ArgumentNullException
	{
		return indexOfElement(elements, element) != INDEX_NOT_FOUND;
	}

	/**
	 * Tests whether or not the specified element occurs
	 * in the specified array.
	 * @param elements
	 * The array to look for occurrences in.
	 * @param element
	 * The element to check look for.
	 * @return
	 * True if and only if the specified array contains the specified element.
	 * False otherwise.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static boolean containsElement(long[] elements, long element) throws ArgumentNullException
	{
		return indexOfElement(elements, element) != INDEX_NOT_FOUND;
	}

	/**
	 * Tests whether or not the specified element occurs
	 * in the specified array.
	 * @param elements
	 * The array to look for occurrences in.
	 * @param element
	 * The element to check look for.
	 * @return
	 * True if and only if the specified array contains the specified element.
	 * False otherwise.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static boolean containsElement(float[] elements, float element) throws ArgumentNullException
	{
		return indexOfElement(elements, element) != INDEX_NOT_FOUND;
	}

	/**
	 * Tests whether or not the specified element occurs
	 * in the specified array.
	 * @param elements
	 * The array to look for occurrences in.
	 * @param element
	 * The element to check look for.
	 * @return
	 * True if and only if the specified array contains the specified element.
	 * False otherwise.
	 * @throws ArgumentNullException
	 * If elements is null.
	 */
	@Pure
	public static boolean containsElement(double[] elements, double element) throws ArgumentNullException
	{
		return indexOfElement(elements, element) != INDEX_NOT_FOUND;
	}

	// -------------------------------------------- //
	// CONTAINS (PREDICATE)
	// -------------------------------------------- //

	/**
	 * Checks if an element in the specified array
	 * matches the specified predicate.
	 * @param elements
	 * The array to look for matches in.
	 * @param predicate
	 * The predicate used to test the elements.
	 * @param <T>
	 * Type of the elements in the array.
	 * @return
	 * True if and only if, the specified array contains an
	 * element matching the specified predicate. False otherwise.
	 * @throws ArgumentNullException
	 * If elements or predicate is null.
	 */
	@Pure
	public static <T> boolean containsMatching(T[] elements, Predicate<? super T> predicate) throws ArgumentNullException
	{
		return indexOfMatching(elements, predicate) != INDEX_NOT_FOUND;
	}

	/**
	 * Checks if an element in the specified array
	 * matches the specified predicate.
	 * @param elements
	 * The array to look for matches in.
	 * @param predicate
	 * The predicate used to test the elements.
	 * @return
	 * True if and only if, the specified array contains an
	 * element matching the specified predicate. False otherwise.
	 * @throws ArgumentNullException
	 * If elements or predicate is null.
	 */
	@Pure
	public static boolean containsMatching(int[] elements, IntPredicate predicate) throws ArgumentNullException
	{
		return indexOfMatching(elements, predicate) != INDEX_NOT_FOUND;
	}

	/**
	 * Checks if an element in the specified array
	 * matches the specified predicate.
	 * @param elements
	 * The array to look for matches in.
	 * @param predicate
	 * The predicate used to test the elements.
	 * @return
	 * True if and only if, the specified array contains an
	 * element matching the specified predicate. False otherwise.
	 * @throws ArgumentNullException
	 * If elements or predicate is null.
	 */
	@Pure
	public static boolean containsMatching(double[] elements, DoublePredicate predicate) throws ArgumentNullException
	{
		return indexOfMatching(elements, predicate) != INDEX_NOT_FOUND;
	}

	/**
	 * Checks if an element in the specified array
	 * matches the specified predicate.
	 * @param elements
	 * The array to look for matches in.
	 * @param predicate
	 * The predicate used to test the elements.
	 * @return
	 * True if and only if, the specified array contains an
	 * element matching the specified predicate. False otherwise.
	 * @throws ArgumentNullException
	 * If elements or predicate is null.
	 */
	@Pure
	public static boolean containsMatching(long[] elements, LongPredicate predicate) throws ArgumentNullException
	{
		return indexOfMatching(elements, predicate) != INDEX_NOT_FOUND;
	}

	// -------------------------------------------- //
	// FIRST MATCHING (PREDICATE)
	// -------------------------------------------- //

	/**
	 * Gets the first element in the specified array
	 * matching the specified predicate.
	 * @param elements
	 * The elements to look for a match in.
	 * @param predicate
	 * The predicate used to test
	 * @param <T>
	 * Type of the elements in the array.
	 * @return
	 * An optional consisting of the first occurrence
	 * in the array which matches the predicate.
	 * If no element matches the predicate an empty
	 * optional is returned.
	 * @throws ArgumentNullException
	 * If elements or predicate is null.
	 * @throws NullPointerException
	 * If the matched element is null.
	 */
	@Pure
	public static <T> Optional<T> firstMatching(T[] elements, Predicate<? super T> predicate) throws ArgumentNullException, NullPointerException
	{
		int idx = indexOfMatching(elements, predicate);
		if (idx == INDEX_NOT_FOUND) return Optional.empty();
		return Optional.of(elements[idx]);
	}

	/**
	 * Gets the first element in the specified array
	 * matching the specified predicate.
	 * @param elements
	 * The elements to look for a match in.
	 * @param predicate
	 * The predicate used to test
	 * @return
	 * An optional consisting of the first occurrence
	 * in the array which matches the predicate.
	 * If no element matches the predicate an empty
	 * optional is returned.
	 * @throws ArgumentNullException
	 * If elements or predicate is null.
	 */
	@Pure
	public static OptionalInt firstMatching(int[] elements, IntPredicate predicate)
	{
		int idx = indexOfMatching(elements, predicate);
		if (idx == INDEX_NOT_FOUND) return OptionalInt.empty();
		return OptionalInt.of(elements[idx]);
	}

	/**
	 * Gets the first element in the specified array
	 * matching the specified predicate.
	 * @param elements
	 * The elements to look for a match in.
	 * @param predicate
	 * The predicate used to test
	 * @return
	 * An optional consisting of the first occurrence
	 * in the array which matches the predicate.
	 * If no element matches the predicate an empty
	 * optional is returned.
	 * @throws ArgumentNullException
	 * If elements or predicate is null.
	 */
	@Pure
	public static OptionalLong firstMatching(long[] elements, LongPredicate predicate)
	{
		int idx = indexOfMatching(elements, predicate);
		if (idx == INDEX_NOT_FOUND) return OptionalLong.empty();
		return OptionalLong.of(elements[idx]);
	}

	/**
	 * Gets the first element in the specified array
	 * matching the specified predicate.
	 * @param elements
	 * The elements to look for a match in.
	 * @param predicate
	 * The predicate used to test
	 * @return
	 * An optional consisting of the first occurrence
	 * in the array which matches the predicate.
	 * If no element matches the predicate an empty
	 * optional is returned.
	 * @throws ArgumentNullException
	 * If elements or predicate is null.
	 */
	@Pure
	public static OptionalDouble firstMatching(double[] elements, DoublePredicate predicate)
	{
		int idx = indexOfMatching(elements, predicate);
		if (idx == INDEX_NOT_FOUND) return OptionalDouble.empty();
		return OptionalDouble.of(elements[idx]);
	}

	// -------------------------------------------- //
	// FILTER
	// -------------------------------------------- //

	/**
	 * Creates and returns a new array instance
	 * consisting only of the elements present
	 * in the specified array which matches the specified predicate.
	 * The order is exactly the same as in the original array.
	 * @param arr
	 * The array to generate a filtered version of.
	 * @param filter
	 * The predicate used to filter some elements.
	 * @param <T>
	 * Type of the array contents.
	 * @return
	 * An array containing all the elements in the specified array,
	 * which matches the specified predicate.
	 */
	public static <T> T[] filter(T[] arr, Predicate<? super T> filter)
	{
		T[] ret = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
		int idx = 0;
		for (T e : arr)
		{
			if ( ! filter.test(e)) continue;
			ret[idx++] = e;
		}
		return Arrays.copyOfRange(ret, 0, idx);
	}


	/**
	 * Creates and returns a new array instance
	 * consisting only of the elements present
	 * in the specified array which matches the specified predicate.
	 * The order is exactly the same as in the original array.
	 * @param arr
	 * The array to generate a filtered version of.
	 * @param filter
	 * The predicate used to filter some elements.
	 * @return
	 * An array containing all the elements in the specified array,
	 * which matches the specified predicate.
	 */
	public static int[] filter(int[] arr, IntPredicate filter)
	{
		int[] ret = new int[arr.length];
		int idx = 0;
		for (int e : arr)
		{
			if ( ! filter.test(e)) continue;
			ret[idx++] = e;
		}
		return Arrays.copyOfRange(ret, 0, idx);
	}

	/**
	 * Creates and returns a new array instance
	 * consisting only of the elements present
	 * in the specified array which matches the specified predicate.
	 * The order is exactly the same as in the original array.
	 * @param arr
	 * The array to generate a filtered version of.
	 * @param filter
	 * The predicate used to filter some elements.
	 * @return
	 * An array containing all the elements in the specified array,
	 * which matches the specified predicate.
	 */
	public static long[] filter(long[] arr, LongPredicate filter)
	{
		long[] ret = new long[arr.length];
		int idx = 0;
		for (long e : arr)
		{
			if ( ! filter.test(e)) continue;
			ret[idx++] = e;
		}
		return Arrays.copyOfRange(ret, 0, idx);
	}

	/**
	 * Creates and returns a new array instance
	 * consisting only of the elements present
	 * in the specified array which matches the specified predicate.
	 * The order is exactly the same as in the original array.
	 * @param arr
	 * The array to generate a filtered version of.
	 * @param filter
	 * The predicate used to filter some elements.
	 * @return
	 * An array containing all the elements in the specified array,
	 * which matches the specified predicate.
	 */
	public static double[] filter(double[] arr, DoublePredicate filter)
	{
		double[] ret = new double[arr.length];
		int idx = 0;
		for (double e : arr)
		{
			if ( ! filter.test(e)) continue;
			ret[idx++] = e;
		}
		return Arrays.copyOfRange(ret, 0, idx);
	}

}
