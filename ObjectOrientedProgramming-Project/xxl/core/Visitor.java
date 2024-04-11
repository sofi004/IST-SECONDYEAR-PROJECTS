package xxl.core;

public interface Visitor {
    public boolean visit(Content content);
    public boolean visit(Add add);
    public boolean visit(Sub sub);
    public boolean visit(Div div);
    public boolean visit(Mul mul);
    public boolean visit(Concat concat);
    public boolean visit(Coalesce coalesce);
    public boolean visit(Average average);
    public boolean visit(Product product);
    public boolean visit(Number number);
    public boolean visit(Character character);
    public boolean visit(Null nula);
    public boolean visit(Literal literal);
    public boolean visit(Function function);
    public boolean visit(BinaryFunction binaryFunction);
    public boolean visit(IntervalFunction intervalFunction);
    public boolean visit(Reference reference);
}
