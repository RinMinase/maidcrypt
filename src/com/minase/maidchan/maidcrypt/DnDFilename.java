package com.minase.maidchan.maidcrypt;

import java.awt.EventQueue;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class DnDFilename extends JFrame {
	private JPanel contentPane;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DnDFilename frame = new DnDFilename();
					frame.setVisible(true);
				} catch (Exception e) { e.getMessage(); }
			}
		});
	}

	public DnDFilename() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 90);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 670, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField.setDropTarget(new DropTarget() {
	        @SuppressWarnings("unchecked")
	        public synchronized void drop(DropTargetDropEvent evt) {
	            try {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                List<File> droppedFiles = (List<File>) evt
	                        .getTransferable().getTransferData(
	                                DataFlavor.javaFileListFlavor);
	                for (File file : droppedFiles) { textField.setText(file.getAbsolutePath()); System.out.println(file.toString()); }
	            } catch (Exception ex) { ex.getMessage(); };
	        }
	    });
		
		contentPane.setDropTarget(new DropTarget() {
	        @SuppressWarnings("unchecked")
			public synchronized void drop(DropTargetDropEvent evt) {
	            try {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                List<File> droppedFiles = (List<File>) evt
	                        .getTransferable().getTransferData(
	                                DataFlavor.javaFileListFlavor);
	                for (File file : droppedFiles) { textField.setText(file.getAbsolutePath()); }
	            } catch (Exception ex) { ex.getMessage(); }
	        }
	    });
		
		setDropTarget(new DropTarget() {
	        @SuppressWarnings("unchecked")
	        public synchronized void drop(DropTargetDropEvent evt) {
	            try {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                List<File> droppedFiles = (List<File>) evt
	                        .getTransferable().getTransferData(
	                                DataFlavor.javaFileListFlavor);
	                for (File file : droppedFiles) { textField.setText(file.getAbsolutePath()); }
	            } catch (Exception ex) { ex.getMessage(); }
	        }
	    });
	}
}
