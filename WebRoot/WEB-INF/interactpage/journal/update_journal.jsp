<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
  <form action="update_journal.action" method="post" id="myform" name="myform" enctype="multipart/form-data" class="formview">
  	<div class="toolbar pb10">
  	  <ul class="c_switch">
	  <li class="selected"><a href="">编辑微期刊</a></li>
	  </ul>
	  <a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
  	 <input type="hidden" name="id" value="${jm.id }">
 	<dl>
	 	<dt>微期刊名称</dt>
		<dd>
			<input type="text" class="text-medium" name="jm.title" value="${jm.title }" id="title" onblur="checkJourTitle()"><span id="title_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>是否支持分享</dt>
		<dd>
			<label class="forradio"><input type="radio" name="jm.isshare" value="N" <s:if test='jm.isshare=="N"'> checked="checked" </s:if> onclick="$('#type').hide()"/>否</label>
 			<label class="forradio"><input type="radio" name="jm.isshare" value="Y" <s:if test='jm.isshare=="Y"'> checked="checked" </s:if> onclick="$('#type').show()"/>是</label>
		</dd>
	</dl>
	<dl>
	 	<dt>分享赠送积分</dt>
		<dd>
			<input type="text" class="text-medium" name="jm.balance" value="${jm.balance }">
		</dd>
	</dl>
	<dl>
	 	<dt>&nbsp;</dt>
		<dd>
			<input type="button" value="提交" onclick="ckButton()">
 			<input type="reset" value="重置">
		</dd>
	</dl>
 </form>
 </div>