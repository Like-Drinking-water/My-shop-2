package com.huanleichen.my.shop.commons.persistence;

import lombok.Data;

@Data
public class BaseTreeEntity<T extends BaseEntity> extends BaseEntity {
    private T parent;
    private Boolean isParent;
}
