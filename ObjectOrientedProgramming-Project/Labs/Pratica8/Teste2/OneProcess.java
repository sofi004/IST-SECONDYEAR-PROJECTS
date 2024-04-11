package Teste2;

import Teste1.InvalidArgumentException;

public class OneProcess {
    public int[] otherProcess(int[] array1, int[] array2) throws CannotProcessException{
        if(array1.length == array2.length){
            throw new CannotProcessException("Têm tamanho diferente");
        }
        else if(array1.length == 0){
            throw new CannotProcessException("O tamanho é zero");
        }

        int[] finalList = new int[array1.length];
        for(int i = 0; i <= array1.length; i++){
            try{
                finalList[i] = process(array1[i], array2[i]);
            }catch(InvalidArgumentException e){
                finalList[i] = -1;
            }
        }
        return finalList;
    }
}
