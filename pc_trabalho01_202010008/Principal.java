/* ***************************************************************
* Autor............: Deyvson Luiz de Oliveira Nogueira
* Matricula........: 202010008
* Inicio...........: 17/08/2023
* Ultima alteracao.: 02/09/2023
* Nome.............: Simulacao de Trens
* Funcao...........: Simular o percurso de dois trens com 4 possiblidades de posicao inicial
*************************************************************** */

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import controller.CenarioController;

public class Principal extends Application {
  private static Stage stage;
  private static Scene scene1;

  @Override
  public void start(Stage primaryStage) throws IOException {
    stage = primaryStage;
    Parent root1 = FXMLLoader.load(getClass().getResource("/fxml/Cenario.fxml")); // Abrir tela inicial
    scene1 = new Scene(root1);

    Image icon = new Image("/fxml/images/icone.png");  // Carregando o ícone da janela

    stage.setTitle("Thomas e Seus Amigos"); // Titulo da janela
    stage.setResizable(false); // Nao permitir maximizar a tela
    stage.getIcons().add(icon);
    stage.setScene(scene1); // Setar a cena
    stage.show(); // Mostrar/Iniciar a cena
  }

  public static void main(String[] args) {
    launch(args);
  }

}
