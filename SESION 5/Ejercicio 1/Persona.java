import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private Cuenta cuenta;
    private char tipoCliente;
    private static int cuentaCorrelativaC = 1000;
    private static int cuentaCorrelativaB = 5000;
    private static int cuentaCorrelativaE = 8000;
    public Persona(int id, String nombre, String apellido, char tipoCliente) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoCliente = tipoCliente;
        // Determinar el número de cuenta
        String numeroCuenta;
        if (tipoCliente == 'B') {
            numeroCuenta = "B" + cuentaCorrelativaB;
            cuentaCorrelativaB++;
        } else if (tipoCliente == 'E') {

            numeroCuenta = "E" + cuentaCorrelativaE;
            cuentaCorrelativaE++;
        } else {
            numeroCuenta = "C" + cuentaCorrelativaC;
            cuentaCorrelativaC++;

        }
        this.cuenta = new Cuenta(numeroCuenta, 50); // El saldo mínimo es 50 soles
    }
    // Getter y setter para tipoCliente
    public char getTipoCliente() {
        return tipoCliente;
    }
    public void setTipoCliente(char tipoCliente) {

        this.tipoCliente = tipoCliente;

    }
    // Getter para Cuenta
    public Cuenta getCuenta() {
        return cuenta;
    }
    // Implementación de los métodos retirar y depositar
    public void retirar(double cantidad) {
        cuenta.retirar(cantidad);
    }
    public void depositar(double cantidad) {
        cuenta.depositar(cantidad);
    }
    // Implementación del método toString
    @Override
    public String toString() {
        return "Cliente: " + id + "\nTipo: " + tipoCliente + "\nNombre: " + nombre + " " + apellido + "\nNúmero de cuenta: " + cuenta.getNumero() + "\nSaldo: " + cuenta.getSaldo();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Persona> personas = new ArrayList<>();
        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Eliminar Cliente");
            System.out.println("3. Mostrar Clientes");
            System.out.println("4. Salir");
            System.out.println("Ingrese una opción:");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el ID de la persona:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.println("Ingrese el nombre de la persona:");
                    String nombre = scanner.nextLine();



                    System.out.println("Ingrese el apellido de la persona:");
                    String apellido = scanner.nextLine();
                    System.out.println("Ingrese el tipo de cliente (C/B/E):");
                    char tipoCliente = scanner.next().charAt(0);
                    Persona nuevaPersona = new Persona(id, nombre, apellido, tipoCliente);
                    personas.add(nuevaPersona);
                    System.out.println("Cliente agregado con éxito con un saldo inicial de 50 soles.");
                    break;
                case 2:
                    System.out.println("Ingrese el ID del cliente a eliminar:");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    // Buscar y eliminar la persona por ID
                    for (int i = 0; i < personas.size(); i++) {
                        if (personas.get(i).id == idEliminar) {
                            personas.remove(i);
                            System.out.println("Cliente eliminado con éxito.");
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Lista de Clientes:");
                    for (Persona persona : personas) {
                        System.out.println(persona.toString());
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 4);
        scanner.close();
    }
}
class Cuenta {
    private String numero;
    private double saldo;
    public Cuenta(String numero, double saldo) {
        this.numero = numero;
        // Verificar que el saldo sea al menos 50 soles
        if (saldo >= 50) {
            this.saldo = saldo;
        } else {
            this.saldo = 50; // Si el saldo es menor a 50, se establece a 50 soles
        }
    }
    // Getter para número de cuenta
    public String getNumero() {
        return numero;
    }
    // Getter para saldo
    public double getSaldo() {
        return saldo;
    }
    // Implementación de los métodos retirar y depositar
    public void retirar(double cantidad) {
        if (saldo - cantidad >= 50) {

            saldo -= cantidad;
        } else {
            System.out.println("Saldo insuficiente para retirar " + cantidad + " soles.");
        }
    }
    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
        } else {
            System.out.println("La cantidad a depositar debe ser mayor que 0.");
        }
    }
    // Implementación del método toString
    @Override
    public String toString() {
        return "Número de cuenta: " + numero + "\nSaldo: " + saldo;
    }
}

