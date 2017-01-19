<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/easyDialog/easydialog.min.js"></script>
<link href="/js/easyDialog/easydialog.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src='/js/My97DatePicker/WdatePicker.js'></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		$.post("/${oname}/user/yuyueRecordShow.action",
		   function(data){
				var aptid = data.yuyue.aptid;
				$.each( data.recordList, function(i, p){
					var html = '<tr>';
					var oname = "${oname}";
					html += '<td>'+p.nickname+'</td>';
					html +='<td>'+p.servicename+'</td>';
					html += '<td>'+p.sername+'</td>';
					html += '<td>'+p.yytimeStr+'';
					html += '</td>';
					html += '<td>';
					if(p.status == "EDT"){
						html += '未确认';
					}else if(p.status == "CMP"){
						html += '已确认';
					}
					html += '</td>';
					html += '<td>';
					html += '<a href="javascript:editAptDetail('+aptid+','+p.wxuid+','+p.id+')">详情</a>';
					html += '</td>';
					html += '</tr>';
					$("#tb").append(html);
				});
		   });
		   
		   $("#guoqi").click(function(){
				$.post("/${oname}/user/yuyueRecordShowByNow.action", function(data){
					$("#tb").html("");
					var aptid = data.yuyue.aptid;
					$.each( data.recordList, function(i, p){
						var html = '<tr>';
						var oname = "${oname}";
						html += '<td>'+p.nickname+'</td>';
						html +='<td>'+p.servicename+'</td>';
						html += '<td>'+p.sername+'</td>';
						html += '<td>'+p.yytimeStr+'';
						html += '</td>';
						html += '<td>';
						if(p.status == "EDT"){
							html += '未确认';
						}else if(p.status == "CMP"){
							html += '已确认';
						}
						html += '</td>';
						html += '<td>';
						html += '<a href="javascript:editAptDetail('+aptid+','+p.wxuid+','+p.id+')">详情</a>';
						html += '</td>';
						html += '</tr>';
						$("#tb").append(html);
					});
		   	});
		});
		
		 $("#sousuo").click(function(){
		 		var status = $('#status').val();
		 		var yytime = $('#yytime').val();
				$.post("/${oname}/user/yuyueRecordShow.action",{"status":status,"yytime":yytime}, function(data){
					$("#tb").html("");
					var aptid = data.yuyue.aptid;
					$.each( data.recordList, function(i, p){
						var html = '<tr>';
						var oname = "${oname}";
						html += '<td>'+p.nickname+'</td>';
						html +='<td>'+p.servicename+'</td>';
						html += '<td>'+p.sername+'</td>';
						html += '<td>'+p.yytimeStr+'';
						html += '</td>';
						html += '<td>';
						if(p.status == "EDT"){
							html += '未确认';
						}else if(p.status == "CMP"){
							html += '已确认';
						}
						html += '</td>';
						html += '<td>';
						html += '<a href="javascript:editAptDetail('+aptid+','+p.wxuid+','+p.id+')">详情</a>';
						html += '</td>';
						html += '</tr>';
						$("#tb").append(html);
					});
		   	});
		});
});
</script>
<div class="wrap_content">
	<div class="toolbar mt20">
	<form action="" method="post" id="myform">
		状态：<select name="status" id="status">
			  <option value ="ALL">全部</option>
			  <option value ="EDT" <s:if test='status == "EDT"'>selected="selected"</s:if>>未确认</option>
			  <option value ="CMP" <s:if test='status == "CMP"'>selected="selected"</s:if>>已确认</option>
		</select>
		预约时间：
		<input id="yytime" type="text" value="<s:date name="yytime" format="yyyy-MM-dd HH:mm:ss"/>" name="yytime" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
		<input type="button" value="搜索" id="sousuo">
		<input type="button" value="已过期" id="guoqi">
	</form>
	</div>

	<table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
			<thead>
				<tr>
					<th>昵称</th>
					<th>预约服务</th>
					<th>预约人员</th>
					<th>预约时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tb">
				
			</tbody>
	</table>
