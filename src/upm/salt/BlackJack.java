package upm.salt;

import java.util.Scanner;

/**
 * 
 */

/**
 * @author Carlos Gomez Rodriguez
 * @author Alejandro Jarabo
 * @author Alejandro Ramos Martin
 * @author Gabriela Gutierrez Cruz
 */
public class BlackJack {

	/**
	 * @param args
	 */

	

	public static void main(String[] args) {

		int n = 0;
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);		//Para introducir la cantidad a apostar
		Scanner sc3 = new Scanner(System.in);		//Para respuesta a la pregunta de si quieres continuar
		Scanner sc4 = new Scanner(System.in);
		Scanner sc5 = new Scanner(System.in);
		
		Cartas baraja = new Cartas();
		System.out.println("Calcule su probabilidad mediante los ensayos de Bernouilli: ");
		System.out.print("Calculo de sacar BlackJack k veces: ");
		int k = sc4.nextInt();
		System.out.print("En n rondas: ");
		int m = sc5.nextInt();
		System.out.println("La probabilidad de que saques BlackJack " + k + " veces en " + m + " rondas es: " + baraja.bernoulli(m, k) + " %");
		System.out.println();
		System.out.print("Si esta preparado pulse enter");
		String c = sc1.nextLine();
		
		System.out.println("Comienza el juego");
		System.out.println();
		
		String resp = "Si";		//Variable comparadora con la respuesta final del usuario(¿Quieres contiuar?)
		int guardaDinero = 100;		//Variable que almacena el capital final del jugador en cada ronda
		
		while ((resp=="Si") && (guardaDinero >= 5) && (n<5)) {		//Evalua en cada ejecucion la respuesta final del usuario y si el bote
															//que tiene es mayor que 5(he puesto que 5 euros sea lo minimo para jugar)

		System.out.println((n+1) + "º RONDA");
		System.out.println("Probabilidad de sacar BlackJack esta ronda: " + baraja.mediaBlackJack() + " %");
		

		Jugador jugador0 = new Jugador();
		Jugador jugador1 = new Jugador();
		Jugador jugador2 = new Jugador();
		Jugador jugador3 = new Jugador();

		baraja.repartoInicial (jugador0, jugador1, jugador2, jugador3); //Ejecuta el raparto inicial previo a cada partida
		
		int dinero=guardaDinero; //Variable que almacena el dinero con el que empezamos cada ronda
		
		
		System.out.println("Su bote actual es de " + dinero + " euros");
		System.out.print("Hagan sus apuestas: ");
	

			jugador2.cantidadActual(dinero); //Llamada al metodo cantidadActual(int dinero) de la clase Jugador.
		    jugador2.cambiarApuesta(sc2.nextInt());	//Nueva entrada de texto (referencia sc2)

			if (dinero == 0) {
				System.out.println("Su capital es 0. Ha finalizado la partida.");
				break;
			}

			jugador2.apuestaValida();
			//Comprueba que la apuesta y el dinero del jugador sean validos para continuar el juego
			
			jugador2.quieroCarta(baraja);
			//Deja que pidas cartas hasta que tu respuesta sea no o te pases de 21

		    jugador0.crupierCogeCarta(baraja);

			System.out.println(jugador2.comparaJugada(jugador0));

			jugador2.resultadoApuesta(jugador0);
			
			System.out.println();
			System.out.println("Su bote actual es de: " + jugador2.getDineroAcumulado() + " euros");		

			System.out.print("¿Quiere seguir jugando? (Si/No): ");
			
			String seguir = sc3.nextLine();		//Nueva entrada de texto (referencia sc3)
			System.out.println();
			System.out.println();
			if (seguir.equals("No")||seguir.equals("no")){	//Si el jugador no quiere seguir se acaba la partida
				System.out.println("Ha acabado la partida. Gracias por jugar con nosotros.");
				System.out.println("Su capital final es de " + jugador2.getDineroAcumulado() + " euros");
				break;
			} else {	//
				/**
				 * 	Si el jugador quiere seguir(respuesta != no), el valor de la variable resp(comparadora del inicio del bucle) pasa
				 * 	a ser "Si" y se almacena en guardaDinero el bote que lleva el jugador
				 */
				resp = "Si";
				guardaDinero = jugador2.getDineroAcumulado();
				n++;
				if (n > 4) {
					System.out.println("Ha llegado al limite de rondas");
					break;
				}
				
			}	
			
			
		}	//Cierre del bucle while grande
		
		sc1.close();
		sc2.close();
		sc3.close();
		sc4.close();
		sc5.close();
	
	}

}

