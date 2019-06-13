package metro;

public class Arista implements Comparable<Arista> {
	
	private int nodo1;
	private int nodo2;
	private int costo;
	
	public Arista(int nodo1, int nodo2, int costo) {
		this.nodo1 = nodo1;
		this.nodo2 = nodo2;
		this.costo = costo;
	}
	
	public int getNodo1() {
		return this.nodo1;
	}
	
	public int getNodo2() {
		return this.nodo2;
	}
	
	public int getCosto() {
		return this.costo;
	}

	@Override
	public int compareTo(Arista arista) {
		return this.costo - arista.costo;
	}
	
	
}
