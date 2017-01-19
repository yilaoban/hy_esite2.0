
package project.caidan.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import project.caidan.mgr.ICaiDanNewsMgr;
import project.caidan.model.CDNewsDto;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.service.FileUploadService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 彩蛋新闻
 * 
 * @author ldw
 * 
 */
public class CDNewsAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4798258920769664905L;
	private ICaiDanNewsMgr newsMgr;
	private CDNewsDto dto;
	private int pageId = 1;
	private String startTime;
	private String endTime;
	private String publishtime;
	private File simg;
	private String simgFileName;
	private String simgContentType;
	private File bimg;
	private String bimgFileName;
	private String bimgContentType;
	private ContentNew news;
	private JSONObject tagJson;
	private long contentid;

	public void setNewsMgr(ICaiDanNewsMgr newsMgr)
	{
		this.newsMgr = newsMgr;
	}

	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String accountType = (String) ActionContext.getContext().getSession().get("accountType");
		dto = (CDNewsDto) newsMgr.findList(account, accountType, pageId);
		return SUCCESS;
	}

	public String addcdNewsPre() throws Exception
	{
		String accountType = (String) ActionContext.getContext().getSession().get("accountType");
		return SUCCESS;
	}

	public String savecdNews() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String accountType = (String) ActionContext.getContext().getSession().get("accountType");
		if (news == null)
		{
			this.addFieldError("error", "缺少内容参数!");
		} else
		{
			if (StringUtils.isEmpty(news.getTitle()))
			{
				this.addFieldError("error", "标题不能为空");
				return "fail";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if (startTime == null || !"".equals(startTime))
			{
				news.setStartTime(sdf.parse(startTime));
			}
			if (endTime == null || !"".equals(endTime))
			{
				news.setEndTime(sdf.parse(endTime));
			}
			if (publishtime == null || !"".equals(publishtime))
			{
				news.setPublishtime(sdf.parse(publishtime));
			}
			String imgfilePath = FileUploadService.getFilePath(IPageConstants.TYPE_NEWS, account.getOwner().getId(), IPageConstants.CONTENT_NEW_FILEFATH);
			// 小图
			if (simg != null)
			{
				String simgfileName = FileUploadService.createFileName(simgFileName);
				String result1 = FileUploadService.saveFile(simg, imgfilePath, simgfileName);
				news.setSimgurl(result1);
			}

			// 大图
			if (bimg != null)
			{
				String bimgfileName = FileUploadService.createFileName(bimgFileName);
				String result2 = FileUploadService.saveFile(bimg, imgfilePath, bimgfileName);
				news.setBimgurl(result2);
			}
			news.setOwnerid(account.getOwner().getId());
			if (StringUtils.isEmpty(news.getContent()))
			{
				news.setContent("");
			}
			long newid = newsMgr.saveNews(news, tagJson, account, accountType);

		}
		return SUCCESS;
	}

	public String editNewsPre() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String accountType = (String) ActionContext.getContext().getSession().get("accountType");
		news = newsMgr.findNewById(contentid, account.getOwner().getId());
		return SUCCESS;
	}

	public String editNewsSub() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String accountType = (String) ActionContext.getContext().getSession().get("accountType");
		if (news == null)
		{
			this.addFieldError("error", "缺少内容参数!");
			return "fail";
		} else
		{
			news.setOwnerid(account.getOwner().getId());
			if (StringUtils.isEmpty(news.getTitle()))
			{
				this.addFieldError("error", "标题不能为空");
				news = newsMgr.findNewById(contentid, account.getOwner().getId());
				return "fail";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if (startTime == null || !"".equals(startTime))
			{
				news.setStartTime(sdf.parse(startTime));
			}
			if (endTime == null || !"".equals(endTime))
			{
				news.setEndTime(sdf.parse(endTime));
			}
			if (publishtime == null || !"".equals(publishtime))
			{
				news.setPublishtime(sdf.parse(publishtime));
			}
			if ("N".equals(news.getIslink()))
			{
				news.setLinkurl("");
				if(news.getContent()==null){
					news.setContent("");
				}
			} else
			{
				news.setContent("");
			}
			String imgfilePath = FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PRODUCT_FILEFATH);
			if (simg != null)
			{
				// 小图
				String simgfileName = FileUploadService.createFileName(simgFileName);
				String result1 = FileUploadService.saveFile(simg, imgfilePath, simgfileName);
				news.setSimgurl(result1);

			} 

			if (bimg != null)
			{
				// 大图
				String bimgfileName = FileUploadService.createFileName(bimgFileName);
				String result2 = FileUploadService.saveFile(bimg, imgfilePath, bimgfileName);
				news.setBimgurl(result2);
			} 
			newsMgr.updateNews(news, tagJson, account, accountType);
		}
		return SUCCESS;

	}
	
	
	
	public String delNew() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = newsMgr.deletecdNews(contentid, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	public String moveUp() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = newsMgr.updateNewsUp(contentid, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	public String moveDown() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = newsMgr.updateNewsDown(contentid, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	


	public CDNewsDto getDto()
	{
		return dto;
	}

	public void setDto(CDNewsDto dto)
	{
		this.dto = dto;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public String getPublishtime()
	{
		return publishtime;
	}

	public void setPublishtime(String publishtime)
	{
		this.publishtime = publishtime;
	}

	public File getSimg()
	{
		return simg;
	}

	public void setSimg(File simg)
	{
		this.simg = simg;
	}

	public String getSimgFileName()
	{
		return simgFileName;
	}

	public void setSimgFileName(String simgFileName)
	{
		this.simgFileName = simgFileName;
	}

	public String getSimgContentType()
	{
		return simgContentType;
	}

	public void setSimgContentType(String simgContentType)
	{
		this.simgContentType = simgContentType;
	}

	public File getBimg()
	{
		return bimg;
	}

	public void setBimg(File bimg)
	{
		this.bimg = bimg;
	}

	public String getBimgFileName()
	{
		return bimgFileName;
	}

	public void setBimgFileName(String bimgFileName)
	{
		this.bimgFileName = bimgFileName;
	}

	public String getBimgContentType()
	{
		return bimgContentType;
	}

	public void setBimgContentType(String bimgContentType)
	{
		this.bimgContentType = bimgContentType;
	}

	public ContentNew getNews()
	{
		return news;
	}

	public void setNews(ContentNew news)
	{
		this.news = news;
	}

	public JSONObject getTagJson()
	{
		return tagJson;
	}

	public void setTagJson(JSONObject tagJson)
	{
		this.tagJson = tagJson;
	}

	public long getContentid()
	{
		return contentid;
	}

	public void setContentid(long contentid)
	{
		this.contentid = contentid;
	}
}
