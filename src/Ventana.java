import java.awt.EventQueue;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;



public class Ventana {

	private JFrame frame;
	private JTextPane txtEditor[];
	private JMenuBar menuBar;
	private JTabbedPane tabbedPane;
	private JButton btnNewFile;
	private JButton btnSaveFile;
	private JButton btnOpenFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
				
		/*Menu*/
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		mnArchivo.add(mntmNuevo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
		mnArchivo.add(mntmAbrir);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar(getActiveTextPane());
			}
		});
		mnArchivo.add(mntmGuardar);
		
		JMenuItem mntmGuardarComo = new JMenuItem("Guardar Como");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComo(getActiveTextPane());
			}
		});
		mnArchivo.add(mntmGuardarComo);
		
		JMenu mnEdicion = new JMenu("Edicion");
		menuBar.add(mnEdicion);
		
		JMenuItem mntmPreferencias = new JMenuItem("Preferencias");
		mnEdicion.add(mntmPreferencias);
		
		JMenu mnEnsamblar = new JMenu("Ensamblar");
		menuBar.add(mnEnsamblar);
		
		JMenuItem mntmEnsamblar = new JMenuItem("Ensamblar");
		mnEnsamblar.add(mntmEnsamblar);
		
		
		/*Tab Inicial*/
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(0, 39, 792, 512);
		frame.getContentPane().add(tabbedPane);
		
		txtEditor = new JTextPane[1];
		txtEditor[0] = new JTextPane();
		txtEditor[0].setContentType("text/enriched");
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(txtEditor[0]);
		scrollPane.setName("0");
		
		tabbedPane.addTab("Sin Titulo", /*icon*/ null,/*Component component*/ scrollPane, /*String tip*/ null);
		
		btnNewFile = new JButton("");
		btnNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevo();
			}
		});
		btnNewFile.setToolTipText("Nuevo");
		btnNewFile.setIcon(new ImageIcon(Ventana.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		btnNewFile.setBounds(12, 2, 25, 25);
		frame.getContentPane().add(btnNewFile);
		
		btnSaveFile = new JButton("");
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar(getActiveTextPane());
			}
		});
		btnSaveFile.setIcon(new ImageIcon(Ventana.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		btnSaveFile.setToolTipText("Guardar");
		btnSaveFile.setBounds(42, 2, 25, 25);
		frame.getContentPane().add(btnSaveFile);
		
		btnOpenFile = new JButton("");
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrir();
			}
		});
		btnOpenFile.setIcon(new ImageIcon(Ventana.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		btnOpenFile.setToolTipText("Abrir");
		btnOpenFile.setBounds(72, 2, 25, 25);
		frame.getContentPane().add(btnOpenFile);
		
		JButton btnEnsamblar = new JButton("Ensamblar");
		btnEnsamblar.setBounds(308, 2, 117, 25);
		frame.getContentPane().add(btnEnsamblar);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBackground(Color.WHITE);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"16 Bits - Linux", "32 Bits - Linux", "64 Bits - Linux"}));
		comboBox.setBounds(203, 3, 103, 24);
		frame.getContentPane().add(comboBox);
		
		JLabel lblFormato = new JLabel("Formato:");
		lblFormato.setBounds(127, 12, 70, 15);
		frame.getContentPane().add(lblFormato);
		
		
		
	}
	
	private void addNewTextPane(String titulo, String contenido){
		JScrollPane scrollPane = new JScrollPane();
		
		JTextPane txtAlgo = new JTextPane();
		txtAlgo.setContentType("text/enriched");
		txtAlgo.setText(contenido);
		
		scrollPane.setViewportView(txtAlgo); 
		tabbedPane.addTab(titulo, null, scrollPane, null);
		
		JTextPane tmpTextPane[];
		tmpTextPane = Arrays.copyOf(txtEditor, txtEditor.length + 1);
		tmpTextPane[txtEditor.length] = txtAlgo;
		
		scrollPane.setName(""+ (txtEditor.length) );
		
		txtEditor = tmpTextPane;
		
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
		
	}
	
	private JTextPane getActiveTextPane(){
		int indice = Integer.parseInt( tabbedPane.getComponentAt( tabbedPane.getSelectedIndex() ).getName() );
		return txtEditor[indice];
	}
	
	//Operaciones con Archivos - Usuario
	/* TODO
	 * Buscar JFileChooser
	 */
	private void guardar(JTextPane textPane){}
	
	private void guardarComo(JTextPane textPane){
		JFileChooser file = new JFileChooser();
		file.showSaveDialog(frame);
		//TODO botones aceptar y cancelar
		if(true && !false){
			Archivo archivo = new Archivo(file.getSelectedFile().getAbsolutePath());
			archivo.guardar(textPane.getText());
			tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), file.getSelectedFile().getName());
		}
	}
	private void abrir(){
		JFileChooser file = new JFileChooser();
		file.showOpenDialog(frame);
		//TODO botones aceptar y cancelar
		if(true && !false){
			Archivo archivo = new Archivo(file.getSelectedFile().getAbsolutePath());
			addNewTextPane(file.getSelectedFile().getName(), archivo.cargar());
		}
	}
	private void nuevo(){
		addNewTextPane("Sin titulo", "<n>Negritas</n>");
	}
	
	//Operaciones con Archivos - Ensamblador
	private void cargar(){}
}






