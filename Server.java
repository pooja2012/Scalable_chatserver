import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.net.ServerSocket;

public class Server {

	  // The server socket.
	  public static ServerSocket serverSocket = null;
	  // The client socket.
	  public static Socket clientSocket = null;

	  // This chat server can accept up to maxClientsCount clients' connections.
	  private static final int maxClientsCount = 100;
	  private static final clientThread[] threads = new clientThread[maxClientsCount];
	  // The default port number.
	    static int portNumber = 2222;
		
	  // Default chatroom reference  
	    public static long chatRoomRef=1134567890;
		public static HashMap<String, Long> roomList=new HashMap<String, Long>();

	  public static void main(String args[]) {

		  System.out.println("Server Ready to start");
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
	private List<Long> roomRefIds;
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
    private boolean killService;
    private long roomRefId;
    
    
	
	public long getRoomRefId() {
		return roomRefId;
	}
	public void setRoomRefId(long roomRefId) {
		this.roomRefId = roomRefId;
	}
	public boolean isKillService() {
		return killService;
	}
	public void setKillService(boolean killService) {
		this.killService = killService;
	}
	public List<Long> getRoomRefIds() {
		return roomRefIds;
	}
	public void setRoomRefIds(List<Long> roomRefIds) {
		this.roomRefIds = roomRefIds;
	}
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

	  private ChatInfoVO chatInfoVO=new ChatInfoVO();
	  

	  public clientThread(Socket clientSocket, clientThread[] threads) {
	    this.clientSocket = clientSocket;
	    this.threads = threads;
	    maxClientsCount = threads.length;
	  }

	  @SuppressWarnings({ "deprecation", "static-access" })
	public void run() {
	    /*int maxClientsCount = this.maxClientsCount;
	    clientThread[] threads = this.threads;*/

	    try {
	      /*
	       * Create input and output streams for this client.
	       */
	    	chatInfoVO.setRoomRefIds(new ArrayList<Long>());
	    	 Server server=new Server();
	      is = new DataInputStream(clientSocket.getInputStream());
	      os = new PrintStream(clientSocket.getOutputStream());
	     // System.out.println("------------------------Input from client : "+is.readLine());

	      /* Start the conversation. */
	      while (true) {
	    	//  System.out.println("countt : "+count);
	    	  System.out.println("chatRoomRef List start: "+chatInfoVO.getRoomRefIds().size());
	    	  chatInfoVO.setMaxClientsCount(this.maxClientsCount);
	    	  System.out.println("chatRoomRef start : "+server.chatRoomRef);
	    	  chatInfoVO.setRoomRefId(server.chatRoomRef);
	    	  chatInfoVO.setRoomList(server.roomList);
	    	  chatInfoVO.setSocket(clientSocket);
	    	  ChatRoomControler chatRoomControler=new  ChatRoomControler();
	    	  //the client input is consumed here
	    	  chatInfoVO=chatRoomControler.consumeMessage(is,chatInfoVO, this.threads, this);
	    	  server.chatRoomRef=chatInfoVO.getRoomRefId();
	    	  server.roomList=chatInfoVO.getRoomList();
	    	  System.out.println("chatRoomRef end : "+server.chatRoomRef);
	    	  System.out.println("chatRoomRef List end: "+chatInfoVO.getRoomRefIds().size());
	    	//  count=chatInfoVO.getJoinChatCount();
	    	  //Disconnecting the client
	    	  if(chatInfoVO.isDisconnect()){
	    	      synchronized (this) {
	  		        for (int i = 0; i < maxClientsCount; i++) {
	  		          if (threads[i] != null && threads[i] != this
	  		              && threads[i].chatInfoVO.getClientName() != null
	  		            		  && threads[i].chatInfoVO.getRoomRefIds()!=null
	  		            		  && this.chatInfoVO.getRoomRefIds()!=null) {
	  		        	  
	  		        	  for(long roomid:this.chatInfoVO.getRoomRefIds()){
	  		        		  
	  		        		  if(threads[i].chatInfoVO.getRoomRefIds().contains(roomid)){
	  		        			  threads[i].os.println(this.chatInfoVO.getChatRoom()+" has left this chatroom");
	  		        		  }
	  		        		  
	  		        	  }
	  		        	  
	  		          
	  		            
	  		            
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
	    	  }
	    	  
	    	  if(chatInfoVO.isKillService()){
	    		  
					synchronized (this) {
						for (int i = 0; i < maxClientsCount; i++) {
							if (threads[i] != null) {
								
								if(threads[i].chatInfoVO.getClientName() != null) {
									threads[i].os.println("*******Bye "+threads[i].chatInfoVO.getClientName() + "**********");
								}
								threads[i].os.println("Server is getting shutdown");
								threads[i].is.close();
								threads[i].os.close();
								threads[i].clientSocket.shutdownInput();
								threads[i].clientSocket.shutdownOutput();
								threads[i].clientSocket.close();
								threads[i].stop();
							   // threads[i] = null;
							}
						}
						if(null!=server.clientSocket) {
							server.clientSocket.close();
						}
						//if(null!=server.serverSocket) {
							 server.serverSocket.close();
						//}
					}
			 	  }
	      }


		     
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

			@SuppressWarnings("static-access")
			public ChatInfoVO consumeMessage(DataInputStream is,ChatInfoVO clientChatInfo,clientThread[] clientThreads,clientThread clientThread) throws IOException{

				//DataInputStream is = new DataInputStream(clientChatInfo.getSocket().getInputStream());
				PrintStream  os = new PrintStream(clientChatInfo.getSocket().getOutputStream());
			    messageVO=clientChatInfo.getMessageVO();
			    chatInfoVO=clientChatInfo.getChatInfoVO();
			    System.out.println("IP : "+clientChatInfo.getSocket().getInetAddress().getLocalHost().toString());
			    String[] ip=clientChatInfo.getSocket().getInetAddress().getLocalHost().toString().split("/");
			    
			    //Initializing the transfer Objects
			    if(null==messageVO){
			    	messageVO=new SendMessageVO();
			    }

			    if(null==chatInfoVO){
			    	chatInfoVO=new ChatInfoVO();
			    }

				@SuppressWarnings("deprecation")
				String line = is.readLine();
				System.out.println(new Date()+":------------------Input from client : "+line+"----------------------");
				joinChatCount=clientChatInfo.getJoinChatCount();
				sendMessageCount=clientChatInfo.getSendMessageCount();
				leaveChatRoomCount=clientChatInfo.getLeaveChatRoomCount();
				disconnectCount=clientChatInfo.getDisconnectCount();
				System.out.println("Incoming : "+line);
				System.out.println("joinChatCount : "+clientChatInfo.getJoinChatCount());
				System.out.println("sendMessageCount : "+sendMessageCount);
				System.out.println("leaveChatRoomCount : "+leaveChatRoomCount);
				System.out.println("disconnectCount : "+disconnectCount);
				System.out.println("disconnect  : "+clientChatInfo.isDisconnect());
				System.out.println("Left : "+clientChatInfo.isLeftChatRoom());

				 if(line.toUpperCase().startsWith("HELO")){
					
				 System.out.println("Ip1 : "+clientChatInfo.getSocket().getInetAddress());
				 System.out.println("Ip2 : "+clientChatInfo.getSocket().getInetAddress().getHostAddress());
				 System.out.println("Ip3 : "+clientChatInfo.getSocket().getInetAddress().getLocalHost());
				 System.out.println("Ip4 : "+clientChatInfo.getSocket().getInetAddress().getLoopbackAddress());
				 System.out.println("Ip5 : "+clientChatInfo.getSocket().getLocalAddress());
				 
				 
				 os.println("HELO BASE_TEST\nIP:"+ip[1]
		        	 		+ "\nPort:"+clientChatInfo.getSocket().getLocalPort()
		        	 		+ "\nStudentID:TESTSERVER1234");

				 }else if(line.toUpperCase().startsWith("JOIN_CHATROOM")){

					 //If Input comes in single line with \n
					 if(line.contains("\\n")){
						 line=line.replace("\\n","#");
						 String[] contents=line.trim().split("#");
						 String[] words=contents[0].split(":");

						 if(words[0].equalsIgnoreCase("JOIN_CHATROOM")){
			   		    	 chatInfoVO.setChatRoom(words[1].trim());
			   		    	 joinChatCount++;
			   		    	clientChatInfo.setJoinChatCount(joinChatCount);
			   		     }

						 if(contents.length>2){
							 
							 words=contents[3].split(":");
							 if(words[0].equalsIgnoreCase("CLIENT_NAME")){
								 
					   			// System.out.println("CHat line : "+words[1]);
					   			 
							    	 chatInfoVO.setClientName(words[1].trim());
							    	 joinChatCount++;

							    	 if(joinChatCount==2){

							    		 joinChatCount=0;
							    		 chatInfoVO.setClientIP(ip[1]);
							    		 chatInfoVO.setPort(String.valueOf(clientChatInfo.getSocket().getLocalPort()));

							    		  if(null!=clientChatInfo.getRoomList() && !clientChatInfo.getRoomList().isEmpty()){
						    	    		//  System.out.println("Room list in");
						    		    	  if(null!=clientChatInfo.getRoomList().get(chatInfoVO.getChatRoom().toUpperCase())){
						    		    	//	  System.out.println("Room list not null");
						    		    		  clientChatInfo.setRoomRefId(clientChatInfo.getRoomList().get(chatInfoVO.getChatRoom().toUpperCase()));
						    		    	  }else{
						    		    		  clientChatInfo.setRoomRefId(clientChatInfo.getRoomRefId()+1);
						    		    	//	  System.out.println("Room list null chatRef: "+clientChatInfo.getChatRoomRefID());
						    		    		  clientChatInfo.getRoomList().put(chatInfoVO.getChatRoom().toUpperCase(), clientChatInfo.getRoomRefId());
						    		    	  }

						    		      }else{
						    		    	  clientChatInfo.setRoomRefId(clientChatInfo.getRoomRefId()+1);
						    		    	//  System.out.println("Room list null out chatRef: "+clientChatInfo.getChatRoomRefID());
						    		    	  clientChatInfo.getRoomList().put(chatInfoVO.getChatRoom().toUpperCase(), clientChatInfo.getRoomRefId());
						    		      }

							    		  	chatInfoVO.setChatRoomRefID(clientChatInfo.getRoomRefId());
							    		  	chatInfoVO.setRoomRefId(clientChatInfo.getRoomRefId());
							    		  if(null!=clientChatInfo.getRoomRefIds()){
							    			  clientChatInfo.getRoomRefIds().add(clientChatInfo.getRoomRefId());
							    		  }else{
							    			  clientChatInfo.setRoomRefIds(new ArrayList<Long>());
							    			  clientChatInfo.getRoomRefIds().add(clientChatInfo.getRoomRefId());
							    		  }
							    		  	
							    		  	chatInfoVO.setJoinID(clientChatInfo.getSocket().getPort()*clientChatInfo.getRoomRefId());
							    		  //	chatInfoVO.setChatRoom(clientChatInfo.getChatRoom());

							    		  	 joinChatCount(is,os,chatInfoVO,clientChatInfo,clientThreads,clientThread);
							    	 }

							     }
						 }
					 }else{
						 
						 //If input from client in different lines
						 String[] words=line.trim().split(":");
			   		     if(words[0].equalsIgnoreCase("JOIN_CHATROOM")){
			   		    	// System.out.println("Chat line : "+words[1]);
			   		    	 chatInfoVO.setChatRoom(words[1].trim());
			   		    	 //System.out.println("before Count : "+joinChatCount);
			   		    	 joinChatCount++;
			   		    //	clientChatInfo.setJoinChatCount(joinChatCount);
			   		    //	System.out.println("Join Count : "+clientChatInfo.getJoinChatCount());

			   		     }
					 }

				 }else if(line.toUpperCase().startsWith("CLIENT_NAME") && joinChatCount==1){
					 
					 String[] words=line.trim().split(":");
					// System.out.println("CHat line : "+words[1]);
					 chatInfoVO.setClientName(words[1].trim());
			    	 joinChatCount++;

			    	 if(joinChatCount==2){

			    		 joinChatCount=0;
			    		 chatInfoVO.setClientIP(String.valueOf(clientChatInfo.getSocket().getLocalAddress().getLocalHost()));
			    		 chatInfoVO.setPort(String.valueOf(clientChatInfo.getSocket().getLocalPort()));

			    		  if(null!=clientChatInfo.getRoomList() && !clientChatInfo.getRoomList().isEmpty()){
		    	    		//  System.out.println("Room list in");
		    		    	  if(null!=clientChatInfo.getRoomList().get(chatInfoVO.getChatRoom().toUpperCase())){
		    		    		//  System.out.println("Room list not null");
		    		    		  clientChatInfo.setRoomRefId(clientChatInfo.getRoomList().get(chatInfoVO.getChatRoom().toUpperCase()));
		    		    	  }else{
		    		    		  clientChatInfo.setRoomRefId(clientChatInfo.getRoomRefId()+1);
		    		    		  //System.out.println("Room list null chatRef: "+clientChatInfo.getChatRoomRefID());
		    		    		  clientChatInfo.getRoomList().put(chatInfoVO.getChatRoom().toUpperCase(), clientChatInfo.getRoomRefId());
		    		    	  }

		    		      }else{
		    		    	  clientChatInfo.setRoomRefId(clientChatInfo.getRoomRefId()+1);
		    		    	  //System.out.println("Room list null out chatRef: "+clientChatInfo.getChatRoomRefID());
		    		    	  clientChatInfo.getRoomList().put(chatInfoVO.getChatRoom().toUpperCase(), clientChatInfo.getRoomRefId());
		    		      }
			    		  
			    		//  System.out.println("Room list chatRef: "+clientChatInfo.getRoomRefId());

			    		  if(null!=clientChatInfo.getRoomRefIds()){
			    			  clientChatInfo.getRoomRefIds().add(clientChatInfo.getRoomRefId());
			    		  }else{
			    			  clientChatInfo.setRoomRefIds(new ArrayList<Long>());
			    			  clientChatInfo.getRoomRefIds().add(clientChatInfo.getRoomRefId());
			    		  }
			    		  
			    		  	chatInfoVO.setJoinID(clientChatInfo.getSocket().getPort()*clientChatInfo.getRoomRefId());
			    		  	chatInfoVO.setChatRoomRefID(clientChatInfo.getRoomRefId());

			    		  	 joinChatCount(is,os,chatInfoVO,clientChatInfo,clientThreads,clientThread);
			    	 }
			    	 
				 }else if (line.toLowerCase().startsWith("chat")){

					 // if Chat Message command comes in one line
					 if(line.contains("\\n")){
						 line=line.replace("\\n","#");
						 String[] contents=line.trim().split("#");
						 String[] words=contents[0].split(":");

						 if(words[0].equalsIgnoreCase("chat")){
			   		    	 String roomRef=words[1].trim();
			   		    	 
			   		    	 //System.out.println("Chat line : "+words[1]);
			   		    	 
				    		//  if(null!=roomRef && clientChatInfo.getRoomRefIds().contains(Long.valueOf(roomRef))){
				    			  messageVO.setChatReference(Long.valueOf(roomRef));
				    			  sendMessageCount++;
				    			//  System.out.println("Chat: "+clientChatInfo.getChatRoomRefID()+" sendMessageCount : " +sendMessageCount);
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
					    		
					    			  messageVO.setJoinID(Long.valueOf(id));
					    			  sendMessageCount++;
					    	  }
						 }
						 if(contents.length>2){
							 words=contents[2].split(":");

						   		if(words[0].equalsIgnoreCase("CLIENT_NAME")){

						   		  String name=words[1].trim();
						   			  messageVO.setClientName(name);
						   			 sendMessageCount++;
						   			// System.out.println("Chat: "+messageVO.getClientName()+" sendMessageCount : " +sendMessageCount);

							    }
						   		
						 }

						 if(contents.length>2){
							 words=contents[3].split(":");


						   		if(words[0].equalsIgnoreCase("MESSAGE")){

								   String message=words[1].trim();
						   		  if(null!=message){
						   			  messageVO.setMessage(message);
						   			  sendMessageCount++;
						   		//	System.out.println("Chat: "+messageVO.getMessage()+" sendMessageCount : " +sendMessageCount);
						   			  if(sendMessageCount==4){
						   				  sendMessageCount=0;
						   					sendMessage(os, clientChatInfo, clientThreads, messageVO,clientThread);
						   			 }

						   		  }else{
						   			sendMessageCount=0;
						   			  os.println("ERROR_CODE:ERR102");
						   			  os.println("ERROR_DESCRIPTION : The Message is blank !");
						   		  }

								     }
						 }
					 }else	{
						 //Input from Client in different Lines
						 String[] words=line.split(":");
		   		     if(words[0].equalsIgnoreCase("chat")){
		   		    System.out.println("CHat line : "+words[1]);
		   		    	 String roomRef=words[1].trim();
		   		    	
			    		//  if(null!=roomRef && clientChatInfo.getRoomRefIds().contains(Long.valueOf(roomRef))){
			    			  messageVO.setChatReference(Long.valueOf(roomRef));
			    			  sendMessageCount++;
			    			//  System.out.println("Chat: "+messageVO.getChatReference()+" sendMessageCount : " +sendMessageCount);
			    		 /* }else{
			    			  os.println("ERROR_CODE:ERR101");
			    			  os.println("ERROR_DESCRIPTION : Chat room reference provided is either not valid or wrong");
			    		  }*/

		   		     }

					 }



				 }else if(line.toUpperCase().startsWith("JOIN_ID") && sendMessageCount==1){

					 String[] words=line.trim().split(":");
		   		  	 String id=words[1].trim();

		   		   messageVO.setJoinID(Long.valueOf(id));
		   			  sendMessageCount++;
		   		//	System.out.println("Chat: "+messageVO.getJoinID()+" sendMessageCount : " +sendMessageCount);
		   		

				 }else if(line.toUpperCase().startsWith("CLIENT_NAME") && sendMessageCount==2 ){

					 String[] words=line.trim().split(":");
					 String name=words[1];
			   		
			   			  messageVO.setClientName(name);
			   			  sendMessageCount++;
			   		//	System.out.println("Chat: "+messageVO.getClientName()+" sendMessageCount : " +sendMessageCount);
			   		 

				 }else if(line.toUpperCase().startsWith("MESSAGE") && sendMessageCount==3 ){

					 String[] words=line.trim().split(":");
					 String message=words[1];

			   		  if(null!=message){
			   			  messageVO.setMessage(message);
			   			  sendMessageCount++;
			   		//	System.out.println("Chat: "+messageVO.getMessage()+" sendMessageCount : " +sendMessageCount);
			   			  if(sendMessageCount==4){
			   				  sendMessageCount=0;
			   					sendMessage(os, clientChatInfo, clientThreads, messageVO,clientThread);
			   			 }

			   		  }else{
			   			  os.println("ERROR_CODE:ERR102");
			   			  os.println("ERROR_DESCRIPTION : The Message is blank !");
			   		  }

				  }else if(line.toUpperCase().startsWith("LEAVE_CHATROOM") ||
						  (line.toUpperCase().startsWith("JOIN_ID") && leaveChatRoomCount==1) ||
						  (line.toUpperCase().startsWith("CLIENT_NAME")) && leaveChatRoomCount==2){
					//  System.out.println("Leave chatroom inside");
					  if(line.contains("\\n")){
						  
						  //If the input comes in one line
						  
							 line=line.replace("\\n","#");
							 String[] contents=line.trim().split("#");


							 if(contents.length>1){
								 String[] words=contents[0].trim().split(":");
								 
								 if (words[0].equalsIgnoreCase("LEAVE_CHATROOM")) {
									 
									 String id=words[1].trim();
									    clientChatInfo.setChatRoomRefID(Long.valueOf(id));
										leaveChatRoomCount++;
										
									}
							 }
							 if(contents.length>2){
								 
								 String[] words=contents[1].trim().split(":");
								 
								 if (words[0].equalsIgnoreCase("JOIN_ID")) {
									 
									 String id=words[1].trim();
									 clientChatInfo.setJoinID(Long.valueOf(id));
										leaveChatRoomCount++;
										
									}
							 }
							 if(contents.length>3){
								 
								 String[] words=contents[3].trim().split(":");
								 
								 if (words[0].equalsIgnoreCase("CLIENT_NAME")) {
									 
									 String name=words[1].trim();
									 clientChatInfo.setClientName(name);
										leaveChatRoomCount++;
										
									}
							 }


					  }else{
						  //Input to leave chat comes in different lines
					  String[] words=line.trim().split(":");

							if (words[0].equalsIgnoreCase("LEAVE_CHATROOM")) {
									
								String id=words[1].trim();
								clientChatInfo.setChatRoomRefID(Long.valueOf(id));
								leaveChatRoomCount++;
							}
							if ( words[0].equalsIgnoreCase("JOIN_ID")) {
								
								 String id=words[1].trim();
								 clientChatInfo.setJoinID(Long.valueOf(id));
								 leaveChatRoomCount++;
							}
							if (words[0].equalsIgnoreCase("CLIENT_NAME")) {
								
								 String name=words[1].trim();
								 clientChatInfo.setClientName(name);
								 leaveChatRoomCount++;
							}
					  }
					  
					  System.out.println("leaveChatRoomCount inside : "+leaveChatRoomCount);
					  
					  if(leaveChatRoomCount==3){
						  //clientChatInfo.setLeftChatRoom(true);
						  leaveChatRoomCount=0;
						  clientChatInfo.getRoomRefIds().remove(clientChatInfo.getChatRoomRefID());
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
									 
									 String name=words[1].trim();
									 clientChatInfo.setClientName(name);
									 disconnectCount++;
									 
									}
							 }


					  }else{

					  String[] words=line.trim().split(":");

							if (words[0].equalsIgnoreCase("DISCONNECT")) {
								disconnectCount++;
							}
							if (words[0].equalsIgnoreCase("PORT")) {
								disconnectCount++;
							}
							if (words[0].equalsIgnoreCase("CLIENT_NAME")) {
								
								String name=words[1].trim();
								 clientChatInfo.setClientName(name);
								disconnectCount++;
								
							}

					  }

					  if(disconnectCount==3){
						 clientChatInfo.setDisconnect(true);
						  disconnectCount=0;
					 }
					  
				  }else if(line.toUpperCase().startsWith("KILL_SERVICE")){
					  
					  clientChatInfo.setKillService(true);
					  
				  }else{
					  
					  clientChatInfo.setKillService(false);
					  
				  }

				 clientChatInfo.setJoinChatCount(joinChatCount);
				 clientChatInfo.setDisconnectCount(disconnectCount);
				 clientChatInfo.setLeaveChatRoomCount(leaveChatRoomCount);
				 clientChatInfo.setSendMessageCount(sendMessageCount);
			      clientChatInfo.setMessageVO(messageVO);
			      clientChatInfo.setChatInfoVO(chatInfoVO);

			      System.out.println("-----Outgoing----");
					System.out.println("joinChatCount : "+clientChatInfo.getJoinChatCount());
					System.out.println("sendMessageCount : "+sendMessageCount);
					System.out.println("leaveChatRoomCount : "+leaveChatRoomCount);
					System.out.println("disconnectCount : "+disconnectCount);
					System.out.println("disconnect  : "+clientChatInfo.isDisconnect());
					System.out.println("Left : "+clientChatInfo.isLeftChatRoom());
			      
				return clientChatInfo;

			}

			private void joinChatCount(DataInputStream is, PrintStream os, ChatInfoVO chatInfoVO2,ChatInfoVO clientChatInfo,clientThread[] clientThreads,clientThread clientThread) {
				// TODO Auto-generated method stub

				  os.println("JOINED_CHATROOM:"+chatInfoVO2.getChatRoom()
			      	 		+ "\nSERVER_IP:"+chatInfoVO2.getClientIP()
			      	 		+ "\nPORT:"+chatInfoVO2.getPort()
			      	 		+ "\nROOM_REF:"+chatInfoVO2.getChatRoomRefID()
			      	 		+ "\nJOIN_ID:"+chatInfoVO2.getJoinID());

				  synchronized (clientThread) {
					  for (int i = 0; i < clientChatInfo.getMaxClientsCount(); i++) {
		    	          if (clientThreads[i] != null && clientThreads[i] == clientThread) {

		    	            clientChatInfo.setClientName(chatInfoVO2.getClientName());
		    	            clientChatInfo.setChatRoom(chatInfoVO2.getChatRoom());
		    	            clientChatInfo.setLeftChatRoom(false);
						//System.out.println("clientChatInfo.getChatRoom : "+clientChatInfo.getChatRoom());
		    	            break;
		    	          }
		    	        }

					  for (int i = 0; i < clientChatInfo.getMaxClientsCount(); i++) {
						  
		    	          if (clientThreads[i] != null //&& clientThreads[i] != clientThread
										&& null!=clientThreads[i].getChatInfoVO().getRoomRefIds()
		    	        		&& clientThreads[i].getChatInfoVO().getRoomRefIds().contains(chatInfoVO2.getChatRoomRefID())) {
		    	        	//  System.out.println("inside Join Ref : "+chatInfoVO2.getChatRoomRefID());
		    	        	  for(long ref : clientThreads[i].getChatInfoVO().getRoomRefIds()){
		    	        		//  System.out.println("Chat Ref : "+ref);
			            		  if(ref==chatInfoVO2.getChatRoomRefID()){
											 clientThreads[i].getOs().println("CHAT:" +chatInfoVO2.getChatRoomRefID()
				 				      	 		+ "\nCLIENT_NAME:" +chatInfoVO2.getClientName()
				 				      	 		+ "\nMESSAGE:"+chatInfoVO2.getClientName()+"  has joined this chatroom");
			            		  }
		    	        	  }
		    	          }
		    	        }
				  }
				  System.out.println("Joined the club Successfully !");
			}


			private void sendMessage(PrintStream os,ChatInfoVO clientChatInfo,clientThread[] clientThreads,SendMessageVO messageVO2,clientThread clientThread){
				   synchronized (clientThread) {

			            for (int i = 0; i < clientChatInfo.getMaxClientsCount(); i++) {
			            	
			              if (clientThreads[i] != null && clientThreads[i].getChatInfoVO().getClientName() != null
			            		  && null!=clientThreads[i].getChatInfoVO().getRoomRefIds()
			            		  && clientThreads[i].getChatInfoVO().getRoomRefIds().contains(messageVO2.getChatReference())) {
			            	//  System.out.println("inside Join Ref : "+messageVO2.getChatReference());
			            	// System.out.println("ChatRoom on thread loop: "+clientThreads[i].getChatInfoVO().getChatRoom());
					        	//System.out.println("ChatRoom on client: "+clientChatInfo.getChatRoom());
			            	  for(long ref : clientThreads[i].getChatInfoVO().getRoomRefIds()){
			            		//  System.out.println("inside Join Ref : "+ref);
			            		  if(ref==messageVO2.getChatReference()){
					            	  clientThreads[i].getOs().println("CHAT:" +messageVO2.getChatReference()
						      	 		+ "\nCLIENT_NAME:" +messageVO2.getClientName()
						      	 		+ "\nMESSAGE:"+messageVO2.getMessage());
			            		  }
			            	  }
			            	  
			              }
			            }
			          }
				   System.out.println("Message Sent Successfully !");
			}

			private void leaveChatRoom(PrintStream os,ChatInfoVO clientChatInfo,clientThread[] clientThreads,clientThread clientThread){

		    	  synchronized (clientThread) {
		  	        for (int i = 0; i < clientChatInfo.getMaxClientsCount(); i++) {
		  	          if (clientThreads[i] != null && clientThreads[i] == clientThread
		  	              && clientThreads[i].getChatInfoVO().getClientName() != null
		  	            		&& null!=clientThreads[i].getChatInfoVO().getRoomRefIds()
		  	            		  && clientThreads[i].getChatInfoVO().getRoomRefIds().contains(clientChatInfo.getChatRoomRefID())  
		  	            		 ) {

		  	        	  for(long ref : clientThreads[i].getChatInfoVO().getRoomRefIds()){
		  	        		  
		            		  if(ref==clientChatInfo.getChatRoomRefID()){
				  	           	clientThreads[i].getOs().println("LEAVE_CHATROOM:" + clientChatInfo.getChatRoomRefID()
				      	 		+ "\nJOIN_ID:" + clientChatInfo.getJoinID()
				      	 		+ "\nCLIENT_NAME:" + clientChatInfo.getClientName());
		            		  }
		  	        	  }
		  	          }
		  	        }
		  	      }
		    	  os.println("LEFT_CHATROOM:"+clientChatInfo.getChatRoomRefID()
	      	 		+ "\nJOIN_ID:"+clientChatInfo.getJoinID());
		    	  System.out.println("Left Chatroom Successfully !");
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


