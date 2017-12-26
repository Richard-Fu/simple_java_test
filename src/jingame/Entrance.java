package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entrance extends GameField{

	BufferedImage red;
	BufferedImage ice;
	BufferedImage head;
	//情节对话
	StoryPoint[] story;
	//剧情类
	Npc1Test npc;
	public Entrance(Hero man) throws IOException {
		
		super(man);
	}
	
	//设置楼层信息  不能放在构造函数里面  因为有些楼层还没有构造  然后就死循环 null指针了
	public void init()
	{
		story = new StoryPoint[13];
		story[0] = Npc.setStoryPoint(true, "啊啊啊！我这是在哪啊？怎么这么恐怖!");
		story[1] = Npc.setStoryPoint(false, "恭喜您，成功穿越到金庸世界");
		story[2] = Npc.setStoryPoint(false, "在您之前已有102人被野猪咬死");
		story[3] = Npc.setStoryPoint(false, "53人饿死，43人中毒身亡。");
		story[4] = Npc.setStoryPoint(false, "不过有1人最后成为了武林盟主!");
		story[5] = Npc.setStoryPoint(true, "不就是金庸群侠传嘛，我肯定能当武林盟主");
		story[6] = Npc.setStoryPoint(true, "看我随随便便统治这个江湖！");
		story[7] = Npc.setStoryPoint(false, "(嘀咕了一句):又来个傻子，ε=(´ο｀*)))唉");
		story[8] = Npc.setStoryPoint(true, "说吧，我接下来是不是要学降龙十八掌");
		story[9] = Npc.setStoryPoint(true, "还是乾坤大挪移？还是跟郭靖结拜？");
		story[10] = Npc.setStoryPoint(false, "(看来真是个傻子唉,我还是走吧)");
		story[11] = Npc.setStoryPoint(true, "诶诶诶，你怎么走了？");
		story[12] = Npc.setStoryPoint(true, "咦，前面怎么有扇门，去瞧瞧.");
		
		try {
			red = ImageIO.read(new File("src/jingame/icon/red.png"));
			ice = ImageIO.read(new File("src/jingame/icon/ice.png"));
			head = ImageIO.read(new File("src/jingame/icon/snow.gif"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		npc = new Npc1Test(true, "无名氏", head, story);
		//地图名字
				floors = "梦境";
				//设置上下楼梯位置
				setUp_x(8);
				setUp_y(0);
				things[up_x][up_y] = new UpStair();
				things[up_x-1][up_y] = new UpStair();
				things[up_x][up_y+1] = npc;
				/*setLeft_x(0);
				setLeft_y(5);
				int[] test = {0,1,0,20};*/
				//background = ImageIO.read(new File("src/jingame/icon/test.png"));
				//地图上除开地板 其他的thing
				for(int j=0;j<GameField.HEIGHT;j++)
				{
					for(int i=0;i<7;i++)
						things[i][j] = new Wall(red,0);
					for(int i=9;i<GameField.WIDTH;i++)
						things[i][j] = new Wall(ice,0);
				}
				
		//设置up_f为 梦境通道
				setUp_f(Game.fieldList.get(1));
		
	}
	//重写rule
	//不用重写rule吧
	//重写paint
}
