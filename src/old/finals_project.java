package old;

import java.time.Clock;
import java.util.Random;
import java.util.Scanner;

class Account {

    int number;
    int pin;
    int confirm_pin;
    double balance;

    String name;
    String address;
    String bdate;

    String card_number;
    String cvv;
    String transaction_history[][];

    String dt_of_creation;

    public Account(int number, int pin, int confirm_pin, double balance, String name, String address, String bdate,
            String card_number, String cvv, String dtoc) {
        this.number = number;
        this.pin = pin;
        this.confirm_pin = confirm_pin;
        this.balance = balance;
        this.name = name;
        this.address = address;
        this.bdate = bdate;
        this.card_number = card_number;
        this.cvv = cvv;

        this.dt_of_creation = dtoc;

    }

    public Account(String transaction_history[][]) {
        this.transaction_history = transaction_history;
    }

}

public class finals_project {

    public static void main(String[] args) {

        Account accounts[] = new Account[50];

        Account temp_acc = new Account(0, 1000, 1000, 0, "", "", "", "4", "000", "");

        accounts[0] = new Account(555555, 1234, 1000, 500, "JAMIL ROMERO", "DARAGA ALBAY", "05 08 2003",
                "4444444444444444", "555", "");
        for (int i = 1; i < accounts.length; i++) {
            accounts[i] = new Account(0, 1000, 1000, 0, "", "", "", "4", "000", "");
        }

        main_menu(accounts);
    }

    public static void main_menu(Account[] accounts) {

        // account number| PIN | name | CV | address | balance

        Scanner reader = new Scanner(System.in);

        int navigator = 0;
        cleardp();
        System.out.println("SYSTEM");
        System.out.println("========================================");
        System.out.println("1.Bank");
        System.out.println("2.ATM");
        System.out.println("----------------------------------------");
        System.out.print("Select option: ");

        navigator = reader.nextInt();

        switch (navigator) {
            case 1:

                banking(accounts);
                break;
            case 2:
                atm(accounts);
                break;

            default:
                main_menu(accounts);
                break;
        }

        reader.close();

    }

    public static void banking(Account[] accounts) {

        int navigator = 0;
        Scanner check = new Scanner(System.in);
        cleardp();
        System.out.println("Banking");
        System.out.println("========================================");
        System.out.println("1.Register");
        System.out.println("2.Login");
        System.out.println("3.Exit");
        System.out.println("----------------------------------------");
        System.out.print("Select option: ");

        navigator = check.nextInt();
        switch (navigator) {
            case 1:
                cleardp();
                register(accounts);
                break;
            case 2:
                cleardp();

                login(accounts, 2);
                break;
            case 3:
                main_menu(accounts);
                break;
            default:
                banking(accounts);
                break;
        }

        check.close();
    }

    public static void atm(Account[] accounts) {
        Account temp_acc2 = new Account(0, 1000, 1000, 0, "", "", "", "4", "000", "");
        int counter = 0;
        int navigator = 0;
        Scanner check = new Scanner(System.in);
        while (true) {
            cleardp();
            System.out.println("ATM");
            System.out.println("========================================");
            System.out.println("1.Login");
            System.out.println("2.Exit");
            System.out.println("----------------------------------------");
            System.out.print("Select option: ");

            navigator = check.nextInt();

            switch (navigator) {
                case 1:
                    cleardp();
                    System.out.println("Login ");
                    System.out.println("========================================");
                    System.out.print("Enter Card number:");
                    temp_acc2.card_number = check.next();
                    System.out.print("Enter CVV:");
                    temp_acc2.cvv = check.next();

                    for (int i = 0; i < accounts.length; i++) {
                        if ((accounts[i].card_number.equals(temp_acc2.card_number))
                                && (accounts[i].cvv.equals(temp_acc2.cvv))) {
                            if (pin_checker(accounts[i].pin) == 1) {
                                counter = 0;
                                cleardp();
                                System.out.println("Account successfully login!");
                                System.out.println("========================================");
                                System.out.println("\nPress enter to Exit ->");
                                check.nextLine();
                                cleardp();
                                account_menu(1, accounts, i);
                            } else {
                                System.out.println("Wrong Pin!\n\n");

                                atm(accounts);
                            }
                        }
                        counter += 1;
                    }
                    if (counter > 0) {
                        System.out.println("No Account Found!");
                        System.out.println("========================================");
                        System.out.println("\nPress enter to Exit ->");
                        check.nextLine();
                    }
                    atm(accounts);
                    break;

                case 2:
                    main_menu(accounts);
                default:
                    break;
            }
        }

    }
    /*
     * else
     * {
     * cleardp();
     * System.out.println("Invalid CVV");
     * if(invalid_input(1)==2)
     * {
     * counter =0;
     * banking(accounts);
     * }
     * break;
     * }
     */

