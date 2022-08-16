package common.maria.repository;

import common.maria.entity.VerifyCode;
import common.maria.entity.VerifyCodePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface VerifyCodeRepository extends JpaRepository<VerifyCode, VerifyCodePK> {

    VerifyCode findBySessionIdAndMobileAndCodeAndExpiredDateLessThanEqual(String sessionId, String mobile, String code, LocalDateTime expiredDate);
}