package com.minase.maidchan.maidcrypt;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.sun.awt.AWTUtilities;

public class FrameDnD extends JFrame {

	private static final long serialVersionUID = -6454699649727981115L;
	
	JLabel balloon, maidchan;
	JTextField fileloc;
	JPasswordField passfield;
	JButton mount, exit;
	JComboBox<String> driveLetter;
	
	Actions action;
	
	//CONSTANTS
	int taskbarSize = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom;
		
	public FrameDnD() {
		action = new Actions(this);
		
		setUndecorated(true);
		AWTUtilities.setWindowOpaque(this, false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(470, 390);
		setLayout(null);
		setTitle("MaidCrypt by Rin Minase");
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource("img/icon.png")).getImage());
		
		balloon = new JLabel(new ImageIcon(getClass().getResource("img/balloon.png")));
		balloon.setBounds(10, 10, 350, 322);
				
		maidchan = new JLabel(new ImageIcon(getClass().getResource("img/maid-chan.png")));
		maidchan.setBounds(getWidth()-198-5, getHeight()-taskbarSize-290, 198, 300);
				
		JLabel msgTitle = new JLabel("MaidCrypt v1.1", SwingConstants.CENTER);
		msgTitle.setBounds(0, 30, 340, 45);
		msgTitle.setFont(msgTitle.getFont().deriveFont(24f));
		
		JLabel lblFile = new JLabel("File Location", SwingConstants.CENTER);
		lblFile.setBounds(20, 90, 250, 15);
				
		fileloc = new JTextField();
		fileloc.setBounds(20, 110, 250, 35);
		fileloc.setFont(fileloc.getFont().deriveFont(16f));
		
		JLabel lblPass = new JLabel("Password", SwingConstants.CENTER);
		lblPass.setBounds(40, 155, 210, 15);
		
		passfield = new JPasswordField();
		passfield.setBounds(40, 170, 210, 35);
		passfield.setFont(passfield.getFont().deriveFont(16f));
		passfield.setHorizontalAlignment(SwingConstants.CENTER);
		
		driveLetter = new JComboBox<String>(new String[] {"O", "P", "R", "S", "T"});
		driveLetter.setBounds(50, 220, 50, 30);
		
		mount = new JButton("Equip!");
		mount.setBounds(110, 220, 130, 30);
		
		exit = new JButton("Exit");
		exit.setBounds(50, 260, 190, 30);

		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch(Exception e) { System.err.println(e.getMessage()); }

		addDragNDrop();
		
		balloon.add(msgTitle);
		balloon.add(fileloc);
		balloon.add(passfield);
		balloon.add(lblFile);
		balloon.add(lblPass);
		balloon.add(driveLetter);
		balloon.add(mount);
		balloon.add(exit);
		
		add(maidchan);
		add(balloon);

//		balloon.addMouseMotionListener(action);
//		balloon.addMouseListener(action);
		
		fileloc.addKeyListener(action);
		mount.addActionListener(action);
		exit.addActionListener(action);
		
		setVisible(true);
	}
	
	@SuppressWarnings("serial")
	private void addDragNDrop() {
		balloon.setDropTarget(new DropTarget() {
	        @SuppressWarnings("unchecked")
	        public synchronized void drop(DropTargetDropEvent evt) {
	            try {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                List<File> droppedFiles = (List<File>)evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
	                for (File file : droppedFiles) { 
	                	fileloc.setText(file.getAbsolutePath()); 
	                	fileloc.setToolTipText(fileloc.getText()); 
	                }
	            } catch (Exception ex) { ex.getMessage(); };
	        }
	    });
		
		fileloc.setDropTarget(new DropTarget() {
	        @SuppressWarnings("unchecked")
	        public synchronized void drop(DropTargetDropEvent evt) {
	            try {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                List<File> droppedFiles = (List<File>)evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
	                for (File file : droppedFiles) { 
	                	fileloc.setText(file.getAbsolutePath()); 
	                	fileloc.setToolTipText(fileloc.getText()); 
	                }
	            } catch (Exception ex) { ex.getMessage(); };
	        }
	    });
		
		passfield.setDropTarget(new DropTarget() {
	        @SuppressWarnings("unchecked")
	        public synchronized void drop(DropTargetDropEvent evt) {
	            try {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                List<File> droppedFiles = (List<File>)evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
	                for (File file : droppedFiles) { 
	                	fileloc.setText(file.getAbsolutePath()); 
	                	fileloc.setToolTipText(fileloc.getText()); 
	                }
	            } catch (Exception ex) { ex.getMessage(); };
	        }
	    });
	}
	
	public static void main(String[] args) {
		new FrameDnD();
	}
	
	private class Actions implements
		ActionListener,
		MouseMotionListener,
		MouseListener,
		KeyListener {
		
		@SuppressWarnings("unused")
		FrameDnD dnd;
		
		public Actions(FrameDnD dnd) {
			this.dnd = dnd;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) { }

		@Override
		public void mouseEntered(MouseEvent arg0) {
			System.err.println(1);
		}

		@Override
		public void mouseExited(MouseEvent arg0) { }

		@Override
		public void mousePressed(MouseEvent arg0) { }

		@Override
		public void mouseReleased(MouseEvent arg0) { }

		@Override
		public void mouseDragged(MouseEvent arg0) { }

		@Override
		public void mouseMoved(MouseEvent arg0) {
//			System.out.println(arg0.getX() + ", " + arg0.getY());
//			System.err.println(arg0.getXOnScreen() + ", " + arg0.getYOnScreen());
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource().equals(exit)) {
				if (JOptionPane.showConfirmDialog(null, "\nArt thou sure of thy action?\nFrom : Maid-chan\n ", 
						"メイドーちゃん (Maid-chan)", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
						new ImageIcon(getClass().getResource("img/tray-128x83.png"))) == 0) {
					System.exit(0);
				}
			} else if (arg0.getSource().equals(mount)) {
				AddressFormatter addFormat = new AddressFormatter(fileloc.getText());
				System.out.println(addFormat.getLocation() + "\n" + addFormat.getFileName());
				
				
				
				if (addFormat.isCorrectInput()) {
					/**	
					 * MOUNT	 
					 */
					
					if (mount.getText().equals("Equip!")) {
						mount.setText("Unequip!");
					} else {
						mount.setText("Equip!");
					}
				}
			}
		}

		@Override
		public void keyPressed(KeyEvent arg0) { }

		@Override
		public void keyReleased(KeyEvent arg0) {
			if (arg0.getSource().equals(fileloc)) { fileloc.setToolTipText(fileloc.getText()); }
		}

		@Override
		public void keyTyped(KeyEvent arg0) { }
	}	
}
