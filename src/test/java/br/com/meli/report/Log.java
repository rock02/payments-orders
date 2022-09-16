package br.com.meli.report;

import org.junit.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Log {
	
	private static String[][] tabela;
	private static String[] coluna;
	private static String[] linha;

	
	public static void passOrFail(String mensagem, boolean passOrFail) {
		if (passOrFail) {
			pass(mensagem + " - PASSED");
		} else {
			fail(mensagem + " - FAIL");
		}
	}

	public static void pass(String mensagem) {
		extentPass(mensagem);
		Assert.assertTrue(true);
	}

	public static void fail(String mensagem) {
		extentFail(mensagem);
		Assert.assertTrue(false);
	}
	
	public static void extentPass(String mensagem) {
		Report.fetchTest().log(Status.PASS, mensagem);
	}

	public static void extentFail(String mensagem) {
		Report.fetchTest().log(Status.FAIL, mensagem);
	}

	public static void extentFatal(String mensagem) {
		Report.fetchTest().log(Status.FATAL, mensagem);
	}

	public static void extentInfo(String mensagem) {
		Report.fetchTest().log(Status.INFO, mensagem);
	}

	public static void extentWarning(String mensagem) {
		Report.fetchTest().log(Status.WARNING, mensagem);
	}

	public static void addTableLog(String[] coluna, String[] linha) {
		tabela = new String[coluna.length][2];
		for (int i = 0; i < coluna.length; i++) {
			tabela[i][0] = coluna[i];
			tabela[i][1] = linha[i];

		}
		Report.fetchTest().log(Status.INFO, MarkupHelper.createTable(tabela));
	}

	public static void addTableLog(String... strings) {
		coluna = new String[strings.length / 2];
		linha = new String[strings.length / 2];
		int size = strings.length;
		for (int i = 0; i < (size / 2); i++) {
			coluna[i] = strings[i];
		}

		for (int i = (size / 2); i < size; i++) {
			linha[i - size / 2] = strings[i];
		}

		tabela = new String[size / 2][2];
		for (int i = 0; i < linha.length; i++) {
			tabela[i][0] = coluna[i];
			tabela[i][1] = linha[i];
		}
		Report.fetchTest().log(Status.INFO, MarkupHelper.createTable(tabela));
	}

}
