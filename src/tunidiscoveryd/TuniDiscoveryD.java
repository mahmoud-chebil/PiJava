/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunidiscoveryd;
import Entities.Activite;
import Entities.Evenement;
import Services.ActiviteService;
import Services.EvenementService;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 *
 * @author MSI
 */
public class TuniDiscoveryD extends Application{
    Stage stage;
    Parent parent;
    
      @Override
    public void start(Stage primarystage) {

    
    this.stage= primarystage;
        try {
            //parent = FXMLLoader.load(getClass().getResource("/Gui/ActiviteFXML.fxml"));
            //parent = FXMLLoader.load(getClass().getResource("/Gui/EvenementFXML.fxml"));
           parent = FXMLLoader.load(getClass().getResource("/Gui/ListeEvenement.fxml"));
          // parent = FXMLLoader.load(getClass().getResource("/Gui/PieChart.fxml"));
            Scene scene= new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Gestion Activite");
            stage.initStyle(StageStyle.UTILITY); //remove Max & Min option
            stage.show();
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
        

    }
    
}




        
               // EvenementService ev = new EvenementService(); 
                //System.out.println(ev.rechercheEvenement("wissal").toString());
                
                
                
                
                
                
                //System.out.println(a.afficherEvenement().toString());
                //a.supprimerEvenement(61);
                //Evenement e= new Evenement(1000, "desc", "titre",12,"12-12-2022","13-12-2022",20121212,"email@gmail.com");
                //a.ajouterEvenement(e);
                // e.setId(62);
                //e.setPrix_evenement(100);
                //e.setDecription_evenement("wissal");
                //e.setTitre_evenement("wissal");
                //e.setNbre_places(1);
                //e.setDate_debut("12-12-2020");
                //e.setDate_fin("13-12-2022");
                //e.setTel(20202020);
                //e.setEmail("email");
                //ev.modifierEvenement(e);
                
            
                
                
               // ActiviteService a = new ActiviteService();
               // System.out.println(a.afficherActivite().toString());
                //Activite ac = new Activite("activvv", "type", 3);
                //a.ajouterActivite(ac);
                //a.SupprimerActivite(19);
                //ac.setId(20);
                //ac.setNom_activite("nomac");
                //ac.setPrix_activite(0);
                //ac.setType_activite("type");
                //a.modifierActivite(ac);
