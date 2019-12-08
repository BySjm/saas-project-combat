<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
<script>
    function deleteById() {
        var id = getCheckId();
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="/system/user/delete.do?id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    function roleList() {
        var id = getCheckId();
        if(id) {
            location.href="/system/user/roleList.do?id="+id;
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            反馈管理
            <small>我的反馈</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <section class="content">
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">反馈列表</h3>
            </div>
            <div class="box-body">
                <div class="table-box">
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
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
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <th class="" style="padding-right:0px;">

                            </th>
                            <th class="sorting">序号</th>
                            <th class="sorting">提出人</th>
                            <th class="sorting">提出时间</th>
                            <th class="sorting">反馈类型</th>
                            <th class="sorting">反馈标题</th>
                            <th class="sorting">反馈内容</th>
                            <th class="sorting">联系电话</th>
                            <c:if test="${degree == 0}">
                            <th class="sorting">企业名称</th>
                            </c:if>
                            <th class="sorting">状态</th>
                            <%--<c:if test="${item.state == 0}">--%>
                            <th class="text-center">操作</th>
                            <%--</c:if>--%>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="item" varStatus="status">
                            <tr>
                                <td><input name="ids" value="${item.id}" type="checkbox"></td>
                                <td>${status.index+1}</td>
                                    <%--<td>${item.inputTime}</td>--%>

                                <td>${item.inputBy}</td>

                                <td><fmt:formatDate value="${item.inputTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

                                <td>
                                    <c:if test="${item.classType == 1}">管理</c:if>
                                    <c:if test="${item.classType == 2}">安全</c:if>
                                    <c:if test="${item.classType == 3}">建议</c:if>
                                    <c:if test="${item.classType == 4}">其他</c:if>
                                </td>
                                <td>${item.title }</td>
                                <td>${item.content}</td>
                                <td>${item.tel}</td>

                                <c:if test="${degree == 0}">
                                <td>${item.companyName}</td>
                                </c:if>


                                <td>
                                    <c:if test="${item.state == 0}"><font style="color: red">未处理</font></c:if>
                                    <c:if test="${item.state == 1}"><font style="color: green">已处理</font></c:if>
                                </td>


                                <td>
                                        <%--<a href="${ctx }/cargo/contract/toView.do?id=${o.id}">[查看详情]</a>--%>
                                    <c:if test="${item.state == 0}">
                                        <a href="${ctx }/system/feedBack/toUpdate.do?id=${item.id}">[编辑]</a>
                                    </c:if>
                                </td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="box-footer">
                <jsp:include page="../../common/page.jsp">
                    <jsp:param value="${ctx}/system/feedBack/list.do" name="pageUrl"/>
                </jsp:include>
            </div>
        </div>

    </section>
</div>
</body>

</html>