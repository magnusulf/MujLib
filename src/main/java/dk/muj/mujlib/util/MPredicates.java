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

import java.util.function.Predicate;

/**
 * This class contains a few predicate implementations,
 * for different tasks.
 * The case insensitive string predicate implementations
 * do often have a simpler equivalent by lowercasing both strings,
 * but have some nice optimisations.
 *
 * @author Magnus Ulf Jørgensen
 */
public final class MPredicates
{
	// -------------------------------------------- //
	// CONSTRUCTOR (FORBIDDEN)
	// -------------------------------------------- //

	private MPredicates()
	{
		throw new AssertionError();
	}

	// -------------------------------------------- //
	// STARTS WITH IGNORE CASE
	// -------------------------------------------- //

	/**
	 * Gets a predicate, which will yield true
	 * for all strings that start with (case insensitive) prefix.
	 *
	 * This predicate is equal to (but faster when several checks are made)
	 * t.toLowerCase().startsWith(prefix.toLowerCase);
	 * @param prefix
	 * The prefix which other string should start with (case insensitive)
	 * in order for this predicate to return true.
	 * @return predicate
	 * A predicate that will return true, if and only if
	 * the string that is tested for, starts with (case insensitive)
	 * the passed prefix.
	 * This predicate doesn't accept null values.
	 * @throws ArgumentNullException
	 * If prefix is null.
	 */
	@Pure
	public static Predicate<String> getStartsWithIgnoreCase(String prefix) throws ArgumentNullException
	{
		Argument.handleNull(prefix, "prefix");
		return new PredicateStartsWithIgnoreCase(prefix);
	}

	private static class PredicateStartsWithIgnoreCase implements Predicate<String>
	{
		private final String prefixLowerCase;
		private final String prefixUpperCase;

		private PredicateStartsWithIgnoreCase(String prefix)
		{
			prefixLowerCase = prefix.toLowerCase();
			prefixUpperCase = prefix.toUpperCase();
		}

		@Override
		public boolean test(String t)
		{
			Argument.handleNull(t, "t");
			boolean ret = matches(t);
			assert ret == t.toLowerCase().startsWith(prefixLowerCase);
			return ret;
		}

		private boolean matches(String t)
		{
			int index = prefixLowerCase.length();

			if (t.length() < index) return false;

			while (index-- > 0)
			{
				char ch = t.charAt(index);
				if (ch == prefixLowerCase.charAt(index)) continue;
				if (ch == prefixUpperCase.charAt(index)) continue;
				return false;
			}

			return true;
		}
	}

	// -------------------------------------------- //
	// EQUALS IGNORE CASE
	// -------------------------------------------- //

	/**
	 * Gets a predicate, which will yield true
	 * for all strings that is equal to  (case insensitive) str.
	 *
	 * This predicate is equal to (but faster when several checks are done)
	 * t.equalsIgnoreCase(str);
	 * @param str
	 * The string which other strings should be equal to (case insensitive)
	 * in order for this predicate to return true.
	 * @return predicate
	 * A predicate that will return true, if and only if
	 * the string that is tested for, is equal to (case insensitive)
	 * the passed string.
	 * This predicate doesn't accept null values.
	 * @throws ArgumentNullException
	 * If str is null.
	 */
	@Pure
	public static Predicate<String> getEqualsIgnoreCase(String str) throws ArgumentNullException
	{
		Argument.handleNull(str, "str");
		return new PredicateEqualsIgnoreCase(str);
	}

	private static class PredicateEqualsIgnoreCase implements Predicate<String>
	{
		private final String strLowerCase;
		private final String strUpperCase;

		private PredicateEqualsIgnoreCase(String str)
		{
			strLowerCase = str.toLowerCase();
			strUpperCase = str.toUpperCase();
		}

		@Override
		public boolean test(String t)
		{
			Argument.handleNull(t, "t");
			boolean ret = matches(t);
			assert ret == t.equalsIgnoreCase(strLowerCase);
			return ret;
		}

		public boolean matches(String t)
		{
			int index = strLowerCase.length();

			if (t.length() != index) return false;

			while (index-- > 0)
			{
				char ch = t.charAt(index);
				if (ch == strLowerCase.charAt(index)) continue;
				if (ch == strUpperCase.charAt(index)) continue;
				return false;
			}

			return true;
		}
	}

	// -------------------------------------------- //
	// ENUM NAME
	// -------------------------------------------- //

	/**
	 * Turns a string predicate into an enum predicate.
	 * The resulting enum predicate uses the string predicate internally.
	 * An enum will match the returned enum predicate,
	 * if it's name matches the string predicate.
	 * @param predicate
	 * The string predicate to create an enum predicate for.
	 * @return
	 * An enum predicate based on the specified string predicate.
	 * An enum will pass this predicate, if its name
	 * passes the specified string predicate.
	 */
	@Pure
	public static Predicate<Enum<?>> getEnumNameMatches(Predicate<String> predicate)
	{
		Argument.handleNull(predicate, "predicate");
		return e -> predicate.test(e.name());
	}

}
