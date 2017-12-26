package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Floor implements Thing{

	transient BufferedImage floor;
	public Floor()
	{
		try {
		floor = ImageIO.read(new File("src/jingame/icon/floor.png"));
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	}
	public Floor(BufferedImage floor)
	{
		/*try {
			floor = ImageIO.read(new File("src/jingame/icon/floor.png"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/this.floor = floor;
	}
	//地板就是什么都不做
	public void doJob(Hero man,int i, int j) {
	}
	@Override
	public BufferedImage getImage() {
		// TODO 自动生成的方法存根
		return floor;
	}

	
}
