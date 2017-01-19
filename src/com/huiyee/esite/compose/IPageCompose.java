package com.huiyee.esite.compose;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.CardDto;
import com.huiyee.esite.dto.DanymicUserDetail;
import com.huiyee.esite.dto.DanymicUserSiftDto;
import com.huiyee.esite.dto.DaohangDto;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.MaterialDto;
import com.huiyee.esite.dto.NewDetailDto;
import com.huiyee.esite.dto.OwnerDataDto;
import com.huiyee.esite.dto.PageDto;
import com.huiyee.esite.dto.PageShowMaterialDto;
import com.huiyee.esite.dto.QueryDto;
import com.huiyee.esite.dto.SiteDataDto;
import com.huiyee.esite.dto.SiteDto;
import com.huiyee.esite.dto.SiteGroupDto;
import com.huiyee.esite.dto.SitePage;
import com.huiyee.esite.dto.SourceDto;
import com.huiyee.esite.dto.TerminalDataDto;
import com.huiyee.esite.dto.VisitUserDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.ContentCategory;
import com.huiyee.esite.model.ContentForm;
import com.huiyee.esite.model.ContentFormMapping;
import com.huiyee.esite.model.ContentFormRecord;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.esite.model.ContentTab;
import com.huiyee.esite.model.ContentVideo;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.InteractModel;
import com.huiyee.esite.model.Mb;
import com.huiyee.esite.model.MyTempalte;
import com.huiyee.esite.model.Owner;
import com.huiyee.esite.model.OwnerContentType;
import com.huiyee.esite.model.OwnerSource;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageAddress;
import com.huiyee.esite.model.PageSource;
import com.huiyee.esite.model.PageTemplate;
import com.huiyee.esite.model.ReportGenderAnalyse;
import com.huiyee.esite.model.SinaChecklistRecord;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.SiteSource;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.model.SuperHdModel;
import com.huiyee.esite.model.TemplateCard;
import com.huiyee.esite.model.UserPkvTag;
import com.huiyee.esite.model.UserTag;
import com.huiyee.esite.model.UserUpload;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WeiXinPage;
import com.huiyee.esite.model.WxUser;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.exam.dto.PingceResultDto;

import net.sf.json.JSONObject;
public interface IPageCompose extends IPageConstants
{

	public IDto composeWbPageIndexAction(Account account, String type, int pageId);
	
	public WeiXinPage findWXPage(long id,long owner);
	
	public WeiXinPage findWXPageByPageid(long pageid,long owner);
	
	public Sitegroup findSitegroupByPageid(long pageid);
	
	public WxUser addWXUser(WxUser wp);
	
	public HyUser findHyUserBywxuid(long wxuid,long owner);
	
	public HyUser saveHyUserByWxuid(long wxuid,long owner);
	
	public void beforeHudong(long owner);
	
	public HdRsDto ineractCanRun(SuperHdModel sh);
	//互动动作结束
	public void canRunLog(long aoid,String type,HttpServletRequest httpRequest);

	public List<ContentCategory> findNextCategory(long ccid, Account account);

	public IDto findContentByCategoryId(Account account, long ccid, long typeid, int pageId, String entityName);

	public IDto composeCreateSite();

	public IDto composeCreateSiteSub(Account account, String type, String sitename, List<Long> modules, long groupid,long appid);

	public IDto composeUpdateSite(long siteid);

	public IDto composeUpdateSiteSub(Account account, long siteid, String sitename, List<Long> modules);

	public int composeDelSite(long siteid);

	public IDto composePageAcion(long groupid,String type,long siteid);

	public IDto composeUpdatePage(long pageid,long ownerid);

	public int composeUpdatePageSub(String pagename, String jspname, long pageid,String istemplate,String ownertempid);

	public int composeDelPage(long pageid);

	public IDto addContentPre(Account account, String contentProducy,long ccid);

	public long saveProduct(ContentProduct product, JSONObject tagJson);
	
	public long addProductLevel(long productid,String ids, String names);
	
	public long updateProductVip(long productid);
	
	public IDto composeFeatureAction(long pageid);

	public IDto findProductById(Account account, long contentId);

	public int editProduct(ContentProduct product, long l, JSONObject tagJson);

	public int deleteProduct(long contentId, long l);

	public int checkProduct(long contentId, String status, long owner);

	public int publicProduct(long contentId, long l);
	
	public int offProduct(long contentId, long l);

