<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<div class="wrap_content left_module">
	<div class="toolbar pb10">
		<ul class="c_switch">
			<li class="selected"><a href="">上传券号</a></li>
		</ul>
		<a href="/${oname }/cd-coupon/productCodeIndex.action?pid=${pid }" class="return" title="返回"></a>
	</div>
		<form id="addCouponsForm"  action="code_upload_submit.action" name="addCouponsForm" enctype="multipart/form-data" method="post" class="formview jNice">
			<input type="hidden" name="pid" value="${pid }">
			<input type="hidden" name="lid" value="${lid }">
			<input type="hidden" name="returnType" value="${returnType }">
			<dl>
				<dt>要上传的券号</dt>
				<dd>
					<input type="file" name="couponsCodeUp" id="couponsCodeUp" />
					<span><font color="red">${messageError}</font></span>
				</dd>
			</dl>
			<dl>
				<dt>是否验证券号格式</dt>
				<dd>
					<label class="forradio"><input type="radio" id="yseCheck" name="typeCheck" value="1" checked/>是</label>
					<label class="forradio"><input type="radio" id="noCheck" name="typeCheck" value="0"/>否</label>
				</dd>
			</dl>
			<dl>
				<dt></dt>
				<dd>
					<input type="submit" id="button2" value="提交" />
				</dd>
			</dl>
			<dl>
				<dt></dt>
				<dd>
					<span class="notice">说明：1.上传时，以txt文件上传，一条记录一行，俩字段之间用英文逗号分隔不可有空格，优惠券号码在第一个字段，密码在第二个字段。2.当选择了验证格式之后，系统将验证上传的券号长度是否一致。例子1:123456,456 券号是123456 密码是456；例子2:如果密码为空则只写券号，如123456
					</span>
				</dd>
			</dl>
		</form>
</div>