//2017.11.25
package chatroom02;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

//继承Dialog
public class ChatFrame extends Dialog {

	// 定义一个用于格式化日期的格式器
		private DateFormat formatter = DateFormat.getDateTimeInstance();
	//用户
	UserInfo user ;
	//聊天信息区
	JTextArea msgArea = new JTextArea(20,60);
	//聊天输入区
	JTextField msgField = new JTextField(30);
	//发送按钮
	JButton send = new JButton("发送(ctrl+enter)");
	//用户信息区
	JPanel userInfo = new JPanel();
	//用户头像
	ImageIcon image ;
	//首先设计GUI     这里Client参数在用户注册的时候就把它给初始化了，但不让它显示 1
	//要么就通过LoginFrame LoginFrame有一个类成员是Client  通过它调用  2
	//还是用第二种方法    所以初始化的时候就让parent=null
	public ChatFrame(Client parent, final UserInfo user)
	{
		super(parent, "和" + user.getName() +"聊天中");
		this.user = user;
		this.image = new ImageIcon("icon/"+user.getIcon() + ".gif");
		//让消息区不可编辑
		msgArea.setEditable(false);
		//给消息区加滚动条   BorderLayout布局
		add(new JScrollPane(msgArea));
		userInfo.add(new JLabel(user.getName(),image,JLabel.CENTER));
		add(userInfo,BorderLayout.NORTH);
		JPanel bottom = new JPanel();
		bottom.add(new JLabel("请输入："));
		bottom.add(msgField);
		bottom.add(send);
		add(bottom,BorderLayout.SOUTH);
		//添加为send添加监听器        书上用的是Action接口  我感觉这里没必要用   但是  Action接口功能很强大
		//以后用得到         刚才忘记lambda怎么写了   它适用于函数式接口    这个e代表里面方法的参数  然后直接写就行
		Action sendAction = new AbstractAction()
		{
			public void actionPerformed(ActionEvent evt)
			{
			//直接调用LoginFrame里面的Client的send方法
			//内容由ChatFrame来封装massage      因为这个用户消息 所以用MASSAGE来做记号
			//然后用户名和内容用SPLITTER隔开  然后client调用send发送给服务器
			//服务器再通过格式解开   获得用户名和内容     还可以通过当前的Socket（1）来辨认出谁发的
			//然后再通过这个用户名获取他的Socket  再通过这个Socket （2）
			//封装Socket(1)的用户名和内容  通过（2）发送
			//然后客户端  解开消息   获得用户名和内容  就知道是谁发的
			//通过这个用户名获取chatFrame  调用这个chatFrame显示  然后再把消息填上msgArea通过refreshText
			String content = Protocol.MASSAGE +user.getName() +Protocol.SPLITTER 
					+ msgField.getText() +Protocol.MASSAGE;
			LoginFrame.client.send(content);
			refreshText("我" , msgField.getText());
			//清空msgField以便方便
			msgField.setText("");
			}
		};
		send.addActionListener(sendAction);
		// 将Ctrl+Enter键和"send"关联
		msgField.getInputMap().put(KeyStroke.getKeyStroke('\n'
			, java.awt.event.InputEvent.CTRL_MASK) , "发送(ctrl+enter)");
		// 将"send"与sendAction关联
		msgField.getActionMap().put("发送(ctrl+enter)", sendAction);
		//chatFrame其实只要干这个工作  封装消息 调用client的send方法                  client其实不用管群发还是
		//私人发  只管发给服务器就是  这个跟UDP不一样  刚开始被迷惑了  UDP是有个IP 端口专门是broadcast的 
		//它每个用户还有个自己的IP 端口  而TCP有服务器和客户端之分  所以只需一股脑的把消息按照格式给服务器就行
		//服务器怎么解析  是它的事（按照格式反解就行）  思路清晰了
		
		//给该Dialog  赋予关闭的权利
		this.addWindowListener(new WindowAdapter()  
	    {  
	        public void windowClosing(WindowEvent e)  
	        {  
	        	//好像懂怎么在内部类里调用外部类本身的东西了 
	        	//因为在内部类里用的this 是指向当前内部类 而指不到外部类
	        	//所以调用外部类 里面的函数  在外部类里面的函数里面用this 
	        	//不就是指向外部类吗   牛逼 学到了
	            closeFrame();  
	        }  
	    });
		//改左上角图标
				ImageIcon icon=new ImageIcon("icon/icon.png");
		        this.setIconImage(icon.getImage()); 
		//让窗口中间显示？
				setLocationRelativeTo(null);
		//程序框自适应我的定义的各种大小
		pack();
	}
	// 关闭窗体   
    private void closeFrame()  
    {  
        System.out.println("调用窗体关闭功能");  
        int result = JOptionPane.showConfirmDialog
        		(null, "只隐藏该页面（不关闭）", "确认", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);  
        if (result == JOptionPane.NO_OPTION)  
            this.dispose();  
        else
        	this.setVisible(false);
    }  
	//向聊天区域更新内容方法   (通过Client来调用,Client根据Socket判断user,通过user得到对应ChatFrame
	//再通过该ChatFrame 调用该方法进行更新
	public void refreshText(String name, String content)
	{
		//加在已有信息前
		msgArea.setText(name+"————"+formatter.format(new Date())+": \n " +content
				+ "\n" + msgArea.getText());
	}  
}
