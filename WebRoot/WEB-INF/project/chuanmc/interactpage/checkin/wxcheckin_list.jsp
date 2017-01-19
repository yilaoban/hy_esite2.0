<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	   <li <s:if test='dto.type == "W"'>class="selected"</s:if>><a href="/${oname }/interact/index.action?mid=10008">微博签到</a></li>
	  <li <s:if test='dto.type == "X"'>class="selected"</s:if>><a href='/${oname }/interact/wxCheckin.action?mid=10008'>微信签到</a></li>
	  </ul>
	</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>
					商家微信
				</th>
				<th>
					初次签到得分
				</th>
				<th>
					连续签到每天增加积分
				</th>
				<th>
					最多的连续签到天数
				</th>
				<th>
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.list" var="s">
				<tr align="center">
					<td align="center">
						${s.nickName }
					</td>
					<s:if test="addnum==0&&addaddnum==0&&maxday==0">
						<td colspan="3">/</td>
					</s:if>
					<s:else>
						<td align="center">
							${s.addnum }
						</td>
						<td align="center">
							${s.addaddnum }
						</td>
						<td align="center">
							${s.maxday }
						</td>
					</s:else>
					<td align="center">
						<a href="checkinEdit.action?ownerwbuid=${s.ownerwbuid }&mid=${mid }&utype=1">编辑</a><i class="split">|</i><!-- <a href="javascript:checkin_view(1,${s.ownerwbuid },1,'source')">预览</a><i class="split">| --><a href="checkin_data.action?ownerwbuid=${s.ownerwbuid }&mid=${mid }&utype=1">签到数据</a><i class="split">|</i><a  href="javascript:checkindel('${s.nickName }',${s.ownerwbuid },1)">删除</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
