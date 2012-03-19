import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GUIController;


public class GUI {
	private GUIController guic;

	public GUI() {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setSize(400,480);
		frame.setVisible(true);
		
		
		JButton readButton = new JButton("read file");
		readButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
			}
		});
		

		JButton reportButton = new JButton("generate report");
		reportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
			}
		});
		
		
		JPanel buttonPannel = new JPanel();
		buttonPannel.add(readButton);
		buttonPannel.add(reportButton);
		
		frame.add(buttonPannel, BorderLayout.NORTH);
		
	}
}