package common.maria.entity;

import lombok.*;

import javax.persistence.IdClass;
import java.io.Serializable;

@Data
public class VerifyCodePK implements Serializable {

    private String sessionId;
    private String mobile;
}
