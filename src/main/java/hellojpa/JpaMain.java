package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
//            member.setTeamId(team.getId());
//            member.changeTeam(team); // 연관관계 편의 메소드 (1)
            em.persist(member);

            team.addMember(member); // 연관관계 편의 메소드 (2) 중 선택

            em.flush(); // 쿼리 보기
            em.clear(); // 영속성 컨텍스트 클리어
            // DB에서 값 가져오기
//            Member findMember = em.find(Member.class, member.getId());
//            List<Member> members = findMember.getTeam().getMembers();
//
//            for(Member m : members) {
//                System.out.println("m.getName() = " + m.getName());
//            }

//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);
//            Team findTeam = findMember.getTeam();
//            System.out.println("findTeam.getName() = " + findTeam.getName());

            // Team 변경
//            Team newTeam = em.find(Team.class, 100L);
//            findMember.setTeam(newTeam);

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("===============");
            System.out.println("members = " + findTeam);
            System.out.println("===============");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
