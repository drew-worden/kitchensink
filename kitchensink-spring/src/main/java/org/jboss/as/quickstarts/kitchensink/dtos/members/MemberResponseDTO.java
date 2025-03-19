// Package
package org.jboss.as.quickstarts.kitchensink.dtos.members;

// Imports
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Used to return a member
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDTO {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
}
