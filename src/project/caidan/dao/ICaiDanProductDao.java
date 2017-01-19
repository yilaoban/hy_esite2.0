package project.caidan.dao;

import java.util.List;

import com.huiyee.esite.model.ProductCode;

import project.caidan.dto.CdReportSift;
import project.caidan.model.CDProductRmb;
import project.caidan.model.CdWkqRmb;


public interface ICaiDanProductDao
{
	public List<CDProductRmb> findProductList(long owner);
	
	public List<CDProductRmb> findProductList(int start,int size,long owner);
	
	public CDProductRmb findProductById(long id);
	
	public List<CDProductRmb> findOrderList(long hyuid,String status,int type,int start,int size);
	
	public List<CDProductRmb> findOrderList(long hyuid,String status,int type);
	
	public CDProductRmb findCdOrderById(long hyuid, String status, String type, long id);

	public int findProductCodeLess(long id);

	public int findChannelPerformanceTotal(long owner, CdReportSift sift);

	public List<CdWkqRmb> findChannelPerformance(long owner, CdReportSift sift, int start, int size);

	public List<CdWkqRmb> findChannelPerformance(CdReportSift sift);
	
	public long findWxuidByPogid(long pogid);

	public CDProductRmb findProductByPid(long pid);

	public ProductCode findCodeByPogid(long id);

	public CDProductRmb findProductByProductid(long productid);


}
