package api.dump;

public class ImportDataMain {

	public static void main(String[] args) {
		
		String csvFile = null;
//		if (args.length < 1){
//			System.out.println("É necessário informar o caminho do CSV");
//		}
		int numOfArgs = args.length;
		
		switch (numOfArgs) {
		case 1:
			csvFile = args[0];
			break;
		default:
			csvFile = "/home/juccelino.barros/Desktop/UFRPE/Mestrado/"
					+ "ProgramacaoAplicada/projeto/casos-doencas/casos-dengue2016.csv";
			break;
		}
		
		ReadCSVFile readCSVFile = new ReadCSVFile();
		readCSVFile.parserCSVtoDB(csvFile);
	}

}
