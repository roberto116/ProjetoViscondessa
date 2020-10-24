/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.projeto.telas;

import br.senac.projeto.registros.ItemProduto;
import java.net.URL;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private TableColumn<ItemProduto, String> colunaQnt;
    @FXML
    private TextField tfGenero;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField tfPesquisa;
    @FXML
    private TextField tfQuantidade;

    //variaveis globais
    List <ItemProduto>listaProduto = new ArrayList();
    boolean editMode = false;
    ItemProduto itemProdutoEdicao = null;
    int totalItens = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colunaJogos.setCellValueFactory(new PropertyValueFactory("jogo"));
        colunaGenero.setCellValueFactory(new PropertyValueFactory("genero"));
        colunaQnt.setCellValueFactory(new PropertyValueFactory("quantidade"));
    }    

    @FXML
    private void salvar(ActionEvent event) {
        if(!editMode){
            ItemProduto item = new ItemProduto();

            item.jogo = tfJogo.getText();
            item.genero = tfGenero.getText();
            item.quantidade = tfQuantidade.getText();
            
            if(item.jogo.equals("") || item.genero.equals("")|| item.quantidade.equals("")){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Erro");
                alert.setHeaderText("Campos Vazio");
                alert.setContentText("Click em OK para tentar novamente");
                alert.showAndWait();
                return;
            }
            
            item.id = totalItens;
            
            totalItens++;

            listaProduto.add(item);
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Inserir");
            alert.setHeaderText("Inserido com Sucesso");
            alert.setContentText("Click em OK para continuar");
            alert.showAndWait();
        }
        else{
            itemProdutoEdicao.jogo = tfJogo.getText();
            itemProdutoEdicao.genero = tfGenero.getText();
            itemProdutoEdicao.quantidade = tfQuantidade.getText();
            
            for(int i = 0; i < listaProduto.size();i++){
                ItemProduto itemLista = listaProduto.get(i);
                    if(itemLista.id == itemProdutoEdicao.id){
                       listaProduto.set(i, itemProdutoEdicao);
                       break;
                    }
            }
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Salvar");
            alert.setHeaderText("Salvo com Sucesso");
            alert.setContentText("Click em OK para continuar");
            alert.showAndWait();
            
        }
        
        limparCampos(event);
        pesquisar(event);
    }

    @FXML
    private void editar(ActionEvent event) {
        ItemProduto itemSelecionado = tabelaProduto.getSelectionModel().getSelectedItem();
        
        if(itemSelecionado != null){
            editMode = true;
            
            itemProdutoEdicao = itemSelecionado;
            
            tfJogo.setText(itemProdutoEdicao.jogo);
            tfGenero.setText(itemProdutoEdicao.genero);
            tfQuantidade.setText(itemProdutoEdicao.quantidade);
            
            btnSalvar.setText("Salvar");
        }
    }

    @FXML
    private void pesquisar(ActionEvent event) {
         
        if(tfPesquisa.getText().equals("")){
            tabelaProduto.setItems(FXCollections.observableArrayList(listaProduto));
            tabelaProduto.refresh();
        }
        else{
            List<ItemProduto> listaResultado = new ArrayList();
            
            for(int i = 0; i < listaProduto.size();i++){
                ItemProduto itemLista = listaProduto.get(i);
                if(itemLista.jogo.contains(tfPesquisa.getText())){
                    listaResultado.add(itemLista);
                }
            }
            
            tabelaProduto.setItems(FXCollections.observableArrayList(listaResultado));
            tabelaProduto.refresh();
        }
    }

    @FXML
    private void excluir(ActionEvent event) {
        ItemProduto itemSelecionado = tabelaProduto.getSelectionModel().getSelectedItem();
        
        if(itemSelecionado != null){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Remoção");
            alert.setContentText("Remover o item " + itemSelecionado.jogo);
            
            Optional<ButtonType> resultado = alert.showAndWait();
            
            if(resultado.get() == ButtonType.OK){
                for(int i = 0; i < listaProduto.size();i++){
                ItemProduto itemLista = listaProduto.get(i);
                    if(itemLista.id == itemSelecionado.id){
                       listaProduto.remove(i);
                       break;
                    }
                }
            }
            pesquisar(event);
        }
        
    }

    @FXML
    private void limparCampos(ActionEvent event) {
        tfJogo.clear();
        tfGenero.clear();
        tfQuantidade.clear();
        
        btnSalvar.setText("Inserir");
        editMode = false;
    }

    @FXML
    private void sair(ActionEvent event) {
        System.exit(0);
    }

}
