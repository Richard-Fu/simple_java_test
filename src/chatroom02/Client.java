package chatroom02;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
//Client要做这么几件事
//首先链接服务器
//收服务器的消息  判断是哪个用户发来的   显示并更新该用户的chatFrame（所以chatFrame要有个更新消息的快捷方法）
//收服务器的用户信息  并更新JList
//发信息  被chatFrame调用  被loginFrame调用 
//由于会阻塞所以需要两个线程
public class Client extends JFrame{

	//自己做自己的变量？
	private static Client client;
	//服务器信息
	private final String IP = "127.0.0.1";
	private final int PORT = 33233;
	
	//GUI信息  没什么好想法  就一个JList算了
	//存JList数据的
	private DefaultListModel<UserInfo> listModel = new DefaultListModel<>();
	private JList<UserInfo> list = new JList(listModel);
	//首先用来接收服务器传来的UserInfo  
	private ArrayList<UserInfo> users;
	//存所有人用户
	private UserInfo all = new UserInfo("所有人" ,"", "all"
			, null , -2000, null);
	//客户端的Socket
	private Socket socket;
	//客户端的输出流
	private BufferedWriter out;
	//测试printStream
	private PrintStream ps;
	//客户端的输入流
	private BufferedReader in;
	//输入流 接收对象
	private ObjectInputStream inObject;
	//当前客户端自己的用户名
	public static String NAME;
	//初始化GUI
	public Client(String name)
	{
		super("局域网聊天");
		// 设置该JList使用ImageCellRenderer作为单元格绘制器
		list.setCellRenderer(new ImageCellRenderer());
		this.NAME = name;
		listModel.addElement(all);
		list.addMouseListener(new ChangeMusicListener());
		add(new JScrollPane(list));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2, 2, 200 , 600);
		//改左上角图标
				ImageIcon icon=new ImageIcon("icon/icon.png");
		        this.setIconImage(icon.getImage()); 
		//让窗口中间显示？
				setLocationRelativeTo(null);
	}
	// ------下面四个方法是对ListModel的包装------
			// 向用户列表中添加用户
			public void addUser(UserInfo user)
			{
				listModel.addElement(user);
			}
			// 从用户列表中删除用户
			public void removeUser(int pos)
			{
				listModel.removeElementAt(pos);
			}
			// 获取该聊天窗口的用户数量
			public int getUserNum()
			{
				return listModel.size();
			}
			// 获取指定位置的用户
			public UserInfo getUser(int pos)
			{
				return listModel.elementAt(pos);
			}
	//初始化网络
	public void init() throws IOException
	{
		//首先初始化网络  链接到服务器
			//连接到服务器   String int
			socket = new Socket(IP, PORT);
			//获取该socket对应的输入输出流 并封装到节点流
			//out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//为什么写用 PrintStream都能发出去  BufferedWriter不能呢？
			ps = new PrintStream(socket.getOutputStream());
			//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			inObject = new ObjectInputStream(socket.getInputStream());
			new ReadObject().start();
			//new ReadMsg().start();
			System.out.println("连接服务器失败");
		
		//然后调用send()  也就是往服务器写
		//然后启动接受信息线程  并及时更新GUI
	}
	/*public static void main(String[] args) {
		// TODO 自动生成的方法存根
		client = new Client();
		client.init();
		
	}*/
	//客户端  写操作
	public void send(String content)
	{
		/*//调用out
		try {
			out.write(content);
			out.flush();
			//System.out.println("123");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/
		//证明是上面的out.write方法的问题
		ps.println(content);
		
	}
	//新建一个线程 读服务器的数据
	class ReadObject extends Thread
	{
		Object o;
		public void run()
		{
			try {
				
				//ArrayList<UserInfo> temp = new ArrayList<>();
				while((o =inObject.readObject())!=null)
				{//if 是 arraylist
					if(o.getClass() == ArrayList.class)
					{	
						System.out.println("收对象！");
						users =(ArrayList<UserInfo>)o;
						//收到列表之后的操作  其实是更新DefualtList
						System.out.println("收到列表!" + "   size :" +users.size());
						/*for(UserInfo u : users)
						{
						System.out.println(u.getName() + "   " +u.getIcon());
						}*/
						//判断users里面是否有“所有人”
						if(!users.contains(all))
							users.add(all);
					//遍历users 判断listModel里面是否包含此对象 如果没有 添加
					for(UserInfo u : users)
					{
						/*System.out.println("用户数:  " +getUserNum());
						boolean isAdd = true;
						if(listModel.isEmpty())
							{addUser(u);
							 isAdd = false;
							 System.out.println("用户数:  " +getUserNum());
							}
						else
							for(int i=1 ;i<getUserNum(); i++)
							{
								if(getUser(i).equals(u))
									isAdd = false;
							}
						if(isAdd)
							addUser(u);*/
						//System.out.println(u.getName() + "   " +u.getIcon());
						if(!listModel.contains(u))
							addUser(u);
					}
					//再遍历listModel  看listModel里面是否有users没有的  也就是已经下线的
					for(int i=0 ;i<getUserNum(); i++)
					{
						//避免把所有人删除
						if(!users.contains(getUser(i))&&getUser(i).getName()!="所有人")
						{
							removeUser(i);
						}
					}
				}//If 是String
					else if(o.getClass() == String.class)
					{
						//拆数据
						String msg = (String)o;
						if(msg.startsWith(Protocol.MASSAGE) &&
								msg.endsWith(Protocol.MASSAGE))
						{
							//拆信息
							System.out.println("收到内容：" + msg);
							String temp = msg.substring(Protocol.LENGTH
									, msg.length()-Protocol.LENGTH);
							//再拆
							String[] content = temp.split(Protocol.SPLITTER);
							System.out.println(content.length);
							System.out.println(content[0]);
							System.out.println(content[1]);
							//存被发信息的对象信息   用来找聊天框
							UserInfo user;
							//如果是所有人信息
							if(content[0].startsWith(Protocol.ALL) 
									&& content[0].endsWith(Protocol.ALL))
							{
								//去掉标签
								String q = content[0].substring(Protocol.LENGTH
										, content[0].length()-Protocol.LENGTH);
								System.out.println(q);
								String[] w = q.split(Protocol.ALLSPLIT);
								System.out.println(w.length);
								System.out.println(w[0]);
								System.out.println(w[1]);
								//content[0]还是存发信息人的name
								content[0] = w[1];
								//user 为 所有人
								user = searchByName(w[0]);
							}
							//找到该user的chatFrame  显示它  并更新它的消息框
							else
								 user = searchByName(content[0]);
							//如果有  显示并更新  
							if(user.getChatFrame()!=null)
							{	// 如果该用户的窗口没有显示，则让该用户的窗口显示出来
								if (!user.getChatFrame().isShowing())
								{
									
									user.getChatFrame().setVisible(true);
								}
								//如果是自己发的群发消息  不用再更新对话框
								if(!content[0].equals(NAME))
									{user.getChatFrame()
									.refreshText(content[0], content[1]);
									}
							}
							//如果没有 添加 并更新
							else 
							{
								user.setChatFrame(new ChatFrame(null, user));
								//如果是自己发的群发消息  不用再更新对话框
								if(!content[0].equals(NAME))
									{user.getChatFrame()
									.refreshText(content[0], content[1]);
									}
								user.getChatFrame().setVisible(true);
							}
						}
					}
				}
				
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				//如果掉线  关所有IO
			}
			
		}
	}// close ReadObject
	//读普通数据的线程
	/*class ReadMsg extends Thread
	{
		String msg = "";
		public void run()
		{
			try
			{
				System.out.println("收话！");
				while((msg = in.readLine()) != null)
				{
					//拆数据
					if(msg.startsWith(Protocol.MASSAGE) &&
							msg.endsWith(Protocol.MASSAGE))
					{
						//拆信息
						String temp = msg.substring(Protocol.LENGTH
								, msg.length()-Protocol.LENGTH);
						//再拆
						String[] content = temp.split(Protocol.SPLITTER);
						//找到该user的chatFrame  显示它  并更新它的消息框
						UserInfo user = searchByName(content[0]);
						//如果有  显示并更新  
						if(user.getChatFrame()!=null)
						{
							user.getChatFrame().refreshText(content[1]);
							user.getChatFrame().setVisible(true);
						}
						//如果没有 添加 并更新
						else 
						{
							user.setChatFrame(new ChatFrame(null, user));
							user.getChatFrame().refreshText(content[1]);
						}
					}
				}
			}catch(IOException ex)
			{
				//如果掉线  关所有东西
			}
		}
	}*/
	
	// 定义用于改变JList列表项外观的类
	class ImageCellRenderer extends JPanel
		implements ListCellRenderer<UserInfo>
	{
		private ImageIcon icon;
		private String name;
		// 定义绘制单元格时的背景色
		private Color background;
		// 定义绘制单元格时的前景色
		private Color foreground;
		@Override
		public Component getListCellRendererComponent(JList list
			, UserInfo userInfo , int index
			, boolean isSelected , boolean cellHasFocus)
		{
			// 设置图标
			icon = new ImageIcon("icon/" + userInfo.getIcon() + ".gif");
			name = userInfo.getName();
			// 设置背景色、前景色
			background = isSelected ? list.getSelectionBackground()
				: list.getBackground();
			foreground = isSelected ? list.getSelectionForeground()
				: list.getForeground();
			// 返回该JPanel对象作为单元格绘制器
			return this;
		}
		// 重写paintComponent方法，改变JPanel的外观
		public void paintComponent(Graphics g)
		{
			int imageWidth = icon.getImage().getWidth(null);
			int imageHeight = icon.getImage().getHeight(null);
			g.setColor(background);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(foreground);
			// 绘制好友图标
			g.drawImage(icon.getImage() , getWidth() / 2 - imageWidth / 2
				, 10 , null);
			g.setFont(new Font("SansSerif" , Font.BOLD , 18));
			// 绘制好友用户名
			g.drawString(name, getWidth() / 2 - name.length() * 10
				, imageHeight + 30 );
		}
		// 通过该方法来设置该ImageCellRenderer的最佳大小
		public Dimension getPreferredSize()
		{
			return new Dimension(60, 80);
		}
	}
	
	// 实现JList上的鼠标双击事件的监听器
		class ChangeMusicListener extends MouseAdapter
		{
			public void mouseClicked(MouseEvent e)
			{
				// 如果鼠标的击键次数大于2
				if (e.getClickCount() >= 2)
				{
					// 取出鼠标双击时选中的列表项
					UserInfo user = (UserInfo)list.getSelectedValue();
					// 如果该列表项对应用户的交谈窗口为null        //默认就是null
					if (user.getChatFrame() == null)
					{
						// 为该用户创建一个交谈窗口，并让该用户引用该窗口
						user.setChatFrame(new ChatFrame(null , user));
					}
					// 如果该用户的窗口没有显示，则让该用户的窗口显示出来
					if (!user.getChatFrame().isShowing())
					{
						user.getChatFrame().setVisible(true);
					}
				}
			}
		}
		
		//通过name  找当前user
		private UserInfo searchByName(String name)
		{
			//循环users 找匹配
			for(UserInfo user : users)
			{
				if(user.getName().equals(name))
				{
					return user;
				}
			}
			return null;
		}
}
