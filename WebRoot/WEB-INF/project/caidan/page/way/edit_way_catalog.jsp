<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
	<form action="/${oname }/cd-way/editCatalogSave.action" id="form1" class="formview jNice" method="post">
	<div class="toolbar pb10">
	  	<ul class="c_switch">
			<li class="selected"><a href="#">编辑渠道分类</a></li>
		</ul>
		<a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<div>
		<input type="hidden" name="catalog.id" value="${catalog.id }"/>
		<dl>
		 	<dt>名称</dt>
			<dd>
				<input type="text" name="catalog.name" id="name" value="${catalog.name }"/>
			</dd>
		</dl>
		<dl>
		 	<dt>简介</dt>
			<dd>
				<textarea  maxlength="200" name="catalog.hydesc" id="hydesc">${catalog.hydesc }</textarea>
			</dd>
		</dl>
	</div>
	<dl>
		<dt></dt>
		<dd>
			<button type="submit" class="btn btn-primary">保存</button>
		</dd>
	</dl>
	</form>
</div>