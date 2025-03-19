// Package
package org.jboss.as.quickstarts.kitchensink.config;

// Imports
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * This class is used to configure the MongoDB database.
 * It is used to validate the data that is being inserted into the database.
 */
@Configuration
public class MongoConfig {

    /**
     * This method is used to create a LocalValidatorFactoryBean object.
     * @return LocalValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * This method is used to create a ValidatingMongoEventListener object.
     * @return ValidatingMongoEventListener
     */
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }
}

