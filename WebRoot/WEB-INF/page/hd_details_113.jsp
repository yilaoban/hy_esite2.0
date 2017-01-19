<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">
<form>
		<input type="hidden" name="featureid" value="${featureid }"/>
		<input type="hidden" name="sitegroupid" value="${sitegroupid }"/>
		分享时间段：
		<input type="text" name="siftDto.starttime" id="starttime" class="Wdate"	 onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',dateFmt:'yyyy-MM-dd 00:00:00',onpicked:function(){shareEndTime.focus();},autoPickDate:true})"  readonly="readonly" value="${ siftDto.starttime}"/>
						至
		<input type="text" name="siftDto.endtime" id="endtime"	class="Wdate"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',dateFmt:'yyyy-MM-dd 23:59:59',autoPickDate:true})"  readonly="readonly" value="${siftDto.endtime }"/>
		审核状态：<select name="siftDto.wbstatus">
				<option value="ALL" <s:if test='siftDto.wbstatus=="A"'>selected="selected"</s:if>>全部</option>
				<option value="CMP" <s:if test='siftDto.wbstatus=="CMP"'>selected="selected"</s:if>>审核通过</option>
				<option value="EDT" <s:if test='siftDto.wbstatus=="A"'>selected="selected"</s:if>>待审核</option>
				<option value="DEL" <s:if test='siftDto.wbstatus=="A"'>selected="selected"</s:if>>审核不通过</option>
			</select>
		终端：<select name="siftDto.terminal">
				<option value="-1" <s:if test='siftDto.terminal=="-1"'>selected="selected"</s:if>>全部</option>
				<option value="C" <s:if test='siftDto.terminal=="C"'>selected="selected"</s:if>>PC</option>
				<option value="A" <s:if test='siftDto.terminal=="A"'>selected="selected"</s:if>>PAD</option>
				<option value="P" <s:if test='siftDto.terminal=="P"'>selected="selected"</s:if>>PHONE</option>
			</select>
		排序:<select name="siftDto.orderType">
				<option value="-1" <s:if test='siftDto.orderType=="-1"'>selected="selected"</s:if>>默认</option>
				<option value="attitudesCount" <s:if test='siftDto.orderType=="attitudesCount"'>selected="selected"</s:if>>赞</option>
				<option value="commentsCount" <s:if test='siftDto.orderType=="commentsCount"'>selected="selected"</s:if>>评论</option>
				<option value="repostsCount" <s:if test='siftDto.orderType=="repostsCount"'>selected="selected"</s:if>>转发</option>
			</select>
		<input type="submit" value="搜索"/>
	</form>
<s:if test="dto.sinashare.size>0">
	<ul class="share-list">
			<s:iterator value="dto.sinashare" var="st">
				<li class="line">
					<div class='wb_face'><img src="${st.user.imageurl }" width='50px'/></div>
					<div class="wb_detail">
						<div class="wb_text">
							<a href="www.weibo.com/u/${st.user.wbuid }" target="_blank">${st.user.nickname }</a>：${st.content }
						</div>
						<div class="wb_media">
								<img src="${imgDomain }${st.imgPath }" height="80px"/>
						</div>
						<div style="text-align:right;">
							<span class="left"><s:date name="#st.createtime" format="yyyy-MM-dd HH:mm:ss"/></span> 转发：${st.repostsCount },评论：${st.commentsCount },赞：${st.attitudesCount }
						</div>
					</div>
				</li>
			</s:iterator>
	</ul>
	<%@include file="/WEB-INF/pagechip/pageBar2.jsp" %>
</s:if>
<s:else>
	没有记录!
</s:else>
</div>