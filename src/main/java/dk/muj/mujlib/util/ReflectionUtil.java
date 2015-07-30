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
import dk.muj.mujlib.doc.Alpha;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * This is highly experimental
 *
 * @author Magnus Ulf Jørgensen
 */
@Alpha
public final class ReflectionUtil
{
	// -------------------------------------------- //
	// CONSTRUCTOR (FORBIDDEN)
	// -------------------------------------------- //

	private ReflectionUtil()
	{
		throw new AssertionError();
	}

	// -------------------------------------------- //
	// MAKE ACCESSIBLE
	// -------------------------------------------- //

	private static final Field MODIFIERS_FIELD;

	static
	{
		MODIFIERS_FIELD = getField(Field.class, "modifiers", ExceptionHandler.PRINT);
		MODIFIERS_FIELD.setAccessible(true);
	}

	public static <T extends Throwable> boolean makeAccessible(Class<?> clazz, String fieldName, ExceptionHandler<T> exceptionHandler) throws T
	{
		return makeAccessible(getField(clazz, fieldName, exceptionHandler), exceptionHandler);
	}

	public static <T extends Throwable> boolean makeAccessible(Field field, ExceptionHandler<T> exceptionHandler) throws T
	{
		Argument.handleNull(field, "field");
		Argument.handleNull(exceptionHandler, "exceptionHandler");
		return exceptionHandler.tryRun(() -> {
			field.setAccessible(true);
			MODIFIERS_FIELD.setInt(field, field.getModifiers() & ~ Modifier.FINAL);
		});
	}

	// -------------------------------------------- //
	// GET FIELD
	// -------------------------------------------- //

	public static <T extends Throwable> Field getAccessibleField(Class<?> clazz, String fieldName, ExceptionHandler<T> exceptionHandler) throws T
	{
		Field field = getField(clazz, fieldName, exceptionHandler);
		if (field != null)
		{
			makeAccessible(field, exceptionHandler);
		}
		return field;
	}

	public static <T extends Throwable> Field getField(Class<?> clazz, String fieldName, ExceptionHandler<T> exceptionHandler) throws T
	{
		Argument.handleNull(clazz, "clazz");
		Argument.handleNull(fieldName, "fieldName");
		Argument.handleNull(exceptionHandler, "exceptionHandler");

		return exceptionHandler.tryCall(() -> clazz.getDeclaredField(fieldName));
	}

	// -------------------------------------------- //
	// GET FIELD VALUE
	// -------------------------------------------- //

	public static <T extends Throwable> Object getFieldValue(String fieldName, Object target, ExceptionHandler<T> exceptionHandler) throws T
	{
		return getFieldValue(target.getClass(), fieldName, target, exceptionHandler);
	}

	public static <T extends Throwable> Object getFieldValue(Class<?> clazz, String fieldName, Object target, ExceptionHandler<T> exceptionHandler) throws T
	{
		return getFieldValue(getField(clazz, fieldName, exceptionHandler), target, exceptionHandler);
	}

	public static <T extends Throwable> Object getFieldValue(Field field, Object target, ExceptionHandler<T> exceptionHandler) throws T
	{
		Argument.handleNull(field, "field");
		Argument.handleNull(exceptionHandler, "exceptionHandler");
		if ( ! Modifier.isFinal(field.getModifiers())) Argument.handleNull(target, "target");

		return exceptionHandler.tryRun(() -> field.get(target));
	}

	// -------------------------------------------- //
	// SET FIELD VALUE
	// -------------------------------------------- //

	public static <T extends Throwable> boolean setFieldValue(String fieldName, Object target, Object value, ExceptionHandler<T> exceptionHandler) throws T
	{
		return setFieldValue(target.getClass(), fieldName, target, value, exceptionHandler);
	}

	public static <T extends Throwable> boolean setFieldValue(Class<?> clazz, String fieldName, Object target, Object value, ExceptionHandler<T> exceptionHandler) throws T
	{
		return setFieldValue(getField(clazz, fieldName, exceptionHandler), target, value, exceptionHandler);
	}

	public static <T extends Throwable> boolean setFieldValue(Field field, Object target, Object value, ExceptionHandler<T> exceptionHandler) throws T
	{
		Argument.handleNull(field, "field");
		Argument.handleNull(exceptionHandler, "exceptionHandler");
		if ( ! Modifier.isStatic(field.getModifiers())) Argument.handleNull(target, "target");

		return exceptionHandler.tryRun(() -> field.set(target, value));
	}

	// -------------------------------------------- //
	// TRANSFER FIELD VALUE
	// -------------------------------------------- //

	public static <T extends  Throwable> boolean transferFieldValue(String fieldName, Object from, Object to, ExceptionHandler<T> exceptionHandler) throws  T
	{
		return transferFieldValue(from.getClass(), fieldName, from, to, exceptionHandler);
	}

	public static <T extends  Throwable> boolean transferFieldValue(Class<?> clazz, String fieldName, Object from, Object to, ExceptionHandler<T> exceptionHandler) throws  T
	{
		return transferFieldValue(getField(clazz, fieldName, exceptionHandler), from, to, exceptionHandler);
	}

	public static <T extends  Throwable> boolean transferFieldValue(Field field, Object from, Object to, ExceptionHandler<T> exceptionHandler) throws  T
	{
		return setFieldValue(field, to, getFieldValue(field, from, exceptionHandler), exceptionHandler);
	}

	// -------------------------------------------- //
	// ANNOTATIONS
	// -------------------------------------------- //

	public static boolean hasAnnotation(Field field, Class<? extends Annotation> annotation)
	{
		Argument.handleNull(field, "field");
		Argument.handleNull(annotation, "annotation");
		if ( ! annotation.isAnnotation()) throw new IllegalArgumentException(annotation + " is not an annotation.");
		return field.getAnnotation(annotation) != null;
	}

}
