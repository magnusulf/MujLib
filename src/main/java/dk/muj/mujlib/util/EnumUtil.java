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

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * This class provides several utility methods,
 * for dealing with enumerations.
 *
 * @author Magnus Ulf Jørgensen
 */
public final class EnumUtil
{
	// -------------------------------------------- //
	// CONSTRUCTOR (FORBIDDEN)
	// -------------------------------------------- //

	private EnumUtil()
	{
		throw new AssertionError();
	}

	// -------------------------------------------- //
	// VALUES
	// -------------------------------------------- //

	/**
	 * Gets an array of enum values, declared in the passed class.
	 * This method is equivalent to {@code clazz.getEnumConstants();}
	 * It does however produce some nice exceptions when failing.
	 * @param clazz
	 * The class in which to look for enum constants.,
	 * @param <T>
	 * The type of the enums constants. This must extend {@code Enum<T>}
	 * @return enumValues
	 * An array of all enum constants in the specified class.
	 * @throws ArgumentNullException
	 * If clazz is null.
	 * @throws IllegalArgumentException
	 * If clazz is not an enum.
	 */
	@Pure
	public static <T extends Enum<T>> T[] getEnumValues(Class<T> clazz) throws ArgumentNullException, IllegalArgumentException
	{
		// Check
		classEnumCheck(clazz);

		return Objects.requireNonNull(clazz.getEnumConstants(), "Failed retrieving enum constants at runtime.");
	}

	/**
	 * Finds an enum in the passed clazz,
	 * whose name is equal to str (case insensitive)
	 * @param clazz
	 * The clazz to look for enum values in.
	 * @param str
	 * The string which the returned enums name must be equal to (case insensitive)
	 * @param <T>
	 * Type of the enum
	 * @return
	 * If a matching enum is found, that enum is returned wrapped inside an optional.
	 * Otherwise an empty optional is returned.
	 * @throws ArgumentNullException
	 * if either clazz or str is null
	 * @throws IllegalArgumentException
	 * If clazz is not enum.
	 */
	@Pure
	public static <T extends Enum<T>> Optional<T> getEnumValueByName(Class<T> clazz, String str) throws ArgumentNullException, IllegalArgumentException
	{
		// Check
		classEnumCheck(clazz);
		Argument.handleNull(str, "str");

		return getEnumValueMatching(clazz, MPredicates.getEnumNameMatches(MPredicates.getEqualsIgnoreCase(str)));
	}

	/**
	 * Finds an enum in the passed clazz,
	 * which matches the specified predicate.
	 * @param clazz
	 * The clazz to look for enum values in.
	 * @param predicate
	 * The predicate which the returned enum must match.
	 * @param <T>
	 * Type of the enum
	 * @return
	 * If a matching enum a found, that enum is returned wrapped inside an optional.
	 * Otherwise an empty optional is returned.
	 * @throws ArgumentNullException
	 * If either clazz or predicate is null
	 * @throws IllegalArgumentException
	 * If clazz is not enum.
	 */
	@Pure
	public static <T extends Enum<T>> Optional<T> getEnumValueMatching(Class<T> clazz, Predicate<Enum<?>> predicate) throws ArgumentNullException, IllegalArgumentException
	{
		classEnumCheck(clazz);
		Argument.handleNull(predicate, "predicate");

		return ArrayUtil.firstMatching(getEnumValues(clazz), predicate);
	}

	// -------------------------------------------- //
	// PRIVATE UTIL
	// -------------------------------------------- //

	private static void classEnumCheck(Class<? extends Enum<?>> clazz)
	{
		Argument.handleNull(clazz, "clazz");

		// Only possible if someone does an unsafe generic cast.
		if ( ! clazz.isEnum()) throw new IllegalArgumentException(clazz.getName() + " is not an enum.");
	}

}
