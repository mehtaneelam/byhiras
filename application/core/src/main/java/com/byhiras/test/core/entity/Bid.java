package com.byhiras.test.core.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class Bid {
    private Integer id;
    private User bidder;
    private BidAmount bidAmount;
    private Item bidItem;

    public Bid(User bidder, BidAmount bidAmount, Item bidItem) {
        this.bidder = bidder;
        this.bidAmount = bidAmount;
        this.bidItem = bidItem;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (!bidder.equals(bid.bidder)) return false;
        return bidItem.equals(bid.bidItem);
    }

    @Override
    public int hashCode() {
        int result = bidder.hashCode();
        result = 31 * result + bidItem.hashCode();
        return result;
    }

    public User getBidder() {
        return bidder;
    }

    public BidAmount getBidValue() {
        return bidAmount;
    }

    public Item getBidItem() {
        return bidItem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
