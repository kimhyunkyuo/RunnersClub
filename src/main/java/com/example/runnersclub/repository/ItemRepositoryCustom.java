package com.example.runnersclub.repository;

import com.example.runnersclub.dto.ItemSearchDto;
import com.example.runnersclub.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ItemRepositoryCustom {
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
