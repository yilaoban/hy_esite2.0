<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function microRecordChecked(id){
		var srcString = '/${oname}/bbs/microRecordChecked.action?crid=' + id;
		$('#wideModal').removeData("bs.modal");
		$('#wideModal').modal({
			remote:srcString
		});
	}
	function microRecordDetail(id,uid){
		var srcString = '/${oname}/bbs/microRecordDetail.action?crid=' + id + '&uid=' + uid + '&type=${type}&utype=${utype}';
		$('#wideModal2').removeData("bs.modal");
		$('#wideModal2').modal({
			remote:srcString
		});
	}
</script>
<div class="wrap_content">
			<div class="toolbar pb10">
				<ul class="c_switch">
					<li <s:if test='type == "N"'>class="selected"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/microRecord.action?type=N">新闻</a></li>
					<li <s:if test='type == "F"'>class="selected"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/microRecord.action?type=F">表单</a></li>
					<li <s:if test='type == "V"'>class="selected"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/microRecord.action?type=V">视频</a></li>
					<li <s:if test='type == "P"'>class="selected"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/microRecord.action?type=P">产品</a></li>
					<li <s:if test='type == "M"'>class="selected"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/microRecord.action?type=M">图片</a></li>
				</ul>
			</div>
			<div>
				<ul class="c_switch">
					<li <s:if test='utype == 0'>class="selected"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/microRecord.action?type=${type}&utype=0">微博</a></li>
					<li <s:if test='utype == 1'>class="selected"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/microRecord.action?type=${type}&utype=1">微信</a></li>
				</ul>
				<form action="/${oname}/bbs/microRecord.action" method="post">
					<input type="hidden" name="type" value="${type }"> 
					<input type="hidden" name="utype" value="${utype }"> 
					<input type="hidden" name="uid" value="${uid }"> 
					<input type="text" name="title" value="${title }">
					<input type="submit" value="搜索">
				</form>
				<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
					<thead>
						<tr>
							<th>
								标题
							</th>
							<th>
								昵称	
							</th>
							<th>
								描述
							</th>
							<th>
								创建时间
							</th>
							<th>
								操作
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="dto.contentRecordList" var="f">
							<tr align="center">
								<td>
									${f.title}
								</td>
								<td>
									 ${f.nickname }
								</td>
								<td>
									${f.hydesc }
								</td>
								<td>
									<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
							      	<a href="javascript:void(0)" onclick="microRecordDetail(${f.id},${f.uid})">详细信息</a><i class="split">|</i>
							      	<a href="javascript:void(0)" onclick="microRecordChecked(${f.id})">审核</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
 <div id="wideModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width:400px;">
    <div class="modal-content">
    </div>
  </div>
</div>
<div id="wideModal2" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width:400px;">
    <div class="modal-content">
    </div>
  </div>
</div>