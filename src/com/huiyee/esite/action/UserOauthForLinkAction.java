package com.huiyee.esite.action;

public class UserOauthForLinkAction extends AbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3965001216794383226L;
	private long appid = 1;// 申请的app在本地数据库的id
	private String sinaOauthUrl;
	private long entityid;
	private String oldurl;
	private long pageid;
	private long shareid;
	private String source;

	public void setOldurl(String oldurl)
	{
		this.oldurl = oldurl;
	}

	@Override
	public String execute() throws Exception
	{
		if (pageid > 0)
		{
			long siteid = pageCompose.findSiteidByPageid(pageid);
			appid = pageCompose.findAppidBySiteid(siteid);
			entityid=siteid;
		}
		System.out.println("oldurl:" + oldurl);
		String state = entityid + "," + appid + "," + oldurl+","+shareid+","+pageid+","+source;
		sinaOauthUrl = pageCompose.findUserOauthLink(state, appid,shareid);
		return SUCCESS;
	}

	public void setAppid(long appid)
	{
		this.appid = appid;
	}

	public String getSinaOauthUrl()
	{
		return sinaOauthUrl;
	}

	public void setEntityid(long entityid)
	{
		this.entityid = entityid;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public long getShareid() {
		return shareid;
	}

	public void setShareid(long shareid) {
		this.shareid = shareid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
