// Package
package org.jboss.as.quickstarts.kitchensink.mappers;

// Imports
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberCreateDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberResponseDTO;
import org.jboss.as.quickstarts.kitchensink.model.Member;

/**
 * Maps a Member entity to a MemberCreateDTO and vice versa.
 */
public class MemberMapper {

    /**
     * Maps a MemberCreateDTO to a Member entity.
     * @param memberCreateDTO The MemberCreateDTO to map.
     * @return The mapped Member entity.
     */
    public static Member toEntity(MemberCreateDTO memberCreateDTO) {
        return Member.builder()
                .name(memberCreateDTO.getName())
                .email(memberCreateDTO.getEmail())
                .phoneNumber(memberCreateDTO.getPhoneNumber())
                .build();
    }

    /**
     * Maps a Member entity to a MemberResponseDTO.
     * @param member The Member entity to map.
     * @return The mapped MemberResponseDTO.
     */
    public  static MemberResponseDTO toResponse(Member member) {
        return MemberResponseDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
