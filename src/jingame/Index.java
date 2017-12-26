package jingame;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Index{

	//首页  里面有三个按钮  新的开始 回忆往事  游戏相关
	//首先是设定这个大小
	private final int TABLE_WIDTH = 1290;
	private final int TABLE_HEIGHT = 960;
	private final int B_WIDTH = 620;
	private final int B_HEIGHT =100;
	private JFrame f;
	private JButton newGame;
	private JButton loadGame;
	private JButton aboutGame;
	public Index()
	{
		f = new JFrame("金庸群侠传");
		newGame = new JButton("新的开始");//330 430
		loadGame = new JButton("回忆往事");
		aboutGame = new JButton("游戏相关");
		ImageIcon icon=new ImageIcon("src/jingame/icon/title.png");
        f.setIconImage(icon.getImage());
		newGame.setBounds(330, 430, B_WIDTH, B_HEIGHT);
		loadGame.setBounds(330,530, B_WIDTH, B_HEIGHT);
		aboutGame.setBounds(330,700, B_WIDTH, B_HEIGHT);
		
		//添加各个按钮响应器
		newGame.addActionListener(e ->
		{
			f.setVisible(false);
			try {
				new Game().init();
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		});
		//读档只能读一个   唉  不想写了  烦躁！
		loadGame.addActionListener(e ->{
			f.setVisible(false);
			try {
				Game g = new Game();
				g.init();
				g.loadFile();
				g.refreshFile();
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		});
		f.setLayout(null);
		f.add(newGame);
		f.add(loadGame);
		f.add(aboutGame);
		f.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//让窗口中间显示？
		//f.setLocationRelativeTo(null);
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
		
	}
	public static void main(String[] args) throws IOException
	{
		new Index();
	}
}
