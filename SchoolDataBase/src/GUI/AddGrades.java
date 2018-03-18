package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class AddGrades extends JFrame implements ActionListener{
	
	static Connection dbConn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	//定义组件
	JPanel jp1,jp2,jp3,jp4;//面板
	JLabel jlb1,jlb2,jlb3;//标签
	JTextField jtf1,jtf2,jtf3;//文本
	JButton jb1;//按钮
	
	//构造函数
	public AddGrades()
	{
		//连接数据库
	    String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	    String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=SchoolDB";
	    String userName = "sa";
	    String userPwd = "123456";
	    try
	    {
	        Class.forName(driverName);
	        dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
	        //System.out.println("连接数据库成功");
	     }
	     catch (Exception e)
	     {
	        e.printStackTrace();
	        System.out.print("连接失败");
	     }
	    
		//创建面板
		jp1 = new JPanel();//学号面板
		jp2 = new JPanel();//课程号面板
		jp3 = new JPanel();//成绩面板
		jp4 = new JPanel();//录入按钮面板
		
		//创建标签
		jlb1 = new JLabel("学        号");
		jlb2 = new JLabel("课  程  号");
		jlb3 = new JLabel("成        绩");
		
		//创建按钮
		jb1=new JButton("录入");
		//设置监听
		jb1.addActionListener(this);
		
		//创建文本框
		jtf1=new JTextField(11);
		jtf2=new JTextField(5);
		jtf3=new JTextField(3);
		
		//设置布局管理
		this.setLayout(new GridLayout(4, 1));//网格式布局
		
		//加入各个组件
		jp1.add(jlb1);
		jp1.add(jtf1);
		
		jp2.add(jlb2);
		jp2.add(jtf2);
		
		jp3.add(jlb3);
		jp3.add(jtf3);
		
		jp4.add(jb1);
		
		//加入到JFrame
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
					
		//设置窗体
		this.setTitle("录入成绩");//窗体标签
		this.setSize(300, 200);//窗体大小
		this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出关闭JFrame
		this.setVisible(true);//显示窗体
			
		//锁定窗体
		this.setResizable(false);
	}
	
	//响应函数
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand()=="录入")
		{
			try {
				String sql = "INSERT INTO Grades (Sno,Lno,Grade) VALUES (?, ?, ?)";
				ps=dbConn.prepareStatement(sql);
				ps.setString(1, jtf1.getText());
				ps.setString(2, jtf2.getText());
				ps.setString(3, jtf3.getText());
				if(ps.executeUpdate()==1)
				{
					JOptionPane.showMessageDialog(null,"添加成功！","提示消息",JOptionPane.WARNING_MESSAGE);
					dispose();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	//清空文本框和密码框
	public void clear()
	{
		jtf1.setText("");
		jtf2.setText("");
		jtf3.setText("");
	}

}
