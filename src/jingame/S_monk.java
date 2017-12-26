package jingame;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class S_monk extends Monster{
	//head 存头像
		public S_monk()
		{	name = "猥琐和尚";
			try {
				head = ImageIO.read(new File("src/jingame/icon/9.gif"));
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			ex = 16;
			money = 5;
			hp = 50;
			defence = 5;
			attack = 6;
			get_money = 1;
		}
}
