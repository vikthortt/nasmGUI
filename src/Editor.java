import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JTextPane;


public class Editor implements Runnable {
	private List<String> diccionarioMnemonicos;
	private Map<String, String> colores;
	private JTextPane textPane;
	
	public Editor(JTextPane txtPane){
		textPane = txtPane;
		
		diccionarioMnemonicos = new ArrayList<String>();
		diccionarioMnemonicos.add("MOV");
		diccionarioMnemonicos.add("ADD");
		diccionarioMnemonicos.add("INC");
		diccionarioMnemonicos.add("DEC");
		diccionarioMnemonicos.add("MUL");
		diccionarioMnemonicos.add("JMP");
		
		colores = new TreeMap<String, String>();
		colores.put("mnemonicos", "red");
	}
	
	public String enrichText(String baseText){
		for(String word: diccionarioMnemonicos){
			baseText = baseText.replace(word+" ", "<color><param>red</param>" + word + "</color> ");
			
		}
		System.out.println("CHANGED: " + baseText);
		return baseText;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Super Cow to the rescue!");
		String strPrev = "";
		while(textPane != null){
			if(textPane.getText() != null && !textPane.getText().equals(strPrev)){
				textPane.setText( enrichText( textPane.getText() ) );
				strPrev = textPane.getText();
			}
		}
	}
	
	
	
}
