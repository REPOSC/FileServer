import java.io.*;
import java.util.*;
import java.net.*;

public class Main2 {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(10010);
      while (true) {
		System.out.println("���ط���������");
		Socket socket = serverSocket.accept();
		System.out.println("�ͻ���������");
		Thread thread = new ServerThread2(socket);
		thread.start();
	  }
    } catch (Exception e) {
		e.printStackTrace();
	}
  }
  // �ر���Դ
  // ʲôʱ��ط�������ʲôʱ��ؿͻ���
}

class ServerThread2 extends Thread {
  Socket socket = null;
  public ServerThread2(Socket socket) {
    this.socket = socket;
  }
  @Override
  public void run() {
	  try {
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		DataInputStream dis = new DataInputStream(is);
		String filename = dis.readUTF();
		File file = new File(filename);
		FileInputStream fis = new FileInputStream(file);
		byte []bytes = new byte[(int)file.length()];		
		fis.read(bytes);
		os.write(bytes);
		is.close();
		fis.close();
		os.close();
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
}