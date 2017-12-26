package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pig extends Monster{
	//head 存头像
	public Pig()
	{	name = "黑毛猪";
		try {
			head = ImageIO.read(new File("src/jingame/icon/pig.gif"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		ex = 12;
		money = 5;
		hp = 15;
		defence = 10;
		attack = 4;
		//get_money = 2;
	}
	

}
