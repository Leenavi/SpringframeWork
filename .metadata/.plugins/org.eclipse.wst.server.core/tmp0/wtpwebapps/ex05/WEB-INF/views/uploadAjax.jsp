<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<style>
	.uploadResult{
		width: 100%;
		background-color: gray;
	}
	
	.uploadResult ul{
		display: flex;
		flex-flow: row;
		justify-content: center;
		align-items: center;
	}
	
	.uploadResult ul li{
		list-style: none;
		padding: 10px;
		align-content: center;
		text-align: center;
	}
	.uploadResult ul li img{
		width: 100px;
	}
	
	.uploadResult ul li img span{
		color: white;
	}
	
	.bigPictureWrapper{
		position: absolute;
		display: none;
		justfy-content: center;
		align-items: center;
		top: 0%;
		width: 100%;
		height: 100%;
		background-color: gray;
		z-index: 100%;
		background: rgba(255,255,255,0.5);
	}
	
	.bigPicture{
		position: relative;
		display: flex;
		justfy-content: center;
		align-items: center;
	}
	
	.bigPicture img{
		width: 600px;
	}
	
</style>
</head>
<body>
	<h1>upload with Ajax </h1>
	
	<div class = 'uploadDiv'>
		<input type = 'file' name='uploadFile' multiple="multiple">
	</div>
	
	<div class='uploadResult'>
		<ul>
		</ul>
	</div>
	
	<button id='uploadBtn'>Upload</button>
	
	<div class="bigPictureWrapper">
		<div class="bigPicture"></div>
	</div>
	
<script type="text/javascript">

	function showImage(fileCallPath){
		$(".bigPictureWrapper").css("display", "flex").show();
		
		$(".bigPicture")
		.html("<img src='/display?fileName="+encodeURI(fileCallPath)+"'>")
		.animate({width: '100%', height: '100%'}, 1000);
	} //end showImages


	$(document).ready(function() {
		
		let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$"); //jquery 정규 표현식
		let maxSize = 5242880;  //5MB
		
		$(".uploadResult").on("click","span", function(e){
			let targetFile = $(this).data("file");
			let type = $(this).data("type");
			console.log(targetFile);
			
			$.ajax({
				url: '/deleteFile',
				data: {fileName: targetFile, type:type},
				dataType: 'text',
				type: 'post',
				success: function(result){
					alert(result);
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
			}
			return true;
		}
		
		let uploadResult = $(".uploadResult ul");
		
		function showUploadFile(uploadResultArr){
			
			let str = "";
			
			$(uploadResultArr).each(function(i, obj){
				
				if(!obj.image) {
					
					let fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid + 
							"_" + obj.fileName);
					
					let fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
					
					str += "<li><div><a href='/download?fileName="+fileCallPath+"'>"
							+ "<img src='/resources/img/attach.png'>"
							+ obj.fileName + "</a>"+
							"<span data-file=\'"+fileCallPath+"\' data-type='image'>X</span></div></li>"
				}else{
					let fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
		               
		               let originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
		               
		               originPath = originPath.replace(new RegExp(/\\/g), "/");
		               
		               str += "<li><div><a href=\"javascript:showImage(\'"+ 
		                     originPath + "\')\"><img src='/display?fileName="+ 
		                     fileCallPath +"'></a>"+
		                     "<span data-file=\'"+fileCallPath+"\' data-type='image'>X</span></div></li>"
				}
			});
			
			uploadResult.append(str);
			
		}; //end showUploadFile
		
		
		let cloneObj = $(".uploadDiv").clone();
		
		$("#uploadBtn").on("click", function(e){
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
					
					showUploadFile(result);
					$(".uploadDiv").html(cloneObj.html());
				}
			});
		});	 
	});
</script>

</body>
</html>