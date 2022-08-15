package tw.com.ispan.cma.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.ispan.cma.domain.MembersBean;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MembersBean, Integer> {

    List<MembersBean> findByMemberAccouunt(String memberAccouunt);


}
