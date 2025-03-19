// Package
package org.jboss.as.quickstarts.kitchensink.controllers;

// Imports
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberCreateDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberResponseDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.global.KitchenSinkResponse;
import org.jboss.as.quickstarts.kitchensink.services.MemberRegistration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for member management.
 */
@Slf4j
@RequestMapping("/rest/members")
@RestController
@Tag(name = "Member Management", description = "Operations about members")
public class MemberRestController {
    // Dependencies
    private final MemberRegistration memberRegistration;

    // Constructor
    public MemberRestController(MemberRegistration memberRegistration) {
        this.memberRegistration = memberRegistration;
    }

    /**
     * Registers a new member.
     * @param memberCreateDTO The member to be registered.
     * @return The registered member.
     * @throws Exception If the member could not be registered.
     */
    @PostMapping
    @Operation(summary = "Register a new member", description = "Creates a new member in the system")
    public ResponseEntity<KitchenSinkResponse<MemberResponseDTO>> registerMember(@RequestBody MemberCreateDTO memberCreateDTO) throws Exception {
        KitchenSinkResponse<MemberResponseDTO> response = KitchenSinkResponse.<MemberResponseDTO>builder()
                .message("Member created successfully.")
                .data(memberRegistration.register(memberCreateDTO))
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves a member by ID.
     * @param id The ID of the member to be retrieved.
     * @return The member with the given ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Find a member by ID", description = "Returns a single member")
    public ResponseEntity<KitchenSinkResponse<MemberResponseDTO>> getMemberById(@PathVariable String id) {
        KitchenSinkResponse<MemberResponseDTO> response = KitchenSinkResponse.<MemberResponseDTO>builder()
                .message("Member retrieved successfully.")
                .data(memberRegistration.getMemberById(id))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves all members.
     * @return A list of all members.
     */
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
