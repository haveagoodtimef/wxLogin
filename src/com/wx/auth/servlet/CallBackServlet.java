package com.wx.auth.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.auth.util.AuthUtil;

import net.sf.json.JSONObject;

/**
 *1.这是回调地址
 * code 是回传的参数
 */
public class CallBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+AuthUtil.APPID+
					"&secret="+AuthUtil.APPSECRET+
					"&code="+code+
					"&grant_type=authorization_code";
		JSONObject jsonObject=AuthUtil.doGetJson(url);
		String openid=jsonObject.getString("openid");
		String token=jsonObject.getString("access_token");
		String inforUrl="https://api.weixin.qq.com/sns/userinfo?"+
					"access_token="+token+
					 "&openid="+openid+
					 "&lang=zh_CN";
		JSONObject userInfo=AuthUtil.doGetJson(inforUrl);
		System.out.println(userInfo);
		//使用微信用户信息直接登录
		request.setAttribute("info", userInfo);
		request.getRequestDispatcher("/index1.jsp").forward(request, response);
		
		/**
		*userInfo中的信息.
		final String strOpenid =(String)userInfo.get("openid");
		
		//如果有unionid,可以在直接获取..
		final String strUnionid =(String)userInfo.get("unionid");
		
		System.out.println("openid ++>>>>>"+userInfo.get("openid"));
		
		final String nickname = (String)userInfo.get("nickname");
		System.out.println("nickname++>>>"+userInfo.get("nickname"));
		
		final int sex = (int)userInfo.get("sex");
		System.out.println("sex+>>>"+userInfo.get("sex"));
		
		System.out.println(userInfo.get("city")); //城市
		System.out.println(userInfo.get("province"));  //省份
		System.out.println(userInfo.get("headimgurl")); //头像
		*/
	}

	

}
