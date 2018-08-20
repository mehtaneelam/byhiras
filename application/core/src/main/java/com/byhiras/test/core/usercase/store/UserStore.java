package com.byhiras.test.core.usercase.store;


import com.byhiras.test.core.entity.Item;
import com.byhiras.test.core.entity.User;

import java.util.List;

public interface UserStore {

    User getUser(String username);
    List<Item> getItemsBidByUser(String username);
}
