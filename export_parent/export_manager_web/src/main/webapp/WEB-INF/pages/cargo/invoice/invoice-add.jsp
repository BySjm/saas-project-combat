<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
            货运管理
            <small>发票管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">发票管理</a></li>
            <li class="active">发票单编辑</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--委托单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">编辑发票单</div>
            <form id="editForm" action="${ctx}/cargo/invoice/edit.do" method="post" >
                <input type="text" name="invoiceId" value="${invoice.invoiceId}">
                <input id="checkedId" type="hidden" name="packingListId" value="">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">提单号:</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="提单号" name="blNo" value="${invoice.blNo}">
                    </div>

                    <div class="col-md-2 title">贸易条款:</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="贸易条款" name="tradeTerms" value="${invoice.tradeTerms}">
                    </div>

                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='submitFun()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">委托单列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 ** 委托单 状态为1 已上运 ** -->
                <div class="table-box">
                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <th class="" style="padding-right:0px;">

                            </th>
                            <th class="sorting">委托单号</th>
                            <th class="sorting">货运类型</th>
                            <th class="sorting">托运方</th>
                            <th class="sorting">收货方</th>
                            <th class="sorting">通知人</th>
                            <th class="sorting">信用证号</th>
                            <th class="sorting">装运港</th>
                            <th class="sorting">目的港</th>
                            <th class="sorting">装货日期</th>
                            <th class="sorting">运费</th>
                            <th class="sorting">校验人</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="o" varStatus="status">
                            <tr>
                                <td><input type="checkbox" onclick="clickCheck()" name="shippingOrderId" value="${o.shippingOrderId}"/></td>
                                <td>${o.orderType}</td>
                                <td>${o.shipper}</td>
                                <td>${o.consignee}</td>
                                <td>${o.notifyParty}</td>
                                <td>${o.lcNo}</td>
                                <td>${o.portOfLoading}</td>
                                <td>${o.portOfDischarge}</td>
                                <td><fmt:formatDate value="${o.loadingDate}" pattern="yyyy-MM-dd"/></td>
                                <td>${o.freight}</td>
                                <td>${o.checkBy}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!--数据列表/-->
                    <!--工具栏/-->
                </div>
                <!-- 数据表格 /-->
            </div>
            <!-- /.box-body -->

            <!-- .box-footer-->
            <div class="box-footer">
                <jsp:include page="../../common/page.jsp">
                    <jsp:param value="/cargo/shipping/list.do" name="pageUrl"/>
                </jsp:include>
            </div>
            <!-- /.box-footer-->
        </div>
        </form>
    </section>

</div>
<!-- 内容区域 /-->
</body>
<script src="../../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="../../css/style.css">
<%--<script>
    $('#loadingDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>--%>


<!-- 获取选中的装箱单id, 存放到表单的隐藏域中 -->
<script>
    function clickCheck() {

        let size = $("input:checkbox:checked").length;
        if (1 !== size) {
            alert("请勾选待处理的记录，且每次只能勾选一个");
            $("input:checkbox:checked").prop("checked", false)
        } else {
            $('#checkedId').val($('input[type=checkbox]:checked').val());
        }
    }
</script>

<!-- 提交表单 -->
<script>
    function submitFun() {
        let checkedIdValue = $('#checkedId').val();
        if (checkedIdValue == null || '' === checkedIdValue) {
            alert("委托单不能为空!");
        } else {
            $('#editForm').submit();
        }
    }
</script>


</html>