package com.huiyee.esite.util;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.io.DOMReader;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.springframework.util.StringUtils;

import com.huiyee.esite.exception.GenericServiceException;

public class UrlReader {
	
	private static Log logger = LogFactory.getLog(UrlReader.class);
	private String escapeElements="&amp;:&:&apos;:'";
	private static List xmls = new ArrayList();
	private static List htmls;
	
	private static int ISURL = 1;
	private static int ISFILE = 2;
	private static Pattern schemePattern = Pattern.compile("^[^:/?#]+:.*");
	
	//private static int flags = Pattern.CASE_INSENSITIVE|Pattern.DOTALL|Pattern.MULTILINE;
	public static String getHTML(String url) throws Exception {
		try {
		URL u = new URL(url);
		String prefix = u.getProtocol()+"://" + u.getHost();
		if (u.getPort() !=-1) 
			prefix = prefix + ":" + u.getPort();
		
		String svpath = u.getPath();
		int lidx = svpath.lastIndexOf("/");
		if (lidx >=0)
			svpath = svpath.substring(0, lidx + 1);
		else
			svpath ="/";
		
		return processHTML(u, svpath, prefix, ISURL);
		}catch (Exception x) {
			logger.warn("ex:", x);
			return null;
		}
	}
	
	
	//note: path must not end with /
	public static String getHTMLByFile(String filename, String path) throws Exception {
		
		URL u = new File(filename).toURI().toURL();
		return processHTML(u, "/", path, ISFILE );
	}
	private static String processHTML(URL u, String svpath, String prefix, int kind) throws Exception {
		
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties prop = cleaner.getProperties();
		prop.setUseCdataForScriptAndStyle(true);
		prop.setOmitXmlDeclaration(true);
		prop.setOmitDoctypeDeclaration(true);
		prop.setAdvancedXmlEscape(true);
		prop.setTranslateSpecialEntities(true);
		
		String charset = "utf-8";
		TagNode tn = cleaner.clean(u, charset);
		TagNode[] metas = tn.getElementsByName("meta", true);
		if (metas != null && metas.length != 0) {
			for (int i=0; i<metas.length; i++) {
				TagNode t =(TagNode) metas[i];
				String content = t.getAttributeByName("content");
				if (content == null) continue;
				int idx = content.indexOf("charset=");
				if (idx <0) continue;
				charset = content.substring(idx + 8);
				break;
			}
		}
		
		if (charset.equalsIgnoreCase("utf-8") == false) {
			
			tn = cleaner.clean(u, charset);
		}
		
		DomSerializer xs = new DomSerializer(prop);
		org.w3c.dom.Document w3cDoc = xs.createDOM(tn);
		
		DOMReader reader = new DOMReader();
		
		org.dom4j.Document dom4jDoc = reader.read(w3cDoc);
		cleanup(dom4jDoc, prefix, svpath, kind);
		return outputHTML(dom4jDoc);
	}
	

	
	private static void cleanup(Document doc, String prefix, String svpath, int kind) {
        List k = doc.selectNodes("//@src");
        for (Iterator iter = k.iterator(); iter.hasNext();) {
	        Attribute attribute = (Attribute) iter.next();
	        String src = attribute.getValue().trim();
	        if (src.startsWith("/")) {
	            src = HyConfig.getImgDomain()+ prefix + src;
	            attribute.setValue(src);
	         } 
	         else {
	        	 Matcher m = schemePattern.matcher(src);
         		if (m.matches() == false)  { // not match any URI scheme, so we use default
         			src = HyConfig.getImgDomain()+ prefix + svpath +  src;
         			attribute.setValue(src);
         		}
	         }
	        
        }
        
       k = doc.selectNodes("//@style");
        for (Iterator iter = k.iterator(); iter.hasNext();) {
	        Attribute attribute = (Attribute) iter.next();
	        String src = attribute.getValue().trim();
	        if(src.contains("url(")){
	        	String url = src.substring(src.indexOf('(')+1, src.indexOf(')'));
	        	if (url.startsWith("/")) {
		            String newurl = HyConfig.getImgDomain()+ prefix + url;
		            String str=src.replace(url, newurl);
		            attribute.setValue(str);
		         } else if (url.startsWith("http://") == false) { // maybe, this is the relative path
		        	 String newurl = HyConfig.getImgDomain()+ prefix + svpath +  url;
		        	 String str=src.replace(url, newurl);
		        	 attribute.setValue(str);
		         }
	        }
        }
        
	    k = doc.selectNodes("//@href");
	    for (Iterator iter = k.iterator(); iter.hasNext();) {
	        Attribute attribute = (Attribute) iter.next();
	        String url = attribute.getValue().trim();
	        if (url.endsWith(".css")) {
	        	if (url.startsWith("/")) {
	        		url = HyConfig.getImgDomain()+ prefix+url;
	        		attribute.setValue(url);
	        	} else if (url.startsWith("http://") == false && url.startsWith("https://")==false) {
	        		url = HyConfig.getImgDomain()+ prefix + svpath +  url;
	        		attribute.setValue(url);
	        	}
	        }
	        
	    }
        
       if (kind == ISFILE)
    	   return;
       
       k = doc.selectNodes("//@href");
        for (Iterator iter = k.iterator(); iter.hasNext();) {
	        Attribute attribute = (Attribute) iter.next();
	        String url = attribute.getValue().trim();
	        //logger.info(url);
            if (url.startsWith("#") == false && url.startsWith("mailto")==false) {
            	
            	if (url.startsWith("/")) {
            		url = prefix+url;
            		attribute.setValue(url);
            	} else if (url.startsWith("http://") == false && url.startsWith("https://")==false) {
            		url = prefix + svpath +  url;
            		//logger.info("R: " + url);
            		attribute.setValue(url);
            	}
            }
	        
        }
	}
	
	
	private  static String outputHTML(Document doc) throws Exception {
		
		StringWriter sw = new StringWriter();
	    OutputFormat format = OutputFormat.createPrettyPrint();
	    format.setSuppressDeclaration(true);
	  
	    format.setOmitEncoding(true);
	    format.setNewlines(true);
	    format.setTrimText(true);
	    format.setExpandEmptyElements(true);
	    

	    HTMLWriter writer = new HTMLWriter(sw, format);
	    
	    writer.write(doc);
	    writer.flush();
	    
	    return replaceXml2HtmlTokens(sw.toString());
	}
	
