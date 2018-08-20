package com.byhiras.test.core.usercase;

import com.byhiras.test.core.entity.Bid;
import com.byhiras.test.core.usercase.exceptions.ItemNotFoundException;
import com.byhiras.test.core.usercase.store.BidStore;
import com.byhiras.test.core.usercase.store.ItemStore;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class GetWinningBidForAnItem {


    private ItemStore itemStore;
    private BidStore bidStore;

    public GetWinningBidForAnItem(ItemStore itemStore, BidStore bidStore) {
        this.itemStore = itemStore;
        this.bidStore = bidStore;
    }

    public Bid getWinningBid(String itemCode){
        Optional.ofNullable(itemStore.getItem(itemCode))
                .orElseThrow(ItemNotFoundException::new);
        /* a winning bid is the one with maximum bid amount
        the objective of keeping the logic of winning bid in the application
        we don't rely on the underlying store to have the logic to calculate max
        the below approach may impact the performance
        depending one the actually performance need  of the application this can be optimized
        BENEFITS of doing here the underlying db is nosql .. or we may have multi currency */
        List<Bid> bids=  bidStore.getAllBids(itemCode);
        //if no bid then we return null
        Bid winningBid = bids.stream()
                .max(Comparator.comparing(bid -> bid.getBidValue().getAmount())).orElse(null);
        return winningBid;
    }


}
