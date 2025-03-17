package org.jboss.as.quickstarts.kitchensink.controller;

import lombok.extern.slf4j.Slf4j;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberCreateDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberResponseDTO;
import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/members")
@RestController
public class MemberController {
    private final MemberRegistration memberRegistration;

    public MemberController(MemberRegistration memberRegistration) {
        this.memberRegistration = memberRegistration;
    }

    @PostMapping
    public MemberResponseDTO registerMember(@RequestBody MemberCreateDTO memberCreateDTO) throws Exception {
        return memberRegistration.register(memberCreateDTO);
    }

    @GetMapping("/{id:[0-9][0-9]*}")
    public MemberResponseDTO getMemberById(@PathVariable Long id) {
        return memberRegistration.getMemberById(id);
    }
}
