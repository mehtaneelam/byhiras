package com.byhiras.test.core.usercase.store;

import com.byhiras.test.core.entity.Item;

public interface ItemStore {

    Item getItem(String itemCode);
}
