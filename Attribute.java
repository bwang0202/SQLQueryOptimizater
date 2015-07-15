
class Attribute {
 
  private String name;
  private String attType;
  
  public String getName () {
    return name; 
  }
  
  public String getType () {
    return attType;
  }
  
  public Attribute (String inType, String inName) {
    name = inName;
    attType = inType;
  }
  
}