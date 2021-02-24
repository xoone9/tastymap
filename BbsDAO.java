package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public BbsDAO() {
		try { // DB 연동하는 부분
			String dbURL = "jdbc:mysql://localhost:3306/BBS?characterEncoding=UTF-8&serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "root";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String getDate() { // 날짜를 상수로 반환
		String SQL ="SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ""; // 데이터베이스 오류
	}
	
	public int getNext() { //게시판 번호 리펀
		String SQL ="SELECT bbsID FROM BBS ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) +1;
			}
			return 1; //첫번째 게시물인경우
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이버베이스 오류
	}
	public int write(String bbsTitle, String userID, String bbsContent,String bbsLocation,
			String foodSort,String foodName,String restaurantName,String address) {  // 데이터베이스에 게시물 입력
		String SQL ="INSERT INTO BBS VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);
			pstmt.setString(7, bbsLocation);
			pstmt.setString(8, foodSort);
			pstmt.setString(9, foodName);
			pstmt.setString(10, restaurantName);
			pstmt.setString(11, address);
			pstmt.setInt(12, 0);
			
			return pstmt.executeUpdate(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	public ArrayList <Bbs> getList(int pageNumber){ //게시판에 필요한 정보 데이터베이스에서 불러오기
		String SQL ="SELECT *FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";
		ArrayList<Bbs> list= new ArrayList<Bbs>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber -1)* 10);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Bbs bbs =new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				bbs.setBbsLocation(rs.getString(7));
				bbs.setFoodSort(rs.getString(8));
				bbs.setRecommendNum(rs.getInt(12));
				list.add(bbs);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list; 	
	}
	
	public ArrayList <Bbs> getBest(){ //게시판에 필요한 정보 데이터베이스에서 불러오기
		String SQL ="SELECT * from bbs where recommendNum > 0 order by recommendNum desc";
		ArrayList<Bbs> list= new ArrayList<Bbs>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			//pstmt.setInt(1, getNext() - (pageNumber -1)* 10);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Bbs bbs =new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setRecommendNum(rs.getInt(12));
				list.add(bbs);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list; 	
	}
	
	public boolean nextPage(int pageNumber) {  // 다음 이전 페이지 처리
		String SQL ="SELECT *FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber -1)* 10);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false; //�뜲�씠�꽣踰좎씠�뒪 �삤瑜�
	}
	
	public Bbs getBbs(int bbsID) {
		String SQL ="SELECT *FROM BBS WHERE bbsID = ? ";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Bbs bbs =new Bbs(); //6개의 변수 받아 bbs인스턴스에 넣어 반환
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				bbs.setBbsLocation(rs.getString(7));
				bbs.setFoodSort(rs.getString(8));
				bbs.setFoodName(rs.getString(9));
				bbs.setRestaurantName(rs.getString(10));
				bbs.setAddress(rs.getString(11));
				bbs.setRecommendNum(rs.getInt(12));
				return bbs;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public int update(int bbsID, String bbsTitle, String bbsContent, String bbsLocation, String foodSort, String foodName, String restaurantName, String address) { //게시판 정보 수정하는 곳
		String SQL ="UPDATE BBS SET bbsTitle =?, bbsContent = ?, bbsLocation = ?, foodSort =?, foodName = ?, restaurantName = ?, address =? WHERE bbsID =?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);
			pstmt.setString(3, bbsLocation);
			pstmt.setString(4, foodSort);
			pstmt.setString(5, foodName);
			pstmt.setString(6, restaurantName);
			pstmt.setString(7, address);
			pstmt.setInt(8, bbsID);
			return pstmt.executeUpdate(); //
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 

	}
	public int delete(int bbsID) { 
		String SQL ="UPDATE BBS SET bbsAvailable = 0 WHERE bbsID =?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			return pstmt.executeUpdate(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
		
	}
	
	public int recom(int bbsID) { 
		String SQL ="UPDATE BBS SET recommendNum = recommendNum + 1 WHERE bbsID =?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			return pstmt.executeUpdate(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
		
	}
	
}

