package com.example.runnersclub.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

import java.security.Principal;
import java.util.List;

@Getter
@Setter
public class CartOrderDto {

    private Long cartItemId;

    private List<CartOrderDto> cartOrderDtoList;

}
