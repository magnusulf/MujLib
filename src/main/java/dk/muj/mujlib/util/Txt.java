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

import java.util.regex.Pattern;

/**
 * This class has some different utilities
 * for dealing with strings.
 *
 * @author Magnus Ulf Jørgensen
 */
public final class Txt
{
	// -------------------------------------------- //
	// CONSTRUCTOR (FORBIDDEN)
	// -------------------------------------------- //

	private Txt()
	{
		throw new AssertionError();
	}

	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //

	public static final Pattern REGEX_WHITESPACE = Pattern.compile("\\s+");

	private static final char[] VOWELS =
	{
		'a', 'e', 'i', 'o', 'u', 'y', 'æ', 'ø', 'å', 'ä', 'ö', 'ü',
		'A', 'E', 'I', 'O', 'U', 'Y', 'Æ', 'Ø', 'Å', 'Ä', 'Ö', 'Ü'
	};

	private static final String[] DIGIT_TO_STRING = {null, "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

	private static final String[] ELEVEN_THROUGH_NINETEEN = {"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

	private static final String[] TENS_TO_STRING = {null, "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

	private static final String[] POWER_OF_TEN_TO_STRING =
			{
					null,
					"ten",
					"hundred",
					"thousand",
					null,
					null,
					"million",
					null,
					null,
					"billion",
					null,
					null,
					"trillion",
					null,
					null,
					"quadrillion",
					null,
					null
			};

	// -------------------------------------------- //
	// REPEAT
	// -------------------------------------------- //

	/**
	 * Creates a string equal to the passed sequence,
	 * concatenated onto itself X times.
	 * @param cs
	 * The charsequence to repeat.
	 * @param times
	 * The amount of times to repeat the charsequence.
	 * @return str
	 * The charsequence repeated after itself.
	 * @throws ArgumentNullException
	 * if the charsequence is null,
	 * @throws IllegalArgumentException
	 * if the times is less than or equal to 0
	 */
	@Pure
	public static String repeat(final CharSequence cs, final int times) throws ArgumentNullException, IllegalArgumentException
	{
		Argument.handleNull(cs, "cs");
		if (times <= 0) throw new IllegalArgumentException("times: " + times);

		StringBuilder ret = new StringBuilder(times*cs.length());
		for (int i = 0; i < times; i++)
		{
			ret.append(cs);
		}

		assert ret.length() == cs.length()*times;

		return ret.toString();
	}

	/**
	 * Creates a string equal to the passed char,
	 * concatenated onto itself X times.
	 * @param ch
	 * The character to repeat.
	 * @param times
	 * The amount of times to repeat the character.
	 * @return str
	 * The character repeated after itself.
	 * @throws IllegalArgumentException
	 * if the times is less than or equal to 0
	 */
	@Pure
	public static String repeat(final char ch, final int times) throws IllegalArgumentException
	{
		// Checks
		if (times <= 0) throw new IllegalArgumentException("times: " + times);

		// Create, fill, and return.
		char[] ret = new char[times];
		for (int i = 0; i < times; i++)
		{
			ret[i] = ch;
		}

		assert ret.length == times;

		return new String(ret);
	}

	// -------------------------------------------- //
	// LETTER STRING
	// -------------------------------------------- //

	/**
	 * Turns a long such as 1, 2, 3 etc
	 * into a letter string (no digits).
	 * EXAMPLES
	 * 1 turns into "one"
	 * 2 turns into "two"
	 * etc
	 * For negative numbers it will start with "minus "
	 *
	 * The output of this method is undefined.
	 * And nothing is tested.
	 * TODO
	 *
	 * @param number
	 * The number to get a string representation of.
	 * @return
	 * A non-digit string representation of the passed number.
	 */
	@Pure
	public static String letterString(long number)
	{
		// This is just zero
		if (number == 0) return "zero";

		// negative numbers
		if (number < 0) return "minus ".concat(letterString(-number));

		// Special detection, because english makes no sense :P
		if (number >= 11 && number <= 19) return ELEVEN_THROUGH_NINETEEN[ (int) number-11];

		String ret = "";
		for(int idx = 0; number > 0; idx++)
		{
			int digit = (int) number % 10;
			number /= 10;

			if (digit == 0) continue; // Don't add nothingness.
			ret = letterString0(digit, idx).concat(ret);
		}

		assert ! ret.contains("null") : ret;
		assert ! ret.codePoints().anyMatch(Character::isDigit);

		return ret;
	}

	private static String letterString0(int number, int index)
	{
		assert number > 0: "number: " + number;
		assert index >= 0: "index: " + index;

		// Just a simple digit such as 1, 3, 6, 9 or 0
		if (index == 0) return DIGIT_TO_STRING[number];

		// A number such as 10, 30, 60 or 90
		if (index == 1) return TENS_TO_STRING[number];

		// For hundreds
		if (index == 2) return DIGIT_TO_STRING[number] + "hundred";

		final int rest = index % 3;
		String pre = letterString0(number, rest);
		String ret = pre;
		if (rest == 0) ret += " " + POWER_OF_TEN_TO_STRING[index-rest];
		// Higher numbers with many digits.
		return ret + " ";
	}

	// -------------------------------------------- //
	// VOWEL & AAN
	// -------------------------------------------- //

	/**
	 * This method can check if a character is a vowel.
	 * It may not support all unicode characters and all
	 * languages. If it does not, please make a PR so it will.
	 * @param ch
	 * Character to check wether it is a vowel or not.
	 * @return
	 * True if and only if the passed char is a vowel
	 */
	@Pure
	public static boolean isVowel(char ch)
	{
		return ArrayUtil.containsElement(VOWELS, ch);
	}

	/**
	 * This method will return the appropriate
	 * telex ("an" or "a") depending on the passed string.
	 *
	 * @param str
	 * The string to check for what it's telex should be.
	 * @return
	 * "an" if and only if the first character is a vowel.
	 * "a" otherwise.
	 * @throws ArgumentNullException
	 * If str is null.
	 * @throws IllegalArgumentException
	 * If str is empty.
	 */
	@Pure
	public static String aan(String str) throws ArgumentNullException, IllegalArgumentException
	{
		Argument.handleNull(str, "str");
		if (str.isEmpty()) throw new IllegalArgumentException("str is empty");
		return isVowel(str.charAt(0)) ? "an" : "a";
	}

}
