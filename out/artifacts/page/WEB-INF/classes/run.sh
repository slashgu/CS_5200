#!/bin/sh
#
# Compile and Run the OpenJPA test program
#
cp=.
cp=$cp:lib/mysql-connector-java.jar
cp=$cp:lib/openjpa-2.0.1.jar
cp=$cp:lib/commons-lang-2.1.jar
cp=$cp:lib/geronimo-jta_1.1_spec-1.1.1.jar
cp=$cp:lib/serp-1.13.1.jar
cp=$cp:lib/commons-collections-3.2.1.jar
cp=$cp:lib/geronimo-jpa_2.0_spec-1.1.jar
#
echo "Compiling"
javac -cp $cp *.java */*.java
echo "Enhancing"
java -cp $cp org.apache.openjpa.enhance.PCEnhancer nu/Student.java nu/Course.java
echo "Running"
java -cp $cp Test
