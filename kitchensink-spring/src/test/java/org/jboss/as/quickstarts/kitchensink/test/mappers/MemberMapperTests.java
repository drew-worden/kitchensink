// Package
package org.jboss.as.quickstarts.kitchensink.test.mappers;

// Imports
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberCreateDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberResponseDTO;
import org.jboss.as.quickstarts.kitchensink.mappers.MemberMapper;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the MemberMapper class.
 *
 * These tests verify that the MemberMapper class correctly maps between Member entities and Member DTOs.
 */
public class MemberMapperTests {

    /**
     * Test the toEntity method.
     *
     * Given: a MemberCreateDTO with sample values.
     * When: mapping to a Member entity.
     * Then: the resulting Member should have the same field values.
     */
    @Test
    void testToEntity() {
        // Given: a MemberCreateDTO with sample values.
        MemberCreateDTO createDTO = new MemberCreateDTO();
        createDTO.setName("Test User");
        createDTO.setEmail("test@example.com");
        createDTO.setPhoneNumber("1234567890");

        // When: mapping to a Member entity.
        Member member = MemberMapper.toEntity(createDTO);

        // Then: the resulting Member should have the same field values.
        assertNotNull(member, "The mapped Member should not be null.");
        assertEquals("Test User", member.getName(), "Name should be mapped correctly.");
        assertEquals("test@example.com", member.getEmail(), "Email should be mapped correctly.");
        assertEquals("1234567890", member.getPhoneNumber(), "Phone number should be mapped correctly.");
    }

    /**
     * Test the toResponse method.
     *
     * Given: a Member entity with sample values.
     * When: mapping to a MemberResponseDTO.
     * Then: the resulting DTO should have the same field values as the Member entity.
     */
    @Test
    void testToResponse() {
        // Given: a Member entity with sample values.
        Member member = Member.builder()
                .id("507f1f77bcf86cd799439011")
                .name("Test User")
                .email("test@example.com")
                .phoneNumber("1234567890")
                .build();

        // When: mapping to a MemberResponseDTO.
        MemberResponseDTO responseDTO = MemberMapper.toResponse(member);

        // Then: the resulting DTO should have the same field values as the Member entity.
        assertNotNull(responseDTO, "The mapped MemberResponseDTO should not be null.");
        assertEquals("507f1f77bcf86cd799439011", responseDTO.getId(), "ID should be mapped correctly.");
        assertEquals("Test User", responseDTO.getName(), "Name should be mapped correctly.");
        assertEquals("test@example.com", responseDTO.getEmail(), "Email should be mapped correctly.");
        assertEquals("1234567890", responseDTO.getPhoneNumber(), "Phone number should be mapped correctly.");
    }
}
