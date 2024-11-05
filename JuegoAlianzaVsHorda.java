import java.util.Scanner;

// Clase base Personaje
class Personaje {
    String nombre;
    int salud;
    int fuerza;
    int agilidad;
    int ataque;
    int recuperacion;
    int ataqueEspecial;
    int ataquesEspecialesRestantes = 3;
    boolean defendiendo;

    // Constructor del personaje
    public Personaje(String nombre, int salud, int fuerza, int agilidad, int ataque, int recuperacion, int ataqueEspecial) {
        this.nombre = nombre;
        this.salud = salud;
        this.fuerza = fuerza;
        this.agilidad = agilidad;
        this.ataque = ataque;
        this.recuperacion = recuperacion;
        this.ataqueEspecial = ataqueEspecial;
        this.defendiendo = false;
    }

    // Método para atacar
    public void atacar(Personaje enemigo) {
        System.out.println(nombre + " ataca a " + enemigo.nombre + " infligiendo " + ataque + " de daño.");
        enemigo.recibirDaño(ataque);
    }

    // Método para realizar un ataque especial
    public void realizarAtaqueEspecial(Personaje enemigo) {
        if (ataquesEspecialesRestantes > 0) {
            System.out.println(nombre + " realiza su ataque especial infligiendo " + ataqueEspecial + " de daño.");
            enemigo.recibirDaño(ataqueEspecial);
            ataquesEspecialesRestantes--;
        } else {
            System.out.println("No te quedan ataques especiales.");
        }
    }

    // Método para defenderse
    public void defender() {
        System.out.println(nombre + " se está defendiendo.");
        defendiendo = true;
    }

    // Método para recibir daño
    public void recibirDaño(int cantidad) {
        if (defendiendo) {
            cantidad = (int)(cantidad * 0.60);  // Reduce el daño a 60% si se está defendiendo
            System.out.println(nombre + " bloquea parte del ataque. Daño recibido: " + cantidad);
        } else {
            System.out.println(nombre + " recibe " + cantidad + " de daño.");
        }
        salud -= cantidad;
        if (salud < 0) salud = 0;
        defendiendo = false;  // Deja de defenderse después de un turno
    }

    // Método para aumentar el poder de ataque
    public void aumentarPoder() {
        int incremento = (int)(0.40 * ataque);  // Incrementa un 40% del ataque
        ataque += incremento;
        System.out.println(nombre + " aumenta su ataque en " + incremento + ". Ataque actual: " + ataque);
    }

    // Método para recuperarse
    public void recuperarse() {
        System.out.println(nombre + " se recupera " + recuperacion + " puntos de vida.");
        salud += recuperacion;
        if (salud > 2000) salud = 2000;  // Vida máxima es 2000
    }

    // Mostrar las estadísticas del personaje
    public void mostrarEstadisticas() {
        System.out.println("Estadísticas de " + nombre + ":");
        System.out.println("Salud: " + salud);
        System.out.println("Ataque: " + ataque);
        System.out.println("Ataques especiales restantes: " + ataquesEspecialesRestantes);
        System.out.println();
    }

    // Verifica si el personaje sigue con vida
    public boolean estaVivo() {
        return salud > 0;
    }
}

// Subclases específicas para los personajes

class Mago extends Personaje {
    public Mago(String nombre) {
        super(nombre, 2000, 360, 150, 210, 100, 600);
    }
}

class Brujo extends Personaje {
    public Brujo(String nombre) {
        super(nombre, 2000, 135, 150, 210, 100, 300);
    }
}

class Sacerdote extends Personaje {
    public Sacerdote(String nombre) {
        super(nombre, 2000, 110, 150, 190, 100, 400);
    }
}

class Chaman extends Personaje {
    public Chaman(String nombre) {
        super(nombre, 2000, 90, 150, 180, 100, 450);
    }
}

class Cazador extends Personaje {
    public Cazador(String nombre) {
        super(nombre, 2000, 50, 150, 250, 100, 600);
    }
}

class Guerrero extends Personaje {
    public Guerrero(String nombre) {
        super(nombre, 1500, 200, 160, 220, 200, 650);
    }
}

class Picaro extends Personaje {
    public Picaro(String nombre) {
        super(nombre, 1500, 200, 100, 210, 200, 320);
    }
}

class Druida extends Personaje {
    public Druida(String nombre) {
        super(nombre, 1500, 200, 180, 230, 200, 340);
    }
}

class CaballeroDeLaMuerte extends Personaje {
    public CaballeroDeLaMuerte(String nombre) {
        super(nombre, 1500, 200, 150, 210, 200, 360);
    }
}

class Paladin extends Personaje {
    public Paladin(String nombre) {
        super(nombre, 1500, 200, 120, 220, 200, 580);
    }
}

// Juego Alianza vs Horda
public class JuegoAlianzaVsHorda {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Elección de personajes para dos jugadores
        System.out.println("Jugador 1, elige tu clase:");
        Personaje jugador1 = elegirPersonaje(scanner);
        
        System.out.println("Jugador 2, elige tu clase:");
        Personaje jugador2 = elegirPersonaje(scanner);
    
        // Ciclo de turnos
        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            System.out.println("Turno del: " + jugador1.nombre );
            mostrarMenu(jugador1, jugador2, scanner);

            // Mostrar vida del jugador 2
            jugador2.mostrarEstadisticas();

            if (!jugador2.estaVivo()) {
                System.out.println(jugador1.nombre + "¡ha ganado!");
                break;
            }

            System.out.println("Turno del: " + jugador2.nombre );
            mostrarMenu(jugador2, jugador1, scanner);

            // Mostrar vida del jugador 1
            jugador1.mostrarEstadisticas();

            if (!jugador1.estaVivo()) {
                System.out.println(jugador2.nombre + "¡ha ganado!");
                break;
            }
        }

        scanner.close();
    }

    // Método para elegir el personaje de cada jugador
    public static Personaje elegirPersonaje(Scanner scanner) {
        System.out.println("Elige tu clase: (1) Mago, (2) Guerrero, (3) Brujo, (4) Sacerdote, (5) Chaman, (6) Cazador, (7) Picaro, (8) Druida, (9) Caballero de la Muerte, (10) Paladin");
        int clase = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        switch (clase) {
            case 1:
                return new Mago("Mago");
            case 2:
                return new Guerrero("Guerrero");
            case 3:
                return new Brujo("Brujo");
            case 4:
                return new Sacerdote("Sacerdote");
            case 5:
                return new Chaman("Chaman");
            case 6:
                return new Cazador("Cazador");
            case 7:
                return new Picaro("Picaro");
            case 8:
                return new Druida("Druida");
            case 9:
                return new CaballeroDeLaMuerte("Caballero de la Muerte");
            case 10:
                return new Paladin("Paladin");
            default:
                System.out.println("Clase no válida. Se seleccionará Guerrero por defecto.");
                return new Guerrero("Guerrero");
        }
    }

    // Menú de acciones para los jugadores
    public static void mostrarMenu(Personaje personaje, Personaje oponente, Scanner scanner) {
        System.out.println("Elige tu acción: (1) Atacar, (2) Defender, (3) Aumentar poder, (4) Recuperarse, (5) Ataque especial");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                personaje.atacar(oponente);
                break;
            case 2:
                personaje.defender();
                break;
            case 3:
                personaje.aumentarPoder();
                break;
            case 4:
                personaje.recuperarse();
                break;
            case 5:
                personaje.realizarAtaqueEspecial(oponente);
        }
    }
}
