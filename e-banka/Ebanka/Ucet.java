package Ebanka;

public class Ucet {
    private int id;
    private int idu;
    private int balance;

    public Ucet(int id, int idu, int balance) {
        this.id = id;
        this.idu = idu;
        this.balance = balance;
    }
//get
    public int getId() {
        return id;
    }

    public int getIdu() {
        return idu;
    }

    public int getBalance() {
        return balance;
    }
//set
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public void setId(int id) {
        this.id = id;
    }

}
