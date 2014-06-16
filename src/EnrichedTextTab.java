import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class EnrichedTextTab extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final boolean EDITABLE = true;
	public static final boolean NO_EDITABLE = false;
	
	private Archivo archivo;
	private JTextArea jTextPane;
	private boolean editable;
	private String name;

	public EnrichedTextTab( boolean editable) {
		// TODO Auto-generated constructor stub
		jTextPane = new JTextArea();
		//jTextPane.setContentType("text/plain");
	
		this.setViewportView(jTextPane); 
		
		name = "Sin titulo";
		
		this.editable = editable;
	}
	
	public EnrichedTextTab(Archivo archivo, boolean editable){
		jTextPane = new JTextArea();
		//jTextPane.setContentType("text/html");
		this.setViewportView(jTextPane); 
		
		this.archivo = archivo;
		name = archivo.getName();
		jTextPane.setText(this.archivo.cargar());
		this.editable = editable;
	}
	
	public String getText(){
		return jTextPane.getText();
	}
	
	public void setText(String str){
		if(editable)
			jTextPane.setText(str);
	}
	
	public boolean guardar(){
		try{
			return archivo.guardar(getText());
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean guardarComo(String file){
		archivo = new Archivo(file);
		name = archivo.getName();
		editable = EDITABLE;
		return guardar();
	}
	
	public String getName(){
		return name;
	}
	
	public Archivo getArchivo(){
		return archivo;
	}

	public boolean isEditable() {
		return editable;
	}
}
