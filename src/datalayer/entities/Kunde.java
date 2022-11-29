package datalayer.entities;

public class Kunde {
    private int nr;
    private String name;
    private Konto konto;

    public Kunde(int nr, String name, Konto konto) {
        this.nr = nr;
        this.name = name;
        this.konto = konto;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }

    @Override
    public String toString()
    {
        return "Kunde: nr = " + this.nr + ", name = " + this.name + ", " + this.konto;
    }
}
