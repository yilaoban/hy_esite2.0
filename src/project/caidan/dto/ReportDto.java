
package project.caidan.dto;

import java.util.List;

import org.apache.solr.common.SolrDocument;

import project.caidan.model.CDAccountCpz;
import project.caidan.model.CDAccountDl;
import project.caidan.model.CDWayCatalog;
import project.caidan.model.CdWkqRmb;
import project.caidan.model.WayReport;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.Pager;

public class ReportDto implements IDto
{

	private List<WayReport> list;
	private Pager pager;
	private List<CDAccountCpz> cpzs;
	private CDAccountDl dl;
	private List<CdWkqRmb> wkq;
	private List<CDWayCatalog> catalogs;
	private List<SolrDocument> docList;
	private Double sum_add = 0d;
	private Double sum_sub = 0d;

	public List<CDWayCatalog> getCatalogs()
	{
		return catalogs;
	}

	public void setCatalogs(List<CDWayCatalog> catalogs)
	{
		this.catalogs = catalogs;
	}

	public List<WayReport> getList()
	{
		return list;
	}

	public void setList(List<WayReport> list)
	{
		this.list = list;
	}

	public Pager getPager()
	{
		return pager;
	}

	public void setPager(Pager pager)
	{
		this.pager = pager;
	}

	public List<CDAccountCpz> getCpzs()
	{
		return cpzs;
	}
	
	public List<SolrDocument> getDocList() {
		return docList;
	}

	public void setDocList(List<SolrDocument> docList) {
		this.docList = docList;
	}

	public void setCpzs(List<CDAccountCpz> cpzs)
	{
		this.cpzs = cpzs;
	}

	public CDAccountDl getDl()
	{
		return dl;
	}

	public void setDl(CDAccountDl dl)
	{
		this.dl = dl;
	}

	public List<CdWkqRmb> getWkq()
	{
		return wkq;
	}

	public void setWkq(List<CdWkqRmb> wkq)
	{
		this.wkq = wkq;
	}

	public Double getSum_add() {
		return sum_add;
	}

	public void setSum_add(Double sum_add) {
		this.sum_add = sum_add;
	}

	public Double getSum_sub() {
		return sum_sub;
	}

	public void setSum_sub(Double sum_sub) {
		this.sum_sub = sum_sub;
	}
	
}
