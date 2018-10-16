package br.com.ifma.view.components.dialog;

import br.com.ifma.view.components.jpanel.JpFase;
import java.awt.Frame;
import java.beans.PropertyChangeEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Pedro Brito
 */
public class DialogMoverQuestao extends JDialog {

    private final JTextField txtFieldDE = new JTextField();
    private final JTextField txtFieldPara = new JTextField();
    private final String msgString1 = "Mover questão";
    private final String msgString2 = "Para posição";
    private final String btnString1 = "Confirmar";
    private final String btnString2 = "Cancelar";
    private final JOptionPane optionPane;
    private final JpFase jpFase;

    public DialogMoverQuestao(Frame frame, JpFase jpFase) throws NumberFormatException {
        super(frame);
        this.jpFase = jpFase;

        Object[] array = {msgString1, txtFieldDE, msgString2, txtFieldPara};

        Object[] options = {btnString1, btnString2};

        optionPane = new JOptionPane(array,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION,
                null,
                options,
                options[0]);

        setContentPane(optionPane);
        setSize(300, 180);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);

        optionPane.addPropertyChangeListener((PropertyChangeEvent e) -> {
            String prop = e.getPropertyName();
            
            if (isVisible()
                    && (e.getSource() == optionPane)
                    && (JOptionPane.VALUE_PROPERTY.equals(prop)
                    || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
                Object value = optionPane.getValue();
                
                if (value == JOptionPane.UNINITIALIZED_VALUE) {
                    return;
                }
                
                optionPane.setValue(
                        JOptionPane.UNINITIALIZED_VALUE);
                
                if (btnString1.equals(value)) {
                    try {
                        int numQuestao = Integer.valueOf(txtFieldDE.getText());
                        int paraPosicao = Integer.valueOf(txtFieldPara.getText());
                        
                        if (paraPosicao > 0 && paraPosicao <= jpFase.getJpQuestoes().size() && numQuestao!=paraPosicao) {
                            jpFase.moverQuestao(numQuestao, paraPosicao);
                            dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(frame, "Nova posição inválida", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ef) {
                        JOptionPane.showMessageDialog(frame, "Informe apenas número", "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (IndexOutOfBoundsException ie) {
                        JOptionPane.showMessageDialog(frame, "Número da questão inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    dispose();
                }
            }
        });
    }

}
