<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
	  <div class="toolbar pb10">
	  	<ul class="c_switch">
		  <li class="selected"><a href="">添加积分规则</a></li>
		  </ul>
		  <a href="javascript:window.history.back()" class="return" title="返回"></a>
		</div>
	  <form action="addRuleSub.action" method="post" enctype="multipart/form-data" id="form1" class="formview jNice">
	  	<input type="hidden" name="ownerwbuid" value="${ownerwbuid }"/>
		<dl>
		 	<dt>积分模块</dt>
			<dd class="pt10">
	  		<s:if test='dto.set.type!=null'>
	  			<input type="hidden" name="type" value="${dto.set.type }">
	  			<s:if test='dto.set.type=="GUZ"'><input type="text" disabled="disabled" value="关注"/></s:if>
				<s:elseif test='dto.set.type=="FNX"'><input type="text" disabled="disabled" value="分享"/></s:elseif>
				<s:elseif test='dto.set.type=="ZHF"'><input type="text" disabled="disabled" value="转发"/></s:elseif>
				<s:elseif test='dto.set.type=="COM"'><input type="text" disabled="disabled" value="评论"/></s:elseif>
				<s:elseif test='dto.set.type=="CHN"'><input type="text" disabled="disabled" value="签到"/></s:elseif>
				<s:elseif test='dto.set.type=="ZAN"'><input type="text" disabled="disabled" value="赞"/></s:elseif>
				<s:elseif test='dto.set.type=="SUB"'><input type="text" disabled="disabled" value="订阅"/></s:elseif>
	  		</s:if>
	  		<s:else>
		  		<select name="type">
		  			<option value="GUZ" <s:if test='dto.set.type=="GUZ"'>selected="selected"</s:if>>关注</option>
		  			<option value="FNX" <s:if test='dto.set.type=="FNX"'>selected="selected"</s:if>>分享</option>
		  			<option value="ZHF" <s:if test='dto.set.type=="ZHF"'>selected="selected"</s:if>>转发</option>
		  			<option value="COM" <s:if test='dto.set.type=="COM"'>selected="selected"</s:if>>评论</option>
		  			<!-- <option value="CHN" <s:if test='dto.set.type=="CHN"'>selected="selected"</s:if>>签到</option>-->
		  			<option value="ZAN" <s:if test='dto.set.type=="ZAN"'>selected="selected"</s:if>>赞</option>
		  			<option value="SUB" <s:if test='dto.set.type=="SUB"'>selected="selected"</s:if>>订阅</option>
		  		</select>
	  		</s:else>
			</dd>
		</dl>
		<dl>
		 	<dt>获得积分</dt>
			<dd>
				<input type="text" class="text-medium" name="addnum" value="${dto.set.addnum }" onkeyup="this.value = this.value.replace(/\D/g,'');">
			</dd>
		</dl>
		<dl>
		 	<dt>描述</dt>
			<dd>
				<textarea rows="" cols="" name="hydesc">${dto.set.hydesc }</textarea>
			</dd>
			<dd>
				<span class="notice">积分模块在同一微博下唯一,当新增已存在积分模块的规则时,会替换掉原有规则.</span>
			</dd>
		</dl>
		<dl>
		 	<dt></dt>
			<dd>
			 	<input type="submit" value="提交">
			</dd>
		</dl>
	 </form>
 </div>