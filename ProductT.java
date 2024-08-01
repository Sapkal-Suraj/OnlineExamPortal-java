package onlineExam.angular.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProductT {
	
	@Id
	int pid;
	String pname;
	int pprice;
	String category;
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
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "ProductT [pid=" + pid + ", pname=" + pname + ", pprice=" + pprice + ", category=" + category + "]";
	}
	

}
