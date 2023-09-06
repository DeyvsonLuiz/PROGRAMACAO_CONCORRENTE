/* ***************************************************************
* Autor............: Deyvson Luiz de Oliveira Nogueira
* Matricula........: 202010008
* Inicio...........: 17/08/2023
* Ultima alteracao.: 02/09/2023
* Nome.............: Controle da Possiblidade 1 de posicoes
* Funcao...........: Gerenciar o arquivo fxml
*************************************************************** */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CenarioController implements Initializable {
  private boolean estaAtivo = true;
  private String possibilidade = "P1";
  private SequentialTransition animacaoTremUm; // SequentialTransition do Trem 1. Tem como funcao juntar as timelines, nesse projeto foram criadas 3 timelines
  private SequentialTransition animacaoTremDois; // SequentialTransition do Trem 2. Tem como funcao juntar as timelines, nesse projeto foram criadas 3 timelines
  private FadeTransition fadeTransition1 = new FadeTransition();
  private FadeTransition fadeTransition2 = new FadeTransition();
  Stage stage = new Stage();
  String audioFile1 = getClass().getResource("/fxml/sounds/background.mp3").toExternalForm();
  Media media1 = new Media(audioFile1);
  MediaPlayer mediaPlayer1 = new MediaPlayer(media1);

  // FXMLs
  @FXML
  private ImageView muteEfeitosSonoros;
  @FXML 
  private ImageView muteMusic;
  @FXML
  private ImageView img_EfeitoSonoro;
  @FXML
  private ImageView img_Music;
  @FXML
  private ImageView img_glow1;
  @FXML
  private ImageView img_glow2;
  @FXML
  private ImageView img_glow3;
  @FXML
  private ImageView img_glow4;
  @FXML
  private ImageView line_1;
  @FXML
  private ImageView line_2;
  @FXML
  private ImageView line_3;
  @FXML
  private ImageView line_4;
  @FXML
  private Label lbl_pausado;
  @FXML
  private ImageView img_paused;
  @FXML
  private Slider sld_1;
  @FXML
  private Slider sld_2;
  @FXML
  private ImageView img_TremDois;
  @FXML
  private ImageView img_TremUm;
  @FXML
  private ImageView btn_pause;
  @FXML
  private ImageView btn_1;
  @FXML
  private ImageView btn_2;
  @FXML
  private ImageView btn_3;
  @FXML
  private ImageView btn_4;
  @FXML
  private ImageView btn_config;
  @FXML
  private ImageView btn_play;
  @FXML
  private ImageView btn_reset;
  @FXML
  private Label lbl_resetado;
  @FXML
  private ProgressBar loadPb;
  @FXML
  private ImageView loadBg;
  

  @FXML
  void clicouEmEfeitosSonoros(MouseEvent event) {
    muteEfeitosSonoros.setVisible(true);
    estaAtivo = false;
  }

  @FXML
  void clicouEmMusica(MouseEvent event) {
    mediaPlayer1.setVolume(0);
    muteMusic.setVisible(true);
  }

  @FXML
  void clicouEmMuteEfeitosSonoros(MouseEvent event) {
    estaAtivo = true;
    muteEfeitosSonoros.setVisible(false);
  }

  @FXML
  void clicouEmMuteMusica(MouseEvent event) {
    mediaPlayer1.setVolume(1);
    muteMusic.setVisible(false);
  }

  // BOTAO PLAY
  @FXML
  void clicouBotaoPlay(MouseEvent event){
    String audioFile = getClass().getResource("/fxml/sounds/clicarPlayPause.mp3").toExternalForm();
    Media media = new Media(audioFile);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    
    if(estaAtivo==true){
      mediaPlayer.setVolume(1);
    }else{
      mediaPlayer.setVolume(0);
    }
    
    mediaPlayer.play();
    
    btn_pause.setVisible(true);
    btn_play.setVisible(false);
    animacaoTremUm.play();
    animacaoTremDois.play();
    img_paused.setVisible(false);
    lbl_pausado.setVisible(false);
  }

 // BOTAO PAUSE
  @FXML
  void clicouBotaoPause(MouseEvent event) {
    String audioFile = getClass().getResource("/fxml/sounds/clicarPlayPause.mp3").toExternalForm();
    Media media = new Media(audioFile);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    
    if(estaAtivo==true){
      mediaPlayer.setVolume(1);
    }else{
      mediaPlayer.setVolume(0);
    }
    
    mediaPlayer.play();

    btn_play.setVisible(true);
    btn_pause.setVisible(false);
    animacaoTremUm.pause();
    animacaoTremDois.pause();
    img_paused.setVisible(true);
    lbl_pausado.setVisible(true);
  }


  // BOTAO RESET
  @FXML
  void clicouBotaoReset(MouseEvent event) {
    String audioFile = getClass().getResource("/fxml/sounds/clicarReset.mp3").toExternalForm();
    Media media = new Media(audioFile);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    
    if(estaAtivo==true){
      mediaPlayer.setVolume(1);
    }else{
      mediaPlayer.setVolume(0);
    }
    
    mediaPlayer.play();

    lbl_resetado.setVisible(true);
    fadeTransition1.setDuration(Duration.seconds(1.5));
    fadeTransition1.setNode(lbl_resetado);
    fadeTransition1.setFromValue(1);
    fadeTransition1.setToValue(0);
    fadeTransition1.play();

    animacaoTremUm.stop();
    animacaoTremDois.stop();
    animacaoTremUm.play();
    animacaoTremDois.play();
    animacaoTremUm.stop();
    animacaoTremDois.stop();
    sld_1.setValue(1);
    sld_2.setValue(1);
    img_paused.setVisible(false);
    lbl_pausado.setVisible(false);
    btn_pause.setVisible(false);
    btn_play.setVisible(true);

  }

  // BOTAO CONFIG
  @FXML
  void clicouBotaoConfig(MouseEvent event) {
    String audioFile = getClass().getResource("/fxml/sounds/clicarConfig.mp3").toExternalForm();
    Media media = new Media(audioFile);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    
    if(estaAtivo==true){
      mediaPlayer.setVolume(1);
    }else{
      mediaPlayer.setVolume(0);
    }
    
    mediaPlayer.play();

    if(possibilidade.equals("P1")){
      btn_1.setDisable(true);
      btn_2.setDisable(false);
      btn_3.setDisable(false);
      btn_4.setDisable(false);
      btn_2.setVisible(!btn_2.isVisible());
      btn_3.setVisible(!btn_3.isVisible());
      btn_4.setVisible(!btn_4.isVisible());
      line_2.setVisible(!line_2.isVisible());
      line_3.setVisible(!line_3.isVisible());
      line_4.setVisible(!line_4.isVisible());
    }
    else if(possibilidade.equals("P2")){
        btn_1.setDisable(false);
        btn_2.setDisable(true);
        btn_3.setDisable(false);
        btn_4.setDisable(false);
        btn_1.setVisible(!btn_1.isVisible());
        btn_3.setVisible(!btn_3.isVisible());
        btn_4.setVisible(!btn_4.isVisible());
        line_1.setVisible(!line_1.isVisible());
        line_3.setVisible(!line_3.isVisible());
        line_4.setVisible(!line_4.isVisible());
    }
    else if(possibilidade.equals("P3")){
        btn_1.setDisable(false);
        btn_2.setDisable(false);
        btn_3.setDisable(true);
        btn_4.setDisable(false);
        btn_2.setVisible(!btn_2.isVisible());
        btn_1.setVisible(!btn_1.isVisible());
        btn_4.setVisible(!btn_4.isVisible());
        line_2.setVisible(!line_2.isVisible());
        line_1.setVisible(!line_1.isVisible());
        line_4.setVisible(!line_4.isVisible());
    }
    else if(possibilidade.equals("P4")){
        btn_1.setDisable(false);
        btn_2.setDisable(false);
        btn_3.setDisable(false);
        btn_4.setDisable(true);
        btn_2.setVisible(!btn_2.isVisible());
        btn_3.setVisible(!btn_3.isVisible());
        btn_1.setVisible(!btn_1.isVisible());
        line_2.setVisible(!line_2.isVisible());
        line_3.setVisible(!line_3.isVisible());
        line_1.setVisible(!line_1.isVisible());
    }    
  }

  // BOTAO 1
  @FXML
  void clicouBotaoUm(MouseEvent event) throws IOException { 
    String audioFile = getClass().getResource("/fxml/sounds/clicarMudarPosicao.mp3").toExternalForm();
    Media media = new Media(audioFile);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    
    if(estaAtivo==true){
      mediaPlayer.setVolume(1);
    }else{
      mediaPlayer.setVolume(0);
    }
    
    mediaPlayer.play();

    possibilidade = "P1";
    animacaoTremUm.stop();
    animacaoTremDois.stop();
    sld_1.setValue(1);
    sld_2.setValue(1);
    btn_pause.setVisible(false);
    btn_play.setVisible(true); 

    btn_1.setDisable(true);
    btn_2.setDisable(false);
    btn_3.setDisable(false);
    btn_4.setDisable(false);
    
    img_glow1.setDisable(true);
    img_glow1.setVisible(true);
    img_glow2.setVisible(false);
    img_glow3.setVisible(false);
    img_glow4.setVisible(false);

    btn_1.setVisible(true);
    btn_2.setVisible(false);
    btn_3.setVisible(false);
    btn_4.setVisible(false);
    line_1.setVisible(true);
    line_2.setVisible(false);
    line_3.setVisible(false);
    line_4.setVisible(false);
    
    img_TremUm.setTranslateX(0);
    img_TremUm.setTranslateY(0);
    img_TremUm.setRotate(0);   

    img_TremDois.setTranslateX(0);
    img_TremDois.setTranslateY(0);
    img_TremDois.setRotate(0);
    
    animacaoTremUm = TremUm();
    animacaoTremDois = TremDois();

    
  }
  
  // BOTAO 2
  @FXML
  void clicouBotaoDois(MouseEvent event) throws IOException { 
    String audioFile = getClass().getResource("/fxml/sounds/clicarMudarPosicao.mp3").toExternalForm();
    Media media = new Media(audioFile);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    
    if(estaAtivo==true){
      mediaPlayer.setVolume(1);
    }else{
      mediaPlayer.setVolume(0);
    }
    
    mediaPlayer.play();

    possibilidade = "P2";
    animacaoTremUm.stop();
    animacaoTremDois.stop();
    sld_1.setValue(1);
    sld_2.setValue(1);
    btn_pause.setVisible(false);
    btn_play.setVisible(true); 

    btn_1.setDisable(false);
    btn_2.setDisable(true);
    btn_3.setDisable(false);
    btn_4.setDisable(false);
    
    img_glow1.setVisible(false);
    img_glow2.setVisible(true);
    img_glow3.setVisible(false);
    img_glow4.setVisible(false);

    btn_1.setVisible(false);
    btn_2.setVisible(true);
    btn_3.setVisible(false);
    btn_4.setVisible(false);
    line_1.setVisible(false);
    line_2.setVisible(true);
    line_3.setVisible(false);
    line_4.setVisible(false);
    
    img_TremUm.setTranslateX(935);
    img_TremUm.setTranslateY(0);
    img_TremUm.setRotate(180);   

    img_TremDois.setTranslateX(935);
    img_TremDois.setTranslateY(0);
    img_TremDois.setRotate(180);
    
    animacaoTremUm = TremUm2();
    animacaoTremDois = TremDois2();

    
  }
  
  // BOTAO 3
  @FXML
  void clicouBotaoTres(MouseEvent event) throws IOException { 
    String audioFile = getClass().getResource("/fxml/sounds/clicarMudarPosicao.mp3").toExternalForm();
    Media media = new Media(audioFile);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    
    if(estaAtivo==true){
      mediaPlayer.setVolume(1);
    }else{
      mediaPlayer.setVolume(0);
    }
    
    mediaPlayer.play();

    possibilidade = "P3";
    animacaoTremUm.stop();
    animacaoTremDois.stop();
    sld_1.setValue(1);
    sld_2.setValue(1);
    btn_pause.setVisible(false);
    btn_play.setVisible(true);  

    btn_1.setDisable(false);
    btn_2.setDisable(false);
    btn_3.setDisable(true);
    btn_4.setDisable(false);
    
    img_glow1.setVisible(false);
    img_glow2.setVisible(false);
    img_glow3.setVisible(true);
    img_glow4.setVisible(false);

    btn_1.setVisible(false);
    btn_2.setVisible(false);
    btn_3.setVisible(true);
    btn_4.setVisible(false);
    line_1.setVisible(false);
    line_2.setVisible(false);
    line_3.setVisible(true);
    line_4.setVisible(false);

    img_TremUm.setTranslateX(0);
    img_TremUm.setTranslateY(0);
    img_TremUm.setRotate(0);
    

    img_TremDois.setTranslateX(935);
    img_TremDois.setTranslateY(0);
    img_TremDois.setRotate(180);
    
    animacaoTremUm = TremUm3();
    animacaoTremDois = TremDois3();
  }

  // BOTAO 4
  @FXML
  void clicouBotaoQuatro(MouseEvent event) throws IOException {
    String audioFile = getClass().getResource("/fxml/sounds/clicarMudarPosicao.mp3").toExternalForm();
    Media media = new Media(audioFile);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    
    if(estaAtivo==true){
      mediaPlayer.setVolume(1);
    }else{
      mediaPlayer.setVolume(0);
    }
    
    mediaPlayer.play();

    possibilidade = "P4"; 
    animacaoTremUm.stop();
    animacaoTremDois.stop();
    sld_1.setValue(1);
    sld_2.setValue(1);
    btn_pause.setVisible(false);
    btn_play.setVisible(true);  
    
    btn_1.setDisable(false);
    btn_2.setDisable(false);
    btn_3.setDisable(false);
    btn_4.setDisable(true);

    img_glow1.setVisible(false);
    img_glow2.setVisible(false);
    img_glow3.setVisible(false);
    img_glow4.setVisible(true);

    btn_1.setVisible(false);
    btn_2.setVisible(false);
    btn_3.setVisible(false);
    btn_4.setVisible(true);
    line_1.setVisible(false);
    line_2.setVisible(false);
    line_3.setVisible(false);
    line_4.setVisible(true);

    img_TremUm.setTranslateX(935);
    img_TremUm.setTranslateY(0);
    img_TremUm.setRotate(180);
    

    img_TremDois.setTranslateX(0);
    img_TremDois.setTranslateY(0);
    img_TremDois.setRotate(0);
    
    animacaoTremUm = TremUm4();
    animacaoTremDois = TremDois4();
  }

  // INITIALIZE
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    fadeTransition1.setDuration(Duration.seconds(1));
    fadeTransition1.setNode(loadPb);
    fadeTransition1.setFromValue(1);
    fadeTransition1.setToValue(0);
    fadeTransition2.setDuration(Duration.seconds(1));
    fadeTransition2.setNode(loadBg);
    fadeTransition2.setFromValue(1);
    fadeTransition2.setToValue(0);
    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2.5));
    
    pauseTransition.play();
    pauseTransition.setOnFinished(event -> {
      fadeTransition1.play();
      fadeTransition2.play();
  });
    
    
    mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
    mediaPlayer1.play();

    animacaoTremUm = TremUm();
    animacaoTremDois = TremDois();

    SliderTremUm();
    SliderTremDois();
  }

  // SLIDER DO TREM 1
  public void SliderTremUm() {
    sld_1.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> arg0, Number agr1, Number arg2) {
        animacaoTremUm.setRate(sld_1.getValue());
      }
    });
  }

  // SLIDER DO TREM 2
  public void SliderTremDois() {
    sld_2.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> arg0, Number agr1, Number arg2) {
        animacaoTremDois.setRate(sld_2.getValue());
      }
    });
  }

  /* ------------------------------------------------------------------------------------------------------------------- *
   * ---------------------------------v v v v v v {-- AREA DAS POSSIBILIDADES --} v v v v v v----------------------------- *
   * ------------------------------------------------------------------------------------------------------------------- */
  
  // POSSIBILIDADE 1
  public SequentialTransition TremUm() {
    // PRIMEIRA TIMELINE ==> DO INICIO ATE O FIM DO PRIMEIRO TRILHO SIMPLES
    Timeline timeline1 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateYProperty(), 0)), // POSICAO INICIAL VERT
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateXProperty(), 0)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.translateXProperty(), 130)), // IDA TRILO DUPLO
        new KeyFrame(Duration.seconds(0.8), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.rotateProperty(), 45)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.translateXProperty(), 130)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 180)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateYProperty(), 48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.4), new KeyValue(img_TremUm.rotateProperty(), 45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.6), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 180)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremUm.translateXProperty(), 350)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.5), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremUm.rotateProperty(), -45)) // ROTACAO P/ CIMA
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline2 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateXProperty(), 350)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 400)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateYProperty(), 48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremUm.rotateProperty(), -45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 400)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 537)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.3), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.rotateProperty(), 45)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 537)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.translateXProperty(), 587)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.translateYProperty(), 48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.8), new KeyValue(img_TremUm.rotateProperty(), 45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.translateXProperty(), 587)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremUm.translateXProperty(), 748)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.9), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremUm.rotateProperty(), -45)) // ROTACAO P/ CIMA
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline3 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateXProperty(), 748)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 798)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateYProperty(), 48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremUm.rotateProperty(), -45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 798)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(2.6), new KeyValue(img_TremUm.translateXProperty(), 1050)) // IDA TRILHO DUPLO
    );

    SequentialTransition sequencia = new SequentialTransition(timeline1, timeline2, timeline3);

    sequencia.setRate(1.0);
    sequencia.setCycleCount(Timeline.INDEFINITE);

    return sequencia;
  }
  public SequentialTransition TremDois() {
    // PRIMEIRA TIMELINE ==> DO INICIO ATE O FIM DO PRIMEIRO TRILHO SIMPLES
    Timeline timeline1 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateYProperty(), 0)), // POSICAO INICIAL VERT
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateXProperty(), 0)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.translateXProperty(), 130)), // IDA TRILO DUPLO
        new KeyFrame(Duration.seconds(0.8), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.rotateProperty(), -45)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.translateXProperty(), 130)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 180)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateYProperty(), -48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.4), new KeyValue(img_TremDois.rotateProperty(), -45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.6), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 180)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremDois.translateXProperty(), 350)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.5), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremDois.rotateProperty(), 45)) // ROTACAO P/ BAIXO
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline2 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateXProperty(), 350)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 400)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateYProperty(), -48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremDois.rotateProperty(), 45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 400)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 537)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.3), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.rotateProperty(), -45)), // ROTACAO P/ CIMAA
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 537)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.translateXProperty(), 587)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.translateYProperty(), -48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.8), new KeyValue(img_TremDois.rotateProperty(), -45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.translateXProperty(), 587)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremDois.translateXProperty(), 748)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.9), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremDois.rotateProperty(), 45)) // ROTACAO P/ BAIXO
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline3 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateXProperty(), 748)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 798)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateYProperty(), -48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremDois.rotateProperty(), 45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 798)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(2.6), new KeyValue(img_TremDois.translateXProperty(), 1050)) // IDA TRILHO DUPLO
    );

    SequentialTransition sequencia = new SequentialTransition(timeline1, timeline2, timeline3);

    sequencia.setRate(1.0);
    sequencia.setCycleCount(Timeline.INDEFINITE);

    return sequencia;
  }

  // POSSIBILIDADE 2
  public SequentialTransition TremUm2() {
    // PRIMEIRA TIMELINE ==> DO INICIO ATE O FIM DO PRIMEIRO TRILHO SIMPLES
    Timeline timeline1 = new Timeline(
      new KeyFrame(Duration.seconds(0.8), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateYProperty(), 0)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateXProperty(), 935)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.translateXProperty(), 800)), // IDA TRILO DUPLO
        new KeyFrame(Duration.seconds(0.8), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.rotateProperty(), 135)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.translateXProperty(), 800)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 750)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateYProperty(), 48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.4), new KeyValue(img_TremUm.rotateProperty(), 135)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.6), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 750)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremUm.translateXProperty(), 570)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.5), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremUm.rotateProperty(), 225)) // ROTACAO P/ CIMA
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline2 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateXProperty(), 573)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 518)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateYProperty(), 48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremUm.rotateProperty(), 225)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 518)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 393)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.3), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.rotateProperty(), 135)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 393)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.translateXProperty(), 343)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.translateYProperty(), 48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.8), new KeyValue(img_TremUm.rotateProperty(), 135)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.translateXProperty(), 343)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremUm.translateXProperty(), 185)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.9), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremUm.rotateProperty(), 225)) // ROTACAO P/ CIMA
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline3 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateXProperty(), 185)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 120)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateYProperty(), 48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremUm.rotateProperty(), 225)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 120)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(2.6), new KeyValue(img_TremUm.translateXProperty(), -100)) // IDA TRILHO DUPLO
    );

    SequentialTransition sequencia = new SequentialTransition(timeline1, timeline2, timeline3);

    sequencia.setRate(1.0);
    sequencia.setCycleCount(Timeline.INDEFINITE);

    return sequencia;
  }
  public SequentialTransition TremDois2() {
    // PRIMEIRA TIMELINE ==> DO INICIO ATE O FIM DO PRIMEIRO TRILHO SIMPLES
    Timeline timeline1 = new Timeline(
      new KeyFrame(Duration.seconds(0.8), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateYProperty(), 0)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateXProperty(), 935)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.translateXProperty(), 800)), // IDA TRILO DUPLO
        new KeyFrame(Duration.seconds(0.8), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.rotateProperty(), 225)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.translateXProperty(), 800)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 750)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateYProperty(), -48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.4), new KeyValue(img_TremDois.rotateProperty(), 225)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.6), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 750)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremDois.translateXProperty(), 570)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.5), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremDois.rotateProperty(), 135)) // ROTACAO P/ CIMA
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline2 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateXProperty(), 573)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 518)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateYProperty(), -48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremDois.rotateProperty(), 135)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 518)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 393)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.3), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.rotateProperty(), 225)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 393)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.translateXProperty(), 343)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.translateYProperty(), -48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.8), new KeyValue(img_TremDois.rotateProperty(), 225)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.translateXProperty(), 343)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremDois.translateXProperty(), 185)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.9), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremDois.rotateProperty(), 135)) // ROTACAO P/ CIMA
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline3 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateXProperty(), 185)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 120)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateYProperty(), -48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremDois.rotateProperty(), 135)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 120)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(2.6), new KeyValue(img_TremDois.translateXProperty(), -100)) // IDA TRILHO DUPLO
    );

    SequentialTransition sequencia = new SequentialTransition(timeline1, timeline2, timeline3);

    sequencia.setRate(1.0);
    sequencia.setCycleCount(Timeline.INDEFINITE);

    return sequencia;
  }
  
  // POSSIBILIDADE 3
  public SequentialTransition TremUm3() {
    // PRIMEIRA TIMELINE ==> DO INICIO ATE O FIM DO PRIMEIRO TRILHO SIMPLES
    Timeline timeline1 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateYProperty(), 0)), // POSICAO INICIAL VERT
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateXProperty(), 0)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.translateXProperty(), 130)), // IDA TRILO DUPLO
        new KeyFrame(Duration.seconds(0.8), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.rotateProperty(), 45)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.translateXProperty(), 130)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 180)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateYProperty(), 48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.4), new KeyValue(img_TremUm.rotateProperty(), 45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.6), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 180)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremUm.translateXProperty(), 350)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.5), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremUm.rotateProperty(), -45)) // ROTACAO P/ CIMA
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline2 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateXProperty(), 350)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 400)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateYProperty(), 48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremUm.rotateProperty(), -45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 400)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 537)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.3), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.rotateProperty(), 45)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 537)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.translateXProperty(), 587)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.translateYProperty(), 48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.8), new KeyValue(img_TremUm.rotateProperty(), 45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.translateXProperty(), 587)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremUm.translateXProperty(), 748)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.9), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremUm.rotateProperty(), -45)) // ROTACAO P/ CIMA
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline3 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateXProperty(), 748)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 798)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateYProperty(), 48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremUm.rotateProperty(), -45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 798)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(2.6), new KeyValue(img_TremUm.translateXProperty(), 1050)) // IDA TRILHO DUPLO
    );

    SequentialTransition sequencia = new SequentialTransition(timeline1, timeline2, timeline3);

    sequencia.setRate(1.0);
    sequencia.setCycleCount(Timeline.INDEFINITE);

    return sequencia;
  }
  public SequentialTransition TremDois3() {
    // PRIMEIRA TIMELINE ==> DO INICIO ATE O FIM DO PRIMEIRO TRILHO SIMPLES
    Timeline timeline1 = new Timeline(
      new KeyFrame(Duration.seconds(0.8), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateYProperty(), 0)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateXProperty(), 935)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.translateXProperty(), 800)), // IDA TRILO DUPLO
        new KeyFrame(Duration.seconds(0.8), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.rotateProperty(), 225)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.translateXProperty(), 800)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 750)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateYProperty(), -48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.4), new KeyValue(img_TremDois.rotateProperty(), 225)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.6), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 750)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremDois.translateXProperty(), 570)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.5), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremDois.rotateProperty(), 135)) // ROTACAO P/ CIMA
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline2 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateXProperty(), 573)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 518)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateYProperty(), -48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremDois.rotateProperty(), 135)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 518)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 393)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.3), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.rotateProperty(), 225)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 393)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.translateXProperty(), 343)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.translateYProperty(), -48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.8), new KeyValue(img_TremDois.rotateProperty(), 225)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.translateXProperty(), 343)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremDois.translateXProperty(), 185)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.9), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremDois.rotateProperty(), 135)) // ROTACAO P/ CIMA
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline3 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateXProperty(), 185)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 120)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateYProperty(), -48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremDois.rotateProperty(), 135)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 120)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(2.6), new KeyValue(img_TremDois.translateXProperty(), -100)) // IDA TRILHO DUPLO
    );

    SequentialTransition sequencia = new SequentialTransition(timeline1, timeline2, timeline3);

    sequencia.setRate(1.0);
    sequencia.setCycleCount(Timeline.INDEFINITE);

    return sequencia;
  }

  // POSSIBILIDADE 4
  public SequentialTransition TremUm4() {
    // PRIMEIRA TIMELINE ==> DO INICIO ATE O FIM DO PRIMEIRO TRILHO SIMPLES
    Timeline timeline1 = new Timeline(
      new KeyFrame(Duration.seconds(0.8), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateYProperty(), 0)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateXProperty(), 935)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.translateXProperty(), 800)), // IDA TRILO DUPLO
        new KeyFrame(Duration.seconds(0.8), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.rotateProperty(), 135)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.translateXProperty(), 800)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 750)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremUm.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateYProperty(), 48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.4), new KeyValue(img_TremUm.rotateProperty(), 135)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.6), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 750)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremUm.translateXProperty(), 570)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.5), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremUm.rotateProperty(), 225)) // ROTACAO P/ CIMA
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline2 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateXProperty(), 573)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 518)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateYProperty(), 48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremUm.rotateProperty(), 225)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 518)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 393)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.3), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.rotateProperty(), 135)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateXProperty(), 393)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.translateXProperty(), 343)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremUm.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.translateYProperty(), 48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.8), new KeyValue(img_TremUm.rotateProperty(), 135)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremUm.translateXProperty(), 343)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremUm.translateXProperty(), 185)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.9), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremUm.rotateProperty(), 225)) // ROTACAO P/ CIMA
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline3 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateXProperty(), 185)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 120)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremUm.translateYProperty(), 48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremUm.rotateProperty(), 225)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.rotateProperty(), 180)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremUm.translateXProperty(), 120)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(2.6), new KeyValue(img_TremUm.translateXProperty(), -100)) // IDA TRILHO DUPLO
    );

    SequentialTransition sequencia = new SequentialTransition(timeline1, timeline2, timeline3);

    sequencia.setRate(1.0);
    sequencia.setCycleCount(Timeline.INDEFINITE);

    return sequencia;
  }
  public SequentialTransition TremDois4() {
    // PRIMEIRA TIMELINE ==> DO INICIO ATE O FIM DO PRIMEIRO TRILHO SIMPLES
    Timeline timeline1 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateYProperty(), 0)), // POSICAO INICIAL VERT
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateXProperty(), 0)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.translateXProperty(), 130)), // IDA TRILO DUPLO
        new KeyFrame(Duration.seconds(0.8), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.rotateProperty(), -45)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.translateXProperty(), 130)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 180)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.0), new KeyValue(img_TremDois.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateYProperty(), -48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.4), new KeyValue(img_TremDois.rotateProperty(), -45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.6), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 180)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremDois.translateXProperty(), 350)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.5), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(2.7), new KeyValue(img_TremDois.rotateProperty(), 45)) // ROTACAO P/ BAIXO
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline2 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateXProperty(), 350)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 400)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateYProperty(), -48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremDois.rotateProperty(), 45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 400)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 537)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(1.3), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO P/ CIMA
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.rotateProperty(), -45)), // ROTACAO P/ CIMAA
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateXProperty(), 537)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.translateXProperty(), 587)), // DESCIDA HORIZ
        new KeyFrame(Duration.seconds(1.5), new KeyValue(img_TremDois.translateYProperty(), 0)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.translateYProperty(), -48)), // DESCIDA VERT
        new KeyFrame(Duration.seconds(1.8), new KeyValue(img_TremDois.rotateProperty(), -45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(2.0), new KeyValue(img_TremDois.translateXProperty(), 587)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremDois.translateXProperty(), 748)), // IDA TRILHO SIMPLES
        new KeyFrame(Duration.seconds(2.9), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO P/ BAIXO
        new KeyFrame(Duration.seconds(3.1), new KeyValue(img_TremDois.rotateProperty(), 45)) // ROTACAO P/ BAIXO
    );

    // SEGUNDA TIMELINE ==> DO FIM DO PRIMEIRO TRILHO SIMPLES ATE O FIM DO SEGUNDO
    // TRILHO SIMPLES
    Timeline timeline3 = new Timeline(
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateXProperty(), 748)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 798)), // SUBIDA HORIZ
        new KeyFrame(Duration.seconds(0.0), new KeyValue(img_TremDois.translateYProperty(), -48)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateYProperty(), 0)), // SUBIDA VERT
        new KeyFrame(Duration.seconds(0.3), new KeyValue(img_TremDois.rotateProperty(), 45)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.rotateProperty(), 0)), // ROTACAO RESET
        new KeyFrame(Duration.seconds(0.5), new KeyValue(img_TremDois.translateXProperty(), 798)), // IDA TRILHO DUPLO
        new KeyFrame(Duration.seconds(2.6), new KeyValue(img_TremDois.translateXProperty(), 1050)) // IDA TRILHO DUPLO
    );

    SequentialTransition sequencia = new SequentialTransition(timeline1, timeline2, timeline3);

    sequencia.setRate(1.0);
    sequencia.setCycleCount(Timeline.INDEFINITE);

    return sequencia;
  }
}