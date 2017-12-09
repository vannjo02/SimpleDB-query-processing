package simpledb.query;

/**
 * The scan class corresponding to the <i>select</i> relational
 * algebra operator.
 * All methods except next delegate their work to the
 * underlying scan.
 * @author Edward Sciore
 */
public class RenameScan implements Scan {
   private Scan s;
   private String Old, New;
   
   /**
    * Creates a select scan having the specified underlying
    * scan and predicate.
    * @param s the scan of the underlying query
    * @param pred the selection predicate
    */
   public RenameScan(Scan s, String Old, String New) {
      this.s = s;
      this.Old = Old;
      this.New = New;
   }
   
   // Scan methods
   
   public void beforeFirst() {
      s.beforeFirst();
   }
   
   /**
    * Move to the next record satisfying the predicate.
    * The method repeatedly calls next on the underlying scan
    * until a suitable record is found, or the underlying scan
    * contains no more records.
    * @see simpledb.query.Scan#next()
    */
   public boolean next() {
      return s.next();
   }
   
   public void close() {
      s.close();
   }
   
   public Constant getVal(String fldname) {
      return s.getVal(fldname);
   }
   
   public int getInt(String fldname) {
      return s.getInt(fldname);
   }
   
   public String getString(String fldname) {
      return s.getString(fldname);
   }
   
   public boolean hasField(String fldname) {
      return s.hasField(fldname);
   }
   
}
