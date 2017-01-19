package com.huiyee.esite.mgr.imp;

import java.util.List;
import java.util.Random;

import com.huiyee.esite.dao.IContentProductDao;
import com.huiyee.esite.dao.IXuantuDao;
import com.huiyee.esite.mgr.IXuantuManager;
import com.huiyee.esite.model.ContentProduct;
import com.huiyee.weixin.dao.IWxBuyProductDao;
import com.huiyee.weixin.model.PayAddress;
import com.huiyee.weixin.model.ShoppingCartRecord;

public class XuantuManagerImpl implements IXuantuManager {
	
	private IXuantuDao xuantuDao;
	private IWxBuyProductDao wxBuyProductDao;
	private IContentProductDao productDao;
	
	
	
	public void setProductDao(IContentProductDao productDao)
	{
		this.productDao = productDao;
	}


	public void setWxBuyProductDao(IWxBuyProductDao wxBuyProductDao)
	{
		this.wxBuyProductDao = wxBuyProductDao;
	}


	public void setXuantuDao(IXuantuDao xuantuDao)
	{
		this.xuantuDao = xuantuDao;
	}


	@Override
	public int addCollection(long productid, long ownerid, long hyuid)
	{
		return xuantuDao.addCollection(productid, ownerid, hyuid);
	}


	@Override
	public int checkCollection(long productid, long ownerid, long hyuid)
	{
		return xuantuDao.checkCollection(productid, ownerid, hyuid);
	}


	@Override
	public List myCollection(long ownerid, long hyuid,int start,int size)
	{
		return xuantuDao.myCollection(ownerid, hyuid, start, size);
	}


	@Override
	public int mytotal(long ownerid, long hyuid)
	{
		return xuantuDao.mytotal(ownerid, hyuid);
	}


	@Override
	public int removeCollection(long ownerid, long hyuid, long productid)
	{
		return xuantuDao.removeCollection(ownerid, hyuid, productid);
	}
	
	@Override
	public ShoppingCartRecord addShoppingCart(long productid, long hyuid,String status,long owner)
	{
		ContentProduct product = xuantuDao.findEasyProductById(productid,owner);
		ShoppingCartRecord p = null;
		if(product != null){
			p = xuantuDao.findShoppingCartProduct(productid,hyuid,status);
			long result = 0;
			if(p != null){				
//				xuantuDao.updatePayShoppingCartNum(p.getId(),hyuid);
				result = p.getId();//需要返回购物车的id
			}else{
				p = new ShoppingCartRecord();
				result = xuantuDao.savePayShoppingCart(productid,hyuid,status);
			}
			p.setId(result);
			p.setType(product.getType());
			return p;
		}else{
			return null;
		}
	}

	@Override
	public List findShoppingCartProductByHyuid(long hyuid, long owner, String type, int start, int size)
	{
		return xuantuDao.findShoppingCartProductByHyuid(hyuid, owner, type, start, size);
	}


	@Override
	public int delShopCartProduct(long shopCartid, long hyuid)
	{
		return xuantuDao.delShopCartProduct(shopCartid, hyuid);
	}


	@Override
	public int savesubmit(long hyuid,long wxuid,long ownerid, String name, String telphone, String[] something) throws Exception
	{
		List<PayAddress>  list = wxBuyProductDao.findPayAddressListByHyUid(hyuid);
		if(list.size() == 0){
			PayAddress address = new PayAddress();
			address.setHyuid(hyuid);
			address.setName(name);
			address.setTelphone(telphone);
			address.setIsdefault("Y");
			wxBuyProductDao.savePayAddress(hyuid, address);//保存地址
			list = wxBuyProductDao.findPayAddressListByHyUid(hyuid);
		}
		long payOrderid = wxBuyProductDao.saveNewOrder(hyuid, wxuid, ownerid, 1, list.get(0).getId(), null);
		for(String str:something){
			String[] param = str.split("-");
			if(param.length == 4){
				long productid = Long.parseLong(param[0]);
				int price = Integer.parseInt(param[1]);
				int num = Integer.parseInt(param[2]);
				String zb = "("+param[3]+")";
				ContentProduct p = productDao.findProductById(productid);
				if(p != null){
					wxBuyProductDao.savePayOrderGoods(payOrderid, productid, p.getName()+zb, "", p.getSimgurl(), price, 1, getRandomString(10), num, 0, 0);
				}else{
					throw new Exception();
				}
			}
		}
		return xuantuDao.delShopCart(hyuid);
	}
	
	public static String getRandomString(int length)
	{ // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++)
		{
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
}
