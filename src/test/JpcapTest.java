package test;

import jpcap.*; 
import jpcap.packet.*;

public class JpcapTest {

	public static void main(String[] args) {

		try {
			NetworkInterface[] devices = JpcapCaptor.getDeviceList();
			for (int i = 0; i < devices.length; i++) {
				System.out.println("datalink_name:" + devices[i].datalink_name);
				System.out.println("datalink_description:"
						+ devices[i].datalink_description);
				System.out.println("description:" + devices[i].description);
				System.out.println("name:" + devices[i].name);
				System.out.println("loopback:" + devices[i].loopback);
				System.out.print("mac_address:");

				for (int j = 0; j < devices[i].mac_address.length; j++) {
					byte b = devices[i].mac_address[j];
					System.out.print(Integer.toHexString(b & 0xff) + ":");
				}
				System.out.println("");
				NetworkInterfaceAddress[] address = devices[i].addresses;
				for (int j = 0; j < address.length; j++) {
					System.out.println("address:" + j + ":"
							+ address[j].address);// IP
					System.out.println("broadcast:" + j + ":"
							+ address[j].broadcast);
					System.out.println("destination:" + j + ":"
							+ address[j].destination);
					System.out.println("subnet:" + j + ":" + address[j].subnet);// 掩码
				}
				
				if(devices.length>1){/*去掉虚拟网卡的处理*/ 
			         JpcapCaptor captor2 = JpcapCaptor.openDevice(devices[i], 65535, false, 20);
			         captor2.loopPacket(-1, new PacketReceiver(){
			        	 public void receivePacket(Packet packet) {
			     			/* 进行简单的处理 */
			     			System.out.println(packet);
			     		}
			         }); 
			         
			    }

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
 
}
