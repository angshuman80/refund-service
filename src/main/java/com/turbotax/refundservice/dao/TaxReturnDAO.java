package com.turbotax.refundservice.dao;

import com.turbotax.refundservice.model.table.TaxReturn;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;

@Component
public class TaxReturnDAO {

    private final DynamoDbClient dynamoDbClient;
     // Constructor injection for DynamoDbClient
    // This allows for easier testing and better separation of concerns
    public TaxReturnDAO(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public TaxReturn getTaxReturn(String userId, int year) {
        // Define the key with partition key and sort key
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userId", AttributeValue.builder().s(userId).build());
        key.put("year", AttributeValue.builder().n(String.valueOf(year)).build());

        // Create the GetItemRequest
        GetItemRequest request = GetItemRequest.builder()
                .tableName("TaxReturn")
                .key(key)
                .build();

        // Execute the request
        GetItemResponse response = dynamoDbClient.getItem(request);

        // Return the item if it exists
        return response.hasItem() ? prepareResponse(response.item()) : null;
    }

    private TaxReturn prepareResponse(Map<String, AttributeValue> item) {
        if (item == null || item.isEmpty()) {
            return null; // No item found
        }

        TaxReturn taxReturn = new TaxReturn();
        taxReturn.setUserId(item.get("userId").s());
        taxReturn.setYear(Integer.parseInt(item.get("year").n()));
        taxReturn.setSsn(item.get("ssn").s());
        taxReturn.setLast4(Integer.parseInt(item.get("last4").n()));
        taxReturn.setRefundAmount(Double.parseDouble(item.get("refundAmount").n()));
        taxReturn.setRefundStatus(item.get("refundStatus").s());

        return taxReturn;
    }


    public void updateTaxReturn(TaxReturn taxReturn) {

        // Define the table name
        String tableName = "TaxReturn";

        // Define the item to insert
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("userId", AttributeValue.builder().s(taxReturn.getUserId()).build());
        item.put("year", AttributeValue.builder().n(""+taxReturn.getYear()).build());
        item.put("ssn", AttributeValue.builder().s(taxReturn.getSsn()).build());
        item.put("last4", AttributeValue.builder().n(""+taxReturn.getLast4()).build());
        item.put("refundAmount", AttributeValue.builder().n(""+taxReturn.getRefundAmount()).build());
        item.put("refundStatus", AttributeValue.builder().s(taxReturn.getRefundStatus()).build());
        item.put("fillingDate", AttributeValue.builder().s(taxReturn.getFillingDate()).build());
        item.put("refundDate", AttributeValue.builder().s(taxReturn.getRefundDate()).build());

        // Create the PutItemRequest
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        // Execute the request
        PutItemResponse response = dynamoDbClient.putItem(request);

    }
}
