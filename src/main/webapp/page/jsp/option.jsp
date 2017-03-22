<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}/page"></c:set>
<!DOCTYPE html>
<html>
	<head>
		<title>选项信息管理 | sixDepScore</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${ctx}/css/fullcalendar.css" />
<link rel="stylesheet" href="${ctx}/css/style.css" />
<link rel="stylesheet" href="${ctx}/css/media.css" />
<link rel="stylesheet" href="${ctx}/css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

<script src="${ctx}/js/excanvas.min.js"></script> 
<script src="${ctx}/js/jquery.min.js"></script> 
<script src="${ctx}/js/jquery.ui.custom.js"></script> 
<script src="${ctx}/js/bootstrap.min.js"></script> 
<script src="${ctx}/js/jquery.flot.min.js"></script> 
<script src="${ctx}/js/jquery.flot.resize.min.js"></script> 
<script src="${ctx}/js/jquery.peity.min.js"></script> 
<script src="${ctx}/js/fullcalendar.min.js"></script> 
<script src="${ctx}/js/calendar.js"></script>
<script src="${ctx}/js/matrix.js"></script> 
<script src="${ctx}/js/dashboard.js"></script> 
<script src="${ctx}/js/jquery.gritter.min.js"></script> 
<script src="${ctx}/js/interface.js"></script> 
<script src="${ctx}/js/chat.js"></script> 
<script src="${ctx}/js/jquery.validate.js"></script> 
<script src="${ctx}/js/form_validation.js"></script> 
<script src="${ctx}/js/jquery.wizard.js"></script> 
<script src="${ctx}/js/jquery.uniform.js"></script> 
<script src="${ctx}/js/select2.min.js"></script> 
<script src="${ctx}/js/popover.js"></script> 
<script src="${ctx}/js/jquery.dataTables.min.js"></script> 
<script src="${ctx}/js/tables.js"></script> 
<%-- 
	<script type="text/javascript" src="${ctx}/libs/ligerUI/js/ligerui.all.js"></script>
	<script type="text/javascript" src="${ctx}/libs/jquery.cookie.js"></script>
    <script src="${ctx}/libs/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script> 
	
<link rel="stylesheet" type="text/css" href="${ctx}/libs/ligerUI/skins/Aqua/css/ligerui-all.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/libs/ligerUI/skins/Gray2014/css/all.css" /> --%>
	
