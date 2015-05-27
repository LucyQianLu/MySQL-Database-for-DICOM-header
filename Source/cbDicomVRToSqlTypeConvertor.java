/*=========================================================================
 Program: DICOM to MySQL Database
 Copyright (c) 2015 Qian Lu
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:

 * Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.

 * Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.

 * Neither the name of David Gobbi nor the names of any contributors
 may be used to endorse or promote products derived from this software
 without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
=========================================================================*/
package localdatabasewriter;

import org.dcm4che2.data.Tag;
import org.dcm4che2.data.VR;
import org.dcm4che2.data.VRMap;

/**
 * Module:  cbDicomVRToSqlTypeConvertor.java
 * Convert DICOM VR to related SQL Type for preparing DICOM header database.
 */

public class cbDicomVRToSqlTypeConvertor {
   /*In DICOM, the value multiplicity (VM) of a data element specified the number of values
     that can be encoded in the value field of that data element. For character strings, 
     backslash is used as a delimiter between character string values that are of fixed length 
     as well as variable length. see PS3.6 in DICOM Standard for detail. For VM value equal to 
     1-n, 3-3n, 7-7n,etc., we use BLOB for data type.*/  
  public cbSqlTypeNSize  Convert(VR vr, int vm) {
    cbSqlTypeNSize ts = new cbSqlTypeNSize();
    String sqlType = "";
    // if data length equals to -1, then use SQL default data length. 
    int len = -1;
    if (vm <= 0) {
      System.out.println("Invalid VM value!");
      return null;
    }
    if (vr == VR.OB || vr == VR.OF || vr == VR.OW || vr == VR.SQ) {
      System.out.println("Invalid VR type! Currently not support OB, OF, OW or SQ type,"
              + "since OB, OF, and OW objects normally do not carry searchable information!");
      return null;
    }
    if (vm == Integer.MAX_VALUE) {
      sqlType = "BLOB";
    }
    if (vr == VR.AE) {
      sqlType = "VARCHAR";
      len = 16 * vm;
    }
    if (vr == VR.AS) {
      sqlType = "CHAR";
      len = 4 * vm;
    }
    if (vr == VR.AT) {
      sqlType = "CHAR";
      len = 4 * vm;
    }
    if (vr == VR.CS) {
      sqlType = "VARCHAR";
      len = 16 * vm;
    }
    if (vr == VR.DA) {
      sqlType = "DATE";
    }
    if (vr == VR.DS) {
      sqlType = "Decimal";
      len = 16 * vm;
    }
    if (vr == VR.DT) {
      sqlType = "DATETIME";
    }
    if (vr == VR.FD) {
      sqlType = "DOUBLE";
    }
    if (vr == VR.FL) {
      sqlType = "FLOAT";
    }
    if (vr == VR.IS) {
      sqlType = "INT";
    }
    if (vr == VR.LO) {
      sqlType = "VARCHAR";
      len = 64 * vm;
    }
    if (vr == VR.LT) {
      sqlType = "LONGBLOB";
    }
    if (vr == VR.PN) {
      // DICOM standard allow 64 chars per component group, and up to 3 groups of components.
      // So calculate to bytes: 64*3*2 =  384  
      sqlType = "VARCHAR";
      len = 384 * vm;
    }
    if (vr == VR.SH) {
      sqlType = "VARCHAR";// DICOM standard allow 16 chars, in some charactor, 1 char equal to 2 byte. 
      len = 32 * vm;// DICOM standard allow 16 chars, in some charactor, 1 char equal to 2 byte. 
    }
    if (vr == VR.SL) {
      sqlType = "INT";
    }
    if (vr == VR.SS) {
      sqlType = "SMALLINT";
    }
    if (vr == VR.ST) {
      sqlType = "BLOB";
    }
    if (vr == VR.TM) {
      sqlType = "DATATIME";
    }
    if (vr == VR.UI) {
      sqlType = "VARCHAR";
      len = 64 * vm;
    }
    if (vr == VR.UL) {
      sqlType = "INT";
    }
    if (vr == VR.UN) {
      sqlType = "LONGBLOB";
    }
    if (vr == VR.US) {
      sqlType = "SMALLINT";
    }
    if (vr == VR.UT) {
      sqlType = "LONGBLOB";
    }

    ts.setLength(len);
    ts.setType(sqlType);

    return ts;
  }

