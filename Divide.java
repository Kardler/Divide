package divSrc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;


public class Divide {
	
	private static int n ;
	private static boolean prime[];
	private static Integer prime2[];
	static int periodNumber, prePeriod;
	
	public static void period(int a, int b, int k){//деление столбиком
		int length2=0, length5=0;//счетчики для кол-ва 5 и 2
		int s=b; //остаток от деления
		int c=b; //целая часть деления
		s=c%2;
		while(s==0){
			c/=2;
			s = c%2;
			length2++;
		}
		s=c%5;
		while(s==0){
			c/=5;
			s=c%5;
			length5++;
		}
		periodNumber=1;
		int r = k%c;//
		
		while(r!=1){//расчет кол-ва символов в периоде
			r=(r*k)%c;
			if(r==0) break;
			periodNumber++;
		}
		prePeriod = length5>=length2?length5:length2;//если периодическая дробь смешенная, то кол-во цифр до периода
		Stack<Integer> arr = new Stack<Integer>();
		int z= a/b;// целая часть числа
		do{
			r =z%k;
			z = z/k;
			arr.push(r);
		}while(z!=0);
		while(!arr.isEmpty()){
			System.out.print(arr.pop());
		}//обратный ход для записи числа в k-ичной системе счисления
		System.out.print(",");
		a=(a%b);//дробная часть
		
		for(int i =0; i<prePeriod; i++){//запись дробной части в k-ичной системе
			System.out.print((a*k/b));
			a*=k;//10
			a%=b;
		}
		System.out.print("(");
		for(int i=0; i<periodNumber; i++){//апись периода
			System.out.print((a*k/b));
			a*=k;
			a%=b;
		}
		System.out.print(")");
	}
	
	public static void period(double a, double b, int k){//перевод в k-ичную систему для не целых чисел
		
		int c = (int)(a/b);//выделение целой части
		double d = a/b-c ;// дробная часть
		double e;//переменная для работы с дробной частью
		int r;//остаток от деления
		//int[] array = new int[100];
		Stack<Integer> arr = new Stack<Integer>();
		e = d;
		do{//вычисление целой части в k системе
			r = (c%k);
			c = c/k;
			arr.push(r);
		}while(c!=0);
		while(!arr.isEmpty()){//вывод числа
			System.out.print(arr.pop());
		}
		System.out.print(",");
		for(int i=0; i<10; i++){//вычисление дробной части
			e=e*k;
			System.out.print((int)(e));
			e=e-(int)e;
			if(e==0) break;
		}
		
		
		for(int i=0; i<10; i++){
			
		}
	}
	
	public static boolean isPereodic(int b){
		n = b+1;
		generatePrimeNumbers();
		if(prime[b]){
			return true;
		}
		else{
			arrayOfPrime();
			for(int i=1; i<prime2.length; i++){//есть ли простые числа в знаменателе 
				if(b%prime2[i]==0) return true;
			}
		}
		return false;
	}
	
	public static void arrayOfPrime(){//из решета забираем только простые числа
		ArrayList<Integer> arrList = new ArrayList<Integer>();
        for(int i=0; i<n; i++){
        	if(prime[i]==true) {arrList.add(i);}
        }
        prime2 = arrList.toArray(new Integer[arrList.size()]);
	}
		
	public static void generatePrimeNumbers(){ //решето Эратосфена
		prime = new boolean[n];
		for( int i =0; i<n; i++){
			prime[i]=true;	
		}
		Arrays.fill(prime,true);
		prime[1] = false;
		for (int i=2; i*i < n; i++){
				if (prime[i]){
					for (int j=i*i; j < n; j+=i){
						prime[j] = false;}}  }//!!!!!!!!
		if(n>2) prime[2]= false;
		if (n>5) prime[5]= false;// исключение нулевых периодов
	}
	

	public static boolean isPrime(int a){
	        return prime[a];
	}
	
	public static void main(String... args){
		Scanner in = new Scanner(System.in);
		System.out.println("Введите два числа и систему счисления: ");
		double a = in.nextDouble();
		double b =  in.nextDouble();
		int k = in.nextInt();
		if(a>0&&b<0 || b>0&&a<0) {//обработка знака
			System.out.print("-");
			a=Math.abs(a);
			b = Math.abs(b);
		}
		if(a<0 && b<0){
			a=Math.abs(a);
			b = Math.abs(b);
		}
		if (a%1==0 && b%1==0) {//число целое?
			if (isPereodic((int)b)) {
			period((int)a,(int)b, k);}
			else{
				period(a,b,k);
			}
		}
		else {period(a,b,k);}
		in.close();
	}
}
