<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
    
<%@ include file="../includes/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Tables</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board List Page
                <button id="regBtn" type="button" class="btn btn-xs pull-right">새글 등록</button>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <!-- <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example"> -->
                <table width="100%" class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>#번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>수정일</th>
                        </tr>
                    </thead>
                    
                    <c:forEach var="board" items="${list}">
                    	<tr><!-- c:out 대신 그냥 el표기법을 쓸 수 있지만 보안상 좋지 않음. css 공격 등 -->
                    		<td><c:out value="${board.bno}" /></td>
                    		<td><a class="move" href='<c:out value="${board.bno }"/>'>
                    		<c:out value="${board.title }"/></a></td>
                    		<td><c:out value="${board.writer}" /></td>
                    		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}" /></td>
                    		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}" /></td>
                    	</tr>
                    </c:forEach>
                   
                </table>
                <!-- /.table-responsive -->
                
                <!-- 검색 조건 처리 -->
                <div class="row">
                	<div class="col-lg-12">
                		<form action="/board/list" id="searchForm">
                			<select name="type">
                				<option value=""
                				<c:out value="${pageMaker.cri.type == null? 'selected' : '' }"/>>--</option>
                				<option value="T"
                				<c:out value="${pageMaker.cri.type == 'T'? 'selected' : '' }"/>>제목</option>
                				<option value="C"
                				<c:out value="${pageMaker.cri.type == 'C'? 'selected' : '' }"/>>내용</option>
                				<option value="W"
                				<c:out value="${pageMaker.cri.type == 'W'? 'selected' : '' }"/>>작성자</option>
                				<option value="TC"
                				<c:out value="${pageMaker.cri.type == 'TC'? 'selected' : '' }"/>>제목 or 내용</option>
                				<option value="TW"
                				<c:out value="${pageMaker.cri.type == 'TW'? 'selected' : '' }"/>>제목 or 작성자</option>
                				<option value="TWC"
                				<c:out value="${pageMaker.cri.type == 'TWC'? 'selected' : '' }"/>>제목 or 내용 or 작성자</option>
                			</select>
                			<input type="text" name="keyword"
                			value=<c:out value="${pageMaker.cri.keyword}"/>>
                			<input type="hidden" name="pageNum" value='${pageMaker.cri.pageNum}'>
                			<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
                			<button class='btn btn-default'>검색</button>
                		</form>
                	</div>
                </div>
                
                <!-- 페이징 처리 -->
               	<div class='pull-right'>
	                <ul class="pagination">
	                
	                	<c:if test="${pageMaker.prev}">
		                	<li class="pageinate_button previous"><a href="${pageMaker.startPage-1}">이전</a></li>
		                </c:if>
		                
		                <c:forEach var="num" begin="${pageMaker.startPage}"
		                			end="${pageMaker.endPage}">
		                	<li class="pageinate_button ${pageMaker.cri.pageNum == num ? "active":""}"><a href="${num}">${num}</a></li>
						</c:forEach>
		                
		                <c:if test="${pageMaker.next}">
		               		<li class="pageinate_button next"><a href="${pageMaker.endPage+1}">다음</a></li>
		                </c:if>
	                </ul>
                </div>
                
                <!-- 페이징 종료 -->
                
                <form id="actionForm" action="/board/list" method="get">
                	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
                	<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                	<input type="hidden" name="keyword" value=<c:out value="${pageMaker.cri.keyword}"/>>
                	<input type="hidden" name="type" value=<c:out value="${pageMaker.cri.type}"/>>
                </form>
                
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
            
<!-- The Modal -->
  <div class="modal" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Modal Title</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <c:out value="${result}"/>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save Changes</button>
        </div>
        
      </div>
    </div>
  </div><!-- end the Modal -->    
            
<%@ include file="../includes/footer.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
		let result = '<c:out value="${result}"/>';
		
		checkModal(result);
		
		//브라우저의 현재 히스토리 항목을 새로운 상태로 대체.
		history.replaceState({}, null, null);
		
		function checkModal(result){
			if(result === "" || history.state) {
				return;
			}
			
			if (result>0) { //자바스크립트에서는 문자열(<c:out value="${result}"/>)이 숫자로 암묵적 변환돼서 비교 가능
				$(".modal-body").html("게시글 " + result + " 번이 등록되었습니다.");
			}
			
			$("#myModal").modal("show");  //modal을 띄워주는 코드.
										  //modal-body가 비어있다면 아무 내용 없이 modal만 뜰 것.
		} //end checkModal
		
		$("#regBtn").on("click", function() {
			self.location = "/board/register";
		});
		
		//페이지 번호 이벤트 처리
		let actionForm = $("#actionForm");
		$(".pageinate_button a").on("click", function(e) {
			e.preventDefault();
			console.log('click');
			actionForm.find("input[name='bno']").remove();
			actionForm.attr("action", "/board/list");
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		});
		
		//상세페이지 이동시 pageNum, amount 전달
		$(".move").on("click", function(e){
			e.preventDefault();
			actionForm.append("<input type='hidden' name='bno' value='" + $(this).attr("href") + "'>");
			actionForm.attr("action", "/board/get");
			actionForm.submit();
		});
		
		//검색 버튼 이벤트 처리
		let searchForm = $("#searchForm")
		$("#searchForm button").on("click", function(e){
			if(!searchForm.find("option:selected").val()){
				alert("검색 종류를 선택하세요.");
				return false;
			}
			if(!searchForm.find("input[name='keyword']").val()){
				alert("키워드를 입력하세요.");
				return false;
			}
			searchForm.find("input[name='pageNum']").val("1");  //필터링 된 상태로 1페이지로 이동해라
			e.preventDefault();
			
			searchForm.submit();
		});
	});
</script>