package com.framework.filter;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 权限控制Filter
 * @author YuQuan
 * @version 1.0
 * 
 */
public class PermissionFilter implements Filter {
	private Logger log = Logger.getLogger(this.getClass().getName());
	/**
	 * 权限配置路径 
	 */
	private String configPath = "";
	
	/**
	 * 无权限消息提�?
	 */
	private String notPermissionMsg = "您无该操作权限！";
	
	/**
	 * 无权限页面跳�?
	 */
	private String notPermissionPage;
	
	/**
	 * 是否启动缓存
	 */
	private boolean cache;
	
	/**
	 * 不控制权限页面列�?
	 */
	private List<String> notControlList;
	
	
	
	public void destroy() { 
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
	 
		String path;
		try {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			path = request.getRequestURI();
			path = path.replace(request.getContextPath(), ""); 
			log.debug(path);
			if(this.getNotControlList().contains(path)){ 
				chain.doFilter(req, resp);
				return ;
			}
			
			int _notControlListSize = this.getNotControlList().size();
			boolean _notControlFlag = false;
			for(int i = 0; i < _notControlListSize; i++){
				String _notPath = this.getNotControlList().get(i);
				int likePos = -1;
				if(_notPath.startsWith("*")){
					likePos = 1;
					_notPath = _notPath.substring(likePos,_notPath.length()); 
					if(path.length() > likePos && path.endsWith(_notPath)){
						_notControlFlag = true;
						this.getNotControlListAdd(path);
						break;
					}
				}else if(_notPath.endsWith("*")){
					likePos = _notPath.lastIndexOf("*");
					_notPath = _notPath.substring(0,likePos);
					if(path.length() > likePos && path.startsWith(_notPath)){
						_notControlFlag = true;
						this.getNotControlListAdd(path);
						break;
					}
				}
				
			}
			if(_notControlFlag){
				chain.doFilter(req, resp);
				return ;
			}
			
			List _controlList = this.getUserQuanXian(request);
			if(_controlList.contains(path)){
				this.writeLog(path);
				chain.doFilter(req, resp);
				return ;
			}else{
				if(this.getNotPermissionPage() == null || "".equals(this.getNotPermissionPage())){
					this.writeMsg(response, this.getNotPermissionMsg());					
				}else{
					//无权限跳转页�?
					request.getRequestDispatcher(this.getNotPermissionPage()).forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 访问路径日志输出，可以重写方法保存日�?
	 * @param _path
	 */
	protected void writeLog(String _path){
		log.debug("path:"+_path);
	}

	public void init(FilterConfig config) throws ServletException { 
		 
		if(config.getInitParameter("notPermissionPage") != null){
			this.setNotPermissionPage(config.getInitParameter("notPermissionPage"));
		}
		if(config.getInitParameter("notPermissionMsg") != null){
			this.setNotPermissionPage(config.getInitParameter("notPermissionMsg"));
		}
		if(config.getInitParameter("configPath") != null){
			this.setConfigPath(config.getInitParameter("configPath"));
		}
		if("true".equals(config.getInitParameter("cache"))){
			this.setCache(true);
		}
		loadConfig();
	}
	
	/**
	 * 加载权限配置
	 *
	 */
	protected void loadConfig(){
		BufferedReader br = null;
		try {
			if("".equals(this.getConfigPath())){
				this.setConfigPath(PermissionFilter.class.getResource("/").getPath()+ "permission.txt");
			}else{
				this.setConfigPath(PermissionFilter.class.getResource("/").getPath()+ this.getConfigPath()); 
			} 
			File _file = new File(this.getConfigPath());
			if(!_file.exists()){
				log.error("权限配置文件不存�?");
				return ;
			} 
			br = new BufferedReader(new FileReader(_file));
			String _configStr ;
			notControlList = new ArrayList<String>(); 
			while((_configStr = br.readLine()) != null){
				_configStr = _configStr.trim();
				_configStr = new String(_configStr.getBytes("GBK"),"UTF-8");  
				if(!"".equals(_configStr.trim()) && !notControlList.contains(_configStr.trim()) && !_configStr.startsWith("#")){
					notControlList.add(_configStr);
				} 
			}  
		} catch (Exception e) {
			log.error(e.getMessage(),e);			 
		} finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) { 
					e.printStackTrace();
				}
			}
		}
		
	} 
	/**
	 * 消息提示
	 * @param response
	 * @param msg
	 */
	public void writeMsg(HttpServletResponse response,String msg){
		try {
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8"); 
			PrintWriter out = response.getWriter();		
			out.write(msg);
			out.flush();
			out.close();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

	public String getConfigPath() {
		return configPath;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}   
	
	public String getNotPermissionMsg() {
		return notPermissionMsg;
	}

	public void setNotPermissionMsg(String notPermissionMsg) {
		this.notPermissionMsg = notPermissionMsg;
	}

	public String getNotPermissionPage() {
		return notPermissionPage;
	}

	public void setNotPermissionPage(String notPermissionPage) {
		this.notPermissionPage = notPermissionPage;
	} 
	
	public boolean isCache() {
		return cache;
	}

	public void setCache(boolean cache) {
		this.cache = cache;
	}

	public List<String> getNotControlList() {
		return notControlList;
	}

	public void setNotControlList(List<String> notControlList) {
		this.notControlList = notControlList;
	}
	
	public void getNotControlListAdd(String url){
		this.notControlList.add(url);
	}
	
	/**
	 * 当前用户�?��可以访问的地�?
	 * @param request
	 * @return
	 */
	public List<String> getUserQuanXian(HttpServletRequest request){
		List<String> urls = new ArrayList<String>();
//		urls.add("/user/add.do");
//		urls.add("/user/edit.do");
		return urls;
	}
	
	public void test(){
		
		String path = "/comm2on/index2.jsp";
		int _notControlListSize = this.getNotControlList().size();
		boolean _notControlFlag = false;
		for(int i = 0; i < _notControlListSize; i++){
			String _notPath = this.getNotControlList().get(i);
			int likePos = -1;
			if(_notPath.startsWith("*")){
				likePos = 1;
				_notPath = _notPath.substring(likePos,_notPath.length()); 
				if(path.length() > likePos && path.endsWith(_notPath)){
					_notControlFlag = true;
					this.getNotControlListAdd(path);
					break;
				}
			}else if(_notPath.endsWith("*")){
				likePos = _notPath.lastIndexOf("*");
				_notPath = _notPath.substring(0,likePos);
				if(path.length() > likePos && path.startsWith(_notPath)){
					_notControlFlag = true;
					this.getNotControlListAdd(path);
					break;
				}
			}
			
		}
		if(_notControlFlag){
			System.out.println("true");
		}else{
			System.out.println("false");
			
		}
	}

	public  static  void main(String [] args){
		PermissionFilter q = new PermissionFilter();
		q.loadConfig();
		q.test();
		q.test(); 
//	System.out.println("123456".substring("123456".indexOf("1"),"123456".indexOf("5")));
		//System.out.println(QuanXainFilter.class.getResource("").getPath());
	}
	
}