<style type="text/css">
	@-moz-document url-prefix() {   /*针对 Firefox 的 hack 代码*/
  fieldset { display: table-cell; }
}
	thead th{padding: 15px !important;}
	thead th:first-child{width: 10%;}
	thead th:nth-child(2){width: 20%;}
	thead th:last-child{width: 20%;}
	tbody td:first-child{text-align: center;}
	tbody td:nth-child(2){text-align: center;}
	tbody td:nth-child(3){margin-left: 15px !important;}
	tbody td:last-child{text-align: center;}
	tbody td{padding: 25px 30px 15px 30px !important;vertical-align: middle;}
	.add{margin: 3px 3px;}
	.keyword{min-width: 95.5%;}
	.sel{width:98%}
	#themename{margin-bottom: 10px;}
	body{ font-family: helvetica, "微软雅黑"; font-size:12px; color:#666;}
	#content{width: 100%;float: right;}
	.container-fluid{padding-top:none;}
	h1{padding: 20px;}
	.tcdPageCode{padding: 15px 20px;text-align: left;color: #ccc;text-align:center;}
	.tcdPageCode a{display: inline-block;color: #428bca;display: inline-block;height: 25px;	line-height: 25px;	padding: 0 10px;border: 1px solid #ddd;	margin: 0 2px;border-radius: 4px;vertical-align: middle;}
	.tcdPageCode a:hover{text-decoration: none;border: 1px solid #428bca;}
	.tcdPageCode span.current{display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;color: #fff;background-color: #428bca;	border: 1px solid #428bca;border-radius: 4px;vertical-align: middle;}
	.tcdPageCode span.disabled{	display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;	color: #bfbfbf;background: #f2f2f2;border: 1px solid #bfbfbf;border-radius: 4px;vertical-align: middle;}
	#addmyModal{display:none;}
	#myModal{display: none;}

</style>

	</head>
	<body> 

<!--main-container-part-->
<div id="content">
	 		<form action="#" method="get" class="form-horizontal">
            <div class="form-actions">
             <div class="control-group" for="stype" >
             <label>选项类型：</label>
              			<select name="stype" id="stype"  style="float:left">
								<option value="1">大区经理积分项</option>
								<option value="2">推广经理积分项</option>
							</select>
			              <button type="button" class="btn btn-success btn-block modify" style=' width: 7%; margin-left: 10%;'  onclick="search();">查 询</button>
			
            </div>
            </div>
          </form>
<!--theme-table-->
	<div class="container-fluid">
		<div class="widget-box">
          <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
            <h5>选项信息管理</h5>
            
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-info btn-xs fr add" data-toggle="modal" data-target="#addmyModal" >
				  添加
				</button>
				
				<!-- Modal -->
				<div class="modal fade" id="myModal"  data-show="false" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div>
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only"></span></button>
				        <h4 class="modal-title" id="myModalLabel">选项信息设置</h4>
				      </div>
				      <div class="modal-body">
				        <form class="form-horizontal" role="form">
				        	<label for="code">序号：</label>
				        	<input type="text" id="code" value="" pattern="^[1-9]\d*$" class="keyword" maxlength="10"/>
							<br />
				        	<br />
				        	<label for="name">选项名称：</label>
				        	<input type="text" id="name" value="" class="keyword" maxlength="50"/>
				        	<br />
				        	<br />
				        	<label for="score">分值：</label>
				        	<input type="text" id="score" value="" pattern="^[1-9]\d*$" class="keyword" maxlength="10"/>
				        	<br />
				        	<br />
				        	<label for="unit">单位：</label>
				        	<input type="text" id="unit" value="" class="keyword" maxlength="10"/>
				        	<br />
				        	<br />
				        	<label for="fre">频率：</label>
				        	<select name="fre" id="fre" class="sel">
								<option value="1">每天一次</option>
								<option value="2">每月一次</option>
							</select>
				        	<br />
				        	<br />
				        	<label for="type">分类：</label>
				        	<select name="type" id="type" class="sel">
								<option value="1">大区经理积分表</option>
								<option value="2">推广经理积分表</option>
							</select>
				        </form>
				      </div>
				      <div class="modal-footer">
				        <label id="warn" align="center" style="padding-bottom:0.2cm;"></label>
				        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				        <button type="button" class="btn btn-primary" id="updatetr" onclick="update();">保存</button>
				      </div>
				    </div>
				  </div>
				  </div>
				</div>
				
				<!-- addModal -->
				<div class="modal fade" id="addmyModal" data-show="false"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div>
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only"></span></button>
				        <h4 class="modal-title" id="addmyModalLabel">选项信息添加</h4>
				      </div>
				      <div class="modal-body">
				        <form class="form-horizontal" role="form">
				        	<label for="addcode">序号：</label>
				        	<input type="text" id="addcode" value="" pattern="^[1-9]\d*$" class="keyword" maxlength="10"/>
							<br />
				        	<br />
				        	<label for="addname">选项名称：</label>
				        	<input type="text" id="addname" value="" class="keyword" maxlength="10"/>
				        	<br />
				        	<br />
				        	<label for="addscore">分值：</label>
				        	<input type="text" id="addscore" value="" pattern="^[1-9]\d*$"class="keyword" maxlength="10"/>
				        	<br />
				        	<br />
				        	<label for="addunit">单位：</label>
				        	<input type="text" id="addunit" value="" class="keyword" maxlength="10"/>
				        	<br />
				        	<br />
				        	<label for="addfre">频率：</label>
				        	<select name="addfre" id="addfre" class="sel">
								<option value="1">每天一次</option>
								<option value="2">每月一次</option>
							</select>
				        	<br />
				        	<br />
				        	<label for="addtype">分类：</label>
				        	<select name="type" id="addtype" class="sel">
								<option value="1">大区经理积分表</option>
								<option value="2">推广经理积分表</option>
							</select>
				        </form>
				      </div>
				      <div class="modal-footer">
				        <label id="addwarn" align="center" style="padding-bottom:0.2cm;"></label>
				        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				        <button type="button" class="btn btn-primary" id="addtr1" onclick="add();">保存</button>
				      </div>
				    </div>
				  </div>
				  </div>
				</div>
            
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table" id="msg_table">
              <thead>
                <tr style="height:10px;">
                  <th style="display:none">选项ID</th>
                   <th style="width:10%;">选项序号</th>
                  <th>选项名称</th>
                  <th style="width:10%;">分值</th>
                  <th style="width:15%;">单位</th>
                  <th style="width:15%;">频率</th>
                  <th style="width:15%;">操作</th>
                </tr>
              </thead>
              <tbody id="tbody">
              	
                
                 <div class="modal fade" id="myDel">
			         <div class="modal-dialog">
			            <div class="modal-content">
			               <div class="modal-header">
			                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			                      <h4 class="modal-title">确认操作</h4>
			                  </div>
			                  <div class="modal-body">
			                      <h5 class="text-danger">确定要删除该选项?</h5>
			                 </div>
			                 <div class="modal-footer">
			                    <input type="hidden" id="hidd">
			                     <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			                     <button type="button" class="btn btn-danger" value="deleteAlone" onclick="deleteRow()">确认删除</button>
			                 </div>
			             </div><!-- /.modal-content -->
			         </div><!-- /.modal-dialog -->
			 </div><!-- /.modal -->
			 <!--删除操作modal 上-->
              </tbody>
            </table>
          </div>
       </div>
       
</div>
<div class="tcdPageCode"></div>
</div>

<!--End-theme-table-->


 <!--Footer-part-->
<div class="row-fluid">
  <div id="footer" class="span12"> 2017 &copy; 步长制药. Copyright 电商部  </div>
</div>
<!--end-Footer-part-->

<script src="${ctx}/js/jquery.page.js"></script>
<script type="text/javascript">
//var pageCount=0;
search();
function search(){
var dtid = document.getElementById('stype').value;
		var urlPath = "${ctx}/selectOptions.do";
		$.ajax({
			type : "POST",
			url : urlPath,
			dataType : "json",
			async : false,
			data : {
				'deptypeid' : dtid,
			},
			success : function(msg) {
			var json=msg.option;
			var str='';
			for(var i=0;i<json.length;i++){
			var f='';
			if(json[i].frequency==1)
			{
			f="每天一次";
			}
			else if(json[i].frequency==2)
			{
			f="每月一次";
			}
			 str+="<tr style='height:5px;'>"
			 +"<td style='display:none;text-align:center;'><span class='opt' onclick='editRow("+(i+1)+")'  id=i"+(i+1)+"  name="+json[i].id+">"+json[i].id+"</span></td>"
			  +"<td style='text-align:center;'><span onclick='editRow("+(i+1)+")' id=c"+(i+1)+" data-target=\"#myModal\" name="+json[i].optionscode+">"+json[i].optionscode+"</span></td>"
			 +"<td style='text-align:center;'><span onclick='editRow("+(i+1)+")' id=n"+(i+1)+" data-target=\"#myModal\"  name="+json[i].name+">"+json[i].name+"</span></td>"
			 +"<td style='text-align:center;'><span onclick='editRow("+(i+1)+")' id=s"+(i+1)+"  name="+json[i].score+">"+json[i].score+"</span></td>"
			 +"<td style='text-align:center;'><span onclick='editRow("+(i+1)+")' id=u"+(i+1)+"  name="+json[i].unit+">"+json[i].unit+"</span></td>"
			 +"<td style='text-align:center;'><span onclick='editRow("+(i+1)+")' id=f"+(i+1)+"  name="+json[i].frequency+">"+f+"</span></td>"
             +"<td style='text-align:center;'><a onclick='showMsg("+(i+1)+")' class=\"delmsg btn btn-link\" data-toggle=\"#modal\" aria-hidden=\"true\" id=\"delete-row\">删除</a></td>"  
             +"</tr>"
			}
			$('#tbody').html(str);
			},
			error : function(data) {
				//alert(data);
			}
		});
	}
     </script>
<script type="text/javascript">
  // This function is called from the pop-up menus to transfer to
  // a different page. Ignore if the value returned is a null string:
  function goPage (newURL) {

      // if url is empty, skip the menu dividers and reset the menu selection to default
      if (newURL != "") {
      
          // if url is "-", it is this page -- reset the menu:
          if (newURL == "-" ) {
              resetMenu();            
          } 
          // else, send page to designated URL            
          else {  
            document.location.href = newURL;
          }
      }
  }

// resets the menu selection upon entry to this page:
function resetMenu() {
   document.gomenu.selector.selectedIndex = 2;
}
</script>

<script type="text/javascript"> 	

 function deleteRow(){
 	var key = $("#hidd").val();
    $("#myDel").modal("hide");
    var oid =$("#i"+key)[0].outerText; 
		var urlPath = "${ctx}/deleteOption.do";
		$.ajax({
			type : "POST",
			url : urlPath,
			data : {
				'id' : oid,
			},
			dataType : "json",
			async : false,
			success : function(data) {
				if (data=="1") {
					alert("删除成功!");
				} else {
					alert("删除失败!");
				}
			},
			error : function(data) {
				alert("删除失败!");
			}
		}); 
        location.reload();  
   }      

   function showMsg(key){
   	$("#myDel").modal("show");
   	$("#hidd").val(key);
   }  

   var optionscode;
   var name;
   var score; 
   var unit;  
   var frequency; 
   var type; 
   var rid; 
	function editRow(key) {
		$("#myModal").modal("show");
		optionscode =$("#c"+key)[0].outerText;
		$("#code").val(optionscode);
		name =$("#n"+key).text();;
		$("#name").val(name);
		score =$("#s"+key).text();;
		$("#score").val(score);
		unit =$("#u"+key).text();;
		$("#unit").val(unit);
		frequency =$("#f"+key).attr("name");
		$("#fre").val(frequency);
		type = document.getElementById('stype').value;
		$("#type").val(type);
		rid =$("#i"+key).text();
		$("#rid").val(rid);
		}
		
	function update() {
		var updatec = document.getElementById('code').value;
		var updaten = document.getElementById('name').value;
		var updates = document.getElementById('score').value;
		var updateu = document.getElementById('unit').value;
		var updatef = document.getElementById('fre').value;
		var updatet = document.getElementById('type').value;
		var checkc =/^[1-9]\d*$/;
		var checks =/^[1-9]\d*$/;

		if((updaten.replace(/\s/g, "").length == 0)||(updateu.replace(/\s/g, "").length == 0)||(updatef.replace(/\s/g, "").length == 0)||(updatet.replace(/\s/g, "").length == 0)){
			document.getElementById("warn").style.color = "red";
			document.getElementById("warn").innerText = "序号、选项名称、分值、单位均不能为空！";
		}
		else if ((!checkc.test(updatec))||(!checks.test(updates))) {
			document.getElementById("warn").style.color = "red";
			document.getElementById("warn").innerText = "请输入有效的序号和分值！";
		} 
		else {
		var urlPath = "${ctx}/updateOption.do";
			$.ajax({
						type : "POST",
						url : urlPath,
						data : {
							'id' : rid,
							'optionscode' : updatec,
							'name' : updaten,
							'score' : updates,
							'unit' : updateu,
							'frequency' : updatef,
							'deptypeid' : updatet,
						},
						dataType : "json",
						async : false,
						success : function(data) {
							if (data == "1") {
								document.getElementById("warn").style.color = "green";
								document.getElementById("warn").innerText = "更新成功!";
								setTimeout("self.location.reload();", 1000);
  
							}
							else {
								document.getElementById("warn").style.color = "red";
								document.getElementById("warn").innerText = "更新失败!";
								setTimeout("self.location.reload();", 1000);
					}
				}
			});
		}
	}

	function add() {
		var addc = document.getElementById('addcode').value;
		var addn = document.getElementById('addname').value;
		var adds = document.getElementById('addscore').value;
		var addu = document.getElementById('addunit').value;
		var addf = document.getElementById('addfre').value;
		var addt = document.getElementById('addtype').value;
		var checkc =/^[1-9]\d*$/;
		var checks =/^[1-9]\d*$/;

		if((addn.replace(/\s/g, "").length == 0)||(addu.replace(/\s/g, "").length == 0)||(addf.replace(/\s/g, "").length == 0)||(addt.replace(/\s/g, "").length == 0)){
			document.getElementById("addwarn").style.color = "red";
			document.getElementById("addwarn").innerText = "序号、选项名称、分值、单位均不能为空！";
		}
		else if ((!checkc.test(addc))||(!checks.test(adds))) {
			document.getElementById("addwarn").style.color = "red";
			document.getElementById("addwarn").innerText = "请输入有效的序号和分值！";
		} 
		else {
			var urlPath = "${ctx}/addOption.do";
			$.ajax({
						type : "POST",
						url : urlPath,
						data : {
							'optionscode' : addc,
							'name' : addn,
							'score' : adds,
							'unit' : addu,
							'frequency' : addf,
							'deptypeid' : addt,
						},
						dataType : "json",
						async : false,
						success : function(data) {
							if (data == "1") {
								document.getElementById("addwarn").style.color = "green";
								document.getElementById("addwarn").innerText = "添加成功!";
								setTimeout("self.location.reload();", 1000);
  
							}
							else {
								document.getElementById("addwarn").style.color = "red";
								document.getElementById("addwarn").innerText = "添加失败!";
								setTimeout("self.location.reload();", 1000);

							}
						}
					});
		}
	}
	
/* 	 $(".tcdPageCode").createPage({
        pageCount:pageCount,
        current:1,
        backFn:function(p){
            //console.log(p);
        }
 	});  */
 	$('#addmyModal').modal('hide');
</script>

	</body>
</html>
