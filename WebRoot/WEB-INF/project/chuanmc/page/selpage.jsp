<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">关联页面</h4>
</div>
<div class="modal-body">
	111
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
  <button type="button" class="btn btn-primary" onclick="onClik(${dto.pbr.pcid})" data-dismiss="modal">关联</button>
</div>