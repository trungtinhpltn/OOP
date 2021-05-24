package gameBanTau;

import javax.swing.JButton;

public class Button extends JButton {
	private int dogi;
	private int cotj;
	public Button(int i, int j){
		super();
		dogi= i;
		cotj= j;
	}
	public int getDogi() {
		return dogi;
	}
	public void setDogi(int dogi) {
		this.dogi = dogi;
	}
	public int getCotj() {
		return cotj;
	}
	public void setCotj(int cotj) {
		this.cotj = cotj;
	}
}
