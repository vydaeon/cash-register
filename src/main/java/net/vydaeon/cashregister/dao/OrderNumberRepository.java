package net.vydaeon.cashregister.dao;

import net.vydaeon.cashregister.domain.OrderNumber;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

/**
 * {@link CouchbaseRepository} for {@link OrderNumber}.
 *
 * @author Brad Bottjen
 */
public interface OrderNumberRepository extends CouchbaseRepository<OrderNumber, Integer> {
}
