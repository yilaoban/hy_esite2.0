<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content">
  <form action="sava_email_periodical.action" method="post" id="myform" name="myform" enctype="multipart/form-data" class="formview">
    <h2 class="pb20">
		  邮件期刊
		  <a href="javascript:window.history.back()" class="return" title="返回">返回</a>
	  </h2>
 	<dl>
 		<dt>标题</dt>
 		<dd>
 			<input type="text" class="text-medium" name="title" id="title" onblur="checkEtitle()"><span id="title_"></span>
 		</dd>
	</dl>
 	<dl>
 		<dt>URL</dt>
 		<dd>
 			<input type="text" class="text-medium" name="url" id="url" onblur="checkEurl()"><span id="url_"></span>
 		</dd>
 	</dl>
 	<dl>
 		<dt>&nbsp;</dt>
 		<dd>
 			<input type="button" value="提交" onclick="checkButton()">
 			<input type="reset" value="重置">
 		</dd>
 	</dl>
 	
 </form>
 </div>