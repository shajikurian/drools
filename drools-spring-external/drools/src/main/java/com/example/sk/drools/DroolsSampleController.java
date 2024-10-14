package com.example.sk.drools;

import com.example.sk.drools.discount.BuyingInfo;
import com.example.sk.drools.discount.Discount;
import com.example.sk.drools.loan.Participant;
import com.example.sk.drools.loan.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 10/12/2024
 * {@code @authors} shaji
 */
@RestController()
@RequestMapping("/")
public class DroolsSampleController {

    @Autowired
    private DroolsService droolsService;

    @PostMapping("/bankservice/getrate")
    public Rate getRate(@RequestBody Participant request){
        return droolsService.getRate(request);
    }

    @PostMapping("/productservice/getdiscount")
    public Discount getRate2(@RequestBody BuyingInfo request){
        return droolsService.getDiscount(request);
    }

}