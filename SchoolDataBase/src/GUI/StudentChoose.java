package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
//import java.util.Vector;

public class StudentChoose extends JFrame implements ActionListener
{
	/**
	 * 学生功能选择
	 */
	private static final long serialVersionUID = 1L;
	
	//学生学号，用于之后查找
	private static String useword = null;
	
	//定义组件
	JPanel jp1;//面板
	JButton jb1,jb2;//按钮
	
	static Connection dbConn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	//显示查询结果
	//private JTable tab;
	
	//构造函数
	public StudentChoose(String key)
	{
		//初始化学生学号
		useword = key;
		
		//连接数据库
		Login win= new Login();
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
		jb1=new JButton("查询基本信息");
		jb2=new JButton("查询所有成绩");
		//设置监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);	
		//加入各个组件
		jp1.add(jb1);
		jp1.add(jb2);
		//加入到JFrame
		this.add(jp1);
		//设置窗体
		this.setTitle("学生"+useword);//窗体标签
		this.setSize(300, 100);//窗体大小
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
			try {
				System.out.println("该学生学号为\t"+useword);
				String sql = "SELECT Sno,Sname,Ssex,Sbirth,Smajor FROM Student WHERE Sno =?";
				ps=dbConn.prepareStatement(sql);
				ps.setString(1, useword);
				rs=ps.executeQuery();
				
				JFrame jf = new JFrame("基本信息");
				//创建一个面板容器
				JPanel jp = new JPanel();
				while(rs.next())
				{
					System.out.println(rs.getString(1));
					JLabel jl = new JLabel(rs.getString(1));
					jp.add(jl);
				}
				jf.add(jp);
				//jf.setSize(width, height)
				//jf.setLocation(x, y)
				jf.setBounds(200, 200, 300, 300);
				jf.setVisible(true);
				
				/*int count = 0;
				while(rs.next()){
				count++;
				}
				rs=ps.executeQuery();
				
				// 将查询获得的记录数据，转换成适合生成JTable的数据形式
				Object[][] info = new Object[count][5];
				count = 0;
				while(rs.next()){
				info[count][0] = rs.getString(1);
				info[count][1] = rs.getString(2);
				info[count][2] = rs.getString(3);
				info[count][3] = rs.getString(4);
				info[count][4] = rs.getString(5);
				count++;
				}
				// 定义表头
				String[] title = {"学号","姓名","性别","出生日期","专业"};
				// 创建JTable
				this.tab = new JTable(info,title);
				this.tab.getTableHeader();
				
		        this.setTitle("基本信息");  
		        this.setSize(260, 150);
		        this.setLocation(300, 200);  
		        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		        this.setResizable(false);  
		        this.setVisible(true);*/
			    
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getActionCommand()=="查询所有成绩")
		{
			
		}
	}

}
