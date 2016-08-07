package net.vydaeon.cashregister.dao;

import net.vydaeon.cashregister.domain.Item;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * {@link ItemRepository} implementation using a CSV file on the classpath.
 *
 * @author Brad Bottjen
 */
@Repository
class ItemRepositoryClasspathCsvImpl implements ItemRepository {

    @Override
    public List<Item> getItems() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        List<Item> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                classLoader.getResourceAsStream("items.csv"), UTF_8))) {
            reader.readLine(); //skip header line
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] tokens = line.split(",");
                String name = tokens[0];
                BigDecimal price = new BigDecimal(tokens[1]);
                Item item = new Item(name, price);
                items.add(item);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("error reading items.csv from classpath", ioe);
        }
        return items;
    }
}
