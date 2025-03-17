package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@Document(collection = "members")
public class Member implements Serializable {
    @Id
    private String id;

    @NotNull
    @Size(min = 1, max = 25, message = "Name must be between 1 and 25 characters.")
    @Pattern(regexp = "[^0-9]*", message = "Name must not contain numbers.")
    private String name;

    @NotNull
    @NotEmpty
    @Email
    @Indexed(unique = true)
    private String email;

    @NotNull
    @Size(min = 10, max = 12)
    @Digits(fraction = 0, integer = 12)
    @Field(name = "phone_number")
    private String phoneNumber;
}
