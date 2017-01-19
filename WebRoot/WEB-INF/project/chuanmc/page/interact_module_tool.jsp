<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test="redirectXc">
	<!-- 微现场跳转过来的页面 tool用的是站点 -->
	<div id="tab">
		<ul>
			<li class="select"><a href="/${oname }/interact/edit_xcLottery.action?xcid=${redirectXc }">基础设置</a></li>		
		</ul>
	</div>
	<div class="wrap_content left_module">
</s:if>
<s:else>
	<div id="tab">
		<ul>
			<s:if test="mid==10000">
				<li class="select"><a href="javascript:void(0)">表单</a></li>
			</s:if>
			<s:elseif test="mid==10002">
				<li class="select"><a href="javascript:void(0)">投票</a></li>
			</s:elseif>
			<s:elseif test="mid==10003">
				<li class="select"><a href="javascript:void(0)">大转盘</a></li>
			</s:elseif>
			<s:elseif test="mid==10004">
				<li class="select"><a href="javascript:void(0)">砸金蛋</a></li>
			</s:elseif>
			<s:elseif test="mid==10006">
				<li class="select"><a href="javascript:void(0)">调研</a></li>
			</s:elseif>
			<s:elseif test="mid==10011">
				<li class="select"><a href="javascript:void(0)">摇一摇</a></li>
			</s:elseif>
			<s:elseif test="mid==10015">
				<li class="select"><a href="javascript:void(0)">测评</a></li>
			</s:elseif>
			<s:elseif test="mid==10016">
				<li class="select"><a href="javascript:void(0)">九宫格</a></li>
			</s:elseif>
			<div class="dropdown right" style="margin-top:10px;margin-right:15px;">
			 <a id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" href="javascript:void(0);">
			   	<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>
			   	<s:if test="mid==10000">表单</s:if>
				<s:elseif test="mid==10002">投票</s:elseif>
				<s:elseif test="mid==10003">大转盘</s:elseif>
				<s:elseif test="mid==10004">砸金蛋</s:elseif>
				<s:elseif test="mid==10006">调研</s:elseif>
				<s:elseif test="mid==10011">摇一摇</s:elseif>
				<s:elseif test="mid==10015">测评</s:elseif>
				<s:elseif test="mid==10016">九宫格</s:elseif>
			   	<span class="caret"></span>
			 </a>
			 <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu1" style="min-width:100px;">
				<li style="background:#fff;border:0px;border-radius:0px;height: 30px;margin: 0px;padding:0px;">
					<a href="/${oname }/interact/index.action?mid=10000">表单</a>
				</li>
				<li style="background:#fff;border:0px;border-radius:0px;height: 30px;margin: 0px;padding:0px;">
					<a href="/${oname }/interact/index.action?mid=10002">投票</a>
				</li>
				<li style="background:#fff;border:0px;border-radius:0px;height: 30px;margin: 0px;padding:0px;">
					<a href="/${oname }/interact/index.action?mid=10003">大转盘</a>
				</li>
				<li style="background:#fff;border:0px;border-radius:0px;height: 30px;margin: 0px;padding:0px;">
					<a href="/${oname }/interact/index.action?mid=10004">砸金蛋</a>
				</li>
				<li style="background:#fff;border:0px;border-radius:0px;height: 30px;margin: 0px;padding:0px;">
					<a href="/${oname }/interact/index.action?mid=10006">调研</a>
				</li>
				<li style="background:#fff;border:0px;border-radius:0px;height: 30px;margin: 0px;padding:0px;">
					<a href="/${oname }/interact/index.action?mid=10011">摇一摇</a>
				</li>
				<li style="background:#fff;border:0px;border-radius:0px;height: 30px;margin: 0px;padding:0px;">
					<a href="/${oname }/interact/index.action?mid=10015">评测</a>
				</li>
				<li style="background:#fff;border:0px;border-radius:0px;height: 30px;margin: 0px;padding:0px;">
					<a href="/${oname }/interact/index.action?mid=10016">九宫格</a>
				</li>
			  </ul>
			</div>
		</ul>
	</div>
	<div class="wrap_content left_module">
</s:else>