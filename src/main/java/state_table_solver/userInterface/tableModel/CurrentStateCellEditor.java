package state_table_solver.userInterface.tableModel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import state_table_solver.Controller;
import state_table_solver.stateTable.State;

/**
 * <p>State cell editor for current state column in JTable.
 * 
 * @author Jacob Head
 */

public class CurrentStateCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private State nextState;
    private int stateIndex;
    private JButton stateSelectorBtn;
    private JRootPane parentPane;
    private Controller controller;

    public CurrentStateCellEditor(Controller c) {
        JButton stateSelectorBtn = new JButton();
        stateSelectorBtn.setOpaque(false);
        stateSelectorBtn.setContentAreaFilled(false);
        stateSelectorBtn.setBorderPainted(false);
        stateSelectorBtn.addActionListener(this);
        this.stateSelectorBtn = stateSelectorBtn;
        this.controller = c;
    }

    @Override
    public Object getCellEditorValue() {
        return this.nextState;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.parentPane = table.getRootPane();
        setStateIndex(row);
        if (value instanceof State) {
            this.nextState = (State) value;
        }
        stateSelectorBtn.setText(this.nextState.getId());
        return this.stateSelectorBtn;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JLabel inputLabel = new JLabel("State id:");
        JTextField textField = new JTextField();
        Object textInput = new Object[] {inputLabel, textField};
        Object[] options = new Object[] {"Delete", "Save", "Cancel"};

        int response = JOptionPane.showOptionDialog(
            this.parentPane,
            textInput,
            "Create a new project",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            null
        );

        switch(response) {
            case 0:
                if(this.controller.appData().getStateTable().getStateCount() < 3) {
                    //TODO alert here
                    return;
                }
                controller.removeRow(getStateIndex());
                cancelCellEditing();
            case 1:
                this.nextState.setId(textField.getText());
                this.parentPane.repaint();
                this.parentPane.validate();
                stopCellEditing();
            default:
                cancelCellEditing();
        }
        
    }

    public void setStateIndex(int stateIndex) {
        this.stateIndex = stateIndex;
    }

    public int getStateIndex() {
        return stateIndex;
    }

}