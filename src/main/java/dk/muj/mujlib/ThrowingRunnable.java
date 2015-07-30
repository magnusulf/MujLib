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

import dk.muj.mujlib.arg.Argument;
import dk.muj.mujlib.arg.ArgumentNullException;
import dk.muj.mujlib.doc.Pure;
import dk.muj.mujlib.util.Mujtil;

/**
 * This interface is almost equivalent to {@code Runnable}
 * however it's abstract method, allows throwing exceptions.
 *
 * This does extend the runnable interface.
 *
 * The run method (from runnable) behaves exactly as the throwingRun method.
 * The only difference between those methods is their name,
 * and that throwingRun has a throws clause.
 *
 * This means that this class breaks the contract from
 * runnable, by throwing an exception.
 * However I don't consider that as a major issues,
 * because it could already throw unchecked exceptions.
 *
 * @author Magnus Ulf Jørgensen
 */
@FunctionalInterface
public interface ThrowingRunnable extends Runnable
{
	// -------------------------------------------- //
	// ABSTRACT
	// -------------------------------------------- //
	/**
	 * Method like Runnable#run
	 * but it can throw an exception.
	 * @throws Exception
	 * This might throw an exception.
	 */
	public void throwingRun() throws Exception;

	// -------------------------------------------- //
	// OVERRIDE: RUNNABLE
	// -------------------------------------------- //

	/**
	 * {@inheritDoc}
	 *
	 * This can throw an exception.
	 */
	public default void run()
	{
		try
		{
			this.throwingRun();
		}
		catch (Exception ex)
		{
			Mujtil.sneakyThrow(ex);
		}
	}

	// -------------------------------------------- //
	// COMPOSITION
	// -------------------------------------------- //

	/**
	 * This will combine two throwing runnables.
	 * Which will consists of this, and the passed.
	 * First it will execute this, and then it will execute the specified runnable.
	 * @param after
	 * The second runnable to be run (after this).
	 * @return
	 * A new {@code ThrowingRunnable} first executing this,
	 * then executing the specified runnable.
	 * @throws ArgumentNullException
	 * If after is null.
	 */
	@Pure
	public default ThrowingRunnable andThen(ThrowingRunnable after) throws ArgumentNullException
	{
		Argument.handleNull(after, "after");
		return () ->
		{
			this.throwingRun();
			after.throwingRun();
		};
	}

	/**
	 * This will combine two throwing runnables.
	 * Which will consists of this, and the passed.
	 * First it will execute the specified runnable, and then it will execute this.
	 * @param before
	 * The first runnable to be run.
	 * @return
	 * A new {@code ThrowingRunnable} first executing the specified runnable,
	 * then executing this.
	 * @throws ArgumentNullException
	 * If before is null.
	 */
	@Pure
	public default ThrowingRunnable compose(ThrowingRunnable before) throws ArgumentNullException
	{
		Argument.handleNull(before, "before");
		return () ->
		{
			before.throwingRun();
			this.throwingRun();
		};
	}

}
