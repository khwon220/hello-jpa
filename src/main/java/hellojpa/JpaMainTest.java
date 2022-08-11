package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
                                                                        // persistence.xml의 unit name

        EntityManager em = emf.createEntityManager();

        /**
         * 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
         * 엔티티 매니저는 스레드간에 공유 X (사용하고 버리기)
         * JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
         */

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

//            Member findMember = em.find(Member.class, 1L);
            // 조회
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
            // 수정
//            findMember.setName("HelloJPA");
//            em.persist(findMember); // 필요 없음 : 아래 commit() 덕분
            
            // 1개 이상 조회
//            List<Member> result = em.createQuery("select m from Member as m", Member.class) // (쿼리, 반환타입)
//                    .setFirstResult(5) // 페이징
//                    .setMaxResults(8)
//                    .getResultList();
            /** JPQL ;객체 지향 SQL
             * JPA는 SQL을 추상화한 JPQL이라는 쿼리를 객체 지향 쿼리 언어 제공
             *      SQL 추상화로 특정 데이커베이스 SQL에 의존 X
             * 테이블을 대상이 아닌 객체(Member)를 대상으로 쿼리를 사용한다. => 객체 지향 쿼리 !
             */
            
//            for(Member member : result) {
//                System.out.println("member.getName() = " + member.getName());
//            }


            // 비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");

            /** 영속 **/
            // 1차 캐시에 저장됨
//            em.persist(member); // DB에 SQL 전달 X
            // 1차 캐시에서 조회
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

            // 1차 캐시에 없으므로 DB에서 조회 후 1차 캐시에 저장
//            Member findMember1 = em.find(Member.class, 101L);
            // 1차 캐시에서 조회
//            Member findMember2 = em.find(Member.class, 101L);
//            System.out.println("result = " + (findMember1 == findMember2)); // true : 1차 캐시 때문에 가능

            // 엔티티 등록 - 트랜잭션을 지원하는 쓰기 지연 (쓰기 지연 SQL 저장소)
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//            em.persist(member1);
//            em.persist(member2);
//            System.out.println("============"); // 여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.
                                                    // 커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.

            // 변경 감지 (Dirty Checking)
//            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZZZ");
//            em.persist(member); // 필요 없음! 영속 컨텍스트에서 변경 감지로 인해 커밋에서 실행되는 SQL에 update문을 포함하기 때문

            // 플러시
//            Member member = new Member(200L, "member200");
//            em.persist(member);
//            em.flush(); // 쓰기 지연 SQL 저장소의 쿼리를 DB에 전송
//            System.out.println("============");
            /** flush
             * 영속성 컨텍스트를 비우지 않음
             * 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
             * 트랜잭션이라는 작업 단위가 중요 => 커밋 직전에만 동기화 하면 됨
             */

            // 준영속
//            Member member = em.find(Member.class, 150L); // 조회1
//            member.setName("AAAAA");
//            em.detach(member); // 특정 엔티티(member)만 준영속 상태로 전환
//            em.clear(); // 영속성 컨텍스트 완전히 초기화
//            Member member2= em.find(Member.class, 150L); // 조회2 : clear로 초기화되어 1차 캐시에 없으므로 다시 조회
//            System.out.println("===========");


            /** 기본키 매핑 : IDENTITY **/
//            Member member = new Member();
//            member.setUsername("C");
//            System.out.println("===========");
//            em.persist(member); // insert 쿼리 실행!
                    // IDENTITY는 insert 후에 PK 값을 알 수 있기 때문에 예외적.
//            System.out.println("member.getId() = " + member.getId()); // insert 쿼리와 동시에 바로 PK 확인 가능.
                    // 따로 select 쿼리를 날리지 않아도 insert 하는 시점에 바로 return 되어 영속성 컨텍스트에 저장됨.
//            System.out.println("===========");

            /** 기본키 매핑 : SEQUENCE **/
//            Member member = new Member();
//            member.setUsername("C");
            MemberTest memberTest1 = new MemberTest();
            memberTest1.setUsername("A");
            MemberTest memberTest2 = new MemberTest();
            memberTest2.setUsername("B");
            MemberTest memberTest3 = new MemberTest();
            memberTest3.setUsername("C");
            // SequenceGenerator의 allocationsSize 속성 설정 시
            // ex) create sequence member_seq start with 1 increment by 50 실행
            System.out.println("===========");
//            em.persist(member); // call next value for SEQUENCE_NAME 실행!
                    // DB에서 PK로 사용할 값을 시퀀스에서 가져옴.
            em.persist(memberTest1); // SEQUENCE 1, 51
            em.persist(memberTest2); // MEMORY
            em.persist(memberTest3); // MEMORY
                    // ... 메모리가 51이 됐을 때 51부터 100까지 또 호출 ...
//            System.out.println("member.getId() = " + member.getId()); // 가져온 값은 영속성 컨텍스트에 저장됨. 따라서 버퍼링 기능 사용 가능.
            System.out.println("member1.getId() = " + memberTest1.getId());
            System.out.println("member2.getId() = " + memberTest2.getId());
            System.out.println("member3.getId() = " + memberTest3.getId());
            System.out.println("===========");

            tx.commit(); // 이 때 DB에 SQL 전달 O
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
