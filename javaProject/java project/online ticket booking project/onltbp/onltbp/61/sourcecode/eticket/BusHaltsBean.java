/*
 * Created on Nov 16, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package eticket;
import java.io.*;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BusHaltsBean implements Serializable {
	
	
	protected int trid,lid,seqno;
	
	public void setTrid(int i) {this.trid=i;}
	public void setLid(int i) {this.lid=i;}
	public void setSeqno(int i) {this.seqno=i;}
	

	public int getSeqno() {return seqno;}
	public int getLid() {return lid;}
	public int getTrid() {return trid;}
	
}