	public long savePicture(ContentPicture picture, JSONObject tagJson);

	public IDto findPictureById(long contentId, Account account);

	public int editPicture(ContentPicture picture, JSONObject tagJson);

	public int delPicture(long contentId, long id);

	public int checkPicture(long contentId, String status, Account account);

	public int publicPicture(long contentId, long id);
	
	public int offPicture(long contentId, long id);

	public long saveNew(ContentNew cn, JSONObject tagJson);

	public IDto findNewById(long contentId, Account account);

	public int editNew(ContentNew cn, JSONObject tagJson);

	public int checkNew(long contentId, String status, Account account);

	public int delNew(long contentId, long id);

	public int publicNew(long contentId, long id);
	
	public int offNew(long contentId, long id);

	public long saveVideo(ContentVideo video, JSONObject tagJson);

	public IDto findVideoById(long contentId, Account account);

	public int editVideo(ContentVideo video, JSONObject tagJson);

	public int checkVideo(long contentId, String status, Account account);

	public int delVideo(long contentId, long id);

	public int publicVideo(long contentId, long id);
	
	public int offVideo(long contentId, long id);

	public long delCategory(long ccid);

	public int addCategory(long owner, ContentCategory cc);

	public long findPageIdForPageShow(long cid, long appid);
	
	public String findOauthLink(String state,long appid);
	
	public String findUserOauthLink(String state,long appid,long shareid);
	
	public long findAppidBySiteid(long siteid);
	
	public long findSiteidByPageid(long pageid);
	
	public String OauthByCode(long appid,String code,long site);
	
	public IDto userOauthByCode(long appid,String code,long site,String ip,String terminal,long shareid,long pageid,String source);

	public long updateUidByViewer(long viewer, long siteid);
	
	public long updateUidByViewer(long viewer, long siteid,String token,long endtsecond,String userinfo);

	public void insertVisitLogAnonymous(long siteid, long pageid,String ip,String agent,String key);

	public void insertVisitLog(long siteid,long pageid, VisitUser vu, String ip,String agent,String key);

	public List<ContentProduct> findOwnerProduct(long id);

	public Page findHomePageBySiteid(long siteid);
	
	public Page findPageByid(long pageid);
	
	public String findPagePic(long pageid,long wxapageid);

	/**
	 * site统计
	 * 
	 * @param siteid
	 * @return
	 */
	public IDto composeSiteGroupCount(long siteid);

	public IDto composeVistLogList(long siteid, String type, int page);
	
	public int composeSetHome(long siteid,long pageid);
	
	public int composeCreateSitegroup(String name,Account account,String groupType);
	
	/**
	 * 页面浏览
	 * @param pageid
	 * @return
	 */
	public Map<String,Object> show(long pageid);
	
	public IDto composeInteraction(long sitegroupid);
	
	public IDto composeInteractionDetail(long features,long sitegroupid, QueryDto siftDto);
	
	public IDto composeUnifyLogin(long ownerId, long accountId, String ip);
	
	public IDto composeLoadPageIndex(Account account, String type);
	
	public IDto composeCreateLoadPageSiteSub(Account account, String type, String sitename, List<Long> modules, long appid);
	
	public IDto composeUpdateLoadSite(long siteid);
	
	public IDto composeUpdateLoadPageSiteSub(Account account, long siteid, String sitename, List<Long> modules);
	
	public int addSite(String name, long ownerid,String type,long appid);
	
	public IDto composeLoadPage(long siteid, String type);
	
	public IDto composeLoadPageInteraction(long siteid);
	
	public IDto composeLoadPageInteractionDetail(long features, long siteid) ;
	
	public IDto composeSiteCount(long siteid);
	
	public IDto composeLoadVistLogList(long siteid, String type, int page) ;
	
	public UserUpload composeUserUpload(long fid);
	
	/**
	 * 
	 * @return
	 */
	public IDto findZanTotal(String[] pfid);

	public List<ContentNew> findNewsByOwner(long id);
	
	public int updateName(long pfid, String name) ;
	
	public long findOwnerByPageid(long pageid);
	
	public IDto composeSiteGroupList(Account account,int pageId,String type);
	
	public IDto sitegroupListLink(long sitegroupid);
	
	public Sitegroup sitegroupListbyId(long pgid);
	
	public IDto composeSiteAcion(long groupid,Account account, String type);
	
	public IDto composeFeatureBySiteid(long siteid);
	
