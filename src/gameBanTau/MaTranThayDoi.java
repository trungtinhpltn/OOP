package gameBanTau;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MaTranThayDoi extends Component{
	int vtS , vtE;
	int sumE , sumS ;
	private int vtTau6=0 , vtTau5=0, vtTau4=0, vtTau3=0, vtTau2=0 ;
	private Random rd = new Random();
	private int row = 10;
	private int col = 10;
	private int MT[][] = new int[row][col];
	private int giaTri;
	public boolean viTriOK;
	public MaTranThayDoi() {
		
	}
	public void createMT(int d , int c ,int  kT , int gT ,boolean vertical) {
		if(vertical) {
			viTriOK = true;
			vtE = c + kT;
			vtS = d + kT;
			giaTri = gT;
			sumE=0 ; sumS=0 ;
			if(vtE <= col) {
				for(int j =c ; j< vtE ; j++) {
					sumE += MT[d][j];
				}
			}
			if(vtS <= row) {
				for(int i =d ; i< vtS ; i++) {
					sumS += MT[i][c];
				}
			}
			
			if(vtE <= col && sumE == 0) {
				for(int j =c ; j< vtE ; j++) {
					MT[d][j] = giaTri++;
				}
			}	
			else if(vtS <= row && sumS == 0) {
				for(int i =d ; i< vtS ; i++) {
					MT[i][c] = giaTri++;
				}
			}
			else {
				viTriOK= false;
			}	
		}
		else {
			viTriOK = true;
			vtE = c + kT;
			vtS = d + kT;
			giaTri = gT;
			sumE=0 ; sumS=0 ;
			if(vtE <= col) {
				for(int j =c ; j< vtE ; j++) {
					sumE += MT[d][j];
				}
			}
			if(vtS <= row) {
				for(int i =d ; i< vtS ; i++) {
					sumS += MT[i][c];
				}
			}
			if(vtS <= row && sumS == 0) {
				for(int i =d ; i< vtS ; i++) {
					MT[i][c] = giaTri++;
				}
			}	
			else if(vtE <= col && sumE == 0) {
				for(int j =c ; j< vtE ; j++) {
					MT[d][j] = giaTri++;
				}
			}
			else {
				viTriOK= false;
			}	
		}
	}
	public int[][] getMT() {
		return MT;
	}
	
	public void setGiaTri(int giaTri) {
		this.giaTri = giaTri;
	}

	public int getGiaTri() {
		return giaTri;
	}
	public int getValue(int dog, int cot) {
		return MT[dog][cot];
	}
	
	public void setMaTrix(int i , int j) {
		MT[i][j] = 0;
	}
	
	public void kiemTraHuongTau() {
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col ; j++) {
				if(MT[i][j] == 1) {
					int E = j+1;
					int S = i+1;
					if(E < row) {
						if( MT[i][E] == 2) {
							vtTau6 = 1;
						}
						else {
							vtTau6 = 2;
						}
					}
					else {
						vtTau6 = 2;
					}
				}
				else if(MT[i][j] == 7) {
					int E = j+1;
					int S = i+1;
					if(E < row) {
						if( MT[i][E] == 8) {
							vtTau5 = 1;
						}
						else {
							vtTau5 = 2;
						}
					}
					else {
						vtTau5 = 2;
					}
				}
				else if(MT[i][j] == 12) {
					int E = j+1;
					int S = i+1;
					if(E < row) {
						if( MT[i][E] == 13) {
							vtTau4 = 1;
						}
						else {
							vtTau4 = 2;
						}
					}
					else {
						vtTau4 = 2;
					}
				}
				else if(MT[i][j] == 16) {
					int E = j+1;
					int S = i+1;
					if(E < row) {
						if( MT[i][E] == 17) {
							vtTau3 = 1;
						}
						else {
							vtTau3 = 2;
						}
					}
					else {
						vtTau3 = 2;
					}
				}
				else if(MT[i][j] == 19){
					int E = j+1;
					int S = i+1;
					if(E < row) {
						if( MT[i][E] == 20) {
							vtTau2 = 1;
						}
						else {
							vtTau2 = 2;
						}
					}
					else {
						vtTau2 = 2;
					}
				}
			}
		}
	}
	
	public int getVtTau6() {
		return vtTau6;
	}

	public int getVtTau5() {
		return vtTau5;
	}

	public int getVtTau4() {
		return vtTau4;
	}

	public int getVtTau3() {
		return vtTau3;
	}

	public int getVtTau2() {
		return vtTau2;
	}
}
