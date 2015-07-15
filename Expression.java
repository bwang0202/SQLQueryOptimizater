import java.util.*;

public class Expression {
  
  // this is an exhaustive list of expression types
  static public final String [] validTypes = {"plus", "minus", "times", 
    "divided by",  "or", "and", "not", "literal string", "literal float",
    "literal int", "identifier", "unary minus",
    "sum", "avg", "equals", "greater than", "less than"};
  
  // this is an exhaustive list of the unary expression types
  static public final String [] unaryTypes = {"not", "unary minus", "sum", "avg"};
  
  // this is an exhaustive list of the binary expression types
  static public final String [] binaryTypes = {"plus", "minus", "times",
    "divided by", "or", "and", "equals", "greater than", "less than"};
  
  // this is an exhaustive list of the value types
  public final String [] valueTypes = {"literal string", "literal float",
    "literal int", "identifier"};
  
  // this is the type of the expression
  private String myType;
  
  // this is the literal value contained in the expression; only non-null
  // if myType is "literal" or "identifier"
  private String myValue;
  
  // these are the two subexpressions
  private Expression leftSubexpression;
  private Expression rightSubexpression;
  
    // prints the expression
  //TODO: maybe remove redundent parenthses
  public String evalPrint () {
    
    String toMe;
    
    // see if it is a literal type
    for (int i = 0; i < valueTypes.length; i++) {
      if (myType.equals (valueTypes[i])) {
        if (myType.equals("identifier")){
          toMe = myValue.substring(2);
          return toMe;
        }
        toMe = replace(myType);
        toMe += "("+myValue+")";
        return toMe;
      } 
    }
    
    // see if it is a unary type
    for (int i = 0; i < unaryTypes.length; i++) {
      if (myType.equals (unaryTypes[i])) {
        toMe = "(" + replace(myType) + " " + leftSubexpression.evalPrint () + ")";
        return toMe;
      }
    }
    
    // lastly, do a binary type
    for (int i = 0; i < binaryTypes.length; i++) {
      if (myType.equals (binaryTypes[i])) {
        toMe = "(" + leftSubexpression.evalPrint () + " " + replace(myType) + " " + rightSubexpression.evalPrint () + ")";
        return toMe;
      }
    }
    throw new RuntimeException ("got a bad type in the expression when printing");
  }
  
  // prints the expression
  public String print () {
    
    String toMe;
    
    // see if it is a literal type
    for (int i = 0; i < valueTypes.length; i++) {
      if (myType.equals (valueTypes[i])) {
        toMe = myValue;
        return toMe;
      } 
    }
    
    // see if it is a unary type
    for (int i = 0; i < unaryTypes.length; i++) {
      if (myType.equals (unaryTypes[i])) {
        toMe = "(" + myType + " " + leftSubexpression.print () + ")";
        return toMe;
      }
    }
    
    // lastly, do a binary type
    for (int i = 0; i < binaryTypes.length; i++) {
      if (myType.equals (binaryTypes[i])) {
        toMe = "(" + leftSubexpression.print () + " " + myType + " " + rightSubexpression.print () + ")";
        return toMe;
      }
    }
    throw new RuntimeException ("got a bad type in the expression when printing");
  }
  
  // create a new expression of type specified type
  public Expression (String expressionType) {
    
    // verfiy it is a valid expression type
    for (int i = 0; i < validTypes.length; i++) {
      if (expressionType.equals (validTypes[i])) {
        myType = expressionType;
        return;
      }
    }
    
    // it is not valid, so throw an exception
    throw new RuntimeException ("you tried to create an invalid expr type");
  }
  
  public String getType () {
    return myType;
  }
  
  // this returns the value of the expression, if it is a literal (in which
  // case the literal values encoded as a string is returned), or it is an
  // identifier (in which case the name if the identifier is returned)
  public String getValue () {
    for (int i = 0; i < valueTypes.length; i++) {
      if (myType.equals (valueTypes[i])) {
        return myValue;
      }
    } 
    throw new RuntimeException ("you can't get a value for that expr type!");
  }
  
  // this sets the value of the expression, if it is a literal or an 
  // identifier
  public void setValue (String toMe) {
    for (int i = 0; i < valueTypes.length; i++) {
      if (myType.equals (valueTypes[i])) {
        myValue = toMe;
        return;
      }
    } 
    throw new RuntimeException ("you can't set a value for that expr type!");
  }
  
