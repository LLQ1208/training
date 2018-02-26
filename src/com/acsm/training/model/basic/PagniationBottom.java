package com.acsm.training.model.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页底部自动生成
 * ClassName: PagniationBottom
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(Optional). 
 * Date: 2015年11月09日 
 *
 * @author liuxiaobing
 * @version 
 * @since JDK 1.6
 */
@SuppressWarnings("rawtypes")
public class PagniationBottom {
	
	
	private PageHelper pageHelper;
	
	/**
	 * 分页跳转方法，默认为page的JS方法
	 * function page(currentPage,pageSize)
	 */
	private String pageJSFunction = "page";
	
	public PagniationBottom(PageHelper pageHelper){
		this.pageHelper = pageHelper;
	}
	
	public PagniationBottom(PageHelper pageHelper, String pageJSFunction){
		this.pageHelper = pageHelper;
		this.pageJSFunction = pageJSFunction;
	}

	
	/**
	 * 重新toString方法，用于生成页面元素
	 * TODO
	 * @see Object#toString()
	 */
	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		
		//分页左侧内容 Results Per Page: 10 | 20 | 50
		stringBuilder.append("<div class='pagination input-sm pull-left' style='margin-left:5px'>");
			stringBuilder.append(pageHelper.getStart()+"-"+pageHelper.getEnd()+"/"+pageHelper.getTotalRow());
		stringBuilder.append("</div>");
		
		//分页右侧页码
		stringBuilder.append("<ul class='pagination input-sm pull-right'>");
		stringBuilder.append("<li><a data-currentpage='1' data-pagesize='"+pageHelper.getPageSize()+"' class='page-click' href=\"javascript:\"><i class='fa fa-angle-double-left'></i></a></li>");
		if(pageHelper.getCurrentPage()==1){
			stringBuilder.append("<li class='disabled'><a><i class='fa fa-angle-left'></i></a></li>");
		}else{
			stringBuilder.append("<li><a data-currentpage='"+(pageHelper.getCurrentPage()-1)+"' data-pagesize='"+pageHelper.getPageSize()+"' class='page-click'><i class='fa fa-angle-left'></i></a></li>");
		}
		
		//解析只要显示的页码
		List<String> array = this.generateDisplayPageList(pageHelper);
		for(String pageNumber:array){
			
			
			if(pageNumber.equals("...")){
				stringBuilder.append("<li><a>"+pageNumber+"</a></li>");
			}else{
				if((pageHelper.getCurrentPage()+"").equals(pageNumber)){
					stringBuilder.append("<li class='active'><a data-currentpage='"+pageNumber+"' data-pagesize='"+pageHelper.getPageSize()+"' class='page-click'>"+pageNumber+"</a></li>");
				}else{
					stringBuilder.append("<li><a data-currentpage='"+pageNumber+"' data-pagesize='"+pageHelper.getPageSize()+"' class='page-click'>"+pageNumber+"</a></li>");
				}
			}		
		}
		if(pageHelper.getCurrentPage()==pageHelper.getTotalPage()){
			stringBuilder.append("<li class='disabled'><a><i class='fa fa-angle-right'></i></a></li>");
		}else{
			stringBuilder.append("<li><a data-currentpage='"+(pageHelper.getCurrentPage()+1)+"' data-pagesize='"+pageHelper.getPageSize()+"' class='page-click'><i class='fa fa-angle-right'></i></a></li>");
		}
		stringBuilder.append("<li><a data-currentpage='"+pageHelper.getTotalPage()+"' data-pagesize='"+pageHelper.getPageSize()+"' class='page-click');\"><i class='fa fa-angle-double-right'></i></a></li>");
		stringBuilder.append("<li><a style='padding:2px'><input type='text' value='"+pageHelper.getCurrentPage()+"' style='width: 26px; height: 25px; font-size:12px;text-align: center;' id='gotoPage'></a></li>");
		stringBuilder.append("<li><a data-currentpage='#gotoPage' data-pagesize='"+pageHelper.getPageSize()+"' class='page-click' data-i18n='table.page.go'>Go</a></li>");
		stringBuilder.append("</ul>");
		return stringBuilder.toString();
	}
	
	/**
	 * 提取需要显示的页码
	 * generateDisplayPageList:(Use one sentence to describe this method's function). 
	 * TODO(Use one sentence to describe this method's using scenario – Optional).
	 * TODO(Use one sentence to describe this method's logic).
	 *
	 * @author yuchao
	 * @param pageHelper
	 * @return
	 * @since JDK 1.6
	 */
	private List<String> generateDisplayPageList(PageHelper pageHelper){
		List<String> array = new ArrayList<String>();
		if(pageHelper.getTotalPage()<9){
			for(int i=0;i<pageHelper.getTotalPage();i++){
				array.add(i+1+"");
			}
		}else{
			int halfPage = pageHelper.getTotalPage()/2;
			int firstPage = 1;
			int secondPage = 2;
			int secondLastPage = pageHelper.getTotalPage() -1;
			int lastPage = pageHelper.getTotalPage();
			
			array.add(firstPage+"");
			array.add(secondPage+"");
			
			if(pageHelper.getCurrentPage()>halfPage){
				if(halfPage - secondPage >1){
					array.add("...");
				}
				
				//如果目前已经包含当前页面，则不添加当前页面
				if(!array.contains(halfPage+"")){
					array.add(halfPage+"");
				}				
				
				if(pageHelper.getCurrentPage()-halfPage>1){
					array.add("...");
				}
				
				//如果目前已经包含当前页面，则不添加当前页面
				if(!array.contains(pageHelper.getCurrentPage()+"")&&(pageHelper.getCurrentPage()!=pageHelper.getTotalPage())){
					array.add(pageHelper.getCurrentPage()+"");
				}
				
				
				
				if(secondLastPage-pageHelper.getCurrentPage()>1){
					array.add("...");
				}				
			}else{
				if(pageHelper.getCurrentPage() - secondPage >1){
					array.add("...");
				}
				//如果目前已经包含当前页面，则不添加当前页面
				if(!array.contains(pageHelper.getCurrentPage()+"")){
					array.add(pageHelper.getCurrentPage()+"");
				}
				if(halfPage-pageHelper.getCurrentPage()>1){
					array.add("...");
				}
				//如果目前已经包含当前页面，则不添加当前页面
				if(!array.contains(halfPage+"")){
					array.add(halfPage+"");
				}	
				if(secondLastPage-halfPage>1){
					array.add("...");
				}				
			}
			
			//如果已经包含了此页面，则不添加该页码(当最后一页时， 倒数第二页不增加）
			if(!array.contains(secondLastPage+"")){
				array.add(secondLastPage+"");
			}
			
			if(!array.contains(lastPage+"")){
				array.add(lastPage+"");
			}	
		}
		return array;
	}


	public PageHelper getPageHelper() {
		return pageHelper;
	}

	public void setPageHelper(PageHelper pageHelper) {
		this.pageHelper = pageHelper;
	}


	public String getPageJSFunction() {
		return pageJSFunction;
	}


	public void setPageJSFunction(String pageJSFunction) {
		this.pageJSFunction = pageJSFunction;
	}
}
