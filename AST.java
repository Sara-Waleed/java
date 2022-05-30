package add_mult;

public class AST {
    public static abstract class Expr {
        public abstract int evaluate();
        public abstract String prefix();
    }
    public static class DigitExpr extends Expr {
        int digit;
        public DigitExpr(int digit) {
            this.digit = digit;
        }
        @Override
        public int evaluate() {
            return digit;
        }
        @Override
        public String toString() {
            return this.digit + "";
        }
        @Override
        public String prefix() {
            return this.digit + "";
        }
    }
    public static class AddExpr extends Expr {
        Expr left;
        Expr right;
        public AddExpr(Expr left, Expr right) {
            this.left = left;
            this.right = right;
        }
        @Override
        public int evaluate() {
            return left.evaluate() + right.evaluate();
        }
        @Override
        public String toString() {
            return this.left.toString() + "+" + this.right.toString();
        }
        @Override
        public String prefix() {
            return "+" + left.prefix() + right.prefix();
        }
    }

    public static class MultExpr extends Expr {
        Expr left;
        Expr right;
        public MultExpr(Expr left, Expr right) {
            this.left = left;
            this.right = right;
        }
        @Override
        public int evaluate() {
            return left.evaluate() * right.evaluate();
        }
        @Override
        public String toString() {
            return this.left.toString() + "*" + this.right.toString();
        }
        @Override
        public String prefix() {
            return "*" + left.prefix() + right.prefix();
        }
    }
}







import java.io.*;
import java.util.*;
public f(){

try{

BufferedReader in;
in =new BefferedReader(new InputStreamReader(System.in));
StringToken st=ne2w StringToken(in.readLine());
int count =st.countToken();
boolean firsttoken=true;
whilr(st.hasmoretokend()){
String input=st.nextToken();

if(firsttoken){

}






}




















