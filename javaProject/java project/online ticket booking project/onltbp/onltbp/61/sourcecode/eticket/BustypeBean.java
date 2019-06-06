package eticket;
import java.io.*;
public class BustypeBean implements Serializable
{
	protected String tname,capacity,desc;
	protected int tid;

	public void setCapacity(String s) {this.capacity=s; }
	public void setTname(String s) {this.tname=s; }
public void setTid(int i) {this.tid=i; }
public void setDesc(String s) {this.desc=s; }

public String getCapacity() { return capacity; }
public String getTname() { return tname; }
public int getTid() {return tid; }
public String getDesc() { return desc; }

}
