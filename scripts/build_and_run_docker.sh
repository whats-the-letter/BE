#!/bin/bash
cd /opt/codedeploy-agent/deployment-root/$DEPLOYMENT_GROUP_ID/$DEPLOYMENT_ID/deployment-archive
docker build -t wtl-be:$TIME .
docker run -d --name wtl-be-container -p 80:8080 \
  --log-driver=awslogs \
  --log-opt awslogs-region=ap-northeast-2 \
  --log-opt awslogs-group=dny-logs \
  --log-opt awslogs-stream=dny-be-stream \
  wtl-be:$TIME
docker image prune -f