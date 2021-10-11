package com.companyName.project.repository.base;

import com.companyName.project.domain.base.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item getById( Long id);
    Item findByCode(String code);


}
