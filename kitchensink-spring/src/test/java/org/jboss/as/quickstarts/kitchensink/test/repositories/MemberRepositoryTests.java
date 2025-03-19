// Package
package org.jboss.as.quickstarts.kitchensink.test.repositories;

// Imports
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.repositories.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the MemberRepository class.
 *
 * These tests use the @DataMongoTest annotation to configure the test environment for MongoDB.
 * The @Autowired annotation is used to inject the MemberRepository instance.
 * The @AfterEach annotation is used to clean up the repository after each test.
 */
@DataMongoTest
public class MemberRepositoryTests {

    // Inject the MemberRepository instance.
    @Autowired
    private MemberRepository memberRepository;

    // Clean up the repository after each test.
    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    /**
     * Test the save method.
     *
     * Given: a new Member is saved in the repository.
     * When: the save method is called.
     * Then: the member should be saved and its fields should match.
     */
    @Test
    public void testFindByEmail() {
        // Given: a new Member is saved in the repository.
        Member member = Member.builder()
                .name("John Doe")
                .email("john@example.com")
                .phoneNumber("1234567890")
                .build();
        Member savedMember = memberRepository.save(member);

        // When: findByEmail is called.
        Member foundMember = memberRepository.findByEmail("john@example.com");

        // Then: the member should be found and its fields should match.
        assertNotNull(foundMember);
        assertEquals(savedMember.getId(), foundMember.getId());
        assertEquals("John Doe", foundMember.getName());
        assertEquals("john@example.com", foundMember.getEmail());
        assertEquals("1234567890", foundMember.getPhoneNumber());
    }

    /**
     * Test the findByEmail method.
     *
     * Given: a member with the specified email exists in the repository.
     * When: findByEmail is called with the email.
     * Then: the member should be returned.
     */
    @Test
    public void testFindByEmailNotFound() {
        // When: findByEmail is called with an email that doesn't exist.
        Member foundMember = memberRepository.findByEmail("nonexistent@example.com");

        // Then: no member is returned.
        assertNull(foundMember);
    }

    /**
     * Test the findAllByOrderByNameAsc method.
     *
     * Given: several members saved in an unsorted order.
     * When: findAllByOrderByNameAsc is called.
     * Then: the returned list is sorted by name in ascending order.
     */
    @Test
    public void testFindAllByOrderByNameAsc() {
        // Given: several members saved in an unsorted order.
        Member member1 = Member.builder()
                .name("Alice")
                .email("alice@example.com")
                .phoneNumber("1111111111")
                .build();
        Member member2 = Member.builder()
                .name("Bob")
                .email("bob@example.com")
                .phoneNumber("2222222222")
                .build();
        Member member3 = Member.builder()
                .name("Charlie")
                .email("charlie@example.com")
                .phoneNumber("3333333333")
                .build();

        // Save in mixed order.
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member1);

        // When: findAllByOrderByNameAsc is called.
        List<Member> members = memberRepository.findAllByOrderByNameAsc();

        // Then: the returned list is sorted by name in ascending order.
        assertNotNull(members);
        assertEquals(3, members.size());
        // Alphabetical order: Alice, Bob, Charlie
        assertEquals("Alice", members.get(0).getName());
        assertEquals("Bob", members.get(1).getName());
        assertEquals("Charlie", members.get(2).getName());
    }
}
