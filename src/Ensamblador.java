
public class Ensamblador {
	
	private Archivo inst;
	private String mensaje;
	
	public Ensamblador() {
		// TODO Auto-generated constructor stub
	}

	public boolean ensamblar(Archivo archivo, String modo){
		//TODO
		String args[] = new String[10];
		args[0] = "nasm";  					//Ensamblador
		args[1] = archivo.getPath(); 		//Archivo
		args[2] = "-felf"+modo;				//Modo
		args[3] = "-l";						//Genera archivo instrucciones
		args[4] = archivo.getPath().replace(".s", ".inst");
		
		inst = null;
		mensaje = ":)";
		
		return false;
	}

	public Archivo getInstFile() {
		// TODO Auto-generated method stub
		return inst;
	}

	public String getMensaje() {
		// TODO Auto-generated method stub
		return mensaje;
	}
}
