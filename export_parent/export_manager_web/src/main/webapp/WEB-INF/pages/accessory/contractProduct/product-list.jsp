<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<%--<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="/factory/delete.do?id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
    /*不行*/


</script>--%>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            货物管理
            <small>货物管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <section class="content">
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">货物列表</h3>
            </div>
            <div class="box-body">
                <div class="table-box">


                    <div class="pull-left">
                        <div class="form-group form-inline">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/accessory/contractProduct/toAdd.do"'><i class="fa fa-file-o"></i> 新建</button>
                               <%-- <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>--%>
                                <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                            </div>
                        </div>
                    </div>
                    <div class="box-tools pull-right">
                        <div class="has-feedback">
                            <input type="text" class="form-control input-sm" placeholder="搜索">
                            <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        </div>
                    </div>
                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                            <td class="tableHeader">序号</td>
                            <td class="tableHeader">厂家</td>
                            <td class="tableHeader">货号</td>
                            <td class="tableHeader">装率</td>
                            <td class="tableHeader">箱数</td>
                            <td class="tableHeader">包装单位</td>
                            <%--<td class="tableHeader">数量</td>--%>
                            <td class="tableHeader">单价</td>
                            <td class="tableHeader">总金额</td>
                            <td class="tableHeader">操作</td>
                        </tr>
                        </thead>
                        <tbody class="tableBody" >
                        ${links }
                        <c:forEach items="${page.list}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                <td><input type="checkbox" name="id" value="${o.id}"/></td>
                                <td>${status.index+1}</td>
                                <td>${o.factoryName}</td>
                                <td>${o.productNo}</td>
                                <td>${o.loadingRate}</td>
                                <td>${o.boxNum}</td>
                                <td>${o.packingUnit}</td>
                                <%--<td>${o.cnumber}</td>--%>
                                <td>${o.price}</td>
                                <td>${o.amount}</td>
                                <td>
                                    <a href="${ctx}/accessory/contractProduct/toUpdate.do?id=${o.id}">[修改]</a>
                                    <a href="${ctx}/accessory/contractProduct/delete.do?id=${o.id}&contractId=${o.contractId}">[删除]</a>
                                    <a href="${ctx}/accessory/extCproduct/list.do?contractProductId=${o.id}">[附件列表操作]</a>
                                </td>
                            </tr>

                            <c:forEach items="${o.extCproducts}" var="ext" varStatus="status">
                                <tr height="40" class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                    <td>&nbsp;</td>
                                    <td align="right"><font color="blue">附件：${status.index+1}&nbsp;</font></td>
                                    <td>${ext.factoryName}</td>
                                    <td>${ext.productNo}</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>${ext.packingUnit}</td>
                                    <%--<td>${ext.cnumber}</td>--%>
                                    <td>${ext.price}</td>
                                    <td>${ext.amount}</td>
                                </tr>
                            </c:forEach>
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
                    <jsp:param value="/accessory/contractProduct/list.do?companyId=${companyId}" name="pageUrl"/>
                </jsp:include>
            </div>
            <!-- /.box-footer-->

        </div>
    </section>

</div>
<!-- 内容区域 /-->
</body>

</html>