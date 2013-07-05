package ru.md24inc.alembic.pervoc.gui;

import com.google.common.collect.Lists;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;
import javafx.stage.Stage;
import javafx.util.Callback;
import ru.md24inc.alembic.pervoc.dao.VocabularyDao;
import ru.md24inc.alembic.pervoc.domains.Card;

import java.io.File;
import java.util.*;

/**
 * @author miroque
 */
public class MainWindow extends Application {
    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 450;
    public static final int COLUMN_COUNT = 3;

    private FileChooser fj;
    private TableView<Card> tableOfCards = new TableView<Card>();
    private ObservableList<Card> cards = FXCollections.observableArrayList();
    private TranscriptPanel transcriptPanel = new TranscriptPanel();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        final BorderPane bp = BorderPaneBuilder.create()
                .top(createTranscriptPanel())
                .center(createCardsScrollPane())
                .build();

        final BorderPane bp2 = BorderPaneBuilder.create()
                .top(createMenuBar())
                .center(bp)
                .build();

        stage.setScene(new Scene(bp2));
        stage.setWidth(MIN_WIDTH);
        stage.setMinWidth(MIN_WIDTH);
        stage.setHeight(MIN_HEIGHT);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setTitle("Personal Vocabulary (version 0.10)");
        stage.show();
    }

    private MenuBar createMenuBar() {
        return MenuBarBuilder.create()
                .menus(createFileMenu(),
                        createViewsMenu())
                .build();
    }

    private Menu createFileMenu() {
        return MenuBuilder.create()
                .text("File")
                .items(createOpenMenuItem(),
                        createSaveMenuItem(),
                        new SeparatorMenuItem(),
                        createQuitMenuItem())
                .build();
    }

    private MenuItem createQuitMenuItem() {
        return MenuItemBuilder.create()
                .text("Quit")
                .accelerator(KeyCombination.keyCombination("Ctrl+Q"))
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.exit(0); // FIXME wrong way to close
                    }
                })
                .build();
    }

    private MenuItem createSaveMenuItem() {
        return MenuItemBuilder.create()
                .text("Save...")
                .accelerator(KeyCombination.keyCombination("Ctrl+S"))
                .build();
    }

    private Menu createViewsMenu() {
        return MenuBuilder.create()
                .text("Views")
                .items(createTranscriptMenuItem())
                .build();
    }

    private MenuItem createOpenMenuItem() {
        fj = FileChooserBuilder.create()
                .extensionFilters(new FileChooser.ExtensionFilter("Personal Vocabular Files", "*.pvoc"))
                .build();
        return MenuItemBuilder.create()
                .text("Open...")
                .accelerator(KeyCombination.keyCombination("Ctrl+O"))
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        File file = fj.showOpenDialog(null);
                        if (file == null) {
                            return;
                        }
                        cards.setAll(new VocabularyDao().getVocabular(file.toString()));
                        System.out.println("File - " + file.toString());
                        System.out.println(cards.size());
                    }
                })
                .build();
    }

    private MenuItem createTranscriptMenuItem() {
        return MenuItemBuilder.create()
                .text("Transcript")
                .accelerator(KeyCombination.keyCombination("Ctrl+Shift+T"))
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        transcriptPanel.setVisible(!transcriptPanel.isVisible());
                    }
                })
                .build();
    }

    private Node createCardsScrollPane() {
        tableOfCards.setItems(cards);
        tableOfCards.getColumns().setAll(createColumns());
        tableOfCards.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        tableOfCards.addKeyListener(new KeyListener() {

//            @Override
//            public void keyTyped(KeyEvent e) {
//            }

//            @Override
//            public void keyReleased(KeyEvent e) {
//            }

//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_INSERT) {
//                    cards.add(new Card());
//                    tableOfCards.repaint();
//					scrollPaneForTableVoc.repaint();
//                    System.out.println(cards.size());

//                }
//            }
//        });
//        tableOfCards.addComponentListener((ComponentListener) transcriptPanel);
        return tableOfCards;
    }

    private TranscriptPanel createTranscriptPanel() {
        transcriptPanel.setVisible(false);
        transcriptPanel.addTypeIn(tableOfCards);
        return transcriptPanel;
    }

    private static Collection<TableColumn<Card, String>> createColumns() {
        final Collection<TableColumn<Card,String>> columns = Lists.newArrayListWithCapacity(COLUMN_COUNT);
        columns.add(TableColumnBuilder.<Card, String> create().text("Word")
                .cellValueFactory(new Callback<TableColumn.CellDataFeatures<Card, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Card, String> card) {
                        return new ReadOnlyObjectWrapper<String>(card.getValue().getWord().getValue());
                    }
                })
                .build());
        columns.add(TableColumnBuilder.<Card, String>create().text("Transcript")
                .cellValueFactory(new Callback<TableColumn.CellDataFeatures<Card, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Card, String> card) {
                        return new ReadOnlyObjectWrapper<String>(card.getValue().getTranscript().getValue());
                    }
                })
                .build());
        columns.add(TableColumnBuilder.<Card, String>create().text("Translation")
                .cellValueFactory(new Callback<TableColumn.CellDataFeatures<Card, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Card, String> card) {
                        return new ReadOnlyObjectWrapper<String>(card.getValue().getTranslation().getValue());
                    }
                })
                .build());
        return columns;
    }
}
