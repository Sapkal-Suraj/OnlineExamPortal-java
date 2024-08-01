package onlineExam.angular.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;


@Entity
public class Product {
   @Id
	
	int pid;
   String pname;
   String pprice;
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public String getPname() {
	return pname;
}
public void setPname(String pname) {
	this.pname = pname;
}
public String getPprice() {
	return pprice;
}
public void setPprice(String pprice) {
	this.pprice = pprice;
}
@Override
public String toString() {
	return "Product [pid=" + pid + ", pname=" + pname + ", pprice=" + pprice + "]";
}

	
}
