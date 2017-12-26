//2017.11.25
//客户端  服务器的协议符号  通过这个接口来定义这些符号
//刚开始弄成类了，    应该是接口      再一次体现出接口的性质了
package chatroom02;

public interface Protocol {

	//符号长度
	public final static int LENGTH = 2;
	//用户发出的信息  标识
	public final static String MASSAGE ="!!";
	//所有人的信息
	public final static String ALL = "%%";
	//所有人里面的分隔符
	public final static String ALLSPLIT = "!#";
	//在线信息标识
	public final static String ONTIME = "@@";
	//名字  内容分割符
	public final static String SPLITTER = "##";
}
