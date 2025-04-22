package io.dustin.salesmgmt.authorization.domain;

import lombok.Getter;

@Getter
public enum Permission {
    READ_ORDER("readOrder", "주문 조회 권한"),
    WRITE_ORDER("writeOrder", "주문 작성 권한"),
    READ_PAYMENT("readPayment", "결제 조회 권한"),
    WRITE_PAYMENT("writePayment", "결제 작성 권한"),
    READ_USER("readUser", "유저 조회 권한"),
    WRITE_USER("writeUser", "유저 관리 권한"),
    ADMIN("admin", "전체 관리자 권한");

    private final String key;
    private final String description;

    Permission(String key, String description) {
        this.key = key;
        this.description = description;
    }
}

