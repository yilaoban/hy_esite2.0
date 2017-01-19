
package com.huiyee.interact.appointment.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.interact.appointment.dto.AddOrderDto;
import com.huiyee.interact.appointment.mgr.IInteractAptManager;
import com.huiyee.interact.appointment.model.AppointmentMappingModel;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.OrderMappingModel;
import com.huiyee.interact.lottery.model.Lottery;

public class AddOrderAction extends InteractModelAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3288641682594128602L;

	private long id;
	private String result;
	private AddOrderDto dto;
	private long mid = 10000;
	private long resultid;
	private long aptid;
	private List<String> defaultvalue;
	private List<String> isshow;
	private List<Integer> row;
	private AppointmentModel apt;
	private AppointmentMappingModel mappingmodel;
	private File pic;
	private String picFileName;
	private String picContentType;
	private File repic;
	private String repicFileName;
	private String repicContentType;
	public static List<String> mappingList;
	private String newMappingStr;
	private File sharepic;
	private String sharepicFileName;
	private String sharepicContentType;
	private String sharepicimage;
	private List<Lottery> zlotteryList;
	private List<Lottery> glotteryList;
	private List<Lottery> llotteryList;
	private List<Lottery> ylotteryList;
	private Lottery lottery;

	private IInteractAptManager interactAptManager;
	private String picimage;
	private String repicimage;

	private String titlename;

	private List<OrderMappingModel> subSys;// 固定字段
	private List<OrderMappingModel> subUdf;// 自定义字段
	private String refUrl;

	@Override
	public void init() throws Exception
	{
		super.init();
		mappingList = new ArrayList<String>();
		for (int i = 1; i < 31; i++)
		{
			mappingList.add("f" + i);
		}
	}

	public String getSharepicimage()
	{
		return sharepicimage;
	}

	public void setSharepicimage(String sharepicimage)
	{
		this.sharepicimage = sharepicimage;
	}

	public List<Lottery> getYlotteryList()
	{
		return ylotteryList;
	}

	public void setYlotteryList(List<Lottery> ylotteryList)
	{
		this.ylotteryList = ylotteryList;
	}

	public Lottery getLottery()
	{
		return lottery;
	}

	public void setLottery(Lottery lottery)
	{
		this.lottery = lottery;
	}

	public File getSharepic()
	{
		return sharepic;
	}

	public List<Lottery> getZlotteryList()
	{
		return zlotteryList;
	}

	public void setZlotteryList(List<Lottery> zlotteryList)
	{
		this.zlotteryList = zlotteryList;
	}

	public List<Lottery> getGlotteryList()
	{
		return glotteryList;
	}

	public void setGlotteryList(List<Lottery> glotteryList)
	{
		this.glotteryList = glotteryList;
	}

	public List<Lottery> getLlotteryList()
	{
		return llotteryList;
	}

	public void setLlotteryList(List<Lottery> llotteryList)
	{
		this.llotteryList = llotteryList;
	}

	public void setSharepic(File sharepic)
	{
		this.sharepic = sharepic;
	}

	public String getSharepicFileName()
	{
		return sharepicFileName;
	}

	public void setSharepicFileName(String sharepicFileName)
	{
		this.sharepicFileName = sharepicFileName;
	}

	public String getSharepicContentType()
	{
		return sharepicContentType;
	}

	public void setSharepicContentType(String sharepicContentType)
	{
		this.sharepicContentType = sharepicContentType;
	}

	public File getRepic()
	{
		return repic;
	}

	public void setRepic(File repic)
	{
		this.repic = repic;
	}

	public String getRepicFileName()
	{
		return repicFileName;
	}

	public void setRepicFileName(String repicFileName)
	{
		this.repicFileName = repicFileName;
	}

	public String getRepicContentType()
	{
		return repicContentType;
	}

	public void setRepicContentType(String repicContentType)
	{
		this.repicContentType = repicContentType;
	}

	public File getPic()
	{
		return pic;
	}

	public void setPic(File pic)
	{
		this.pic = pic;
	}

	public String getPicFileName()
	{
		return picFileName;
	}

	public void setPicFileName(String picFileName)
	{
		this.picFileName = picFileName;
	}

	public String getPicContentType()
	{
		return picContentType;
	}

	public void setPicContentType(String picContentType)
	{
		this.picContentType = picContentType;
	}

	public List<String> getDefaultvalue()
	{
		return defaultvalue;
	}

	public void setDefaultvalue(List<String> defaultvalue)
	{
		this.defaultvalue = defaultvalue;
	}

	public List<String> getIsshow()
	{
		return isshow;
	}

	public void setIsshow(List<String> isshow)
	{
		this.isshow = isshow;
	}

	public List<Integer> getRow()
	{
		return row;
	}

	public void setRow(List<Integer> row)
	{
		this.row = row;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public AddOrderDto getDto()
	{
		return dto;
	}

	public void setDto(AddOrderDto dto)
	{
		this.dto = dto;
	}

	public long getResultid()
	{
		return resultid;
	}

	public void setResultid(long resultid)
	{
		this.resultid = resultid;
	}

	public AppointmentModel getApt()
	{
		return apt;
	}

	public void setApt(AppointmentModel apt)
	{
		this.apt = apt;
	}

	public AppointmentMappingModel getMappingmodel()
	{
		return mappingmodel;
	}

	public long getAptid()
	{
		return aptid;
	}

	public void setAptid(long aptid)
	{
		this.aptid = aptid;
	}

	public void setMappingmodel(AppointmentMappingModel mappingmodel)
	{
		this.mappingmodel = mappingmodel;
	}

	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		zlotteryList = interactAptManager.findLotteryByType(ownerid, "Z");
		glotteryList = interactAptManager.findLotteryByType(ownerid, "G");
		llotteryList = interactAptManager.findLotteryByType(ownerid, "L");
		ylotteryList = interactAptManager.findLotteryByType(ownerid, "Y");
		return SUCCESS;
	}

	private String createFileName(String fileName, String type)
	{
		int index = fileName.lastIndexOf('.');
		// 判断上传文件名是否有扩展名
		if (index != -1)
		{
			return FileUploadService.getNow() + type + fileName.substring(index);
		}
		return FileUploadService.getNow() + type;
	}

	// 新增
	public String addOrder() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if ("Z".equals(dto.getType()))
		{
			apt.setLotteryid(dto.getZlotteryid());
		} else if ("G".equals(dto.getType()))
		{
			apt.setLotteryid(dto.getGlotteryid());
		} else if ("L".equals(dto.getType()))
		{
			apt.setLotteryid(dto.getLlotteryid());
		} else if ("Y".equals(dto.getType()))
		{
			apt.setLotteryid(dto.getYlotteryid());
		}
		resultid = interactAptManager.addOrderMes(apt, ownerid, mid, subSys, subUdf, aptid, IPageConstants.OWNER_INTERACT_SHOW);
		interactAptManager.updateRuletypeByLotteryid(apt.getLotteryid());
		if (resultid > 0)
		{
			result = "Y";
		}
		return SUCCESS;
	}

	// 编辑
	public String updateOrderPre() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = new AddOrderDto();
		apt = interactAptManager.findOrderMesNew(ownerid, id);
		dto.setAm(apt);
		if (apt != null && apt.getLotteryid() != 0)
		{
			lottery = interactAptManager.findLotteryById(apt.getLotteryid());
			if (lottery != null && lottery.getType() != null)
			{
				dto.setType(lottery.getType());
			}
		}
		zlotteryList = interactAptManager.findLotteryByType(ownerid, "Z");
		glotteryList = interactAptManager.findLotteryByType(ownerid, "G");
		llotteryList = interactAptManager.findLotteryByType(ownerid, "L");
		ylotteryList = interactAptManager.findLotteryByType(ownerid, "Y");
		return SUCCESS;
	}

	// 编辑。保存
	public String updateOrderSave() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if ("Z".equals(dto.getType()))
		{
			apt.setLotteryid(dto.getZlotteryid());
		} else if ("G".equals(dto.getType()))
		{
			apt.setLotteryid(dto.getGlotteryid());
		} else if ("L".equals(dto.getType()))
		{
			apt.setLotteryid(dto.getLlotteryid());
		} else if ("Y".equals(dto.getType()))
		{
			apt.setLotteryid(dto.getYlotteryid());
		}
		interactAptManager.updateRuletypeByLotteryid(apt.getLotteryid());
		int len = interactAptManager.updateOrderMes(apt, aptid, subSys, subUdf);
		if (len == 1)
		{
			result = "Y";
		}
		if (redirectCb != 0)
			return "toCb";
		return SUCCESS;
	}
	
	
	
	// 编辑。保存
	public String updateAptSub() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		Calendar c = Calendar.getInstance();
		apt.setStarttimeDate(c.getTime());
		c.add(Calendar.YEAR, 1);
		apt.setEndtimeDate(c.getTime());
		apt.setTotallimit(1);
		apt.setDaylimit(1);
		apt.setIslottery("N");
		int len = interactAptManager.updateOrderMes(apt, apt.getId(), subSys,subUdf);

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		jo.put("status", len);
		if(refUrl!=null&&refUrl.trim().length()>0){
			jo.put("redirect", refUrl);
		}
		out.write(jo.toString());
		out.flush();
		out.close();
		return null;

	}

	// 删除
	public String deleteOrderMes() throws Exception
	{
		int len = interactAptManager.deleteOrderMes(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(len);
		out.flush();
		out.close();
		return null;
	}

	public String findAptMapping() throws Exception
	{
		String mapping = interactAptManager.findAptMapping(aptid, newMappingStr);
		mapping = mapping == null ? "OUT" : mapping;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(mapping);
		out.flush();
		out.close();
		return null;
	}

	// 卡片新增申请
	public String addAppointment() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		long len = interactAptManager.addAppointment(mid, ownerid, titlename);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(len);
		out.flush();
		out.close();
		return null;
	}

	public String helpAppointment() throws Exception
	{
		return SUCCESS;
	}

	public long getMid()
	{
		return mid;
	}

	public void setMid(long mid)
	{
		this.mid = mid;
	}

	public String getNewMappingStr()
	{
		return newMappingStr;
	}

	public void setNewMappingStr(String newMappingStr)
	{
		this.newMappingStr = newMappingStr;
	}

	public void setInteractAptManager(IInteractAptManager interactAptManager)
	{
		this.interactAptManager = interactAptManager;
	}

	public String getPicimage()
	{
		return picimage;
	}

	public void setPicimage(String picimage)
	{
		this.picimage = picimage;
	}

	public String getRepicimage()
	{
		return repicimage;
	}

	public void setRepicimage(String repicimage)
	{
		this.repicimage = repicimage;
	}

	public String getTitlename()
	{
		return titlename;
	}

	public void setTitlename(String titlename)
	{
		this.titlename = titlename;
	}

	public List<OrderMappingModel> getSubSys()
	{
		return subSys;
	}

	public void setSubSys(List<OrderMappingModel> subSys)
	{
		this.subSys = subSys;
	}

	public List<OrderMappingModel> getSubUdf()
	{
		return subUdf;
	}

	public void setSubUdf(List<OrderMappingModel> subUdf)
	{
		this.subUdf = subUdf;
	}

	public String getRefUrl()
	{
		return refUrl;
	}

	public void setRefUrl(String refUrl)
	{
		this.refUrl = refUrl;
	}

}
