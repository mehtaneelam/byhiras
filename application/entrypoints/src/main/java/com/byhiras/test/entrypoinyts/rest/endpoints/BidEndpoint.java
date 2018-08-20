package com.byhiras.test.entrypoinyts.rest.endpoints;


import com.byhiras.test.core.entity.Bid;
import com.byhiras.test.core.usercase.GetBidsForAnItem;
import com.byhiras.test.core.usercase.RegisterBid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
public class BidEndpoint {

    private GetBidsForAnItem getBidsForAnItem;
    private RegisterBid registerBid;

    public BidEndpoint(GetBidsForAnItem getBidsForAnItem, RegisterBid registerBid) {
        this.getBidsForAnItem = getBidsForAnItem;
        this.registerBid = registerBid;
    }

    @GetMapping(value = "bids")
    public ResponseEntity<Object> getBidsByItem(@RequestParam("item_code") String itemCode) {
        // since item code here is string for SQL injection

        List<Bid> bids = getBidsForAnItem.getAllBidsForAnItem(itemCode);
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }

    @PostMapping(value = "bids", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerBid(@RequestBody Map<String, Object> body) {
        // can add various validation here
        // real example would have a dto; as the number parameter here a few
        Bid bid = registerBid.registerBid(body.get("username").toString(), body.get("item_code").toString(), new BigDecimal(body.get("amount").toString()));
        return new ResponseEntity<>( bid, HttpStatus.CREATED);
    }

}

