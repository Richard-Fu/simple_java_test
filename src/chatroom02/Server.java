package chatroom02;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class Server extends JFrame{

	//table的首行
	String[] first = {"用户名", "密码", "图标", "Socket", "在线"};
	//维护表格数据  //扩展了DefalutTabelModel
	ExtendedTableModel model = new ExtendedTableModel(first, null);
	//表格
	JTable table = new JTable(model);
	//TextArea  存用户消息传输过程
	JTextArea area = new JTextArea(20, 50); 
	// 定义一个用于格式化日期的格式器
			private DateFormat formatter = DateFormat.getDateTimeInstance();
	public Server(String title)
	{
		super(title);
		//area不可编辑
		area.setEditable(false);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(40);
		//设置table各列宽
		table.getColumn(first[3]).setPreferredWidth(400);
		//把table加个滚动条   并加到当前Frame上
		add(new JScrollPane(table));
		//加area在下面  并加滚动条
		add(new JScrollPane(area), BorderLayout.SOUTH);
		//让该JFrame能被关闭
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置Frame大小
		setPreferredSize(new Dimension(600, 800));
		//改左上角图标
		ImageIcon icon=new ImageIcon("icon/icon.png");
        this.setIconImage(icon.getImage()); 
		pack();
		setVisible(true);
	}
	//拿个ArrayList存用户
	ArrayList<UserInfo> users = new ArrayList<>();
	
	public void init()
	{
		try 
			(ServerSocket ss = new ServerSocket(33233))
		{	while(true)
			{
				//此行代码会堵塞  一直等别人连接
				Socket socket = ss.accept();
				new ServerThread(socket).start();
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new Server("server").init();
	}

	private class ServerThread extends Thread
	{
		Socket socket;
		ObjectOutputStream sendList;
		BufferedReader bb;
		public ServerThread(Socket socket)
		{
			this.socket = socket;
		}
		//我知道之前为什么多个客户端没用了   因为我没写在run里面
		public void run()
		{
			try {
				bb = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//每个客户端 都有自己的sendList
				sendList = new ObjectOutputStream(socket.getOutputStream());
				String line = null;
				System.out.println("server" +socket);
				//事实证明 这个while是堵塞的    为什么我一加这个while 就只能同时存在一个客户端?
				try
				{	while((line = bb.readLine())!=null)
					{
					//每次读到判断是什么消息
					//如果是在线消息
					if(line.startsWith(Protocol.ONTIME) && 
							line.endsWith(Protocol.ONTIME))
					{
						//消息结构
						System.out.println("服务器收到在线：" + line);
						/*Protocol.ONTIME +userName.getText() 
						+Protocol.SPLITTER
						+ String.valueOf(password.getPassword()) + Protocol.SPLITTER 
						+headBox.getSelectedItem() + Protocol.ONTIME;*/
						//拆数据   把参数一  到参数二位置的字符串拆出来
						String content = line.substring(Protocol.LENGTH,
								line.length()-Protocol.LENGTH);
						//再拆    按照参数  拆成几个字符串
						String[] info = content.split(Protocol.SPLITTER);
						//创一个新对象   初始lost为0
						//System.out.println("icon:  "+info[1]);
						UserInfo user = new UserInfo(info[0], info[1], info[2], socket
								, 0, sendList);
						//转化成Object[]  存入model
 						Object[] o = {user.getName(), user.getPassword()
									, new ImageIcon("icon/" + user.getIcon() + ".gif")
									, user.getSocket(), true};
						//先判断users是否有值（也就是是否已经注册）
						if(users.isEmpty())
						{
							user.setLost(0);
							users.add(user);
							model.addRow(o);
							//更新信息框
							area.setText(formatter.format(new Date())+"---"
									+(user.getName()) 
									+"  上线"
									+"\n" +area.getText());
						}
						//看到底加不加
						boolean isAdd = true;
						//把每个用户都遍历一遍
						for(UserInfo u : users)
						{
							//如果
							/*System.out.println(u.getSocket().toString());
							System.out.println(user.getSocket().toString());
							System.out.println(u.getSocket().toString()
									.equals(user.getSocket().toString()));*/
							if(u.equals(user))
							{
								u.setLost(0);
								//System.out.println("lost1:  "+u.getLost());
								isAdd = false;
							}
							//如果当前lost>2  去除该用户
							if(u.getLost()>2)
							{
								//列表移除
								users.remove(u);
								//数据库 该用户在线信息为false
								model.setFalse(u.getName());
							}
							//当前lost+1
							u.setLost(u.getLost()+1);
							//测试  试试加没加入users
							System.out.println(u.getName() +u.getSocket() +u.getLost() 
							+ "  "
							+ u.getSocket()
							/*.toString().equals(u.getSocket()
									.toString())*/
							);
						}
						//如果没有包含 就添加它
						if(isAdd)
						{
							users.add(user);
							model.addRow(o);
							area.setText(formatter.format(new Date())+"---"
									+(user.getName()) 
									+"  上线"
									+"\n" +area.getText());
						}
						System.out.println("行数：" +model.getRowCount());
						System.out.println("列数：" +model.getColumnCount());
						//发送在线信息给各自用户
						//接完在线信息就发list给当前客户端
					//UserInfo[] u = new UserInfo[users.size()];
					//users.toArray(u);
					//为什么每次都新建一个Arraylist呢  因为writeObject的机制  只传第一次对象值  所以
						//每次传过去的对象  值不会变   所以每次创建一个新的
							sendList.writeObject(new ArrayList<UserInfo>(users));
							System.out.println(user.getName()+"已收到: "+users.size());
				}//close ontime
					//如果是聊天信息
					else if(line.startsWith(Protocol.MASSAGE) &&
							line.endsWith(Protocol.MASSAGE))
					{
						//拆信息
						System.out.println("服务器收到内容：" + line);
						String content = line.substring(Protocol.LENGTH
								, line.length()-Protocol.LENGTH);
						System.out.println("服务器收到内容：" + content);
						//再拆
						String[] msg = content.split(Protocol.SPLITTER);
						//  找要发给谁  也就是msg[0] 参数三是 发消息的对象
						sendMsg(msg[0], msg[1],searchUserBySocket(socket));
						area.setText(formatter.format(new Date())+"---"
								+(searchUserBySocket(socket).getName()) 
								+" 对 " +msg[0] 
								+" 说: "+msg[1]
								+"\n" +area.getText());
						//调用该user的 socket发
					}
			 }}catch(IOException ex)
				{
					//如果传输失败的话
				 //更新服务器  消息框
				 area.setText(formatter.format(new Date())+"---"
							+(searchUserBySocket(socket).getName()) 
							+"  离开"
							+"\n" +area.getText());
				 //数据库 该用户在线信息为false
				 model.setFalse(searchUserBySocket(socket).getName());
				    //移除当前对象
				 users.remove(searchUserBySocket(socket));
				  
					
				    System.out.println("当前users.size: " +users.size());
				    //关闭资源
				    try{
				    	if(bb != null)
				    {
				    	bb.close();
				    }
				    if(sendList != null)
				    {
				    	sendList.close();
				    }
				    if(socket != null)
				    {
				    	socket.close();
				    }
				    }catch(IOException e)
				    {
					   e.printStackTrace();
				    }
				}//close while try
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}//close run
		//通过socket找到当前UserInfo对象
		private UserInfo searchUserBySocket(Socket socket)
		{
			//遍历users
			for(UserInfo user : users)
			{
				if(user.getSocket().equals(socket))
				{
					return user;
				}
			}
			return null;
		}
		//通过name找到当前UserInfo对象
		private UserInfo searchUserByName(String name)
		{
			//遍历users
			for(UserInfo user : users)
			{
				System.out.println((user.getName() + " = " +name)
						+ (user.getName().equals(name)));
				if(user.getName().equals(name))
				{
					return user;
				}
			}
			return null;
		}
		private void sendMsg(String name, String content, UserInfo u)
		{
			//user 是被发   u 是发    也就是name  是被发人 名
			//调用该方法 返回对应UserInfo
			UserInfo user;
			//如果user在线
				try 
				{
					//如果是name 是所有人   就发给每一个用户
					if(name.equals("所有人"))
					{
						for(UserInfo uu : users)
						{
							String msg = Protocol.MASSAGE 
									+ Protocol.ALL + name + Protocol.ALLSPLIT
									+u.getName()
									+ Protocol.ALL
									+Protocol.SPLITTER 
									+ content +Protocol.MASSAGE;
							//调用它的socket发送
							
							System.out.println(uu.getName() + " 被发: " +msg);
							uu.getOp().writeObject(msg);
						}
					}
					if(( user = searchUserByName(name) )!= null)
					{	//包装消息
					
				String msg = Protocol.MASSAGE + u.getName() +Protocol.SPLITTER 
					+ content +Protocol.MASSAGE;
				
				//调用它的socket发送
			
				System.out.println(u.getName() + " 被发: " +msg);
				user.getOp().writeObject(msg);
				}}
			    catch (IOException e) {
				// 
			    	//如果传输失败的话
					//移除当前对象
			    	user = searchUserByName(name);
				    users.remove(user);
				    System.out.println("当前users.size: " +users.size());
				    //关闭资源
				    try{
				    	if(user.getOp()!= null)
				    {
				    		user.getOp().close();
				    }
				    if(user.getSocket() != null)
				    {
				    	user.getSocket().close();
				    }
				    }catch(IOException ex)
				    {
					   ex.printStackTrace();
			    	//发送给u  user不在线的事实
					String msg = Protocol.MASSAGE + user.getName() +Protocol.SPLITTER 
							+ "我已经下线，请关闭聊天框"+content +Protocol.MASSAGE;
					try {
						u.getOp().writeObject(msg);
					} catch (IOException exx) {
						// TODO 自动生成的 catch 块
						exx.printStackTrace();
					}
				}
			}
		}
	}//close serverThread
	class ExtendedTableModel extends DefaultTableModel
	{
		//重新构造一个构造器  委托给父类Defalut干
		public ExtendedTableModel(String[] columnNames, Object[][] cells)
		{
			super(cells, columnNames );
		}
		//重写getColumnClass方法  根据每列第一个值来返回该类类型
		public Class getColumnClass(int c)
		{
			return getValueAt(0, c).getClass();
		}
		//写一个方法 把该对象在线信息改成false
		public void setFalse(String name)
		{
			//遍历每个行的第一个   如果equals  改它的在线
			for(int i=0;i<getRowCount();i++)
			{
				System.out.println(" asd" +getValueAt(i,0).equals(name));
				if(getValueAt(i,0).equals(name))
				{
					setValueAt(false, i, 4 );
				}
			}
		}
	}
}
