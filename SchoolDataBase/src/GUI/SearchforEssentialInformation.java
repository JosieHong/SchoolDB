package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SearchforEssentialInformation extends JFrame implements ActionListener{

	/**
	 * 查询某位学生的基本信息
	 */
	private static final long serialVersionUID = 1L;
	//定义组件
	JPanel jp1,jp2,jp3,jp4;//面板
	JButton jb1;//按钮
	JRadioButton jr1,jr2,jr3;ButtonGroup jb;//单选按钮
	JTextField jtf1,jtf2,jtf3;//文本
	
	static Connection dbConn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	private JTable tab;
	
	//构造函数
	public SearchforEssentialInformation(){
		
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
		jp3 = new JPanel();//专业面板
		jp4 = new JPanel();//查询基本信息面板
		
		//创建按钮
		jb1=new JButton("查询基本信息");
		//设置监听
		jb1.addActionListener(this);
		
		//创建文本框
		jtf1=new JTextField(10);
		jtf2=new JTextField(10);
		jtf3=new JTextField(10);
		
		//设置单选按钮
		jr1 = new JRadioButton("学号");
		jr2 = new JRadioButton("姓名");
		jr3 = new JRadioButton("专业");
		//通过按钮组设置当前只能选中一个
		jb = new ButtonGroup();
		jb.add(jr1);
		jb.add(jr2);
		jb.add(jr3);
		
		//设置布局管理
		this.setLayout(new GridLayout(4, 1));//网格式布局
		
		//加入各个组件
		jp1.add(jr1);
		jp1.add(jtf1);
		
		jp2.add(jr2);
		jp2.add(jtf2);
		
		jp3.add(jr3);
		jp3.add(jtf3);
		
		jp4.add(jb1);
		
		//加入到JFrame
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
					
		//设置窗体
		this.setTitle("查询基本信息");//窗体标签
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
		if(e.getActionCommand()=="查询基本信息")
		{
			if(jr1.isSelected())//通过学号查询基本信息
			{
				try {
					String sql = "SELECT Sno,Sname,Ssex,Sbirth,Smajor " + 
							"FROM Student " + 
							"WHERE Sno =?";
						ps=dbConn.prepareStatement(sql);
						ps.setString(1, jtf1.getText());
						rs=ps.executeQuery();
						JFrame jf = new JFrame("基本信息");
						//创建一个面板容器
						JPanel jp = new JPanel();
						while(rs.next())
						{
							JLabel jl1 = new JLabel(rs.getString(1));
							JLabel jl2 = new JLabel(rs.getString(2));
							JLabel jl3 = new JLabel(rs.getString(3));
							JLabel jl4 = new JLabel(rs.getString(4));
							JLabel jl5 = new JLabel(rs.getString(5));
							jp.add(jl1);
							jp.add(jl2);
							jp.add(jl3);
							jp.add(jl4);
							jp.add(jl5);
						}
						jf.add(jp);
						jf.setBounds(200, 200, 300, 300);
						jf.setSize(400, 100);
						jf.setVisible(true);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			else if(jr2.isSelected())//通过姓名查询基本信息
			{
				try {
					String sql = "SELECT Sno,Sname,Ssex,Sbirth,Smajor " + 
							"FROM Student " + 
							"WHERE Sname =?";
						ps=dbConn.prepareStatement(sql);
						ps.setString(1, jtf2.getText());
						rs=ps.executeQuery();
						
						JFrame jf = new JFrame("基本信息");
						//创建一个面板容器
						JPanel jp = new JPanel();
						while(rs.next())
						{
							JLabel jl1 = new JLabel(rs.getString(1));
							JLabel jl2 = new JLabel(rs.getString(2));
							JLabel jl3 = new JLabel(rs.getString(3));
							JLabel jl4 = new JLabel(rs.getString(4));
							JLabel jl5 = new JLabel(rs.getString(5));
							jp.add(jl1);
							jp.add(jl2);
							jp.add(jl3);
							jp.add(jl4);
							jp.add(jl5);
						}
						jf.add(jp);
						jf.setBounds(200, 200, 300, 300);
						jf.setSize(400, 100);
						jf.setVisible(true);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			else//通过专业查询基本信息
			{
				try {
					String sql = "SELECT Sno,Sname,Ssex,Sbirth,Mname " + 
							"FROM Student_Major " + 
							"WHERE Mname =?";
						ps=dbConn.prepareStatement(sql);
						ps.setString(1, jtf3.getText());
						rs=ps.executeQuery();
						
						JFrame jf = new JFrame("基本信息");
						//创建一个面板容器
						JPanel jp = new JPanel();
						while(rs.next())
						{
							JLabel jl1 = new JLabel(rs.getString(1));
							JLabel jl2 = new JLabel(rs.getString(2));
							JLabel jl3 = new JLabel(rs.getString(3));
							JLabel jl4 = new JLabel(rs.getString(4));
							JLabel jl5 = new JLabel(rs.getString(5));
							jp.add(jl1);
							jp.add(jl2);
							jp.add(jl3);
							jp.add(jl4);
							jp.add(jl5);
						}
						jf.add(jp);
						jf.setBounds(200, 200, 300, 300);
						jf.setSize(400, 500);
						jf.setVisible(true);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}
			
	}
}
