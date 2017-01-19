<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微预约表单 -->
<link href="/css/Hshenqing/index.css" rel="stylesheet" type="text/css" />
<script src="/js/wyy_ht/mobiscroll_002.js" type="text/javascript"></script>
<script src="/js/wyy_ht/mobiscroll_004.js" type="text/javascript"></script>
<link href="/css/wyy_ht/mobiscroll_002.css" rel="stylesheet" type="text/css">
<link href="/css/wyy_ht/mobiscroll.css" rel="stylesheet" type="text/css">
<script src="/js/wyy_ht/mobiscroll.js" type="text/javascript"></script>
<script src="/js/wyy_ht/mobiscroll_003.js" type="text/javascript"></script>
<script src="/js/wyy_ht/mobiscroll_005.js" type="text/javascript"></script>
<link href="/css/wyy_ht/mobiscroll_003.css" rel="stylesheet" type="text/css">

<link href="https://res.wx.qq.com/open/libs/weui/0.4.0/weui.css" rel="stylesheet"/>
<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet"/>
<script src="/js/jquery-weui/js/jquery-weui.js" type="text/javascript"></script>

<%@include file="/WEB-INF/card/background.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		if("${method}"=="E"){
			return;
		}
		var kv = $('#hy_kv').val();
		var kk = kv.split("-");
		var catid = 0;
		var servicerid = 0;
		var serviceid = 0;
		if(kk.length == 3){
			catid = kk[0];
			var type = kk[1];
			if(type == "U"){
				servicerid = kk[2];
			}
			if(type == "S"){
				serviceid = kk[2];
				$('#serviceid').val(serviceid);	
			}
			$('#type').val(type);
			$('#catid').val(catid);
		}
	
	//服务者列表
		if(catid > 0 && servicerid > 0){
			$.post("/${oname}/user/findYuYueServicerListBycatid.action",{"catid":catid},function(data){
				$("#servicer").empty();
				$("#servicer").append("<option value='-1'>选择"+'${blocks[0].title2}'+"</option>");
				if(data.servicerList){
					$.each(data.servicerList, function(i, n){
						if(n.id == servicerid){
							$("#servicer").append("<option selected='selected' value='"+n.id+"'>"+n.name+"</option>");
						}else{
							$("#servicer").append("<option value='"+n.id+"'>"+n.name+"</option>");
						}
					});
				}
			});
			
			$.post("/${oname}/user/findServiceListBySerid.action",{"serid":servicerid,"catid":catid},function(data){
				$("#service").empty();
				$("#service").append("<option value='0'>选择"+'${blocks[0].title1}'+"</option>");
				if(data.serviceList){
					$.each(data.serviceList, function(i, n){
						$("#service").append("<option value='"+n.id+"'>"+n.name+"</option>");
					});
				}
			});
		}

		//服务列表   
		if(catid > 0 && serviceid > 0){
			$.post("/${oname}/user/findYuYueService.action",{"catid":catid},function(data){
				$("#service").empty();
				$("#service").append("<option value='0'>选择"+'${blocks[0].title1}'+"</option>");
				if(data.serviceList){
					$.each(data.serviceList, function(i, n){
						if(n.id == serviceid){
							$("#service").append("<option selected='selected' value='"+n.id+"'>"+n.name+"</option>");
						}else{
							$("#service").append("<option value='"+n.id+"'>"+n.name+"</option>");
						}
						
					});
				}
			});
			
			//根据服务id查询人员列表
			 $.post("/${oname}/user/findServicerListByService.action",{"serviceid":serviceid,"catid":catid},function(data){
				$("#servicer").empty();
				$("#servicer").append("<option value='-1'>选择"+'${blocks[0].title2}'+"</option>");
				if(data.servicerList){
					$.each(data.servicerList, function(i, n){
						$("#servicer").append("<option value='"+n.id+"'>"+n.name+"</option>");
					});
				}
			});
			
		}
		//根据服务id查询人员列表（联动）
		$("#service").change(function(){ 
		   $.post("/${oname}/user/findServicerListByService.action",{"serviceid":$("#service").val(),"catid":catid},function(data){
				console.log(data.servicerList);
				$("#servicer").html("");
				$("#servicer").append("<option value='-1'>选择"+'${blocks[0].title2}'+"</option>");
				if(data.servicerList){
					$.each(data.servicerList, function(i, n){
						if(n.id == servicerid){
							$("#servicer").append("<option selected='selected' value='"+n.id+"'>"+n.name+"</option>");
						}else{
							$("#servicer").append("<option value='"+n.id+"'>"+n.name+"</option>");
						}
						
					});
				}
			});
		});
		
		
	});

