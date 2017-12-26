package test;

import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TextFocus extends JFrame{

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new TextFocus();
	}
	    JTextField text1,text2;
	    JButton button1,button2;
	    public TextFocus(){
	        init();
	        setVisible(true);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	    void init(){
	        text1=new JTextField(8);
	        add(text1);
	        setLayout(new FlowLayout());
	        FocusPolice focusPolice1=new FocusPolice();
	        text1.addFocusListener(focusPolice1);
	        add(new JButton("click"));
	        pack();
	    }
	class FocusPolice implements FocusListener{
	    public void focusGained(FocusEvent e){
	        System.out.print("11");
	    }
	    public void focusLost(FocusEvent e){
	        System.out.print("22");
	    }
	}
}
