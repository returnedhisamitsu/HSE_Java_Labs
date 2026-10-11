public class Complex {
    private int Re;
    private int Im;

    Complex(){
        this(0,0);
    }
    Complex(int Re, int Im){
        this.Re = Re;
        this.Im = Im;
    }

    public int getRe() {return Re;}
    public int getIm() {return Im;}

    public void setRe(int nRe){
        Re = nRe;
    }

    public void setIm(int nIm){
        Im = nIm;
    }

    public Complex add(Complex p){
        return new Complex(Re + p.Re, Im + p.Im);
    }

    public Complex mult(Complex p){
        int NewRe = Re * p.Re - Im * p.Im;
        int NewIm = Re * p.Im + Im * p.Re;
        return new Complex(NewRe, NewIm);
    }

    public String toString(){
        if (Re == 0 && Im == 0) {return "0";}
        if (Re == 0) {return Im + "i";}
        if (Im == 0) {return String.valueOf(Re);}

        String sign = (Im < 0) ? " - " : " + ";
        return Re + sign + Math.abs(Im) + "i";
    }
}

