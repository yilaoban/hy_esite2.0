package com.huiyee.esite.filter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;
import com.opensymphony.xwork2.ActionContext;

public class CheckCodeFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request0, ServletResponse response0,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) request0;
		HttpServletResponse response = (HttpServletResponse) response0;
		// 禁止缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的响应是图片
		response.setContentType("image/jpeg");

		int width = 70;
		int height = 24;
		// 具有可访问图像缓冲区的Image
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(getRandomColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 设置干扰线颜色
		g.setColor(getRandomColor(180, 200));
		Random random = new Random();
		for (int i = 0; i < 130; i++) {
			int x1 = random.nextInt(width - 1);
			int y1 = random.nextInt(height - 1);
			int deltaX = random.nextInt(6) + 1;
			int deltaY = random.nextInt(12) + 1;
			BasicStroke bs = new BasicStroke(2, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_BEVEL);
			Line2D line = new Line2D.Double(x1, y1, x1 + deltaX, y1 + deltaY);
			g2d.setStroke(bs);
			g2d.draw(line);
		}

		String sRand = "";
		// 输出随机的验证文字
		char[] chars = {'A','B','C','E','F','G','H','J','K','L','M','N','P','R','S','T','U','V','W','X','Y','Z','2','3','4','5','6','7','8','9'};
		for (int i = 0; i < 4; i++) {
//			if (random.nextInt(2) == 1) {
//				itmp = random.nextInt(26) + 'A';// 生成A~Z的字母
//			} else {
//				itmp = random.nextInt(10) + '0';// 生成0~9数字
//			}
			int random1=random.nextInt(chars.length-1);
			
			char ctmp = chars[random1];
			sRand = sRand + ctmp;
			Color color = getRandomColor(20, 130);
			g.setColor(color);
			// 下面对文字进行旋转和缩放
			// 将文字旋转随机的角度
			AffineTransform trans = new AffineTransform();
			trans.rotate(random.nextInt(45) / 180 * Math.PI, 15 * i + 10, 6);
			// 缩放文字
			float scaleSize = random.nextFloat() + 0.5f;
			if (scaleSize < 0.8 || scaleSize > 1.2) {
				scaleSize = 1f;
			}
			trans.scale(scaleSize, scaleSize);
//			g2d.setTransform(trans);
			Font font = new Font("华文宋体", Font.BOLD, 18);
			g2d.setFont(font);
			g2d.drawString(String.valueOf(ctmp), 15 * i + 10, 14);
		}
		request.getSession().setAttribute("checkCode", sRand);
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	private Color getRandomColor(int fc, int bc) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		Random random = new Random();
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
