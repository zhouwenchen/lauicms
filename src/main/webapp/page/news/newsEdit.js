layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;
	
	//创建一个编辑器
	var addNewsArray = [],addNews;
 	var editIndex = layedit.build('news_content');
 	form.on("submit(addNews)",function(data){
 		//是否添加过信息
//	 	if(window.sessionStorage.getItem("addNews")){
//	 		addNewsArray = JSON.parse(window.sessionStorage.getItem("addNews"));
//	 	}
	 	//显示、审核状态
 		var isShow = data.field.isShow =="on" ? "checked" : "",
 		newsStatus = data.field.newsStatus =="on" ? "审核通过" : "待审核",
 		newsContent = layedit.getContent(editIndex);

 		addNews = '{"id":'+$("#id").val()+',';  					//id
 		addNews += '"newsId":"'+$("#newsId").val()+'",'; 			//newsId
 		addNews += '"newsLook":"'+$(".newsLook option").eq($(".newsLook").val()).text()+'",'; //开放浏览
 		addNews += '"newsTime":"'+$(".newsTime").val()+'",'; 		//发布时间
 		addNews += '"newsName":"'+$(".newsName").val()+'",'; 		//新闻名称
 		addNews += '"newsAuthor":"'+$(".newsAuthor").val()+'",'; 	//文章作者
 		addNews += '"newsContent":"'+ newsContent +'",'; 			// 内容
 		addNews += '"isShow":"'+ isShow +'",';  					//是否展示
 		addNews += '"newsStatus":"'+ newsStatus +'"}';				//审核状态
 		addNewsArray.unshift(JSON.parse(addNews));
 		console.log(JSON.stringify(JSON.parse(addNews)));
// 		window.sessionStorage.setItem("addNews",JSON.stringify(addNewsArray)); // 写入到浏览器的缓存中
 		//弹出loading
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
 		$.post("/newsInfo/updateNewsInfo",{"params":JSON.stringify(JSON.parse(addNews))}, function(data){
			if(data.status=="success"){
				layer.close(index);
				top.layer.close(index);
				top.layer.msg("编辑数据成功！");
	 			layer.closeAll("iframe");
		 		//刷新父页面
		 		parent.location.reload();
//			 		layer.msg("展示状态修改成功！");
			}else {
				layer.close(index);
				layer.msg("编辑数据失败！");
			}
		});
 		
//        setTimeout(function(){
//            top.layer.close(index);
//			top.layer.msg("文章添加成功！");
// 			layer.closeAll("iframe");
//	 		//刷新父页面
//	 		parent.location.reload();
//        },2000);
 		return false;
 	});
	
	
	
	
	
	//根据id的值，加载数据
	var newsData = '';
//	console.log(parent.id);
	var id = parent.id;
	$.ajax({
		url : "/newsInfo/getNewsInfoByWhere",
		data:{"id":id},
		type : "get",
		dataType : "json",
		success : function(data){
//		console.log(data);
//		console.log(data[0].newsName)
		// 表单中的数据回显操作
		var obj = data[0];
		$("#id").val(obj.id);
		$("#newsId").val(obj.newsId)
		$(".newsName").val(obj.newsName);
		
		// 是否审核
		if("审核通过" == obj.newsStatus){
			$(".newsStatus").attr('checked','');
			$(".newsStatus").next().select("div").addClass("layui-form-checked");// 
		}
		// 给是否显示添加样式
//		$(".newsStatus").val(obj.newsStatus);
		if("checked" == obj.isShow){
			$(".isShow").attr('checked','');
			$(".isShow").next().select("div").addClass("layui-form-checked");// 
		}
//		$(".isShow").val(obj.isShow);
		
		$(".newsAuthor").val(obj.newsAuthor);
		$(".newsTime").val(obj.createtime);
		$(".newsLook").val(obj.newsLook);
		
		// 富文本编辑器的回显操作
		layedit.setContent(editIndex,obj.newsContent);
//		console.log(layedit.getContent(editIndex));
		$("#news_content").val(obj.newsContent);
		
//		var newArray = [];
//		//单击首页“待审核文章”加载的信息
//		if($(".top_tab li.layui-this cite",parent.document).text() == "待审核文章"){
//			if(window.sessionStorage.getItem("addNews")){
//				var addNews = window.sessionStorage.getItem("addNews");
//				newsData = JSON.parse(addNews).concat(data);
//			}else{
//				newsData = data;
//			}
//			for(var i=0;i<newsData.length;i++){
//        		if(newsData[i].newsStatus == "待审核"){
//					newArray.push(newsData[i]);
//        		}
//        	}
//        	newsData = newArray;
//        	newsList(newsData);
//		}else{    //正常加载信息
//			newsData = data;
//			if(window.sessionStorage.getItem("addNews")){
//				var addNews = window.sessionStorage.getItem("addNews");
//				newsData = JSON.parse(addNews).concat(newsData);
//			}
//			//执行加载数据的方法
////			newsList();
//		}
	}});
	
	
 
	
});
