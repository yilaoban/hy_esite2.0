<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
	<form>
	服务人员
		<select name="serid" onchange="this.form.submit()">
			<option value="0">不限</option>
			<s:iterator value="dto.list" var="p">
			<option value="${p.id }" <s:if test="#p.id==serid">selected="selected"</s:if>>${p.name }</option>
			</s:iterator>
		</select> 
	</form>
	<s:iterator value="dto.pjList" var="p">
		<div class="comment">
			<div class="comment_portrait">
				<img src="${p.wxUser.headimgurl }"/>
			</div>
			<div class="comment_customer">
				<span>${p.wxUser.nickname }</span><s:date name="#p.createtime"/>
			</div>
			
			<div class="comment_content">
				<div>服务人员：${p.yuYueServicer.name }</div>
                <s:iterator value="#p.wdList" var="w">
				<div>${w.name}：
					<span class="customer_stars">
					<s:bean name="org.apache.struts2.util.Counter" id="counter">
	        			<s:param name="first" value="1"/>
	       				<s:param name="last" value="#w.level"/>
	        			<s:iterator>
	        				<span class="y_star"></span>
	        			</s:iterator>
 					</s:bean>
 					<s:bean name="org.apache.struts2.util.Counter" id="counter">
	        			<s:param name="first" value="1"/>
	       				<s:param name="last" value="5-#w.level"/>
	        			<s:iterator>
	        				<span class="y_star_b"></span>
	        			</s:iterator>
 					</s:bean>
	        		</span>
				</div>
                </s:iterator>
				<div>
					评价内容：${p.content }
				</div>
				<div style="border-top:1px solid #ddd;">
					商家回复：${p.dzcontent }
				</div>
			</div>
		</div>
	</s:iterator>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>