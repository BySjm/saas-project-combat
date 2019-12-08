<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
    <script type="text/javascript" src="//api.map.baidu.com/api?v=2.0&ak=Hd3Snf71apiRLcFWZzr5e5shNwCHZU1l"></script>
    <title>货物运输路线</title>
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            货运管理
            <small>路线查看</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">路线查看</a></li>
            <li class="active">路线查看</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <section class="content">
        <div id="allmap" style="height: 550px"></div>
    </section>
</div>
<!-- 内容区域 /-->
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能

    var map = new BMap.Map("allmap");
    map.disableBizAuthLogo(); //关闭
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

    // 创建地址解析器实例
    var myGeo = new BMap.Geocoder();
    // 将地址解析结果显示在地图上,并调整地图视野
    var atgStart;
    var portOfLoading = '${shipping.portOfLoading}';
    myGeo.getPoint(portOfLoading,function(point){
        if (point) {
            map.centerAndZoom(point, 7);
            map.addOverlay(new BMap.Marker(point));
            atgStart=point;
        }else{
            alert("您选择地址没有解析到结果!");
        }
    }, portOfLoading.substring(0, 2));

    var atgLast;
    var portOfDischarge = '${shipping.portOfDischarge}';
    myGeo.getPoint(portOfDischarge, function(point){
        if (point) {
            // map.centerAndZoom(point, 16);
            map.addOverlay(new BMap.Marker(point));
            atgLast=point;
        }else{
            alert("您选择地址没有解析到结果!");
        }

    }, portOfDischarge.substring(0, 2));

    var atgMiddle;
    var portOfTrans = '${shipping.portOfTrans}';
    myGeo.getPoint(portOfTrans, function(point){
        if (point) {
            // map.centerAndZoom(point, 16);
            map.addOverlay(new BMap.Marker(point));
            atgMiddle=point;
        }else{
            alert("您选择地址没有解析到结果!");
        }

    }, portOfTrans.substring(0, 2));


    var myP1 ;    //起点
    var myP2 ;    //终点
    var myP3 ;    //途径点
    var myIcon;

    window.a = function () {

        myP1 = new BMap.Point(atgStart.lng, atgStart.lat);    //起点
        myP2 = new BMap.Point(atgLast.lng, atgLast.lat);    //终点
        myP3 = new BMap.Point(atgMiddle.lng, atgMiddle.lat);    //途径点

        myIcon = new BMap.Icon("/img/lunc.png", new BMap.Size(46, 70), {    //小车图片
            //offset: new BMap.Size(0, -5),    //相当于CSS精灵
            imageOffset: new BMap.Size(0, 0)    //图片的偏移量。为了是图片底部中心对准坐标点。
        });

        var driving2 = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});    //驾车实例
        driving2.search(myP1, myP2, {waypoints: [myP3]});    //显示一条公交线路
    }

    window.run = function (){

        var  pts1=[];
        var dri1 = new BMap.DrivingRoute(map);
        dri1.search(myP1,myP3);
        dri1.setSearchCompleteCallback(function(){
            pts1 = dri1.getResults().getPlan(0).getRoute(0).getPath();
        });

        var pts2=[];
        var dri2 = new BMap.DrivingRoute(map);
        dri2.search(myP3,myP2);
        dri2.setSearchCompleteCallback(function(){
            pts2 = dri2.getResults().getPlan(0).getRoute(0).getPath();

            var pts=pts1.concat(pts2);
            var paths = pts.length;    //获得有几个点
            var carMk = new BMap.Marker(pts[0],{icon:myIcon});
            map.addOverlay(carMk);
            i=0;
            function resetMkPoint(i){
                carMk.setPosition(pts[i]);
                if(i < paths){
                    setTimeout(function(){
                        i++;
                        resetMkPoint(i);
                    },2);
                }
            }
            setTimeout(function(){
                resetMkPoint(5);
            },2)

        });
    }

    setTimeout(function(){
        a();
    }, 350);

    setTimeout(function(){
        run();
    }, 400);
</script>








