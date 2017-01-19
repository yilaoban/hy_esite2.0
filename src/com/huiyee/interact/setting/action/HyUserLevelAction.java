package com.huiyee.interact.setting.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.BalanceSet;
import com.huiyee.interact.setting.dto.SettingDto;
import com.huiyee.interact.setting.mgr.IHyUserLevelCodeMgr;
import com.huiyee.interact.setting.mgr.IHyUserLevelMgr;
import com.huiyee.interact.setting.mgr.IUserJfMgr;
import com.huiyee.interact.setting.model.HyUserLevel;
import com.huiyee.interact.setting.model.HyUserLevelCode;

public class HyUserLevelAction extends AbstractAction {
	private static final long serialVersionUID = 7746644779008424195L;
	private IHyUserLevelMgr hyUserLevelMgr;
	private IUserJfMgr userJfMgr;
	private IHyUserLevelCodeMgr hyUserLevelCodeMgr;

	private HyUserLevel level;
	private List<HyUserLevel> levelList;
	private int lightType = 2;
	private long levelid;
	private long hyuid;
	private BalanceSet balanceSet;
	private int hylevel;
	private float totalnum;
	private float dannum;
	private float usednum;

	private int pageId = 1;
	private SettingDto dto;
	private File media;
	private String mediaFileName;
	private HyUserLevelCode code;

	public void setUserJfMgr(IUserJfMgr userJfMgr) {
		this.userJfMgr = userJfMgr;
	}

	public void setHyUserLevelMgr(IHyUserLevelMgr hyUserLevelMgr) {
		this.hyUserLevelMgr = hyUserLevelMgr;
	}

	public void setHyUserLevelCodeMgr(IHyUserLevelCodeMgr hyUserLevelCodeMgr) {
		this.hyUserLevelCodeMgr = hyUserLevelCodeMgr;
	}

	@Override
	public String execute() throws Exception {
		balanceSet = userJfMgr.findBalanceSetByOwner(this.getOwner());
		if (balanceSet != null && balanceSet.getHylevel() > 0) {
			levelList = hyUserLevelMgr.findHyUserLevelByOwner(this.getOwner());
			return SUCCESS;
		} else {
			return "fail";
		}
	}

	public String preuserLevel() throws Exception {
		balanceSet = userJfMgr.findBalanceSetByOwner(this.getOwner());
		return SUCCESS;
	}

	public String addUserLevel() throws Exception {
		return SUCCESS;
	}

	public String saveUserLevel() throws Exception {
		if (level != null) {
			level.setTotalnum((int) (totalnum * 100));
			level.setDannum((int) (dannum * 100));
			level.setUsednum((int) (usednum * 100));
		}
		hyUserLevelMgr.saveUserLevel(level, this.getOwner(), hylevel);
		return SUCCESS;
	}

	public String editUserLevel() throws Exception {
		level = hyUserLevelMgr.findHyUserLevelById(levelid, this.getOwner());
		return SUCCESS;
	}

	public String updateUserLevel() throws Exception {
		if (level != null) {
			level.setTotalnum((int) (totalnum * 100));
			level.setDannum((int) (dannum * 100));
			level.setUsednum((int) (usednum * 100));
		}
		hyUserLevelMgr.updateUserLevelById(level, hylevel);
		return SUCCESS;
	}

