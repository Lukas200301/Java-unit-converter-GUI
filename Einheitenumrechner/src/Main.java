import javax.swing.SwingUtilities;

public class Main {

	public Main(){
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	try {
	            	Einheitenumrechner Fenster = new Einheitenumrechner();		//Fenster wird erstellt
	            	Datum d = new Datum(Fenster);	//Wird erstellt damit der Title vom JFrame das aktuelle Datum wird
	            	Fenster.setSize(300,200);
	            	Fenster.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });	
	}
	public static void main(String[] args) {		//Programmstart mit Fehler abfangen
		new Main();
	}
}