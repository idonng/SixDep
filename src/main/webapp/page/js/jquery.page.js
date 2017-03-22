(function($){
	var ms = {
		init:function(obj,args){
			return (function(){
				ms.fillHtml(obj,args);
				ms.bindEvent(obj,args);
			})();
		},
		//填充html
		fillHtml:function(obj,args){
			return (function(){
				obj.empty();
				//上一页
				if(args.current > 1){
					obj.append('<a href="javascript:;" class="prevPage">上一页</a>');
				}else{
					obj.remove('.prevPage');
					obj.append('<span class="disabled">上一页</span>');
				}
				//中间页码
				if(args.current != 1 && args.current >= 4 && args.pageCount != 4){
					obj.append('<a href="javascript:;" class="tcdNumber">'+1+'</a>');
				}
				if(args.current-2 > 2 && args.current <= args.pageCount && args.pageCount > 5){
					obj.append('<span>...</span>');
				}
				var start = args.current -2,end = args.current+2;
				if((start > 1 && args.current < 4)||args.current == 1){
					end++;
				}
				if(args.current > args.pageCount-4 && args.current >= args.pageCount){
					start--;
				}
				for (;start <= end; start++) {
					if(start <= args.pageCount && start >= 1){
						if(start != args.current){
							obj.append('<a href="javascript:;" class="tcdNumber">'+ start +'</a>');
						}else{
							obj.append('<span class="current">'+ start +'</span>');
						}
					}
				}
				
				if(args.current + 2 < args.pageCount - 1 && args.current >= 1 && args.pageCount > 5){
					obj.append('<span>...</span>');
				}
				if(args.current != args.pageCount && args.current < args.pageCount -2  && args.pageCount != 4){
					obj.append('<a href="javascript:;" class="tcdNumber">'+args.pageCount+'</a>');
				}
				//下一页
				if(args.current < args.pageCount){
					obj.append('<a href="javascript:;" class="nextPage">下一页</a>');
				}else{
					obj.remove('.nextPage');
					obj.append('<span class="disabled">下一页</span>');
				}
			})();
		},
		//绑定事件
		bindEvent:function(obj,args){
			return (function(){
				obj.on("click","a.tcdNumber",function(){
					var url="../loadUserInfo.do";
					var current = parseInt($(this).text());
					$.post(url, {
						"currentPage":current,
						"rowSize" : 10
					}, function(data) {
						var json=data.user;
						var str='';
						pageCount=data.pageSize;
						for(var i=0;i<json.length;i++){
						 str+="<tr>"
						 +"<td>"+(i+1)+"</td>"
						 +"<td style='display:none;text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=i"+(i+1)+"  name="+json[i].id+">"+json[i].id+"</span></td>"
						 +"<td style='text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=n"+(i+1)+"  name="+json[i].name+">"+json[i].name+"</span></td>"
						 +"<td style='text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=d"+(i+1)+"  name="+data.position[i]+">"+data.position[i]+"</span></td>"
						 +"<td style='text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=m"+(i+1)+"  name="+json[i].mobilephone+">"+json[i].mobilephone+"</span></td>"
						 +"<td style='text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=a"+(i+1)+"  name="+json[i].area+">"+json[i].area+"</span></td>"
			             +"<td style='text-align:center;'><a onclick='showMsg("+(i+1)+")' class=\"delmsg btn btn-link\" data-toggle=\"modal\" aria-hidden=\"true\" id=\"delete-row\">删除</a></td>"  
			             +"</tr>"
						}
						$('#tbody').html(str);
						$(".tcdPageCode").createPage({
					        pageCount:pageCount,
					        current:1,
					        backFn:function(p){
					            //console.log(p);
					        }
					    });
						ms.fillHtml(obj,{"current":current,"pageCount":data.pageSize});
					}, "json");
					if(typeof(args.backFn)=="function"){
						args.backFn(current);
					}
				});
				//上一页
				obj.on("click","a.prevPage",function(){
					var url="../loadUserInfo.do";
					var current = parseInt(obj.children("span.current").text());
					$.post(url, {
						"currentPage":current-1,
						"rowSize" : 10
					}, function(data) {
						var json=data.user;
						var str='';
						pageCount=data.pageSize;
						for(var i=0;i<json.length;i++){
						 str+="<tr>"
						 +"<td>"+(i+1)+"</td>"
						 +"<td style='display:none;text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=i"+(i+1)+"  name="+json[i].id+">"+json[i].id+"</span></td>"
						 +"<td style='text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=n"+(i+1)+"  name="+json[i].name+">"+json[i].name+"</span></td>"
						 +"<td style='text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=d"+(i+1)+"  name="+data.position[i]+">"+data.position[i]+"</span></td>"
						 +"<td style='text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=m"+(i+1)+"  name="+json[i].mobilephone+">"+json[i].mobilephone+"</span></td>"
						 +"<td style='text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=a"+(i+1)+"  name="+json[i].area+">"+json[i].area+"</span></td>"
			             +"<td style='text-align:center;'><a onclick='showMsg("+(i+1)+")' class=\"delmsg btn btn-link\" data-toggle=\"modal\" aria-hidden=\"true\" id=\"delete-row\">删除</a></td>"  
			             +"</tr>"
						}
						$('#tbody').html(str);
						$(".tcdPageCode").createPage({
					        pageCount:pageCount,
					        current:1,
					        backFn:function(p){
					            //console.log(p);
					        }
					    });
					ms.fillHtml(obj,{"current":current-1,"pageCount":args.pageCount});
					}, "json");
					if(typeof(args.backFn)=="function"){
						args.backFn(current-1);
					}
				});
				//下一页
				
				obj.on("click","a.nextPage",function(){
					debugger;
					var url = "../loadUserInfo.do";
					var current = parseInt(obj.children("span.current").text());
					$.post(url, {
						"currentPage":current+1,
						"rowSize" : 10
					}, function(data) {
						var json=data.user;
						var str='';
						pageCount=data.pageSize;
						for(var i=0;i<json.length;i++){
						 str+="<tr>"
						 +"<td>"+(i+1)+"</td>"
						 +"<td style='display:none;text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=i"+(i+1)+"  name="+json[i].id+">"+json[i].id+"</span></td>"
						 +"<td style='text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=n"+(i+1)+"  name="+json[i].name+">"+json[i].name+"</span></td>"
						 +"<td style='text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=d"+(i+1)+"  name="+data.position[i]+">"+data.position[i]+"</span></td>"
						 +"<td style='text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=m"+(i+1)+"  name="+json[i].mobilephone+">"+json[i].mobilephone+"</span></td>"
						 +"<td style='text-align:center;'><span class='keyword' onclick='editRow("+(i+1)+")' id=a"+(i+1)+"  name="+json[i].area+">"+json[i].area+"</span></td>"
			             +"<td style='text-align:center;'><a onclick='showMsg("+(i+1)+")' class=\"delmsg btn btn-link\" data-toggle=\"modal\" aria-hidden=\"true\" id=\"delete-row\">删除</a></td>"  
			             +"</tr>"
						}
						$('#tbody').html(str);
						$(".tcdPageCode").createPage({
					        pageCount:pageCount,
					        current:1,
					        backFn:function(p){
					            //console.log(p);
					        }
					    });
						ms.fillHtml(obj,{"current":current+1,"pageCount":args.pageCount});
					}, "json");
					if(typeof(args.backFn)=="function"){
						args.backFn(current+1);
					}
				});
			})();
		}
	}
	$.fn.createPage = function(options){
		var args = $.extend({
			backFn : function(){}
		},options);
		ms.init(this,args);
	}
})(jQuery);