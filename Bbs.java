package bbs;

public class Bbs { //게시판 출력에 필요한 변수들
	private int bbsID; // 게시판 ID
	private String bbsTitle; // 게시판 제목
	private String userID;  // 유저ID
	private String bbsDate; // 게시판 작성 날짜
	private String bbsContent; // 게시판 내용
	private int bbsAvailable;  // 게시판 삭제 유무
	private String bbsLocation; // 위치
	private String foodSort; // 음식종류
	private String foodName; // 음식이름
	private String restaurantName; // 식당이름
	private String address; // 식당상세주소
	private int  recommendNum; // 추천수
	
	public int getBbsID() {
		return bbsID;
	}
	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getBbsDate() {
		return bbsDate;
	}
	public void setBbsDate(String bbsDate) {
		this.bbsDate = bbsDate;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}
	public int getBbsAvailable() {
		return bbsAvailable;
	}
	public void setBbsAvailable(int bbsAvailable) {
		this.bbsAvailable = bbsAvailable;
	}
	public String getBbsLocation() {
		return bbsLocation;
	}
	public void setBbsLocation(String bbsLocation) {
		this.bbsLocation = bbsLocation;
	}
	public String getFoodSort() {
		return foodSort;
	}
	public void setFoodSort(String foodSort) {
		this.foodSort = foodSort;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getRecommendNum() {
		return recommendNum;
	}
	public void setRecommendNum(int recommendNum) {
		this.recommendNum = recommendNum;
	}
	

	
}
