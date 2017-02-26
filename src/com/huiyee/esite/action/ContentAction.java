
package com.huiyee.esite.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.ContentDto;
import com.huiyee.esite.dto.PageAddressDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.ContentTab;
import com.huiyee.esite.model.OwnerContentType;
import com.huiyee.esite.service.FileUploadService;
import com.opensymphony.xwork2.ActionContext;

public class ContentAction extends AbstractAction
{

	private long ccid;
	private ContentDto dto;
	private int pageId = 1;

	// 新增内容
	private String name;
	private File simg;
	private String simgFileName;
	private String simgContentType;
	private File bimg;
	private String bimgFileName;
	private String bimgContentType;
	private String linkurl;
	private double price;
	private double salesprice;
	private String detail;
	private String status;
	private String simgurl;
	private String bimgurl;
	private String tag;
	private long contentId;
	private long productid;
	private String ccname;
	private int ccindex;
	private String cctype;
	private long typeid;
	private List<OwnerContentType> subList;
	private String subtype;
	private String password;

	private int isuse;

	private String entityName;
	private String redirecturl;

	private String ids;
	private String names;
	private String ccpic;
	private String ccdesc;
	private String json;
	private JSONObject jsonObj;
	private long pageid;
	private PageAddressDto pdto;

	private int total;
	private int used;
	private int shownum;
	private JSONObject tagJson;
	private double jftoprice;
	private int maxjf;
	private String productType;// 因为subtype被用作实物,卡券的字段,所以此字段暂代subtype表示微商城还是积分商城
	private int moveUp;
	private int personlimit;
	private Map<String, ContentTab> tabs;
	private String tabid="f1";
	private ContentTab tab;
	

	public String getPassword()
	{
		return password;
	}

	
	public void setPassword(String password)
	{
		this.password = password;
	}


	public JSONObject getTagJson()
	{
		return tagJson;
	}

	public void setTagJson(JSONObject tagJson)
	{
		this.tagJson = tagJson;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public int getUsed()
	{
		return used;
	}

	public void setUsed(int used)
	{
		this.used = used;
	}

	public PageAddressDto getPdto()
	{
		return pdto;
	}

	public void setPdto(PageAddressDto pdto)
	{
		this.pdto = pdto;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public String execute() throws Exception
	{
		ServletActionContext.getRequest().getSession().setAttribute("positionId", IPageConstants.POSITIONID_6);
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.findContentByCategoryId(account, ccid, typeid, pageId, entityName);
		try
		{
			if ("F".equals(dto.getCurrent().getType()))
			{
				return "reditectForum";
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String findNext() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		List<ContentCategory> list = pageCompose.findNextCategory(ccid, account);
		if (list == null || list.size() == 0)
		{
			list = null;
		}
		Gson gson = new Gson();
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		return null;
	}

	public String addProductPre() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.addContentPre(account, IPageConstants.CONTENT_PRODUCT, ccid);
		return SUCCESS;
	}

	public String addProductLevel() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		long i = 0;
		if (isuse == 0)
		{
			i = pageCompose.updateProductVip(productid);
		} else
		{
			i = pageCompose.addProductLevel(productid, ids, names);
		}
		PrintWriter out = response.getWriter();
		out.print(i);
		out.flush();
		out.close();
		return null;
	}

	public String addProductSub() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		ContentProduct product = new ContentProduct();

		if (StringUtils.isEmpty(name))
		{
			this.addFieldError("error", "名称不能为空");
			dto = (ContentDto) pageCompose.addContentPre(account, IPageConstants.CONTENT_PRODUCT, ccid);
			return "fail";
		}
		product.setOwner(account.getOwner().getId());
		product.setCatid(ccid);
		product.setName(name);
		product.setLinkurl(linkurl);
		product.setPrice(price);
		product.setSalesprice(salesprice);
		product.setDetail(detail);
		product.setStatus(status);
		product.setTag(tag);
		product.setPersonlimit(personlimit);
		if (StringUtils.isNotEmpty(subtype))
		{
			product.setSubtype(subtype);
		} else
		{
			product.setSubtype(IPageConstants.PRODUCT_SUBTYPE_SHIWU);
		}
		product.setTotal(total);
		product.setUsed(used);
		product.setShownum(shownum);
		product.setMaxjf(maxjf);

		String imgfilePath = FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PRODUCT_FILEFATH);
		// 小图
		if (simg != null)
		{
			String simgfileName = FileUploadService.createFileName(simgFileName);
			String result1 = FileUploadService.saveFile(simg, imgfilePath, simgfileName);
			product.setSimgurl(result1);
		}

		// 大图
		if (bimg != null)
		{
			String bimgfileName = FileUploadService.createFileName(bimgFileName);
			String result2 = FileUploadService.saveFile(bimg, imgfilePath, bimgfileName);
			product.setBimgurl(result2);
		}

		long productid = pageCompose.saveProduct(product, tagJson);
		redirecturl = "/" + this.getOname() + "/content/content_index.action?ccid=" + ccid;
		return SUCCESS;
	}

