package cn.thinking.common.plugin;


 
 import org.apache.ibatis.session.RowBounds;

import cn.thinking.common.page.Page;
 
 public class BindedRowBounds extends RowBounds
 {
   private Page page;
 
   public BindedRowBounds()
   {
   }
 
   public BindedRowBounds(Page page)
   {
     super(page.getCurrentPage()<=1?0:page.getCurrentPage()-1, page.getShowCount());
     this.page = page;
   }
 
   public Page getPage() {
    return this.page;
   }

	public void setPage(Page page) {
		this.page = page;
	}

   
 }