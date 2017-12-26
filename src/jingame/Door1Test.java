package jingame;

import java.awt.image.BufferedImage;

public class Door1Test extends Npc{

	//既有剧情框 又有可能战斗场面的发生
	//因为它已经继承了npc，所以要new 一个monster为了产生战斗场面
	//因为要重复造门  所以直接构造算了
	public Door1Test(String name, boolean isStory, boolean isStore
			, boolean isAdventure, boolean isDoor, StoryPoint[] storylist
			, NumPoint[] numList, int[] num2List
			, BufferedImage head, Monster m)
	{
		super(name, isStory, isStore, isAdventure, isDoor
				,storylist, numList, num2List, head);
		this.m = m;
	}
	public void doJob(Hero man, int i, int j) {
		// TODO 自动生成的方法存根
		//先把storyBounds设好
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
}
