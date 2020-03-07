package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;

import java.sql.SQLException;


public class FriendListController {

    String[] friends;
    int userId;

    DbHandler dbh;

    public FriendListController(){
        dbh = new DbHandler();
        dbh.initConection();
    }


    @FXML
    ListView friendList ;
    @FXML
    Button utmanaButton;
    @FXML
    ListView requests;
    @FXML
    Button tackaNej;
    @FXML
    Button tackaJa;


    public void setFriends(String[] newFriends){
        friends = newFriends;
        ObservableList<String> list = FXCollections.observableArrayList(friends);
        friendList.setItems(list);
        friendList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void challengeFriend(ActionEvent event) throws SQLException {

        try {


        String opponent = (String) friendList.getSelectionModel().getSelectedItem();
        int opponentId = dbh.findUserId(opponent);
        utmanaButton.setText(String.valueOf(opponentId));
        dbh.addRequests(userId,opponentId);

     } catch (Exception e){
        e.printStackTrace();
        }
    }
    public void setUserId(int uId){
        userId = uId;
        String[] requestedPlayer = dbh.findRequestsForPlayer(uId);
        if (requestedPlayer.length>0){
            setRequests(requestedPlayer);
        }

    }
    public void setRequests(String[] requestsFromPlayer){

        ObservableList<String> list = FXCollections.observableArrayList(requestsFromPlayer);
        requests.setItems(list);
        requests.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void acceptChallenge(ActionEvent event){

        String opponent = (String) friendList.getSelectionModel().getSelectedItem();
        if (opponent!= null){

        }
    }
}