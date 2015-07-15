public class Entry {

  public String type;
  public String name;
  
  public Entry(String s1, String s2) {
    type = s1;
    name = s2;
  }
  public String toString() {
    return type + " <== " + name;
  }
}