	public int updateSiteName(String name, long sitegroupid);
	
	public int updatePageName(String name, long pageid);
	
	public List<String> findAtUsers(long wbuid,long pageid,String keywords);
	
	public IDto composeSites(Account account, String type, int pageId);
	
	public int composeSinaShare(long uid,long pageid,long shareid,String content,String bimg,String mimg,String simg,String terminal,String source,String ip);
	
	public int composeSinaForward(long wbid,long uid,long pageid,long shareid,String content,String terminal,String source,String ip,String gz,String gzwbuid);
	
	public int composeSinaComment(long wbid,long uid,long pageid,long shareid,String content,String terminal,String source,String ip,String gz);
	
	public int composeCheckToken(long uid,long siteid);
	
	public List<SinaChecklistRecord> findSinaShareRecord(long shareid,long fid);

	public String updateHandler(long featureid, long sitegroupid, long owner);
	
	public int findSinaShareTotal(long shareid);

	public List<String> exportSiteCountDetail(long sitegroupid, String type, long id);
	
	public IDto findSiteNames(long groupid);
	
	public String composeCreateActivitySub(String name,List<Long> modules,long siteid,long ownerid);
	
	public IDto composeCreateActivitySub();
	
	public IDto composeFindActivityList(long siteid);
	
	public IDto composeUpdateActivity(long activityid);
	
	public String composeCompileActivity(long activityid,ArrayList<Long> moduleList,String activityname);
	
	public String composeDelActivity(long activityid);
	//页面配置
	public IDto composePageconfig(long sitegroupid,Account account);
	
	public IDto composePageconfigNew(long siteid,Account account);
	
	public int deleteSiteGroup(long sgid);
	
	public int updateSiteGroup(String name, long sgid);
	
	public IDto visitViewAllData(long sgid,int datenum) ;

	public IDto showAnalysisReport(long sgid,int pageId,long ownerid);
	
	public IDto showVisitHighChartReport(long sgid);
	
	public IDto showVisiterReport(long sgid,int pageId,long ownerid);
	
	public IDto showDynamicHighChartReport(long sgid);
	
	
	public Site findSiteById(long id);
	
	public ReportGenderAnalyse visitGenderData(long sgid);
	
	public IDto findDanymicUserRecord(long sgid,Account account, DanymicUserSiftDto siftDto,int page);
	
	/**
	 * 查询表单并根据前台传入的条件查询
	 */
	public IDto composeSearchVisitReport(long ownerid,long sgid,VisitUserDto visitdto,int pageId);
	
	public IDto findDanymicUserRecordByNickname(long sgid,Account account, String nickname, int page) ;
	
	public List<DanymicUserDetail> findDanymicRecordDetailListData(long sgid,String wbuid,DanymicUserSiftDto siftDto, int pageId);
	
	public IDto findDanymicRecordDetailList(long sgid,long siteid,String wbuid,DanymicUserSiftDto siftDto, int pageId) ;
	

	public IDto activityReportDataAll(long sitegroupid,long activityid,long ownerid);
	
	public IDto activityReportData(long sitegroupid,long activityid);
	
	public IDto visitTerminalCount(long sitegroupid,long activityid);
	
	public IDto visitSourceCount(long sitegroupid,long activityid);
	
	public IDto activityParticipateDataAll(long sitegroupid,long activityid,long ownerid);
	
	public IDto activityParticipateData(long sitegroupid,long activityid);
	
	public IDto activityParticipateSourceData(long sitegroupid,long activityid);
	
	public IDto activityParticipateSuccessDataAll(long sitegroupid,long activityid,long ownerid);
	
	public IDto activityParticipateSuccessData(long sitegroupid,long activityid);
	
	public IDto activityParticipateSourceSuccessData(long sitegroupid,long activityid);
	
	public IDto activityParticipateSourceFailDataAll(long sitegroupid,long activityid,long ownerid);
	
	public IDto activityParticipateSourceFailData(long sitegroupid,long activityid);
	
	public IDto activityParticipateSourceFailSource(long sitegroupid,long activityid);
	
	public IDto getAnonVisitData(long sitegroupid,int pageId,long activityid);
	
	public IDto nonAnonVisitReportData(long sitegroupid,int pageId,long activityid);
	
	public IDto findnonAnonVisitDataDetails(long sitegroupid,String visitTime1,String visitTime2,String terminal,String source,int pageId,long activityid);
	
