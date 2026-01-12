package com.example.step02.ex04;

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
    private final DataSource dataSource;
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
     * DataSource 인스턴스로 데이터베이스에 연결
     */
    private void getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
            System.out.println("SUCCESS: Get the Connection object.");
        }
    }

    /**
     * SQL 문을 실행할 Statement 인스턴스를 생성
     */
    private void createStatement() throws SQLException {
        // Connection 인스턴스가 생성되어 있지 않으면 Connection 인스턴스를 생성
        if (connection == null || connection.isClosed()) {
            getConnection();
        }

        // Statement 인스턴스가 생성되어 있는지 확인하고, Statement 인스턴스를 생성
        if (statement == null || statement.isClosed()) {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println("SUCCESS: Create the Statement object.");
        }
    }

    /**
     * Statement 인스턴스로 SQL 문을 실행
     *
     * @param query 실행할 SQL 문
     */
    private void executeQuery(String query) throws SQLException {
        // Statement 인스턴스가 생성되어 있지 않으면 Connection 인스턴스와 Statement 인스턴스를 생성
        if (statement == null || statement.isClosed()) {
            createStatement();
        }

        // Statement 인스턴스로 SQL 문을 실행
        resultSet = statement.executeQuery(query);
        System.out.println("QUERY: " + query);

        // ResultSet 인스턴스에서 레코드(records)의 개수를 확인
        resultSet.last();
        int numRows = resultSet.getRow();
        resultSet.beforeFirst();

        System.out.println("ROWS: " + numRows);

        // ResultSet 인스턴스에서 각 레코드의 컬럼(columns)의 개수를 확인
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numColumns = metaData.getColumnCount();

        System.out.println("COLUMNS: " + numColumns);
    }

    /**
     * SQL 문을 실행하는 PreparedStatement 인스턴스를 생성
     *
     * @param query 실행할 SQL 문
     */
    private void prepareStatement(String query) throws SQLException {
        // Connection 인스턴스가 생성되어 있지 않으면 Connection 인스턴스를 생성
        if (connection == null || connection.isClosed()) {
            getConnection();
        }

        // preparedStatement 인스턴스가 생성되어 있는지 확인하고, preparedStatement 인스턴스를 생성
        if (preparedStatement == null || preparedStatement.isClosed()) {
            preparedStatement = connection.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            System.out.println("SUCCESS: Create the PreparedStatement object.");
            System.out.println("QUERY: " + query);
        }
    }

    /**
     * PreparedStatement 인스턴스로 SQL 문을 실행
     *
     * @return SQL 문이 적용된 레코드의 개수
     */
    private int executeUpdate() throws SQLException {
        // PreparedStatement 인스턴스가 생성되어 있지 않으면 예외 발생
        if (preparedStatement == null || preparedStatement.isClosed()) {
            throw new RuntimeException("The PreparedStatement object is closed.");
        }

        // PreparedStatement 인스턴스로 SQL 문을 실행
        int numRows = preparedStatement.executeUpdate();
        System.out.println("ROWS: " + numRows);

        return numRows;
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
     * members 테이블의 모든 레코드를 조회
     *
     * @return 조회한 레코드의 목록
     */
    public List<MemberVo> getMemberList() {
        List<MemberVo> memberList = new ArrayList<>();

        try {
            // 실행할 SQL 문을 작성
            String query = "SELECT * FROM members ORDER BY id DESC";

            // SQL 문을 실행
            executeQuery(query);

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
                memberList.add(memberVo);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }

        return memberList;
    }

    /**
     * members 테이블에 새로운 레코드를 추가
     *
     * @param memberVo 추가할 회원 정보
     */
    public void insertMember(MemberVo memberVo) {
        try {
            // members 테이블에 새로운 레코드를 추가하는 SQL 문
            String query = "INSERT INTO members (id, username, password, name, email) " +
                    "VALUES (NEXTVAL('SEQ_MEMBER_ID'), ?, ?, ?, ?)";

            // SQL 문을 실행하는 PreparedStatement 인스턴스를 생성
            prepareStatement(query);

            // PreparedStatement 인스턴스의 setString 메서드 등으로 SQL 문의 지정한 위치(?)에
            // 원하는 값을 치환한다.
            preparedStatement.setString(1, memberVo.getUsername());
            preparedStatement.setString(2, memberVo.getPassword());
            preparedStatement.setString(3, memberVo.getName());
            preparedStatement.setString(4, memberVo.getEmail());

            // SQL 문을 실행
            executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    /**
     * members 테이블에서 레코드 삭제
     *
     * @param id 삭제하고자 하는 레코드의 id
     */
    public void deleteMember(String id) {
        // 문자열로 전달된 id를 정수로 변환
        int intId;

        try {
            // 문자열로 전달된 id를 정수로 변환
            intId = Integer.parseInt(id);

            // 전달된 id로 members 테이블에서 레코드를 삭제할 SQL 문
            String query = "DELETE FROM members WHERE id = ?";

            // PreparedStatement 인스턴스를 생성
            prepareStatement(query);

            // SQL 문의 지정한 위치에 값을 치환
            preparedStatement.setInt(1, intId);

            // SQL 문을 실행
            executeUpdate();
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid id for deleting a member.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }
}

