<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            统计分析
            <small>货物市场价格排名统计</small>
        </h1>
    </section>
    <section class="content">
        <div class="box box-primary">
            <div id="main" style="width: 600px;height:500px;"></div>
        </div>
    </section>
</div>
</body>
<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="../../plugins/echarts/echarts.min.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    $.get('/stat/priceCharts.do').done(function (data) {
        //数组
        let names = [];
        let values= [];
        for (let i = 0; i < data.length; i++) {
            names[i] = data[i].name;
            values[i] = data[i].value;
        }
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption({
            title : {
                text: '货物市场价格排名统计',
                subtext: '',
                x:'center'
            },
            color: ['#3398DB'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : names,
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLabel: {
                        rotate:80
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value',

                }
            ],
            series : [
                {
                    name:'价格',
                    type:'bar',
                    barWidth: '60%',
                    data:values
                }
            ]
        })
    });

</script>
</html>