package com.huiyee.esite.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;

public class JsonUtil {

	/**
	 * @param args
	 */
	public static String json2Json(String jsonStr) {
		JSONObject jo = JSONObject.fromObject(jsonStr);
		int count = 0 ;
		List<String> list = new ArrayList<String>();
		Map<String, JSONArray> map = new HashMap<String, JSONArray>();
		for(Object o : jo.keySet()){
			String var = o.toString();
			list.add(var);
			JSONArray ja ;
			try {
				ja = JSONArray.fromObject(jo.get(var));
			} catch (RuntimeException e) {
				count = 1;
				break;
			}
			map.put(var, ja);
			if(count == 0){
				count = ja.size();
				continue;
			}
		}
		if(count <= 1){
			return "{\"list\":["+jsonStr+"]}";
		}
		StringBuffer s = new StringBuffer("[");
		for(int i = 0 ; i < count ; i++){
			StringBuffer sb = new StringBuffer("{");
			for(String st : list){
				String var = st;
				JSONArray ja = map.get(var);
				sb.append('"').append(var).append('"').append(":").append('"').append(ja.get(i)).append('"').append(",");				
			}
			String sbf = sb.substring(0, sb.length()-1)+"}";
			s.append(sbf+",");
		}
		String json = s.substring(0, s.length()-1)+"]";
		
		return  "{\"list\":"+json+"}";
		
	}
	
	public static void main(String[] args) {
		String str="{\"title\":[\"苏格兰独立了吗1\",\"苏格兰独立了吗2\",\"苏格兰独立了吗3\"],\"content\":[\"苏格兰独立了苏格兰独立了苏格兰独立了1\",\"苏格兰独立了苏格兰独立了苏格兰独立了2\",\"苏格兰独立了苏格兰独立了苏格兰独立了3\"],\"img\":[\"1/795/content/picture/201409191339334285.jpg\",\"2/795/content/picture/201409191339334285.jpg\",\"3/795/content/picture/201409191339334285.jpg\"]}";
		String str2 = "{\"list\":[{},{}]}";
		System.out.println(JsonUtil.json2Json(str));
	}
	
	public static Map<String, Object> parseJSON2Map(String jsonStr){  
        Map<String, Object> map = new HashMap<String, Object>();  
        //最外层解析  
        JSONObject json = JSONObject.fromObject(jsonStr);  
        for(Object k : json.keySet()){  
            Object v = json.get(k);   
            //如果内层还是数组的话，继续解析  
            if(v instanceof JSONArray){  
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();  
                Iterator<JSONObject> it = ((JSONArray)v).iterator();  
                while(it.hasNext()){  
                    JSONObject json2 = it.next();  
                    list.add(parseJSON2Map(json2.toString()));  
                }  
                map.put(k.toString(), list);  
            } else {  
                map.put(k.toString(), v);  
            }  
        }  
        return map;  
    } 

}
