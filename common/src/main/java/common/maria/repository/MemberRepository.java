package common.maria.repository;

import common.maria.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    Member findByMobile(String mobile);

    Member findBynickname(String nickName);

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);

    boolean existsByNickname(String mobile);
}
