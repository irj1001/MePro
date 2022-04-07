#!/bin/bash

if [ ! -d ./bin ]; then
    mkdir bin
fi

javac -classpath ./bin:./lib/* \
-encoding UTF-8 \
-d bin \
-sourcepath ./ \
./src/juego/control/*.java \
./src/juego/modelo/*.java \
./src/juego/textui/*.java \
./src/juego/util/*.java \