</div>
<script type="text/javascript">
	
function editAptDetail1(aptid,wxuid,id){
	var srcString = '/${oname}/user/yuyue_apt_record.action?aptid='+aptid+'&wxuid='+wxuid + '&recordid=' + id;
	$('#wideModal1').removeData("bs.modal");
		$('#wideModal1').modal({
			remote:srcString
		});
}

function editAptDetail(aptid,wxuid,id){
	$.post("/${oname}/user/yuyue_apt_record.action",{"aptid":aptid,"wxuid":wxuid,"recordid":id},function(data){
		$("#userinfo").html("");
		$('#rid').val(data.aptrecord.id);
		$('#recordid').val(data.record.id);
		$.each( data.aptrecord.maps, function(i, p){
			var html = '<tr align="center">';
			html += '<td>'+p.name+'</td>';
			html +='<td><input name="record.'+p.mapping+'" class="text-medium" value="'+data.aptrecord.values[i]+'"/></td>';
			html += '</tr>';
			$("#userinfo").append(html);
		});
		var html2 = '<tr align="center">';
		html2 += '<td>服务</td><td>'+data.record.servicename+'</td>' ;
		html2 += '</tr>';
		html2 += '<tr align="center">';
		html2 += '<td>预约人员</td><td>'+data.record.sername+'</td>' ;	
		html2 += '</tr>';
		html2 += '<tr align="center">';
		html2 += '<td>预约时间</td><td><input id="yytime" type="text" value="'+data.record.yytimeStr+'" name="yytime" class="Wdate" onFocus="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'})" readonly="readonly"/></td>' ;	
		html2 += '</tr>';
		$("#userinfo").append(html2);
		
	});
	
	easyDialog.open({
		  container : 'wideModal1',
		  overlay : false
	});
}

function formsub(){
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:'/${oname}/user/updateAptRecord.action',
	        data:$('#form1').serialize(),// 你的formid
	        async: false,
	        error: function(request) {
	            alert("Connection error");
	        },
	        success: function(data) {
	            if(data > 0){
	            	window.parent.location.reload();
	            }else{
	            	alert("操作失败!");
	            }
	        }
	    });
	}

</script>

<div class="easyDialog_wrapper" id="wideModal1" style="display: none;">
  <div class="easyDialog_content">
    <h4 class="easyDialog_title" id="easyDialogTitle">
      <a href="javascript:void(0)" title="关闭窗口" class="close_btn" onclick="easyDialog.close();">&times;</a>
      标题
    </h4>
    <div class="easyDialog_text">
    	 <form action="" id="form1">
	    	<input type="hidden" name="record.id" value="${record.id }" id="rid"/>
	    	<input type="hidden" value="${recordid }" name="recordid" id="recordid">
	    	<table width="100%" border="1" cellspacing="1" cellpadding="1" class="tb_normal">
				<tbody id="userinfo">
					<s:iterator value="record.maps" var="m" status="st">
						<tr align="center">
							<td>${m.name}</td><td><input name="record.${m.mapping }" class="text-medium" value="${record.values[st.index] }"/></td>
						</tr>
					</s:iterator>
					<tr align="center">
					<td>服务</td><td>${dto.record.servicename }</td>
					</tr>
					<tr align="center">
						<td>预约人员</td><td>${dto.record.sername }</td>
					</tr>
					<tr align="center">
						<td>预约时间</td><td><input id="yytime" type="text" value="<s:date name="dto.record.yytime" format="yyyy-MM-dd HH:mm:ss"/>" name="yytime" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"/></td>
					</tr>  	    	
				</tbody>
			</table>
	    </form>
    
    </div>
    <div class="easyDialog_footer">
      <button class="btn" onclick="easyDialog.close();">取消</button>
      <button class="btn btn-primary" onclick="formsub()">确定</button>
    </div>
  </div>
</div>
