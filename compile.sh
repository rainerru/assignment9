#!/bin/bash
cd src
javac -d ../out assignment9_int/*.java
javac -d ../out rainer_sieberer/*.java
javac -d ../out ../test/rainer_sieberer/*.java
cd ..
