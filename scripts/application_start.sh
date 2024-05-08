#!/bin/bash
cd /home/ubuntu/app
jar_file=$(ls *.jar | head -n 1)
java -jar $jar_file