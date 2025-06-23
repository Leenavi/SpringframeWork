<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 닫히지 않은 <div>는 제거 또는 상단에서 제대로 열어야 합니다 -->
<!-- 예: <div id="wrapper"> <div id="page-wrapper"> 가 상단에 없다면 이 닫는 div들도 제거 -->

<!-- jQuery (필요 시 활성화) -->
<script src="/resources/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script src="/resources/vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="/resources/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script src="/resources/vendor/datatables-responsive/dataTables.responsive.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/resources/dist/js/sb-admin-2.js"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
  $(document).ready(function() {
    $('#dataTables-example').DataTable({
      responsive: true
    });

    $(".sidebar-nav")
      .attr("class", "sidebar-nav navbar-collapse collapse")
      .attr("aria-expanded", 'false')
      .attr("style", "height:1px");
  });
</script>

</body>
</html>
