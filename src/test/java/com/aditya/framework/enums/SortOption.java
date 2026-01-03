package com.aditya.framework.enums;

public enum SortOption {
    NAME_ASC("Name (A to Z)", "NAME_ASC"),
    NAME_DESC("Name (Z to A)", "NAME_DESC"),
    PRICE_ASC("Price (low to high)", "PRICE_ASC"),
    PRICE_DESC("Price (high to low)", "PRICE_DESC");

    private final String uiText;
    private final String sortType;

    SortOption(String uiText, String sortType) {
        this.uiText = uiText;
        this.sortType = sortType;
    }

    public String uiText() { return uiText; }
    public String sortType() { return sortType; }

    public static SortOption fromSortType(String sortType) {
        for (SortOption o : values()) {
            if (o.sortType.equalsIgnoreCase(sortType)) return o;
        }
        throw new IllegalArgumentException("Unknown sortType: " + sortType);
    }
}