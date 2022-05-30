/*
Mohamed Ayman Ahmed Mohamed     ( CS-5 - SN:3506 )
Amr Ahmed Mohamed Elgendy       ( CS-4 - SN:3488 )
Sara Walid Mohamed Abdelwahed   ( CS-4 - SN:3463 )
*/

public class SimpleParser {
    private final String msg;
    private int lookahead;

    public static class Token {
        String symbol;
        Type type;

        public Token(String symbol, Type type) {
            this.symbol = symbol;
            this.type = type;
        }
    }

    public enum Type {NUM, ADD, SUB, POLY, VAR, EOF}

    public SimpleParser(String msg) {
        this.msg = msg;
        System.out.print("Expression : " + msg);
        lookahead = 0;
    }

    public Token getNextToken() throws Exception {
        if (lookahead >= msg.length())
            return new Token("", Type.EOF);

        char ch = msg.charAt(lookahead);
        if (ch >= '0' && ch <= '9')
            return new Token(ch + "", Type.NUM);
        if (ch == '+')
            return new Token("+", Type.ADD);
        if (ch == '-')
            return new Token("-", Type.SUB);
        if (ch == '^')
            return new Token("^", Type.POLY);
        if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
            return new Token(ch + "", Type.VAR);
        Error("Unexpected Token");
        return null;
    }

    public void Error(String msg) throws Exception {
        throw new Exception(msg);
    }

    public void Expr() throws Exception {
        Token nt = getNextToken();
        switch (nt.type) {
            case NUM:
            case VAR:
            case SUB:
                E();
                eat(Type.EOF);
                break;
            default:
                Error("Unexpected Token");
        }
    }

    public void E() throws Exception {
        Token nt = getNextToken();
        switch (nt.type) {
            case NUM:
            case VAR:
            case SUB:
                T();
                E_prime();
                break;
            default:
                Error("Unexpected Token");
        }
    }

    public void E_prime() throws Exception {
        Token nt = getNextToken();
        switch (nt.type) {
            case ADD:
                eat(Type.ADD);
                E();
                break;
            case SUB:
                eat(Type.SUB);
                E();
                break;
            case EOF:
                break;
            default:
                Error("Unexpected Token");
        }
    }

    public void T() throws Exception {
        Token nt = getNextToken();
        switch (nt.type) {
            case NUM:
                D();
                T_prime();
                break;
            case VAR:
                F();
                break;
            case SUB:
                eat(Type.SUB);
                T();
                break;
            default:
                Error("Unexpected Token");
        }
    }

    public void T_prime() throws Exception {
        Token nt = getNextToken();
        switch (nt.type) {
            case VAR:
                F();
                break;
            case ADD:
            case SUB:
            case EOF:
                break;
            default:
                Error("Unexpected Token");
        }
    }

    public void F() throws Exception {
        Token nt = getNextToken();
        switch (nt.type) {
            case VAR:
                V();
                F_prime();
                break;
            default:
                Error("Unexpected Token");
        }
    }

    public void F_prime() throws Exception {
        Token nt = getNextToken();
        switch (nt.type) {
            case POLY:
                eat(Type.POLY);
                D();
                break;
            case ADD:
            case SUB:
            case EOF:
                break;
            default:
                Error("Unexpected Token");
        }
    }

    public void D() throws Exception {
        Token nt = getNextToken();
        switch (nt.type) {
            case NUM:
                eat(Type.NUM);
                break;
            case SUB:
                eat(Type.SUB);
                D();
                break;
            default:
                Error("Unexpected Token");
        }
    }

    public void V() throws Exception {
        Token nt = getNextToken();
        switch (nt.type) {
            case VAR:
                eat(Type.VAR);
                break;
            default:
                Error("Unexpected Token");
        }
    }

    public void eat(Type type) throws Exception {
        Token tok = getNextToken();
        if (tok.type == type)
            lookahead++;
        else
            Error("Unexpected Token");
    }


    public static void main(String[] args) throws Exception {

        String[] arr = {
                //      Valid terms
                "x", "3", "3x^2", "-2x^3",

                //      Valid Polynomial Equations
                "2+3x-2x^2", "1+2x^2-4x^5", "3",

                //      Negative Polynomial [BONUS]
                "x^-2",

                //      Big One
                "-2-4z^-7+9R^-2+4-6P^-5+x-y-3e+2-w^-3+2i-4u^-2-3-4-5-2a-s-4d-f-5G-9H^-4+9"
        };

        for (String msg : arr) {
            SimpleParser sp = new SimpleParser(msg);
            sp.Expr();
            System.out.println(" : Success");
        }

    }
}
