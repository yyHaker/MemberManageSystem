package cn.thinking.common.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * Description: 测试类:测试BASE64加密解密
 * 
 * ------------------加密前:java12345 加密后:amF2YTEyMzQ1------------------
 * ------------------解密前:amF2YTEyMzQ1 解密后:java12345------------------
 * @update [修改人] [修改时间]
 * @version 1.0
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
    		String str = "java12345";
    		String ret = null;
    		ret = new BASE64Encoder().encode(str.getBytes());
		System.out.println("加密前:" + str + " 加密后:" + ret);
    		str = "amF2YTEyMzQ1";
    		try {
    			ret = new String(new BASE64Decoder().decodeBuffer(str));
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		System.out.println("解密前:" + str + " 解密后:" + ret);

	}

}
