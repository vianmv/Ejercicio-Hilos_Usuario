/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author viane
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class HilosUsuario extends Thread {

    public int inicio;
    public int fin;
    public int resultado;

    public HilosUsuario(int fin) {
        this.inicio = 0;
        this.fin = fin;  //Se asigna el valor ingresado
    }

    @Override
    public void run() {
        int suma = 0; 
        for (int i = inicio; i <= fin; i++) {
            suma += i;
            try {
                Thread.sleep(50); //Pausa de 50ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //suma parcial, getName devuelve el nombre del hilo actual
            System.out.println("Hilo " + getName() + " suma parcial: " + suma);
        }
        resultado = suma; //guarda el resultado final
        //muestra suma final
        System.out.println("Hilo " + getName() + ": suma de " + inicio + " a " + fin + " es " + resultado);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numHilos = 0;
        HilosUsuario[] hilos = null;

        while (true) {
            try {
                System.out.print("Ingresa el numero de hilos: ");
                numHilos = sc.nextInt();
                
                if (numHilos > 0) {
                    hilos = new HilosUsuario[numHilos];
                    break; //Salir del ciclo cuando es válido
                } else {
                    System.out.println("Debe ser un numero mayor que 0");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingresa solo numeros"); //Manejo de error

            }
        }

        //pedir número para cada hilo
        for (int i = 0; i < numHilos; i++) {
            int fin = 0;
            while (true) {
                try {
                    System.out.print("Hilo " + (i + 1) + "Ingresa un numero: ");
                    fin = sc.nextInt();

                    if (fin >= 0) {
                        break;
                    } else {
                        System.out.println("El numero debe ser 0 o mayor");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ingresa solo numeros");
                }
            }
            hilos[i] = new HilosUsuario(fin); //Crea el hilo con el valor ingresado
        }

        //Iniciar hilos
        for (int i = 0; i < numHilos; i++) {
            hilos[i].start(); //Inicia el hilo
        }
    }
}
