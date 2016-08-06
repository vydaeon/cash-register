package net.vydaeon.cashregister.dao;

import net.vydaeon.cashregister.domain.Order;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

/**
 * {@link CouchbaseRepository} for {@link Order}s.
 *
 * @author Brad Bottjen
 */
public interface OrderRepository extends CouchbaseRepository<Order, String> {
}
