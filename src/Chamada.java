import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Chamada {

	private int duracao;
	private GregorianCalendar data;

	public Chamada(int duracao, GregorianCalendar data) {
		this.duracao = duracao;
		this.data = data;
	}

	public GregorianCalendar getData() {
		return data;
	}

	public int getDuracao() {
		return duracao;
	}

	@Override
	public String toString() {
		SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy");

		return "Duracao da chamada = " + duracao
				+ "\n data = " + formatacao.format(data);
	}
}
