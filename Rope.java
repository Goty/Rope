import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class Rope {
	private Rope ropeIzquierda;
	private Rope ropeDerecha;
	private int longitudTotal;
	private int altura;
	private String cadena;
	private int longitudCadena;

	public Rope(String cadena) {
		this.cadena = cadena;
		longitudCadena = cadena.length();
		longitudTotal = longitudCadena;
	}

	public Rope(Rope ropeIzquierda, Rope ropeDerecha, int altura, int longitud) {
		this.ropeIzquierda = ropeIzquierda;
		this.ropeDerecha = ropeDerecha;
		this.altura = altura;
		longitudTotal = longitud;
	}

	public int getAltura() {
		return altura;
	}

	public int getLongitud() {
		return longitudTotal;
	}

	public Rope getRopeIzquierda() {
		return ropeIzquierda;
	}

	public Rope getRopeDerecha() {
		return ropeDerecha;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public void setRopeIzquierda(Rope rope) {
		ropeIzquierda = rope;
	}

	public void setRopeDerecha(Rope rope) {
		ropeDerecha = rope;
	}

	public void setLongitud(int longitud) {
		longitudTotal = longitud;
	}

	public Rope concatenar(Rope rope) {
		Rope ropeRaiz = new Rope("");
		ropeRaiz.setRopeIzquierda(this);
		ropeRaiz.setRopeDerecha(rope);
		ropeRaiz.setLongitud(rope.getLongitud() + longitudTotal);

		if (altura > rope.getAltura()) {
			ropeRaiz.setAltura(altura + 1);
		} else {
			ropeRaiz.setAltura(rope.getAltura() + 1);
		}
		return ropeRaiz;
	}

	public char get(int i) {
		if (altura == 0) {
			return cadena.charAt(i);
		} else {
			if (ropeIzquierda.getLongitud() > i) {
				return ropeIzquierda.get(i);
			} else {
				return ropeDerecha.get(i - ropeIzquierda.getLongitud());
			}
		}
	}

	public Rope balancear() {
		List<Rope> list = new ArrayList<Rope>();
		Stack<Rope> pila = new Stack<Rope>();
		Rope cima = this;
		pila.push(cima);
		while (!pila.empty()) {
			if (cima.getAltura() != 0) {
				cima = pila.pop();
				pila.push(cima.getRopeDerecha());
				pila.push(cima.getRopeIzquierda());
				cima = pila.peek();
			}
			else {
				list.add(pila.pop());
			}
		}
		return arbolizar(list);
	}

	public Rope arbolizar(List<Rope> list) {
		if (list.size() == 1) {
			return list.get(0);
		} else {
			Rope ropeIzquierdo = arbolizar(list.subList(0, list.size() / 2));
			Rope ropeDerecho = arbolizar(list.subList(list.size() / 2,
					list.size()));
			int altura = ropeIzquierdo.getAltura() + 1;
			int longitud = ropeIzquierdo.getLongitud()
					+ ropeDerecho.getLongitud();
			return new Rope(ropeIzquierdo, ropeDerecho, altura, longitud);
		}
	}
	
	public String toString(){
		if (altura == 0){
			return cadena;
		}
		else{
			return ropeIzquierda.toString()+ropeDerecha.toString();
		}
	}
	

}
