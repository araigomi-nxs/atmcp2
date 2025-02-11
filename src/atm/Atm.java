package atm;

import java.util.Scanner;

public class Atm {

    public static void main ( String[] args)
    {
            Account  account[]= new Account[100];


            for( int i = 0 ; i < account.length ; i++)
            {
                account[i] = new Account();
            }

            main_menu(account);

    }

    public static void main_menu(Account[] accounts)
    {
        accounts[0].create("Jamil Romero", "Daraga,Albay", "05/08/2003", 0, "1234");

        Scanner read = new Scanner(System.in);
        int navigator = 0;

        System.out.println("SYSTEM");
        System.out.println("========================================");
        System.out.println("1.Login");
        System.out.println("----------------------------------------");
        System.out.print("Select option: ");
        navigator = read.nextInt();

        switch ( navigator)
        {
            case 1:
                System.out.println("Enter pin");
                if ( accounts[0].login(read.next()) == 1)
                {

                    account_menu(accounts);
                }
                else {
                    main_menu(accounts);
                }

                break;
            default:
                main_menu(accounts);
                break;

        }


    }

    public static void account_menu(Account[] accounts)
    {
        Scanner read = new Scanner(System.in);
        int navigator = 0;

        cleardp();
        System.out.println("ACCOUNT MENU");
        System.out.println("========================================");
        System.out.println("1.Deposit");
        System.out.println("2.Withdraw");
        System.out.println("3.Check Balance");
        System.out.println("----------------------------------------");
        System.out.print("Select option: ");


        navigator = read.nextInt();
        switch ( navigator)
        {
            case 1:
                cleardp();
                System.out.println("Enter Deposit Amount: ");
                if(             accounts[0].deposit(read.nextDouble())      ==1         )
                {
                    System.out.println("Successful Deposit!\n\n\n");
                    read.nextLine();
                    account_menu(accounts);
                }
                else
                {
                    System.out.println("Unsuccessful Deposit!\n\n");
                    read.nextLine();
                    account_menu(accounts);
                }
                break;

            case 2:
                cleardp();
                System.out.println("Enter Withdraw Amount: ");
                if( accounts[0].withdraw(read.nextDouble())==1)
                {
                    System.out.println("Successful Withdrawal!\n\n");
                    read.nextLine();
                    account_menu(accounts);
                }
                else
                {
                    System.out.println("Unsuccessful Withdrawal!\n\n");
                    read.nextLine();
                    account_menu(accounts);
                }
                break;
            case 3:
            {
                cleardp();
                System.out.println( accounts[0].checkBalance()+"\n\n");
                read.nextLine();
                account_menu(accounts);

            }
            default:
                read.nextLine();
                account_menu(accounts);
                break;

        }



    }
    public static void cleardp() {
        System.out.println("\033[2J\033[1;1H");

    }
}
