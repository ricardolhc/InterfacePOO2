package com.baseclasse;

import java.util.Calendar;

import com.exceptions.notafiscal.DataNotSupportedException;

/**
 * A classe NotaFiscal modela uma nota fiscal do sistema.
 * @author Ricardo, Tales, Mateus, Mauricio
 * @since jan 2023
 * @version 1.0
 */
public class NotaFiscal {

    /**
     * O atributo codigo, do tipo int, identifica o código da nota fiscal.
     */
    private int codigo;

    /**
     * O atributo data, do tipo Calendar, identifica a data da nota fiscal.
     */
    private Calendar data;

    private double total;

    /**
     * O atributo itens, do tipo ListaItem, identifica a lista de itens da nota fiscal.
     */
    private ListaItem itens;

    /**
     * O atributo codigoUnico, do tipo int, identifica o código único da nota fiscal.
     */
    private static int codigoUnico = 10000;

    public NotaFiscal(Calendar data, ListaItem itens) {
        if(data == null) {
            throw new IllegalArgumentException("Data não pode ser nula.");
        }

        if(itens == null) {
            throw new IllegalArgumentException("Lista de itens não pode ser nula.");
        }
        
        this.data = data;
        this.itens = itens;
        this.codigo = codigoUnico;
        codigoUnico++;

        try {
            this.total = itens.getTotal();
        } catch (Exception e) { 
            this.total = 0;
        }
    }

    /**
     * O método add adiciona um item na lista de itens da nota fiscal.
     * @param item Item que será adicionado na lista de itens da nota fiscal.
     * @throws Exception
     */
    public void add(Item item) throws Exception { 

        if(item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }

        try {  
            itens.addItem(item); 
        } catch (Exception e) {
            throw e;
        }
    
    }

    /**
     * O método remove remove um item da lista de itens da nota fiscal.
     * @param item Item que será removido da lista de itens da nota fiscal.
     * @throws Exception
     */
    public void remove(Item item) throws Exception { 

        if(item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }

        try {
            itens.removeItem(item); 
        } catch (Exception e) {
            throw e;
        }
    }

    /** 
     * @return int que identifica o codigo do item da nota fiscal.
     */
    public Item getItem(int codigo) throws Exception { 

        if(codigo < 0) {
            throw new IllegalArgumentException("Código não pode ser negativo.");
        }
        
        try {
            return itens.getItem(codigo); 
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * @return double que identifica o total de itens na nota fiscal.
     */
    public double getTotal() throws Exception { 
        try {
            total = itens.getTotal(); 
            return total;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @return int que identifica o codigo da nota fiscal.
     */
    public int getCodigo() { return codigo; }

    /**
     * @return Calendar que identifica a data da nota fiscal.
     */
    public Calendar getData() { return data; }

    /**
     * @return ListaItem que identifica a lista de itens da nota fiscal.
     */
    public ListaItem getItens() { return itens; }

    /**
    @param data Calendar que identifica a data da nota fiscal.
    */
    public void setData(Calendar data) { 

        if(data == null) {
            throw new IllegalArgumentException("Data não pode ser nula.");
        }

        this.data = data; 
    }

    /**
    @param itens ListaItem que identifica a lista de itens da nota fiscal.
    */
    public void setItens(ListaItem itens) { 

        if(itens == null) {
            throw new IllegalArgumentException("Lista de itens não pode ser nula.");
        }

        this.itens = itens; 
    }

    public String getDataFormatada() {
        if(data == null) {
            throw new DataNotSupportedException("Erro ao formatar data.");
        }

        String dia = String.valueOf(data.get(Calendar.DAY_OF_MONTH));
        String mes = String.valueOf(data.get(Calendar.MONTH) + 1);
        String ano = String.valueOf(data.get(Calendar.YEAR));

        String data = "";

        if(dia.length() == 1) {
            data += "0" + dia + "/";
        } else {
            data += dia + "/";
        }

        if(mes.length() == 1) {
            data += "0" + mes + "/";
        } else {
            data += mes + "/";
        }

        data += ano;

        return data;
    }

}