package util;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class Util {
	
	public static String getCamelCaseAtributo(String tocc) {
		String[] temp;
		temp = tocc.split("\\s*_\\s*");
		return temp[0] + temp[1].replaceFirst(temp[1].substring(0, 1), temp[1].substring(0, 1).toUpperCase());
	}
	
	public static void geraDirEFile(String fileContent, String nomeDoProjeto, String nomeClasse) {
		byte[] data = fileContent.getBytes();
		String dirSrc = System.getProperty("user.dir")+"\\Projetos"+"\\"+nomeDoProjeto;
		File dir = new File(dirSrc);
		dir.mkdir();
		dirSrc = System.getProperty("user.dir")+"\\Projetos"+"\\"+nomeDoProjeto+"\\"+nomeClasse;
		Path fileSrc = Paths.get(dirSrc, nomeClasse+".java");
		dir = new File(dirSrc);
		dir.mkdir();
	    try (OutputStream out = new BufferedOutputStream(
	    	      Files.newOutputStream(fileSrc, CREATE, APPEND))) {
	    	      out.write(data, 0, data.length);
	    	    } catch (IOException x) {
	    	      System.err.println(x);
	    	      JOptionPane.showInternalMessageDialog(null, x);
	    	    }
	}
	
	public static String getClasse(String nomeClasse ,String corpoDaClasse) {
		return "public class "+nomeClasse+" { \n\n"
				+ corpoDaClasse+"\n\n"
				+ "}";
	}
	
	
	

}
