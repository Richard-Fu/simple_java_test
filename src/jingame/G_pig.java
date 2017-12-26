package jingame;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class G_pig extends Monster{
	//head 存头像
			public G_pig()
			{	name = "绿毛猪";
				try {
					head = ImageIO.read(new File("src/jingame/icon/g_pig.gif"));
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				ex = 15;
				money = 6;
				hp = 25;
				defence = 12;
				attack = 4;
				//get_money = 2;
			}
}
