package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Driver.RunTestcaseThread;

public class RecievingThread extends Thread {

		ServerSocket server;
    	int port = 9876;
    	int minutes;
    	ObjectInputStream ois = null;
    	Socket socket = null;
    	RunTestcaseThread rtt = null;
    	ArrayList<String> testCases = new ArrayList<String>();
    	public RecievingThread(ArrayList<String> Cases, int min ) throws IOException{
    		server = new ServerSocket(port);   
    		this.testCases = Cases;
    		this.minutes = min;
    	}
    	public void SetList(){
    		rtt = new RunTestcaseThread(testCases,minutes);
    		rtt.start();
    	}
    	
    	public void run()
    	{
    		
    		String message = "a";
    		int index = 0;
	      while(true)
	      {
	    	  index ++;
	            try {
					socket = server.accept();
					ois = new ObjectInputStream(socket.getInputStream());
		            
					message = (String) ois.readObject();
					if(message.equals("Enemey Ahead")){
							rtt.setEnemeyFlag(true);
					}
					ois.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
	      }
	     
    	}
    	public void EndThread() throws IOException{
			ois.close();
			socket.close();
    	}
}
