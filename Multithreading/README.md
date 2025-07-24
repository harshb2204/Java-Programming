![](/diagrams/process.png)

---

**Process:**

Process is an instance of a program that is getting executed.
It has its own resource like memory, thread etc. OS allocate these resources to process when its created.

- Compilation (*javac Test.java*): generates bytecode that can be executed by JVM.
- Execution (*java Test*): at this point, JVM starts the new Process, here *Test* is the class which has `public static void main(String args[])` method.
