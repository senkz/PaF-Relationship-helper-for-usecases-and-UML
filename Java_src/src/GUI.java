import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import model.DiagramObject;
import model.UMLCRUD;
import model.UMLClass;
import model.UMLUsecase;
import controller.GUIController;

public class GUI implements ActionListener {
	private GUIController guic = new GUIController();
	private JTextField filename = new JTextField(), dir = new JTextField();
	private JFrame frame = new JFrame();
	private JFileChooser fileBrowser = new JFileChooser();
	private JComboBox<String> exportType = new JComboBox<String>();
	private JComboBox<String> reportType = new JComboBox<String>();
	private JPanel modelDropdowns = new JPanel();
	private JPanel crudPanel = new JPanel();
	private JPanel drawPanel = new JPanel();
	private UMLCRUD crud;
	private JCheckBox create = new JCheckBox("Create");
	private JCheckBox read = new JCheckBox("Read");
	private JCheckBox update = new JCheckBox("Update");
	private JCheckBox delete = new JCheckBox("Delete");
	private UMLUsecase usecase;
	private JComboBox<UMLClass> UMLClassCB = new JComboBox<UMLClass>();
	private JComboBox<UMLUsecase> UMLUsecaseCB = new JComboBox<UMLUsecase>();

	public GUI() {
		frame.setLayout(new BorderLayout());
		frame.getContentPane().setBackground(Color.WHITE);
		
		frame.setSize(700,120);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JButton readButton = new JButton("read file");
		readButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int rVal = fileBrowser.showOpenDialog(frame);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					filename.setText(fileBrowser.getSelectedFile().getName());
					dir.setText(fileBrowser.getCurrentDirectory().toString());
					guic.read(dir.getText()+"\\"+filename.getText(), drawPanel);
					refreshUMLDropdownPanel();
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
					filename.setText("You pressed cancel");
					dir.setText("");
				}
			}
		});
		
		JLabel chooseExportType = new JLabel("Choose export format");
		exportType.addItem("xmlmd");
				
		JButton exportButton = new JButton("save file");
		exportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int rVal = fileBrowser.showSaveDialog(frame);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					filename.setText(fileBrowser.getSelectedFile().getName());
					dir.setText(fileBrowser.getCurrentDirectory().toString());
					guic.write(dir.getText()+"\\"+filename.getText(), (String) exportType.getSelectedItem());
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
					filename.setText("You pressed cancel");
					dir.setText("");
				}
			}
		});
		
		JPanel exportPanel = new JPanel();
		exportPanel.setLayout(new GridLayout(3,1));
		exportPanel.add(chooseExportType);
		exportPanel.add(exportType);
		exportPanel.add(exportButton);
		
		JLabel chooseReportType = new JLabel("Choose report format");
		reportType.addItem("crud");

		JButton reportButton = new JButton("generate report");
		reportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JFrame popup = new JFrame((String) reportType.getSelectedItem());
				popup.setSize(500,500);
				popup.add(guic.generateReport((String) reportType.getSelectedItem()));
				popup.setVisible(true);
			}
		});		
		
		JPanel reportPanel = new JPanel();
		exportPanel.setLayout(new GridLayout(2,1));
		exportPanel.add(chooseReportType);
		exportPanel.add(reportType);
		exportPanel.add(reportButton);
		
		drawPanel.setPreferredSize(new Dimension(400,400));
		drawPanel.setBackground(Color.WHITE);
		drawPanel.setForeground(Color.BLACK);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(readButton);
		buttonPanel.add(exportPanel);
		buttonPanel.add(reportPanel);
		
		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.add(modelDropdowns, BorderLayout.SOUTH);
		
		JFrame jf = new JFrame();
		jf.setLayout(new BorderLayout());
		jf.getContentPane().setBackground(Color.WHITE);
		
		jf.setSize(500,500);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		jf.add(drawPanel, BorderLayout.CENTER);
		jf.setResizable(false);
		

		UMLClassCB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if((UMLClass)UMLClassCB.getSelectedItem() != null && (UMLUsecase)UMLUsecaseCB.getSelectedItem() != null)
					refreshCrud((UMLClass)UMLClassCB.getSelectedItem(), (UMLUsecase)UMLUsecaseCB.getSelectedItem());	
			}
		});
		
		UMLUsecaseCB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if((UMLClass)UMLClassCB.getSelectedItem() != null && (UMLUsecase)UMLUsecaseCB.getSelectedItem() != null)
					refreshCrud((UMLClass)UMLClassCB.getSelectedItem(), (UMLUsecase)UMLUsecaseCB.getSelectedItem());	
			}
		});
		
		modelDropdowns.setLayout(new BorderLayout());
		modelDropdowns.add(UMLClassCB,BorderLayout.EAST);
		modelDropdowns.add(UMLUsecaseCB,BorderLayout.WEST);
		modelDropdowns.add(crudPanel,BorderLayout.CENTER);
	}
	
	private void refreshUMLDropdownPanel() {
		UMLClassCB.removeAllItems();
		UMLUsecaseCB.removeAllItems();
		
		if(guic.getModel() == null) {
			return;
		}
		
		for(DiagramObject dio : guic.getModel().getDiagramObjects()) {
			if(dio.getClass() == UMLClass.class) {
				UMLClassCB.addItem((model.UMLClass) dio);
			}
			if(dio.getClass() == UMLUsecase.class) {
				UMLUsecaseCB.addItem((model.UMLUsecase) dio);
			}
		}
		
		//refreshCrud((UMLClass)UMLClass.getSelectedItem(), (UMLUsecase)UMLUsecase.getSelectedItem());
		modelDropdowns.updateUI();
	}
	
	private void refreshCrud(UMLClass uclass,final UMLUsecase usecase) {	
		crudPanel.removeAll();
		this.usecase = usecase;
		crud = new UMLCRUD(uclass);
		
		create.setSelected(false);
		read.setSelected(false);
		update.setSelected(false);
		delete.setSelected(false);
		
		if(guic.getCrud(uclass, usecase)!=null) {
			crud = guic.getCrud(uclass, usecase);
			create.setSelected(crud.getCreate());
			read.setSelected(crud.getRead());
			update.setSelected(crud.getUpdate());
			delete.setSelected(crud.getDelete());
		}
		
		create.addActionListener(this);
		read.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		
		crudPanel.setLayout(new GridLayout(1,4));
		crudPanel.add(create);
		crudPanel.add(read);
		crudPanel.add(update);
		crudPanel.add(delete);
		crudPanel.updateUI();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JCheckBox source = (JCheckBox) (event.getSource());
		if(source.equals(create)) {
			crud.setCreate(source.isSelected());
		} else if(source.equals(read)) {
			crud.setRead(source.isSelected());
		} else if(source.equals(update)) {
			crud.setUpdate(source.isSelected());
		} else {
			crud.setDelete(source.isSelected());
		}
		usecase.addCRUD(crud);
	}
}