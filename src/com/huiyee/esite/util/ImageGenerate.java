package com.huiyee.esite.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageGenerate {
 
    public static BufferedImage generate(String text,Font font,File imagefile,Color color) throws IOException {
		
    	//获取font的样式应用在str上的整个矩形

		  Rectangle2D r=font.getStringBounds(text, new FontRenderContext(AffineTransform.getScaleInstance(1, 1),false,false));

		  int unitHeight=(int)Math.floor(r.getHeight());//获取单个字符的高度

		  //获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度

		  int width=(int)Math.round(r.getWidth())+1;

		  int height=unitHeight+3;//把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
    	// 创建BufferedImage对象
    	BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
    	// 获取Graphics2D
    	Graphics2D g2d = image.createGraphics();
    	// ----------  增加下面的代码使得背景透明  -----------------
    	image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
    	g2d.dispose();
    	g2d = image.createGraphics();
    	// ----------  背景透明代码结束  -----------------
    	g2d.setColor(color);
    	g2d.setFont(font);
    	// 画图
    	g2d.setStroke(new BasicStroke(1));
    	g2d.drawString(text, 0, font.getSize());
    	//释放对象
    	g2d.dispose();
    	// 保存文件
    	ImageIO.write(image, "png", imagefile);
    	return image;
    }
    
    public static BufferedImage generate2() throws IOException {
		
		int width=1;
		int height=1;//把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
    	// 创建BufferedImage对象
    	BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
    	// 获取Graphics2D
    	Graphics2D g2d = image.createGraphics();
    	// ----------  增加下面的代码使得背景透明  -----------------
    	image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
    	g2d.dispose();
    	g2d = image.createGraphics();
    	// ----------  背景透明代码结束  -----------------
    	//释放对象
    	g2d.dispose();
    	// 保存文件
//    	ImageIO.write(image, "png", new File("D:/3.png"));
    	return image;
    }
    
    public static void main(String[] args)
	{

	}
    
}