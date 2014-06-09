package workflow.db;

// Generated 2014-5-15 18:04:07 by Hibernate Tools 3.4.0.CR1

/**
 * Relationnetfull generated by hbm2java
 */
public class Relationnetfull implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5138681235669573870L;
	private Long nid;
	private Long trustorId;
	private Long trusteeId;
	private Float trustValue;

	public Relationnetfull() {
	}

	public Relationnetfull(Long trustorId, Long trusteeId, Float trustValue) {
		this.trustorId = trustorId;
		this.trusteeId = trusteeId;
		this.trustValue = trustValue;
	}

	public Long getNid() {
		return this.nid;
	}

	public void setNid(Long nid) {
		this.nid = nid;
	}

	public Long getTrustorId() {
		return this.trustorId;
	}

	public void setTrustorId(Long trustorId) {
		this.trustorId = trustorId;
	}

	public Long getTrusteeId() {
		return this.trusteeId;
	}

	public void setTrusteeId(Long trusteeId) {
		this.trusteeId = trusteeId;
	}

	public Float getTrustValue() {
		return this.trustValue;
	}

	public void setTrustValue(Float trustValue) {
		this.trustValue = trustValue;
	}

}