package Demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Assignment {
	public static String reducedATM(int amount) throws IOException
	{
		String res="";
		try {
			FileReader fr=new FileReader("amount.txt");
			String s="";
			int ch;
			while((ch= fr.read())!=-1)
			{
				s+=(char)ch;
			}
			//System.out.println(s);
			String str[]=s.split(" ");
			int i1=Integer.parseInt(str[0]);
			int i2=Integer.parseInt(str[1]);
			int i3=Integer.parseInt(str[2]);
			while(amount>100)
			{
				if(amount%2000==0) {
					i1-=1;
					amount=amount-2000;
				}
				else if(amount%500==0) {
					i2-=1;
					amount=amount-500;
				}
				else if(amount%100==0) {
					i3-=1;
					amount=amount-100;
				}
			}
			String str1=String.valueOf(i1);
			String str2=String.valueOf(i2);
			String str3=String.valueOf(i3);
			 res=str1+" "+str2+" "+str3;
			FileWriter fw=new FileWriter("amount.txt");
			for(int i=0;i<res.length();i++)
			{
				fw.write(res.charAt(i));
			}
			fw.close();
			//System.out.println(str1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public static void LoadtoATM(int count2000,int count500,int count100)
	{
		String res="";
		String str1=String.valueOf(count2000);
		String str2=String.valueOf(count500);
		String str3=String.valueOf(count100);
		 res=str1+" "+str2+" "+str3;
		FileWriter fw;
		try {
			fw = new FileWriter("amount.txt");
			for(int i=0;i<res.length();i++)
			{
				fw.write(res.charAt(i));
			}
			System.out.println("amount loaded successfully....");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(str1);
	} 
	public static void CustomerDetails()
	{
		Connection con=DButil.getConnection();
		int amount=0;
		try {
			Statement smt=con.createStatement();
			String str="select * from customer" ;
			ResultSet rs=smt.executeQuery(str);
			System.out.print("accountno"+"   "+"cus_name"+"   "
			  		+ "    "+"pincode"+"   "+"balance");
			System.out.println();
			
			while(rs.next())
			{
			  System.out.print(rs.getInt("account_no")+"        "+rs.getString("cus_name")+"      "
			  		+ "       "+rs.getInt("pincode")+"      "+rs.getInt("balance"));
			  System.out.println();
			  
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public static void customerDetails()
//	{
//	  Scanner sc=new Scanner(System.in);
//	  Connection con=DButil.getConnection();
//	  Statement smt = null;
//	try {
//		smt = con.createStatement();
//	} catch (SQLException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//		String str="create table customer(account_no,name,pincode,balance);";
//		try {
//			smt.execute(str);
//		 int n=4;
//		   while(n>0) {
//			   String name=sc.next();
//			  int account_no=sc.nextInt();
//			  int balance=sc.nextInt();
//			  int pincode=sc.nextInt();
//			String st="insert into customer values("+account_no+""+name+""+pincode+""+balance+");";
//				smt.execute(st);
//				n--;
//		   }
//		}
//			 catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
	public static void checkBalance(int acc_no){
		Connection con=DButil.getConnection();
		int amount=0;
		try {
			Statement smt=con.createStatement();
			String str="select balance from customer where account_no="+acc_no+"";
			ResultSet rs=smt.executeQuery(str);
			while(rs.next())
			{
			  amount=rs.getInt("balance");
			}
			System.out.println("account balance is:"+amount);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void withdraw(int withdrawammount,int acc_no){
		Connection con=DButil.getConnection();
		int amount=0;
		try {
			Statement smt=con.createStatement();
			String str="select balance  from customer where account_no="+acc_no+"";
			ResultSet rs=smt.executeQuery(str);
			while(rs.next())
			 amount=rs.getInt("balance");		 
			if(amount<withdrawammount )
			{
				System.out.println("insufficent amount..");
				return;
			}
			 if(withdrawammount<100 && withdrawammount>10000) {
					System.out.println("enter the valid amount to withdraw..");
					return;
			 }
				else 
				{
				   String s="update customer set balance="+(amount-withdrawammount)+" where account_no="+acc_no+"";
				   smt.execute(s);
					System.out.println("withdrawn successfully");
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void transferMoney(int acc_no1,int acc_no2,int transferamount){
		Connection con=DButil.getConnection();
		int amount=0,amount1=0;
		try {
			Statement smt=con.createStatement();
			String str="select balance from customer where account_no="+acc_no1;
			String str1="select balance from customer where account_no="+acc_no2;
			ResultSet rs=smt.executeQuery(str);
			while(rs.next()) {
			  amount=rs.getInt("balance");
			}
			System.out.println(amount);
			ResultSet rs1=smt.executeQuery(str1);
			while(rs1.next()) {
			  amount1=rs1.getInt("balance");
			}
			System.out.println("hello");
			if(amount<transferamount )
			{
				System.out.println("insufficent amount..!");
				return;
			}
			else if(transferamount<100 || transferamount>10000) {
				System.out.println("enter the valid amount to transfer..!");
				return;
		 }
			else
			{
				 String s="update customer set balance="+(amount-transferamount)+" where account_no="+acc_no1+"";
				 String s1="update customer set balance="+(amount1+transferamount)+" where account_no="+acc_no2+"";
				 smt.execute(s);smt.execute(s1);
					 System.out.println("-------transaction done successfully---------");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void checkATMbalance() throws IOException
	{
		try {
			String s=Assignment.reducedATM(0);
			String str[]=s.split(" ");
			int i1=Integer.parseInt(str[0]);
			int i2=Integer.parseInt(str[1]);
			int i3=Integer.parseInt(str[2]);
			int total=i1*2000+i2*500+i3*100;
			System.out.println("denomination      "+"Number     "+"value    ");
			System.out.println("2000               "+i1+"         "+i1*2000+" ");
			System.out.println("500                "+i2+"        "+i2*500+"  ");
			System.out.println("100                "+ i3+"        "+i3*100+"   ");
			System.out.println("total amount avaible in the ATM ="+total);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
