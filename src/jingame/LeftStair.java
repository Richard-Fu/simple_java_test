package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LeftStair implements Thing{
	private transient BufferedImage leftStair;
	public LeftStair()
	{
		try {
			leftStair = ImageIO.read(new File("src/jingame/icon/stair.png"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void doJob(Hero man,int i, int j) {
		
		GameField f;
		//获得当前楼层的left楼层
		f = Game.position.getF().getLeft_f();
		//首先x y 等于left层 的right 的位置 我感觉要+1不然就无限跳转了
		Game.position.getXy()[0]=f.getRight_x()+1;
		Game.position.getXy()[2]=f.getRight_y();
		//当前楼层设置成不可视
		//up设置成可视
		Game.position.getF().setVisible(false);
		f.setVisible(true);
		//然后设置当前楼层  为当前的up
		Game.position.setF(f);
		Game.position.getXy()[4]=(Game.fieldList.indexOf(f));
		System.out.println(Game.fieldList.indexOf(f));
	}
	public BufferedImage getImage() {
		// TODO 自动生成的方法存根
		return leftStair;
	}
}
