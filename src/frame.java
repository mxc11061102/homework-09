import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class frame extends JApplet implements ActionListener{
	public static int count=0;
	int b[][]=new int[6][6];
	int c[][]=new int[100][100];
	public  int list[]=new int[6];
	private FlowLayout fl=new FlowLayout(FlowLayout.CENTER);
	private JButton bt=new JButton("最终结果");
	private JButton btsleep=new JButton("自动执行");
	private JButton bnext=new JButton("下一步");
	private JButton bpre=new JButton("上一步");
	private JButton bopen=new JButton("打开");
	private JButton brandom=new JButton("随机生成");
	private JLabel lbx=new JLabel("x长度");
	private JLabel lby=new JLabel("y长度");			
	private JTextField tfx=new JTextField(2);
	private JTextField tfy=new JTextField(2);
	private JPanel jpl=new JPanel();
	private JPanel jplb=new JPanel();
	private JPanel arr=new JPanel();
	private JTextField[][] a=new JTextField[6][6];
	private JTextArea ta=new JTextArea(30,20);
	
	public frame()
	{
		jplb.add(bt);
		jplb.add(btsleep);
		jplb.add(bnext);
		jplb.add(bpre);
		jpl.add(lbx);
		jpl.add(tfx);
		jpl.add(lby);
		jpl.add(tfy);
		jpl.add(bopen);
		jpl.add(brandom);
		ta.setBackground(Color.yellow);
		ta.setText("此处显示信息");
		arr.setLayout(new GridLayout(6,6));
		for(int i=0;i<6;i++)
			for(int j=0;j<6;j++)
		{
			a[i][j]=new JTextField(2);
			arr.add(a[i][j]);
		}
		jpl.setSize(20,20);
		jplb.setSize(50,20);
		bt.addActionListener(this);
		bnext.addActionListener(this);
		bpre.addActionListener(this);
		btsleep.addActionListener(this);
		bopen.addActionListener(this);
		brandom.addActionListener(this);
		this.add(jplb);		
		this.add(jpl);
		this.add(arr);
		this.add(ta);
		this.setLayout(fl);
		this.setSize(300,300);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(bopen))
		{
				open();
		}
		if(e.getSource().equals(brandom))
		{
				ran();
		}
		int x=Integer.parseInt(tfx.getText());
		int y=Integer.parseInt(tfy.getText());		 
		for(int i=0;i<y;i++)
		{
			for(int j=0;j<x;j++)
			{
			 b[i][j]=Integer.parseInt(a[i][j].getText());
			}
		}// 从TextFiled中得到数组

		if(e.getSource().equals(bt))
		{
	
		int l= find(x,y,b);
		JOptionPane.showMessageDialog( this ,"最终结果为"+l);
		}

		else if(e.getSource().equals(btsleep))
		{
	
			
			int temp[][]=new int[6][6];
			for(int i=0;i<6;i++)
				for(int j=0;j<6;j++)
				{
					temp[i][j]=b[i][j];
				
				}

			for(int i=0;i<y;i++)
			{
			count++;
			ta.setText("当前步骤答案为"+"	"+find(Integer.parseInt(tfx.getText()),count,temp)+"\n"+"当前计算前"+ count +"行的数据"+"\n"+"矩阵区域为第"+"\n"+(list[1]+1)+"到"+(list[2]+1)+"列（如图所示）"+"\n"+findm(count)+"到"+list[5]+"行（如图所示）");
			for(int j=0;j<6;j++)
				for(int k=0;k<6;k++)
				{
					a[k][j].setBackground(Color.white);
					a[k][j].paintImmediately(getBounds());
				}
			for(int j=list[1];j<list[2]+1;j++)
				for(int k=list[4]-1;k<list[5];k++)
				{
					a[k][j].setBackground(Color.yellow);
					a[k][j].paintImmediately(getBounds());
				}
			ta.append("\n"+"临时数组如下"+"\n");
			for(int j=0;j<((count+1)*count/2);j++)
				{
				for(int t=0;t<Integer.parseInt(tfx.getText());t++)		
					ta.append(c[j][t]+"    ");
				ta.append("\n");
				}
			ta.paintImmediately(getBounds());
				try
				{Thread.sleep(2000);
				}catch(InterruptedException error)
				{		          
		        }						
			}
		}
		else if(e.getSource().equals(bnext))
		{
			count++;
			if(count<0) {
				ta.setText("倒退的太多了！ERROR！！！！！！！！！");
				for(int j=0;j<6;j++)
					for(int i=0;i<6;i++)
						a[i][j].setBackground(Color.white);
			}
			else if	(count==0) {
				ta.setText("已回到初始状态");
				for(int j=0;j<6;j++)
					for(int i=0;i<6;i++)
						a[i][j].setBackground(Color.white);
			}
			else
			{
			int temp[][]=new int[6][6];
			for(int i=0;i<count;i++)
				for(int j=0;j<6;j++)
				{
					temp[i][j]=b[i][j];
				
				}
			ta.setText("当前步骤答案为"+"	"+find(Integer.parseInt(tfx.getText()),count,temp)+"\n"+"当前计算前"+ count +"行的数据"+"\n"+"矩阵区域为第"+"\n"+(list[1]+1)+"到"+(list[2]+1)+"列（如图所示）"+"\n"+findm(count)+"到"+list[5]+"行（如图所示）");
			for(int j=0;j<6;j++)
				for(int i=0;i<6;i++)
					a[i][j].setBackground(Color.white);
			for(int j=list[1];j<list[2]+1;j++)
				for(int i=list[4]-1;i<list[5];i++)
					a[i][j].setBackground(Color.yellow);
			ta.append("\n"+"临时数组如下"+"\n");
			for(int j=0;j<((count+1)*count/2);j++)
				{
				for(int i=0;i<Integer.parseInt(tfx.getText());i++)		
					ta.append(c[j][i]+"    ");
				ta.append("\n");
				}
			}
			
		}
		else if(e.getSource().equals(bpre))
		{
			count--;
			if(count<0) {
				ta.setText("倒退的太多了！ERROR！！！！！！！！！");
				for(int j=0;j<6;j++)
					for(int i=0;i<6;i++)
						a[i][j].setBackground(Color.white);
			}
			else if	(count==0) {
				ta.setText("已回到初始状态");
				for(int j=0;j<6;j++)
					for(int i=0;i<6;i++)
						a[i][j].setBackground(Color.white);
			}
			
			else{
			int temp[][]=new int[6][6];
			for(int i=0;i<=count;i++)
				for(int j=0;j<6;j++)
				{
					temp[i][j]=b[i][j];
				
				}
			ta.setText("当前步骤答案为"+"	"+find(Integer.parseInt(tfx.getText()),count,temp)+"\n"+"当前计算前"+ count +"行的数据"+"\n"+"矩阵区域为第"+"\n"+(list[1]+1)+"到"+(list[2]+1)+"列（如图所示）"+"\n"+findm(count)+"到"+list[5]+"行（如图所示）");
			for(int j=0;j<6;j++)
				for(int i=0;i<6;i++)
					a[i][j].setBackground(Color.white);
			for(int j=list[1];j<list[2]+1;j++)
				for(int i=list[4]-1;i<list[5];i++)
					a[i][j].setBackground(Color.yellow);
			ta.append("\n"+"临时数组如下"+"\n");
			for(int j=0;j<((count+1)*count/2);j++)
				{
				for(int i=0;i<Integer.parseInt(tfx.getText());i++)		
					ta.append(c[j][i]+"    ");
				ta.append("\n");
				}
			}
		}
			
	}
	public void init()
	{
		new frame();
	}
	public int find(int x,int y,int b[][]){
		int sum,sumb,n,flagfront=0,flaglast=0;
		int max=-9999;
		
		for(int i=0;i<x;i++)
		   {
		  n=0;
		  for(int j=0;j<y;j++)
		     {
		       sum=0;
		         for(int t=j;t>=0;t--)
		           {           
		           sum=sum+b[t][i]; 
		           c[n][i]=sum;
		           n++;          
		           }

		       }
		   }
		
		for(int m=0;m<(y*(y+1)/2);m++)
		  for(int i=0;i<x;i++)
		   {
			  for(int j=x-1;j>=i;j--)
			  {
				  sumb=0;
				  for(int k=i;k<=j;k++)
					  sumb=sumb+c[m][k];
				  	if(max<sumb) 
				  	{
				  		max=sumb;
				  		flagfront=i;
				  		flaglast=j;
				  		list[3]=m;
				  	}
			  }
		   }
		  list[0]=max;
		  list[1]=flagfront;
		  list[2]=flaglast;
	

			return max;
			
	}
	public int findm(int y)
	{
		int m=list[3];

		int rowsub=0;
		for (int i=0;i<y;i++)
			if (m>=(i*(i+1)/2) && m<((i+2)*(i+1)/2))
			{
				rowsub=m-i*(i+1)/2;
				list[4]=i-rowsub+1;
				list[5]=i+1;
			}
	return list[4];		
	}
	public void open()
	{
		try{
		int[][] arr = new int[10][10];
		JFileChooser chooser = new JFileChooser(".");
		chooser.showOpenDialog(null); 
		File file = chooser.getSelectedFile();
		BufferedReader in = new BufferedReader(new FileReader(file));  //
		  String line;  //一行数据
		  int row=0;
		  //逐行读取，并将每个数组放入到数组中
		  while((line = in.readLine()) != null){
		   String[] temp = line.split(",");  
		   for(int j=0;j<temp.length;j++){
		    arr[row][j] = Integer.parseInt(temp[j]);
		   }
		   row++;
		  }
		  in.close();
		  tfx.setText(""+arr[0][1]);
		  tfy.setText(""+arr[0][0]);
		  for(int i=0;i<Integer.parseInt(tfy.getText());i++)
			  for(int j=0;j<Integer.parseInt(tfx.getText());j++)
			  {
				  a[i][j].setText(""+arr[i+1][j]);
			  }
	}catch(Exception err){}
	}
	public void ran()
	{
		Random rdm=new Random();		
		  for(int i=0;i<Integer.parseInt(tfy.getText());i++)
			  for(int j=0;j<Integer.parseInt(tfx.getText());j++)
				  a[i][j].setText(""+rdm.nextInt()%20);		
	}
}
