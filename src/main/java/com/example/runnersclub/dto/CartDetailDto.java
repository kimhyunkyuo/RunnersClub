package com.example.runnersclub.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter @Setter
public class CartDetailDto {

    private Long cartItemId;

    private String itemNm;

    private int price;

    private int count;

    private String imgUrl;

    private CartDetailDto(Long cartItemId, String itemNm, int price, int count, String imgUrl){
        this.cartItemId = cartItemId;
        this.itemNm = itemNm;
        this.price = price;
        this.count = count;
        this.imgUrl = imgUrl;
    }
}
