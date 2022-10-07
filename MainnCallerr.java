package Demo;

import java.io.IOException;
import java.util.Scanner;

public class MainnCallerr {
	public static void main(String[] agrs)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("1.LoadtoATM\n2.customerDetails\n3.CheckBalance\n4.WithDrawMoney\n5.transferMoney\n6.checkATMBalance");
		int choice=sc.nextInt();
		switch(choice)
		{
		  case 1:
			  System.out.println("enter the 2000count");
			  int count2000=sc.nextInt();
			  System.out.println("enter the 500count");
			  int count500=sc.nextInt();
			  System.out.println("enter the 100count");
			  int count100=sc.nextInt();
			  Assignment.LoadtoATM(count2000, count500, count100);
			  break;
		  case 2:
			  Assignment.CustomerDetails();
//			  System.out.println("enter the withdraw amount:");
//			  int withdraw=sc.nextInt();
//			  System.out.println("enter the account no");
//			  int acc_no=sc.nextInt();
//			  Assignment.withdraw(withdraw, acc_no);
//			  try {
//				Assignment.reducedATM(withdraw);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			  break;
		  case 3:
			  System.out.println("enter the account no:");
			  int accno=sc.nextInt();
			  Assignment.checkBalance(accno);
//			  System.out.println("enter account number 1:");
//			  int Acc_no1=sc.nextInt();
//			  System.out.println("enter account number 1:");
//			  int Acc_no2=sc.nextInt();
//			  int transferMoney=sc.nextInt();
//			  Assignment.transferMoney(Acc_no1, Acc_no2, transferMoney);
//			  break;
		  case 4:
			  try {
				Assignment.checkATMbalance();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  break;
		  case 5:
			  System.out.println("enter the withdraw amount:");
			  int withdraw=sc.nextInt();
			  System.out.println("enter the account no");
			  int acc_no=sc.nextInt();
			  Assignment.withdraw(withdraw, acc_no);
			  try {
				Assignment.reducedATM(withdraw);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  break;
		  case 6:
			  try {
				Assignment.checkATMbalance();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  break;
			default:
				System.out.println("invalid input");
				break;
		}
	}

}
