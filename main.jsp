<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="bbs.BbsDAO"%>
<%@ page import="bbs.Bbs"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/custom.css"> <!-- css 파일 참조시키기 -->
<title>맛 있을 지도!</title>
<style>

</style>
</head>
<body>
	<% 
		String userID =null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
		int pageNumber =1; //기본페이지
		if(request.getParameter("pageNumber") != null){
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			System.out.println(pageNumber);
		}
	%>
	<nav class="navbar navbar-default">
 		<div class="navbar-header">
  			<button type="button" class="navbar-toggle collapsed"
   				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
   				aria-expanded="false">
    			<span class="icon-bar"></span>
   				<span class="icon-bar"></span>
  				<span class="icon-bar"></span>
  			</button>
			<a class="navbar-brand" href="main.jsp">맛 있을 지도!</a>	
		</div>
		<div class="collapse navbar-collapse" id ="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="main.jsp">메인</a></li>
				<li><a href="bbs.jsp">게시판</a></li>
			</ul>
			<%
				if(userID ==null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
			<%
				} else{
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul>
				</li>
			</ul>
			<%
				}
			%>

		</div>
	</nav>
	
	
	<div class ="containeer">
		<div class ="jumbotron">
			<h1 style = "text-align:center">맛 있을 지도!</h1>
			<p style = "text-align:center">눈으로 맛 보는 음식 ~!</p>
		</div>
	</div>
	
	

<!-- 지도누르면 지역으로 이동하는 링크 지도  -->	
	
	<div align="center" >
		<br><br><br><br><br><br><br><br><br><br><br><br>
		<img src="Images/3.jpg" border="0" width="529" height="720" usemap="#image1" alt="" >
		<map name="image1" >
		<area  alt="인천" title="인천" href="http://localhost:8080/BBS/login.jsp" shape="rect" coords="80,163,156,184"   >    <!-- alt 랑 title없어도 돌아감 -->
		<area  alt="서울" title="서울" href="http://localhost:8080/BBS/login.jsp" shape="rect" coords="157,142,243,186" style="outline:none;"    >
		<area  alt="경기도" title="경기도" href="http://localhost:8080/BBS/join.jsp" shape="rect" coords="131,76,256,237" style="outline:none;"  >
		<area  alt="대전" title="대전" href="http://localhost:8080/BBS/login.jsp" shape="rect" coords="197,293,283,337" style="outline:none;" >
		<area  alt="광주" title="광주" href="http://localhost:8080/BBS/join.jsp" shape="rect" coords="143,421,229,465" style="outline:none;">
		<area  alt="대구" title="대구" href="http://localhost:8080/BBS/login.jsp" shape="rect" coords="321,356,407,400" style="outline:none;" >
		<area  alt="대구1" title="울산" href="http://localhost:8080/BBS/join.jsp" shape="rect" coords="386,396,489,434" style="outline:none;">
		<area  alt="부산" title="부산" href="http://localhost:8080/BBS/login.jsp" shape="rect" coords="366,435,475,474" style="outline:none;" >
		<area  alt="제주" title="제주도" href="http://localhost:8080/BBS/join.jsp" shape="rect" coords="83,646,228,694" style="outline:none;" >
		<area  alt="세종" title="세종" href="http://localhost:8080/BBS/join.jsp" shape="rect" coords="197,275,246,304" style="outline:none;" >
		<area  alt="강원도" title="강원" href="http://localhost:8080/BBS/login.jsp" shape="rect" coords="198,18,446,209" style="outline:none;">
		<area  alt="경상남도" title="전라남도" href="http://localhost:8080/BBS/join.jsp" shape="rect" coords="64,426,248,576" style="outline:none;" >
		<area  alt="경상북도" title="경상북도" href="http://localhost:8080/BBS/login.jsp" shape="rect" coords="321,218,441,401" style="outline:none;" >
		<area  alt="전라북도" title="전라북도" href="http://localhost:8080/BBS/join.jsp" shape="rect" coords="117,349,253,439" style="outline:none;">
		<area  alt="경상북도" title="경상북도" href="http://localhost:8080/BBS/login.jsp" shape="rect" coords="275,249,345,400" style="outline:none;">
		<area  alt="경상남도" title="경상남도" href="http://localhost:8080/BBS/join.jsp" shape="rect" coords="244,372,459,523" style="outline:none;">
		<area  alt="충청북도" title="충청북도" href="http://localhost:8080/BBS/login.jsp" shape="rect" coords="214,200,353,351" style="outline:none;" >
		<area  alt="충청남도" title="충청남도" href="http://localhost:8080/BBS/login.jsp" shape="rect" coords="83,222,248,362" style="outline:none;" >
		</map>
	</div>
	
	
<!-- BEST10 -->


	<div style=" position:fixed; right:20px; bottom:20px; background-color:gold;"> <h2>오늘의 인기글</h2>
		<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
			<tbody>
			<%
				BbsDAO bbsDAO = new BbsDAO();
				ArrayList<Bbs> list = bbsDAO.getBest();
				for(int i=0; i<list.size(); i++){
			%>
			<tr>
				<td><a href="view.jsp?bbsID=<%= list.get(i).getBbsID() %>"><%=i+1 %>위 <%= list.get(i).getBbsTitle() %></a></td>
			</tr>		
			<%
				}
			%>
			</tbody>
		</table>
	
	</div>


	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/jquery.min.js"></script>

</body>
</html>