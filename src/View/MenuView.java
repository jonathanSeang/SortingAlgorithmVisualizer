package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.concurrent.BlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import Controller.Message.ExecuteBubbleMessage;
import Controller.Message.ExecuteHeapMessage;
import Controller.Message.ExecuteMergeMessage;
import Controller.Message.ExecuteQuickMessage;
import Controller.Message.Message;

public class MenuView extends JFrame {

	private JPanel settingsPanel;
	private JLabel sizeLabel;
	private JSlider sizeSlider;
	private JLabel speedLabel;	
	private JSlider speedSlider;
	
	private JPanel algorithmsPanel;
	private JButton bubbleButton;
	private JButton mergeButton;
	private JButton heapButton;
	private JButton quickButton;
	
	private JPanel controllerPanel;
	private JButton prevStepButton;
	private JButton nextStepButton;
	private JButton playButton;
	private JButton pauseButton;
	private JButton terminateButton; 
	
	private BlockingQueue<Message> queue;
	
	public MenuView(BlockingQueue<Message> queue) {
		
		this.queue = queue;
		
		createFrame();
		createSettingsPanel();
		createAlgorithmsPanel();
		//createControllerPanel();
		this.pack();
		
	}
	
	private void createFrame() {
		
		setTitle("Welcome to sorting algorithm visualizer");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void createSettingsPanel() {
		
		settingsPanel = new JPanel(new FlowLayout());
		
		sizeLabel = new JLabel("Size: ");
		sizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		sizeSlider.setPaintLabels(true);
		sizeSlider.setLabelTable(sizeSlider.createStandardLabels(10));
		
		speedLabel = new JLabel("Speed: ");
		speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		speedSlider.setPaintLabels(true);
		speedSlider.setLabelTable(speedSlider.createStandardLabels(10));
		
		settingsPanel.add(sizeLabel);
		settingsPanel.add(sizeSlider);
		settingsPanel.add(speedLabel);
		settingsPanel.add(speedSlider);
		this.add(settingsPanel, BorderLayout.NORTH);
		
		settingsPanel.repaint();
		settingsPanel.revalidate();
		
	}
	
	private void createAlgorithmsPanel() {
		
		algorithmsPanel = new JPanel(new FlowLayout());
		
		bubbleButton = new JButton("Bubble");
		bubbleButton.addActionListener(event -> {
			try { this.queue.put(new ExecuteBubbleMessage()); }
			catch (InterruptedException e) { e.printStackTrace(); }
		});
		
		mergeButton = new JButton("Merge");
		mergeButton.addActionListener(event -> {
			try { this.queue.put(new ExecuteMergeMessage()); }
			catch (InterruptedException e) { e.printStackTrace(); }
		});
		
		heapButton = new JButton("Heap");
		heapButton.addActionListener(event -> {
			try { this.queue.put(new ExecuteHeapMessage()); }
			catch (InterruptedException e) { e.printStackTrace(); }
		});
		
		quickButton = new JButton("Quick");
		quickButton.addActionListener(event -> {
			try { this.queue.put(new ExecuteQuickMessage()); }
			catch (InterruptedException e) { e.printStackTrace(); }
		});
		
		algorithmsPanel.add(bubbleButton);
		algorithmsPanel.add(mergeButton);
		algorithmsPanel.add(heapButton);
		algorithmsPanel.add(quickButton);
		this.add(algorithmsPanel, BorderLayout.CENTER);
		
		algorithmsPanel.repaint();
		algorithmsPanel.revalidate();
		
	}
	
	private void createControllerPanel() {
		
		controllerPanel = new JPanel(new FlowLayout());
		
		//add functionality later
		prevStepButton = new JButton("Previous Step");
		nextStepButton = new JButton("Next Step");
		playButton = new JButton("Play");
		pauseButton = new JButton("Pause");
		terminateButton = new JButton("Terminate");
		
		controllerPanel.add(prevStepButton);
		controllerPanel.add(nextStepButton);
		controllerPanel.add(playButton);
		controllerPanel.add(pauseButton);		//maybe use wait() and notify()
		controllerPanel.add(terminateButton);
		
		this.add(controllerPanel, BorderLayout.SOUTH);
		
		controllerPanel.repaint();
		controllerPanel.revalidate();
		
	}
	
}
