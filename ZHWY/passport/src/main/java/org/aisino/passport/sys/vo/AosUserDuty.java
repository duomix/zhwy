package org.aisino.passport.sys.vo;
import org.aisino.core.base.BaseDomain; 
public class AosUserDuty extends BaseDomain { 
 private static final long serialVersionUID = 1L;
 private String xid;
  public String getXid() {
  	return xid;
  }
  public void setXid(String xid) {
  	this.xid = xid;
  }
 private String id;
 private String userid;
 private String dutyid;
 private String dutyname;
 private String createdate;
 private String createby;
 private String available;
 private String ismain;
 
 
 public String getDutyname() {
	return dutyname;
}
public void setDutyname(String dutyname) {
	this.dutyname = dutyname;
}
public void setId(String id){
    this.id = id;
 }
 public String getId(){
    return this.id;
 }
 public void setUserid(String userid){
    this.userid = userid;
 }
 public String getUserid(){
    return this.userid;
 }
 public void setDutyid(String dutyid){
    this.dutyid = dutyid;
 }
 public String getDutyid(){
    return this.dutyid;
 }
 public void setCreatedate(String createdate){
    this.createdate = createdate;
 }
 public String getCreatedate(){
    return this.createdate;
 }
 public void setCreateby(String createby){
    this.createby = createby;
 }
 public String getCreateby(){
    return this.createby;
 }
 public void setAvailable(String available){
    this.available = available;
 }
 public String getAvailable(){
    return this.available;
 }
 public void setIsmain(String ismain){
    this.ismain = ismain;
 }
 public String getIsmain(){
    return this.ismain;
 }
} 