	public void delUserLevel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int res = hyUserLevelMgr.delUserLevel(levelid, this.getOwner());
		out.print(res);
		out.flush();
		out.close();
	}

	public void confirmUserLevel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int res = hyUserLevelMgr.updateHyUserByHyuid(hyuid, levelid);
		out.print(res);
		out.flush();
		out.close();
	}

	public String userLevelCode() throws Exception {
		if (code == null) {
			return null;
		}
		dto = new SettingDto();
		int total = hyUserLevelCodeMgr.findCodeCount(code);
		if (total > 0) {
			int rows = 10;
			int start = (pageId - 1) * rows;
			List<HyUserLevelCode> codeList = hyUserLevelCodeMgr.findCodeList(code, start, rows);
			dto.setCodeList(codeList);
			dto.setPager(new Pager(pageId, total, rows));
		}
		return SUCCESS;
	}

	public String uploadLevelCode() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			if (code == null) {
				return null;
			}
			List<String> codes = new ArrayList<String>();
			String fileExt = mediaFileName.substring(mediaFileName.indexOf("."));
			if (".xls".equalsIgnoreCase(fileExt)) {
				InputStream is = new FileInputStream(media);
				HSSFWorkbook workbook = new HSSFWorkbook(is);
				HSSFSheet sheet = workbook.getSheetAt(workbook.getActiveSheetIndex());
				for (int i = 0; i <= sheet.getLastRowNum(); i++) {
					HSSFRow row = sheet.getRow(i);
					if (row != null) {
						HSSFCell cell = row.getCell(0);// 取第一列
						if (cell != null) {
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							String code = cell.getStringCellValue();
							if (StringUtils.isNotBlank(code)) {
								codes.add(code);
							}
						}
					}
				}
				is.close();
			} else if (".xlsx".equalsIgnoreCase(fileExt)) {
				InputStream is = new FileInputStream(media);
				XSSFWorkbook workbook = new XSSFWorkbook(is);
				XSSFSheet sheet = workbook.getSheetAt(workbook.getActiveSheetIndex());
				for (int i = 0; i <= sheet.getLastRowNum(); i++) {
					XSSFRow row = sheet.getRow(i);
					if (row != null) {
						XSSFCell cell = row.getCell(0);// 取第一列
						if (cell != null) {
							cell.setCellType(XSSFCell.CELL_TYPE_STRING);
							String code = cell.getStringCellValue();
							if (StringUtils.isNotBlank(code)) {
								codes.add(code);
							}
						}
					}
				}
				is.close();
			} else if (".txt".equalsIgnoreCase(fileExt)) {
				InputStream is = new FileInputStream(media);
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String str = null;
				while ((str = br.readLine()) != null) {
					String code = str.trim();
					if (StringUtils.isNotBlank(code)) {
						codes.add(code);
					}
				}
				br.close();
				isr.close();
				is.close();
			} else {
				out.print("{\"errcode\":-1,\"errmsg\":\"file type no support\"}");
				return null;
			}

			// save code
			if (codes.size() == 0) {
				out.print("{\"errcode\":-2,\"errmsg\":\"empty code list\"}");
			} else {
				int[] res = hyUserLevelCodeMgr.addCode(this.getOwner(), code.getLevelid(), codes);
				int count = 0;
				for (int re : res) {
					if (re > 0) {
						count++;
					}
				}
				out.print("{\"errcode\":0,\"count\":" + count + "}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String editLevelCode() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			if (code == null) {
				return null;
			}
			int res = hyUserLevelCodeMgr.updateCode(code);
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String delLevelCode() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("html/text;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			if (code == null) {
				return null;
			}
			int res = hyUserLevelCodeMgr.delCode(code.getId());
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public List<HyUserLevel> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<HyUserLevel> levelList) {
		this.levelList = levelList;
	}

	public int getLightType() {
		return lightType;
	}

	public void setLightType(int lightType) {
		this.lightType = lightType;
	}

	public long getLevelid() {
		return levelid;
	}

	public void setLevelid(long levelid) {
		this.levelid = levelid;
	}

	public HyUserLevel getLevel() {
		return level;
	}

	public void setLevel(HyUserLevel level) {
		this.level = level;
	}

	public long getHyuid() {
		return hyuid;
	}

	public void setHyuid(long hyuid) {
		this.hyuid = hyuid;
	}

	public BalanceSet getBalanceSet() {
		return balanceSet;
	}

	public void setBalanceSet(BalanceSet balanceSet) {
		this.balanceSet = balanceSet;
	}

	public int getHylevel() {
		return hylevel;
	}

	public void setHylevel(int hylevel) {
		this.hylevel = hylevel;
	}

	public float getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(float totalnum) {
		this.totalnum = totalnum;
	}

	public float getDannum() {
		return dannum;
	}

	public void setDannum(float dannum) {
		this.dannum = dannum;
	}

	public float getUsednum() {
		return usednum;
	}

	public void setUsednum(float usednum) {
		this.usednum = usednum;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public SettingDto getDto() {
		return dto;
	}

	public void setDto(SettingDto dto) {
		this.dto = dto;
	}

	public File getMedia() {
		return media;
	}

	public void setMedia(File media) {
		this.media = media;
	}

	public String getMediaFileName() {
		return mediaFileName;
	}

	public void setMediaFileName(String mediaFileName) {
		this.mediaFileName = mediaFileName;
	}

	public HyUserLevelCode getCode() {
		return code;
	}

	public void setCode(HyUserLevelCode code) {
		this.code = code;
	}

}
