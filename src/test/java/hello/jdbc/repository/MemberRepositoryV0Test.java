package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryV0Test {

    MemberRepositoryV0 repositoryV0 = new MemberRepositoryV0();

    @Test
    @DisplayName("member save")
    void testName() throws SQLException {
        // given
        Member member = new Member("ahn", 1000);
        // when
        repositoryV0.save(member);
        // then
    }

}