    public static void register(Account[] accounts) {
        Scanner check = new Scanner(System.in);
        Random rand = new Random();
        int counter = 0;
        int rnum_gegcn, rnum_cvv;
        Account temp_acc = new Account(0, 1000, 1000, 0, "", "", "", "4", "000", "");

        while (true) {
            cleardp();
            System.out.println(" Register Account");
            System.out.println("========================================");
            System.out.print("Name: ");
            temp_acc.name = check.nextLine();
            temp_acc.name = temp_acc.name.toUpperCase();

            System.out.print("Address: ");
            temp_acc.address = check.nextLine();
            temp_acc.address = temp_acc.address.toUpperCase();

            System.out.print("Birthdate(DD/MM/YYYY): ");
            temp_acc.bdate = check.nextLine();
            temp_acc.bdate = temp_acc.bdate.toUpperCase();

            System.out.print("Create Pin(4 digit number): ");
            temp_acc.pin = check.nextInt();
            check.nextLine();

            if (String.valueOf(temp_acc.pin).length() == 4) {
                System.out.print("Confirm Pin(4 digit number): ");
                temp_acc.confirm_pin = check.nextInt();
                check.nextLine();

                if (String.valueOf(temp_acc.confirm_pin).length() == 4) {
                    if (temp_acc.confirm_pin == temp_acc.pin) {
                        // personal info end
                        break;
                    } else {

                        if (invalid_input(1) == 2) {
                            banking(accounts);
                        }
                    }

                } else {

                    if (invalid_input(2) == 2) {
                        banking(accounts);
                    }
                }
            } else {

                if (invalid_input(2) == 2) {
                    banking(accounts);
                }
            }

        }

        // unique account_number generation
        for (int y = 0; y < accounts.length; y++) {
            if (accounts[y].number == 0) {
                for (int x = 0; x == 0; x++) {

                    int rnum_gen = rand.nextInt(10000000);

                    for (int z = 0; z < accounts.length; z++) {
                        if (accounts[z].number == rnum_gen) {
                            counter += 1;
                        }
                    }

                    if (counter == 0) {

                        temp_acc.number = rnum_gen;
                        cleardp();
                        System.out.println("Wrong Pin. Please Retry");
                        if (progression_input() == 2) {
                            banking(accounts);
                        } else {
                            cleardp();
                            System.out.println("Account has been successfully registered!\n\n");

                            for (int u = 1; u < 16; u++) {
                                rnum_gegcn = rand.nextInt(9);
                                temp_acc.card_number += Integer.toString(rnum_gegcn);
                            }
                            for (int o = 0; o < 3; o++) {
                                rnum_cvv = rand.nextInt(9);
                                temp_acc.cvv += Integer.toString(rnum_cvv);
                            }
                            temp_acc.dt_of_creation = time_date();

                            display(temp_acc);
                            accounts[y] = temp_acc;
                            System.out.println("\nPress enter to Exit ->");
                            check.nextLine();
                            banking(accounts);

                            return;

                        }

                    } else {
                        x = -1;
                    }

                }
            }
        }

        for (int u = 0; u < accounts.length; u++) {
            System.out.println(accounts[u].number);
        }

    }

