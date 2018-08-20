package com.byhiras.test.core.usercase;

import com.byhiras.test.core.entity.Bid;
import com.byhiras.test.core.entity.BidAmount;
import com.byhiras.test.core.entity.Item;
import com.byhiras.test.core.entity.User;
import com.byhiras.test.core.usercase.exceptions.CouldNotRegisterBidException;
import com.byhiras.test.core.usercase.exceptions.ItemNotFoundException;
import com.byhiras.test.core.usercase.exceptions.UserNotFoundException;
import com.byhiras.test.core.usercase.store.BidStore;
import com.byhiras.test.core.usercase.store.ItemStore;
import com.byhiras.test.core.usercase.store.UserStore;

import java.math.BigDecimal;
import java.util.Optional;

public class RegisterBid {

    private UserStore userStore;
    private ItemStore itemStore;
    private BidStore bidStore;
    private GetWinningBidForAnItem getWinningBidForAnItem;

    public RegisterBid(UserStore userStore, ItemStore itemStore, BidStore bidStore,GetWinningBidForAnItem getWinningBidForAnItem) {
        this.userStore = userStore;
        this.itemStore = itemStore;
        this.bidStore = bidStore;
        this.getWinningBidForAnItem = getWinningBidForAnItem;
    }

    public Bid registerBid(String username , String itemCode , BigDecimal amount){

        Item item = Optional.ofNullable(itemStore.getItem(itemCode))
                .orElseThrow(ItemNotFoundException::new);

        User user = Optional.ofNullable(userStore.getUser(username))
                .orElseThrow(UserNotFoundException::new);

        BidAmount bidAmount = new BidAmount(amount);

        Bid bid = new Bid(user,bidAmount,item);

        return registerBid(bid);

    }

    private synchronized Bid registerBid(Bid bid){
        // check if the new bid is higher to current winning and not by the same user having current winning bid
        Bid winningBidForAnItem = getWinningBidForAnItem.getWinningBid(bid.getBidItem().getItemCode());
        if(bid.getBidValue().getAmount().compareTo(winningBidForAnItem.getBidValue().getAmount()) != 1 || winningBidForAnItem.getBidder().equals(bid.getBidder())){
            throw new CouldNotRegisterBidException();
        }else{
            return bidStore.registerBid(bid);
        }
    }


}
