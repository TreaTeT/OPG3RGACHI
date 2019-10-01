package Ebanka;

public class Klient {
    private int id;
    private String meno;
    private String priezvisko;
    private String adresa;
    private String COP;

    public Klient(int id, String meno, String priezvisko, String adresa, String COP) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.adresa = adresa;
        this.COP = COP;
    }

//get
    public int getId() {
        return id;
    }

   public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getCOP() {
        return COP;
    }
//set
    public void setId(int id) {
        this.id = id;
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

    public void setCOP(String COP) {
        this.COP = COP;
    }
}





