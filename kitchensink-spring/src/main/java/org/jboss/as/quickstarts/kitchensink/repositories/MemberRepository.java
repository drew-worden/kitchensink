package org.jboss.as.quickstarts.kitchensink.repositories;

import org.bson.types.ObjectId;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends MongoRepository<Member, ObjectId> {
    Member findByEmail(String email);
    List<Member> findAllByOrderByNameAsc();
}
