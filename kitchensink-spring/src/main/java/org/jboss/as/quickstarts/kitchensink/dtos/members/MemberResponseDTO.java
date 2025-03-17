package org.jboss.as.quickstarts.kitchensink.dtos.members;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
