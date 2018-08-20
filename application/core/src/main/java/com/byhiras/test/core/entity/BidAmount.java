package com.byhiras.test.core.entity;

import com.byhiras.test.core.usercase.exceptions.InvalidBidAmountException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class BidAmount {

    // assumed same currency across the application
    private static final Currency DEFAULT_CURRENCY = Currency.getInstance("GBP");
    private BigDecimal amount;
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;


    public BidAmount(BigDecimal amount) {

        if(amount !=null && amount.compareTo(BigDecimal.ZERO) == 1){ // bid amount cannot be negative or zero
            this.amount = amount.setScale(DEFAULT_CURRENCY.getDefaultFractionDigits(), DEFAULT_ROUNDING);;
        }else {
            throw new InvalidBidAmountException();
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }

    // TODO getUser
    public void setAmount(BigDecimal amount) {
        if(amount !=null && amount.compareTo(BigDecimal.ZERO) >= 0){
            this.amount = amount;
        }
    }
}
