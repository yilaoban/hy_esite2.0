<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<link href="/js/icheck/skins/all.css" rel="stylesheet">
<script src="/js/icheck/icheck.min.js"></script>
<form id="mbform">
	<input type="hidden" name="mbid" value="${mbid }">
	<input type="hidden" name="stype" value="${stype }">
<s:if test="mbid > 0">
		<dl>
		 	<dd>
		 		<input type="radio" name="type" id="type1" value="A" checked="checked"><label for="type1"><s:if test='stype=="E"'>新活动</s:if><s:else>新网站</s:else></label>
				<input type="radio" name="type" id="type2" value="B"><label for="type2">已有<s:if test='stype=="E"'>活动</s:if><s:else>网站</s:else></label>
			</dd>
		</dl>
		<div class="sA input-group">
			<input type="text" id="sname" name="name" class="form-control" placeholder="新<s:if test='stype==\"E\"'>活动</s:if><s:else>网站</s:else>名称" aria-describedby="basic-addon3">
		</div>
		<dl class="sB" style="display: none">
		 	<dd>选择<s:if test='stype=="E"'>活动</s:if><s:else>网站</s:else>:
		 	<select class="form-control" name="groupid">
				  <s:iterator value="sitegroup" var="s">
				  	<option value="${s.id }">${s.groupname }</option>
				  </s:iterator>
				</select>
			</dd>
		</dl>
		<nav>
		  <ul class="pager">
		    <li><a href="#" class="tijiao">提交</a></li>
		  </ul>
		</nav>
</s:if>
<s:else>
		<dl>
		 	<dd>
		 		<label>新<s:if test='stype=="E"'>活动</s:if><s:else>网站</s:else></label>
			</dd>
		</dl>
		<div class="sA input-group">
			<input type="text" id="sname" name="name" class="form-control" placeholder="新<s:if test='stype==\"E\"'>活动</s:if><s:else>网站</s:else>名称" aria-describedby="basic-addon3">
		</div>
		<nav>
		  <ul class="pager">
		    <li><a href="#" class="tijiao">提交</a></li>
		  </ul>
		</nav>
</s:else>
</form>
<script type="text/javascript">
$(document).ready(function(){
	 $('#mbform input').iCheck({
	    checkboxClass: 'icheckbox_minimal-red',
	    radioClass: 'iradio_minimal-red',
	    increaseArea: '20%'// optional
	  });
	 $('#mbform input').on('ifChecked', function(event){
		  $(".sA,.sB").hide();
		  $(".s"+$(this).val()).show();
		});
	
	$(".tijiao").click(function(){
		if( ('${mbid}'== 0 && $("#sname").val()=="") || ($("input[name='type']:checked").val()=="A" && $("#sname").val()=="")){
			layer.msg('站点名称不能为空!',{time: 2000});
			return;
		}
		$.ajax({
            cache: true,
            type: "POST",
            url:"usembsub.action",
            data:$('#mbform').serialize(),// 你的formid
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
            	if(data > 0){
            		layer.msg('创建成功!',{icon: 6, time: 2000},function(){
                		top.window.location.href="/${oname}/page/pageconfig_new.action?siteid="+data+"&stype=P";
            		});
            	}else if(data==-1){
    				layer.msg('此站点已存在！请重新输入',{ time: 2000});
    			}else{
    				layer.msg('创建失败！',{ time: 2000});
    			}
            }
        });	
		
	});
});
</script>