	public IDto findnonAnonVisitDataDetails(long sitegroupid,String nickname,int pageId,long activityid);
	
	public IDto visitViewAllDataAjax(long sgid,Account account) ;
	
	public IDto hourReportData(long sgid,Account account);

	public IDto composeFansReport(long sgid,int pageId,long ownerid);
	
	public IDto composeHdFansReport(long sgid,int pageId,long ownerid);
	
	public IDto findVisitReportDetailList(long sgid,long siteid,String wbuid,int pageId);
	
	public IDto activityParticipateDetails(String terminalName,int pageId,long activityid);
	
	public IDto getActivityParticipateDetails(String terminalName,String visitTime1,String visitTime2,String source,int pageId,long activityid);
	
	public IDto getActivityParticipateDetailsByName(String terminalName,String nickname,int pageId,long activityid);
	
	public IDto activityParticipateSuccessDetails(String terminalName,int pageId,long activityid);
	
	public IDto getActivityParticipateSuccessDetails(String terminalName,String visitTime1,String visitTime2,String source,int pageId,long activityid);
	
	public IDto getActivityParticipateSuccessDetailsByName(String terminalName,String nickname,int pageId,long activityid);
	
	public IDto activityParticipateFailDetails(String terminalName,int pageId,long activityid); 
	
	public IDto getActivityParticipateFailDetails(String terminalName,String visitTime1,String visitTime2,String source,int pageId,long activityid);
	
	public IDto getActivityParticipateFailDetailsByName(String terminalName,String nickname,int pageId,long activityid);
	
	public long getWbuidByUserInfo(long uid);
	
	public long composeActivityLog(long activityid,long wbuid,String type,String ip,String terminal,String source);
	
	public IDto composeFace(long category);

	public List<InteractModel> composeFindInteractModel();
	
	public int findPageFeatureFid();
	
	public IDto findPageFeature(long ownerid,int pageId);
	
	public ReportGenderAnalyse danymicGenderData(long sgid,Account account) ;
	
	public IDto hdViewAllData(long sgid,int daynum,Account account);
	
	public IDto HdHourReportData(long sgid,Account account);
	
	public IDto findVisitRecordByNickname(long sgid,Account account, String nickname, int page);
	
	public IDto findVisitLogUnkownList(long sgid, int pageid, Account account);
	 
	public IDto getVisitLevelData(long sgid, Account account);
	
	public IDto getHdLevelData(long sgid, Account account) ;
	
	public IDto getReportViewAllData(Account account);
	
	public Sitegroup composeSiteGroup(Account account, long sgid);
	
	public IDto getPageTemplateList(long ownerid);
	
	public String findAppSecrectByAppid(long appid);
	
	public TerminalDataDto visitTerminalData(long sgid, Account account);
	
	public TerminalDataDto danymicTerminalData(long sgid, Account account);
	
	public IDto composeTemplateIndexAction(long categoryid,String type);
	
	public int saveOwnerTemplate(long ownerid,List<Long> templates);
	
	public IDto composeMyTemplateAction(long ownerid);
	
	public IDto hdAreaReport(long sgid,long ownerid);
	
	public IDto hdAreaList(long sgid,long ownerid,int pageId);
	
	public MyTempalte findOneTemplate(long id);
	
	public int updateNameTemplate(long id,String name);
	
	public int updateTemplate(long id,String style,File pic,String oldpic);
	
	public int deleteTemplate(long id);
	
	public IDto hdNumReport(long sgid,long ownerid);

	/**
	 * 加关注
	 * @param pageid
	 * @param wbuid
	 * @param cid
	 * @return
	 */
	public String createFriendsShips(long pageid, long wbuid, long cid);
	
	public IDto hdPerNumReport(long sgid,long ownerid);
	
	public OwnerDataDto getOwnerVisitData(long ownerid);
	
	public OwnerDataDto getOwnerHdData(long ownerid);
	
	public SiteDataDto getSiteData(long sgid,long owner,int daynum);
	
	public IDto findZamDetailList(long productid,int pageid);
	
	public IDto composeCardAction(long pageid);
	
	public IDto composeCardListAction(long pageid,String subtype);
	
	public IDto composeCard(long pageid,String type,String ptype);
	
	public long composeAddCard(long pageid,long cardid);
	
	public long composeUpdateCard(long pageid,long cardid,long pcid);
	
