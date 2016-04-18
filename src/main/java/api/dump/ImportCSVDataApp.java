package api.dump;

public class ImportCSVDataApp {

	public static void main(String[] args) {
		
		String csvFile = null;
		if (args.length < 1){
			System.out.println("Erro: é necessário informar o caminho do CSV");
		} else{
			csvFile = args[0];
		}
		
		ParserCSVtoDB readCSVFile = new ParserCSVtoDB();
		readCSVFile.parserCSVtoDB(csvFile);
	}

}
