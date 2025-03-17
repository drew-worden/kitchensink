package org.jboss.as.quickstarts.kitchensink.dtos.members;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberCreateDTO {
    private String name;
    private String email;
    private String phoneNumber;
}
