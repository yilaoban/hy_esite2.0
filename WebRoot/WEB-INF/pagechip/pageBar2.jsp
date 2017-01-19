<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%-- 用户后台专用分页标签 --%>
<script type="text/javascript">
  function checkMax(max)
  {
  var pageId=document.getElementsByName("topage")[0].value;
 
  if(pageId>max||pageId<1||pageId.search("^-?\\d+$")!=0)
  {
    alert("请输入正确的页数");
    document.getElementsByName("pageId")[0].value=null;
    return false;
  }
  else
  {
 	<s:url var="url1" includeParams="all" escapeAmp="false">
  </s:url>
  var a="siftDto.pageId=${dto.pager.currentPage}";
  var b="siftDto.pageId="+pageId;
  var u = "${url1}";
  var url=decodeURI(u);
  if(url.indexOf("?") == -1){
  	url += "?siftDto.pageId="+pageId;
  }
  else if(url.indexOf("pageId") == -1){
  	url +="&siftDto.pageId="+pageId;
  }
  else{
  	url = url.replace(a,b);
  }
  window.location.href=url;
  
  }
  }  
</script>
<div id="pagination" class="mt10">
	<s:if test="dto.pager.totalPage > 0">
		<s:if test="dto.pager.hasPrevPage">
			<s:url var="url" includeParams="all">
				<s:param name="siftDto.pageId"
					value="dto.pager.prevPage"></s:param>
			</s:url>
			<a href="${url}" class="pre">上一页</a>
		</s:if>
		<s:if test="dto.pager.totalPage >= 1">
			<s:url var="url" includeParams="all">
				<s:param name="siftDto.pageId"
					value="dto.pager.firstPage"></s:param>
					<s:param name="id"
					value="dto.dtoid"></s:param>
			</s:url>
			<s:if test="dto.pager.currentPage == 1">
				<a class="current" href="${url}">1</a>
			</s:if>
			<s:else>
				<a href="${url}">1</a>
			</s:else>
		</s:if>
		<s:if test="dto.pager.currentPage -2 > 2">
				<a>...</a>
		</s:if>
		<s:iterator var="pageIndex" value="dto.pager.pageBarList" begin="1" >
			<s:if test="dto.pager.currentPage == #pageIndex">
				<a class="current"><s:property value="pageIndex" /></a>
			</s:if>
			<s:else>
				<s:url var="url" includeParams="all"> 
					<s:param name="siftDto.pageId" value="pageIndex"></s:param>
				</s:url>
				<s:a href="%{url}" includeParams="all">
					<s:property value="pageIndex" />
				</s:a>
			</s:else>
		</s:iterator>
		<s:if test="dto.pager.currentPage +3 < dto.pager.totalPage">
				<a>...</a>
		</s:if>
		<s:if test="dto.pager.totalPage >1 && (dto.pager.currentPage +3 <= dto.pager.totalPage)">
			<s:url var="url" includeParams="all">
				<s:param name="siftDto.pageId"
					value="dto.pager.totalPage"></s:param>
					<s:param name="id"
					value="dto.dtoid"></s:param>
			</s:url>
			<s:if test="dto.pager.currentPage == dto.pager.totalPage">
				<a class="current" href="${url}">${dto.pager.totalPage}</a>
			</s:if>
			<s:else>
				<a href="${url}">${dto.pager.totalPage}</a>
			</s:else>
		</s:if>
		<s:if test="dto.pager.hasNextPage">
			<s:url var="url" includeParams="all">
				<s:param name="siftDto.pageId"
					value="dto.pager.nextPage"></s:param>
					<s:param name="id"
					value="dto.dtoid"></s:param>
			</s:url>
			<a href="${url}" class="next">下一页</a>
		</s:if>
		<span>共<s:property value="dto.pager.totalPage" />页</span>
		<span class="goto">到第<input name="topage" type="text" class="txt" />页</span>
		<a href="javascript:void(0)" class="btn-gray" onclick="return checkMax(${dto.pager.totalPage})"><span>确定</span></a>


	</s:if>
</div>

