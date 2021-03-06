<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            反馈管理
            <small>${noun}反馈</small>
        </h1>
    </section>
    <!-- 内容头部 /-->
    <h3>☆☆☆☆您的反馈将提交给${noun}管理员！我们会认真接受您的反馈☆☆☆☆</h3>

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">反馈内容填写</div>
            <form id="editForm" action="${ctx}/system/feedBack/save.do" method="post">
                <div class="row data-type" style="margin: 0px">
                    <input type="hidden" name="ToPeople" value="${ToPeople}">

                    <div class="col-md-2 title">反馈标题</div>
                    <div class="col-md-10 data">
                        <input type="text" class="form-control" placeholder="反馈标题" name="title">
                    </div>

                    <div class="col-md-2 title">反馈类型</div>
                    <div class="col-md-10 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio"  name="classType" value=1>管理</label></div>
                            <div class="radio"><label><input type="radio"  name="classType" value=2>安全</label></div>
                            <div class="radio"><label><input type="radio"  name="classType" value=3>建议</label></div>
                            <div class="radio"><label><input type="radio"  name="classType" value=4>其他</label></div>
                        </div>
                    </div>

                    <div class="col-md-2 title rowHeight2x">反馈内容</div>
                    <div class="col-md-10 data rowHeight2x">
                        <textarea class="form-control" rows="3" name="content" placeholder="请填写您的反馈内容"></textarea>
                    </div>
                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

</div>
<!-- 内容区域 /-->
</body>
<script src="../../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="../../css/style.css">
<script>
    $('#signingDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#deliveryPeriod').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#shipTime').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>
</html>