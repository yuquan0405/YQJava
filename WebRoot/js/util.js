/****************************************�������غ���start****************************************/
/**������汾�ж�**/
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

/**�Ƿ�IE6**/
function isIE6() {  
	   var OsObject = ""; 
	   if(navigator.userAgent.indexOf("MSIE 6")>0) {  
	        return true;  
	   }else{
		   	return false;
	   }
}
��

/**ˢ�±�ҳ��**/
function refresh(obj){��
    
	if(obj == null��|| obj == undefined��){ 
		if(getOs() == "chrome"){
			window.location.reload();// �ȸ������Ҫ�ô˷���ˢ��
		}else{
			window.location.href=window.location.href;
		}
		
	}else{
		if(getOs() == "chrome"){
			obj.location.reload();// �ȸ������Ҫ�ô˷���ˢ��
		}else{
			obj.location.href=obj.location.href;
		}		
	}	
}

/**��ַ����������������������**/
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
}��

/****************************************Cookie��غ���start****************************************/
/**����cookie��Ĭ�ϱ���30��**/
function setCookie(name, value,_day) {
	var Days = 30;
	if(day != null ){
		Days = _day;
	}
	var exp = new Date(); 
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	//document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}
/**��ȡcookie**/
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
/**ɾ��һ��cookie**/
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null) {
		//document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
	}
}
/****************************************Cookie��غ���end****************************************/

/****************************************��֤��غ���start****************************************/
/**��֤EMAIL��ʽ**/
function validateEmail(obj){
	if (obj.value == "") {
		return false;
	}
  var emailReg=/^([a-zA-Z0-9_\-\.\+]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
  if (!emailReg.test(obj.value)) {
		alert("E-mail��ʽ������ȷ��ʽ��admin@qq.com");
		obj.value = "";
		obj.focus(); 
		return false;
	}
	return true;
}


/**��֤һ����ַ�Ƿ���IP��ַ**/
function validateIP(obj) { 
	if (obj.value == "") return false;
	var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g 
	if(re.test(obj.value)){ 
		if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256) return true; 
	}
	alert("IP��ַ��ʽ������ȷ��ʽ��192.168.1.1");
	obj.value = "";
	obj.focus();
	return false; 
}

/**����ַ��Ƿ񳬳����ƣ����İ�����**/
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
	   alert(strnew+"���ȹ���������������");
	   obj.focus();    
	   return false;
   }
   else
   {
	   return true;
   }
}

/**��֤�绰��ʽ**/
function validatePhone(obj){
	if (obj.value == "") return false;
    	var phoneReg=/^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/;
    	if (!phoneReg.test(obj.value)) {
		alert("�绰��ʽ����\n\n��ȷ��ʽ�����磺\n�̻���1234-12345678\n�̻����ֻ���1234-12345678-1234\n�ֻ���12345678901");
		obj.value = "";
		obj.focus();
		obj.select();
		return false;
	}
	return true;
}
/****************************************��֤��غ���end****************************************/

/****************************************��Ԫ����غ���start****************************************/
/**�������ƶ��½��б��Option��_objLeft��ID  _objRight��ID**/
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

/**checkboxȫѡ**/
function selectAll(obj,lstName){
	var objs = document.getElementsByName(lstName);
	for(i = 0; i < objs.length; i++){
		objs[i].checked = obj.checked;		
	}
}

/****************************************��Ԫ����غ���end****************************************/
/****************************************����������غ���start****************************************/

/**JS�е�Trim�������滻�ַ����еĿո�**/
function trimStr(s){
	
  return s.replace(/(^\s*)|(\s*$)/g, ""); 
}

/**ֻ�ı���ֻ��Ϊ���ּ�С���� onkeyup="keyUp(this);" onafterpaste="keyUp(this);"**/
function keyUpFloat(obj) {
	 
	obj.value = obj.value.replace(/[^\d\.]/g, '');
}

/**ֻ�ı���ֻ��Ϊ���� onkeyup="keyUp(this);" onafterpaste="keyUp(this);"**/
function keyUpNum(obj) {
	obj.value = obj.value.replace(/\D/g, '');
}
/**
 *	Purpose: �ж������Ƿ���Ϊ����
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
 *	Purpose: �����֤����ȡ����
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
/****************************************����������غ���end****************************************/