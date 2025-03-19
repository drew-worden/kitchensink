// Package
package org.jboss.as.quickstarts.kitchensink.dtos.members;

// Imports
import lombok.*;

/**
 * Used to create a new member
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateDTO {
    private String name;
    private String email;
    private String phoneNumber;
}
