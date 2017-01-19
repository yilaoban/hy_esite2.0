<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content">
	<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
			<thead>
				<tr>
					<th>
						标题
					</th>
					<th>
						物料数
					</th>
					<th>
						开始时间
					</th>
					<th>
						结束时间
					</th>
					<th>
						状态
					</th>
					<th>
						操作
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="dto.activity" var="f">
					<tr align="center">
						<td>
							${f.title}
						</td>
						<td>
							${f.sucainum }
						</td>
						<td>
							<s:date name="starttime" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<s:date name="endtime" format="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<s:if test="#f.status == 0">未发布</s:if>
							<s:elseif test="#f.status == 1">已发布</s:elseif>
						</td>
						<td>
							<a href="/${oname }/interact/senderEffect.action?cbaid=${f.id}">分享效果</a>
							<a href="/${oname }/interact/jiliConfirm.action?cbaid=${f.id}">激励确认</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>		
	</div>
