package gameBanTau;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import javafx.scene.layout.Border;


public class BanTau extends JFrame implements Serializable{
	public ArrayList<NguoiChoi> arrNguoiChoi=new ArrayList<NguoiChoi>();
	private JMenuBar mnuBar;
	private JMenu  mnuOption;
	private JMenuItem mnuItemExit, mnuItemHelp, mnuItemHistory , mnuItemReStart ;
	private JCheckBoxMenuItem mnuItemSound ;
	private JTextField txtChat;
	private JButton btnSend;
	final File f = new File("C:/bantau/nguoiChoi.hust");	// đường dẫn lưu ls
	private ChonTau chonTau;
	public BanTau(){
		super("Bắn tàu");
		addControl();
		addEvents();
		setSize(1230, 890);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addControl() {
		Container  con = getContentPane();
		
		mnuBar = new JMenuBar();
		setJMenuBar(mnuBar);
		(mnuOption = new JMenu("Options")).setMnemonic('O');
		mnuBar.add(mnuOption);
		mnuItemReStart = new JMenuItem("New Game" , 'e');
		mnuItemHistory= new JMenuItem("History", 'i');
		mnuItemHelp = new JMenuItem("Help", 'e');
		mnuItemExit = new JMenuItem("Exit" , 'x');
		mnuItemSound = new JCheckBoxMenuItem("Sound");
		mnuItemSound.setSelected(true);
		mnuOption.add(mnuItemReStart);
		mnuOption.addSeparator();
		mnuOption.add(mnuItemSound);
		mnuOption.addSeparator();
		mnuOption.add(mnuItemHistory);
		mnuOption.addSeparator();
		mnuOption.add(mnuItemHelp);
		mnuOption.addSeparator();
		mnuOption.add(mnuItemExit);
		
		
		mnuItemReStart.setEnabled(true);
		if (f.exists()) {
			arrNguoiChoi = docDuLieu();// đọc file lên luôn
		}
		else {
			File fi = new File("C:\\bantau");
			fi.mkdir();
		}
		
		JPanel pnMain = new JPanel(new BorderLayout());
		pnMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 50, 10));
		pnMain.setBackground(new Color(119, 175, 159));
		
		JPanel pnCenter = new JPanel(new BorderLayout());
		chonTau = new ChonTau(this);
		pnCenter.add(chonTau, BorderLayout.CENTER);
		
		pnMain.add(pnCenter, BorderLayout.CENTER);
		con.add(pnMain);
	}
	
	private void addEvents() {
		mnuItemSound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if( mnuItemSound.isSelected() == false) {
						chonTau.xepTau.stop();
						chonTau.lose.stop();
						chonTau.win.stop();
						chonTau.audioNen.stop();
						chonTau.chonNhac = 0;
					}
			}
		});
		mnuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chonTau.luotBan > 0) {
					if( chonTau.ktHis == 0) {
					}
					else {
						String lev = "Dễ";
						if(chonTau.level == 1) {
							lev = "Khó";
						}
						Calendar cal = Calendar.getInstance();
						Date d = cal.getTime();
						NguoiChoi ngChoi= new NguoiChoi(chonTau.lbTen.getText(), chonTau.luotBan, chonTau.sdf.format(d), lev, "Chưa hoàn thành");
						BanTau.this.arrNguoiChoi.add(ngChoi);
						BanTau.this.luuDuLieu(); // Lưu dữ liệu
					}
					System.exit(0);
				}
				else {
					System.exit(0);
				}
			}
		});
		mnuItemHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Hướng dẫn\nNgười chơi", "Hướng dẫn", JOptionPane.DEFAULT_OPTION);
			}
		});
		mnuItemReStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chonTau.xepTau.stop();
				chonTau.audioNen.stop();
				chonTau.win.stop();
				chonTau.lose.stop();
				if(chonTau.luotBan > 0) {
					if(chonTau.ktHis == 0) {		
					}
					else {
						String lev = "Dễ";
						if(chonTau.level == 1) {
							lev = "Khó";
						}
						Calendar cal = Calendar.getInstance();
						Date d = cal.getTime();
						NguoiChoi ngChoi= new NguoiChoi(chonTau.lbTen.getText(), chonTau.luotBan, chonTau.sdf.format(d), lev, "Chưa hoàn thành");
						BanTau.this.arrNguoiChoi.add(ngChoi);
						BanTau.this.luuDuLieu(); // Lưu dữ liệu
					}
					BanTau.this.dispose();
					new BanTau();
				}
				else {
					BanTau.this.dispose();
					new BanTau();
				}
			}
		});
		mnuItemHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arrNguoiChoi.isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Chưa có lịch sử chơi");
					return;
				}
				History his = new History(BanTau.this, "History", arrNguoiChoi);
			}
		});
	}

	public ArrayList<NguoiChoi> docDuLieu(){ // đọc dữ liệu từ file
		ArrayList<NguoiChoi> arrNgChoi = null;
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object ob = ois.readObject();
			ois.close();
			fis.close();
			arrNgChoi = (ArrayList<NguoiChoi>) ob;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrNgChoi;
	}
	
	public void luuDuLieu(){ // lưu dữ liệu vào file
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(arrNguoiChoi);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addArr(NguoiChoi ng) {
		arrNguoiChoi.add(ng);
	}
	
	public boolean soundOn() {
		return mnuItemSound.isSelected();
	}
}
