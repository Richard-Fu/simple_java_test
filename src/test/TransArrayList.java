//2017.11.25
//事实证明能直接传输List集合

package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TransArrayList {

	ArrayList<User> list;
	public TransArrayList()
	{
		list = new ArrayList<User>();
		list.add(new User("abc", 'a'));
		list.add(new User("qwe", 'r'));
	}
	private void write() throws IOException
	{
		FileOutputStream os = new FileOutputStream(".123.txt");
		ObjectOutputStream oo = new ObjectOutputStream(os);
		oo.writeObject(list);
	}
	private void read() throws IOException, ClassNotFoundException
	{
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(".123.txt"));
		ArrayList<User> list2 = (ArrayList<User>)is.readObject();
		System.out.println(list2);
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO 自动生成的方法存根
		TransArrayList ta = new TransArrayList();
		ta.write();
		ta.read();
	}

}
class User implements java.io.Serializable
{
	private String name;
	private char sex;
	public User(String name, char sex)
	{
		this.name = name; 
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", sex=" + sex + "]";
	}
	
}