	public String editProductPre() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.findProductById(account, contentId);
		return SUCCESS;
	}

	public String editProductSub() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		if (StringUtils.isEmpty(name))
		{
			this.addFieldError("error", "名称不能为空");
			dto = (ContentDto) pageCompose.findProductById(account, contentId);
			return "fail";
		}
		ContentProduct product = new ContentProduct();
		product.setOwner(account.getOwner().getId());
		product.setCatid(ccid);
		product.setName(name);
		product.setLinkurl(linkurl);
		product.setPrice(price);
		product.setSalesprice(salesprice);
		product.setDetail(detail);
		product.setStatus(status);
		product.setId(contentId);
		product.setTag(tag);
		product.setTotal(total);
		product.setUsed(used);
		product.setShownum(shownum);
		product.setPersonlimit(personlimit);
		if (StringUtils.isNotEmpty(subtype))
		{
			product.setSubtype(subtype);
		} else
		{
			product.setSubtype(IPageConstants.PRODUCT_SUBTYPE_SHIWU);
		}
		product.setMaxjf(maxjf);

		String imgfilePath = FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PRODUCT_FILEFATH);
		if (simg != null)
		{
			// 小图
			String simgfileName = FileUploadService.createFileName(simgFileName);
			String result1 = FileUploadService.saveFile(simg, imgfilePath, simgfileName);
			product.setSimgurl(result1);

		} else
		{
			product.setSimgurl(simgurl);
		}

		if (bimg != null)
		{
			// 大图
			String bimgfileName = FileUploadService.createFileName(bimgFileName);
			String result2 = FileUploadService.saveFile(bimg, imgfilePath, bimgfileName);
			product.setBimgurl(result2);
		} else
		{
			product.setBimgurl(bimgurl);
		}

		int result = pageCompose.editProduct(product, account.getOwner().getId(), tagJson);
		redirecturl = "/" + this.getOname() + "/content/content_index.action?ccid=" + ccid;
		return SUCCESS;
	}

	public String showProduct() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.findProductById(account, contentId);
		return SUCCESS;
	}

	public String delProduct() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = pageCompose.deleteProduct(contentId, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String ajaxfindOwnerProduct() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		List<ContentProduct> list = pageCompose.findOwnerProduct(account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		return null;
	}

	public String shenhe() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.findProductById(account, contentId);
		return SUCCESS;
	}

	public String shenheSub() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int result = pageCompose.checkProduct(contentId, status, account.getOwner().getId());
		return SUCCESS;
	}

	public String publicProduct() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = pageCompose.publicProduct(contentId, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String offProduct() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int result = pageCompose.offProduct(contentId, account.getOwner().getId());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String delCategory() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = pageCompose.delCategory(ccid);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String addCategory() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		ContentCategory cc = new ContentCategory();
		cc.setName(ccname);
		cc.setIdx(ccindex);
		cc.setFartherCatId(ccid);
		cc.setType(cctype);
		cc.setTypeid(typeid);
		cc.setDesc(ccdesc);
		cc.setPic(ccpic);
		cc.setPassword(password);
		if (StringUtils.isNotEmpty(subtype))
			cc.setSubtype(subtype);
		int result = pageCompose.addCategory(account.getOwner().getId(), cc);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String updateCategoryName() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		ContentCategory cc = new ContentCategory();
		cc.setName(ccname);
		cc.setDesc(ccdesc);
		cc.setPic(ccpic);
		cc.setPassword(password);
		int result = pageCompose.updateCategoryName(ccid, cc);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String updateUpProduct() throws Exception
	{
		int result = pageCompose.updateContnetIdx(contentId, ccid, cctype, moveUp);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public String createContentType() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int result = pageCompose.addContentType(account, ccname, cctype);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public String deleteContentType() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int result = pageCompose.deleteContentType(typeid, account);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public String configTypeIndex() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (ContentDto) pageCompose.findConfigType(account);
		return SUCCESS;
	}

	public String configTypeSub() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = 0;
		if (subList != null && subList.size() > 0)
		{
			rs = pageCompose.configTypeSub(subList, account.getOwner().getId());
		}
		String result = rs > 0 ? "Y" : "N";
		out.print(result);
		out.flush();
		out.close();
		return null;

	}

	public long getCcid()
	{
		return ccid;
	}

	public void setCcid(long ccid)
	{
		this.ccid = ccid;
	}

	public ContentDto getDto()
	{
		return dto;
	}

	public void setDto(ContentDto dto)
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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

	public String getLinkurl()
	{
		return linkurl;
	}

	public void setLinkurl(String linkurl)
	{
		this.linkurl = linkurl;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public long getContentId()
	{
		return contentId;
	}

	public void setContentId(long contentId)
	{
		this.contentId = contentId;
	}

	public String getSimgurl()
	{
		return simgurl;
	}

	public void setSimgurl(String simgurl)
	{
		this.simgurl = simgurl;
	}

	public String getBimgurl()
	{
		return bimgurl;
	}

	public void setBimgurl(String bimgurl)
	{
		this.bimgurl = bimgurl;
	}

	public String getCcname()
	{
		return ccname;
	}

	public void setCcname(String ccname)
	{
		this.ccname = ccname;
	}

	public int getCcindex()
	{
		return ccindex;
	}

	public void setCcindex(int ccindex)
	{
		this.ccindex = ccindex;
	}

	public String getCctype()
	{
		return cctype;
	}

	public void setCctype(String cctype)
	{
		this.cctype = cctype;
	}

	public String getTag()
	{
		return tag;
	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

	public double getSalesprice()
	{
		return salesprice;
	}

	public void setSalesprice(double salesprice)
	{
		this.salesprice = salesprice;
	}

	public long getTypeid()
	{
		return typeid;
	}

	public void setTypeid(long typeid)
	{
		this.typeid = typeid;
	}

	public String getEntityName()
	{
		return entityName;
	}

	public void setEntityName(String entityName)
	{
		this.entityName = entityName;
	}

	public String getRedirecturl()
	{
		return redirecturl;
	}

	public void setRedirecturl(String redirecturl)
	{
		this.redirecturl = redirecturl;
	}

	public String getCcpic()
	{
		return ccpic;
	}

	public void setCcpic(String ccpic)
	{
		this.ccpic = ccpic;
	}

	public String getCcdesc()
	{
		return ccdesc;
	}

	public void setCcdesc(String ccdesc)
	{
		this.ccdesc = ccdesc;
	}

	public List<OwnerContentType> getSubList()
	{
		return subList;
	}

	public void setSubList(List<OwnerContentType> subList)
	{
		this.subList = subList;
	}

	public String breadset() throws Exception
	{
		json = pageCompose.findBread(ccid);
		if (json != null)
		{
			jsonObj = JSONObject.fromObject(json);
		} else
		{
			jsonObj = new JSONObject();
		}
		return SUCCESS;
	}

	public String breadsetsub() throws Exception
	{
		int result = pageCompose.saveBread(ccid, json);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public String getJson()
	{
		return json;
	}

	public JSONObject getJsonObj()
	{
		return jsonObj;
	}

	public void setJson(String json)
	{
		this.json = json;
	}

	public String getSubtype()
	{
		return subtype;
	}

	public void setSubtype(String subtype)
	{
		this.subtype = subtype;
	}

	public String contentlink() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		pdto = (PageAddressDto) pageCompose.findAddressList(account, pageid);
		return SUCCESS;
	}

	public String updateToPage() throws Exception
	{
		int result = pageCompose.updateToPage(ccid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public String tabIndex() throws Exception
	{
		HttpServletRequest httpRequest = ServletActionContext.getRequest();
		String referer = httpRequest.getHeader("referer");
		if (referer != null && referer.contains("/content/content_index.action"))
		{
			ActionContext.getContext().getSession().put("contentlisturl", referer);
		}
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		tabs = pageCompose.findProductTabs(contentId, account.getOwner().getId());
		if (tabid != null&&tabs!=null)
		{
			tab=tabs.get(tabid);
		}
		return SUCCESS;
	}

	public String updateTabIndex() throws Exception
	{
		int result = pageCompose.updateTabIndex(tab);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	public double getJftoprice()
	{
		return jftoprice;
	}

	public void setJftoprice(double jftoprice)
	{
		this.jftoprice = jftoprice;
	}

	public int getMaxjf()
	{
		return maxjf;
	}

	public void setMaxjf(int maxjf)
	{
		this.maxjf = maxjf;
	}

	public String getProductType()
	{
		return productType;
	}

	public void setProductType(String productType)
	{
		this.productType = productType;
	}

	public int getShownum()
	{
		return shownum;
	}

	public void setShownum(int shownum)
	{
		this.shownum = shownum;
	}

	public int getMoveUp()
	{
		return moveUp;
	}

	public void setMoveUp(int moveUp)
	{
		this.moveUp = moveUp;
	}

	public String getIds()
	{
		return ids;
	}

	public int getPersonlimit()
	{
		return personlimit;
	}

	public void setIds(String ids)
	{
		this.ids = ids;
	}

	public String getNames()
	{
		return names;
	}

	public void setNames(String names)
	{
		this.names = names;
	}

	public long getProductid()
	{
		return productid;
	}

	public void setProductid(long productid)
	{
		this.productid = productid;
	}

	public void setPersonlimit(int personlimit)
	{
		this.personlimit = personlimit;
	}

	public int getIsuse()
	{
		return isuse;
	}

	public void setIsuse(int isuse)
	{
		this.isuse = isuse;
	}

	public Map<String, ContentTab> getTabs()
	{
		return tabs;
	}

	public void setTabs(Map<String, ContentTab> tabs)
	{
		this.tabs = tabs;
	}

	public String getTabid()
	{
		return tabid;
	}

	public void setTabid(String tabid)
	{
		this.tabid = tabid;
	}

	
	public ContentTab getTab()
	{
		return tab;
	}

	
	public void setTab(ContentTab tab)
	{
		this.tab = tab;
	}

}
