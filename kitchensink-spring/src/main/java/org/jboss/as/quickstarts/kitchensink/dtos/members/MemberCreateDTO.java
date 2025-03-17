package org.jboss.as.quickstarts.kitchensink.dtos.members;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateDTO {
    private String name;
    private String email;
    private String phoneNumber;
}
