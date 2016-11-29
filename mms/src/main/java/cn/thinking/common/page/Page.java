package cn.thinking.common.page;


/**
 * Description:分页方法类
 * @update [修改人] [修改时间]
 * @version 1.0
 */

public class Page {
	private int showCount = 10; //每页显示记录数
	private int totalPage;		//总页数
	private int totalResult;	//总记录数
	private int currentPage;	//当前页
	private int currentResult;	//当前记录起始索引
	private boolean entityOrField;	//true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr;		//最终页面显示的底部翻页导航，详细见：getPageStr();
	
	public Page(){
	}
	
	public Page(int currentPage, int showCount) {
		this.currentPage = currentPage;
		this.showCount = showCount;
	}
	
	public int getTotalPage() {
		if(totalResult%showCount==0){
			totalPage = totalResult/showCount;
		} else{ 
			totalPage = totalResult/showCount+1;
		}
		if(totalPage <= 0){
			totalPage = 1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public int getCurrentPage() {
		if(currentPage<=0)
			currentPage = 1;
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}
	public int getShowCount() {
		return showCount;
	}
	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}
	public int getCurrentResult() {
		currentResult = (getCurrentPage()-1)*getShowCount();
		if(currentResult<0)
			currentResult = 0;
		return currentResult;
	}
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public boolean isEntityOrField() {
		return entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}	
	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
//		if(totalResult>0){
//			sb.append("	<ul>\n");
//			if(currentPage==1){
//				sb.append("	<li class=\"pageinfo\">首页</li>\n");
//				sb.append("	<li class=\"pageinfo\">上页</li>\n");
//			}else{	
//				sb.append("	<li><a href=\"#@\" onclick=\"nextPage(1)\">首页</a></li>\n");
//				sb.append("	<li><a href=\"#@\" onclick=\"nextPage("+(currentPage-1)+")\">上页</a></li>\n");
//			}
//			
//			if(currentPage>=getTotalPage()){
//				sb.append("	<li class=\"pageinfo\">下页</li>\n");
//				sb.append("	<li class=\"pageinfo\">尾页</li>\n");
//			}else{
//				sb.append("	<li><a href=\"#@\" onclick=\"nextPage("+(currentPage+1)+")\">下页</a></li>\n");
//				sb.append("	<li><a href=\"#@\" onclick=\"nextPage("+getTotalPage()+")\">尾页</a></li>\n");
//			}
//			
//			int totalPage = getTotalPage();
//			sb.append("	<li class=\"pageinfo\">第<input type=\"text\" id=\"page.currentPage\" value=\""+(currentPage>totalPage?totalPage:currentPage)+"\" onchange=\"currentPageChange(this)\">页/"+getTotalPage()+"页</li>\n");
//			
//			sb.append("	<li class=\"pageinfo\">\n");
//			sb.append("	每页显示<select id=\"page.showCount\" onchange=\"showCountChange()\">\n");
//			sb.append("	<option value=\"10\" selected=\"selected\">10</option>\n");
//			sb.append("	<option value=\"20\">20</option>\n");
//			sb.append("	<option value=\"50\">50</option>\n");
//			sb.append("	<option value=\"100\">100</option>\n");
//			sb.append("	</select>条\n");
//			sb.append("	</li>\n");
//			sb.append("</ul>\n");
//			sb.append("	<li class=\"pageinfo\">\n");
//			sb.append("	每页显示<select id=\"page.showCount\" onchange=\"showCountChange()\">\n");
//			if(getShowCount() <= 10){
//				sb.append("	<option value=\"10\" selected=\"selected\">10</option>\n");
//			} else {
//				sb.append("	<option value=\"10\">10</option>\n");
//			}
//			if(getShowCount() == 20){
//				sb.append("	<option value=\"20\" selected=\"selected\">20</option>\n");
//			} else {
//				sb.append("	<option value=\"20\">20</option>\n");
//			}
//			if(getShowCount() == 50){
//				sb.append("	<option value=\"50\" selected=\"selected\">50</option>\n");
//			} else {
//				sb.append("	<option value=\"50\">50</option>\n");
//			}
//			if(getShowCount() == 100){
//				sb.append("	<option value=\"100\" selected=\"selected\">100</option>\n");
//			} else {
//				sb.append("	<option value=\"100\">100</option>\n");
//			}
//			sb.append("	</select>条\n");
//			sb.append("	</li>\n");
//			sb.append("</ul>\n");
//			
//			sb.append("<script type=\"text/javascript\">\n");
//			sb.append("function showCountChange(){\n");
//			sb.append("	var currentPage = document.getElementById(\"page.currentPage\").value;\n");
//			sb.append("	nextPage(currentPage);\n");
//			sb.append("}\n");
//			
//			sb.append("function currentPageChange(page){\n");
//			sb.append("	var currentPage = document.getElementById(\"page.currentPage\").value;\n");
//			sb.append("	var totalPage = "+getTotalPage()+";\n");
//			sb.append("	if(!isNaN(currentPage) && currentPage>0 && currentPage<=totalPage){ \n");
//			sb.append("		nextPage(currentPage);\n");
//			sb.append("	}\n");
//			sb.append("}\n");
//
//			sb.append("function nextPage(page){");
//			sb.append("	if(true && document.forms[0]){\n");
//			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
//			sb.append("		if(url.indexOf('?')>-1){url += \"&page.currentPage=\";}\n");
//			sb.append("		else{url += \"?page.currentPage=\";}\n");
//			sb.append("		url = url+page;\n");
//			sb.append("		var showCount = document.getElementById(\"page.showCount\").value;\n");
//			sb.append("		url=url+\"&showCount=\"+showCount;\n");
//			sb.append("		document.forms[0].action = url;\n");
//			sb.append("		document.forms[0].submit();\n");
//			sb.append("	}else{\n");
//			sb.append("		var url = document.location+'';\n");
//			sb.append("		if(url.indexOf('?')>-1){\n");
//			sb.append("			if(url.indexOf('currentPage')>-1){\n");
//			sb.append("				var reg = /currentPage=\\d*/g;\n");
//			sb.append("				url = url.replace(reg,'currentPage=');\n");
//			sb.append("			}else{\n");
//			sb.append("				url += \"&page.currentPage=\";\n");
//			sb.append("			}\n");
//			sb.append("		}else{url += \"?page.currentPage=\";}\n");
//			sb.append("		url = url+page;\n");
//			sb.append("		var showCount = document.getElementById(\"page.showCount\").value;\n");
//			sb.append("		url=url+\"&showCount=\"+showCount;\n");
//			sb.append("		document.location = url;\n");
//			sb.append("	}\n");
//			sb.append("}\n");
//			sb.append("</script>\n");
//		}
		pageStr = sb.toString();
		return pageStr;
	}
	
}
