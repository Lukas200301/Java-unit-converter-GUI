import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Einheitenumrechner extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String[] laengenEinheiten = {"Meter", "Zentimeter", "Kilometer", "Meilen", "Yards", "Fuß", "Zoll"};
    private String[] gewichtEinheiten = {"Kilogramm", "Gramm", "Pfund", "Unzen"};
    private String[] fluessigkeitenEinheiten = {"Liter", "Milliliter", "Gallone", "Quart", "Pint", "Flüssigunze"};
    private String[] temperaturEinheiten = {"Celsius", "Kelvin", "Fahrenheit"};
    private String[] alleEinheiten = {"Meilen", "Kilometer", "Yards", "Meter", "Fuß", "Zoll", "Zentimeter",
    									"Kilogramm", "Unzen", "Pfund", "Gramm", "Gallone", "Liter", "Milliliter",
    									"Quart", "Pint", "Flüssigunze", "Celsius", "Kelvin", "Fahrenheit"};
    private JComboBox<String> comboEinheit1;
    private JComboBox<String> comboEinheit2;
    private JTextField eingabeFeld;
    private JLabel ausgabeFeld;
        
    public Einheitenumrechner() {
    		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(800,375);
        setResizable(false);
        JPanel HauptPanel = (JPanel) getContentPane();	// Hauptpanel wird erstellt
        HauptPanel.setLayout(new BorderLayout());
        pack();
        setVisible(true);

        JPanel inputPanel = new JPanel(new FlowLayout());	 // Eingabefeld für den Wert
        JLabel inputLabel = new JLabel("Wert:");
        eingabeFeld = new JTextField(10);
        inputPanel.add(inputLabel);
        inputPanel.add(eingabeFeld);
        HauptPanel.add(inputPanel, BorderLayout.NORTH);

        JPanel comboPanel1 = new JPanel();	 // Auswahlliste für die erste Einheit
        JLabel comboLabel1 = new JLabel("Von:");
        comboEinheit1 = new JComboBox<String>(alleEinheiten);	//Alle Einheiten werden in die Auswahlliste hinzugefügt
        comboPanel1.add(comboLabel1);
        comboPanel1.add(comboEinheit1);

        JPanel comboPanel2 = new JPanel();	 // Auswahlliste für die zweite Einheit
        JLabel comboLabel2 = new JLabel("Nach:");
        comboEinheit2 = new JComboBox<String>(alleEinheiten);	//Alle Einheiten werden in die Auswahlliste hinzugefügt
        comboPanel2.add(comboLabel2);
        comboPanel2.add(comboEinheit2);
        
        JPanel comboPanel3 = new JPanel();	 // Ausgabe-Label für das Ergebnis
        ausgabeFeld = new JLabel("");		  
        comboPanel3.add(ausgabeFeld, BorderLayout.SOUTH);

        JPanel comboPanel = new JPanel(new BorderLayout());	 // Füge beide Auswahllisten zusammen
        comboPanel.add(comboPanel1, BorderLayout.NORTH);
        comboPanel.add(comboPanel2,BorderLayout.CENTER);
        comboPanel.add(comboPanel3, BorderLayout.SOUTH);
        HauptPanel.add(comboPanel, BorderLayout.CENTER);

        comboEinheit1.addActionListener(this);	  // Füge ActionListener zu den 2 Auswahllisten hinzu
        comboEinheit2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {	 // Event-Handler für die Auswahllisten
        String vonEinheit = (String) comboEinheit1.getSelectedItem();
        String zuEinheit = (String) comboEinheit2.getSelectedItem();
       
        double Eingabe;
        try {
        	Eingabe = Double.parseDouble(eingabeFeld.getText());  //Eingabe einlesen
        } catch (NumberFormatException ex) {
        	ausgabeFeld.setText("Ungültige Eingabe");
            return;
        }
        double ausgabe = 0;
        if (isLaengenEinheit(vonEinheit) && isLaengenEinheit(zuEinheit)) {	 // Längen-Umrechnungen
        	ausgabe = getLaengenEinheiten(vonEinheit,zuEinheit,Eingabe);
        }else if (isGewichtEinheit(vonEinheit) && isGewichtEinheit(zuEinheit)) {	  // Gewicht-Umrechnungen
        	ausgabe = getGewichtEinheit(vonEinheit,zuEinheit,Eingabe);
        }else if (isFluessigkeitEinheit(vonEinheit) && isFluessigkeitEinheit(zuEinheit)) {	   // Flüssigkeit-Umrechnungen   	
        	ausgabe = getFluessigkeitEinheit(vonEinheit,zuEinheit,Eingabe);
        }else if (isTemperaturEinheit(vonEinheit) && isTemperaturEinheit(zuEinheit)) {	// Temperatur-Umrechnungen
        	ausgabe = getTemperaturEinheit(vonEinheit,zuEinheit,Eingabe);
        }else {		   // Keine Umrechnung möglich
        	ausgabeFeld.setText("Keine Umrechnung möglich");
            return;
        }
        ausgabe = Math.round(ausgabe * 100000.0) / 100000.0;		   // Runde das Ergebnis auf 5 Nachkommastellen
        ausgabeFeld.setText(Eingabe + " " + vonEinheit + " sind " + ausgabe + " " + zuEinheit);	 	// Ausgabe
    }

    private boolean isLaengenEinheit(String eingabe) {	 // Überprüft, ob eine Einheit eine Längeneinheit ist
        for (String s : laengenEinheiten) {
            if (s.equals(eingabe)) {
                return true;
            }
        }
        return false;
    }

    private boolean isGewichtEinheit(String eingabe) {	  // Überprüft, ob eine Einheit eine Gewichtseinheit ist
        for (String s : gewichtEinheiten) {
            if (s.equals(eingabe)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFluessigkeitEinheit(String eingabe) {	 // Überprüft, ob eine Einheit eine Flüssigkeiteneinheit ist
        for (String s : fluessigkeitenEinheiten) {
            if (s.equals(eingabe)) {
                return true;
            }
        }
        return false;
    }
	
	private boolean isTemperaturEinheit(String eingabe) {	// Überprüft, ob eine Einheit eine Temperatureinheit ist
	    for (String s : temperaturEinheiten) {
	        if (s.equals(eingabe)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public double getLaengenEinheiten(String vonEinheit, String zuEinheit, double Eingabe) {	// Längen-Umrechnungen
		double ausgabe=0;
		switch(vonEinheit) {
	    	case "Meter":		// Umrechnung von Meter in andere Längeneinheiten
	    		switch(zuEinheit) {
	        		case "Zentimeter":
	                	ausgabe = Eingabe * 100;
	                	break;
	        		case "Kilometer":
	                	ausgabe = Eingabe / 1000;
	                	break;
	        		case "Meilen":
	                	ausgabe = Eingabe / 1609.344;
	                	break;
	        		case "Yards":
	                	ausgabe = Eingabe * 1.0936;
	                	break;
	        		case "Fuß":
	                	ausgabe = Eingabe * 3.2808;
	                	break;
	        		case "Zoll":
	                	ausgabe = Eingabe * 39.3701;
	                	break;
	                default:
	                	ausgabe = Eingabe;
	            }
	            break;
	    	case "Zentimeter":		// Umrechnung von Zentimeter in andere Längeneinheiten
	    		switch(zuEinheit) {
	        		case "Meter":
	            		ausgabe = Eingabe / 100;
	            		break;
	        		case "Kilometer":
	                	ausgabe = Eingabe / 100000;
	                	break;
	        		case "Meilen":
	                	ausgabe = Eingabe *  0.0000062137;
	                	break;
	        		case "Yards":
	                	ausgabe = Eingabe * 0.010936;
	                	break;
	        		case "Fuß":
	                	ausgabe = Eingabe * 0.0328084;
	                	break;
	        		case "Zoll":
	                	ausgabe = Eingabe * 0.3937;
	                	break;
	                default :
	                	ausgabe = Eingabe;
	            }
	        	break;
	    	case "Kilometer":	// Umrechnung von Kilometer in andere Längeneinheiten
	    		switch(zuEinheit) {
	        		case "Meter":
	            		ausgabe = Eingabe * 1000;
	            		break;
	        		case "Zentimeter":
	                	ausgabe = Eingabe * 100000;
	                	break;
	        		case "Meilen":
	                	ausgabe = Eingabe * 0.6214;
	                	break;
	        		case "Yards":
	                	ausgabe = Eingabe * 1093.6;
	                	break;
	        		case "Fuß":
	                	ausgabe = Eingabe * 3280.8;
	                	break;
	        		case "Zoll":
	                	ausgabe = Eingabe * 39270.1;
	                	break;
	                default :
	                	ausgabe = Eingabe;
	            }
	        	break;
	    	case "Meilen":	// Umrechnung von Meilen in andere Längeneinheiten
	    		switch(zuEinheit) {
	        		case "Meter":
	            		ausgabe = Eingabe * 1609.344;
	            		break;
	        		case "Zentimeter":
	                	ausgabe = Eingabe * 160934.4;
	                	break;
	        		case "Kilometer":
	                	ausgabe = Eingabe * 1.609;
	                	break;
	        		case "Yards":
	                	ausgabe = Eingabe * 1760;
	                	break;
	        		case "Fuß":
	                	ausgabe = Eingabe * 5280;
	                	break;
	        		case "Zoll":
	                	ausgabe = Eingabe * 63360;
	                	break;
	                default :
	                	ausgabe = Eingabe;
	            }
	        	break;
	    	case "Yards":	// Umrechnung von Yards in andere Längeneinheiten
	        	switch(zuEinheit) {
		        	case "Meter":
	            		ausgabe = Eingabe * 0.9144;
	            		break;
		        	case "Zentimeter":
	                	ausgabe = Eingabe * 91.44;
	                	break;
		        	case "Kilometer":
	                	ausgabe = Eingabe * 0.000914;
	                	break;
		        	case "Meilen":
	                	ausgabe = Eingabe * 0.000568;
	                	break;
		        	case "Fuß":
	                	ausgabe = Eingabe * 3;
	                	break;
		        	case "Zoll":
	                	ausgabe = Eingabe * 36;
	                	break;
	                default :
	                	ausgabe = Eingabe;
	            }
	        	break;
	    	case "Fuß":	 // Umrechnung von Fuß in andere Längeneinheiten
	        	switch(zuEinheit) {
		        	case "Meter":
	            		ausgabe = Eingabe * 0.3048;
	            		break;
		        	case "Zentimeter":
	                	ausgabe = Eingabe * 30.48;
	                	break;
		        	case "Kilometer":
	                	ausgabe = Eingabe * 0.000305;
	                	break;
		        	case "Meilen":
	                	ausgabe = Eingabe * 0.000189;
	                	break;
		        	case "Yards":
	                	ausgabe = Eingabe * 0.33333;
	                	break;
		        	case "Zoll":
	                	ausgabe = Eingabe * 12;
	               default :
	                	ausgabe = Eingabe;
	            }
	        	break;
	    	case "Zoll":	// Umrechnung von Zoll in andere Längeneinheiten
	        	switch(zuEinheit) {
		        	case "Meter":
	            		ausgabe = Eingabe * 0.0254;
	            		break;
		        	case "Zentimeter":
	                	ausgabe = Eingabe * 2.54;
	                	break;
		        	case "Kilometer":
	                	ausgabe = Eingabe * 0.0000254;
	                	break;
		        	case "Meilen":
	                	ausgabe = Eingabe * 0.000016;
	                	break;
		        	case "Yards":
	                	ausgabe = Eingabe * 0.0278;
	                	break;
		        	case "Fuß":
	                	ausgabe = Eingabe * 0.0833;
	                	break;
	                default :
	                	ausgabe = Eingabe;
	            }
	        	break;
		}
		return ausgabe;
	}
	
	public static double getGewichtEinheit(String vonEinheit, String zuEinheit, double Eingabe) {	// Gewicht-Umrechnungen
		double ausgabe=0;
		switch(vonEinheit) {
	    	case "Kilogramm":		// Umrechnung von Kilogramm in andere Gewichtseinheit
	        	switch(zuEinheit) {
		        	case "Gramm":
	                 	ausgabe = Eingabe * 1000;
	                 	break;
		        	case "Pfund":
	                 	ausgabe = Eingabe * 2.20462262;
	                 	break;
		        	case "Unzen":
	                 	ausgabe = Eingabe * 35.27396195;
	                 	break;
		            default :
		                 	ausgabe = Eingabe;
	             }
	             break;
	    	case "Gramm":	// Umrechnung von Gramm in andere Gewichtseinheit
	    		switch(zuEinheit) {
		        	case "Kilogramm":
	                  	ausgabe = Eingabe * 0.001;
	                  	break;
		        	case "Pfund":
	                  	ausgabe = Eingabe * 0.002205;
	                  	break;
		        	case "Unzen":
	                  	ausgabe = Eingabe * 0.03527;
	                  	break;
	                default :
	                  	ausgabe = Eingabe;
	    		}
	    		break;
	    	case "Pfund":	// Umrechnung von Pfund in andere Gewichtseinheit
	    		switch(zuEinheit) {
		        	case "Gramm":
	                  	ausgabe = Eingabe * 453.5924;
	                  	break;
		        	case "Kilogramm":
	                  	ausgabe = Eingabe * 0.4536;
	                  	break;
		        	case "Unzen":
	                  	ausgabe = Eingabe * 16;
	                  	break;
		        	default :
	                  	ausgabe = Eingabe;
	    		}
	    		break;
	    	case "Unzen":	// Umrechnung von Unzen in andere Gewichtseinheit
	    		switch(zuEinheit) {
		        	case "Gramm":
	                   	ausgabe = Eingabe * 28.3495;
	                   	break;
		        	case "Kilogramm":
	                   	ausgabe = Eingabe * 0.02835;
	                   	break;
		        	case "Pfund":
	                   	ausgabe = Eingabe * 0.06250;
	                   	break;
		        	default :
	                   	ausgabe = Eingabe;
	    		}
	    		break;
			}
		return ausgabe;
	}
	
	public static double getFluessigkeitEinheit(String vonEinheit, String zuEinheit, double Eingabe) {	// Flüssigkeit-Umrechnungen
		double ausgabe=0;
		switch(vonEinheit) {
	    	case "Gallone":	// Umrechnung von Gallone in andere Flüssigkeiteinheit
	    		switch(zuEinheit) {
		        	case "Liter":
	                	ausgabe = Eingabe * 3.7854;
	                	break;
		        	case "Milliliter":
	                	ausgabe = Eingabe * 3785.4;
	                	break;
		        	case "Quart":			
	                	ausgabe = Eingabe * 4;
	                	break;
		        	case "Pint":
	                	ausgabe = Eingabe * 8;
	                	break;
		        	case "Flüssigunze":
	                	ausgabe = Eingabe * 128;
	                	break;
	                default :
	                	ausgabe = Eingabe;
	            }
	            break;
	    	case "Liter":	// Umrechnung von Liter in andere Flüssigkeiteinheit
	    		switch(zuEinheit) {
		        	case "Gallone":
	                	ausgabe = Eingabe * 0.2642;
	                	break;
		        	case "Milliliter":
	                	ausgabe = Eingabe * 1000;
	                	break;
		        	case "Quart":		
	                	ausgabe = Eingabe * 1.0567;
	                	break;
		        	case "Pint":
	                	ausgabe = Eingabe * 2.1134;
	                	break;
		        	case "Flüssigunze":
	                	ausgabe = Eingabe * 33.8144;
	                	break;
	               default :
	                	ausgabe = Eingabe;
	            }
	        	break;
	    	case "Milliliter":	// Umrechnung von Milliliter in andere Flüssigkeiteinheit
	    		switch(zuEinheit) {
		        	case "Liter":
	                	ausgabe = Eingabe * 0.001;
	                	break;
		        	case "Gallone":
	                	ausgabe = Eingabe * 0.00026417205235814843;
	                	break;
		        	case "Quart":		
	                	ausgabe = Eingabe * 0.00105625;
	                	break;
		        	case "Pint":
	                	ausgabe = Eingabe * 0.0021125;
	                	break;
		        	case "Flüssigunze":
	                	ausgabe = Eingabe * 0.0338;
	                	break;
	                default :
	                	ausgabe = Eingabe;
	            }
	        	break;
	    	case "Quart":	// Umrechnung von Quart in andere Flüssigkeiteinheit
	    		switch(zuEinheit) {
		        	case "Liter":
	                	ausgabe = Eingabe * 0.9464;
	                	break;
		        	case "Milliliter":
	                	ausgabe = Eingabe * 946.4;
	                	break;
		        	case "Gallone":			
	                	ausgabe = Eingabe * 0.25;
	                	break;
		        	case "Pint":
	                	ausgabe = Eingabe * 2.0001;
	                	break;
		        	case "Flüssigunze":
	                	ausgabe = Eingabe * 32;
	                	break;
	                default :
	                	ausgabe = Eingabe;
	            }
	        	break;
	    	case "Pint":	// Umrechnung von Pint in andere Flüssigkeiteinheit
	    		switch(zuEinheit) {
		        	case "Liter":
	                	ausgabe = Eingabe * 0.4732;
	                	break;
		        	case "Milliliter":
	                	ausgabe = Eingabe * 473.1765;
	                	break;
		        	case "Quart":				
	                	ausgabe = Eingabe * 0.5;
	                	break;
		        	case "Gallone":
	                	ausgabe = Eingabe * 0.125;
	                	break;
		        	case "Flüssigunze":
	                	ausgabe = Eingabe * 16;
	                	break;
	                default :
	                	ausgabe = Eingabe;
	            }
	        	break;
	    	case "Flüssigunze":	 // Umrechnung von Flüssigunze in andere Flüssigkeiteinheit
	    		switch(zuEinheit) {
		        	case "Liter":
	                	ausgabe = Eingabe * 0.0296;
	                	break;
		        	case "Milliliter":
	                	ausgabe = Eingabe * 29.6208;
	                	break;
		        	case "Quart":			
	                	ausgabe = Eingabe * 0.0313;
	                	break;
		        	case "Pint":
	                	ausgabe = Eingabe * 0.0625;
	                	break;
		        	case "Gallone":
	                	ausgabe = Eingabe * 0.0078125;
	                	break;
	                default :
	                	ausgabe = Eingabe;
	            }    
	        	break;
        }
		return ausgabe;
	}
	
	public static double getTemperaturEinheit(String vonEinheit, String zuEinheit, double Eingabe) {	// Temperatur-Umrechnungen
		double ausgabe=0;
		switch(vonEinheit) {
	    	case "Celsius":	// Umrechnung von Celsius in andere Temperatureinheit
	    		switch(zuEinheit) {
		        	case "Kelvin":
	                	ausgabe = Eingabe + 273.15;
	                	break;
		        	case "Fahrenheit":
	                	ausgabe = Eingabe * 9/5 + 32;
	                	break;
	                default :
	                	ausgabe = Eingabe;
	            }
	    		break;
	    	case "Kelvin":	// Umrechnung von Kelvin in andere Temperatureinheit
	    		switch(zuEinheit) {
		        	case "Celsius":
	                	ausgabe = Eingabe - 273.15;
	                	break;
		        	case "Fahrenheit":
	                	ausgabe = 1.8 * (Eingabe - 273) + 32;
	                	break;
	               default :
	                	ausgabe = Eingabe;
	            }
	    		break;
	    	case "Fahrenheit":	// Umrechnung von Fahrenheit in andere Temperatureinheit
	    		switch(zuEinheit) {
	        	case "Kelvin":
	            	ausgabe = (Eingabe - 32) / 1.8 + 273;
	            	break;
	        	case "Celsius":
	            	ausgabe = (Eingabe - 32) * 0.555555556;
	            	break;
	            default :
	            	ausgabe = Eingabe;
	            }
	        	break;
        }
		return ausgabe;
	}
}