<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<script type="text/javascript" src="/js/functions.js"></script>
<script type="text/javascript" src="/js/ajaxData.js"></script>
<div >
      <div>
        微博昵称：${detailDto.user.nickname}  互动总数：${detailDto.total}  粉丝情况：${detailDto.user.fensishu}
      </div>
      <!-- 
       <div>
        基本信息        
         姓名：  性别：<s:if test="#attr.detailDto.user.gender=='M'">男</s:if><s:elseif test="#attr.detailDto.user.gender=='W'">女</s:elseif><s:else>其他</s:else> 区域:   QQ号码：   电话：${detailDto.user.phone}  email地址： ${detailDto.user.email} 
      </div>
       -->
      <div >
           互动信息
           <input type="hidden" id="detailPageId">
           <input type="hidden" id="detailTotal" value="${detailDto.total}">
           <input type="hidden" id="sgid" value="${sgid}">
           <input type="hidden" id="siteid" value="${siteid}">
           <input type="hidden" id="wbuid" value="${wbuid}">
   <table id="detailtable" class="tb_normal" width="100%" border="1" cellspacing="0" cellpadding="0">
      <thead>
		<tr>
			<th>互动时间</th>
			<th>互动类型</th>
			<th>互动对象</th>
			<th>互动终端</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="detailDto.list" var="d" >
			<tr align="center">
				<td align="center">
				  ${d.createtime}
			    </td>
				<td>
				   ${d.actiontype}
				</td>
				<td>
				   ${d.target}
				</td>
				 <td><s:if test='#d.terminal=="A"'>PAD</s:if><s:elseif test='#d.terminal=="C"'>PC</s:elseif><s:elseif test='#d.terminal=="P"'>PHONE</s:elseif></td>
			</tr>
		</s:iterator>
	</tbody>
  </table>
            <span  id="detailprepage"></span>&nbsp;<span id="detailnextpage"> <s:if test="#attr.detailDto.total>10"><a href="javascript:void(0)" onclick="danymicRecordDetailData(2)">下一页</a></s:if></span>
   </div>
</div>
