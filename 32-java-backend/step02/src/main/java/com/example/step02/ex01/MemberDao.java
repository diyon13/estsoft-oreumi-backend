package com.example.step02.ex01;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DBMS에 연동해서 members 테이블의 데이터를 조회하는 DAO(Data Access Object) 클래스
 */
public class MemberDao {
    // H2 데이터베이스의 접속 정보를 나타내는 정적 상수(static constants)
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * members 테이블의 모든 레코드를 조회
     *
     * @return 조회한 레코드의 목록
     */

    public List<MemberVo> getMemberList() {
        List<MemberVo> memberVoList = new ArrayList<>();

        try {
            // 데이터베이스에 연결
            connectDatabase();

            // 실행할 SQL 문을 작성
            String query = "SELECT * FROM MEMBERS";
            System.out.println("Query: " + query);

            // SQL 문을 실행
            resultSet = statement.executeQuery(query);

            // 반복자(iterator)로 ResultSet 인스턴스에 저장된 레코드를 차례대로 확인
            while (resultSet.next()) {
                // ResultSet 인스턴스의 커서가 가리키는 레코드의 컬럼(column)을 참조
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Date createdAt = resultSet.getDate("created_at");

                // MemberVo 인스턴스 생성
                MemberVo memberVo = new MemberVo(id, username, password, name, email, createdAt);

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

    private  void connectDatabase() throws ClassNotFoundException, SQLException {
        // JDBC 드라이버 로드
        Class.forName(DB_DRIVER);
        System.out.println("SUCCESS: Load the JDBC driver.");

        // Connection 인스턴스 생성
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("SUCCESS: Connect to the database successfully.");

        //Statement 인스턴스 생성
        statement = connection.createStatement();
        System.out.println("SUCCESS: Create the statement successfully.");
    }
}
