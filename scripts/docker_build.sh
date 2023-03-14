#!/bin/sh

docker build -t lonestar_test:v1 . 
docker run -it lonestar_test:v1

# to make the container auto-start, use ...
# docker update --restart always lonestar_test:v1
