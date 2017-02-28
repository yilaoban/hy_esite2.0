package com.huiyee.esite.compose;

import java.io.File;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.RangeFacet;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.AreaDto;
import com.huiyee.esite.dto.CardDto;
import com.huiyee.esite.dto.ChooseContentDto;
import com.huiyee.esite.dto.CommunityDto;
import com.huiyee.esite.dto.ContentDto;
import com.huiyee.esite.dto.ContentFormDto;
import com.huiyee.esite.dto.ContentRecordDto;
import com.huiyee.esite.dto.CustomVisitDto;
import com.huiyee.esite.dto.DanymicAton;
import com.huiyee.esite.dto.DanymicRecordDetailDto;
import com.huiyee.esite.dto.DanymicRecordDto;
import com.huiyee.esite.dto.DanymicUserDetail;
import com.huiyee.esite.dto.DanymicUserSiftDto;
import com.huiyee.esite.dto.DaohangDto;
import com.huiyee.esite.dto.DataIndexDto;
import com.huiyee.esite.dto.DynamicActionDto;
import com.huiyee.esite.dto.EditPGDto;
import com.huiyee.esite.dto.FansAnalysisDto;
import com.huiyee.esite.dto.FansLevelAnalyseDto;
import com.huiyee.esite.dto.Feature149Dto;
import com.huiyee.esite.dto.FeatureActionDto;
import com.huiyee.esite.dto.HdAreaDto;
import com.huiyee.esite.dto.HdNumDataDto;
import com.huiyee.esite.dto.HdPerNumDataDto;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.dto.HdType;
import com.huiyee.esite.dto.HighChartsDataDto;
import com.huiyee.esite.dto.HourReportDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.InteractionActionDto;
import com.huiyee.esite.dto.LoadPageDto;
import com.huiyee.esite.dto.LoadPageIndexDto;
import com.huiyee.esite.dto.LoadPageInteractionDto;
import com.huiyee.esite.dto.LoadPageSiteCountDto;
import com.huiyee.esite.dto.LoginDto;
import com.huiyee.esite.dto.MarketActivityDto;
import com.huiyee.esite.dto.MaterialDto;
import com.huiyee.esite.dto.MbDto;
import com.huiyee.esite.dto.NewDataDto;
import com.huiyee.esite.dto.NewDetailDto;
import com.huiyee.esite.dto.OwnerDataDto;
import com.huiyee.esite.dto.OwnerSourceDto;
import com.huiyee.esite.dto.PageActionDto;
import com.huiyee.esite.dto.PageAddressDto;
import com.huiyee.esite.dto.PageConfigDto;
import com.huiyee.esite.dto.PageDto;
import com.huiyee.esite.dto.PageEditDto;
import com.huiyee.esite.dto.PageEditGDto;
import com.huiyee.esite.dto.PageRelationDto;
import com.huiyee.esite.dto.PageShowMaterialDto;
import com.huiyee.esite.dto.PageTemplateDto;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.dto.PersonalTailorDto;
import com.huiyee.esite.dto.PublishDto;
import com.huiyee.esite.dto.QueryDto;
import com.huiyee.esite.dto.ReportArea;
import com.huiyee.esite.dto.ReportHdNum;
import com.huiyee.esite.dto.ReportRecordDto;
import com.huiyee.esite.dto.ReportSource;
import com.huiyee.esite.dto.ReportTerminalAnalyse;
import com.huiyee.esite.dto.ReportViewAllDto;
import com.huiyee.esite.dto.RgAnalysisDto;
import com.huiyee.esite.dto.SiteCountDto;
import com.huiyee.esite.dto.SiteDataDto;
import com.huiyee.esite.dto.SiteDto;
import com.huiyee.esite.dto.SiteGroupDto;
import com.huiyee.esite.dto.SiteIndexActionDto;
import com.huiyee.esite.dto.SitePage;
import com.huiyee.esite.dto.SiteVistLogDto;
import com.huiyee.esite.dto.SolrDataDto;
import com.huiyee.esite.dto.SolrRecord;
import com.huiyee.esite.dto.SourceDto;
import com.huiyee.esite.dto.TemplateDto;
import com.huiyee.esite.dto.TerminalDataDto;
import com.huiyee.esite.dto.UserOauthDto;
import com.huiyee.esite.dto.VisitLogDto;
import com.huiyee.esite.dto.VisitNum;
import com.huiyee.esite.dto.VisitReportDetailDto;
import com.huiyee.esite.dto.VisitUserDto;
import com.huiyee.esite.dto.VisitViewAllDto;
import com.huiyee.esite.dto.ZanDetailDto;
import com.huiyee.esite.dto.ZujianDto;
import com.huiyee.esite.dto.showdto.Show118Dto;
import com.huiyee.esite.dto.showdto.Show123Dto;
import com.huiyee.esite.dto.showdto.Show124Dto;
import com.huiyee.esite.dto.showdto.Show125Dto;
import com.huiyee.esite.dto.showdto.Show126Dto;
import com.huiyee.esite.dto.showdto.Show134Dto;
import com.huiyee.esite.dto.showdto.Show151Dto;
import com.huiyee.esite.dto.showdto.Show153Dto;
import com.huiyee.esite.dto.showdto.Show154Dto;
import com.huiyee.esite.fmgr.IHdFeatureManager;
import com.huiyee.esite.mgr.IAccountManager;
import com.huiyee.esite.mgr.IActivityManager;
import com.huiyee.esite.mgr.IAppManager;
import com.huiyee.esite.mgr.IAreaManager;
import com.huiyee.esite.mgr.IBBSManager;
import com.huiyee.esite.mgr.IContentFormMgr;
import com.huiyee.esite.mgr.IContentManager;
import com.huiyee.esite.mgr.IContentRecordMgr;
import com.huiyee.esite.mgr.IEmotionsManager;
import com.huiyee.esite.mgr.IFansAnalyseManager;
import com.huiyee.esite.mgr.IFeatureManager;
import com.huiyee.esite.mgr.IFriendsShipsMgr;
import com.huiyee.esite.mgr.IHdRecordManager;
import com.huiyee.esite.mgr.IHdUserDataMgr;
import com.huiyee.esite.mgr.IHyUserMgr;
import com.huiyee.esite.mgr.IInteractModelManager;
import com.huiyee.esite.mgr.IMarketActivityMgr;
import com.huiyee.esite.mgr.IMaterialManager;
import com.huiyee.esite.mgr.IMbMgr;
import com.huiyee.esite.mgr.IPageCacheManager;
import com.huiyee.esite.mgr.IPageManager;
import com.huiyee.esite.mgr.IPageShowMaterialManager;
import com.huiyee.esite.mgr.ISinaCommentManager;
import com.huiyee.esite.mgr.ISinaForwardManager;
import com.huiyee.esite.mgr.ISinaShareManager;
import com.huiyee.esite.mgr.ISinaShareRecordManager;
import com.huiyee.esite.mgr.ISinaTokenMgr;
import com.huiyee.esite.mgr.ISinaUserManager;
import com.huiyee.esite.mgr.ISiteActionManager;
import com.huiyee.esite.mgr.ISiteManager;
import com.huiyee.esite.mgr.ISiteSourceManager;
import com.huiyee.esite.mgr.ITemplateManager;
import com.huiyee.esite.mgr.IUserMgr;
import com.huiyee.esite.mgr.IUserTagMgr;
import com.huiyee.esite.mgr.IUserUploadManager;
import com.huiyee.esite.mgr.IVisitMgr;
import com.huiyee.esite.mgr.IVisitReportManager;
import com.huiyee.esite.mgr.IWSManager;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Activity;
import com.huiyee.esite.model.ActivityLog;
import com.huiyee.esite.model.AppointmentRecord;
import com.huiyee.esite.model.AreaAnalysis;
import com.huiyee.esite.model.BBSCategory;
import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.Block2page;
import com.huiyee.esite.model.BlockContext;
import com.huiyee.esite.model.CardBackGround;
import com.huiyee.esite.model.CbActivity;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentForm;
import com.huiyee.esite.model.ContentFormMapping;
import com.huiyee.esite.model.ContentFormRecord;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.ContentRecord;
import com.huiyee.esite.model.ContentTab;
import com.huiyee.esite.model.ContentVideo;
import com.huiyee.esite.model.CustomVisitReport;
import com.huiyee.esite.model.EdgeBean;
import com.huiyee.esite.model.FansAnalyse;
import com.huiyee.esite.model.FansLevelAnalyse;
import com.huiyee.esite.model.Feature;
import com.huiyee.esite.model.HD149Model;
import com.huiyee.esite.model.HdModel;
import com.huiyee.esite.model.HourAnalyse;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.InteractModel;
import com.huiyee.esite.model.MarketingSet;
import com.huiyee.esite.model.Mb;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.MyTempalte;
import com.huiyee.esite.model.NodeBean;
import com.huiyee.esite.model.Owner;
import com.huiyee.esite.model.OwnerContentType;
import com.huiyee.esite.model.OwnerPrivilege;
import com.huiyee.esite.model.OwnerSource;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageAddress;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageCache;
import com.huiyee.esite.model.PageCard;
import com.huiyee.esite.model.PageFeature;
import com.huiyee.esite.model.PageHtml;
import com.huiyee.esite.model.PageRelation;
import com.huiyee.esite.model.PageSource;
import com.huiyee.esite.model.PageTemplate;
import com.huiyee.esite.model.ReportGenderAnalyse;
import com.huiyee.esite.model.SinaApp;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.SinaShare;
import com.huiyee.esite.model.SinaToken;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.SiteCount;
import com.huiyee.esite.model.SiteSource;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.model.Source;
import com.huiyee.esite.model.SuperHdModel;
import com.huiyee.esite.model.Tag;
import com.huiyee.esite.model.TemplateBlock;
import com.huiyee.esite.model.TemplateCard;
import com.huiyee.esite.model.TemplateCategory;
import com.huiyee.esite.model.TemplateModel;
import com.huiyee.esite.model.TemplateStyleModel;
import com.huiyee.esite.model.Terminal;
import com.huiyee.esite.model.UserInfo;
import com.huiyee.esite.model.UserPkvTag;
import com.huiyee.esite.model.UserTag;
import com.huiyee.esite.model.UserUpload;
import com.huiyee.esite.model.VisitAnalyse;
import com.huiyee.esite.model.VisitLog;
import com.huiyee.esite.model.VisitReportDetail;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WeiXinPage;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.esite.solr.HylogSolrServer;
import com.huiyee.esite.solr.SolrServerMgr;
import com.huiyee.esite.util.CacheUtil;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.esite.util.HttpTookit;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.ZuoB;
import com.huiyee.esite.util.ZuoBUtil;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.bbs.model.BBSContent;
import com.huiyee.interact.cb.model.CbActivityMatter;
import com.huiyee.interact.checkin.mgr.ICheckinMgr;
import com.huiyee.interact.checkin.model.CheckinBalance;
import com.huiyee.interact.checkin.model.CheckinRecord;
import com.huiyee.interact.exam.dto.PingceResultDto;
import com.huiyee.interact.exam.mgr.IExamManager;
import com.huiyee.interact.exam.model.ExamResult;
import com.huiyee.interact.xc.dto.XcSiteDto;
import com.huiyee.interact.xc.mgr.IXcMgr;
import com.huiyee.interact.xc.model.XcInfo;
import com.huiyee.tfmodel.HyWbComment;
import com.huiyee.tfmodel.HyWbLogin;
import com.huiyee.tfmodel.HyWbSrc;
import com.huiyee.tfmodel.HyWbUser;
import com.huiyee.weixin.mgr.IWxBuyProductMgr;
import com.huiyee.weixin.model.WxMp;
import com.huiyee.weixin.model.WxPageShow;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PageComposeImpl implements IPageCompose {

	private ISiteManager siteManager;
	private IHyUserMgr hyUserMgr;
	private ISiteActionManager siteActionManager;
	private IContentManager contentManager;
	private IPageManager pageManager;
	private IFeatureManager featureManager;
	private ISinaTokenMgr sinaTokenMgr;
	private IUserMgr userMgr;
	private IVisitMgr visitMgr;
	private IPageCacheManager pageCacheManager;
	private IAccountManager accountManager;
	private IUserUploadManager userUploadManager;
	private ISinaUserManager sinaUserManager;
	private ISinaShareRecordManager sinaShareRecordManager;
	private ISinaShareManager sinaShareManager;
	private IActivityManager activityManager;
	private IVisitReportManager visitReportManager;
	private IFansAnalyseManager fansAnalyseManager;
	private Map<Long, IHdFeatureManager> hdManagers;
	private ISinaForwardManager sinaForwardManager;
	private IEmotionsManager emotionsManager;
	private IInteractModelManager interactModelManager;
	private ISinaCommentManager sinaCommentManager;
	private ITemplateManager templateManager;
	private IHdRecordManager hdRecordManager;
	private IFriendsShipsMgr friendsShipsMgr;
	private IWSManager wsManager;
	private Map<Long, com.huiyee.esite.fmgr.IFeatureManager> managers;
	private SolrServerMgr solrServerMgr;
	private HylogSolrServer hylogSolrServer;
	private IMaterialManager materialManager;
	private IWeiXinMgr weiXinMgr;
	private ICheckinMgr checkinMgr;
	private IContentFormMgr contentFormMgr;
	private IAreaManager areaMgr;
	private IBBSManager bbsMgr;
	private IHdUserDataMgr hdUserDataMgr;
	private IMarketActivityMgr marketActivityMgr;
	private IPageShowMaterialManager pageShowMaterialManager;
	private IContentRecordMgr contentRecordMgr;
	private IMbMgr mbMgr;
	private ISiteSourceManager siteSourceManager;
	private IExamManager examManager;
	private IXcMgr xcMgr;
	private IUserTagMgr userTagMgr;
	private IAppManager appManager;
	private IWxBuyProductMgr wxBuyProductMgr;
	
	public void setWxBuyProductMgr(IWxBuyProductMgr wxBuyProductMgr) {
		this.wxBuyProductMgr = wxBuyProductMgr;
	}

	public void setAppManager(IAppManager appManager)
	{
		this.appManager = appManager;
	}

	public void setUserTagMgr(IUserTagMgr userTagMgr)
	{
		this.userTagMgr = userTagMgr;
	}

	public void setXcMgr(IXcMgr xcMgr)
	{
		this.xcMgr = xcMgr;
	}

	public void setExamManager(IExamManager examManager)
	{
		this.examManager = examManager;
	}

	public void setHylogSolrServer(HylogSolrServer hylogSolrServer)
	{
		this.hylogSolrServer = hylogSolrServer;
	}

	public void setSiteSourceManager(ISiteSourceManager siteSourceManager)
	{
		this.siteSourceManager = siteSourceManager;
	}

	public void setMbMgr(IMbMgr mbMgr)
	{
		this.mbMgr = mbMgr;
	}

	public void setContentRecordMgr(IContentRecordMgr contentRecordMgr)
	{
		this.contentRecordMgr = contentRecordMgr;
	}

	public void setPageShowMaterialManager(IPageShowMaterialManager pageShowMaterialManager)
	{
		this.pageShowMaterialManager = pageShowMaterialManager;
	}

	public void setMarketActivityMgr(IMarketActivityMgr marketActivityMgr)
	{
		this.marketActivityMgr = marketActivityMgr;
	}

	public void setBbsMgr(IBBSManager bbsMgr) {
		this.bbsMgr = bbsMgr;
	}

	public void setContentFormMgr(IContentFormMgr contentFormMgr) {
		this.contentFormMgr = contentFormMgr;
	}

	public void setCheckinMgr(ICheckinMgr checkinMgr) {
		this.checkinMgr = checkinMgr;
	}

	public void setMaterialManager(IMaterialManager materialManager) {
		this.materialManager = materialManager;
	}

	public void setFriendsShipsMgr(IFriendsShipsMgr friendsShipsMgr) {
		this.friendsShipsMgr = friendsShipsMgr;
	}

	public void setHdRecordManager(IHdRecordManager hdRecordManager) {
		this.hdRecordManager = hdRecordManager;
	}

	public void setSinaCommentManager(ISinaCommentManager sinaCommentManager) {
		this.sinaCommentManager = sinaCommentManager;
	}

	public void setHdManagers(Map<Long, IHdFeatureManager> hdManagers) {
		this.hdManagers = hdManagers;
	}

	public void setVisitReportManager(IVisitReportManager visitReportManager) {
		this.visitReportManager = visitReportManager;
	}

	public void setSinaShareManager(ISinaShareManager sinaShareManager) {
		this.sinaShareManager = sinaShareManager;
	}

	public void setPageCacheManager(IPageCacheManager pageCacheManager) {
		this.pageCacheManager = pageCacheManager;
	}

	public void setVisitMgr(IVisitMgr visitMgr) {
		this.visitMgr = visitMgr;
	}

	public void setUserMgr(IUserMgr userMgr) {
		this.userMgr = userMgr;
	}

	public void setSinaTokenMgr(ISinaTokenMgr sinaTokenMgr) {
		this.sinaTokenMgr = sinaTokenMgr;
	}

	public void setSolrServerMgr(SolrServerMgr solrServerMgr) {
		this.solrServerMgr = solrServerMgr;
	}

	public void setHdUserDataMgr(IHdUserDataMgr hdUserDataMgr) {
		this.hdUserDataMgr = hdUserDataMgr;
	}

	@Override
	public IDto composeWbPageIndexAction(Account account, String type, int pageId) {
		SiteIndexActionDto dto = new SiteIndexActionDto();
		int start = (pageId - 1) * IPageConstants.SITE_LIMIT;
		int total = siteManager.findSitegroupCountByOwner(account.getOwner().getId(),type);
		if (total > 0) {
			dto.setSitegroup(siteManager.findSitegroupByOwner(account.getOwner().getId(), type, start, IPageConstants.SITE_LIMIT));
		}
		Pager pager = new Pager(pageId, total, IPageConstants.SITE_LIMIT);
		dto.setModules(siteManager.findModules());
		dto.setPager(pager);
		return dto;
	}

	@Override
	public IDto composeLoadPageIndex(Account account, String type) {
		LoadPageIndexDto dto = new LoadPageIndexDto();
		dto.setSiteList(siteManager.findSiteListAll(type, account.getOwner().getId()));
		return dto;
	}

	public IDto findContentByCategoryId(Account account, long ccid, long typeid, int pageId, String entityName) {
		long owner = account.getOwner().getId();
		ContentDto dto = new ContentDto();
		List<OwnerContentType> typeList = contentManager.findContentType(account.getOwner().getId());
		dto.setTypeList(typeList);
		if (ccid == 0 && typeid == 0 && typeList.size() > 0) {
			typeid = typeList.get(0).getId();
		}

		ContentCategory ct = null;
		if (ccid <= 0) {
			boolean isdefalut = ccid == 0 ? true : false;// 当前台的ccid没传时 默认选中一个
															// 当ccid<0时就不需要默认选中
			if (isdefalut) {
				ccid = contentManager.findDefaultCcid(typeid, owner);
			}

			if (!isdefalut || ct == null) {
				ct = new ContentCategory();
				ct.setTypeid(typeid);
				ct.setType(contentManager.findContactCategoryType(typeid, owner));
			}
		}
		if (ccid > 0) {
			ct = contentManager.findContentCategoryById(ccid);
		}
		dto.setCurrent(ct);
		String type = ct.getType();
		typeid = ct.getTypeid();
		if (!StringUtils.isEmpty(type) && ccid > 0) {
			if ("T".equals(type)) {
				dto.setProduct(contentManager.findProductByCcid(ccid, owner, (pageId - 1) * IPageCompose.CONTENT_LIMIT, IPageConstants.CONTENT_LIMIT, entityName));
				int total = contentManager.findProductTotal(ccid, owner, entityName);
				dto.setPager(new Pager(pageId, total, IPageCompose.CONTENT_LIMIT));
			} else if ("P".equals(type)) {
				dto.setPictureList(contentManager.findPictureByCcid(ccid, owner, (pageId - 1) * IPageCompose.CONTENT_LIMIT, IPageConstants.CONTENT_LIMIT, entityName));
				int total = contentManager.findPictureTotal(ccid, owner, entityName);
				dto.setPager(new Pager(pageId, total, IPageCompose.CONTENT_LIMIT));
			} else if ("N".equals(type)) {
				dto.setNewList(contentManager.findNewByCcid(ccid, owner, (pageId - 1) * IPageCompose.CONTENT_LIMIT, IPageConstants.CONTENT_LIMIT, entityName));
				int total = contentManager.findNewsTotal(ccid, owner, entityName);
				dto.setPager(new Pager(pageId, total, IPageCompose.CONTENT_LIMIT));
			} else if ("V".equals(type)) {
				dto.setVideoList(contentManager.findVideoByCcid(ccid, owner, (pageId - 1) * IPageCompose.CONTENT_LIMIT, IPageConstants.CONTENT_LIMIT, entityName));
				int total = contentManager.findVideoTotal(ccid, owner, entityName);
				dto.setPager(new Pager(pageId, total, IPageCompose.CONTENT_LIMIT));
			}
			List<Long> topic = bbsMgr.findEntityByType(owner, type);
			dto.setTopicList(topic);
		}
		dto.setTree(new Gson().toJson(contentManager.findTreeByType(type, ccid, owner, typeid)));
		return dto;
	}

	public IDto findContentIndex(long ccid, Account account) {
		ContentDto dto = new ContentDto();
		return dto;
	}

	@Override
	public List<ContentCategory> findNextCategory(long ccid, Account account) {
		return contentManager.findNextCategory(ccid, account.getOwner().getId());
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}

	public void setContentManager(IContentManager contentManager) {
		this.contentManager = contentManager;
	}

	@Override
	public IDto composeCreateSite() {
		SiteIndexActionDto dto = new SiteIndexActionDto();
		dto.setApps(sinaTokenMgr.findSinaApp());
		// dto.setModules(siteManager.findModules());
		return dto;
	}

	@Override
	public IDto composeCreateSiteSub(Account account, String type, String sitename, List<Long> modules, long groupid, long appid) {
		int res = 0;
		SiteIndexActionDto dto = new SiteIndexActionDto();
		try {
			dto.setModules(siteManager.findModules());
			dto.setApps(sinaTokenMgr.findSinaApp());
			res = siteManager.saveLoadPageSite(account.getOwner().getId(), sitename, type, appid, modules, groupid);
		} catch (Exception e) {
			e.printStackTrace();
			dto.setResult("系统发生错误!");
		}
		if (res == 1) {
			dto.setResult("Y");
		}
		return dto;
	}

	@Override
	public IDto composeCreateLoadPageSiteSub(Account account, String type, String sitename, List<Long> modules, long appid) {
		int res = 1;
		LoadPageIndexDto dto = new LoadPageIndexDto();
		try {
			dto.setModules(siteManager.findModules());
			dto.setApps(sinaTokenMgr.findSinaApp());
			res = siteManager.saveLoadPageSite(account.getOwner().getId(), sitename, type, appid, modules, 0);
		} catch (Exception e) {
			e.printStackTrace();
			dto.setResult("系统发生错误!");
		}
		if (res == 1) {
			dto.setResult("Y");
		}
		return dto;
	}

	@Override
	public IDto composeSiteGroupCount(long siteid) {
		SiteCountDto siteCountDto = new SiteCountDto();
		Site site = siteManager.findSiteById(siteid);
		if (site != null)
			siteCountDto.setSite(site);
		List<SiteCount> sitecount = siteActionManager.findSiteActionBySiteid(siteid, site.getOwnerId());
		if (sitecount != null && sitecount.size() > 0)
			siteCountDto.setSiteCountList(sitecount);
		return siteCountDto;
	}

	@Override
	public IDto composeSiteCount(long siteid) {
		LoadPageSiteCountDto siteCountDto = new LoadPageSiteCountDto();
		Site site = siteManager.findSiteByID(siteid);
		siteCountDto.setSite(site);
		List<SiteCount> sitecount = siteActionManager.findSiteCountBySite(siteid, site.getOwnerId());
		if (sitecount != null && sitecount.size() > 0)
			siteCountDto.setSiteCountList(sitecount);
		return siteCountDto;
	}

	@Override
	public IDto composeVistLogList(long sitegid, String type, int page) {
		SiteVistLogDto svld = new SiteVistLogDto();
		List<VisitLog> visitLogList = visitMgr.findVistLogBySiteGidAndtype(sitegid, type, page);
		int total = visitMgr.findTotalVistLogBySiteidAndtype(sitegid, type);
		Pager pager = new Pager(page, total, IPageConstants.CONTENT_LIMIT);
		svld.setVistLogList(visitLogList);
		svld.setPager(pager);
		return svld;
	}

	@Override
	public IDto composeLoadVistLogList(long siteid, String type, int page) {
		SiteVistLogDto svld = new SiteVistLogDto();
		List<VisitLog> visitLogList = visitMgr.findVistLogBySiteAndtype(siteid, type, page);
		int total = visitMgr.findTotalVistLogBySiteAndtype(siteid, type);
		Pager pager = new Pager(page, total, IPageConstants.CONTENT_LIMIT);
		svld.setVistLogList(visitLogList);
		svld.setPager(pager);
		return svld;
	}

	public IDto composeUpdateSite(long siteid) {
		SiteIndexActionDto dto = new SiteIndexActionDto();
		dto.setModules(siteManager.findModules());
		dto.setSite(siteManager.findSiteByID(siteid));
		dto.setModuleArr(siteManager.findSiteModule(siteid));
		return dto;
	}

	public IDto composeUpdateLoadSite(long siteid) {

		LoadPageIndexDto dto = new LoadPageIndexDto();
		if (siteid <= 0) {
			dto.setApps(sinaTokenMgr.findSinaApp());
		}
		dto.setModules(siteManager.findModules());
		dto.setSite(siteManager.findSiteByID(siteid));
		dto.setModuleArr(siteManager.findSiteModule(siteid));
		return dto;
	}

	@Override
	public IDto composeUpdateSiteSub(Account account, long siteid, String sitename, List<Long> modules) {
		int res = 0;
		SiteIndexActionDto dto = new SiteIndexActionDto();
		try {
			dto.setModules(siteManager.findModules());
			res = siteManager.updateSite(sitename, siteid, modules);

		} catch (Exception e) {
			e.printStackTrace();
			dto.setResult(e.getMessage());
		}
		if (res == 1) {
			dto.setResult("Y");
		}
		return dto;
	}

	@Override
	public IDto composeUpdateLoadPageSiteSub(Account account, long siteid, String sitename, List<Long> modules) {
		int res = 0;
		LoadPageIndexDto dto = new LoadPageIndexDto();
		try {
			dto.setModules(siteManager.findModules());
			res = siteManager.updateSite(sitename, siteid, modules);

		} catch (Exception e) {
			e.printStackTrace();
			dto.setResult(e.getMessage());
		}
		if (res == 1) {
			dto.setResult("Y");
		}
		return dto;
	}

	@Override
	public int composeDelSite(long sitegroupid) {
		return siteManager.deleteSite(sitegroupid);
	}

	@Override
	public IDto composePageAcion(long groupid, String type, long siteid) {
		PageActionDto dto = new PageActionDto();
		dto.setSite(siteManager.findSiteById(groupid));
		dto.setSites(siteManager.findSiteList(type, groupid));
		// dto.setSite(siteManager.findSiteByID(siteid));
		// dto.setPages(pageManager.findPageBySiteid(siteid));
		return dto;
	}

	@Override
	public IDto composeLoadPage(long siteid, String type) {
		LoadPageDto dto = new LoadPageDto();
		dto.setSite(siteManager.findSiteByID(siteid));
		dto.setPages(pageManager.findPageBySiteid(siteid));
		// dto.setPages(pageManager.findPageBySiteid(siteid));
		return dto;
	}

	public void setPageManager(IPageManager pageManager) {
		this.pageManager = pageManager;
	}

	@Override
	public IDto composeUpdatePage(long pageid, long ownerid) {
		PageActionDto dto = new PageActionDto();
		dto.setPage(pageManager.findPageById(pageid));
		dto.setList(pageManager.findPageTemplate(ownerid));
		return dto;
	}

	@Override
	public int composeUpdatePageSub(String pagename, String jspname, long pageid, String istemplate, String ownertempid) {
		if ("Y".equals(istemplate)) {
			jspname = null;
		}
		if ("N".equals(istemplate)) {
			ownertempid = null;
		}
		return pageManager.updatePage(pagename, jspname, pageid, istemplate, ownertempid);
	}

	@Override
	public int composeDelPage(long pageid) {
		return pageManager.deletePage(pageid);
	}

	public IDto addContentPre(Account account, String contentProducy, long ccid) {
		ContentDto dto = new ContentDto();
		List<ContentCategory> list = new ArrayList<ContentCategory>();
		list.add(contentManager.findContentCategoryById(ccid));
		dto.setContentcategory(list);
		return dto;
	}

	public long saveProduct(ContentProduct product,JSONObject tags) {
		ContentCategory cc=contentManager.findContentCategoryById(product.getCatid());
		if(cc.getSubtype()!=null&&cc.getSubtype().trim().length()>0){
			product.setType(cc.getSubtype());
		}
		long pid = contentManager.saveProduct(product);
		if(tags != null){
			Account account = (Account) ActionContext.getContext().getSession().get("account");
			userTagMgr.updateContentTag(account.getOwner().getId(), cc.getId(), pid, tags);
		}
		return pid;
	}

	@Override
	public IDto composeFeatureAction(long pageid) {
		FeatureActionDto dto = new FeatureActionDto();
		dto.setPage(pageManager.findPageById(pageid));
		dto.setFeatures(featureManager.findFeatureByPageId(pageid));
		return dto;
	}

	@Override
	public IDto composeFeatureBySiteid(long siteid) {
		FeatureActionDto dto = new FeatureActionDto();
		Site site = siteManager.findSiteByID(siteid);
		dto.setSitename(site == null ? "" : site.getName());
		dto.setFeatures(featureManager.findFeaturesBySiteId(siteid));
		return dto;
	}

	public void setFeatureManager(IFeatureManager featureManager) {
		this.featureManager = featureManager;
	}

	public IDto findProductById(Account account, long contentId) {
		ContentDto dto = new ContentDto();
		dto.setContentcategory(contentManager.findContactCategoryByOwnerAndType(account.getOwner().getId(), IPageConstants.CONTENT_PRODUCT));
		dto.setCp(contentManager.findProductById(contentId));
		dto.setCcList(contentManager.findContentCategoryLevel(dto.getCp().getCatid(), account.getOwner().getId()));
		return dto;
	}

	@Override
	public int editProduct(ContentProduct product, long ownerid,JSONObject tags) {
		userTagMgr.updateContentTag(ownerid, product.getCatid(), product.getId(), tags);
		return contentManager.updateProduct(product, ownerid);
	}

	public int deleteProduct(long contentId, long ownerid) {
		return contentManager.updateProduct(contentId, IPageConstants.CONTENT_DEL, ownerid);
	}

	public int checkProduct(long contentId, String status, long ownerid) {
		return contentManager.updateProduct(contentId, status, ownerid);
	}

	public int publicProduct(long contentId, long ownerid) {
		return contentManager.updateProduct(contentId, IPageConstants.CONTENT_CMP, ownerid);
	}

	public int offProduct(long contentId, long ownerid) {
		return contentManager.updateProduct(contentId, IPageConstants.CONTENT_INP, ownerid);
	}

	public long savePicture(ContentPicture picture,JSONObject tags) {
		long pid = contentManager.savePicture(picture);
		userTagMgr.updateContentTag(picture.getOwnerid(),picture.getCatid(), pid, tags);
		return pid;
	}

	public IDto findPictureById(long contentId, Account account) {
		ContentDto dto = new ContentDto();
		dto.setContentcategory(contentManager.findContactCategoryByOwnerAndType(account.getOwner().getId(), IPageConstants.CONTENT_PICTURE));
		dto.setPicture(contentManager.findPictureById(contentId, account.getOwner().getId()));
		dto.setCcList(contentManager.findContentCategoryLevel(dto.getPicture().getCatid(), account.getOwner().getId()));
		return dto;
	}

	public int editPicture(ContentPicture picture,JSONObject tags) {
		userTagMgr.updateContentTag(picture.getOwnerid(),picture.getCatid(), picture.getId(), tags);
		return contentManager.updatePicture(picture);
	}

	public int delPicture(long contentId, long owner) {
		return contentManager.updatePicture(contentId, owner, IPageConstants.CONTENT_DEL);
	}

	public int checkPicture(long contentId, String status, Account account) {
		return contentManager.updatePicture(contentId, account.getOwner().getId(), status);
	}

	@Override
	public int publicPicture(long contentId, long owner) {
		return contentManager.updatePicture(contentId, owner, IPageConstants.CONTENT_CMP);
	}

	@Override
	public int offPicture(long contentId, long owner) {
		return contentManager.updatePicture(contentId, owner, IPageConstants.CONTENT_INP);
	}

	public long saveNew(ContentNew cn,JSONObject tags) {
		long pid=contentManager.saveNew(cn);
		userTagMgr.updateContentTag(cn.getOwnerid(),cn.getCatid(), pid, tags);
		return pid;
	}

	public IDto findNewById(long contentId, Account account) {
		ContentDto dto = new ContentDto();
		dto.setContentcategory(contentManager.findContactCategoryByOwnerAndType(account.getOwner().getId(), IPageConstants.CONTENT_NEW));
		dto.setCn(contentManager.findNewById(contentId, account.getOwner().getId()));
		dto.setCcList(contentManager.findContentCategoryLevel(dto.getCn().getCatid(), account.getOwner().getId()));
		return dto;
	}

	public int editNew(ContentNew cn,JSONObject tags) {
		userTagMgr.updateContentTag(cn.getOwnerid(),cn.getCatid(), cn.getId(), tags);
		return contentManager.updateNew(cn);
	}

	public int checkNew(long contentId, String status, Account account) {
		return contentManager.updateNew(contentId, account.getOwner().getId(), status);
	}

	public int delNew(long contentId, long id) {
		return contentManager.updateNew(contentId, id, IPageConstants.CONTENT_DEL);
	}

	public int publicNew(long contentId, long id) {
		return contentManager.updateNew(contentId, id, IPageConstants.CONTENT_CMP);
	}

	public int offNew(long contentId, long id) {
		return contentManager.updateNew(contentId, id, IPageConstants.CONTENT_INP);
	}

	public long saveVideo(ContentVideo video, JSONObject tags) {
		long pid=contentManager.saveVideo(video);
		userTagMgr.updateContentTag(video.getOwnerid(),video.getCatid(), pid, tags);
		return pid;
	}

	public IDto findVideoById(long contentId, Account account) {
		ContentDto dto = new ContentDto();
		dto.setContentcategory(contentManager.findContactCategoryByOwnerAndType(account.getOwner().getId(), IPageConstants.CONTENT_VIDEO));
		dto.setVideo(contentManager.findVideoById(contentId, account.getOwner().getId()));
		dto.setCcList(contentManager.findContentCategoryLevel(dto.getVideo().getCatid(), account.getOwner().getId()));
		return dto;
	}

	public int editVideo(ContentVideo video, JSONObject tags) {
		userTagMgr.updateContentTag(video.getOwnerid(),video.getCatid(), video.getId(), tags);
		return contentManager.updateVideo(video);
	}

	public int checkVideo(long contentId, String status, Account account) {
		return contentManager.updateVideo(contentId, status, account.getOwner().getId());
	}

	public int delVideo(long contentId, long id) {
		return contentManager.updateVideo(contentId, IPageConstants.CONTENT_DEL, id);
	}

	public int publicVideo(long contentId, long id) {
		return contentManager.updateVideo(contentId, IPageConstants.CONTENT_CMP, id);
	}

	public int offVideo(long contentId, long id) {
		return contentManager.updateVideo(contentId, IPageConstants.CONTENT_INP, id);
	}

	public long delCategory(long ccid) {
		return contentManager.delCategory(ccid);
	}

	public int addCategory(long owner, ContentCategory cc) {
		return contentManager.addCategory(owner, cc);
	}

	@Override
	public long findPageIdForPageShow(long cid, long appid) {
		return sinaTokenMgr.findPageIdForPageShow(cid, appid);
	}

	public List<ContentProduct> findOwnerProduct(long id) {
		return contentManager.findOwnerProduct(id);
	}

	@Override
	public Page findHomePageBySiteid(long siteid) {
		return pageManager.findHomePageBySiteid(siteid);
	}

	@Override
	public Page findPageByid(long pageid) {
		return pageManager.findPageById(pageid);
	}

	@Override
	public long updateUidByViewer(long viewer, long siteid, String token, long endtsecond, String userinfo) {
		userMgr.saveSiteUser(viewer, siteid, token, endtsecond, userinfo);
		return userMgr.findUidByViewer(viewer, siteid);
	}

	@Override
	public long updateUidByViewer(long viewer, long siteid) {
		long uid = userMgr.findUidByViewer(viewer, siteid);
		if (uid == 0) {
			uid = userMgr.updateUidByViewer(viewer, siteid);
		}
		return uid;
	}

	public void setSiteActionManager(ISiteActionManager siteActionManager) {
		this.siteActionManager = siteActionManager;
	}

	@Override
	public void insertVisitLog(long siteid, long pageid, VisitUser vu, String ip, String agent, String key) {
		visitMgr.insertVisitLog(siteid, pageid, vu, ip, agent, HttpRequestDeviceUtils.getMediaDevice(agent), key);
	}

	@Override
	public void insertVisitLogAnonymous(long siteid, long pageid, String ip, String agent, String key) {
		visitMgr.insertVisitLogAnonymous(siteid, pageid, ip, agent, HttpRequestDeviceUtils.getMediaDevice(agent), key);
	}

	@Override
	public String findOauthLink(String state, long appid) {
		SinaApp sa = sinaTokenMgr.findSinaAppById(appid);
		String str = null;
		try {
			// str = Oauth.authorize("code", sa.getAppkey() + "", sa
			// .getOauthByCodeUrl(), state, "false");
			str = wsManager.loginForLink(1, sa.getOauthByCodeUrl(), state, sa.getAppkey() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public String findPublishLink(String state, long appid, long pageid) {
		SinaApp sa = sinaTokenMgr.findSinaAppById(appid);
		String str = null;
		try {
			// str = Oauth.authorize("code", sa.getAppkey() + "", sa
			// .getOauthByCodeUrl(), state, "false");
			String appkey = sa.getAppkey() + "";
			str = wsManager.loginForLink(1, HyConfig.getYuming() + "/user/publish.action", state, appkey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public String publishByCode(long appid, String code, long pageid, long ownerid) {
		SinaApp sa = sinaTokenMgr.findSinaAppById(appid);
		HyWbLogin login = null;
		// HyWbLogin login = new HyWbLogin();
		// login.setAccessToken("2.00zdCAvB0zD9Bz3d2588efd20ANcKR");
		// login.setWbuid(275831111);
		try {
			login = wsManager.loginForWeiBo(code, sa.getAppkey() + "", sa.getAppsecret(), HyConfig.getYuming() + "/user/publish.action");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (login == null) {
			return sa.getRefurl();
		}

		Date tokenendtime = new Date(login.getExpireIn());
		HyWbUser user = null;
		// HyWbUser user = new HyWbUser();
		// user.setScreenName("dddd");
		Page page = pageManager.findPageById(pageid);
		try {
			user = wsManager.findUserByid(login.getAccessToken(), login.getWbuid());
			// 根据当前传入的pageid查找page是否已绑定
			SinaToken sinatoken = sinaTokenMgr.findSinaToken(pageid);
			// 根据当前传入的appid和账号的wbuid查找该微博账号是否已绑定
			SinaToken sinaT = sinaTokenMgr.findSinaTokenByAppidAndCid(appid, login.getWbuid());
			if (sinatoken == null) {
				sinaTokenMgr.updateSinaTokenPublish(pageid, page.getSiteid(), appid, login.getWbuid(), ownerid, login.getAccessToken(), tokenendtime, user.getScreenName());
				if (sinaT != null) {
					pageManager.updatePageAddressStatus(sinaT.getPageid());
				}
			} else {
				if (sinaT != null) {
					// 把当前的pageid设为null
					sinaTokenMgr.updateSinaTokenByPageid(pageid);
					// 根据当前传入的appid和wbuid查找pageid并修改
					sinaTokenMgr.updateSinaTokenByAppicCid(appid, login.getWbuid(), pageid);
					// 根据当前传入的pageid查找出一条记录并取出appid,cid，根据appid,cid修改pageid
					sinaTokenMgr.updateSinaTokenByAppicCid(sinatoken.getAppid(), sinatoken.getCid(), sinaT.getPageid());
					if (pageid != sinaT.getPageid()) {
						sinaTokenMgr.updatePageSubSina(pageid, "Y");
						sinaTokenMgr.updatePageSubSina(sinaT.getPageid(), "N");
					} else {
						sinaTokenMgr.updatePageSubSina(pageid, "Y");
					}
					pageManager.updatePageAddressStatus(sinaT.getPageid());
					pageManager.updatePageAddressStatus(pageid);
				} else {
					// 如果appid和该微博账号没有绑定page，直接根据当前pageid修改appid,cid
					sinaTokenMgr.updateSinaTokenPublish(pageid, page.getSiteid(), appid, login.getWbuid(), ownerid, login.getAccessToken(), tokenendtime, user.getScreenName());
					sinaTokenMgr.updatePageSubSina(pageid, "Y");
					pageManager.updatePageAddressStatus(pageid);
				}
			}
			PageAddress pa = new PageAddress();
			SinaToken st = sinaTokenMgr.findSinaTokenCidByPageid(pageid, appid);
			long cid = st.getCid();
			String token = sa.getToken();
			String address = HyConfig.getHypageyuming() + "/" + cid + "/" + token;
			pa.setPageid(pageid);
			pa.setName("微博");
			pa.setWeixin("N");
			pa.setAddress(address);
			pageManager.savePageAddress(pa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/page/sub_weibo_flow.action";
		// return "/page/pageconfig_new.action?siteid="+page.getSiteid();
	}

	@Override
	public String findUserOauthLink(String state, long appid, long shareid) {
		SinaApp sa = sinaTokenMgr.findSinaAppById(appid);
		SinaShare ss = sinaShareManager.findSinaShareById(shareid);
		String str = null;
		try {
			str = wsManager.loginForLink(1, ss.getSoauthurl(), state, sa.getAppkey() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public String OauthByCode(long appid, String code, long siteid) {
		SinaApp sa = sinaTokenMgr.findSinaAppById(appid);
		HyWbLogin login = null;
		try {
			login = wsManager.loginForWeiBo(code, sa.getAppkey() + "", sa.getAppsecret(), sa.getOauthByCodeUrl());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (login == null) {
			return sa.getRefurl();
		}
		Date tokenendtime = new Date(System.currentTimeMillis() + (login.getExpireIn() - 10) * 1000L);
		HyWbUser user = null;
		try {
			user = wsManager.findUserByid(login.getAccessToken(), login.getWbuid());
			sinaTokenMgr.updateSinaToken(user, appid, login.getAccessToken(), tokenendtime, siteid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sa.getRefurl();

	}

	@Override
	public IDto userOauthByCode(long appid, String code, long siteid, String ip, String terminal, long shareid, long pageid, String source) {
		UserOauthDto dto = new UserOauthDto();
		SinaApp sa = sinaTokenMgr.findSinaAppById(appid);
		SinaShare ss = sinaShareManager.findSinaShareById(shareid);
		HyWbLogin login = null;
		try {
			login = wsManager.loginForWeiBo(code, sa.getAppkey() + "", sa.getAppsecret(), ss.getSoauthurl());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (login == null) {
			dto.setRefUrl(ss.getCoauthurl());
			return dto;
		}
		Date tokenendtime = new Date(System.currentTimeMillis() + (login.getExpireIn() - 10) * 1000L);
		HyWbUser user = null;
		try {
			user = wsManager.findUserByid(login.getAccessToken(), login.getWbuid());
			sinaTokenMgr.updateSinaToken(user, appid, login.getAccessToken(), tokenendtime, siteid);
			dto.setRefUrl(ss.getOldurl() + "&viewer=" + user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;

	}

	@Override
	public long findAppidBySiteid(long siteid) {
		return sinaTokenMgr.findAppidBySiteid(siteid);
	}

	@Override
	public int composeSetHome(long siteid, long pageid) {
		return pageManager.updateHome(siteid, pageid);
	}

	@Override
	public int composeCreateSitegroup(String name, Account account, String groupType) {
		return siteManager.addSitegroup(name, account.getOwner().getId(), groupType);
	}

	@Override
	public Map<String, Object> show(long pageid) {
		Map<String, Object> map;
		try {
			map = pageCacheManager.getMap(pageid);
		} catch (Exception e) {
			System.out.println("cache error" + pageid);
			updateData1(pageid);
			map = pageCacheManager.getMap(pageid);
		}
		List<Feature> fs = featureManager.findNeedUserFeatureByPageId(pageid);//找个性化功能
		VisitUser visit = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		if (fs != null)
		{
			for (Feature f : fs)
			{
				map.put((f.getIdx()) + "-" + f.getId(), managers.get(f.getId()).featureUserRecord(visit, f.getFid()));
			}
		}
		if(visit!=null && StringUtils.isNotEmpty(visit.getSkey())){
			if ("n".equalsIgnoreCase(visit.getSkey()))
			{	//n-id 新闻详情页
				ContentNew cn = contentManager.findNewsById(Long.parseLong(visit.getSvalue()));
				if(cn != null){
					cn.setReadNum(hylogSolrServer.readNumber("n", cn.getId()+""));
				}
				map.put("news", cn);
			}
			List<BBSContent> list = new ArrayList<BBSContent>();
			if ("ct".equalsIgnoreCase(visit.getSkey()))
			{
				//ct-hy-id 产品目录列表
				list = contentManager.findBBSProduct(Long.parseLong(visit.getSvalue()), 1);
			}else if ("ctt".equalsIgnoreCase(visit.getSkey()))
			{
				//ctt-hy-id产品详情页
				ContentProduct ct = contentManager.findProductById(Long.parseLong(visit.getSvalue()));
				map.put("product", ct);
			}else if ("cp".equalsIgnoreCase(visit.getSkey()))
			{
				//cp-hy-id 图片目录列表
				list = contentManager.findBBSPicture(Long.parseLong(visit.getSvalue()), 0);
			}
			else if ("cpp".equalsIgnoreCase(visit.getSkey()))
			{
				//cpp-hy-id图片详情页
				ContentPicture cp = contentManager.findPictureById(Long.parseLong(visit.getSvalue()));
				map.put("picture", cp);
			} else if ("cv".equalsIgnoreCase(visit.getSkey()))
			{
				//cv-hy-id 视频目录详情页
				list = contentManager.findBBSVideo(Long.parseLong(visit.getSvalue()), 3);
			} else if ("cvv".equalsIgnoreCase(visit.getSkey()))
			{
				//cvv-hy-id 视频详情页
				ContentVideo cvv = contentManager.findVideoById(Long.parseLong(visit.getSvalue()));
				map.put("vedio", cvv);
			} else if ("cn".equalsIgnoreCase(visit.getSkey()))
			{
				//cn-hy-id 新闻目录详情页
				list = contentManager.findBBSNews(Long.parseLong(visit.getSvalue()), 2);
			}
			if(list.size()>0){
				map.put("list", list);			
			}
		}
		return map;
	}

	@Override
	public IDto composeInteraction(long siteid) {
		InteractionActionDto dto = new InteractionActionDto();
		dto.setSite(siteManager.findSiteById(siteid));
		List<Feature> features = featureManager.findFeatureBySitegroupid(siteid);
		dto.setFeatures(features);
		return dto;
	}

	@Override
	public IDto composeLoadPageInteraction(long siteid) {
		LoadPageInteractionDto dto = new LoadPageInteractionDto();
		List<Feature> features = featureManager.findFeatureBySiteId(siteid);
		dto.setSite(siteManager.findSiteByID(siteid));
		dto.setFeatures(features);
		return dto;
	}

	@Override
	public IDto composeInteractionDetail(long features, long siteid, QueryDto siftDto) {
		InteractionActionDto dto = new InteractionActionDto();
		if (features == IPageConstants.FEATURE_ZAM) {
			dto.setZandetails(featureManager.findZanDetailBySitegroupid(siteid));
		}
		if (features == IPageConstants.FEATURE_UPLOAD) {
			dto.setUploaddetails(featureManager.findUploadDetailBySitegroupid(siteid));
		}
		if (features == IPageConstants.SINA_ACCREDIT) {
			// 新浪授权
			if (siftDto == null) {
				siftDto = new QueryDto();
			}
			dto.setSinausers(featureManager.findSinaUsersDetail(siteid, siftDto));
			int total = featureManager.findSinaUsersDetailTotal(siteid, siftDto);
			dto.setPager(new Pager(siftDto.getPageId(), total, IPageConstants.SINA_USER_DETAIL_LIMIT));
		}
		if (features == IPageConstants.USER_SINA_SHARE) {
			// 用户新浪分享
			if (siftDto == null) {
				siftDto = new QueryDto();
			}
			dto.setSinashare(featureManager.findSinaShare(siteid, siftDto));
			int total = featureManager.findSinaShareTotal(siteid, siftDto);
			dto.setPager(new Pager(siftDto.getPageId(), total, IPageConstants.SINA_SHARE_LIMIT));
		}
		return dto;
	}

	@Override
	public IDto composeLoadPageInteractionDetail(long features, long siteid) {
		InteractionActionDto dto = new InteractionActionDto();
		if (features == IPageConstants.FEATURE_ZAM) {
			dto.setZandetails(featureManager.findZanDetailBySiteId(siteid));
		}
		if (features == IPageConstants.FEATURE_UPLOAD) {
			dto.setUploaddetails(featureManager.findUploadDetailBySiteId(siteid));
		}
		return dto;
	}

	@Override
	public IDto composeUnifyLogin(long ownerId, long accountId, String ip) {
		LoginDto dto = new LoginDto();
		Account account = accountManager.addUnifyLoginByOwnerandAccountId(ownerId, accountId, ip);
		if (account == null) {
			return null;
		}
		// left_page的权限控制
		Map<Integer, String> map = accountManager.findHideControl(ownerId, accountId);
		dto.setHideControl(map);
		dto.setAccount(account);
		return dto;
	}

	public void setAccountManager(IAccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@Override
	public int addSite(String name, long ownerid, String type, long appid) {
		return siteManager.addSite(name, ownerid, type, appid);
	}

	@Override
	public UserUpload composeUserUpload(long fid) {
		return userUploadManager.findUserUpload(fid);
	}

	public void setUserUploadManager(IUserUploadManager userUploadManager) {
		this.userUploadManager = userUploadManager;
	}

	@Override
	public IDto findZanTotal(String[] pfid) {
		DynamicActionDto dto = new DynamicActionDto();
		dto.setZanList(featureManager.findZanTotal(pfid));
		return dto;
	}

	public List<ContentNew> findNewsByOwner(long owner) {
		return contentManager.findNewsByOwner(owner);
	}

	public void setSinaUserManager(ISinaUserManager sinaUserManager) {
		this.sinaUserManager = sinaUserManager;
	}

	@Override
	public int updateName(long pfid, String name) {
		return pageManager.updateName(pfid, name);
	}

	@Override
	public long findOwnerByPageid(long pageid) {
		return pageManager.findOwnerByPageid(pageid);
	}

	@Override
	public IDto composeSiteGroupList(Account account, int pageId,String type) {
		SiteGroupDto dto = new SiteGroupDto();
		int start = (pageId - 1) * IPageConstants.SITE_LIMIT;
		int total = siteManager.findSitegroupCountByOwner(account.getOwner().getId(),type);
		if (total > 0) {
			List<Sitegroup> list = siteManager.findSitegroupListByOwner(account.getOwner().getId(),type, start, IPageConstants.SITE_LIMIT);
			if (list != null && list.size() > 0) {
				for (Sitegroup s : list) {
					s.setSite(siteManager.findSiteBySiteGroupId(s.getId()));
					s.setWps(weiXinMgr.findWxPageShowBySitegroupId(s.getId()));
				}
			}
			dto.setList(list);
//			dto.setCbcount(siteManager.findCbCount(account.getOwner().getId(), type));
//			dto.setPubcount(siteManager.findPubCount(account.getOwner().getId(), type));
		}
		Pager pager = new Pager(pageId, total, IPageConstants.SITE_LIMIT);
		dto.setPager(pager);
		
		if(IPageConstants.SITEGROUP_TYPE_XIANCHANG.equals(type)){
			dto.setXcTemplate(siteManager.findXcTemplate());
		}
		return dto;
	}
	
	@Override
	public IDto sitegroupListLink(long sitegroupid) {
		SiteGroupDto dto = new SiteGroupDto();
		List<WxPageShow> wps = siteManager.sitegroupListLink(sitegroupid);
		dto.setLinkList(wps);
		return dto;
	}
	
	@Override
	public Sitegroup sitegroupListbyId(long pgid) {
		return siteManager.sitegroupListbyId(pgid);
	}

	@Override
	public IDto composeSiteAcion(long groupid, Account account, String type) {
		PageActionDto dto = new PageActionDto();
		Site site = siteManager.findSiteById(groupid);
		if (site == null) {
			return dto;
		}
		if (site.getOwnerId() != account.getOwner().getId()) {
			return dto;
		}
		dto.setSite(site);
		dto.setSites(siteManager.findSiteList(type, groupid));
		return dto;
	}

	@Override
	public int updateSiteName(String name, long sitegroupid) {
		return siteManager.updateSiteName(name, sitegroupid);
	}

	@Override
	public List<String> findAtUsers(long uid, long pageid, String keywords) {
		UserInfo info = sinaUserManager.findUserByUid(uid);
		if (info != null) {
			String token = info.getToken();
			return wsManager.findAtUsers(token, keywords, 5, info.getWbuid1());
		}
		return null;
	}

	@Override
	public IDto composeSites(Account account, String type, int pageId) {
		PageActionDto dto = new PageActionDto();
		long owner = account.getOwner().getId();
		int start = (pageId - 1) * IPageConstants.SITE_LIMIT;
		int total = siteManager.findSiteCountByOwner(owner, type);
		if (total > 0) {
			dto.setSites(siteManager.findSites(owner, type, start, IPageConstants.SITE_LIMIT));
		}
		Pager pager = new Pager(pageId, total, IPageConstants.SITE_LIMIT);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public int composeSinaShare(long uid, long pageid, long shareid, String content, String bimg, String mimg, String simg, String terminal, String source, String ip) {
		UserInfo info = sinaUserManager.findUserByUid(uid);
		long wbuid = info.getWbuid1();
		String token = info.getToken();
		HyWbSrc status = null;
		try {
			if (StringUtils.isEmpty(bimg)) {
				status = wsManager.weibo(token, content);
			} else {
				status = wsManager.weiboByPic(token, content, HyConfig.getImgDomain() + bimg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (status == null) {
			System.out.println("statua is null : uid" + uid + "---wbuid" + wbuid + "---token" + token + "---pageid:" + pageid + "---shareid" + shareid + "---bimg" + bimg
					+ "---mimg" + mimg + "---simg" + simg + "---content" + content);
			return -1;
		}
		long result = 0;
		if (StringUtils.isEmpty(bimg)) {
			result = sinaShareRecordManager.saveSinaShareRecord(shareid, status, null, null, null, terminal, pageid, source, ip);
		} else {
			result = sinaShareRecordManager.saveSinaShareRecord(shareid, status, bimg, mimg, simg, terminal, pageid, source, ip);
		}
		if (result > 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public int composeSinaForward(long wbid, long uid, long pageid, long shareid, String content, String terminal, String source, String ip, String gz, String gzwbuid) {
		UserInfo info = sinaUserManager.findUserByUid(uid);
		long wbuid = info.getWbuid1();
		String token = info.getToken();
		HyWbSrc src = null;
		try {
			src = wsManager.repost(wbid + "", content, token, 3);
			if ("Y".equals(gz)) {
				wsManager.guanzhu(token, Long.parseLong(gzwbuid));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (src == null) {
			System.out.println("statua is null :wbid = " + wbid + "---uid" + uid + "---wbuid" + wbuid + "---token" + token + "---pageid:" + pageid + "---shareid" + shareid
					+ "---content" + content);
			return -1;
		}
		long result = sinaForwardManager.saveSinaForward(wbuid, wbid, Long.parseLong(src.getId()), shareid, content, terminal, pageid, source, ip);
		if (result > 0) {
			return 1;
		}
		return 0;
	}

	public void setSinaShareRecordManager(ISinaShareRecordManager sinaShareRecordManager) {
		this.sinaShareRecordManager = sinaShareRecordManager;
	}

	@Override
	public long findSiteidByPageid(long pageid) {
		return siteManager.findSiteidByPageid(pageid);
	}

	@Override
	public int composeCheckToken(long uid, long siteid) {
		String result = sinaUserManager.findTokenByUidAndSiteid(uid, siteid);
		if (result == null) {
			return -1;
		} else if ("Y".equals(result)) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<SinaChecklistRecord> findSinaShareRecord(long shareid, long fid) {
		return sinaShareRecordManager.findRecordByShareid(shareid, fid);
	}

	@Override
	public String updateHandler(long featureid, long sitegroupid, long owner) {
		StringBuilder result = new StringBuilder();
		if (featureid == IPageConstants.USER_SINA_SHARE) {
			List<SinaShare> list = sinaShareManager.findSinaShareBySiteGroup(sitegroupid, owner);
			for (SinaShare s : list) {
				if ("I".equals(s.getStatus())) {
					result.append(s.getName() + "：正在更新中<br>");
				} else {
					Date now = new Date();
					if (s.getLastupdatetime() != null && (now.getTime() - s.getLastupdatetime().getTime() < 1000 * 60 * 60)) {
						result.append(s.getName() + "：更新频率过快,更新完一小时内请勿再次更新<br>");
					} else {
						sinaShareManager.updateStatus(s.getId(), "N");
						result.append(s.getName() + ":开始更新<br>");
					}
				}
			}
		}
		return result.toString();
	}

	@Override
	public int findSinaShareTotal(long shareid) {
		return sinaShareManager.findSinaShareTotal(shareid);
	}

	@Override
	public List<String> exportSiteCountDetail(long siteid, String type, long id) {
		List<String> list = new ArrayList<String>();
		list.add("访问用户,访问时间,访问IP");
		List<VisitLog> visitLogList = visitMgr.findVistLogExport(siteid, type);
		if (visitLogList != null && visitLogList.size() > 0) {
			for (VisitLog vl : visitLogList) {
				StringBuilder sb = new StringBuilder();
				sb.append(vl.getNickname() + ",");
				sb.append(vl.getCreatetime() + ",");
				sb.append(vl.getIp() + ",");
				list.add(sb.toString());
			}
			return list;
		} else {
			return null;
		}
	}

	public void setActivityManager(IActivityManager activityManager) {
		this.activityManager = activityManager;
	}

	@Override
	public IDto findSiteNames(long groupid) {
		PageActionDto dto = new PageActionDto();
		List<Site> list = siteManager.findSiteNames(groupid);
		if (list.size() > 0) {
			for (Site s : list) {
				s.setActivityCount(activityManager.findActivityCounts(s.getId()));
				s.setModules(siteManager.findSiteModule(s.getId()));
			}
		}
		dto.setSite(siteManager.findSiteById(groupid));
		dto.setSites(list);
		return dto;
	}

	@Override
	public String composeCreateActivitySub(String name, List<Long> modules, long siteid, long ownerid) {
		try {
			long id = activityManager.saveActivityAndModule(name, modules, siteid, ownerid);
			if (id > 0) {
				return "Y";
			} else {
				return "系统出错!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "系统出错!";
		}
	}

	@Override
	public IDto composeCreateActivitySub() {
		SiteIndexActionDto dto = new SiteIndexActionDto();
		dto.setModules(siteManager.findModules());
		return dto;
	}

	@Override
	public IDto composeFindActivityList(long siteid) {
		SiteIndexActionDto dto = new SiteIndexActionDto();
		dto.setActivity(activityManager.findActivityList(siteid));
		// 根据siteid找到activity的name以及status
		List<Activity> activity = activityManager.findActivityList(siteid);
		for (int i = 0; i < activity.size(); i++) {
			List<Module> modules = activityManager.findModuleList(activity.get(i).getId());
			activity.get(i).setModules(modules);
		}
		dto.setActivity(activity);
		return dto;
	}

	@Override
	public IDto composePageconfig(long siteid, Account account) {
		PageConfigDto dto = new PageConfigDto();
		Site site = siteManager.findSiteById(siteid);
		if (site == null) {
			return dto;
		}
		if (site.getOwnerId() == account.getOwner().getId()) {
			dto.setSite(site);
			List<Site> list = siteManager.findSiteBySiteGroupId(siteid);
			for (int i = 0; i < list.size(); i++) {
				if (list.size() > 0) {
					list.get(i).setPages(pageManager.findPageBySiteid(list.get(i).getId()));
				}
			}
			dto.setSites(list);
		}

		return dto;
	}

	@Override
	public IDto composePageconfigNew(long siteid, Account account) {
		PageConfigDto dto = new PageConfigDto();
		Site site = siteManager.findSiteById(siteid);
		if (site != null) {
			if (site.getOwnerId() == account.getOwner().getId()) {
				List<Page> pages = pageManager.findPageBySiteid(siteid);
				Sitegroup  sg = siteManager.findSitegroupByid(site.getSitegroupid());
				site.setPages(pages);
				dto.setSite(site);
				dto.setSitegroup(sg);
			}
		}
		return dto;
	}

	@Override
	public int deleteSiteGroup(long sgid) {
		return siteManager.deleteSiteGroup(sgid);
	}

	@Override
	public int updateSiteGroup(String name, long sgid) {
		return siteManager.updateSiteGroup(name, sgid);
	}

	public IDto visitViewAllData(long sgid, int daynum) {
		VisitViewAllDto dto = new VisitViewAllDto();
		List<Integer> hvisitnum = new ArrayList<Integer>();
		List<Integer> nhvisitnum = new ArrayList<Integer>();
		List<Integer> nhvisitadd = new ArrayList<Integer>();
		List<String> datelist = new ArrayList<String>();
		// List<Integer> hdynamicnum = new ArrayList<Integer>();
		List<VisitAnalyse> list = visitReportManager.findVisitAnalyseByDate(sgid);
		for (VisitAnalyse visitAnalyse : list) {
			hvisitnum.add(visitAnalyse.getVisitTotalNum());
			nhvisitnum.add(visitAnalyse.getVisitNum());
			nhvisitadd.add(visitAnalyse.getAddVisitNum());
			// hdynamicnum.add(visitAnalyse.getHdnum());
			datelist.add("'" + visitAnalyse.getDate().substring(5, 10) + "'");
		}
		dto.setDatelist(datelist);
		dto.setNhvisitadd(nhvisitadd);
		dto.setNhvisitnum(nhvisitnum);
		dto.setHvisitnum(hvisitnum);
		// dto.setHdynamicnum(hdynamicnum);
		return dto;
	}

	public IDto hdViewAllData(long sgid, int daynum, Account account) {
		VisitViewAllDto dto = new VisitViewAllDto();
		Site site = siteManager.findSiteById(sgid);
		if (site == null) {
			return null;
		}

		dto.setGroupname(site.getName());
		if (site.getOwnerId() != account.getOwner().getId()) {
			return null;
		}

		List<String> datelist = new ArrayList<String>();
		List<Integer> hdynamicnum = new ArrayList<Integer>();
		List<VisitAnalyse> list = visitReportManager.findVisitAnalyseByDate(sgid);
		for (VisitAnalyse visitAnalyse : list) {
			hdynamicnum.add(visitAnalyse.getHdnum());
			datelist.add("'" + visitAnalyse.getDate().substring(5, 10) + "'");
		}
		dto.setDatelist(datelist);
		dto.setHdynamicnum(hdynamicnum);
		return dto;
	}

	public IDto visitViewAllDataAjax(long sgid, Account account) {
		VisitViewAllDto dto = new VisitViewAllDto();
		Site site = siteManager.findSiteById(sgid);
		if (site == null) {
			return dto;
		}
		if (site.getOwnerId() != account.getOwner().getId()) {
			return dto;
		}
		int total = visitReportManager.sumHdNum(sgid);
		List<Object> dataphone = new ArrayList<Object>();
		List<Object> datapc = new ArrayList<Object>();
		List<Object> datapad = new ArrayList<Object>();
		int phone = visitReportManager.findDynamicNumBySiteGroupPhone(sgid);
		int pad = visitReportManager.findDynamicNumBySiteGroupPad(sgid);
		int pc = visitReportManager.findDynamicNumBySiteGroupPC(sgid);
		DecimalFormat df2 = new DecimalFormat("##.##%");
		if (total != 0) {
			double phoneformat = (phone * 1.00) / (total * 1.00);
			double pcformat = (pc * 1.00) / (total * 1.00);
			double padformat = (pad * 1.00) / (total * 1.00);
			dataphone.add("phone=" + (phone != 0 ? df2.format(phoneformat) : "0%"));
			dataphone.add(phone);
			datapc.add("pc=" + (pc != 0 ? df2.format(pcformat) : "0%"));
			datapc.add(pc);
			datapad.add("pad=" + (pad != 0 ? df2.format(padformat) : "0%"));
			datapad.add(pad);
		} else {
			dataphone.add("phone=0%");
			dataphone.add(0);
			datapc.add("pc=0%");
			datapc.add(0);
			datapad.add("pad=0%");
			datapad.add(0);
		}
		List<List<Object>> zongduan = new ArrayList<List<Object>>();
		zongduan.add(dataphone);
		zongduan.add(datapc);
		zongduan.add(datapad);
		List<List<Object>> areaobj = new ArrayList<List<Object>>();
		List<ReportArea> arealist = visitReportManager.findDymamicAreaBySiteGroup(sgid);
		if (arealist != null && arealist.size() > 0) {
			for (ReportArea reportArea : arealist) {
				int num = reportArea.getNum();
				double areanum = 0.0;
				String areastring = reportArea.getArea();
				List<Object> areas = new ArrayList<Object>();
				if (total != 0) {
					areanum = (num * 1.00) / (total * 1.00);
					areas.add(areastring + "=" + (areanum != 0 ? df2.format(areanum) : "0%"));
				} else {
					areas.add(areastring + "=0%");
				}
				areas.add(num);
				areaobj.add(areas);
			}
		}
		List<List<Object>> sourceobj = new ArrayList<List<Object>>();
		List<ReportSource> sourcelist = visitReportManager.findDymamicSourceBySiteGroup(sgid);
		if (sourcelist != null && sourcelist.size() > 0) {
			for (ReportSource reportSource : sourcelist) {
				int num = reportSource.getNum();
				String source = reportSource.getSource();
				if (source == null)
					source = "未知来源";
				List<Object> resorces = new ArrayList<Object>();
				if (total != 0) {
					double sourcenum = (num * 1.00) / (total * 1.00);
					resorces.add(source + "=" + (sourcenum != 0 ? df2.format(sourcenum) : "0%"));
				} else {
					resorces.add(source + "=0%");
				}
				resorces.add(reportSource.getNum());
				sourceobj.add(resorces);
			}
		}
		List<List<Object>> sex = new ArrayList<List<Object>>();
		List<Object> genderMan = new ArrayList<Object>();
		int manNum = visitReportManager.findDynamicNumBySiteGroupGender(sgid, "M");
		if (total != 0) {
			double mnum = (manNum * 1.00) / (total * 1.00);
			genderMan.add("男=" + (mnum != 0 ? df2.format(mnum) : "0%"));
		} else {
			genderMan.add("男=0%");
		}

		genderMan.add(manNum);
		List<Object> genderWomen = new ArrayList<Object>();
		int womenNum = visitReportManager.findDynamicNumBySiteGroupGender(sgid, "F");
		if (total != 0) {
			double wnum = (womenNum * 1.00) / (total * 1.00);
			genderWomen.add("女=" + (wnum != 0 ? df2.format(wnum) : "0%"));
		} else {
			genderMan.add("女=0%");
		}
		genderWomen.add(womenNum);

		List<Object> genderOther = new ArrayList<Object>();
		int otherGender = total - manNum - womenNum;
		if (total != 0) {
			double othergen = (otherGender * 1.00) / (total * 1.00);
			genderOther.add("其他=" + (othergen != 0 ? df2.format(othergen) : "0%"));
		} else {
			genderOther.add("其他=0%");
		}
		genderOther.add(otherGender);
		sex.add(genderMan);
		sex.add(genderWomen);
		sex.add(genderOther);
		dto.setArea(areaobj);
		dto.setZongduan(zongduan);
		dto.setSource(sourceobj);
		dto.setSex(sex);
		dto.setHfansN(fansAnalyseManager.findHdFansTotal(sgid, "N"));
		dto.setHfansY(fansAnalyseManager.findHdFansTotal(sgid, "Y"));
		return dto;
	}

	/**
	 * 得出互动列表用 从arylist中得到ip,num,time 根据得到的ip放入到arylist中循环遍历得到area
	 */
	@Override
	public IDto showAnalysisReport(long siteid, int pageId, long ownerid) {
		RgAnalysisDto rgdto = new RgAnalysisDto();
		Site site = siteManager.findSiteById(siteid);
		rgdto.setSite(site);
		if (site == null) {
			return rgdto;
		}
		if (site.getOwnerId() != ownerid && site.getOwnerId() != 0) {
			return rgdto;
		}
		rgdto.setHdnum(visitReportManager.sumHdNum(siteid));
		int start = (pageId - 1) * IPageConstants.CONTENT_SIZE;
		int total = visitReportManager.findDynamicAreaReportTotal(siteid);
		if (total > 0) {
			// 得出ip，人数，时间的集合
			List<AreaAnalysis> arylist = visitReportManager.showDynamicAreaBySiteGroupId(siteid, start, IPageConstants.CONTENT_SIZE);
			rgdto.setArea(arylist);
		}
		Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_SIZE);
		rgdto.setPager(pager);
		return rgdto;
	}

	/**
	 * 得出highchart饼图的人数
	 */
	@Override
	public IDto showVisitHighChartReport(long sgid) {

		HighChartsDataDto hcdto = new HighChartsDataDto();
		List<List<Object>> arrlist = new ArrayList<List<Object>>();
		// 得出ip，人数，时间的集合
		List<ReportArea> arealist = visitReportManager.findVisitAreaBySiteGroup(sgid);
		int unother = 0;
		int total = visitReportManager.sumVisitNum(sgid);
		if (arealist != null && arealist.size() > 0) {
			for (int i = 0; i < arealist.size(); i++) {
				ReportArea area = arealist.get(i);
				// 根据得到的ip查到对应的区域
				List<Object> oblist = new ArrayList<Object>();

				oblist.add(area.getArea());
				oblist.add(area.getNum());
				unother = unother + area.getNum();
				arrlist.add(oblist);
			}
		}
		if (total != 0) {
			int other = total - unother;
			if (other != 0) {
				List<Object> otherlist = new ArrayList<Object>();
				otherlist.add("其它");
				otherlist.add(other);
				arrlist.add(otherlist);
			}
		}
		hcdto.setAreas(arrlist);
		return hcdto;
	}

	@Override
	public IDto hdAreaReport(long siteid, long ownerid) {
		HighChartsDataDto hdto = new HighChartsDataDto();
		Site sg = siteManager.findSiteById(siteid);
		hdto.setSite(sg);
		if (sg == null) {
			return hdto;
		}
		if (sg.getOwnerId() != ownerid && sg.getOwnerId() != 0) {
			return hdto;
		}
		List<HdModel> models = hdRecordManager.findHdModelListBySgid(siteid);
		hdto.setModels(models);
		List<List<Object>> arrlist = new ArrayList<List<Object>>();
		List<ReportArea> arylist = visitReportManager.findDymamicAreaBySiteGroup(siteid);
		int total = visitReportManager.findDAreaReportTotal(siteid);
		int unother = 0;
		DecimalFormat df2 = new DecimalFormat("##.##%");
		if (arylist != null && arylist.size() > 0) {
			for (int i = 0; i < arylist.size(); i++) {
				ReportArea area = arylist.get(i);
				int num = area.getNum();
				unother += num;
				double areanum = 0.0;
				List<Object> list = new ArrayList<Object>();
				list.add(area.getArea());
				list.add(area.getNum());
				if (total != 0) {
					areanum = (num * 1.00) / (total * 1.00);
					area.setNumpercent((areanum != 0 ? df2.format(areanum) : "0%"));
				} else {
					area.setNumpercent("=0%");
				}
				arrlist.add(list);
			}
		}
		if (total != 0) {
			int other = total - unother;
			if (other != 0) {
				double areanumother = 0.0;
				List<Object> otherlist = new ArrayList<Object>();
				ReportArea othearea = new ReportArea();
				othearea.setArea("其它");
				othearea.setNum(other);
				areanumother = (other * 1.00) / (total * 1.00);
				othearea.setNumpercent((areanumother != 0 ? df2.format(areanumother) : "0%"));
				otherlist.add("其它");
				otherlist.add(other);
				arrlist.add(otherlist);
				arylist.add(othearea);
			}
		}
		hdto.setAreas(arrlist);
		hdto.setArea(arylist);
		Gson gson = new Gson();
		hdto.setAreastr(gson.toJson(arrlist));
		return hdto;
	}

	@Override
	public IDto hdNumReport(long siteid, long ownerid) {
		HdNumDataDto hdto = new HdNumDataDto();
		Site sg = siteManager.findSiteById(siteid);
		hdto.setSite(sg);
		if (sg == null) {
			return hdto;
		}
		if (sg.getOwnerId() != ownerid && sg.getOwnerId() != 0) {
			return hdto;
		}
		List<List<Object>> arrlist = new ArrayList<List<Object>>();
		List<HdModel> models = hdRecordManager.findHdModelListBySgid(siteid);
		hdto.setModels(models);
		List<ReportHdNum> arylist = visitReportManager.findDymamicHdNumBySiteGroup(siteid);
		int total = visitReportManager.findDymamicHdNumTotal(siteid);
		DecimalFormat df2 = new DecimalFormat("##.##%");
		if (arylist != null && arylist.size() > 0) {
			for (int i = 0; i < arylist.size(); i++) {
				ReportHdNum hdNum = arylist.get(i);
				int num = hdNum.getHdnum();
				double hdnum = 0.0;
				List<Object> list = new ArrayList<Object>();
				list.add(hdNum.getNickname());
				list.add(hdNum.getHdnum());
				if (total != 0) {
					hdnum = (num * 1.00) / (total * 1.00);
					hdNum.setHdnumpercent((hdnum != 0 ? df2.format(hdnum) : "0%"));
				} else {
					hdNum.setHdnumpercent("=0%");
				}
				arrlist.add(list);
			}
		}
		// if (total != 0)
		// {
		// int other=total-unother;
		// double hdnumother= 0.0;
		// List<Object> otherlist = new ArrayList<Object>();
		// ReportHdNum otherhd=new ReportHdNum();
		// otherhd.setNickname("其他");
		// othearea.setNum(other);
		// hdnumother = (other * 1.00) / (total * 1.00);
		// othearea.setNumpercent((hdnumother != 0 ? df2.format(hdnumother) :
		// "0%"));
		// otherlist.add("其他");
		// otherlist.add(other);
		// arrlist.add(otherlist);
		// arylist.add(othearea);
		// }
		hdto.setList(arylist);
		Gson gson = new Gson();
		hdto.setNicknames(gson.toJson(arrlist));
		return hdto;
	}

	@Override
	public IDto hdPerNumReport(long siteid, long ownerid) {
		HdPerNumDataDto hdto = new HdPerNumDataDto();
		Site sg = siteManager.findSiteById(siteid);
		hdto.setSite(sg);
		if (sg == null) {
			return hdto;
		}
		if (sg.getOwnerId() != ownerid && sg.getOwnerId() != 0) {
			return hdto;
		}
		List<HdModel> models = hdRecordManager.findHdModelListBySgid(siteid);
		hdto.setModels(models);
		int total = visitReportManager.findDymamicHdNumTotalBySiteGroup(siteid);
		DecimalFormat df2 = new DecimalFormat("##.##%");
		int onenum = visitReportManager.findHdPerNumByNum(siteid, 1);
		int twonum = visitReportManager.findHdPerNumByNum(siteid, 2);
		int threenum = visitReportManager.findHdPerNumByNum(siteid, 3);
		int fournum = visitReportManager.findHdPerNumByNum(siteid, 4);
		int fivenum = visitReportManager.findHdPerNumByNum(siteid, 5);
		if (total != 0) {
			double hdnumnone = (onenum * 1.00) / (total * 1.00);
			String onepercent = hdnumnone != 0 ? df2.format(hdnumnone) : "0%";
			hdto.setOnenum(onenum);
			hdto.setOnenumpercent(onepercent);
			double hdnumntwo = (twonum * 1.00) / (total * 1.00);
			String twopercent = hdnumntwo != 0 ? df2.format(hdnumntwo) : "0%";
			hdto.setTwonum(twonum);
			hdto.setTwonumpercent(twopercent);
			double hdnumnthree = (threenum * 1.00) / (total * 1.00);
			String threepercent = hdnumnthree != 0 ? df2.format(hdnumnthree) : "0%";
			hdto.setThreenum(threenum);
			hdto.setThreenumpercent(threepercent);
			double hdnumnfour = (fournum * 1.00) / (total * 1.00);
			String fourpercent = hdnumnfour != 0 ? df2.format(hdnumnfour) : "0%";
			hdto.setFournum(fournum);
			hdto.setFournumpercent(fourpercent);
			double hdnumnfive = (fivenum * 1.00) / (total * 1.00);
			String fivepercent = hdnumnfive != 0 ? df2.format(hdnumnfive) : "0%";
			hdto.setFivenum(fivenum);
			hdto.setFivenumpercent(fivepercent);
		}
		return hdto;
	}

	@Override
	public IDto hdAreaList(long sgid, long ownerid, int pageId) {
		HdAreaDto dto = new HdAreaDto();
		int start = (pageId - 1) * IPageConstants.CONTENT_SIZE;
		int totalpage = visitReportManager.findDynamicAreaReportTotal(sgid);
		if (totalpage > 0) {
			List<AreaAnalysis> listarea = visitReportManager.showDynamicAreaBySiteGroupId(sgid, start, IPageConstants.CONTENT_SIZE);
			dto.setList(listarea);
		}
		Pager pager = new Pager(pageId, totalpage, IPageConstants.CONTENT_SIZE);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public IDto showDynamicHighChartReport(long sgid) {

		HighChartsDataDto hdto = new HighChartsDataDto();
		List<List<Object>> arrlist = new ArrayList<List<Object>>();
		List<ReportArea> arylist = visitReportManager.findDymamicAreaBySiteGroup(sgid);
		int total = visitReportManager.findDAreaReportTotal(sgid);
		int unother = 0;
		if (arylist != null && arylist.size() > 0) {
			for (int i = 0; i < arylist.size(); i++) {
				ReportArea area = arylist.get(i);
				List<Object> list = new ArrayList<Object>();
				list.add(area.getArea());
				list.add(area.getNum());
				unother = unother + area.getNum();
				arrlist.add(list);
			}
		}
		if (total != 0) {
			int other = total - unother;
			if (other != 0) {
				List<Object> otherlist = new ArrayList<Object>();
				otherlist.add("其它");
				otherlist.add(other);
				arrlist.add(otherlist);
			}
		}
		hdto.setAreas(arrlist);
		return hdto;
	}

	@Override
	public IDto composeUpdateActivity(long activityid) {
		SiteIndexActionDto dto = new SiteIndexActionDto();
		Activity activity = activityManager.findActivityNameById(activityid);
		// 找到选择的模块
		activity.setModuleids(activityManager.findModuleIdByActivityid(activityid));
		dto.setActivityone(activity);
		// 找到所有的模块
		dto.setModules(siteManager.findModules());
		return dto;
	}

	@Override
	public String composeDelActivity(long activityid) {
		return activityManager.delteActivity(activityid);
	}

	@Override
	public String composeCompileActivity(long activityid, ArrayList<Long> moduleList, String activityname) {
		return activityManager.updateActivity(activityid, moduleList, activityname);
	}

	@Override
	public Site findSiteById(long id) {
		return siteManager.findSiteById(id);
	}

	public IDto findDanymicUserRecord(long sgid, Account account, DanymicUserSiftDto siftDto, int page) {
		DanymicRecordDto dto = new DanymicRecordDto();
		Site site = siteManager.findSiteById(sgid);
		if (site != null) {
			if (site.getOwnerId() != account.getOwner().getId()) {
				return dto;
			}
			dto.setGroupname(site.getName());
			List<DanymicAton> alist = visitReportManager.findAtionBySgid(sgid);
			dto.setAlist(alist);
			int start = (page - 1) * IPageConstants.DANYMIC_USER_RECORD_LIMIT;
			int total = visitReportManager.findDanymicRecordListTotal(sgid, siftDto);
			if (total > 0) {
				dto.setList(visitReportManager.findDanymicRecordList(sgid, siftDto, start, IPageConstants.DANYMIC_USER_RECORD_LIMIT));
			}
			Pager pager = new Pager(page, total, IPageConstants.DANYMIC_USER_RECORD_LIMIT);
			dto.setPager(pager);
		}
		return dto;
	}

	public IDto findDanymicUserRecordByNickname(long sgid, Account account, String nickname, int page) {
		DanymicRecordDto dto = new DanymicRecordDto();
		Site site = siteManager.findSiteById(sgid);
		if (site != null) {
			if (site.getOwnerId() != account.getOwner().getId()) {
				return dto;
			}
			dto.setAlist(visitReportManager.findAtionBySgid(sgid));
			dto.setGroupname(site.getName());
			int start = (page - 1) * IPageConstants.DANYMIC_USER_RECORD_LIMIT;
			int total = visitReportManager.findDanymicRecordListTotalByNickName(sgid, nickname);
			if (total > 0) {
				dto.setList(visitReportManager.findDanymicRecordListByNickName(sgid, nickname, start, IPageConstants.DANYMIC_USER_RECORD_LIMIT));
			}
			Pager pager = new Pager(page, total, IPageConstants.DANYMIC_USER_RECORD_LIMIT);
			dto.setPager(pager);
		}
		return dto;
	}

	public IDto findVisitRecordByNickname(long sgid, Account account, String nickname, int page) {
		CustomVisitDto dto = new CustomVisitDto();
		Site site = siteManager.findSiteById(sgid);
		if (site != null) {
			if (site.getOwnerId() != account.getOwner().getId()) {
				return dto;
			}
			int start = (page - 1) * IPageConstants.DANYMIC_USER_RECORD_LIMIT;
			int total = visitReportManager.findVisitRecordListTotalByNickName(sgid, nickname);
			if (total > 0) {
				dto.setReports(visitReportManager.findVisitRecordListByNickName(sgid, nickname, start, IPageConstants.DANYMIC_USER_RECORD_LIMIT));
			}
			Pager pager = new Pager(page, total, IPageConstants.DANYMIC_USER_RECORD_LIMIT);
			dto.setPager(pager);
		}
		return dto;
	}

	public ReportGenderAnalyse visitGenderData(long siteid) {
		ReportGenderAnalyse genderAnalyse = visitReportManager.findReportGenderAnalyseByGsid(siteid);
		if (genderAnalyse == null) {
			genderAnalyse = new ReportGenderAnalyse();
		}
		DecimalFormat df2 = new DecimalFormat("##%");
		int vfmum = genderAnalyse.getVfnum();
		int vmnum = genderAnalyse.getVmnum();
		int vothernum = genderAnalyse.getVothernum();
		int vtotal = vfmum + vmnum + vothernum;
		double wnum = 0.0;
		double mnum = 0.0;
		double othergen = 0.0;
		if (vtotal != 0) {
			wnum = (vfmum * 1.00) / (vtotal * 1.00);
			mnum = (vmnum * 1.00) / (vtotal * 1.00);
			othergen = (vothernum * 1.00) / (vtotal * 1.00);
		}
		genderAnalyse.setVfnumstr("女=" + (wnum != 0.0 ? df2.format(wnum) : "0%"));
		genderAnalyse.setVmnumstr("男=" + (mnum != 0.0 ? df2.format(mnum) : "0%"));
		genderAnalyse.setVothernumstr("其他=" + (othergen != 0.0 ? df2.format(othergen) : "0%"));
		return genderAnalyse;
	}

	public ReportGenderAnalyse danymicGenderData(long sgid, Account account) {
		ReportGenderAnalyse genderAnalyse = visitReportManager.findReportGenderAnalyseByGsid(sgid);
		Site site = siteManager.findSiteById(sgid);
		if (site == null) {
			return null;
		}
		if (site.getOwnerId() != account.getOwner().getId()) {
			return null;
		}
		if (genderAnalyse == null) {
			genderAnalyse = new ReportGenderAnalyse();
		}
		genderAnalyse.setGroupname(site.getName());
		DecimalFormat df2 = new DecimalFormat("##%");
		int hfnum = genderAnalyse.getHfnum();
		int hmnum = genderAnalyse.getHmnum();
		int hothernum = genderAnalyse.getHothernum();
		int htotal = hfnum + hmnum + hothernum;
		double whnum = 0.0;
		double mhnum = 0.0;
		double otherhnum = 0.0;
		if (htotal != 0) {
			whnum = (hfnum * 1.00) / (htotal * 1.00);
			mhnum = (hmnum * 1.00) / (htotal * 1.00);
			otherhnum = (hothernum * 1.00) / (htotal * 1.00);
		}
		genderAnalyse.setHfnumstr("女=" + (whnum != 0 ? df2.format(whnum) : "0%"));
		genderAnalyse.setHmnumstr("男=" + (mhnum != 0 ? df2.format(mhnum) : "0%"));
		genderAnalyse.setHothernumstr("其他=" + (otherhnum != 0 ? df2.format(otherhnum) : "0%"));
		return genderAnalyse;
	}

	public TerminalDataDto danymicTerminalData(long sgid, Account account) {
		TerminalDataDto dto = new TerminalDataDto();
		List<ReportTerminalAnalyse> list = visitReportManager.findReportTerminalAnalyseByGsid(sgid);
		Site site = siteManager.findSiteById(sgid);
		if (site == null) {
			return null;
		}
		if (site.getOwnerId() != account.getOwner().getId()) {
			return null;
		}
		dto.setName(site.getName());
		int total = visitReportManager.findDynamicTerminalReportTotal(sgid);
		List<List<Object>> zongduan = new ArrayList<List<Object>>();
		DecimalFormat df2 = new DecimalFormat("##%");
		for (ReportTerminalAnalyse reportTerminalAnalyse : list) {
			String ter = reportTerminalAnalyse.getTerminal();
			String terminal = "";
			if ("C".equalsIgnoreCase(ter)) {
				terminal = "PC=";
			} else if ("A".equalsIgnoreCase(ter)) {
				terminal = "Pad=";
			} else if ("P".equalsIgnoreCase(ter)) {
				terminal = "Phone=";
			}
			List<Object> lo = new ArrayList<Object>();
			int dnum = reportTerminalAnalyse.getHnum();
			double hdnum = (dnum * 1.00) / (total * 1.00);
			lo.add(terminal + (dnum != 0 ? df2.format(hdnum) : "0%"));
			lo.add(dnum);
			zongduan.add(lo);
		}
		dto.setList(list);
		dto.setTerminaldata(zongduan);
		return dto;
	}

	public TerminalDataDto visitTerminalData(long siteid, Account account) {
		TerminalDataDto dto = new TerminalDataDto();
		List<ReportTerminalAnalyse> list = visitReportManager.findReportTerminalAnalyseByGsid(siteid);
		Site site = siteManager.findSiteById(siteid);
		if (site == null) {
			return null;
		}
		if (site.getOwnerId() != account.getOwner().getId()) {
			return null;
		}
		dto.setName(site.getName());
		int total = visitReportManager.findVisitTerminalReportTotal(siteid);
		List<List<Object>> zongduan = new ArrayList<List<Object>>();
		DecimalFormat df2 = new DecimalFormat("##%");
		for (ReportTerminalAnalyse reportTerminalAnalyse : list) {
			String ter = reportTerminalAnalyse.getTerminal();
			String terminal = "";
			if ("C".equalsIgnoreCase(ter)) {
				terminal = "PC=";
			} else if ("A".equalsIgnoreCase(ter)) {
				terminal = "Pad=";
			} else if ("P".equalsIgnoreCase(ter)) {
				terminal = "Phone=";
			}
			List<Object> lo = new ArrayList<Object>();
			int vnum = reportTerminalAnalyse.getVnum();
			double hdnum = (vnum * 1.00) / (total * 1.00);
			lo.add(terminal + (vnum != 0 ? df2.format(hdnum) : "0%"));
			lo.add(vnum);
			zongduan.add(lo);
		}
		dto.setList(list);
		dto.setTerminaldata(zongduan);
		return dto;
	}

	@Override
	public IDto activityReportDataAll(long siteid, long activityid, long ownerid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		Site site = siteManager.findSiteById(siteid);
		if (site != null && site.getOwnerId() != ownerid) {
			return dto;
		}
		dto.setSite(site);
		dto.setActivityList(list);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			VisitLog visitLog = activityManager.findUserCount(activityid);
			int userCount = (int) visitLog.getCountSum();
			VisitLog unkownVisitLog = activityManager.findUnkonwnUserCount(activityid);
			int unKnownCount = (int) unkownVisitLog.getCountSum();
			dto.setUnKnownCount(unKnownCount);
			dto.setUserCount(userCount);
			dto.setCountSum(userCount + unKnownCount);

			long participateCount = activityManager.findParticipateCount(activityid);
			long participateSuccessCount = activityManager.findParticipateSuccessCount(activityid);
			long participateFailCount = participateCount - participateSuccessCount;
			dto.setParticipateCount(participateCount);
			dto.setParticipateSuccessCount(participateSuccessCount);
			dto.setParticipateFailCount(participateFailCount);

			dto.setCreatetime(visitLog.getCreateTime());
			dto.setUnknowncreatetime(unkownVisitLog.getCreateTime());

			List<Terminal> terminal = activityManager.findVisitCountByTerminal(activityid);
			for (Terminal t : terminal) {
				int anoncount = activityManager.findAnonVisitCountByTerminal(t.getName(), activityid);
				t.setAnoncount(anoncount);
			}
			dto.setTerminal(terminal);

			List<Source> source = activityManager.findVistitCountBySource(activityid);
			for (Source s : source) {
				int anoncount = activityManager.findAnonVisitCountBySource(s.getName(), activityid);
				s.setAnoncount(anoncount);
			}
			dto.setSource(source);
		}
		return dto;
	}

	public IDto findDanymicRecordDetailList(long sgid, long siteid, String wbuid, DanymicUserSiftDto siftDto, int pageId) {
		DanymicRecordDetailDto dto = new DanymicRecordDetailDto();
		int start = (pageId - 1) * IPageConstants.DANYMIC_USER_RECORD_DETAIL_LIMIT;
		List<DanymicUserDetail> list = visitReportManager.findDanymicRecordDetailList(sgid, wbuid, siftDto, start, IPageConstants.DANYMIC_USER_RECORD_DETAIL_LIMIT);
		for (DanymicUserDetail record : list) {
			HdModel model = hdManagers.get(record.getAction()).findHdModel(sgid, record.getEntityid());
			record.setTarget(model.getName());
		}
		dto.setList(list);
		dto.setTotal(visitReportManager.findDanymicRecordDetailTotal(sgid, wbuid, siftDto));
		UserInfo info = userMgr.findUserInfo(siteid, wbuid);
		dto.setUser(info);
		return dto;
	}

	/**
	 * 访问用户表查看详情
	 */
	@Override
	public IDto findVisitReportDetailList(long sgid, long siteid, String wbuid, int pageId) {
		VisitReportDetailDto dto = new VisitReportDetailDto();
		dto.setNum(visitReportManager.countVisitNum(sgid, wbuid));
		int start = (pageId - 1) * IPageConstants.DANYMIC_USER_RECORD_DETAIL_LIMIT;
		int total = visitReportManager.findVisitReportDetailTotal(sgid, wbuid);
		dto.setTotal(total);
		if (total > 0) {
			List<VisitReportDetail> list = visitReportManager.findVisitReportDetail(sgid, wbuid, start, IPageConstants.DANYMIC_USER_RECORD_DETAIL_LIMIT);
			dto.setList(list);
		}
		UserInfo user = userMgr.findUserInfo(siteid, wbuid);
		dto.setUser(user);
		Pager pager = new Pager(pageId, total, IPageConstants.DANYMIC_USER_RECORD_DETAIL_LIMIT);
		dto.setPager(pager);
		return dto;
	}

	public List<DanymicUserDetail> findDanymicRecordDetailListData(long sgid, String wbuid, DanymicUserSiftDto siftDto, int pageId) {
		int start = (pageId - 1) * IPageConstants.DANYMIC_USER_RECORD_DETAIL_LIMIT;
		List<DanymicUserDetail> list = visitReportManager.findDanymicRecordDetailList(sgid, wbuid, siftDto, start, IPageConstants.DANYMIC_USER_RECORD_DETAIL_LIMIT);
		for (DanymicUserDetail record : list) {
			HdModel model = hdManagers.get(record.getAction()).findHdModel(sgid, record.getEntityid());
			record.setTarget(model.getName());
		}
		return list;
	}

	@Override
	public IDto activityReportData(long siteid, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		dto.setActivityList(list);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			VisitLog visitLog = activityManager.findUserCount(activityid);
			int userCount = (int) visitLog.getCountSum();
			VisitLog unkownVisitLog = activityManager.findUnkonwnUserCount(activityid);
			int unKnownCount = (int) unkownVisitLog.getCountSum();
			dto.setUnKnownCount(unKnownCount);
			dto.setUserCount(userCount);
		}
		return dto;
	}

	@Override
	public IDto visitTerminalCount(long siteid, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		dto.setActivityList(list);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			List<Terminal> terminal = activityManager.findVisitCountByTerminal(activityid);
			for (Terminal t : terminal) {
				int anoncount = activityManager.findAnonVisitCountByTerminal(t.getName(), activityid);
				t.setAnoncount(anoncount);
			}
			List<List<Object>> terminalList = new ArrayList<List<Object>>();
			if (terminal != null && terminal.size() > 0) {
				for (Terminal t : terminal) {
					List<Object> oblist = new ArrayList<Object>();
					oblist.add(t.getNameStr());
					oblist.add(t.getAnoncount() + t.getCount());
					terminalList.add(oblist);
				}
			}
			dto.setTerminalList(terminalList);
		}
		return dto;
	}

	@Override
	public IDto visitSourceCount(long siteid, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		dto.setActivityList(list);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			List<Source> source = activityManager.findVistitCountBySource(activityid);
			for (Source s : source) {
				int anoncount = activityManager.findAnonVisitCountBySource(s.getName(), activityid);
				s.setAnoncount(anoncount);
			}
			List<List<Object>> sourceList = new ArrayList<List<Object>>();
			if (source != null && source.size() > 0) {
				for (Source t : source) {
					List<Object> oblist = new ArrayList<Object>();
					oblist.add(t.getName());
					oblist.add(t.getAnoncount() + t.getCount());
					sourceList.add(oblist);
				}
			}
			dto.setSourceList(sourceList);
		}
		return dto;
	}

	@Override
	public IDto activityParticipateData(long siteid, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		dto.setActivityList(list);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			List<Terminal> terminal = activityManager.findParticipateCountByTerminal(activityid);
			List<List<Object>> terminalList = new ArrayList<List<Object>>();
			if (terminal != null && terminal.size() > 0) {
				for (Terminal t : terminal) {
					List<Object> oblist = new ArrayList<Object>();
					oblist.add(t.getNameStr());
					oblist.add(t.getCount());
					terminalList.add(oblist);
				}
			}
			dto.setTerminalList(terminalList);
		}
		return dto;
	}

	@Override
	public IDto activityParticipateSourceData(long siteid, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		dto.setActivityList(list);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			List<Source> source = activityManager.findParticipateCountBySource(activityid);
			List<List<Object>> sourceList = new ArrayList<List<Object>>();
			if (source != null && source.size() > 0) {
				for (Source t : source) {
					List<Object> oblist = new ArrayList<Object>();
					oblist.add(t.getName());
					oblist.add(t.getCount());
					sourceList.add(oblist);
				}
			}
			dto.setSourceList(sourceList);
		}
		return dto;
	}

	@Override
	public IDto activityParticipateDataAll(long siteid, long activityid, long ownerid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		Site site = siteManager.findSiteById(siteid);
		if (site != null && site.getOwnerId() != ownerid) {
			return dto;
		}
		dto.setActivityList(list);
		dto.setSite(site);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			List<Terminal> terminal = activityManager.findParticipateCountByTerminal(activityid);
			dto.setTerminal(terminal);

			List<Source> source = activityManager.findParticipateCountBySource(activityid);
			dto.setSource(source);

			VisitLog visitLog = activityManager.findUserCount(activityid);
			int userCount = (int) visitLog.getCountSum();
			VisitLog unkownVisitLog = activityManager.findUnkonwnUserCount(activityid);
			int unKnownCount = (int) unkownVisitLog.getCountSum();
			dto.setCountSum(userCount + unKnownCount);
			long participateCount = activityManager.findParticipateCount(activityid);
			long participateSuccessCount = activityManager.findParticipateSuccessCount(activityid);
			long participateFailCount = participateCount - participateSuccessCount;
			dto.setParticipateCount(participateCount);
			dto.setParticipateSuccessCount(participateSuccessCount);
			dto.setParticipateFailCount(participateFailCount);

		}
		return dto;
	}

	@Override
	public IDto activityParticipateSuccessData(long siteid, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		dto.setActivityList(list);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			List<Terminal> terminal = activityManager.findParticipateSuccessCountByTerminal(activityid);
			List<List<Object>> terminalList = new ArrayList<List<Object>>();
			if (terminal != null && terminal.size() > 0) {
				for (Terminal t : terminal) {
					List<Object> oblist = new ArrayList<Object>();
					oblist.add(t.getNameStr());
					oblist.add(t.getCount());
					terminalList.add(oblist);
				}
			}
			dto.setTerminalList(terminalList);
		}
		return dto;
	}

	@Override
	public IDto activityParticipateSuccessDataAll(long siteid, long activityid, long ownerid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		Site site = siteManager.findSiteById(siteid);
		if (site != null && site.getOwnerId() != ownerid) {
			return dto;
		}
		dto.setActivityList(list);
		dto.setSite(site);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			List<Source> source = activityManager.findParticipateSuccessCountBySource(activityid);
			dto.setSource(source);
			List<Terminal> terminal = activityManager.findParticipateSuccessCountByTerminal(activityid);

			VisitLog visitLog = activityManager.findUserCount(activityid);
			int userCount = (int) visitLog.getCountSum();
			VisitLog unkownVisitLog = activityManager.findUnkonwnUserCount(activityid);
			int unKnownCount = (int) unkownVisitLog.getCountSum();
			dto.setCountSum(userCount + unKnownCount);

			long participateCount = activityManager.findParticipateCount(activityid);
			long participateSuccessCount = activityManager.findParticipateSuccessCount(activityid);
			long participateFailCount = participateCount - participateSuccessCount;
			dto.setParticipateCount(participateCount);
			dto.setParticipateSuccessCount(participateSuccessCount);
			dto.setParticipateFailCount(participateFailCount);

			dto.setTerminal(terminal);
		}
		return dto;
	}

	@Override
	public IDto activityParticipateSourceSuccessData(long siteid, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		dto.setActivityList(list);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			List<Source> source = activityManager.findParticipateSuccessCountBySource(activityid);
			List<List<Object>> sourceList = new ArrayList<List<Object>>();
			if (source != null && source.size() > 0) {
				for (Source t : source) {
					List<Object> oblist = new ArrayList<Object>();
					oblist.add(t.getName());
					oblist.add(t.getCount());
					sourceList.add(oblist);
				}
			}
			dto.setSourceList(sourceList);
		}
		return dto;
	}

	@Override
	public IDto activityParticipateSourceFailDataAll(long siteid, long activityid, long ownerid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		Site site = siteManager.findSiteById(siteid);
		if (site != null && site.getOwnerId() != ownerid) {
			return dto;
		}
		dto.setActivityList(list);
		dto.setSite(site);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			List<Terminal> terminal = activityManager.findParticipateFailCountByTerminal(activityid);
			dto.setTerminal(terminal);

			VisitLog visitLog = activityManager.findUserCount(activityid);
			int userCount = (int) visitLog.getCountSum();
			VisitLog unkownVisitLog = activityManager.findUnkonwnUserCount(activityid);
			int unKnownCount = (int) unkownVisitLog.getCountSum();
			dto.setCountSum(userCount + unKnownCount);

			long participateCount = activityManager.findParticipateCount(activityid);
			long participateSuccessCount = activityManager.findParticipateSuccessCount(activityid);
			long participateFailCount = participateCount - participateSuccessCount;
			dto.setParticipateCount(participateCount);
			dto.setParticipateSuccessCount(participateSuccessCount);
			dto.setParticipateFailCount(participateFailCount);

			List<Source> source = activityManager.findParticipateFailCountBySource(activityid);
			dto.setSource(source);
		}
		return dto;
	}

	@Override
	public IDto activityParticipateSourceFailData(long siteid, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		dto.setActivityList(list);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			List<Terminal> terminal = activityManager.findParticipateFailCountByTerminal(activityid);
			List<List<Object>> terminalList = new ArrayList<List<Object>>();
			if (terminal != null && terminal.size() > 0) {
				for (Terminal t : terminal) {
					List<Object> oblist = new ArrayList<Object>();
					oblist.add(t.getNameStr());
					oblist.add(t.getCount());
					terminalList.add(oblist);
				}
			}
			dto.setTerminalList(terminalList);
		}
		return dto;
	}

	@Override
	public IDto activityParticipateSourceFailSource(long siteid, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		dto.setActivityList(list);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			List<Source> source = activityManager.findParticipateFailCountBySource(activityid);
			List<List<Object>> sourceList = new ArrayList<List<Object>>();
			if (source != null && source.size() > 0) {
				for (Source t : source) {
					List<Object> oblist = new ArrayList<Object>();
					oblist.add(t.getName());
					oblist.add(t.getCount());
					sourceList.add(oblist);
				}
			}
			dto.setSourceList(sourceList);
		}
		return dto;
	}

	@Override
	public IDto getAnonVisitData(long siteid, int pageId, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		dto.setActivityList(list);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			int start = (pageId - 1) * IPageConstants.SITE_LIMIT;
			int total = activityManager.findAnonVisitDataCount(activityid);
			if (total > 0) {
				List<VisitLog> vistLogList = activityManager.findAnonVisitData(activityid, start, IPageConstants.SITE_LIMIT);
				dto.setVistLogList(vistLogList);
			}
			Pager pager = new Pager(pageId, total, IPageConstants.SITE_LIMIT);
			dto.setPager(pager);
		}
		return dto;
	}

	@Override
	public IDto nonAnonVisitReportData(long siteid, int pageId, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		dto.setActivityList(list);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			int start = (pageId - 1) * IPageConstants.CONTENT_MOUNTS;
			int total = activityManager.findnonAnonVisitCount(activityid);
			if (total > 0) {
				List<VisitLog> vistLogList = activityManager.findnonAnonVisitData(activityid, start, IPageConstants.CONTENT_MOUNTS);
				dto.setVistLogList(vistLogList);
			}
			List<Terminal> terminal = activityManager.findTerminalNames(activityid);
			List<Source> source = activityManager.findSourceNames(activityid);
			for (Terminal t : terminal) {
				t.setName(t.getName());
			}
			dto.setTerminal(terminal);
			dto.setSource(source);
			Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_MOUNTS);
			dto.setPager(pager);
		}
		return dto;
	}

	@Override
	public IDto findnonAnonVisitDataDetails(long siteid, String visitTime1, String visitTime2, String terminal, String source, int pageId, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		dto.setActivityList(list);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			int total = activityManager.findnonAnonVisitDataDetailsCount(activityid, visitTime1, visitTime2, terminal, source);
			if (total > 0) {
				List<VisitLog> vistLogList = activityManager.findnonAnonVisitDataDetails(activityid, visitTime1, visitTime2, terminal, source, pageId,
						IPageConstants.CONTENT_MOUNTS);
				dto.setVistLogList(vistLogList);
			}
			List<Terminal> terminalNames = activityManager.findTerminalNames(activityid);
			List<Source> sourceNames = activityManager.findSourceNames(activityid);
			for (Terminal t : terminalNames) {
				t.setName(t.getName());
			}
			dto.setTerminal(terminalNames);
			dto.setSource(sourceNames);
			Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_MOUNTS);
			dto.setPager(pager);
		}
		return dto;
	}

	@Override
	public IDto findnonAnonVisitDataDetails(long siteid, String nickname, int pageId, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		List<Activity> list = activityManager.findActivityListBysitegrouid(siteid);
		dto.setActivityList(list);
		if (list.size() > 0 && activityid == 0) {
			activityid = list.get(0).getId();
		}
		if (activityid > 0) {
			int start = (pageId - 1) * IPageConstants.CONTENT_MOUNTS;
			int total = activityManager.findnonAnonVisitDataDetailsCount(activityid, nickname);
			if (total > 0) {
				List<VisitLog> vistLogList = activityManager.findnonAnonVisitDataDetails(activityid, nickname, start, IPageConstants.CONTENT_MOUNTS);
				dto.setVistLogList(vistLogList);
			}
			List<Terminal> terminalNames = activityManager.findTerminalNames(activityid);
			List<Source> sourceNames = activityManager.findSourceNames(activityid);
			for (Terminal t : terminalNames) {
				t.setName(t.getName());
			}
			dto.setTerminal(terminalNames);
			dto.setSource(sourceNames);
			Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_MOUNTS);
			dto.setPager(pager);
		}
		return dto;
	}

	/**
	 * 查询表单并根据前台传入的条件查询
	 */
	@Override
	public IDto composeSearchVisitReport(long ownerid, long sgid, VisitUserDto visitdto, int pageId) {

		CustomVisitDto cdto = new CustomVisitDto();
		Site sg = siteManager.findSiteById(sgid);
		cdto.setSite(sg);
		if (sg == null) {
			return cdto;
		}
		if (sg.getOwnerId() != ownerid && sg.getOwnerId() != 0) {
			return cdto;
		}
		cdto.setSitegroupname(siteManager.findSiteById(sgid).getName());
		int start = (pageId - 1) * IPageConstants.CONTENT_SIZES;
		int total = visitReportManager.searchVisitReportTotal(sgid, visitdto);
		if (total > 0) {
			List<CustomVisitReport> list = visitReportManager.searchVisitReport(sgid, visitdto, start, IPageConstants.CONTENT_SIZES);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					CustomVisitReport report = list.get(i);
					String str = visitReportManager.findAreaByIp(report.getIp());
					report.setArea(str);
				}
			}
			cdto.setReports(list);
		}
		Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_SIZES);
		cdto.setPager(pager);
		return cdto;
	}

	/**
	 * 得出访问列表
	 */
	@Override
	public IDto showVisiterReport(long siteid, int pageId, long ownerid) {

		RgAnalysisDto vdto = new RgAnalysisDto();
		Site sg = siteManager.findSiteById(siteid);
		vdto.setSite(sg);
		if (sg == null) {
			return vdto;
		}
		if (sg.getOwnerId() != ownerid && sg.getOwnerId() != 0) {
			return vdto;
		}
		vdto.setVisitnum(visitReportManager.sumVisitNum(siteid));
		int start = (pageId - 1) * IPageConstants.CONTENT_SIZE;
		int total = visitReportManager.findVisitAreaReportTotal(siteid);
		if (total > 0) {
			List<AreaAnalysis> arylist = visitReportManager.showVisiterBySiteGroupId(siteid, start, IPageConstants.CONTENT_SIZE);

			vdto.setAreas(arylist);
		}
		Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_SIZE);
		vdto.setPager(pager);
		return vdto;
	}

	public IDto hourReportData(long siteid, Account account) {
		HourReportDto dto = new HourReportDto();
		Site site = siteManager.findSiteById(siteid);
		if (site == null) {
			return dto;
		}
		if (site.getOwnerId() != account.getOwner().getId()) {
			return dto;
		}
		List<Integer> vnum = new ArrayList<Integer>();
		List<HourAnalyse> arrlist = new ArrayList<HourAnalyse>();
		// List<String> hours = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			vnum.add(0);
			HourAnalyse analyse = new HourAnalyse();
			analyse.setHour(i);
			analyse.setVisitNum(0);
			arrlist.add(analyse);
		}
		List<HourAnalyse> list = visitReportManager.findHourAnalyseBySgid(siteid);
		for (HourAnalyse analyse : list) {
			int hour = analyse.getHour();
			HourAnalyse analysehour = arrlist.get(hour);
			analysehour.setVisitNum(analyse.getVisitNum());
			vnum.set(hour, analyse.getVisitNum());
			// hours.add("'"+hour+"~"+(hour+1+"'"));
		}
		dto.setVnum(vnum);
		// dto.setHour(hours);
		dto.setName(siteManager.findSiteById(siteid).getName());
		dto.setList(arrlist);
		return dto;
	}

	public IDto HdHourReportData(long sgid, Account account) {
		HourReportDto dto = new HourReportDto();
		Site site = siteManager.findSiteById(sgid);
		if (site == null) {
			return dto;
		}
		if (site.getOwnerId() != account.getOwner().getId()) {
			return dto;
		}
		List<Integer> hnum = new ArrayList<Integer>();
		// List<String> hours = new ArrayList<String>();
		List<HourAnalyse> arrlist = new ArrayList<HourAnalyse>();
		for (int i = 0; i < 24; i++) {
			hnum.add(0);
			HourAnalyse analyse = new HourAnalyse();
			analyse.setHour(i);
			analyse.setHdNum(0);
			arrlist.add(analyse);
		}
		List<HourAnalyse> list = visitReportManager.findHourAnalyseBySgid(sgid);
		for (HourAnalyse analyse : list) {
			int hour = analyse.getHour();
			HourAnalyse analysehour = arrlist.get(hour);
			analysehour.setHdNum(analyse.getHdNum());
			hnum.set(hour, analyse.getHdNum());
			// hours.add("'"+hour+"~"+(hour+1+"'"));
		}
		dto.setHnum(hnum);
		dto.setName(siteManager.findSiteById(sgid).getName());
		dto.setList(arrlist);
		// dto.setHour(hours);
		return dto;
	}

	@Override
	public IDto composeFansReport(long siteid, int pageId, long ownerid) {
		FansAnalysisDto dto = new FansAnalysisDto();
		Site s = siteManager.findSiteById(siteid);
		dto.setSite(s);
		if (s.getOwnerId() != ownerid) {
			return dto;
		}
		FansAnalyse fansAnalyse = fansAnalyseManager.findFansAnalyse(siteid);
		if (fansAnalyse != null) {
			dto.setVfansN(fansAnalyse.getVunfansnum());
			dto.setVfansY(fansAnalyse.getVfansnum());
		}

		int start = (pageId - 1) * IPageConstants.FANS_ANALYSE_LIMIT;
		int total = dto.getVfansN() + dto.getVfansY();
		if (total > 0) {
			dto.setFansList(fansAnalyseManager.findFansAnalyse(siteid, "V", start, IPageConstants.FANS_ANALYSE_LIMIT));
		}
		Pager pager = new Pager(pageId, total, IPageConstants.FANS_ANALYSE_LIMIT);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public IDto composeHdFansReport(long sgid, int pageId, long ownerid) {
		FansAnalysisDto dto = new FansAnalysisDto();
		Site s = siteManager.findSiteById(sgid);
		dto.setSite(s);
		if (s.getOwnerId() != ownerid) {
			return dto;
		}
		FansAnalyse fansAnalyse = fansAnalyseManager.findFansAnalyse(sgid);
		if (fansAnalyse != null) {
			dto.setHfansN(fansAnalyse.getDunfansnum());
			dto.setHfansY(fansAnalyse.getDfansnum());
		}
		int start = (pageId - 1) * IPageConstants.FANS_ANALYSE_LIMIT;
		int total = dto.getHfansN() + dto.getHfansY();
		if (total > 0) {
			dto.setFansList(fansAnalyseManager.findFansAnalyse(sgid, "D", start, IPageConstants.FANS_ANALYSE_LIMIT));
		}
		Pager pager = new Pager(pageId, total, IPageConstants.FANS_ANALYSE_LIMIT);
		dto.setPager(pager);
		return dto;
	}

	public void setFansAnalyseManager(IFansAnalyseManager fansAnalyseManager) {
		this.fansAnalyseManager = fansAnalyseManager;
	}

	@Override
	public IDto activityParticipateDetails(String terminalName, int pageId, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		Activity activity = activityManager.findActivityByActivityid(activityid);
		if (activity == null) {
			return dto;
		} else {
			int siteid = 0;
			int start = (pageId - 1) * IPageConstants.CONTENT_MOUNTS;
			int total = activityManager.findAnonParticipateCountByTerminal(terminalName, activityid);
			siteid = (int) activity.getSiteid();
			if (total > 0) {
				List<ActivityLog> activityLogList = activityManager.findActivityParticipateDetails(activityid, terminalName, start, IPageConstants.CONTENT_MOUNTS);
				for (ActivityLog al : activityLogList) {
					UserInfo userInfo = activityManager.findNickNames(siteid, al.getWbuid());
					if (userInfo != null) {
						al.setNickname(userInfo.getNickname());
					}
				}
				dto.setActivityLogList(activityLogList);
			}
			List<Source> source = activityManager.findActivityParticipateSourceNames(terminalName, activityid);
			dto.setSource(source);
			Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_MOUNTS);
			dto.setPager(pager);
		}
		return dto;
	}

	@Override
	public IDto getActivityParticipateDetails(String terminalName, String visitTime1, String visitTime2, String source, int pageId, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		Activity activity = activityManager.findActivityByActivityid(activityid);
		if (activity == null) {
			return dto;
		} else {
			int siteid = 0;
			int start = (pageId - 1) * IPageConstants.CONTENT_MOUNTS;
			int total = activityManager.findActivityParticipateDetailsCount(terminalName, activityid, visitTime1, visitTime2, source);
			siteid = (int) activity.getSiteid();
			if (total > 0) {
				List<ActivityLog> activityLogList = activityManager.findActivityParticipateDetails(activityid, terminalName, visitTime1, visitTime2, source, start,
						IPageConstants.CONTENT_MOUNTS);
				for (ActivityLog al : activityLogList) {
					UserInfo userInfo = activityManager.findNickNames(siteid, al.getWbuid());
					if (userInfo != null) {
						al.setNickname(userInfo.getNickname());
					}
				}
				dto.setActivityLogList(activityLogList);
			}
			List<Source> sourceName = activityManager.findActivityParticipateSourceNames(terminalName, activityid);
			dto.setSource(sourceName);
			Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_MOUNTS);
			dto.setPager(pager);
		}
		return dto;
	}

	@Override
	public IDto getActivityParticipateDetailsByName(String terminalName, String nickname, int pageId, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		Activity activity = activityManager.findActivityByActivityid(activityid);
		if (activity == null) {
			return dto;
		} else {
			int siteid = (int) activity.getSiteid();
			int start = (pageId - 1) * IPageConstants.CONTENT_MOUNTS;
			List<UserInfo> userInfo = activityManager.findWbuid(siteid, nickname);
			int total = 0;
			for (UserInfo ui : userInfo) {
				long wbuid = ui.getUserid();
				total += activityManager.findActivityParticipateDetailsByName(activityid, terminalName, wbuid);
			}
			List<ActivityLog> activityLogList = new ArrayList<ActivityLog>();
			if (total > 0) {
				for (UserInfo ui : userInfo) {
					long wbuid = ui.getUserid();
					List<ActivityLog> activityLog = activityManager.findActivityParticipateDetailsByName(activityid, terminalName, wbuid, start, IPageConstants.CONTENT_MOUNTS);
					for (ActivityLog al : activityLog) {
						if (ui.getNickname() != null) {
							al.setNickname(ui.getNickname());
							activityLogList.add(al);
						}
					}
				}
				dto.setActivityLogList(activityLogList);
			}
			List<Source> sourceName = activityManager.findActivityParticipateSourceNames(terminalName, activityid);
			dto.setSource(sourceName);
			Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_MOUNTS);
			dto.setPager(pager);
		}
		return dto;
	}

	@Override
	public IDto activityParticipateSuccessDetails(String terminalName, int pageId, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		Activity activity = activityManager.findActivityByActivityid(activityid);
		if (activity == null) {
			return dto;
		} else {
			int start = (pageId - 1) * IPageConstants.CONTENT_MOUNTS;
			int total = activityManager.findAnonParticipateSuccessCountByTerminal(terminalName, activityid);
			int siteid = (int) activity.getSiteid();
			if (total > 0) {
				List<ActivityLog> activityLogList = activityManager.findActivityParticipateSuccessDetails(activityid, terminalName, start, IPageConstants.CONTENT_MOUNTS);
				for (ActivityLog al : activityLogList) {
					UserInfo userInfo = activityManager.findNickNames(siteid, al.getWbuid());
					if (userInfo != null) {
						al.setNickname(userInfo.getNickname());
					}
				}
				dto.setActivityLogList(activityLogList);
			}
			List<Source> source = activityManager.findActivityParticipateSuccessSourceNames(terminalName, activityid);
			dto.setSource(source);
			Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_MOUNTS);
			dto.setPager(pager);
		}
		return dto;
	}

	@Override
	public IDto getActivityParticipateSuccessDetails(String terminalName, String visitTime1, String visitTime2, String source, int pageId, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		Activity activity = activityManager.findActivityByActivityid(activityid);
		if (activity == null) {
			return dto;
		} else {
			int start = (pageId - 1) * IPageConstants.CONTENT_MOUNTS;
			int total = activityManager.findActivityParticipateSuccessDetailsCount(terminalName, activityid, visitTime1, visitTime2, source);
			int siteid = (int) activity.getSiteid();
			if (total > 0) {
				List<ActivityLog> activityLogList = activityManager.findActivityParticipateSuccessDetails(activityid, terminalName, visitTime1, visitTime2, source, start,
						IPageConstants.CONTENT_MOUNTS);
				for (ActivityLog al : activityLogList) {
					UserInfo userInfo = activityManager.findNickNames(siteid, al.getWbuid());
					if (userInfo != null) {
						al.setNickname(userInfo.getNickname());
					}
				}
				dto.setActivityLogList(activityLogList);
			}
			List<Source> sourceName = activityManager.findActivityParticipateSuccessSourceNames(terminalName, activityid);
			dto.setSource(sourceName);
			Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_MOUNTS);
			dto.setPager(pager);
		}
		return dto;
	}

	@Override
	public IDto getActivityParticipateSuccessDetailsByName(String terminalName, String nickname, int pageId, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		Activity activity = activityManager.findActivityByActivityid(activityid);
		if (activity == null) {
			return dto;
		} else {
			int siteid = (int) activity.getSiteid();
			int start = (pageId - 1) * IPageConstants.CONTENT_MOUNTS;
			List<UserInfo> userInfo = activityManager.findWbuid(siteid, nickname);
			int total = 0;
			for (UserInfo ui : userInfo) {
				long wbuid = ui.getUserid();
				total += activityManager.findActivityParticipateSuccessDetailsByName(activityid, terminalName, wbuid);
			}
			List<ActivityLog> activityLogList = new ArrayList<ActivityLog>();
			if (total > 0) {
				for (UserInfo ui : userInfo) {
					long wbuid = ui.getUserid();
					List<ActivityLog> activityLog = activityManager.findActivityParticipateSuccessDetailsByName(activityid, terminalName, wbuid, start,
							IPageConstants.CONTENT_MOUNTS);
					for (ActivityLog al : activityLog) {
						if (ui.getNickname() != null) {
							al.setNickname(ui.getNickname());
							activityLogList.add(al);
						}
					}
				}
				dto.setActivityLogList(activityLogList);
			}
			List<Source> sourceName = activityManager.findActivityParticipateSuccessSourceNames(terminalName, activityid);
			dto.setSource(sourceName);
			Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_MOUNTS);
			dto.setPager(pager);
		}
		return dto;
	}

	@Override
	public IDto activityParticipateFailDetails(String terminalName, int pageId, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		Activity activity = activityManager.findActivityByActivityid(activityid);
		if (activity == null) {
			return dto;
		} else {
			int start = (pageId - 1) * IPageConstants.CONTENT_MOUNTS;
			int total = activityManager.findParticipateFailCountByTerminal(terminalName, activityid);
			int siteid = (int) activity.getSiteid();
			if (total > 0) {
				List<ActivityLog> activityLogList = activityManager.findActivityParticipateFailDetails(activityid, terminalName, start, IPageConstants.CONTENT_MOUNTS);
				for (ActivityLog al : activityLogList) {
					UserInfo userInfo = activityManager.findNickNames(siteid, al.getWbuid());
					if (userInfo != null) {
						al.setNickname(userInfo.getNickname());
					}
				}
				dto.setActivityLogList(activityLogList);
			}
			List<Source> source = activityManager.findActivityParticipateFailSourceNames(terminalName, activityid);
			dto.setSource(source);
			Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_MOUNTS);
			dto.setPager(pager);
		}
		return dto;
	}

	@Override
	public IDto getActivityParticipateFailDetails(String terminalName, String visitTime1, String visitTime2, String source, int pageId, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		Activity activity = activityManager.findActivityByActivityid(activityid);
		if (activity == null) {
			return dto;
		} else {
			int start = (pageId - 1) * IPageConstants.CONTENT_MOUNTS;
			int total = activityManager.findActivityParticipateFailDetailsCount(terminalName, activityid, visitTime1, visitTime2, source);
			int siteid = (int) activity.getSiteid();
			if (total > 0) {
				List<ActivityLog> activityLogList = activityManager.findActivityParticipateFailDetails(activityid, terminalName, visitTime1, visitTime2, source, start,
						IPageConstants.CONTENT_MOUNTS);
				for (ActivityLog al : activityLogList) {
					UserInfo userInfo = activityManager.findNickNames(siteid, al.getWbuid());
					if (userInfo != null) {
						al.setNickname(userInfo.getNickname());
					}
				}
				dto.setActivityLogList(activityLogList);
			}
			List<Source> sourceName = activityManager.findActivityParticipateFailSourceNames(terminalName, activityid);
			dto.setSource(sourceName);
			Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_MOUNTS);
			dto.setPager(pager);
		}
		return dto;
	}

	@Override
	public IDto getActivityParticipateFailDetailsByName(String terminalName, String nickname, int pageId, long activityid) {
		SiteVistLogDto dto = new SiteVistLogDto();
		Activity activity = activityManager.findActivityByActivityid(activityid);
		if (activity == null) {
			return dto;
		} else {
			int siteid = (int) activity.getSiteid();
			int start = (pageId - 1) * IPageConstants.CONTENT_MOUNTS;
			List<UserInfo> userInfo = activityManager.findWbuid(siteid, nickname);
			int total = 0;
			for (UserInfo ui : userInfo) {
				long wbuid = ui.getUserid();
				total += activityManager.findActivityParticipateFailDetailsByName(activityid, terminalName, wbuid);
			}
			List<ActivityLog> activityLogList = new ArrayList<ActivityLog>();
			if (total > 0) {
				for (UserInfo ui : userInfo) {
					long wbuid = ui.getUserid();
					List<ActivityLog> activityLog = activityManager.findActivityParticipateFailDetailsByName(activityid, terminalName, wbuid, start, IPageConstants.CONTENT_MOUNTS);
					for (ActivityLog al : activityLog) {
						if (ui.getNickname() != null) {
							al.setNickname(ui.getNickname());
							activityLogList.add(al);
						}
					}
				}
				dto.setActivityLogList(activityLogList);
			}
			List<Source> sourceName = activityManager.findActivityParticipateFailSourceNames(terminalName, activityid);
			dto.setSource(sourceName);
			Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_MOUNTS);
			dto.setPager(pager);
		}
		return dto;
	}

	public void setSinaForwardManager(ISinaForwardManager sinaForwardManager) {
		this.sinaForwardManager = sinaForwardManager;
	}

	@Override
	public long getWbuidByUserInfo(long uid) {
		UserInfo user = activityManager.findwbuidByUid(uid);
		return user.getUserid();
	}

	@Override
	public long composeActivityLog(long activityid, long wbuid, String type, String ip, String terminal, String source) {
		return activityManager.saveActivityLog(activityid, wbuid, type, ip, terminal, source);
	}

	@Override
	public IDto composeFace(long category) {
		DynamicActionDto dto = new DynamicActionDto();
		dto.setEmotionsCategory(emotionsManager.findEmotionsCategory());
		dto.setEmotions(emotionsManager.findEmotionsByCategory(category));
		return dto;
	}

	public void setEmotionsManager(IEmotionsManager emotionsManager) {
		this.emotionsManager = emotionsManager;
	}

	@Override
	public List<InteractModel> composeFindInteractModel() {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		List<InteractModel> list = interactModelManager.findAllInteractModel(account.getOwner().getId());
		return list;
	}

	@Override
	public List<InteractModel> composeUsedInteractModel() {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		if(account != null){			
			List<InteractModel> list = interactModelManager.findInteractModel(account.getOwner().getId());
			return list;
		}else{
			return null;
		}
	}

	public void setInteractModelManager(IInteractModelManager interactModelManager) {
		this.interactModelManager = interactModelManager;
	}

	@Override
	public int composeSinaComment(long fatherwbid, long uid, long pageid, long shareid, String content, String terminal, String source, String ip, String gz) {
		UserInfo info = sinaUserManager.findUserByUid(uid);
		long wbuid = info.getWbuid1();
		String token = info.getToken();
		HyWbComment comment = null;
		String wbidPar = String.valueOf(fatherwbid);
		try {
			comment = wsManager.wbcomment(wbidPar, token, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (comment == null) {
			System.out.println("statua is null :wbid = " + fatherwbid + "---uid" + uid + "---wbuid" + wbuid + "---token" + token + "---pageid:" + pageid + "---shareid" + shareid
					+ "---content" + content);
			return -1;
		}
		long result = sinaCommentManager.saveSinaComment(wbuid, fatherwbid, comment.getId(), shareid, content, terminal, pageid, source, ip);
		if (result > 0) {
			return 1;
		}
		return 0;
	}

	public IDto findVisitLogUnkownList(long sgid, int pageid, Account account) {
		CustomVisitDto dto = new CustomVisitDto();
		Site site = siteManager.findSiteById(sgid);
		if (site != null) {
			if (site.getOwnerId() != account.getOwner().getId()) {
				return dto;
			}
			int start = (pageid - 1) * IPageConstants.DANYMIC_USER_RECORD_LIMIT;
			int total = visitReportManager.findNoLoginVisitTotalBySiteGroup(sgid);
			if (total > 0) {
				dto.setReports(visitReportManager.findVisitLogUnknow(sgid, start, IPageConstants.DANYMIC_USER_RECORD_LIMIT));
			}
			Pager pager = new Pager(pageid, total, IPageConstants.DANYMIC_USER_RECORD_LIMIT);
			dto.setPager(pager);
		}
		return dto;
	}

	public IDto getVisitLevelData(long siteid, Account account) {
		FansLevelAnalyseDto dto = new FansLevelAnalyseDto();
		Site site = siteManager.findSiteById(siteid);
		if (site == null) {
			return null;
		}
		if (site.getOwnerId() != account.getOwner().getId()) {
			return null;
		}
		dto.setSitename(site.getName());
		List<FansLevelAnalyse> list = fansAnalyseManager.findFansLevelAnalyseList(siteid);
		int vtotal = fansAnalyseManager.findLevelVnumTotal(siteid);
		for (int i = 0; i < list.size(); i++) {
			FansLevelAnalyse fansLevelAnalyse = list.get(i);
			DecimalFormat df2 = new DecimalFormat("##.##%");
			int num = fansLevelAnalyse.getVnum();
			int level = fansLevelAnalyse.getLevel();
			if (level == 1) {
				dto.setVlevelnum1(num);
				dto.setVlevelpercent1((num != 0 ? df2.format((num * 1.00) / (vtotal * 1.00)) : "0%"));
			} else if (level == 2) {
				dto.setVlevelnum2(num);
				dto.setVlevelpercent2((num != 0 ? df2.format((num * 1.00) / (vtotal * 1.00)) : "0%"));
			} else if (level == 3) {
				dto.setVlevelnum3(num);
				dto.setVlevelpercent3((num != 0 ? df2.format((num * 1.00) / (vtotal * 1.00)) : "0%"));
			} else if (level == 4) {
				dto.setVlevelnum4(num);
				dto.setVlevelpercent4((num != 0 ? df2.format((num * 1.00) / (vtotal * 1.00)) : "0%"));
			}

		}
		dto.setList(list);
		return dto;
	}

	public IDto getHdLevelData(long sgid, Account account) {
		FansLevelAnalyseDto dto = new FansLevelAnalyseDto();
		Site site = siteManager.findSiteById(sgid);
		if (site == null) {
			return null;
		}
		if (site.getOwnerId() != account.getOwner().getId()) {
			return null;
		}
		dto.setSitename(site.getName());
		List<FansLevelAnalyse> list = fansAnalyseManager.findFansLevelAnalyseList(sgid);
		int htotal = fansAnalyseManager.findLevelDnumTotal(sgid);
		for (int i = 0; i < list.size(); i++) {
			FansLevelAnalyse fansLevelAnalyse = list.get(i);
			DecimalFormat df2 = new DecimalFormat("##.##%");
			int num = fansLevelAnalyse.getDnum();
			int level = fansLevelAnalyse.getLevel();
			if (level == 1) {
				dto.setDlevelnum1(num);
				dto.setDlevelpercent1((num != 0 ? df2.format((num * 1.00) / (htotal * 1.00)) : "0%"));
			} else if (level == 2) {
				dto.setDlevelnum2(num);
				dto.setDlevelpercent2((num != 0 ? df2.format((num * 1.00) / (htotal * 1.00)) : "0%"));
			} else if (level == 3) {
				dto.setDlevelnum3(num);
				dto.setDlevelpercent3((num != 0 ? df2.format((num * 1.00) / (htotal * 1.00)) : "0%"));
			} else if (level == 4) {
				dto.setDlevelnum4(num);
				dto.setDlevelpercent4((num != 0 ? df2.format((num * 1.00) / (htotal * 1.00)) : "0%"));
			}

		}
		dto.setList(list);
		return dto;
	}

	public IDto getReportViewAllData(Account account) {
		DataIndexDto indexdto = new DataIndexDto();
		List<ReportViewAllDto> viewlist = new ArrayList<ReportViewAllDto>();
		long ownerid = account.getOwner().getId();
		List<Site> list = siteManager.findSiteListByOwner(ownerid);
		int alltotalvisitnum = 0;
		int allvisitnum = 0;
		int allnvisitnum = 0;
		int alltodayadd = 0;
		int alltotalhdnum = 0;
		int alljoinnum = 0;
		int allsucjoinnum = 0;
		int alloutnum = 0;
		for (Site site : list) {
			long sgid = site.getId();
			ReportViewAllDto dto = new ReportViewAllDto();
			;
			dto.setGroupname(site.getName());
			dto.setSgid(site.getId());
			List<FansLevelAnalyse> levellist = fansAnalyseManager.findFansLevelAnalyseList(sgid);
			dto.setLevellist(levellist);
			List<AreaAnalysis> arylist = visitReportManager.showDynamicAreaBySiteGroupId(sgid, 0, IPageConstants.CONTENT_SIZE);
			dto.setArealist(arylist);
			int totalvistinum = visitReportManager.findVisitLogTotalNum(sgid);
			alltotalvisitnum = alltotalvisitnum + totalvistinum;
			int vistinum = visitReportManager.findLoginVisitLogTotalNum(sgid);
			allvisitnum = allvisitnum + vistinum;
			dto.setTotalvisitnum(totalvistinum);
			dto.setVisitnum(vistinum);
			int nvisitnum = totalvistinum - vistinum;
			allnvisitnum = allnvisitnum + nvisitnum;
			dto.setNvisitnum(nvisitnum);
			int todayadd = visitReportManager.findTodayDddNum(sgid);
			alltodayadd = alltodayadd + todayadd;
			dto.setTodayadd(todayadd);
			int totalhdnum = visitReportManager.findHdTotalNum(sgid);
			alltotalhdnum = alltotalhdnum + totalhdnum;
			dto.setTotalhdnum(totalhdnum);
			int joinnum = visitReportManager.findJoinNum(sgid);
			alljoinnum = alljoinnum + joinnum;
			int sucjoinnum = visitReportManager.findJoinSucNum(sgid);
			allsucjoinnum = allsucjoinnum + sucjoinnum;
			int outnum = joinnum - sucjoinnum;
			alloutnum = alloutnum + outnum;
			dto.setJoinnum(joinnum);
			dto.setSucjoinnum(sucjoinnum);
			dto.setOutnum(outnum);
			viewlist.add(dto);

		}
		List<FansLevelAnalyse> levellistall = fansAnalyseManager.findFansLevelAnalyseListByOwner(ownerid);
		indexdto.setLevellist(levellistall);
		List<AreaAnalysis> arylistall = visitReportManager.findHdAreaOwner(ownerid, 0, IPageConstants.CONTENT_SIZE);
		indexdto.setArealist(arylistall);
		indexdto.setViewall(viewlist);
		indexdto.setNvisitnum(allnvisitnum);
		indexdto.setJoinnum(alljoinnum);
		indexdto.setOutnum(alloutnum);
		indexdto.setSucjoinnum(allsucjoinnum);
		indexdto.setTodayadd(alltodayadd);
		indexdto.setTotalhdnum(alltotalhdnum);
		indexdto.setTotalvisitnum(alltotalvisitnum);
		indexdto.setVisitnum(allvisitnum);
		return indexdto;
	}

	@Override
	public Sitegroup composeSiteGroup(Account account, long sgid) {
		return siteManager.findSitegroupByOwner(account.getOwner().getId(), sgid);
	}

	@Override
	public IDto getPageTemplateList(long ownerid) {
		PageTemplateDto dto = new PageTemplateDto();
		List<TemplateModel> list = pageManager.findPageTemplate(ownerid);
		if (list != null && list.size() > 0) {
			dto.setList(list);
			return dto;
		}
		return null;
	}

	@Override
	public String findAppSecrectByAppid(long appid) {
		return sinaTokenMgr.findAppSecrectByAppid(appid);
	}

	public void setTemplateManager(ITemplateManager templateManager) {
		this.templateManager = templateManager;
	}

	@Override
	public IDto composeTemplateIndexAction(long categoryid, String type) {
		TemplateDto dto = new TemplateDto();
		dto.setCategoryList(templateManager.findTemplateCategory());
		if (categoryid == 0) {
			dto.setTemplateList(templateManager.findTemplateModelList(dto.getCategoryList().get(0).getId(), type));
		} else {
			dto.setTemplateList(templateManager.findTemplateModelList(categoryid, type));
		}
		return dto;
	}

	@Override
	public int saveOwnerTemplate(long ownerid, List<Long> templates) {
		if (templates != null) {
			return templateManager.saveOwnerTemplate(ownerid, templates);
		}
		return -1;
	}

	@Override
	public IDto composeMyTemplateAction(long ownerid) {
		TemplateDto dto = new TemplateDto();
		dto.setCategoryList(templateManager.findTemplateCategory());
		dto.setMyTemplateList(templateManager.findMyTemplateByOwnerid(ownerid));
		return dto;
	}

	@Override
	public int deleteTemplate(long id) {
		return templateManager.deleteTemplate(id);
	}

	@Override
	public MyTempalte findOneTemplate(long id) {
		MyTempalte my = templateManager.findOneTemplate(id);
		String str = my.getStyle();
		Gson gson = new Gson();
		TemplateStyleModel t = gson.fromJson(str, TemplateStyleModel.class);
		my.setT(t);
		return my;
	}

	@Override
	public int updateNameTemplate(long id, String name) {
		return templateManager.updateNameTemplate(id, name);
	}

	private String createFileName(String fileName, String type) {
		int index = fileName.lastIndexOf('.');
		// 判断上传文件名是否有扩展名
		if (index != -1) {
			return FileUploadService.getNow() + type + fileName.substring(index);
		}
		return FileUploadService.getNow() + type;
	}

	@Override
	public int updateTemplate(long id, String style, File pic, String oldpic) {
		int result = 0;
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if (pic != null) {
			String name = createFileName(pic.getName(), "_m");
			String path = FileUploadService.getFilePath(IPageConstants.TYPE_TEMP, ownerid, 1 + "");
			String saveResult = FileUploadService.saveFile(pic, path, name);
			TemplateStyleModel t = new TemplateStyleModel();
			t.setBgcolor(style);
			t.setBackground(saveResult);
			Gson gson = new Gson();
			String gstyle = gson.toJson(t);
			result = templateManager.updateTemplate(id, gstyle);
			if (result > 0) {
				return result;
			}
			return 0;
		}
		if (pic == null) {
			TemplateStyleModel t = new TemplateStyleModel();
			t.setBgcolor(style);
			t.setBackground(oldpic);
			Gson gson = new Gson();
			String gstyle = gson.toJson(t);
			result = templateManager.updateTemplate(id, gstyle);
			if (result > 0) {
				return result;
			}
			return 0;
		}
		return 0;
	}

	@Override
	public String createFriendsShips(long pageid, long wbuid, long cid) {
		return friendsShipsMgr.saveFriendsShips(pageid, wbuid, cid);
	}

	@Override
	public OwnerDataDto getOwnerVisitData(long ownerid) {
		OwnerDataDto dto = new OwnerDataDto();
		List<Integer> vwbdaynums = new ArrayList<Integer>();
		List<Integer> vwxdaynums = new ArrayList<Integer>();
		List<String> dates = new ArrayList<String>();
		for (int i = 30; i > 0; i--) {
			Date date = DateUtil.reduceDays(new Date(), i);
			String xdate = DateUtil.getDateString2(date);
			dates.add("'" + xdate + "'");
			vwbdaynums.add(0);
			vwxdaynums.add(0);
		}
		SolrDataDto dataDto = solrServerMgr.getOwnerWbVisitData(ownerid);
		List<RangeFacet> rflist = dataDto.getRflist();
		if (rflist != null && rflist.size() > 0) {
			for (RangeFacet rangeFacet : rflist) {
				List<RangeFacet.Count> rx = rangeFacet.getCounts();
				for (RangeFacet.Count ra : rx) {
					if (ra.getValue() != null) {
						Date date = new Date(Long.parseLong(ra.getValue() + ""));
						String datestr = "'" + DateUtil.getDateString2(date) + "'";
						if (dates.contains(datestr)) {
							int index = dates.lastIndexOf(datestr);
							vwbdaynums.set(index, ra.getCount());
						}

					}
				}
			}
		}
		dataDto = solrServerMgr.getOwnerWxVisitData(ownerid);
		rflist = dataDto.getRflist();
		if (rflist != null && rflist.size() > 0) {
			for (RangeFacet rangeFacet : rflist) {
				List<RangeFacet.Count> rx = rangeFacet.getCounts();
				for (RangeFacet.Count ra : rx) {
					if (ra.getValue() != null) {
						Date date = new Date(Long.parseLong(ra.getValue() + ""));
						String datestr = "'" + DateUtil.getDateString2(date) + "'";
						if (dates.contains(datestr)) {
							int index = dates.lastIndexOf(datestr);
							vwxdaynums.set(index, ra.getCount());
						}

					}
				}
			}
		}
		dto.setDates(dates);
		dto.setVwbdaynums(vwbdaynums);
		dto.setVwxdaynums(vwxdaynums);
		return dto;
	}

	@Override
	public OwnerDataDto getOwnerHdData(long ownerid) {
		OwnerDataDto dto = new OwnerDataDto();
		List<Integer> hdaynums = new ArrayList<Integer>();
		List<String> dates = new ArrayList<String>();
		for (int i = 30; i > 0; i--) {
			Date date = DateUtil.reduceDays(new Date(), i);
			String xdate = DateUtil.getDateString2(date);
			dates.add("'" + xdate + "'");
			hdaynums.add(0);
		}
		SolrDataDto dataDto = solrServerMgr.getOwnerHdData(ownerid);
		List<RangeFacet> rflist = dataDto.getRflist();
		if (rflist != null && rflist.size() > 0) {
			for (RangeFacet rangeFacet : rflist) {
				List<RangeFacet.Count> rx = rangeFacet.getCounts();
				for (RangeFacet.Count ra : rx) {
					if (ra.getValue() != null) {
						Date date = new Date(Long.parseLong(ra.getValue() + ""));
						String datestr = "'" + DateUtil.getDateString2(date) + "'";
						if (dates.contains(datestr)) {
							int index = dates.lastIndexOf(datestr);
							hdaynums.set(index, ra.getCount());
						}

					}
				}
			}
		}
		dto.setDates(dates);
		dto.setHdaynums(hdaynums);
		return dto;
	}

	@Override
	public SiteDto getSiteHdData(long siteid, long ownerid) {
		SiteDto dto = new SiteDto();
		Site site = siteManager.findSiteById(siteid);
		if (site == null)
			return null;
		if (ownerid != site.getOwnerId())
			return null;
		dto.setSite(site);
		List<String> dates = new ArrayList<String>();
		for (int i = 30; i > 0; i--) {
			Date date = DateUtil.reduceDays(new Date(), i);
			String xdate = DateUtil.getDateString2(date);
			dates.add("'" + xdate + "'");

		}
		List<JSONObject> data = new ArrayList<JSONObject>();

		List<SitePage> plist = siteManager.findPageBySiteId(siteid);
		for (SitePage sitePage : plist) {
			long pid = sitePage.getPageid();
			SolrDataDto solrDto = solrServerMgr.getPageHdData(pid);
			Map<String, List<Integer>> dmp = new HashMap<String, List<Integer>>();
			JSONObject js_data_obj = new JSONObject();
			List<Integer> datalist = new ArrayList<Integer>();
			for (int i = 30; i > 0; i--) {
				datalist.add(0);
			}
			List<RangeFacet> rflist = solrDto.getRflist();
			if (rflist != null && rflist.size() > 0) {
				for (RangeFacet rangeFacet : rflist) {
					List<RangeFacet.Count> rx = rangeFacet.getCounts();
					for (RangeFacet.Count ra : rx) {
						if (ra.getValue() != null) {
							Date date = new Date(Long.parseLong(ra.getValue() + ""));
							String datestr = "'" + DateUtil.getDateString2(date) + "'";
							if (dates.contains(datestr)) {
								int index = dates.lastIndexOf(datestr);
								datalist.set(index, ra.getCount());
							}

						}
					}
				}
			}
			js_data_obj.put("name", sitePage.getPagename());
			js_data_obj.put("data", datalist);
			data.add(js_data_obj);
		}
		dto.setData(data);
		dto.setDates(dates);
		return dto;
	}

	@Override
	public SiteDto getSiteVisitData(long siteid, long ownerid) {
		SiteDto dto = new SiteDto();
		Site site = siteManager.findSiteById(siteid);
		if (site == null)
			return null;
		if (ownerid != site.getOwnerId())
			return null;
		dto.setSite(site);
		List<String> dates = new ArrayList<String>();
		for (int i = 30; i > 0; i--) {
			Date date = DateUtil.reduceDays(new Date(), i);
			String xdate = DateUtil.getDateString2(date);
			dates.add("'" + xdate + "'");

		}
		List<JSONObject> data = new ArrayList<JSONObject>();

		List<SitePage> plist = siteManager.findPageBySiteId(siteid);
		for (SitePage sitePage : plist) {
			long pid = sitePage.getPageid();
			SolrDataDto solrDto = solrServerMgr.getPageVisitData(pid);
			Map<String, List<Integer>> dmp = new HashMap<String, List<Integer>>();
			JSONObject js_data_obj = new JSONObject();
			List<Integer> datalist = new ArrayList<Integer>();
			for (int i = 30; i > 0; i--) {
				datalist.add(0);
			}
			List<RangeFacet> rflist = solrDto.getRflist();
			if (rflist != null && rflist.size() > 0) {
				for (RangeFacet rangeFacet : rflist) {
					List<RangeFacet.Count> rx = rangeFacet.getCounts();
					for (RangeFacet.Count ra : rx) {
						if (ra.getValue() != null) {
							Date date = new Date(Long.parseLong(ra.getValue() + ""));
							String datestr = "'" + DateUtil.getDateString2(date) + "'";
							if (dates.contains(datestr)) {
								int index = dates.lastIndexOf(datestr);
								datalist.set(index, ra.getCount());
							}

						}
					}
				}
			}
			js_data_obj.put("name", sitePage.getPagename());
			js_data_obj.put("data", datalist);
			data.add(js_data_obj);
		}
		dto.setData(data);
		dto.setDates(dates);
		return dto;
	}

	@Override
	public OwnerDataDto getPageData(long ownerid, int pageId) {
		OwnerDataDto dto = new OwnerDataDto();
		int start = (pageId - 1) * IPageConstants.PAGE_LIMIT;
		int total = siteManager.findPageWolCountByOwner(ownerid);
		if (total > 0) {
			Pager pager = new Pager(pageId, total, IPageConstants.PAGE_LIMIT);
			dto.setPager(pager);
			List<SitePage> list = siteManager.findPageByOwner(ownerid, start, IPageConstants.PAGE_LIMIT);
			List<PageDto> pagelist = new ArrayList<PageDto>();
			for (SitePage sitePage : list) {
				long pgid = sitePage.getPageid();
				PageDto pageDto = solrServerMgr.getPageData(pgid);
				pageDto.setPageid(pgid);
				pageDto.setPagename(sitePage.getPagename());
				pageDto.setSitename(sitePage.getSitename());
				pagelist.add(pageDto);
			}
			dto.setList(pagelist);
		}

		return dto;
	}

	public SiteDataDto getSiteData(long sgid, long owner, int daynum) {
		SiteDataDto dto = new SiteDataDto();
		List<Site> sitelist = siteManager.findSiteByOwner(owner);
		List<Integer> hvisitnum = new ArrayList<Integer>();
		List<Integer> nhvisitnum = new ArrayList<Integer>();
		List<Integer> nhvisitadd = new ArrayList<Integer>();
		List<String> datelist = new ArrayList<String>();
		// List<Integer> hdynamicnum = new ArrayList<Integer>();
		List<VisitAnalyse> list = visitReportManager.findVisitAnalyseByDate(sgid);
		for (VisitAnalyse visitAnalyse : list) {
			hvisitnum.add(visitAnalyse.getVisitTotalNum());
			nhvisitnum.add(visitAnalyse.getVisitNum());
			nhvisitadd.add(visitAnalyse.getAddVisitNum());
			// hdynamicnum.add(visitAnalyse.getHdnum());
			datelist.add("'" + visitAnalyse.getDate().substring(5, 10) + "'");
		}
		dto.setDatelist(datelist);
		dto.setNhvisitadd(nhvisitadd);
		dto.setNhvisitnum(nhvisitnum);
		dto.setHvisitnum(hvisitnum);
		dto.setSitelist(sitelist);
		// dto.setHdynamicnum(hdynamicnum);

		List<HdModel> hdlist = hdRecordManager.findHdModelListBySgid(sgid);
		DecimalFormat df2 = new DecimalFormat("##.##%");
		int typetotal = hdRecordManager.findHdReportNumTotal(sgid);
		List<List<Object>> hddatalist = new ArrayList<List<Object>>();
		for (HdModel hdModel : hdlist) {
			int num = hdModel.getTotal();
			double modelnum = 0.0;
			List<Object> listobj = new ArrayList<Object>();
			listobj.add(hdModel.getType());
			listobj.add(num);
			if (typetotal != 0) {
				modelnum = (num * 1.00) / (typetotal * 1.00);
				hdModel.setTotalstr((modelnum != 0 ? df2.format(modelnum) : "0%"));
			} else {
				hdModel.setTotalstr("=0%");
			}
			hddatalist.add(listobj);
		}
		List<VisitNum> hnumlist = new ArrayList<VisitNum>();
		for (int i = 0; i < 4; i++) {
			VisitNum numvs = new VisitNum();
			numvs.setNum(String.valueOf((i + 1)));
			numvs.setPernum(0);
			numvs.setPercent("0%");
			hnumlist.add(numvs);
		}
		List<VisitNum> hdnumlist = visitReportManager.findHdNumListBySgid(sgid);
		int hdtotal = visitReportManager.findDymamicHdNumTotalBySiteGroup(sgid);
		VisitNum numhd = new VisitNum();
		numhd.setNum("5");
		numhd.setPernum(visitReportManager.findHdPerNumByNum(sgid, 5));
		numhd.setPercent("0%");
		hnumlist.add(numhd);
		hdnumlist.add(numhd);
		if (hdnumlist != null && hdnumlist.size() > 0) {
			for (int i = 0; i < hdnumlist.size(); i++) {
				VisitNum hdNum = hdnumlist.get(i);
				String percent = "0%";
				if (hdtotal != 0) {
					int numh = hdNum.getPernum();
					int cnum = Integer.parseInt(hdNum.getNum());
					switch (cnum) {
					case 1:
						dto.setHnum1(numh);
						break;
					case 2:
						dto.setHnum2(numh);
						break;
					case 3:
						dto.setHnum3(numh);
						break;
					case 4:
						dto.setHnum4(numh);
						break;
					case 5:
						dto.setHnum5(numh);
						break;
					default:
						break;
					}
					double vnuhd = (numh * 1.00) / (hdtotal * 1.00);
					percent = vnuhd != 0 ? df2.format(vnuhd) : "0%";
					VisitNum numdvv = hnumlist.get(cnum - 1);
					numdvv.setPercent(percent);
					numdvv.setPernum(numh);
				}
			}
		}

		List<ReportArea> arealist = visitReportManager.findDymamicAreaBySiteGroup(sgid);
		List<List<Object>> areaojb = new ArrayList<List<Object>>();
		int areatotal = visitReportManager.findDAreaReportTotal(sgid);
		int unother = 0;
		if (arealist != null && arealist.size() > 0) {
			for (int i = 0; i < arealist.size(); i++) {
				ReportArea area = arealist.get(i);
				int num = area.getNum();
				unother += num;
				double areanum = 0.0;
				List<Object> ablist = new ArrayList<Object>();
				ablist.add(area.getArea());
				ablist.add(area.getNum());
				if (areatotal != 0) {
					areanum = (num * 1.00) / (areatotal * 1.00);
					area.setNumpercent((areanum != 0 ? df2.format(areanum) : "0%"));
				} else {
					area.setNumpercent("=0%");
				}
				areaojb.add(ablist);
			}
		}
		if (areatotal != 0) {
			int other = areatotal - unother;
			if (other != 0) {
				double areanumother = 0.0;
				List<Object> otherlist = new ArrayList<Object>();
				ReportArea othearea = new ReportArea();
				othearea.setArea("其它");
				othearea.setNum(other);
				areanumother = (other * 1.00) / (areatotal * 1.00);
				othearea.setNumpercent((areanumother != 0 ? df2.format(areanumother) : "0%"));
				otherlist.add("其它");
				otherlist.add(other);
				areaojb.add(otherlist);
				arealist.add(othearea);
			}
		}

		List<ReportTerminalAnalyse> terminallist = visitReportManager.findReportTerminalAnalyseByGsid(sgid);

		int terminaltotal = visitReportManager.findDynamicTerminalReportTotal(sgid);
		List<List<Object>> zongduan = new ArrayList<List<Object>>();
		for (ReportTerminalAnalyse reportTerminalAnalyse : terminallist) {
			String ter = reportTerminalAnalyse.getTerminal();
			String terminal = "";
			if ("C".equalsIgnoreCase(ter)) {
				terminal = "PC";
			} else if ("A".equalsIgnoreCase(ter)) {
				terminal = "Pad";
			} else if ("P".equalsIgnoreCase(ter)) {
				terminal = "Phone";
			}
			List<Object> lo = new ArrayList<Object>();
			int dnum = reportTerminalAnalyse.getHnum();
			double hdnum = (dnum * 1.00) / (terminaltotal * 1.00);
			lo.add(terminal);
			lo.add(dnum);
			zongduan.add(lo);
			reportTerminalAnalyse.setPercent((dnum != 0 ? df2.format(hdnum) : "0%"));
			reportTerminalAnalyse.setTerminal(terminal);
		}
		Gson gson = new Gson();
		dto.setArealist(arealist);
		dto.setHdlist(hdlist);
		dto.setTerminallist(terminallist);
		dto.setHdnumlist(hnumlist);
		dto.setHdtypedata(gson.toJson(hddatalist));
		dto.setHdareadata(gson.toJson(areaojb));
		dto.setHdterminaldata(gson.toJson(zongduan));
		return dto;
	}

	public IDto findZamDetailList(long productid, int pageid) {
		ZanDetailDto dto = new ZanDetailDto();
		int start = (pageid - 1) * IPageConstants.DANYMIC_USER_RECORD_LIMIT;
		int total = hdRecordManager.findZanDetailTotal(productid);
		if (total > 0) {
			dto.setList(hdRecordManager.findZanDetail(productid, start, IPageConstants.DANYMIC_USER_RECORD_LIMIT));
		}
		Pager pager = new Pager(pageid, total, IPageConstants.DANYMIC_USER_RECORD_LIMIT);
		dto.setPager(pager);
		return dto;
	}

	public void setWsManager(IWSManager wsManager) {
		this.wsManager = wsManager;
	}

	@Override
	public IDto composeCardAction(long pageid) {
		CardDto dto = new CardDto();
		long categoryid = templateManager.findCardCategoryid(pageid);
		dto.setFcards(templateManager.findCardList(categoryid, "F"));
		dto.setScards(templateManager.findCardList(categoryid, "S"));
		dto.setCcards(templateManager.findCardList(categoryid, "C"));
		dto.setD1cards(templateManager.findCardList(categoryid, "D1"));
		dto.setD2cards(templateManager.findCardList(categoryid, "D2"));
		dto.setD4cards(templateManager.findCardList(categoryid, "D4"));
		dto.setD5cards(templateManager.findCardList(categoryid, "D5"));
		dto.setD6cards(templateManager.findCardList(categoryid, "D6"));
		dto.setD7cards(templateManager.findCardList(categoryid, "D7"));
		dto.setQcards(templateManager.findCardList(categoryid, "Q"));
		dto.setKcards(templateManager.findCardList(categoryid, "K"));
		return dto;
	}

	@Override
	public IDto composeCardListAction(long pageid, String subtype) {
		CardDto dto = new CardDto();
		long categoryid = templateManager.findCardCategoryid(pageid);
		dto.setSoncards(templateManager.findTemplateCardList(categoryid, subtype));
		return dto;
	}

	@Override
	public IDto composeCard(long pageid, String type, String ptype) {
		CardDto dto = new CardDto();
		long categoryid = templateManager.findCardCategoryid(pageid);
		List<TemplateCard> list = null;
		if ("F".equals(ptype)) {
			list = templateManager.findCardList(categoryid, type, "N");
		} else if ("Z".equals(ptype)) {
			List<PageCard> pageCardList = templateManager.findTemplateCardByPageid(pageid);
			if (pageCardList != null && pageCardList.size() > 0) {
				list = templateManager.findCardList(categoryid, type, "N");
			} else {
				list = templateManager.findCardList(categoryid, type, "Y");
			}
		}
		dto.setCards(list);
		return dto;
	}

	@Override
	public long composeAddCard(long pageid, long cardid) {
		return templateManager.updateCopyCard(pageid, cardid);
	}

	@Override
	public long composeUpdateCard(long pageid, long cardid, long pcid) {
		templateManager.deleteCard(pcid, pageid);
		return templateManager.updateCopyCard(pageid, cardid);
	}

	@Override
	public IDto composeShowCard(long pcid) {
		CardDto dto = new CardDto();
		dto.setTc(templateManager.findCardByPcid(pcid));
//		dto.setJspstyle(pageManager.findJspstyleByPcid(pcid)); 连续式 卡片式  已过时不用
		dto.setCardcss(templateManager.findCssByPcid(pcid));
		return dto;
	}

	@Override
	public IDto composeShowCard2(long pcid) {
		CardDto dto = new CardDto();
		dto.setTc(templateManager.findCardByPcid2(pcid));
		dto.setCardcss(templateManager.findCssByPcid(pcid));
		return dto;
	}
	
	@Override
	public void setXq(CardDto dto){
		//卡片详情页
		VisitUser visit = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		System.out.println(visit.getSkey());
		if(visit!=null && StringUtils.isNotEmpty(visit.getSkey())){
			if ("n".equalsIgnoreCase(visit.getSkey()))
			{	//n-id 新闻详情页
				ContentNew cn = contentManager.findNewsById(Long.parseLong(visit.getSvalue()));
				if(!HyConfig.isRun()){
					cn.setReadNum(hylogSolrServer.readNumber("n", cn.getId()+""));					
				}
				dto.setNews(cn);
				if(dto.getTc()!= null){
					dto.setFatherpage(pageManager.findFatherPageid(dto.getTc().getPageid()));					
				}
			}
			List<BBSContent> list = new ArrayList<BBSContent>();	
			if ("ct".equalsIgnoreCase(visit.getSkey()))
			{
				//ct-hy-id 产品目录列表
				list = contentManager.findBBSProduct(Long.parseLong(visit.getSvalue()), 1);
			}else if ("ctt".equalsIgnoreCase(visit.getSkey()))
			{
				//ctt-hy-id产品详情页
				ContentProduct ct = contentManager.findProductById(Long.parseLong(visit.getSvalue()));
				boolean isBuyProduct = wxBuyProductMgr.findPersonLimitNum(ct,visit.getHyUserId(),0);
				dto.setBuyProduct(isBuyProduct);
				dto.setProduct(ct);
			} else if ("cp".equalsIgnoreCase(visit.getSkey()))
			{
				//cp-hy-id 图片目录列表
				list = contentManager.findBBSPicture(Long.parseLong(visit.getSvalue()), 0);
			}else if ("cpp".equalsIgnoreCase(visit.getSkey()))
			{
				//cpp-hy-id图片详情页
				ContentPicture cp = contentManager.findPictureById(Long.parseLong(visit.getSvalue()));
				dto.setPicture(cp);
			} else if ("cv".equalsIgnoreCase(visit.getSkey()))
			{
				//cv-hy-id 视频目录详情页
				list = contentManager.findBBSVideo(Long.parseLong(visit.getSvalue()), 3);
			} else if ("cvv".equalsIgnoreCase(visit.getSkey()))
			{
				//cvv-hy-id 视频详情页
				ContentVideo cvv = contentManager.findVideoById(Long.parseLong(visit.getSvalue()));
				dto.setVideo(cvv);
			}  else if ("cn".equalsIgnoreCase(visit.getSkey()))
			{
				//cn-hy-id 新闻目录详情页
				list = contentManager.findBBSNews(Long.parseLong(visit.getSvalue()), 2);
			} else if ("ce".equalsIgnoreCase(visit.getSkey()))
			{
				//ce-hy-id 测评结果页面
				ExamResult result = examManager.findExamResult(Long.parseLong(visit.getSvalue()));
				dto.setExamResult(result);
			}
			if(list.size()>0){
				dto.setList(list);			
			}
		}
	}

	@Override
	public IDto composeEditBlock(long relationid, long pageid) {
		CardDto dto = new CardDto();
		TemplateBlock tb = templateManager.findTemplateBlockByRelationid(relationid);
		PageBlockRelation pbr = templateManager.findPageBlockRelationByRelationid(relationid);
		dto.setPages(pageManager.findSitePagesByOnePageOfSite(pageid));
		dto.setTb(tb);
		dto.setPbr(pbr);
		return dto;
	}

	@Override
	public int composeSaveRelationJson(long relationid, String json) {
		return templateManager.saveRelationJson(relationid, json);
	}

	@Override
	public IDto composeTemplateCardByPageid(long pageid) {
		CardDto dto = new CardDto();
		dto.setPc(templateManager.findTemplateCardByPageid(pageid));
		return dto;
	}

	@Override
	public IDto composeTemplateCardByPageid2(long pageid) {
		CardDto dto = new CardDto();
		dto.setPc(templateManager.findTemplateCardByPageid2(pageid));
		return dto;
	}

	public IDto composeEditPage(long pageid,long ownerid) {
		CardDto dto = new CardDto();
		Page page = templateManager.findPageByPageid(pageid,ownerid);
		if (page == null) {
			return null;
		}
		dto.setPage(page);
		dto.setPageid(pageid);
		dto.setStype(page.getStype());
		dto.setSubtype(templateManager.findSubTypeByPageid(pageid));
		dto.setPc(templateManager.findTemplateCardByPageid(pageid));
		if (dto.getPc() != null && dto.getPc().size() > 0) { // 用于控制是否能够继续新增卡片
			List<PageCard> list = dto.getPc();
			for (int i = 0; i < list.size(); i++) {
				if ("F".equals(list.get(i).getType()) || "C".equals(list.get(i).getType()) || "Q".equals(list.get(i).getType())) {
					dto.setIsAddCard("Y");
					break;
				}
			}
		}

		long relationid = templateManager.findRelationidByPageid(pageid);
		while (relationid != 0) {
			pageid = templateManager.findPageidByRelationid(relationid);
			relationid = templateManager.findRelationidByPageid(pageid);
		}
		page = pageManager.findPageByPageid(pageid);
		List<Page> showPageList = new ArrayList<Page>();
		List<Page> pageList = templateManager.findSuccessPageByPageid(pageid);
		showPageList.add(page);
		showPageList.addAll(pageList);
		while (pageList != null && pageList.size() > 0) { // 如果存在子页面 则不断循环页面的子页面
															// 将所有的子页面保存起来
			List<Page> list = new ArrayList<Page>();
			for (int i = 0; i < pageList.size(); i++) {
				List<Page> pageList1 = templateManager.findSuccessPageByPageid(pageList.get(i).getId());
				if (pageList1 != null && pageList1.size() > 0) {
					for (int j = 0; j < pageList1.size(); j++) {
						showPageList.add(pageList1.get(j));
						list.add(pageList1.get(j));
					}
				}
			}
			pageList = list;
		}
		dto.setPages(showPageList);
		return dto;

	}

	public IDto composeEditKongBaiPage(long pageid,long ownerid) {
		PageEditDto dto = new PageEditDto();
		Page page = templateManager.findPageByPageid(pageid,ownerid);
		if (page == null) {
			return null;
		}
		dto.setPage(page);
		dto.setPageid(pageid);
		dto.setStype(templateManager.findPageStype(pageid));
		PageHtml pageHtml = templateManager.findHtmlByPageid(pageid, "E");
		dto.setPageHtml(pageHtml);
		JSONObject css = null;
		JSONObject js = null;
		if (pageHtml != null) {
			css = JSONObject.fromObject(pageHtml.getCss());
			js = JSONObject.fromObject(pageHtml.getJs());
		}
		dto.setCss(css);
		dto.setJs(js);
		return dto;
	}

	public IDto composeEditPageG(long pageid,long ownerid) {
		PageEditGDto dto = new PageEditGDto();
		Page page = templateManager.findPageByPageid(pageid,ownerid);
		if (page == null) {
			return null;
		}
		dto.setPage(page);
		dto.setPageid(pageid);
		return dto;
	}

	public IDto composeTemplateCardByPageidAndPcid(long pageid, long pcid) {
		CardDto dto = new CardDto();
		dto.setPc(templateManager.findTemplateCardByPageid(pageid));
		dto.setTc(templateManager.findCardByPcid(pcid));
		return dto;
	}

	@Override
	public IDto composeCardListByPageid(long pageid, String type) {
		CardDto dto = new CardDto();
		// dto.setPc(templateManager.findCardListByPageid(pageid)); //所有页面上的的卡片
		long relationid = templateManager.findRelationidByPageid(pageid);
		Page page = pageManager.findPageByPageid(pageid);
		dto.setPage(page);
		while (relationid != 0) {
			pageid = templateManager.findPageidByRelationid(relationid);
			relationid = templateManager.findRelationidByPageid(pageid);
		}
		List<PageCard> pageCardsList = templateManager.findTemplateCardByPageid(pageid);
		if (pageCardsList != null && pageCardsList.size() > 0) {
			for (int i = 0; i < pageCardsList.size(); i++) {// 遍历出卡片上有几个模块
				List<TemplateBlock> tb = templateManager.findRelationidByPcid(pageCardsList.get(i).getId());
				pageCardsList.get(i).setTb(tb);
			}
		}
		dto.setCheckedpc(pageCardsList);// 选择的卡片
		List<Page> showPageList = new ArrayList<Page>();
		List<Page> pageList = templateManager.findSuccessPageByPageid(pageid);
		showPageList.addAll(pageList);
		while (pageList != null && pageList.size() > 0) { // 如果存在子页面 则不断循环页面的子页面
															// 将所有的子页面保存起来
			List<Page> list = new ArrayList<Page>();
			for (int i = 0; i < pageList.size(); i++) {
				List<Page> pageList1 = templateManager.findSuccessPageByPageid(pageList.get(i).getId());
				if (pageList1 != null && pageList1.size() > 0) {
					for (int j = 0; j < pageList1.size(); j++) {
						showPageList.add(pageList1.get(j));
						list.add(pageList1.get(j));
					}
				}
			}
			pageList = list;
		}
		if (showPageList != null && showPageList.size() > 0) {
			for (int i = 0; i < showPageList.size(); i++) {
				List<PageCard> pageCards = templateManager.findTemplateCardByPageid(showPageList.get(i).getId());
				if (pageCards != null && pageCards.size() > 0) {
					for (int j = 0; j < pageCards.size(); j++) {// 遍历出卡片上有几个模块
						List<TemplateBlock> tbb = templateManager.findRelationidByPcid(pageCards.get(j).getId());
						pageCards.get(j).setTb(tbb);
					}
				}
				showPageList.get(i).setPageCards(pageCards);
			}
		}
		dto.setPages(showPageList);
		return dto;
	}

	@Override
	public int composeMovePosition(String cardMoveStr) {
		return templateManager.updateCardPosition(cardMoveStr);
	}

	@Override
	public int composeDeleteCard(long pcid, long pageid) {
		return templateManager.deleteCard(pcid, pageid);
	}

	@Override
	public int updateCardName(long pcid, String name) {
		return templateManager.updateCardName(pcid, name);
	}

	@Override
	public int addPageHtml(long pageid, String sTotalString) {
		return pageCacheManager.savePageHtml(pageid, sTotalString);
	}

	@Override
	public String findHtmlByPageid(long pageid) {
		String str = "";
		PageCache pageCache = pageCacheManager.findPageCacheByPageid(pageid);
		if (pageCache != null) {
			str = pageCache.getHtml();
		}
		return str;
	}

	@Override
	public List<Site> composeSiteGroupList(Account account) {
		return siteManager.findSiteListByOnwer(account.getOwner().getId());
	}

	@Override
	public int saveNewPage(long siteid, long pageid) {
		int result = 1;
		Page page = pageManager.findPageById(pageid);
		pageManager.addcopyPage(siteid, page);
		return result;
	}

	@Override
	public String saveNewSuit(long siteid, long pageid) {
		Map<Long, Long> pidMap = new HashMap<Long, Long>();
		Set<Long> ridSet = new HashSet<Long>();

		copySuit(siteid, pageid, pidMap, ridSet);

		for (Long rid : ridSet) {
			PageBlockRelation pbr = templateManager.findPageBlockRelationById(rid);
			String json = pbr.getJson();
			for (Map.Entry<Long, Long> Entry : pidMap.entrySet()) {
				String pageid_old = Entry.getKey().toString();
				String pageid_copy = Entry.getValue().toString();
				json = json.replaceAll(pageid_old, pageid_copy);
			}
			templateManager.updatePageBlockRelation(rid, json);
		}

		return "Y";
	}

	private void copySuit(long siteid, long pageid, Map<Long, Long> pidMap, Set<Long> ridSet) {
		if (!pidMap.containsKey(pageid)) {
			Page page = pageManager.findPageById(pageid);
			Long copied = pageManager.addcopyPage(siteid, page);
			pidMap.put(pageid, copied);
			List<Block2page> b2ps = templateManager.findBlock2pageByPageid(pageid);
			for (Block2page b2p : b2ps) {
				copySuit(siteid, b2p.getPageid(), pidMap, ridSet);
			}
		}
	}

	@Override
	public List<SitePage> findSitePageByOwner(long owner) {
		return siteManager.findSitePageByOwner(owner);
	}

	public void setManagers(Map<Long, com.huiyee.esite.fmgr.IFeatureManager> managers) {
		this.managers = managers;
	}

	@Override
	public int updateOffline(long pageid) {
		int count = pageManager.updateOffline(pageid);//下线
		pageManager.savePageOnlineLog(pageid, "N");//记日志
		Sitegroup sg = siteManager.findSitegroupByPageid(pageid);//更新站点
		siteManager.updateSiteGroupTime(sg.getId());
		for(String url:HyConfig.getClearUrl()){
		HttpTookit.doGet(url, "key=" + Long.toString(pageid), "utf-8", true);
		}
		return count;
	}

	@Override
	public int updateOnline(long pageid) {
		List<Page> childPage = templateManager.findSuccessPageByPageid(pageid);//子页面
		childPage.add(pageManager.findPageById(pageid));
		updateData(pageid);//更新数据
		for(Page p : childPage){
			pageManager.updateOnline(p.getId());//上线
			pageManager.savePageOnlineLog(p.getId(), "Y");//记日志
		}
		Sitegroup sg = siteManager.findSitegroupByPageid(pageid);//更新站点
		return siteManager.updateSiteGroupTime(sg.getId());
	}

	@Override
	public PageDto getPageVisitArea(long pageid, long ownerid, String src) {
		PageDto pageDto = new PageDto();
		SitePage page = siteManager.findPageById(pageid);
		if (page == null)
			return null;
		if (ownerid != page.getOwnerid())
			return null;
		List<List<Object>> areaList = new ArrayList<List<Object>>();
		SolrDataDto dto = solrServerMgr.getPageVisitArea(pageid, src);
		int total = dto.getTotal();
		int thisall = 0;
		int i = 0;
		FacetField field = dto.getField();
		if (field != null) {
			List<FacetField.Count> cs = field.getValues();
			for (FacetField.Count a : cs) {
				if (i < 10) {
					List<Object> oblist = new ArrayList<Object>();
					oblist.add("'" + a.getName() + "'");
					oblist.add(a.getCount());
					areaList.add(oblist);
				}
				thisall = thisall + (int) a.getCount();
				i++;
			}
		}
		int other = total - thisall;
		if (other > 0) {
			List<Object> otherlist = new ArrayList<Object>();
			otherlist.add("'其他'");
			otherlist.add(other);
			areaList.add(otherlist);
		}
		pageDto.setData(areaList);
		pageDto.setSitePage(page);
		return pageDto;
	}

	@Override
	public PageDto getPageHdArea(long pageid, long ownerid, String src) {
		PageDto pageDto = new PageDto();
		SitePage page = siteManager.findPageById(pageid);
		if (page == null)
			return null;
		if (ownerid != page.getOwnerid())
			return null;
		List<List<Object>> areaList = new ArrayList<List<Object>>();
		SolrDataDto dto = solrServerMgr.getPageHdArea(pageid, src);
		int total = dto.getTotal();
		int thisall = 0;
		int i = 0;
		FacetField field = dto.getField();
		if (field != null) {
			List<FacetField.Count> cs = field.getValues();
			for (FacetField.Count a : cs) {
				if (i < 10) {
					List<Object> oblist = new ArrayList<Object>();
					oblist.add("'" + a.getName() + "'");
					oblist.add(a.getCount());
					areaList.add(oblist);
				}
				thisall = thisall + (int) a.getCount();
				i++;
			}
		}
		int other = total - thisall;
		if (other > 0) {
			List<Object> otherlist = new ArrayList<Object>();
			otherlist.add("'其他'");
			otherlist.add(other);
			areaList.add(otherlist);
		}
		pageDto.setData(areaList);
		pageDto.setSitePage(page);
		return pageDto;
	}

	@Override
	public PageDto getPageVisitGender(long pageid, long ownerid, String src) {
		PageDto pageDto = new PageDto();
		SitePage page = siteManager.findPageById(pageid);
		if (page == null)
			return null;
		if (ownerid != page.getOwnerid())
			return null;
		List<List<Object>> genderList = new ArrayList<List<Object>>();
		SolrDataDto dto = solrServerMgr.getPageVisitGender(pageid, src);
		int total = dto.getTotal();
		int thisall = 0;
		FacetField field = dto.getField();
		if (field != null) {
			List<FacetField.Count> cs = field.getValues();
			for (FacetField.Count a : cs) {
				List<Object> oblist = new ArrayList<Object>();
				String gender = a.getName();
				if ("1".equals(gender)) {
					oblist.add("'男'");
				} else if ("2".equals(gender)) {
					oblist.add("'女'");
				}
				oblist.add(a.getCount());
				genderList.add(oblist);
				thisall = thisall + (int) a.getCount();
			}
		}
		int other = total - thisall;
		if (other > 0) {
			List<Object> otherlist = new ArrayList<Object>();
			otherlist.add("'未知'");
			otherlist.add(other);
			genderList.add(otherlist);
		}
		pageDto.setData(genderList);
		pageDto.setSitePage(page);
		return pageDto;
	}

	@Override
	public PageDto getPageHdGender(long pageid, long ownerid, String src) {
		PageDto pageDto = new PageDto();
		SitePage page = siteManager.findPageById(pageid);
		if (page == null)
			return null;
		if (ownerid != page.getOwnerid())
			return null;
		List<List<Object>> genderList = new ArrayList<List<Object>>();
		SolrDataDto dto = solrServerMgr.getPageHdGender(pageid, src);
		int total = dto.getTotal();
		int thisall = 0;
		FacetField field = dto.getField();
		if (field != null) {
			List<FacetField.Count> cs = field.getValues();
			for (FacetField.Count a : cs) {
				List<Object> oblist = new ArrayList<Object>();
				String gender = a.getName();
				if ("1".equals(gender)) {
					oblist.add("'男'");
				} else if ("2".equals(gender)) {
					oblist.add("'女'");
				}
				oblist.add(a.getCount());
				genderList.add(oblist);
				thisall = thisall + (int) a.getCount();
			}
		}
		int other = total - thisall;
		if (other > 0) {
			List<Object> otherlist = new ArrayList<Object>();
			otherlist.add("'未知'");
			otherlist.add(other);
			genderList.add(otherlist);
		}
		pageDto.setData(genderList);
		pageDto.setSitePage(page);
		return pageDto;
	}

	@Override
	public PageDto getPageVisitFans(long pageid, long ownerid) {
		PageDto pageDto = new PageDto();
		SitePage page = siteManager.findPageById(pageid);
		if (page == null)
			return null;
		if (ownerid != page.getOwnerid())
			return null;
		List<List<Object>> areaList = new ArrayList<List<Object>>();
		SolrDataDto dto = solrServerMgr.getPageVisitFans(pageid);
		Map<String, Integer> fq = dto.getFq();
		if (fq != null) {
			for (Map.Entry<String, Integer> entry : fq.entrySet()) {
				String key = entry.getKey();
				Integer value = entry.getValue();
				if ("A".equalsIgnoreCase(key)) {
					pageDto.setFansnum1(value);
				} else if ("B".equalsIgnoreCase(key)) {
					pageDto.setFansnum2(value);
				} else if ("C".equalsIgnoreCase(key)) {
					pageDto.setFansnum3(value);
				} else if ("D".equalsIgnoreCase(key)) {
					pageDto.setFansnum4(value);
				} else if ("E".equalsIgnoreCase(key)) {
					pageDto.setFansnum5(value);
				} else if ("F".equalsIgnoreCase(key)) {
					pageDto.setFansnum6(value);
				}

			}
		}
		pageDto.setData(areaList);
		pageDto.setSitePage(page);
		return pageDto;
	}

	@Override
	public PageDto getPageHdFans(long pageid, long ownerid) {
		PageDto pageDto = new PageDto();
		SitePage page = siteManager.findPageById(pageid);
		if (page == null)
			return null;
		if (ownerid != page.getOwnerid())
			return null;
		List<List<Object>> areaList = new ArrayList<List<Object>>();
		SolrDataDto dto = solrServerMgr.getPageHdFans(pageid);
		Map<String, Integer> fq = dto.getFq();
		if (fq != null) {
			for (Map.Entry<String, Integer> entry : fq.entrySet()) {
				String key = entry.getKey();
				Integer value = entry.getValue();
				if ("A".equalsIgnoreCase(key)) {
					pageDto.setFansnum1(value);
				} else if ("B".equalsIgnoreCase(key)) {
					pageDto.setFansnum2(value);
				} else if ("C".equalsIgnoreCase(key)) {
					pageDto.setFansnum3(value);
				} else if ("D".equalsIgnoreCase(key)) {
					pageDto.setFansnum4(value);
				} else if ("E".equalsIgnoreCase(key)) {
					pageDto.setFansnum5(value);
				} else if ("F".equalsIgnoreCase(key)) {
					pageDto.setFansnum6(value);
				}

			}
		}
		pageDto.setData(areaList);
		pageDto.setSitePage(page);
		return pageDto;
	}

	@Override
	public IDto composeVisitListByPageId(long pid, int pageId, long ownerid, String src) {
		ReportRecordDto dto = new ReportRecordDto();
		SitePage page = siteManager.findPageById(pid);
		if (page == null)
			return null;
		if (ownerid != page.getOwnerid())
			return null;
		int start = (pageId - 1) * IPageConstants.SITE_LIMIT;
		SolrDataDto dataDto = solrServerMgr.getPageVisitList(pid, start, IPageConstants.SITE_LIMIT, src);
		int total = dataDto.getTotal();
		List<SolrRecord> slist = new ArrayList<SolrRecord>();
		if (total > 0) {
			SolrDocumentList list = dataDto.getDocumentList();
			for (SolrDocument solrDocument : list) {
				SolrRecord record = new SolrRecord();
				if ("s".equals(src)) {
					String wbuid = (String) solrDocument.getFieldValue("wbuid");
					if (wbuid != null) {
						record.setWbuid(Long.parseLong(wbuid));
					}
					record.setNickname(solrDocument.getFieldValue("wnm") + "");
				}
				record.setArea(solrDocument.getFieldValue("area") + "");
				record.setIp(solrDocument.getFieldValue("ip") + "");
				record.setTerminal(solrDocument.getFieldValue("term") + "");
				record.setCreatetime(new Date(Long.parseLong((solrDocument.getFieldValue("created") + ""))));
				slist.add(record);
			}
		}
		dto.setList(slist);
		Pager pager = new Pager(pageId, total, IPageConstants.SITE_LIMIT);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public IDto composeHdListByPageId(long pid, int pageId, long ownerid, String src) {
		ReportRecordDto dto = new ReportRecordDto();
		SitePage page = siteManager.findPageById(pid);
		if (page == null)
			return null;
		if (ownerid != page.getOwnerid())
			return null;
		int start = (pageId - 1) * IPageConstants.SITE_LIMIT;
		SolrDataDto dataDto = solrServerMgr.getPageHdList(pid, start, IPageConstants.SITE_LIMIT, src);
		int total = dataDto.getTotal();
		List<SolrRecord> slist = new ArrayList<SolrRecord>();
		if (total > 0) {
			SolrDocumentList list = dataDto.getDocumentList();
			for (SolrDocument solrDocument : list) {
				SolrRecord record = new SolrRecord();
				if ("s".equals(src)) {
					String wbuid = (String) solrDocument.getFieldValue("wbuid");
					if (wbuid != null) {
						record.setWbuid(Long.parseLong(wbuid));
					}
					record.setNickname(solrDocument.getFieldValue("wnm") + "");
				}
				record.setArea(solrDocument.getFieldValue("area") + "");
				record.setIp(solrDocument.getFieldValue("ip") + "");
				record.setTerminal(solrDocument.getFieldValue("term") + "");
				record.setCreatetime(new Date(Long.parseLong((solrDocument.getFieldValue("created") + ""))));
				slist.add(record);
			}
		}
		dto.setList(slist);
		Pager pager = new Pager(pageId, total, IPageConstants.SITE_LIMIT);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public PageDto getPageHdType(long pageid, long ownerid, String src) {
		PageDto pageDto = new PageDto();
		SitePage page = siteManager.findPageById(pageid);
		if (page == null)
			return null;
		if (ownerid != page.getOwnerid())
			return null;
		List<List<Object>> hdTypeList = new ArrayList<List<Object>>();
		List<HdType> hdlist = new ArrayList<HdType>();
		SolrDataDto dto = solrServerMgr.getPageHdType(pageid, src);
		FacetField field = dto.getField();
		if (field != null) {
			List<FacetField.Count> cs = field.getValues();
			for (FacetField.Count a : cs) {
				List<Object> oblist = new ArrayList<Object>();
				long id = Long.parseLong(a.getName());
				HdType type = featureManager.findHdType(id);
				Feature feature = featureManager.findFeaturesId(type.getAct());
				String typename = feature.getName();
				oblist.add("'" + type.getName() + "'");
				oblist.add(a.getCount());
				hdTypeList.add(oblist);
				type.setCount((int) a.getCount());
				type.setType(typename);
				hdlist.add(type);
			}
		}
		pageDto.setData(hdTypeList);
		pageDto.setSitePage(page);
		pageDto.setTypelist(hdlist);
		return pageDto;
	}

	@Override
	public long findPageidByPcid(long pcid) {
		return templateManager.findPageidByPcid(pcid);
	}

	@Override
	public CardDto findTemplateSiteIndex(long category, String type) {
		CardDto dto = new CardDto();
		List<TemplateCategory> catetgorylist = templateManager.findTemplateCategory();
		if (catetgorylist != null && catetgorylist.size() > 0) {
			if (category == 0)
				category = catetgorylist.get(0).getId();
			dto.setCards(templateManager.findCardList(category, type));
			dto.setCategorylist(catetgorylist);
		}

		return dto;
	}

	private int updateData1(long pageid) {
		int count = 0;
		List<Page> showPageList = templateManager.findSuccessPageByPageid(pageid);//子页面
		showPageList.add(pageManager.findPageByPageid(pageid));//加入本页面
		for (int a = 0; a < showPageList.size(); a++) {
			templateManager.deletePageCard2(showPageList.get(a).getId());//删掉前台所有卡片
			List<PageCard> pagecard = templateManager.findPageCardAllByPageid(showPageList.get(a).getId());//找到后台所有卡片
			if (pagecard != null && pagecard.size() > 0) {
				for (int i = 0; i < pagecard.size(); i++) {
					PageCard pc = pagecard.get(i);
					templateManager.savePageCard2(pc.getId(), pc.getPageid(), pc.getCardid(), pc.getPosition(), pc.getCardname(), pc.getBg(), pc.getIsshow(), pc.getCss(), pc.getEventName());
					count += templateManager.savePageBolckRelation2(pc.getId());
				}
			}
			for(String url:HyConfig.getClearUrl()){
			HttpTookit.doGet(url, "key=" + showPageList.get(a).getId(), "utf-8", true);
			}
		}
		Sitegroup sg = siteManager.findSitegroupByPageid(pageid);
		siteManager.updateSiteGroupTime(sg.getId());
		return count;
	}

	public int updateData(long pageid) {
		Page page = pageManager.findPageById(pageid);
		int count = 0;
		if("G".equals(page.getType()) || "H".equals(page.getType())){
			//个性化更新数据
			count = updateData2(pageid);
			for(String url:HyConfig.getClearUrl()){
			HttpTookit.doGet(url, "key=" + Long.toString(pageid), "utf-8", true);
			}
		}
		if("C".equals(page.getType()) || "H".equals(page.getType())){
			//卡片更新数据
			count = updateData1(pageid);
		}
		return count;
	}

	private int updateData2(long pageid) {
		List<Feature> list = pageManager.findFeaturesByPageid(pageid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", pageManager.findPageById(pageid));
		for (int idx = 1; idx <= list.size(); idx++) {
			Feature feature = list.get(idx - 1);
			map.put(idx + "", managers.get(feature.getId()).config(feature.getFid()));
		}
		return pageCacheManager.saveMap(pageid, map);
	}

	@Override
	public int composeMoveStyle(String jspstyle, long pageid) {
		if ("K".equals(jspstyle)) {
			return templateManager.updatePageJspName(IPageConstants.CARD_MOVE_STYLE_K, jspstyle, pageid);
		} else if ("L".equals(jspstyle)) {
			return templateManager.updatePageJspName(IPageConstants.CARD_MOVE_STYLE_L, jspstyle, pageid);
		} else if ("Q".equals(jspstyle)) {
			return templateManager.updatePageJspName(IPageConstants.CARD_MOVE_STYLE_Q, jspstyle, pageid);
		}
		return 0;
	}

	@Override
	public int updatePageName(String name, long pageid) {
		return pageManager.updatePageName(pageid, name);
	}

	@Override
	public String findHtmlByPage(long pageid, String type) {
		return pageManager.findHtmlByPage(pageid, type);
	}

	@Override
	public List<TemplateCard> findCardListByType(long pageid, String type) {
		long categoryid = templateManager.findCardCategoryid(pageid);
		List<TemplateCard> list = templateManager.findCardList(categoryid, type);
		return list;
	}

	@Override
	public IDto composePublishSinaIndex(long siteid) {
		PublishDto dto = new PublishDto();
		List<Page> plist = pageManager.findSinaPublishPageBySiteid(siteid);
		List<SinaApp> applist = sinaTokenMgr.findSinaApp();
		dto.setPlist(plist);
		dto.setApplist(applist);
		return dto;
	}

	@Override
	public NewDetailDto findNewDetails(long relationid, long nid,String type) {
		NewDetailDto dto = new NewDetailDto();
		List<BlockContext> list = templateManager.findesBlockContext(relationid);
		long contextid = 0;
		if (list != null && list.size() > 0) {
			contextid = list.get(0).getId();
		}
		Page page = templateManager.findPageByRelationidAndContextid(relationid, contextid);
		dto.setPage(page);
		if (page != null) {
			dto.setPageid(page.getId());
			List<PageCard> pageCards = templateManager.findTemplateCardByPageid(page.getId());
			if (pageCards != null && pageCards.size() > 0) {
				dto.setTc(pageCards.get(0));
				dto.setCardcss(pageCards.get(0).getCss());
			}
		}
		if("N".equalsIgnoreCase(type)){
			ContentNew contentNew = templateManager.findNewsByNid(nid);
			contentNew.setReadNum(hylogSolrServer.readNumber("n", contentNew.getId()+""));
			dto.setContentNew(contentNew);			
		}
		if("P".equalsIgnoreCase(type)){
			ContentProduct contentProduct = templateManager.findProductByNid(nid);
			dto.setContentProduct(contentProduct);			
		}
		if("B".equalsIgnoreCase(type)){
			dto.setTopicid(nid);
			List<String> bbsList=bbsMgr.findSource(nid);
			if(bbsList!=null&&bbsList.size()==3){
				dto.setSource(bbsList.get(0)+"-"+bbsList.get(1)+"-"+bbsList.get(2));
			}
		}
		return dto;
	}
	
	@Override
	public PingceResultDto findExamResult(long relationid, long rid) {
		PingceResultDto dto = new PingceResultDto();
		List<BlockContext> list = templateManager.findesBlockContext(relationid);
		long contextid = 0;
		if (list != null && list.size() > 0) {
			contextid = list.get(0).getId();
		}
		Page page = templateManager.findPageByRelationidAndContextid(relationid, contextid);
		dto.setPage(page);
		if (page != null) {
			List<PageCard> pageCards = templateManager.findTemplateCardByPageid(page.getId());
			if (pageCards != null && pageCards.size() > 0) {
				dto.setTc(pageCards.get(0));
				dto.setCardcss(pageCards.get(0).getCss());
			}
		}
		ExamResult result = examManager.findExamResult(rid);
		dto.setResult(result);
		return dto;
	}

	@Override
	public int savePageBg(long pageid, String title, String keywords, String description, String background, String uploadMusic, String musicname, String uploadArrow, JSONObject tagJson) {
		String str = pageManager.findPageBg(pageid);
		JSONObject jo;
		try{
			jo = JSONObject.fromObject(str);
		} catch (Exception e){
			jo=new JSONObject();
		} 
		if (background.contains("/")) {
			background = "url(" + background + ")";
		}
		jo.element("background", background);
		jo.element("title",title);
		jo.element("keywords",keywords);
		jo.element("description",description);
		jo.element("uploadMusic",uploadMusic);
		jo.element("musicname",musicname);
		jo.element("uploadArrow", uploadArrow);
		if(tagJson!=null){
			Account account=(Account)ActionContext.getContext().getSession().get("account");
			userTagMgr.updatePageTag(account.getOwner().getId(), pageid, tagJson);
		}
		return pageManager.savePageBg(pageid, jo.toString());
	}

	@Override
	public String findPageBg(long pageid) {
		return pageManager.findPageBg(pageid);
	}

	@Override
	public int savePageCardStyle(long cardid, String bg) {
		return templateManager.savePageCardStyle(cardid, bg);
	}

	@Override
	public int savePageCardCss(long cardid, String css) {
		return templateManager.savePageCardCss(cardid, css);
	}

	@Override
	public IDto findBgByCardid(long cardid) {
		String flag;
		CardDto dto = new CardDto();
		CardBackGround cardbg = templateManager.findBgByCardid(cardid);
		if (cardbg.getBg1() != null && !"".equals(cardbg.getBg1())) {
			dto.setBg(cardbg.getBg1());
			if (dto.getBg().startsWith("#")) {
				flag = "C";
				dto.setFlag(flag);
			} else if (dto.getBg().startsWith("url")) {
				String sub = dto.getBg().substring(4, dto.getBg().length() - 1);
				dto.setBg(sub);
				flag = "P";
				dto.setFlag(flag);
			}
		} else if (cardbg.getBg2() != null && !"".equals(cardbg.getBg2())) {
			dto.setBg(cardbg.getBg2());
			if (dto.getBg().startsWith("#")) {
				flag = "C";
				dto.setFlag(flag);
			} else if (dto.getBg().startsWith("url")) {
				String sub = dto.getBg().substring(4, dto.getBg().length() - 1);
				dto.setBg(sub);
				flag = "P";
				dto.setFlag(flag);
			}
		} else {
			flag = "N";
			dto.setFlag(flag);
		}
		return dto;
	}

	@Override
	public MaterialDto findMaterialIndex(long category, long scategory, int pageId) {
		return materialManager.findMaterialIndex(category, scategory, pageId);
	}

	@Override
	public MaterialDto findMaterialList(long category, long scategory, int pageId) {
		int size = 0;
		if (category == 1) {
			size = 9;
		} else {
			size = 42;
		}
		if (scategory > 5) {
			size = 40;
		}
		return materialManager.findMaterialList(category, scategory, pageId, size);
	}

	@Override
	public JSONObject findContentDetails(JSONObject jb, VisitUser vu) {
		// String hyType = jb.getString("hyType");
		long featureid = jb.getLong("featureid");
		Map<String, Object> m = new HashMap<String, Object>();
		if (featureid == 102) {// 产品列表
			long ccid = jb.getLong("ccid");
			long ownerid = jb.getLong("ownerid");
			long xqpageid;
			try{
				xqpageid = jb.getLong("pageid");
				m.put("pageid", xqpageid);
			} catch (Exception e){
				System.out.println("产品详情页没有："+ccid);
			}
			List<ContentProduct> list = contentManager.findProductByCcid(ccid, ownerid);
			JSONArray ja = JSONArray.fromObject(list);
			m.put("list", ja);
			jb = JSONObject.fromObject(m);
			return jb;
		}
		if (featureid == 109) {// 图片列表
			long ccid = jb.getLong("ccid");
			long ownerid = jb.getLong("ownerid");
			List<ContentPicture> list = contentManager.findPictureByCcid(ccid, ownerid);
			JSONArray ja = JSONArray.fromObject(list);
			m.put("list", ja);
			jb = JSONObject.fromObject(m);
			return jb;
		}
		if (featureid == 110) {// 新闻列表
			long ccid = jb.getLong("ccid");
			long ownerid = jb.getLong("ownerid");
			long xqpageid;
			try{
				xqpageid = jb.getLong("pageid");
				m.put("pageid", xqpageid);
			} catch (Exception e){
				System.out.println("新闻详情页没有："+ccid);
			}
			List<ContentNew> list = contentManager.findNewByCcid(ccid, ownerid);
			DateUtil dataU = new DateUtil();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCreatetimeStr(dataU.getDateStringM(list.get(i).getPublishtime()));
			}
			JSONArray ja = JSONArray.fromObject(list);
			m.put("list", ja);
			jb = JSONObject.fromObject(m);
			return jb;
		} else if (featureid == 118) {// 申请
			long fid = jb.getLong("fid");
			Show118Dto d = (Show118Dto) managers.get(featureid).config(fid);
			if (d != null) {
				Gson gson = new Gson();
				String str1 = gson.toJson(d.getApp());
				jb.element("obj", str1);
			}
		} else if (featureid == 123) {// 投票
			long fid = jb.getLong("fid");
			Show123Dto d = (Show123Dto) managers.get(featureid).config(fid);
			if (d != null) {
				Gson gson = new Gson();
				String str1 = gson.toJson(d.getInteractVote());
				jb.element("obj", str1);
			}
		} else if (featureid == 124) {// 调研
			long fid = jb.getLong("fid");
			Show124Dto d = (Show124Dto) managers.get(featureid).config(fid);
			if (d != null) {
				Gson gson = new Gson();
				String str1 = gson.toJson(d.getResearchModel());
	
				jb.element("obj", str1);

			}
		} else if (featureid == 125 || featureid == 136 || featureid == 138 || featureid == 143 || featureid == 161) {// 老虎机
			long fid = jb.getLong("fid");
			Show125Dto d = (Show125Dto) managers.get(featureid).config(fid);
			if (d != null) {
				Gson gson = new Gson();
				String str1 = gson.toJson(d.getLottery());
				jb.element("obj", str1);
			}
		} else if (featureid == 126) { // 竞拍
			long fid = jb.getLong("fid");
			Show126Dto d = (Show126Dto) managers.get(featureid).config(fid);
			if (d != null) {
				Gson gson = new Gson();
				String str1 = gson.toJson(d.getAuction());
				//int jf = this.ownerBalanceMgr.findJFByUser((VisitUser) ActionContext.getContext().getSession().get("visitUser"));
				jb.element("obj", str1).element("jf", 0);
			}
		} else if (featureid == 134) {// 口碑
			long fid = jb.getLong("fid");
			Show134Dto d = (Show134Dto) managers.get(featureid).config(fid);
			if (d != null) {
				Gson gson = new Gson();
				String str1 = gson.toJson(d.getSpread());
				jb.element("obj", str1);
			}
		} else if (featureid == 133) {// 签到
			if (vu != null) {
				long uid = vu.getUid();
				int type = vu.getCd();
				long ownerwbuid = 0;
				if (vu.getWxuid() > 0) {
					ownerwbuid = vu.getWxUser().getMpid();
				} else {
					ownerwbuid = vu.getCid();
				}
				CheckinBalance cb = checkinMgr.findBalance(ownerwbuid, uid, type);
				if (cb != null) {
					cb.setRemainBalance(cb.getTotal() - cb.getUsed());
					int checkinBalance = checkinMgr.findCheckinBalanceCount(ownerwbuid, uid, type);
					List<CheckinRecord> checkinRecord = checkinMgr.findCheckinBalance(ownerwbuid, uid, type);// 查询签到记录
					if (checkinRecord != null && checkinRecord.size() > 0) {
						CheckinRecord cr = checkinRecord.get(0);
						String created = DateUtil.getDateString(cr.getCreatetime());
						if (DateUtil.getDateString(new Date()).equals(created)) {
							cb.setIscheck(1);
						}
					}
					cb.setCheckinBalance(checkinBalance);
					cb.setCheckinRecord(checkinRecord);
					Gson gson = new Gson();
					String str1 = gson.toJson(cb);
					jb.element("obj", str1);
				}
			}
		} else if (featureid == 151) {// 社区
			Show151Dto d = new Show151Dto();
			long fid = jb.getLong("fid");
			d = (Show151Dto) managers.get(featureid).config(fid);
			if (d != null) {
				Gson gson = new Gson();
				String str1 = gson.toJson(d.getList());
				jb.element("obj", str1);
			}
		} else if (featureid == 153) {// 测评
			Show153Dto d = new Show153Dto();
			long fid = jb.getLong("fid");
			long xqpageid= jb.getLong("pageid");
			d = (Show153Dto) managers.get(featureid).config(fid);
			if (d != null) {
				Gson gson = new Gson();
				String str1 = gson.toJson(d.getExamModel());
				jb.element("obj", str1).element("pageid", xqpageid);
			}
		}else if (featureid >= 154 || featureid <= 160) {// 多目录
			Show154Dto d = new Show154Dto();
			long fid = jb.getLong("fid");
			Long xqpageid = 0L;
			if(jb.containsKey("pageid")){
				xqpageid = jb.getLong("pageid");				
			}
			d = (Show154Dto) managers.get(featureid).config(fid);
			if (d != null) {
				Gson gson = new Gson();
				String str1 = gson.toJson(d.getList());
				jb.element("list", str1).element("pageid", xqpageid);
			}
		}
		return jb;
	}

	public void setWeiXinMgr(IWeiXinMgr weiXinMgr) {
		this.weiXinMgr = weiXinMgr;
	}

	@Override
	public WxUser addWXUser(WxUser wp) {
		return weiXinMgr.addWXUser(wp);
	}

	@Override
	public String findPagePic(long pageid, long wxapageid) {
		return weiXinMgr.findPagePic(wxapageid);
	}

	@Override
	public int composeUploadPage(long siteid, String pagename, String html, String js, String css) {
		Site s = siteManager.findSiteById(siteid);
		return pageManager.saveUploadPage(siteid, pagename, s.getType(), html, js, css);
	}

	@Override
	public int updateContnetIdx(long contentId, long ccid, String cctype, int moveUp) {
		return contentManager.updateContentIdx(contentId, ccid, cctype, moveUp);
	}

	@Override
	public int updateCategoryName(long id, ContentCategory cc) {
		return contentManager.updateCategoryName(id, cc);
	}

	@Override
	public IDto findAddressList(Account account,long pageid) {
		PageAddressDto dto = new PageAddressDto();
//		dto.setList(pageManager.findAddressList(pageid));
		WxMp wm=weiXinMgr.updateFindWxMp(account.getOwner().getId(), 0);//需要承接的页面需要修改
		dto.setWxMp(wm);
		return dto;
	}

	@Override
	public int findSourceExit(long pageid, String source) {
		return pageManager.findSourceExit(pageid, source);
	}

	@Override
	public int savePageAddress(PageAddress pa, Account account) {
		List<WeiXinPage> wx = weiXinMgr.findWeiXinPageList(pa.getPageid());
		String str = pa.getSource().toLowerCase();
		String address = HyConfig.getPageyuming(account.getOwner().getId()) + "/" + account.getOwner().getEntity() + "/user/show/" + pa.getPageid() + "/" + str + ".html";
		pa.setAddress(address);
		pa.setSource(str);
		for (int i = 0; i < wx.size(); i++) {
			if ("Y".equals(pa.getWeixin())) {
				long id = wx.get(i).getId();
				address = HyConfig.getPageyuming(account.getOwner().getId()) + "/" + account.getOwner().getEntity() + "/user/wxshowpage/" + id + "/" + str + ".html";
				pa.setAddress(address);
			}
		}
		return pageManager.savePageAddress(pa);
	}

	@Override
	public int updatehiddenBlk(long relationid) {
		return pageManager.updatehiddenBlk(relationid);
	}

	@Override
	public int deleteBlk(long relationid) {
		return pageManager.deleteBlk(relationid);
	}

	@Override
	public int updateshowBlk(long pcid) {
		return pageManager.updateshowBlk(pcid);
	}

	@Override
	public PageAddress findSinaTokenIsExit(long pageid) {
		PageAddress pa = pageManager.findPageAddress(pageid);
		int count = sinaTokenMgr.findSinaTokenIsExit(pageid);
		if (pa != null) {
			pa.setCount(count);
		} else {
			return null;
		}
		return pa;

	}
	@Override
	public HdRsDto ineractCanRun(SuperHdModel sh) {
		// 存在否
		HdRsDto rs=new HdRsDto();
		if (sh == null) {
			rs.setStatus(-10000);
			rs.setHydesc("互动不存在，请检查id正确性！");
			return rs;
		}
		// 后台通行
//		if (HyConfig.isRun()) {
//			rs.setStatus(1);
//			rs.setHydesc("后台请求！");
//			return rs;
//		}
		
	   Long subTime=  	(Long) ActionContext.getContext().getSession().get("subTime");
	   long nowTime=System.currentTimeMillis();
	   if(subTime!=null&&nowTime-subTime<2000){
		    rs.setStatus(-10001);
			rs.setHydesc("提交过于频繁");
			return rs;
	   }
	   ActionContext.getContext().getSession().put("subTime", nowTime);
		// 第一层时间过滤
		if (sh.getStarttime()==null || sh.getStarttime().getTime() > System.currentTimeMillis() ) {
			rs.setStatus(-10002);
			rs.setHydesc(sh.getStartnote());
			return rs;
		}
		
		if ( sh.getEndtime()==null || System.currentTimeMillis() > sh.getEndtime().getTime() ) {
			rs.setStatus(-10003);
			rs.setHydesc(sh.getEndnote());
			return rs;
		}
		
		// 获取用户类别和信息
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		// 存在否
		if (vu == null) {
			rs.setStatus(-10004);
			rs.setHydesc("用户不存在！");
			return rs;
		}

		// 第六层次数过滤
		if (!"UPD".equals(sh.getStatus())&&sh.getTotallimit() >=0) {// 没有设置次数，不过滤
			int total =  hdUserDataMgr.findUserTotal(vu.getUid(), vu.getCd(), sh.getId(), sh.getFeatureid());
			if (total >= sh.getTotallimit()) {
				rs.setStatus(-10005);
				rs.setHydesc("总数达上限！");
				return rs;
			} 
		}
		if(!"UPD".equals(sh.getStatus())&&sh.getDaylimit()>=0){
			int num  = hdUserDataMgr.findUserDayNum(vu.getUid(), vu.getCd(), sh.getId(), sh.getFeatureid());
			if (num >= sh.getDaylimit()) {
				rs.setStatus(-10006);
				rs.setHydesc("每天次数达上限！");
				return rs;
			}
		}
		rs.setStatus(10000);
		rs.setHydesc("可以通过！");
		return rs;
	}
	
	/**
	 * type:
	 * t:投票
	 * d:调研
	 * f:表单
	 * l:砸金蛋
	 * y:摇一摇
	 * z:转盘
	 * c:测评
	 */
	@Override
	public void canRunLog(long aoid,String type,HttpServletRequest httpRequest) 
	{
		if(HyConfig.isRun()){
			return;
		}
		try
		{
			VisitUser vux = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
			//只记录微信环境下互动
			
			String ip = ClientUserIp.getIpAddr(httpRequest);
			String uagent = httpRequest.getHeader("user-agent");
			VisitLogDto vl = new VisitLogDto();
			vl.setCreatetime(System.currentTimeMillis());
			vl.setIp(ip);
			vl.setUagent(uagent);
			vl.setUrl(httpRequest.getServletPath() + "?" + httpRequest.getQueryString());
			vl.setCookie(vux.getCookieUser().getCookie());
			vl.setMtype(vux.getCd());
			vl.setPageid(vux.getPageid());
			vl.setSource(vux.getSource());
			vl.setAdesc(type);
			vl.setAoid(aoid);
			vl.setAtype("h");
			if(vux.getWxUser()!=null)
			{
				vl.setOpenid(vux.getWxUser().getOpenid());
				vl.setWxuid(vux.getWxUser().getId());
				vl.setGz(vux.getWxUser().getSubscribe());
			}
			if(vux.getHyUser()!=null)
			{
				vl.setHyuid(vux.getHyUser().getId());
			}
			CacheUtil.addVisitLog(vl);
			
		} catch (Exception e)
		{
			e.printStackTrace();

		}
	}

	@Override
	public int updateisshowMenu(long pcid, String isshow) {
		return pageManager.updateisshowMenu(pcid, isshow);
	}

	@Override
	public Page findPageSubweixin(long pageid) {
		return pageManager.findPageSubweixin(pageid);
	}

	@Override
	public int findPageFeatureFid() {
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		if (account == null) {
			return 0;
		}
		return featureManager.findPageFeatureFid(account.getOwner().getId());
	}

	@Override
	public IDto findPageFeature(long ownerid, int pageId) {
		PersonalTailorDto dto = new PersonalTailorDto();
		int start = (pageId - 1) * IPageConstants.PERSONAL_TAILOR;
		int total = featureManager.findPageFeatureFid(ownerid);
		if (total > 0) {
			List<PageFeature> list = featureManager.findPageFeature(ownerid, start, IPageConstants.PERSONAL_TAILOR);
			dto.setList(list);
			for (int i = 0; i < list.size(); i++) {
				long pageid = list.get(i).getPageid();
				String name = pageManager.findPageName(pageid);
				dto.setPagename(name);
			}
		}
		Pager pager = new Pager(pageId, total, IPageConstants.CONTENT_MOUNTS);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public int updateCode(long pageid, String type, String html) {
		return pageManager.updateHtml(pageid, type, html);
	}

	@Override
	public int savePageTemplate(PageTemplate pt) {
		return pageManager.savePageTemplate(pt);
	}

	@Override
	public IDto findPageTemplate() {
		PageTemplateDto dto = new PageTemplateDto();
		dto.setPlist(templateManager.findPageTemplateList());
		return dto;
	}

	@Override
	public long findXcIdBySiteid(long siteid) {
		return siteManager.findXcIdBySiteid(siteid);
	}

	@Override
	public int updateSiteGroupIsTemplateSetY(long sitegroupid) {
		return siteManager.updateSiteGroupIsTemplateSetY(sitegroupid);
	}

	@Override
	public String composePageRelation(long pageid) {
		PageRelationDto dto = new PageRelationDto();
		Gson gs = new Gson();
		long siteid = siteManager.findSiteidByPageid(pageid);
		List<Page> pageList = siteManager.findPageListBySiteId(siteid);
		int m = 0;
		if (pageList != null && pageList.size() > 0) {
			for (int i = 0; i < pageList.size(); i++) {
				ZuoB zb = ZuoBUtil.MZb(0 + 0.1 * m, 0 + 0.1 * m, 1 + 0.1 * m, m + 0.2);
				NodeBean node = new NodeBean(6, 143, 139);
				node.setId(String.valueOf(pageList.get(i).getId()));
				node.setSize(3);
				node.setLabel(pageList.get(i).getName());
				node.setX(zb.getX());
				node.setY(zb.getY());
				dto.getNodes().add(node);
			}
		}
		List<PageRelation> pageRelationList = pageManager.findPageRelationListBySiteid(siteid);
		if (pageRelationList != null && pageRelationList.size() > 0) {
			for (int i = 0; i < pageRelationList.size(); i++) {
				EdgeBean edge = new EdgeBean();
				String source = String.valueOf(pageRelationList.get(i).getFapageid());
				String target = String.valueOf(pageRelationList.get(i).getPageid());
				edge.setId(source + " TO " + target);
				edge.setSource(source);
				edge.setTarget(target);
				dto.getEdges().add(edge);
			}
		}
		return gs.toJson(dto);
	}

	@Override
	public int updateSiteGroupIsTemplateSetN(long sitegroupid) {
		return siteManager.updateSiteGroupIsTemplateSetN(sitegroupid);
	}

	@Override
	public void savePosition(String ps) {
		String[] p = ps.split("~yh~");
		try {
			for (String s : p) {
				String[] st = s.split("~hy~");
				templateManager.savePostionStyle(Long.parseLong(st[0]), st[1]);
			}
		} catch (Exception e) {
			System.out.println("error ps:" + ps);
			e.printStackTrace();
		}
	}

	@Override
	public long siteCopy(Owner owner, String sitename, String copyType, long groupid) {
		long sitegroupid=0;
		if("N".equals(copyType)||groupid==0){
			sitegroupid= featureManager.addNormalSitegroup(owner, sitename, copyType, groupid);
		}else{
			int exist=siteManager.findSitegroupCountByGrp(copyType,groupid);
			if(exist==0){
				return -1;
			}
			
			if("C".equals(copyType)){
				sitegroupid= featureManager.addCbSite(owner, sitename, copyType, groupid);
			}else if("W".equals(copyType)){
				sitegroupid= featureManager.addXcSite(owner, sitename, copyType, groupid);
			}else if("A".equals(copyType)){
				//线下签到
				sitegroupid= featureManager.addOffCheckSite(owner, sitename, copyType, groupid);
			}else{
				sitegroupid= featureManager.addSiteCopy(owner, sitename, copyType, groupid);
			}
			if(sitegroupid>0)
				updateOnlineByGroup(sitegroupid,copyType);
		}
		return sitegroupid>0?1:sitegroupid;
	}

	@Override
	public IDto findTaoZhuangSite() {
		SiteIndexActionDto dto = new SiteIndexActionDto();
//		dto.setSitegroup(siteManager.findAllTempSiteGroup());
//		dto.setWSiteGroupList(siteManager.findAllTempSiteGroup("W"));
		dto.setBSiteGroupList(siteManager.findAllTempSiteGroup("B"));
		dto.setCSiteGroupList(siteManager.findAllTempSiteGroup("C"));
		dto.setPSiteGroupList(siteManager.findAllTempSiteGroup("P"));
		return dto;
	}

	@Override
	public int addContentType(Account account, String ccname, String type) {
		return contentManager.addContentType(ccname, account.getOwner().getId(), type);
	}

	@Override
	public int deleteContentType(long typeid, Account account) {
		return contentManager.deleteContentType(typeid, account.getOwner().getId());
	}

	@Override
	public IDto findConfigType(Account account) {
		List<OwnerContentType> list = contentManager.findConfigType(account);
		ContentDto dto = new ContentDto();
		dto.setTypeList(list);
		return dto;
	}

	@Override
	public int configTypeSub(List<OwnerContentType> subList, long owner) {
		return contentManager.updateTypeSub(subList, owner);
	}

	@Override
	public List<InteractModel> compostInteractModel() {
		return interactModelManager.findInteractModel();
	}

	@Override
	public String addOwnerInteract(String title, long mid, long owner) {
		return interactModelManager.addOwnerInteract(title, mid, owner);
	}

	@Override
	public int deleteOwnerInteract(long lid, long ownerid) {
		return interactModelManager.deleteOwnerInteract(lid, ownerid);
	}

	@Override
	public InteractModel composeOmName(long omid) {
		return interactModelManager.findInteractModelById(omid);
	}

	@Override
	public IDto composeContentForm(long owner, long ccid, long typeid) {
		ContentFormDto dto = new ContentFormDto();
		dto = getBaseDto(ccid, typeid, dto, true);
		ContentForm cf = contentFormMgr.findContentFormByCcid(dto.getCurrent().getId());
		dto.setContentform(cf);

		String type = dto.getCurrent().getType();
		if (!StringUtils.isEmpty(type) && dto.getCurrent().getId() > 0 && cf != null) {
			List<List<String>> list = new ArrayList<List<String>>();
			List<ContentFormMapping> colum = contentFormMgr.findMappingById(cf.getId());
			if (colum.size() > 4) {
				colum = colum.subList(0, 4);
			}
			dto.setColum(colum);
			List<ContentFormRecord> record = contentFormMgr.findRecordByFormid(cf.getId());
			for (ContentFormRecord cfr : record) {
				list.add(cfr.composeList(colum));
			}
			dto.setRecord(record);
		}
		return dto;
	}

	@Override
	public IDto composeUpdateContentForm(long id, long formid) {
		ContentFormDto dto = new ContentFormDto();
		ContentForm cf = contentFormMgr.findContentFormById(formid);
		dto.setContentform(cf);

		/**
		 * TAB选项 和 目录树相关数据
		 */
		long ccid = cf.getCatid();
		dto = getBaseDto(ccid, 0, dto, false);

		return dto;
	}

	private ContentFormDto getBaseDto(long ccid, long typeid, ContentFormDto dto, boolean needTree) {

		/**
		 * TAB选项 和 目录树相关数据
		 */

		Account account = (Account) ActionContext.getContext().getSession().get("account");
		long owner = account.getOwner().getId();

		List<OwnerContentType> typeList = contentManager.findContentType(owner);
		dto.setTypeList(typeList);
		if (ccid == 0 && typeid == 0 && typeList.size() > 0) {
			typeid = typeList.get(0).getId();
		}

		ContentCategory ct = new ContentCategory();
		if (ccid <= 0) {
			boolean isdefalut = ccid == 0 ? true : false;// 当前台的ccid没传时 默认选中一个
															// 当ccid<0时就不需要默认选中
			if (isdefalut) {
				ccid = contentManager.findDefaultCcid(typeid, owner);
			}

			if (!isdefalut || ct == null) {
				ct = new ContentCategory();
				ct.setTypeid(typeid);
				ct.setType(contentManager.findContactCategoryType(typeid, owner));
			}
		}
		if (ccid > 0) {
			ct = contentManager.findContentCategoryById(ccid);
		}
		dto.setCurrent(ct);
		String type = ct.getType();
		typeid = ct.getTypeid();
		ccid = ct.getId();
		if (needTree) {
			dto.setTree(new Gson().toJson(contentManager.findTreeByType(type, ccid, owner, typeid)));
		}
		return dto;

	}

	@Override
	public int updateContentFormSub(Account account, ContentForm contentform, List<ContentFormMapping> newList) {
		return contentFormMgr.updateContentFormSub(account.getOwner().getId(), contentform, newList);
	}

	@Override
	public IDto addContentFormIndex(Account account, long formid) {
		ContentFormDto dto = new ContentFormDto();
		ContentForm cf = contentFormMgr.findContentFormById(formid);
		cf.setShowList(contentFormMgr.findMappingById(cf.getId()));
		dto.setContentform(cf);
		long ccid = cf.getCatid();
		dto = getBaseDto(ccid, 0, dto, false);
		return dto;
	}

	@Override
	public int addContentFormSub(ContentFormRecord record, Account account) {
		return contentFormMgr.addContentFormSub(record,account);
	}

	@Override
	public IDto findcssBgByCardid(long cardid) {
		PageCard pc = templateManager.findcssBgByCardid(cardid);
		CardDto dto = new CardDto();
		dto.setCardcss(pc.getCss());
		return dto;
	}

	@Override
	public int deletFormRecord(Account account, long formid, long recordid) {
		ContentForm cf = contentFormMgr.findContentFormById(formid);
		if (cf.getOwnerid() == account.getOwner().getId()) {
			return contentFormMgr.deletFormRecord(recordid);
		} else {
			return 0;
		}
	}

	@Override
	public IDto findContentFormRecord(Account account, long formid, long recordid) {

		ContentFormDto dto = new ContentFormDto();
		ContentForm cf = contentFormMgr.findContentFormById(formid);

		if (cf.getOwnerid() == account.getOwner().getId()) {
			List<ContentFormMapping> colum = contentFormMgr.findMappingById(cf.getId());
			ContentFormRecord cfr = contentFormMgr.findRecordById(recordid);
			if (cfr != null) {
				for (ContentFormMapping mapping : colum) {
					String value = cfr.getMapingValue(mapping.getMapping());
					if ("P".equals(mapping.getStype()) && StringUtils.isNotEmpty(value)) {
						JSONObject jo = JSONObject.fromObject(value);
						mapping.setRecord(jo.getString("name"));
						;
					} else {
						mapping.setRecord(value);
					}
				}
			}
			dto.setColum(colum);
		}
		long ccid = cf.getCatid();
		dto = getBaseDto(ccid, 0, dto, false);
		return dto;
	}

	@Override
	public IDto composeDataIndexAction(Account account) {
		NewDataDto dto = new NewDataDto();
		solrServerMgr.getOwnerWxVisitData(account.getOwner().getId());
		return dto;
	}

	@Override
	public void beforeHudong(long owner) {
		VisitUser visit = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		if (visit != null && visit.getCookieUser() != null && visit.getCookieUser().getCookie() != null && visit.getCookieUser().getId() == 0) {
			long id = hyUserMgr.updateCookieUserByCookie(visit.getCookieUser().getCookie(), owner);
			visit.getCookieUser().setId(id);
			visit.getCookieUser().setOwner(owner);
		}

	}

	public void setHyUserMgr(IHyUserMgr hyUserMgr) {
		this.hyUserMgr = hyUserMgr;
	}

	@Override
	public List<Page> findOnlinePageByOwnerid(long ownerid, String start, String end) {
		return pageManager.findOnlinePageByOwnerid(ownerid, start, end);
	}

	@Override
	public Page findPageByPageid(long pageid) {
		return pageManager.findPageByPageid(pageid);
	}

	@Override
	public WxUser findWxUserByOpenid(String openid) {
		return weiXinMgr.findWxUserByOpenid(openid);
	}

	@Override
	public IDto composeProvince() {
		AreaDto dto = new AreaDto();
		dto.setProvince(areaMgr.findAreaPorvince());
		return dto;
	}

	public void setAreaMgr(IAreaManager areaMgr) {
		this.areaMgr = areaMgr;
	}

	@Override
	public IDto composeCity(String provinceId) {
		AreaDto dto = new AreaDto();
		dto.setCity(areaMgr.findAreaCity(provinceId));
		return dto;
	}

	@Override
	public IDto composeEditPg(long pageid) {
		EditPGDto dto = new EditPGDto();
		Page page = pageManager.findPageById(pageid);
		String param = pageManager.findPageParams(page.getJspname());
		if (param != null) {
			dto.setParam(JSONObject.fromObject(param));
		}
		if (page.getParamValue() != null) {
			dto.setValue(JSONObject.fromObject(page.getParamValue()));
		}
		dto.setPages(pageManager.findSitePagesByOnePageOfSite(pageid));
		return dto;
	}

	@Override
	public int composeSavePg(long pageid, String json) {
		return pageManager.updatePageParams(pageid, json);
	}

	@Override
	public IDto findForumByOwnerid(long ownerid, int pageId, long accid) {
		CommunityDto dto = new CommunityDto();
		List<BBSCategory> bbsCatergoryList = bbsMgr.findForumByOwnerid(ownerid);
		if (bbsCatergoryList != null) {
			int total = bbsMgr.findTotlaForumByOwnerid(ownerid, accid);
			if (total > 0) {
				// BBSCategory cat = bbsMgr.findBBSCategoryByOwnerid(ownerid);
				// if(cat != null){
				// dto.setCateid(cat.getId());
				// }
				int start = (pageId - 1) * IInteractConstants.INTERACT_BBS_LIMIT;
				List<BBSForum> bbsForumList = bbsMgr.findForumListByOwnerid(ownerid, accid, start, IInteractConstants.INTERACT_BBS_LIMIT);
				if (bbsForumList != null && bbsForumList.size() > 0) {
					dto.setBbsForumList(bbsForumList);
				}
			}
			dto.setBbsCatergoryList(bbsCatergoryList);
			Pager pager = new Pager(pageId, total, IInteractConstants.INTERACT_BBS_LIMIT);
			dto.setPager(pager);
		}
		return dto;
	}

	@Override
	public long composeUploadPageHtml(long siteid, String pagename, String jspname) {
		Site s = siteManager.findSiteById(siteid);
		return pageManager.saveUploadPageHtml(siteid, pagename, jspname, s.getType(), null, null);
	}

	@Override
	public String saveBBSCategoryByOwner(long ownerid) {
		return bbsMgr.saveBBSCategoryByOwner(ownerid);
	}

	@Override
	public IDto saveForum(List<Integer> catList, long accid, long ownerid) {
		CommunityDto dto = new CommunityDto();
		for (int i = 0; i < catList.size(); i++) {
			BBSCategory cat = bbsMgr.findCatNameByCatid(catList.get(i));
			if (cat != null) {
				String title = cat.getName();
				bbsMgr.saveBBSForum(title, catList.get(i), accid, ownerid);
			}
		}
		return dto;
	}

	@Override
	public String saveForumNew(String title, String cateType, long ownerid, long accid) {
		long cateid = bbsMgr.findBBSCateByTypeName(getTypeName(cateType), ownerid);
		if (cateid == 0)
		{
			cateid = bbsMgr.saveBBSCate(getTypeName(cateType), ownerid);
		}
		int total = bbsMgr.findForumByOwneridAndTitle(title, cateid, ownerid);
		if (total > 0) {
			return "N";
		} else {
			bbsMgr.saveBBSForum(title, cateid, accid, ownerid);
			return "Y";
		}
	}
	
	private String getTypeName(String type)
	{
		if ("T".equals(type))
		{
			return "产品";
		}
		else if ("P".equals(type))
		{
			return "图片";
		}
		else if ("V".equals(type))
		{
			return "视频";
		}
		else if ("N".equals(type))
		{
			return "新闻";
		}
		else if("L".equals(type))
		{
			return "论坛";
		}else{
			return "";
		}
	}

	@Override
	public int saveNormalForum(BBSForum forum) {
		return (int) bbsMgr.saveBBSForum(forum.getTitle(), forum.getCateid());
	}

	@Override
	public int composeSavePageParamAndValue(long pageid, String jspname, String param, String value) {
		pageManager.updatePageParams(pageid, value);
		return pageManager.savePageParam(jspname, param);
	}

	@Override
	public int updateForumName(BBSForum forum, long ownerid, long cateid) {
		int total = bbsMgr.findForumByOwneridAndTitle(forum.getTitle(), cateid, ownerid);
		if (total > 0) {
			return 0;
		} else {
			return bbsMgr.updateForumName(forum);
		}
	}

	@Override
	public IDto findForumByOwnerid(long ownerid) {
		CommunityDto dto = new CommunityDto();
		List<BBSCategory> bbsCatergoryList = bbsMgr.findBBSCategoryByOwnerid(ownerid);
		if (bbsCatergoryList != null && bbsCatergoryList.size() > 0) {
			dto.setBbsCatergoryList(bbsCatergoryList);
		}
		return dto;
	}

	@Override
	public int updateBBSForumByForumid(BBSForum forum, String ip, long owner) {
		return bbsMgr.updateBBSForumByForumid(forum, ip, owner);
	}

	@Override
	public int updateBBSForumPermissionByForumid(BBSForum forum) {
		return bbsMgr.updateBBSForumPermissionByForumid(forum);
	}

	@Override
	public IDto findBBSForumById(long id) {
		CommunityDto dto = new CommunityDto();
		BBSForum bbsForum = bbsMgr.findBBSForumById(id);
		dto.setBbsForum(bbsForum);
		return dto;
	}

	@Override
	public IDto findBBSForumByIdAndOwnerid(long id, long ownerid) {
		CommunityDto dto = new CommunityDto();
		BBSForum bbsForum = bbsMgr.findBBSForumById(id);
		List<Page> pageList = bbsMgr.findPageidByOwnerid(ownerid);
		dto.setBbsForum(bbsForum);
		dto.setPageList(pageList);
		return dto;
	}

	@Override
	public int updateSiteIsWhole(long siteid) {
		return siteManager.updateSiteIsWhole(siteid);
	}

	@Override
	public int updateNamebypageid(long pageid, String name) {
		return siteManager.updateNamebypageid(pageid, name);
	}

	@Override
	public JSONObject findJspname(long siteid) {
		return pageManager.findJspname(siteid);
	}

	@Override
	public HyUser findHyUserBywxuid(long wxuid,long owner) {
		return hyUserMgr.findHyUserBywxuid(wxuid, owner);
	}

	@Override
	public IDto findZujian() {
		ZujianDto dto = new ZujianDto();
		dto.setZjList(templateManager.findZujian());
		return dto;
	}

	@Override
	public String addZujian(long pcid, long bid) {
		return templateManager.addZujian(pcid, bid);
	}

	@Override
	public int changeEvent(long pcid, String eventName) {
		return templateManager.updateEvent(pcid, eventName);
	}

	@Override
	public int editContentFormSub(ContentFormRecord record, Account account) {
		return contentFormMgr.updateFormRecord(record,account);
	}

	@Override
	public int delforum(long forumid) {
		return bbsMgr.delforum(forumid);
	}

	@Override
	public int updateOwnerInteractTitle(long lid, long owner, String title) {
		return interactModelManager.updateOwnerInteractTitle(lid, owner, title);
	}

	@Override
	public int updateOwnerInteractStatus(long lid, long owner, String status) {
		return interactModelManager.updateOwnerInteractStatus(lid, owner, status);
	}

	@Override
	public List<Map<String, String>> findRecordByPoint(String x, String y, String tags, int pagesize) {
		return contentFormMgr.findRecordByPoint(x, y, tags, pagesize);
	}
	
	@Override
	public Map<String, String> findRecordId(long recordid)
	{
		return contentFormMgr.findRecordId(recordid);
	}

	@Override
	public long copyCard(long pcid){
		return pageManager.addCopyCard(pcid);
	}

	@Override
	public IDto findCbActivityListByCbid(long owner,int pageId)
	{
		MarketActivityDto dto = new MarketActivityDto();
		int total = marketActivityMgr.findTotalCbActivityByCbid(owner);
		if(total > 0){
			int start = (pageId - 1) * IInteractConstants.INTERACT_CB_ACTIVITY;
			List<CbActivity> cbActivityList = marketActivityMgr.findCbActivityListByCbid(owner,start,IInteractConstants.INTERACT_CB_ACTIVITY);
			dto.setCbActivityList(cbActivityList);
		}
		Pager pager = new Pager(pageId, total, IInteractConstants.INTERACT_CB_ACTIVITY);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public IDto findWxPageShowListByAid(long cbid,long aid, int pageId)
	{
		PageShowMaterialDto dto = new PageShowMaterialDto();
		int total = pageShowMaterialManager.findTotalWxPageShowByAid(cbid,aid);
		if(total > 0){
			int start = (pageId - 1) * IInteractConstants.INTERACT_CB_ACTIVITY;
			List<CbActivityMatter> wxPageShowList = pageShowMaterialManager.findWxPageShowListByAid(cbid,aid,start,IInteractConstants.INTERACT_CB_ACTIVITY);
			if(wxPageShowList != null && wxPageShowList.size()>0){
				for (CbActivityMatter cbActivityMatter : wxPageShowList)
				{
					if(cbActivityMatter.getWxshareid()>0){
						WxPageShow s=activityManager.findWxPageShowById(cbActivityMatter.getWxshareid(),aid);
						cbActivityMatter.setTitle(s.getTitle());
						cbActivityMatter.setPic(s.getPic());
						cbActivityMatter.setDescription(s.getDescription());
					}else if(cbActivityMatter.getKv()!=null){
						String kv=cbActivityMatter.getKv();
						Pattern p=Pattern.compile("cn-hy-\\d+");
						Matcher m=p.matcher(kv);
						if(m.matches()){
							String nid=kv.replace("cn-hy-", "");
							Account account = (Account) ActionContext.getContext().getSession().get("account");
							ContentNew cn=contentManager.findNewById(Long.parseLong(nid),account.getOwner().getId());
							cbActivityMatter.setTitle(cn.getTitle());
							cbActivityMatter.setPic(cn.getSimgurl());
							cbActivityMatter.setDescription(cn.getShortdesc());
						}
						
					}
				}
				dto.setMatterList(wxPageShowList);
			}
		}
		Pager pager = new Pager(pageId, total, IInteractConstants.INTERACT_CB_ACTIVITY);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public IDto findPageShowMaterialByOwnerid(long ownerid, int pageId,String title)
	{
		int rows = IInteractConstants.INTERACT_CB_ACTIVITY;
		int start = (pageId - 1) * rows;
		int total = pageShowMaterialManager.findTotalWxPageShowByOwnerid(ownerid,title);
		List<WxPageShow> wxPageShowList = pageShowMaterialManager.findPageShowMaterialByOwnerid(ownerid,title,start,rows);
		
		PageShowMaterialDto dto = new PageShowMaterialDto();
		dto.setWxPageShowList(wxPageShowList);
		dto.setPager(new Pager(pageId, total, rows));
		return dto;
	}

	@Override
	public int updatePageShowActioned(long id, String actioned)
	{
		return pageShowMaterialManager.updatePageShowActioned(id, actioned);
	}

	@Override
	public List<Page> findPagesByName(long ownerid, String name, int start, int rows) {
		return pageManager.findPagesByName(ownerid, name, start, rows);
	}

	@Override
	public IDto findContentRecordList(int pageId,String type,int utype,long uid,String title)
	{
		ContentRecordDto dto = new ContentRecordDto();
		int total = contentRecordMgr.findTotalContentReport(type,utype,uid,title);
		if(total > 0){
			int start = (pageId - 1) * IInteractConstants.INTERACT_RECORD;
			List<ContentRecord> list = contentRecordMgr.findContentRecordList(type,utype,uid,title,start,IInteractConstants.INTERACT_RECORD);
			if(list != null && list.size()>0){
				dto.setContentRecordList(list);
			}
		}
		Pager pager = new Pager(pageId, total, IInteractConstants.INTERACT_RECORD);
		dto.setPager(pager);
		return dto;
	}

	@Override
	public int saveBread(long catid, String json)
	{
		return contentManager.saveBread(catid, json);
	}
	
	@Override
	public String findBread(long catid)
	{
		return contentManager.findBread(catid);
	}

	@Override
	public IDto findMbList(String type, long tagid)
	{
		MbDto dto = new MbDto();
		List<Tag> tagList = mbMgr.findTagList(type);
		if(tagList != null && tagList.size() > 0){
			List<Mb> mbList = mbMgr.findMbList(type,tagid);
			int total = mbMgr.findTotalMb(type);
			dto.setMbList(mbList);
			dto.setTagList(tagList);
			dto.setTagid(tagid);
			dto.setTotal(total);
		}
		return dto;
	}

	@Override
	public List<Page> findPagesBySiteid(long siteid)
	{
		return pageManager.findPageBySiteid(siteid);
	}

	@Override
	public int saveMb(Mb mb)
	{
		return mbMgr.saveMb(mb);
	}

	@Override
	public List<Sitegroup> findSitegroupList(long ownerid)
	{
		return siteManager.findSitegroupByOwner(ownerid);
	}

	@Override
	public long saveUseMb(Owner owner, String type, long mbid, String name, long groupid, String stype)
	{
		return featureManager.saveUseMb(owner, type, mbid, name, groupid ,stype);
	}
	
	@Override
	public int saveSiteSource(long siteid, long owner, SiteSource sitesource)
	{
		return siteSourceManager.saveSiteSource(siteid, owner, sitesource);
	}

	@Override
	public List<SiteSource> findSiteSourceByOwner(Owner owner,long pageid)
	{
		if("Y".equals(owner.getSup())){
			Page p = pageManager.findPageById(pageid);
			return siteSourceManager.findSiteSourceByOwnerid(owner.getId(),p.getSiteid(),pageid);			
		}else{
			return siteSourceManager.findSiteSourceByPageid(owner.getId());
		}
	}

	@Override
	public void updatePageSource(long pageid, List<Long> sources)
	{
		siteSourceManager.updatePageSource(pageid, sources);
	}

	@Override
	public List<String> findPageSourceByPageid(long pageid,boolean isRun,int level)
	{
		/*
			if(isRun){
				//只在卡片编辑的时候走
				return siteSourceManager.findPageSourceByPageidInCardAndPage(pageid);
			}
			return siteSourceManager.findPageSourceByPageidInCardOrPage(pageid,type);
		 */
		if(isRun){
			//卡片编辑的时候 卡片级和页面级 都显示
			return siteSourceManager.findPageSourceByPageidInEdit(pageid);
		}
		//level 搜索对应级别资源
		return siteSourceManager.findPageSourceByPageidInShow(pageid, level);
	}

	@Override
	public List<PageSource> findPageSourceListByPageid(long pageid)
	{
		return siteSourceManager.findPageSourceListByPageid(pageid);
	}

	@Override
	public SourceDto findPageSourceById(long psid,long pageid)
	{
		SourceDto dto = new SourceDto();
		dto.setPages(pageManager.findSitePagesByOnePageOfSite(pageid));
		PageSource ps =  siteSourceManager.findPageSourceById(psid);
//		dto.setJson(ps.getSource().getJson());
//		dto.setVjson(ps.getVjson());
//		dto.setTop(ps.getTop());
//		dto.setLeft(ps.getLeft());
//		dto.setRight(ps.getRight());
//		dto.setBottom(ps.getBottom());
		return dto;
	}

	@Override
	public int savePageSourceEdit(long psid, String json)
	{
		return siteSourceManager.savePageSourceEdit(psid, json);
	}
	
	@Override
	public int savePageSourceEditC(long psid, String json,String top,String left,String right,String bottom)
	{
		return siteSourceManager.savePageSourceEditC(psid, json,top,left, right, bottom);
	}
	
	@Override
	public Map<String, SiteSource> findSiteSource(long siteid)
	{
		List<SiteSource> list=siteSourceManager.findSiteSource(siteid);
		if(list!=null){
			Map<String, SiteSource> map=new HashMap<String, SiteSource>();
			for (SiteSource siteSource : list)
			{
				siteSource.setJson(StringEscapeUtils.escapeHtml4(siteSource.getJson()));
				siteSource.setVjson(StringEscapeUtils.escapeHtml4(siteSource.getVjson()));
				map.put(siteSource.getId()+"", siteSource);
			}
			return map;
		}
		return null;
	}
	
	@Override
	public int updateSiteSource(long siteid, long owner, SiteSource sitesource)
	{
		return siteSourceManager.updateSiteSource(siteid, owner, sitesource);
	}

	@Override
	public SiteSource findSiteSourceById(long sourceid)
	{
		return siteSourceManager.findSiteSourceById(sourceid);
	}

	@Override
	public int findSourcePage(long sourceid)
	{
		return siteSourceManager.findSourcePage(sourceid);
	}

	@Override
	public int delSiteSource(long sourceid)
	{
		return siteSourceManager.delSiteSource(sourceid);
	}

	@Override
	public Sitegroup findSitegroupByPageid(long pageid)
	{
		return siteManager.findSitegroupByPageid(pageid);
	}

	@Override
	public HyUser saveHyUserByWxuid(long wxuid, long owner)
	{
		return hyUserMgr.saveHyUserByWxuid(wxuid, owner);
	}

	@Override
	public int savePageHeight(long pageid, String height)
	{
		String str = pageManager.findPageBg(pageid);
		JSONObject jo;
		try{
			jo = JSONObject.fromObject(str);
		} catch (Exception e){
			jo=new JSONObject();
		}
		jo.element("pageheight", height);
		return pageManager.savePageBg(pageid, jo.toString());
	}


	@Override
	public WeiXinPage findWXPage(long id,long owner) 
	{
		WeiXinPage wp= weiXinMgr.findWXPage(id);
		if(wp!=null)
		{
		wp.setWxMp(weiXinMgr.updateFindWxMp(owner, wp.getMpid()));
		}
		return wp;
	}

	
	@Override
	public WeiXinPage findWXPageByPageid(long pageid,long owner)
	{
		WeiXinPage wp= weiXinMgr.findWXPageByPageid(pageid,owner);
		wp.setWxMp(weiXinMgr.updateFindWxMp(owner, 0));
		return wp;
	}
	
	@Override
	public IDto findXcSiteBygroupid(long sitegroupid, long xcid)
	{
		if(sitegroupid==0){
			sitegroupid=siteManager.findSiteGroupByXcid(xcid);
		}else if(xcid==0){
			xcid=siteManager.findxcidBysitegroup(sitegroupid);
		}
		XcSiteDto dto=new XcSiteDto();
		List<Site> list=siteManager.findSiteIdBySiteGroupId(sitegroupid);
		Map<Long, WxPageShow> map=new HashMap<Long, WxPageShow>();
		for (Site site : list)
		{
			WxPageShow wx=weiXinMgr.findWxPageShowBySiteId(site.getId());
			map.put(site.getId(), wx);
		}
		dto.setWps(map);
		dto.setList(list);
		XcInfo xc=xcMgr.findXcInfoByXcid(xcid);
		dto.setXc(xc);
		dto.setSg(siteManager.findSitegroupByid(sitegroupid));
		return dto;
	}
	
	@Override
	public DaohangDto composeDhByXcid(long xcid)
	{
		DaohangDto dto=new DaohangDto();
		long sitegroupid=siteManager.findSiteGroupByXcid(xcid);
		dto.setSitegroup(siteManager.findSitegroupByid(sitegroupid));
		return dto;
	}

	@Override
	public int updateOnlineAll(long siteid)
	{
		List<Long> pages = pageManager.findPageIdBySiteid(siteid);
		int count = 0 ;
		for(Long p : pages){
			count += updateOnline(p);
		}
		return count;
	}
	
	@Override
	public int updateOnlineByGroup(long sitegroupid,String groupType)
	{
		int count = 0;
		List<Site> list = siteManager.findSiteBySiteGroupId(sitegroupid);
		for (Site site : list)
		{
			int rs = updateOnlineAll(site.getId());
			count = count + rs;
		}
		return 0;
	}

	@Override
	public int updateOfflineAll(long siteid)
	{
		List<Long> pages = pageManager.findPageIdBySiteid(siteid);
		int count = 0 ;
		for(Long p : pages){
			count += updateOffline(p);
		}
		return count;
	}

	@Override
	public int updateDataAll(long siteid)
	{
		List<Long> pages = pageManager.findPageIdBySiteid(siteid);
		int count = 0 ;
		for(Long p : pages){
			count += updateData(p);
		}
		return count;
	}

	@Override
	public Site findSiteWithGrpById(long siteid)
	{
		return siteManager.findSiteWithGrpById(siteid);
	}
	
	@Override
	public IDto findNewsMaterial(long ownerid, long ccid, int pageId)
	{
		PageShowMaterialDto dto=new PageShowMaterialDto();
		List<ContentCategory> list=activityManager.findContentCategory(ownerid);
		dto.setCc(list);
		if(ccid>0){
			int total=contentManager.findNewsTotal(ccid, ownerid, null);
			if(total>0){
				List<ContentNew> nlist=contentManager.findNewsByCcid(ccid, ownerid, (pageId - 1) * IPageCompose.CONTENT_LIMIT, IPageConstants.CONTENT_LIMIT, IPageConstants.STATUS_CMP);
				dto.setNews(nlist);
				dto.setPager(new Pager(pageId,total,IPageCompose.CONTENT_LIMIT));
			}
		}
		return dto;
	}

	@Override
	public int updateToPage(long ccid)
	{
		ContentCategory cc = contentManager.findContentCategoryById(ccid);
		List<Long> pages = pageManager.findPagesByCcid(ccid);
		if("N".equals(cc.getType())){
			
		}else if("P".equals(cc.getType())){
			
		}else if("T".equals(cc.getType())){
					
		}else if("V".equals(cc.getType())){
			
		}
		for(Long p : pages){
			updateData(p);
		}
		return pages.size();
	}
	
	
	@Override
	public String findInteractRecord(VisitUser vu,long hdid)
	{
		String kv=vu.getKv();
		if(kv!=null){
			long recordid=0;
			Pattern pattern=Pattern.compile("\\d+");
			Matcher m=pattern.matcher(kv);
			if(m.find()){
				recordid=Long.parseLong(m.group());
			}
			if(recordid>0){
				//kv带id
				if(kv.startsWith(IPageConstants.KV_APT)){
					AppointmentRecord record=hdRecordManager.findAptRecord(recordid);
					return new Gson().toJson(record);
				}else if (kv.startsWith(IPageConstants.KV_VOTE)){
					// TODO 
				}
			}else{
				//kv不带id
				if(kv.startsWith(IPageConstants.KV_APT)){
					AppointmentRecord record=hdRecordManager.findAptRecord(hdid,vu.getUid(),vu.getCd());
					return new Gson().toJson(record);
				}else if (kv.startsWith(IPageConstants.KV_VOTE)){
					// TODO 
				}
			}
		}
		return null;
	}
	
	@Override
	public UserPkvTag findContentTag(long ccid, long pid)
	{
		return userTagMgr.findContentTag(ccid, pid);
	}
	@Override
	public UserPkvTag findPageTag(long pageid)
	{
		return userTagMgr.findPageTag(pageid);
	}

	@Override
	public List<UserTag> findOwnerUserTag(long id)
	{
		return userTagMgr.findOwnerTag(id);
	}

	@Override
	public IDto chooseContent(Account account)
	{
		ChooseContentDto dto = new ChooseContentDto();
		List<ContentCategory> list = contentManager.findBangDingCategory(account.getOwner().getId());
		JSONArray ja=new JSONArray();
		JSONArray ja1=new JSONArray();
		JSONArray ja2=new JSONArray();
		JSONArray ja3=new JSONArray();
		JSONArray ja4=new JSONArray();
		for (ContentCategory contentCategory : list)
		{
			JSONObject jo=new JSONObject();
			jo.put("id", contentCategory.getId());
			jo.put("name", contentCategory.getName());
			jo.put("pId", contentCategory.getFartherCatId());
			jo.put("pageid", contentCategory.getPageid());
			if("T".equals(contentCategory.getType())){
				ja.add(jo);
			}else if("P".equals(contentCategory.getType())){
				ja1.add(jo);
			}else if("V".equals(contentCategory.getType())){
				ja2.add(jo);
			}else if("N".equals(contentCategory.getType())){
				ja3.add(jo);
			}else if("F".equals(contentCategory.getType())){
				ja4.add(jo);
			}
		}
		dto.setJson(ja.toString());
		dto.setJson1(ja1.toString());
		dto.setJson2(ja2.toString());
		dto.setJson3(ja3.toString());
		dto.setJson4(ja4.toString());
		
		
		/**
		 * 微商城
		 */
		List<ContentCategory> list2 = contentManager.findBangDingShopCategory(account.getOwner().getId(),IPageConstants.PRODUCT_WSC);
		JSONArray ja5=new JSONArray();
		for (ContentCategory contentCategory : list2)
		{
			JSONObject jo=new JSONObject();
			jo.put("id", contentCategory.getId());
			jo.put("name", contentCategory.getName());
			jo.put("pId", contentCategory.getFartherCatId());
			jo.put("pageid", contentCategory.getPageid());
			ja5.add(jo);
		}
		dto.setJson5(ja5.toString());
		
		/**
		 * 积分商城
		 */
		List<ContentCategory> list3 = contentManager.findBangDingShopCategory(account.getOwner().getId(),IPageConstants.PRODUCT_JF);
		JSONArray ja6=new JSONArray();
		for (ContentCategory contentCategory : list3)
		{
			JSONObject jo=new JSONObject();
			jo.put("id", contentCategory.getId());
			jo.put("name", contentCategory.getName());
			jo.put("pId", contentCategory.getFartherCatId());
			jo.put("pageid", contentCategory.getPageid());
			ja6.add(jo);
		}
		dto.setJson6(ja6.toString());
		return dto;
	}
	
	public IDto findContentByCcid(long ccid,long owner,int pageId,int size) {
		ContentDto dto = new ContentDto();
		ContentCategory ct = contentManager.findContentCategoryById(ccid);
		String type = ct.getType();
		dto.setCurrent(ct);
		if(StringUtils.isNotBlank(ct.getPassword())){
			VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
			Boolean result = (Boolean) ServletActionContext.getRequest().getSession().getAttribute("check_"+vu.getUid());
			if(result == null ){
				return dto;
			}
		}
		if (!StringUtils.isEmpty(type) && ccid > 0 && size >0) {
			if ("T".equals(type)) {
				dto.setProduct(contentManager.findProductByCcid(ccid, owner, (pageId - 1) * size, size, null));
			} else if ("P".equals(type)) {
				dto.setPictureList(contentManager.findPictureByCcid(ccid, owner, (pageId - 1) * size, size, null));
			} else if ("N".equals(type)) {
				dto.setNewList(contentManager.findNewByCcid(ccid, owner, (pageId - 1) * size, size, null));
			} else if ("V".equals(type)) {
				dto.setVideoList(contentManager.findVideoByCcid(ccid, owner, (pageId - 1) * size, size, null));
			}
		}
		return dto;
	}
	
	@Override
	public IDto composeSiteGroupByType(Account account, int pageId, String grouptype)
	{
		SiteGroupDto dto = new SiteGroupDto();
		int start = (pageId - 1) * IPageConstants.SITE_LIMIT;
		int total = siteManager.findGroupTotalByType(account.getOwner().getId(),grouptype);
		if (total > 0) {
			List<Sitegroup> list = siteManager.findGroupByType(account.getOwner().getId(),grouptype, start, IPageConstants.SITE_LIMIT);
			if (list != null && list.size() > 0) {
				for (Sitegroup s : list) {
					s.setSite(siteManager.findSiteBySiteGroupId(s.getId()));
					s.setWps(weiXinMgr.findWxPageShowBySitegroupId(s.getId()));
				}
			}
			dto.setList(list);
		}
		Pager pager = new Pager(pageId, total, IPageConstants.SITE_LIMIT);
		dto.setPager(pager);
		List<Sitegroup> tlist=siteManager.findAllTempSiteGroup(grouptype);
		if (tlist != null && tlist.size() > 0) {
			for (Sitegroup s : tlist) {
				s.setSite(siteManager.findSiteBySiteGroupId(s.getId()));
				s.setWps(weiXinMgr.findWxPageShowBySitegroupId(s.getId()));
			}
		}
		dto.setTaozhuang(tlist);
		return dto;
	}
	
	@Override
	public List<Sitegroup> findPageTofs(Account account, String grouptype)
	{
		List<Sitegroup> list = new ArrayList<Sitegroup>();
		long owner = account.getOwner().getId();
		list = siteManager.findGroupByType(owner, grouptype);
		for (Sitegroup sitegroup : list)
		{	
			List<Site> list1 = siteManager.findSiteBySiteGroupId(sitegroup.getId());
			for (Site site : list1)
			{
				List<Page> list2 = siteManager.findPageListBySiteId(site.getId());
				site.setPages(list2);
			}
			sitegroup.setSite(list1);
		}
		return list;
	}

	@Override
	public List<Sitegroup> findGroupTofs(Account account, String grouptype)
	{
		List<Sitegroup> list = new ArrayList<Sitegroup>();
		long owner = account.getOwner().getId();
		list = siteManager.findGroupByType(owner, grouptype);
		return list;
	}
	@Override
	public List<ContentCategory> findCategoryByFatherCcid(long catid, long owner)
	{
		return contentManager.findCategoryByFatherCcid(catid,owner);
	}

	@Override
	public int freeOpenApp(long appid, long ownerid)
	{
		OwnerPrivilege op = accountManager.findOwnerPrivilege(ownerid,appid);
		if(op == null){
			int result = accountManager.saveOwnerPrivilege(ownerid, appid, IPrivilegeConstants.OWNER_PERMISSION_TRY, IPrivilegeConstants.PERMISSION_R_and_X);
			List<OwnerPrivilege> list = accountManager.findOwnerPrivilege(ownerid);
			Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
			account.getOwner().setPrivileges(list);
			return result;
		}else{
			return 0;
		}
	}

	@Override
	public IDto findOwnerSiteSourceByOwnerid(long ownerid)
	{
		OwnerSourceDto dto = new OwnerSourceDto();
		dto.setList(siteSourceManager.findOwnerSource(ownerid));
		return dto;
	}

	@Override
	public IDto findSiteSourceVm(String type)
	{
		OwnerSourceDto dto = new OwnerSourceDto();
		dto.setVmList(siteSourceManager.findSiteSourceVm(type));
		return dto;
	}

	@Override
	public int saveOwnerSiteSource(long ownerid, long vmid, String title)
	{
		return siteSourceManager.saveOwnerSiteSource(ownerid, vmid, title);
	}

	@Override
	public IDto editOwnerSiteSource(long id)
	{
		OwnerSourceDto dto = new OwnerSourceDto();
		OwnerSource os=siteSourceManager.findOwnerSourceById(id);
		dto.setOs(os);
		return dto;
	}

	@Override
	public int saveEditOwnerSiteSource(OwnerSource os)
	{
		return siteSourceManager.saveEditOwnerSiteSource(os);
	}

	@Override
	public IDto findPage4Source(long ownerid,long pageid)
	{
		OwnerSourceDto dto = new OwnerSourceDto();
		dto.setList(siteSourceManager.findOwnerSource(ownerid));
		dto.setSource(siteSourceManager.findPage4SourceByPageid(pageid,IPageConstants.SITE_SOURCE_TYPE_DH));
		return dto;
	}

	@Override
	public int savePage4Source(long ownerid, long pageid, long osid)
	{
		return siteSourceManager.savePage4Source(pageid, osid);
	}

	@Override
	public int saveCancelPage4Source(long ownerid, long pageid, String type)
	{
		return siteSourceManager.saveCancelPage4Source(ownerid, pageid, type);
	}

	
	@Override
	public int deleteOwnerSource(long owner, long osid)
	{
		return siteSourceManager.delOwnerSource(owner,osid);
	}
	
	@Override
	public int updateOwnerSourceStyle(long id, Account account, Map<String, String> map)
	{
		if(map==null||map.size()==0){
			return -1;
		}else{
			String json=new JSONObject().fromObject(map).toString();
			return siteSourceManager.updateOwnerSourceStyle(id,account.getOwner().getId(),json);
		}
	}

	@Override
	public Object findAppObjectByAppid(long ownerid, long appid)
	{
		return appManager.findAppObjectByAppid(ownerid, appid);
	}

	@Override
	public long addProductLevel(long productid,String ids, String names)
	{
		return contentManager.addProductLevel(productid,ids,names);
	}
	
	@Override
	public long findAppUsedSiteGroup(long owner,int appid)
	{
		long pageid=appManager.findAppUsedPageid(owner, appid);
		if(pageid>0){
			Sitegroup sg=findSitegroupByPageid(pageid);
			if (sg != null)
				return sg.getId();
		}
		return 0;
	}
	
	@Override
	public int updateUsedSiteGroup(long owner, long groupid, int appid)
	{
		Map<String, Long> map=new HashMap<String, Long>();
		if(appid==2){
			List<Site> list=siteManager.findSiteBySiteGroupId(groupid);
			for (Site site : list)
			{
				List<Page> pages = siteManager.findPageListBySiteId(site.getId());
				for (Page page : pages)
				{
					if (!"NOR".equalsIgnoreCase(page.getApptype()))
					{
						map.put(page.getApptype(), page.getId());
					}
					
				}
			}
		
		}
		if(map.size()>0)
			return appManager.updateUsedSiteGroup(owner,appid,map);
		return 0;
	}

	@Override
	public long updateProductVip(long productid)
	{
		return contentManager.updateProductVip(productid);
	}
	
	@Override
	public Map<String, ContentTab> findProductTabs(long contentId,long owner)
	{
		Map<String, String> map=contentManager.findProductTabs(contentId,owner);
		Map<String, ContentTab> rs=new HashMap<String, ContentTab>();
		for (String key : map.keySet())
		{
			ContentTab t=new Gson().fromJson(map.get(key), ContentTab.class);
			rs.put(key, t);
		}
		return rs;
	}
	
	@Override
	public int updateTabIndex(ContentTab tab)
	{
		return contentManager.updateTabIndex(tab);
	}
}
