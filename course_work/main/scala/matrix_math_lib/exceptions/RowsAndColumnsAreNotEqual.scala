package matrix_math_lib.exceptions

class RowsAndColumnsAreNotEqual(s: String) extends Exception(s) {
  System.err.println(s)
}
