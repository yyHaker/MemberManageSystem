package cn.thinking.common.baseeditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 
 * Description: 控制层通用类,业务模块的Controller可继承此类进行方法复用,如:对于ajax交互时处理回写json的处理等...
 * @update [修改人] [修改时间]
 * @version 1.0
 * 
 */
public abstract class BaseController {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	//被@ModelAttribute注释的方法会在此controller每个方法执行前被执行,这里起到初始化的作用
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	/**
	 * 
	 * Discription:贴心的spring，提供了一种重载的方法,为每个变量绑定一个不同的Editor,对于不同的变量进行不同的处理
	 * 
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		// binder.registerCustomEditor(int.class, new
		// CustomNumberEditor(int.class, true));
		binder.registerCustomEditor(int.class, new IntegerEditor());
		// binder.registerCustomEditor(long.class, new
		// CustomNumberEditor(long.class, true));
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(float.class, new FloatEditor());
		binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
	}
	
	/**
	 * 分页对象
	 * @param binder
	 */
//    @InitBinder("page")  
//    public void initBinderPage(WebDataBinder binder) {    
//        binder.setFieldDefaultPrefix("page.");    
//    } 

	/**
	 * 向客户端输出字符串流
	 * 
	 * @param response
	 * @param string
	 * @throws IOException
	 */
	protected void responseWriter(HttpServletResponse response, String data) {
		PrintWriter writer = null;
		try {
			response.setContentType("application/json;charset=utf-8");
			writer = response.getWriter();
			writer.write(data);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				writer.flush();
				writer.close();
			}
		}
	}

	/**
	 * 返回调用成功Json串
	 * 
	 * @param obj
	 *            传入对象
	 * @return string 返回带有成功标记的json字符串
	 */
	/*protected String successJson(Object obj) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", "1");
		jsonObject.put("data", obj);
		return jsonObject.toString();
	}*/

	/**
	 * 返回调用失败Json串
	 * 
	 * @param obj
	 *            传入对象
	 * @return string 返回带有失败标记的json字符串
	 */
	/*protected String errorJson(String error) {
		if (null == error || "".equals(error)) {
			error = "加载数据错误";
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", "-1");
		jsonObject.put("error", error);
		return jsonObject.toString();
	}*/
	
	
	/**
	 * 返回调用成功Json串(分页)
	 * 
	 * @param obj
	 *            传入对象
	 * @return string 返回带有成功标记的json字符串
	 */
/*	protected String successPageJson(Object obj,Object obj1) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", "1");
		jsonObject.put("data", obj);
		jsonObject.put("condition", obj1);
		return jsonObject.toString();
	}*/

	
	/**
	 * 将request.getInputSream()字节流转换成字符流
	 * @return
	 * @throws IOException e
	 */
	protected String readRequestInputStream() throws  IOException{

			 BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
			 String line = null;
			 StringBuilder sb = new StringBuilder();
			 while ((line = br.readLine()) != null) {
				 sb.append(line);
			 }
			 return sb.toString();

	}

}
