import java.io.*;
import java.util.*;
import java.net.*;

public class Main2 {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(10010);
      while (true) {
		System.out.println("下载服务器启动");
		Socket socket = serverSocket.accept();
		System.out.println("客户端已连接");
		Thread thread = new ServerThread2(socket);
		thread.start();
	  }
    } catch (Exception e) {
		e.printStackTrace();
	}
  }
  // 关闭资源
  // 什么时候关服务器，什么时候关客户端
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
		System.out.println("已发送 " + bytes.length + " 个字节");
		is.close();
		fis.close();
		os.close();
		System.out.println("finished");
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
}