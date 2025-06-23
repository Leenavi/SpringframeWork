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
                Board Read Page
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
            <form role="form" action="/board/modify" method="post">
            <!--role : 웹 페이지의 구조와 의미를 명확히 하기 위해 사용되는 웹 접근성 속성 -->
	            <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
	            <input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
	            <input type="hidden" name="keyword" value=<c:out value="${cri.keyword}"/>>
                <input type="hidden" name="type" value=<c:out value="${cri.type}"/>>
            
            <div class="form-group">
                   		<label>Bno</label><input class="form-control" name="bno"
                   		value="<c:out value='${board.bno}' />" readonly="readonly">
                   </div>
                   <div class="form-group">
                   		<label>Title</label><input class="form-control" name="title"
                   		value="<c:out value='${board.title}' />">
                   </div>
                   <div class="form-group">
	                   <label>Text area</label>
	                   <textarea row="3" class="form-control" name="content">
	                   <c:out value='${board.content}' /></textarea>
                   </div>
                   <div class="form-group">
                   		<label>Writer</label><input class="form-control" name="writer"
                   		value="<c:out value='${board.writer}' />" readonly="readonly">
                   </div>
                   
                   <button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
                   <button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
                   <button type="submit" data-oper='list' class="btn btn-info">List</button>
            </form>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<script type="text/javascript">
	$(document).ready(function() {
		let formObj = $("form");
		
		$('button').on("click", function(e) {
			
			e.preventDefault();
			
			let operation = $(this).data("oper");
			
			if(operation ==='remove'){
				formObj.attr("action", "/board/remove");
			}else if(operation === 'list') {
				//move to list
				formObj.attr("action", "/board/list").attr("method", "get");
				let pageNumTag = $("input[name='pageNum']").clone();  //복사값을 쓰는 이유는 원본값을 유지하기 위함인 듯
				let amountTag = $("input[name='amount']").clone();
				let keywordTag = $("input[name='keyword']").clone();
				let typeTag = $("input[name='type']").clone();
				
				formObj.empty(); //input 태그 속성값 클리어
				formObj.append(pageNumTag);  //복사(clone())한 값 추가.
				formObj.append(amountTag);
				formObj.append(keywordTag);
				formObj.append(typeTag);
			}
			formObj.submit();
		});
	});
</script>
            
<%@ include file="../includes/footer.jsp" %>