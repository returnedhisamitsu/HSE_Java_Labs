# Complex Matrix Calculator

A console program in Java for working with matrices of complex numbers.

## Features

- Complex numbers: addition, subtraction, multiplication, division
- Matrices of complex numbers:
  - addition and subtraction
  - multiplication
  - division (`A / B` is computed as `A * B^-1`)
  - determinant
  - inverse matrix
  - transposition
  - multiplication and division by a (complex) number
- Any result can be saved as `A` or `B`, so operations can be chained, for example `(A + B) * B`
- Interactive console menu with two matrices, `A` and `B`
- Input validation: wrong sizes, non-square matrices, singular matrices and division by zero produce a clear error message instead of crashing the program

## Project structure

```
src/
  Complex.java    - immutable complex number (double re, im) and its operations
  Matrix.java     - matrix of Complex values and matrix operations
  ConsoleUI.java  - console menu, input and output
  Main.java       - entry point
```

## How to run

From the project root:

```
javac src/*.java
java -cp src Main
```

Or open the project in IntelliJ IDEA and run `Main`.

## How to use

The program shows a menu:

```
1 - Enter Matrix
2 - Show Matrix
3 - Find determinant
4 - Find A + B
5 - Find A - B
6 - Find A * B
7 - Find A / B
8 - Find Inverse Matrix
9 - Transpose Matrix
10 - Multiply by number
11 - Divide by number
0 - Exit
```

To enter a matrix, choose `1`, type the matrix name (`A` or `B`), then the number of rows and columns. After that, enter the matrix row by row, with elements separated by spaces.\
After every operation that returns a matrix, the program asks whether to save the result as `A` or `B`. Type `A` or `B` to save it, or press Enter to skip.

### Number format

- Complex number: `re,im` (for example `1,2` means `1 + 2i`, `0,-1` means `-i`)
- Real number: just `re` (for example `5` means `5 + 0i`)
- Use a dot as the decimal separator: `2.5`

### Example

Entering a 2x2 matrix:

```
1 2
3 4
```

Entering a 2x2 matrix with complex elements:

```
0,1 1
1   0,-1
```

## Notes

- Results are printed with two digits after the decimal point.
- Comparisons with zero use a small tolerance (`1e-9`) because of floating-point errors.
- The determinant and the inverse matrix are computed with cofactor expansion, which is slow for large matrices (O(n!)). The program is intended for small matrices.
- Matrix division `A / B` means `A * B^-1` (B is multiplied from the right).
- `Complex` is immutable: every operation returns a new object.
