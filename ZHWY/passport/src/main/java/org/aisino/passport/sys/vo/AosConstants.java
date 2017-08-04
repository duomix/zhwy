package org.aisino.passport.sys.vo;
import org.aisino.core.base.BaseDomain; 
public class AosConstants extends BaseDomain { 
 private static final long serialVersionUID = 1L;
 private String xid;
  public String getXid() {
  	return xid;
  }
  public void setXid(String xid) {
  	this.xid = xid;
  }
 private String id;
 private String pid;
 private String type;
 private String code;
 private String value;
 private String isedit;
 private String remark;
 public void setId(String id){
    this.id = id;
 }
 public String getId(){
    return this.id;
 }
 public void setPid(String pid){
    this.pid = pid;
 }
 public String getPid(){
    return this.pid;
 }
 public void setType(String type){
    this.type = type;
 }
 public String getType(){
    return this.type;
 }
 public void setCode(String code){
    this.code = code;
 }
 public String getCode(){
    return this.code;
 }
 public void setValue(String value){
    this.value = value;
 }
 public String getValue(){
    return this.value;
 }
 public void setIsedit(String isedit){
    this.isedit = isedit;
 }
 public String getIsedit(){
    return this.isedit;
 }
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
} 

