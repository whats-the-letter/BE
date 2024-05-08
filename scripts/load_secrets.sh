#!/bin/bash
export AWS_REGION=$(aws ssm get-parameter --name "/aws/myregion" --with-decryption --query "Parameter.Value" --output text)
export TIME=$(aws ssm get-parameter --name "/app/time" --query "Parameter.Value" --output text)