package org.jboss.as.quickstarts.kitchensink.dtos.members.global;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class KitchenSinkResponse<T> {
    String message;
    LocalDateTime timestamp = LocalDateTime.now();
    T data;
    List<String> errors;
}
