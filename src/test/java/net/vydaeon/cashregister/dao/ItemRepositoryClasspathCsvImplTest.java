package net.vydaeon.cashregister.dao;

import net.vydaeon.cashregister.domain.Item;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link ItemRepositoryClasspathCsvImpl}
 *
 * @author Brad Bottjen
 */
public class ItemRepositoryClasspathCsvImplTest {

    private ItemRepositoryClasspathCsvImpl dao = new ItemRepositoryClasspathCsvImpl();

    @Test
    public void getItems() throws Exception {
        List<Item> items = dao.getItems();
        assertThat(items, is(notNullValue()));

        List<String> itemsCsvLines = items.stream().map(this::toCsvLine).collect(toList());
        assertThat(itemsCsvLines, is(expectedCsvLines()));
    }

    private List<String> expectedCsvLines() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        List<String> csvLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                classLoader.getResourceAsStream("items.csv"), UTF_8))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                csvLines.add(line);
            }
        }
        csvLines = csvLines.subList(1, csvLines.size()); //drop header line
        return unmodifiableList(csvLines);
    }

    private String toCsvLine(Item item) {
        return String.join(",", item.getName(), item.getPrice().toString());
    }
}
