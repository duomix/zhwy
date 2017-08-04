package org.aisino.passport.sys.vo;
import org.aisino.core.base.BaseDomain; 
public class AosResource extends BaseDomain { 
 private static final long serialVersionUID = 1L;
 private String xid;
  public String getXid() {
  	return xid;
  }
  public void setXid(String xid) {
  	this.xid = xid;
  }
 private String resid;
 private String resname;
 private String restype;
 private String identitytype;
 private String url;
 private String rescode;
 private String pid;
 private String resicon;
 private String available;
 public void setResid(String resid){
    this.resid = resid;
 }
 public String getResid(){
    return this.resid;
 }
 public void setResname(String resname){
    this.resname = resname;
 }
 public String getResname(){
    return this.resname;
 }
 public void setRestype(String restype){
    this.restype = restype;
 }
 public String getRestype(){
    return this.restype;
 }
 public void setIdentitytype(String identitytype){
    this.identitytype = identitytype;
 }
 public String getIdentitytype(){
    return this.identitytype;
 }
 public void setUrl(String url){
    this.url = url;
 }
 public String getUrl(){
    return this.url;
 }
 public void setRescode(String rescode){
    this.rescode = rescode;
 }
 public String getRescode(){
    return this.rescode;
 }
 public void setPid(String pid){
    this.pid = pid;
 }
 public String getPid(){
    return this.pid;
 }
 public void setResicon(String resicon){
    this.resicon = resicon;
 }
 public String getResicon(){
    return this.resicon;
 }
 public void setAvailable(String available){
    this.available = available;
 }
 public String getAvailable(){
    return this.available;
 }
} 

