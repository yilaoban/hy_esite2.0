<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<div class="toolbar pb10">
  <a href="/${oname }/cd-lottery/save_yyy.action" class="btn btn-primary">新增摇一摇</a>
  <a href="/${oname }/cd-lottery/save_lhj.action?htype=L" class="btn btn-primary">新增砸金蛋</a>
   <a href="/${oname }/cd-lottery/save_hongbao.action?htype=H" class="btn btn-primary">新增抢红包</a>
</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>
					标题
				</th>
				<th>
					抽奖类型
				</th>
				<th>
					开始时间/结束时间
				</th>
				<th>
					中奖率(‱)
				</th>
				<th>
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.list" var="s">
				<tr align="center">
					<td align="center">
						${s.name }
					</td>
					<td align="center">
						<s:if test='#s.type == "Y"'>
							摇一摇
						</s:if>
						<s:elseif test='#s.type == "L"'>
							砸金蛋
						</s:elseif>
						<s:elseif test='#s.type == "H"'>
							抢红包
						</s:elseif>
					</td>
					<td align="center">
						<s:date name="starttime" format="yyyy-MM-dd HH:mm" />
						/
						<s:date name="endtime" format="yyyy-MM-dd HH:mm" />
					</td>
					<td align="center">
						${s.zjl }
					</td>
					<td align="center">
						<s:if test='#s.type == "Y"'>
							<a href="edit_yyy.action?lid=${s.id }&htype=Y">编辑</a><i class="split">|</i>
						</s:if>
						<s:elseif test='#s.type == "L"'>
							<a href="edit_lhj.action?lid=${s.id }&htype=L">编辑</a><i class="split">|</i>
						</s:elseif>
						<s:elseif test='#s.type == "H"'>
							<a href="edit_hongbao.action?lid=${s.id }&htype=H">编辑</a><i class="split">|</i>
						</s:elseif>
						<a href="prize.action?lid=${s.id }&returnType=${s.type}">奖品</a><i class="split">|</i>
						<a href="lottery_user_sina.action?lid=${s.id }&type=${s.type}">数据</a><i class="split">|</i>
						<a href="javascript:ldel(${s.id })">删除</a><i class="split">|</i>
						<a href="javascript:lotteryclean('${oname }','${s.id }')">清空</a><i class="split">|</i>
						<a href="javascript:lotteryUp(${s.id },'${s.name }')">上移</a><i class="split">|</i>
						<a href="javascript:lotteryDown(${s.id },'${s.name }')">下移</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<script type="text/javascript">
function lotteryUp(id,name){
	var layerid=layer.confirm('确定将抽奖['+name+']上移吗?',{title:"上移"},function(){
		$.post("/${oname}/cd-lottery/lotteryUp.action",{inajax:1,lid:id},function(data){
		if(data==0){
			layer.msg('上移失败！已经是最前了……', {icon: 5, time: 2000});
		}else{
			layer.msg('上移成功！', {icon: 6, time: 1500}, function(){
					window.location.reload();
			}); 
		}
		});
	});
}

function lotteryDown(id,name){
	var layerid=layer.confirm('确定将抽奖['+name+']下移吗?',{title:"下移"},function(){
		$.post("/${oname}/cd-lottery/lotteryDown.action",{inajax:1,lid:id},function(data){
		if(data==0){
			layer.msg('下移失败！已经是最后了……', {icon: 5, time: 2000});
		}else{
			layer.msg('下移成功！', {icon: 6, time: 1500}, function(){
					window.location.reload();
			}); 
		}
		});
	});
}
</script>
