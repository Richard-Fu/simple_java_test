package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Npc1Test extends Npc{

	//测试剧情Npc
	public Npc1Test()
	{
		//为剧情npc
		isStory = true;
		//设置名字
		name = "神秘人";
		//设置头像
		try {
			head = ImageIO.read(new File("src/jingame/icon/qwe.gif"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//一共五句话
		storylist = new StoryPoint[15];
		storylist[0] = setStoryPoint(false, "小子看你骨骼惊奇，当我徒弟吗?");
		storylist[1] = setStoryPoint(true, "(..怎么又碰到一个骗子?)");
		storylist[2] = setStoryPoint(true, "当你徒弟干嘛?");
		storylist[3] = setStoryPoint(true, "(不对，我也骗骗他算了)");
		storylist[4] = setStoryPoint(false, "你不想当我徒弟吗？");
		storylist[5] = setStoryPoint(true, "好啊，我当你徒弟");
		storylist[6] = setStoryPoint(false, "那我在荷花池等你,不过你要小心路上的东西");
		storylist[7] = setStoryPoint(false, "野猪林的野猪皮糙肉厚");
		storylist[8] = setStoryPoint(false, "皇子坡的盗贼贪得无厌");
		storylist[9] = setStoryPoint(false, "少林山下的臭和尚力大且皮厚");
		storylist[10] = setStoryPoint(false, "不过荷花池的美女还不错，有钱！");
		storylist[11] = setStoryPoint(false, "哦，我还听说西边好像有什么宝贝。");
		storylist[12] = setStoryPoint(true, "宝贝？（屠龙宝刀到了就送?）");
		storylist[12] = setStoryPoint(true, "还有荷花池怎么去啊？我不知道走!");
		storylist[13] = setStoryPoint(false, "看你的造化了(这都是命啊！嘿嘿)");
		storylist[14] = setStoryPoint(true, "诶，怎么又走了？这个世界的人都是这样吗");
		//送他一个显示
	}
	public Npc1Test(boolean isStory, String name
			, BufferedImage head, StoryPoint[] storylist)
	{
		this.isStory = isStory;
		this.name = name;
		this.head = head;
		this.storylist = storylist;
	}
	
}
