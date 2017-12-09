package simpledb.query;

import simpledb.record.Schema;

/** The Plan class corresponding to the <i>select</i>
  * relational algebra operator.
  * @author Edward Sciore
  */
public class RenamePlan implements Plan {
   private Plan p;
   private String Old, New;
   private Schema schema = new Schema();
   
   /**
    * Creates a new select node in the query tree,
    * having the specified subquery and predicate.
    * @param p the subquery
    * @param pred the predicate
    */
   public RenamePlan(Plan p, String Old, String New) {
      this.p = p;
      schema.add(Old,p.schema());
      schema.add(New,p.schema());
      
   }
   
   /**
    * Creates a select scan for this query.
    * @see simpledb.query.Plan#open()
    */
   public Scan open() {
      Scan s = p.open();
      return new ProjectScan(s, schema.fields());
   }
   
   /**
    * Estimates the number of block accesses in the selection,
    * which is the same as in the underlying query.
    * @see simpledb.query.Plan#blocksAccessed()
    */
   public int blocksAccessed() {
      return p.blocksAccessed();
   }
   
   /**
    * Estimates the number of output records in the selection,
    * which is determined by the 
    * reduction factor of the predicate.
    * @see simpledb.query.Plan#recordsOutput()
    */
   public int recordsOutput() {
      return p.recordsOutput();
   }
   
   /**
    * Estimates the number of distinct field values
    * in the projection.
    * If the predicate contains a term equating the specified 
    * field to a constant, then this value will be 1.
    * Otherwise, it will be the number of the distinct values
    * in the underlying query 
    * (but not more than the size of the output table).
    * @see simpledb.query.Plan#distinctValues(java.lang.String)
    */
   public int distinctValues(String fldname) {
      return p.distinctValues(fldname);
   }
   
   /**
    * Returns the schema of the selection,
    * which is the same as in the underlying query.
    * @see simpledb.query.Plan#schema()
    */
   public Schema schema() {
      return p.schema();
   }
}
