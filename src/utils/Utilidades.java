package utils;

public class Utilidades {
    public static boolean verificarListaVazia(Object[] listaVazia) {
		boolean listaEstaVazia = true;

		for (Object item : listaVazia) {
			if (item != null) {
				listaEstaVazia = false;
				break;
			}
		}

        return listaEstaVazia;
    }

    public static boolean verificarObjetoVazio(Object item) {
		boolean objetoVazio = true;

		if (item != null) {
			objetoVazio = false;
		}

        return objetoVazio;
    }
}