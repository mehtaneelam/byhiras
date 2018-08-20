package com.byhiras.test.configuration;

import com.byhiras.test.core.usercase.GetBidsForAnItem;
import com.byhiras.test.core.usercase.GetItemsBidByUser;
import com.byhiras.test.core.usercase.GetWinningBidForAnItem;
import com.byhiras.test.core.usercase.RegisterBid;
import com.byhiras.test.entrypoinyts.rest.endpoints.BidEndpoint;
import com.byhiras.test.entrypoinyts.rest.endpoints.CurrentWinningBidEndpoint;
import com.byhiras.test.entrypoinyts.rest.endpoints.ItemEnpoint;
import com.byhiras.test.entrypoinyts.rest.exception.DefaultExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfiguration {

    @Bean
    public BidEndpoint bidEndpoint(GetBidsForAnItem getBidsForAnItem, RegisterBid registerBid){
        return new BidEndpoint(getBidsForAnItem,registerBid);
    }

    @Bean
    public ItemEnpoint itemEndpoint(GetItemsBidByUser getAllBidsForAnItem){
        return new ItemEnpoint(getAllBidsForAnItem);
    }

    @Bean
    public CurrentWinningBidEndpoint currentWinningBidEndpoint(GetWinningBidForAnItem getWinningBidForAnItem){
        return new CurrentWinningBidEndpoint(getWinningBidForAnItem);
    }

    @Bean
    public DefaultExceptionHandler defaultExceptionHandler(){
        return new DefaultExceptionHandler();
    }

}
