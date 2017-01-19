<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<iframe id="data" src="${mcrmDomain }/data/site_user.action?ticket=${sessionScope.ticket}&siteid=${siteid}&source=${source}" width="100%" height="700px" scrolling="no" frameborder="0" marginheight="0" marginwidth="0"> </iframe>