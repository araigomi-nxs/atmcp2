package atm;


public class Account {
    String name ="";
    String address ="";
    String bDate ="";
    double balance = 0.0;
    String PIN= "0000";

    public void create(String name, String address, String bDate, double balance, String PIN) {
        this.name = name;
        this.address = address;
        this.bDate = bDate;
        this.balance = balance;
        this.PIN = PIN;


    }
    public int login(String entered_PIN)
    {
        if(this.PIN.equals(entered_PIN))
        {
            System.out.println("Successful Login!");
            return  1;
        }
        else
        {
            System.out.println("Unsuccessful Login!");
            return  0;
        }
    }

    //return int 1( successful transaction | 0 unsuccessful)
    public int deposit(double deposit_amount) {
        if (deposit_amount >= 100) {
            this.balance += deposit_amount;

            return 1;
        } else {
            return 0;






        }

    }

    //return int 1( successful transaction | 0 unsuccessful)
    public int withdraw(double withdraw_amount) {
            if( withdraw_amount >= 500 && this.balance >= withdraw_amount)
            {
                this.balance -= withdraw_amount;

                return 1;
            }
            else
            {
                return  0;
            }
       }

    public String checkBalance()
    {
        return balance +"";
    }
}