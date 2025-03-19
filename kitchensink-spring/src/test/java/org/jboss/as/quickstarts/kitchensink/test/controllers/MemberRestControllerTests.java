// Package
package org.jboss.as.quickstarts.kitchensink.test.controllers;

// Imports
import org.jboss.as.quickstarts.kitchensink.controllers.MemberRestController;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberCreateDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberResponseDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.global.KitchenSinkResponse;
import org.jboss.as.quickstarts.kitchensink.services.MemberRegistration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the MemberRestController class.
 */
@ExtendWith(MockitoExtension.class)
public class MemberRestControllerTests {

    // Mocks
    @Mock
    private MemberRegistration memberRegistration;

    @InjectMocks
    private MemberRestController memberRestController;

    /**
     * Test the registerMember method.
     * @throws Exception
     */
    @Test
    public void testRegisterMember() throws Exception {
        // Given
        MemberCreateDTO createDTO = new MemberCreateDTO();
        // Populate createDTO fields as necessary

        MemberResponseDTO responseDTO = new MemberResponseDTO();
        // Populate responseDTO fields as necessary

        when(memberRegistration.register(createDTO)).thenReturn(responseDTO);

        // When
        ResponseEntity<KitchenSinkResponse<MemberResponseDTO>> responseEntity = memberRestController.registerMember(createDTO);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        KitchenSinkResponse<MemberResponseDTO> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals("Member created successfully.", responseBody.getMessage());
        assertEquals(responseDTO, responseBody.getData());
    }

    /**
     * Test the registerMember method when an exception is thrown.
     * @throws Exception
     */
    @Test
    public void testRegisterMemberThrowsException() throws Exception {
        // Given
        MemberCreateDTO createDTO = new MemberCreateDTO();
        when(memberRegistration.register(createDTO)).thenThrow(new Exception("Registration failed"));

        // When/Then
        Exception exception = assertThrows(Exception.class, () -> {
            memberRestController.registerMember(createDTO);
        });
        assertEquals("Registration failed", exception.getMessage());
    }

    /**
     * Test the updateMember method.
     * @throws Exception
     */
    @Test
    public void testGetMemberById() {
        // Given
        String id = "1";
        MemberResponseDTO responseDTO = new MemberResponseDTO();
        when(memberRegistration.getMemberById(id)).thenReturn(responseDTO);

        // When
        ResponseEntity<KitchenSinkResponse<MemberResponseDTO>> responseEntity = memberRestController.getMemberById(id);

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        KitchenSinkResponse<MemberResponseDTO> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals("Member retrieved successfully.", responseBody.getMessage());
        assertEquals(responseDTO, responseBody.getData());
    }

    /**
     * Test the updateMember method.
     * @throws Exception
     */
    @Test
    public void testGetAllMembers() {
        // Given
        List<MemberResponseDTO> memberList = List.of(new MemberResponseDTO(), new MemberResponseDTO());
        when(memberRegistration.getAllMembers()).thenReturn(memberList);

        // When
        ResponseEntity<KitchenSinkResponse<Iterable<MemberResponseDTO>>> responseEntity = memberRestController.getAllMembers();

        // Then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        KitchenSinkResponse<Iterable<MemberResponseDTO>> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals("Members retrieved successfully.", responseBody.getMessage());
        assertEquals(memberList, responseBody.getData());
    }
}