	public IDto composeShowCard(long pcid);
	
	public IDto composeShowCard2(long pcid);
	
	public void setXq(CardDto dto);
	
	public IDto composeEditBlock(long blockid,long pageid);
	
	public int composeSaveRelationJson(long relationid,String json);
	
	public IDto composeTemplateCardByPageid(long pageid);
	
	public String findHtmlByPage(long pageid,String type);
	
	public int updateCode(long pageid,String type,String html);
	
	public IDto composeTemplateCardByPageid2(long pageid);
	
	public long findXcIdBySiteid(long siteid);
	
	public IDto composeEditPage(long pageid,long ownerid);
	
	public IDto composeEditKongBaiPage(long pageid,long ownerid);
	
	public IDto composeEditPageG(long pageid,long ownerid);
	
	public IDto composeCardListByPageid(long pageid,String type);
	
	public String composePageRelation(long pageid);
	
	public int composeMovePosition(String cardMoveStr);
	
	public int composeDeleteCard(long pcid,long pageid);
	
	public int updateCardName(long pcid,String name);
	
	public int addPageHtml(long pageid,String sTotalString);
	
	public String findHtmlByPageid(long pageid);
	
	public List<Site> composeSiteGroupList(Account account);
	
	public int saveNewPage(long siteid,long pageid);
	
	public String saveNewSuit(long siteid,long pageid);
	
	public List<SitePage> findSitePageByOwner(long owner);
	
	public OwnerDataDto getPageData(long ownerid,int pageId);
	
	public SiteDto getSiteHdData(long siteid,long ownerid);
	
	public int updateOnline(long pageid);
	
	public int updateOffline(long pageid);
	
	public int updateOnlineAll(long siteid);

	public int updateOnlineByGroup(long sitegroupid,String groupType);
	
	public int updateDataAll(long siteid);
	
	public int updateData(long pageid);
	
	public SiteDto getSiteVisitData(long siteid,long ownerid);
	
	public PageDto getPageVisitArea(long pageid,long ownerid,String src);
	
	public PageDto getPageVisitGender(long pageid,long ownerid,String src);
	
	public PageDto getPageVisitFans(long pageid,long ownerid);
	
	public PageDto getPageHdGender(long pageid,long ownerid,String src) ;
	
	public PageDto getPageHdFans(long pageid,long ownerid);
	
	public PageDto getPageHdArea(long pageid,long ownerid,String src);
	
	public IDto composeHdListByPageId(long pid,int pageId,long ownerid,String src);
	
	public IDto composeVisitListByPageId(long pid,int pageId,long ownerid,String src);
	
	public PageDto getPageHdType(long pageid,long ownerid,String src);
	
	public long findPageidByPcid(long pcid);
	
	public IDto composeTemplateCardByPageidAndPcid(long pageid,long pcid);
	
	public CardDto  findTemplateSiteIndex(long category,String type);
	
	public int composeMoveStyle(String jspstyle,long pageid);
	
	public List<TemplateCard> findCardListByType(long pageid,String type);
	
	public IDto composePublishSinaIndex(long siteid);
	
	public String findPublishLink(String state, long appid,long pageid);
	
	public String publishByCode(long appid, String code, long pageid,long ownerid);
	
	public PingceResultDto findExamResult(long relationid,long rid);
	
	public NewDetailDto findNewDetails(long relationid,long nid,String type);
	
	public String findPageBg(long pageid);
	
	public int savePageBg(long pageid,String title,String keywords,String description,String background,String uploadMusic,String musicname, String uploadArrow, JSONObject tagJson);
	
	public int savePageHeight(long pageid,String height);
	
	public int savePageCardStyle(long cardid,String css);
	
	public int savePageCardCss(long cardid,String bg);
	
	public IDto findBgByCardid(long cardid);
	
	public IDto findcssBgByCardid(long cardid);
	
	public MaterialDto findMaterialIndex(long category,long scategory, int pageId);
	
	public MaterialDto findMaterialList(long category,long scategory, int pageId);
	
	public JSONObject findContentDetails(JSONObject jb,VisitUser vu);
	
	/**
	 * 绑定微信
	 * @param pageid
	 * @param ownerid
	 * @param name
	 * @param appid
	 * @param appsecret
	 * @param pic
	 * @param infoed
	 * @return
	 */
	//public String composePublic2qq(long pageid,long ownerid,String name,String appid,String appsecret,String pic,String infoed);
	
