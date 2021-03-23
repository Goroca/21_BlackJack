package upm.salt;

import java.util.*;
/**
 * 
 */

/**
 * @author Carlos Gomez Rodriguez
 * @author Alejandro Jarabo
 * @author Alejandro Ramos Martin
 * @author Gabriela Gutierrez Cruz

 */
public class Cartas {
	int[] baraja= {11,11,11,11,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};

	public Cartas() {
		
	}
	public int getCarta(){
		int valor=0;
		int carta=0;
		Random r = new Random();

		while(valor==0){

			carta=r.nextInt(52);
			valor=baraja[carta];
		}

		baraja[carta]=0;

		return valor;

	}
	
	public void repartoInicial (Jugador j0, Jugador j1, Jugador j2, Jugador j3){
		j3.setCarta(getCarta());
		j2.setCarta(getCarta());
		j1.setCarta(getCarta());
		j0.setCarta(getCarta());
		j3.setCarta(getCarta());
		j2.setCarta(getCarta());
		j1.setCarta(getCarta());
		
		System.out.println("Primera Carta jugador1: "+j1.getValor(0));
		System.out.println("SU PRIMERA CARTA: "+j2.getValor(0));
		System.out.println("Primera Carta jugador3: "+j3.getValor(0));
		System.out.println();
		
		System.out.println("Segunda Carta jugador1: "+j1.getValor(1));
		System.out.println("SU SEGUNDA CARTA: "+j2.getValor(1));
		System.out.println("Segunda Carta jugador3: "+j3.getValor(1));
		System.out.println();
		
		System.out.println("Primera Carta Crupier: "+j0.getValor(0));
		System.out.println();
		
		System.out.println("Sus cartas suman: " + j2.suma());	
		System.out.println();
		System.out.println("La probabilidad de pedir sin pasarse es: " + pPedirCartaSinPasarse(j2.suma()) + " %");	
		System.out.println("La probabilidad de conseguir 21 es: " + pConseguir21(j2.suma()) + " %");
		System.out.println();
	}

	public int posibles(){

		int a=0;

		for(int i=0;i<baraja.length;i++){

			if(baraja[i]!=0){
				a++;			
			}		
		}
		return a;
	}	
	
	public double redondear( double numero, int decimales ) 
	{
	    return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
	}  

	public double pPedirCartaSinPasarse(int n){

		double a=0.0;
		double b=0.0;
		double pf=0.0;

		for(int i=0;i<baraja.length;i++){

			if(baraja[i]!=0){
				a++;			
			}	
		}
		for(int j=0;j<baraja.length;j++){

			if((n+baraja[j]<=21) && (baraja[j]!=0)){
				b++;
			}
			if((n>10) && (n<21) && baraja[j]==11){
				b++;
			}
		}
		pf= (b/a)*100;
		if (pf>100.0){return 100;}
		return redondear(pf,2);
	}
	//Para saber la probabilidad de sacar 21 y casi asegurar la victoria
	public double pConseguir21(int n){

		double a=0.0;
		double b=0.0;
		double pf=0.0;

		int cartaPara21 = 21-n;
		for(int i=0;i<baraja.length;i++){

			if(baraja[i]!=0){
				a++;			
			}	
		}
		for(int j=0;j<baraja.length;j++){

			if((baraja[j]==cartaPara21) && (baraja[j]!=0)){
				b++;
			}
			if((cartaPara21==1) && (baraja[j]==11)){
				b++;
			}	
		}
		pf= (b/a)*100;
		if (pf>100.0){return 100;}
		return redondear(pf,2);
	}
	
	public int posibles(int[] baraja){

		int a=0;

		for(int i=0;i<baraja.length;i++){

			if(baraja[i]!=0){
				a++;			
			}		
		}
		return a;
	}	

	public int ases(int[] baraja){

		int a=0;

		for(int i=0;i<baraja.length;i++){

			if(baraja[i]==11){
				a++;			
			}		
		}
		return a;
	}	

	public int figuras(int[] baraja){

		int a=0;

		for(int i=0;i<baraja.length;i++){

			if(baraja[i]==10){
				a++;			
			}		
		}
		return a;
	}	
	private int[] getBaraja(){
		return baraja;
	}

	public void getAs(int[] baraja){
		for(int i=0; i< baraja.length;i++)	{
			if (baraja[i]==11){
				baraja[i]=0;
				break;
			}
		}		

	}
	public void getCarta(int[] baraja){

		Random m = new Random();
		int valor=0;
		int carta=0;

		while(valor==0){
			carta=m.nextInt(52);
			valor=baraja[carta];
		}

		baraja[carta]=0;				
	}



	public double pSacarBlackJack() {
		Cartas c = new Cartas();

		int[] baraja = c.getBaraja();
		getCarta(baraja);
		double iniciales = posibles(baraja);
		double ases = ases(baraja);
		getAs(baraja);
		getCarta(baraja);
		getCarta(baraja);
		getCarta(baraja);

		double ahora = posibles(baraja);
		double figura = figuras(baraja);

		double pA = ases/iniciales;
		double pBA= figura/ahora;
		return (pA*pBA)*100;

	}

	public double mediaBlackJack(){
		int n = 0;
		double suma = 0;
		double p = 0;
		while (n<10){
			p=pSacarBlackJack();
			suma += p;
			//System.out.println((n+1) + "º Prob BlackJack: " + p);
			n++;
		}
		return redondear(suma/n, 2);
	}

	public int factorial(int n) {
		int resultado = 1;
		if (n==0) {
			return 1;
		}
		while (n>0) {
			resultado *= n;
			n--;
		}
		return resultado;
	}

	public double combinatoria (int n,int k) {
		double numerador = factorial(n);
		double denominador = factorial(k)*factorial(n-k);
		return numerador/denominador;
	}




	public double bernoulli (int n, int k) {
		double pSacarBlackJack = mediaBlackJack()/100;
		double resultado = combinatoria(n,k)*Math.pow(pSacarBlackJack,k)*Math.pow(1-pSacarBlackJack,n-k); 
		return redondear(resultado*100,5);
	}





}
