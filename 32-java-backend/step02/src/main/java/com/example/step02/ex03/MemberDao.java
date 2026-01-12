package com.example.step02.ex03;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DBMS에 연동해서 members 테이블의 데이터를 조회하는 DAO(Data Access Object) 클래스
 */
public class MemberDao {
    // 데이터베이스 연동과 관련된 객체들
    private DataSource dataSource;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    // 생성자(JNDI를 통해 DataSource 인스턴스를 받아옴)
    public MemberDao() {
        try {
            // Context 인스턴스 생성
            Context initContext = new InitialContext();

            // JNDI에 접근해서 DataSource 인스턴스를 받아온다.
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/h2");
            System.out.println("SUCCESS: Get the DataSource object.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * members 테이블의 모든 레코드를 조회
     *
     * @return 조회한 레코드의 목록
     */
    public List<MemberVo> getMemberList() {
        List<MemberVo> memberVoList = new ArrayList<>();

        try {
            // DataSource 인스턴스로 데이터베이스에 연결
            connection = dataSource.getConnection();
            System.out.println("SUCCESS: Get the Connection object.");

            // Statement 인스턴스 생성
            statement = connection.createStatement();
            System.out.println("SUCCESS: Create the Statement object.");

            // 실행할 SQL 문을 작성
            String query = "SELECT * FROM members";
            System.out.println("QUERY: " + query);

            // SQL 문을 실행
            resultSet = statement.executeQuery(query);

            // 반복자(iterator)로 ResultSet 인스턴스에 저장된 레코드를 차례대로 확인
            while (resultSet.next()) {
                // ResultSet 인스턴스에서 조회한 레코드의 컬럼으로 MemberVo 인스턴스 생성
                MemberVo memberVo = new MemberVo(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getDate("created_at")
                );

                // ArrayList 인스턴스에 MemberVo 인스턴스를 추가
                memberVoList.add(memberVo);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }

        return memberVoList;
    }

    /**
     * 데이터베이스 연결을 해제
     */
    private void closeConnection() {
        if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) { }
        if (statement != null) try { statement.close(); } catch (SQLException ignore) { }
        if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) { }
        if (connection != null) try { connection.close(); } catch (SQLException ignore) { }

        // 객체들을 닫아도 참조 변수들은 null로 초기화되지 않는다.
        resultSet = null;
        statement = null;
        preparedStatement = null;
        connection = null;

        System.out.println("SUCCESS: Close the Statement and Connection objects.");
    }

    /**
     * members 테이블에 새로운 레코드를 추가
     *
     * @param memberVo 추가할 회원 정보
     */
    public void insertMember(MemberVo memberVo) {
        try {
            // DataSource 인스턴스를 통해 데이터베이스에 연결
            connection = dataSource.getConnection();
            System.out.println("SUCCESS: Create the Connection object.");

            // members 테이블에 새로운 레코드를 추가하는 SQL 문
            String query = "INSERT INTO members (id, username, password, name, email) " +
                    "VALUES (NEXTVAL('SEQ_MEMBER_ID'), ?, ?, ?, ?)";
            System.out.println("QUERY: " + query);

            // SQL 문을 실행하는 PreparedStatement 인스턴스를 생성
            preparedStatement = connection.prepareStatement(query);
            System.out.println("SUCCESS: Create the PreparedStatement object.");

            // PreparedStatement 인스턴스의 setString 메서드 등으로 SQL 문의 지정한 위치(?)에
            // 원하는 값을 치환한다.
            preparedStatement.setString(1, memberVo.getUsername());
            preparedStatement.setString(2, memberVo.getPassword());
            preparedStatement.setString(3, memberVo.getName());
            preparedStatement.setString(4, memberVo.getEmail());

            // SQL 문을 실행
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }
}

