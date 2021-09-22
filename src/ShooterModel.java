import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShooterModel {
	
	private ArrayList<ActionListener> actionListeners = new ArrayList<>();
	
	public void addActionListener(ActionListener listener) {
		   actionListeners.add(listener);
	   }
	
	public void fireActionListener() {
		   for (ActionListener listener : actionListeners) {
			   listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_FIRST,"fire"));
		   }
	   }
}
