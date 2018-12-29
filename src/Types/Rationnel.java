package Types;

import java.math.BigInteger;

public class Rationnel implements Valeurs {
    private final BigInteger numerateur;
    private final BigInteger denominateur;
    private final boolean entier;

    public BigInteger getDenominateur() {
        return denominateur;
    }

    public BigInteger getNumerateur() {
        return numerateur;
    }

    public Rationnel(BigInteger numerateur, BigInteger denominateur) {
        this.numerateur = numerateur;
        this.denominateur = denominateur;
        this.entier = false;
    }

    public Rationnel(BigInteger numerateur) {
        this.numerateur = numerateur;
        this.denominateur = BigInteger.valueOf(1);
        this.entier = true;
    }

    public String affichageDansConsole() {
        if (entier) {
            return numerateur.toString();
        }
        else {
            return numerateur.toString() + "/" + denominateur.toString();
        }
    }

    @Override
    public String getType() {
        return "Rationnel";
    }

    /*
            Multiplie deux rationnels : a*b
     */
    public static Rationnel mul(Rationnel a, Rationnel b) {
        return new Rationnel(
                a.getNumerateur().multiply(b.getNumerateur()),
                a.getDenominateur().multiply(b.getDenominateur())
        );
    }

    /*
        Additionne deux rationnels : a+b
     */
    public static Rationnel add(Rationnel a, Rationnel b) {
        if (!a.getDenominateur().equals(b.getDenominateur())) {
            return new Rationnel(
                    (a.getNumerateur().multiply(b.getDenominateur())).add(a.getDenominateur().multiply(b.getNumerateur())),
                    a.getNumerateur().multiply(b.getDenominateur())
            );
        } else {
            return new Rationnel(
                    a.getNumerateur().add(b.getNumerateur())
            );
        }

    }
    /*
        Soustrait deux rationnels : a-b
     */
    public static Rationnel sub(Rationnel a, Rationnel b) {
        if (!a.getDenominateur().equals(b.getDenominateur())) {
            return new Rationnel(
                    (a.getNumerateur().multiply(b.getDenominateur())).subtract(a.getDenominateur().multiply(b.getNumerateur())),
                    a.getNumerateur().multiply(b.getDenominateur())
            );
        } else {
            return new Rationnel(
                    a.getNumerateur().subtract(b.getNumerateur())
            );
        }
    }

    /*
        Divise deux rationnels : a/b
     */
    public static Rationnel div(Rationnel a, Rationnel b) {
        return mul(a, new Rationnel(b.getDenominateur(), b.getNumerateur()));
    }
}
