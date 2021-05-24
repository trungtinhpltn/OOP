package gameBanTau;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Level extends JDialog{
	public JRadioButton Easy, Hard;
	private JButton btnOk;
	private Font font = new Font("arial", Font.PLAIN, 16);
	private ButtonGroup rdgroup;
	public Level () {
		this.setTitle("Level");
		setModal(true);
		addControls();
		addEvents();
	}
	private void addControls() {
		Container con = getContentPane();

		con.setLayout(new BorderLayout());
		
		JPanel pnRadio = new JPanel();
		pnRadio.setLayout(new FlowLayout());
		pnRadio.setBackground(Color.LIGHT_GRAY);
		JLabel lbLevel = new JLabel("Level:");
		lbLevel.setFont(font);
		JPanel pnRd = new JPanel();
		pnRd.setLayout(new GridLayout(2, 1));
		pnRd.setBackground(Color.LIGHT_GRAY);
		Easy = new JRadioButton("Easy", true);
		Hard = new JRadioButton("Hard");
		Easy.setFont(font);
		Hard.setFont(font);
		pnRd.add(Easy); pnRd.add(Hard);
		pnRadio.add(lbLevel); pnRadio.add(pnRd);
		rdgroup = new ButtonGroup();
		rdgroup.add(Easy); rdgroup.add(Hard);
		
		con.add(pnRadio,BorderLayout.CENTER);
	}
	private void addEvents() {
		Easy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xuLyThoat();
			}
		});
		Hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuLyThoat();
			}
		});
	}
	protected void xuLyThoat() {
		this.dispose();
	}

	public void showWindow() {
		this.setSize(200, 120);
		this.setLocationRelativeTo(null);;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
}