    public static void login(Account[] accounts, int mode) {
        Account temp_acc1 = new Account(0, 1000, 1000, 0, "", "", "", "4", "000", "");

        int counter = 0;
        Scanner check = new Scanner(System.in);
        cleardp();

        while (true) {
            System.out.println("Login ");
            System.out.println("========================================");
            System.out.print("Enter Account number:");
            temp_acc1.number = check.nextInt();
            // System.out.println(temp_acc1.number);

            check.nextLine();

            System.out.print("Enter Account name:");
            temp_acc1.name = check.nextLine();
            temp_acc1.name = temp_acc1.name.toUpperCase();
            // System.out.println(temp_acc1.name);

            for (int i = 0; i < accounts.length; i++) {
                if (accounts[i].number == temp_acc1.number) {
                    if (accounts[i].name.equals(temp_acc1.name) == true) {

                        if (pin_checker(accounts[i].pin) == 1) {
                            counter = 0;
                            cleardp();
                            System.out.println("Account successfully login!");
                            System.out.println("========================================");
                            System.out.println("\nPress enter to Exit ->");
                            check.nextLine();
                            cleardp();
                            account_menu(mode, accounts, i);

                        } else {
                            System.out.println("Wrong Pin!\n\n");

                            banking(accounts);
                        }

                    } else {
                        cleardp();
                        System.out.println("Invalid Name");
                        if (invalid_input(1) == 2) {
                            counter = 0;
                            banking(accounts);
                        }
                        break;
                    }

                } else {
                    counter += 1;
                }
            }
            if (counter > 0) {
                cleardp();
                System.out.println("No accounts found");
                System.out.println("========================================");
                System.out.println("\nPress enter to Exit ->");
                check.nextLine();
                banking(accounts);

            }
        }

    }

    public static int pin_checker(int account_pin) {
        Scanner check = new Scanner(System.in);

        while (true)

        {
            cleardp();
            System.out.print("Confirm Pin(4 digit number): ");
            int temp_pin = check.nextInt();
            check.nextLine();

            if (String.valueOf(temp_pin).length() == 4) {
                if (account_pin == temp_pin) {
                    return 1;
                } else {
                    System.out.println("Wrong Pin!!");
                    System.out.println("========================================");
                    System.out.println("\nPress enter to Exit ->");
                    check.nextLine();
                    return 0;
                }
            } else {
                System.out.println("Invalid Input");
                System.out.println("========================================");
                System.out.println("\nPress enter to Exit ->");
                check.nextLine();
                return 0;

            }
        }
    }

    public static void account_menu(int mode, Account[] accounts, int index) {
        // ATM WITHDRAW, CHECK BALANCE , DEPOSIT,
        // BANK DEPOSIT WITHDRAW BUYCARD, CHECK ACCOUNT INFO
        Scanner check = new Scanner(System.in);
        int state = 0;

        while (true) {
            if (mode == 1) {
                cleardp();
                System.out.println("A T M");
                System.out.println("========================================");
                System.out.println("1.Withdraw");
                System.out.println("2.Deposit");
                System.out.println("3.Balance Inquiry");
                System.out.println("4.Exit");
                System.out.println("----------------------------------------");
                System.out.print("Select option: ");

                state = check.nextInt();

                switch (state) {
                    case 1:
                        withdraw(accounts, index, mode);
                        break;
                    case 2:
                        deposit(accounts, index, mode);
                        break;
                    case 3:
                        bal_inquiry(accounts[index].balance, index, accounts);
                        break;
                    case 4:
                        atm(accounts);
                    default:
                        break;
                }
            } else {
                cleardp();
                System.out.println("B A N K");
                System.out.println("========================================");
                System.out.println("1.Withdraw");
                System.out.println("2.Deposit");
                System.out.println("3.Balance Inquiry");
                System.out.println("4.Account Information");
                System.out.println("5.Transaction History");
                System.out.println("6.Exit");
                System.out.println("----------------------------------------");
                System.out.print("Select option: ");

                state = check.nextInt();
                switch (state) {
                    case 1:
                        withdraw(accounts, index, mode);
                        break;
                    case 2:
                        deposit(accounts, index, mode);
                        break;
                    case 3:
                        bal_inquiry(accounts[index].balance, index, accounts);
                        break;
                    case 4:
                        cleardp();
                        display(accounts[index]);
                        System.out.println("\nPress enter to Exit ->");
                        check.nextLine();
                        check.nextLine();
                        cleardp();
                        break;
                    case 5:
                        break;
                    case 6:
                        main_menu(accounts);
                        break;

                    default:
                        break;

                }
            }
        }
    }

