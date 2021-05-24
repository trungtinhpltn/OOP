package gameBanTau;

import java.io.Serializable;

public class NguoiChoi implements Serializable {
	private String tenNgChoi;
	private int soLanBan;
	private String thoiGianChoi;
	private String level;
	private String ghiChu;
	
	public NguoiChoi() {
		super();
	}
	public NguoiChoi(String tenNgChoi, int soLanBan, String thoiGianChoi, String level, String ghiChu) {
		super();
		this.tenNgChoi = tenNgChoi;
		this.soLanBan = soLanBan;
		this.thoiGianChoi = thoiGianChoi;
		this.level = level;
		this.ghiChu = ghiChu;
	}
	public String getTenNgChoi() {
		return tenNgChoi;
	}
	public void setTenNgChoi(String tenNgChoi) {
		this.tenNgChoi = tenNgChoi;
	}
	public int getSoLanBan() {
		return soLanBan;
	}
	public void setSoLanBan(int soLanBan) {
		this.soLanBan = soLanBan;
	}
	public String getThoiGianChoi() {
		return thoiGianChoi;
	}
	public void setThoiGianChoi(String thoiGianChoi) {
		this.thoiGianChoi = thoiGianChoi;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
}
