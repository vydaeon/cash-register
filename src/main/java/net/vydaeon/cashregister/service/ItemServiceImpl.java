package net.vydaeon.cashregister.service;

import net.vydaeon.cashregister.dao.ItemRepository;
import net.vydaeon.cashregister.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default implementation of {@link ItemService}.
 *
 * @author Brad Bottjen
 */
@Service
class ItemServiceImpl implements ItemService {

    //NOTE: this services isn't really needed now, but serves as a point for later expansion

    private final ItemRepository itemRepository;

    @Autowired
    ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.getItems();
    }
}
