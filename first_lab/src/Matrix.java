public class Matrix {
    private final int n;
    private final int m;
    private final Complex[][] data;

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Size of matrix must be positive.");
        }

        this.n = n;
        this.m = m;
        this.data = new Complex[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.data[i][j] = Complex.ZERO;
            }
        }
    }

    public Matrix(Complex[][] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array can't be empty.");
        }

        this.n = arr.length;
        if (n == 0) {
            throw new IllegalArgumentException("Matrix must has atleast one row.");
        }

        if (arr[0] == null) {
            throw new IllegalArgumentException("Marix row can't be empty.");
        }

        this.m = arr[0].length;
        if (m == 0) {
            throw new IllegalArgumentException("Matrix must has atleast one column.");
        }

        this.data = new Complex[n][m];

        for (int i = 0; i < n; i++) {
            if (arr[i] == null || arr[i].length != m) {
                throw new IllegalArgumentException("All rows of the matrix must be of the same length.");
            }
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == null){
                    throw new IllegalArgumentException("Matrix element at (" + i + ", " + j + ") must not be null.");
                }
                data[i][j] = arr[i][j];
            }
        }

    }

    public int getRows() { return n; }
    public int getCols() { return m; }

    public Complex get(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            throw new IndexOutOfBoundsException("Index out of range: (" + i + ", " + j + ").");
        }
        return data[i][j];
    }

    public void set(int i, int j, Complex val) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            throw new IndexOutOfBoundsException("Index out of range: (" + i + ", " + j + ").");
        }
        if (val == null) {
            throw new IllegalArgumentException("Value must not be null.");
        }
        data[i][j] = val;
    }

    public Matrix add(Matrix other) {
        if (n != other.n || m != other.m) {
            throw new IllegalArgumentException("The matrices must be of the same size.");
        }

        Complex[][] newData = new Complex[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newData[i][j] = get(i, j).add(other.get(i, j));
            }
        }
        return new Matrix(newData);
    }

    public Matrix sub(Matrix other){
        if (n != other.n || m != other.m) {
            throw new IllegalArgumentException("The matrices must be of the same size.");
        }

        Complex[][] newData = new Complex[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newData[i][j] = get(i, j).sub(other.get(i, j));
            }
        }
        return new Matrix(newData);
    }

    public Matrix multByNum(Complex num){
        Complex[][] newData = new Complex[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                newData[i][j] = data[i][j].multiply(num);
            }
        }
        return new Matrix(newData);
    }

    public Matrix divByNum(Complex num){
        Complex[][] newData = new Complex[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                newData[i][j] = data[i][j].divide(num);
            }
        }
        return new Matrix(newData);
    }

    public Matrix multiply(Matrix other){
        if (m != other.n){
            throw new IllegalArgumentException("Number of cols in Matrix 1 must be equal" +
                    " to number of rows in Matrix 2");
        }

        Complex[][] newData = new Complex[n][other.m];

        for (int i = 0; i < n; i++){
           for (int j = 0; j < other.m; j++){
               Complex sum = Complex.ZERO;
               for (int k = 0; k < m; k++){
                   sum = sum.add(data[i][k].multiply(other.data[k][j]));
               }
               newData[i][j] = sum;
           }
        }
        return new Matrix(newData);
    }

    public Matrix transpose(){
        Complex[][] newData = new Complex[m][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                newData[j][i] = data[i][j];
            }
        }
        return new Matrix(newData);
    }

    private Matrix minor(int i_target, int j_target){
        Complex[][] newData = new Complex[n-1][m-1];
        int ni = 0;
        for (int i = 0; i < n; i++){
            if (i == i_target){continue;}
            int nj = 0;
            for (int j = 0; j < m; j++){
                if (j == j_target){continue;}
                newData[ni][nj] = data[i][j];
                nj += 1;
            }
            ni += 1;
        }
        return new Matrix(newData);
    }

    public Complex findDet(){
        if (n != m){
            throw new IllegalArgumentException("Matrix must be square size");
        }
        int size = n;
        if (size == 1){return data[0][0];}
        if (size == 2){return (data[0][0].multiply(data[1][1])).sub(data[0][1].multiply(data[1][0]));}

        Complex res = Complex.ZERO;
        for (int j = 0; j < m; j++){

            int sign = (j % 2 == 0) ? 1 : -1;

            if (sign == 1){
                res = res.add(data[0][j].multiply(findDetOfMinor(0, j)));
            }
            else{
                res = res.sub(data[0][j].multiply(findDetOfMinor(0, j)));
            }
        }

        return res;
    }

    private Complex findDetOfMinor(int i, int j){
        return minor(i, j).findDet();
    }

    public Matrix inverse(){
        Complex det = this.findDet();
        if (det.isEqual(Complex.ZERO)){
            throw new ArithmeticException("Can't find inverse matrix if it has determinant = 0");
        }

        if (n == 1) {
            Complex[][] single = { { Complex.ONE.divide(data[0][0]) } };
            return new Matrix(single);
        }

        Complex[][] newData = new Complex[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if ((i + j) % 2 == 0){
                    newData[i][j] = findDetOfMinor(i, j).divide(det);
                }
                else {
                    newData[i][j] = (Complex.minusONE.multiply(findDetOfMinor(i, j))).divide(det);
                }
            }
        }
        return new Matrix(newData).transpose();
    }

    public Matrix divide(Matrix other){
        return this.multiply(other.inverse());
    }

    @Override
    public String toString(){
        String[][] textMatrix = new String[n][m];
        int width = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                textMatrix[i][j] = data[i][j].toString();
                width = Math.max(width, textMatrix[i][j].length());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++){
            sb.append("| ");
            for (int j = 0; j < m; j++){
                sb.append(String.format("%" + width + "s", textMatrix[i][j]));
                if (j < m - 1) {sb.append("  ");}
            }
            sb.append(" |");
            if (i < n - 1){sb.append("\n");}
        }
        return sb.toString();
    }
}