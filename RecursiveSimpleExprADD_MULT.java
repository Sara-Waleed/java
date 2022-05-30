
package add_mult;
import add_mult.AST.*;

public class RecursiveSimpleExprADD_MULT {
    private String msg;
    private int lookahead;

    public class Token{
        String symbol;
        Type type;
        public Token(String symbol, Type type){
            this.symbol = symbol;
            this.type = type;
        }
    }
    public static enum Type {
        NUM, ADD,MULT,EOF
    };
    public RecursiveSimpleExprADD_MULT(String msg){
        this.msg = msg;
        lookahead = 0;
    }
    public Token getNextToken() throws Exception {
        if (lookahead >= msg.length()) {
            return new Token(" ",Type.EOF);
        }
        char ch = msg.charAt(lookahead);
        if(Character.isDigit(ch))
            return new Token(ch+"",Type.NUM);
        if(ch == '+')
            return new Token("+",Type.ADD);
        if(ch == '*')
            return new Token("*",Type.MULT);
        Error("unexpected token");
        return null;
    }
     public void eat(Type t) throws Exception {
        if (t == getNextToken().type) {
            lookahead++;
        } else {
            Error("Error at " + lookahead);
        }
    }
    public void Error(String msg) throws Exception {
        throw new Exception(msg);
    }
    public Expr Expr() throws Exception{
        Token nextToken = getNextToken();
        Expr root = null;
        switch(nextToken.type){
            case NUM: 
                root = E(); 
                eat(Type.EOF);
                break;
            default: Error("unexpected token");
        }
        return root;
    }
    /*
       E -> T E'
    */
    public Expr E() throws Exception{
        Token nextToken = getNextToken();
        Expr root = null,left = null;
        switch(nextToken.type){
            case NUM: 
                left = T(); 
                root = E_prime(left);                
                break;
            default: Error("unexpected token");
        }
        return root;
    }
    /*
       E' -> + T E'
    */
    public Expr E_prime(Expr left) throws Exception{
        Token nextToken = getNextToken();
        Expr root = null, right = null;
        switch(nextToken.type){
            case ADD: 
                eat(Type.ADD); 
                right = T(); 
                root = new AddExpr(left, right);
                root = E_prime(root);
                break;
            case EOF: 
                root = left;
                break;
            default: Error("unexpected token");
        }
        return root;
    }
    /*
       T -> F T'
    */
    public Expr T() throws Exception{
        Token nextToken = getNextToken();
        Expr root = null, left = null;
        switch(nextToken.type){
            case NUM: 
                left = F();
                root = T_prime(left); break;            
            default: Error("unexpected token");
        }
        return root;
    }
    /*
       T' -> * F T'
    */
    public Expr T_prime(Expr left) throws Exception{
        Token nextToken = getNextToken();
        Expr root = null, right = null;
        switch(nextToken.type){
            case MULT: 
                eat(Type.MULT); 
                right = F(); 
                root = new MultExpr(left, right);
                root = T_prime(root);
                break;
            case ADD: 
            case EOF: 
                root = left;
                break;
            default: Error("unexpected token");
        }
        return root;
    }
    /*
       F -> NUM
    */
    public Expr F() throws Exception{
        Token nextToken = getNextToken();
        Expr root = null;
        switch(nextToken.type){
            case NUM: 
                eat(Type.NUM);
                root = new DigitExpr(Integer.parseInt(nextToken.symbol));
                break;
            default: Error("unexpected token");
        }
        return root;
    }
    public static void main(String[] args)throws Exception {
        String msg = "1+2*3*5+6";
        RecursiveSimpleExprADD_MULT parser = new RecursiveSimpleExprADD_MULT(msg);
        Expr root = parser.Expr();
        
        System.out.println("success " + root.prefix());
    }
}
