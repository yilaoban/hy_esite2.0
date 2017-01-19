<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	function jiconfirm(impid){
		layer.confirm('是否确认此激励？', {
		    btn: ['确定','取消'], //按钮
		    title:"激励确认"
		}, function(){
			$.post("/${oname}/interact/jiliSub.action",{"impelId":impid},function(data){
				if(data==-1){
					layer.msg('余额不足!',2,0);
				}else if(data==1){
					layer.msg('操作成功~', {icon: 6, time: 1500},function(data){
						$("#tr_"+impid).remove();
					}); 
				}
			});
		});
	}
	$(document).ready(function(){
		$("#tc").click(function(){
			for(var i=2;i<=11;i++){
				$("#myform tr td:nth-child("+i+")").toggle();
			}
			if($("#myform tr td:nth-child(2)").is(":visible")){
				$(this).text("查看次数");
			}else{
				$(this).text("查看激励");
			}
		});
	});
</script>
<div class="wrap_content">
  <ul class="c_switch clearfix">
	  <li class="selected"><a href="#">活动:${dto.current.title }</a></li>
	  	<a href="/${oname }/interact/cbActivity.action" class="return" title="返回"></a>
  </ul>
	<div class="toolbar pb10">
		活动金额:<s:property value="fenToyuan(dto.hbtotal)"/>元;已用金额:<s:property value="fenToyuan(dto.hbused)"/>元;剩余金额:<s:property value="fenToyuan(dto.hbtotal-dto.hbused)"/>元
		<form class="formview jNice left">
			<input type="hidden" name="cbaid" value="${cbaid }"/>
			<input type="radio" name="status" value="0"  onclick="this.form.submit()" <s:if test='status=="0"'>checked="checked"</s:if>/>未确认
			<input type="radio" name="status" value="1"  onclick="this.form.submit()" <s:if test='status=="1"'>checked="checked"</s:if>/>已确认
		</form>
	</div>
	<table width="100%"  class="tb_normal" id="myform" border="1" cellspacing="1" cellpadding="1" >
		<thead>
			<tr>
				<td>姓名</td>
				<td>日期</td>
				<td>转发激励</td>
				<td>点击激励</td>
				<td>互动激励</td>
				<td>关注激励</td>
				<td>外部激励</td>
				<td>总金额</td>
				<s:if test="status==0">
					<td>操作</td>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.senderImpel" var="s">
			<tr id="tr_${s.id }">
				<td>${s.name }</td>
				<td><s:date name="dataday" format="yyyy-MM-dd"/></td>
				<td>${s.zhuanfanum }次/<s:property value="fenToyuan(#s.zhuanfa)"/>元</td>
				<td>${s.clicknum }次/<s:property value="fenToyuan(#s.click)"/>元</td>
				<td>${s.hudongnum }次/<s:property value="fenToyuan(#s.hudong)"/>元</td>
				<td>${s.guanzhunum }次/<s:property value="fenToyuan(#s.guanzhu)"/>元</td>
				<td>${s.waibu }次/<s:property value="fenToyuan(#s.waibu)"/>元</td>
				<td><s:property value="fenToyuan(#s.total)"/>(元)</td>
				<s:if test="status==0">
					<td><a href="javascript:jiconfirm('${s.id}')">确认</a></td>
				</s:if>
			</tr>
			</s:iterator>
		</tbody>
	</table>
</div>