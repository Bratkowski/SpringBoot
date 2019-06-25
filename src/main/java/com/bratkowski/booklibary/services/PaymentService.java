package com.bratkowski.booklibary.services;

import com.bratkowski.booklibary.domain.Hire;
import com.bratkowski.booklibary.domain.Payment;
import com.bratkowski.booklibary.domain.User;
import com.bratkowski.booklibary.repository.HireRepository;
import com.bratkowski.booklibary.repository.PaymentRepository;
import com.bratkowski.booklibary.repository.UserRepositoryJpa;
import com.bratkowski.booklibary.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    HireRepository hireRepository;

    @Autowired
    UserRepositoryJpa userRepositoryJpa;

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

    public Map<User, BigDecimal> getUserWithNegativeBalance () {
        Map<User, BigDecimal> userMap = new HashMap<>();

        List<User> users = userRepositoryJpa.findAll();

        for (User user:
             users) {
            BigDecimal balance = getPaymentSumByUser(user.getId()).subtract(getPenaltySumByUser(user.getId()));

            System.out.println("USERID: " + user.getId());
            System.out.println("SALDO: " + balance);

            if(balance.compareTo(BigDecimal.ZERO) < 0)
                userMap.put(user, balance);
        }

        System.out.println("COUNT: " + userMap.size());
        return userMap;
    }

    public void pay(Integer userId) {
        Payment payment = new Payment();
        User user = userRepositoryJpa.getOne(userId);

        payment.setUser(user);

        BigDecimal amount =  getPenaltySumByUser(user.getId()).subtract(getPaymentSumByUser(user.getId()));

        payment.setAmount(amount);
        payment.setDate(new Date());
        paymentRepository.save(payment);
    }
}
