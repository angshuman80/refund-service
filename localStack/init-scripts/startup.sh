#!/bin/bash

# Create a DynamoDB table named 'TaxReturn' with primary key 'userId', sort key 'year',
# and additional attributes 'ssn', 'last4', and 'refundAmount'
awslocal dynamodb create-table \
    --table-name TaxReturn \
    --attribute-definitions \
        AttributeName=userId,AttributeType=S \
        AttributeName=year,AttributeType=N \
    --key-schema \
        AttributeName=userId,KeyType=HASH \
        AttributeName=year,KeyType=RANGE \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --stream-specification StreamEnabled=true,StreamViewType=NEW_AND_OLD_IMAGES

echo "DynamoDB table 'TaxReturn' created successfully."


# Insert a dummy item into the 'TaxReturn' table with the generated UUID
awslocal dynamodb put-item \
    --table-name TaxReturn \
    --item "{
        \"userId\": {\"S\": \"user123\"},
        \"year\": {\"N\": \"2024\"},
        \"ssn\": {\"S\": \"123-45-6789\"},
        \"last4\": {\"N\": \"6789\"},
        \"refundAmount\": {\"N\": \"1500.75\"},
        \"refundStatus\": {\"S\": \"PENDING\"},
         \"fillingDate\": {\"S\": \"2024-03-05\"}
    }"

awslocal dynamodb put-item \
    --table-name TaxReturn \
    --item "{
        \"userId\": {\"S\": \"user124\"},
        \"year\": {\"N\": \"2024\"},
        \"ssn\": {\"S\": \"123-45-6799\"},
        \"last4\": {\"N\": \"6799\"},
        \"refundAmount\": {\"N\": \"1500.75\"},
        \"refundStatus\": {\"S\": \"NA\"},
         \"fillingDate\": {\"S\": \"2024-03-05\"}
    }"

echo "Dummy data inserted into 'TaxReturn' table successfully."
