// File generated by the BNF Converter (bnfc 2.9.4.1).

package org.syntax.stella.Absyn;

public class TypeRecord  extends Type {
  public final ListRecordFieldType listrecordfieldtype_;
  public int line_num, col_num, offset;
  public TypeRecord(ListRecordFieldType p1) { listrecordfieldtype_ = p1; }

  public <R,A> R accept(Visitor<R,A> v, A arg) { return v.visit(this, arg); }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof TypeRecord) {
      TypeRecord x = (TypeRecord)o;
      return this.listrecordfieldtype_.equals(x.listrecordfieldtype_);
    }
    return false;
  }

  public int hashCode() {
    return this.listrecordfieldtype_.hashCode();
  }


}