package Model;

import View.IObserver;

public interface IObservable {
	
	void add(IObserver o);
	void alert();
}
