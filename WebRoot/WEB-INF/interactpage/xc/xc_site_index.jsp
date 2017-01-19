<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<div class="switch_tab_div pb10">
	<span><a href="/${oname }/page/xcSiteGroup.action">我的现场</a><i class="gt">&gt;&gt;</i> ${dto.sg.groupname }</span>
	<p class="clearfix"></p>
</div>
<div class="bigbtn-toobar">
	<div class="btn-round"><a href="/${oname }/interact/edit_xcLottery.action?xcid=${xcid }"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span></a><a href="/${oname }/interact/edit_xcLottery.action?xcid=${xcid }">基础设置</a></div>
	<div class="btn-round"><a href="/${oname }/interact/edit_xcBigScreen.action?xcid=${xcid }"><span class="glyphicon glyphicon-sound-dolby" aria-hidden="true"></span></a><a href="/${oname }/interact/edit_xcBigScreen.action?xcid=${xcid }">大屏切换</a></div>
	<div class="btn-round"><a href="/${oname }/page/pageconfig_new.action?siteid=${dto.list[3].id }&stype=${dto.list[3].type }&xcid=${xcid}"><span class="glyphicon glyphicon-hd-video" aria-hidden="true"></span></a><a href="/${oname }/page/pageconfig_new.action?siteid=${dto.list[3].id }&stype=${dto.list[3].type }&xcid=${xcid}">大屏设置</a></div>
	<div class="btn-round"><a href="/${oname }/page/xcPerson.action?xcid=${xcid}"><span class="glyphicon glyphicon-hd-video" aria-hidden="true"></span></a><a href="/${oname }/page/xcPerson.action?xcid=${xcid}">参会人员</a></div>
	<div class="clearfix"></div>
</div>
	<s:iterator value="dto.list" var="s" status="st" begin="0" end="2">
	<div class="sitegroup_List">
		<div style="float:left">
			<img  alt="" <s:if test='dto.wps[#s.id].pic != ""'>src="${dto.wps[s.id].pic }"</s:if><s:else>src="/images/nopic.png"</s:else> width="100px"/>
		</div>
		<div class="m_badge">${s.name}</div>
		<div class="sitegroup_List_left2" style="margin-left:120px;">
	    	<div>分享标题：<s:if test='dto.wps[#s.id]==null'>未设置标题</s:if><s:else>${dto.wps[s.id].title }</s:else></div>
	    	<div>分享描述：<s:if test='dto.wps[#s.id]==null'>未设置描述</s:if><s:else>${dto.wps[s.id].description }</s:else></div>
	    	<s:if test="#st.index==0">
				<div >确认参加用户：<span style="color:red">${dto.xc.invitedNum }</span></div>
	    	</s:if>
	    	<s:elseif test="#st.index==1">
	    		<div >签到用户：<span style="color:red">${dto.xc.checkedNum }</span></div>
	    	</s:elseif>
		    <div class="operations">
	    		<a href="/${oname }/page/pageconfig_new.action?siteid=${s.id }&stype=${s.type }&xcid=${xcid}"><span title="编辑" class="glyphicon glyphicon-edit"></span></a>
	    		<i class="split">|</i>
		        <a target="_blank" href="/${oname}/data/site_chart.action?siteid=${s.id}&source=EWX"><span class="glyphicon glyphicon-stats" title="推广效果"></span></a>
		    </div>
	    </div>
	</div>
	</s:iterator>
</div>
