# Booth's Algorithm - Binary Multiplication in Java

## Overview

This project implements **Booth's Algorithm** in Java, which is an efficient method for multiplying two signed binary numbers. Booth's Algorithm reduces the number of arithmetic operations and handles both positive and negative numbers using two's complement.

The program converts decimal numbers to binary, applies Booth's Algorithm, and finally converts the binary result back to decimal.

## Features

- Converts decimal numbers (both positive and negative) into binary.
- Implements Booth's Algorithm to multiply two integers.
- Supports signed binary numbers using two's complement.
- Displays intermediate steps of the algorithm, including the accumulator, multiplier, and control bits.
- Returns the correct product in decimal form.

## How the Program Works

1. **Input**: The program accepts two integers (multiplicand and multiplier) as input.
   
2. **Binary Conversion**: The program converts both numbers to their binary representation. If the numbers are negative, it computes their two's complement.

3. **Booth's Algorithm**:
   - Initializes the accumulator and sequence counter.
   - Based on the value of the least significant bit (Qn) and the extra bit (Qn+1), the algorithm performs one of the following operations:
     - Add the multiplicand (or its two’s complement) to the accumulator.
     - Perform an arithmetic shift right on the accumulator and the multiplier.
   - Repeats this process until the sequence counter reaches zero.
   
4. **Result**: After processing, the binary result is converted back into decimal, and the product is displayed.

## Files

- **BoothsAlgo.java**: This is the main Java class that contains all the logic for Booth's Algorithm.

## How to Run the Program

### Prerequisites

- Java Development Kit (JDK) installed on your system.
- A terminal/command prompt or an IDE like IntelliJ IDEA, Eclipse, or VSCode.

### Steps to Run

1. Clone or download this repository.
   
2. Open a terminal/command prompt in the project folder and compile the Java file:
   ```bash
   javac BoothsAlgo.java
   ```

3. Run the program using:
   ```bash
   java BoothsAlgo
   ```

4. The program will prompt you to enter two integers (multiplicand and multiplier).

5. The output will display the intermediate steps of Booth’s Algorithm and finally show the result of the multiplication.

### Sample Input/Output

```
Enter the first number
5
Enter the second number
3

Multiplicand=5 Binary Form=0101
Multiplier=3 Binary Form=0011

Accumulator           QR         Qn Qn+1   Sequence Counter
[0, 0, 0, 0]       [0, 0, 1, 1]       1 0          4
[0, 1, 0, 1]       [0, 0, 1, 1]       1 1          3
[0, 0, 1, 0]       [1, 0, 0, 1]       1 1          2
[0, 0, 1, 0]       [0, 1, 0, 0]       0 1          1

Result = 15
```

## Key Methods

- **`multiplyInteger(int multiplicand, int multiplier)`**: Main method that runs the algorithm and prints intermediate steps and final output.
- **`decToBin(int n)`**: Converts a decimal number to its binary representation.
- **`findTwoComplement(String n)`**: Finds the two’s complement of a binary number.
- **`arithmeticShiftRight(char[] a, char[] b)`**: Performs arithmetic shift right on the accumulator and multiplier.
- **`addTwoBin(char[] a, char[] b)`**: Adds two binary numbers.
- **`binToDec(char[] a, char[] b)`**: Converts binary result to decimal.

## Additional Notes

- **Negative Numbers**: The program handles negative numbers by converting them to two's complement before performing the multiplication. The final result is adjusted to ensure correct sign representation.
  
- **Efficiency**: Booth’s Algorithm optimizes multiplication by reducing the number of required operations, especially useful when multiplying numbers with a large number of bits.

## References

- [Booth's Multiplication Algorithm](https://en.wikipedia.org/wiki/Booth%27s_multiplication_algorithm)
- [Two's Complement](https://en.wikipedia.org/wiki/Two%27s_complement)

---

Enjoy multiplying signed integers using Booth's Algorithm with this Java implementation!