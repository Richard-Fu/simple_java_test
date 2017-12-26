package test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;



public class Text {

	public void init()
	{
		String[] heads = new String[]
				{
						"1", "2", "3", "4", "5", "6"
				};
		JComboBox headBox = new JComboBox(heads);
		//给JComboBox加装饰器   继承JPanel,接口ListCellRenderer
		headBox.setRenderer(new ImageCellRenderer());
		JFrame f = new JFrame();
		f.add(headBox);
		f.pack();
		f.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new Text().init();
	}
	//ImageCellRenderer   JComboBox的装饰器  JList也是这样装饰
	class ImageCellRenderer extends JPanel implements ListCellRenderer
	{
		//JComboBox  GUI成员  需要用getListCellRendererComponent里面的参数  得到每一项的属性
		//再用这些属性  把它们绘制出来
		private ImageIcon icon;
		private String name;
		private Color background;
		private Color foreground;
		//下面是ListCellRenderer接口里面的方法
		//返回每一个列表项  （已经转换成对应的Component了）
		public Component getListCellRendererComponent(JList list
				, Object value, int index, boolean isSelected, boolean cellHasFocus )
		{
			//通过列表项属性  给上面声明的变量赋值  为了下面把它们画出来
			icon = new ImageIcon("icon/" +value + ".gif");
			//根据实践  目前不需要画name  因为是选择头像
			name = value.toString();
			//这里的被不被选好像是JList  JComboBox里面的接口实现的  以后一定要研究他们的源代码
			//感觉设计的好屌   昨天也看了一个课 说编程用的是工程思维 不是科学思维  工程思维是资源受限 只要出一个合适解
			//而科学思维是最优解      也就是算法数据结构里面最厉害的算法与结构  
			
			//这里面的list方法返回的Color其实可以自己设置
			background = isSelected ? list.getSelectionBackground()
					: list.getBackground();
			foreground = isSelected ? list.getSelectionForeground()
					: list.getForeground();
			//返回该JPanel对象作为列表绘制器  也就是自己
			return this;
		}//close getComponent
		
		//重写JPanel里面的paintComponent()方法,改变JPanel的外观   好像可用作设置背景  
		//配合JLayer有层级关系     JPanel做背景   试试
		public void paintComponent(Graphics g)
		{
			//获取图片大小  便于之后布局    null 指的是  ImageObserver observer
			int imageWidth = icon.getImage().getWidth(null);
			int imageHeight = icon.getImage().getHeight(null);
			//画背景
			//先设画笔颜色    体现为什么这个 类 要设几个成员变量了
			g.setColor(background);
			//这里的getWidth() , getHeight()是返回该Component的属性
			//之后的getDimention里面设置了该Component的大小
			g.fillRect(0, 0, getWidth(), getHeight());
			//画前景
			g.setColor(foreground);
			//绘制图标        null 指的是  ImageObserver observer
			g.drawImage(icon.getImage(), getWidth()/2 - imageWidth/2, 10, null);
			g.drawString(name, getWidth()/2-name.length()*10, imageHeight+30);
		}
		public Dimension getPreferredSize()
		{
			return new Dimension(60,80);
		}
	}
}
