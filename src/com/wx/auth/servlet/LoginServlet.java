package com.wx.auth.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.auth.util.AuthUtil;

/**
 * 引导用户进入接口
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//对应的回调域名是需要跟在公众平台中填写的"网页授权域名"保持一致.
		String backUrl="http://wx.fenghongzhang.com/WeChat2048/CallBackServlet";  //公网上能够访问
		//String backUrl="http://www.fenghongzhang.com/WeChat2048/CallBackServlet";  //公网上能够访问
		String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AuthUtil.APPID+
				    "&redirect_uri="+URLEncoder.encode(backUrl)+
				    "&response_type=code"+
				    "&scope=snsapi_userinfo"+
				    "&state=STATE#wechat_redirect"; //state是页面间相互传参用的.
		response.sendRedirect(url);
	}

	

}
