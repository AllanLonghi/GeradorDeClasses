package class_generator;


import java.nio.charset.Charset;

import util.Util;

public class ClassGenerator {
	Charset charset = Charset.forName("US-ASCII");
	private String[] listaCsv;
	private String getterSetter;
	private String atributo;
	private String atributoCsv;
	private String tipo;
	private String tipoCsv;
	private String atributoConstrutor;
	private String thisConstrutor;
	private String nomeClasse;
	private String[] classes;
	

	public ClassGenerator() {
	}

	public String getCamelCaseGetterAndSetter(String tocc) {
		String[] temp;
		temp = tocc.split("\\s*_\\s*");
		return temp[0].replaceFirst(temp[0].substring(0, 1), temp[0].substring(0, 1).toUpperCase())
				+ temp[1].replaceFirst(temp[1].substring(0, 1), temp[1].substring(0, 1).toUpperCase());
	}
	
	public String[] getClassesCsv(String csv) {
		classes = csv.split("\\s*;\\s*");
		return classes;
	}

	public void geraClasse(String csv, String nomeDoProjeto) {
		classes = getClassesCsv(csv);
		for(int i = 0; i < classes.length; i++) {
			listaCsv = classes[i].split("\\s*,\\s*");
			for (int d = 0; d < listaCsv.length; d += 2) {
				tipoCsv = listaCsv[d + 1];	
				atributoCsv = listaCsv[d];
				
				if (tipoCsv.toUpperCase().equals("TABLE")) {
					nomeClasse = atributoCsv;
					atributo = "";
					getterSetter = "";
					atributoConstrutor = "";
					thisConstrutor = "";
					continue;	
				}
				tipo = (tipoCsv.toUpperCase().equals("BIGINT"))?"long":tipo;
				tipo = (tipoCsv.toUpperCase().equals("VARCHAR"))?"String":tipo;
				tipo = (tipoCsv.toUpperCase().equals("INTEGER"))?"int":tipo;
				tipo = (tipoCsv.toUpperCase().equals("REAL"))?"float":tipo;
				tipo = (tipoCsv.toUpperCase().equals("DATE"))?"Date":tipo;
					atributo += getAtributo();
					getterSetter += getGetterESetter();
					atributoConstrutor += getAtributoConstrutor();
					thisConstrutor += getInicializaAtributo();
				
			}
			criaProjeto(nomeDoProjeto);
		}


	}
	
	public void criaProjeto(String nomeDoProjeto) {
			atributoConstrutor = atributoConstrutor.replaceFirst(",", "");
			String classe = Util.getClasse(nomeClasse, atributo+getConstrutor()+getterSetter);
			Util.geraDirEFile(classe, nomeDoProjeto, nomeClasse);
	}
	


	public String getGetterESetter() {
		return "\n\t public " + tipo + " get" + getCamelCaseGetterAndSetter(atributoCsv) + "(){ \n"
				+ "\t\t return "	+ Util.getCamelCaseAtributo(atributoCsv) + ";\n"
				+ "\t }\n\n"
				+ "\t public void set"+ getCamelCaseGetterAndSetter(atributoCsv)+"("+tipo+" " + Util.getCamelCaseAtributo(atributoCsv) + " ){ \n"
				+getInicializaAtributo()
				+ "\t }\n";
	}
	

		
	public String getAtributo() {
		return "\t private " + tipo + " " + Util.getCamelCaseAtributo(atributoCsv)
		+ ";\n";
	}

	public String getConstrutor() {
		return "\n\n\t public " + nomeClasse + "(" + atributoConstrutor + "){\n"
				+ thisConstrutor
				+ "\t }\n";
	}

	public String getAtributoConstrutor() {
		return ", " + tipo + " " + Util.getCamelCaseAtributo(atributoCsv);
	}

	public String getInicializaAtributo() {
		return "\t\t this." +Util.getCamelCaseAtributo(atributoCsv) + " = " + Util.getCamelCaseAtributo(atributoCsv) + ";\n";
	}

}
