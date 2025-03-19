// Package
package org.jboss.as.quickstarts.kitchensink.services;

// Imports
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberCreateDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberResponseDTO;
import org.jboss.as.quickstarts.kitchensink.exceptions.ResourceNotFoundException;
import org.jboss.as.quickstarts.kitchensink.mappers.MemberMapper;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.repositories.MemberRepository;
import org.springframework.stereotype.Service;

/**
 * Service class for registering members.
 */
@Slf4j
@Service
public class MemberRegistration {
    // Dependencies
    private final MemberRepository memberRepository;

    // Constructor
    public MemberRegistration(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Registers a new member.
     *
     * @param memberCreateDTO The member to register.
     * @return The registered member.
     * @throws Exception If the member could not be registered.
     */
    public MemberResponseDTO register(MemberCreateDTO memberCreateDTO) throws Exception {
        Member memberEntity = MemberMapper.toEntity(memberCreateDTO);
        log.info("Registering {}", memberEntity.getName());;
        memberRepository.save(memberEntity);
        return MemberMapper.toResponse(memberEntity);
    }

    /**
     * Retrieves a member by their ID.
     *
     * @param id The ID of the member to retrieve.
     * @return The member with the given ID.
     * @throws ResourceNotFoundException If the member could not be found.
     */
    public MemberResponseDTO getMemberById(String id) {
        Member memberEntity = memberRepository.findById(new ObjectId(id)).orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
        log.info("Found member: {}", memberEntity.getName());
        return MemberMapper.toResponse(memberEntity);
    }

    /**
     * Retrieves all members.
     *
     * @return All members.
     */
    public Iterable<MemberResponseDTO> getAllMembers() {
        log.info("Retrieving all members");
        return memberRepository.findAllByOrderByNameAsc().stream()
                .map(MemberMapper::toResponse)
                .toList();
    }
}