  // this gets the subexpression, which is only possible if this is a 
  // unary operation (such as "unary minus" or "not")
  public Expression getSubexpression () {
    
    // verfiy it is a valid expression type
    for (int i = 0; i < unaryTypes.length; i++) {
      if (myType.equals (unaryTypes[i])) {
        return leftSubexpression;
      }
    }
    
    // it is not valid, so throw an exception
    throw new RuntimeException ("you can't get the subexpression of an " +
                                "expression that is not unary!");
  }
  
  // this sets the subexpression, which is only possible if this is a 
  // unary operation (such as "unary minus" or "not")
  public void setSubexpression (Expression newChild) {
    
    // verfiy it is a valid expression type
    for (int i = 0; i < unaryTypes.length; i++) {
      if (myType.equals (unaryTypes[i])) {
        leftSubexpression = newChild;
        return;
      }
    }
    
    // it is not valid, so throw an exception
    throw new RuntimeException ("you can't set the subexpression of an " +
                                "expression that is not unary!");
  }
  
  // this gets either the left or the right subexpression, which is only 
  // possible if this is a binary operation... whichOne should either be
  // the string "left" or the string "right"
  public Expression getSubexpression (String whichOne) {
    
    // verfiy it is a valid expression type
    for (int i = 0; i < binaryTypes.length; i++) {
      if (myType.equals (binaryTypes[i])) {
        if (whichOne.equals ("left"))
          return leftSubexpression;
        else if (whichOne.equals ("right"))
          return rightSubexpression;
        else
          throw new RuntimeException ("whichOne must be left or right");
      }
    }
    
    // it is not valid, so throw an exception
    throw new RuntimeException ("you can't get the l/r subexpression of " +
                                "an expression that is not binry!");
  }
  
  // this sets the left and the right subexpression
  public void setSubexpression (Expression left, Expression right) {
    
    // verfiy it is a valid expression type
    for (int i = 0; i < binaryTypes.length; i++) {
      if (myType.equals (binaryTypes[i])) {
        leftSubexpression = left;
        rightSubexpression = right;
        return;
      }
    }
    
    // it is not valid, so throw an exception
    throw new RuntimeException ("you can't set the l/r subexpression of " +
                                "an expression that is not binry!");
  }
  
