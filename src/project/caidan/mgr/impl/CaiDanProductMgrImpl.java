package project.caidan.mgr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import project.caidan.dao.IAccountTypeDao;
import project.caidan.dao.ICaiDanProductDao;
import project.caidan.dao.ICaiDanRmbDao;
import project.caidan.dto.CDProductDto;
import project.caidan.mgr.ICaiDanProductMgr;
import project.caidan.model.CDAccountCpz;
import project.caidan.model.CDProductRmb;
import project.caidan.model.CDRmb;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.OrderGoods;
import com.huiyee.esite.model.PayRecord;
import com.huiyee.esite.model.ProductCode;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.JsonStringUtil;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.weixin.dao.IWxBuyProductDao;
import com.huiyee.weixin.model.PayOrder;
import com.huiyee.weixin.model.template.JFDHLPTZ;


public class CaiDanProductMgrImpl extends AbstractMgr implements ICaiDanProductMgr
{
	private ICaiDanProductDao cdProductDao;
	private ICaiDanRmbDao cdRmbDao;
	private IWxBuyProductDao wxBuyProductDao;
	private IAccountTypeDao accountTypeDao;
	
	
	public void setAccountTypeDao(IAccountTypeDao accountTypeDao)
	{
		this.accountTypeDao = accountTypeDao;
	}

	public void setWxBuyProductDao(IWxBuyProductDao wxBuyProductDao)
	{
		this.wxBuyProductDao = wxBuyProductDao;
	}

	public void setCdRmbDao(ICaiDanRmbDao cdRmbDao)
	{
		this.cdRmbDao = cdRmbDao;
	}

	public void setCdProductDao(ICaiDanProductDao cdProductDao)
	{
		this.cdProductDao = cdProductDao;
	}

	@Override
	public IDto findProductList(long owner,long hyuid,int size)
	{
		CDProductDto dto = new CDProductDto();
		List<CDProductRmb> list=new ArrayList<CDProductRmb>();
		if(size == 0){
			list = cdProductDao.findProductList(owner);
		}else{
			list = cdProductDao.findProductList((size - 1) * IInteractConstants.LOTTERY_LIST_LIMIT, IInteractConstants.LOTTERY_LIST_LIMIT, owner);
		}
		if(list.size()>0){
			for (Iterator<CDProductRmb> iter = list.iterator(); iter.hasNext();)  
			{  
				/**
				 * 遍历list,当产品是券码并且无用券号密码时从list中去掉
				 */
				CDProductRmb cdpr=iter.next();
				if(cdpr.getProduct()!=null&&IPageConstants.PRODUCT_SUBTYPE_COUPON.equals(cdpr.getProduct().getSubtype())){
					int num=cdProductDao.findProductCodeLess(cdpr.getProduct().getId());
					if(num==0){
						iter.remove();
					}
				}
				
			} 	
		}
		dto.setList(list);
		int jf = this.findJFen(hyuid);
		dto.setJf(jf);
		return dto;
	}

	@Override
	public IDto findCdProductById(long id, long hyuid)
	{
		CDProductDto dto = new CDProductDto();
		CDProductRmb cdProduct = cdProductDao.findProductById(id);
		dto.setCdProduct(cdProduct);
		int jf = this.findJFen(hyuid);
		dto.setJf(jf);
		return dto;
	}

	@Override
	public IDto findWxUser(long wxuid)
	{
		CDProductDto dto = new CDProductDto();
		CDRmb cdRmb = cdRmbDao.findCDRmbByWxuid(wxuid);
		dto.setCdRmb(cdRmb);
		return dto;
	}

	@Override
	public IDto findOrderList(long hyuid, String status, int size, int type)
	{
		CDProductDto dto = new CDProductDto();
		if(size == 0){
			List<CDProductRmb> payOrderList = cdProductDao.findOrderList(hyuid,status,type);
			dto.setPayOrderList(payOrderList);
		}else{
			List<CDProductRmb> payOrderList = cdProductDao.findOrderList(hyuid,status,type,(size - 1) * IInteractConstants.LOTTERY_LIST_LIMIT,IInteractConstants.LOTTERY_LIST_LIMIT);
			dto.setPayOrderList(payOrderList);
		}
		return dto;
	}

	@Override
	public IDto findCdWxUser(long hyuid)
	{
		CDProductDto dto = new CDProductDto();
		int jf = this.findJFen(hyuid);
		dto.setJf(jf);
		return dto;
	}

	@Override
	public IDto findCdOrderById(long hyuid, String status, String type, long id)
	{
		CDProductDto dto = new CDProductDto();
		CDProductRmb cdProduct = cdProductDao.findCdOrderById(hyuid,status,type,id);
		if(IPageConstants.PRODUCT_SUBTYPE_COUPON.equals(cdProduct.getProduct().getSubtype())){
			ProductCode  pcode=cdProductDao.findCodeByPogid(id);
			dto.setCode(pcode);
		}
		dto.setCdProduct(cdProduct);
		return dto;
	}

