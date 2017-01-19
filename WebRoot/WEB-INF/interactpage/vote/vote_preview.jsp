<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div style="margin:0 auto;width:320px;">
<form action="/user/vote.action" method="post" id="myform">
<ul>
<li>投票标题：${vote.title }</li>
<li>投票说明：${vote.content}</li>
<li>开始时间：<s:date name="vote.starttime" format="yyyy-MM-dd HH:mm"/></li>
<li>结束时间：<s:date name="vote.endtime" format="yyyy-MM-dd HH:mm"/></li>
<li>选项:</li>
<s:iterator value="vote.options" var="o" status="st">
<li>
<label class="select">
<s:if test='vote.type=="C"'><input type="checkbox" name="cho" value="${o.id }"/></s:if>
<s:if test='vote.type=="R"'><input type="radio" <s:if test='#st.index==0'>checked="checked"</s:if> name="cho" value="${o.id }"/></s:if>
${o.content }
<s:if test='#o.pic!=null && #o.pic !=""'><img src="${imgDomain}${o.pic}" width="100" height="80"></s:if>
</label>
</li>
</s:iterator>
</ul>
<input type="hidden" name="voteid" value="${vote.id }"/>
<input type="hidden" name="wbuid" value="${wbuid}"/>
<input type="hidden" name="pageid" value="${pageid}"/>
<input type="button" onclick="sub()" value="提交">
<input type="button" onclick="window.close();" value="关闭">
</form>
</div>
<script language="javascript">
		function sub(){
					layer.alert("投票成功!",1);
			}
</script>