    public static void display(Account display_account) {
        String card_linked = (display_account.card_number != "0000000000000000") ? display_account.card_number
                : "No card linked";

        System.out.println("Account Details");
        System.out.println("========================================");
        System.out.println("Account NO#: " + display_account.number);
        System.out.println("Account name: " + display_account.name);
        System.out.println("Adress: " + display_account.address);
        System.out.println("Date of Birth: " + display_account.bdate);
        System.out.println("Date of Creation:" + display_account.dt_of_creation);

        System.out.println("\nCurrent Balance: " + display_account.balance);

        System.out.println("\nCard: " + card_linked);
        System.out.println("CVV:" + display_account.cvv);

        System.out.println("========================================");

    }

    public static int progression_input() {
        cleardp();
        while (true) {
            Scanner check = new Scanner(System.in);
            int state = 0;
            System.out.println("Save Information?");
            System.out.println("========================================");
            System.out.println("1.Save");
            System.out.println("2.Exit without saving");
            System.out.println("----------------------------------------");
            System.out.print("Select option: ");
            state = check.nextInt();

            switch (state) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                default:
                    break;

            }
        }
    }

    public static int invalid_input(int mode) {
        Scanner check = new Scanner(System.in);
        int state = 0;

        while (true) {

            if (mode == 1) {

                System.out.println("========================================");
                System.out.println("1.Retry");
                System.out.println("2.Exit");
                System.out.println("----------------------------------------");
                System.out.print("Select option: ");
            } else {
                cleardp();
                System.out.println("Invalid input. Please Retry");
                System.out.println("========================================");
                System.out.println("1.Retry");
                System.out.println("2.Exit");
                System.out.println("----------------------------------------");
                System.out.print("Select option: ");

            }

            state = check.nextInt();

            switch (state) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                default:
                    cleardp();
                    break;
            }
        }

    }

    public static void withdraw(Account[] loginned_account, int index, int mode) {

        Scanner check = new Scanner(System.in);
        double previous_update = loginned_account[index].balance;
        double withdraw_amount = 0.0;
        double service_fee = 15.0;

        while (true) {
            cleardp();
            System.out.println("Withdraw");
            System.out.println("========================================");
            System.out.print("Enter Amount: ");
            withdraw_amount = check.nextDouble();
            if (withdraw_amount >= 500.00) {
                if ((withdraw_amount <= (loginned_account[index].balance - service_fee))) {
                    if (pin_checker(loginned_account[index].pin) == 1) {
                        loginned_account[index].balance -= (withdraw_amount + service_fee);

                        System.out.println("Succefully Withdrawn!!");
                        System.out.println("========================================");
                        System.out.println("\nPress enter to Exit ->");
                        check.nextLine();
                        check.nextLine();
                        // receipt here
                        receipt(loginned_account, 1, index, -(withdraw_amount + service_fee), previous_update);
                        account_menu(mode, loginned_account, index);
                    } else {
                        account_menu(mode, loginned_account, index);
                    }
                } else {
                    cleardp();
                    System.out.println("Not enough Balance");
                    System.out.println("========================================");
                    System.out.println("\nPress enter to Exit ->");
                    check.nextLine();
                    check.nextLine();
                    account_menu(mode, loginned_account, index);

                }
            } else {
                cleardp();
                System.out.println("Amount is lower than minimum withdrawal amount");
                System.out.println("========================================");
                System.out.println("\nPress enter to Exit ->");
                check.nextLine();
                check.nextLine();
                account_menu(mode, loginned_account, index);
            }

        }
    }

    public static void deposit(Account[] loginned_account, int index, int mode) {
        Scanner check = new Scanner(System.in);
        double deposit_amount = 0;
        double previous_update = loginned_account[index].balance;
        int state = 0;

        while (true) {
            cleardp();
            System.out.println("Deposit");
            System.out.println("========================================");
            System.out.print("Enter Amount:");
            deposit_amount = check.nextDouble();
            cleardp();
            System.out.println("Confirm Deposit?");
            System.out.println("========================================");
            System.out.println("1.Yes");
            System.out.println("2.Exit(Any key)");
            System.out.println("----------------------------------------");
            System.out.print("Select option: ");
            state = check.nextInt();
            if (state == 1) {
                if (deposit_amount > 0) {
                    loginned_account[index].balance += deposit_amount;
                    cleardp();
                    System.out.println("Succefully Depositted!!");

                    System.out.println("========================================");
                    System.out.println("\nPress enter to Exit ->");
                    receipt(loginned_account, 2, index, deposit_amount, previous_update);
                    check.nextLine();

                    account_menu(mode, loginned_account, index);
                } else {
                    cleardp();
                    System.out.println("Invalid Amount");
                    System.out.println("========================================");
                    System.out.println("\nPress enter to Exit ->");
                    check.nextLine();
                    check.nextLine();
                    account_menu(mode, loginned_account, index);
                }

            } else {
                account_menu(mode, loginned_account, index);
            }

        }

    }

    public static void bal_inquiry(double account_balance, int index, Account[] accounts) {
        Scanner check = new Scanner(System.in);
        cleardp();
        System.out.println("Account Balance");
        System.out.println("========================================");
        System.out.println("Current Balance:\tPhp" + account_balance);
        System.out.println("----------------------------------------");
        System.out.println("\nPress enter to Exit ->");
        receipt(accounts, 3, index, 0, 0);
        check.nextLine();
    }

    public static void receipt(Account[] accounts, int type, int index, double update, double previous_update)

    {
        Scanner check = new Scanner(System.in);
        String transaction_number = "";
        Random rand = new Random();
        String td = time_date();
        for (int u = 0; u < 10; u++) {
            transaction_number += Integer.toString(rand.nextInt(9));
        }

        cleardp();
        System.out.println("TRANSACTION                  NO:");
        System.out.println("========================================");
        System.out.println("            OFFICIAL RECEIPT");
        System.out.println("========================================");
        System.out.println("Account Number: " + accounts[index].number);
        System.out.println("Account Name: " + accounts[index].name);
        System.out.println("Date & Time: " + td);
        System.out.print("Transaction: ");
        if (type == 1) {
            System.out.println("Withdrawal");
        } else if (type == 2) {
            System.out.println("Deposit");
        } else {
            System.out.println("Balance Inquiry");
        }

        if (update != 0 || previous_update != 0) {
            System.out.println("\nPrevious Balance: " + previous_update);
            System.out.println("Computation: " + (update - 15));
            System.out.println("Additional fees: Service fee- PHP 15.00");
            System.out.println("Current Balance:" + accounts[index].balance);
        }

        System.out.println("----------------------------------------");
        System.out.println("\nPress enter to Exit ->");

        check.nextLine();

    }

    public static String time_date() {
        Clock systemClock = Clock.systemDefaultZone();

        String dat_time = String.valueOf(systemClock.instant());

        return dat_time;

    }

    public static void cleardp() {
        System.out.println("\033[2J\033[1;1H");

    }
}
