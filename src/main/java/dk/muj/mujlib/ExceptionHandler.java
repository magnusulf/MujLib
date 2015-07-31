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

package dk.muj.mujlib;

import dk.muj.mujlib.util.Mujtil;

import java.util.concurrent.Callable;

/**
 * This interface represents a consumer for an exception.
 * It can do everything from, printing the stacktrace,
 * to rethrowing the exception, or be completely silent.
 *
 * The generic parameterization is in place,
 * because how an exception is handled will differ,
 * It is used in the throws clause for for the tryRun and tryCall methods.
 * So if the exception will be rethrown, the parameterization could be
 * {@code Exception}, and a try catch statement must be in place.
 * But if nothing is done, or the stack trace is printed
 * the parameterization should be {@code AssertionError}
 * then a try catch statement isn't necessary, and won't bloat the code.
 *
 * @author Magnus Ulf Jørgensen
 */
public interface ExceptionHandler<T extends Throwable>
{
	// -------------------------------------------- //
	// ABSTRACT
	// -------------------------------------------- //

	/**
	 * This method will handle an exception.
	 * Common ways to do this is:
	 * 1. Print the stack trace.
	 * 2. Ignore it.
	 * 3. Rethrow it
	 * @param t
	 * The throwable to handle.
	 */
	public void handle(Throwable t) throws T;

	// -------------------------------------------- //
	// DEFAULT
	// -------------------------------------------- //

	/**
	 * This exception handler will always rethrow the exception.
	 * It's generic type is Exception, so rethrowing is done in a checked manner.
	 * So that the user (you) will have to handle the exception.
	 * tryRun and tryCall will not reach their return statement when executed on this handler.
	 */
	public static final ExceptionHandler<Exception> THROW_CHECKED = Mujtil::sneakyThrow;

	/**
	 * This exception handler will always rethrow the exception.
	 * It's generic type is RuntimeException, so rethrowing this is done in a unchecked manner.
	 * So that the user (you) are not forced to handle the exception.
	 * tryRun and tryCall will not reach their return statement when executed on this handler.
	 */
	public static final ExceptionHandler<RuntimeException> THROW_UNCHECKED = Mujtil::sneakyThrow;;

	/**
	 * This exception handler will always print the stack trace.
	 * It will never rethrow the exception.
	 * So tryRun and tryCall will reach their return statement when executed on this handler.
	 * It's generic type is AssertionError, so you will not need a try catch statement.
	 */
	public static final ExceptionHandler<AssertionError> PRINT = Throwable::printStackTrace;

	/**
	 * This exception handler will not do anything, when given an exception to handle.
	 * So tryRun and tryCall will reach their return statement when executed on this handler.
	 * It's generic type is AssertionError, so you will not need a try catch statement.
	 */
	public static final ExceptionHandler<AssertionError> SILENT = t -> {};

	// -------------------------------------------- //
	// TRY PERFORM
	// -------------------------------------------- //

	/**
	 * This method attempts to call the specified callable.
	 * If an exception is thrown by the callable, it
	 * will be dealt with by this exception handler.
	 * This method will only catch instances of {@code Exception}
	 * if another throwable is thrown, it won't be handled here.
	 * @param callable
	 * A callable representing a piece of code, that might throw an exception.
	 * @param <V>
	 * Type of the return value.
	 * @return
	 * The returned value from calling Callable#call
	 * on the specified Callable.
	 * If the call fails (an exception is thrown)
	 * and this exception handler doesn't rethrow it
	 * then null is returned.
	 * @throws T
	 * This method might throw. Depending on the exception handler.
	 */
	public default <V> V tryCall(Callable<V> callable) throws T
	{
		try
		{
			return callable.call();
		}
		catch (Exception ex)
		{
			this.handle(ex);
			return null;
		}
	}

	/**
	 * This method attempts to run the runnable.
	 * If an exception is thrown by the callable, it
	 * will be dealt with by this exception handler.
	 * This method will only catch instances of {@code Exception}
	 * if another throwable is thrown, it won't be handled here.
	 * @param runnable
	 * A throwing runnable representing a piece of code, that might throw an exception.
	 * @return
	 * True if the runnable ran successfully.
	 * If it fails false is returned. Unless, the exception
	 * was rethrown by this exception handler,
	 * in that case nothign is returned.
	 * @throws T
	 * This method might throw. Depending on the exception handler.
	 */
	public default boolean tryRun(ThrowingRunnable runnable) throws T
	{
		try
		{
			runnable.throwingRun();
			return true;
		}
		catch (Exception ex)
		{
			this.handle(ex);
			return false;
		}
	}

}