</script>


<s:if test='blocks[0].display=="Y"'>
<div class="Hhrbox clearfix block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct};z-index:0;" hyblk="S" hydata="${blocks[0].rid}">
<s:if test='blocks[0].obj.id > 0'>
	<form id="myform${pcid }" method="post" class="preview">
		<s:set name="obj" value='blocks[0].obj' />
			 	<input type="hidden" name="aptRecord.aptid" value="${obj.id}" />
			 	<input type="hidden" name="aptRecord.pageid" value="${pageid}">
			 	<input type="hidden" value="${pageid }" name="pageid" id="pageid">
			 	<input type="hidden" name="catid" value="0" id="catid">
		        <input type="hidden" value="N" id="type" name="type">
		        <input type="hidden" value="${blocks[0].title1}" name="tag1">
		        <input type="hidden" value="${blocks[0].title2}" name="tag2">
			 	<ul>
				<s:iterator value="#obj.list" var="p" status="st">
				<li>
					<s:if test='#p.isshow == "Y"'>
						<s:if test='#p.stype == "T"'>
						     <input type="text" placeholder="${p.name }" title="${p.name }" class="Hhrinput" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" onblur="check(this)" />
						     <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:if>
						<s:if test='#p.stype == "E"'>
						     <input type="email" placeholder="${p.name }" title="${p.name }" class="Hhrinput" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" onblur="check(this)" />
						     <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:if>
						<s:if test='#p.stype == "N"'>
						     <input type="number" placeholder="${p.name }" title="${p.name }" class="Hhrinput" min="0" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" onblur="check(this)" />
						     <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:if>
						<s:elseif test='#p.stype == "R"'>
							<span>${p.name }</span>
								<s:iterator value="#p.defaultvalueArr" var="f" status="st">
								 <input style="margin-left:10px;" type="radio" name="aptRecord.${p.mapping}" value="${f}" msg="${p.req}" <s:if test="#st.index==0"> checked="checked"</s:if>  />${f}
								 </s:iterator>
							<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
						<s:elseif test='#p.stype == "D"'>
								<input type="date" placeholder="${p.name }" title="${p.name }" class="Hhrinput" name="aptRecord.${p.mapping}" id="${p.mapping}" value="${p.defaultvalue}" msg="${p.req}"/>
								<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
						<s:elseif test='#p.stype == "C"'>
							<span>${p.name }</span>
						       <s:iterator value="#p.defaultvalueArr" var="f" status="st">
								<input style="margin-left:10px;" type="checkbox" name="aptRecord.${p.mapping}" value="${f}" msg="${p.req}"/>${f}
								</s:iterator>
						      <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
						<s:elseif test='#p.stype == "S"'>
						     <span>${p.name }</span>
						     <select class="Hhrinput" name="aptRecord.${p.mapping}">
						     <s:iterator value="#p.defaultvalueArr" var="f">
								  <option value ="${f}">${f}</option>
								</s:iterator> 
						    </select>
						    <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
						<s:elseif test='#p.stype == "A"'>
								<textarea placeholder="${p.name }" title="${p.name }" name="aptRecord.${p.mapping}" rows="3"></textarea>
							<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
					</s:if>
				</li>
				</s:iterator>
				
				
				<li class="st">
					  <select class="Hhrinput" name="serviceid" size="1" id="service" style="height:30px;">
	                    <option>选择${blocks[0].title1}</option>
	                    <option>服务1</option>
	                    <option>服务2</option>
	                  </select>
	                  <font color="red">*</font>
				</li>
				<li class="st">
					  <select class="Hhrinput" name="serid" size="1" id="servicer" style="height:30px;">
	                    <option>选择${blocks[0].title2}</option>
	                    <option>服务人员</option>
	                   	<option>服务人员</option>
	                  </select>
	                  <font color="red">*</font>
				</li>
				<li>
					 <input placeholder="${blocks[0].title3}" title="${blocks[0].title3}" class="Hhrinput" id="appDateTime" name="yytime" readonly type="text">
					 <font color="red">*</font>
				</li>
				
				<li>
					 <textarea placeholder="${blocks[0].title4}" class="Hhrinput" cols="3" name="hydesc" rows="3" style="height:60px;"></textarea>
				</li>
				
				</ul>
   				<input type="button" class="Hhrbutton" id="tijiao${pcid }" value="提交信息" />
	</form>	
