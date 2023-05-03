import javax.swing.SwingUtilities;

public class MainMethod {

	public MainMethod(){
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	try {
	            	JavaUnitConverter Window = new JavaUnitConverter();		
	            	CurrentDate date = new CurrentDate(Window);	
	            	Window.setSize(300,200);
	            	Window.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });	
	}
	public static void main(String[] args) {		
		new MainMethod();
	}
}