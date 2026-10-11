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
                this.data[i][j] = new Complex(0, 0);
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
                data[i][j] = arr[i][j];
            }
        }

    }

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
}