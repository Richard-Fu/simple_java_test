package jingame;

public class StoryPoint implements java.io.Serializable{
	//用来区分谁说话
	//0表示Npc 1表示Hero
	private boolean whoTalk;
	//存说话的内容
	private String content;
	//两个参数构造
	public StoryPoint(boolean whoTalk, String content)
	{
		this.whoTalk = whoTalk;
		this.content = content;
	}
	
	

	public boolean isWhoTalk() {
		return whoTalk;
	}



	public void setWhoTalk(boolean whoTalk) {
		this.whoTalk = whoTalk;
	}



	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
