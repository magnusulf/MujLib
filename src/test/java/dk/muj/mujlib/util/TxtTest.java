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

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TxtTest extends TestCase
{
	
	@Test
	public void testRepeat() throws Exception
	{
		assertEquals("aaa", Txt.repeat("a", 3));
		assertEquals("abcabcabc", Txt.repeat("abc", 3));
		assertEquals("i", Txt.repeat("i", 1));
	}

	@Test
	public void testRepeatChar() throws Exception
	{
		assertEquals("aaa", Txt.repeat('a', 3));
		assertEquals("gggg", Txt.repeat('g', 4));
		assertEquals("i", Txt.repeat('i', 1));
	}

	@Test
	public void testLetterString() throws Exception
	{
		// TODO: This method is really complex,
		// and its output is not well defined.
	}

	private static final char[] VOWELS =
			{
					'a', 'e', 'i', 'o', 'u', 'y', 'æ', 'ø', 'å', 'ä', 'ö', 'ü',
					'A', 'E', 'I', 'O', 'U', 'Y', 'Æ', 'Ø', 'Å', 'Ä', 'Ö', 'Ü'
			};

	@Test
	public void testIsVowel() throws Exception
	{
		for (char c = 0; c < Character.MAX_VALUE; c++)
		{
			if (ArrayUtil.containsElement(VOWELS, c)) assertTrue(Txt.isVowel(c));
			else assertFalse(Txt.isVowel(c));
		}
	}

	@Test
	public void testAan() throws Exception
	{
		assertEquals("an", Txt.aan("agf"));
		assertEquals("an", Txt.aan("ægf"));
		assertEquals("an", Txt.aan("Ögf"));
		assertEquals("an", Txt.aan("ügf"));
		assertEquals("a", Txt.aan(".gdsf"));
		assertEquals("a", Txt.aan("dsgf"));
		assertEquals("a", Txt.aan("!jknredf"));
		assertEquals("a", Txt.aan("gdn"));
	}

}
