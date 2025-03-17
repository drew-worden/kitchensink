package org.jboss.as.quickstarts.kitchensink.repositories;

import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends MongoRepository<Member, Long> {
    Member findById(long id);
    Member findByEmail(String email);
    Iterable<Member> findAllByOrderByNameAsc();
}
