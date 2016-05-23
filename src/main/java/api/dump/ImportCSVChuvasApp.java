package api.dump;

public class ImportCSVChuvasApp {

	public static void main(String[] args) {
		String csvFile = null;
		//			csvFile = args[0];
		csvFile = "/home/juccelino.barros/precipitacoes.csv";
		if (!csvFile.endsWith(".csv")) {
			System.out.println("Erro: arquivo não é CSV");
		}else{
			ParserCSVChuvastoDB parserchuvasBD = new ParserCSVChuvastoDB();
			parserchuvasBD.parserCSVChuvastoDB(csvFile);
		}
	}
}

