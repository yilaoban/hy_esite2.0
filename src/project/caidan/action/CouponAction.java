
package project.caidan.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.dao.DataAccessException;

import project.caidan.dto.CouponDto;
import project.caidan.mgr.ICouponMgr;
import project.caidan.model.CDWay;
import project.caidan.model.CDWayCatalog;
import project.caidan.model.Coupon;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.ProductCode;
import com.huiyee.esite.service.FileUploadService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 兑换券
 * 
 * @author ldw
 * 
 */
public class CouponAction extends AbstractAction
{

	private ICouponMgr couponMgr;
	private int pageId = 1;
	private CouponDto dto;
	private File simg;
	private String simgFileName;
	private String simgContentType;
	private File bimg;
	private String bimgFileName;
	private String bimgContentType;
	private ContentProduct product;
	private Coupon coupon;
	private JSONObject tagJson;
	private long catalog;
	private long pid;
	private long id;

	private File couponsCodeUp;
	private String couponsCodeUpFileName;
	private String couponsCodeUpContentType;
	private String messageError;
	private int typeCheck;
	private String code;
	private String password;
	private long total;
	private long productid;

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (CouponDto) couponMgr.findCouponList(account, pageId);
		return SUCCESS;
	}

	public String addCouponPre() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = new CouponDto();
		List<CDWayCatalog> catalogs = couponMgr.findWayCatalogs(account);
		dto.setCatalogs(catalogs);
		return SUCCESS;
	}

	public String saveCodePre() throws Exception
	{
		return SUCCESS;
	}

	public int saveCode() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		int i = couponMgr.saveCode(pid, code, password, total);
		PrintWriter out = response.getWriter();
		out.print(i);
		out.flush();
		out.close();
		return 0;
	}

	public String productCode() throws Exception
	{

		dto = (CouponDto) couponMgr.findProductCodeList(pid, pageId, code);
		return SUCCESS;

	}

	public String uploadCodeSubmit()
	{
		String fileType = getCouponsCodeUpContentType();
		if (couponsCodeUp == null)
		{
			messageError = "请选择上传的文件";
			return ERROR;
		}
		Integer i = 0;
		Map<String, Object> map = getCouponsCodeList(fileType, pid, i);
		if (map.get("error") != null)
		{
			return ERROR;
		} else
		{
			try
			{
				couponMgr.addPrizeCode((List<ProductCode>) map.get("success"), pid);
				return SUCCESS;
			} catch (DataAccessException e)
			{
				messageError = "上传重复的券号,请检查券号是否有重复或者以前上传过";
				return ERROR;
			}
		}
	}

	private Map<String, Object> getCouponsCodeList(String fileType, long couponid, Integer dupcount)
	{

		Map<String, Object> map = new HashMap<String, Object>();
		if (fileType.equals("text/plain"))
		{
			List<ProductCode> list = new ArrayList<ProductCode>();
			try
			{
				BufferedReader bw = new BufferedReader(new FileReader(couponsCodeUp));
				String line = null;
				String line1 = null;
				String[] str = null;
				int flag = 0;
				int length = 0;
				ProductCode cd = new ProductCode();
				cd.setPid(couponid);
				line1 = bw.readLine().trim();
				if (line1.indexOf(",") != -1)
				{
					flag = 1;
					str = line1.split(",");
					cd.setCode(str[0]);
					cd.setPassword(str[1]);
				} else
				{
					flag = 0;
					cd.setCode(line1);
				}
				length = line1.length();
				list.add(cd);
				while ((line = bw.readLine()) != null)
				{
					line = line.trim();
					if (flag == 0)
					{
						if (typeCheck == 1)
						{
							if (line.length() != length)
							{
								messageError = "您上传txt类型的文件中存在优惠券券号位数不对";
								map.put("error", messageError);
								return map;
							}
						}
						boolean isdup = false;
						for (ProductCode c : list)
						{
							if (line.equals(c.getCode()))
							{
								dupcount++;
								isdup = true;
							}
						}
						if (!isdup)
						{
							length = line.length();
							ProductCode c = new ProductCode();
							c.setPid(couponid);
							c.setCode(line);
							list.add(c);
						}
					} else
					{
						str = line.split(",");
						if (typeCheck == 1)
						{
							if (line.length() != length)
							{
								messageError = "您上传txt类型的文件中存在优惠券券号位数不对";
								map.put("error", messageError);
								return map;
							}
						}
						boolean isdup = false;
						for (ProductCode c : list)
						{
							if (str[0].equals(c.getCode()))
							{
								dupcount++;
								isdup = true;
							}
						}
						if (!isdup)
						{
							ProductCode c = new ProductCode();
							c.setPid(couponid);
							c.setCode(str[0]);
							c.setPassword(str[1]);
							list.add(c);
						}
					}
				}
				bw.close();
				map.put("success", list);
				return map;
			} catch (IOException e)
			{
				messageError = "IO异常";
				map.put("error", messageError);
				return map;
			} catch (DataAccessException e)
			{
				messageError = "上传重复的券号,请检查券号是否有重复或者以前上传过";
				map.put("error", messageError);
				e.printStackTrace();
				return map;
			} catch (Exception e)
			{
				messageError = "上传出错,可能是包含中文符号或者系统不识别字符.";
				map.put("error", messageError);
				e.printStackTrace();
				return map;

			}

		} else
		{
			messageError = "您只能上传txt类型的文本文件";
			map.put("error", messageError);
			return map;
		}
	}

	public String findWayByCatalog() throws Exception
	{

		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		List<CDWay> list = couponMgr.findWayByCatalog(account, catalog);
		Gson gson = new Gson();
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		return null;

	}

	public String addCouponSub() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		if (product != null && coupon != null)
		{
			if (StringUtils.isEmpty(product.getName()))
			{
				this.addFieldError("error", "名称不能为空");
				dto = new CouponDto();
				List<CDWayCatalog> catalogs = couponMgr.findWayCatalogs(account);
				dto.setCatalogs(catalogs);
				return "fail";
			}
			product.setOwner(account.getOwner().getId());
			// product.setSubtype(IPageConstants.PRODUCT_SUBTYPE_KAQUAN);
			product.setType(IPageConstants.PRODUCT_JF);

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

			long productid = couponMgr.saveCoupon(account, coupon, product, tagJson);
		}
		return SUCCESS;
	}

	public String editPre() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (CouponDto) couponMgr.findCouponByid(pid, account);
		return SUCCESS;
	}

	public String editSub() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		if (product != null && coupon != null)
		{
			if (StringUtils.isEmpty(product.getName()))
			{
				this.addFieldError("error", "名称不能为空");
				dto = new CouponDto();
				List<CDWayCatalog> catalogs = couponMgr.findWayCatalogs(account);
				dto.setCatalogs(catalogs);
				return "fail";
			}
			product.setOwner(account.getOwner().getId());
			// product.setSubtype(IPageConstants.PRODUCT_SUBTYPE_KAQUAN);
			product.setType(IPageConstants.PRODUCT_JF);

			String imgfilePath = FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PRODUCT_FILEFATH);
			if (simg != null)
			{
				// 小图
				String simgfileName = FileUploadService.createFileName(simgFileName);
				String result1 = FileUploadService.saveFile(simg, imgfilePath, simgfileName);
				product.setSimgurl(result1);

			} else
			{
				product.setSimgurl(product.getSimgurl());
			}

			if (bimg != null)
			{
				// 大图
				String bimgfileName = FileUploadService.createFileName(bimgFileName);
				String result2 = FileUploadService.saveFile(bimg, imgfilePath, bimgfileName);
				product.setBimgurl(result2);
			} else
			{
				product.setBimgurl(product.getBimgurl());
			}
			int rs = couponMgr.updateCoupon(account, coupon, product, tagJson);
		}
		return SUCCESS;

	}
	
	public String delCouponCode() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		int i = couponMgr.delCouponCode(id,this.getOwner());
		PrintWriter out = response.getWriter();
		out.print(i);
		out.flush();
		out.close();
		return null;
	}
	
	public String clearCouponCode() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		int i = couponMgr.updateCodeClean(productid,this.getOwner());
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(i));
		out.flush();
		out.close();
		return null;
	}
	
	public void setCouponMgr(ICouponMgr couponMgr)
	{
		this.couponMgr = couponMgr;
	}

	public CouponDto getDto()
	{
		return dto;
	}

	public void setDto(CouponDto dto)
	{
		this.dto = dto;
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

	public ContentProduct getProduct()
	{
		return product;
	}

	public void setProduct(ContentProduct product)
	{
		this.product = product;
	}

	public Coupon getCoupon()
	{
		return coupon;
	}

	public void setCoupon(Coupon coupon)
	{
		this.coupon = coupon;
	}

	public JSONObject getTagJson()
	{
		return tagJson;
	}

	public void setTagJson(JSONObject tagJson)
	{
		this.tagJson = tagJson;
	}

	public long getCatalog()
	{
		return catalog;
	}

	public void setCatalog(long catalog)
	{
		this.catalog = catalog;
	}

	public long getPid()
	{
		return pid;
	}

	public void setPid(long pid)
	{
		this.pid = pid;
	}

	public File getCouponsCodeUp()
	{
		return couponsCodeUp;
	}

	public void setCouponsCodeUp(File couponsCodeUp)
	{
		this.couponsCodeUp = couponsCodeUp;
	}

	public String getCouponsCodeUpFileName()
	{
		return couponsCodeUpFileName;
	}

	public void setCouponsCodeUpFileName(String couponsCodeUpFileName)
	{
		this.couponsCodeUpFileName = couponsCodeUpFileName;
	}

	public String getCouponsCodeUpContentType()
	{
		return couponsCodeUpContentType;
	}

	public void setCouponsCodeUpContentType(String couponsCodeUpContentType)
	{
		this.couponsCodeUpContentType = couponsCodeUpContentType;
	}

	public String getMessageError()
	{
		return messageError;
	}

	public void setMessageError(String messageError)
	{
		this.messageError = messageError;
	}

	public int getTypeCheck()
	{
		return typeCheck;
	}

	public void setTypeCheck(int typeCheck)
	{
		this.typeCheck = typeCheck;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public long getProductid()
	{
		return productid;
	}

	public void setProductid(long productid)
	{
		this.productid = productid;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getTotal()
	{
		return total;
	}

	public void setTotal(long total)
	{
		this.total = total;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

}
