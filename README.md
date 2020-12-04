# jmtrace
A tool for tracing shared memory access in a Java program packaged in a jar file.

## Requirements
- JDK 1.8 (>= 1.8.0_152)
- maven 3

## Build
This project uses maven to package jar file. Run command below in this directory to generate agent jar file:
```shell
$ mvn clean package
```
The agent jar file is `jmtrace-1.0-SNAPSHOT-jar-with-dependencies.jar` under directory `target`.

## Usage
```shell
$ java -javaagent:[path to jmtrace-1.0-SNAPSHOT-jar-with-dependencies.jar] -jar [path to target traced jar]
```
Every time accessing shared memory, *jmtrace* will output a log consisting of four parts:
```
R/W     ThreadID    Object_Identify_Code  Object/Class_Member(or index of array)
```

## Example
```shell
$ java -javaagent:target/jmtrace-1.0-SNAPSHOT-jar-with-dependencies.jar -jar test.jar
W 1 0cc34f4d00000000 com.tiva.ISER.App.sc
R 1 300ffa5d00000000 com.tiva.ISER.App.az
W 2 1d56ce6a00000000 java.lang.String[0]
W 2 5197848c00000002 int[2]
R 3 5197848c00000003 int[3]
```