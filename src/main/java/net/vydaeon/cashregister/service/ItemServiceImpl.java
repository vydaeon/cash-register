package net.vydaeon.cashregister.service;

import net.vydaeon.cashregister.dao.ItemDao;
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

    private final ItemDao itemDao;

    @Autowired
    ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public List<Item> getItems() {
        return itemDao.getItems();
    }
}
