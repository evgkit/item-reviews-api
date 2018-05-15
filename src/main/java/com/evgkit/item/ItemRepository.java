package com.evgkit.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

    @RestResource(rel = "title-contains", path = "containsTitle")
    Page<Item> findByTitleContaining(@Param("title") String title, Pageable page);
}
