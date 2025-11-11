import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Time_Calculator extends JFrame {

    private JSpinner diasSpinner;
    private JLabel horasLabel;
    private JLabel minutosLabel;
    private JLabel segundosLabel;
    private JLabel mensajeFinalLabel;

    public Time_Calculator() {
        setTitle("Calculadora de Tiempo");
        setSize(400, 380);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // Fuentes
        Font tituloFont = new Font("Arial", Font.BOLD, 18);
        Font resultadoFont = new Font("Arial", Font.BOLD, 16);
        Font normalFont = new Font("Arial", Font.PLAIN, 14);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel titulo = new JLabel("Horas - Minutos y Segundos");
        titulo.setFont(tituloFont);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

        // Ingreso de días
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(new JLabel("Ingrese la cantidad de días:"), gbc);

        SpinnerNumberModel model = new SpinnerNumberModel(1.0, 0.0, 1000.0, 0.25);
        diasSpinner = new JSpinner(model);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(diasSpinner, "#.##");
        diasSpinner.setEditor(editor);
        gbc.gridx = 1;
        add(diasSpinner, gbc);

        // Botón CALCULAR
        JButton calcularButton = new JButton("CALCULAR");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(calcularButton, gbc);

        // Resultados
        horasLabel = crearFilaResultado("Horas:", resultadoFont, 3, gbc);
        minutosLabel = crearFilaResultado("Minutos:", resultadoFont, 4, gbc);
        segundosLabel = crearFilaResultado("Segundos:", resultadoFont, 5, gbc);

        // Mensaje final con soporte para múltiples líneas
        mensajeFinalLabel = new JLabel("<html><div style='text-align:center;'> </div></html>");
        mensajeFinalLabel.setFont(normalFont);
        mensajeFinalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mensajeFinalLabel.setPreferredSize(new Dimension(350, 50)); // Suficiente para evitar corte

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(mensajeFinalLabel, gbc);

        // Acción del botón
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTiempo();
            }
        });
    }

    private JLabel crearFilaResultado(String texto, Font font, int fila, GridBagConstraints gbc) {
        gbc.gridy = fila;
        gbc.gridx = 0;
        add(new JLabel(texto), gbc);

        JLabel label = new JLabel("0");
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc.gridx = 1;
        add(label, gbc);

        return label;
    }

    private void calcularTiempo() {
        double dias = (Double) diasSpinner.getValue();

        int horas = (int) (dias * 24);
        int minutos = (int) (dias * 24 * 60);
        int segundos = (int) (dias * 24 * 60 * 60);

        horasLabel.setText(String.valueOf(horas));
        minutosLabel.setText(String.valueOf(minutos));
        segundosLabel.setText(String.valueOf(segundos));

        String mensaje = String.format(
                "<html><div style='text-align:center;'>%.2f días tienen %d horas<br>%d minutos - %d segundos</div></html>",
                dias, horas, minutos, segundos);
        mensajeFinalLabel.setText(mensaje);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Time_Calculator().setVisible(true);
        });
    }
}

