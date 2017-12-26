package jingame;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Store1Test extends Npc{

	//测试商店
	public Store1Test ()
	{
		//是商店
		isStore = true;
		//设置名字
		name = "金庸杂货店 ";
		//设置头像
				try {
					head = ImageIO.read(new File("src/jingame/icon/7.gif"));
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		//属性值列表
		numList = new NumPoint[4];
		//依次设置
		//第一个是生命值   (属性，金币)
		numList[0] = setNumPoint(100,20);
		//攻击力
		numList[1] = setNumPoint(5, 20);
		//防御力
		numList[2] = setNumPoint(5, 20);
		//经验值
		numList[3] = setNumPoint(20,20);
	}
	public void doJob(Hero man, int i, int j) {
		// TODO 自动生成的方法存根
		//先把storyBounds设好
		Game.position.getXy()[0]=Game.position.getXy()[1];
		Game.position.getXy()[2]=Game.position.getXy()[3];
				Game.story.setBounds(480,345,790,250);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				Game.story.showStory(this , man);
		
	}
}
