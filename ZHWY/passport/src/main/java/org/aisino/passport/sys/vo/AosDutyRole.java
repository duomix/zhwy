package org.aisino.passport.sys.vo;
import org.aisino.core.base.BaseDomain; 
public class AosDutyRole extends BaseDomain { 
 private static final long serialVersionUID = 1L;
 private String xid;
  public String getXid() {
  	return xid;
  }
  public void setXid(String xid) {
  	this.xid = xid;
  }
 private String id;
 private String dutyid;
 private String roleid;
 private String rolename;
 private String createdate;
 private String createby;
 private String available;
 public void setId(String id){
    this.id = id;
 }
 public String getId(){
    return this.id;
 }
 public void setDutyid(String dutyid){
    this.dutyid = dutyid;
 }
 public String getDutyid(){
    return this.dutyid;
 }
 public void setRoleid(String roleid){
    this.roleid = roleid;
 }
 public String getRoleid(){
    return this.roleid;
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
public String getRolename() {
	return rolename;
}
public void setRolename(String rolename) {
	this.rolename = rolename;
}
 
} 

