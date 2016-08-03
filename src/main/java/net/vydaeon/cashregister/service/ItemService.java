package net.vydaeon.cashregister.service;

import net.vydaeon.cashregister.domain.Item;

import java.util.List;

/**
 * Service interface for {@link Item}s.
 *
 * @author Brad Bottjen
 */
public interface ItemService {

    /**
     * @return a {@link List} of all {@link Item}s.
     */
    List<Item> getItems();
}
