package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repositoryV0 = new MemberRepositoryV0();

    @Test
    @DisplayName("member save")
    void testName() throws SQLException {

        String memberid = "test3";
        int money = 1000;
        int updateMoney = 2000;
        // save
        Member member = new Member(memberid, money);
        repositoryV0.save(member);

        // findById
        Member member1 = repositoryV0.findById(memberid);
        log.info("member={}", member1);

        assertThat(member).isEqualTo(member1);

        repositoryV0.update(memberid, updateMoney);
        Member updateMember = repositoryV0.findById(memberid);
        assertThat(updateMember.getMoney()).isEqualTo(updateMoney);

        repositoryV0.delete(memberid);
    }

}