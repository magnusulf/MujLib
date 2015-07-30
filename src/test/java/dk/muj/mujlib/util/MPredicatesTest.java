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

import java.util.function.Predicate;

public class MPredicatesTest extends TestCase
{
	
	@Test
	public void testGetStartsWithIgnoreCase() throws Exception
	{
		// This predicate has assertions making it self testing.
		Predicate<String> predicate = MPredicates.getStartsWithIgnoreCase("abc");

		predicate.test("abc");
		predicate.test("ABC");
		predicate.test("acb");
		predicate.test("cba");
		predicate.test("abcreg");
		predicate.test("ABCNBIHBU");
		predicate.test(" abc");
		predicate.test("ab");
		predicate.test("a");
		predicate.test("ba");


	}

	@Test
	public void testGetEqualsIgnoreCase() throws Exception
	{
		// This predicate has assertions making it self testing.
		Predicate<String> predicate = MPredicates.getEqualsIgnoreCase("abc");

		predicate.test("abc");
		predicate.test("ABC");
		predicate.test("acb");
		predicate.test("cba");
		predicate.test("abcreg");
		predicate.test("ABCNBIHBU");
		predicate.test(" abc");
		predicate.test("ab");
		predicate.test("a");
		predicate.test("ba");
	}

	@Test
	public void testGetEnumNameMatches() throws Exception
	{
		Predicate<Enum<?>> predicate = MPredicates.getEnumNameMatches(s -> s.equalsIgnoreCase("monday"));
		for (EnumUtilTest.Day day : EnumUtilTest.Day.values())
		{
			assertEquals(day.name().equalsIgnoreCase("monday"), predicate.test(day));
		}
	}

	// There is some more methods, related to enum names.
	// But those are just combining the above methods.
	// So tests is not really necessary.

}
