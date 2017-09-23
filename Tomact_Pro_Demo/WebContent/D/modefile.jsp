<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="echarts.min.as.js"></script>
<script src="jquery-1.11.3.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
/* $(function(){
	
	
}); */

function as() {
	$.ajax({
		url:"../Demo_Servelet",
		success:function(da){
			var a=da.split(",");
			//console.log(a);
			 var myChart = echarts.init(document.getElementById('main'));

			 var rate = 30;
			 var data = {
			     max: 100,
			     rate: 20
			 }
			 var labelArr = [0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100];
			 var endFix = '';
			 option = {
			             tooltip: {
			                 formatter: parseFloat(rate)
			                // formatter: "{a} <br/>{b} : {c}%"
			             },
			             series: [{
			                 splitNumber: parseInt(data.max),
			                 max: parseInt(data.max),
			                 min: 0,
			                 type: 'gauge',
			                 startAngle: 180,
			                 endAngle: 0,
			                 name: '风险指标',
			                 detail: {
			                     /* formatter: function () {
			                         return "应用层风险指数";
			                     },
			                     
			                     offsetCenter: [0, '-40%'],
			                     textStyle: {
			                         color: '#7E6565',
			                         fontSize: 16,
			                         fontFamily: 'opensans,Roboto,Helvetica,Arial'
			                     } */
			                	 formatter:'{value}%',
				                 offsetCenter: [0, '-40%'],
				                 textStyle: {
				                         color: '#7E6565',
				                         fontSize: 16,
				                         fontFamily: 'opensans,Roboto,Helvetica,Arial'
				                     }
			                 },
			                 title: {
			                     show: true,
			                     offsetCenter: ['0%', '22%'],
			                     textStyle: {
			                         color: '#000',
			                         fontSize: 13
			                     }
			                 },
			                 data: [{
			                    /*  value: parseFloat(rate),
			                     name: data.name */
			                     value: 100, 
			                     name: '应用层风险指标',
			                     
			                 }],
			                 itemStyle: { //仪表盘指针样式
			                     normal: {
			                         color: '#c2cbd6',
			                         shadowColor: 'rgba(0, 0, 0, 0.5)'
			                     }
			                 },
			                 pointer: { //仪表盘指针
			                     show: true,
			                     length: '60%',
			                     width: 10
			                 },
			                 axisTick: { // 刻度样式
			                     show: false
			                 },
			                 axisLabel: {
			                     show: true,
			                     distance: 1,
			                     formatter: function (value) {
			                         for(var i = 0; i < labelArr.length; i++) {
			                             if(value === Math.floor(labelArr[i])) {
			                                 return labelArr[i] === 0 ? 0 : labelArr[i] + endFix;
			                             }
			                         }
			                     }
			                 },
			                 splitLine: { //分割线样式
			                     show: false,
			                     length: 20,
			                 },
			                 axisLine: {
			                     lineStyle: {
			                         width: 20, //柱子的宽度
			                         "color": [
			                             [0, "#76cd58"],
			                             [0.05, "#5bd199"],
			                             [0.1, "#53cf94"],
			                             [0.15, "#5ecf8f"],
			                             [0.2, "#69cf89"],
			                             [0.25, "#7ccf7f"],
			                             [0.3, "#90cf74"],
			                             [0.35, "#afcf64"],
			                             [0.4, "#cbcf54"],
			                             [0.45, "#e5cf45"],
			                             [0.5, "#fdcb39"],
			                             [0.55, "#f5bd31"],
			                             [0.6, "#f5ac2c"],
			                             [0.65, "#f09728"],
			                             [0.7, "#e88426"],
			                             [0.75, "#e27825"],
			                             [0.8, "#d26d24"],
			                             [0.85, "#d76423"],
			                             [0.9, "#d35b23"],
			                             [0.95, "#d25822"],
			                             [1, "#cf5322"]
			                         ]
			                     }
			                 },
			             }]
			         };
			 setInterval(function () {
				    option.series[0].data[0].value = (Math.random() * 100).toFixed(2) - 0;
				    myChart.setOption(option, true);
				},2000);

		}
	});
}
var t1 = window.setInterval(as,1000); 

</script>
</head>
<body>
  <div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>