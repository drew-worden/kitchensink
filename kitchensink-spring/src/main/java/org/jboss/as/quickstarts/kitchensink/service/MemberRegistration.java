package org.jboss.as.quickstarts.kitchensink.service;

import lombok.extern.slf4j.Slf4j;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberCreateDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberResponseDTO;
import org.jboss.as.quickstarts.kitchensink.exceptions.ResourceNotFoundException;
import org.jboss.as.quickstarts.kitchensink.mappers.MemberMapper;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.repositories.MemberRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberRegistration {
    private final MemberRepository memberRepository;

    public MemberRegistration(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDTO register(MemberCreateDTO memberCreateDTO) throws Exception {
        Member memberEntity = MemberMapper.toEntity(memberCreateDTO);
        log.info("Registering {}", memberEntity.getName());;
        memberRepository.save(memberEntity);
        return MemberMapper.toResponse(memberEntity);
    }

    public MemberResponseDTO getMemberById(Long id) {
        Member memberEntity = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
        log.info("Found member: {}", memberEntity.getName());
        return MemberMapper.toResponse(memberEntity);
    }
}