  public boolean isLiteral() {
    if(myType.equals("literal string") || myType.equals("literal float") || myType.equals("literal int")) {
      return true;
    }
    return false;
  }
  public boolean isIdentifier() {
    if (myType.equals("identifier")) {
      return true;
    }
    return false;
  }
  public boolean isUnary() {
    for (int i = 0; i < unaryTypes.length; i++) {
      if (myType.equals (unaryTypes[i])) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isCompare() {
    if(myType.equals("equals") || myType.equals("greater than") || myType.equals("less than")) {
      return true;
    }
    return false;
  }
  public boolean containsAggg(){
    if(myType.equals("sum") || myType.equals("avg")) {
      return true;
    }
    if (myType.equals("minus") || myType.equals("times") || myType.equals("divided by") || myType.equals("times")) {
      return this.leftSubexpression.containsAggg() || this.rightSubexpression.containsAggg();
    }  
    return false;
  }
  
  //author bojun
  //return real type of this expression
  //does not guarentee validness of expression
  //could only be called on "literal string/int/float" "identifier" "sum/avg" "+-*/"
  public String getMyType(Interpreter i) {
    if (isLiteral()) {
      return myType;
    }
    //identifier case
    if (isIdentifier()) {
      return i.typeIdentifier(this.myValue);
    }
    //corner cases
    if (myType.equals("sum") || myType.equals("avg")) {
      return this.leftSubexpression.getMyType(i);
    }
    //more corner cases
    if (myType.equals("plus") ||myType.equals("minus") || myType.equals("times") || myType.equals("divided by")) {
      if (this.leftSubexpression.getMyType(i).equals("literal string") || this.rightSubexpression.getMyType(i).equals("literal string")) {
        return "literal string";
      }
      if (this.leftSubexpression.getMyType(i).equals("literal float") || this.rightSubexpression.getMyType(i).equals("literal float")) {
        return "literal float";
      }
      return "literal int";
    }
    return "no such type";

  }
  //return if this expression is valid
  public boolean isValidExpr(Interpreter i) {
    //base case
    if (isLiteral()) {
      return true;
    }
    if (isIdentifier()) {
      return i.checkIdentifier(this.myValue);
    }
    //unary case 1
    if (myType.equals("sum") || myType.equals("avg")) {
      return this.leftSubexpression.isValidExpr(i) && 
        (this.leftSubexpression.getMyType(i).equals("literal float") || this.leftSubexpression.getMyType(i).equals("literal int"));
    }
    //unary case 2
    if (myType.equals("not") || myType.equals("unary minus")) {
      return this.leftSubexpression.isValidExpr(i);
    }
    //binary case 1
    if (myType.equals("plus")) {
      return this.leftSubexpression.isValidExpr(i) && this.rightSubexpression.isValidExpr(i);
    }
    //binary case 2
    if (myType.equals("or") || myType.equals("and")) {
      return this.leftSubexpression.isValidExpr(i) && this.rightSubexpression.isValidExpr(i);
    }
    //"minus", "times","divided by",
    if (myType.equals("minus") || myType.equals("times") || myType.equals("divided by")) {
      if (this.leftSubexpression.getMyType(i).equals("literal string") || this.rightSubexpression.getMyType(i).equals("literal string")) {
        System.out.println(this.print() + " can not minus, times, divide a string");
        return false;
      }
      return this.leftSubexpression.isValidExpr(i) && this.rightSubexpression.isValidExpr(i);
    }
    //"equals", "greater than", "less than"
    //ASSUMPTION: allow string comparasion.
    if (myType.equals("equals") || myType.equals("greater than") || myType.equals("less than")) {
      if (this.leftSubexpression.getMyType(i).equals("literal string") && this.rightSubexpression.getMyType(i).equals("literal string")) {
        return this.leftSubexpression.isValidExpr(i) && this.rightSubexpression.isValidExpr(i);
      }
      if (this.leftSubexpression.getMyType(i).equals("literal string") || this.rightSubexpression.getMyType(i).equals("literal string")) {

        System.out.println(this.print() + " can not compare a string with int or float");
        return false;
      }
      return this.leftSubexpression.isValidExpr(i) && this.rightSubexpression.isValidExpr(i);
    }
    return false;
  }
  
  public Set<Expression> allIdentifiers() {
    Set<Expression> result = new HashSet<Expression>();
    if (isIdentifier()) {
      result.add(this);
      return result;
    }
    if (myType.equals("times") || myType.equals("plus") || myType.equals("minus") || myType.equals("divided by")) {
      result.addAll(this.leftSubexpression.allIdentifiers());
      result.addAll(this.rightSubexpression.allIdentifiers());
      return result;
    }
    return result; //empty set
  }
  
  public String replace(String type) {
//    "plus", "minus", "times",
//    "divided by", "or", "and", "equals", "greater than", "less than"
    if (type.equals("plus")) return "+";
    if (type.equals("minus")) return "-";
    if (type.equals("times")) return "*";
    if (type.equals("divided by")) return "/";
    if (type.equals("or")) return "||";
    if (type.equals("and")) return "&&";
    if (type.equals("equals")) return "==";
    if (type.equals("greater than")) return ">";
    if (type.equals("less than")) return "<";
    //"literal string", "literal float",
   // "literal int",.
    if (type.equals("literal string")) return "Str";
    if (type.equals("literal float")) return "Float";
    if (type.equals("literal int")) return "Float";
    if (type.equals("Float")) return "Float";
    if (type.equals("Int")) return "Float";
    if (type.equals("Str")) return "Str";
    throw new RuntimeException("replace of " + type +" was not implemented");

  }
  
  public List<Entry> allIdentifiers(Interpreter i) {
    ArrayList<Entry> result = new ArrayList<Entry>();
    if (isIdentifier()) {
      //System.out.println("[0]is identifier " + this.print());
      //System.out.println("[1]"+this.getMyType(i));
      //System.out.println("[2]"+this.evalPrint());
      result.add(new Entry(this.getMyType(i), this.evalPrint()));
      //System.out.println("[allIdentifiers]"+result);
      return result;
    }
    //recursive case
    if (leftSubexpression != null) {
      result.addAll(leftSubexpression.allIdentifiers(i));
    }
    if (rightSubexpression != null) {
      result.addAll(rightSubexpression.allIdentifiers(i));
    }
    return result;
    
  }
  
  public String getAgggType() {
    if (myType.equals("sum")) return "sum";
    if (myType.equals("avg")) return "avg";
    throw new RuntimeException("aggg type " + myType +" is not aggregate");
  }
  public String getAgggEvalPrint() {
    return this.leftSubexpression.evalPrint();
  }
}

