package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Controller{
    private final String[] ofsAddrs = new String[3];

    @FXML
    private Label courseName1;

    @FXML
    private Label courseName2;

    @FXML
    private Label courseName3;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXButton of1;

    @FXML
    private JFXButton of2;

    @FXML
    private JFXButton of3;

    @FXML
    private JFXButton sf1;

    @FXML
    private JFXButton sf2;

    @FXML
    private JFXButton sf3;

    @FXML
    private JFXTextField box1field1;

    @FXML
    private JFXTextField box1field2;

    @FXML
    private JFXTextField box1field3;

    @FXML
    private JFXTextField box1field4;

    @FXML
    private JFXTextField box1field5;

    @FXML
    private JFXTextField box2field1;

    @FXML
    private JFXTextField box2field2;

    @FXML
    private JFXTextField box2field3;

    @FXML
    private JFXTextField box2field4;

    @FXML
    private JFXTextField box2field5;


    @FXML
    private JFXTextField box3field1;

    @FXML
    private JFXTextField box3field2;

    @FXML
    private JFXTextField box3field3;

    @FXML
    private JFXTextField box3field4;

    @FXML
    private JFXTextField box3field5;


    @FXML
    private JFXButton btnSaveAll;

    @FXML
    void getFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a text file");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if(file != null) {
            if(event.getSource() == of1) {
                ofsAddrs[0] = file.getAbsolutePath();
                loadFile(file, 1);
            } else if(event.getSource() == of2) {
                ofsAddrs[1] = file.getAbsolutePath();
                loadFile(file, 2);
            } else if(event.getSource() == of3) {
                ofsAddrs[2] = file.getAbsolutePath();
                loadFile(file, 3);
            }
        }
    }

    @FXML
    void saveFile(ActionEvent event) {
        if(event.getSource() == sf1) {
            System.out.println("SF1 got saved");
            saveData(1);
        } else if(event.getSource() == sf2) {
            System.out.println("SF2 got saved");
            saveData(2);
        } else if(event.getSource() == sf3) {
            System.out.println("SF3 got saved");
            saveData(3);
        }
    }

    private void saveData(int btnSrc) {
        try {
            PrintWriter printWriter = new PrintWriter(ofsAddrs[btnSrc - 1]);
            JFXTextField[] boxFields;
            if(btnSrc == 1) {
                boxFields = new JFXTextField[]{box1field1, box1field2, box1field3, box1field4, box1field5};
            } else if(btnSrc == 2) {
                boxFields = new JFXTextField[]{box2field1, box2field2, box2field3, box2field4, box2field5};
            } else {
                boxFields = new JFXTextField[]{box3field1, box3field2, box3field3, box3field4, box3field5};
            }
            for (JFXTextField field:
                 boxFields) {
                printWriter.println(field.getText());
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveAll(ActionEvent event) {
        System.out.println("All Files Saved");
        saveData(1);
        saveData(2);
        saveData(3);
    }

     private void loadFile(File file, int btnSrc) {
         JFXTextField[] boxFields;
        if(btnSrc == 1) {
            courseName1.setText(file.getName());
            boxFields = new JFXTextField[]{box1field1, box1field2, box1field3, box1field4, box1field5};
        } else if( btnSrc == 2) {
            courseName2.setText(file.getName());
            boxFields = new JFXTextField[]{box2field1, box2field2, box2field3, box2field4, box2field5};
        } else {
            courseName3.setText(file.getName());
            boxFields = new JFXTextField[]{box3field1, box3field2, box3field3, box3field4, box3field5};
         }
        try {
            Scanner scanner = new Scanner(file);
            String line = "";
            while (scanner.hasNextLine()) {
                line += scanner.nextLine();
                line += ",";
            }
            String[] marksArr = line.split(",");

            System.out.println(Arrays.toString(marksArr));
            for (int i = 0; i < 5; i++) {
                boxFields[i].setText(marksArr[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
