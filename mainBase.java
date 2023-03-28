package entornosed4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EntornosED4 {

    public static void main(String[] args) {
        Cuestionario c = new Cuestionario();
        c.agregarPregunta(new Pregunta("¿Cuál de estos juegos es un MMO?", List.of(
                new Respuesta("The Binding of Isaac", false),
                new Respuesta("Super Meat Boy", false),
                new Respuesta("Final Fantasy XIV", true),
                new Respuesta("Hatoful Boyfriend", false)
        )));
        c.agregarPregunta(new Pregunta("a", List.of(
                new Respuesta("a", false),
                new Respuesta("aa", true),
                new Respuesta("aaa", false)
        )));
        c.agregarPregunta(new Pregunta("¿?", List.of(
                new Respuesta("Sí", false),
                new Respuesta("No", true)
        )));

        c.agregarPregunta(new Pregunta("owo?", List.of(
                new Respuesta("owo", true),
                new Respuesta("owoo", false),
                new Respuesta("owooo", false),
                new Respuesta("owoooo", false),
                new Respuesta("owoooooo", false)
        )));
  
        c.agregarPregunta(new Pregunta("boop?", List.of(
                new Respuesta("bop", true),
                new Respuesta("boop", false),
                new Respuesta("booop", false),
                new Respuesta("boooop", false),
                new Respuesta("booooop", false)
        )));

        c.preguntar();
        c.imprimirResultados();
        c.calcularNota(0, 0);
    }

}

class Respuesta {

    String respuesta;
    boolean esCorrecta;

    public Respuesta(String respuesta, boolean esCorrecta) {
        this.respuesta = respuesta;
        this.esCorrecta = esCorrecta;
    }

}

class Pregunta {

    private final String pregunta;
    private final List<Respuesta> respuestas;

    // Nota: si crees que agregarás más respuestas, amplía las letras
    private static final String letras = "abcdefghijklmnñopqrstuvwxyz";

    public Pregunta(String pregunta, List<Respuesta> respuestas) {
        this.pregunta = pregunta;
        this.respuestas = respuestas;
    }

    public Pregunta(String pregunta) {
        this.pregunta = pregunta;
        this.respuestas = new ArrayList<>();
    }

    public void agregarRespuesta(Respuesta r) {
        this.respuestas.add(r);
    }

    public boolean respuestaCorrecta(char respuesta) {
        int indice = letras.indexOf(respuesta);
        if (indice == -1) {
            return false;
        }
        return this.respuestas.get(indice).esCorrecta;
    }

    public boolean preguntar(int numero) {
        System.out.println(numero + ". " + this.pregunta);
        int indice = 0;
        for (Respuesta r : this.respuestas) {
            System.out.printf("%c) %s\n", letras.charAt(indice), r.respuesta);
            indice++;
        }
        System.out.println("Elige: ");
        Scanner sc = new Scanner(System.in);
        char respuesta = sc.nextLine().charAt(0);
        return this.respuestaCorrecta(respuesta);
    }
}

class Cuestionario {

    private final ArrayList<Pregunta> preguntas;
    private int aciertos;
    private int errores;// ¿Alguien sabe el antónimo de acierto en este contexto? yo no

    public Cuestionario() {
        this.preguntas = new ArrayList<>();
    }

    public void agregarPregunta(Pregunta p) {
        this.preguntas.add(p);
    }

    public void preguntar() {
        int numero = 1;
        for (Pregunta p : this.preguntas) {
            boolean acierta = p.preguntar(numero);
            numero++;
            if (acierta) {
                System.out.println("Correcto");
                this.aciertos++;
            } else {
                System.out.println("Incorrecto");
                this.errores++;
            }
        }
    }

    public void imprimirResultados() {
        int total = this.preguntas.size();
        double porcentajeAciertos = (100 * (double) this.aciertos) / total;
        double porcentajeErrores = (100 * (double) this.errores) / total;
        System.out.printf("Total de preguntas: %d\nAciertos: %d (%.2f %%)\nErrores: %d (%.2f %%)", total, this.aciertos, porcentajeAciertos, this.errores, porcentajeErrores);
    }
}
  
