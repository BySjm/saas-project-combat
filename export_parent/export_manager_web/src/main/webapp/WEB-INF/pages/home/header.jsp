<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%--传给后台的数据通过json封装起来，再用ajax将json传到后台，ajax是用到Jquery的ajax，在jsp先加入Jquery的引用--%>
<script type="text/javascript" src="plugins/jQuery/jquery-2.2.3.min.js"></script>
<header class="main-header">
    <a href="all-admin-index.html" class="logo">
        <span class="logo-mini"><img src="../img/logo.png"></span>
        <span class="logo-lg">
                    <img src="../img/export.png">
                    <i> SaaS外贸进出口平台</i>
                </span>
    </a>

    <nav class="navbar navbar-static-top">
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <li class="dropdown messages-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" onclick="doAjax()">--%>
                        <i class="fa fa-envelope-o"></i>
                        <span id="feedBackNumId" class="label label-success">0</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">你有<span id="feedBackNumId1">0</span>个未处理的反馈</li>
                        <li>
                            <ul class="menu">
                                <li>
                                    <a href="${pageContext.request.contextPath}/system/feedBack/solve.do"
                                       target="iframe">
                                        <div class="pull-left">
                                            <img src="../img/user2-160x160.jpg" class="img-circle" alt="User Image">
                                        </div>
                                        <h4>
                                            点击这里去处理反馈
                                            <%--<small><i class="fa fa-clock-o"></i> 5 分钟前</small>--%>
                                        </h4>
                                        <%--  <p>欢迎登录系统?</p>--%>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="footer"><a href="#">See All Messages</a></li>
                    </ul>
                </li>
                <%--<script type="text/javascript">--%>
                <%--function doAjax() {--%>
                <%--var messages = [{type:0,title: '系统消息', time: '5 分钟前', content: '欢迎登录系统?'},--%>
                <%--{type:0,title: '团队消息', time: '2 小时前',  content: '你有新的任务了'},--%>
                <%--{type:1,title: 'Developers', time: 'Today',  content: 'Why not buy a new awesome theme?'},--%>
                <%--{type:1,title: 'Sales Department', time: 'Yesterday',  content:'Why not buy a new awesome theme?'},--%>
                <%--{type:1,title: 'Reviewers', time: '2 days',  content:'Why not buy a new awesome theme?'}];--%>

                <%--$.ajax({--%>

                <%--type: 'POST',--%>

                <%--data: JSON.stringify(messages),--%>

                <%--contentType: 'application/json',--%>

                <%--// dataType: 'json',--%>

                <%--url: 'baseinfo/infoback/list.do',--%>

                <%--success: function (data) {--%>
                <%--alert(data)--%>
                <%--alert(1);--%>
                <%--var arr=data.list;--%>
                <%--for(var i=0;i<data.list.length;i++){--%>
                <%--var title=arr[i].title;--%>
                <%--var time=arr[i].time;--%>
                <%--var content=arr[i].content;--%>
                <%--alert("第"+(i+1)+"条消息:"+title+"  "+time+"  "+content);--%>
                <%--}--%>


                <%--// alert(data)--%>
                <%--// alert("OK");  当dataType知名为“json”而返回的又不是json时，肯定会出错，所以若返回的类型不是json，这一个属性可以不填。--%>

                <%--// for(var i = 0; i < messages.length; i++){--%>
                <%--//     alert("第"+(i+1)+"条消息:"+messages[i].title+"  "+messages[i].time+"  "+messages[i].content);--%>
                <%--// }--%>
                <%--if(data.status = 1){--%>

                <%--// window.location.href="baseinfo/infoback/list.do";--%>
                <%--}--%>

                <%--},--%>

                <%--error: function (e) {--%>
                <%--alert("error");--%>
                <%--}--%>

                <%--});--%>

                <%--}--%>
                <%--</script>--%>

                <%--<ul class="dropdown-menu">--%>
                <%--<li class="header">你有4个邮件</li>--%>
                <%--<li>--%>
                <%--<ul class="menu">--%>
                <%--<li>--%>
                <%--<a href="#">--%>
                <%--<div class="pull-left">--%>
                <%--<img src="../img/user2-160x160.jpg" class="img-circle" alt="User Image">--%>
                <%--</div>--%>
                <%--<h4>--%>
                <%--系统消息--%>
                <%--<small><i class="fa fa-clock-o"></i> 5 分钟前</small>--%>
                <%--</h4>--%>
                <%--<p>欢迎登录系统?</p>--%>
                <%--</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a href="#">--%>
                <%--<div class="pull-left">--%>
                <%--<img src="../img/user3-128x128.jpg" class="img-circle" alt="User Image">--%>
                <%--</div>--%>
                <%--<h4>--%>
                <%--团队消息--%>
                <%--<small><i class="fa fa-clock-o"></i> 2 小时前</small>--%>
                <%--</h4>--%>
                <%--<p>你有新的任务了</p>--%>
                <%--</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a href="#">--%>
                <%--<div class="pull-left">--%>
                <%--<img src="../img/user4-128x128.jpg" class="img-circle" alt="User Image">--%>
                <%--</div>--%>
                <%--<h4>--%>
                <%--Developers--%>
                <%--<small><i class="fa fa-clock-o"></i> Today</small>--%>
                <%--</h4>--%>
                <%--<p>Why not buy a new awesome theme?</p>--%>
                <%--</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a href="#">--%>
                <%--<div class="pull-left">--%>
                <%--<img src="../img/user3-128x128.jpg" class="img-circle" alt="User Image">--%>
                <%--</div>--%>
                <%--<h4>--%>
                <%--Sales Department--%>
                <%--<small><i class="fa fa-clock-o"></i> Yesterday</small>--%>
                <%--</h4>--%>
                <%--<p>Why not buy a new awesome theme?</p>--%>
                <%--</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a href="#">--%>
                <%--<div class="pull-left">--%>
                <%--<img src="../img/user4-128x128.jpg" class="img-circle" alt="User Image">--%>
                <%--</div>--%>
                <%--<h4>--%>
                <%--Reviewers--%>
                <%--<small><i class="fa fa-clock-o"></i> 2 days</small>--%>
                <%--</h4>--%>
                <%--<p>Why not buy a new awesome theme?</p>--%>
                <%--</a>--%>
                <%--</li>--%>
                <%--</ul>--%>
                <%--</li>--%>
                <%--<li class="footer"><a href="#">See All Messages</a></li>--%>
                <%--</ul>--%>

                <!-- Notifications: style can be found in dropdown.less -->
                <li class="dropdown notifications-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bell-o"></i>
                        <span class="label label-warning">10</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">你有10个新消息</li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul class="menu">
                                <li>
                                    <a href="#">
                                        <i class="fa fa-users text-aqua"></i> 5 new members joined today
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fa fa-warning text-yellow"></i> Very long description here that may
                                        not
                                        fit into the page and may cause design problems
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fa fa-users text-red"></i> 5 new members joined
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fa fa-user text-red"></i> You changed your username
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="footer"><a href="#">View all</a></li>
                    </ul>
                </li>
                <!-- Tasks: style can be found in dropdown.less -->
                <li class="dropdown tasks-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-flag-o"></i>
                        <span class="label label-danger">9</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">你有9个新任务</li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul class="menu">
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Design some buttons
                                            <small class="pull-right">20%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-aqua" style="width: 20%"
                                                 role="progressbar" aria-valuenow="20" aria-valuemin="0"
                                                 aria-valuemax="100">
                                                <span class="sr-only">20% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Create a nice theme
                                            <small class="pull-right">40%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-green" style="width: 40%"
                                                 role="progressbar" aria-valuenow="20" aria-valuemin="0"
                                                 aria-valuemax="100">
                                                <span class="sr-only">40% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Some task I need to do
                                            <small class="pull-right">60%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-red" style="width: 60%"
                                                 role="progressbar" aria-valuenow="20" aria-valuemin="0"
                                                 aria-valuemax="100">
                                                <span class="sr-only">60% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                                <li>
                                    <!-- Task item -->
                                    <a href="#">
                                        <h3>
                                            Make beautiful transitions
                                            <small class="pull-right">80%</small>
                                        </h3>
                                        <div class="progress xs">
                                            <div class="progress-bar progress-bar-yellow" style="width: 80%"
                                                 role="progressbar" aria-valuenow="20" aria-valuemin="0"
                                                 aria-valuemax="100">
                                                <span class="sr-only">80% Complete</span>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <!-- end task item -->
                            </ul>
                        </li>
                        <li class="footer">
                            <a href="#">View all tasks</a>
                        </li>
                    </ul>
                </li>
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="../img/user2-160x160.jpg" class="user-image" alt="User Image">
                        <span class="hidden-xs"> ${sessionScope.loginUser.userName}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="../img/user2-160x160.jpg" class="img-circle" alt="User Image">

                            <p>
                                ${sessionScope.loginUser.userName}
                            </p>
                        </li>
                        <!-- Menu Body
                <li class="user-body">
                    <div class="row">
                        <div class="col-xs-4 text-center">
                            <a href="#">Followers</a>
                        </div>
                        <div class="col-xs-4 text-center">
                            <a href="#">Sales</a>
                        </div>
                        <div class="col-xs-4 text-center">
                            <a href="#">Friends</a>
                        </div>
                    </div>
                </li>-->
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="#" class="btn btn-default btn-flat">修改密码</a>
                            </div>
                            <div class="pull-right">
                                <a href="/logout.do" class="btn btn-default btn-flat">注销</a>
                            </div>
                        </li>
                    </ul>
                </li>

            </ul>
        </div>
    </nav>
</header>
<!-- 页面头部 /-->
<script>
    $(function () {
        //    ajax请求
        let url = "${pageContext.request.contextPath}/system/feedBack/findFeedBackNum.do";
        $.get(url, function (resp) {
            //处理响应
            $("#feedBackNumId").text(resp);
            $("#feedBackNumId1").text(resp);
        })

    })
</script>