	public static org.dom4j.Document createDocument(String text, boolean title) throws Exception {
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties prop = cleaner.getProperties();
		prop.setUseCdataForScriptAndStyle(false);
		if (title)
			prop.setPruneTags("script,title");
		else
			prop.setPruneTags("script");
		prop.setOmitXmlDeclaration(true);
		prop.setOmitDoctypeDeclaration(false);
		
		TagNode tn = cleaner.clean(text);
		
		DomSerializer xs = new DomSerializer(prop);
		org.w3c.dom.Document w3cDoc = xs.createDOM(tn);
		
		DOMReader reader = new DOMReader();
		
		org.dom4j.Document dom4jDoc = reader.read(w3cDoc);
		return dom4jDoc;
		
	}
	
	public void init() {
		
		if (this.escapeElements == null) {
			return;
		}
		
		String[] tokens = StringUtils.delimitedListToStringArray(this.escapeElements, ":");
		
		if (tokens == null) return;
	
		if (tokens.length%2 != 0) 
			throw new GenericServiceException("parameters " + this.escapeElements  + " is not correct, must be even");
		
		int i=0;
		xmls = new ArrayList();
		htmls = new ArrayList();
		while (i < tokens.length) {
			xmls.add(tokens[i]);
			htmls.add(tokens[i+1]);
			i = i+2;
		}
		
	}

	public static String replaceXml2HtmlTokens(String html) {
		if (html == null) return  null;
		
		for (int i=0; i<xmls.size(); i++) {
			html = html.replaceAll((String)xmls.get(i), (String)htmls.get(i));
		}
		
		return html;
		
	}
	
	
	public String getEscapeElements() {
		return escapeElements;
	}



	public void setEscapeElements(String escapeElements) {
		this.escapeElements = escapeElements;
	}



	public static void main(String[] args) throws Exception {
		//String url = "http://www.elong.com/promotion/web/edm/201009/acquisition100925_ace_e.html?emailid=crm:acquisition:20100925";
		//String url = "http://www.tiantian.com";
		//String url = "http://localhost:8080/win/email/view.html?id=10";
		//String url = "http://links.techwebnewsletters.com/servlet/MailView?ms=MzU4MjE2MzMS1&r=MTI2ODcyNjI1NQS2&j=ODI3MTk3OTgS1&mt=1&rt=0";
		String url = "http://www.xici.net/d223721609.htm";
		//System.out.println(getHTML(url));
		//getHTML(url);
		for(int i=0;i<50000;i++){
			UrlReader ur = new UrlReader();
			Thread.sleep(100);
			ur.init();
			ur.getHTML(url);
		}
	}

}
