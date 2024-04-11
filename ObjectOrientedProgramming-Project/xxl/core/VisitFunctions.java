package xxl.core;


public class VisitFunctions implements Visitor{
    public boolean visit(Content content){
        return false;
    }

    public boolean visit(Add add){
        return true;
    }

    public boolean visit(Sub sub){
        return true;
    }

    public boolean visit(Div div){
        return true;
    }

    public boolean visit(Mul mul){
        return true;
    }

    public boolean visit(Concat concat){
        return true;
    }

    public boolean visit(Coalesce coalesce){
        return true;
    }

    public boolean visit(Average average){
        return true;
    }

    public boolean visit(Product product){
        return true;
    }

    public boolean visit(Number number){
        return false;
    }

    public boolean visit(Character character){
        return false;
    }

    public boolean visit(Null nula){
        return false;
    }

    public boolean visit(Literal literal){
        return false;
    }

    public boolean visit(Function function){
        return true;
    }

    public boolean visit(BinaryFunction binaryFunction){
        return true;
    }

    public boolean visit(IntervalFunction intervalFunction){
        return true;
    }

    public boolean visit(Reference reference){
        return false;
    }
}