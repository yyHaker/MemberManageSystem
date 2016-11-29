package cn.thinking.common.util;
  
import java.util.HashMap;  
import java.util.Map;   

/**
 * 
 * Description: MySQL特殊字符处理类
 * @update [修改人] [修改时间]
 * @version 1.0
 *
 */
public class MySQLEncoder {                
	private static Map<String,String> referencesMap = new HashMap<String,String>();               
	static{              
		referencesMap.put("'","//'");              
		referencesMap.put("/","///");              
	    referencesMap.put("//","////");        
	    referencesMap.put("\\r","\\\r");
	    referencesMap.put("\\n","\\\n");
	    referencesMap.put(" ","// ");              
	    referencesMap.put("/0","///0");              
	    referencesMap.put("/b","///b");              
	    referencesMap.put(" ","// ");              
	    referencesMap.put("/t","///t");              
	    referencesMap.put("/f","///f");          
	}      
	
	//escape sql tag with the source code.          
	public static String encode(String source) {              
		if (source == null)  return "";                            
		StringBuffer sbuffer = new StringBuffer(source.length());                            
		for (int i = 0; i < source.length(); i++) {                  
			String c = source.substring(i,i+1);                                    
			if (referencesMap.get(c) != null) {                      
				sbuffer.append(referencesMap.get(c));                 
			} else {                      
				sbuffer.append(c);                  
			}              
		}
		
		return sbuffer.toString();          
	}      
}    