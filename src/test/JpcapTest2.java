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
		           captor = JpcapCaptor.openDevice(devices[i], 65535, false, 20);  // ����һ����ָ���豸�����Ӳ����ظ����� 
		            
		        //   captor.setFilter("arp", true);                  //���˵õ���Ҫ�� ARP �� 
		         } catch (IOException e) { 
		         System.out.println(e.getMessage()); 
		         } 

		         Runnable runnable = new JpcapTest2(); 
		         Thread thread = new Thread(runnable);            //����һ�����̼߳��� ARP ���� 
		         thread.setName("thread1"); 
		         thread.setPriority(6); 
		         thread.start(); 

		         Scanner s = new Scanner(System.in);         //�� main �߳��� , ������ "exit" ����ֹͣ���� 
		         String input = s.next(); 
		         if (input.equals("exit")) 
		             System.exit(0); //normal exit 
		         } 
		 
		 
		    public void run() {                                     //���߳�thread1����ʱ���õķ��� 
		         while (bl) { 
		           captor.processPacket(1, handler);                //���������� ARP �� 
		         } 
		    } 

		 
		    public static int nic() { 
		        devices = JpcapCaptor.getDeviceList();                // ����һ�������豸�б� 
		        for (int i = 0; i < devices.length; i++) {            //��ӡ��ѡ�豸��������Ϣ 
		             System.out.println(i + ". > " + "NIO_gen_eth: " + devices[i].name); 
		            for (NetworkInterfaceAddress a : devices[i].addresses) { 
		                System.out.println("   IP address:" + a.address); 
		            } 
		        } 

		        System.out.print("> Choose the NIC you want to use: "); 
		        try { 
		            str = in.readLine();                            //�������ֲ�ѡ������ 
		        } catch (IOException e) { 
		        System.out.println(e.getMessage()); 
		        } 
		        i = Integer.parseInt(str); 
		        return i; 
		} 

		 
		     private static PacketReceiver handler = new PacketReceiver() { 
		         public void receivePacket(Packet packet) {          //�����࣬ÿ����һ�����͵��ô˷��� 
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
//		             System.out.println("Ӳ������       " + ((ARPPacket) packet).hardtype); 
//		             System.out.println("��������       " + ((ARPPacket) packet).operation); 
//		             System.out.println("Դ MAC ��ַ " + ((ARPPacket) packet).getSenderHardwareAddress());   
//		             System.out.println("Դ IP ��ַ   " + ((ARPPacket) packet).getSenderProtocolAddress());   
//		             System.out.println("Ŀ�� MAC ��ַ    " + ((ARPPacket) packet).getTargetHardwareAddress()); 
//		              System.out.println("Ŀ�� IP ��ַ     " + ((ARPPacket) packet).getTargetProtocolAddress()); 
		        	 
//		              System.out.println("�汾��:        " + ((IPPacket)packet).version+"");
//		              System.out.println("�ײ�����:      " + packet.header.length +"");
//		              System.out.println("�ܳ���:        " + ((IPPacket)packet).len + "");
//		              System.out.println("��ʾ�ֶ�:      " + (((IPPacket)packet).dont_frag == true ? "�ֶ�" : "���ֶ�"));
//		              System.out.println("��־�ֶ�:      " + ((IPPacket)packet).ident + "");
//		              System.out.println("ƫ����:        " + ((IPPacket)packet).offset +"");
//		              System.out.println("TTL�ֶ�:        " + ((IPPacket)packet).hop_limit+"");
//		              System.out.println("Э������:      " + ((IPPacket)packet).protocol);
//		              System.out.println("У���:        " + "");
//		              System.out.println("ԴIP��ַ:      " + ((IPPacket)packet).src_ip.toString());
//		              System.out.println("Ŀ��IP��ַ:    " + ((IPPacket)packet).dst_ip.toString());
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
