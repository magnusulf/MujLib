MujLib
A Java Libarary with many unrelated features.

This is currently undergoing development, most features will stay
in their current form, or only be slightly changed.
NO COMPATIBILITY IS GUARANTEED YET.

*** Nice to know

Almost all methods in this library are pure
and we try to promote functional paradigms.

Unless otherwise stated, everything is non-null.

All ranges will have an inclusive start, and exclusive end,
unless otherwise stated.

This library will do it's best to not accept your input.
It prefers to just throw an exception, rather than
giving special not so obvious treatment to odd input.
Perfect cases are null, NaN, float and double infinity and negative numbers,
all of those do not always have a logical output,
and many methods will fail if given them as input.

*** Notable things:

RangeList
A lazily evaluated very cheap list, for a range of integers.

ArrayUtil
indexOf, contains & filter operations for arrays.
Map and reduce is TODO.

RandomUtil
Easily generate a random number between two values.

String predicates
Very quick string predicates for case insensitive
prefix or equality.

ArrayIterator
An ListIterator for an array, likely faster than
Arrays.asList().listIterator.

Easy collection constructors
Easy instantiation of lists, sets, linked sets and maps.
This is done with varargs, and is easy to read for humans.

Argument checker
Easy readable argument checking for null, NaN and infinity arguments.

Other misc utilities.

*** Todos:
Any tasks which are not yet done, but should be done.
Is marked in the source code. If it is slightly important
a corresponding github ticket will also be made.
