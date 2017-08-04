package org.aisino.passport.sys.vo;
import org.aisino.core.base.BaseDomain; 
public class AosUser extends BaseDomain { 
 private static final long serialVersionUID = 1L;
 private String xid;
  public String getXid() {
  	return xid;
  }
  public void setXid(String xid) {
  	this.xid = xid;
  }
 private String userid;
 private String username;
 private String user_img;
 private String account;
 private String password;
 private String status;
 private String identitytype;
 private String lastlogintime;
 private String createdate;
 private String createby;
 private String issys;
 private String available;
 private String dutyid_main;//用户主职务id   只能一个
 private String dutyname_main;//用户主职务名称
 private String dutyid_assis;//用户副职务 可以有多个 以逗号分
 private String deptid;//部门id
 private String deptname;//部门名称
 
 
 public String getDeptname() {
	return deptname;
}
public void setDeptname(String deptname) {
	this.deptname = deptname;
}
public String getDeptid() {
	return deptid;
}
public void setDeptid(String deptid) {
	this.deptid = deptid;
}
public String getDutyname_main() {
	return dutyname_main;
}
public void setDutyname_main(String dutyname_main) {
	this.dutyname_main = dutyname_main;
}
public String getDutyid_main() {
	return dutyid_main;
}
public void setDutyid_main(String dutyid_main) {
	this.dutyid_main = dutyid_main;
}
public String getDutyid_assis() {
	return dutyid_assis;
}
public void setDutyid_assis(String dutyid_assis) {
	this.dutyid_assis = dutyid_assis;
}
public void setUserid(String userid){
    this.userid = userid;
 }
 public String getUserid(){
    return this.userid;
 }
 public void setUsername(String username){
    this.username = username;
 }
 public String getUsername(){
    return this.username;
 }
 public void setUser_img(String user_img){
    this.user_img = user_img;
 }
 public String getUser_img(){
    return this.user_img;
 }
 public void setAccount(String account){
    this.account = account;
 }
 public String getAccount(){
    return this.account;
 }
 public void setPassword(String password){
    this.password = password;
 }
 public String getPassword(){
    return this.password;
 }
 public void setStatus(String status){
    this.status = status;
 }
 public String getStatus(){
    return this.status;
 }
 public void setIdentitytype(String identitytype){
    this.identitytype = identitytype;
 }
 public String getIdentitytype(){
    return this.identitytype;
 }
 public void setLastlogintime(String lastlogintime){
    this.lastlogintime = lastlogintime;
 }
 public String getLastlogintime(){
    return this.lastlogintime;
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
 public void setIssys(String issys){
    this.issys = issys;
 }
 public String getIssys(){
    return this.issys;
 }
 public void setAvailable(String available){
    this.available = available;
 }
 public String getAvailable(){
    return this.available;
 }
} 

