package com.turbotax.refundservice.service;

import com.turbotax.refundservice.dao.TaxReturnDAO;
import com.turbotax.refundservice.model.RefundStatus;
import com.turbotax.refundservice.model.RefundStatusResponse;
import com.turbotax.refundservice.model.Status;
import com.turbotax.refundservice.model.table.TaxReturn;
import com.turbotax.refundservice.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.time.LocalDate;

@Service
public class RefundService {
    private final IrsService irsClient;
    private final TaxReturnDAO taxReturnDAO;
    private final RefundStatusPredictionService aiPredictionClient;


    public RefundService(IrsService irsClient,
                         RefundStatusPredictionService aiPredictionClient, TaxReturnDAO taxReturnDAO) {
        this.irsClient = irsClient;
        this.taxReturnDAO = taxReturnDAO;
        this.aiPredictionClient = aiPredictionClient;
    }

    public RefundStatusResponse getRefundStatus(String userId) {

        // get current year
        int currentYear = LocalDate.now().getYear();
        // Fetch tax return for the user
        TaxReturn taxReturn = taxReturnDAO.getTaxReturn(userId, currentYear);

        if (taxReturn != null) {
            // add switch case for refund status
           return getRefundStatus(taxReturn);
        }
        return null;

    }

    public RefundStatusResponse getRefundStatus(TaxReturn taxReturn) {
        if (taxReturn != null) {
           RefundStatusResponse response= prepareResponse(taxReturn);
            // add switch case for refund status
            switch (Status.valueOf(taxReturn.getRefundStatus().toUpperCase())) {
                case NA:
                    return response;
                case DISPUTED:
                    response.setDisputeReason("Refund Disputed");
                    return response;
                case RECEIVED:
                    response.setExpectedDepositDate(DateUtils.dateyyyymmdd(5));
                    return response;
                case PENDING:
                    return processPendingStatus(taxReturn,response);
                default:
                    // Default case for pending or unknown status
                    break;
            }
            // If refund status is not recognized, return a default response
        }

        // If no tax return found, return a default response
        return null;
    }

    private RefundStatusResponse processPendingStatus(TaxReturn taxReturn, RefundStatusResponse response) {
        // Predict refund date using AI model
        RefundStatus refundStatus = irsClient.
                fetchRefundStatus(taxReturn.getSsn(), taxReturn.getLast4());
        if (refundStatus.isAvailable()) {
            response.setRefundStatus(Status.RECEIVED.name());
            response.setExpectedDepositDate(refundStatus.getExpectedDate());
            taxReturn.setRefundStatus(Status.RECEIVED.name());
            taxReturn.setRefundDate(refundStatus.getExpectedDate());
        } else {
            // Predict refund date using AI model
            if(taxReturn.getRefundDate() == null) {
                Integer predictedDays = aiPredictionClient.predictRefundDays();
                String refundDate = DateUtils.addDaysToDate(taxReturn.getFillingDate(), predictedDays);
                taxReturn.setRefundDate(refundDate);
                response.setTaxRefundDate(refundDate);
            }

        }
        taxReturnDAO.updateTaxReturn(taxReturn);
        return response;
    }

    /**
     * Prepares the RefundStatusResponse from the TaxReturn object.
     *
     * @param taxReturn The TaxReturn object containing refund details.
     * @return RefundStatusResponse with refund status and details.
     */

    private RefundStatusResponse prepareResponse(TaxReturn taxReturn) {
       RefundStatusResponse response = new RefundStatusResponse();
       response.setRefundStatus(taxReturn.getRefundStatus());
       response.setTaxReturnYear(taxReturn.getYear());
       response.setLast4ssn(taxReturn.getLast4());
       response.setReturnAmount(taxReturn.getRefundAmount());
       return response;
    }
}
