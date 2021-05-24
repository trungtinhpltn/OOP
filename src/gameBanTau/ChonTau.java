
package gameBanTau;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class ChonTau extends Container {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Random rd = new Random();
	JTextField txtTen ;
	private JButton btnTau6, btnTau5, btnTau4, btnTau3 , btnTau2;
	private JTextArea txtTB;
	private JTextField txtChat;
	private JScrollPane sc;
	private JPanel pnSouth , pnLuaChonPA , pnRdOK, pnComArea , pnTen, pnLabel ,pnSS, pnCenter, pnAiban , pnChonTauOK;
	private JButton btnXepTau , btnRandom , btnRandomOK , btnSend, btnAiban , btnChonTauOK, btnLevel;
	private JLabel lbAnh , lbLanBan;
	public JLabel lbTen;
	private int size = 40, kc = 1 ,tauisSelec =0, soLanBan = 56, chonRd= 0,ngBan = 0, mayban = 0, check = 0, thongMinh = 0;
	private int dTau6= 0, dTau5= 0 , dTau4 = 0 , dTau3 = 0 , dTau2 = 0 , kichThuoc , giaTri, lanChonTau = 0 ;
	private boolean vertical = true;
	public int luotBan = 0, level = 0, ktHis = 1 ,chonNhac = 0 ;
	private final int // thứ tự mảng ảnh theo tàu
			tau2 = 2,
			tau3 = 3,
			tau4 = 4,
			tau5 = 5,
			tau6 = 6;
	private BanTau parent;
	private JPanel pnChonTau ;
	private Level l = new Level();
	private Image image[][] = new Image[2][7]; // mảng ảnh
	Object[] options = {"Có","Không"};
	private final int row=10;
	private final int col=10;
	private MaTranThayDoi mT= new MaTranThayDoi();
	private MaTrix mTrandom = new MaTrix();
	private MaTrix mTComputer = new MaTrix();
	private Button [][]arrBtn = new Button[row][col];
	private Button [][]comBtn = new Button[row][col];
	private ArrayList<Point> arrPoint = new ArrayList<Point>();
	private ArrayList<JButton> arrTau = new ArrayList<JButton>();
	private ComButtonHandler itemCom = new ComButtonHandler();
	private Mouse m = new Mouse();
	private MyMouse mm = new MyMouse();
	public AudioClip win, lose , trungTau , noTau , truot ,audioNen, xepTau;
	
	public ChonTau(BanTau parent) { // contructor
		this.parent = parent;
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col; j++) {
				arrPoint.add(new Point(i, j));
			}
		}
		image[1][tau2] = (new ImageIcon("hinhAnh/2ngang.gif")).getImage();
		image[1][tau3] = (new ImageIcon("hinhAnh/3ngang.gif")).getImage();
		image[1][tau4] = (new ImageIcon("hinhAnh/4ngang.gif")).getImage();
		image[1][tau5] = (new ImageIcon("hinhAnh/5ngang.gif")).getImage();
		image[1][tau6] = (new ImageIcon("hinhAnh/6ngang.gif")).getImage();
		
		try {
			audioNen = Applet.newAudioClip(new File("sounds/nhacnenconTra.wav").toURL());
			xepTau = Applet.newAudioClip(new File("sounds/xepTau.wav").toURL());
			win = Applet.newAudioClip(new File("sounds/Win.wav").toURL());
			lose = Applet.newAudioClip(new File("sounds/thua.wav").toURL());
			trungTau = Applet.newAudioClip(new File("sounds/trungtau.wav").toURL());
			noTau = Applet.newAudioClip(new File("sounds/noTau.wav").toURL());
			truot = Applet.newAudioClip(new File("sounds/truot.wav").toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		audioNen.play();
		setLayout(new BorderLayout());
		pnCenter = new JPanel(new BorderLayout());
		TitledBorder titbBorder = new TitledBorder(
				BorderFactory.createLineBorder(Color.gray), "Bản đồ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				new Font("arial", Font.ROMAN_BASELINE, 20), Color.blue);
		
		pnCenter.setBorder(titbBorder);
		pnCenter.setBackground(new Color(208, 244, 244));
		
		pnLabel = new JPanel();
		pnLabel.setBackground(Color.LIGHT_GRAY);
		pnLabel.setLayout(new BorderLayout());
		JPanel pnLB = new JPanel(new FlowLayout());
		pnLB.setBackground(Color.LIGHT_GRAY);
		lbLanBan = new JLabel("Số lần bắn: "+ soLanBan);
		lbLanBan.setFont(new Font("arial", Font.CENTER_BASELINE, 18));
		lbLanBan.setForeground(Color.BLUE);
		pnLB.add(lbLanBan);
		
		lbAnh = new JLabel(new ImageIcon("hinhAnh/BK.png"));
		
		pnLabel.add(pnLB, BorderLayout.NORTH); pnLabel.add(lbAnh, BorderLayout.CENTER);
		btnAiban = new JButton(new ImageIcon("hinhAnh/aiBan.png"));
		btnAiban.setPreferredSize(new Dimension(281, 38));
		pnAiban = new JPanel();
		pnAiban.setBackground(new Color(208, 244, 244));
		pnAiban.add(btnAiban);
		
		JPanel pnMyArea = new JPanel(new FlowLayout());
		pnMyArea.setBackground(new Color(208, 244, 244));
		pnMyArea.add(comArea());
		pnMyArea.add(pnLabel);
		pnMyArea.add(myArea());
		pnCenter.add(pnMyArea, BorderLayout.CENTER);
		pnCenter.add(pnAiban, BorderLayout.SOUTH);
		pnAiban.setVisible(false);
		pnLabel.setVisible(false);
		
		pnSouth = new JPanel(new BorderLayout());
		
		pnLuaChonPA = new JPanel(new FlowLayout());
		pnLuaChonPA.setBackground(new Color(208, 244, 244));
		btnXepTau = new JButton("Tự xếp tàu");
		btnRandom = new JButton("Random");
		
		pnRdOK = new JPanel(new FlowLayout());
		pnRdOK.setBackground(new Color(208, 244, 244));
		btnRandomOK = new JButton("OK");
		
		pnRdOK.add(btnRandomOK); 
		pnLuaChonPA.add(btnXepTau); pnLuaChonPA.add(btnRandom);
		
		pnChonTau = new JPanel(new BorderLayout());
		pnChonTau.setBorder(new TitledBorder(
				BorderFactory.createLineBorder(Color.gray), "Chọn tàu và chọn vị trí đặt trên bản đồ theo thứ tự tàu ở dưới"));
		JPanel pnTop = new JPanel(new GridLayout(1, 2));
		JPanel pnBottom = new JPanel(new GridLayout(1, 3));
		btnTau6 = new JButton("Tàu Sân Bay", new ImageIcon(image[1][tau6]));
		btnTau5 = new JButton("Tàu Khu Trục", new ImageIcon(image[1][tau5]));
		btnTau4 = new JButton("Tàu Tuần Tra", new ImageIcon(image[1][tau4]));
		btnTau3 = new JButton("Tàu Ngư Lôi", new ImageIcon(image[1][tau3]));
		btnTau2 = new JButton("Tàu Do Thám", new ImageIcon(image[1][tau2]));
		arrTau.add(btnTau6);arrTau.add(btnTau5);arrTau.add(btnTau4);arrTau.add(btnTau3);arrTau.add(btnTau2);
		pnTop.add(btnTau6); pnTop.add(btnTau5);
		pnBottom.add(btnTau4); pnBottom.add(btnTau3); pnBottom.add(btnTau2);
		
		pnChonTau.add(pnTop,BorderLayout.NORTH);
		pnChonTau.add(pnBottom, BorderLayout.CENTER);
		
		pnChonTauOK = new JPanel();
		pnChonTauOK.setBackground(new Color(208, 244, 244));
		btnChonTauOK = new JButton("OK");
		pnChonTauOK.add(btnChonTauOK);
		
		txtTB = new JTextArea("Chào mừng bạn đến với bắn tàu",7,0);
		txtTB.setFont(new Font("arial", Font.PLAIN, 15));
		txtTB.setBackground(new Color(254, 254, 254));
		sc = new JScrollPane(txtTB, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.GRAY),"Tin nhắn"));
		pnSouth.add(pnLuaChonPA, BorderLayout.CENTER);
		pnSouth.add(pnRdOK ,BorderLayout.SOUTH);
		pnRdOK.setVisible(false);
		
		pnSS = new JPanel(new BorderLayout());
		pnSS.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.GRAY),"Trò chuyện"));
		pnSS.setBackground(new Color(208, 244, 244));
		txtChat = new JTextField();
		txtChat.setBackground(new Color(254, 254, 254));
		pressKey pre = new pressKey();
		txtChat.addKeyListener(pre);
		btnSend = new JButton("Gửi");
		pnSS.add(txtChat, BorderLayout.CENTER); 
		pnSS.add(btnSend, BorderLayout.EAST);
		
		add(pnCenter, BorderLayout.CENTER);
		add(pnSouth, BorderLayout.SOUTH);
		addEvetns();
	
	}
	
	private Component myArea() { // tạo area cho người chơi
		
		JPanel pnMyArea = new JPanel(new BorderLayout());
		pnMyArea.setBackground(Color.LIGHT_GRAY);
		JPanel pnMap = new JPanel();
		pnMap.setBackground(Color.BLACK);
		pnMap.setLayout(new GridLayout(row, col, kc, kc));
		
		pnMap.setPreferredSize(new Dimension((size+kc)*row, (size+kc)*col));
		
		for(int i =  0 ; i<row ; i++) {
			for(int j = 0 ; j < col; j++) {
				arrBtn[i][j] = createBtn(i,j);
				pnMap.add(arrBtn[i][j]);
			}
		}
		JPanel pnDong = new JPanel(new GridLayout(10,1));
		pnDong.setBackground(Color.LIGHT_GRAY);
		pnDong.setBorder(BorderFactory.createMatteBorder(0 ,1, 1, 1, Color.BLACK));
		pnDong.setPreferredSize(new Dimension(20, 10*size));
		JLabel lbA = new JLabel("A");
		lbA.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbB = new JLabel("B");
		lbB.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbC = new JLabel("C");
		lbC.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbD = new JLabel("D");
		lbD.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbE = new JLabel("E");
		lbE.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbF = new JLabel("F");
		lbF.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbG = new JLabel("G");
		lbG.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbH = new JLabel("H");
		lbH.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbI = new JLabel("I");
		lbI.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbK = new JLabel("K");
		lbK.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		pnDong.add(lbA);pnDong.add(lbB);pnDong.add(lbC);pnDong.add(lbD);pnDong.add(lbE);
		pnDong.add(lbF);pnDong.add(lbG);pnDong.add(lbH);pnDong.add(lbI);pnDong.add(lbK);
		
		JPanel pnCot = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnCot.setBackground(Color.LIGHT_GRAY);
		pnCot.setBorder(BorderFactory.createMatteBorder(0 ,1, 1, 1, Color.BLACK));
		
		JPanel pnCotC = new JPanel(new GridLayout(1, 10, 22,0));
		pnCotC.setPreferredSize(new Dimension(10*size, 13));
		pnCotC.setBackground(Color.LIGHT_GRAY);
		pnCotC.setBorder(BorderFactory.createEmptyBorder(0, 9, 0, 0));
		
		JLabel lb1 = new JLabel("1");
		lb1.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb2 = new JLabel("2");
		lb2.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb3 = new JLabel("3");
		lb3.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb4 = new JLabel("4");
		lb4.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb5 = new JLabel("5");
		lb5.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb6 = new JLabel("6");
		lb6.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb7 = new JLabel("7");
		lb7.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb8 = new JLabel("8");
		lb8.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb9 = new JLabel("9");
		lb9.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb10 = new JLabel("10");
		lb10.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		
		pnCotC.add(lb1);pnCotC.add(lb2);pnCotC.add(lb3);pnCotC.add(lb4);pnCotC.add(lb5);
		pnCotC.add(lb6);pnCotC.add(lb7);pnCotC.add(lb8);pnCotC.add(lb9);pnCotC.add(lb10);
		pnCot.add(pnCotC);
		
		pnTen = new JPanel();
		pnTen.setBackground(Color.LIGHT_GRAY);
		txtTen = new JTextField(13);
		txtTen.setPreferredSize(new Dimension(0, 30));
		txtTen.setFont(new Font("arial", Font.PLAIN, 16));
		lbTen = new JLabel("Tên người chơi:");
		lbTen.setFont(new Font("arial", Font.CENTER_BASELINE, 18));
		lbTen.setForeground(Color.BLUE);
		pnTen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnTen.add(lbTen); pnTen.add(txtTen);
		
		
		btnLevel = new JButton("Level");
		btnLevel.setBorder(null);
		btnLevel.setFont(new Font("arial", Font.PLAIN, 16));
		btnLevel.setPreferredSize(new Dimension(80, 29));
		pnTen.add(btnLevel);
		pnMyArea.add(pnTen, BorderLayout.NORTH);
		pnMyArea.add(pnMap, BorderLayout.CENTER);
		pnMyArea.add(pnDong, BorderLayout.WEST);
		pnMyArea.add(pnCot, BorderLayout.SOUTH);
		return pnMyArea;
	}

	private Component comArea() { // tạo area computer
		pnComArea = new JPanel(new BorderLayout());
		JPanel pnArea = new JPanel();
		pnComArea.setBackground(Color.GRAY);
		pnArea.setLayout(new GridLayout(row, col, kc, kc));
		pnArea.setPreferredSize(new Dimension((size+kc)*row, (size+kc)*col));
		pnArea.setBackground(Color.BLACK);
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col; j++) {
				(comBtn[i][j] = createComButton(i ,j)).addActionListener(itemCom);
				pnArea.add(comBtn[i][j]);
			}
		}
		JPanel pnDong = new JPanel(new GridLayout(10,1,0,1));
		pnDong.setBackground(Color.LIGHT_GRAY);
		pnDong.setBorder(BorderFactory.createMatteBorder(0 ,1, 1, 1, Color.BLACK));
		pnDong.setPreferredSize(new Dimension(20, 10*size));
		
		JLabel lbA = new JLabel("A");
		lbA.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbB = new JLabel("B");
		lbB.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbC = new JLabel("C");
		lbC.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbD = new JLabel("D");
		lbD.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbE = new JLabel("E");
		lbE.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbF = new JLabel("F");
		lbF.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbG = new JLabel("G");
		lbG.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbH = new JLabel("H");
		lbH.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbI = new JLabel("I");
		lbI.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lbK = new JLabel("K");
		lbK.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		pnDong.add(lbA);pnDong.add(lbB);pnDong.add(lbC);pnDong.add(lbD);pnDong.add(lbE);
		pnDong.add(lbF);pnDong.add(lbG);pnDong.add(lbH);pnDong.add(lbI);pnDong.add(lbK);
		
		JPanel pnCot = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnCot.setBackground(Color.LIGHT_GRAY);
		pnCot.setBorder(BorderFactory.createMatteBorder(0 ,1, 1, 1, Color.BLACK));
		
		JPanel pnCotC = new JPanel(new GridLayout(1, 10, 22,0));
		pnCotC.setPreferredSize(new Dimension(10*size, 13));
		pnCotC.setBackground(Color.LIGHT_GRAY);
		pnCotC.setBorder(BorderFactory.createEmptyBorder(0, 9, 0, 0));
		
		JLabel lb1 = new JLabel("1");
		lb1.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb2 = new JLabel("2");
		lb2.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb3 = new JLabel("3");
		lb3.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb4 = new JLabel("4");
		lb4.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb5 = new JLabel("5");
		lb5.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb6 = new JLabel("6");
		lb6.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb7 = new JLabel("7");
		lb7.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb8 = new JLabel("8");
		lb8.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb9 = new JLabel("9");
		lb9.setFont(new Font("arial", Font.CENTER_BASELINE, 16));
		JLabel lb10 = new JLabel("10");
		lb10.setFont(new Font("arial", Font.CENTER_BASELINE, 16));

		pnCotC.add(lb1);pnCotC.add(lb2);pnCotC.add(lb3);pnCotC.add(lb4);pnCotC.add(lb5);
		pnCotC.add(lb6);pnCotC.add(lb7);pnCotC.add(lb8);pnCotC.add(lb9);pnCotC.add(lb10);
		pnCot.add(pnCotC);
		
		JPanel pnlb = new JPanel();
		pnlb.setBackground(Color.LIGHT_GRAY);
		JLabel lb = new JLabel("Computer");
		lb.setFont(new Font("arial", Font.CENTER_BASELINE, 18));
		lb.setForeground(Color.BLUE);
		pnlb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlb.add(lb);
		pnComArea.add(pnDong, BorderLayout.WEST);
		pnComArea.add(pnlb, BorderLayout.NORTH);
		pnComArea.add(pnArea, BorderLayout.CENTER);
		pnComArea.add(pnCot , BorderLayout.SOUTH);
		
		pnComArea.setVisible(false);
		return pnComArea;
	}

	private Button createComButton(int i, int j) { //tạo button computer
		Button btn = new Button(i, j);
		btn.setIcon(new ImageIcon("hinhAnh/bien.png"));
		btn.addMouseListener(m);
		btn.setBorder(null);
		return btn;
	}

	private Button createBtn(int i, int j) { // tạo button cho người chơi
		Button btn = new Button(i, j);
		btn.setIcon(new ImageIcon("hinhAnh/bien.png"));
		btn.setBorder(null);
		return btn;
	}

	protected void xuLyTaoRandom() { // tạo ma trận random
		 mTrandom.reSet();
		 mTrandom.createMaTrix();
	}

	protected void xuLyTaoMTTD(MouseEvent e) { // tạo ra ma trận tàu
		Button bt = (Button)e.getSource();
		int d = bt.getDogi();
		int c = bt.getCotj();
		mT.createMT(d, c, kichThuoc, giaTri , vertical);	
		if(mT.viTriOK == false) {
			JOptionPane.showMessageDialog(null, "vị trí không đc chọn lại");
			return;
		}
		hienThiMau();
		if( arrTau.isEmpty()== false) {
			for(JButton b: arrTau)
				b.setEnabled(true);
		}
		if( lanChonTau == 5) {
			pnChonTau.setVisible(false);
			pnSouth.add(pnChonTauOK, BorderLayout.CENTER);
		}
	}

	private class ComButtonHandler implements ActionListener{ // sự kiện của mảng Computerbutton
		public void actionPerformed(ActionEvent e) {
			if(check == 0) {
				pnAiban.setVisible(false);
				ngBan = 1;
			}
			mTComputer.kiemTraHuongTau();
			Button bt = (Button) e.getSource();
			int i = bt.getDogi();
			int j = bt.getCotj();
			bt.removeActionListener(itemCom);
			bt.removeMouseListener(m);
			if(ngBan ==1) {
				txtTB.setText(txtTB.getText()+"\nLượt bắn "+(++luotBan)+":");
			}
			txtTB.setText(txtTB.getText()+"\nBạn bắn vào vị trí ["+hamDoc(i)+"]["+(j+1)+"] của máy.");
			int gt = mTComputer.getValueMT(i, j);
			if(level == 1) {
				if(gt > 0) {
					if(parent.soundOn()) {
						trungTau.play();
					}
					bt.setIcon(new ImageIcon("hinhAnh/bom.png"));
					if( 1<= gt && gt <= 6 ) {
						txtTB.setText(txtTB.getText()+"\nMáy: Bắn trúng tàu!!!\n");
						++dTau6;
						if( dTau6 == 6) {
							if(parent.soundOn()) {
								noTau.play();
							}
							JOptionPane.showMessageDialog(null, "Bắn nổ tàu Sân Bay");
							hienThiMay6();
						}
					}
					else if(7<= gt && gt <= 11) {
						txtTB.setText(txtTB.getText()+"\nMáy: Bắn trúng tàu!!!\n");
						dTau5++;
						if( dTau5 == 5) {
							if(parent.soundOn()) {
								noTau.play();
							}
							JOptionPane.showMessageDialog(null, "Bắn nổ tàu Khu Trục");
							hienThiMay5();
						}
					}
					else if(12<= gt && gt <= 15) {
						txtTB.setText(txtTB.getText()+"\nMáy: Bắn trúng tàu!!!\n");
						dTau4++;
						if( dTau4 == 4) {
							if(parent.soundOn()) {
								noTau.play();
							}
							JOptionPane.showMessageDialog(null, "Bắn nổ tàu Tuần Tra");
							hienThiMay4();
						}
					}
					else if(16<= gt && gt <= 18) {
						txtTB.setText(txtTB.getText()+"\nMáy: Bắn trúng tàu!!!\n");
						dTau3++;
						if( dTau3 == 3) {
							if(parent.soundOn()) {
								noTau.play();
							}
							JOptionPane.showMessageDialog(null, "Bắn nổ tàu Ngư Lôi");
							hienThiMay3();
						}
					}
					else {
						txtTB.setText(txtTB.getText()+"\nMáy: Bắn trúng tàu!!!\n");
						dTau2++;
						if( dTau2 == 2) {
							if(parent.soundOn()) {
								noTau.play();
							}
							JOptionPane.showMessageDialog(null, "Bắn nổ tàu Do Thám");
							hienThiMay2();
						}
					}
					if(checkWin() == true) {
						xuLyWin();
						return;
					}
				}
				else {
					if(parent.soundOn()) {
						truot.play();
					}
					bt.setIcon(new ImageIcon("hinhAnh/truot.png"));
					txtTB.setText(txtTB.getText()+"\nMáy: Không trúng tàu!!!\n");
				}
			}

			else {
				if(gt > 0) {
					if(parent.soundOn()) {
						trungTau.play();
					}
					bt.setIcon(new ImageIcon("hinhAnh/bom.png"));
					if( 1<= gt && gt <= 6 ) {
						txtTB.setText(txtTB.getText()+"\nMáy: Bắn trúng tàu Sân Bay!!!\n");
						++dTau6;
						if( dTau6 == 6) {
							if(parent.soundOn()) {
								noTau.play();
							}
							JOptionPane.showMessageDialog(null, "Bắn nổ tàu Sân Bay");
							hienThiMay6();
						}
					}
					else if(7<= gt && gt <= 11) {
						txtTB.setText(txtTB.getText()+"\nMáy: Bắn trúng tàu Khu Trục!!!\n");
						dTau5++;
						if( dTau5 == 5) {
							if(parent.soundOn()) {
								noTau.play();
							}
							JOptionPane.showMessageDialog(null, "Bắn nổ tàu Khu Trục");
							hienThiMay5();
						}
					}
					else if(12<= gt && gt <= 15) {
						txtTB.setText(txtTB.getText()+"\nMáy: Bắn trúng tàu Tuần Tra!!!\n");
						dTau4++;
						if( dTau4 == 4) {
							if(parent.soundOn()) {
								noTau.play();
							}
							JOptionPane.showMessageDialog(null, "Bắn nổ tàu Tuần Tra");
							hienThiMay4();
						}
					}
					else if(16<= gt && gt <= 18) {
						txtTB.setText(txtTB.getText()+"\nMáy: Bắn trúng tàu Ngư Lôi!!!\n");
						dTau3++;
						if( dTau3 == 3) {
							if(parent.soundOn()) {
								noTau.play();
							}
							JOptionPane.showMessageDialog(null, "Bắn nổ tàu Ngư Lôi");
							hienThiMay3();
						}
					}
					else {
						txtTB.setText(txtTB.getText()+"\nMáy: Bắn trúng tàu Do Thám!!!\n");
						dTau2++;
						if( dTau2 == 2) {
							if(parent.soundOn()) {
								noTau.play();
							}
							JOptionPane.showMessageDialog(null, "Bắn nổ tàu Do Thám");
							hienThiMay2();
						}
					}
					if(checkWin() == true) {
						xuLyWin();
						return;
					}
				}
				else {
					if(parent.soundOn()) {
						truot.play();
					}
					bt.setIcon(new ImageIcon("hinhAnh/truot.png"));
					txtTB.setText(txtTB.getText()+"\nMáy: Không trúng tàu nào!!!\n");
				}
			}

			soLanBan--;
			lbLanBan.setText("Số lần bắn còn lại: "+soLanBan);
			if(soLanBan == 0) {
				String lev = "Dễ";
				if(level == 1) {
					lev = "Khó";
				}
				Calendar cal = Calendar.getInstance();
				Date d = cal.getTime();
				NguoiChoi ngChoi= new NguoiChoi(lbTen.getText(), luotBan, sdf.format(d), lev, "Lose-Hết lượt chơi");
				parent.addArr(ngChoi);
				parent.luuDuLieu(); // Lưu dữ liệu
				xuLyThua();
				if(parent.soundOn()) {
					lose.play();
				}
				JOptionPane.showMessageDialog(
						pnSouth, "Rất đáng tiếc bạn đã bị thua mất rồi...", "Thua",
						JOptionPane.DEFAULT_OPTION, new ImageIcon("hinhAnh/lose.png"));
				ktHis = 0;
				return;
			}
			else {
				try {
					Thread.sleep(50);
					mayBan();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}		
			}
		}
	}
	
	private class Mouse extends MouseAdapter{ // di chuyen chuot vao mang computerbutton
		
		public void mouseExited(MouseEvent e) {
			((AbstractButton) e.getSource()).setIcon(new ImageIcon("hinhAnh/bien.png"));
		}
		
		public void mouseEntered(MouseEvent e) {
			((AbstractButton) e.getSource()).setIcon(new ImageIcon("hinhAnh/tamngam.png"));
		}
	}

	private class MyMouse extends MouseAdapter{// nhấn chuột
		public void mousePressed(MouseEvent e) {
			if( e.getModifiers() == e.BUTTON1_MASK) {
				xuLyTaoMTTD(e);
				
			}
			
			if( e.getModifiers() == e.BUTTON3_MASK) {
				vertical = !vertical;
				Button bt = (Button) e.getSource();
				int d = bt.getDogi();
				int c = bt.getCotj();
				mT.createMT(d, c , kichThuoc , giaTri, vertical);
				hienThiMauXep();
				for(int i = 0 ; i < row ; i++) {
					for(int j = 0 ; j< col ; j++) {
						int gt = mT.getValue(i, j);
						if(tauisSelec == 6 ) {
							if( 1 <= gt && gt <=6) {
								mT.setMaTrix(i, j);
							}	
						}
						else if(tauisSelec == 5 ) {

							if ( 7 <= gt && gt <=11) {
								mT.setMaTrix(i, j);
							}
						}
						else if(tauisSelec == 4 ) {
							if( 12 <= gt && gt <=15) {
								mT.setMaTrix(i, j);
							}
						}
						else if(tauisSelec == 3 ) {
							if (16 <= gt && gt <=18 )
								mT.setMaTrix(i, j);
						}
						else {
							if(19 <= gt && gt <=20)
								mT.setMaTrix(i, j);
						}
					}
				}
			}
		}
		
		public void mouseEntered(MouseEvent e) {
			Button bt = (Button) e.getSource();
			int d = bt.getDogi();
			int c = bt.getCotj();
			mT.createMT(d, c , kichThuoc , giaTri, vertical);
			hienThiMauXep();
			for(int i = 0 ; i < row ; i++) {
				for(int j = 0 ; j< col ; j++) {
					int gt = mT.getValue(i, j);
					if(tauisSelec == 6 ) {
						if( 1 <= gt && gt <=6) {
							mT.setMaTrix(i, j);
						}	
					}
					else if(tauisSelec == 5 ) {
						if ( 7 <= gt && gt <=11) {
							mT.setMaTrix(i, j);
						}
					}
					else if(tauisSelec == 4 ) {
						if( 12 <= gt && gt <=15) {
							mT.setMaTrix(i, j);
						}
					}
					else if(tauisSelec == 3 ) {
						if (16 <= gt && gt <=18 )
							mT.setMaTrix(i, j);
					}
					else {
						if(19 <= gt && gt <=20)
							mT.setMaTrix(i, j);
					}
				}
			}
		}
	}
	
	private class pressKey implements KeyListener{

		public void keyTyped(KeyEvent e) {
		
		}

		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				btnSend.doClick();
			}
		}

		public void keyReleased(KeyEvent e) {
			
		}
		
	}

	public void xuLyThua() {
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col ; j++) {
				comBtn[i][j].removeActionListener(itemCom);
				comBtn[i][j].removeMouseListener(m);
			}
		}
		if(dTau6 < 6) {
			hienThiMay6();
		}
		if(dTau5 < 5) {
			hienThiMay5();
		}
		if(dTau4 < 4) {
			hienThiMay4();
		}
		if(dTau3 < 3) {
			hienThiMay3();
		}
		if(dTau2 < 2) {
			hienThiMay2();
		}
	}

	private void addEvetns() { // sự kiện của button loại tàu
		
		btnLevel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				l.showWindow();
			}
		});
		
		btnTau6.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent arg0) {
				btnTau6.setEnabled(false);
				for(int i = 0; i< arrTau.size(); i++) {
					if(arrTau.get(i) == btnTau6)
						arrTau.remove(i);
				}
				if( chonNhac == 0) {
					if( parent.soundOn()) {
						chonNhac = 1;
						xepTau.loop();
					}
				}
				dongChonTau();
				tauisSelec = 6;
				kichThuoc = 6;
				giaTri = 1;
				lanChonTau++;
				xuLyChonViTri();
			}
		});
		
		btnTau5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				btnTau5.setEnabled(false);
				for(int i = 0; i< arrTau.size(); i++) {
					if(arrTau.get(i) == btnTau5)
						arrTau.remove(i);
				}
				if( chonNhac == 0) {
					if( parent.soundOn()) {
						chonNhac = 1;
						xepTau.loop();
					}
				}
				dongChonTau();
				kichThuoc = 5;
				giaTri = 7;
				tauisSelec = 5;
				lanChonTau++;
				xuLyChonViTri();
			}
		});
		
		btnTau4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnTau4.setEnabled(false);
				for(int i = 0; i< arrTau.size(); i++) {
					if(arrTau.get(i) == btnTau4)
						arrTau.remove(i);
				}
				if( chonNhac == 0) {
					if( parent.soundOn()) {
						chonNhac = 1;
						xepTau.loop();
					}
				}
				dongChonTau();
				kichThuoc = 4;
				giaTri = 12;
				tauisSelec = 4;
				lanChonTau++;
				xuLyChonViTri();
			}
		});
		
		btnTau3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				btnTau3.setEnabled(false);
				for(int i = 0; i< arrTau.size(); i++) {
					if(arrTau.get(i) == btnTau3)
						arrTau.remove(i);
				}
				if( chonNhac == 0) {
					if( parent.soundOn()) {
						chonNhac = 1;
						xepTau.loop();
					}
				}
				dongChonTau();
				kichThuoc = 3;
				giaTri = 16;
				tauisSelec = 3;
				xuLyChonViTri();
				lanChonTau++;
			}
		});
		
		btnTau2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				btnTau2.setEnabled(false);
				for(int i = 0; i< arrTau.size(); i++) {
					if(arrTau.get(i) == btnTau2)
						arrTau.remove(i);
				}
				if( chonNhac == 0) {
					if( parent.soundOn()) {
						chonNhac = 1;
						xepTau.loop();
					}
				}
				dongChonTau();
				kichThuoc = 2;
				giaTri = 19;
				tauisSelec = 2;
				lanChonTau++;
				xuLyChonViTri();
			}
		});
		
		btnXepTau.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				audioNen.stop();
				if( chonNhac == 0) {
					if( parent.soundOn()) {
						chonNhac = 1;
						xepTau.loop();
					}
				}
				pnLuaChonPA.setVisible(false);
				pnSouth.add(pnChonTau,BorderLayout.CENTER);
			}
		});
		
		btnRandom.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				chonRd = 1;
				audioNen.stop();
				if( chonNhac == 0) {
					if( parent.soundOn()) {
						chonNhac = 1;
						xepTau.loop();
					}
				}
				btnXepTau.setEnabled(false);
				pnRdOK.setVisible(true);
				xuLyTaoRandom();
				hienThiTau();
				
			}
		});
		
		btnRandomOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTen.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tên", "Tên người chơi trống", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtTen.getText().length() > 35) {
					JOptionPane.showMessageDialog(null, "Tên quá dài", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(l.Hard.isSelected()) {
					level = 1;
					soLanBan = 50;
					lbLanBan.setText("Số lần bắn: "+soLanBan);
				}
				xepTau.stop();
				pnTen.remove(btnLevel);
				lbTen.setText(txtTen.getText());
				pnTen.remove(txtTen);
				pnComArea.setVisible(true);
				pnAiban.setVisible(true);
				pnLabel.setVisible(true);
				pnLuaChonPA.setVisible(false);
				pnSouth.add(sc,BorderLayout.CENTER);
				pnSouth.add(pnSS, BorderLayout.SOUTH);
				pnSouth.remove(pnRdOK);
			}
		});
		
		btnChonTauOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTen.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tên", "Tên người chơi trống", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(txtTen.getText().length() > 35) {
					JOptionPane.showMessageDialog(null, "Tên quá dài", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
					return;
				}
				lbTen.setText(txtTen.getText());
				pnTen.remove(txtTen);
				if(l.Hard.isSelected()) {
					level = 1;
					soLanBan = 50;
					lbLanBan.setText("Số lần bắn: "+soLanBan);
				}
				xepTau.stop();
				pnTen.remove(btnLevel);
				pnComArea.setVisible(true);
				pnLabel.setVisible(true);
				pnAiban.setVisible(true);
				pnSouth.remove(pnChonTauOK);
				pnSouth.add(sc,BorderLayout.CENTER);
				pnSouth.add(pnSS, BorderLayout.SOUTH);
				pnSouth.remove(pnRdOK);
			}
		});
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtChat.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập tin nhắn","Tin nhắn trống", JOptionPane.WARNING_MESSAGE);
				}
				else {
					txtTB.setText(txtTB.getText()+"\nBạn: "+txtChat.getText());
					txtChat.setText(null);
				}
			}
		});
		
		btnAiban.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				btnAiban.setEnabled(false);
				pnAiban.setVisible(false);
				check = 1;
				if(aiBanTruoc()) {
					JOptionPane.showMessageDialog(null, "Máy bắn trước");
					mayban =1;
					mayBan();
				}
				else {
					JOptionPane.showMessageDialog(null, "Bạn là người bắn trước");
					ngBan = 1;
				}
			}
		});
	}
	
	protected void dongChonTau() { // đóng chọn tàu
		if( arrTau.isEmpty()== false) {
			for(JButton b: arrTau)
				b.setEnabled(false);
		}		
	}

	protected void hienThiMau() { // hiển thị màu của tàu khi xếp
		mT.kiemTraHuongTau();
		int vttau6 = mT.getVtTau6();
		int vttau5 = mT.getVtTau5();
		int vttau4 = mT.getVtTau4();
		int vttau3 = mT.getVtTau3();
		int vttau2 = mT.getVtTau2();
		for(int i = 0; i< row; i ++) {
			for(int j = 0 ; j< col ; j++) {
				arrBtn[i][j].removeMouseListener(mm);
				int gt = mT.getValue(i, j);
				if( gt == 0) {
					arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/bien.png"));
				}					
				else if ( gt == 1) {			
					if( vttau6 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/66dd.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/65d.png"));
						arrBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/64d.png"));
						arrBtn[i+3][j].setIcon(new ImageIcon("hinhAnh/63d.png"));
						arrBtn[i+4][j].setIcon(new ImageIcon("hinhAnh/62d.png"));
						arrBtn[i+5][j].setIcon(new ImageIcon("hinhAnh/61d.png"));
					}

					else {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/66nn.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/65n.png"));
						arrBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/64n.png"));
						arrBtn[i][j+3].setIcon(new ImageIcon("hinhAnh/63n.png"));
						arrBtn[i][j+4].setIcon(new ImageIcon("hinhAnh/62n.png"));
						arrBtn[i][j+5].setIcon(new ImageIcon("hinhAnh/61n.png"));
					}
				}
				else if( gt == 7 ) {
					if( vttau5 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/55d.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/54d.png"));
						arrBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/53d.png"));
						arrBtn[i+3][j].setIcon(new ImageIcon("hinhAnh/52d.png"));
						arrBtn[i+4][j].setIcon(new ImageIcon("hinhAnh/51d.png"));
					}

					else {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/55n.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/54n.png"));
						arrBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/53n.png"));
						arrBtn[i][j+3].setIcon(new ImageIcon("hinhAnh/52n.png"));
						arrBtn[i][j+4].setIcon(new ImageIcon("hinhAnh/51n.png"));
					}
				}
				else if( gt == 12 ) {
					if( vttau4 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/44d.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/43d.png"));
						arrBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/42d.png"));
						arrBtn[i+3][j].setIcon(new ImageIcon("hinhAnh/41d.png"));
					}

					else {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/44n.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/43n.png"));
						arrBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/42n.png"));
						arrBtn[i][j+3].setIcon(new ImageIcon("hinhAnh/41n.png"));
					}
				}
				else if( gt == 16 ) {
					if( vttau3 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/33d.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/32d.png"));
						arrBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/31d.png"));
					}

					else {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/33n.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/32n.png"));
						arrBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/31n.png"));
					}
					
				}
				else if( gt == 19 ){
					if( vttau2 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/22d.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/21d.png"));
					}

					else {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/22n.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/21n.png"));
					}
				}					
			}
		}
	}
	
	protected void xuLyChonViTri() { // mở khỏa arr button
		if(tauisSelec > 0) {
			for(int i =  0 ; i<row ; i++) {
				for(int j = 0 ; j < col; j++) {
					if(mT.getValue(i, j) == 0) {
						arrBtn[i][j].addMouseListener(mm);
					}
				}
			}					
		}
	}

	private boolean checkWin() { // kiếm tra người win
		if( dTau6 < 6 || dTau5 < 5 || dTau4 < 4 || dTau3 < 3 || dTau2 < 2)
			return false;
		return true;
	}

	private void xuLyWin() { // xử lý người win
		
		String lev = "Dễ";
		if(this.level == 1) {
			lev = "Khó";
		}
		Calendar cal = Calendar.getInstance();
		Date d = cal.getTime();
		NguoiChoi ngChoi= new NguoiChoi(lbTen.getText(), luotBan, sdf.format(d), lev, "Win");
		parent.arrNguoiChoi.add(ngChoi);
		parent.luuDuLieu(); // Lưu dữ liệu
		xuLyThua();
		if(parent.soundOn()) {
			win.play();
		}
		JOptionPane.showMessageDialog(
				pnSouth, "Chúc mừng bạn đã dành chiến thắng",
				"Chiến thắng", JOptionPane.DEFAULT_OPTION, new ImageIcon("hinhAnh/win.png"));
		ktHis = 0;
	}
		
	private boolean checkMayWin() { // kiểm tra máy win
		int maTranPhu[][] = new int[row][col];
		if(chonRd == 1) {
			maTranPhu = mTrandom.getMT();
			for(int i = 0 ; i< row; i++) {
				for(int j = 0 ; j < col ; j++) {
					if(maTranPhu[i][j] !=0) { // có một phần tử bất kì khác không.
						return false; // chưa win
					}
				}
			}
		}
		else {
			maTranPhu = mT.getMT();
			for(int i = 0 ; i< row; i++) {
				for(int j = 0 ; j < col ; j++) {
					if(maTranPhu[i][j] !=0) { // có một phần tử bất kì khác không.
						return false; // chưa win
					}
				}
			}
		}
		return true;
	}
	
	public void mayBan() { // máy bắn người
		if(thongMinh == 0) {
			mayThongMinh();
			thongMinh = 1;
		}
		int si = arrPoint.size();
		int mayChon = rd.nextInt(si);
		int i = arrPoint.get(mayChon).x;
		int j = arrPoint.get(mayChon).y;
		arrPoint.remove(mayChon);
		if(mayban == 1) {
			txtTB.setText(txtTB.getText()+"\nLượt bắn "+(++luotBan)+":");
		}
		txtTB.setText(txtTB.getText()+"\nMáy bắn vào vị trí ["+hamDoc(i)+"]["+(j+1)+"] của bạn.");
		if(chonRd == 1) {
			int gt=mTrandom.getValueMT(i, j);
			if(gt > 0) {
				arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/lu.png"));
				if( 1<= gt && gt <= 6 ) {
					txtTB.setText(txtTB.getText()+"\nBạn: Bắn trúng tàu Sân Bay!!!\n");
				}
				else if(7<= gt && gt <= 11) {
					txtTB.setText(txtTB.getText()+"\nBạn: Bắn trúng tàu Khu Trục!!!\n");
				}
				else if(12<= gt && gt <= 15) {
					txtTB.setText(txtTB.getText()+"\nBạn: Bắn trúng tàu Tuần Tra!!!\n");
				}
				else if(16<= gt && gt <= 18) {
					txtTB.setText(txtTB.getText()+"\nBạn: Bắn trúng tàu Ngư Lôi!!!\n");
				}
				else {
					txtTB.setText(txtTB.getText()+"\nBạn: Bắn trúng tàu Do Thám!!!\n");
				}
				mTrandom.setMaTrix(i, j);
				if(checkMayWin() == true) {
					xuLyMayWin();
					return;
				}
			}
			else {
				arrBtn[i][j].setIcon(null);
				arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/truot.png"));
				txtTB.setText(txtTB.getText()+"\nBạn: Không trúng tàu nào!!!\n");
			}
		}
		else {
			int gt=mT.getValue(i, j);
			if(gt > 0) {
				arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/lu.png"));
				if( 1<= gt && gt <= 6 ) {
					txtTB.setText(txtTB.getText()+"\nBạn: Bắn trúng tàu Sân Bay!!!\n");
				}
				else if(7<= gt && gt <= 11) {
					txtTB.setText(txtTB.getText()+"\nBạn: Bắn trúng tàu Khu Trục!!!\n");
				}
				else if(12<= gt && gt <= 15) {
					txtTB.setText(txtTB.getText()+"\nBạn: Bắn trúng tàu Tuần Tra!!!\n");
				}
				else if(16<= gt && gt <= 18) {
					txtTB.setText(txtTB.getText()+"\nBạn: Bắn trúng tàu Ngư Lôi!!!\n");
				}
				else {
					txtTB.setText(txtTB.getText()+"\nBạn: Bắn trúng tàu Do thám!!!\n");
				}
				mT.setMaTrix(i, j);
				if(checkMayWin() == true) {
					xuLyMayWin();
					return;
				}
			}
			else {
				arrBtn[i][j].setIcon(null);
				arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/truot.png"));
				txtTB.setText(txtTB.getText()+"\nBạn: Không trúng tàu nào!!!\n");
			}
		}
	}
	
	private void xuLyMayWin() { // xử lý máy win
		String lev = "Dễ";
		if(level == 1) {
			lev = "Khó";
		}
		Calendar cal = Calendar.getInstance();
		Date d = cal.getTime();
		NguoiChoi ngChoi= new NguoiChoi(lbTen.getText(), luotBan, sdf.format(d), lev, "Lose- Máy Win");
		parent.arrNguoiChoi.add(ngChoi);
		parent.luuDuLieu(); // Lưu dữ liệu
		xuLyThua();
		if(parent.soundOn()) {
			lose.play();
		}
		JOptionPane.showMessageDialog(
				pnSouth, "Rất đáng tiếc bạn đã bị thua máy mất rồi",
				"Thua", JOptionPane.DEFAULT_OPTION, new ImageIcon("hinhAnh/lose.png"));
		ktHis = 0;
	}
	
	public void hienThiTau() { // chọn rd
		mTrandom.kiemTraHuongTau();
		int vttau6 = mTrandom.getVtTau6();
		int vttau5 = mTrandom.getVtTau5();
		int vttau4 = mTrandom.getVtTau4();
		int vttau3 = mTrandom.getVtTau3();
		int vttau2 = mTrandom.getVtTau2();
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col ; j++) {
				int gt = mTrandom.getValueMT(i, j);
				if(gt == 0) {
					arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/bien.png"));
				}
				else if ( gt == 1) {
					if( vttau6 == 4) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/61d.png"));
						arrBtn[i-1][j].setIcon(new ImageIcon("hinhAnh/62d.png"));
						arrBtn[i-2][j].setIcon(new ImageIcon("hinhAnh/63d.png"));
						arrBtn[i-3][j].setIcon(new ImageIcon("hinhAnh/64d.png"));
						arrBtn[i-4][j].setIcon(new ImageIcon("hinhAnh/65d.png"));
						arrBtn[i-5][j].setIcon(new ImageIcon("hinhAnh/66dd.png"));
					}

					else if( vttau6 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/66dd.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/65d.png"));
						arrBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/64d.png"));
						arrBtn[i+3][j].setIcon(new ImageIcon("hinhAnh/63d.png"));
						arrBtn[i+4][j].setIcon(new ImageIcon("hinhAnh/62d.png"));
						arrBtn[i+5][j].setIcon(new ImageIcon("hinhAnh/61d.png"));
					}

					else if( vttau6 == 1) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/66nn.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/65n.png"));
						arrBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/64n.png"));
						arrBtn[i][j+3].setIcon(new ImageIcon("hinhAnh/63n.png"));
						arrBtn[i][j+4].setIcon(new ImageIcon("hinhAnh/62n.png"));
						arrBtn[i][j+5].setIcon(new ImageIcon("hinhAnh/61n.png"));
					}
					else {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/61n.png"));
						arrBtn[i][j-1].setIcon(new ImageIcon("hinhAnh/62n.png"));
						arrBtn[i][j-2].setIcon(new ImageIcon("hinhAnh/63n.png"));
						arrBtn[i][j-3].setIcon(new ImageIcon("hinhAnh/64n.png"));
						arrBtn[i][j-4].setIcon(new ImageIcon("hinhAnh/65n.png"));
						arrBtn[i][j-5].setIcon(new ImageIcon("hinhAnh/66nn.png"));
					}
				}
				else if( gt == 7 ) {
					if( vttau5 == 4) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/51d.png"));
						arrBtn[i-1][j].setIcon(new ImageIcon("hinhAnh/52d.png"));
						arrBtn[i-2][j].setIcon(new ImageIcon("hinhAnh/53d.png"));
						arrBtn[i-3][j].setIcon(new ImageIcon("hinhAnh/54d.png"));
						arrBtn[i-4][j].setIcon(new ImageIcon("hinhAnh/55d.png"));
					}

					else if( vttau5 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/55d.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/54d.png"));
						arrBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/53d.png"));
						arrBtn[i+3][j].setIcon(new ImageIcon("hinhAnh/52d.png"));
						arrBtn[i+4][j].setIcon(new ImageIcon("hinhAnh/51d.png"));
					}

					else if( vttau5 == 1) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/55n.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/54n.png"));
						arrBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/53n.png"));
						arrBtn[i][j+3].setIcon(new ImageIcon("hinhAnh/52n.png"));
						arrBtn[i][j+4].setIcon(new ImageIcon("hinhAnh/51n.png"));
					}
					else {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/51n.png"));
						arrBtn[i][j-1].setIcon(new ImageIcon("hinhAnh/52n.png"));
						arrBtn[i][j-2].setIcon(new ImageIcon("hinhAnh/53n.png"));
						arrBtn[i][j-3].setIcon(new ImageIcon("hinhAnh/54n.png"));
						arrBtn[i][j-4].setIcon(new ImageIcon("hinhAnh/55n.png"));
					}
				}
				else if( gt == 12 ) {
					if( vttau4 == 4) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/41d.png"));
						arrBtn[i-1][j].setIcon(new ImageIcon("hinhAnh/42d.png"));
						arrBtn[i-2][j].setIcon(new ImageIcon("hinhAnh/43d.png"));
						arrBtn[i-3][j].setIcon(new ImageIcon("hinhAnh/44d.png"));
					}

					else if( vttau4 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/44d.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/43d.png"));
						arrBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/42d.png"));
						arrBtn[i+3][j].setIcon(new ImageIcon("hinhAnh/41d.png"));
					}

					else if( vttau4 == 1) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/44n.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/43n.png"));
						arrBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/42n.png"));
						arrBtn[i][j+3].setIcon(new ImageIcon("hinhAnh/41n.png"));
					}
					else {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/41n.png"));
						arrBtn[i][j-1].setIcon(new ImageIcon("hinhAnh/42n.png"));
						arrBtn[i][j-2].setIcon(new ImageIcon("hinhAnh/43n.png"));
						arrBtn[i][j-3].setIcon(new ImageIcon("hinhAnh/44n.png"));
					}
				}
				else if( gt == 16 ) {
					if( vttau3 == 4) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/31d.png"));
						arrBtn[i-1][j].setIcon(new ImageIcon("hinhAnh/32d.png"));
						arrBtn[i-2][j].setIcon(new ImageIcon("hinhAnh/33d.png"));
					}

					else if( vttau3 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/33d.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/32d.png"));
						arrBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/31d.png"));
					}

					else if( vttau3 == 1) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/33n.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/32n.png"));
						arrBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/31n.png"));
					}
					else {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/31n.png"));
						arrBtn[i][j-1].setIcon(new ImageIcon("hinhAnh/32n.png"));
						arrBtn[i][j-2].setIcon(new ImageIcon("hinhAnh/33n.png"));
					}
				}
				else if( gt == 19 ) {
					if( vttau2 == 4) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/21d.png"));
						arrBtn[i-1][j].setIcon(new ImageIcon("hinhAnh/22d.png"));
					}

					else if( vttau2 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/22d.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/21d.png"));
					}

					else if( vttau2 == 1) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/22n.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/21n.png"));
					}
					else {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/21n.png"));
						arrBtn[i][j-1].setIcon(new ImageIcon("hinhAnh/22n.png"));
					}
				}
			}
		}
	}
	
	protected void hienThiMay6() { // hiển thị tàu của máy khi bắn nổ
		mTComputer.kiemTraHuongTau();
		int vttau6 = mTComputer.getVtTau6();
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col ; j++) {
				int gt = mTComputer.getValueMT(i, j);
				if ( gt == 1) {
					if( vttau6 == 4) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/61d.png"));
						comBtn[i-1][j].setIcon(new ImageIcon("hinhAnh/62d.png"));
						comBtn[i-2][j].setIcon(new ImageIcon("hinhAnh/63d.png"));
						comBtn[i-3][j].setIcon(new ImageIcon("hinhAnh/64d.png"));
						comBtn[i-4][j].setIcon(new ImageIcon("hinhAnh/65d.png"));
						comBtn[i-5][j].setIcon(new ImageIcon("hinhAnh/66dd.png"));
					}

					else if( vttau6 == 2) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/66dd.png"));
						comBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/65d.png"));
						comBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/64d.png"));
						comBtn[i+3][j].setIcon(new ImageIcon("hinhAnh/63d.png"));
						comBtn[i+4][j].setIcon(new ImageIcon("hinhAnh/62d.png"));
						comBtn[i+5][j].setIcon(new ImageIcon("hinhAnh/61d.png"));
					}
					
					else if( vttau6 == 1) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/66nn.png"));
						comBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/65n.png"));
						comBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/64n.png"));
						comBtn[i][j+3].setIcon(new ImageIcon("hinhAnh/63n.png"));
						comBtn[i][j+4].setIcon(new ImageIcon("hinhAnh/62n.png"));
						comBtn[i][j+5].setIcon(new ImageIcon("hinhAnh/61n.png"));
					}
					else {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/61n.png"));
						comBtn[i][j-1].setIcon(new ImageIcon("hinhAnh/62n.png"));
						comBtn[i][j-2].setIcon(new ImageIcon("hinhAnh/63n.png"));
						comBtn[i][j-3].setIcon(new ImageIcon("hinhAnh/64n.png"));
						comBtn[i][j-4].setIcon(new ImageIcon("hinhAnh/65n.png"));
						comBtn[i][j-5].setIcon(new ImageIcon("hinhAnh/66nn.png"));
					}
				}
			}
		}
	}
	
	protected void hienThiMay5() {
		mTComputer.kiemTraHuongTau();
		int vttau5 = mTComputer.getVtTau5();
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col ; j++) {
				int gt = mTComputer.getValueMT(i, j);
				if( gt == 7 ) {
					if( vttau5 == 4) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/51d.png"));
						comBtn[i-1][j].setIcon(new ImageIcon("hinhAnh/52d.png"));
						comBtn[i-2][j].setIcon(new ImageIcon("hinhAnh/53d.png"));
						comBtn[i-3][j].setIcon(new ImageIcon("hinhAnh/54d.png"));
						comBtn[i-4][j].setIcon(new ImageIcon("hinhAnh/55d.png"));
					}

					else if( vttau5 == 2) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/55d.png"));
						comBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/54d.png"));
						comBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/53d.png"));
						comBtn[i+3][j].setIcon(new ImageIcon("hinhAnh/52d.png"));
						comBtn[i+4][j].setIcon(new ImageIcon("hinhAnh/51d.png"));
					}
					
					else if( vttau5 == 1) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/55n.png"));
						comBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/54n.png"));
						comBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/53n.png"));
						comBtn[i][j+3].setIcon(new ImageIcon("hinhAnh/52n.png"));
						comBtn[i][j+4].setIcon(new ImageIcon("hinhAnh/51n.png"));
					}
					else {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/51n.png"));
						comBtn[i][j-1].setIcon(new ImageIcon("hinhAnh/52n.png"));
						comBtn[i][j-2].setIcon(new ImageIcon("hinhAnh/53n.png"));
						comBtn[i][j-3].setIcon(new ImageIcon("hinhAnh/54n.png"));
						comBtn[i][j-4].setIcon(new ImageIcon("hinhAnh/55n.png"));
					}
				}
			}
		}
	}
	
	protected void hienThiMay4() {
		mTComputer.kiemTraHuongTau();
		int vttau4 = mTComputer.getVtTau4();
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col ; j++) {
				int gt = mTComputer.getValueMT(i, j);
				if( gt == 12 ) {
					if( vttau4 == 4) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/41d.png"));
						comBtn[i-1][j].setIcon(new ImageIcon("hinhAnh/42d.png"));
						comBtn[i-2][j].setIcon(new ImageIcon("hinhAnh/43d.png"));
						comBtn[i-3][j].setIcon(new ImageIcon("hinhAnh/44d.png"));
					}

					else if( vttau4 == 2) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/44d.png"));
						comBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/43d.png"));
						comBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/42d.png"));
						comBtn[i+3][j].setIcon(new ImageIcon("hinhAnh/41d.png"));
					}
					
					else if( vttau4 == 1) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/44n.png"));
						comBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/43n.png"));
						comBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/42n.png"));
						comBtn[i][j+3].setIcon(new ImageIcon("hinhAnh/41n.png"));
					}
					else {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/41n.png"));
						comBtn[i][j-1].setIcon(new ImageIcon("hinhAnh/42n.png"));
						comBtn[i][j-2].setIcon(new ImageIcon("hinhAnh/43n.png"));
						comBtn[i][j-3].setIcon(new ImageIcon("hinhAnh/44n.png"));
					}
				}
			}
		}
	}
	
	protected void hienThiMay3() {
		mTComputer.kiemTraHuongTau();
		int vttau3 = mTComputer.getVtTau3();
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col ; j++) {
				int gt = mTComputer.getValueMT(i, j);
				if( gt == 16 ) {
					if( vttau3 == 4) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/31d.png"));
						comBtn[i-1][j].setIcon(new ImageIcon("hinhAnh/32d.png"));
						comBtn[i-2][j].setIcon(new ImageIcon("hinhAnh/33d.png"));
					}

					else if( vttau3 == 2) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/33d.png"));
						comBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/32d.png"));
						comBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/31d.png"));
					}
					
					else if( vttau3 == 1) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/33n.png"));
						comBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/32n.png"));
						comBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/31n.png"));
					}
					else {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/31n.png"));
						comBtn[i][j-1].setIcon(new ImageIcon("hinhAnh/32n.png"));
						comBtn[i][j-2].setIcon(new ImageIcon("hinhAnh/33n.png"));
					}
				}
			}
		}
	}
	
	protected void hienThiMay2() {
		mTComputer.kiemTraHuongTau();
		int vttau2 = mTComputer.getVtTau2();
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col ; j++) {
				int gt = mTComputer.getValueMT(i, j);
				if( gt == 19 ) {
					if( vttau2 == 4) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/21d.png"));
						comBtn[i-1][j].setIcon(new ImageIcon("hinhAnh/22d.png"));
					}

					else if( vttau2 == 2) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/22d.png"));
						comBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/21d.png"));
					}
					
					else if( vttau2 == 1) {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/22n.png"));
						comBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/21n.png"));
					}
					else {
						comBtn[i][j].setIcon(new ImageIcon("hinhAnh/21n.png"));
						comBtn[i][j-1].setIcon(new ImageIcon("hinhAnh/22n.png"));
					}
				}
			}
		}
	}

	protected char hamDoc(int i) {
		char x = 0;
		switch((i+1)) {
		case 1:
			x= 'A';
			break;
		case 2:
			x= 'B';
			break;
		case 3:
			x= 'C';
			break;
		case 4:
			x= 'D';
			break;
		case 5:
			x= 'E';
			break;
		case 6:
			x= 'F';
			break;
		case 7:
			x= 'G';
			break;
		case 8:
			x= 'H';
			break;
		case 9:
			x= 'I';
			break;
		case 10:
			x= 'K';
			break;
		}
		return x;
	}

	protected boolean aiBanTruoc() {
		int mayban = rd.nextInt(2);
		if(mayban == 1)
			return true;
		return false;
	}

	private void hienThiMauXep() { // hien thi mau cua tau
		mT.kiemTraHuongTau();
		int vttau6 = mT.getVtTau6();
		int vttau5 = mT.getVtTau5();
		int vttau4 = mT.getVtTau4();
		int vttau3 = mT.getVtTau3();
		int vttau2 = mT.getVtTau2();
		for(int i = 0; i< row; i ++) {
			for(int j = 0 ; j< col ; j++) {
				int gt = mT.getValue(i, j);
				if( gt == 0) {
					arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/bien.png"));
				}					
				else if ( gt == 1) {
					if( vttau6 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/66dd.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/65d.png"));
						arrBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/64d.png"));
						arrBtn[i+3][j].setIcon(new ImageIcon("hinhAnh/63d.png"));
						arrBtn[i+4][j].setIcon(new ImageIcon("hinhAnh/62d.png"));
						arrBtn[i+5][j].setIcon(new ImageIcon("hinhAnh/61d.png"));
					}

					else {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/66nn.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/65n.png"));
						arrBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/64n.png"));
						arrBtn[i][j+3].setIcon(new ImageIcon("hinhAnh/63n.png"));
						arrBtn[i][j+4].setIcon(new ImageIcon("hinhAnh/62n.png"));
						arrBtn[i][j+5].setIcon(new ImageIcon("hinhAnh/61n.png"));
					}
					
				}
				else if( gt == 7 ) {
					if( vttau5 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/55d.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/54d.png"));
						arrBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/53d.png"));
						arrBtn[i+3][j].setIcon(new ImageIcon("hinhAnh/52d.png"));
						arrBtn[i+4][j].setIcon(new ImageIcon("hinhAnh/51d.png"));
					}

					else {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/55n.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/54n.png"));
						arrBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/53n.png"));
						arrBtn[i][j+3].setIcon(new ImageIcon("hinhAnh/52n.png"));
						arrBtn[i][j+4].setIcon(new ImageIcon("hinhAnh/51n.png"));
					}
					
				}
				else if( gt == 12 ) {
					if( vttau4 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/44d.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/43d.png"));
						arrBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/42d.png"));
						arrBtn[i+3][j].setIcon(new ImageIcon("hinhAnh/41d.png"));
					}

					else if( vttau4 == 1) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/44n.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/43n.png"));
						arrBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/42n.png"));
						arrBtn[i][j+3].setIcon(new ImageIcon("hinhAnh/41n.png"));
					}
				}
				else if( gt == 16 ) {
					if( vttau3 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/33d.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/32d.png"));
						arrBtn[i+2][j].setIcon(new ImageIcon("hinhAnh/31d.png"));
					}

					else if( vttau3 == 1) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/33n.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/32n.png"));
						arrBtn[i][j+2].setIcon(new ImageIcon("hinhAnh/31n.png"));
					}
				}
				else if( gt == 19 ) {
					if( vttau2 == 2) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/22d.png"));
						arrBtn[i+1][j].setIcon(new ImageIcon("hinhAnh/21d.png"));
					}

					else if( vttau2 == 1) {
						arrBtn[i][j].setIcon(new ImageIcon("hinhAnh/22n.png"));
						arrBtn[i][j+1].setIcon(new ImageIcon("hinhAnh/21n.png"));
					}
				}					
			}
		}
	}
	
	protected void mayThongMinh() { // máy thắng sau 55 lượt bắn
		if( chonRd == 1) {
			int lan = 0;
			while( lan <= 45) {
				int s= arrPoint.size();
				int index = rd.nextInt(s);
				int i = arrPoint.get(index).x;
				int j = arrPoint.get(index).y;
				if( mTrandom.getValueMT(i, j) == 0) {
					arrPoint.remove(index);
					lan++;
				}
			}
		}
		else {
			int lan = 0;
			while( lan <= 45) {
				int s = arrPoint.size();
				int index = rd.nextInt(s);
				int i = arrPoint.get(index).x;
				int j = arrPoint.get(index).y;
				if( mT.getValue(i, j) == 0) {
					arrPoint.remove(index);
					lan++;
				}
			}
		}
	}
}




