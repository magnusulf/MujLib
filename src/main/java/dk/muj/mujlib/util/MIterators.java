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
import dk.muj.mujlib.hash.HashUtil;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class is a static utility class
 * which has some different iterator implementations.
 * The implementations is hidden in here,
 * so they can be freely changed by the authors.
 *
 * @author Magnus Ulf Jørgensen
 */
public final class MIterators
{
	// -------------------------------------------- //
	// CONSTRUCTOR (FORBIDDEN)
	// -------------------------------------------- //

	private MIterators()
	{
		throw new AssertionError();
	}

	// -------------------------------------------- //
	// ARRAY ITERATOR
	// -------------------------------------------- //

	/**
	 * Creates a {@code ListIterator} that iterates over an array.
	 * It's starts between index 0 and -1, so a call to nextIndex()
	 * just after creation would yield {@code 0}
	 *
	 * The only optional operation this iterator implements,
	 * is set() which will affect the underlying array.
	 *
	 * The iterator presents the elements in the order
	 * they are in the array.
	 *
	 * Any modification to the array, externally, or through
	 * this iterators set() method. Will affect the array
	 * and the elements presented by this iterator.
	 * If that behaviour is not preferred. Do {@code MIterators.forArray(array.copy())}
	 *
	 * @param array
	 * The array which the iterator is based on.
	 * @param <T>
	 * The type for the iterator, and the type for array elements.
	 * @return
	 * A ListIterator which is based on the passed array.
	 * @throws ArgumentNullException
	 * If array is null.
	 */
	@Pure
	public static <T> ListIterator<T> forArray(T[] array)
	{
		Argument.handleNull(array, "array");
		return new ArrayIterator<>(array);
	}

	/**
	 * Creates a {@code ListIterator} that iterates over an array.
	 * It's starts at the specified startIndex, so a call to
	 * nextIndex just after creation would yield {@code startIndex}
	 *
	 * The only optional operation this iterator implements,
	 * is set() which will affect the underlying array.
	 *
	 * The iterator presents the elements in the order
	 * they are in the array.
	 *
	 * Any modification to the array, externally, or through
	 * this iterators set() method. Will affect the array
	 * and the elements presented by this iterator.
	 * If that behaviour is not preferred. Do {@code MIterators.forArray(array.copy())}
	 *
	 * @param startIndex
	 * The index which the iterator should start at.
	 * So a call to nextIndex() just after creation,
	 * will yield {@code startIndex}
	 * @param array
	 * The array which the iterator is based on.
	 * @param <T>
	 * The type for the iterator, and the type for array elements.
	 * @return
	 * A ListIterator which is based on the passed array.
	 * @throws ArgumentNullException
	 * If array is null.
	 */
	@Pure
	public static <T> ListIterator<T> forArray(int startIndex, T[] array) throws ArgumentNullException
	{
		Argument.handleNull(array, "array");
		return new ArrayIterator<>(startIndex, array);
	}

	private static class ArrayIterator<T> implements ListIterator<T>
	{
		// -------------------------------------------- //
		// FIELDS
		// -------------------------------------------- //

		private final T[] array;

		private int nextIndex = 0;
		private int currentIndex = -1;

		// -------------------------------------------- //
		// CONSTRUCT
		// -------------------------------------------- //

		public ArrayIterator(T[] array)
		{
			this.array = array;
		}

		public ArrayIterator(int index, T[] array)
		{
			this.nextIndex = index;
			this.array = array;
		}

		// -------------------------------------------- //
		// OVERRIDE
		// -------------------------------------------- //

		@Override
		public boolean hasNext()
		{
			return this.array.length > nextIndex;
		}

		@Override
		public boolean hasPrevious()
		{
			return 0 < nextIndex;
		}

		@Override
		public T next()
		{
			if ( ! this.hasNext()) throw new NoSuchElementException("No next element in iterator.");
			this.currentIndex = nextIndex++;
			return array[currentIndex];
		}

		@Override
		public T previous()
		{
			if ( ! this.hasPrevious()) throw new NoSuchElementException("No previous element in iterator.");
			this.currentIndex = --nextIndex;
			return array[currentIndex];
		}

		@Override
		public int nextIndex()
		{
			return this.nextIndex;
		}

		@Override
		public int previousIndex()
		{
			return this.nextIndex - 1;
		}

		@Override
		public void set(T e)
		{
			if (currentIndex == -1) throw new IllegalStateException("No current item in iterator.");

			this.array[currentIndex] = e;
		}

		@Override
		public void add(T e)
		{
			throw new UnsupportedOperationException("Array size can't be changed.");
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException("Array size can't be changed.");
		}

		// -------------------------------------------- //
		// EQUALS AND HASHCODE (FOR TESTING)
		// -------------------------------------------- //

		@Override
		public boolean equals(Object o)
		{
			if (!(o instanceof ArrayIterator)) return false;

			ArrayIterator<?> that = (ArrayIterator<?>) o;

			if ( ! Mujtil.equals(this.nextIndex, that.nextIndex)) return false;
			if ( ! Mujtil.equals(this.currentIndex, that.currentIndex)) return false;
			if ( ! Mujtil.equals(this.array, that.array)) return false;

			return true;
		}

		@Override
		public int hashCode()
		{
			int result = HashUtil.HASHCODE_START;
			result = HashUtil.resultAddField(result, nextIndex);
			result = HashUtil.resultAddField(result, currentIndex);
			result = HashUtil.resultAddField(result, array);
			return result;
		}
	}

}
