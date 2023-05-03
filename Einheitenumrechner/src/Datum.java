import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Datum extends JFrame {
	private static final long serialVersionUID = 1L;

    public Datum(Einheitenumrechner name) {		//Datum als Name des JFrames "Einheitenumrechner"
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy    HH:mm:ss"); 
        new Thread(new Runnable() {  			//Thread der immer wieder ausgeführt wird um die Uhrzeit zu aktualisieren
            @Override
            public void run() {
                while (true) {            
                    String dateText = sdf.format(new Date());                
                    SwingUtilities.invokeLater(new Runnable() {
                    	@Override
                        public void run() {
                    		name.setTitle(dateText);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
       
    }
}