  // sometimes, you want string only database, and here is the convertor you can use. 
  public static cbSqlTypeNSize ConvertToStringOnly(VR vr, int vm) {
    cbSqlTypeNSize ts = new cbSqlTypeNSize();
    String sqlType = "";
    int len = -1;
    if (vm <= 0) {
      System.out.println("Invalid VM value!");
      return null;
    }
    if (vr == VR.OB || vr == VR.OF || vr == VR.OW || vr == VR.SQ) {
      System.out.println("Invalid VR type! Currently not support OB, OF, OW or SQ type,"
              + "since OB, OF, and OW objects normally do not carry searchable information!");
      return null;
    }
    if (vm == Integer.MAX_VALUE) {
      sqlType = "BLOB";
    }
    if (vr == VR.AE) {
      sqlType = "VARCHAR";
      len = 16 * vm;
    }
    if (vr == VR.AS) {
      sqlType = "CHAR";
      len = 4 * vm;
    }
    if (vr == VR.AT) {
      sqlType = "CHAR";
      len = 4 * vm;
    }
    if (vr == VR.CS) {
      sqlType = "VARCHAR";
      len = 16 * vm;
    }
    if (vr == VR.DA) {
      sqlType = "DATE";
    }
    if (vr == VR.DS) {
      sqlType = "VARCHAR";
      len = 16 * vm;
    }
    if (vr == VR.DT) {
      sqlType = "DATETIME";
    }
    if (vr == VR.FD) {
      // double can store 8 bytes maximum, wich is 18446744073709551616L in number, less than 24 characters. 
      sqlType = "VARCHAR";
      len = 24 * vm;
    }
    if (vr == VR.FL) {
      // double can store 4 bytes maximum, wich is 4294967296 in number, less than 12 characters.
      sqlType = "VARCHAR";
      len = 12 * vm;
    }
    if (vr == VR.IS) {
      // int can store 4 bytes maximum, wich is 4294967296 in number, less than 12 characters. 
      sqlType = "VARCHAR";
      len = 12 * vm;
    }
    if (vr == VR.LO) {
      // DICOM standard allow 64 chars. 
      sqlType = "VARCHAR";
      len = 64 * vm;
    }
    if (vr == VR.LT) {
      sqlType = "LONGTEXT";
    }
    if (vr == VR.PN) {
      // DICOM standard allow 64 chars per component group, and up to 3 groups of components.
      // So calculate to bytes: 64*3*2 =  384  
      sqlType = "VARCHAR";
      len = 384 * vm;
    }
    if (vr == VR.SH) {
      sqlType = "VARCHAR";// DICOM standard allow 16 chars, in some charactor, 1 char equal to 2 byte. 
      len = 32 * vm;// DICOM standard allow 16 chars, in some charactor, 1 char equal to 2 byte. 
    }
    if (vr == VR.SL) {
      sqlType = "VARCHAR";
      len = 12 * vm;
    }
    if (vr == VR.SS) {
      // smallint can store 2 bytes maximum, wich is 65535 in number, less than 6 characters. 
      sqlType = "VARCHAR";
      len = 6 * vm;
    }
    if (vr == VR.ST) {
      sqlType = "TEXT";
    }
    if (vr == VR.TM) {
      sqlType = "DATATIME";
    }
    if (vr == VR.UI) {
      sqlType = "VARCHAR";
      len = 64 * vm;
    }
    if (vr == VR.UL) {
      sqlType = "VARCHAR";
      len = 64 * vm;
    }
    if (vr == VR.UN) {
      sqlType = "LONGTEXT";
    }
    if (vr == VR.US) {
      sqlType = "VARCHAR";
      len = 6 * vm;
    }
    if (vr == VR.UT) {
      sqlType = "LONGTEXT";
    }

    ts.setLength(len);
    ts.setType(sqlType);

    return ts;
  }   
}