package api.dump;

public class ImportCSVDataApp {

	public static void main(String[] args) {
		
		String csvFile = null;
		if (args.length < 1){
			System.out.println("Erro: é necessário informar o caminho do CSV");
			csvFile = "/home/juccelino.barros/Desktop/UFRPE/Mestrado/"
					+ "ProgramacaoAplicada/projeto/casos-doencas/"
					+ "casos-dengue2015.csv";
		} else {
			csvFile = args[0];
		}
		
		if (!csvFile.endsWith(".csv")) {
			System.out.println("Erro: arquivo não é CSV");
		}else{
			ParserCSVtoDB readCSVFile = new ParserCSVtoDB();
			readCSVFile.parserCSVtoDB(csvFile);
		}
		
	}

}
