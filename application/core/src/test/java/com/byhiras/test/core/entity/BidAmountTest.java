package com.byhiras.test.core.entity;

import com.byhiras.test.core.usercase.exceptions.InvalidBidAmountException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class BidAmountTest {

    @Test(expected = InvalidBidAmountException.class)
    public void bidCannotBeNegative(){
        BidAmount bidAmount = new BidAmount(BigDecimal.valueOf(-1));
    }

    @Test(expected = InvalidBidAmountException.class)
    public void bidCannotZero(){
        BidAmount bidAmount = new BidAmount(BigDecimal.ZERO);
    }

    @Test
    public void bidCanBeAnyPositiveAmount(){
        BidAmount  bidAmount = new BidAmount(BigDecimal.TEN);
        BigDecimal expected = BigDecimal.TEN.setScale(Currency.getInstance("GBP").getDefaultFractionDigits(),RoundingMode.HALF_EVEN);
        Assert.assertEquals(expected, bidAmount.getAmount() );

    }
}
