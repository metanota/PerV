package ru.md24inc.alembic.pervoc.gui;

import java.io.File;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPaneBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class TranscriptPanel extends BorderPane {

    List<Object> consonants;
    List<Object> vowels;
    List<Object> special;
    Node typeIn;

    public TranscriptPanel() {
        initSymbols();
        setVisible(false);
    }

    private void initSymbols() {
        // Setting up Panel properties
        // setLayout(new GridLayout(0, 10, 2, 2));
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        // Reading xml file with phonetic symbols
        XMLConfiguration mxconf = null;
        try {
            mxconf = new XMLConfiguration(new File("src/main/resources/symbols.xml"));
        } catch (ConfigurationException e) {
            System.out.println("Reading config failed due: " + e.getMessage());
        }
        // Fill up collection variables with needed symbols
        consonants = mxconf.getList("Consonants.symbol");
        vowels = mxconf.getList("Vowels.symbol");
        special = mxconf.getList("Special.symbol");

        setTop(new SymbolRow("Consonants", consonants, Color.rgb(0, 0, 150)));
//        add(Box.createRigidArea(new Dimension(0, 10)));
        setCenter(new SymbolRow("Vowels", vowels, Color.rgb(150, 0, 0)));
//        add(Box.createRigidArea(new Dimension(0, 10)));
        setBottom(new SymbolRow("Special", special, Color.rgb(93, 62, 0)));
    }

    private static class SymbolRow extends BorderPane {
//        Border fronties = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);

        public SymbolRow(String h, List<Object> in, Color color) {
//            setLayout(new BorderLayout(5, 5));

            setLeft(new Label(h));

            GridPane symbolsPanel = GridPaneBuilder.create()
                    .hgap(2)
                    .vgap(2)
                    .build();

            int i = 0;
            for (Object ob : in) {
                Node symbol = createSymbolLabel(ob, color);
                symbolsPanel.add(symbol, 10 - (i % 10), i / 10); // FIXME math magic! ;)
                i++;
            }

            setCenter(symbolsPanel);
        }

        private Node createSymbolLabel(Object ob, Color color) {
            Text symbol = new Text(ob.toString());
//            symbol.setStyle("-fx-border-color: black;");
//            symbol.setBorder(fronties);
            symbol.setFill(color);
//            symbol.setToolTipText("Here will be hint");
            symbol.setTextAlignment(TextAlignment.CENTER);
            symbol.setFont(Font.font("SansSerif", 16));
            return symbol;

//            Pane p = new Pane();
//            p.setStyle("-fx-border-color: black;");
//            p.getChildren().add(symbol);
//            return p;
        }

//        @Override
//        public void mouseClicked(MouseEvent e) {
//            JLabel ex = (JLabel) e.getComponent();
//            System.out.print(ex.getText() + " ");
//            typeInActiveCell(ex);
//        }

//        private void typeInActiveCell(JLabel ex) {
//            System.out.println("Class of column: "+typeIn.getColumnClass(typeIn.getSelectedColumn()));
//            Class nc = typeIn.getColumnClass(typeIn.getSelectedColumn());
            /*if (typeIn.isCellSelected(typeIn.getSelectedRow(), typeIn.getSelectedColumn())) {
                System.out.println("Value at selected cell: " + typeIn.getModel().getValueAt(typeIn.getSelectedRow(), typeIn.getSelectedColumn()).toString());
                typeIn.setValueAt(typeIn.getValueAt(typeIn.getSelectedRow(), typeIn.getSelectedColumn()).toString() + ex.getText(), typeIn.getSelectedRow(), typeIn.getSelectedColumn());
            } else {
                System.out.println("Sorry cell not selected");
            }
            typeIn.repaint();*/
//        }
    }

    public void addTypeIn(Node typeIn){
        this.typeIn = typeIn;
    }

    public void toggleVisibility() {
        setVisible(!isVisible());
    }
}
