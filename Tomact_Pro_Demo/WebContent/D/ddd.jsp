<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>实时检测</title>

 <meta name="description" content="ECharts">

<title>CPU page</title>
<script type="text/javascript" src="jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="echarts-2.2.7/doc/asset/js/esl/esl.js"></script>
<script type="text/javascript" src="echarts.min.as.js"></script>
</head>

<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="height:450px ; width: 95%;"></div>

	<script type="text/javascript" >
		var myChart;
		var eCharts;
		var cpu_r = 100;
		var cpu_y= '85%';
		var cpu_title = '-25%'; //  常数  仪表盘的标题相比于圆心的偏移位置
		var cpu_detail =21;

		require([ 'echarts', 'echarts-2.2.7/build/dist/chart/line' //按需加载图表关于线性图、折线图的部分
		], DrawEChart //异步加载的回调函数绘制图表
		);

		//创建ECharts图表方法
		function DrawEChart(ec) {
			eCharts = ec;
			myChart = eCharts.init(document.getElementById('main'));
			myChart.showLoading({
				text : "图表数据正在努力加载...",
				effect :'whirling',//'spin' | 'bar' | 'ring' | 'whirling' | 'dynamicLine' | 'bubble'
				textStyle : {
					fontSize : 20
				}
			});
			
			
			//定义图表options
			var options = {
				backgroundColor: 'white',// 设置整个容器的背景颜色
				title : {
					text : "最近10次CPU采样结果",
					itemGap	:270,
					subtext : "当前CPU采样",
					subtextStyle :{
						fontSize: 15, fontWeight: 'bolder', color: '#333'
					},
					//sublink : "http://www.baidu.com"   副标题的超链接
				},
				
				tooltip : {
					trigger : 'axis'  //坐标轴出发
				},
				legend : {
					data : [ "进程使用率","服务率","用户使用率","I/O等待率","告警阀值"]//图例组件展现了不同系列的标记(symbol)，颜色和名字。可以通过点击图例控制哪些系列不显示。
				},
				toolbox : {
					show : true, // 设置 数据展现类型切换是否可见
					feature : {
						//mark : {show : true},
						dataView : {show : true,readOnly : false}, //是否展现数据视图一栏
						magicType : {
							show : true,type : [ 'line', 'bar' ]// 启用的动态类型
						},
						restore : {show : true},  //还原配置项
						saveAsImage : {
							show : true    //保存图片
						}
					}
				},
				
				calculable : true,
				xAxis : [ {
					type : 'category',
					boundaryGap : false,
					data : [ '第1次采样', '第2次采样', '第3次采样', '第4次采样', '第5次采样', '第6次采样', '第7次采样', '第8次采样', '第9次采样', '第10次采样' ]
				} ],
				
				yAxis : [ {
					type : 'value',// 坐标轴类型为  数值 轴
					axisLabel : {
						formatter : '{value} %'
					},
					splitArea : {
						show : true   //坐标轴在 grid 区域中的分隔区域，默认不显示。
					}
				} ],
				
				grid : {
						width : '90%' , //直角坐标轴占整页的百分比
						height : '43%'
						},	
							
				series : [	/*从这里开始对码表的设置，所有的码表都放在这个容器里*/
						{
							name : '空闲率',
							type : 'gauge',
							center : [ '15%', cpu_y ],//圆心坐标，支持绝对值（px）和百分比
							radius : cpu_r,  //仪表盘的半径
							splitNumber : 10, // 分割段数，默认为5
							axisLine : { // 坐标轴线
								lineStyle : { // 属性lineStyle控制线条样式
									color : [ [0, "#76cd58"],//绿
					                             [0.05, "#5bd199"],
					                             [0.1, "#53cf94"],
					                             [0.15, "#5ecf8f"],
					                             [0.2, "#69cf89"],
					                             [0.25, "#69CF89"],//绿
					                             [0.3, "#8DC4F2"],
					                             [0.35, "#8DB2F2"],
					                             [0.4, "#8D99F2"],
					                             [0.45, "#7786F4"],
					                             [0.5, "#607CDB"],
					                             [0.55, "#fdcb39"],//黄
					                             [0.6, "#f5bd31"],
					                             [0.65, "#f5ac2c"],
					                             [0.7, "#f09728"],
					                             [0.75, "#e88426"],
					                             [0.8, "#d26d24"],//红
					                             [0.85, "#d76423"],
					                             [0.9, "#F36210"],
					                             [0.95, "#F35210"],
					                             [1, "#FD3B00"]],
									width : 8
								}
							},
							axisTick : { // 坐标轴小标记
								splitNumber : 10, // 每份split细分多少段
								length : 12, // 属性length控制线长
								lineStyle : { // 属性lineStyle控制线条样式
									color : 'auto'
								}
							},
							axisLabel : { // 坐标轴文本标签，详见axis.axisLabel
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									color : 'auto'
								}
							},
							splitLine : { // 分隔线
								show : true, // 默认显示，属性show控制显示与否
								length : 30, // 属性length控制线长
								lineStyle : { // 属性lineStyle（详见lineStyle）控制线条样式
									color : 'auto'
								}
							},
							pointer : {
								width : 5 ,
								length:"70%"  // 指针的长度  默认是 80%
							},
							title : {
								show : true,
								offsetCenter : [ 0, cpu_title ], // x, y，单位px
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									fontWeight : 'bolder',
									fontSize : 10     // 仪表盘标题的样式
								}
							},
							detail : {
								formatter : '{value}%',
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									color : 'auto',
									fontWeight : 'bolder',
									fontSize : cpu_detail
								}
							},
							data : [ {
								value : 66,
								name : '进程使用率'
							} ]
						},
						{
							name : '服务率',
							type : 'gauge',
							center : [ '39%', cpu_y ],//圆心坐标，支持绝对值（px）和百分比
							radius : cpu_r,  //仪表盘的半径
							splitNumber : 10, // 分割段数，默认为5
							axisLine : { // 坐标轴线
								lineStyle : { // 属性lineStyle控制线条样式
									color : [ [ 0.2, '#228b22' ],
											[ 0.8, '#48b' ], [ 1, '#ff4500' ] ],
									width : 8
								}
							},
							axisTick : { // 坐标轴小标记
								splitNumber : 10, // 每份split细分多少段
								length : 12, // 属性length控制线长
								lineStyle : { // 属性lineStyle控制线条样式
									color : 'auto'
								}
							},
							axisLabel : { // 坐标轴文本标签，详见axis.axisLabel
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									color : 'auto'
								}
							},
							splitLine : { // 分隔线
								show : true, // 默认显示，属性show控制显示与否
								length : 30, // 属性length控制线长
								lineStyle : { // 属性lineStyle（详见lineStyle）控制线条样式
									color : 'auto'
								}
							},
							pointer : {
								width : 5
							},
							title : {
								show : true,
								offsetCenter : [ 0, cpu_title ], // x, y，单位px
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									fontWeight : 'bolder',
									fontSize : 10
								}
							},
							detail : {
								formatter : '{value}%',
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									color : 'auto',
									fontWeight : 'bolder',
									fontSize : cpu_detail
								}
							},
							data : [ {
								value : 20,
								name : '服务率'
							} ]
						},
						{
							name : '用户使用率',
							type : 'gauge',
							center : [ '62%', cpu_y ],//圆心坐标，支持绝对值（px）和百分比
							radius : cpu_r,  //仪表盘的半径
							splitNumber : 10, // 分割段数，默认为5
							axisLine : { // 坐标轴线
								lineStyle : { // 属性lineStyle控制线条样式
									color : [ [ 0.2, '#228b22' ],
											[ 0.8, '#48b' ], [ 1, '#ff4500' ] ],
									width : 8
								}
							},
							axisTick : { // 坐标轴小标记
								splitNumber : 10, // 每份split细分多少段
								length : 12, // 属性length控制线长
								lineStyle : { // 属性lineStyle控制线条样式
									color : 'auto'
								}
							},
							axisLabel : { // 坐标轴文本标签，详见axis.axisLabel
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									color : 'auto'
								}
							},
							splitLine : { // 分隔线
								show : true, // 默认显示，属性show控制显示与否
								length : 30, // 属性length控制线长
								lineStyle : { // 属性lineStyle（详见lineStyle）控制线条样式
									color : 'auto'
								}
							},
							pointer : {
								width : 5
							},
							title : {
								show : true,
								offsetCenter : [ 0, cpu_title ], // x, y，单位px
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									fontWeight : 'bolder',
									fontSize : 10
								}
							},
							detail : {
								formatter : '{value}%',
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									color : 'auto',
									fontWeight : 'bolder',
									fontSize : cpu_detail
								}
							},
							data : [ {
								value : 40,
								name : '用户使用率	'
							} ]
						},
						{
							name : 'IO等待率',
							type : 'gauge',
							center : [ '85%', cpu_y ],//圆心坐标，支持绝对值（px）和百分比
							radius : cpu_r,  //仪表盘的半径
							splitNumber : 10, // 分割段数，默认为5
							axisLine : { // 坐标轴线
								lineStyle : { // 属性lineStyle控制线条样式
									color : [ [ 0.2, '#228b22' ],
											[ 0.8, '#48b' ], [ 1, '#ff4500' ] ],
									width : 8
								}
							},
							axisTick : { // 坐标轴小标记
								splitNumber : 10, // 每份split细分多少段
								length : 12, // 属性length控制线长
								lineStyle : { // 属性lineStyle控制线条样式
									color : 'auto'
								}
							},
							axisLabel : { // 坐标轴文本标签，详见axis.axisLabel
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									color : 'auto',
									//fontSize : 12
								}
							},
							splitLine : { // 分隔线
								show : true, // 默认显示，属性show控制显示与否
								length : 30, // 属性length控制线长
								lineStyle : { // 属性lineStyle（详见lineStyle）控制线条样式
									color : 'auto'
								}
							},
							pointer : {
								width : 5
							},
							title : {
								show : true,
								offsetCenter : [ 0, cpu_title ], // x, y，单位px
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									fontWeight : 'bolder',
									fontSize : 10
								}
							},
							detail : {
								formatter : '{value}%',
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									color : 'auto',
									fontWeight : 'bolder',
									fontSize : cpu_detail
								}
							},
							data : [ {
								value : 70,
								name : 'I/O等待率'
							} ]
						},
						
	//  坐标系的设计
	//============================================================================================================			
						
						{
							name : '进程使用率',
							type : 'line',
							data : [ 21, 22, 23, 27, 25, 28, 31 ,25, 25, 25],//必须是Integer类型的,String计算平均值会出错
							markPoint : {
								data : [ {
									type : 'max',
									name : '最大值'
								}, {
									type : 'min',
									name : '最小值'
								} ]
							},
							//markLine : {
								//data : [ {
								//	type : 'average',
								//	name : '平均值'
								//} ]
							//}
						} ,
						{
							name : '服务率',
							type : 'line',
							data : [ 13, 12, 13, 11, 14, 18, 21 , 14, 14, 14  ],//必须是Integer类型的,String计算平均值会出错
							markPoint : {
								data : [ {
									type : 'max',
									name : '最大值'
								}, {
									type : 'min',
									name : '最小值'
								} ]
							}
						} ,		
						{
							name : '用户使用率',
							type : 'line',
							data : [ 32, 33, 32, 31, 32, 38, 21 , 32, 32, 32 ],//必须是Integer类型的,String计算平均值会出错
							markPoint : {
								data : [ {
									type : 'max',
									name : '最大值'
								}, {
									type : 'min',
									name : '最小值'
								} ]
							}
						} ,
						{
							name : 'I/O等待率',
							type : 'line',
							data : [ 22, 21, 23, 24, 25, 22, 11, 22, 22, 22 ],//必须是Integer类型的,String计算平均值会出错
							markPoint : {
								data : [ {
									type : 'max',
									name : '最大值'
								}, {
									type : 'min',
									name : '最小值'
								} ]
							}
						} ,
						{
							name : '告警阀值',
							type : 'line',
							itemStyle: {
								normal: {
									color: 'red',
									lineStyle: {width: 5}
								}
						},
							data : [ 70, 70, 70, 70,70, 70, 70, 70,70, 70 ]//必须是Integer类型的,String计算平均值会出错
							
						} 
				]
			};
			
			
			//clearTimeout(loadingTicket);
			//loadingTicket = setTimeout(function (){
				myChart.setOption(options);
				myChart.hideLoading();
			//},100);
			
			

			//myChart.setOption(options); //先把可选项注入myChart中
			myChart.hideLoading();
			//getChartData();
		}
	</script>


	<script type="text/javascript">
		function getChartData() {
			//获得图表的options对象
			var options = myChart.getOption();
			//通过Ajax获取数据
			$.ajax({
				type : "post",
				async : false, //同步执行
				url : "${pageContext.request.contextPath}/echarts/line_data",
				data : {},
				dataType : "json", //返回数据形式为json
				success : function(result) {
					if (result) {
						options.legend.data = result.legend;
						options.xAxis[0].data = result.category;
						options.series[0].data = result.series[0].data;

						myChart.hideLoading();
						myChart.setOption(options);
					}
				},
				error : function(errorMsg) {
					alert("不好意思，大爷，图表请求数据失败啦!");
					myChart.hideLoading();
				}
			});
		}
	</script>
</body>
</html>
