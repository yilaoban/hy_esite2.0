<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <form onsubmit="return false" class="formview">
    <dl>
      <dt>选择应用站点</dt>
      <dd>
        <select id="pageid">
          <s:iterator value="dto.sg_list" var="sg">
            <s:iterator value="#sg.site" var="s">
              <s:iterator value="#s.pages" var="p">
                <s:if test="#p.apptype=='ZSJ'">
                  <option value="${p.id}">${sg.groupname}</option>
                </s:if>
              </s:iterator>
            </s:iterator>
          </s:iterator>
        </select>
        <button class="btn btn-info btn-sm" onclick="savePage()">保存</button>
        <button class="btn btn-primary" style="float: right;" onclick="index()">添加索引</button>
      </dd>
    </dl>
  </form>

  <table id="table" class="tb_normal" width="100%" border="1" cellspacing="0" cellpadding="0">
    <thead>
      <tr>
        <th>搜索可见</th>
        <th>站点</th>
        <th>最近更新时间</th>
        <th>下次更新时间</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.ssi_list" var="l">
        <tr>
          <td>
            <input type="checkbox" <s:if test='#l.status==1'>checked</s:if> onclick="updateStatus(this,'${l.id}')" />
          </td>
          <td>${l.groupName}</td>
          <td>
            <s:date name="updatetime" format="yyyy-MM-dd HH:mm:ss"></s:date>
          </td>
          <td>
            <s:date name="starttime" format="yyyy-MM-dd HH:mm:ss"></s:date>
          </td>
          <td>
            <s:if test="#l.canUpdate()">
              <a href="javascript:;" onclick="updateIndex('${l.id}')">立刻更新</a>
              |
            </s:if>
            <a href="javascript:;" onclick="deleteIndex('${l.id}')">删除</a>
          </td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#pageid").val('${ss.pageid}');
	});

	function savePage() {
		var pageid = $("#pageid").val();
		if (pageid == null) {
			return;
		}
		$.ajax({
			url : "/${oname}/interact/site_search_page_save.action",
			type : "post",
			data : {
				"ss.pageid" : pageid
			},
			cache : false,
			success : function(res) {
				if (res > 0) {
					layer.alert("保存成功");
				} else {
					layer.alert("保存失败");
				}
			}
		});
	}

	function index() {
		layer.open({
			type : 2,
			area : [ '650px', '500px' ],
			title : "添加索引",
			content : "/${oname}/interact/site_search_index.action",
			end : function() {
				window.location.reload();
			}
		});
	}

	function updateIndex(id) {
		$.ajax({
			url : "/${oname}/interact/site_search_index_update.action",
			data : {
				"ssi.id" : id
			},
			type : "post",
			cache : false,
			success : function(res) {
				if (res > 0) {
					layer.alert("更新成功", function() {
						window.location.reload();
					});
				} else {
					layer.alert("更新失败");
				}
			}
		});
	}

	function deleteIndex(id) {
		layer.confirm("删除后将无法搜索此站内容，确定删除吗？", function() {
			$.ajax({
				url : "/${oname}/interact/site_search_index_delete.action",
				data : {
					"ssi.id" : id
				},
				type : "post",
				cache : false,
				success : function(res) {
					if (res > 0) {
						layer.alert("删除成功", function() {
							window.location.reload();
						});
					} else {
						layer.alert("删除失败");
					}
				}
			});
		});
	}

	function updateStatus(cbx, id) {
		var status = 0;
		if ($(cbx).is(":checked")) {
			status = 1;
		}
		$.ajax({
			url : "/${oname}/interact/site_search_status_update.action",
			data : {
				"ssi.id" : id,
				"ssi.status" : status
			},
			type : "post",
			cache : false,
			success : function(res) {
				if (res > 0) {

				} else {
					layer.alert("更新失败,请稍后再试", function() {
						window.location.reload();
					});
				}
			}
		});
	}

	
</script>