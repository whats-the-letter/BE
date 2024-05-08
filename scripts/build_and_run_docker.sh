#!/bin/bash
cd /opt/codedeploy-agent/deployment-root/$DEPLOYMENT_GROUP_NAME/$DEPLOYMENT_ID/deployment-archive
docker build -t wtl-be:$TIME .
docker run -d --name wtl-be-container -p 80:8080 \
  --log-driver=awslogs \
  --log-opt awslogs-region=$AWS_REGION \
  --log-opt awslogs-group=dny-logs \
  --log-opt awslogs-stream=dny-be-stream \
  wtl-be:$TIME
docker image prune -f