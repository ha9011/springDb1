package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    @DisplayName("연결 테스트")
    void driverManager() throws SQLException {
        Connection conn1 = DriverManager.getConnection(URL, USERNAME, PW);
        Connection conn2 = DriverManager.getConnection(URL, USERNAME, PW);
        Connection conn3 = DriverManager.getConnection(URL, USERNAME, PW);

        log.info("conn={}, class={}", conn1, conn1.getClass());
        log.info("conn={}, class={}", conn2, conn2.getClass());
        log.info("conn={}, class={}", conn3, conn3.getClass());
    }

    @Test
    @DisplayName("")
    void datasourceDriverManager() throws SQLException {
        // DriverManagerDataSource - 항상 새로운 커넥션을 획득
        DriverManagerDataSource dataSo = new DriverManagerDataSource(URL, USERNAME, PW);
        useDataSource(dataSo);
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection conn1 = dataSource.getConnection();
        Connection conn2 = dataSource.getConnection();
        log.info("conn={}, class={}", conn1, conn1.getClass());
        log.info("conn={}, class={}", conn2, conn2.getClass());
    }

    @Test
    @DisplayName("히카리 커넥션 풀")
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PW);
        dataSource.setMaximumPoolSize(10); //pool사이즈 최대 지정
        dataSource.setPoolName("MyPool"); //pool이름 설정

        useDataSource(dataSource);
        Thread.sleep(1000);
    }
}
