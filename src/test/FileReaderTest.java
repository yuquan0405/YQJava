package test;
import java.io.*;

public class FileReaderTest {

	public static void main(String[] args) {
	//	FileReaderTest.writeTest("tt");
		readTest();
		
	}
	
	
	public static void readTest(){
		String fileName = "F:\\logs (1)\\logs\\catalina.out";
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			boolean flag = false;
			long i = 0;
			while ((tempString = reader.readLine()) != null) {
				i++;
				// 显示行号
				if(tempString.toLowerCase().indexOf("delete from cj0708 ".toLowerCase()) > 0){
//					System.out.println(tempString);
//					System.out.println();
//					line++;
					System.out.println(i+":"+tempString);
					flag = true;
//					break;
				}
//				if(i > 2853000){//2853289
//					line++;
//					System.out.println(tempString);
//					if(line > 9000 ){
//						break;
//					}
//				}
				
//				if(line >= 100){
//					break;
//				}
			}
			System.out.println("count:"+line);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}
	
	public static void writeTest(String msg){
		String fileName = "C:\\test.log";
		File file = new File(fileName);
		BufferedWriter w = null;
		try {
			w = new BufferedWriter(new FileWriter(file,true));
			String tempString = null;
			w.write(msg); 
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (w != null) {
				try {
					w.close();
				} catch (IOException e1) {
				}
			}
		}
	}

}
