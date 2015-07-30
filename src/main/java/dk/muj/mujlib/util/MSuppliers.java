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

import dk.muj.mujlib.ExceptionHandler;
import dk.muj.mujlib.arg.Argument;
import dk.muj.mujlib.arg.ArgumentNullException;
import dk.muj.mujlib.doc.Pure;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

/**
 * This class contains some different suppliers,
 * and provides a static way to create them.
 *
 * @author Magnus Ulf Jørgensen
 */
public final class MSuppliers
{
	// -------------------------------------------- //
	// CONSTRUCTOR (FORBIDDEN)
	// -------------------------------------------- //

	private MSuppliers()
	{
		throw new AssertionError();
	}

	// -------------------------------------------- //
	// SINGLETON SUPPLIER
	// -------------------------------------------- //

	/**
	 * Creates a supplier, which always supplies the same object.
	 * @param element
	 * The object which the supplier will always return.
	 * @param <T>
	 * Type of element and suppliers return value.
	 * @return supplier
	 * A supplier always returning element
	 * @throws ArgumentNullException
	 * If element is null
	 */
	@Pure
	public static <T> Supplier<T> getSingletonSupplier(T element) throws ArgumentNullException
	{
		Argument.handleNull(element, "element");
		return () -> element;
	}

	// -------------------------------------------- //
	// INSTANCE SUPPLIER
	// -------------------------------------------- //

	/**
	 * This method will find a no-arg constructor
	 * in the specified class, and create an instance supplier by
	 * {@code MSuppliers.getInstanceSupplier(Constructor<T>)}
	 * @param clazz
	 * The class to find a no-arg constructor in.
	 * @param <T>
	 * The generic type for the constructor and the returned supplier.
	 * @return
	 * A supplier that returns a new instance of an object
	 * which is initialised using the found constructor.
	 * @throws ArgumentNullException
	 * If clazz is null.
	 * @throws IllegalArgumentException
	 * If a no-arg constructors could not be found.
	 */
	@Pure
	@SuppressWarnings("unchecked")
	public static <T> Supplier<T> getInstanceSupplier(Class<T> clazz) throws ArgumentNullException
	{
		Argument.handleNull(clazz, "clazz");
		for (Constructor<?> c: clazz.getConstructors())
		{
			if (c.getParameterCount() == 0) return getInstanceSupplier((Constructor<T>) c);
		}
		throw new IllegalArgumentException(clazz.getName() + " doesn't have a no-arg constructor.");
	}

	/**
	 * Creates a supplier, which will return a new
	 * instance of T. based on the specified no-arg constructor.
	 * @param constructor
	 * The constructor that should be used to create new instances.
	 * This constructor must be a no-arg constructor.
	 * @param <T>
	 * The generic type for the constructor and the returned supplier.
	 * @return
	 * A supplier that returns a new instance of an object
	 * which is initialised using the passed constructor.
	 * @throws ArgumentNullException
	 * If constructor is null.
	 * @throws IllegalArgumentException
	 * If the constructor isn't a no-arg constructor.
	 */
	@Pure
	public static <T> Supplier<T> getInstanceSupplier(Constructor<T> constructor) throws ArgumentNullException, IllegalArgumentException
	{
		Argument.handleNull(constructor, "constructor");
		if (constructor.getParameterCount() != 0) throw new IllegalArgumentException("Constructor isn't no-arg.");
		constructor.setAccessible(true);
		return () -> ExceptionHandler.THROW_UNCHECKED.tryCall(() -> constructor.newInstance());
	}

	// -------------------------------------------- //
	// NULL SUPPLIER
	// -------------------------------------------- //

	/**
	 * Always returns {@code null}.
	 * Can be used as a supplier.
	 * @param <T>
	 * Generic type of the supplier
	 * @return
	 * null
	 * @see Supplier
	 */
	@Pure
	public static <T> T getNull()
	{
		return null;
	}

	/**
	 * Creates a supplier, which always returns {@code null}.
	 * @param <T>
	 * Generic type of the supplier
	 * @return
	 * A supplier which always returns null.
	 * @see Supplier
	 */
	@Pure
	public static <T> Supplier<T> getNullSupplier()
	{
		return MSuppliers::getNull;
	}

}
