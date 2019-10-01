package aaa;



public class Klient {
    private int ID;
    private String meno;
    private String priezvisko;
    private String adresa;
    private String COP;

    public Klient(int ID, String meno, String priezvisko, String adresa, String COP){
        this.ID = ID;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.adresa = adresa;
        this.COP = COP;

    }

    public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public int getID() {
        return ID;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getCOP() {
        return COP;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCOP(String COP) {
        this.COP = COP;
    }
}
