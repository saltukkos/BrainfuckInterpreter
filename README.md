# Interpreter of esoteric programming language "Brainfuck" on Java

Main purposes of this project was:
  - Writing well-designed OOP application
  - Required using of Factory pattern
  - Testing
  - Build application in jar
    
For testing I used [JUnit](junit.org/)
Building with [Apache Ant](ant.apache.org/)

### Build

```sh
$ cd ant/
$ ant deploy
```

### Usage
```sh
$ cd bin/
$ java -jar '<your program>'
```

### Examples
Hello world
```sh
$ java -jar brainfuck.jar '++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.'
Hello World!

Program finished correctly
```
Factorial of 1..5
```sh
$ java -jar brainfuck.jar '++++++>>++++++++++>>>+>+<<<<<<[>>>>>>>>>+[-[<<<<<[+<<<<<]>>[[-]>[<<+>+>-]<[>+<-]<[>+<-[>+<-[>+<-[>+<-[>+<-[>+<-[>+<-[>+<-[>+<-[>[-]>>>>+>+<<<<<<-[>+<-]]]]]]]]]]]>[<+>-]+>>>>>]<<<<<[<<<<<]>>>>>>>[>>>>>]++[-<<<<<]>>>>>>-]+>>>>>]<[>++<-]<<<<[<[>+<-]<<<<]>>[->[-]++++++[<++++++++>-]>>>>]<<<<<[<[>+>+<<-]>.<<<<<]>.<<-]'
1
1
2
6
24
120

Program finished correctly
```
Sort
```sh
$ java -jar brainfuck.jar ">,[>,]+[<[-<]>[>]>[<-[>]<.>>]<<+]"
qazxswedcvfrtgbnhyujm,kiol./;'][p1029384756^@
',./0123456789;[]abcdefghijklmnopqrstuvwxyz
Program finished correctly

```
