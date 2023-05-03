import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CurrentDate extends JFrame {
	private static final long serialVersionUID = 1L;

    public CurrentDate(JavaUnitConverter name) {		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd    hh.mm aa"); 
        new Thread(new Runnable() {  			
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
