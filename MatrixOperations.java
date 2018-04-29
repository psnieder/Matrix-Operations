package matrix;

/**
 * Matrix Operations.
 * @author Paul Snieder
 */
public class MatrixOperations {

  public static void main(String[] args) {
    
    int[][] matrix = new int[5][5]; //generate the matrix
    
    matrix[0][1] = 1; //populate the matrix
    matrix[1][0] = 1;
    matrix[1][2] = 1;
    matrix[2][1] = 1;
    matrix[1][3] = 1;
    matrix[3][1] = 1;
    matrix[1][4] = 1;
    matrix[4][1] = 1;
    matrix[2][4] = 1;
    matrix[4][2] = 1;
    matrix[3][4] = 1;
    matrix[4][3] = 1;
    
    int n = matrix.length; //get the length of the nxn matrix
    
    int[][] colVector = one(n); //generate the "1" column vector
    
    int[][] kVector = transpose(matrixMultiplication(matrix, colVector)); //generate kVector (vector of degrees)
    
    double[][] constant = new double[1][1]; //generate the constant multiplier in matrix form
    constant[0][0] = 0.5; //populate the constant multiplier
    
    int[][] m = matrixMultiplication(matrixMultiplication(kVector, colVector), constant); //total number of edges in the network
    
    int[][] N = matrixMultiplication(matrix,matrix); //find common neighbors between vertices i and j
    
    for (int i = 0; i < N.length; i++) { //set diagonals to 0 because a node doesn't have common neighbors with itself
      N[i][i] = 0;                       //if we keep diagonals, then N[i][i] will be the degree of vertex i 
    }
    
    int numTriangles = (trace(matrixMultiplication(matrixMultiplication(matrix, matrix), matrix)))/6; //number of triangles in the network
  
    
    System.out.println("Original Matrix: ");
    printMatrix(matrix); //print original matrix to verify it's there
    System.out.println();
    
    System.out.println("'1' Column Vector: ");
    printMatrix(colVector); //print the "1" matrix to verify it's there
    System.out.println();
    
    System.out.println("k Vector (vector of degrees):");
    printMatrix(kVector); //prints vector of degrees
    System.out.println();
    
    System.out.println("Number of edges in the network:");
    printMatrix(m);
    System.out.println();
    
    System.out.println("Matrix N: Number of common neighbors of vertices i and j");
    printMatrix(N);
    System.out.println();
    
    System.out.println("Number of triangles in the network:");
    System.out.print(numTriangles);
    System.out.println();    
    
  }
  
  /**
   * Transposes a matrix
   * @param A, original matrix
   * @return B, the transpose
   */
  public static int[][] transpose(int[][] A) {
    
   int  n = A.length;
   int m = A[0].length;
   int[][] B = new int[m][n];
   
   for (int i = 0; i < m; i++) {
     for (int j = 0; j < n; j++) {
       B[i][j] = A[j][i];
     }
   }
   
   return B;
   
  }
  /**
   * Get's the trace of a matrix
   * @param A the input matrix
   * @return trace, the trace (sum of diagonal)
   */
  public static int trace(int[][] A) {
    
    int n = A.length;
    int trace = 0;
    
    for (int i = 0; i < n; i++) {
      trace += A[i][i];
    }
    
    return trace;
    
  }
  
  /**
   * Generates the "1" matrix (nx1 column vector)
   * @param n, number of rows
   * @return A, the column vector
   */
  public static int[][] one(int n) {
    
    int[][] A = new int[n][1];
    
    for (int i = 0; i < n; i++) {
      A[i][0] = 1;
    }
    
    return A;
    
  }
  /**
   * Prints a matrix
   * @param A, the matrix to print
   */
  public static void printMatrix(int[][] A) {
    
    int n = A.length;
    int m = A[0].length;
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        System.out.print(A[i][j] + " ");
      }
      System.out.println();
    }
    
  }
  
  /**
   * Does matrix multiplication on A and B
   * @param A, first matrix
   * @param B, second matrix
   * @return result, the resulting matrix
   */
  public static int[][] matrixMultiplication(int[][] A, int[][] B) {
    
    int aRows = A.length;
    int aCol = A[0].length;
    int bCol = B[0].length; 
    
    int[][] result = new int[aRows][bCol];
    
    for (int i = 0; i < aRows; i++) { 
      for (int j = 0; j < bCol; j++) { 
          for (int k = 0; k < aCol; k++) { 
              result[i][j] += A[i][k] * B[k][j];
          }
      }
    }
    
    return result;
    
  }
  
  /**
   * Does matrix multiplication on A and B (overloaded for int[] and double[] parameters)
   * @param A, first matrix
   * @param B, second matrix
   * @return result, the resulting matrix
   */
  public static int[][] matrixMultiplication(int[][] A, double[][] B) {
    
    int aRows = A.length;
    int aCol = A[0].length;
    int bCol = B[0].length;
    
    int[][] result = new int[aRows][bCol];
    
    for (int i = 0; i < aRows; i++) { 
      for (int j = 0; j < bCol; j++) { 
          for (int k = 0; k < aCol; k++) { 
              result[i][j] += A[i][k] * B[k][j];
          }
      }
    }
    
    return result;
    
  }

}