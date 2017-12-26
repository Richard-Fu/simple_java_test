package jingame;

import java.awt.image.BufferedImage;
import java.beans.Transient;

public class Npc implements Thing{

	//Npc是所有Npc的父类   感觉这样好麻烦 每个Npc都要一个类？不跟怪物一样吗，每个怪物一个类
	//Npc有名字 头像 还有剧情 还能卖装备吧  还是只能买属性？
	//买装备还好 只要显示出来加点键盘响应就行，卖装备要存玩家目前的所有的装备
	//算了 只能买属性  而且是固定的
	
	//所有就只剩名字 头像  是否有剧情 和是否有商店  是否有奇遇（这个存在不多）
	//剧情用个剧情数组      商店就只要存属性值就行
	protected transient BufferedImage head;
	protected String name;
	protected Monster m;
	//是否是剧情
	protected boolean isStory;
	//是否是商店
	protected boolean isStore;
	//是否有奇遇
	protected boolean isAdventure;
	//是否是门
	protected boolean isDoor;
	//剧情对话内容
	protected StoryPoint[] storylist;
	//属性值列表   也要个数据结构来存   首先存数值  然后存价格
	protected NumPoint[] numList;
	//存Door里面的信息  用int就够了
	protected int[] num2List;
	//那其实跟Monster原理一样  也可以只用构造函数  不用新类
	public Npc() {}
	public Npc(String name, boolean isStory, boolean isStore, boolean isAdventure
			,boolean isDoor, StoryPoint[] storylist
			, NumPoint[] numList, int[] num2List
			, BufferedImage head)
	{
		this.name = name;
		this.isStory = isStory;
		this.isStore = isStory;
		this.isAdventure = isAdventure;
		this.storylist = storylist;
		this.numList = numList;
		this.num2List = num2List;
		this.isDoor = isDoor;
		this.head = head;
	}
	public BufferedImage getHead() {
		return head;
	}
	public void setHead(BufferedImage head) {
		this.head = head;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStory() {
		return isStory;
	}
	public void setStory(boolean isStory) {
		this.isStory = isStory;
	}
	public boolean isStore() {
		return isStore;
	}
	public void setStore(boolean isStore) {
		this.isStore = isStore;
	}
	public boolean isAdventure() {
		return isAdventure;
	}
	public void setAdventure(boolean isAdventure) {
		this.isAdventure = isAdventure;
	}
	public StoryPoint[] getStorylist() {
		return storylist;
	}
	public void setStorylist(StoryPoint[] storylist) {
		this.storylist = storylist;
	}
	
	//设置方法初始化Point
	protected static StoryPoint setStoryPoint(boolean who,String content)
	{
		return new StoryPoint(who,content);
	}
	protected NumPoint setNumPoint(int num, int money)
	{
		return new NumPoint(num, money);
	}
	public NumPoint[] getNumList() {
		return numList;
	}
	public void setNumList(NumPoint[] numList) {
		this.numList = numList;
	}
	@Override
	public void doJob(Hero man, int i, int j) {
		// TODO 自动生成的方法存根
		//先把storyBounds设好
		//npc 默认说次话就消失 
		//如果有特殊的  重写该方法!
				Game.position.getF().things[i][j] = new Floor();
				Game.position.getF().flag[i][j] = 1;
				Game.story.setBounds(480,345,790,250);
				Game.isStory = true;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				Game.story.showStory(this , man);
				
		
	}
	@Override
	public BufferedImage getImage() {
		// TODO 自动生成的方法存根
		return head;
	}
	public boolean isDoor() {
		return isDoor;
	}
	public void setDoor(boolean isDoor) {
		this.isDoor = isDoor;
	}
	public Monster getM() {
		return m;
	}
	public void setM(Monster m) {
		this.m = m;
	}
	public int[] getNum2List() {
		return num2List;
	}
	public void setNum2List(int[] num2List) {
		this.num2List = num2List;
	}
}
