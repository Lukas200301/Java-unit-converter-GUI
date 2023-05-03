import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JavaUnitConverter extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String[] lengths = {"Meter", "Centimeter", "Kilometer", "Miles", "Yards", "Feet", "Inch"};
    private String[] weights = {"Kilogram", "Gram", "Pound", "Ounces"};
    private String[] fluids = {"Liter", "Milliliter", "Gallon", "Quart", "Pint", "Fluid Ounce"};
    private String[] temperatures = {"Celsius", "Kelvin", "Fahrenheit"};
    private String[] all = {"Miles", "Kilometer", "Yards", "Meter", "Feet", "Inch", "Centimeter",
    									"Kilogram", "Ounces", "Pound", "Gram", "Gallon", "Liter", "Milliliter",
    									"Quart", "Pint", "Fluid Ounce", "Celsius", "Kelvin", "Fahrenheit"};
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JTextField input;
    private JLabel output;
        
    public JavaUnitConverter() {
    		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(800,375);
        setResizable(false);
        JPanel MainPanel = (JPanel) getContentPane();	
        MainPanel.setLayout(new BorderLayout());
        pack();
        setVisible(true);

        JPanel inputPanel = new JPanel(new FlowLayout());	
        JLabel inputLabel = new JLabel("Value:");
        input = new JTextField(10);
        inputPanel.add(inputLabel);
        inputPanel.add(input);
        MainPanel.add(inputPanel, BorderLayout.NORTH);

        JPanel comboPanel1 = new JPanel();	 
        JLabel comboLabel1 = new JLabel("From:");
        comboBox1 = new JComboBox<String>(all);	
        comboPanel1.add(comboLabel1);
        comboPanel1.add(comboBox1);

        JPanel comboPanel2 = new JPanel();	 
        JLabel comboLabel2 = new JLabel("To:");
        comboBox2 = new JComboBox<String>(all);	
        comboPanel2.add(comboLabel2);
        comboPanel2.add(comboBox2);
        
        JPanel comboPanel3 = new JPanel();	 
        output = new JLabel("");		  
        comboPanel3.add(output, BorderLayout.SOUTH);

        JPanel comboPanel = new JPanel(new BorderLayout());	 
        comboPanel.add(comboPanel1, BorderLayout.NORTH);
        comboPanel.add(comboPanel2,BorderLayout.CENTER);
        comboPanel.add(comboPanel3, BorderLayout.SOUTH);
        MainPanel.add(comboPanel, BorderLayout.CENTER);

        comboBox1.addActionListener(this);	 
        comboBox2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {	 
        String fromUnit = (String) comboBox1.getSelectedItem();
        String toUnit = (String) comboBox2.getSelectedItem();
       
        double InputNumber;
        try {
        	InputNumber = Double.parseDouble(input.getText());  
        } catch (NumberFormatException ex) {
        	output.setText("Invalid input");
            return;
        }
        double OutputNumber = 0;
        if (isLenghUnit(fromUnit) && isLenghUnit(toUnit)) {	 
        	OutputNumber = getLenghUnit(fromUnit,toUnit,InputNumber);
        }else if (isWeightUnit(fromUnit) && isWeightUnit(toUnit)) {	 
        	OutputNumber = getWeightUnit(fromUnit,toUnit,InputNumber);
        }else if (isFluidUnit(fromUnit) && isFluidUnit(toUnit)) {	   	
        	OutputNumber = getFluidUnit(fromUnit,toUnit,InputNumber);
        }else if (isTemperaturesUnit(fromUnit) && isTemperaturesUnit(toUnit)) {	
        	OutputNumber = getTemperaturesUnit(fromUnit,toUnit,InputNumber);
        }else {		   
        	output.setText("No conversion possible");
            return;
        }
        OutputNumber = Math.round(OutputNumber * 100000.0) / 100000.0;		  
        output.setText(InputNumber + " " + fromUnit + " are " + OutputNumber + " " + toUnit);	 	
    }

    private boolean isLenghUnit(String input) {	 
        for (String s : lengths) {
            if (s.equals(input)) {
                return true;
            }
        }
        return false;
    }

    private boolean isWeightUnit(String input) {	  
        for (String s : weights) {
            if (s.equals(input)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFluidUnit(String input) {	 
        for (String s : fluids) {
            if (s.equals(input)) {
                return true;
            }
        }
        return false;
    }
	
	private boolean isTemperaturesUnit(String input) {	
	    for (String s : temperatures) {
	        if (s.equals(input)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public double getLenghUnit(String fromUnit, String toUnit, double Input) {	
		double output=0;
		switch(fromUnit) {
	    	case "Meter":		
	    		switch(toUnit) {
	        		case "Centimeter":
	        			output = Input * 100;
	                	break;
	        		case "Kilometer":
	        			output = Input / 1000;
	                	break;
	        		case "Miles":
	        			output = Input / 1609.344;
	                	break;
	        		case "Yards":
	        			output = Input * 1.0936;
	                	break;
	        		case "Feet":
	        			output = Input * 3.2808;
	                	break;
	        		case "Inch":
	        			output = Input * 39.3701;
	                	break;
	                default:
	                	output = Input;
	            }
	            break;
	    	case "Centimeter":		
	    		switch(toUnit) {
	        		case "Meter":
	        			output = Input / 100;
	            		break;
	        		case "Kilometer":
	        			output = Input / 100000;
	                	break;
	        		case "Miles":
	        			output = Input *  0.0000062137;
	                	break;
	        		case "Yards":
	        			output = Input * 0.010936;
	                	break;
	        		case "Feet":
	        			output = Input * 0.0328084;
	                	break;
	        		case "Inch":
	        			output = Input * 0.3937;
	                	break;
	                default :
	                	output = Input;
	            }
	        	break;
	    	case "Kilometer":	
	    		switch(toUnit) {
	        		case "Meter":
	        			output = Input * 1000;
	            		break;
	        		case "Centimeter":
	        			output = Input * 100000;
	                	break;
	        		case "Miles":
	        			output = Input * 0.6214;
	                	break;
	        		case "Yards":
	        			output = Input * 1093.6;
	                	break;
	        		case "Feet":
	        			output = Input * 3280.8;
	                	break;
	        		case "Inch":
	        			output = Input * 39270.1;
	                	break;
	                default :
	                	output = Input;
	            }
	        	break;
	    	case "Miles":	
	    		switch(toUnit) {
	        		case "Meter":
	        			output = Input * 1609.344;
	            		break;
	        		case "Centimeter":
	        			output = Input * 160934.4;
	                	break;
	        		case "Kilometer":
	        			output = Input * 1.609;
	                	break;
	        		case "Yards":
	        			output = Input * 1760;
	                	break;
	        		case "Feet":
	        			output = Input * 5280;
	                	break;
	        		case "Inch":
	        			output = Input * 63360;
	                	break;
	                default :
	                	output = Input;
	            }
	        	break;
	    	case "Yards":	
	        	switch(toUnit) {
		        	case "Meter":
		        		output = Input * 0.9144;
	            		break;
		        	case "Centimeter":
		        		output = Input * 91.44;
	                	break;
		        	case "Kilometer":
		        		output = Input * 0.000914;
	                	break;
		        	case "Miles":
		        		output = Input * 0.000568;
	                	break;
		        	case "Feet":
		        		output = Input * 3;
	                	break;
		        	case "Inch":
		        		output = Input * 36;
	                	break;
	                default :
	                	output = Input;
	            }
	        	break;
	    	case "Feet":	 
	        	switch(toUnit) {
		        	case "Meter":
		        		output = Input * 0.3048;
	            		break;
		        	case "Centimeter":
		        		output = Input * 30.48;
	                	break;
		        	case "Kilometer":
		        		output = Input * 0.000305;
	                	break;
		        	case "Miles":
		        		output = Input * 0.000189;
	                	break;
		        	case "Yards":
		        		output = Input * 0.33333;
	                	break;
		        	case "Inch":
		        		output = Input * 12;
	               default :
	            	   output = Input;
	            }
	        	break;
	    	case "Inch":	
	        	switch(toUnit) {
		        	case "Meter":
		        		output = Input * 0.0254;
	            		break;
		        	case "Centimeter":
		        		output = Input * 2.54;
	                	break;
		        	case "Kilometer":
		        		output = Input * 0.0000254;
	                	break;
		        	case "Miles":
		        		output = Input * 0.000016;
	                	break;
		        	case "Yards":
		        		output = Input * 0.0278;
	                	break;
		        	case "Feet":
		        		output = Input * 0.0833;
	                	break;
	                default :
	                	output = Input;
	            }
	        	break;
		}
		return output;
	}
	
	public static double getWeightUnit(String fromUnit, String toUnit, double Input) {	
		double output=0;
		switch(fromUnit) {
	    	case "Kilogram":		
	        	switch(toUnit) {
		        	case "Gram":
		        		output = Input * 1000;
	                 	break;
		        	case "Pound":
		        		output = Input * 2.20462262;
	                 	break;
		        	case "Ounces":
		        		output = Input * 35.27396195;
	                 	break;
		            default :
		            	output = Input;
	             }
	             break;
	    	case "Gram":	
	    		switch(toUnit) {
		        	case "Kilogram":
		        		output = Input * 0.001;
	                  	break;
		        	case "Pound":
		        		output = Input * 0.002205;
	                  	break;
		        	case "Ounces":
		        		output = Input * 0.03527;
	                  	break;
	                default :
	                	output = Input;
	    		}
	    		break;
	    	case "Pound":	
	    		switch(toUnit) {
		        	case "Gram":
		        		output = Input * 453.5924;
	                  	break;
		        	case "Kilogram":
		        		output = Input * 0.4536;
	                  	break;
		        	case "Ounces":
		        		output = Input * 16;
	                  	break;
		        	default :
		        		output = Input;
	    		}
	    		break;
	    	case "Ounces":	
	    		switch(toUnit) {
		        	case "Gram":
		        		output = Input * 28.3495;
	                   	break;
		        	case "Kilogram":
		        		output = Input * 0.02835;
	                   	break;
		        	case "Pound":
		        		output = Input * 0.06250;
	                   	break;
		        	default :
		        		output = Input;
	    		}
	    		break;
			}
		return output;
	}
	
	public static double getFluidUnit(String fromUnit, String toUnit, double Input) {	
		double output=0;
		switch(fromUnit) {
	    	case "Gallon":	
	    		switch(toUnit) {
		        	case "Liter":
		        		output = Input * 3.7854;
	                	break;
		        	case "Milliliter":
		        		output = Input * 3785.4;
	                	break;
		        	case "Quart":			
		        		output = Input * 4;
	                	break;
		        	case "Pint":
		        		output = Input * 8;
	                	break;
		        	case "Fluid Ounce":
		        		output = Input * 128;
	                	break;
	                default :
	                	output = Input;
	            }
	            break;
	    	case "Liter":	
	    		switch(toUnit) {
		        	case "Gallon":
		        		output = Input * 0.2642;
	                	break;
		        	case "Milliliter":
		        		output = Input * 1000;
	                	break;
		        	case "Quart":		
		        		output = Input * 1.0567;
	                	break;
		        	case "Pint":
		        		output = Input * 2.1134;
	                	break;
		        	case "Fluid Ounce":
		        		output = Input * 33.8144;
	                	break;
	               default :
	            	   output = Input;
	            }
	        	break;
	    	case "Milliliter":	
	    		switch(toUnit) {
		        	case "Liter":
		        		output = Input * 0.001;
	                	break;
		        	case "Gallon":
		        		output = Input * 0.00026417205235814843;
	                	break;
		        	case "Quart":		
		        		output = Input * 0.00105625;
	                	break;
		        	case "Pint":
		        		output = Input * 0.0021125;
	                	break;
		        	case "Fluid Ounce":
		        		output = Input * 0.0338;
	                	break;
	                default :
	                	output = Input;
	            }
	        	break;
	    	case "Quart":	
	    		switch(toUnit) {
		        	case "Liter":
		        		output = Input * 0.9464;
	                	break;
		        	case "Milliliter":
		        		output = Input * 946.4;
	                	break;
		        	case "Gallon":			
		        		output = Input * 0.25;
	                	break;
		        	case "Pint":
		        		output = Input * 2.0001;
	                	break;
		        	case "Fluid Ounce":
		        		output = Input * 32;
	                	break;
	                default :
	                	output = Input;
	            }
	        	break;
	    	case "Pint":	
	    		switch(toUnit) {
		        	case "Liter":
		        		output = Input * 0.4732;
	                	break;
		        	case "Milliliter":
		        		output = Input * 473.1765;
	                	break;
		        	case "Quart":				
		        		output = Input * 0.5;
	                	break;
		        	case "Gallon":
		        		output = Input * 0.125;
	                	break;
		        	case "Fluid Ounce":
		        		output = Input * 16;
	                	break;
	                default :
	                	output = Input;
	            }
	        	break;
	    	case "Fluid Ounce":	
	    		switch(toUnit) {
		        	case "Liter":
		        		output = Input * 0.0296;
	                	break;
		        	case "Milliliter":
		        		output = Input * 29.6208;
	                	break;
		        	case "Quart":			
		        		output = Input * 0.0313;
	                	break;
		        	case "Pint":
		        		output = Input * 0.0625;
	                	break;
		        	case "Gallon":
		        		output = Input * 0.0078125;
	                	break;
	                default :
	                	output = Input;
	            }    
	        	break;
        }
		return output;
	}
	
	public static double getTemperaturesUnit(String fromUnit, String toUnit, double Input) {	
		double output=0;
		switch(fromUnit) {
	    	case "Celsius":	
	    		switch(toUnit) {
		        	case "Kelvin":
		        		output = Input + 273.15;
	                	break;
		        	case "Fahrenheit":
		        		output = Input * 9/5 + 32;
	                	break;
	                default :
	                	output = Input;
	            }
	    		break;
	    	case "Kelvin":	
	    		switch(toUnit) {
		        	case "Celsius":
		        		output = Input - 273.15;
	                	break;
		        	case "Fahrenheit":
		        		output = 1.8 * (Input - 273) + 32;
	                	break;
	               default :
	            	   output = Input;
	            }
	    		break;
	    	case "Fahrenheit":	
	    		switch(toUnit) {
	        	case "Kelvin":
	        		output = (Input - 32) / 1.8 + 273;
	            	break;
	        	case "Celsius":
	        		output = (Input - 32) * 0.555555556;
	            	break;
	            default :
	            	output = Input;
	            }
	        	break;
        }
		return output;
	}
}