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
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board Register
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <form action="/board/register" method="post">
                	<div class="form-group">
                		<label>Title</label><input class="form-control" name="title">
                	</div>
                	<div class="form-group">
                		<label>Text area</label>
                		<textarea rows="3" class="form-control" name="content"></textarea>
                	</div>
                	<div class="form-group">
                		<label>Writer</label><input class="form-control" name="writer">
                	</div>
                	<button class="btn btn-info" type="submit">Submit Button</button>
                	<button class="btn btn-default" type="reset">Reset Button</button>
                </form>
            </div>
            <!-- end panel-body -->
        </div>
        <!-- end panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                File Attach
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
            	<div class="form-group uploadDiv">
            		<input type="file" name='uploadFile' multiple="multiple">
            	</div>
            	<div class='uploadResult'>
            		<ul>
            		</ul>
            	</div>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
            
<%@ include file="../includes/footer.jsp" %>

<script>
function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display", "flex").show();
	
	$(".bigPicture")
	.html("<img src='/display?fileName="+encodeURI(fileCallPath)+"'>")
	.animate({width: '100%', height: '100%'}, 1000);
} //end showImages


$(document).ready(function() {
	
	let formObj = $("form[role='form']");
	
	$("button[type='submit']").on("click", function(e){
		e.preventDefault();
		console.log("submit clicked");
		
		let str = "";
		
		$(".uploadResult ul li").each(function(i, obj){
			
			let jobj = $(obj);
			console.dir(jobj);
			
			str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>"
			str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>"
			str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>"
			str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("type")+"'>"
		});
		formObj.append(str).submit();
	});
	
	
	let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$"); //jquery 정규 표현식
	let maxSize = 5242880;  //5MB
	
	$(".uploadResult").on("click","button", function(e){
		
		console.log("delete file");
		
		let targetFile = $(this).data("file");
		let type = $(this).data("type");
		
		let targetLi = $(this).closest("li");
		console.log(targetFile);
		
		$.ajax({
			url: '/deleteFile',
			data: {fileName: targetFile, type:type},
			dataType: 'text',
			type: 'post',
			success: function(result){
				alert(result);
				targetLi.remove();
			}
		});
	});  //$.ajax
	
	$(".bigPictureWrapper").on("click", function(e){
		$(".bigPicture").animate({width:'0%', height: '0%'}, 1000);
		setTimeout(()=>{
			$(this).hide();
		});
	})
	
	function checkExtension(fileName, fileSize) {
		if(fileSize >= maxSize) {
			alert("파일 사이즈 초과");
			return false;
		}
		
		if(regex.test(fileName)) {
			alert("해당 종류의 파일은 업로드 할 수 없음.");
			return false;
		}
		return true;
	}
	
	$("input[type='file']").change(function(e){
		let formData = new FormData();
		let inputFile = $("input[name='uploadFile']");
		
		let files = inputFile[0].files;
		/* console.log(inputFile)
		console.log("-------------------")
		console.log(files) */
		
		for(let i=0; i<files.length; i++){
			
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
				
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url: "/uploadAjaxAction",
			type: "post",
			processData: false,  //필수 - > 데이터를 문자열로 변환하지말라!
			contentType: false,  //contentType 설정하지않음( 자동으로 enctype="multipart/form-data")
			data: formData,
			dataType: 'json',  //전달데이타가 형식이 json
			success: function(result){
				console.log(result);
				showUploadResult(result);
//				$(".uploadDiv").html(cloneObj.html());
			}
		});
		
	});
	
	let uploadResult = $(".uploadResult ul");
	
	function showUploadResult(uploadResultArr){
		if(!uploadResultArr || uploadResultArr.length == 0){return;}
		
		let uploadUL = $(".uploadResult ul");
		
		let str = "";
		
		$(uploadResultArr).each(function(i, obj){
			
			if(obj.image) {
				
				let fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
				
				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+
						obj.fileName+"' data-type='"+obj.image+"'><div><span>"+obj.fileName+"</span>"+
						"<button type='button' class='btn btn-warning btn-circle' data-file=\'"+fileCallPath+"\' data-type='image'>"+
						"<i class='fa fa-times'></i></button><br>"+
						"<img src='/display?fileName="+fileCallPath+"'></div></li>";
			}else{
				let fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
				let fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
	               
//	               let originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
	               
//	               originPath = originPath.replace(new RegExp(/\\/g), "/");
	               
				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+
						obj.fileName+"' data-type='"+obj.image+"'><div><span>"+obj.fileName+"</span>"+
						"<button type='button' class='btn btn-warning btn-circle' data-file=\'"+fileCallPath+"\' data-type='file'>"+
						"<i class='fa fa-times'></i></button><br>"+
						"<a href='/download?fileName=" + fileLink + "'>"+
						"<img src='/resources/img/attach.png'></a></div></li>";
			}
		});
		
		uploadResult.append(str);
		
	}; //end showUploadFile
	
	
});
	
	
</script>