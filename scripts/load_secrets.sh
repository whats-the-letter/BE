#!/bin/bash
export TIME=$(aws ssm get-parameter --name "/wtl/time" --query "Parameter.Value" --output text --region ap-northeast-2)