/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.mqm.frame.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.mqm.frame.infrastructure.util.ContextUtil;
import com.mqm.frame.sys.role.vo.Role;
import com.mqm.frame.sys.user.vo.User;
import com.mqm.frame.util.constants.BaseConstants;

/**
 * <pre>
 * 程序的中文名称。
 * </pre>
 * @author meihu  meihu@foresee.cn
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class DefaultController {
	
	public User getUser(){
		return (User)ContextUtil.get(BaseConstants.USER_PROFILE,ContextUtil.SCOPE_SESSION);
	}
	
	public boolean isAdmin(){
		User user = (User)ContextUtil.get(BaseConstants.USER_PROFILE,ContextUtil.SCOPE_SESSION);
		List<Role> roles = (List<Role>)user.getRoles();
		for(Role role: roles){
			if("supadmin".equals(role.getCode())){
				return true;
			}
		}
		return false;
	}
	
	protected int getPageIndex(HttpServletRequest req){
		String pageIndex = req.getParameter("page");
		if(StringUtils.isNotEmpty(pageIndex)){
			return Integer.parseInt(pageIndex);
		}
		return 0;
	}
	
	protected int getPageSize(HttpServletRequest req){
		String limit = req.getParameter("limit");
		if(StringUtils.isNotEmpty(limit)){
			return Integer.parseInt(limit);
		}
		return 0;
	}
}
