package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@SequenceGenerator(name = "member_seq_generator",
                   sequenceName = "member_seq", // 매핑할 데이터베이스 이름
                   initialValue = 1, allocationSize = 50) // DB에 미리 50씩 넣어두고 메모리처럼 1씩 사용
//@TableGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        table = "MY_SEQUENCES",
//        pkColumnValue = "MEMBER_SEQ", allocationSize = 1
//)
public class Member {
    // create sequence member_seq
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    // create table MY_SEQUENCES
//    @Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;
    /** 기본키 매핑
     * 직접 할당 : @Id만 사용
     * 자동 생성 : @GeneratedValue
     *      - IDENTITY : 기본 키를 데이터베이스에 위임 (ex. MySQL의 AUTO_INCREMENT)
     *      - SEQUENCE : 데이터베이스 시퀀스는 유일한 값을 순서대로 생성하는 특별한 데이터베이스 오브젝트 (ex. 오라클 시퀀스)
     *                  * @SequenceGenerator : 시퀀스 이름 지정 가능
     *      - TABLE : 키 생성 전용 테이블을 하나 만들어서 데이터베이스 시퀀스를 흉내내는 전략 (단점 : 성능 저하)
     *
     * 권장하는 식별자 전략
     *      - 기본 키 제약 조건 : null, 유일, 불변
     *      - 미래까지 이 조건을 만족하는 자연키를 찾기 어렵다. 대리키(대체키)를 사용하자.
     *      - 비즈니스를 기본 키로 가져오는 것은 절대 권장하지 않는다. (ex. 주민등록번호)
     *      - 권장 : Long형 + 대체키 + 키 생성전략 사용
     */

    @Column(name = "name", nullable = false) // DB 컬럼명, not null
    private String username;

    private int age;

    @Enumerated(EnumType.STRING) // ORDINAL 사용 X
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 최신버전은 Temporal 대신 LocalDate 사용
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob
    private String description;

    @Transient // DB 저장 X, 주로 메모리상에서만 임시로 어떤 값을 보관하고 싶을 때 사용
    private int temp;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
