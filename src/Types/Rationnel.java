package Types;

import java.math.BigInteger;

public class Rationnel implements Valeurs {
    private final BigInteger numerateur;
    private final BigInteger denominateur;
    private final boolean entier;


//region Constructeurs
    public Rationnel(BigInteger numerateur, BigInteger denominateur) {
        if (denominateur.equals(BigInteger.valueOf(1))) {
            this.entier = true;
        } else {
            this.entier = false;
        }
        this.numerateur = numerateur;
        this.denominateur = denominateur;
    }

    public Rationnel(int numerateur, int denominateur) {
        if (denominateur == 1) {
            this.entier = true;
        } else {
            this.entier = false;
        }
        this.numerateur = BigInteger.valueOf(numerateur);
        this.denominateur = BigInteger.valueOf(denominateur);
    }

    public Rationnel(long numerateur, long denominateur) {
        if (denominateur == 1) {
            this.entier = true;
        } else {
            this.entier = false;
        }
        this.numerateur = BigInteger.valueOf(numerateur);
        this.denominateur = BigInteger.valueOf(denominateur);
    }

    public Rationnel(BigInteger numerateur) {
        this.numerateur = numerateur;
        this.denominateur = BigInteger.valueOf(1);
        this.entier = true;
    }

    public Rationnel(int numerateur) {
        this.numerateur = BigInteger.valueOf(numerateur);
        this.denominateur = BigInteger.valueOf(1);
        this.entier = true;
    }

    public Rationnel(long numerateur) {
        this.numerateur = BigInteger.valueOf(numerateur);
        this.denominateur = BigInteger.valueOf(1);
        this.entier = true;
    }

//endregion

    public BigInteger getDenominateur() {
        return denominateur;
    }

    public BigInteger getNumerateur() {
        return numerateur;
    }

    public boolean isEntier() {
        return entier;
    }

    @Override
    public String affichageDansConsole() {
        Rationnel x = reductionFraction(this);
        if (entier || x.getDenominateur().equals(BigInteger.valueOf(1))) {
            return x.getNumerateur().toString();
        }
        else {
            return x.getNumerateur().toString() + "/" + x.getDenominateur().toString() + " ~= " + calculValeurApprochee(x);
        }
    }

    @Override
    public String getType() {
        return "Rationnel";
    }

    /*
        Renvoie une fraction irréductible de celle passée en paramètre
     */
    private static Rationnel reductionFraction(Rationnel r) {
        BigInteger pgcd = Rationnel.calculPGCD(r.getNumerateur(), r.getDenominateur());
        return new Rationnel(r.getNumerateur().divide(pgcd), r.getDenominateur().divide(pgcd));
    }

    private float calculValeurApprochee(Rationnel x) {
        return x.getNumerateur().floatValue()/x.getDenominateur().floatValue();
    }

    /*
        Calcul le PGCD de deux BigInteger
     */
    private static BigInteger calculPGCD(BigInteger a, BigInteger b) {
        return a.gcd(b);
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
