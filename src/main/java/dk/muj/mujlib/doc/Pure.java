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

package dk.muj.mujlib.doc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A constructor or method having this annotation, must be pure.
 * Pure means that the method is stateless, and it's output is only
 * and only based on it's input.
 * If a pure method has no input, it must always return the same value.
 *
 * Several calls to a pure function, at any point in time,
 * with the same parameters, must always yield the same return value.
 *
 * Furthermore a pure function may not have any side effects,
 * side effects include, modifying anything (also parameters),
 * starting a thread and most IO operations.
 * Creating objects is not counted as a side effect, unless they leave
 * the scope of the method, in another way than being returned.
 * Passing an object to another pure method is also fine.
 * Throwing exceptions, is ok for pure methods. If it is documented as,
 * an alternate return type, or if it will only occur because of a source code issue.
 *
 * Because a Pure method is not affected by anything but it's input,
 * and doesn't modify anything but local values .
 * They are assumed to be thread safe, unless otherwise stated.
 *
 * The current RetentionPolicy for this annotation is
 * RetentionPolicy.CLASS, but might change at any time.
 *
 * @author Magnus Ulf Jørgensen
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface Pure
{
}
