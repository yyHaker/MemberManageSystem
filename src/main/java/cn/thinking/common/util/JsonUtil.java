package cn.thinking.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;




/**
 * Created on 2015-4-14
 * <p>Title:       区域卫生信息平台底层框架</p>
 * <p>Description: Json与DateStore之间转换工具类</p>
 * @version        0.1
*/
public class JsonUtil {
	
	private static SerializeConfig mapping = new SerializeConfig();  
    
    static{  
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));  
    }  
    
    /** 
     * 把JSON文本转换为JSONObject或者JSONArray
     */
    public static Object parse(String text){
        return JSON.parse(text); 
    }
    
    /** 
     * 把JSON文本转换为JSONObject
     */
    public static JSONObject parseObject(String text){
        return JSON.parseObject(text);
    }
    
    /** 
     * 把JSON文本转换为JSONArray
     */
    public static JSONArray parseArray(String text){
        return JSON.parseArray(text);
    }
	
	/** 
	 * 
	 * javaBean、list、map等转换为 json字符串
	 */
	public static String objectToJsonString(Object obj){  
        return JSON.toJSONString(obj,mapping);
    }
	
	/** 
	 * 将JavaBean转换为JSONObject或者JSONArray
	 */
	public static Object objectToJson(Object javaObject){
        return JSON.toJSONString(javaObject);
    }
	
	/** 
	 * json字符串转换为javaBean
	 * @param jsonStr 被转换的json字符串
	 * @param clazz 输出实体Bean的class
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static <T> T jsonToObject(String jsonStr,Class<T> clazz){  
		if(jsonStr.equals("")){
			try {
				return clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}else{
			return JSON.parseObject(jsonStr,clazz); 
		}
		return null;
    }
	
	/** 
	 * 根据某一个节点参数的key，将value转换为javaBean
	 * @param jsonStr 被转换的json字符串
	 * @param paramKey 节点参数key
	 * @param clazz 输出实体Bean的class
	 */
	public static <T> T jsonToObjectByKey(String jsonStr,String paramKey,Class<T> clazz){
		return JSON.parseObject(getJsonByKey(jsonStr, paramKey).toString(),clazz);
    }
     
    /** 
     * json字符串转换为javaBean list
     */
    public static <T> List<T> jsonToList(String jsonArrayStr,Class<T> clazz){ 
    	if(!jsonArrayStr.startsWith("[") && !jsonArrayStr.endsWith("]")){
    		jsonArrayStr = "[" + jsonArrayStr + "]";
		}
        return JSON.parseArray(jsonArrayStr, clazz);  
    }  
      
    /** 
     * json字符串转换为map 
     */
    public static <T> Map<String,Object> jsonToMap(String jsonStr){  
        return jsonToObject(jsonStr, Map.class);
    }  
    
    /** 
     * json字符串转换为javaBean map
     */
    public static <T> Map<String,T> jsonToMap(String jsonStr,Class<T> clazz){  
        Map<String,T> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, T>>() {});  
        for (Entry<String, T> entry : map.entrySet()) {  
            JSONObject obj = (JSONObject) entry.getValue();  
            map.put(entry.getKey(), JSONObject.toJavaObject(obj, clazz));  
        }  
        return map;  
    }  
    
    /** 
     * json字符串转换为xml
     */
    public static String jsonToXml(String json){  
        StringReader input = new StringReader(json);  
        StringWriter output = new StringWriter();  
        JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false).repairingNamespaces(false).build();  
        try {  
            XMLEventReader reader = new JsonXMLInputFactory(config).createXMLEventReader(input);  
            XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(output);  
            writer = new PrettyXMLEventWriter(writer);  
            writer.add(reader);  
            reader.close();  
            writer.close();  
        } catch( Exception e){  
            e.printStackTrace();  
        } finally {  
            try {  
                output.close();  
                input.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        if(output.toString().length()>=38){
            return output.toString().substring(39);  
        }  
        return output.toString();  
    }  
    
    /** 
     * xml转换为json字符串
     */
    public static String xmlToJson(String xml){  
        StringReader input = new StringReader(xml);  
        StringWriter output = new StringWriter();  
//        JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true).autoPrimitive(true).prettyPrint(true).build(); 
        JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true).prettyPrint(true).build(); 
        try {  
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(input);  
            XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);  
            writer.add(reader);  
            reader.close();  
            writer.close();  
        } catch( Exception e){  
            e.printStackTrace();  
        } finally {  
            try {  
                output.close();  
                input.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return output.toString();  
    }  
    
    /** 
     * 根据参数Key获取对应value的json格式
     * 
     * @param json 处理的Json字符串
     * @param paramKey  要获取值的参数key
     */
    public static String getJsonByKey(String json,String paramKey){
    	JSONObject jsonObj = parseObject(json);
    	String str = "";
    	if(jsonObj.get(paramKey) != null){
    		str = jsonObj.get(paramKey).toString();
    	}
    	return str;
    }
    
    /**
     * 替换并映射节点name为beanname
     * 
     * @param json
     * @param oldNodeName
     * @param newNodeName
     * @return
     */
    public static String replaceNodeName(String json, String oldNodeName, String beanName){
    	json = json.replaceAll(oldNodeName, beanName);
    	
    	JSONArray array = new JSONArray();
    	List list = new ArrayList();
    	if(JSON.parse(json) instanceof JSONArray){
    		array = JSON.parseArray(json);
    		
    		for(int i=0;i<array.size();i++){
        		JSONObject obj = (JSONObject) array.get(i);
        		
        		if(obj.get(beanName) != null){
	        		if(!obj.get(beanName).toString().startsWith("[")){
	        			if(!obj.get(beanName).equals("")) list.add(obj.get(beanName));
	        			obj.remove(beanName);
	        			obj.put(beanName, list);
	        		}
        		}
        	}
    		return array.toString().replaceAll("\\\\","");
    	}else{
    		JSONObject obj = JSON.parseObject(json);
    		
    		if(obj.get(beanName) != null){
    			if(!obj.get(beanName).toString().startsWith("[")){
        			list.add(obj.get(beanName));
        			obj.remove(beanName);
        			obj.put(beanName, list);
        		}
    		}
    		
    		return obj.toString().replaceAll("\\\\","");
    	}
    	
    }
    
    /**
     * 拼装Json串结构
     * @param parentNodeName 父节点名称
     * @param childNodeName 子节点名称（没有可写null）
     * @param obj 需拼装内容
     */
    public static String appendToJson(String parentNodeName,String childNodeName,Object obj){
    	StringBuffer jsonStr = new StringBuffer();
    	StringBuffer subStr = new StringBuffer();
    	if(!(obj instanceof List)){
			obj = objectToMap(obj);
		}
    	subStr.append("{");
    	if(childNodeName != null){
    		subStr.append(" \""+childNodeName+"\": ").append(objectToJsonString(obj));
    	}else{
    		subStr.append(objectToJsonString(obj));
    	}
    	subStr.append("}");
    	
    	jsonStr.append("{");
    	jsonStr.append(" \""+parentNodeName+"\": ").append(subStr);
    	jsonStr.append("}");
    	
    	return jsonStr.toString();
    	
    }
    
    /**
     * 在指定Json串中某一节点后面，追加子Json串
     * @param jsonStr 原json字符串
     * @param nodeName 要在其后追加内容的节点名称
     * @param addStr 被追加json字符串
     */
    public static String addJsonAfterNode(String jsonStr,String nodeName,String addStr){
    	JSONObject topObj = JSON.parseObject(jsonStr);
    	JSONObject subObj = new JSONObject();
    	for(Entry entry: topObj.entrySet()) {
    		 if(entry.getKey().equals(nodeName)){
    			 topObj.putAll(jsonToMap(addStr));
    		 }else{
    			 subObj = JSON.parseObject(entry.getValue().toString());
    			 for(Entry subEntry: subObj.entrySet()) {
    				 if(subEntry.getKey().equals(nodeName)){
    					
    					subObj.putAll(jsonToMap(addStr));
    				 }
    			 }
    			 entry.setValue(subObj);
    		 }
    		}
    	return topObj.toJSONString();
    }
    
    /**
     * 将Object 转换为  Map
     */
    public static Map<String, Object> objectToMap(Object obj) {  
  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
  
                    map.put(key, value);  
                }  
            }  
        } catch (Exception e) {  
            System.out.println("执行objectToMap方法出错: " + e);
        }  
        return map;  
    }  
    
}
