package com.byhiras.test.core.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Item {

    private String itemCode; // item code is unique
    private String description; // description of the item

    public Item(String itemCode, String description) {
        this.itemCode = itemCode;
        this.description = description;
    }

    public String getItemCode() {
        return itemCode;
    }



    public String getDescription() {
        return description;
    }



    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (itemCode != null ? !itemCode.equals(item.itemCode) : item.itemCode != null) return false;
        return description != null ? description.equals(item.description) : item.description == null;
    }

    @Override
    public int hashCode() {
        int result = itemCode != null ? itemCode.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