</s:if>
</div>
</s:if>

<script type="text/javascript">
	$(document).ready(function(){
		$("#tijiao${pcid }").click(function(){
			var catid = $('#catid').val();
			if(catid == 0){
				$.alert("请选择项目","");
				return;
			}
			var appDateTime =$("#appDateTime").val();
			if(appDateTime == ""){
				$.alert("请您添加预约时间并下滑确认!","");
				return;
			}
			if(formcheck()){
				$.ajax({
	                cache: true,
	                type: "POST",
	               	url:"/${oname}/user/saveYuYueRecord.action", 
	                data:$('#myform${pcid}').serialize(),// 你的formid
	                async: false,
	                error: function(request) {
	                    console.log("Connection error");
	                },
	                success: function(data) {
	                	if(data.status == 0){
	                		$.alert(data.desc,"",function(){
		                		window.history.back();
	                		});
	                	}else{
		                	$.alert(data.desc,"");
	                	}
		                
	                }
	            });	
			}
		});
	});
	
	function formcheck(){
		var inputs = document.getElementsByTagName('input');  
        for(var i=0,len=inputs.length;i<len;i++){  
             if(inputs[i].getAttribute("msg")=="Y"){
             	var name = inputs[i].getAttribute("name");
             	var obj = document.getElementsByName(name);
             	var count = 0;
             	  if(obj != null && obj.length >1){
					   for(var j=0; j<obj.length; j++){
					     if(!obj[j].checked) {
					     	count = count + 1;
					     	if(count == obj.length){
					     		$.alert("请选择一项","");
					     		return false;
					     	}
					     }
					   }
				  }else if(obj.length == 1){
  					if(inputs[i].value=="" || inputs[i].value==null){
  						$.alert(inputs[i].title+"不能为空！","");
						return false;
					}
				  }
			}
		}
		return true;
	}
	function check(object){
		var req = object.getAttribute("msg");
		if(req == 'Y'){
			if(object.value==""){
				$.alert(object.title+"不能为空！","");
			}
		}
	}
			
        $(function () {
        	if("${method}"=="E"){
				return;
			}
			var currYear = (new Date()).getFullYear();	
			var opt={};
			opt.date = {preset : 'date'};
			opt.datetime = {preset : 'datetime'};
			opt.time = {preset : 'time'};
			opt.default = {
				theme: 'android-ics light', //皮肤样式
		        display: 'modal', //显示方式 
		        mode: 'scroller', //日期选择模式
				dateFormat: 'yyyy-mm-dd',
				lang: 'zh',
				showNow: true,
				nowText: "今天",
		        startYear: currYear - 10, //开始年份
		        endYear: currYear + 10 //结束年份
			};

		  	$("#appDate").mobiscroll($.extend(opt['date'], opt['default']));
		  	var optDateTime = $.extend(opt['datetime'], opt['default']);
		  	var optTime = $.extend(opt['time'], opt['default']);
		    $("#appDateTime").mobiscroll(optDateTime).datetime(optDateTime);
		    $("#appTime").mobiscroll(optTime).time(optTime);
        });
    </script>
     
</form>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
