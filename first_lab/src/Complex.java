import java.util.Locale; //нужно для красивого вывода

public class Complex {
    private static final double EPS = 1e-9;
    private final double re;
    private final double im;

    public Complex(double re, double im){
        this.re = re;
        this.im = im;
    }

    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex minusONE = new Complex(-1, 0);

    private static boolean isZero(double x){
        return Math.abs(x) < EPS;
    }

    private static String fmt(double x){
        String s = String.format(Locale.US, "%.2f", x);
        if (s.equals("-0.00")){
            return "0.00";
        }
        else{
            return s;
        }
    }

    public double getRe() {return re;}
    public double getIm() {return im;}

    public Complex add(Complex p){
        return new Complex(re + p.re, im + p.im);
    }

    public Complex sub(Complex p){
        return new Complex(re - p.re, im - p.im);
    }

    public Complex multiply(Complex p){
        double newRe = re * p.re - im * p.im;
        double newIm = re * p.im + im * p.re;
        return new Complex(newRe, newIm);
    }

    public Complex divide(Complex p){

        double denom = (Math.pow(p.re, 2) + Math.pow(p.im, 2));
        if (isZero(denom)){
            throw new ArithmeticException("Division by zero complex number");
        }

        double newRe = (re * p.re + im * p.im) / denom;
        double newIm = (im * p.re - re * p.im) / denom;
        return new Complex(newRe, newIm);
    }

    public boolean isEqual(Complex p){
        return isZero(re - p.re) && isZero(im - p.im);
    }

    @Override
    public String toString(){
        if (isZero(re) && isZero(im)) {return "0";}
        if (isZero(re)) {return im + "i";}
        if (isZero(im)) {return String.valueOf(re);}

        String sign = (im < 0) ? " - " : " + ";
        return fmt(re) + sign + fmt(Math.abs(im)) + "i";
    }
}

