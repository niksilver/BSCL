There are two versions of this exercise. The first is how far I got after the
allotted/recommended 60 minutes. The second version is after another hour and
a bit, because I wanted to finish it.

The implementation is Scala. Is this an "appropriate technology" as requested?
Well, I chose it because I've been coding with Scala very recently, find it a
lot of fun, and I'm more productive (= less rusty) with this than Java. So
if "appropriate" means "allows the developer to actually deliver some
reasonable code within the time constraints", then, yes, it's appropriate.
Scala also runs on the Java platform (which I know TP uses). Most
importantly I've written it in a functional style, so there are no mutable
variables, which means this implementation will scale well -- ideal for a
message queue. On the other hand I wouldn't recommend dropping a bit of Scala
into an application run by team with no experience of Scala. If the rest of
the (real) application is in Java then the chosen technology for this should
be Java, too, otherwise it won't be maintainable.

The application source can be found in src/main/scala/com/bscl
The test suites can be found in src/test/scala/com/bscl
I've included the ScalaTest jar in the lib directory, but I'll assume
you have Scala (I'm running 2.9.2) and sbt, the simple build tool.

You can run tests by starting up sbt at the top level and typing
test

They all pass, in both versions.

Implementation notes:

- Scala allows multiple classes to be defined in a single file, so I've done
  that for the main code because it's quite small. The tests, however, are
  split over multiple files because they can get quite lengthy.
  
- I used the language of the problem. E.g. a "place" method instead of
  "enqueue".

- Instruction message implementation very simple, just for testing
  the InstructionQueue class. In the one-hour version it omits all
  non-essential properties, so only has instructionType (required for
  prioritising). In the larger version it has additional parameters but
  they are optional for ease of testing. Real requirements will determine
  how far this is from acceptability.
  
- Interesting that the remove method didn't specify what criteria you should
  use to remove messages. Similarly the timeStamp property doesn't
  have a unit (e.g. seconds, milliseconds past the Epoch, etc).
  
- A real implementation of a message queue would serialise the messages, etc.
  In fact, in a real implementation you'd probably use an action MQ.

- There will definitely be gaps and edge cases I've not thought of, but
  you've got to stop somewhere, so I'm stopping here. It's been fun.
 