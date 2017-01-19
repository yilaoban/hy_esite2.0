<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content left_module">
	<div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="#">调研结果</a></li>
	  </ul>
	  <a class="return" href="/${oname }/interact/research_record_list.action?searchid=${searchid }&mid=10006&mtype=${mtype}"></a>
	</div>
	<div id="questioncontent">
		<ul>
		<s:iterator value="dto.researchVO" var="r" >
			<li>
				<dl>
					<s:if test='type=="SGL"'>
						<dt>单选题</dt>
						<dd>${r.title }</dd>
						<dd>答:${r.content }</dd>
					</s:if>
					<s:elseif test='type=="MUP"'>
						<dt>多选题</dt>
						<dd>${r.title }</dd>
						<dd>答:${r.content }</dd>
					</s:elseif>
					<s:elseif test='type=="FIL"'>
						<dt>填空题</dt>
						<dd>${r.title }</dd>
						<dd>答:${r.answer }</dd>
					</s:elseif>
					<s:elseif test='type=="GAD"'>
						<dt>打分题</dt>
						<dd>${r.title }</dd>
						<dd>答:${r.answer }</dd>
					</s:elseif>
					<s:elseif test='type=="ORD"'>
						<dt>排序题</dt>
						<dd>${r.title }</dd>
						<dd>排序为:</dd>
						<dd>${r.content }</dd>
					</s:elseif>
					
				</dl>
			</li>
		</s:iterator>
		</ul>
	</div>




		
</div>