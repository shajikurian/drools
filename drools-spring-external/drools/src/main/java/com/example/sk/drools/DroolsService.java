package com.example.sk.drools;

import com.example.sk.drools.discount.BuyingInfo;
import com.example.sk.drools.discount.Discount;
import com.example.sk.drools.loan.Participant;
import com.example.sk.drools.loan.Rate;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 10/12/2024
 * {@code @authors} shaji
 */
@Service
@Slf4j
public class DroolsService {
    @Autowired
    private KieContainer kieContainer;

    public Rate getRate(Participant applicantRequest) {
        Rate rate = new Rate();
        KieSession kieSession = kieContainer.newKieSession("ksession1");
        kieSession.setGlobal("rate", rate);
        kieSession.insert(applicantRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return rate;
    }

    public Discount getDiscount(BuyingInfo buyingInfo) {
        Discount discount = new Discount();
        KieSession kieSession = kieContainer.newKieSession("ksession2");
        kieSession.setGlobal("discount", discount);
        kieSession.insert(buyingInfo);
        kieSession.fireAllRules();
        kieSession.dispose();
        return discount;
    }

}
