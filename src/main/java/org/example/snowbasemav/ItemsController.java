package org.example.snowbasemav;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://snowflakestudios.netlify.app/")
@RequestMapping("/api/items")
public class ItemsController {

    @Autowired
    private ItemsRepository itemsRepository;

    // Get all items
    @GetMapping
    public List<Items> getAllItems() {
        return itemsRepository.findAll();
    }

    @PostMapping
    public Items addNewItem(@RequestBody Items newItem) {
        return itemsRepository.save(newItem);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemsRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Optional<Items> getItemById(@PathVariable Long id) {
        return itemsRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Items updateItem(@PathVariable Long id, @RequestBody Items updatedItem) {
        return itemsRepository.findById(id)
                .map(item -> {
                    item.setName(updatedItem.getName());
                    item.setDescription(updatedItem.getDescription());
                    item.setImageUrl(updatedItem.getImageUrl());
                    item.setQuantity(updatedItem.getQuantity());
                    item.setValue(updatedItem.getValue());
                    return itemsRepository.save(item);
                })
                .orElse(null);
    }
}