/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.projeto.telas;

import br.senac.projeto.registros.ItemProduto;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Beto
 */
public class TelaPrincipalController implements Initializable {

    @FXML
    private TextField tfJogo;
    @FXML
    private TableView<ItemProduto> tabelaProduto;
    @FXML
    private TableColumn<ItemProduto, String> colunaJogos;
    @FXML
    private TableColumn<ItemProduto, String> colunaGenero;
    @FXML
    private TextField tfGenero;

    //variaveis globais
    List <ItemProduto>listaProduto = new ArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colunaJogos.setCellValueFactory(new PropertyValueFactory("jogo"));
         colunaGenero.setCellValueFactory(new PropertyValueFactory("genero"));
        
    }    

    @FXML
    private void salvar(ActionEvent event) {
        ItemProduto item = new ItemProduto();
        
        item.jogo = tfJogo.getText();
        item.jogo = tfGenero.getText();
        
        listaProduto.add(item);
    }

    @FXML
    private void editar(ActionEvent event) {
    }

    @FXML
    private void pesquisar(ActionEvent event) {
        tabelaProduto.setItems(FXCollections.observableArrayList(listaProduto));
    }

    @FXML
    private void excluir(ActionEvent event) {
    }

    private static class ItemAgenda extends ItemProduto {

        public ItemAgenda() {
        }
    }
    
}
