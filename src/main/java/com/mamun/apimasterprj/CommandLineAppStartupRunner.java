package com.mamun.apimasterprj;

import com.mamun.apimasterprj.domain.base.Item;
import com.mamun.apimasterprj.repository.base.ItemRepository;
import com.mamun.apimasterprj.test.DbSourceNativeSqlTest;
import com.mamun.apimasterprj.test.JdbcTemplateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;


@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    DbSourceNativeSqlTest dbSourceNativeSqlTest;

    @Autowired
    JdbcTemplateTest jdbcTemplateTest;


    public void callExternalMethods() throws Exception {
        dbSourceNativeSqlTest.cleanupDatasource();
    }


    void runJDBC() {

        log.info("Creating tables for testing...");

        jdbcTemplate.execute("DROP TABLE IF EXISTS books;");
        jdbcTemplate.execute("CREATE TABLE books(" +
                "id SERIAL, name VARCHAR(255), price NUMERIC(15, 2))");

        List<Item> books = Arrays.asList(
                new Item("CLP-0001", "Thinking in Java"),
                new Item("CLP-0002", "Mkyong in Java"),
                new Item("CLP-0003", "Getting Clojure"),
                new Item("CLP-0004", "Head First Android Development")
        );

        log.info("[SAVE]");
//        int c = 0;
//        books.forEach(book -> {
//            log.info("Saving...{}", book.getCode());
//            itemRepository.save(book);
//        });


        // find all
        log.info("[FIND_ALL] {}", itemRepository.findAll());


    }




    @Override
    public void run(String... args) throws Exception {
        this.callExternalMethods();
        this.runJDBC();
        jdbcTemplateTest.testJdbcTemplate();
    }


}
