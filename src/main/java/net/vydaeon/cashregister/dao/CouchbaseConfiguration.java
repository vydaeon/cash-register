package net.vydaeon.cashregister.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.convert.CustomConversions;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Arrays;
import java.util.List;

/**
 * {@link Configuration} for Couchbase DB via Spring Data.
 *
 * @author Brad Bottjen
 */
@Configuration
@EnableCouchbaseRepositories
class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    @Value("${couchbaseConfiguration.bootstrapHosts}")
    private String bootstrapHosts;

    @Value("${couchbaseConfiguration.bucketName}")
    private String bucketName;

    @Value("${couchbaseConfiguration.bucketPassword}")
    private String bucketPassword;

    @Override
    protected List<String> getBootstrapHosts() {
        String[] bootstrapHostsArray = bootstrapHosts.split(",");
        return Arrays.asList(bootstrapHostsArray);
    }

    @Override
    protected String getBucketName() {
        return bucketName;
    }

    @Override
    protected String getBucketPassword() {
        return bucketPassword;
    }

    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(Arrays.asList(
                BigDecimalToStringConverter.INSTANCE,
                StringToBigDecimalConverter.INSTANCE,
                InstantToStringConverter.INSTANCE,
                StringToInstantConverter.INSTANCE
        ));
    }
}
