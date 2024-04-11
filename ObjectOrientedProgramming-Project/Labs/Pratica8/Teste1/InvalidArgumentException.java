package Teste1;
public class InvalidArgumentException extends Exception{
    private static final String ERROR_MESSAGE = "Lista vazia ou nula";

    public InvalidArgumentException(){
        super(ERROR_MESSAGE);
    }
    
}
