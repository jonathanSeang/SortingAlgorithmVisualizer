package View;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import Model.NumsModel;

public class BarsView extends JFrame implements IObserver{

	private final int FRAME_WIDTH = 500;
	private final int FRAME_HEIGHT = 500;
	private final int SCALER = 10;

	private NumsModel numsModel;	
	
	public BarsView(NumsModel numsModel) {
		this.numsModel = numsModel;
		this.numsModel.add(this);
		
		createFrame();
		
		
	}
	
	private void createFrame() {
		
		setTitle("Bars View");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
		int[] currSorting = numsModel.getCurrSorting();
		int arrSize = currSorting.length;
		
		//x, y, width, height
		for (int i = 0; i < currSorting.length; i++) {
			g2.fillRect(FRAME_WIDTH/arrSize*i, FRAME_HEIGHT-(currSorting[i]*SCALER), FRAME_WIDTH/arrSize, (currSorting[i]*SCALER));
		}
		
	}

	@Override
	public void update() {
		repaint();
	}
	
}
