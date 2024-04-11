package Teste1;
import java.util.ArrayList;
import java.util.List;

public class check {
    public List<Boolean> checkDoubleList(List<String> strList) throws InvalidArgumentException{
        List<Boolean> list = new ArrayList<Boolean>();
        if(strList.size() == 0 || strList == null){
            throw new InvalidArgumentException();
        }
        for(String s : strList){
            try{
                Double.parseDouble(s); // This will throw a NumberFormatException if parsing fails
                list.add(true); // Parsing succeeded, add true to the list
            }catch(NumberFormatException e){
                list.add(false); // Parsing failed, add false to the list
            }
        }
        return list;
    }

}
