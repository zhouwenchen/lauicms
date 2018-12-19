package org.zwc.cms.constant;

/**
 * cms常量的定义  
* @author zhouwenchen
* @date 2018年12月17日 上午11:52:37 
*/  
public interface CmsEnum {
	/** newsStatus 审核状态*/
	String NEWSSTATUS_PASS = "审核通过";
	String NEWSSTATUS_WAIT_PASS = "待审核";
	String NEWSSTATUS_REFUSE = "审核拒绝";
	
	/** newlook 权限*/
	String NEWSLOOK_OPEN = "开放浏览";
	String NEWSLOOK_CLOSE = "会员浏览";
	
	/** isShow 是否显示*/
	String ISSHOW_YES = "checked";
	String ISSHOW_NO = "";
	
}