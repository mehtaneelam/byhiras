package com.byhiras.test.core.usercase;

import com.byhiras.test.core.entity.Bid;
import com.byhiras.test.core.entity.BidAmount;
import com.byhiras.test.core.entity.Item;
import com.byhiras.test.core.entity.User;
import com.byhiras.test.core.usercase.exceptions.ItemNotFoundException;
import com.byhiras.test.core.usercase.store.BidStore;
import com.byhiras.test.core.usercase.store.ItemStore;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class GetBidsForAnItemTest {

    private static ItemStore itemStoreMock;
    private static BidStore bidStoreMock;
    private static GetBidsForAnItem getBidsForAnItem;

    @BeforeClass
    public static void setUp() {
        itemStoreMock = Mockito.mock(ItemStore.class);
        bidStoreMock = Mockito.mock(BidStore.class);
        getBidsForAnItem = new GetBidsForAnItem(itemStoreMock,bidStoreMock);

    }

    @Test(expected = ItemNotFoundException.class)
    public void exceptionWhenItemNotFound(){
        givenItemDoesNotExist();

        getBidsForAnItem.getAllBidsForAnItem("SOME_CODE");
    }

    @Test
    public void givenValidItem_WhenAllBidsRequested_ThenReturnAllBids(){
        String itemCode = "SOME_CODE";
        List<Bid> expectedBids = new ArrayList<Bid>(){{
            add(new Bid(new User("USER-1"), new BidAmount(BigDecimal.TEN),new Item(itemCode,"SOME_DESCRIPTION")));
            add(new Bid(new User("USER-2"), new BidAmount(BigDecimal.ONE),new Item(itemCode,"SOME_DESCRIPTION")));
        }};

        givenItemExists(itemCode);

        when(bidStoreMock.getAllBids(itemCode)).thenReturn(expectedBids);
        List<Bid> actualBids = getBidsForAnItem.getAllBidsForAnItem("SOME_CODE");
        Assert.assertEquals(expectedBids.size(),actualBids.size());
        actualBids.forEach(bid -> Assert.assertEquals(bid.getBidItem().getItemCode(),itemCode));

    }

    private void givenItemDoesNotExist(){
        when(itemStoreMock.getItem("SOME_CODE")).thenReturn(null);
    }

    private void givenItemExists(String itemCode){
        when(itemStoreMock.getItem(itemCode)).thenReturn(new Item(itemCode,"SOME_DESCRIPTION"));
    }
}
