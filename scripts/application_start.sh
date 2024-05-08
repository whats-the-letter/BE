#!/bin/bash
cd /home/ubuntu/app
java -jar $(find build/libs/ -name '*.jar' -not -name '*-plain.jar' | sort | tail -n 1)