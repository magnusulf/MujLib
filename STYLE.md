#Code style

This file contains some guidelines for code style, which should be followed
when making contributions to this project.

These rules are in place to keep the code base consistent
and easily readable.

###Naming conventions
* Use "max" and "min" rather than "maximum" and "minimum".
* Names should be camelCase rather than snake_case

###Whitespace and newlines
* Newlines should be LF rather than CR LF (not strictly enforced)
* All curly brackets ("{" and "}") should be placed on their own line.
* There should never be two empty lines right next to each other.
* After keywords such as `if`, `for` and `catch`, there should be a space
before the opening parentheses ("(")
* Use tabs for indentation instead of spaces.
* Use spaces on both sides of the `:` in an enhanced for loop
so that it will look like this `for (Object obj : array)`
* Always put a space after a comma.

###General
* Always use getters and setters where possible. Even inside the class that declared the field.
* **D**on't **R**epeat **Y**ourself. Avoid duplicate code.
