
package Clases;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Persona {
 
    //Constantes    
    private final static char SEXO_DEFECTO = 'H';
    public static final int PESO_BAJO = -1;
    public static final int PESO_IDEAL = 0; 
    public static final int SOBREPESO = 1;
 
    //Atributos
    private String nombre;
    private int edad;
    private String NSS;
    private char sexo;
    private double peso;
    private double altura;
 
    //Contructor   
    public Persona(String nombre, int edad, char sexo, double peso, double altura) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        generarNSS();
        comprobarSexo();
    }
 
    //Métodos privados
    private void comprobarSexo() {
 
        //Si el sexo no es una H o una M, por defecto es H
        if (sexo != 'H' && sexo != 'M') {
            this.sexo = SEXO_DEFECTO;
        }
    }
 
    private void generarNSS() {
        
        //Generamos una cadena aleatoria con 5 números y 3 letras
        String uuid = UUID.randomUUID().toString().toUpperCase();
        Stream<Character> digit = uuid.chars().mapToObj(i -> (char) i).filter(Character::isDigit).limit(5);
        Stream<Character> alpha = uuid.chars().mapToObj(i -> (char) i).filter(Character::isAlphabetic).limit(3);
        List<Character> collect = Stream.concat(digit, alpha).collect(Collectors.toList());
        Collections.shuffle(collect);
 
        //Pasamos la cadena generada para el NSS a String
        NSS = collect.stream().map(Object::toString).collect(Collectors.joining());
        
    }
  
    //Métodos publicos
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
 
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
 
    
     // Calcula el indice de masa corporal

    public int calcularIMC() {
        //Calculamos el peso de la persona
        double pesoActual = peso / (Math.pow(altura, 2));      
             
        if (this.sexo == 'H') {
            
            //Si es hombre
            
            if (pesoActual >= 20 && pesoActual <= 25) {
                return PESO_IDEAL;
            } else if (pesoActual < 20) {
                return PESO_BAJO;
            } else {
                return SOBREPESO;
            }
            
        } else {
            
            //Si es mujer
            
            if (pesoActual >= 19 && pesoActual <= 24) {
                return PESO_IDEAL;
            } else if (pesoActual < 19) {
                return PESO_BAJO;
            } else {
                return SOBREPESO;
            }
            
        }
    }
 

    // La persona es mayor de edad
     
    public boolean esMayorDeEdad() {
        boolean mayor = false;
        if (edad >= 18) {
            mayor = true;
        }
        return mayor;
    }
 

    // Devuelve la informacion de la persona
    
    @Override
    public String toString() {
        String sexo;
        if (this.sexo == 'H') {
            sexo = "hombre";
        } else {
            sexo = "mujer";
        }
        return "Informacion de la persona:\n"
                + "Nombre: " + nombre + "\n"
                + "Sexo: " + sexo + "\n"
                + "Edad: " + edad + " años\n"
                + "NSS: " + NSS + "\n"
                + "Peso: " + peso + " kg\n"
                + "Altura: " + altura + " metros\n";
    }
 
}


