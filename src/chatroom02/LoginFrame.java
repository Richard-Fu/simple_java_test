//2017.11.25
//登录界面   程序通过它启动    感觉怪怪的  不符合常理
package chatroom02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.swing.*;
public class LoginFrame extends JFrame{
	//首先定义GUI参数
	//显示大图的JLabel
	private JLabel top ;
	//选择头像的JComboBox
	private JComboBox headBox;
	//头像图标数组    因为JComboBox构造传图标数组就构成了图标下拉框
	private String[] heads;
	//JList的装饰器    是我弄错了DefaultListModel是让JList列表项可变  （它是元素可变的集合类）
	//装饰器其实只是用ListCellRenderer
	//用户名框
	private JTextField userName;
	//密码框
	private JPasswordField password;
	//登录按钮
	private JButton login;
	//还是要用client发送消息
	public static Client client;
	//构造并使它初始
	public LoginFrame(String title)
	{
		super(title);
		//client = parent;
		//初始top使它是个大图  并设置大小
		top = new JLabel(new ImageIcon("icon/chatroomtitle.png"));
		top.setPreferredSize(new Dimension(430,130));
		//初始JComboBox
		heads = new String[]
				{
						"1", "2", "3", "4", "5", "6"
				};
		headBox = new JComboBox(heads);
		//给JComboBox加装饰器   继承JPanel,接口ListCellRenderer
		headBox.setRenderer(new ImageCellRenderer());
		//给JComboBox加个响应器  返回选了哪个选项 应该不用  直接用getSelectedItem
		
		//用户名框   参数  默认值   框能输入多少字符
		userName = new JTextField("用户名", 12);
		//默认显示******
		password = new JPasswordField("123456", 12);
		//password默认回显字符应该是*
		
		login = new JButton("登录");
		//给login加响应器  
		login.addActionListener(e ->{
			//这个响应器要做两件事  一是 把注册信息发给服务器   并每隔一会就发一次
			//二是 初始化客户端（我感觉不用这样    让客户端调LoginFrame比较好， 只是客户端默认不显示
			//我这里让你显示）也就是显示client  不显示this框  但是我还是存在的   然后chatFrame变成client
			//的内部类  然后直接调用client里面的方法就是  这样问题应该都解决了
			client = new Client(userName.getText());
			try
			{	client.init();
				//首先发消息
				//按要求封装好 消息   注意password的获取方法getPassword  不能用getText了  不安全好像
				String content = Protocol.ONTIME +userName.getText() 
				+Protocol.SPLITTER + String.valueOf(password.getPassword()) + Protocol.SPLITTER 
				+headBox.getSelectedItem() + Protocol.ONTIME;
				client.send(content);
				System.out.println(content);
				//  启动Timer 每20秒发一次
				javax.swing.Timer timer = new javax.swing.Timer(1000*5 ,event ->
				{
					client.send(content);	
				});
				timer.start();
			}catch(IOException ex)
			{
				JOptionPane.showMessageDialog
        		(null, "请确认服务器在线,并联系管理员"
        				,"ERROR",JOptionPane.WARNING_MESSAGE);
				//因为JOption是模式的   所以我不按确定他就不会往下进行 
				//体现了一点JOptionPane框的作用
				System.exit(1);
			}
			//显示client  然后this不显示
			client.setVisible(true);
			setVisible(false);
		});
		add(top, BorderLayout.NORTH);
		add(headBox, BorderLayout.WEST);
		
		//让用户名栏和密码栏  增添一个焦点响应
		//本来 里面都有默认值   当获取焦点  默认值清空
		userName.addFocusListener(new FocusAdapter()
				{
			
					public void focusGained(FocusEvent e)
					{
						userName.setText("");
					}
				});
		password.addFocusListener(new FocusAdapter()
				{
					public void focusGained(FocusEvent e)
					{
						password.setText("");
					}
				});
		//装载userName password
		JPanel info = new JPanel();
		//让它为Box式布局
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		info.add(userName);
		info.add(password);
		add(info, BorderLayout.CENTER);
		add(login, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//让窗口中间显示？
		setLocationRelativeTo(null);
		//改左上角图标
		ImageIcon icon=new ImageIcon("icon/icon.png");
        this.setIconImage(icon.getImage()); 
		pack();
		setVisible(true);
	}
	//ImageCellRenderer   JComboBox的装饰器  JList也是这样装饰
	//刚才出了个大错   重写的函数名写错了  搞的他以为我是自己定义的所以显示不出来   吃了大亏！！！
	class ImageCellRenderer extends JPanel implements ListCellRenderer
	{
		//JComboBox  GUI成员  需要用getListCellRendererComponent里面的参数  得到每一项的属性
		//再用这些属性  把它们绘制出来
		private ImageIcon icon;
		private String name;
		private Color background;
		private Color foreground;
		//下面是ListCellRenderer接口里面的方法
		//返回每一个列表项  （已经转换成对应的Component了）
		public Component getListCellRendererComponent(JList list
				, Object value, int index, boolean isSelected, boolean cellHasFocus )
		{
			//通过列表项属性  给上面声明的变量赋值  为了下面把它们画出来
			icon = new ImageIcon("icon/" +value + ".gif");
			//根据实践  目前不需要画name  因为是选择头像
			name = value.toString();
			//这里的被不被选好像是JList  JComboBox里面的接口实现的  以后一定要研究他们的源代码
			//感觉设计的好屌   昨天也看了一个课 说编程用的是工程思维 不是科学思维  工程思维是资源受限 只要出一个合适解
			//而科学思维是最优解      也就是算法数据结构里面最厉害的算法与结构  
			
			//这里面的list方法返回的Color其实可以自己设置
			background = isSelected ? list.getSelectionBackground()
					: list.getBackground();
			foreground = isSelected ? list.getSelectionForeground()
					: list.getForeground();
			//返回该JPanel对象作为列表绘制器  也就是自己
			return this;
		}//close getComponent
		
		//重写JPanel里面的paintComponent()方法,改变JPanel的外观   好像可用作设置背景  
		//配合JLayer有层级关系     JPanel做背景   试试
		public void paintComponent(Graphics g)
		{
			//获取图片大小  便于之后布局    null 指的是  ImageObserver observer
			int imageWidth = icon.getImage().getWidth(null);
			int imageHeight = icon.getImage().getHeight(null);
			//画背景
			//先设画笔颜色    体现为什么这个 类 要设几个成员变量了
			g.setColor(background);
			//这里的getWidth() , getHeight()是返回该Component的属性
			//之后的getDimention里面设置了该Component的大小
			g.fillRect(0, 0, getWidth(), getHeight());
			//画前景
			g.setColor(foreground);
			//绘制图标        null 指的是  ImageObserver observer
			g.drawImage(icon.getImage(), getWidth()/2 - imageWidth/2, 10, null);
			//不用画字
			//g.drawString(name, getWidth()/2-name.length()*10, imageHeight+30);
		}
		public Dimension getPreferredSize()
		{
			return new Dimension(60,80);
		}
	}
	/*//还是要搞个线程搞client
	class initClient extends Thread
	{
		public void run()
		{
			client = new Client();
			client.init();
		}
	}*/
	public static void main(String[] args)
	{
		new LoginFrame("ss");
	}
}
