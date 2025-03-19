// Package
package org.jboss.as.quickstarts.kitchensink.exceptions;

/**
 * Custom exception to be thrown when a resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
