
package recognizer;


public class Recognizer {
public static boolean belongs(String token){
    
    int state=1;
    int i=0;
    while(i<token.length()){
        char ch=token.charAt(i);
        switch(state){
            case 1:
                if(ch=='a')state=2;
                else if(ch=='b')state=1;
                else return false;
                break;
            case 2:
                if(ch=='a')state=3;
                else if (ch=='b')state=1;
                else return false;
                break;
            case 3:
                if(ch=='a')state=3;
                else if (ch == 'b')state=3;
                else return false ;
                 break;
                 
                
                
        }
        i=i+1;
    }
    if(state==3)return true;
    else return false ;
    
    
    
}
  
    public static void main(String[] args) {
       
        boolean result=belongs("abaabbba");
        System.out.println(result);
        
        
        
        
        
    }
    
}
