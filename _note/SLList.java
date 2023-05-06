/**
 * 1. The nested class can be declared as static to save memory 
 *      if it has no need to use any of the instance methods or variables of the outer class.
 * 
 * 2. Sentinel in SLList makes sure that the first node is not null when SLList is empty,
 *      so that we don't have to deal with the special cases.
 * 
 * 3. Three different ways of size implentment.
 */
public class SLList {
  private static class IntNode {
    public int item;
    public IntNode next;

    public int size() {
      if (this.next == null) {
        return 1;
      }
  
      return 1 + this.next.size();
    }

    public IntNode(int item, IntNode next) {
      this.item = item;
      this.next = next;
    }
  }

  private IntNode sentinel;
  private int size;

  public SLList() {
    this.sentinel = new IntNode(-1, null);
    this.size = 0;
  }

  public SLList(int x) {
    this.sentinel = new IntNode(-1, null);
    this.sentinel.next = new IntNode(x, null);
    this.size = 1;
  }

  public void addFirst(int x) {
    this.sentinel.next = new IntNode(x, this.sentinel.next);
    this.size++;
  }

  public int getFirst() {
    return this.sentinel.next.item;
  }

  public void addLast(int x) {
    IntNode p = sentinel;
    while (p.next != null) {
      p = p.next;
    }
    p.next = new IntNode(x, null);
    this.size++;
  }

  public int getLast() {
    IntNode p = sentinel;
    while (p.next != null) {
      p = p.next;
    }
    return p.item;
  }

  private static int size(IntNode p) {
    if (p == null) {
      return 0;
    }

    if (p.next == null) {
      return 1;
    }

    return 1 + size(p);
  }

  public int size() {
    return this.size;
    
    // return size(this.sentinel);
    
    // if (this.sentinel.next == null) {
    //   return 0;
    // }
    // return this.sentinel.next.size();
  }

}
