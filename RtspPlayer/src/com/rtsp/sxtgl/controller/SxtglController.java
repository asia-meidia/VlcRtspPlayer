/**
 * Copyright(c) MQM Science & Technology Ltd.
 * 秦墨科技有限公司
 */
package com.rtsp.sxtgl.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mqm.frame.common.Converter.DateConverter;
import com.mqm.frame.sys.user.vo.User;
import com.mqm.frame.util.StringUtil;
import com.rtsp.sxtgl.service.ISxtglService;
import com.rtsp.sxtgl.vo.Sxt;

/**
 * <pre>
 * 文件中文描述
 * <pre>
 * @author meihu2007@sina.com
 * 2015年5月27日
 */
@Controller
@RequestMapping(value="/sxtgl")
@SessionAttributes("user")
public class SxtglController {
	
	private static final Logger log = Logger.getLogger(SxtglController.class);
	
	@Resource
	private ISxtglService sxtglService;
	
    @InitBinder  
    protected void initBinder(HttpServletRequest request,  
                                  ServletRequestDataBinder binder) throws Exception {  
        //对于需要转换为Date类型的属性，使用DateEditor进行处理  
        binder.registerCustomEditor(Date.class, new DateConverter());  
    }  
	/**
	 * 进入摄像头管理界面
	 * @return
	 */
	@RequestMapping(value="sxt.do")
	public String main(ModelMap map , HttpServletRequest req){
		return "/front/sxtgl/sxt";
	}
	
	/**
	 * 查询摄像头信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="sxt.do", params="cx")
	public String cx(ModelMap map , Sxt sxt , HttpServletRequest req){
		List list = sxtglService.findList(sxt);
		int count = sxtglService.findListCount(sxt);
		
		return StringUtil.pageListToJson(list, count);
	}
	
	/**
	 * 新增摄像头
	 * @return
	 */
	@RequestMapping(value="sxt.do", params="xz")
	public String xz(ModelMap map, Sxt sxt , HttpServletRequest req){
		sxtglService.insert(sxt);
		return "{\"success\":true,\"msg\":\"新增成功\"}";
	}
	
	/**
	 * 保存年假申请业务
	 * @return
	 */
	@RequestMapping(value="sxt.do", params="xg")
	@ResponseBody
	public String xg(ModelMap map, Sxt sxt , HttpServletRequest req, @ModelAttribute("user") User user){
		sxt.setCjr(user.getLoginId());
		sxtglService.update(sxt);
		return "{\"success\":true,\"msg\":\"修改成功\"}";
	}
	
	/**
	 * 更新年假申请业务
	 * @return
	 */
	@RequestMapping(value="sxt.do", params="sc")
	@ResponseBody
	public String sc(ModelMap map , Sxt sxt , HttpServletRequest req,  @ModelAttribute("user") User user){
		sxt.setXgr(user.getLoginId());
		sxtglService.deleteById(sxt.getId());
		return "{\"success\":true,\"msg\":\"更新成功\"}";
	}
	/**
	 * @param sxtglService the sxtglService to set
	 */
	public void setSxtglService(ISxtglService sxtglService) {
		this.sxtglService = sxtglService;
	}
	
	
	
}
