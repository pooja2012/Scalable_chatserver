package ChatApplication;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;

public class Server {

	  // The server socket.
	  private static ServerSocket serverSocket = null;
	  // The client socket.
	  private static Socket clientSocket = null;

	  // This chat server can accept up to maxClientsCount clients' connections.
	  private static final int maxClientsCount = 100;
	  private static final clientThread[] threads = new clientThread[maxClientsCount];
	  // The default port number.
	    static int portNumber = 2222;

	  public static void main(String args[]) {

		  System.out.println("Server Ready to start");
	    /*if (args.length < 1) {
	      System.out.println("Usage: java MultiThreadChatServerSync <portNumber>\n"
	          + "Now using port number=" + portNumber);
	    } else {
	      portNumber = Integer.valueOf(args[0]).intValue();
	    }*/

	    /*
	     * Open a server socket on the portNumber (default 2222). Note that we can
	     * not choose a port less than 1023 if we are not privileged users (root).
	     */
	    try {
	      serverSocket = new ServerSocket(portNumber);
	      Scanner scan = new Scanner(System.in);
	      if(scan.nextLine().equalsIgnoreCase("HELO BASE_TEST")){
	    	  System.out.println("HELO BASE_TEST");
	    	  System.out.println("IP : "+serverSocket.getLocalSocketAddress());
	    	  System.out.println("Port : "+serverSocket.getLocalPort());
	    	  System.out.println("StudentID : TESTSERVER1234");
	      }
	    } catch (IOException e) {
	      System.out.println(e);
	    }

	    /*
	     * Create a client socket for each connection and pass it to a new client
	     * thread.
	     */
	    while (true) {
	      try {
	        clientSocket = serverSocket.accept();
	        int i = 0;
	        for (i = 0; i < maxClientsCount; i++) {
	          if (threads[i] == null) {
	            (threads[i] = new clientThread(clientSocket, threads)).start();
	            break;
	          }
	        }
	        if (i == maxClientsCount) {
	          PrintStream os = new PrintStream(clientSocket.getOutputStream());
	          os.println("Server too busy. Try later.");
	          os.close();
	          clientSocket.close();
	        }
	      } catch (IOException e) {
	        System.out.println(e);
	      }
	    }
	  }
	}

	/*
	 * The chat client thread. This client thread opens the input and the output
	 * streams for a particular client, ask the client's name, informs all the
	 * clients connected to the server about the fact that a new client has joined
	 * the chat room, and as long as it receive data, echos that data back to all
	 * other clients. The thread broadcast the incoming messages to all clients and
	 * routes the private message to the particular client. When a client leaves the
	 * chat room this thread informs also all the clients about that and terminates.
	 */
	class clientThread extends Thread {

	  private String clientName = null;
	  private DataInputStream is = null;
	  private PrintStream os = null;
	  private Socket clientSocket = null;
	  private final clientThread[] threads;
	  private int maxClientsCount;
	  private long clientID=1134567890;
	  private String chatRoom="1";

	  public clientThread(Socket clientSocket, clientThread[] threads) {
	    this.clientSocket = clientSocket;
	    this.threads = threads;
	    maxClientsCount = threads.length;
	  }

	  @SuppressWarnings("deprecation")
	public void run() {
	    int maxClientsCount = this.maxClientsCount;
	    clientThread[] threads = this.threads;

	    try {
	      /*
	       * Create input and output streams for this client.
	       */
	      is = new DataInputStream(clientSocket.getInputStream());
	      os = new PrintStream(clientSocket.getOutputStream());
	      String name;
	      String room;
	      while (true) {
	    	  os.println("JOIN_CHATROOM : ");
	    	  room=is.readLine().trim();
	        os.println("CLIENT_NAME : ");
	        name = is.readLine().trim();
	        if (name.indexOf('@') == -1) {
	          break;
	        } else {
	          os.println("The name should not contain '@' character.");
	        }
	      }
	      
	      /* Welcome the new the client. */
	      clientID=clientID+1;
	      os.println("JOINED_CHATROOM : "+room);
  		  os.println("SERVER_IP : "+this.clientSocket.getInetAddress());
  		  os.println("PORT : "+this.clientSocket.getLocalPort());
  		  os.println("ROOM_REF : " +this.clientSocket.getPort());
  		  os.println("JOIN_ID : "+clientID);
  		  
	      synchronized (this) {
	        for (int i = 0; i < maxClientsCount; i++) {
	          if (threads[i] != null && threads[i] == this) {
	            clientName = "@" + name;
	            chatRoom=room;
	            break;
	          }
	        }
	        
	     
  		  
	        for (int i = 0; i < maxClientsCount; i++) {
	        	
	          if (threads[i] != null && threads[i] != this && threads[i].chatRoom.equalsIgnoreCase(this.chatRoom)) {
	        	  System.out.println("ChatRoom on thread loop: "+threads[i].chatRoom);
		        	System.out.println("ChatRoom on client: "+this.chatRoom);
	            threads[i].os.println(name
	                + " has joined the chat room : "+room);
	          }
	        }
	      }
	    
	      /* Start the conversation. */
	      while (true) {
	        String line = is.readLine();
	        if (line.startsWith("LEAVE_CHATROOM")) {
	          break;
	        }
	        /* If the message is private sent it to the given client. */
	        if (line.startsWith("@")) {
	          String[] words = line.split("\\s", 2);
	          if (words.length > 1 && words[1] != null) {
	            words[1] = words[1].trim();
	            if (!words[1].isEmpty()) {
	              synchronized (this) {
	                for (int i = 0; i < maxClientsCount; i++) {
	                  if (threads[i] != null && threads[i] != this
	                      && threads[i].clientName != null
	                      && threads[i].clientName.equals(words[0])
	                      && threads[i].chatRoom.equalsIgnoreCase(this.chatRoom)) {
	                    threads[i].os.println("<" + name + "> " + words[1]);
	                    /*
	                     * Echo this message to let the client know the private
	                     * message was sent.
	                     */
	                    this.os.println( name + " - > " + words[1]);
	                    break;
	                  }
	                }
	              }
	            }
	          }
	        } else {
	          /* The message is public, broadcast it to all other clients. */
	          synchronized (this) {
	            for (int i = 0; i < maxClientsCount; i++) {
	            	
	              if (threads[i] != null && threads[i].clientName != null && threads[i].chatRoom.equalsIgnoreCase(this.chatRoom)) {
	            	  System.out.println("ChatRoom on thread loop: "+threads[i].chatRoom);
			        	System.out.println("ChatRoom on client: "+this.chatRoom);
	                threads[i].os.println(name + " - > " + line);
	              }
	            }
	          }
	        }
	      }
	      synchronized (this) {
	        for (int i = 0; i < maxClientsCount; i++) {
	          if (threads[i] != null && threads[i] != this
	              && threads[i].clientName != null
	            		  && threads[i].chatRoom.equalsIgnoreCase(this.chatRoom)) {
	            threads[i].os.println("LEAVE_CHATROOM : " + chatRoom);
	          threads[i].os.println("JOIN_ID : " + clientID);
	          threads[i].os.println("CLIENT_NAME : " + name);
	          }
	        }
	      }
	      os.println("*** Bye " + name + " ***");

	      /*
	       * Clean up. Set the current thread variable to null so that a new client
	       * could be accepted by the server.
	       */
	      synchronized (this) {
	        for (int i = 0; i < maxClientsCount; i++) {
	          if (threads[i] == this) {
	            threads[i] = null;
	          }
	        }
	      }
	      /*
	       * Close the output stream, close the input stream, close the socket.
	       */
	      is.close();
	      os.close();
	      clientSocket.close();
	    } catch (IOException e) {
	    }
	  }
	}
       
	