	@Override
	public IDto savePayMoney(long payOrderid, long hyuid, long owner, VisitUser vu, String url, String ip)
	{
		CDProductDto dto = new CDProductDto();
		PayOrder payOrder = wxBuyProductDao.findPayOrderById(payOrderid);
		List<OrderGoods> productList;
		String subject = "";
		if(payOrder.getGoodscount() > 0){
			 productList = wxBuyProductDao.findPayOrderGoods(payOrderid);
		}else{
			//纯卡券订单 查子订单下的商品
			long id = wxBuyProductDao.findChildOrder(payOrderid);
			productList = wxBuyProductDao.findPayOrderGoods(id);
		}
		if(productList != null && productList.size() > 0){
			if(productList.size() > 1){
				subject = productList.get(0).getProductname() + "等";
			}else{
				subject = productList.get(0).getProductname();
			}
		}
		int it=this.findJFen(hyuid);
		int totalPrice = payOrder.getRealprice();
		if(it<totalPrice)
		{
			dto.setStatus(-1);
			dto.setHydesc("积分不足");
			return dto;
		}
		if (owner > 0)
		{
			List<WxTemplate> wts = this.findWxTemplate(owner,"JFS",0);	
			if (wts != null&&wts.size()>0&&vu!=null&&vu.getWxUser()!=null)
			{
				for(WxTemplate wt:wts)
				{
				JFDHLPTZ dd = (JFDHLPTZ) JsonStringUtil.String2Obj(wt.getData(), JFDHLPTZ.class);
				dd.setKeyword1(subject);
				dd.setKeyword2(totalPrice+"积分");
				int ttt=it-totalPrice;
				dd.setKeyword3(ttt+"积分");
				dd.setKeyword4(DateUtil.getDateTimeString(new Date()));
				WxTemplateJob wj = new WxTemplateJob();
				wj.setMpid(wt.getMpid());
				wj.setType("JFS");
				wj.setEntityid(wt.getEntityid());
				wj.setRemark(wt.getRemark());
				wj.setTemplate_id(wt.getTemplate_id());
				wj.setTouser(vu.getWxUser().getOpenid());
				wj.setData(dd.toData());
				wj.setUrl(url);
				this.addTmplMsg(wj);
				}
			}
		}
		this.updateBalance(hyuid, -totalPrice, "积分商品兑换", "JFS", "DHS", payOrderid);
		
		wxBuyProductDao.updatepayOrderStatus(payOrderid,null, "CMP");
		wxBuyProductDao.updatePayorderStatusByFid(payOrderid,"CMP");//更新子订单
		PayRecord payRecord = new PayRecord(); 
		payRecord.setIp(ip);
		payRecord.setMediaorder("积分支付");
		payRecord.setHyuid(hyuid);
		payRecord.setOrderid(payOrderid);
		payRecord.setPrice(totalPrice);
		wxBuyProductDao.savePayRecord(payRecord);//保存支付记录
		dto.setStatus(1);
		dto.setHydesc("支付成功");
		return dto;
	}

	@Override
	public int saveCdRmbget(long wxuid,long hyuid, float rmb)
	{
		int jf = this.findJFen(hyuid);
		if(jf >= ((int)rmb*100)){
			this.updateBalance(hyuid, -(int)rmb*100, "积分兑换现金提现", "CDD", "CDD", 0);
			return cdRmbDao.saveCdRmbget(wxuid,rmb);
		}else{
			return 0;
		}
	}
	
	@Override
	public List<CDAccountCpz> findCouponArea(long pid,int pageId,int size)
	{
		pageId=pageId==0?1:pageId;
		size=size==0?16:size;
		return accountTypeDao.findCpzsByPid(pid,(pageId-1)*size,size);
	}
	
	@Override
	public CDProductRmb findProductById(long id)
	{
		return cdProductDao.findProductById(id);
	}
	
	@Override
	public CDProductRmb findProductByProductid(long productid)
	{
		return cdProductDao.findProductByProductid(productid);
	}
	
	@Override
	public int findProductCodeLess(long productid)
	{
		return cdProductDao.findProductCodeLess(productid);
	}
	
	@Override
	public int findRemianJf(long hyuid,int jf){
		int it=this.findJFen(hyuid);
		if(it < jf){
			return -1;
		}else{
			return 1;
		}
	}
	
	@Override
	public ProductCode findProductCode(long payOrderid)
	{
		List<OrderGoods> pog = wxBuyProductDao.findPayOrderGoods(payOrderid);
		if(pog.size()==1){
			try
			{
				return cdProductDao.findCodeByPogid(pog.get(0).getId());
			} catch (Exception e)
			{
				System.out.println("get product code error payOrderid:"+payOrderid);
			}
		}
		return null;
	}
}
