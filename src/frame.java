import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class frame extends JApplet implements ActionListener{
	public static int count=0;
	int b[][]=new int[6][6];
	int c[][]=new int[100][100];
	public  int list[]=new int[6];
	private FlowLayout fl=new FlowLayout(FlowLayout.CENTER);
	private JButton bt=new JButton("���ս��");
	private JButton btsleep=new JButton("�Զ�ִ��");
	private JButton bnext=new JButton("��һ��");
	private JButton bpre=new JButton("��һ��");
	private JLabel lbx=new JLabel("x����");
	private JLabel lby=new JLabel("y����");			
	private JTextField tfx=new JTextField(2);
	private JTextField tfy=new JTextField(2);
	private JPanel jpl=new JPanel();
	private JPanel jplb=new JPanel();
	private JPanel arr=new JPanel();
	private JTextField[][] a=new JTextField[6][6];
	private JTextArea ta=new JTextArea(10,20);
	
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
		
		ta.setBackground(Color.yellow);
		ta.setText("�˴���ʾ���");
		arr.setLayout(new GridLayout(6,6));
		for(int i=0;i<6;i++)
			for(int j=0;j<6;j++)
		{
			a[i][j]=new JTextField(2);
			arr.add(a[i][j]);
		}
		jpl.setSize(50,20);
		jplb.setSize(50,20);
		bt.addActionListener(this);
		bnext.addActionListener(this);
		bpre.addActionListener(this);
		btsleep.addActionListener(this);
		this.add(jplb);		
		this.add(jpl);
		this.add(arr);
		this.add(ta);
		this.setLayout(fl);
		this.setSize(300,300);
		this.setVisible(true);
		}
	public void actionPerformed(ActionEvent e){

		int x=Integer.parseInt(tfx.getText());
		int y=Integer.parseInt(tfy.getText());		 
		for(int i=0;i<y;i++)
		{
			for(int j=0;j<x;j++)
			{
			 b[i][j]=Integer.parseInt(a[i][j].getText());
			}
		}// ��TextFiled�еõ�����

		if(e.getSource().equals(bt))
		{
	
		int l= find(x,y,b);
		JOptionPane.showMessageDialog( this ,l);
		}
		//*
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
			ta.setText(find(Integer.parseInt(tfx.getText()),count,temp)+"\n"+"��ǰ����ǰ"+ count +"�е�����"+"\n"+"��������Ϊ��"+"\n"+(list[1]+1)+"��"+(list[2]+1)+"��"+"\n"+findm(count)+"��"+list[5]+"��");
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
			int temp[][]=new int[6][6];
			for(int i=0;i<count;i++)
				for(int j=0;j<6;j++)
				{
					temp[i][j]=b[i][j];
				
				}
			ta.setText(find(Integer.parseInt(tfx.getText()),count,temp)+"\n"+"��ǰ����ǰ"+ count +"�е�����"+"\n"+"��������Ϊ��"+"\n"+(list[1]+1)+"��"+(list[2]+1)+"��"+"\n"+findm(count)+"��"+list[5]+"��");
			
		}
		else if(e.getSource().equals(bpre))
		{
			count--;
			if(count<=0) ta.setText("ERROR");
			else{
			int temp[][]=new int[6][6];
			for(int i=0;i<=count;i++)
				for(int j=0;j<6;j++)
				{
					temp[i][j]=b[i][j];
				
				}
			ta.setText(find(Integer.parseInt(tfx.getText()),count,temp)+"\n"+"��ǰ����ǰ"+ count +"�е�����"+"\n"+"��������Ϊ��"+"\n"+(list[1]+1)+"��"+(list[2]+1)+"��"+"\n"+findm(count)+"��"+list[5]+"��");
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
				  		System.out.println(list[3]);
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
}
