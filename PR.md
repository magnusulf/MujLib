#Pull requests

This file describes how we use pull requests.

#####Only a single commit
A single pull requests should only consist of a single commit,
that makes it much easier to see what is actually going on.
Squashing and ammending commits should be done.

#####Do just one thing
A single pull request should only be focused on one thing.
So if you want to do several things, spread it out to several pull requests.

#####Boy scout rule
[The boy scout rule](http://programmer.97things.oreilly.com/wiki/index.php/The_Boy_Scout_Rule)
is a good rule to follow. Even if a pull request should only do one thing,
doing a little bit of cleanup is always appreciated.

#####Feedback iterations
A pull request will rarely be accepted in the first go.
Often feedback will be given, and things will change.
After a couple of iterations with feedback a pull request might be good enough,
when it is good enough it will be pulled.

#####Include tests
If any new functionality is added, tests should be included.
We can't accept functionality which isn't properly tested.
