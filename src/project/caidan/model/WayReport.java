
package project.caidan.model;

import java.io.Serializable;
import java.util.List;

public class WayReport implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8951307642157572728L;
	private CDWay way;
	private int num;
	private List<CDAccountDl> list;

	public CDWay getWay()
	{
		return way;
	}

	public void setWay(CDWay way)
	{
		this.way = way;
	}

	public int getNum()
	{
		return num;
	}

	public void setNum(int num)
	{
		this.num = num;
	}

	public List<CDAccountDl> getList()
	{
		return list;
	}

	public void setList(List<CDAccountDl> list)
	{
		this.list = list;
	}

}
