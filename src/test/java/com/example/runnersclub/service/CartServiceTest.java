package com.example.runnersclub.service;

import com.example.runnersclub.constant.ItemSellStatus;
import com.example.runnersclub.dto.CartItemDto;
import com.example.runnersclub.entity.Cart;
import com.example.runnersclub.entity.CartItem;
import com.example.runnersclub.entity.Item;
import com.example.runnersclub.entity.Member;
import com.example.runnersclub.repository.CartItemRepository;
import com.example.runnersclub.repository.ItemRepository;
import com.example.runnersclub.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class CartServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository   memberRepository;

    @Autowired
    CartService cartService;

    @Autowired
    CartItemRepository  cartItemRepository;

    public Item saveItem(){
        Item item = new Item();
        item.setItemNm("테스트상품");
        item.setPrice(100000);
        item.setItemDetail("테스트 상품입니다.");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(1000);
        return itemRepository.save(item);
    }

    public Member saveMember(){
        Member member = new Member();
        member.setEmail("test@test.com");
        return memberRepository.save(member);
    }

    @Test
    @DisplayName("장바구니 담기 테스트")
    public void addCart(){
        Item item = saveItem();
        Member member = saveMember();

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setCount(5);
        cartItemDto.setItemId(item.getId());

        Long cartItemId = cartService.addCart(cartItemDto, member.getEmail());
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(item.getId(), cartItem.getItem().getId());
        assertEquals(cartItemDto.getCount(), cartItem.getCount());
    }

}