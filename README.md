#MujLib
A Java Library with many unrelated features.

This is currently undergoing development, most features will stay
in their current form, or only be slightly changed.
NO COMPATIBILITY IS GUARANTEED YET.

####License
Checkout the license [here](https://github.com/magnusulf/MujLib/blob/master/LICENSE.md)

####Tickets
See how GitHub issues should be used [here](https://github.com/magnusulf/MujLib/blob/master/TICKETS.md).
GitHub issues are used for keeping track of many things including, bugs, todos, feature requests, discussions and more.

####Pull requests
See how pull requests are handled in this reporistory [here](https://github.com/magnusulf/MujLib/blob/master/PR.md)
We have some strict rules about pull requests.

##Nice to know

1. Almost all methods in this library are pure
and we try to promote functional paradigms.

2. Unless otherwise stated, everything is non-null.

3. All ranges will have an inclusive start, and exclusive end,
unless otherwise stated.

4. This library will do it`s best to not accept your input.
It prefers to just throw an exception, rather than
giving special not so obvious treatment to odd input.
Perfect cases are null, NaN, infinity and negative numbers,
those values do not always have a logical output,
and many methods will fail if given them as input.

##Notable things:

1. **RangeList**
  * A lazily evaluated very cheap list, for a range of integers `MCollections.range()`.

2. **ArrayUtil**
  * indexOf, contains & filter operations for arrays.
  * Map and reduce is TODO.

3. **RandomUtil**
  * Easily generate a random number between two values.

4. **String predicates**
  * Fast case insensitive equality predicate `MPredicates.getEqualsIgnoreCase()`.
  * Fast case insensitive starts with predicate `MPredicates.getStartsWithIgnoreCase()`.

5. **ArrayIterator**
  * A ListIterator for an array `MIterators.forArray()`, likely faster than `Arrays.asList().listIterator()`.

6. **Easy collection constructors**
  * Easy vararg instantiation of a mutable list `MCollections.list()`.
  * Easy vararg instantiation of a mutable set `MCollections.set()`.
  * Easy vararg instantiation of a mutable linked set `MCollections.linkedSet()`.
  * Easy vararg instantiation of a mutable map `MCollections.map()`.

7. **Argument checker**
  * Easy argument checking of null `Argument.handleNull`.
  * Easy argument checking of NaN `Argument.handleNaN`.
  * Easy argument checking of float or double infinity `Argument.handleInfinity`.
  * Easy argument checking for both NaN and infinity `Argument.handleStrange`.

8. **Math**
  * Odd and even predicates `Mth.isOdd` and `Mth.isEven`.
  * Positive and negative predicates `Mth.isPositive` and `Mth.isNegative`.
  * Calculate factorial `Mth.factorial`.
  * Calculate binomial coefficient `Mth.binomial`.

9. **Exception handling**
  * Easily perform a piece of code throwing an exception without try-catch statements.
  You can rethrow the exception, either checked or unchecked, print the stack trace or just ignore it.
  Do this with the "ExceptionHandler" class.
  * We also offer a way to throw checked exceptions as if they were unchecked `Mujtil.throwSneaky(ex)`.
