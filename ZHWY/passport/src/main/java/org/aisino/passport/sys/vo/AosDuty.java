package org.aisino.passport.sys.vo;
import org.aisino.core.base.BaseDomain; 
public class AosDuty extends BaseDomain { 
 private static final long serialVersionUID = 1L;
 private String xid;
  public String getXid() {
  	return xid;
  }
  public void setXid(String xid) {
  	this.xid = xid;
  }
 private String dutyid;
 private String deptid;
 private String dutyname;
 private String createdate;
 private String createby;
 private String available;
 public void setDutyid(String dutyid){
    this.dutyid = dutyid;
 }
 public String getDutyid(){
    return this.dutyid;
 }
 public void setDeptid(String deptid){
    this.deptid = deptid;
 }
 public String getDeptid(){
    return this.deptid;
 }
 public void setDutyname(String dutyname){
    this.dutyname = dutyname;
 }
 public String getDutyname(){
    return this.dutyname;
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
} 

