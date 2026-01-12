package com.example.step02.ex02;

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
    private ResultSet resultSet;

    // 생성자(JNDI를 통해 DataSource 인스턴스를 받아옴)
    public MemberDao() {
        try {
            // context 인스턴스 생성
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
            // DataSource  인스턴스로 데이터베이스에 연결
            connection = dataSource.getConnection();
            System.out.println("SUCCESS: Get the connection object.");

            // Statement 인스턴스 생성
            statement = connection.createStatement();
            System.out.println("SUCCESS: Create the statement object.");

            // 실행할 SQL 문을 작성
            String query = "SELECT * FROM MEMBERS";
            System.out.println("Query: " + query);

            // SQL 문을 실행
            resultSet = statement.executeQuery(query);

            // 반복자(iterator)로 ResultSet 인스턴스에 저장된 레코드를 차례대로 확인
            while (resultSet.next()) {
                // ResultSet 인스턴스의 커서가 가리키는 레코드의 MemberVo 인스턴스 생성
                MemberVo memberVo = new MemberVo(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getDate("created_at")
                );

                //ArrayList 인스턴스에 MemberVo 인스턴스를 추가
                memberVoList.add(memberVo);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) try {resultSet.close();} catch (SQLException ignore) { }
            if (statement != null) try {statement.close();} catch (SQLException ignore) { }
            if (connection != null) try {connection.close();} catch (SQLException ignore) { }

            System.out.println("SUCCESS: Close the Statement and Connection.");
        }

        return memberVoList;
    }

    }