	public int composeUploadPage(long siteid,String pagename,String html,String js,String css);
	
	public int updateContnetIdx(long id, long ccid, String cctype, int oldIdx);
	
	public int updateCategoryName(long id,ContentCategory cc);
	
	public IDto findAddressList(Account account,long pageid);
	
	public int savePageAddress(PageAddress pa,Account account);
	
	public int findSourceExit(long pageid,String source);
	
	public int updatehiddenBlk(long relationid);
	
	public int deleteBlk(long relationid);
	
	public int updateshowBlk(long pcid);
	
	public PageAddress findSinaTokenIsExit(long pageid);
	
	public int updateisshowMenu(long pcid,String isshow);
	
	public Page findPageSubweixin(long pageid);
	
	public int savePageTemplate(PageTemplate pt);
	
	public IDto findPageTemplate();

	/**
	 * 站点复制
	 * @param owner
	 * @param sitename
	 * @param copyType W-微现场  J-集人气  N-普通  S-微站
	 * @param groupid 需复制的sitegroup
	 * @return 0-失败 1-成功 -1重复的站点名称
	 */
	public long siteCopy(Owner owner, String sitename, String copyType, long groupid);
	
	public int updateSiteGroupIsTemplateSetY(long sitegroupid);
	
	public int updateSiteGroupIsTemplateSetN(long sitegroupid);
	
	public void savePosition(String ps);

	public IDto findTaoZhuangSite();

	public int addContentType(Account account, String ccname, String type);
	
	public List<Map<String, String>> findRecordByPoint(String x,String y,String tags,int pagesize);

	public int deleteContentType(long typeid, Account account);

	public IDto findConfigType(Account account);

	public int configTypeSub(List<OwnerContentType> subList, long id);

	public List<InteractModel> composeUsedInteractModel();

	public List<InteractModel> compostInteractModel();

	public String addOwnerInteract(String title, long mid, long id);

	public int deleteOwnerInteract(long lid, long ownerid);

	public InteractModel composeOmName(long omid);

	public IDto composeContentForm(long id, long ccid, long typeid);

	public IDto composeUpdateContentForm(long id, long formid);

	public int updateContentFormSub(Account account, ContentForm contentform, List<ContentFormMapping> newList);

	public IDto addContentFormIndex(Account account, long formid);

	public int addContentFormSub(ContentFormRecord record, Account account);

	public int deletFormRecord(Account account, long formid, long recordid);

	public IDto findContentFormRecord(Account account, long formid, long recordid);
	
	public IDto composeDataIndexAction(Account account);
	
	public List<Page> findOnlinePageByOwnerid(long ownerid, String start, String end);
	
	public Page findPageByPageid(long pageid);
	
	public WxUser findWxUserByOpenid(String openid);

	public IDto composeProvince();
	
	public IDto composeCity(String provinceId);
	
	public IDto composeEditPg(long pageid);
	
	public int composeSavePg(long pageid,String json);
	
	public IDto findForumByOwnerid(long ownerid,int pageId,long accid);
	
	public IDto findForumByOwnerid(long ownerid);
	
	public long composeUploadPageHtml(long siteid,String pagename,String jspname);
	
	public String saveBBSCategoryByOwner(long ownerid);
	
	public IDto saveForum(List<Integer> catList,long accid,long ownerid);
	
	public String saveForumNew(String title,String cateType,long ownerid,long accid);
	
	public int saveNormalForum(BBSForum forum);
	
	public int delforum(long forumid);
	
	public int composeSavePageParamAndValue(long pageid,String jspname,String param,String value);
	
	public int updateForumName(BBSForum forum,long ownerid,long cateid);
	
	public int updateBBSForumByForumid(BBSForum forum,String ip,long owner);
	
	public int updateBBSForumPermissionByForumid(BBSForum forum);
	
	public IDto findBBSForumById(long id);
	
	public IDto findBBSForumByIdAndOwnerid(long id,long ownerid);
	
	public int updateSiteIsWhole(long siteid);
	
	public int updateNamebypageid(long pageid,String name);
	
	public JSONObject findJspname(long siteid);
	
	public IDto findZujian();
	
	public String addZujian(long pcid,long bid);
	
	public int changeEvent(long pcid, String eventName);

	public int editContentFormSub(ContentFormRecord record, Account account);
	
	public int updateOwnerInteractTitle(long lid, long id, String title);

