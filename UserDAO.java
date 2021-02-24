package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public 	UserDAO() {//DB에 접속하는 부분
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS?characterEncoding=UTF-8&serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "root";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public int login(String userID, String userPassword) { //유저 아이디와 비밀번호를 DB에서 검색하여 로그인처리하는 곳
		String SQL ="SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공
				}
				else
					return 0; // 비밀번호 불일치
			}
			return -1; /// 아이디 없음
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2; // -2는 데이터베이스 오류
	}
	public int join(User user) {//회원가입하여 유저들 정보 DB로 저장하는 곳
		String SQL = "INSERT INTO USER VALUES(?,?,?,?,?,?)";
		try{
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			pstmt.setString(6, user.getUserPhone());
			return pstmt.executeUpdate();  //0이상 값 반환
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; ///데이터베이ㅡㅅ 오류
	}
}
