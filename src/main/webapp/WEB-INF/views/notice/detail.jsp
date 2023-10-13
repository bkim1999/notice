<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script> 

  $(function(){
	  fnModifyMode();
	  fnDelete();
	  fnList();
    fnModifyResult();
  });
  
  function fnModifyMode(){
	 $('#btn_modifyMode').click(function(){
		 $.ajax({
      type: 'get',
      url: '${contextPath}/notice/write.do',
      dataType: 'html',
      success: function(resData){
        $('body').html(resData);
        $('#detailMode').html('<a href="${contextPath}/notice/detail.do?noticeNo=${notice.noticeNo}">←뒤로 가기</a>');
        $('#page_title').text('공지 편집하기');
        $('#gubun').val('${notice.gubun}');
        $('#title').val('${notice.title}');
        $('#content').val('${notice.content}');
        $('#btn_write').text('편집완료');
        $('#frm_write').attr('action', '${contextPath}/notice/modify.do');
        $('#noticeNo').attr('name', 'noticeNo');
        $('#noticeNo').val('${notice.noticeNo}');
      }
    })
	 }); 
  }
  
  function fnDelete(){
	   $('#btn_delete').click(function(){
		   if(confirm('공지사항을 삭제할까요?')){
	       $('#frm_delete').submit();
		   }
	   }); 
  }
  
  function fnList(){
	   $('#btn_list').click(function(){
	     location.href="${contextPath}/notice/list.do";
	   }); 
	}
  
  function fnModifyResult(){
    var modifyResult = '${modifyResult}';
    if(modifyResult !== ''){
      if(modifyResult === '1'){
        alert('공지사항이 수정되었습니다.');
      } else{
        alert('공지사항이 수정되지 않았습니다.');
      }
    }
  }
  
</script>
<body>
  
  <div>
    <h1>${notice.noticeNo}번 공지사항</h1>
    <div>구분 : 
      <c:if test="${notice.gubun == 1}"> 
        긴급 
      </c:if>
      <c:if test="${notice.gubun == 2}"> 
        일반
      </c:if>
    </div>
    <div>제목 : ${notice.title}</div>
    <div>${notice.content}</div>
  </div>
  
  <hr>
  
  <div>
    <button type="button" id="btn_modifyMode">편집</button>
    <button type="button" id="btn_delete">삭제</button>
    <button type="button" id="btn_list">목록</button>
    <form id="frm_delete" action="${contextPath}/notice/delete.do" method="post">
      <input type="hidden" name="noticeNo" value="${notice.noticeNo}">
    </form>
  </div>
  
</body>
</html>