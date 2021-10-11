package com.companyName.project.contoller.base;

import com.companyName.project.domain.base.Item;
import com.companyName.project.exception.ResourceNotFoundException;
import com.companyName.project.repository.base.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://spring.io/guides/tutorials/rest/
// https://dzone.com/articles/how-to-create-rest-api-with-spring-boot
// https://howtodoinjava.com/spring-boot2/rest/rest-api-example/
// https://www.tutorialspoint.com/spring_boot/spring_boot_building_restful_web_services.htm


@RestController
@RequestMapping("/api/v1")
public class ItemController {

    private final ItemRepository repository;

    ItemController(ItemRepository repository) {
        this.repository = repository;
    }


    /**
     * Get all items list.
     *
     * @return the list
     */
    @GetMapping(path = "/items", produces = "application/json")
    public List<Item> getAll() {
        return this.repository.findAll();
    }

    /**
     * Gets items by id.
     *
     * @param itemId the item id
     * @return the users by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable(value = "id") Long itemId)
            throws ResourceNotFoundException {

        Item item = this.repository
                        .findById(itemId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + itemId));
        return ResponseEntity.ok().body(item);

    }


    /**
     * Create item.
     *
     * @param item the item
     * @return the item
     */
    // @PostMapping("/items")
    @PostMapping(path= "/items", consumes = "application/json", produces = "application/json")
    public Item create(@Valid @RequestBody Item item) {
        return this.repository.save(item);
    }


    /**
     * Update item response entity.
     *
     * @param itemId the item id
     * @param itemDetails the item details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/item/{id}")
    public ResponseEntity<Item> update(
            @PathVariable(value = "id") Long itemId, @Valid @RequestBody Item itemDetails)
            throws ResourceNotFoundException {

        Item item = this.repository
                        .findById(itemId)
                        .orElseThrow(() -> new ResourceNotFoundException("Item not found on :: " + itemId));

        item.setCode(itemDetails.getCode());
        item.setDescription(itemDetails.getDescription());
        item.setLastUpdateDateTime(new Date());
        final Item updatedItem = this.repository.save(item);
        return ResponseEntity.ok(updatedItem);
    }



    /**
     * Delete item map.
     *
     * @param itemId the item id
     * @return the map
     * @throws ResourceNotFoundException the exception
     */
    @DeleteMapping("/item/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long itemId) throws ResourceNotFoundException {
        Item item =
                this.repository
                        .findById(itemId)
                        .orElseThrow(() -> new ResourceNotFoundException("Item not found on :: " + itemId));

        this.repository.delete(item);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
