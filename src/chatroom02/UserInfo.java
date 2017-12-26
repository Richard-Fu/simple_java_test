package chatroom02;

import java.io.ObjectOutputStream;
import java.net.*;
import java.nio.channels.SocketChannel;
public class UserInfo implements java.io.Serializable
{

	//该用户的图标  这里怎么写  之后画JList的时候就要对应
	private String icon;
	//该用户的名字
	private String name;
	//密码  密码不能传
	private transient String password;
	//服务器端   SocketChannel(每个用户对应一个)
	//为了在传输的时候  能传输UserInfo   因为Socket不允许序列化  所以把它设计成transient的
	private transient Socket socket;
	//该用户失去联系的次数
	private transient int lost;
	//该用户对应的交谈窗口  首先不管他  所以初始为null
	private transient ChatFrame chatFrame;
	//再来个ObjectOutputStream op
	private transient ObjectOutputStream op;
	public UserInfo() {}
	//有参数的构造器   不构造chatFrame
	public UserInfo(String name, String password, String icon, Socket socket
			, int lost, ObjectOutputStream op)
	{
		this.icon = icon;
		this.name = name;
		this.password = password;
		this.socket = socket;
		this.lost = lost;
		this.op = op;
	}
	//只存用户名 头像
	public UserInfo(String name, String icon)
	{
		this.icon = icon;
		this.name = name;
	}
	//所有的getter setter 方法
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public int getLost() {
		return lost;
	}
	public void setLost(int lost) {
		this.lost = lost;
	}
	public ChatFrame getChatFrame() {
		return chatFrame;
	}
	public ObjectOutputStream getOp() {
		return op;
	}
	public void setOp(ObjectOutputStream op) {
		this.op = op;
	}
	public void setChatFrame(ChatFrame chatFrame) {
		this.chatFrame = chatFrame;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//使用address作为UserInfo 的标识 ，
	//所以通过address来写hashCode,equals
	/*public int hashCode()
	{
		return socket.hashCode();
	}*/
	public boolean equals(Object obj)
	{
		if(obj !=null && obj.getClass() == UserInfo.class)
		{
			//转换成UserInfo
			UserInfo target = (UserInfo)obj;
			//避免两个null比来比去
			//对服务器  提的功能
			if(getSocket()!=null)
				{if(getSocket().toString() != null)
				{
				/*System.out.println(target.getSocket().toString());
				System.out.println(getSocket().toString());
				System.out.println(getSocket().toString()
						.equals(target.getSocket().toString()));*/
				/*System.out.println((getSocket().toString()
						.equals(target.getSocket().toString())));*/
				//比较socket 的字符串  而不是socket
				return (getSocket().toString().equals(target.getSocket().toString()));
				}
				}
			else//因为客户端存的socket都为空  所以不能比socket 只能比name
			{
				if(getName()!=null)
				{
					return(getName().equals(target.getName()));
				}
			}
		}
		//体现代码牛逼了   不用else   如果上面if没成功  怎么都要走这一步
		return false;
	}
	
}

