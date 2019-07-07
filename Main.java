import java.io.*;
import java.util.*;
import java.net.*;

public class Main {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(12345);
      while (true) {
		System.out.println("上传服务器启动");
		Socket socket = serverSocket.accept();
		System.out.println("客户端已连接");
		Thread thread = new ServerThread(socket);
		thread.start();
	  }
    } catch (Exception e) {
		e.printStackTrace();
	}
  }
  // 关闭资源
  // 什么时候关服务器，什么时候关客户端
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