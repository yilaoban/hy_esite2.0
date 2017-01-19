<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content left_module">
	 	<input name="record.formid" value="${dto.contentform.id }" type="hidden"/>
	 	<input name="ccid" value="${dto.current.id }" type="hidden"/>
		<s:iterator value="dto.colum" var="p">
				<dl>
				<dt>${p.name }(${p.mapping })</dt>
				<s:if test='#p.stype == "T"'>
					<dd>
						<input type="text" readonly="readonly" value="${p.record }"/>
					</dd>
				</s:if>
				<s:elseif test='#p.stype == "R"'>
					<dd>
						<s:iterator value="#p.defaultList" var="f" status="st">
							<label class="select"><input type="radio" value="${f}" <s:if test="#f==#p.record"> checked="checked"</s:if>  />${f}</label>
						</s:iterator>
					</dd>
				</s:elseif>
				<s:elseif test='#p.stype == "D"'>
					<dd>
						<input type="text"  class="Wdate" name="record.${p.mapping }" value="${p.defaultvalue}" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
					</dd>
				</s:elseif>
				<s:elseif test='#p.stype == "C"'>
					<dd>
					<s:iterator value="#p.defaultList" var="f" status="st">
						<label class="select"><input type="checkbox" <s:if test="#f==#p.record">checked="checked"</s:if> value="${f}"/>${f}</label>
					</s:iterator>
					</dd>
				</s:elseif>
				<s:elseif test='#p.stype == "S"'>
					<dd>
					<select name="record.${p.mapping }">
						<s:iterator value="#p.defaultList" var="f">
						  <option value ="${f}" <s:if test="#f==#p.record">selected="selected"</s:if>>${f}</option>
						</s:iterator>  
					</select>
					</dd>
				</s:elseif>
				<s:elseif test='#p.stype == "A"'>
					<dd>
						<textarea rows="" cols="">${p.record }</textarea>
					</dd>
				</s:elseif>
				<s:elseif test='#p.stype == "P"'>
					<dd>
						<input type="text" readonly="readonly" value="${p.record }"/>
					</dd>
				</s:elseif>
				<s:elseif test='#p.stype == "B"'>
					<dd>
						<s:if test='#p.record != null&&#p.record!="" '><img src="${imgDomain }${p.record }" height="40" width="40"></s:if><s:else><img src="/images/nopic.png" height="40" width="40"></s:else>
					</dd>
				</s:elseif>
				</dl>
		</s:iterator>
			<dl>
				<dt>&nbsp;</dt>
				<dd>
					<input type="button" value="关闭" onclick="closeFrame()"/>
				</dd>
			</dl>
</div>