package net.vydaeon.cashregister.service;

import net.vydaeon.cashregister.dao.ItemRepository;
import net.vydaeon.cashregister.domain.Item;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link ItemServiceImpl}.
 *
 * @author Brad Bottjen
 */
public class ItemServiceImplTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private List<Item> items;

    @InjectMocks
    private ItemServiceImpl itemService;

    @Test
    public void getItems() {
        when(itemRepository.getItems()).thenReturn(items);
        assertThat(itemService.getItems(), is(items));
    }
}
