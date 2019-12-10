<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base.jsp"%>

<body>

<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <%--<section class="content-header">--%>
    <%--<h1>--%>
    <%--模块名称--%>
    <%--<small>模块功能</small>--%>
    <%--</h1>--%>
    <%--<ol class="breadcrumb">--%>
    <%--<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>--%>
    <%--<li><a href="#">模块名称</a></li>--%>
    <%--<li class="active">模块功能</li>--%>
    <%--</ol>--%>
    <%--</section>--%>
    <!-- 内容js /-->

    <!-- 正文区域 -->
    <section class="content">

        <link href="css/StyleSheet.css" rel="stylesheet"/>
        <link href="css/reset.css" rel="stylesheet"/>
        <link href="css/owl.carousel.css" rel="stylesheet"/>
        <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="js/owl.carousel.js"></script>
        <script type="text/javascript" src="js/Lunbo.js"></script>

        <script type="text/javascript">
            $(function () {
                $('#owl-demo').owlCarousel({
                    items: 1,
                    navigation: true,
                    navigationText: ["上一个", "下一个"],
                    autoPlay: true,
                    stopOnHover: true
                }).hover(function () {
                    $('.owl-buttons').show();
                }, function () {
                    $('.owl-buttons').hide();
                });
            });
        </script>

        <div class="banner" id="a01">
            <!-- Demo -->
            <div id="owl-demo" class="owl-carousel">
                <%--<a class="item" href="#" target="_blank"><img src="images/timg3.jpg" alt=""></a>--%>
                <%--<a class="item" href="#" target="_blank"><img src="images/timg.jpg" alt=""></a>--%>
                <%--                <a class="item" href="#" target="_blank"><img src="img/dg.jpg" alt=""></a>--%>
                <a class="item" href="#" <%--target="_blank"--%>><img src="img/dg1.png" alt=""></a>
            </div>
            <!-- Demo end -->

            <div class="head">
                <img src="img/javaSLT.png" class="logo"/>

            </div>

        </div>
        <div class="ct">
            <div class="content">
                <div class="ct1">
                    <p class="wm" style="color:rgb(0,57,112)">第七组SAAS平台展示</p>
                    <div class="pin"></div>
                    <p class="kc">SaaS已成为软件产业的一个重要力量。只要SaaS的品质和可信度能继续得到证实，它的魅力就不会消退。</p>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
