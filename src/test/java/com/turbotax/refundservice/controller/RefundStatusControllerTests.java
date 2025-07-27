package com.turbotax.refundservice.controller;


import com.turbotax.refundservice.exception.ResourceNotFoundException;
import com.turbotax.refundservice.model.RefundStatusResponse;
import com.turbotax.refundservice.service.RefundService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
@WebMvcTest(RefundStatusController.class)
public class RefundStatusControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RefundService refundService;

    @Test
    public void testGetRefundStatus() throws Exception {
        String userId = "testUser";
        Mockito.when(refundService.getRefundStatus(userId)).thenReturn(new RefundStatusResponse());

        mockMvc.perform(get("/v1/tax/refund/status/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testGetRefundStatusWithInvalidUserId() throws Exception {
        String userId = "testUser1";
        // Simulate an exception for invalid user ID
        Mockito.when(refundService.getRefundStatus(userId)).thenThrow(new ResourceNotFoundException("Tax return not found for user: " + userId));

        mockMvc.perform(get("/v1/tax/refund/status/user/{userId}", userId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"));
    }
}
