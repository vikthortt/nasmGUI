import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Archivo {
	File archivo;
	
	public Archivo(String ruta){
		archivo = new File(ruta);
	}
	
	public boolean guardar(String contenido){
		try {
			FileWriter escritor = new FileWriter(archivo);
			escritor.write(contenido);
			escritor.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public String cargar(){
		String ret = null, tmp;
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			tmp = lector.readLine();
			while(tmp != null){
				if(ret == null) ret ="";
				ret += tmp +"\n";
				tmp = lector.readLine();
			}
			lector.close();
			return ret;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public String getName(){
		return archivo.getName();
	}

	public String getPath() {		// 
		return archivo.getAbsolutePath();
	}
}
