package Types;

import java.math.BigInteger;

public class Rationnel {
    private final BigInteger numerateur;
    private final BigInteger denominateur;

    public BigInteger getDenominateur() {
        return denominateur;
    }

    public BigInteger getNumerateur() {
        return numerateur;
    }

    public Rationnel(BigInteger numerateur, BigInteger denominateur) {
        this.numerateur = numerateur;
        this.denominateur = denominateur;
    }

    /*
        Multiplie deux rationnels : a*b
     */
    public Rationnel mul(Rationnel a, Rationnel b) {
        return new Rationnel(
                a.getNumerateur().multiply(b.getNumerateur()),
                a.getDenominateur().multiply(b.getDenominateur())
        );
    }

    /*
        Additionne deux rationnels : a+b
     */
    public Rationnel add(Rationnel a, Rationnel b) {
        if (a.getDenominateur() != b.getDenominateur()) {
            return new Rationnel(
                    (a.getNumerateur().multiply(b.getDenominateur())).add(a.getDenominateur().multiply(b.getNumerateur())),
                    a.getNumerateur().multiply(b.getDenominateur())
            );
        } else {
            return new Rationnel(
                    a.getNumerateur().add(b.getNumerateur()),
                    a.getDenominateur()
            );
        }

    }
    /*
        Soustrait deux rationnels : a-b
     */
    public Rationnel sub(Rationnel a, Rationnel b) {
        if (a.getDenominateur() != b.getDenominateur()) {
            return new Rationnel(
                    (a.getNumerateur().multiply(b.getDenominateur())).subtract(a.getDenominateur().multiply(b.getNumerateur())),
                    a.getNumerateur().multiply(b.getDenominateur())
            );
        } else {
            return new Rationnel(
                    a.getNumerateur().subtract(b.getNumerateur()),
                    a.getDenominateur()
            );
        }
    }

    /*
        Divise deux rationnels : a/b
     */
    public Rationnel div(Rationnel a, Rationnel b) {
        return mul(a, new Rationnel(b.getDenominateur(), b.getNumerateur()));
    }
}
