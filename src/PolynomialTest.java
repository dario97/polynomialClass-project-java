import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {
    private Polynomial pol1;
    private Polynomial pol2;
    private Polynomial pol3;
    private Polynomial pol4;
    private Polynomial pol5;
    private Integer integer1;


    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() {

    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() {

    }


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        int[] intArray1 = {1, 5, -6, 8};
        int[] intArray2 = {-1, 5, 75};
        int[] intArray3 = {0,5,0,8};

        pol1 = new Polynomial(intArray1);
        pol2 = new Polynomial(intArray1);
        pol3 = new Polynomial(intArray2);
        pol4 = new Polynomial(intArray3);
        pol5 = new Polynomial();

        integer1 = 2;

    }


    @org.junit.jupiter.api.AfterEach
    void tearDown() {

    }

    @org.junit.jupiter.api.Test
    void add() {
        int[] arr = {-1,10,75,8};
        Polynomial resultadoEsperado = new Polynomial(arr);

        System.out.println("---add Test---");
        System.out.println("Suma entre: " + pol3.toString() + " + " + pol4.toString());
        System.out.println("Resultado esperado: " + resultadoEsperado.toString());

        assertTrue(pol3.add(pol4).equals(resultadoEsperado));

        System.out.println("Resultado obtenido: " + pol3.add(pol4).toString());
    }

    @org.junit.jupiter.api.Test
    void testGetCoefficient() {
        System.out.println("---getCoefficient Test---");
        System.out.println("Polinomio de entrada: " + pol1.toString());
        assertEquals(8, pol1.getCoefficient(3));
        assertEquals(0, pol1.getCoefficient(456546));
        assertEquals(-6, pol1.getCoefficient(1));
    }


    @org.junit.jupiter.api.Test
    void setCoefficient() {
        System.out.println("---setCoefficient Test---");
        System.out.println("Polinomio de entrada: " + pol1.toString());

        pol1.setCoefficient(0, 25);
        pol1.setCoefficient(2, 0);
        pol1.setCoefficient(4,5);
        System.out.println("Polinomio de salida: " + pol1.toString());

        System.out.println("Polinomio de entrada: " + pol4.toString());
        pol4.setCoefficient(0,4);
        pol4.setCoefficient(2,45);
        System.out.println("Polinomio de salida: " + pol4.toString());

        assertEquals(25, pol1.getCoefficient(0));
        assertEquals(0,pol1.getCoefficient(2));
        assertEquals(5,pol1.getCoefficient(1));
        assertEquals(5, pol1.getCoefficient(4));

        assertEquals(4, pol4.getCoefficient(0));
        assertEquals(45, pol4.getCoefficient(2));

    }

    @org.junit.jupiter.api.Test
    void testValueOf() {
        float value = 2;

        System.out.println("---valueOf Test---");
        System.out.println("Polinomio de entrada: " + pol1.toString());
        System.out.println("Valor de entrada: " + value);
        System.out.println("Resultado esperado: 51");


        assertEquals(51, pol1.valueOf(value));
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        System.out.println("---equals Test---");
        System.out.println("Polinomios de entrada: \n" + pol1.toString() + "\n" + pol2.toString() + "\n" + pol3.toString());
        assertTrue(pol1.equals(pol2));
        assertFalse(pol1.equals(pol3));
        assertFalse(pol1.equals(integer1));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        System.out.println("---toString Test---");
        System.out.println("Polinomio de entrada: " + pol1.toString());
        System.out.println("Valor esperado: p(x) = 8x^3 -6x^2 + 5x^1 + 1" );
        assertTrue(pol1.toString().equals("p(x) = 8x^3 -6x^2 + 5x^1 + 1"));
    }
}