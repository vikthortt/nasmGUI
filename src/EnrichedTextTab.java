import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;


public class EnrichedTextTab extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final boolean EDITABLE = true;
	public static final boolean NO_EDITABLE = false;
	
	private JTextPane jTextPane;
	private JScrollPane jScrollPane;
	private String name;
	private boolean editable;

	public EnrichedTextTab(boolean editable) {
		// TODO Auto-generated constructor stub
		jScrollPane = new JScrollPane();
		jTextPane = new JTextPane();
		jTextPane.setContentType("text/enriched");
		
		jScrollPane.setViewportView(jTextPane); 
		
		
		this.editable = editable;
	}
	
	public String getText(){
		return jTextPane.getText();
	}
	
	public void setText(String str){
		jTextPane.setText(str);
	}
	
	

}
