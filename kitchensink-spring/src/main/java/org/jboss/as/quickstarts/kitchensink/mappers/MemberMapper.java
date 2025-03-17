package org.jboss.as.quickstarts.kitchensink.mappers;

import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberCreateDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberResponseDTO;
import org.jboss.as.quickstarts.kitchensink.model.Member;

public class MemberMapper {
    public static Member toEntity(MemberCreateDTO memberCreateDTO) {
        return Member.builder()
                .name(memberCreateDTO.getName())
                .email(memberCreateDTO.getEmail())
                .phoneNumber(memberCreateDTO.getPhoneNumber())
                .build();
    }

    public  static MemberResponseDTO toResponse(Member member) {
        return MemberResponseDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
