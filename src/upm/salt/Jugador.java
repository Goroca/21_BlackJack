package upm.salt;
import java.util.Scanner;


/**
 * @author Carlos Gomez Rodriguez
 * @author Alejandro Jarabo
 * @author Alejandro Ramos Martin
 * @author Gabriela Gutierrez Cruz
 */
public class Jugador {

	private int[] cartas = {0,0,0,0,0};
	private int dineroAcumulado = 100;		//Variable que mide el dinero que lleva el jugador y se inicializa a 100
	private int apuesta = 0;

	Scanner sc1 = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);
	
	public Jugador() {
  
	}


	public int getCarta(int n) {
		return cartas[n];
	}


	public String getValor(int n) {
		if (cartas[n]==11 || cartas[n]==1) {return "As";}
		if (cartas[n]==10) {return "Figura o 10";}
		return Integer.toString(cartas[n]);

	}

	

	public void crupierCogeCarta(Cartas baraja) {
		int numeroCarta= 2;
	
		while (suma()<17 && numeroCarta<6) {
			setCarta(baraja.getCarta());
			System.out.println(numeroCarta + "º Carta Crupier: " + getValor(numeroCarta-1));
			numeroCarta++;
		}
		System.out.println("Las cartas del crupier suman "+ suma());
		System.out.println();
	}

	public void setCarta(int carta) {

		for(int i=0;i<cartas.length;i++) {
			if(cartas[i]==0) {
				cartas[i]=carta;
				convertidor();
				break;}
		}

	}


	public String comparaJugada(Jugador jugador) {
		int suma1= suma();
		int suma2= jugador.suma();	

		if (suma1>21 && suma2>21) {return "EMPATE";}
		if (suma2>21) {return "HAS GANADO";}
		if (suma1>21) {return "HAS PERDIDO";}

		if (suma1<suma2) {return "HAS PERDIDO";}

		if (suma1>suma2) {return "HAS GANADO";}

		if (suma1==suma2) {return "EMPATE";}

		return null;

	}

	public int suma() {
		int suma = 0;
		for (int i=0; i<cartas.length; i++) {
			suma += cartas[i];
		}
		return suma;

	}

	public boolean desborda() {
		int a=suma();
		if (a>21) {return true;}

		return false;
	}

	public void convertidor() {
		if (desborda()==true) {
			for (int i=0;i<cartas.length;i++) {		
				if (cartas[i]==11) {
					cartas[i]=1;
					break;}
			}
		}
	}
	
	public void apuestaValida (){
		
		while (apuesta % 5 != 0) {
			System.out.println("Debe apostar un multiplo de 5");	//Hemos considerado que cada apuesta sea multiplo de 5(resultados mas limpios)
			System.out.print("Hagan sus apuestas: "); 
			apuesta = sc1.nextInt();
			System.out.println();
		}

		while ((apuesta < 0) || (apuesta > getDineroAcumulado())) {
			System.out.println("Apuesta imposible");
			System.out.print("Hagan sus apuestas: "); 
			apuesta = sc1.nextInt();
			System.out.println();
		}
		
	}
	
	
	public int getDineroAcumulado() {		//Devuelve el dinero Acumulado por el jugador
		return dineroAcumulado;
	}
	
	public void setDineroAcumulado(int nuevoDinero) {
		dineroAcumulado = nuevoDinero;
	}
	
	public void resultadoApuesta(Jugador j0) {
		if(comparaJugada(j0) == "HAS GANADO") {		//Si ganas la ronda sumas a tu bote el doble de tu apuesta
			dineroAcumulado += apuesta;
		}

		else if (comparaJugada(j0) == "HAS PERDIDO") {	//Si pierdes la ronda restas a tu bote el doble de tu apuesta
			dineroAcumulado -= apuesta;
		}
		else if (comparaJugada(j0) == "EMPATE") {	//Si hay un empate te quedas con el mismo dinero
			dineroAcumulado = getDineroAcumulado();
		}
	}

	public void cantidadActual(int dinero) {		//Cambia el dineroAcumulado del inicio de la ronda por la cantidad final con la que se queda
		dineroAcumulado = dinero;
	}
	public int getApuesta() {		//Devuelve el dinero Acumulado por el jugador
		return apuesta;
	}
	public void cambiarApuesta(int nuevaApuesta) {
		apuesta = nuevaApuesta;
	}
	
	
	public void quieroCarta (Cartas baraja){
		System.out.println();
		System.out.print("¿Quiere carta? (Si/No): "); 
		String respuesta= sc2.nextLine();

		int n=2;
		
		while ( ( (respuesta.equals("Si")) || (respuesta.equals("si")) ) && (n < 5)) {

			setCarta(baraja.getCarta());
			System.out.println("Su " + (n+1) + "º carta: "+getValor(n));
			System.out.println("Sus cartas suman: " + suma());
			System.out.println();
			System.out.println("La probabilidad de pedir sin pasarse es: " + baraja.pPedirCartaSinPasarse(suma()) + " %");
			System.out.println("La probabilidad de conseguir 21 es: " + baraja.pConseguir21(suma()) + " %");
			n++;
			
			if (suma() > 20) {
				break;
			}
			
			System.out.println();
			System.out.print("¿Quiere carta? (Si/No): ");
			respuesta = sc2.nextLine();
		}
		if(respuesta.equals("No") || respuesta.equals("no")) {
			System.out.println("Te quedas con las cartas que tienes");
			System.out.println();
		}
		
	}
	
}

