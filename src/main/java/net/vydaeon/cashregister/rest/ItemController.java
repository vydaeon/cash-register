package net.vydaeon.cashregister.rest;

import net.vydaeon.cashregister.domain.Item;
import net.vydaeon.cashregister.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * {@link RestController} for {@link Item}(s).
 *
 * @author Brad Bottjen
 */
@RestController
@RequestMapping("/items")
class ItemController {

    private final ItemService itemService;

    @Autowired
    ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = GET)
    public List<Item> getItems() {
        return itemService.getItems();
    }
}
