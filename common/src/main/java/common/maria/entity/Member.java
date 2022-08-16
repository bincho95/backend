package common.maria.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member implements Serializable {

    private static final long serialVersionUID = 32135486453L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;
    private String email;
    private String nickname;
    private String password;
    private String name;
    private String mobile;
}
