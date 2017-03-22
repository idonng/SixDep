//初始化，增加样式，获取选中值
var options = null;
var imgNum = 0;
$(function() {
	$
			.ajax({
				type : "POST",
				url : "../../MobileOptionsController/selectAllOptionsByUser.do",
				success : function(data) {
					$("#options").empty();
					var options = "";
					var json = eval("(" + data + ")");
					for ( var i = 0; i < json.list.length; i++) {
						if (json.list[i].frequency == 1) {
							options += "<table class='option table table-bordered data-table'><tr>";
							options += "<td align='left' style='width:95%'> "
									+ (i + 1) + "、" + json.list[i].name + " - "
									+ json.list[i].score + json.list[i].unit
									+ "</td>";
							if (json.list[i].number == "1") {
								options += "<td align='right' style='width:5%'><input type='checkbox' class='optionsinput' value="
										+ json.list[i].id + " checked />";
							} else {
								options += "<td align='right' style='width:5%'><input type='checkbox' class='optionsinput' value="
										+ json.list[i].id + " />";
							}
							options += "</tr></table>";
						} else {
							options += "<table class='option table table-bordered data-table'><tr>";
							options += "<td align='left' style='width:80%;'> "
									+ (i + 1) + "、" + json.list[i].name + " - "
									+ json.list[i].score + json.list[i].unit
									+ "</td>";
							options += "<td align='right' style='width:10% '><input type='text' name="
									+ (i + 1)
									+ " class='optionstext' style='margin:2%' id="
									+ json.list[i].id
									+ " value="
									+ json.list[i].number
									+ " placeholder='填报数量' />";
							options += "</td></tr></table>";
						}
					}
					$("#options").append(options);

					var imgTemp = new Array();
					imgTemp = json.proQuality.img;
					var htmlImg = "";
					$('#imgShow').empty();// 清空div避免重复添加
					for ( var num = 0; num < imgTemp.length; num++) {
						// imgTemp[num].substr(imgTemp[num].indexOf("uploads"))
						htmlImg = '<div style=\'margin-left: 50px;margin-top: 20px;\' id=\'div'
								+ (num + 1)
								+ '\'>'
								+ '图片'
								+ (num + 1)
								+ '：<img  id=\'file'
								+ (num + 1)
								+ '\' src =\' '
								+ imgTemp[num]
								+ '\' style=\'width:500px;\'  \>' + '</div>';
						$('#imgShow').append(htmlImg);
					}
				}
			});
});
// 积分填报
function add() {
	$.ligerDialog.confirm('您确定填报已选中项目吗？',
			function(yes) {
				if (yes) {
					// 如果添加了图片，则必须上传图片
					if (imgNum > 0) {
						for ( var i = 1; i <= imgNum; i++) {
							if ($('#file' + i).val() == 0) {
								$.ligerDialog.warn("请选择第(" + i + ")条图片!",
										function(type) {
											$('#file' + i).focus();
										});
								return false;
							}
						}
					}
					var checkt = /^\d$/;
					var optionsIds = "";
					var nums = "";
					var realTime = $("#realTime").val();
					var foruserid = $("#userInfos").val();
					var optionsinputs = document
							.getElementsByClassName("optionsinput");
					for ( var i = 0; i < optionsinputs.length; i++) {
						if (optionsinputs[i].checked) {
							optionsIds += optionsinputs[i].value + "##";
							nums += "1" + "##";
						} else {
							optionsIds += optionsinputs[i].value + "##";
							nums += "0" + "##";
						}
					}
					var optionstexts = document
							.getElementsByClassName("optionstext");
					for ( var i = 0; i < optionstexts.length; i++) {
						if (!checkt.test(optionstexts[i].value)) {
							alert("请输入有效的数字! 第" + optionstexts[i].name + "项");
							return;
						}
						optionsIds += optionstexts[i].id + "##";
						nums += optionstexts[i].value + "##";
					}
					$("#form1").ajaxSubmit({
						url : "../uploadImage.do",
						datatype : "json",
						success : function(returnData) {
							var urlPath = "../insertOrUpdateOptions.do";
							$.ajax({
								type : "POST",
								url : urlPath,
								data : {
									'realTime' : realTime,
									'foruserid' : foruserid,
									'optionsIds' : optionsIds,
									'nums' : nums
								},
								dataType : "json",
								success : function(data) {
									if (data == '1') {
										$.ligerDialog.success('提交成功');
										location.reload();
									} else {
										$.ligerDialog.error("提交失败");
									}
								}
							});
						},
						error : function() {
							$.ligerDialog.error("图片上传失败");
							return;
						}
					});
				} else {
					return;
				}
			});
}
// 上传图片
function addImg() {
	if (imgNum != 0 && $('#file' + imgNum).val() == "") {
		$.ligerDialog.warn('请上传图片(' + imgNum + ')后再添加！');
	} else {
		imgNum = imgNum + 1;
		var html = '<div style=\'margin: 2%;\' id=\'div' + imgNum + '\'>'
				+ '图片' + imgNum
				+ '：<input type=\'file\' name=\'files\' id=\'file' + imgNum
				+ '\' /><br />' + '</div>';
		$('#imgInfo').append(html);
	}
}

// 删除图片
function reduceImg() {
	if (imgNum == 0) {
		$.ligerDialog.warn('对不起，目前没有可以删除的图片信息！');
	} else {
		$('#div' + imgNum).empty();
		imgNum = imgNum - 1;
	}
}
