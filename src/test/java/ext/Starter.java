package ext;

public class Starter {
	public static void main(String[] args) {
		try {
			// Hier der Pfad zur urspruenglichen Eingabedatei
			String inputFile = args[0];

			// Hier der Pfad zur generierten Ausgabedatei
			String outputFile = args[1];

			// Laden der Eingabedatei
			int[] squares = EvaluationFunction.loadInputFile(inputFile);

			// Laden der Ausgabedatei
			int[][] field = EvaluationFunction.loadOutputFile(outputFile);

			// Evaluationsfunktion instanziieren
			EvaluationStandard eval = new EvaluationStandard();

			// Evaluation ausfuehren
			System.out.println(eval.evaluate(field, squares));
		} catch (Exception e) {
			if (e.getMessage() != null) {
				System.out.println("Fehler bei der Auswertung: "
						+ e.getMessage());
			} else {
				System.out
						.println("Fehler bei der Ausfuehrung des Programms. Bitte pruefen Sie die Programmparameter.");
				System.out.println("Korrekte Nutzung:");
				System.out
						.println("java evaluation.Starter #INPUT_FILE# #OUTPUT_FILE#");
			}
		}
	}
}
