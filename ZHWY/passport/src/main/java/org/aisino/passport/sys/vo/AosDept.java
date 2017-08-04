package org.aisino.passport.sys.vo;
import org.aisino.core.base.BaseDomain; 
public class AosDept extends BaseDomain { 
 private static final long serialVersionUID = 1L;
 private String xid;
  public String getXid() {
  	return xid;
  }
  public void setXid(String xid) {
  	this.xid = xid;
  }
 private String deptid;
 private String pid;
 private String deptname;
 private String deptcode;
 private String tier;
 private String leaf_node;
 private String createdate;
 private String createby;
 private String available;
 public void setDeptid(String deptid){
    this.deptid = deptid;
 }
 public String getDeptid(){
    return this.deptid;
 }
 public void setPid(String pid){
    this.pid = pid;
 }
 public String getPid(){
    return this.pid;
 }
 public void setDeptname(String deptname){
    this.deptname = deptname;
 }
 public String getDeptname(){
    return this.deptname;
 }
 public void setDeptcode(String deptcode){
    this.deptcode = deptcode;
 }
 public String getDeptcode(){
    return this.deptcode;
 }
 public void setTier(String tier){
    this.tier = tier;
 }
 public String getTier(){
    return this.tier;
 }
 public void setLeaf_node(String leaf_node){
    this.leaf_node = leaf_node;
 }
 public String getLeaf_node(){
    return this.leaf_node;
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

