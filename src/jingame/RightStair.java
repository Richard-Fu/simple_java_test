package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RightStair implements Thing{
	private transient BufferedImage rightStair;
	public RightStair()
	{
		try {
			rightStair = ImageIO.read(new File("src/jingame/icon/stair.png"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void doJob(Hero man,int i, int j) {
		
		GameField f;
		//获得当前楼层的right楼层
		f = Game.position.getF().getRight_f();
		//首先x y 等于right层 的left 的位置 我感觉要+1不然就无限跳转了
		Game.position.getXy()[0]=f.getLeft_x()+1;
		Game.position.getXy()[2]=f.getLeft_y();
		//当前楼层设置成不可视
		//up设置成可视
		Game.position.getF().setVisible(false);
		f.setVisible(true);
		//然后设置当前楼层  为当前的up
		Game.position.setF(f);
		Game.position.getXy()[4]=(Game.fieldList.indexOf(f));
	}
	public BufferedImage getImage() {
		// TODO 自动生成的方法存根
		return rightStair;
	}
}
