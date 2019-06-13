package metro;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Metro {

	private int cantidadNodos;
	private int cantidadTuneles;
	private int cantidadPuentes;
	private int aristasUsadas;
	private int puentesNecesarios;
	private Arista[] aristas;
	private static int padre[];
	
	public Metro(Scanner entrada) {
		int nodo1, nodo2;
		this.cantidadNodos = entrada.nextInt();
		this.cantidadTuneles = entrada.nextInt();
		this.cantidadPuentes = entrada.nextInt();
		this.aristasUsadas = 0;
		this.puentesNecesarios = 0;
		this.aristas = new Arista[(this.cantidadTuneles + this.cantidadPuentes)];
		for(int i=0; i<this.cantidadTuneles; i++) {
			nodo1 = entrada.nextInt();
			nodo2 = entrada.nextInt();
			this.aristas[i] = new Arista(nodo1, nodo2, 0);
		}
		for(int j = this.cantidadTuneles; j<(this.cantidadTuneles + this.cantidadPuentes); j++) {
			nodo1 = entrada.nextInt();
			nodo2 = entrada.nextInt();
			this.aristas[j] = new Arista(nodo1, nodo2, 1);
		}
		Arrays.sort(aristas);
		this.padre = new int[this.cantidadNodos+1];
		for(int i=1; i<=this.cantidadNodos; i++) 
			this.padre[i] = i; 
	}
	
	public static int find(int x) {
		if(x == padre[x])
			return x;
		else 
			return find(padre[x]);
	}
	
	public static boolean mismoComponente(int x, int y) {
		if(find(x) == find(y))
			return true;
		return false;
	}
	
	public static void union(int x, int y) {
		padre[find(x)] = find(y);
	}
	
	public void resolver(PrintWriter salida) {
		int x, y, i=0, cantidadAristas = (this.cantidadPuentes + this.cantidadTuneles);
		while(i < cantidadAristas && this.aristasUsadas != (this.cantidadNodos-1)) {
			x = this.aristas[i].getNodo1();
			y = this.aristas[i].getNodo2();
			if(mismoComponente(x,y) == false) {
				if(this.aristas[i].getCosto() == 1)
					this.puentesNecesarios++;
				this.aristasUsadas++;
				union(x,y);
			}
			i++;
		}
		salida.println(this.puentesNecesarios);
	}
	
	public static void main(String[] args) throws IOException {
		Scanner entrada = new Scanner(new FileReader("metro.in"));
		Metro metro = new Metro(entrada);
		entrada.close();
		PrintWriter salida = new PrintWriter(new FileWriter("metro.out"));
		metro.resolver(salida);
		salida.close();
	}

}

