import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
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

class ChatInfoVO {

	private static final long serialVersionUID=1L;
	private String chatRoom;
	private String clientIP;
	private String port;
	private String clientName;
	private long roomRefId;
	private long joinID;
	private boolean leftChatRoom;
	private boolean joinChatRoom;
	private boolean leaveChatRoom;
	private boolean disconnect;
	private Socket socket;
	private long chatRoomRefID;
	private int joinChatCount;
	private int sendMessageCount;
	private int leaveChatRoomCount;
	private int disconnectCount;
	private HashMap<String, Long> roomList;
	private SendMessageVO messageVO;
	private ChatInfoVO chatInfoVO;
	
	
	
	public ChatInfoVO getChatInfoVO() {
		return chatInfoVO;
	}
	public void setChatInfoVO(ChatInfoVO chatInfoVO) {
		this.chatInfoVO = chatInfoVO;
	}
	public SendMessageVO getMessageVO() {
		return messageVO;
	}
	public void setMessageVO(SendMessageVO messageVO) {
		this.messageVO = messageVO;
	}
	public int getJoinChatCount() {
		return joinChatCount;
	}
	public void setJoinChatCount(int joinChatCount) {
		this.joinChatCount = joinChatCount;
	}
	public int getSendMessageCount() {
		return sendMessageCount;
	}
	public void setSendMessageCount(int sendMessageCount) {
		this.sendMessageCount = sendMessageCount;
	}
	public int getLeaveChatRoomCount() {
		return leaveChatRoomCount;
	}
	public void setLeaveChatRoomCount(int leaveChatRoomCount) {
		this.leaveChatRoomCount = leaveChatRoomCount;
	}
	public int getDisconnectCount() {
		return disconnectCount;
	}
	public void setDisconnectCount(int disconnectCount) {
		this.disconnectCount = disconnectCount;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public long getChatRoomRefID() {
		return chatRoomRefID;
	}
	public void setChatRoomRefID(long chatRoomRefID) {
		this.chatRoomRefID = chatRoomRefID;
	}
	public HashMap<String, Long> getRoomList() {
		return roomList;
	}
	public void setRoomList(HashMap<String, Long> roomList) {
		this.roomList = roomList;
	}
	public int getMaxClientsCount() {
		return maxClientsCount;
	}
	public void setMaxClientsCount(int maxClientsCount) {
		this.maxClientsCount = maxClientsCount;
	}
	private int maxClientsCount;
	
	public String getChatRoom() {
		return chatRoom;
	}
	public void setChatRoom(String chatRoom) {
		this.chatRoom = chatRoom;
	}
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public long getRoomRefId() {
		return roomRefId;
	}
	public void setRoomRefId(long roomRefId) {
		this.roomRefId = roomRefId;
	}
	public long getJoinID() {
		return joinID;
	}
	public void setJoinID(long joinID) {
		this.joinID = joinID;
	}
	public boolean isLeftChatRoom() {
		return leftChatRoom;
	}
	public void setLeftChatRoom(boolean leftChatRoom) {
		this.leftChatRoom = leftChatRoom;
	}
	public boolean isJoinChatRoom() {
		return joinChatRoom;
	}
	public void setJoinChatRoom(boolean joinChatRoom) {
		this.joinChatRoom = joinChatRoom;
	}
	public boolean isLeaveChatRoom() {
		return leaveChatRoom;
	}
	public void setLeaveChatRoom(boolean leaveChatRoom) {
		this.leaveChatRoom = leaveChatRoom;
	}
	public boolean isDisconnect() {
		return disconnect;
	}
	public void setDisconnect(boolean disconnect) {
		this.disconnect = disconnect;
	}
	
}

class SendMessageVO {

	private static final long serialVersionUID=1L;	
	private long chatReference;
	private long joinID;
	private String clientName;
	private String message;
	private int messageCheckCount;
	
