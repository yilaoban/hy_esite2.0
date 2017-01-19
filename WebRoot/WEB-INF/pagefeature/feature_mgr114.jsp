<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {
		var edt=new Array()  
         edt=$("#EDT").find("table").find("tr");
         $("#EDT").append("<h2>"+parseInt(edt.size()-1)+"</h2>");
          cmp=$("#CMP").find("table").find("tr");
         $("#CMP").append("<h2>"+parseInt(cmp.size()-1)+"</h2>");
          del=$("#DEL").find("table").find("tr");
         $("#DEL").append("<h2>"+parseInt(del.size()-1)+"</h2>");
        }); 
</script>
<body class="iframe_body">
<form id="myform">
<div class="frame_content">
	 			<p>选择分享表单:<select name="dto.shareid" onchange="showuploadrecord()">
	 				<option value="0">-请选择-</option>
	 				<s:iterator value="dto.checklist" var="f">
	 					<option value="${f.id }" <s:if test='dto.shareid==id'>selected="selected"</s:if>>${f.name }</option>
	 				</s:iterator>
	 			</select>
	 			状态:<select onchange="changDiv()" id="cs">
	 			<option value="EDT" selected="selected">待审核</option>
	 			<option value="CMP">审核通过</option>
	 			<option value="DEL">审核不通过</option>
	 			</select>
	 			</p>
	 			<div id="EDT">
		 			<table width="100%">
						<tr>
							<th width="10%">微博图片</th>
							<th width="45%">微博内容</th>
							<th width="10%">发布时间</th>
							<th width="5%">转发数量</th>
							<th width="5%">评论数量</th>
							<th width="5%">赞数量</th>
							<th width="20%">审核</th>
						</tr>		 			
		 			<s:iterator value="dto.checkListRecord" var="record" status="st">
		 			<s:if test='status=="EDT"'>
		 				<tr>
		 					<td><a href="http://api.t.sina.com.cn/${record.wbuid }/statuses/${record.wbid }" target="_blank"><img height="40" width="40" src="${imgDomain }${record.imgPath}"/></a></td>
		 					<td>${record.content }</td>
		 					<td><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
		 					<td>${record.repostsCount }</td>
		 					<td>${record.commentsCount }</td>
		 					<td>${record.attitudesCount }</td>
		 					<td>
		 						<label class="forradio"><input type="radio" name="dto.checkListRecord[${st.index }].status" value="CMP"/>通过</label>
		 						<label class="forradio"><input type="radio" name="dto.checkListRecord[${st.index }].status" value="DEL"/>不通过</label>
		 						<input type="hidden" name="dto.checkListRecord[${st.index }].id"  value="${record.id }"/>
		 					</td>
		 				</tr>
		 			</s:if>
		 			</s:iterator>
		 			</table>
	 			</div>
	 			<div id="CMP" style="display: none;">
		 			<table width="100%">
						<tr>
							<th>微博图片</th>
							<th>微博内容</th>
							<th>发布时间</th>
							<th>转发数量</th>
							<th>评论数量</th>
							<th>赞数量</th>
							<th width="20%">操作</th>
						</tr>		 			
		 			<s:iterator value="dto.checkListRecord" var="record" status="st">
		 			<s:if test='status=="CMP"'>
		 				<tr>
							<td><a href="http://api.t.sina.com.cn/${record.wbuid }/statuses/${record.wbid }" target="_blank"><img height="40" width="40" src="${imgDomain }${record.imgPath}"/></a></td>
		 					<td>${record.content }</td>
		 					<td><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
		 					<td>${record.repostsCount }</td>
		 					<td>${record.commentsCount }</td>
		 					<td>${record.attitudesCount }</td>
		 					<td>
		 						<label class="forradio"><input type="radio" name="dto.checkListRecord[${st.index }].status" value="EDT"/>撤销审核</label>
		 						<input type="hidden" name="dto.checkListRecord[${st.index }].id"  value="${record.id }"/>	
		 					</td>		 				
		 				</tr>
		 			</s:if>
		 			</s:iterator>
		 			</table>
	 			</div>
	 			<div id="DEL"  style="display: none;">
		 			<table width="100%">
						<tr>
							<th>微博图片</th>
							<th>微博内容</th>
							<th>发布时间</th>
							<th>转发数量</th>
							<th>评论数量</th>
							<th>赞数量</th>
							<th width="20%">操作</th>
						</tr>		 			
		 			<s:iterator value="dto.checkListRecord" var="record" status="st">
		 			<s:if test='status=="DEL"'>
		 				<tr>
		 					<td><a href="http://api.t.sina.com.cn/${record.wbuid }/statuses/${record.wbid }" target="_blank"><img height="40" width="40" src="${imgDomain }${record.imgPath}"/></a></td>
		 					<td>${record.content }</td>
		 					<td><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
		 					<td>${record.repostsCount }</td>
		 					<td>${record.commentsCount }</td>
		 					<td>${record.attitudesCount }</td>
		 					<td>
		 						<label class="forradio"><input type="radio" name="dto.checkListRecord[${st.index }].status" value="EDT"/>撤销</label>
		 						<input type="hidden" name="dto.checkListRecord[${st.index }].id"  value="${record.id }"/>	
		 					</td>	
		 				</tr>
		 			</s:if>
		 			</s:iterator>
		 			</table>
	 			</div>
	 			<input type="button" value="确定" id="save114"/>
	 			<input type="button" value="取消" onclick="closeFrame()"/>
</div>
</form>
</body>
<script type="text/javascript">
		$("#save114").click(function(){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:"/${oname}/page/config_sub.action?featureid=${featureid}",
		        data:$('#myform').serialize(),
		        async: false,
		        success: function(data) {
		        	if(data == "Y"){
						layer.load(2);
						setTimeout('layer.closeAll("loading");parent.layer.msg("操作成功!",{icon: 6, time: 1500},function(){closeFrame()})',2000);
		        	}else{
						parent.layer.msg("保存失败！请重试！",{icon: 6, time: 1500},3);
		        	}
		        }
		    });
		});
</script>