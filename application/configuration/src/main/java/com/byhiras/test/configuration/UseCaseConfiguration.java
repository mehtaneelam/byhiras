package com.byhiras.test.configuration;

import com.byhiras.test.core.usercase.GetBidsForAnItem;
import com.byhiras.test.core.usercase.GetItemsBidByUser;
import com.byhiras.test.core.usercase.GetWinningBidForAnItem;
import com.byhiras.test.core.usercase.RegisterBid;
import com.byhiras.test.core.usercase.store.BidStore;
import com.byhiras.test.core.usercase.store.ItemStore;
import com.byhiras.test.core.usercase.store.UserStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public GetBidsForAnItem getBidsForAnItem(ItemStore itemStore, BidStore bidStore) {
        return new GetBidsForAnItem(itemStore, bidStore);
    }

    @Bean
    public GetItemsBidByUser getItemsBidByUser(UserStore userStore) {
        return new GetItemsBidByUser(userStore);
    }

    @Bean
    public RegisterBid registerBid(UserStore userStore, ItemStore itemStore, BidStore bidStore,GetWinningBidForAnItem getWinningBidForAnItem){
        return new RegisterBid(userStore, itemStore ,bidStore, getWinningBidForAnItem);
    }

    @Bean
    public GetWinningBidForAnItem getWinningBidForAnItem(ItemStore itemStore, BidStore bidStore) {
        return new GetWinningBidForAnItem(itemStore,bidStore);
    }

}
