package com.chess.jfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Guiller
 */
public class FXMLChessController extends Application {
    //Aqui se vinculan los elementos visuales a java
    @FXML
    private ImageView source;
    @FXML
    private Pane target;
    @FXML
    private ClipboardContent clipboard;

//    private Partida p = new Partida();

    //coger todos los image view
    //HACER LO QUE ESTÁ HECHO PERO PARA TODAS LAS CASILLAS
    //https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm EXAMPLE 2

    //Primer evento
    @FXML
    private void dragDetected(Event event) {
        // cojo el origen del evento (el imageview)
        try {
            source = (ImageView) event.getSource();
            //drag was detected, start a drag-and-drop gesture
            //allow any transfer mode
            Dragboard dragBoard = source.startDragAndDrop(TransferMode.ANY);

            clipboard = new ClipboardContent();
            //Meter la imagen en el clipboard
            clipboard.putImage(source.getImage());
            dragBoard.setContent(clipboard);

            event.consume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void dragEntered(DragEvent event) {
        //the drag-and-drop gesture entered the target
        //show to the user that it is an actual gesture target
        try {
            if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
                target.setStyle("-fx-background-color: #303030");
                //target = (Pane) event.getSource();
                //Pane casilla = (Pane) target.getChildren();
                //Cambiar de color la casilla
            }

            event.consume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Segundo evento
    @FXML
    private void dragOver(Event evt) {
        DragEvent dragEvt = (DragEvent) evt;
        //data is dragged over the target
        //accept it only if its not dragged from the same node and if it has a string data
        try {
            if (dragEvt.getGestureSource() != target && dragEvt.getDragboard().hasImage()) {
                //allow for both copying and moving, whatever user chooses
                dragEvt.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                System.out.println("MOVIENDO");
            }

            dragEvt.consume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Tercer evento
    @FXML
    private void dragDropped(Event evt) {
        DragEvent dropEvt = (DragEvent) evt;

        try {
            //data dropped
            //if there is a image data on dragboard, read it and use it
            Dragboard db = dropEvt.getDragboard();

            boolean success = false;
            if (db.hasImage()) {
                ImageView iv = new ImageView(db.getImage());
                target = (Pane) evt.getSource();
                target.getChildren().add(iv);

                Pane sourcePane = (Pane) source.getParent();
                sourcePane.getChildren().clear();

                //source.setImage(null);
                success = true;

                // adivinar pane origen y pane destino, para saber a que casilla equivale
            }
            //let the source know whether the image was successfully transferred and used
            dropEvt.setDropCompleted(success);

            dropEvt.consume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
    }
    
    
    
    
    
    
    
    
    
    
    
//    @Override
//    public void start(Stage stage) {
//        stage.setTitle("Hello Drag And Drop");
//
//        Group root = new Group();
//        Scene scene = new Scene(root, 400, 200);
//        scene.setFill(Color.LIGHTGREEN);
//
//        final Text source = new Text(50, 100, "DRAG ME");
//        source.setScaleX(2.0);
//        source.setScaleY(2.0);
//
//        final Text target = new Text(250, 100, "DROP HERE");
//        target.setScaleX(2.0);
//        target.setScaleY(2.0);
//
//        source.setOnDragDetected(new EventHandler<MouseEvent>() {
//            @FXML
//            public void handle(MouseEvent event) {
//                /* drag was detected, start drag-and-drop gesture*/
//                System.out.println("onDragDetected");
//
//                /* allow any transfer mode */
//                Dragboard db = source.startDragAndDrop(TransferMode.ANY);
//
//                /* put a string on dragboard */
//                ClipboardContent content = new ClipboardContent();
//                content.putString(source.getText());
//                db.setContent(content);
//
//                event.consume();
//            }
//        });
//
//        target.setOnDragOver(new EventHandler<DragEvent>() {
//            @FXML
//            public void handle(DragEvent event) {
//                /* data is dragged over the target */
//                System.out.println("onDragOver");
//
//                /* accept it only if it is  not dragged from the same node 
//                 * and if it has a string data */
//                if (event.getGestureSource() != target
//                        && event.getDragboard().hasString()) {
//                    /* allow for both copying and moving, whatever user chooses */
//                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//                }
//
//                event.consume();
//            }
//        });
//
//        target.setOnDragEntered(new EventHandler<DragEvent>() {
//            @FXML
//            public void handle(DragEvent event) {
//                /* the drag-and-drop gesture entered the target */
//                System.out.println("onDragEntered");
//                /* show to the user that it is an actual gesture target */
//                if (event.getGestureSource() != target
//                        && event.getDragboard().hasString()) {
//                    target.setFill(Color.GREEN);
//                }
//
//                event.consume();
//            }
//        });
//
//        target.setOnDragExited(new EventHandler<DragEvent>() {
//            @FXML
//            public void handle(DragEvent event) {
//                /* mouse moved away, remove the graphical cues */
//                target.setFill(Color.BLACK);
//
//                event.consume();
//            }
//        });
//
//        target.setOnDragDropped(new EventHandler<DragEvent>() {
//            @FXML
//            public void handle(DragEvent event) {
//                /* data dropped */
//                System.out.println("onDragDropped");
//                /* if there is a string data on dragboard, read it and use it */
//                Dragboard db = event.getDragboard();
//                boolean success = false;
//                if (db.hasString()) {
//                    target.setText(db.getString());
//                    success = true;
//                }
//                /* let the source know whether the string was successfully 
//                 * transferred and used */
//                event.setDropCompleted(success);
//
//                event.consume();
//            }
//        });
//
//        source.setOnDragDone(new EventHandler<DragEvent>() {
//            @FXML
//            public void handle(DragEvent event) {
//                /* the drag-and-drop gesture ended */
//                System.out.println("onDragDone");
//                /* if the data was successfully moved, clear it */
//                if (event.getTransferMode() == TransferMode.MOVE) {
//                    source.setText("");
//                }
//
//                event.consume();
//            }
//        });
//
//        root.getChildren().add(source);
//        root.getChildren().add(target);
//        stage.setScene(scene);
//        stage.show();
//    }
}