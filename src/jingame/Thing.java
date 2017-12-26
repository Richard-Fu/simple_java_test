package jingame;

import java.awt.image.BufferedImage;

public interface Thing extends java.io.Serializable{

	public abstract void doJob(Hero man,int i ,int j);
	public abstract BufferedImage getImage();
}
