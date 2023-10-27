package com.pojo;

public class Transactions {

	private int transactionid;
	private int accnofrom;
	private int accnoto;
	private String ttype;
	private double tamt;
	private String tdate;
	private String tmode;

	public Transactions() {
		super();
	}

	public Transactions(int transactionid, int accnofrom, int accnoto, String ttype, double tamt, String tdate,
			String tmode) {
		super();
		this.transactionid = transactionid;
		this.accnofrom = accnofrom;
		this.accnoto = accnoto;
		this.ttype = ttype;
		this.tamt = tamt;
		this.tdate = tdate;
		this.tmode = tmode;
	}

	public int getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}

	public int getAccnofrom() {
		return accnofrom;
	}

	public void setAccnofrom(int accnofrom) {
		this.accnofrom = accnofrom;
	}

	public int getAccnoto() {
		return accnoto;
	}

	public void setAccnoto(int accnoto) {
		this.accnoto = accnoto;
	}

	public String getTtype() {
		return ttype;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
	}

	public double getTamt() {
		return tamt;
	}

	public void setTamt(double tamt) {
		this.tamt = tamt;
	}

	public String getTdate() {
		return tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	public String getTmode() {
		return tmode;
	}

	public void setTmode(String tmode) {
		this.tmode = tmode;
	}

	@Override
	public String toString() {
		return "Transactions [transactionid=" + transactionid + ", accnofrom=" + accnofrom + ", accnoto=" + accnoto
				+ ", ttype=" + ttype + ", tamt=" + tamt + ", tdate=" + tdate + ", tmode=" + tmode + "]";
	}

}
