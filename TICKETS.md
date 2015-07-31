#Tickets

GitHub has a built in issues system. Which we want to use for many different things.
All issues should be marked with a label that corresponds to what they are about.
The different labels is covered in this file.

####Bug
Any bugs/glitches/errors that exists in this library.
A ticket including this should describe what they expected to occur, and what actually occurred.
Often that will be telling what input they gave to a method, what they would expect the output to be, and what the output actually was.
Any stacktraces which occured should also be posted. In general be as descriptive as possible.
If possible it should be told EXACTLY how to reproduce said circumstances.
They should also make sure that the bugs is actually in MujLib and not their own source code.

The assignee is the developer responsible for fixing the bug.

All fixes should be local and hopefully not affect anything else.
All places where the buggy souce code was used internally, should be checked.
In case they were mistakenly relying on the bug.
Tests that would have caught the bug should also be added, so that we won't run into the same issue again.

A bug should be closed when it is fixed, and there is made unit tests, which will catch any reintroductions of the bug.

####Discussion
Any discussions about almost anything.
If you are in doubt about something, and want peoples (more or less subjective) opinion.
You should make an issue with the discussion label.

Discussions should be for subjective matters. Whereas things that can be factually answered should be a question.

Examples include:
API design choices.

The assignee is the one person who should make a conclusion
based on the different opinions in the discussion.

A discussion should be closed when a decisison is taken.

####Duplicate
Any issue being discussions, bug reports, todos, questions or something else.
Which already has an issue regarding the same thing, should be marked with this label.

All duplicates should be closed instantaneously.

####Feature request
A request for a specific feature to be added to the library.
These should not occur often because all users of the library are developers, and should rather make a pull request.
But from time to time a feature request would make sense.

The assignee is the one developer responsible for implementing
the requested feature.

A feature request should be closed when it is either added to the library along with unit tests,
or if it is decided that it shouldn't be added.
Even if there is no plans to implement a feature request, it should often still be open so that
any developer interested in this project, could potentially implement it.

####Invalid
Any issue being discussions, bug reports, todos, questions or something else,
which is using an invalid format, should be marked with this label.
We currently don't have any strict format rules, so this will rarely be used.

All invalid formatted issues should be closed instantaneously.

####Question
Any questions which can be factually answered should have this label.
This is usually for newcomers who are in doubt about something.

If the answer can be subjective a discussion might be more appropriate.

The assginee is the person who should track the issues and also answer any follow up questions.

A question should be closed when there is no possible confusion about the answer.

####Todo
Todos are for tasks which should be done.
This is often tasks which no one is currently capable of doing,
or something that will take quite some time.

Todos should only be made by core project contributors.
Because it is only for tasks which should be done.
In other cases it will often be a feature request rather than a todo.

The assignee is the person who should do this task.

A todo should be closed when there it is not todo anymore, because it has been implemented,
or because it was decided that it isn't necessary. 
