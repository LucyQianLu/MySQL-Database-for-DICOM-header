/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localdatabasewriter;

/**
 *
 * @author lucy_l
 */
public class cbSqlTypeNSize {
  
    public cbSqlTypeNSize() {
    }
    
    private String sqlDataType;
    private int sqlDataLength;
    
    public void setType(String type) {
      sqlDataType = type;
    }
    public String getType() {
      return sqlDataType;
    }
    public void setLength(int length) {
      sqlDataLength = length;
    }
    public int getLength() {
      return sqlDataLength;
    }  
}
