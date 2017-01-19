<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li><s:if test='type=="l"'></s:if><a href="/${oname }/interact/exam_record_list.action?searchid=${searchid}&mid=${mid }">微博数据</a></li>
	  <li class="selected"><s:if test='type=="w"'></s:if><a href="/${oname }/interact/wx_exam_record_list.action?searchid=${searchid}&mid=${mid }">微信数据</a></li>
	  <li><s:if test='type=="c"'></s:if><a href="/${oname }/interact/exam_record_data.action?searchid=${searchid}&mid=${mid }">测评结果</a></li>
	  </ul>
	  <a href="/${oname }/interact/index.action?mid=10015" class="return"></a>
	</div>
   <div>
        <form id="nickname" name="nickname" method="get" action="wx_exam_record_list_nickname.action">
		 <input type="hidden" name="searchid" value="${searchid}">
		 <input name="nickname" value="${nickname}">
		 <input type="hidden" name="mid" value="${mid }">
		 <input type="submit" value="搜索" />
		 </form>
		 <form action="/interact/wx_exam_record_list_source.action" method="post">
		 <input type="hidden" name="searchid" value="${searchid}">
		 <input type="hidden" name="mid" value="${mid }">
		 来源：
		 <select name="source">
			<s:iterator value="dto.sourceList" var="f">
			  <option value ="${f.source}">${f.source}</option>
			</s:iterator>  
		 </select>
		 <input type="submit" value="搜索" />
		 </form>
   </div>
	<div>
    共${dto.pager.totalCount }条
		<table class="tb_normal" width="100%" border="1" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>
					ip地址
				</th>
				<th>
					来源
				</th>
				<th>
					测评时间
				</th>
				<th>
					测评结果
				</th>
			</tr>
			<s:iterator value="dto.list" var="l" status="s">
				<tr>
					<td align="center">
						${l.ip }
					</td>
					<td align="center">
						微信<s:if test="nickname!=null">(${l.nickname })</s:if>
					</td>
					<td align="center">
						<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"></s:date>
					</td>
					<td align="center">
						<a href="/${oname }/interact/examresult_list.action?recordid=${l.id}"  >查看全部问题</a>
					</td>
				</tr>
			</s:iterator>
		</table>
		<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
	</div>
</div>