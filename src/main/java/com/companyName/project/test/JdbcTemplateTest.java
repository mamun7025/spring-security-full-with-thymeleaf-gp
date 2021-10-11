package com.companyName.project.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.companyName.project.domain.base.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class JdbcTemplateTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Item> findAllItem() {

        List<Item> customers = new ArrayList<>();

        String sql = "SELECT * FROM BAS_ITEM";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map row : rows) {

            Item obj = new Item();
            obj.setId(   ( (long) row.get("ID"))  );
            obj.setCode((String) row.get("CODE"));
            // Spring returns BigDecimal, need convert
            obj.setDescription(((String) row.get("DESCRIPTION")));
//            obj.setSellingPrice(((BigDecimal) row.get("SELLING_PRICE")));
//            obj.setCreationDateTime(((Timestamp) row.get("CREATED_DATE")).toLocalDateTime());
            customers.add(obj);

        }

        return customers;
    }



    public void testJdbcTemplate(){

        List<Item> allItem = this.findAllItem();

        String jsonString = null;
        String json = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(allItem);
            json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(allItem);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);
        System.out.println(json);

    }



}
