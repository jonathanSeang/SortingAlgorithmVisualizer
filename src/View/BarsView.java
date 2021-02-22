package View;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import Model.NumsModel;

public class BarsView extends JFrame implements IObserver{

	private final int FRAME_DIMENSIONS = 1000;

	private NumsModel numsModel;	
	
	public BarsView(NumsModel numsModel) {
		this.numsModel = numsModel;
		this.numsModel.add(this);
		
		createFrame();
		
		
	}
	
	private void createFrame() {
		
		setTitle("Bars View");
		setSize(FRAME_DIMENSIONS, FRAME_DIMENSIONS);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
		int[] currSorting = numsModel.getCurrSorting();
		int arrSize = currSorting.length;
		int scaler = FRAME_DIMENSIONS/arrSize;
		
		//x, y, width, height
		for (int i = 0; i < currSorting.length; i++) {
			g2.fillRect(FRAME_DIMENSIONS/arrSize*i, FRAME_DIMENSIONS-(currSorting[i]*scaler), FRAME_DIMENSIONS/arrSize, (currSorting[i]*scaler));
		}
		
	}

	@Override
	public void update() {
		repaint();
	}
	
}
