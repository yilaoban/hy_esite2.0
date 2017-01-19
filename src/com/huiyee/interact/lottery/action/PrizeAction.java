package com.huiyee.interact.lottery.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.dao.DataAccessException;

import com.google.gson.Gson;
import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.interact.lottery.dto.PrizeDto;
import com.huiyee.interact.lottery.mgr.ILotteryPrizeMgr;
import com.huiyee.interact.lottery.model.LotteryPrize;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;
import com.opensymphony.xwork2.ActionContext;

public class PrizeAction extends InteractModelAction
{

	private PrizeDto dto;
	private long id;
	private long lid;
	private String name;
	private String total;
	private String price;
	private String jf;
	private String hb;//红包价格
	private String wishing;//祝福语
	private String act_name;//活动名称
	private String remark;//活动备注
	private File file;
	private String fileFileName;
	private String fileContentType;
	private String fileurl;    // 原地址
	private String type;
	private String hydefault;
	private int pageId = 1;
	private ILotteryPrizeMgr lotteryprizeMgr;
	private String result;
	private String returnType;
	private long mid;
	private int hbgz;
	private String hbkey;
	
	/**
	 * 优惠券券号相关
	 */
	/**
	 * 上传给力券的券号
	 * 
	 * @return
	 */
	private File couponsCodeUp;
	private String couponsCodeUpFileName;
	private String couponsCodeUpContentType;
	private String messageError;
	private int typeCheck;
	private String code;
	private long productid;
	
	private List<ContentProduct> list;
	
	private String link;
	
	public String execute() throws Exception
	{
		dto = (PrizeDto) lotteryprizeMgr.findprizeList(lid, pageId);
		return SUCCESS;
	}

