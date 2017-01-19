package com.huiyee.esite.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class PageFileUtil {
	
	public static final String ROOT_PATH="";
	
	public static String readFile(String filePath){
		BufferedReader br = null;
		String str = null;
		try {
			File file = new File(filePath);// 指定要读取的文件  
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));// 获取该文件的输入流  
			char[] bb = new char[1024];// 用来保存每次读取到的字符  
			str = "";
			int n;// 每次读取到的字符长度  
			while ((n = br.read(bb)) != -1) {  
			    str += new String(bb, 0, n);  
			}  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        return str;
	}
	
	public static void SaveFile(String filePath,String source){
		BufferedWriter bw = null;
		try {
			File file = new File(filePath);// 要写入的文本文件
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));// 获取该文件的输出流  
			bw.write(source);// 写内容  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		finally{
			if(bw != null){
				try {
					bw.flush();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 复制文件夹
	 * 
	 * @param oldPath
	 *            ：String 文件路径
	 * @param newPath
	 *            ：String 文件路径
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File oldfiles = new File(oldPath);
			String[] file = oldfiles.list();// 循环时用于存放临时的文件列表
			if(file != null){
				File tempfile = null;// 存放临时文件
				for (int i = 0; i < file.length; i++) {
					// 循环拿到文件夹下的每个文件
					if (oldPath.endsWith(File.separator)) {
						tempfile = new File(oldPath + file[i]);
					} else {
						tempfile = new File(oldPath + File.separator + file[i]);
					}
		
					// 是文件，就直接拷文件
					if (tempfile.isFile()) {
						copyFile(tempfile, newPath + "/"
								+ (tempfile.getName()).toString());
					}
		
					// 是文件夾，继续循环拷文件夹
					if (tempfile.isDirectory()) {
						copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("复制文件夹【" + oldPath + "】时出错！");
			e.printStackTrace();

		}

	}
	
	/**
	 * 复制单个文件
	 * 
	 * @param oldFile
	 *            ：File
	 * @param newPath
	 *            :String 文件路径
	 */
	public static void copyFile(File oldFile, String newPath) {

		Long starttime = System.currentTimeMillis();
		InputStream inStream = null;
		FileOutputStream fout = null;
		try {
			int bytesum = 0;
			int byteread = 0;
			if (oldFile.exists()) { // 文件存在时
				inStream = new FileInputStream(oldFile); // 读入原文件
				fout = new FileOutputStream(newPath);

				byte[] buffer = new byte[1024];

				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fout.write(buffer, 0, byteread);
				}
				fout.flush();
			}
		} catch (Exception e) {
			System.out.println("复制文件【" + oldFile.getAbsolutePath() + "】时出错！");
			e.printStackTrace();
		} finally {
			try {
				// 关闭输入流
				if (inStream != null) {
					inStream.close();
				}
				// 关闭输出流
				if (fout != null) {
					fout.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
