package test;

import jpcap.*; 
import jpcap.packet.*;
import java.io.*;
import java.util.*;



public class JpcapTest2 implements Runnable {
	static JpcapCaptor captor; 
    static NetworkInterface[] devices; 
    static BufferedReader in = new BufferedReader(new InputStreamReader(  
    System.in));                             
    static int i = 0; 
    static String str; 
    static IPPacket p; 
    static IPPacket ip; 
    static boolean bl = true; 
    
    public static String toHString(byte [] bs){
    	String str = "";
    	for (int j = 0; j < bs.length; j++) {
			byte b = bs[j];
			str += Integer.toHexString(b & 0xff);
			 
		}
    	return str;
    }


	public static void main(String[] args) { 
		         System.out.println(" ..... ARP ...."); 
		         nic();
		         //i = 0;
		         try { 
		           captor = JpcapCaptor.openDevice(devices[i], 65535, false, 20);  // 创建一个与指定设备的连接并返回该连接 
		            
		        //   captor.setFilter("arp", true);                  //过滤得到需要的 ARP 包 
		         } catch (IOException e) { 
		         System.out.println(e.getMessage()); 
		         } 

		         Runnable runnable = new JpcapTest2(); 
		         Thread thread = new Thread(runnable);            //开启一个子线程监听 ARP 报文 
		         thread.setName("thread1"); 
		         thread.setPriority(6); 
		         thread.start(); 

		         Scanner s = new Scanner(System.in);         //在 main 线程中 , ，输入 "exit" 用于停止监听 
		         String input = s.next(); 
		         if (input.equals("exit")) 
		             System.exit(0); //normal exit 
		         } 
		 
		 
		    public void run() {                                     //子线程thread1运行时调用的方法 
		         while (bl) { 
		           captor.processPacket(1, handler);                //监听并捕获 ARP 包 
		         } 
		    } 

		 
		    public static int nic() { 
		        devices = JpcapCaptor.getDeviceList();                // 返回一个网络设备列表 
		        for (int i = 0; i < devices.length; i++) {            //打印可选设备的网卡信息 
		             System.out.println(i + ". > " + "NIO_gen_eth: " + devices[i].name); 
		            for (NetworkInterfaceAddress a : devices[i].addresses) { 
		                System.out.println("   IP address:" + a.address); 
		            } 
		        } 

		        System.out.print("> Choose the NIC you want to use: "); 
		        try { 
		            str = in.readLine();                            //输入数字并选择网卡 
		        } catch (IOException e) { 
		        System.out.println(e.getMessage()); 
		        } 
		        i = Integer.parseInt(str); 
		        return i; 
		} 

		 
		     private static PacketReceiver handler = new PacketReceiver() { 
		         public void receivePacket(Packet packet) {          //匿名类，每捕获一个包就调用此方法 
//		              System.out.println("==================================="); 
		        	 
//		             if(Short.valueOf(ARPPacket.ARP_REQUEST).equals(((ARPPacket) packet).operation)) 
//		             { 
//		            	
//		                 System.out.println("This is arp request message"); 
//		                 System.out.println(new String(packet.data));
//		             } 
//		             if(Short.valueOf(ARPPacket.ARP_REPLY).equals(((ARPPacket) packet).operation)) 
//		            { 
//		                  System.out.println("This is arp reply message"); 
//		                  System.out.println(new String(packet.data));
//		             } 
//		             System.out.println("硬件类型       " + ((ARPPacket) packet).hardtype); 
//		             System.out.println("操作类型       " + ((ARPPacket) packet).operation); 
//		             System.out.println("源 MAC 地址 " + ((ARPPacket) packet).getSenderHardwareAddress());   
//		             System.out.println("源 IP 地址   " + ((ARPPacket) packet).getSenderProtocolAddress());   
//		             System.out.println("目标 MAC 地址    " + ((ARPPacket) packet).getTargetHardwareAddress()); 
//		              System.out.println("目标 IP 地址     " + ((ARPPacket) packet).getTargetProtocolAddress()); 
		        	 
//		              System.out.println("版本号:        " + ((IPPacket)packet).version+"");
//		              System.out.println("首部长度:      " + packet.header.length +"");
//		              System.out.println("总长度:        " + ((IPPacket)packet).len + "");
//		              System.out.println("标示字段:      " + (((IPPacket)packet).dont_frag == true ? "分段" : "不分段"));
//		              System.out.println("标志字段:      " + ((IPPacket)packet).ident + "");
//		              System.out.println("偏移量:        " + ((IPPacket)packet).offset +"");
//		              System.out.println("TTL字段:        " + ((IPPacket)packet).hop_limit+"");
//		              System.out.println("协议类型:      " + ((IPPacket)packet).protocol);
//		              System.out.println("校验和:        " + "");
//		              System.out.println("源IP地址:      " + ((IPPacket)packet).src_ip.toString());
//		              System.out.println("目的IP地址:    " + ((IPPacket)packet).dst_ip.toString());
//		             System.out.println("==================================="); 
		        	  
		        	  if(packet instanceof jpcap.packet.TCPPacket){    
		                  TCPPacket p=(TCPPacket)packet;    
		                  //"192.168.1.184".equals(p.dst_ip) ||  "192.168.1.184".equals(p.src_ip)
		                  String sip = p.src_ip.toString();
		                  if("/192.168.1.184".equals(sip)){
		                	 System.out.println("="+p.src_ip+"=");
		                	  String s="TCPPacket:| dst_ip "+p.dst_ip+":"+p.dst_port    
			                           +"|src_ip "+p.src_ip+":"+p.src_port    
			                           +" |len: "+p.len+" | p.protocol:"+ p.protocol; 
			             
			              System.out.println(s); 
			              FileReaderTest.writeTest(s);
			              try {
			            	  String h = new String(p.header,"UTF-8");
			            	//  h = JpcapTest2.toHString(p.header); 
			            	    
			            	  String c = new String(p.data,"UTF-8");//ISO-8859-1
			            	  //c = JpcapTest2.toHString(p.data); 
			            	  System.out.println(h);
			            	  System.out.println(c);
//			            	  StringBuffer c = new StringBuffer();
//			            	  for(int j = 0; j < p.data.length; j++){
//			            		  c.append( (char) p.data[j]);
//			            	  }
			            	  FileReaderTest.writeTest("\nheader:"+h);;
			            		FileReaderTest.writeTest("\ndata:"+c);;
			            		FileReaderTest.writeTest("\n-----------------------------------------\n");
						} catch (Exception e) { 
							e.printStackTrace();
						}
		                  }
		                 
		              }  
		 
		        } 
		    };


}
