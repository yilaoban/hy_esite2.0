<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div style="margin:0 auto;width:320px;">
<form action="/user/research.action" method="post" id="myform" class="preview">
<ul>
<li>调研标题：${research.title }</li>
<li>调研说明：${research.content}</li>
<li>开始时间：<s:date name="research.starttime" format="yyyy-MM-dd HH:mm"/></li>
<li>结束时间：<s:date name="research.endtime" format="yyyy-MM-dd HH:mm"/></li>
<li>问题:</li>
<s:iterator value="research.questions" var="q" status="st">
<dl>
<dt>${st.count})${q.title }</dt>
<s:if test='#q.type=="SGL"'>
	<dd>
	<s:iterator value="#q.options" var="o" status="s">
		<label class="select"><input type="radio" name="dto.xz_List" value="${q.id},${o.id}" <s:if test='#s.index==0'>checked="checked"</s:if>  />${o.content }</label>
	</s:iterator>
	</dd>
</s:if>
<s:if test='#q.type=="MUP"'>
	<dd>
	<s:iterator value="#q.options" var="o" status="st2">
		<label class="select"><input type="checkbox" name="dto.xz_List" value="${q.id},${o.id}">${o.content }</label>
	</s:iterator>
	</dd>
</s:if>
<s:if test='#q.type=="FIL"'>
		<dd><input type="text" class="text-long" name="dto.tk_answer" /><input type="hidden" name="dto.tk_qid"/></dd>
</s:if>
<s:if test='#q.type=="GAD"'>
	<dd><input type="text" class="text-long"></dd>
	<s:iterator value="#q.options" var="o">
		<dd><input type="text" class="text-long"></dd>
	</s:iterator>
</s:if>
<s:if test='#q.type=="ORD"'>
	<s:iterator value="#q.options" var="o">
		<dd>${o.content}</dd>
		<dd><input type="text" class="text-long"></dd>
	</s:iterator>
</s:if>
</dl>
</s:iterator>
<input type="hidden" name="dto.researchid" value="${research.id }"/>
<input type="hidden" name="dto.wbuid" value=""/>
<input type="hidden" name="pageid" value="${pageid }"/>
<input type="hidden" name="source" value=""/>
<input type="button" onclick="sub()" value="提交">
<input type="button" onclick="window.close()" value="关闭">
</form>
</div>
<script language="javascript">
		function sub(){
					layer.alert("提交成功!",1);
			}
</script>