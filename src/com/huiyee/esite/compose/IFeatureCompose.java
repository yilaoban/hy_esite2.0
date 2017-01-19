package com.huiyee.esite.compose;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.dto.DynamicActionDto;
import com.huiyee.esite.dto.ExportDto;
import com.huiyee.esite.dto.FeatureMgrDto;
import com.huiyee.esite.dto.HdDetailDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.QueryObject;

public interface IFeatureCompose {
	
	/**
	 * 显示配置页面
	 * @param featureid
	 * @param fid
	 * @param account
	 * @return
	 */
	public IDto config(long featureid, long fid,Account account);
	
	public IDto configNew(long featureid, long fid,Account account,long relationid);
	/**
	 * 配置提交
	 * @param featureid
	 * @param dto
	 * @param account
	 * @return
	 */
	public String configSub(long featureid,IDto dto, Account account);
	
	public String configSubNew(long featureid,IDto dto, Account account);
	
	/**
	 * 页面上线
	 * @param pageid
	 * @return
	 */
	public int pub(long pageid);
	
	/**
	 * 页面预览
	 * @param pageid
	 * @return
	 */
	public Map<String,Object> show(long pageid,String type,String source);
	
	/**
	 * 删除feature
	 * @param pfid es_page_feature 表的 id
	 * @return
	 */
	public int delete(long pfid);
	
	/**
	 * 显示增加
	 * @param pageid
	 * @return
	 */
	public IDto add(long pageid,long ownerid);
	
	/**
	 * 增加保存
	 * @param pageid
	 * @param dto
	 * @return
	 */
	public int addSub(long pageid,FeatureMgrDto dto);
	
	/**
	 * 向上
	 * @param pfid
	 * @return
	 */
	public int up(long pfid);
	
	/**
	 * 向下
	 * @param pfid
	 * @return
	 */
	public int down(long pfid);
	
	/**
	 * 用户互动
	 * @param featureid
	 * @param uid
	 * @param dto
	 * @return
	 */
	public String dynamicAction(long featureid,long uid,DynamicActionDto dto);
	
	/**
	 * 根据pageid fid 找到对应的ownerid
	 * @param pageid
	 * @param fid
	 * @return
	 */
	public long findOwneridByPageidAndFid(long pageid,long fid);

	/**
	 * 互动报告导出
	 * @param featureid
	 * @param sitegroupid
	 * @param id
	 * @param exportDto 
	 * @return
	 */
	public List<String> export(long featureid, long sitegroupid, long id, ExportDto exportDto);
	
	public int pubBySiteId(long siteid);
	
	public IDto composeHdReport(long siteid,long ownerid);
	
	public IDto composeHdReportDetail(long sitegroupid,long hdid,long hdfid,int pageId);
	
	public IDto composeQueryHdReportDetail(long sitegroupid,long hdid,long hdfid,HdDetailDto dto,int pageId);
	
	public long composeCreatePage(String pagename, String jspname, long siteid,String stype);

	
}
