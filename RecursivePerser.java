



package recursive.perser;

import java.util.logging.Level;
import java.util.logging.Logger;




public class RecursivePerser {

    private String message;
    private int index;
    public RecursivePerser (String mes){
        mes=this.message;
        
        
    }
    public void  Eat(char ch)throws Exception {
        if(ch==message.charAt(index)){
            index++;
        }
        else{throw new Exception("invalid token "); }
        
    }
    public void A(){
        if(index==message.length())
            return ;
            char ch=message.charAt(index);
            
            switch(ch){
                case 'a':
        {
            try {
                Eat('a');
            } catch (Exception ex) {
                Logger.getLogger(RecursivePerser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                    A();
                    break;
                default:
                    throw new Exception("error");
                    
                
                
            } 
        }
              public static void main(String[] args)  {                 
                RecursivePerser p=new RecursivePerser("aaaaaa");
        try {
            p.A();
        } catch (Exception ex) {
            Logger.getLogger(RecursivePerser.class.getName()).log(Level.SEVERE, null, ex);
        }
                



    }
        
    
    
    
    
    
    
    
  
    
}
