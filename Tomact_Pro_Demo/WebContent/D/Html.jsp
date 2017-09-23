<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>实时检测系统</title>

 <meta name="description" content="ECharts">

<title>CPU page</title>
<script type="text/javascript" src="jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="echarts-2.2.7/doc/asset/js/esl/esl.js"></script>
<script type="text/javascript" src="echarts.min.as.js"></script>
</head>
<body style="margin: 0 outo; ">
	<div style="float: left;width: 99%; ">
	 
	 
	 
	 <div style="float:left;width:14%">
	 		<!-- <h1 style="text-align: center;line-height:300px ">占位置11！</h1> -->	
	 		<h4 style="margin-top: 50px">检测结果 ：</h4>
	 		<marquee style="text-align:center;width:100%;height:50%;color: red"id="SuddenVisit" scrollamount="2" direction="up" ></marquee>
	 </div>
	 
		<!-- <h1 style="color:green;">先放起不动，只是占位置而已</h1>  -->
		<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	 <div id="main" style="float:left;height:600px ; width: 85%;"></div> 

  <script type="text/javascript" >
		var myChart;
		var eCharts;
		var cpu_r = 100;
		var cpu_y= '85%';
		var cpu_title = '-25%'; //  常数  仪表盘的标题相比于圆心的偏移位置
		var cpu_detail =21;
		var nums = [];
		var TCP_blocks = [];
		var NetAttack = []; 
		
		
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
					text : "最近10次服务率采样结果",
					itemGap	:270,
					textStyle:{
						textAlign:'center'
					},
					subtext : "当前数据采样",
					subtextStyle :{
						fontSize: 15, fontWeight: 'bolder', color: '#333'
					},
					//sublink : "http://www.baidu.com"   副标题的超链接
				},
				
				tooltip : {
					trigger : 'axis'  //坐标轴出发
				},
				
				legend : {
					data : [ "服务率"]//图例组件展现了不同系列的标记(symbol)，颜色和名字。可以通过点击图例控制哪些系列不显示。
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
							name : 'CPU使用率',
							type : 'gauge',
							center : [ '19%', cpu_y ],//圆心坐标，支持绝对值（px）和百分比
							radius : cpu_r,  //仪表盘的半径
							splitNumber : 10, // 分割段数，默认为5
							axisLine : { // 坐标轴线
								lineStyle : { // 属性lineStyle控制线条样式
									color : [ [ 0.2, '#228b22' ],
												[ 0.8, '#48b' ], [ 1, '#ff4500' ]],
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
								formatter : 1,
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									color : 'auto',
									fontWeight : 'bolder',
									fontSize : cpu_detail
								}
							},
							data : [ {
								value : 34.47,
								name : 'CPU使用率'
							} ]
						},
						 {
							name : '应用层攻击威胁程度',
							type : 'gauge',
							center : [ '50%', cpu_y ],//圆心坐标，支持绝对值（px）和百分比
							/* min : 0,
							max : 0, */
							radius : cpu_r,  //仪表盘的半径
							splitNumber : 10, // 分割段数，默认为5
							axisLine : { // 坐标轴线
								lineStyle : { // 属性lineStyle控制线条样式
									color : [  [0, "#76cd58"],//绿
					                             [0.05, "#5bd199"],
					                             [0.1, "#53cf94"],
					                             [0.15, "#5ecf8f"],
					                             [0.2, "#69cf89"],
					                             [0.25, "607CDB"],//蓝
					                             [0.3, "#607CDB"],
					                             [0.35, "#607CDB"],
					                             [0.4, "#607CDB"],
					                             [0.45, "#607CDB"],
					                             [0.5, "#607CDB"],
					                             [0.55, "#fdcb39"],//黄
					                             [0.6, "#fdcb39"],
					                             [0.65, "#fdcb39"],
					                             [0.7, "#fdcb39"],
					                             [0.75, "#fdcb39"],
					                             [0.8, "#FD3B00"],//红
					                             [0.85, "#FD3B00"],
					                             [0.9, "#FD3B00"],
					                             [0.95, "#FD3B00"],
					                             [1, "#FD3B00"] ],
									width : 8
								}
							},
							axisTick : { // 坐标轴小标记
								splitNumber : 10, // 每份split细分多少段
								length : 12, // 属性length控制线长
								lineStyle : { // 属性lineStyle控制线条样式
									color : 'auto'
								},
								show:true
							},
							axisLabel : { // 坐标轴文本标签，详见axis.axisLabel
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									color : 'auto'
									
								}
							},
							splitLine : { // 分隔线
								show : false, // 默认显示，属性show控制显示与否
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
								offsetCenter : [ -120, -90 ], // x, y，单位px
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									fontWeight : 'bolder',
									fontSize : 15,
									color : '#FD0000'
								}
							},
							detail : {
								formatter : "",
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									color : 'auto',
									fontWeight : 'bolder',
									fontSize : cpu_detail,
								
									offsetCenter:[0,30]
								}
							},
							data : [ {
								value : 0,
								name : '应用层攻击威胁程度',
								
							} ]
						}, 
						 {
							name : '网络层攻击威胁程度',
							type : 'gauge',
							center : [ '81%', cpu_y ],//圆心坐标，支持绝对值（px）和百分比
							radius : cpu_r,  //仪表盘的半径
							splitNumber : 10, // 分割段数，默认为5
							axisLine : { // 坐标轴线
								lineStyle : { // 属性lineStyle控制线条样式
									color : [  [0, "#76cd58"],//绿
					                             [0.05, "#5bd199"],
					                             [0.1, "#53cf94"],
					                             [0.15, "#5ecf8f"],
					                             [0.2, "#69cf89"],
					                             [0.25, "607CDB"],//蓝
					                             [0.3, "#607CDB"],
					                             [0.35, "#607CDB"],
					                             [0.4, "#607CDB"],
					                             [0.45, "#607CDB"],
					                             [0.5, "#607CDB"],
					                             [0.55, "#fdcb39"],//黄
					                             [0.6, "#fdcb39"],
					                             [0.65, "#fdcb39"],
					                             [0.7, "#fdcb39"],
					                             [0.75, "#fdcb39"],
					                             [0.8, "#FD3B00"],//红
					                             [0.85, "#FD3B00"],
					                             [0.9, "#FD3B00"],
					                             [0.95, "#FD3B00"],
					                             [1, "#FD3B00"] ],
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
								show : false, // 默认显示，属性show控制显示与否
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
								offsetCenter : [ -120, -90 ], // x, y，单位px
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									fontWeight : 'bolder',
									fontSize : 15,
									color:'#FD0000'
								}
							},
							detail : {
								formatter : '',
								textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
									color : 'auto',
									fontWeight : 'bolder',
									fontSize : cpu_detail
								}
							},
							data : [ {
								value : 0,
								name : '网络层攻击威胁程度'
							} ]
						},
						
	//  坐标系的设计
	//============================================================================================================			
						{
							name : '服务率',
							type : 'line',
							data : nums,//必须是Integer类型的,String计算平均值会出错
							markPoint : {
								data : [ {
									type : 'max',
									name : '最大值'
								}, {
									type : 'min',
									name : '最小值'
								} ]
							}
						}					 	
				]
			} ;
			
			
			//clearTimeout(loadingTicket);
			//loadingTicket = setTimeout(function (){
				myChart.setOption(options);
				myChart.hideLoading();
			//},100);
			
		
			
			
				setInterval(function (){
					
			    	$.ajax({
				         type : "post",
				         async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
				         url : "../Cpu_Servelet",    //请求发送到Servlet处
				         data : {},
				         dataType : "json",        //返回数据形式为json
				         success : function(result) {
				             //请求成功时执行该函数内容，result即为服务器返回的json对象
				             console.log(result[0].Cpu);
				             if (result) {
				                  //console.log(options)
				            	 if( result[0].Cpu != 0)
				 				{
				 					options.series[0].data[0].value=parseInt(result[0].Cpu);
				 					
				 				} 
				                  	//console.log(options.series[0].data[0].value);
				 					myChart.setOption(options); 
									
				             } 
				         
				        },
				         error : function(errorMsg) {
				             //请求失败时执行该函数
				         alert("CPU图表请求数据失败!");
				         myChart.hideLoading();
				         }
				    })
					//console.log(options.series[4].data.length)
					
				}, 1000);
			
			
			
			
		    setInterval(function (){
				
		    	$.ajax({
			         type : "post",
			         async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			         url : "../ServiceRate_Servelet",    //请求发送到Servlet处
			         data : {},
			         dataType : "json",        //返回数据形式为json
			         success : function(result) {
			             //请求成功时执行该函数内容，result即为服务器返回的json对象
			             if (result) {
			                  //console.log(options)
			            	 if(options.series[3].data.length>=10)//存放折线统计图
			 				{
			 					//console.log(options.series[4].data.length)
			 					//options.series[3].data[0].value=parseInt(result[0].Cpu);
			 					options.series[3].data.shift();
			 					options.series[3].data.push(result[0].Cpu);
			 				}else if(options.series[3].data.length<10)
			 					{
			 					//console.log(options.series[4].data.length)
			 					//options.series[3].data[0].value=parseInt(result[0].Cpu);
			 					options.series[3].data.push(result[0].Cpu)
			 					}
			 					/* console.log(options)
			 					console.log(options.series[3].data[0].value) */
								//TCP_blocks = result[0].TCP_blocks;			 					
			 					myChart.setOption(options); 
								
			             } 
			         
			        },
			         error : function(errorMsg) {
			             //请求失败时执行该函数
			         alert("服务率图表请求数据失败!");
			         myChart.hideLoading();
			         }
			    })
				//console.log(options.series[4].data.length)
				
			}, 1000);
		    
		    setInterval(function (){
				$(function() {
					$.ajax({
						url:'../SuddenVisit_Servelet',
						dataType:'JSON',
						success:function(data){
							
							$("#SuddenVisit").html(data [0].SuddenVisit);
							//console.log(data[0].SuddenVisit);
							NetAttack = data[0].NetAttack;
							 if(data){
								options.series[1].detail.formatter=data[0].WebAttack;
								options.series[1].data[0].value=parseInt(data[0].Web_data_value);
								options.series[2].detail.formatter=data[0].NetAttack;
								options.series[2].data[0].value=parseInt(data[0].Net_data_value);
							 } 
							 console.log(options.series[2].detail.formatter);
							 console.log(options.series[2].data[0].value);
							 console.log(data [0].SuddenVisit)
							 myChart.setOption(options); 
						}
					});
				});
			}, 2000);
			
		    
		    
			
		}
		
		
		
		
		
		setInterval(function (){
			$(function() {
				$.ajax({
					url:'../Html_Servelet',
					dataType:'JSON',
					success:function(data){
						$("#TCP_blocks_numbers").html(data[0].TCP_blocks_numbers);
						$("#HTTP_blocks_numbers").html(data[0].HTTP_blocks_numbers);
						$("#UDP_blocks_numbers").html(data[0].UDP_blocks_numbers);
						$("#ANRC_numbers").html(data[0].ANRC_numbers);
						$("#abnormalIP_numbers").html(data[0].abnormalIP_numbers);
						$("#AllAccept_blocks_numbers").html(data[0].AllAccept_blocks_numbers);
						$("#AllUserIP_numbers").html(data[0].AllUserIP_numbers);
						
					}
				});
			});
		}, 100);
		
		
		$(function() {
			$.ajax({
				
				url:'../Main_Servelet',
				dataType:'JSON',
			 
			});
		});
		
	</script>
 
	</div>
	<div style="float: left;width: 100%;margin-left:350px">
		<div style="float:left;width:33%"> 
			<div style="text-align: center;float: left;width: 60%;">
				<h3 style="text-align: center;color:black;margin:40px">用户访问IP数</h3>
				<h1 style="text-align: center;line-height: 13px;color:green;"id="AllUserIP_numbers"></h1>
			</div>、   
			<div style="float:left;width: 33%;">
					<div style="float:left;width:80%">
						<div style="float: left;width:89%;margin-top:21px;"> 
							<h4 style="text-align: center;color:black">TCP包</h4>
							<h4 style="text-align: center;line-height: 55px">ANRC值</h4>
							
						</div>
						<div style="float:left;width:10%;margin-top:22px;">
						 	<h1 style="text-align: center;line-height:15px" id="TCP_blocks_numbers"></h1>	
						 	<h1 style="text-align: center;line-height:25px;line-height: 55px"id="ANRC_numbers"></h1>		
						</div>
				</div>	
				</div>
			

		
		</div>
		<div style="float:left;width: 36%;margin-top:22px">
			<div style="float:left;text-align: right;width:25%;">
				<h4 style="text-align: center;color:black">Http包</h4>
				<h4 style="text-align: center;line-height: 55px">异常IP数</h4>
			</div>
			
			<div style="float:left;text-align: center;width:25%;">
			
				<h1 style="text-align:left;color:black;margin-top:10px" id="HTTP_blocks_numbers"></h1>
				<h1 style="text-align:left;"id="abnormalIP_numbers"></h1>
			
			</div>
			
			<div style="float:left;text-align: right;width:20%;">
				<h4 style="text-align: left;color:black">UDP包</h4>
				<h4 style="text-align: left;line-height: 55px">总接收包</h4>
			</div>	
			<div style="float:left;text-align: left;width:20%;">
				<h1 style="text-align:left;color:black;margin-top:10px" id="UDP_blocks_numbers"></h1>
				<h1 style="text-align:left;"id="AllAccept_blocks_numbers"></h1>
			</div>
		</div>
		
	<!-- 	<div style="float:left;width: 10%;margin-top:22px"><第二层（三）>
			<div style="text-align: center;float: left;">
				<h3 style="text-align: center;color:black;">突发访问</h3>
				<h1 style="text-align: center;color:green;margin-top:26px">是</h1>
			</div>
		</div> -->
</div> 
</body>
</html>