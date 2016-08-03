package net.vydaeon.cashregister.rest;

import net.vydaeon.cashregister.domain.Item;
import net.vydaeon.cashregister.service.ItemService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link ItemController}.
 *
 * @author Brad Bottjen
 */
public class ItemControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ItemService itemService;

    @Mock
    private List<Item> items;

    private ItemController itemController;

    @Before
    public void before() {
        this.itemController = new ItemController(itemService);
    }

    @Test
    public void getItems() {
        when(itemService.getItems()).thenReturn(items);
        assertThat(itemController.getItems(), is(items));
    }
}
