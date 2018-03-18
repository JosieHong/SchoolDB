package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TeacherChoose extends JFrame implements ActionListener
{
	/**
	 * 功能选择
	 */
	private static final long serialVersionUID = 1L;
	
	//定义组件
	JPanel jp1;//面板
	JButton jb1,jb2,jb3,jb4,jb5,jb6;//按钮
		
	static Connection dbConn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	//构造函数
	public TeacherChoose()
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
		jp1 = new JPanel();//用户名面板
		//创建按钮
		jb1=new JButton("添加学生信息");
		jb2=new JButton("录入成绩");
		jb3=new JButton("查询基本信息");
		jb4=new JButton("查询成绩");
		jb5=new JButton("查询授课情况");
		jb6=new JButton("查询快被开除学生");
		//设置监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
		//加入各个组件
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		jp1.add(jb4);
		jp1.add(jb5);
		jp1.add(jb6);
		//加入到JFrame
		this.add(jp1);
		//设置窗体
		this.setTitle("教师");//窗体标签
		this.setSize(200, 240);//窗体大小
		this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出关闭JFrame
		this.setVisible(true);//显示窗体
						
		//锁定窗体
		this.setResizable(false);
	}
	
	//响应函数
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand()=="添加学生信息")
		{
			AddStudents win = new AddStudents();
		}
		else if(e.getActionCommand()=="录入成绩")
		{
			AddGrades win = new AddGrades();
		}
		else if(e.getActionCommand()=="查询基本信息")
		{
			SearchforEssentialInformation win = new SearchforEssentialInformation();
		}
		else if(e.getActionCommand()=="查询成绩")
		{
			SearchforGrades win = new SearchforGrades();
		}
		else if(e.getActionCommand()=="查询授课情况")
		{
			SearchforGivingLessons win = new SearchforGivingLessons();
		}
		else if(e.getActionCommand()=="查询快被开除学生")
		{
			try {
				String sql = "SELECT DISTINCT Student.Sno, Sname, RFailSUM.SumCredit, OFailSUM.SumCredit " + 
						"FROM RFailSUM, OFailSUM, Student " + 
						"WHERE (Student.Sno = RFailSUM.Sno AND " + 
						"				RFailSUM.SumCredit >=12 ) " + 
						"				OR " + 
						"				(Student.Sno = OFailSUM.Sno AND " + 
						"				OFailSUM.SumCredit >=17)";
				ps=dbConn.prepareStatement(sql);
				rs=ps.executeQuery();
				JFrame jf = new JFrame("快被开除学生");
				//创建一个面板容器
				JPanel jp = new JPanel();
				while(rs.next())
				{
					JLabel jl1 = new JLabel(rs.getString(1));
					JLabel jl2 = new JLabel(rs.getString(2));
					JLabel jl3 = new JLabel(rs.getString(3));
					JLabel jl4 = new JLabel(rs.getString(4));
					jp.add(jl1);
					jp.add(jl2);
					jp.add(jl3);
					jp.add(jl4);
				}
				jf.add(jp);
				jf.setBounds(200, 200, 300, 300);
				jf.setSize(220, 300);
				jf.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
