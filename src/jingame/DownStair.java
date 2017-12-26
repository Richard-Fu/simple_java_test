package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DownStair implements Thing{
	private BufferedImage downStair;
	public DownStair()
	{
		try {
			downStair = ImageIO.read(new File("src/jingame/icon/stair1.png"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void doJob(Hero man,int i, int j) {
		GameField f;
		//获得当前楼层的down楼层
		f = Game.position.getF().getDown_f();
		//首先x y 等于下层 的up 的位置 我感觉要+1不然就无限跳转了
		Game.position.getXy()[0]=f.getUp_x()+1;
		Game.position.getXy()[2]=f.getUp_y();
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
		return downStair;
	}
}
