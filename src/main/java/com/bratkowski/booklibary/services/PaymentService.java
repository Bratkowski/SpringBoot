package com.bratkowski.booklibary.services;

import com.bratkowski.booklibary.domain.Hire;
import com.bratkowski.booklibary.repository.HireRepository;
import com.bratkowski.booklibary.repository.PaymentRepository;
import com.bratkowski.booklibary.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    HireRepository hireRepository;

    public BigDecimal getPaymentSumByUser (Integer userId){
        BigDecimal payment = paymentRepository.sumPaymentByUser(userId);

        return payment != null ? payment : new BigDecimal(0);
    }

    public BigDecimal getPenaltySumByUser(Integer userId) {
        List<Hire> hires = hireRepository.findExpiredHiresByUser(userId);

        BigDecimal penaltySum = new BigDecimal(0);

        for (Hire hire: hires) {
            BigDecimal hirePenalty = hire.getDailyPenalty()
                    .multiply(new BigDecimal(DateUtils.daysDiff(hire.getRealGiveBackDate(), (hire.getPlannedGiveBackDate()))));

        penaltySum = penaltySum.add(hirePenalty);
        }

        return penaltySum;
    }
}
