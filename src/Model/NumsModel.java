package Model;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import View.IObserver;

public class NumsModel implements IObservable{

	private int[] 		currSorting;
	private AllStates[] currStates;
	
	private List<IObserver> allObservers;
	
	//Possible states that an element can have
	private enum AllStates {
		STANDBY,
		VISITING,
		FINISHED
	}

	public NumsModel(int[] currSorting) {
		
		this.currSorting = currSorting;
		this.currStates = new AllStates[currSorting.length];
		Arrays.fill(currStates, AllStates.STANDBY);
		allObservers = new ArrayList<IObserver>();
		
	}

	@Override
	public void add(IObserver oberver) {
		this.allObservers.add(oberver);	
	}

	@Override
	public void alert() {
		
		for (IObserver observer: allObservers) {
			observer.update();
		}
		
	}
	
	
}
