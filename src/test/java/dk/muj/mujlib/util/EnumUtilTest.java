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

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class EnumUtilTest extends TestCase
{
	enum Day
	{
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY,
		SUNDAY,
		;
	}


	@Test
	public void testGetEnumValues() throws Exception
	{
		assertArrayEquals(TimeUnit.values(), EnumUtil.getEnumValues(TimeUnit.class));
		assertArrayEquals(Day.values(), EnumUtil.getEnumValues(Day.class));
	}

	@Test
	public void testGetEnumValueByName() throws Exception
	{
		assertEquals(Day.MONDAY, EnumUtil.getEnumValueByName(Day.class, "MONDAY").get());
		assertEquals(Day.MONDAY, EnumUtil.getEnumValueByName(Day.class, "monday").get());
		assertEquals(Day.MONDAY, EnumUtil.getEnumValueByName(Day.class, "Monday").get());
		assertEquals(Day.MONDAY, EnumUtil.getEnumValueByName(Day.class, "mOndaY").get());

		assertEquals(Day.WEDNESDAY, EnumUtil.getEnumValueByName(Day.class, "WEDNESDAY").get());
		assertEquals(Day.WEDNESDAY, EnumUtil.getEnumValueByName(Day.class, "wednesday").get());
		assertEquals(Day.WEDNESDAY, EnumUtil.getEnumValueByName(Day.class, "Wednesday").get());
		assertEquals(Day.WEDNESDAY, EnumUtil.getEnumValueByName(Day.class, "WedNesdAy").get());

		assertEquals(Day.SUNDAY, EnumUtil.getEnumValueByName(Day.class, "SUNDAY").get());
		assertEquals(Day.SUNDAY, EnumUtil.getEnumValueByName(Day.class, "sunday").get());
		assertEquals(Day.SUNDAY, EnumUtil.getEnumValueByName(Day.class, "Sunday").get());
		assertEquals(Day.SUNDAY, EnumUtil.getEnumValueByName(Day.class, "SUNday").get());

		assertFalse(EnumUtil.getEnumValueByName(Day.class, "Sundays").isPresent());
	}

	@Test
	public void testGetEnumValueMatching() throws Exception
	{
		assertEquals(Day.MONDAY, EnumUtil.getEnumValueMatching(Day.class, e -> e.ordinal() == 0).get());
		assertEquals(Day.SATURDAY, EnumUtil.getEnumValueMatching(Day.class, e -> e.name().startsWith("S")).get());
	}

}
