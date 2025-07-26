package com.turbotax.refundservice.controller;

import com.turbotax.refundservice.model.RefundStatusResponse;
import com.turbotax.refundservice.service.RefundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/tax/refund/status")
public class RefundStatusController {

    private final RefundService refundService;

    public RefundStatusController(RefundService refundService) {
        this.refundService = refundService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<RefundStatusResponse> getRefundStatus(@PathVariable String userId) {
        RefundStatusResponse response = refundService.getRefundStatus("1234");
        return ResponseEntity.ok(response);
    }
}
