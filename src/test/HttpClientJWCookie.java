package test;

import java.io.IOException;
import java.util.Map;


import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpClientJWCookie {
	
	public static String host = "http://127.0.0.1:8089/njw";
	
	
	private static final Log logger = new Log();
	private static String url = host +"/Logon.do?method=logon";
	private static boolean proxySet = false;
	private static String proxyHost = "";
	private static int proxyPort = 80;
	
	public static void main(String[] args) {
		HttpClientJWCookie jw = new HttpClientJWCookie();
		jw.testJw();
	}
	
	public void testJw(){
		HttpClient httpClient = new HttpClient();
		if(proxySet) {
			httpClient.getHostConfiguration().setProxy(proxyHost, proxyPort);
		}
		PostMethod postMethod = new PostMethod(url);
		try {
			//设置请求参数
			/*
			NameValuePair[] parame = {
					new NameValuePair("USERNAME", "admin"),
					new NameValuePair("PASSWORD", "admin_123"),
					new NameValuePair("RANDOMCODE", ""),
					new NameValuePair("useDogCode", "")
			}; 
			postMethod.setRequestBody(parame);// 将表单的值放入postMethod中
			int statusCode2 = httpClient.executeMethod(postMethod);
			
			Header[] header2 = postMethod.getResponseHeaders();
			for (Header h : header2) {
				logger.info(h.getName()+":"+h.getValue());
			}
			logger.info("statusCode: "+statusCode2); 
			String c = postMethod.getResponseBodyAsString();
			c =new String(c.getBytes(),"ISO8859-1");
			logger.info("result: "+c);
			
			*/
		 
			PostMethod postMethodUser = new PostMethod(host +"/Logon.do?method=logonBySSO");
		
			HostConfiguration hconfig = new HostConfiguration();
			HttpState hstate = new HttpState();
		//	Cookie cookie = new Cookie("JSESSIONID", "DA2D12165713D594F95D53F2FBAF75DE");
			Cookie cookie = new Cookie("127.0.0.1","JSESSIONID", "E2B02A69D10E1A2FBE427A589BE0B802","/njw",null,false);
			hstate.addCookie(cookie);
			int statusCode = httpClient.executeMethod(hconfig,postMethodUser,hstate);
		
			Header[] header = postMethodUser.getResponseHeaders();
//			postMethodUser.set
			for (Header h : header) {
				logger.info(h.getName()+":"+h.getValue());
			}
			System.out.println("statusCode:"+statusCode);
			System.out.println(postMethodUser.getResponseBodyAsString());
			//http://127.0.0.1:8089/njw/purview.do?method=findManagerUserdata
			/*
			
			NameValuePair[] parame2 = {
					new NameValuePair("cbsmc", " ' or 1=1 or '' = ")
					,new NameValuePair("cbsjc", "")
					,new NameValuePair("lxr", "")
					,new NameValuePair("lxdh", "")
					,new NameValuePair("cbsdz", " ")
			};
			PostMethod postMethodUser2 = new PostMethod(host +"/jcgl.do?method=jcgl_jccbs_find");
			postMethodUser2.setRequestBody(parame2);
			statusCode = httpClient.executeMethod(postMethodUser2);		
			header = postMethodUser2.getResponseHeaders();
			for (Header h : header) {
				logger.info(h.getName()+":"+h.getValue());
			}
			System.out.println("statusCode:"+statusCode);
			System.out.println(postMethodUser2.getResponseBodyAsString());
//			
			*/

			System.out.println("end!");
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			logger.error("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}  finally {
			// 释放连接
			postMethod.releaseConnection();
		}
	}
}
