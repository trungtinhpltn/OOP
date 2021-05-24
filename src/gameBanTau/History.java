package gameBanTau;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class History extends JDialog {
	DefaultTableModel dtm;
	JTable table;
	JButton btnXoaLS;
	ArrayList<NguoiChoi> array=null;
	BanTau bt;
	History(BanTau parent , String title , ArrayList<NguoiChoi> arr){		
		super(parent , title);
		array = arr;
		bt = parent;
		addControl();
		addEvents();
		setModal(true);
		setSize(800,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private void addEvents() {
		for(NguoiChoi ng : array) {
			Vector<Object> vec = new Vector<Object>();
			vec.add(ng.getTenNgChoi());
			vec.add(ng.getThoiGianChoi());
			vec.add(ng.getLevel());
			vec.add(ng.getSoLanBan());
			String[] tools = {"Start","Finish","Wall", "Eraser"};
			JComboBox<String> cb = new JComboBox<String>(tools);
			vec.add(cb);
			dtm.addRow(vec);
		}
		btnXoaLS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bt.arrNguoiChoi.clear();
				bt.luuDuLieu();
				History.this.dispose();
			}
		});
	}
	private void addControl() {
		dtm = new DefaultTableModel();
		dtm.addColumn("Tên người chơi");
		dtm.addColumn("Thời gian chơi");
		dtm.addColumn("Level");
		dtm.addColumn("Số lần bắn");
		dtm.addColumn("Ghi Chú");
		table = new JTable(dtm);
		table.setRowHeight(30);
		JScrollPane sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JPanel pnBtn = new JPanel();
		btnXoaLS = new JButton("Delete All", new ImageIcon("hinhAnh/thungRac.png"));
		pnBtn.add(btnXoaLS);
		Container con = getContentPane();
		JPanel pnMain = new JPanel(new BorderLayout());
		pnMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 50, 20));
		pnMain.add(sc , BorderLayout.CENTER); pnMain.add(pnBtn, BorderLayout.SOUTH);
		con.add(pnMain);
	}
}
