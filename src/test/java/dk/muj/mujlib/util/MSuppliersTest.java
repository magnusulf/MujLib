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

import java.util.function.Supplier;

public class MSuppliersTest extends TestCase
{
	
	@Test
	public void testGetSingleton() throws Exception
	{
		Object obj = new Object();
		Supplier<Object> supplier = MSuppliers.getSingletonSupplier(obj);

		for (int i = 0; i < 100;i++)
		{
			assertEquals(obj, supplier.get());
		}
	}

	@Test
	public void testGetInstanceSupplierClass() throws Exception
	{
		Supplier<String> supplier = MSuppliers.getInstanceSupplier(String.class);

		for (int i = 0; i < 100; i++)
		{
			assertEquals("", supplier.get());
		}

	}

	@Test
	public void testGetInstanceSupplierConstructor() throws Exception
	{
		Supplier<String> supplier = MSuppliers.getInstanceSupplier(String.class.getConstructor());

		for (int i = 0; i < 100; i++)
		{
			assertEquals("", supplier.get());
		}

	}

	@Test
	public void testGetNull() throws Exception
	{
		Supplier<Object> supplier = MSuppliers::getNull;

		for (int i = 0; i < 100;i++)
		{
			assertNull(supplier.get());
		}
	}

	@Test
	public void testGetNullSupplier() throws Exception
	{
		Supplier<Object> supplier = MSuppliers.getNullSupplier();

		for (int i = 0; i < 100;i++)
		{
			assertNull(supplier.get());
		}
	}

}
