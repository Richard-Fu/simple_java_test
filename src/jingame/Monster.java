package jingame;

import java.awt.image.BufferedImage;

public class Monster extends Creature implements Thing{

	
	//其实只要构造的时候传所有属性就行 ，不要像pig那样构造一个新类了
	//构造新类也行   
	public Monster() {}
	public Monster(String name, int hp, int attack, int defence
			, int money, int ex, BufferedImage head)
	{
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.defence = defence;
		this.money = money;
		this.ex = ex;
		this.head = head;
	}
	protected boolean fight(Hero man) {
		//要用个Dialog显示动画吧    打怪用一个JPanel  每次传值进去 
		//再显示 直到某一方hp=0   然后JPanel隐藏  再出来判断到底谁赢  如果赢了再出一个Jpanel弹
		//出该场战斗获得  然后还有玩家升级（这些都是一个JPanel 在中间显示几秒 或者按键隐藏）
		//有了这个思路  那商店什么的就简单了吧  都是显示与数据分离 哈哈哈 实际操作
		//不是在显示区域操作的
		//调用Game的Fight框架
		//先让它显示
		Game.fight.setBounds(480,0,790,170);
		Game.isFight = true;
		
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/
		return Game.fight.fight(this,man);
		/*return true;*/
	}
	//重写doJob
	public void doJob(Hero man,int i, int j) {
		//触发打架事件，如果赢更新当前位置信息，如果输游戏结束
		if(fight(man))
		{
			//当前位置怪物消失
			System.out.println("消失!");
			Game.position.getF().things[i][j] = new Floor();
			Game.position.getF().flag[i][j] = 1;
		}
	}
	//重写getimage
	public BufferedImage getImage() {
		// TODO 自动生成的方法存根
		return head;
	}
}