	public long getChatReference() {
		return chatReference;
	}
	public void setChatReference(long chatReference) {
		this.chatReference = chatReference;
	}
	public long getJoinID() {
		return joinID;
	}
	public void setJoinID(long joinID) {
		this.joinID = joinID;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getMessageCheckCount() {
		return messageCheckCount;
	}
	public void setMessageCheckCount(int messageCheckCount) {
		this.messageCheckCount = messageCheckCount;
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

	  private DataInputStream is = null;
	  private PrintStream os = null;
	  private Socket clientSocket = null;
	  private final clientThread[] threads;
	  private int maxClientsCount;
	  private long chatRoomRef=1134567890;
	  private HashMap<String, Long> roomList=new HashMap<String, Long>();
	  private ChatInfoVO chatInfoVO=new ChatInfoVO();
	  private int count;

	  public clientThread(Socket clientSocket, clientThread[] threads) {
	    this.clientSocket = clientSocket;
	    this.threads = threads;
	    maxClientsCount = threads.length;
	  }

	  @SuppressWarnings("deprecation")
	public void run() {
	    /*int maxClientsCount = this.maxClientsCount;
	    clientThread[] threads = this.threads;*/

	    try {
	      /*
	       * Create input and output streams for this client.
	       */
	      is = new DataInputStream(clientSocket.getInputStream());
	      os = new PrintStream(clientSocket.getOutputStream());
	     
	  
	      /* Start the conversation. */
	      while (true) {
	    	//  System.out.println("countt : "+count);
	    	  chatInfoVO.setMaxClientsCount(this.maxClientsCount);
	    	  chatInfoVO.setChatRoomRefID(this.chatRoomRef);
	    	  chatInfoVO.setRoomList(this.roomList);
	    	  chatInfoVO.setSocket(clientSocket);
	    	  ChatRoomControler chatRoomControler=new  ChatRoomControler();
	    	  chatInfoVO=chatRoomControler.consumeMessage(chatInfoVO, this.threads, this);
	    	  chatRoomRef=chatInfoVO.getChatRoomRefID();
	    	  roomList=chatInfoVO.getRoomList();
	    	  System.out.println("name : "+chatInfoVO.getChatInfoVO().getClientName());
	    	//  count=chatInfoVO.getJoinChatCount();
	    	  if(chatInfoVO.isDisconnect()){
	    		  break;
	    	  }
	    	  
	      }
	      synchronized (this) {
		        for (int i = 0; i < maxClientsCount; i++) {
		          if (threads[i] != null && threads[i] != this
		              && threads[i].chatInfoVO.getClientName() != null
		            		  && threads[i].chatInfoVO.getChatRoom().equalsIgnoreCase(this.chatInfoVO.getChatRoom())) {
		            threads[i].os.println(this.chatInfoVO.getChatRoom()+" has left this chatroom");
		          }
		        }
		      }
		      os.println("*** Bye "+chatInfoVO.getClientName()+" ***");

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
	  
 class ChatRoomControler {
			
			private int joinChatCount=0;
			private int sendMessageCount=0;
			private int leaveChatRoomCount=0;
			private int disconnectCount=0;
			private ChatInfoVO chatInfoVO;
			private SendMessageVO messageVO;
			
			public ChatInfoVO consumeMessage(ChatInfoVO clientChatInfo,clientThread[] clientThreads,clientThread clientThread) throws IOException{
				
				DataInputStream is = new DataInputStream(clientChatInfo.getSocket().getInputStream());
				PrintStream  os = new PrintStream(clientChatInfo.getSocket().getOutputStream());
			    messageVO=clientChatInfo.getMessageVO();
			    chatInfoVO=clientChatInfo.getChatInfoVO();
			    
			    if(null==messageVO){
			    	messageVO=new SendMessageVO();
			    }
			    
			    if(null==chatInfoVO){
			    	chatInfoVO=new ChatInfoVO();
			    }
			    
				@SuppressWarnings("deprecation")
				String line = is.readLine();
				joinChatCount=clientChatInfo.getJoinChatCount();
				sendMessageCount=clientChatInfo.getSendMessageCount();
				leaveChatRoomCount=clientChatInfo.getLeaveChatRoomCount();
				disconnectCount=clientChatInfo.getDisconnectCount();
				System.out.println("Incoming : "+line);
				System.out.println("joinChatCount : "+clientChatInfo.getJoinChatCount());
				System.out.println("sendMessageCount : "+sendMessageCount);
				System.out.println("leaveChatRoomCount : "+leaveChatRoomCount);
				System.out.println("disconnectCount : "+disconnectCount);
				
				 if(line.toUpperCase().startsWith("HELO BASE_TEST")){
					/* os.println("HELO BASE_TEST");
		        	 os.println("IP : "+clientChatInfo.getSocket().getInetAddress());
		        	 os.println("Port : "+clientChatInfo.getSocket().getLocalPort());
		        	 os.println("StudentID : TESTSERVER1234");*/
		        	 String ip=String.valueOf(clientChatInfo.getSocket().getInetAddress());
		        	 os.println("HELO BASE_TEST\nIP:"+ip.substring(1,ip.length())
		        	 		+ "\nPort:"+clientChatInfo.getSocket().getLocalPort()
		        	 		+ "\nStudentID:TESTSERVER1234");
				 }else if(line.toUpperCase().startsWith("JOIN_CHATROOM")){
					 
					 if(line.contains("\\n")){
						 line=line.replace("\\n","#");
						 String[] contents=line.trim().split("#");
						 String[] words=contents[0].split(":");
						 System.out.println("contents[0] : "+contents[0]);
						 System.out.println("contents[1] : "+contents[1]);
						 System.out.println("contents[2] : "+contents[2]);
						 System.out.println("contents[3] : "+contents[3]);
						 if(words[0].equalsIgnoreCase("JOIN_CHATROOM")){
			   		    	 System.out.println("Chat line : "+words[1]);
			   		    	 chatInfoVO.setChatRoom(words[1].trim());
			   		    	 joinChatCount++;
			   		    	clientChatInfo.setJoinChatCount(joinChatCount);
			   		    	/*clientChatInfo.setChatRoom(chatInfoVO.getChatRoom());*/
			   		    	System.out.println("Join Count : "+clientChatInfo.getJoinChatCount());
			   		     }
						 
						 if(contents.length>2){
							 words=contents[3].split(":");
							 if(words[0].equalsIgnoreCase("CLIENT_NAME")){
					   			 System.out.println("CHat line : "+words[1]);
							    	 chatInfoVO.setClientName(words[1].trim());
							    	 joinChatCount++;
							    	 
							    	 if(joinChatCount==2){
							    		 
							    		 joinChatCount=0;
							    		 String ip=String.valueOf(clientChatInfo.getSocket().getInetAddress());
							    		 chatInfoVO.setClientIP(ip.substring(1, ip.length()));
							    		 chatInfoVO.setPort(String.valueOf(clientChatInfo.getSocket().getLocalPort()));
							    		 
							    		  if(null!=clientChatInfo.getRoomList() && !clientChatInfo.getRoomList().isEmpty()){
						    	    		  System.out.println("Room list in");
						    		    	  if(null!=clientChatInfo.getRoomList().get(chatInfoVO.getChatRoom())){
						    		    		  System.out.println("Room list not null");
						    		    		  clientChatInfo.setChatRoomRefID(clientChatInfo.getRoomList().get(chatInfoVO.getChatRoom()));
						    		    	  }else{
						    		    		  clientChatInfo.setChatRoomRefID(clientChatInfo.getChatRoomRefID()+1);
						    		    		  System.out.println("Room list null chatRef: "+clientChatInfo.getChatRoomRefID());
						    		    		  clientChatInfo.getRoomList().put(chatInfoVO.getChatRoom(), clientChatInfo.getChatRoomRefID());
						    		    	  }
						    		    	  
						    		      }else{
						    		    	  clientChatInfo.setChatRoomRefID(clientChatInfo.getChatRoomRefID()+1);
						    		    	  System.out.println("Room list null out chatRef: "+clientChatInfo.getChatRoomRefID());
						    		    	  clientChatInfo.getRoomList().put(chatInfoVO.getChatRoom(), clientChatInfo.getChatRoomRefID());
						    		      }
							    		  
							    		  	chatInfoVO.setRoomRefId(clientChatInfo.getChatRoomRefID());
							    		  	chatInfoVO.setJoinID(clientChatInfo.getSocket().getPort()*clientChatInfo.getChatRoomRefID());
							    		  //	chatInfoVO.setChatRoom(clientChatInfo.getChatRoom());
							    		  	
							    		  	 joinChatCount(is,os,chatInfoVO,clientChatInfo,clientThreads,clientThread);
							    	 }
							    	 
							     }
						 }
					 }else{
						 
						 String[] words=line.trim().split(":");
			   		     if(words[0].equalsIgnoreCase("JOIN_CHATROOM")){
			   		    	 System.out.println("Chat line : "+words[1]);
			   		    	 chatInfoVO.setChatRoom(words[1].trim());
			   		    	 System.out.println("before Count : "+joinChatCount); 
			   		    	 joinChatCount++;
			   		    	 System.out.println("Count : "+joinChatCount); 
			   		    //	clientChatInfo.setJoinChatCount(joinChatCount);
			   		    	System.out.println("Join Count : "+clientChatInfo.getJoinChatCount());
			   		    	 
			   		     }
					 }
					       
				 }else if(line.toUpperCase().startsWith("CLIENT_NAME") && joinChatCount==1){
					 String[] words=line.trim().split(":");
					 System.out.println("CHat line : "+words[1]);
					 chatInfoVO.setClientName(words[1].trim());
			    	 joinChatCount++;
			    	 
			    	 if(joinChatCount==2){
			    		 
			    		 joinChatCount=0;
			    		 String ip=String.valueOf(clientChatInfo.getSocket().getInetAddress());
			    		 chatInfoVO.setClientIP(ip.substring(1, ip.length()));
			    		 chatInfoVO.setPort(String.valueOf(clientChatInfo.getSocket().getLocalPort()));
			    		 
			    		  if(null!=clientChatInfo.getRoomList() && !clientChatInfo.getRoomList().isEmpty()){
		    	    		  System.out.println("Room list in");
		    		    	  if(null!=clientChatInfo.getRoomList().get(chatInfoVO.getChatRoom())){
		    		    		  System.out.println("Room list not null");
		    		    		  clientChatInfo.setChatRoomRefID(clientChatInfo.getRoomList().get(chatInfoVO.getChatRoom()));
		    		    	  }else{
		    		    		  clientChatInfo.setChatRoomRefID(clientChatInfo.getChatRoomRefID()+1);
		    		    		  System.out.println("Room list null chatRef: "+clientChatInfo.getChatRoomRefID());
		    		    		  clientChatInfo.getRoomList().put(chatInfoVO.getChatRoom(), clientChatInfo.getChatRoomRefID());
		    		    	  }
		    		    	  
		    		      }else{
		    		    	  clientChatInfo.setChatRoomRefID(clientChatInfo.getChatRoomRefID()+1);
		    		    	  System.out.println("Room list null out chatRef: "+clientChatInfo.getChatRoomRefID());
		    		    	  clientChatInfo.getRoomList().put(chatInfoVO.getChatRoom(), clientChatInfo.getChatRoomRefID());
		    		      }
			    		  
			    		  	chatInfoVO.setRoomRefId(clientChatInfo.getChatRoomRefID());
			    		  	chatInfoVO.setJoinID(clientChatInfo.getSocket().getPort()*clientChatInfo.getChatRoomRefID());
			    		  //	chatInfoVO.setChatRoom(clientChatInfo.getChatRoom());
			    		  	
			    		  	 joinChatCount(is,os,chatInfoVO,clientChatInfo,clientThreads,clientThread);
			    	 }
				 }else if (line.toLowerCase().startsWith("chat")){
					
										 
					 if(line.contains("\\n")){
						 line=line.replace("\\n","#");
						 String[] contents=line.trim().split("#");
						 String[] words=contents[0].split(":");
						 
						 if(words[0].equalsIgnoreCase("chat")){
			   		    	 String roomRef=words[1].trim();
			   		    	 System.out.println("CHat line : "+words[1]);
				    		 // if(null!=roomRef && Long.valueOf(roomRef)==clientChatInfo.getChatRoomRefID()){
				    			  messageVO.setChatReference(clientChatInfo.getChatRoomRefID());
				    			  sendMessageCount++;
				    			  System.out.println("Chat: "+clientChatInfo.getChatRoomRefID()+" sendMessageCount : " +sendMessageCount);
				    		/*  }else{
				    			  sendMessageCount=0;
				    			  os.println("ERROR_CODE:ERR101");
				    			  os.println("ERROR_DESCRIPTION : Chat room reference provided is either not valid or wrong"); 
				    		  }*/
			   		    	 
			   		     }
						 if(contents.length>1){
							 words=contents[1].split(":"); 
							 
							 if(words[0].equalsIgnoreCase("JOIN_ID")){
						    	 String id=words[1].trim();
					    		//  if(null!=id && Long.valueOf(id)==clientChatInfo.getChatInfoVO().getJoinID()){
					    			  messageVO.setJoinID(clientChatInfo.getChatInfoVO().getJoinID());
					    			  sendMessageCount++;
					    			  System.out.println("Chat: "+messageVO.getJoinID()+" sendMessageCount : " +sendMessageCount);
					    			 
					    		 /* }else{
					    			  sendMessageCount=0;
					    			  os.println("ERROR_CODE:ERR102");
					    			  os.println("ERROR_DESCRIPTION : Join ID reference provided is either not valid or wrong"); 
					    		  }*/
						    	 
						     }
						 }
						 if(contents.length>2){
							 words=contents[2].split(":"); 
							  
						   		if(words[0].equalsIgnoreCase("CLIENT_NAME")){
								   
						   		  String name=words[1].trim();
						   		 // if(null!=name && name.equalsIgnoreCase(clientChatInfo.getChatInfoVO().getClientName())){
						   			  messageVO.setClientName(clientChatInfo.getChatInfoVO().getClientName());
						   			 sendMessageCount++;
						   			 System.out.println("Chat: "+messageVO.getClientName()+" sendMessageCount : " +sendMessageCount);
						   			 
						   		 /* }else{
						   			sendMessageCount=0;
						   			  os.println("ERROR_CODE:ERR103");
						   			  os.println("ERROR_DESCRIPTION : Client Name provided is either not valid or wrong"); 
						   		  }*/
								    	 
								     }
						 }
						 
						 if(contents.length>2){
							 words=contents[3].split(":"); 

						   		
						   		if(words[0].equalsIgnoreCase("MESSAGE")){
						   			
								   String message=words[1].trim();
						   		  if(null!=message){
						   			  messageVO.setMessage(message);
						   			  sendMessageCount++;
						   			System.out.println("Chat: "+messageVO.getMessage()+" sendMessageCount : " +sendMessageCount);
						   			  if(sendMessageCount==4){
						   				  sendMessageCount=0;
						   					sendMessage(os, clientChatInfo, clientThreads, messageVO,clientThread);	  
						   			 }
						   			  
						   		  }else{
						   			sendMessageCount=0;
						   			  os.println("ERROR_CODE:ERR104");
						   			  os.println("ERROR_DESCRIPTION : The Message is blank !"); 
						   		  }
								    	 
								     }
						 }
					 }else	{				 
						 String[] words=line.split(":");
		   		     if(words[0].equalsIgnoreCase("chat")){
		   		    	 String roomRef=words[1].trim();
		   		    	 System.out.println("CHat line : "+words[1]);
			    		 // if(null!=roomRef && Long.valueOf(roomRef)==clientChatInfo.getChatRoomRefID()){
			    			  messageVO.setChatReference(clientChatInfo.getChatRoomRefID());
			    			  sendMessageCount++;
			    			  System.out.println("Chat: "+messageVO.getChatReference()+" sendMessageCount : " +sendMessageCount);
			    		  //}else{
			    			  os.println("ERROR_CODE:ERR101");
			    			  os.println("ERROR_DESCRIPTION : Chat room reference provided is either not valid or wrong"); 
			    		  //}
		   		    	 
		   		     }
		   		     
					 }
			   	
			   		
					 
				 }else if(line.toUpperCase().startsWith("JOIN_ID") && sendMessageCount==1){
					 
					 String[] words=line.trim().split(":");
		   		  	 String id=words[1].trim();
		   		  	
		   		  if(null!=id && Long.valueOf(id)==clientChatInfo.getChatInfoVO().getJoinID()){
		   			  messageVO.setJoinID(clientChatInfo.getChatInfoVO().getJoinID());
		   			  sendMessageCount++;
		   			System.out.println("Chat: "+messageVO.getJoinID()+" sendMessageCount : " +sendMessageCount);
		   		  }else{
		   			  os.println("ERROR_CODE:ERR102");
		   			  os.println("ERROR_DESCRIPTION : Join ID reference provided is either not valid or wrong"); 
		   		  }
					 
				 }else if(line.toUpperCase().startsWith("CLIENT_NAME") && sendMessageCount==2 ){
					 
					 String[] words=line.trim().split(":");
					 String name=words[1];
					 
			   		//  if(null!=name && name.equalsIgnoreCase(clientChatInfo.getChatInfoVO().getClientName())){
			   			  messageVO.setClientName(clientChatInfo.getChatInfoVO().getClientName());
			   			  sendMessageCount++;
			   			System.out.println("Chat: "+messageVO.getClientName()+" sendMessageCount : " +sendMessageCount);
			   		//  }else{
			   			  os.println("ERROR_CODE:ERR103");
			   			  os.println("ERROR_DESCRIPTION : Client Name provided is either not valid or wrong"); 
			   		 // }
					 
				 }else if(line.toUpperCase().startsWith("MESSAGE") && sendMessageCount==3 ){
					 
					 String[] words=line.trim().split(":");
					 String message=words[1];
					 
			   		  if(null!=message){
			   			  messageVO.setMessage(message);
			   			  sendMessageCount++;
			   			System.out.println("Chat: "+messageVO.getMessage()+" sendMessageCount : " +sendMessageCount);
			   			  if(sendMessageCount==4){
			   				  sendMessageCount=0;
			   					sendMessage(os, clientChatInfo, clientThreads, messageVO,clientThread);	  
			   			 }
			   			  
			   		  }else{
			   			  os.println("ERROR_CODE:ERR104");
			   			  os.println("ERROR_DESCRIPTION : The Message is blank !"); 
			   		  }
					    	 
				  }else if(line.toUpperCase().startsWith("LEAVE_CHATROOM") || 
						  (line.toUpperCase().startsWith("JOIN_ID") && leaveChatRoomCount==1) || 
						  (line.toUpperCase().startsWith("CLIENT_NAME")) && leaveChatRoomCount==2){
					 			  
					  if(line.contains("\\n")){
							 line=line.replace("\\n","#");
							 String[] contents=line.trim().split("#");
							
							 
							 if(contents.length>1){
								 String[] words=line.trim().split(":");
								 if (words[0].equalsIgnoreCase("LEAVE_CHATROOM")) {
										leaveChatRoomCount++;
									}
							 }
							 if(contents.length>2){
								 String[] words=line.trim().split(":");
								 if (words[0].equalsIgnoreCase("JOIN_ID")) {
										leaveChatRoomCount++;
									}
							 }
							 if(contents.length>3){
								 String[] words=line.trim().split(":");
								 if (words[0].equalsIgnoreCase("CLIENT_NAME")) {
										leaveChatRoomCount++;
									}
							 }
							 
							 
					  }else{
					  String[] words=line.trim().split(":");
				
							if (words[0].equalsIgnoreCase("LEAVE_CHATROOM")) {
								leaveChatRoomCount++;
							}
							if (words.length > 2 && words[2].equalsIgnoreCase("JOIN_ID")) {
								leaveChatRoomCount++;
							} else if (words[0].equalsIgnoreCase("JOIN_ID")){
								leaveChatRoomCount++;
							}
							if (words.length > 4 && words[4].equalsIgnoreCase("CLIENT_NAME")) {
								leaveChatRoomCount++;
							} 
					  }
					  if(leaveChatRoomCount==3){
						  clientChatInfo.setLeftChatRoom(true);
						  leaveChatRoomCount=0;
						  leaveChatRoom(os, clientChatInfo, clientThreads,clientThread);
						  
					  }
				  }else if(line.toUpperCase().startsWith("DISCONNECT") || 
						  (line.toUpperCase().startsWith("PORT") && disconnectCount==1) || 
						  (line.toUpperCase().startsWith("CLIENT_NAME")) && disconnectCount==2){
					  
					  if(line.contains("\\n")){
							 line=line.replace("\\n","#");
							 String[] contents=line.trim().split("#");
							
							 
							 if(contents.length>1){
								 String[] words=line.trim().split(":");
								 if (words[0].equalsIgnoreCase("DISCONNECT")) {
									 disconnectCount++;
									}
							 }
							 if(contents.length>2){
								 String[] words=line.trim().split(":");
								 if (words[0].equalsIgnoreCase("PORT")) {
									 disconnectCount++;
									}
							 }
							 if(contents.length>3){
								 String[] words=line.trim().split(":");
								 if (words[0].equalsIgnoreCase("CLIENT_NAME")) {
									 disconnectCount++;
									}
							 }
							 
							 
					  }else{
					 			  
					  String[] words=line.trim().split(":");
				
							if (words[0].equalsIgnoreCase("DISCONNECT")) {
								disconnectCount++;
							}
							if (words.length > 2 && words[2].equalsIgnoreCase("PORT")) {
								disconnectCount++;
							}
							if (words.length > 4 && words[4].equalsIgnoreCase("CLIENT_NAME")) {
								disconnectCount++;
							} 
							
					  }
					  
					  if(disconnectCount==3){
						 clientChatInfo.setDisconnect(true);
						  disconnectCount=0;
					 }
				  }
				 
				 clientChatInfo.setJoinChatCount(joinChatCount);
				 clientChatInfo.setDisconnectCount(disconnectCount);
				 clientChatInfo.setLeaveChatRoomCount(leaveChatRoomCount);
				 clientChatInfo.setSendMessageCount(sendMessageCount);
			      clientChatInfo.setMessageVO(messageVO);
			      clientChatInfo.setChatInfoVO(chatInfoVO);
				
				return clientChatInfo;
				
			}
			
			private void joinChatCount(DataInputStream is, PrintStream os, ChatInfoVO chatInfoVO2,ChatInfoVO clientChatInfo,clientThread[] clientThreads,clientThread clientThread) {
				// TODO Auto-generated method stub
				
				/*os.println("JOINED_CHATROOM : "+chatInfoVO2.getChatRoom());
				  os.println("SERVER_IP : "+chatInfoVO2.getClientIP());
				  os.println("PORT : "+chatInfoVO2.getPort());
				  os.println("ROOM_REF : " +chatInfoVO2.getRoomRefId());
				  os.println("JOIN_ID : "+chatInfoVO2.getJoinID());*/
				  
				  os.println("JOINED_CHATROOM:"+chatInfoVO2.getChatRoom()
			      	 		+ "\nSERVER_IP:"+chatInfoVO2.getClientIP()
			      	 		+ "\nPORT:"+chatInfoVO2.getPort()
			      	 		+ "\nROOM_REF:"+chatInfoVO2.getRoomRefId()
			      	 		+ "\nJOIN_ID:"+chatInfoVO2.getJoinID());
				  
				  synchronized (clientThread) {
					  for (int i = 0; i < clientChatInfo.getMaxClientsCount(); i++) {
		    	          if (clientThreads[i] != null && clientThreads[i] == clientThread) {
		    	            clientChatInfo.setClientName(chatInfoVO2.getClientName());
		    	            clientChatInfo.setChatRoom(chatInfoVO2.getChatRoom());
		    	            clientChatInfo.setLeftChatRoom(false);
		    	            break;
		    	          }
		    	        }
					  
					  for (int i = 0; i < clientChatInfo.getMaxClientsCount(); i++) {
		  	        	
		    	          if (clientThreads[i] != null && clientThreads[i] != clientThread && 
		    	        		  clientThreads[i].getChatInfoVO().getChatRoom().equalsIgnoreCase(chatInfoVO2.getChatRoom())) {
		    	        	/*  System.out.println("ChatRoom on thread loop: "+threads[i].chatRoom);
		    		        	System.out.println("ChatRoom on client: "+this.chatRoom);*/
		    	        	  clientThreads[i].getOs().println(chatInfoVO2.getClientName()
		    	                + " has joined the chat room : "+chatInfoVO2.getChatRoom());
		    	          }
		    	        }
					  
				  }
			}

				
			private void sendMessage(PrintStream os,ChatInfoVO clientChatInfo,clientThread[] clientThreads,SendMessageVO messageVO2,clientThread clientThread){
				   synchronized (clientThread) {
					   
			            for (int i = 0; i < clientChatInfo.getMaxClientsCount(); i++) {
			            	
			              if (clientThreads[i] != null && clientThreads[i].getChatInfoVO().getClientName() != null 
			            		  && clientThreads[i].getChatInfoVO().getChatRoom().equalsIgnoreCase(clientChatInfo.getChatRoom()) && !clientChatInfo.isLeftChatRoom()) {
			            	 System.out.println("ChatRoom on thread loop: "+clientThreads[i].getChatInfoVO().getChatRoom());
					        	System.out.println("ChatRoom on client: "+clientChatInfo.getChatRoom());
			            	 /* clientThreads[i].getOs().println("CHAT: " +messageVO2.getChatReference());
			            	  clientThreads[i].getOs().println("CLIENT_NAME: " +messageVO2.getClientName());
			            	  clientThreads[i].getOs().println("MESSAGE: " +messageVO2.getMessage());*/
			            	  clientThreads[i].getOs().println("CHAT:" +messageVO2.getChatReference()
				      	 		+ "\nCLIENT_NAME:" +messageVO2.getClientName()
				      	 		+ "\nPORT:chatInfoVO2.getPort()"
				      	 		+ "\nMESSAGE:"+messageVO2.getMessage());
			              }
			            }
			          }
			}
			
			private void leaveChatRoom(PrintStream os,ChatInfoVO clientChatInfo,clientThread[] clientThreads,clientThread clientThread){
				
		    	  synchronized (clientThread) {
		  	        for (int i = 0; i < clientChatInfo.getMaxClientsCount(); i++) {
		  	          if (clientThreads[i] != null && clientThreads[i] == clientThread
		  	              && clientThreads[i].getChatInfoVO().getClientName() != null
		  	            		  && clientThreads[i].getChatInfoVO().getChatRoom().equalsIgnoreCase(clientChatInfo.getChatRoom()) && !clientChatInfo.isLeftChatRoom()) {
		  	        
		  	        	/*  clientThreads[i].getOs().println("LEAVE_CHATROOM : " + clientChatInfo.getChatRoom());
		  	        	clientThreads[i].getOs().println("JOIN_ID : " + clientChatInfo.getJoinID());
		  	        	clientThreads[i].getOs().println("CLIENT_NAME : " + clientChatInfo.getClientName());*/
		  	        	clientThreads[i].getOs().println("LEAVE_CHATROOM:" + clientChatInfo.getChatRoom()
		      	 		+ "\nJOIN_ID:" + clientChatInfo.getJoinID()
		      	 		+ "\nCLIENT_NAME:" + clientChatInfo.getClientName());
		  	          }
		  	        }
		  	      }
		    	  os.println("LEFT_CHATROOM:"+clientChatInfo.getChatRoom()
	      	 		+ "\nJOIN_ID:"+clientChatInfo.getJoinID());
			}


		}


	public ChatInfoVO getChatInfoVO() {
		return chatInfoVO;
	}

	public void setChatInfoVO(ChatInfoVO chatInfoVO) {
		this.chatInfoVO = chatInfoVO;
	}

	public DataInputStream getIs() {
		return is;
	}

	public void setIs(DataInputStream is) {
		this.is = is;
	}

	public PrintStream getOs() {
		return os;
	}

	public void setOs(PrintStream os) {
		this.os = os;
	}
	
	
	}
       
	

