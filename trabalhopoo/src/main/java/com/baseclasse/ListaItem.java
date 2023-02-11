package com.baseclasse;

import java.util.ArrayList;

import com.exceptions.item.ItemNotFoundException;
import com.exceptions.item.ItemNotSupportedException;
import com.exceptions.lista.ListaVaziaException;

/**
* Classe responsável por controlar a lista de itens<br>
* @author Ricardo, Tales, Mateus, Mauricio
* @since jan 2023
*/
public class ListaItem {
    
    /**
    * Lista de itens 
    */
    private ArrayList<Item> listaItens;

    /**
    * Construtor default da classe ListaItem<br>
    * Inicializa a lista de itens
    */
    public ListaItem() {
        this.listaItens = new ArrayList<Item>();
    }

    /**
    * Adiciona um item na lista de itens
    * @param item Item a ser adicionado
    */
    public void addItem(Item item) {
        if(item == null) {
            throw new ItemNotSupportedException("Não foi possível adicionar o item.");  
        }
        listaItens.add(item);      
    }

    /**
    * Remove um item da lista de itens
    * @param item Item a ser removido
    */
    public void removeItem(Item item) {
        if(item == null) {
            throw new ItemNotSupportedException("Não foi possível remover o item.");
        }
        listaItens.remove(item);
    }

    /**
    * Procura uma item na lista de itens
    * @param codigo Código do item a ser removido
    */
    public Item getItem(int codigo) {
        for (Item item : listaItens) {
            if (item.getCodigo() == codigo) {
                return item;
            }
        }
        throw new ItemNotFoundException("O item não foi encontrado.");
    }

    /**
    * Retorna o total da lista de itens
    * @return Total da lista de itens
    */
    public double getTotal() {
        double total = 0;

        if(listaItens == null || listaItens.isEmpty()) {
            throw new ListaVaziaException("Não foi possível obter o total, pois a lista de itens está vazia.");
        }

        for (Item item : listaItens) {
            total += item.getPrecoTotal();
        }
        return total;
    }

    /**
    * Retorna a lista de itens
    * @return Lista de itens
    */
    public ArrayList<Item> getArray() {
        return listaItens;
    }

    /**
    * Retorna o tamanho da lista de itens
    * @return Tamanho da lista de itens
    */
    public int size() {
        return listaItens.size();
    }

    public boolean isEmpty() {
        return listaItens.isEmpty();
    }

}