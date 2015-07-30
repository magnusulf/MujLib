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

import java.util.*;

/**
 * This is a static utility class for dealing with Collections.
 * It has some easy constructors for some kinds of collections,
 * more specifically, list, set, linkedSet and map.
 *
 * It also has a range list. Which is a list of integers,
 * in a specific range.
 *
 * @author Magnus Ulf Jørgensen
 */
public final class MCollections
{
	// -------------------------------------------- //
	// CONSTRUCTOR (FORBIDDEN)
	// -------------------------------------------- //

	private MCollections()
	{
		throw new AssertionError();
	}

	// -------------------------------------------- //
	// SIMPLE CONSTRUCTORS
	// -------------------------------------------- //

	/**
	 * Creates a new mutable set from the passed varargs.
	 * If an elements is present several times, it will
	 * only be present once in the return value.
	 * If the array is modified, while this method is running
	 * the results are undefined.
	 * If the array is modified afterwards, the set won't be affected.
	 *
	 * @param elements
	 * The array to base this set of.
	 * @param <E>
	 * Type of the varargs, and the type for the set.
	 * @return set
	 * A set only containing the passed elements.
	 * This set is not guaranteed to be linked,
	 * and it's ordering is undefined.
	 * @throws ArgumentNullException
	 * If elements is null.
	 * The contents of elements can be null however.
	 */
	@Pure
	@SafeVarargs
	public static <E> Set<E> set(E... elements) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		Set<E> set = new HashSet<>(elements.length);
		Collections.addAll(set, elements);
		return set;
	}

	/**
	 * Creates a new mutable linked set from the passed varargs.
	 * If an elements is present several times, only
	 * the first occurrence will be present in the returned set.
	 * If the array is modified, while this method is running
	 * the results are undefined.
	 * If the array is modified afterwards, the set won't be affected.
	 *
	 * @param elements
	 * The array to base this set of.
	 * @param <E>
	 * Type of the varargs, and the type for the set.
	 * @return set
	 * A set only containing the passed elements.
	 * This set is linked, and will be ordered,
	 * according to the arrays order.
	 * @throws ArgumentNullException
	 * If elements is null.
	 * The contents of elements can be null however.
	 */
	@Pure
	@SafeVarargs
	public static <E> Set<E> linkedSet(E... elements) throws ArgumentNullException
	{
		Argument.handleNull(elements, "elements");
		Set<E> set = new LinkedHashSet<>(elements.length);
		Collections.addAll(set, elements);
		return set;
	}

	/**
	 * Creates a new list from the passed varargs.
	 * This method is almost identical to Arrays.asList
	 * however this provides full mutability.
	 * If mutability is not required Arrays.asList is suggested insted of this.
	 * If the array is modified, while this method is running
	 * the results are undefined.
	 * If the array is modified afterwards, the list won't be affected.
	 *
	 * @param elements
	 * The array to base this set of.
	 * @param <E>
	 * Type of the varargs, and the type for the set.
	 * @return list
	 * A list only containing the passed elements,
	 * in the order they were passed.
	 * @throws ArgumentNullException
	 * If elements is null.
	 * The contents of elements can be null however.
	 */
	@Pure
	@SafeVarargs
	public static <E> List<E> list(E... elements)
	{
		Argument.handleNull(elements, "elements");
		List<E> list = new ArrayList<>(elements.length);
		Collections.addAll(list, elements);
		return list;
	}

	/**
	 * Creates a map from the passed varargs.
	 * The passed classes, are provided, so that
	 * this method can fail fast, on arguments of the wrong type.
	 * It will be mapped as following.
	 * Element 1: Key value for element 2
	 * Element 2: The value, which has element 1 as key.
	 * Element 3: Key value for element 4
	 * Element 4: The value, which has element 3 as key.
	 * Element 5: Key value for element 6
	 * Element 6: The value, which has element 5 as key.
	 * etc
	 * @param keyClass
	 * The class for the keys.
	 * @param valueClass
	 * The class fot the values.
	 * @param objects
	 * All elements passed, these are used to fill the map.
	 * @param <K>
	 * Type of the keys.
	 * All elements with an even index,
	 * must be able to be casted to this type.
	 * @param <V>
	 * Type of the values.
	 * All elements with an uneven index,
	 * must be able to be casted to this type.
	 * @return
	 * A map containing all the passed objects in the varargs.
	 * @throws ArgumentNullException
	 * If keyClass or valueClass is null.
	 * @throws IllegalArgumentException
	 * If an uneven number of objects is passed.
	 * All keys must have a corresponding value.
	 * @throws ClassCastException
	 * If an object cannot be cast to it's target type.
	 * The target type can either be K or V,
	 * depending on its index.
	 */
	@Pure
	@SafeVarargs
	public static <K, V> Map<K, V> map(Class<K> keyClass, Class<V> valueClass, Object... objects)
			throws ArgumentNullException, ClassCastException, IllegalArgumentException
	{
		Argument.handleNull(keyClass, "keyClass");
		Argument.handleNull(valueClass, "valueClass");
		Map<K, V> ret = new HashMap<>();

		if (objects.length % 2 != 0)
		{
			throw new IllegalArgumentException("An uneven number of objects was passed.");
		}

		for (int i = 0; i < objects.length;)
		{
			Object one = objects[i++];
			Object two = objects[i++];

			K key = keyClass.cast(one);
			V value = valueClass.cast(two);

			ret.put(key, value);
		}

		return ret;
	}

	// -------------------------------------------- //
	// RANGE LIST
	// -------------------------------------------- //

	/**
	 * Returns an immutable, thread safe, range of integers.
	 * This is a list, of all integers from one integer,
	 * to another.
	 * This does not cache any results, and is only based
	 * on very few fields. So creation is very cheap,
	 * and there is almost no memory overhead on usage.
	 * This list can both be ascending or descending.
	 *
	 * The ordering of this list is as you would expect.
	 * 1, 2, 3, 4, 5, 6, 7, 8 etc
	 * Or for a descending list
	 * -1, -2, -3, -4 etc
	 *
	 * {@code MCollections.range(0, 10) } will yield a list equal to
	 * {@code List<Integer> list = new ArrayList;
	 * for (int = 0; i < 10; i++) list.add(i);}
	 *
	 * @param start
	 * The integer which the list should start at. (inclusive)
	 * @param end
	 * The integer which the list should end at. (exclusive)
	 * @return
	 * An ordered list containing all integers from start to end.
	 */
	@Pure
	public static List<Integer> range(int start, int end)
	{
		// TODO: Implement our own iterators.
		// We can make those much more efficient that the built in ones.

		if (start == end) return Collections.emptyList();
		else if (start == end + 1) return Collections.singletonList(start);
		else if (end > start) return new RangeListAscending(start, end);
		else return new RangeListDescending(start, end);
	}

	/**
	 * This is a convenience method,
	 * which is equal to MCollections.range(0, size);
	 * @param size
	 * Size of the list
	 * @return
	 * The returned list.
	 */
	@Pure
	public static List<Integer> range(int size)
	{
		return range(0, size);
	}

	private static abstract class AbstractRangeList extends AbstractList<Integer> implements RandomAccess
	{

		// -------------------------------------------- //
		// CONVENIENCE
		// -------------------------------------------- //

		public void rangeCheck(int index, String name)
		{
			if (index < 0 || index > this.size())
			{
				throw new IllegalArgumentException(String.format("%s: %d size: %d", name, index, this.size()));
			}
		}

		public void rangeCheck(int index)
		{
			rangeCheck(index, "index");
		}

		// -------------------------------------------- //
		// OVERRIDE: DEFAULT
		// -------------------------------------------- //

		@Override
		public int lastIndexOf(Object o)
		{
			return this.indexOf(o);
		}

		@Override
		public boolean contains(Object o)
		{
			return this.indexOf(o) != -1;
		}

		// -------------------------------------------- //
		// OVERRIDE: MODIFY
		// -------------------------------------------- //

		public static final String CANNOT_BE_MODIFIED_MESSAGE = "This RangeListAscending cannot be modified.";

		@Override public boolean add(Integer e) { throw new UnsupportedOperationException(CANNOT_BE_MODIFIED_MESSAGE); }
		@Override public boolean remove(Object o) { throw new UnsupportedOperationException(CANNOT_BE_MODIFIED_MESSAGE); }
		@Override public boolean containsAll(Collection<?> c) { throw new UnsupportedOperationException(CANNOT_BE_MODIFIED_MESSAGE); }
		@Override public boolean addAll(Collection<? extends Integer> c) { throw new UnsupportedOperationException(CANNOT_BE_MODIFIED_MESSAGE); }
		@Override public boolean addAll(int index, Collection<? extends Integer> c) { throw new UnsupportedOperationException(CANNOT_BE_MODIFIED_MESSAGE); }
		@Override public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(CANNOT_BE_MODIFIED_MESSAGE); }
		@Override public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(CANNOT_BE_MODIFIED_MESSAGE); }
		@Override public void clear() { throw new UnsupportedOperationException(CANNOT_BE_MODIFIED_MESSAGE); }
		@Override public Integer set(int index, Integer element) { throw new UnsupportedOperationException(CANNOT_BE_MODIFIED_MESSAGE); }
		@Override public void add(int index, Integer element) { throw new UnsupportedOperationException(CANNOT_BE_MODIFIED_MESSAGE); }
		@Override public Integer remove(int index) { throw new UnsupportedOperationException(CANNOT_BE_MODIFIED_MESSAGE); }

	}

	private static class RangeListAscending extends AbstractRangeList
	{
		// -------------------------------------------- //
		// FIELDS
		// -------------------------------------------- //

		private final int start;
		private final int end;

		// -------------------------------------------- //
		// CONSTRUCT
		// -------------------------------------------- //

		public RangeListAscending(int start, int end)
		{
			assert start < end;
			this.start = start;
			this.end = end;
		}

		// -------------------------------------------- //
		// OVERRIDE: DEFAULT
		// -------------------------------------------- //

		@Override
		public int size()
		{
			return end - start;
		}

		@Override
		public Integer get(int index)
		{
			this.rangeCheck(index);
			return start + index;
		}

		@Override
		public int indexOf(Object o)
		{
			if (!(o instanceof Integer)) return -1;
			int i = (Integer) o;

			if (i >= this.end || i < this.start) return -1;

			return i - this.start;
		}

		// -------------------------------------------- //
		// OVERRIDE: SUB LIST
		// -------------------------------------------- //

		@Override
		public List<Integer> subList(int fromIndex, int toIndex)
		{
			this.rangeCheck(fromIndex, "fromIndex");
			this.rangeCheck(toIndex, "toIndex");
			if (fromIndex > toIndex) throw new IllegalArgumentException("toIndex: " + toIndex + " fromIndex: " + fromIndex);

			return new RangeListAscending(start + fromIndex, start + toIndex);
		}

	}


	private static class RangeListDescending extends AbstractRangeList
	{
		// -------------------------------------------- //
		// FIELDS
		// -------------------------------------------- //

		private final int start;
		private final int end;

		// -------------------------------------------- //
		// CONSTRUCT
		// -------------------------------------------- //

		public RangeListDescending(int start, int end)
		{
			assert start > end;
			this.start = start;
			this.end = end;
		}

		// -------------------------------------------- //
		// OVERRIDE: DEFAULT
		// -------------------------------------------- //

		@Override
		public int size()
		{
			return start - end;
		}

		@Override
		public Integer get(int index)
		{
			this.rangeCheck(index);
			return start - index;
		}

		@Override
		public int indexOf(Object o)
		{
			if (!(o instanceof Integer)) return -1;
			int i = (Integer) o;

			if (i <= this.end || i > this.start) return -1;

			return this.start - i;
		}

		// -------------------------------------------- //
		// OVERRIDE: SUB LIST
		// -------------------------------------------- //

		@Override
		public List<Integer> subList(int fromIndex, int toIndex)
		{
			this.rangeCheck(fromIndex, "fromIndex");
			this.rangeCheck(toIndex, "toIndex");
			if (fromIndex > toIndex) throw new IllegalArgumentException("toIndex: " + toIndex + " fromIndex: " + fromIndex);

			return new RangeListDescending(start - fromIndex, start - toIndex);
		}

	}

}
