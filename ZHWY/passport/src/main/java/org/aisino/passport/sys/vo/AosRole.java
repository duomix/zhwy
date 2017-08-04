package org.aisino.passport.sys.vo;
import org.aisino.core.base.BaseDomain; 
public class AosRole extends BaseDomain { 
 private static final long serialVersionUID = 1L;
 private String xid;
  public String getXid() {
  	return xid;
  }
  public void setXid(String xid) {
  	this.xid = xid;
  }
 private String roleid;
 private String rolename;
 private String rolecode;
 private String identitytype;
 private String createdate;
 private String createby;
 private String available;
 private String remark;
 private String resid;
 
 public String getResid() {
	return resid;
}
public void setResid(String resid) {
	this.resid = resid;
}
public void setRoleid(String roleid){
    this.roleid = roleid;
 }
 public String getRoleid(){
    return this.roleid;
 }
 public void setRolename(String rolename){
    this.rolename = rolename;
 }
 public String getRolename(){
    return this.rolename;
 }
 public void setRolecode(String rolecode){
    this.rolecode = rolecode;
 }
 public String getRolecode(){
    return this.rolecode;
 }
 public void setIdentitytype(String identitytype){
    this.identitytype = identitytype;
 }
 public String getIdentitytype(){
    return this.identitytype;
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
 public void setRemark(String remark){
    this.remark = remark;
 }
 public String getRemark(){
    return this.remark;
 }
} 

