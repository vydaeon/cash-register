package net.vydaeon.cashregister.dao;

import net.vydaeon.cashregister.domain.Item;

import java.util.List;

/**
 * DAO interface for {@link Item}s.
 *
 * @author Brad Bottjen
 */
public interface ItemRepository {

    /**
     * @return a {@link List} of all {@link Item}s.
     */
    List<Item> getItems();
}
