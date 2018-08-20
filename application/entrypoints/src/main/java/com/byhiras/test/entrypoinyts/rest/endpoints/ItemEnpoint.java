package com.byhiras.test.entrypoinyts.rest.endpoints;

import com.byhiras.test.core.entity.Bid;
import com.byhiras.test.core.entity.Item;
import com.byhiras.test.core.usercase.GetItemsBidByUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemEnpoint {

    private GetItemsBidByUser getAllBidsForAnItem;

    public ItemEnpoint(GetItemsBidByUser getAllBidsForAnItem) {
        this.getAllBidsForAnItem = getAllBidsForAnItem;
    }

    @GetMapping(value = "items")
    public ResponseEntity<Object> getItemsBidByUser(@RequestParam("username") String username) {
        // since item code here is string for SQL injection
        List<Item> items = getAllBidsForAnItem.getItemsBidByUser(username);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
