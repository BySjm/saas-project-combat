<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../base.jsp"%>
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
            生产工厂管理
            <small>工厂管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="/"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="/factory/list.do">工厂列表</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">工厂信息</div>
            <form id="editForm" action="/factory/edit.do" method="post">
                <input type="hidden" id="id" name="id" value="${factory.id}">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">公司全名</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="公司全名" name="fullName" value="${factory.fullName}">
                    </div>

                    <div class="col-md-2 title">厂家类型</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${factory.ctype=="货物"?'checked':''}  name="ctype" value="货物" >货物</label></div>
                            <div class="radio"><label><input type="radio" ${factory.ctype=="附件"?'checked':''}  name="ctype" value="附件">附件</label></div>
                        </div>
                    </div>

                    <div class="col-md-2 title">联系人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="联系人" name="contacts" value="${factory.contacts}">
                    </div>

                    <div class="col-md-2 title">电话</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="电话" name="phone" value="${factory.phone}">
                    </div>

                    <div class="col-md-2 title">地址</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="地址" name="address" value="${factory.address}">
                    </div>

                    <div class="col-md-2 title">传真</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="传真" name="fax" value="${factory.fax}">
                    </div>

                    <div class="col-md-2 title">检查员</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="检查员" name="inspector" value="${factory.inspector}">
                    </div>

                    <div class="col-md-2 title">状态</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${factory.state==0?'checked':''} name="state" value="0">停用</label></div>
                            <div class="radio"><label><input type="radio" ${factory.state==1?'checked':''} name="state" value="1">启用</label></div>
                        </div>
                    </div>

                    <%--<div class="col-md-2 title">启用时间</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="启用时间"  name="createTime" class="form-control pull-right"
                                   value="${factory.createTime}" id="datepicker1">
                        </div>
                    </div>--%>

                    <%--<div class="col-md-2 title">修改时间</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="启用时间"  name="updateTime" class="form-control pull-right"
                                   value="${factory.updateTime}" id="datepicker">
                        </div>
                    </div>--%>

                    <div class="col-md-2 title">排序号</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="排序号" name="orderNo" value="${factory.orderNo}">
                    </div>
                    <div class="col-md-2 title">公司简称</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="公司简称" name="factoryName" value="${factory.factoryName}">
                    </div>
                    <%--<div class="col-md-2 title">说明</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="说明" name="remark" value="${factory.remark}">
                    </div>--%>
                    <div class="col-md-2 title rowHeight2x">说明</div>
                    <div class="col-md-10 data rowHeight2x">
                        <textarea class="form-control" rows="3" name="remark">${factory.remark}</textarea>
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
    $('#datepicker').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#datepicker1').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>
</html>