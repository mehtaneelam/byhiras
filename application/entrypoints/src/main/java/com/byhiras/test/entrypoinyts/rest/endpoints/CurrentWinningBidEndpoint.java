package com.byhiras.test.entrypoinyts.rest.endpoints;

import com.byhiras.test.core.entity.Bid;
import com.byhiras.test.core.usercase.GetWinningBidForAnItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrentWinningBidEndpoint {

    private GetWinningBidForAnItem getWinningBidForAnItem;

    public CurrentWinningBidEndpoint(GetWinningBidForAnItem getWinningBidForAnItem) {
        this.getWinningBidForAnItem = getWinningBidForAnItem;
    }

    @GetMapping(value = "winning_bid")
    public ResponseEntity<Object> getCurrentWinningBidByItem(@RequestParam("itemCode") String code) {
        // since item code here is string for SQL injection
        Bid bid = getWinningBidForAnItem.getWinningBid(code);
        return new ResponseEntity<>( bid, HttpStatus.OK);
    }

}
