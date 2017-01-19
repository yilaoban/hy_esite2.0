package com.huiyee.esite.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class EditUpLoadFileAction extends AbstractAction
{

	private long pageid;
	private String readContent;
	private String writeContent;
	private String fileUrl;
	private String type;//用作判断jsp名字
	
	public String readStyleFromFile()throws Exception{
		File file = new File(fileUrl);// 指定要读取的文件  
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));// 获取该文件的输入流  
        char[] bb = new char[1024];// 用来保存每次读取到的字符  
        String str = "";// 用来将每次读取到的字符拼接
        int n;// 每次读取到的字符长度  
        while ((n = br.read(bb)) != -1) {  
            str += new String(bb, 0, n);  
        }  
        br.close();// 关闭输入流，释放连接  
        readContent=str;
        return SUCCESS;
	}
	
	public String writeStyleToFile()throws Exception{
		int i=0;
        HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		File file = new File(fileUrl);// 要写入的文本文件  
	        if (!file.exists()) {// 如果文件不存在，则创建该文件  
	            file.createNewFile();  
	        }  
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));// 获取该文件的输出流  
	    bw.write(writeContent);// 写内容  
	    bw.flush();// 清空缓冲区，立即将输出流里的内容写到文件里  
	    bw.close();// 关闭输出流，施放资源  
	    int result=i++;
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}
	
//	public static void main(String[] args)throws Exception
//	{
//		readCssFromFile();
//		writeCssToFile();
//		readCssFromFile();
//	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public String getReadContent()
	{
		return readContent;
	}

	public void setReadContent(String readContent)
	{
		this.readContent = readContent;
	}

	public String getWriteContent()
	{
		return writeContent;
	}

	public void setWriteContent(String writeContent)
	{
		this.writeContent = writeContent;
	}

	public String getFileUrl()
	{
		return fileUrl;
	}

	public void setFileUrl(String fileUrl)
	{
		this.fileUrl = fileUrl;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
