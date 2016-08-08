package net.vydaeon.cashregister.domain;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;

/**
 * {@link Document} wrapper for atomically incrementing the end-user order number (1 - 100).
 *
 * @author Brad Bottjen
 */
@Document
public class OrderNumber {

    @Id
    private int id;

    @Version
    private long version;

    @Field
    private int orderNumber;

    /**
     * @return the order-number ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the order-number ID.
     *
     * @param id The order-number ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the order-number version.
     */
    public long getVersion() {
        return version;
    }

    /**
     * Sets the order-number version.
     *
     * @param version The order-number version.
     */
    public void setVersion(long version) {
        this.version = version;
    }

    /**
     * @return the order-number number.
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the order-number number.
     *
     * @param orderNumber The order-number number.
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
