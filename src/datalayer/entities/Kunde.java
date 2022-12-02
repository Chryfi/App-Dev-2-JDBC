package datalayer.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Kunde {
    private int nr;
    private String name;
    private String address;
    private Konto konto;
    private Set<Newsletter> newsletters;

    public Kunde(int nr, String name, String address, Konto konto, Set<Newsletter> newsletters) {
        this.nr = nr;
        this.name = name;
        this.konto = konto;
        this.address = address;
        this.newsletters = newsletters;
    }

    public Kunde(int nr, String name, String address, Konto konto) {
        this(nr, name, address, konto, new HashSet<>());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Newsletter> getNewsletters() {
        return new HashSet<>(this.newsletters);
    }

    public void setNewsletters(Set<Newsletter> newsletters) {
        this.newsletters = new HashSet<>(newsletters);
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
    public String toString() {
        String output = "Nr " + this.nr + ", name " + this.name
                + ", adresse " + this.address + "\nkonto: " + this.konto + "\nnewsletter: ";

        for (Newsletter newsletter : this.newsletters) {
            output += newsletter + "\n";
        }

        return output;
    }
}
