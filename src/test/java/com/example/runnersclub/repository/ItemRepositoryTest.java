package com.example.runnersclub.repository;

import com.example.runnersclub.constant.ItemSellStatus;
import com.example.runnersclub.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트 코드")
    public void createItemTest() {
            Item item = new Item();
            item.setItemNm("테스트 상품명입니다" );
            item.setPrice(50000);
            item.setItemDetail("테스트 상품 상세설명입니다.");
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(50);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item saveItem = itemRepository.save(item);
            System.out.println(saveItem.toString());
        }
        public void createItemList() {
        for (int i = 1; i <= 10; i++){
            Item item = new Item();
            item.setItemNm("테스트 상품명입니다" + i);
            item.setPrice(50000+i);
            item.setItemDetail("테스트 상품 상세설명입니다" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(50);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item saveItem = itemRepository.save(item);
            System.out.println(saveItem.toString());
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemListTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품명입니다3");
        for(Item item : itemList){
            System.out.println(item.toString());
        }

    }
    @Test
    @DisplayName("상품명 OR 상품상세설명 조회 테스트")
    public void findByItemListOrItemDetailTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품명입니다3","테스트 상품 상세설명입니다8");
        for(Item item : itemList){
            System.out.println(item.toString());
        }

    }
    @Test
    @DisplayName("가격 lestThan 테스트")
    public void findByLessThenTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(50008);
        for(Item item : itemList){
            System.out.println(item.toString());
        }

    }
}