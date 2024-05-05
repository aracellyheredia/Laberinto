/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laberinto;
import java.awt.Color;//importamos la clase color del paquete java.awt
                      //para utilizar colores en el panel
import java.awt.Dimension;//para establecer el tamano del panel
import java.awt.Graphics;//para dibujar en el panel
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

//declaramos la clase laberinto que extiende de jpanel
   public class Laberinto extends JPanel {
       //declaramos una constante size de tipo entero
    private static final  int CELDA_SIZE = 80; // Tamaño de cada celda del laberinto
    private static final int FILAS = 10; // Número de filas del laberinto
    private static final int COLUMNAS = 13; // Número de columnas del laberinto
    private static final Color COLOR_CAMINO = Color.WHITE; // Color de las celdas del camino
    private static final Color COLOR_PARED = Color.black; // Color de las celdas de las paredes
    private static final Color COLOR_ENTRADA = Color.YELLOW; // Color de la entrada del laberinto
    private static final Color COLOR_SALIDA = Color.MAGENTA; // Color de la salida del laberinto
    
    private int[][] laberinto = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 1 representa una pared
        {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1}, // 0 representa una celda vacía
        {1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1},
        {1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1},
        {1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
        {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    private int x = 1; // Posición x inicial del jugador
    private int y = 1; // Posición y inicial del jugador
    //constructor de la clase laberinto 
    public Laberinto() {
        setPreferredSize(new Dimension(COLUMNAS * CELDA_SIZE, FILAS * CELDA_SIZE));//establer tamano de la interfaz,preferido
                                                                               //de un componente en un diseno de un interfaz
        setFocusable(true);//para recibir eventos del teclado y mause
        addKeyListener(new KeyListener() {//es una interfaz que define metodos para manejar eventos, de teclado en un
                                           //..componente
             @Override                              //add-solose usa para agregar un objeto(keylistener)
            public void keyPressed(KeyEvent e) {//se invoca cuando se presiona una tecla
                moverJugador(e.getKeyCode());//keypre,keyta... cotroladores
            }
            
            @Override
            public void keyTyped(KeyEvent e) {}//se invoca cuando se genera y libera una pulsacion de tecla
            
            @Override
            public void keyReleased(KeyEvent e) {}//se invoca cuando se suelta una tecla
        });
    }
    
    
   /* protected void ComponenteDePintura(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                int celdaX = j * CELDA_SIZE;
                int celdaY = i * CELDA_SIZE;
                if (laberinto[i][j] == 1) {
                    g.setColor(COLOR_PARED);
                } else if (i == 1 && j == 1) {
                    g.setColor(COLOR_ENTRADA);
                } else if (i == FILAS - 2 && j == COLUMNAS - 2) {
                    g.setColor(COLOR_SALIDA);
                } else {
                    g.setColor(COLOR_CAMINO);
                }
                g.fillRect(celdaX, celdaY, CELDA_SIZE, CELDA_SIZE);
            }
        }
        g.setColor(Color.BLACK);
        g.fillOval(x * CELDA_SIZE, y * CELDA_SIZE, CELDA_SIZE, CELDA_SIZE);
    }*/
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                int cellX = j * CELDA_SIZE;
                int cellY = i * CELDA_SIZE;
                if (laberinto[i][j] == 1) {
                    g.setColor(COLOR_PARED);
                } else if (i == 1 && j == 1) {
                    g.setColor(COLOR_ENTRADA);
                } else if (i == FILAS - 2 && j == COLUMNAS - 2) {
                    g.setColor(COLOR_SALIDA);
                } else {
                    g.setColor(COLOR_CAMINO);
                }
                g.fillRect(cellX, cellY, CELDA_SIZE, CELDA_SIZE);
            }
        }
        g.setColor(Color.BLACK);
        g.fillOval(x * CELDA_SIZE, y * CELDA_SIZE, CELDA_SIZE, CELDA_SIZE);
    }
    
    private void moverJugador(int clave) {
        int Jx = x;
        int Jy = y;
        //se utiliza para representar eventos 
        if (clave == KeyEvent.VK_UP && y > 1 && laberinto[y - 1][x] != 1) {//arriba
            Jy--;
        } else if (clave == KeyEvent.VK_DOWN && y < FILAS - 2 && laberinto[y + 1][x] != 1) {//abajo
            Jy++;
        } else if (clave == KeyEvent.VK_LEFT && x > 1 && laberinto[y][x - 1] != 1) {//iquierda
            Jx--;
        } else if (clave == KeyEvent.VK_RIGHT && x < COLUMNAS - 2 && laberinto[y][x + 1] != 1) {//derecha
            Jx++;
        }
        if (laberinto[Jy][Jx] != 1) {
            x = Jx; //intentara resolver el algoritmo en estas coordenadas
            y = Jy; //intentara resolver el algoritmo en estas coordenadas
            repaint();
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Laberinto");// construimos un objeto de la clase jframe por defecto
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//indica que cuando el usuario haga click en el boton de cierre, se debera cerrar 
                                                                     //el frame y finalizar la aplicacion
        frame.getContentPane().add(new Laberinto());//es un metodo utilizado para obtener el panel de un contenido principal de un frame
        frame.pack();   //para que se ajuste su tamaÑo
        frame.setLocationRelativeTo(null);//establece la ubicacion del componente
        frame.setVisible(true);//para ser visible y sse muestra en pantalla
        System.out.println(" Juego terminado");
    }
    
   }

