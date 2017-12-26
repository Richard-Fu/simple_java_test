package test;
//还是要用绘制器    书上说的鬼话！

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

public class JComboBoxTest {

	public void init()
	{
		String[] heads = new String[]
				{
						"1", "2", "3", "4", "5", "6"
				};
		JComboBox headBox = new JComboBox(heads);
		//使用ListCellRenderer来装饰此列表
		headBox.setRenderer(new ImageCellRenderer());
		JFrame f = new JFrame();
		f.add(headBox);
		f.pack();
		f.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new JComboBoxTest().init();
	}
	class ImageCellRenderer extends JPanel
	implements ListCellRenderer
	{
		private ImageIcon icon;
		private String name;
		//定义绘制单元格时的背景色
		private Color background;
		//前景色
		private Color foreground;
		/*
		 * list - 正在绘制的 JList。
		value - 由 list.getModel().getElementAt(index) 返回的值。  Model是java实行数据 与表现分离
		Model是存数据的
		index - 单元格索引。
		isSelected - 如果选择了指定的单元格，则为 true。
		cellHasFocus - 如果指定的单元格拥有焦点，则为 true。
		*/
		public Component getListCellRendererComponent(JList list, Object value
				, int index, boolean isSelected, boolean cellHasFocus)
		{
			icon = new ImageIcon("icon/" + value + ".gif");
			name = value.toString();
			background = isSelected ? list.getSelectionBackground() 
					:list.getBackground();
			foreground = isSelected ? list.getSelectionForeground()
					:list.getForeground();
			//返回该JPanel对象作为列表项绘制器
			return this;
		}
		//重写paintComponent()方法, 改变JPanel的外观
		public void paintComponent(Graphics g)
		{
			int imageWidth = icon.getImage().getWidth(null);
			int imageHeight = icon.getImage().getHeight(null);
			g.setColor(background);
			//没有为这个Component设宽高  怎么出来的？？//后面设置了
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(foreground);
			//绘制图标
			g.drawImage(icon.getImage(), getWidth()/2-imageWidth/2, 10, null );
			g.drawString(name, getWidth()/2-name.length()*10, imageHeight+30);
		}
		//
		public Dimension getPreferredSize()
		{
			return new Dimension(60,80);
		}
		
	}

}
