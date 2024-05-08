#!/bin/bash

docker run -d --name wtl-be-container -p 80:8080 \
  --log-driver=awslogs \
  --log-opt awslogs-region=$AWS_REGION \
  --log-opt awslogs-group=wtl-logs \
  --log-opt awslogs-stream=wtl-be-stream \
  $IMAGE_URL