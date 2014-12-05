package test;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpClientOA {
	private static final Log logger = new Log();
	private static String url = "http://192.168.1.180:81/oa/Login.aspx?zx=1";
	private static boolean proxySet = false;
	private static String proxyHost = "";
	private static int proxyPort = 80;
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 1000; i++) {
			RunableTest t = new RunableTest();
			t.run();
		}
//		HttpClientOA test = new HttpClientOA();
//		test.testOA();
	}
	
	public void testOA(){
		HttpClient httpClient = new HttpClient();
		if(proxySet) {
			httpClient.getHostConfiguration().setProxy(proxyHost, proxyPort);
		}
		PostMethod postMethod = new PostMethod(url);
		try {
			//设置请求参数
			NameValuePair[] parame = {
					new NameValuePair("__LASTFOCUS", ""),
					new NameValuePair("__VIEWSTATE", "/wEPDwULLTIwNzQ3MzU0MDgPZBYCAgMPZBYEAgUPFgIeB1Zpc2libGVoFgICAQ8PFgIeBFRleHRlZGQCCg8PFgIfAWVkZBgBBR5fX0NvbnRyb2xzUmVxdWlyZVBvc3RCYWNrS2V5X18WAQUHQ2hrVXNlci3eCOeZ8IFVb5hsuGQH6vLGsNkG"),
					new NameValuePair("__EVENTTARGET", ""),
					new NameValuePair("__EVENTARGUMENT", ""),
					new NameValuePair("__EVENTVALIDATION", "/wEWBQLSucGACwKvo8HwCwKG85bvBgLAiqigBwKZwO3DDVj74LKVxG+kJ3Fgu+gaKucIJk01"),
					new NameValuePair("Account", "441846508"),
					new NameValuePair("PWD", "yuquan0405"),
					new NameValuePair("cmdok", "")
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
		//	c =new String(c.getBytes(),"ISO8859-1");
			logger.info("result: "+c);
			
			PostMethod postMethodUser2 = new PostMethod("http://192.168.1.180:81/oa/Default.aspx");
			statusCode = httpClient.executeMethod(postMethodUser2);		
			header = postMethodUser2.getResponseHeaders();
			for (Header h : header) {
				logger.info(h.getName()+":"+h.getValue());
			}
			logger.info("statusCode: "+statusCode); 
		//	System.out.println(postMethodUser2.getResponseBodyAsString());

			
			 
			PostMethod postMethodExit = new PostMethod("http://192.168.1.180:81/oa/other/Logout.aspx?zhuxiao=1");
			statusCode = httpClient.executeMethod(postMethodExit);		
			header = postMethodExit.getResponseHeaders();
			for (Header h : header) {
				logger.info(h.getName()+":"+h.getValue());
			}
			logger.info("Exit statusCode: "+statusCode); 
			System.out.println( postMethodExit.getResponseBodyAsString());
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
