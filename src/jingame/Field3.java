package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Field3 extends GameField{
	
	public Field3(Hero man) throws IOException {
		super(man);
			
	}
	static BufferedImage img;
		public void init()
		{
			floors = "绿树林";
			//设置楼梯位置
			setRight_x(12);
			setRight_y(5);
			try {
				img = ImageIO.read(new File("src/jingame/icon/helmet.png"));
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			/*things[10][2] = new Wall();*/
			things[getRight_x()][getRight_y()] = new RightStair();
			things[1][15] = new Equip("铠甲", "Armour", 1,img
					, 10, 5, 5, 5);
			//设置楼层信息  不能放在构造函数里面  因为有些楼层还没有构造  然后就死循环 null指针了
			//设置上下楼梯位置
			//设置right_F为第一层
			setRight_f(Game.fieldList.get(2));
		}
}
