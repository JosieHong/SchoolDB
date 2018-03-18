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

public class AddStudents extends JFrame implements ActionListener{
	
	/**
	 * 录入一位学生的信息
	 */
	private static final long serialVersionUID = 1L;
	
	static Connection dbConn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	//定义组件
	JPanel jp1,jp2,jp3,jp4,jp5,jp6;//面板
	JLabel jlb1,jlb2,jlb3,jlb4,jlb5;//标签
	JTextField jtf1,jtf2,jtf3;//文本
	JButton jb1;//按钮
	JRadioButton jr1,jr2;
	ButtonGroup jb;//单选按钮
	
	JComboBox<String> jcb1,jcb2;//下拉框
	
	//构造函数
	public AddStudents()
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
		jp2 = new JPanel();//姓名年面板
		jp3 = new JPanel();//性别面板
		jp4 = new JPanel();//出生年月面板
		jp5 = new JPanel();//专业面板
		jp6 = new JPanel();//登陆按钮面板
		
		//创建标签
		jlb1 = new JLabel("学        号");
		jlb2 = new JLabel("姓        名");
		jlb3 = new JLabel("性        别");
		jlb4 = new JLabel("出生年月");
		jlb5 = new JLabel("专业编号");
		
		//创建按钮
		jb1=new JButton("录入");
		//设置监听
		jb1.addActionListener(this);
		
		//创建文本框
		jtf1=new JTextField(15);
		jtf2=new JTextField(5);
		jtf3=new JTextField(15);
		
		//设置单选按钮
		jr1 = new JRadioButton("男");
		jr2 = new JRadioButton("女");
		//通过按钮组设置当前只能选中一个
		jb = new ButtonGroup();
		jb.add(jr2);
		jb.add(jr1);
		
		//创建下拉框
		String[] arr1=new String[70];//数组时个对象,对象在使用前要初始化  
        for(int i=0;i<70;i++){
            arr1[i] = i+1950+"";
        }         
        jcb1 = new JComboBox<String>(arr1);
        String [] arr2 = new String[12];  
        for(int i=0;i<12;i++){  
            arr2[i]=i+1+"";
        }  
        jcb2 = new JComboBox<String>(arr2);
		
		//设置布局管理
		this.setLayout(new GridLayout(6, 1));//网格式布局
		
		//加入各个组件
		jp1.add(jlb1);
		jp1.add(jtf1);
		
		jp2.add(jlb2);
		jp2.add(jtf2);
		
		jp3.add(jlb3);
		jp3.add(jr1);
		jp3.add(jr2);
		
		jp4.add(jlb4);
		jp4.add(jcb1);
		jp4.add(jcb2);
		
		jp5.add(jlb5);
		jp5.add(jtf3);
		
		jp6.add(jb1);
		
		//加入到JFrame
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
					
		//设置窗体
		this.setTitle("录入学生");//窗体标签
		this.setSize(300, 280);//窗体大小
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
				String sql = "INSERT INTO Student (Sno,Sname,Ssex,Sbirth,Smajor,Skey) VALUES (?, ?, ?, ?, ?, ?)";
				ps=dbConn.prepareStatement(sql);
				ps.setString(1, jtf1.getText());
				ps.setString(2, jtf2.getText());
				if(jr1.isSelected())
					ps.setString(3, "男");
				else
					ps.setString(3, "女");
				ps.setString(4, jcb1.getSelectedItem().toString()+jcb2.getSelectedItem().toString());
				ps.setString(5, jtf3.getText());
				ps.setString(6, jtf1.getText());
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
}
