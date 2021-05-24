package gameBanTau;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class MaTrix {
	private Random rd = new Random();
	private final int row=10;
	private final int col=10;
	private int MT[][] = new int[row][col];
	private int vtTau6=0 , vtTau5=0, vtTau4=0, vtTau3=0, vtTau2=0 ;
	ArrayList<Point> listPoint = new ArrayList<Point>();
	public MaTrix() {
		createMaTrix();
	}
	
	public void createMaTrix() {
		for(int i = 0 ; i < row; i++) { // lấy vào danh sách tọa độ điểm của ma trận;
			for( int j = 0 ; j < col ; j++) {
				listPoint.add(new Point(i, j));
			}
		}
		int size = listPoint.size(); // lấy ra kích thước của danh sách
		int giaTri = 1;
		int kichThuoc = 6;
		do {
			int chiSoMaTran = rd.nextInt(size); // lấy ra một cái Point trong list có tọa độ (x, y)
			int d= listPoint.get(chiSoMaTran).x; // dòng
			int c = listPoint.get(chiSoMaTran).y; // cột
			int vtN, vtS , vtE, vtW ;
			vtE = c + kichThuoc;
			vtS = d + kichThuoc;
			vtW = c - kichThuoc;
			vtN = d - kichThuoc;
			int sumE=0 , sumS = 0 , sumW = 0 , sumN = 0 ;
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
			if(vtW >= -1) {
				for(int j = c ; j > vtW ; j--) {
					sumW += MT[d][j];
				}
			}
			if(vtN >= -1) {
				for(int i =d ; i> vtN ; i--) {
					sumN += MT[i][c];
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
			else if(vtW >= -1 && sumW == 0) {

				for(int j = c ; j > vtW ; j--) {
					MT[d][j] = giaTri++;
				}
			}
			else if(vtN >= -1 && sumN == 0){	
				for(int i =d ; i> vtN ; i--) {
					MT[i][c] = giaTri++;
				}
			}
			else {
				continue; 
			}			
			kichThuoc--;
		} while (giaTri <= 20);
	}
	
	public int getValueMT(int i , int j) {
		return MT[i][j];
	}
	
	public int[][] getMT() {
		return MT;
	}
	
	public void setMT(int[][] mT) {
		MT = mT;
	}

	public void setMaTrix(int i, int j) {
		this.MT[i][j] = 0;
	}
	public void reSet() {
		for(int i = 0 ; i < row; i++) {
			for(int j = 0 ; j < col ; j++) {
				MT[i][j] = 0;
			}
		}
	}
	public boolean ktChuaChonVT(int dong , int cot) {
		for(int i = 0 ; i< listPoint.size() ; i++) {
			if(listPoint.get(i).x == dong && listPoint.get(i).y == cot) {
				listPoint.remove(i);
				return true;
			}	
		}
		return false;
	}
	
	public void kiemTraHuongTau() {
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col ; j++) {
				int dt, dd, cp , ct ;
				if( MT[i][j] == 1) {
					dt = i -1;
					dd = i +1;
					cp = j+1;
					ct = j-1;
					if( dt >= 0) {
						if( MT[dt][j] == 2) {
							this.vtTau6 =4;
						}
					}
					if( dd <= 9) {
						if( MT[dd][j] == 2) {
							this.vtTau6 =2;
						}
					}
					if( cp <= 9) {
						if( MT[i][cp] == 2) {
							this.vtTau6 =1;
						}
					}
					if( ct >= 0) {
						if( MT[i][ct] == 2) {
							this.vtTau6 =3;
						}
					}
				}
				
				else if( MT[i][j] == 7) {
					dt = i -1;
					dd = i +1;
					cp = j+1;
					ct = j-1;
					if( dt >= 0) {
						if( MT[dt][j] == 8) {
							this.vtTau5 =4;
						}
					}
					if( dd <= 9) {
						if( MT[dd][j] == 8) {
							this.vtTau5 =2;
						}
					}
					if( cp <= 9) {
						if( MT[i][cp] == 8) {
							this.vtTau5 =1;
						}
					}
					if( ct >= 0) {
						if( MT[i][ct] == 8) {
							this.vtTau5 =3;
						}
					}
				}
				else if( MT[i][j] == 12) {
					dt = i -1;
					dd = i +1;
					cp = j+1;
					ct = j-1;
					if( dt >= 0) {
						if( MT[dt][j] == 13) {
							this.vtTau4 =4;
						}
					}
					if( dd <= 9) {
						if( MT[dd][j] == 13) {
							this.vtTau4 = 2;
						}
					}
					if( cp <= 9) {
						if( MT[i][cp] == 13) {
							this.vtTau4 = 1;
						}
					}
					if( ct >= 0) {
						if( MT[i][ct] == 13) {
							this.vtTau4 = 3;
						}
					}
				}
				else if( MT[i][j] == 16) {
					dt = i -1;
					dd = i +1;
					cp = j+1;
					ct = j-1;
					if( dt >= 0) {
						if( MT[dt][j] == 17) {
							this.vtTau3 =4;
						}
					}
					if( dd <= 9) {
						if( MT[dd][j] == 17) {
							this.vtTau3 =2;
						}
					}
					if( cp <= 9) {
						if( MT[i][cp] == 17) {
							this.vtTau3 =1;
						}
					}
					if( ct >= 0) {
						if( MT[i][ct] == 17) {
							this.vtTau3 =3;
						}
					}
				}
				else if( MT[i][j] == 19) {
					dt = i -1;
					dd = i +1;
					cp = j+1;
					ct = j-1;
					if( dt >= 0) {
						if( MT[dt][j] == 20) {
							this.vtTau2 =4;
						}
					}
					if( dd <= 9) {
						if( MT[dd][j] == 20) {
							this.vtTau2 =2;
						}
					}
					if( cp <= 9) {
						if( MT[i][cp] == 20) {
							this.vtTau2 =1;
						}
					}
					if( ct >= 0) {
						if( MT[i][ct] == 20) {
							this.vtTau2 =3;
						}
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