<%--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <!-- 页面meta -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=Hd3Snf71apiRLcFWZzr5e5shNwCHZU1l"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/LuShu/1.2/src/LuShu_min.js"></script>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
                货运管理
                <small>路线查看</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="all-order-manage-list.html">路线查看</a></li>
                <li class="active">路线查看</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <section class="content">
            <div id="contains" style="width: 800px; height: 600px"></div>
        </section>
    </div>
    <!-- 内容区域 /-->

<script type="text/javascript">
    // 百度地图API功能

    var map = new BMap.Map("contains");
    map.disableBizAuthLogo(); //关闭
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

    // 创建地址解析器实例
    var myGeo = new BMap.Geocoder();
    // 将地址解析结果显示在地图上,并调整地图视野
    var atgStart;
    var portOfLoading = ${shipping.portOfLoading};
    myGeo.getPoint(portOfLoading, function(point){
        if (point) {
            map.centerAndZoom(point, 7);
            map.addOverlay(new BMap.Marker(point));
            console.log(point + "point");
            atgStart=point;
            console.log(atgStart + "*************");
        }else{
            alert("您选择地址没有解析到结果!");
        }

    }, portOfLoading.substring(0, 2));

    var atgLast;
    var portOfTrans = ${shipping.portOfTrans};
    myGeo.getPoint(portOfTrans, function(point){
        if (point) {
            // map.centerAndZoom(point, 16);
            map.addOverlay(new BMap.Marker(point));
            atgLast=point;
        }else{
            alert("您选择地址没有解析到结果!");
        }

    }, portOfTrans.substring(0, 2));

    var atgMiddle;
    var portOfTrans = ${shipping.portOfTrans};
    myGeo.getPoint(portOfTrans, function(point){
        if (point) {
            // map.centerAndZoom(point, 16);
            map.addOverlay(new BMap.Marker(point));
            atgMiddle=point;
        }else{
            alert("您选择地址没有解析到结果!");
        }

    }, portOfTrans.substring(0, 2));

    // 获取经纬度
    var myP1 ;    //起点
    var myP2 ;    //终点
    var myP3 ;    //途径点
    var myIcon;

    window.a = function () {
        console.log(atgStart + " 赋值后");

        myP1 = new BMap.Point(atgStart.lng, atgStart.lat);    //起点
        myP2 = new BMap.Point(atgLast.lng, atgLast.lat);    //终点
        myP3 = new BMap.Point(atgMiddle.lng, atgMiddle.lat);    //途径点

        myIcon = new BMap.Icon("https://developer.baidu.com/map/jsdemo/img/Mario.png", new BMap.Size(32, 70), {    //小车图片
            //offset: new BMap.Size(0, -5),    //相当于CSS精灵
            imageOffset: new BMap.Size(0, 0)    //图片的偏移量。为了是图片底部中心对准坐标点。
        });

        var driving2 = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});    //驾车实例
        driving2.search(myP1, myP2, {waypoints: [myP3]});    //显示一条公交线路
    }

    window.run = function (){

        var  pts1=[];
        var dri1 = new BMap.DrivingRoute(map);
        dri1.search(myP1,myP3);
        dri1.setSearchCompleteCallback(function(){
            pts1 = dri1.getResults().getPlan(0).getRoute(0).getPath();
        });

        var pts2=[];
        var dri2 = new BMap.DrivingRoute(map);
        dri2.search(myP3,myP2);
        dri2.setSearchCompleteCallback(function(){
            pts2 = dri2.getResults().getPlan(0).getRoute(0).getPath();

            var pts=pts1.concat(pts2);
            var paths = pts.length;    //获得有几个点
            var carMk = new BMap.Marker(pts[0],{icon:myIcon});
            map.addOverlay(carMk);
            i=0;
            function resetMkPoint(i){
                carMk.setPosition(pts[i]);
                if(i < paths){
                    setTimeout(function(){
                        i++;
                        resetMkPoint(i);
                    },2);
                }
            }
            setTimeout(function(){
                resetMkPoint(5);
            },2)

        });
    }

    setTimeout(function(){
        a();
    }, 350);

    setTimeout(function(){
        run();
    }, 400);
</script>
</body>
</html>



--%>
