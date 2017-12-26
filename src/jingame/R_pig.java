package jingame;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class R_pig extends Monster{
	//head 存头像
		public R_pig()
		{	name = "红毛猪";
			try {
				head = ImageIO.read(new File("src/jingame/icon/r_pig.gif"));
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			ex = 13;
			money = 6;
			hp = 20;
			defence = 12;
			attack = 4;
			//get_money = 2;
		}
}
