package com.third;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.Light;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javax.imageio.spi.ServiceRegistry;

public class HelloController {

    @FXML
    private ColorPicker chooseColor;

    @FXML
    private MenuItem chooseDowolne;

    @FXML
    private MenuItem chooseKolo;

    @FXML
    private MenuItem chooseKwadrat;

    @FXML
    private MenuItem chooseLinia;

    @FXML
    private MenuItem chooseTrojkat;

    @FXML
    private Pane drawPlace;

    // todo zmienic kolory zeby zobaczyc co jest wyzej a co nizej, ladny border

    @FXML
    void drawDowolne(ActionEvent event) {
        Canvas canvas = new Canvas(580,550);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        // todo graphicsContext.setFill(chooseColor.getValue());
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                graphicsContext.beginPath();
                graphicsContext.moveTo(event.getX(), event.getY());
                graphicsContext.stroke();
            }
        });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                graphicsContext.lineTo(event.getX(), event.getY());
                graphicsContext.stroke();
            }
        });
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {

            }
        });
        drawPlace.setOnMousePressed(null);
        drawPlace.setOnMouseDragged(null);
        drawPlace.setOnMouseReleased(null);
        drawPlace.getChildren().add(canvas);
    }

    @FXML
    void drawLinia(ActionEvent event) {
        Line line = new Line();
        // todo line.setFill(chooseColor.getValue() );
        drawPlace.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                line.setVisible(false);
                line.setEndX(mouseEvent.getX());
                line.setEndY(mouseEvent.getY());
                System.out.println("konieczne " + line.getStartX() + line.getStartY() + line.getEndX() + line.getEndY());
                drawPlace.setOnMousePressed(null);
            }
        });
        drawPlace.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                line.setVisible(true);
                line.setStartX(mouseEvent.getX());
                line.setStartY(mouseEvent.getY());
                drawPlace.setOnMouseDragged(null);
            }
        });
        drawPlace.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("poczatek");
                line.setStartX(mouseEvent.getX());
                line.setStartY(mouseEvent.getY());
                System.out.println("koord "+line.getStartX() +" " +line.getStartY() + line.getEndX() + line.getEndY());
                drawPlace.setOnMouseReleased(null);
            }
        });
        drawPlace.getChildren().add(line);
    }

    @FXML
    void drawKolo(ActionEvent event) {
        // todo kolo zeby miescilo sie w drawPlace
        Circle kolo = new Circle();
        kolo.setFill(chooseColor.getValue());
        drawPlace.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                kolo.setCenterX(mouseEvent.getX());
                kolo.setCenterY(mouseEvent.getY());
                drawPlace.setOnMousePressed(null);
            }
        });
        drawPlace.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                kolo.setVisible(true);
                kolo.setRadius(Math.sqrt(Math.pow(kolo.getCenterX() - mouseEvent.getX(),2) + Math.pow(kolo.getCenterY() - mouseEvent.getY(), 2)));
            }
        });
        drawPlace.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                drawPlace.setOnMouseDragged(null);
                System.out.println("poczatek");
                kolo.setRadius(Math.sqrt(Math.pow(kolo.getCenterX() - mouseEvent.getX(),2) + Math.pow(kolo.getCenterY() - mouseEvent.getY(), 2)));
                drawPlace.setOnMouseReleased(null);
            }
        });
        drawPlace.getChildren().add(kolo);
    }

    @FXML
    void drawTrojkat(ActionEvent event) {
        //todo trojkat w druga strone
        Polygon trojkat = new Polygon();
        trojkat.setFill(chooseColor.getValue());
        drawPlace.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                trojkat.getPoints().addAll(new Double[]{
                        mouseEvent.getX(), mouseEvent.getY(),
                        mouseEvent.getX(), mouseEvent.getY(),
                        mouseEvent.getX(), mouseEvent.getY() });
                drawPlace.setOnMousePressed(null);
            }
        });
        drawPlace.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                trojkat.getPoints().set(2, mouseEvent.getX());
                trojkat.getPoints().set(3, mouseEvent.getY());
                trojkat.getPoints().set(4, trojkat.getPoints().get(0) - Math.abs(mouseEvent.getX() - trojkat.getPoints().get(0)));
                trojkat.getPoints().set(5, mouseEvent.getY());
            }
        });
        drawPlace.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                drawPlace.setOnMouseDragged(null);
                drawPlace.setOnMouseReleased(null);
            }
        });
        drawPlace.getChildren().add(trojkat);
    }

    @FXML
    void drawKwadrat(ActionEvent event) {
        //todo Kwardat w lewo, w gore oraz w dol
        Rectangle kwadrat = new Rectangle();
        kwadrat.setFill(chooseColor.getValue());
        drawPlace.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                kwadrat.setVisible(false);
                //kwadrat.setVisible(true);
                kwadrat.setX(mouseEvent.getX());
                kwadrat.setY(mouseEvent.getY());
                System.out.println("pressed");
                drawPlace.setOnMousePressed(null);
            }
        });
        drawPlace.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                kwadrat.setVisible(true);
                System.out.println("moving");
                //kwadrat.setX(Math.min(kwadrat.getX(), mouseEvent.getX()));
                kwadrat.setWidth(Math.abs(kwadrat.getX() - mouseEvent.getX())); // abs(min(_ , _))
                System.out.println("start x " + kwadrat.getX() + "szerokosc" + kwadrat.getWidth());
                //kwadrat.setY(Math.min(kwadrat.getY(), mouseEvent.getY()));
                kwadrat.setHeight(Math.abs(kwadrat.getY() - mouseEvent.getY()));
            }
        });
        drawPlace.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                drawPlace.setOnMouseDragged(null);
                kwadrat.setX(Math.min(kwadrat.getX(), mouseEvent.getX()));
                kwadrat.setWidth(Math.abs(kwadrat.getX() - mouseEvent.getX()));
                kwadrat.setY(Math.min(kwadrat.getY(), mouseEvent.getY()));
                kwadrat.setHeight(Math.abs(kwadrat.getY() - mouseEvent.getY()));
                System.out.println("poczatek");
                drawPlace.setOnMouseReleased(null);
            }
        });
        drawPlace.getChildren().add(kwadrat);
    }
}

