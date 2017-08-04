package org.aisino.passport.sys.vo;
import org.aisino.core.base.BaseDomain;  
public class AosRoleRes extends BaseDomain { 
 private static final long serialVersionUID = 1L;
 private String xid;
  public String getXid() {
  	return xid;
  }
  public void setXid(String xid) {
  	this.xid = xid;
  }
 private String roleid;
 private String resid;
 private String createdate;
 private String createby;
 private String available;
 private String id;
 public void setRoleid(String roleid){
    this.roleid = roleid;
 }
 public String getRoleid(){
    return this.roleid;
 }
 public void setResid(String resid){
    this.resid = resid;
 }
 public String getResid(){
    return this.resid;
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
 public void setId(String id){
    this.id = id;
 }
 public String getId(){
    return this.id;
 }
} 

