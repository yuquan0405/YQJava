package test;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpClientJW {
	
	public static String host = "http://127.0.0.1:8089/njw";
	
	
	private static final Log logger = new Log();
	private static String url = host +"/Logon.do?method=logon";
	private static boolean proxySet = false;
	private static String proxyHost = "";
	private static int proxyPort = 80;
	
	public static void main(String[] args) {
		for(int i = 0; i < 10000 ; i++){
			HttpClientJW jw = new HttpClientJW();
			jw.testJw();
		}
	}
	
	public void testJw(){
		HttpClient httpClient = new HttpClient();
		if(proxySet) {
			httpClient.getHostConfiguration().setProxy(proxyHost, proxyPort);
		}
		PostMethod postMethod = new PostMethod(url);
		try {
			//设置请求参数
			NameValuePair[] parame = {
					new NameValuePair("USERNAME", "admin"),
					new NameValuePair("PASSWORD", "admin123"),
					new NameValuePair("RANDOMCODE", ""),
					new NameValuePair("useDogCode", "")
			};
		//	postMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			//postMethod.setRequestHeader(header);
			// 将表单的值放入postMethod中
			postMethod.setRequestBody(parame);
			int statusCode = httpClient.executeMethod(postMethod);
			
			Header[] header = postMethod.getResponseHeaders();
			for (Header h : header) {
				logger.info(h.getName()+":"+h.getValue());
			}
			logger.info("statusCode: "+statusCode); 
			String c = postMethod.getResponseBodyAsString();
			c =new String(c.getBytes(),"ISO8859-1");
			logger.info("result: "+c);
			
			/*
			
			PostMethod postMethodUser = new PostMethod(host +"/Logon.do?method=logonBySSO");
			statusCode = httpClient.executeMethod(postMethodUser);
		
			header = postMethodUser.getResponseHeaders();
			for (Header h : header) {
				logger.info(h.getName()+":"+h.getValue());
			}
			System.out.println("statusCode:"+statusCode);
//			System.out.println(postMethodUser.getResponseBodyAsString());
			//http://127.0.0.1:8089/njw/purview.do?method=findManagerUserdata
			
			
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
