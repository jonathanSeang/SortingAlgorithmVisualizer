package View;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.NumsModel;

public class NumbersView extends JFrame implements IObserver{
	
	private JPanel mainPanel;
	private JLabel elementsLabel;
	
	private NumsModel numsModel;		
	
	public NumbersView(NumsModel numsModel) {
		this.numsModel = numsModel;
		this.numsModel.add(this);
		
		createFrame();
		createMainPanel();
		pack();
	}
	
	private void createFrame() {
		
		setTitle("Numbers View");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	private void createMainPanel() {
		
		mainPanel = new JPanel();
		
		elementsLabel = new JLabel();
		setElementsLabelText();
		mainPanel.add(elementsLabel);
		
		this.add(mainPanel);
		
		mainPanel.revalidate();
		mainPanel.repaint();
		
	}
	
	private void setElementsLabelText() {
		
		String newText = "";
		
		for(int num: numsModel.getCurrSorting()) {
			newText += num + " | ";
		}
		
		elementsLabel.setText(newText);
		
	}
	
	@Override
	public void update() {
		setElementsLabelText();
		mainPanel.revalidate();
		mainPanel.repaint();
	}

}
