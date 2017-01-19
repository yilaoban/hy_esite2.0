package com.huiyee.individual.action;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.individual.dto.SecurityCodeRecordDto;
import com.huiyee.individual.mgr.ISecurityCodeRecordMgr;
import com.huiyee.individual.model.SecurityCodeRecord;

public class SecurityCodeRecordAction extends AbstractAction
{
	/**
	 * /WEB-INF/pagefeature/feature_mgr${featureid}.jsp
	 */
	private static final long serialVersionUID = 1L;
	private long pageid;
	private long entityid;
	private int type;
	private String code1;
	private String code2;
	private String code3;
	private String code4;
	private String phone;
	private String address;
	private ISecurityCodeRecordMgr securityCodeRecordMgr;
	private long id;
	private String status;
	private int payed;
	private String result;
	private long featureid;
	private String starttime;
	private String endtime;
	private String submitstarttime;
	private String submitendtime;
	private String edt;
	private String cmp;
	private String fld;
	private SecurityCodeRecordDto dto;
	
	public String getStarttime()
	{
		return starttime;
	}
	public void setStarttime(String starttime)
	{
		this.starttime = starttime;
	}
	public String getEndtime()
	{
		return endtime;
	}
	public void setEndtime(String endtime)
	{
		this.endtime = endtime;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public int getPayed()
	{
		return payed;
	}
	public void setPayed(int payed)
	{
		this.payed = payed;
	}
	public ISecurityCodeRecordMgr getSecurityCodeRecordMgr()
	{
		return securityCodeRecordMgr;
	}
	public void setSecurityCodeRecordMgr(ISecurityCodeRecordMgr securityCodeRecordMgr)
	{
		this.securityCodeRecordMgr = securityCodeRecordMgr;
	}
	
	public String updateStautsPre()throws Exception{
		return SUCCESS;
	}
	
	/**
	 * 提交信息
	 * @return
	 * @throws Exception
	 */
	public String updatePayerPre()throws Exception{
		return SUCCESS;
	}
	
	public String updateStauts()throws Exception{
		int len=securityCodeRecordMgr.updateSecurityCodeRecordStatus(id, status);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String updatePayed()throws Exception{
		int len=securityCodeRecordMgr.updateSecurityCodeRecordPayed(id, payed);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(len);
		out.flush();
		out.close();
		return null;
	}
	
	public String selected()throws Exception{
		dto=(SecurityCodeRecordDto) securityCodeRecordMgr.findInfo(edt,cmp,fld, starttime, endtime,submitstarttime,submitendtime);
		return SUCCESS;
	}
	
	public String getResult()
	{
		return result;
	}
	public void setResult(String result)
	{
		this.result = result;
	}
	
	
	public static void main(String[] args)
	{
		Pattern regexemail = Pattern.compile("^[\\d]{11}$");
        Matcher matcheremail = regexemail.matcher("");
        System.out.println(matcheremail.matches());
	}
	
	public String saveUser() throws Exception {
        Pattern regexemail = Pattern.compile("^[\\d]{11}$");
        Matcher matcheremail = regexemail.matcher(phone);
        if(matcheremail.matches() == false || address == null || "".equals(address)){
        	return null;
        }
        String[] str ={code1,code2,code3,code4};
        int l =0;
        for(int i=0; i<str.length;i++){
        	if(str[i]==null || "".equals(str[i])){
        		l++;
        	}
        }
        if(l==1 || l==3 || 1 == 4){
        	return null;
        }
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		int num =0;
		if(code4 != null){
			num =4;
		}else{
			num =2;
		}
		System.out.println("num :"+num);
		if(HyConfig.isRun()){
			pw.print(1);
		}else{
			VisitUser visit = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
			if(visit != null){
					entityid = visit.getUid();
					type = visit.getCd();
				SecurityCodeRecord scr = securityCodeRecordMgr.findbyUser(entityid,type);
				if(scr == null){  //直接插
					System.out.println("没有记录    为null");
					int p = securityCodeRecordMgr.saveUser(visit.getPageid(),entityid,type,code1,code2,code3,code4,phone,address);
					pw.print(p);
				}else if("FLD".equals(scr.getStatus()) || num==4){  // 4个都替换\
					System.out.println("有记录都替换");
					int p =securityCodeRecordMgr.updateUser(code1,code2,code3,code4,entityid,phone,address);
					pw.print(p);
				}else{  //替换 1  2
					int p = securityCodeRecordMgr.updateUser(code1,code2,entityid,num,scr,phone,address);
					pw.print(p);
				}
			}			
		}
		pw.flush();
		pw.close();
		return null;
	}


	public long getPageid()
	{
		return pageid;
	}


	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}


	public long getEntityid()
	{
		return entityid;
	}


	public void setEntityid(long entityid)
	{
		this.entityid = entityid;
	}


	public int getType()
	{
		return type;
	}


	public void setType(int type)
	{
		this.type = type;
	}


	public String getCode1()
	{
		return code1;
	}


	public void setCode1(String code1)
	{
		this.code1 = code1;
	}


	public String getCode2()
	{
		return code2;
	}


	public void setCode2(String code2)
	{
		this.code2 = code2;
	}


	public String getCode3()
	{
		return code3;
	}


	public void setCode3(String code3)
	{
		this.code3 = code3;
	}


	public String getCode4()
	{
		return code4;
	}


	public void setCode4(String code4)
	{
		this.code4 = code4;
	}


	public String getPhone()
	{
		return phone;
	}


	public void setPhone(String phone)
	{
		this.phone = phone;
	}


	public String getAddress()
	{
		return address;
	}


	public void setAddress(String address)
	{
		this.address = address;
	}
	public SecurityCodeRecordDto getDto()
	{
		return dto;
	}
	public void setDto(SecurityCodeRecordDto dto)
	{
		this.dto = dto;
	}
	public long getFeatureid()
	{
		return featureid;
	}
	public void setFeatureid(long featureid)
	{
		this.featureid = featureid;
	}
	public String getSubmitstarttime()
	{
		return submitstarttime;
	}
	public void setSubmitstarttime(String submitstarttime)
	{
		this.submitstarttime = submitstarttime;
	}
	public String getSubmitendtime()
	{
		return submitendtime;
	}
	public void setSubmitendtime(String submitendtime)
	{
		this.submitendtime = submitendtime;
	}
	public String getEdt()
	{
		return edt;
	}
	public void setEdt(String edt)
	{
		this.edt = edt;
	}
	public String getCmp()
	{
		return cmp;
	}
	public void setCmp(String cmp)
	{
		this.cmp = cmp;
	}
	public String getFld()
	{
		return fld;
	}
	public void setFld(String fld)
	{
		this.fld = fld;
	}
	
	

}
