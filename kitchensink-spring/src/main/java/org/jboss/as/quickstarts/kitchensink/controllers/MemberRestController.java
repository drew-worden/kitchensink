package org.jboss.as.quickstarts.kitchensink.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberCreateDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberResponseDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.global.KitchenSinkResponse;
import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/rest/members")
@RestController
@Tag(name = "Member Management", description = "Operations about members")
public class MemberRestController {
    private final MemberRegistration memberRegistration;

    public MemberRestController(MemberRegistration memberRegistration) {
        this.memberRegistration = memberRegistration;
    }

    @PostMapping
    @Operation(summary = "Register a new member", description = "Creates a new member in the system")
    public ResponseEntity<KitchenSinkResponse<MemberResponseDTO>> registerMember(@RequestBody MemberCreateDTO memberCreateDTO) throws Exception {
        KitchenSinkResponse<MemberResponseDTO> response = KitchenSinkResponse.<MemberResponseDTO>builder()
                .message("Member created successfully.")
                .data(memberRegistration.register(memberCreateDTO))
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a member by ID", description = "Returns a single member")
    public ResponseEntity<KitchenSinkResponse<MemberResponseDTO>> getMemberById(@PathVariable String id) {
        KitchenSinkResponse<MemberResponseDTO> response = KitchenSinkResponse.<MemberResponseDTO>builder()
                .message("Member retrieved successfully.")
                .data(memberRegistration.getMemberById(id))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Find all members", description = "Returns a list of all members ordered by name.")
    public ResponseEntity<KitchenSinkResponse<Iterable<MemberResponseDTO>>> getAllMembers() {
        KitchenSinkResponse<Iterable<MemberResponseDTO>> response = KitchenSinkResponse.<Iterable<MemberResponseDTO>>builder()
                .message("Members retrieved successfully.")
                .data(memberRegistration.getAllMembers())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
