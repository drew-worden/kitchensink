// Package
package org.jboss.as.quickstarts.kitchensink.test.controllers;

// Imports
import com.mongodb.MongoWriteException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteError;
import org.bson.BsonDocument;
import org.jboss.as.quickstarts.kitchensink.controllers.MemberController;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberCreateDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberResponseDTO;
import org.jboss.as.quickstarts.kitchensink.services.MemberRegistration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the MemberController class.
 */
@ExtendWith(MockitoExtension.class)
public class MemberControllerTests {

    // Mocks
    @Mock
    private MemberRegistration memberRegistration;

    @InjectMocks
    private MemberController memberController;

    /**
     * Test the getDashboard method.
     */
    @Test
    public void testGetDashboard() {
        // Given
        List<MemberResponseDTO> members = List.of(new MemberResponseDTO(), new MemberResponseDTO());
        when(memberRegistration.getAllMembers()).thenReturn(members);

        Model model = new ExtendedModelMap();

        // When
        String viewName = memberController.getDashboard(model);

        // Then
        assertEquals("dashboard", viewName);
        // ExtendedModelMap exposes get(String) for attributes:
        assertEquals(members, ((ExtendedModelMap) model).get("members"));
    }

    /**
     * Test the showMemberForm method.
     */
    @Test
    public void testShowMemberForm() {
        // Given
        Model model = new ExtendedModelMap();

        // When
        String viewName = memberController.showMemberForm(model);

        // Then
        assertEquals("member-form", viewName);
        Object memberObj = ((ExtendedModelMap) model).get("member");
        assertNotNull(memberObj);
        assertTrue(memberObj instanceof MemberCreateDTO);
    }

    /**
     * Test the registerMember method.
     */
    @Test
    public void testRegisterMemberWithValidationErrors() {
        // Given
        MemberCreateDTO dto = new MemberCreateDTO();
        Model model = new ExtendedModelMap();
        BindingResult bindingResult = new BeanPropertyBindingResult(dto, "member");
        // Simulate a validation error:
        bindingResult.rejectValue("name", "error.name", "Name is required");

        // When
        String viewName = memberController.registerMember(dto, bindingResult, model);

        // Then
        assertEquals("member-form", viewName);
    }

    /**
     * Test the registerMember method.
     */
    @Test
    public void testRegisterMemberSuccess() throws Exception {
        // Given
        MemberCreateDTO dto = new MemberCreateDTO();
        Model model = new ExtendedModelMap();
        BindingResult bindingResult = new BeanPropertyBindingResult(dto, "member");

        MemberResponseDTO registeredMember = new MemberResponseDTO();
        when(memberRegistration.register(dto)).thenReturn(registeredMember);

        // When
        String viewName = memberController.registerMember(dto, bindingResult, model);

        // Then
        assertEquals("redirect:/dashboard", viewName);
    }

    /**
     * Test the registerMember method.
     */
    @Test
    public void testRegisterMemberMongoWriteException() throws Exception {
        // Given
        MemberCreateDTO dto = new MemberCreateDTO();
        Model model = new ExtendedModelMap();
        BindingResult bindingResult = new BeanPropertyBindingResult(dto, "member");

        // Simulate a duplicate key error (error code 11000)
        WriteError writeError = new WriteError(11000, "Duplicate key", new BsonDocument());
        MongoWriteException mongoException = new MongoWriteException(writeError, new ServerAddress());
        when(memberRegistration.register(dto)).thenThrow(mongoException);

        // When
        String viewName = memberController.registerMember(dto, bindingResult, model);

        // Then
        assertEquals("member-form", viewName);
        assertEquals("A member with this email already exists.", ((ExtendedModelMap) model).get("error"));
        // Ensure the DTO is re-added to the model
        assertEquals(dto, ((ExtendedModelMap) model).get("member"));
    }

    /**
     * Test the registerMember method.
     */
    @Test
    public void testRegisterMemberGenericException() throws Exception {
        // Given
        MemberCreateDTO dto = new MemberCreateDTO();
        Model model = new ExtendedModelMap();
        BindingResult bindingResult = new BeanPropertyBindingResult(dto, "member");

        Exception ex = new Exception("Something went wrong");
        when(memberRegistration.register(dto)).thenThrow(ex);

        // When
        String viewName = memberController.registerMember(dto, bindingResult, model);

        // Then
        assertEquals("member-form", viewName);
        assertEquals("Failed to create member: Something went wrong", ((ExtendedModelMap) model).get("error"));
        assertEquals(dto, ((ExtendedModelMap) model).get("member"));
    }

    /**
     * Test the getMemberById method.
     */
    @Test
    public void testGetMemberByIdWithNullOrEmptyId() {
        // Given
        Model model = new ExtendedModelMap();

        // When with null
        String viewNameNull = memberController.getMemberById(null, model);
        // Then
        assertEquals("dashboard", viewNameNull);
        assertEquals("Invalid member ID", ((ExtendedModelMap) model).get("error"));

        // Reset model for next test
        ((ExtendedModelMap) model).clear();
        // When with empty id
        String viewNameEmpty = memberController.getMemberById("   ", model);
        // Then
        assertEquals("dashboard", viewNameEmpty);
        assertEquals("Invalid member ID", ((ExtendedModelMap) model).get("error"));
    }

    /**
     * Test the getMemberById method.
     */
    @Test
    public void testGetMemberByIdNotFound() {
        // Given
        String id = "1";
        Model model = new ExtendedModelMap();
        when(memberRegistration.getMemberById(id)).thenReturn(null);

        // When
        String viewName = memberController.getMemberById(id, model);

        // Then
        assertEquals("dashboard", viewName);
        assertEquals("No member exists with ID: " + id, ((ExtendedModelMap) model).get("error"));
    }

    /**
     * Test the getMemberById method.
     */
    @Test
    public void testGetMemberByIdSuccess() {
        // Given
        String id = "1";
        Model model = new ExtendedModelMap();
        MemberResponseDTO member = new MemberResponseDTO();
        member.setId(id);
        member.setName("John Doe");
        member.setEmail("john@example.com");
        member.setPhoneNumber("1234567890");

        when(memberRegistration.getMemberById(id)).thenReturn(member);

        // When
        String viewName = memberController.getMemberById(id, model);

        // Then
        assertEquals("member-detail", viewName);
        assertEquals(member, ((ExtendedModelMap) model).get("member"));
    }

    /**
     * Test the getMemberById method.
     */
    @Test
    public void testGetMemberByIdWithException() {
        // Given
        String id = "1";
        Model model = new ExtendedModelMap();
        RuntimeException ex = new RuntimeException("Lookup error");
        when(memberRegistration.getMemberById(id)).thenThrow(ex);

        // When
        String viewName = memberController.getMemberById(id, model);

        // Then
        assertEquals("dashboard", viewName);
        assertEquals("Member not found: " + ex.getMessage(), ((ExtendedModelMap) model).get("error"));
    }
}
