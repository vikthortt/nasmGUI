import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;



public class Ventana {

	private JFrame frame;
	private JMenuBar menuBar;
	private JTabbedPane tabbedPane;
	private JButton btnNewFile;
	private JButton btnSaveFile;
	private JButton btnOpenFile;
	private JButton btnEnsamblar;
	private JComboBox<String> cmbArquitectura;
	
	private Ensamblador ensamblador;

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
		ensamblador = new Ensamblador();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Aplicacion");
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
				guardar(getActiveTextTab());
			}
		});
		mnArchivo.add(mntmGuardar);
		
		JMenuItem mntmGuardarComo = new JMenuItem("Guardar Como");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComo((EnrichedTextTab)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex()));
			}
		});
		mnArchivo.add(mntmGuardarComo);
		
		JMenuItem mntmCerrar = new JMenuItem("Cerrar");
		mntmCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}
		});
		mnArchivo.add(mntmCerrar);
		
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
		
		EnrichedTextTab scrollPane = new EnrichedTextTab(EnrichedTextTab.EDITABLE);
		
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
				guardar(getActiveTextTab());
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
		
		btnEnsamblar = new JButton("Ensamblar");
		btnEnsamblar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(getActiveTextTab().isEditable() && getActiveTextTab().getName().endsWith(".s")){
					String arch;
					arch = cmbArquitectura.getItemAt( cmbArquitectura.getSelectedIndex() );
					if(arch.contains("64")) arch = "64";
					else if(arch.contains("32")) arch = "32";
					else arch = "";
					if(ensamblador.ensamblar(getActiveTextTab().getArchivo(), arch)){
						cargar(ensamblador.getInstFile());
					}else{
						JOptionPane.showMessageDialog(frame, ensamblador.getMensaje(), "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnEnsamblar.setBounds(308, 2, 117, 25);
		frame.getContentPane().add(btnEnsamblar);
		
		cmbArquitectura = new JComboBox<String>();
		cmbArquitectura.setBackground(Color.WHITE);
		cmbArquitectura.setModel(new DefaultComboBoxModel<String>(new String[] {"32 Bits - Linux", "64 Bits - Linux"}));
		cmbArquitectura.setBounds(203, 3, 103, 24);
		frame.getContentPane().add(cmbArquitectura);
		
		JLabel lblFormato = new JLabel("Formato:");
		lblFormato.setBounds(127, 12, 70, 15);
		frame.getContentPane().add(lblFormato);
		
		
		
	}
	
	private void addNewTextTab(String titulo, Archivo archivo, boolean tipo) {
		EnrichedTextTab scrollPane = new EnrichedTextTab(archivo, tipo);
		
		tabbedPane.addTab(titulo, null, scrollPane, null);
		
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
		
	}
	
	private void addNewTextTab(String titulo, String contenido){
		EnrichedTextTab scrollPane = new EnrichedTextTab(EnrichedTextTab.EDITABLE);
		
		tabbedPane.addTab(titulo, null, scrollPane, null);
		scrollPane.setText(contenido);
		
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
		
	}
	
	private EnrichedTextTab getActiveTextTab(){
		return (EnrichedTextTab)tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
	}
	
	private void guardar(EnrichedTextTab textPane){
		if(!textPane.guardar()){
			guardarComo(textPane);
		}
	}
	
	private void guardarComo(EnrichedTextTab textPane){
		JFileChooser file = new JFileChooser();
		int eleccion = file.showSaveDialog(frame);
		if(eleccion == JFileChooser.APPROVE_OPTION){
			textPane.guardarComo(file.getSelectedFile().getAbsolutePath());
			tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), textPane.getName());
		}
	}
	private void abrir(){
		JFileChooser file = new JFileChooser();
		// TODO file.setFileFilter( );
		int eleccion = file.showOpenDialog(frame);
		if(eleccion == JFileChooser.APPROVE_OPTION){
			Archivo archivo = new Archivo(file.getSelectedFile().getAbsolutePath());
			addNewTextTab(file.getSelectedFile().getName(), archivo, EnrichedTextTab.EDITABLE);
		}
	}
	

	private void nuevo(){
		addNewTextTab("Sin titulo", "");
	}
	
	private void cargar(Archivo archivo){
		addNewTextTab(archivo.getName(), archivo, EnrichedTextTab.NO_EDITABLE);
	}
	
	private void cerrar(){
		tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
	}
}






