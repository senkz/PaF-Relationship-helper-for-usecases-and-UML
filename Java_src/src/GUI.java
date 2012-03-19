import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.GUIController;

public class GUI {
	private GUIController guic = new GUIController();
	private JTextField filename = new JTextField(), dir = new JTextField();
	private JFrame frame = new JFrame();
	private JFileChooser fileBrowser = new JFileChooser();

	public GUI() {
		frame.setLayout(new BorderLayout());
		frame.getContentPane().setBackground(Color.WHITE);
		
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setResizable(false);
		
		JButton readButton = new JButton("read file");
		readButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int rVal = fileBrowser.showOpenDialog(frame);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					filename.setText(fileBrowser.getSelectedFile().getName());
					dir.setText(fileBrowser.getCurrentDirectory().toString());
					guic.read(dir.getText()+"\\"+filename.getText());
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
					filename.setText("You pressed cancel");
					dir.setText("");
				}
			}
		});

		JButton reportButton = new JButton("generate report");
		reportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				guic.generateReport();
			}
		});		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(readButton);
		buttonPanel.add(reportButton);
		
		JPanel drawPanel = new JPanel();
		drawPanel.setPreferredSize(new Dimension(400,400));
		drawPanel.setBackground(Color.RED);
		drawPanel.setForeground(Color.BLACK);

		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.add(drawPanel, BorderLayout.CENTER);

		Graphics g = drawPanel.getGraphics();
		g.fillRect(40, 160, 50, 160);
	}
}