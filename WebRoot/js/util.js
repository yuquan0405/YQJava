/****************************************浏览器相关函数start****************************************/
/**浏览器版本判断**/
function getBrowerOs() {  
   var OsObject = ""; 
   if(navigator.userAgent.indexOf("MSIE")>0) {  
        return "msie";  
   }  
   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){  
        return "firefox";  
   }  
   if(isSafari=navigator.userAgent.indexOf("Chrome")>0) {  
       return "chrome";  
   }
   if(isSafari=navigator.userAgent.indexOf("Safari")>0) {  
        return "safari";  
   }   
   if(isCamino=navigator.userAgent.indexOf("Camino")>0){  
        return "camino";  
   }  
   if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){  
        return "gecko";  
   }  
    
}

/**是否IE6**/
function isIE6() {  
	   var OsObject = ""; 
	   if(navigator.userAgent.indexOf("MSIE 6")>0) {  
	        return true;  
	   }else{
		   	return false;
	   }
}
　

/**刷新本页面**/
function refresh(obj){　
    
	if(obj == null　|| obj == undefined　){ 
		if(getOs() == "chrome"){
			window.location.reload();// 谷歌浏览器要用此方法刷新
		}else{
			window.location.href=window.location.href;
		}
		
	}else{
		if(getOs() == "chrome"){
			obj.location.reload();// 谷歌浏览器要用此方法刷新
		}else{
			obj.location.href=obj.location.href;
		}		
	}	
}

/**地址加随机变量，解决缓存问题**/
function getRandomUrl(htmlurl){
	var count =htmlurl.indexOf("?");
	var  date=new Date();
	var t=Date.parse(date);    
	if(count<0){
		htmlurl=htmlurl+"?_tktime="+t;
	}else{
		htmlurl=htmlurl+"&_tktime="+t;
	}
	return htmlurl;
}　

/****************************************Cookie相关函数start****************************************/
/**设置cookie，默认保持30天**/
function setCookie(name, value,_day) {
	var Days = 30;
	if(day != null ){
		Days = _day;
	}
	var exp = new Date(); 
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	//document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}
/**获取cookie**/
function getCookie(name) {
	//var arr = document.cookie.match(new RegExp("(^|   )" + name + "=([^;]*)(;|$)"));
	var arr = document.cookie.split(";");
	if (arr != null) {
		for(var i=0;i<arr.length;i++) {
			var theTmp = arr[i].split("=");
			if(name == trimstr(theTmp[0])) 
				return unescape(theTmp[1]);
		}
	}
	return "";
}
/**删除一个cookie**/
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null) {
		//document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
	}
}
/****************************************Cookie相关函数end****************************************/

/****************************************验证相关函数start****************************************/
/**验证EMAIL格式**/
function validateEmail(obj){
	if (obj.value == "") {
		return false;
	}
  var emailReg=/^([a-zA-Z0-9_\-\.\+]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
  if (!emailReg.test(obj.value)) {
		alert("E-mail格式错误！正确格式：admin@qq.com");
		obj.value = "";
		obj.focus(); 
		return false;
	}
	return true;
}


/**验证一个地址是否是IP地址**/
function validateIP(obj) { 
	if (obj.value == "") return false;
	var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g 
	if(re.test(obj.value)){ 
		if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256) return true; 
	}
	alert("IP地址格式错误，正确格式：192.168.1.1");
	obj.value = "";
	obj.focus();
	return false; 
}

/**检查字符是否超出限制（中文包括）**/
function validateStringLength(obj,number,strnew)
{
    var str=obj.value;
    var len2=0;
    for (var i=0; i<str.length; i++) {   
        if (str.charCodeAt(i)>127 || str.charCodeAt(i)==94) {   
            len2 += 2;   
        } else {   
            len2 ++;   
        }   
    }
   if(len2>number)
   {
	   alert(strnew+"长度过长，请重新输入");
	   obj.focus();    
	   return false;
   }
   else
   {
	   return true;
   }
}

/**验证电话格式**/
function validatePhone(obj){
	if (obj.value == "") return false;
    	var phoneReg=/^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/;
    	if (!phoneReg.test(obj.value)) {
		alert("电话格式错误！\n\n正确格式，例如：\n固话：1234-12345678\n固话带分机：1234-12345678-1234\n手机：12345678901");
		obj.value = "";
		obj.focus();
		obj.select();
		return false;
	}
	return true;
}
/****************************************验证相关函数end****************************************/

/****************************************表单元素相关函数start****************************************/
/**左往右移动下接列表的Option；_objLeft左ID  _objRight右ID**/
function moveOption(_objLeft, _objRight, _remainOld)
{
	var objLeft = document.getElementById(_objLeft);
	var objRight = document.getElementById(_objRight);
	
	for (var i = 0; i < objLeft.options.length; i++)
	{
		if(objLeft.options[i].selected)
		{
			objRight.add(new Option(objLeft.options[i].text, objLeft.options[i].value));	
			if (_remainOld == false)
			{
				objLeft.remove(i);
				i--;
			}
		}
	}
}

/**checkbox全选**/
function selectAll(obj,lstName){
	var objs = document.getElementsByName(lstName);
	for(i = 0; i < objs.length; i++){
		objs[i].checked = obj.checked;		
	}
}

/****************************************表单元素相关函数end****************************************/
/****************************************数据类型相关函数start****************************************/

/**JS中的Trim方法，替换字符串中的空格**/
function trimStr(s){
	
  return s.replace(/(^\s*)|(\s*$)/g, ""); 
}

/**只文本框只能为数字及小数点 onkeyup="keyUp(this);" onafterpaste="keyUp(this);"**/
function keyUpFloat(obj) {
	 
	obj.value = obj.value.replace(/[^\d\.]/g, '');
}

/**只文本框只能为数字 onkeyup="keyUp(this);" onafterpaste="keyUp(this);"**/
function keyUpNum(obj) {
	obj.value = obj.value.replace(/\D/g, '');
}
/**
 *	Purpose: 判断输入是否含有为中文
 *	Inputs: String 
 *	Returns: True, False 
 */
function hasChinese(str) 
{ 
	if(escape(str).indexOf("%u")!=-1) 
	{ 
		return true; 
	} 
	return false; 
} 

/**
 *	Purpose: 从身份证号码取生日
 *	Inputs: String 
 *	Returns: String 
 */
function getBirthday(str) 
{ 
	if(str!="")
	{
		if(str.length==15)
		{
			var year = "19" + str.substring(6,8);
			var month = str.substring(8,10);
			var day = str.substring(10,12);
			var retStr = year + "-" + month + "-" + day;
			return retStr;
		}
		else if(str.length==18)
		{
			var year = str.substring(6,10);
			var month = str.substring(10,12);
			var day = str.substring(12,14);
			var retStr = year + "-" + month + "-" + day;
			return retStr;
		}		
	}
	else{
		return "";	
	}
		
} 
/****************************************数据类型相关函数end****************************************/