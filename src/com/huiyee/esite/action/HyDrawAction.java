package com.huiyee.esite.action;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.util.ColorUtil;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.ImageGenerate;

public class HyDrawAction extends AbstractAction{
	
	private String relationid;
	private String text;//文本
	private String color;//颜色
	private int size;//字体大小
	private String font;//字体
	
	public String getRelationid()
	{
		return relationid;
	}
	
	public void setRelationid(String relationid)
	{
		this.relationid = relationid;
	}
	
	public int getSize()
	{
		return size;
	}
	
	
	public void setSize(int size)
	{
		this.size = size;
	}
	
	@Override
	public String execute() throws Exception {
		File f = new File(HyConfig.getRootPath()+"/png/"+relationid+".png");
		Map<String,Font> map = (Map<String, Font>) ServletActionContext.getRequest().getSession().getServletContext().getAttribute("fonts");
		Font ft = map.get(font);
		if(ft == null){
			ft = new Font("宋体", Font.BOLD, 16);
		}
		ft = ft.deriveFont(Float.valueOf(size));
		BufferedImage image = ImageGenerate.generate(java.net.URLDecoder.decode(text,"UTF-8"), ft, f,ColorUtil.parseToColor(color));
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/png");
		ImageIO.write(image,"PNG",response.getOutputStream());
		return null;
	}

	
	public String getText()
	{
		return text;
	}

	
	public void setText(String text)
	{
		this.text = text;
	}

	
	public String getColor()
	{
		return color;
	}

	
	public void setColor(String color)
	{
		this.color = color;
	}
	
	public String getFont()
	{
		return font;
	}

	
	public void setFont(String font)
	{
		this.font = font;
	}
}
