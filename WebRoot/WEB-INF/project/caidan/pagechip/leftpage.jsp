<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test='#session.accountType=="YYR"'>
  <!-- 运维 -->
  <div id="nav">
    <ul>
      <li class="s_on">
        <a class="nav_desc_1" title="彩蛋运营" href="http://esite.mocaidan.com/${sessionScope.account.owner.entity}/cd-media/adIndex.action?ticket=${sessionScope.ticket}">微站运营</a>
      </li>
      <s:if test='#session.account.username == "admin"'>
      <li>
        <a class="nav_desc_3" title="用户洞察" href="http://mcrm.mocaidan.com/data/index.action?ticket=${sessionScope.ticket}">用户洞察</a>
      </li>
      </s:if>
      <li>
        <a class="nav_desc_4" title="微信互动" href="http://crm.mocaidan.com/wx/index.action?ticket=${sessionScope.ticket}">微信互动</a>
      </li>
      <li>
        <a class="nav_desc_2" title="微站运营" href="http://esite.mocaidan.com/${sessionScope.account.owner.entity}/page/website.action?ticket=${sessionScope.ticket}">微站运营</a>
      </li>
    </ul>
  </div>
</s:if>
<div id="hd">
  <div id="logo">
    <a target="_blank" href="http://www.mocaidan.com/">
      <img src="/project/caidan/img/logo.jpg" border="0" style="width: 200px;" />
    </a>
  </div>
  <div class="link">
    <span class="loginname">登录账户：${sessionScope.account.owner.entity}/${sessionScope.account.username}</span>
    <a href="/${oname }/user/logout.action?url=/login.action" title="登出">
      <span class="logout"></span>
    </a>
  </div>
</div>
<div id="inside">
  <div id="menu">
    <s:url var="lighturl" includeParams="all" escapeAmp="false"></s:url>
    <ul>
      <s:if test='#session.accountType=="ADM"'>
        <li class="select">
          <a title="帐号管理" href="javascript:void(0)">
            <span>帐号管理</span>
          </a>
        </li>
	      <li>
	        <a title="用户洞察" href="http://mcrm.mocaidan.com/data/index.action?ticket=${sessionScope.ticket}">
	        	<span>用户洞察</span>
	        </a>
	      </li>
      </s:if>
      <s:if test='#session.accountType=="PRZ"'>
        <!-- 省级 -->
        <li <s:if test='#lighturl.contains("cd-news")'>class="select"</s:if>>
          <a title="内容管理" href="/${oname}/cd-news/index.action">
            <span>内容管理</span>
          </a>
        </li>
        <li>
          <li <s:if test='#lighturl.contains("cd-report")'>class="select"</s:if>>
          <a title="查看报告" href="/${oname}/cd-report/index.action">
            <span>查看报告</span>
          </a>
          </li>
        </li>
      </s:if>
      <s:if test='#session.accountType=="ALZ"'>
        <!-- 全国 -->
        <li <s:if test='#lighturl.contains("cd-way")'>class="select"</s:if>>
          <a title="渠道管理" href="/${oname}/cd-way/index.action">
            <span>渠道管理</span>
          </a>
        </li>
        <li <s:if test='#lighturl.contains("cd-news")'>class="select"</s:if>>
          <a title="内容管理" href="/${oname}/cd-news/index.action">
            <span>内容管理</span>
          </a>
        </li>
        <li <s:if test='#lighturl.contains("cd-rmb")'>class="select"</s:if>>
          <a title="提现管理" href="/${oname}/cd-rmb/index.action">
            <span>提现管理</span>
          </a>
        </li>
        <li <s:if test='#lighturl.contains("cd-report")'>class="select"</s:if>>
          <a title="查看报告" href="/${oname}/cd-report/index.action">
            <span>查看报告</span>
          </a>
        </li>
      </s:if>
      <s:if test='#session.accountType=="YYR"'>
        <!-- 运维 -->
        <li <s:if test='#lighturl.contains("cd-media")'>class="select"</s:if>>
          <a title="媒体管理" href="/${oname}/cd-media/adIndex.action">
            <span>媒体管理</span>
          </a>
        </li>
        <li <s:if test='#lighturl.contains("cd-guanggao")'>class="select"</s:if>>
          <a title="广告管理" href="/${oname}/cd-guanggao/adGGList.action">
            <span>广告管理</span>
          </a>
        </li>
        <li <s:if test='#lighturl.contains("cd-task")'>class="select"</s:if>>
          <a title="任务管理" href="/${oname}/cd-task/index.action">
            <span>任务管理</span>
          </a>
        </li>
        <li <s:if test='#lighturl.contains("cd-news")'>class="select"</s:if>>
          <a title="新闻管理" href="/${oname}/cd-news/index.action">
            <span>新闻管理</span>
          </a>
        </li>
        <li <s:if test='#lighturl.contains("cd-lottery")'>class="select"</s:if>>
          <a title="抽奖活动" href="/${oname}/cd-lottery/index.action">
            <span>抽奖活动</span>
          </a>
        </li>
        <li <s:if test='#lighturl.contains("cd-coupon")'>class="select"</s:if>>
          <a title="兑换券" href="/${oname}/cd-coupon/index.action">
            <span>兑换券</span>
          </a>
        </li>
        <li <s:if test='#lighturl.contains("cd-user")'>class="select"</s:if>>
          <a title="用户列表" href="/${oname}/cd-user/index.action">
            <span>用户列表</span>
          </a>
        </li>
      </s:if>
    </ul>
  </div>