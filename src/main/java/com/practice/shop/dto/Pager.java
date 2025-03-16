package com.practice.shop.dto;

import lombok.Getter;

@Getter
public class Pager {
    private final Integer totalPage;
    private final Integer currentPage;
    private final Integer indexSize;

    private Integer minIndex;
    private Integer maxIndex;

    public Pager(Integer totalPage, Integer currentPage, Integer indexSize) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.indexSize = indexSize;
        setIndex();
    }

    private void setIndex() {
        minIndex = (currentPage - 1) / indexSize * indexSize + 1;
        if(minIndex + indexSize - 1 < totalPage) {
            maxIndex = minIndex + indexSize - 1;
        } else {
            maxIndex = totalPage < minIndex ? minIndex : totalPage;
        }
    }
}
