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

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class MCollectionsTest
{
	private static final Integer[] arr = {1, 2, 3, 43, 2456, 2645};


	@Test
	public void testSet() throws Exception
	{
		Set<Integer> set = MCollections.set(arr);

		// Size
		assertEquals(arr.length, set.size());

		// Contains
		for (Integer e : arr)
		{
			assertTrue(set.contains(e));
		}
		assertTrue(set.containsAll(MCollections.list(arr)));

		// Equals
		assertTrue(set.equals(MCollections.set(arr)));

		// Empty
		assertTrue(MCollections.set().isEmpty());

		// Ensure no UnsupportedOperations is thrown.
		assertTrue(set.add(54));

		assertTrue(set.remove(1));

	}

	@Test
	public void testLinkedSet() throws Exception
	{
		Set<Integer> set = MCollections.linkedSet(arr);

		// Size
		assertEquals(arr.length, set.size());

		// Contains
		for (Integer e : arr)
		{
			assertTrue(set.contains(e));
		}
		assertTrue(set.containsAll(MCollections.list(arr)));

		// Order
		int i = 0;
		for (Integer e : set)
		{
			assertEquals(set.toString(), e, arr[i]);
			i++;
		}

		// Equals
		assertTrue(set.equals(MCollections.set(arr)));

		// Empty
		assertTrue(MCollections.set().isEmpty());

		// Ensure no UnsupportedOperations is thrown.
		assertTrue(set.add(54));

		assertTrue(set.remove(1));
	}

	@Test
	public void testList() throws Exception
	{
		List<Integer> list = MCollections.list(arr);

		// Size
		assertEquals(arr.length, list.size());

		// Contains
		for (Integer e : arr)
		{
			assertTrue(list.contains(e));
		}
		assertTrue(list.containsAll(MCollections.list(arr)));

		// Order
		int i = 0;
		for (Integer e : list)
		{
			assertEquals(list.toString(), e, arr[i]);
			i++;
		}

		// Equals
		assertTrue(list.equals(Arrays.asList(arr)));

		// Empty
		assertTrue(MCollections.set().isEmpty());

		// Ensure no UnsupportedOperations is thrown.
		assertTrue(list.add(54));

		assertTrue(list.remove(Integer.valueOf(1)));
	}

	@Test
	public void testMap() throws Exception
	{
		Map<String, String> map = MCollections.map(String.class, String.class,
			"key1", "value1",
			"key2", "value2",
			"key3", "value3",
			"key4", "value4",
			"key5", "value5",
			"key6", "value6"
		);
		assertEquals("value1", map.get("key1"));
		assertEquals("value2", map.get("key2"));
		assertEquals("value3", map.get("key3"));
		assertEquals("value4", map.get("key4"));
		assertEquals("value5", map.get("key5"));
		assertEquals("value6", map.get("key6"));
		assertEquals(6, map.size());

		assertNull(map.put("key7", "value7"));
		assertEquals(7, map.size());

		assertEquals("value5", map.remove("key5"));
	}

	@Test
	public void testRangeAscending() throws Exception
	{
		List<Integer> list = MCollections.range(10);
		assertEquals(10, list.size());

		// Ensure contains, and stuff
		for (int i = 0; i < 10; i++)
		{
			assertEquals(i, list.get(i).intValue());
			assertEquals(i, list.indexOf(i));
			assertEquals(i, list.lastIndexOf(i));
			assertTrue(list.contains(i));
		}

		// Ensure doesn't contain
		for (int i = 10; i < 20; i++)
		{
			assertEquals(-1, list.indexOf(i));
			assertEquals(-1, list.lastIndexOf(i));
			assertFalse(list.contains(i));
		}

		// Ensure doesn't contain
		for (int i = -10; i < 0; i++)
		{
			assertEquals(-1, list.indexOf(i));
			assertEquals(-1, list.lastIndexOf(i));
			assertFalse(list.contains(i));
		}

		// Test sublist
		List<Integer> subList = list.subList(2, 5);
		assertEquals(3, subList.size());

		// Get index
		assertEquals(2, subList.get(0).intValue());
		assertEquals(3, subList.get(1).intValue());
		assertEquals(4, subList.get(2).intValue());

		// Index of
		assertEquals(0, subList.indexOf(2));
		assertEquals(1, subList.indexOf(3));
		assertEquals(2, subList.indexOf(4));
		assertEquals(-1, subList.indexOf(1));
		assertEquals(-1, subList.indexOf(0));
		assertEquals(-1, subList.indexOf(5));

		// Last index of
		assertEquals(0, subList.lastIndexOf(2));
		assertEquals(1, subList.lastIndexOf(3));
		assertEquals(2, subList.lastIndexOf(4));
		assertEquals(-1, subList.lastIndexOf(1));
		assertEquals(- 1, subList.lastIndexOf(0));
		assertEquals(- 1, subList.lastIndexOf(5));

		// Contains
		assertTrue(subList.contains(2));
		assertTrue(subList.contains(3));
		assertTrue(subList.contains(4));
		assertFalse(subList.contains(1));
		assertFalse(subList.contains(0));
		assertFalse(subList.contains(5));
	}

	@Test
	public void testRangeDescending() throws Exception
	{
		List<Integer> list = MCollections.range(10, 0);
		assertEquals(10, list.size());

		// Ensure contains, and stuff
		for (int i = 10, j = 0; i > 0; i--, j++)
		{
			assertEquals(i, list.get(j).intValue());
			assertEquals(j, list.indexOf(i));
			assertEquals(j, list.lastIndexOf(i));
			assertTrue(list.contains(i));
		}

		// Ensure doesn't contain
		for (int i = 11; i < 20; i++)
		{
			assertEquals(-1, list.indexOf(i));
			assertEquals(-1, list.lastIndexOf(i));
			assertFalse(list.contains(i));
		}

		// Ensure doesn't contain
		for (int i = -10; i < 0; i++)
		{
			assertEquals(-1, list.indexOf(i));
			assertEquals(-1, list.lastIndexOf(i));
			assertFalse(list.contains(i));
		}

		// Test sublist
		List<Integer> subList = list.subList(2, 5);
		assertEquals(3, subList.size());

		// Get index
		assertEquals(8, subList.get(0).intValue());
		assertEquals(7, subList.get(1).intValue());
		assertEquals(6, subList.get(2).intValue());

		// Index of
		assertEquals(0, subList.indexOf(8));
		assertEquals(1, subList.indexOf(7));
		assertEquals(2, subList.indexOf(6));
		assertEquals(-1, subList.indexOf(9));
		assertEquals(-1, subList.indexOf(10));
		assertEquals(-1, subList.indexOf(5));

		// Last index of
		assertEquals(0, subList.lastIndexOf(8));
		assertEquals(1, subList.lastIndexOf(7));
		assertEquals(2, subList.lastIndexOf(6));
		assertEquals(-1, subList.lastIndexOf(9));
		assertEquals(-1, subList.lastIndexOf(10));
		assertEquals(-1, subList.lastIndexOf(5));

		// Contains
		assertTrue(subList.contains(8));
		assertTrue(subList.contains(7));
		assertTrue(subList.contains(6));
		assertFalse(subList.contains(9));
		assertFalse(subList.contains(10));
		assertFalse(subList.contains(5));

	}

}
