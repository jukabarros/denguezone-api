package api.dump;

public class ImportCSVChuvasApp {

	public static void main(String[] args) {
		String csvFile = null;
		if (args.length < 1){
			System.out.println("Erro: é necessário informar o caminho do CSV");
		} else {
			csvFile = args[0];
			if (!csvFile.endsWith(".csv")) {
				System.out.println("Erro: arquivo não é CSV");
			}else{
				ParserCSVChuvastoDB parserchuvasBD = new ParserCSVChuvastoDB();
				parserchuvasBD.parserCSVChuvastoDB(csvFile);
			}
		}
	}

}
