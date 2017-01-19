package com.huiyee.interact.spread.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.util.HyConfig;

public class FileUploadService
{
	private static Calendar calendar = Calendar.getInstance(Locale.CHINA);
	private static Random random = new Random();
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/**
	 * 
	 * @param file
	 * @param filePath 路径
	 * @param fileName 文件名(传进来什么就存什么)
	 * @return
	 */
	public static String saveFile(File file, String filePath,String fileName)
	{
		if (file == null)
		{
			return null;
		}
		FileInputStream fis;
		try
		{
			fis = new FileInputStream(file);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		return saveFile(fis, filePath,fileName);
	}

	public static String saveFile(InputStream fis,String filePath, String fileName)
	{
		boolean sign = false;
		String fileResult = null;
		if (fis != null)// 处理上传的图片
		{
			try
			{
				BufferedInputStream bis = new BufferedInputStream(fis);
				fileResult = filePath + "/" + fileName;
				filePath = HyConfig.getRootPath()+filePath;
				File dir = new File(filePath);
				if (!dir.exists())
				{
					dir.mkdirs();
				}
				File file = new File(filePath, fileName);
				FileOutputStream fos = new FileOutputStream(file);

				byte[] buf = new byte[4096];
				int size = 0; // 文件总长度
				int len = -1;
				while ((len = bis.read(buf)) != -1)
				{
					size = size + len;
					fos.write(buf, 0, len);
				}
				fis.close();
				fos.close();
				sign = true;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				sign = false;
			}
		}
		if (sign)
		{
			return fileResult;
		}
		else
		{
			return null;
		}
	}

	/**
	 * 获取文件路径
	 * @param type
	 * @param ownerid
	 * @param type2
	 * @param featureid
	 * @return  /ownerid/content:feature:interact/subPath
	 */
	public static String getFilePath(int type, long ownerid, String subPath) {
		switch (type) {
		case IPageConstants.TYPE_CONTENT:
			return "/" + ownerid + "/content/" + subPath;
		case IPageConstants.TYPE_FEATURE:
			return "/" + ownerid + "/feature/" + subPath;
		case IPageConstants.TYPE_INTERACT:
			return "/" + ownerid + "/interact/" + subPath;
		default:
			break;
		}
		return null;
	}
	
	/**
	 * 创建文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String createFileName(String fileName) {
		int index = fileName.lastIndexOf('.');

		// 判断上传文件名是否有扩展名
		if (index != -1) {
			return getNow() + fileName.substring(index);
		}
		return getNow();
	}
	
	public static InputStream reduceImg(File srcfile, int widthdist,
			int heightdist) {
		try {
			if (!srcfile.exists()) {
				return null;
			}
			Image src = ImageIO.read(srcfile);
			if (src == null) {
				return null;
			}
			BufferedImage buffImg = new BufferedImage((int) widthdist,
					(int) heightdist, BufferedImage.TYPE_INT_RGB);
			/*
			 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
			 */
			buffImg.getGraphics().drawImage(
					src.getScaledInstance(widthdist, heightdist,
							Image.SCALE_SMOOTH), 0, 0, null);

			// FileOutputStream out = new FileOutputStream(imgdist);
			// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			// encoder.encode(tag);
			// out.close();
			
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
			ImageIO.write(buffImg, "jpeg",imOut); 
			InputStream is = new ByteArrayInputStream(bs.toByteArray()); 
			return is;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static long getNowLong() {
		return calendar.getTime().getTime();
	}
	
	/**
	 * 得到当前日期 将"yyyy-MM-dd"型字符串转换为date对象。
	 * 
	 * @return
	 */
	public static String getNow() {
		// System.out.println(UUID.randomUUID());
		String now = dateFormat.format(new Date());
		for (int i = 0; i < 4; i++) {
			int itmp = random.nextInt(10) + '0';// 生成0~9数字
			char ctmp = (char) itmp;
			now = now + ctmp;
		}
		return now;
	}

	/**
	 * 得到文件后缀，即文件类型
	 * 
	 * @param fileType
	 * @return
	 */
	public static String getFileSuffix(String fileType) {
		int index = fileType.lastIndexOf('.');

		// 判断上传文件名是否有扩展名
		if (index != -1) {
			return fileType.substring(index+1);
		}
		return "";
	}
}
