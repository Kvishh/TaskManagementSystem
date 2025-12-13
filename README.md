# TrackTask (Task Management System)

TrackTask is a Graphical User Interface (GUI) Task Management System where users are allowed to create, edit,
prioritize, delete or mark as complete their tasks. Its main goal is to remind the users about a task. They
can set a deadline date to their task where they need to accomplish it before the deadline they have set.
They can also remind themselves whether it is the most prioritized task or it is a minor task. There is also
a visual cue if it is most important or the least important. The tasks they have added can also be marked as done,
however, it will not disappear until they have chosen to delete it. The tasks also persist even though the
program has been stopped running. Users can always expect the tasks they have added remain in the application.

## IMPORTANT
Git clone this repo.

In order for this to run, you need to have **Java 11** since if you have other version of Java, be it Java 17,
Java 21, or Java 8, it wouldn't run.

### With Maven installed

Run this in the root directory `mvn javafx:run` on terminal or command line. It is probably much better if you
run this first `mvn clean install` before running the `mvn javafx:run`.

### Without Maven installed

In the root directory, run this `./mvnw.cmd javafx:run` on terminal or command line.
