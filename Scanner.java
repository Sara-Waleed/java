
package scanner;
 enum token{ NUM, WORD, EOF, ERROR }  
public class Scanner {

    private int lookahead=0;
    private String msg;
     public Scanner(String msg){
         this.msg=msg;
         
     }
   public token getnexttoken(){
       int state=1;
       while(lookahead<msg.length()){
           char ch=msg.charAt(lookahead);
           switch(state){
               case 1:
                   if(ch==' ') state=1;
                   else if ( ch >= '1'&&ch <=9) state=2;
                   else if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'))state=3;
                   else state=4;
                   break;
                   
               case 2:
                     
                    if ( ch >= '0'&&ch <=9) state=2;
                    else if(ch==' ') return token.NUM;
                   else state=4;
                   break;
                   
               case 3:
                   if((ch>='0'&&ch<='9')||(ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'))state=3;
                   else if(ch==' ') return token.WORD;
                   else state=4;
                   break;
               case 4:
                   return token.ERROR;
                   
                   
           }
           lookahead++;
           
       }
       return token.EOF;
   }
    
    
    public static void main(String[] args) {


       Scanner s=new Scanner("My age is 21");
       token token;
       while((token=s.getnexttoken())!=token.EOF){
           System.out.println(token);
                   }

    }
    
}
