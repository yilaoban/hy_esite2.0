<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">样式设置</h4>
</div>
<div class="modal-body">
<form id="styleform" name="styleform" class="formview">
	<dl>
	 	<dt>样式</dt>
	 	<dd>
	 		<textarea id="cardcss">${dto.cardcss }</textarea>
		</dd>
	</dl>
</form>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	<button type="button" class="btn btn-primary" onclick="onClikSaveCss(${pcid})" data-dismiss="modal">保存</button>
</div>
