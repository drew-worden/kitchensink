// Package
package org.jboss.as.quickstarts.kitchensink.test.services;

// Imports
import org.bson.types.ObjectId;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberCreateDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberResponseDTO;
import org.jboss.as.quickstarts.kitchensink.exceptions.ResourceNotFoundException;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.repositories.MemberRepository;
import org.jboss.as.quickstarts.kitchensink.services.MemberRegistration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * A collection of tests for the MemberRegistration service class.
 */
@ExtendWith(MockitoExtension.class)
public class MemberRegistrationTests {

    // Mocks
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberRegistration memberRegistration;

    /**
     * Test the register method of the MemberRegistration service.
     * @throws Exception
     */
    @Test
    void testRegister() throws Exception {
        // Given: Create a sample MemberCreateDTO
        MemberCreateDTO createDTO = new MemberCreateDTO();
        createDTO.setName("John Doe");
        createDTO.setEmail("john@example.com");
        createDTO.setPhoneNumber("1234567890");

        // When repository.save is called, simulate that it returns the member with an id set.
        when(memberRepository.save(any(Member.class))).thenAnswer(invocation -> {
            Member member = invocation.getArgument(0);
            // Simulate persistence by setting an id (using a fixed ObjectId string)
            member.setId(new ObjectId("507f1f77bcf86cd799439011").toHexString());
            return member;
        });

        // When: Call the register method
        MemberResponseDTO responseDTO = memberRegistration.register(createDTO);

        // Then: Verify that the response has the expected values
        assertNotNull(responseDTO);
        assertEquals("John Doe", responseDTO.getName());
        assertEquals("john@example.com", responseDTO.getEmail());
        assertEquals("1234567890", responseDTO.getPhoneNumber());
        assertEquals("507f1f77bcf86cd799439011", responseDTO.getId());
    }

    /**
     * Test the register method of the MemberRegistration service with a null name.
     * @throws Exception
     */
    @Test
    void testGetMemberByIdSuccess() {
        // Given: Use a valid ObjectId string
        String id = "507f1f77bcf86cd799439011";
        Member member = Member.builder()
                .id(id)
                .name("Jane Doe")
                .email("jane@example.com")
                .phoneNumber("0987654321")
                .build();

        when(memberRepository.findById(new ObjectId(id))).thenReturn(Optional.of(member));

        // When: Retrieve the member by id
        MemberResponseDTO responseDTO = memberRegistration.getMemberById(id);

        // Then: Assert the returned values match
        assertNotNull(responseDTO);
        assertEquals("Jane Doe", responseDTO.getName());
        assertEquals("jane@example.com", responseDTO.getEmail());
        assertEquals("0987654321", responseDTO.getPhoneNumber());
        assertEquals(id, responseDTO.getId());
    }

    /**
     * Test the getMemberById method of the MemberRegistration service with a null name.
     */
    @Test
    void testGetMemberByIdNotFound() {
        // Given: A valid id for which no member exists
        String id = "507f1f77bcf86cd799439011";
        when(memberRepository.findById(new ObjectId(id))).thenReturn(Optional.empty());

        // When/Then: Expect a ResourceNotFoundException with the appropriate message
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            memberRegistration.getMemberById(id);
        });
        assertEquals("Member not found with id: " + id, thrown.getMessage());
    }

    /**
     * Test the getAllMembers method of the MemberRegistration service.
     */
    @Test
    void testGetAllMembers() {
        // Given: Two sample Member entities
        Member member1 = Member.builder()
                .id("507f1f77bcf86cd799439011")
                .name("Alice")
                .email("alice@example.com")
                .phoneNumber("1111111111")
                .build();

        Member member2 = Member.builder()
                .id("507f191e810c19729de860ea")
                .name("Bob")
                .email("bob@example.com")
                .phoneNumber("2222222222")
                .build();

        // Simulate the repository returning a list sorted by name ascending
        when(memberRepository.findAllByOrderByNameAsc()).thenReturn(List.of(member1, member2));

        // When: Retrieve all members
        Iterable<MemberResponseDTO> responses = memberRegistration.getAllMembers();

        // Then: Verify the list size and mapped properties
        List<MemberResponseDTO> responseList = (List<MemberResponseDTO>) responses;
        assertEquals(2, responseList.size());

        MemberResponseDTO response1 = responseList.get(0);
        MemberResponseDTO response2 = responseList.get(1);

        assertEquals("Alice", response1.getName());
        assertEquals("alice@example.com", response1.getEmail());
        assertEquals("1111111111", response1.getPhoneNumber());
        assertEquals("507f1f77bcf86cd799439011", response1.getId());

        assertEquals("Bob", response2.getName());
        assertEquals("bob@example.com", response2.getEmail());
        assertEquals("2222222222", response2.getPhoneNumber());
        assertEquals("507f191e810c19729de860ea", response2.getId());
    }
}
