package workflow.db;

// Generated 2014-5-15 18:04:07 by Hibernate Tools 3.4.0.CR1

/**
 * Focusws generated by hbm2java
 */
public class Focusws implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8584635276820537660L;
	private Long fwsid;
	private long userid;
	private long wsid;

	public Focusws() {
	}

	public Focusws(long userid, long wsid) {
		this.userid = userid;
		this.wsid = wsid;
	}

	public Long getFwsid() {
		return this.fwsid;
	}

	public void setFwsid(Long fwsid) {
		this.fwsid = fwsid;
	}

	public long getUserid() {
		return this.userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getWsid() {
		return this.wsid;
	}

	public void setWsid(long wsid) {
		this.wsid = wsid;
	}

}
