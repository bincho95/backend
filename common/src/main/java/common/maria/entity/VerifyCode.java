package common.maria.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@IdClass(VerifyCodePK.class)
@Table(name = "verifycode")
@Getter
@Setter
@ToString
public class VerifyCode implements Serializable {

    private static final long serialVersionUID = 3123213123L;

    @Id
    @Column(name = "session_id")
    private String sessionId;
    @Id
    private String mobile;
    private String code;
    @Column(name = "expired_date")
    private LocalDateTime expiredDate;

}
