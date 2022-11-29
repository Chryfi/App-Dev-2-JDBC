package datalayer.entities;

public class Konto {
    private final int nr;
    private double saldo;

    public Konto(int nr, double saldo) {
        this.nr = nr;
        this.saldo = saldo;
    }

    public int getNr() {
        return nr;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString()
    {
        return "Konto: nr = " + this.nr + ", saldo = " + this.saldo;
    }
}
