import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);
    private Matrix a;
    private Matrix b;

    public void run(){
        while(true){
            printMenu();
            String choice = scanner.nextLine().trim();

            try {
                switch (choice){
                    case "1" -> {
                        String name = askName();
                        getMatrix(name);
                        set(name, readMatrix(name));
                    }
                    case "2" -> showMatrix(askName());
                    case "3" -> showDet(getMatrix(askName()));
                    case "4" -> showAdd();
                    case "5" -> showSub();
                    case "6" -> showMultiply();
                    case "7" -> showDivide();
                    case "8" -> showInverse(askName());
                    case "9" -> showTranspose(askName());
                    case "0" -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Wrong command.");
                }
            } catch (IllegalArgumentException | ArithmeticException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void printMenu(){
        System.out.println();
        System.out.println(" 1 - Enter Matrix");
        System.out.println(" 2 - Show Matrix");
        System.out.println(" 3 - Find determinant");
        System.out.println(" 4 - Find A + B");
        System.out.println(" 5 - Find A - B");
        System.out.println(" 6 - Find A * B");
        System.out.println(" 7 - Find A / B");
        System.out.println(" 8 - Find Inverse Matrix");
        System.out.println(" 9 - Transpose Matrix");
        System.out.println(" 0 - Exit");
        System.out.println("<-------------------------->");
    }

    private Matrix getMatrix(String name){
        return switch (name){
            case "A" -> a;
            case "B" -> b;
            default -> throw new IllegalArgumentException("Unknown matrix: " + name);
        };
    }

    private void set(String name, Matrix value){
        switch (name){
            case "A" -> a = value;
            case "B" -> b = value;
            default -> throw new IllegalArgumentException("Unknown matrix: " + name);
        }
    }

    private String askName(){
        System.out.println("Which matrix A/B? ");
        return scanner.nextLine().trim().toUpperCase();
    }

    private Matrix readMatrix(String name){
        int n = readInt("Rows of " + name + ": ");
        int m = readInt("Cols of " + name + ": ");
        Matrix result = new Matrix(n, m);

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                System.out.println(name + "[" + i + "][" + j + "]:");
                double re = readDouble(" re: ");
                double im = readDouble(" im: ");
                result.set(i, j, new Complex(re, im));
            }
        }
        return result;
    }

    private void showMatrix(String name){
        Matrix matrix = getMatrix(name);
        if (matrix == null){
            System.out.println("This matrix is not entered yet.");
            return;
        }
        System.out.println(name + "=\n" + matrix);
    }

    private void showDet(Matrix matrix){
        if (matrix == null){
            System.out.println("This matrix is not entered yet.");
            return;
        }
        System.out.println("Det for this matrix is: " + matrix.findDet());
    }

    private boolean bothEntered(){
        if (a == null || b == null){
            System.out.println("Firstly enter both matrices");
            return false;
        }
        return true;
    }

    private void showAdd(){
        if (!bothEntered()) return;
        Matrix result = a.add(b);
        System.out.println("A + B:\n" + result);
    }

    private void showSub(){
        if (!bothEntered()) return;
        Matrix result = a.sub(b);
        System.out.println("A - B:\n" + result);
    }

    private void showMultiply(){
        if (!bothEntered()) return;
        Matrix result = a.multiply(b);
        System.out.println("A * B:\n" + result);
    }

    private void showDivide(){
        if (!bothEntered()) return;
        Matrix result = a.divide(b);
        System.out.println("A / B:\n" + result);
    }

    private void showInverse(String name){
        Matrix matrix = getMatrix(name);
        if (matrix == null){
            System.out.println("This matrix is not entered yet.");
            return;
        }
        System.out.println("For matrix " + name + ":\n" + matrix + "\n\nInverse is:\n" + matrix.inverse());
    }

    private void showTranspose(String name){
        Matrix matrix = getMatrix(name);
        if (matrix == null){
            System.out.println("This matrix is not entered yet.");
            return;
        }
        System.out.println("For matrix " + name + ":\n" + matrix + "\n\nTranspose is:\n" + matrix.transpose());
    }

    private int readInt(String message){
        while (true){
            System.out.println(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            }
            catch (NumberFormatException e){
                System.out.println("This is not a number, try again.");
            }
        }
    }

    private double readDouble(String message){
        while (true){
            System.out.println(message);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            }
            catch (NumberFormatException e){
                System.out.println("This is not a number, try again.");
            }
        }
    }
}