	public String addPre() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		list=lotteryprizeMgr.findProductByTypeZ(account,ContentProduct.PRODUCT_SUBTYPE_K);
		return SUCCESS;
	}

	public String add() throws Exception
	{
		Pattern pattern=Pattern.compile("[0-9]*");
	    Matcher match1=pattern.matcher(total);
	    Matcher match2=pattern.matcher(price);
	    Matcher match3=pattern.matcher(jf);
	    
	    if(total==null ||total.trim().length()== 0){
	    	total="0";
	    }
	    if(price==null ||price.trim().length()== 0){
	    	price="0";
	    }
		if (StringUtils.isEmpty(name))
		{
			result = "名称不能为空";
		}else if(!StringUtils.isEmpty(total)){
			if(!match1.matches()){
				result = "数量必须为数字";
			}else if(!StringUtils.isEmpty(price)){
				if(!match2.matches()){
					result = "价格必须为数字";
				}
			}
		}
		if("J".equalsIgnoreCase(type)){
			if(!match3.matches()){
				result = "类型为积分的奖品,积分数量必须为数字";
			}
		}else{
			jf="0";
		}
		if("M".equalsIgnoreCase(type)||"H".equalsIgnoreCase(type)){
			if(StringUtils.isEmpty(hb)){
				hb="0";
			}
			if(!pattern.matcher(hb).matches()||StringUtils.isEmpty(hb)){
				result = "类型为红包的奖品,红包数量必须为数字";
			}
			if(StringUtils.isEmpty(wishing)||StringUtils.isEmpty(act_name)||StringUtils.isEmpty(remark)){
				result = "祝福语,活动名称,备注不能为空";
			}
			if("M".equalsIgnoreCase(type)){
				if(hbgz == 0 && StringUtils.isBlank(hbkey)){
					result = "关注领取或者填写红包口令";
				}
			}
		}else{
			hb="0";
		}
		if("Z".equalsIgnoreCase(type)){
			if(productid<=0){
				result = "未选择自提商品";
			}
		}else{
			productid=0;
		}
		
		if(StringUtils.isEmpty(result))
		{
			String imgurl = null;
			if (file != null)
			{
				Account account = (Account) ActionContext.getContext().getSession().get("account");
				String imgpath = FileUploadService.getFilePath(IPageConstants.TYPE_INTERACT, account.getOwner().getId(), String.valueOf(10003));
				String simgfileName = FileUploadService.createFileName(fileFileName);
				String result1 = FileUploadService.saveFile(file, imgpath, simgfileName);
				imgurl = result1;
			}

			LotteryPrize lp=new LotteryPrize();
			lp.setLid(lid);
			lp.setName(name);
			lp.setTotal(Integer.parseInt(total));
			lp.setPrice(Integer.parseInt(price));
			lp.setImg(imgurl);
			lp.setType(type);
			lp.setHydefault(hydefault);
			lp.setJf(Integer.parseInt(jf));
			lp.setHprice(Integer.parseInt(hb));
			lp.setWishing(wishing);
			lp.setAct_name(act_name);
			lp.setRemark(remark);
			lp.setHbgz(hbgz);
			lp.setHbkey(hbkey);
			lp.setProductid(productid);
			lp.setLink(link);
			
			long typeid = lotteryprizeMgr.addprize(lp);
			if (typeid > 0)
			{
				result = "Y";
			}
			else
			{
				result = "添加失败";
			}
		}

		return SUCCESS;
	}

	public String del() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "";
		long typeid = lotteryprizeMgr.delprize(id);
		if (typeid > 0)
		{
			result = "success";
		}
		else
		{
			result = "删除失败";
		}
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	public String updatePre() throws Exception
	{
		dto = new PrizeDto();
		LotteryPrize p = lotteryprizeMgr.findPrizeByLpid(id);
		dto.setPrize(p);
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		list=lotteryprizeMgr.findProductByTypeZ(account,ContentProduct.PRODUCT_SUBTYPE_K);
		return SUCCESS;
	}

	public String update() throws Exception
	{
		Pattern pattern=Pattern.compile("[0-9]*");
	    Matcher match1=pattern.matcher(total);
	    Matcher match2=pattern.matcher(price);
	    Matcher match3=pattern.matcher(jf);
	    
	    if(total==null ||total.trim().length()== 0){
	    	total="0";
	    }
	    if(price==null ||price.trim().length()== 0){
	    	price="0";
	    }
		if (StringUtils.isEmpty(name))
		{
			result = "名称不能为空";
		}else if(!StringUtils.isEmpty(total)){
			if(!match1.matches()){
				result = "数量必须为数字";
			}else if(!StringUtils.isEmpty(price)){
				if(!match2.matches()){
					result = "价格必须为数字";
				}
			}
		}
		if("J".equalsIgnoreCase(type)){
			if(!match3.matches()){
				result = "类型为积分的奖品,积分数量必须为数字";
			}
		}else{
			jf="0";
		}
		if("M".equalsIgnoreCase(type)||"H".equalsIgnoreCase(type)){
			if(StringUtils.isEmpty(hb)){
				hb="0";
			}
			if(!pattern.matcher(hb).matches()){
				result = "类型为红包的奖品,红包数量必须为数字";
			}
			if(StringUtils.isEmpty(wishing)||StringUtils.isEmpty(act_name)||StringUtils.isEmpty(remark)){
				result = "祝福语,活动名称,备注不能为空";
			}
			if("M".equalsIgnoreCase(type)){
				if(hbgz == 0 && StringUtils.isBlank(hbkey)){
					result = "关注领取或者填写红包口令";
				}
			}
		}else{
			hb="0";
		}
		if("Z".equalsIgnoreCase(type)){
			if(productid<=0){
				result = "未选择自提商品";
			}
		}else{
			productid=0;
		}
		if(StringUtils.isEmpty(result))
		{
			String imgurl = null;
			if (file != null)
			{
				Account account = (Account) ActionContext.getContext().getSession().get("account");
				String imgpath = FileUploadService.getFilePath(IPageConstants.TYPE_INTERACT, account.getOwner().getId(), String.valueOf(10003));
				String simgfileName = FileUploadService.createFileName(fileFileName);
				String result1 = FileUploadService.saveFile(file, imgpath, simgfileName);
				imgurl = result1;
			}
			else
			{
				imgurl = fileurl;
			}
			LotteryPrize lp=new LotteryPrize();
			lp.setId(id);
			lp.setName(name);
			lp.setTotal(Integer.parseInt(total));
			lp.setPrice(Integer.parseInt(price));
			lp.setImg(imgurl);
			lp.setType(type);
			lp.setHydefault(hydefault);
			lp.setJf(Integer.parseInt(jf));
			lp.setHprice(Integer.parseInt(hb));
			lp.setWishing(wishing);
			lp.setAct_name(act_name);
			lp.setRemark(remark);
			lp.setHbgz(hbgz);
			lp.setHbkey(hbkey);
			lp.setProductid(productid);
			lp.setLink(link);
			long typeid = lotteryprizeMgr.updateprize(lp);
			if (typeid > 0)
			{
				result = "Y";
			}
			else
			{
				result = "修改失败";
			}
		}
		return SUCCESS;
	}

	public String saveLotteryCodePre(){
		return SUCCESS;
	}
	
	public String uploadLotteryPrizeCodeSubmit()
	{
		String fileType = getCouponsCodeUpContentType();
		if (couponsCodeUp == null)
		{
			messageError = "请选择上传的文件";
			return ERROR;
		}
		Integer i=0;
		Map<String, Object> map = getCouponsCodeList(fileType, id,i);
		if (map.get("error") != null)
		{
			return ERROR;
		}
		else
		{
			try
			{
				lotteryprizeMgr.addLotteryPrizeCode((List<LotteryPrizeCode>) map.get("success"), id);
				return SUCCESS;
			}
			catch (DataAccessException e)
			{
				messageError = "上传重复的券号,请检查券号是否有重复或者以前上传过";
				return ERROR;
			}
		}
	}

	private Map<String, Object> getCouponsCodeList(String fileType, long couponid,Integer dupcount)
	{

		Map<String, Object> map = new HashMap<String, Object>();
		if (fileType.equals("text/plain"))
		{
			List<LotteryPrizeCode> list = new ArrayList<LotteryPrizeCode>();
			try
			{
				BufferedReader bw = new BufferedReader(new FileReader(couponsCodeUp));
				String line = null;
				String line1 = null;
				String[] str = null;
				int flag = 0;
				int length=0;
				LotteryPrizeCode cd = new LotteryPrizeCode();
				cd.setLpid(couponid);
				line1 = bw.readLine().trim();
				if (line1.indexOf(",") != -1)
				{
					flag = 1;
					str = line1.split(",");
					cd.setCode(str[0]);
					cd.setPassword(str[1]);
				}
				else
				{
					flag = 0;
					cd.setCode(line1);
				}
				length=line1.length();
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
						boolean isdup=false;
						for (LotteryPrizeCode c : list)
						{
							if (line.equals(c.getCode()))
							{
								dupcount++;
								isdup=true;
							}
						}
						if(!isdup){
							length=line.length();
							LotteryPrizeCode c = new LotteryPrizeCode();
							c.setLpid(couponid);
							c.setCode(line);
							list.add(c);
						}
					}
					else
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
						boolean isdup=false;
						for (LotteryPrizeCode c : list)
						{
							if (str[0].equals(c.getCode()))
							{
								dupcount++;
								isdup=true;
							}
						}
						if(!isdup){
							LotteryPrizeCode c = new LotteryPrizeCode();
							c.setLpid(couponid);
							c.setCode(str[0]);
							c.setPassword(str[1]);
							list.add(c);
						}
					}
				}
				bw.close();
				map.put("success", list);
				return map;
			}
			catch (IOException e)
			{
				messageError = "IO异常";
				map.put("error", messageError);
				return map;
			}
			catch (DataAccessException e)
			{
				messageError = "上传重复的券号,请检查券号是否有重复或者以前上传过";
				map.put("error", messageError);
				e.printStackTrace();
				return map;
			}catch (Exception e) {
				messageError = "上传出错,可能是包含中文符号或者系统不识别字符.";
				map.put("error", messageError);
				e.printStackTrace();
				return map;
			
			}

		}
		else
		{
			messageError = "您只能上传txt类型的文本文件";
			map.put("error", messageError);
			return map;
		}
	}

	public String findLotteryCodePre() {
		dto = (PrizeDto) lotteryprizeMgr.findLotteryCodeListByLpid(id,pageId);
		return SUCCESS;
	}
	
	public String findPrizeCode() {
		dto = (PrizeDto) lotteryprizeMgr.findLotteryCodeByLpidAndCode(id,code);
		return SUCCESS;
	}
	
	public String prizeManageDoc()throws Exception{
		return SUCCESS;
	}
	
	public PrizeDto getDto()
	{
		return dto;
	}

	public void setDto(PrizeDto dto)
	{
		this.dto = dto;
	}

	public long getLid()
	{
		return lid;
	}

	public void setLid(long lid)
	{
		this.lid = lid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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




	public String getTotal()
	{
		return total;
	}

	public void setTotal(String total)
	{
		this.total = total;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price = price;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getHydefault()
	{
		return hydefault;
	}

	public void setHydefault(String hydefault)
	{
		this.hydefault = hydefault;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public void setLotteryprizeMgr(ILotteryPrizeMgr lotteryprizeMgr)
	{
		this.lotteryprizeMgr = lotteryprizeMgr;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
	}

	public String getFileFileName()
	{
		return fileFileName;
	}

	public void setFileFileName(String fileFileName)
	{
		this.fileFileName = fileFileName;
	}

	public String getFileContentType()
	{
		return fileContentType;
	}

	public void setFileContentType(String fileContentType)
	{
		this.fileContentType = fileContentType;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public String getReturnType()
	{
		return returnType;
	}

	public void setReturnType(String returnType)
	{
		this.returnType = returnType;
	}

	public String getFileurl()
	{
		return fileurl;
	}

	public void setFileurl(String fileurl)
	{
		this.fileurl = fileurl;
	}

	public int getTypeCheck()
	{
		return typeCheck;
	}

	public void setTypeCheck(int typeCheck)
	{
		this.typeCheck = typeCheck;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public String getJf()
	{
		return jf;
	}

	public void setJf(String jf)
	{
		this.jf = jf;
	}

	public String getHb() {
		return hb;
	}

	public void setHb(String hb) {
		this.hb = hb;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	public String getCode()
	{
		return code;
	}

	
	public void setCode(String code)
	{
		this.code = code;
	}
	
	public int getHbgz()
	{
		return hbgz;
	}

	
	public void setHbgz(int hbgz)
	{
		this.hbgz = hbgz;
	}

	public String getHbkey()
	{
		return hbkey;
	}

	
	public void setHbkey(String hbkey)
	{
		this.hbkey = hbkey;
	}

	
	public long getProductid()
	{
		return productid;
	}

	
	public void setProductid(long productid)
	{
		this.productid = productid;
	}

	
	public List<ContentProduct> getList()
	{
		return list;
	}

	
	public void setList(List<ContentProduct> list)
	{
		this.list = list;
	}

	
	public String getLink()
	{
		return link;
	}

	
	public void setLink(String link)
	{
		this.link = link;
	}
	
	public String nine_prize() throws Exception{
		return SUCCESS;
	}

}
