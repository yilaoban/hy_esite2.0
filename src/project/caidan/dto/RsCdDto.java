package project.caidan.dto;

import java.util.List;

import org.apache.solr.common.SolrDocument;

import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.OrderGoods;

public class RsCdDto {

	private List<HyUser> list;
	private List<SolrDocument> jfList;
	private List<OrderGoods> ogList;
	private Pager pager;

	private String hydesc;
	private int status;

	public List<HyUser> getList() {
		return list;
	}

	public void setList(List<HyUser> list) {
		this.list = list;
	}

	public List<SolrDocument> getJfList() {
		return jfList;
	}

	public void setJfList(List<SolrDocument> jfList) {
		this.jfList = jfList;
	}

	public List<OrderGoods> getOgList() {
		return ogList;
	}

	public void setOgList(List<OrderGoods> ogList) {
		this.ogList = ogList;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getHydesc() {
		return hydesc;
	}

	public void setHydesc(String hydesc) {
		this.hydesc = hydesc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
