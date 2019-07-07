import java.io.*;
import java.util.*;
import java.net.*;

public class Main {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(12345);
      while (true) {
		System.out.println("�ϴ�����������");
		Socket socket = serverSocket.accept();
		System.out.println("�ͻ���������");
		Thread thread = new ServerThread(socket);
		thread.start();
	  }
    } catch (Exception e) {
		e.printStackTrace();
	}
  }
  // �ر���Դ
  // ʲôʱ��ط�������ʲôʱ��ؿͻ���
}

class ServerThread extends Thread {
  Socket socket = null;
  public ServerThread(Socket socket) {
    this.socket = socket;
  }
  @Override
  public void run() {
	  try {
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		String filename = dis.readUTF();
		long filesize = dis.readLong();
		FileOutputStream fos = new FileOutputStream(new File(filename));
		for (long i = 0; i < filesize; ++i){
			int a = is.read();
			fos.write(a);
		}
		is.close();
		fos.close();
		socket.close();
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
}