#!/bin/bash
export AWS_REGION=$(aws ssm get-parameter --name "/wtl/region" --with-decryption --query "Parameter.Value" --output text)
export TIME=$(aws ssm get-parameter --name "/wtl/time" --query "Parameter.Value" --output text)