	public int updateOwnerInteractStatus(long lid, long id, String string);

	public Map<String, String> findRecordId(long recordid);
	
	public long copyCard(long pcid);
	
	public IDto findCbActivityListByCbid(long cbid,int pageId);
	
	public IDto findWxPageShowListByAid(long cbid,long aid,int pageId);
	
	public IDto findPageShowMaterialByOwnerid(long ownerid,int pageId,String title);
	
	public int updatePageShowActioned(long id,String actioned);
	
	public List<Page> findPagesByName(long ownerid,String name,int start, int rows);
	
	public IDto findContentRecordList(int pageId,String type,int utype,long uid,String title);
	
	public int saveBread(long catid,String json);
	
	public String findBread(long catid);
	
	public IDto findMbList(String type,long tagid);
	
	public List<Page> findPagesBySiteid(long siteid);
	
	public int saveMb(Mb mb);
	
	public List<Sitegroup> findSitegroupList(long ownerid);
	
	public long saveUseMb(Owner owner,String type,long mbid,String name,long groupid,String stype);

	public int saveSiteSource(long siteid, long id, SiteSource sitesource);
	
	public List<SiteSource> findSiteSourceByOwner(Owner owner,long pageid);
	
	public void updatePageSource(long pageid,List<Long> sources);
	
	public List<String> findPageSourceByPageid(long pageid,boolean isRun,int level);
	
	public List<PageSource> findPageSourceListByPageid(long pageid);
	
	public SourceDto findPageSourceById(long psid,long pageid);
	
	public int savePageSourceEdit(long psid,String json);
	
	public int savePageSourceEditC(long psid,String json,String top,String left,String right,String bottom);

	public Map<String, SiteSource> findSiteSource(long siteid);
	
	public SiteSource findSiteSourceById(long sourceid);

	public int updateSiteSource(long siteid, long id, SiteSource sitesource);
	
	public int findSourcePage(long sourceid);
	
	public int delSiteSource(long sourceid);

	public IDto findXcSiteBygroupid(long sitegroupid, long xcid);

	/**
	 * 根据xcid查找的导航条  sitegroupid-siteid-xcid-title
	 * @param xcid
	 * @return
	 */
	public DaohangDto composeDhByXcid(long xcid);

	/**
	 * 根据siteid查找site信息 包含sitegroupname, sitegroupid, sitegroup_stype
	 * @param siteid
	 * @return
	 */
	public Site findSiteWithGrpById(long siteid);

	public IDto findNewsMaterial(long ownerid, long ccid, int pageId);

	public int updateOfflineAll(long siteid);
	
	public int updateToPage(long ccid);

	public String findInteractRecord(VisitUser vu,long hdid);

	public List<UserTag> findOwnerUserTag(long id);

	public UserPkvTag findContentTag(long ccid, long pid);

	public UserPkvTag findPageTag(long pageid);
	
	public IDto chooseContent(Account account);
	
	public IDto findContentByCcid(long ccid,long owner,int pageId,int size);

	public IDto composeSiteGroupByType(Account account, int pageId, String grouptype);
	
	public List<Sitegroup> findPageTofs(Account account, String string);
	
	public List<ContentCategory> findCategoryByFatherCcid(long catid, long owner);

	public List<Sitegroup> findGroupTofs(Account account, String siteGroupD);
	
	public int freeOpenApp(long appid,long ownerid);
	
	public IDto findOwnerSiteSourceByOwnerid(long ownerid);
	
	public IDto findSiteSourceVm(String type);
	
	public int saveOwnerSiteSource(long ownerid,long vmid,String title);
	
	public IDto editOwnerSiteSource(long id);
	
	public int saveEditOwnerSiteSource(OwnerSource os);
	
	public IDto findPage4Source(long ownerid,long pageid);
	
	public int savePage4Source(long ownerid,long pageid,long osid);
	
	public int saveCancelPage4Source(long ownerid,long pageid,String type);

	public int deleteOwnerSource(long id, long id2);

	public int updateOwnerSourceStyle(long id, Account account, Map<String, String> map);
	
	public Object findAppObjectByAppid(long ownerid,long appid);

	public long findAppUsedSiteGroup(long owner, int appid);

	public int updateUsedSiteGroup(long id, long groupid, int appid);

	public Map<String, ContentTab> findProductTabs(long contentId, long owner);

	public int updateTabIndex(ContentTab tab);
}
