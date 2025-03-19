// Package
package org.jboss.as.quickstarts.kitchensink.repositories;

// Imports
import org.bson.types.ObjectId;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Member objects.
 */
@Repository
public interface MemberRepository extends MongoRepository<Member, ObjectId> {
    /**
     * Find a member by their email.
     * @param email The email of the member.
     * @return The member with the given email.
     */
    Member findByEmail(String email);

    /**
     * Find all members in the database.
     * @return A list of all members in the database.
     */
    List<Member> findAllByOrderByNameAsc();
}
