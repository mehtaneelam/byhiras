package com.byhiras.test.core.usercase;

import com.byhiras.test.core.entity.Item;
import com.byhiras.test.core.entity.User;
import com.byhiras.test.core.usercase.exceptions.UserNotFoundException;
import com.byhiras.test.core.usercase.store.UserStore;

import java.util.List;
import java.util.Optional;

public class GetItemsBidByUser {

    private UserStore userStore;


    public GetItemsBidByUser(UserStore userStore) {
        this.userStore = userStore;

    }

    public List<Item> getItemsBidByUser(String username){
        Optional.ofNullable(userStore.getUser(username))
                .orElseThrow(UserNotFoundException::new);
        return userStore.getItemsBidByUser(username);
    }


}
