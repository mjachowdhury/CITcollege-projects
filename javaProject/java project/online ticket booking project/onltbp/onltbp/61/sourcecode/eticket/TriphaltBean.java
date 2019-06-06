/*
 * Created on Nov 23, 2006
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
public class TriphaltBean implements Serializable {
	protected int lid,trid,seqno;
	
	public void setLid(int i) {this.lid=i;}
	public void setTrid(int i) {this.trid=i;}
	public void setSeqno(int i) {this.seqno=i;}
	
	public int getLid(){return lid;}
	public int getTrid(){return trid;}
	public int getSeqno(){return seqno;}

}
