package com.listas;

import java.util.ArrayList;
import java.util.Calendar;

import com.baseclasse.Item;
import com.baseclasse.NotaFiscal;

import com.exceptions.item.ItemNotSupportedException;

import com.exceptions.notafiscal.AddNotaFiscalException;
import com.exceptions.notafiscal.CodigoNotaFiscalNotSupportedException;
import com.exceptions.notafiscal.DataNotSupportedException;
import com.exceptions.notafiscal.NotaFiscalNotFoundException;

/**
* Classe responsável por controlar a lista de notas fiscais<br>
* @author Ricardo, Tales, Mateus, Mauricio
* @since jan 2023
*/
public class ListaNotaFiscal implements INotasFiscais {

    /**
     * Lista de notas fiscais
     */
    private ArrayList<NotaFiscal> listaNotaFiscal;

    /**
    * Construtor default da classe ListaNotaFiscal<br>
    * Inicializa a lista de itens
    */
    public ListaNotaFiscal() {
        listaNotaFiscal = new ArrayList<NotaFiscal>();
    }

    public ListaNotaFiscal(ArrayList<NotaFiscal> listaNotaFiscal) {
        this.listaNotaFiscal = listaNotaFiscal;
    }

    /**
    * Método responsável por adicionar uma nota fiscal na lista de notas fiscais
    * @param nf Nota fiscal a ser adicionada
    */
    @Override
    public void addNotaFiscal(NotaFiscal nf) throws Exception {
        if(nf == null) {
            throw new AddNotaFiscalException("Não foi possível adicionar a nota fiscal.");
        }
        listaNotaFiscal.add(nf);
    }

    /**
    * Método responsável por remover uma nota fiscal da lista de notas fiscais
    * @param codigo Código da nota fiscal a ser removida
     * @throws Exception
    */
    @Override
    public void removeNotaFiscal(int codigo) throws Exception {

        if(codigo < 0) {
            throw new CodigoNotaFiscalNotSupportedException("Para remover uma nota fiscal o código não pode ser menor que zero.");
        }
        
        try {
            NotaFiscal notaFiscal = getNotaFiscal(codigo);
            listaNotaFiscal.remove(notaFiscal);
            return;
        } catch (NotaFiscalNotFoundException e) {
            throw new NotaFiscalNotFoundException("Não foi possível remover a nota fiscal com o código: " + codigo + ".");
        }
    }

    /**
    * Método responsável por retornar uma nota fiscal da lista de notas fiscais
    * @param codigo Código da nota fiscal a ser retornada
    * @return Nota fiscal com o código informado
    */
    @Override
    public NotaFiscal getNotaFiscal(int codigo) throws Exception {

        if(codigo < 0) {
            throw new CodigoNotaFiscalNotSupportedException("Para obter uma nota fiscal o código não pode ser menor que zero.");
        }

        for (NotaFiscal notaFiscal : listaNotaFiscal){
            if (notaFiscal.getCodigo() == codigo){
                return notaFiscal;
            }
        }
        throw new NotaFiscalNotFoundException("Não foi possível obter a nota fiscal com o código: " + codigo + ".");
    }

    /**
    * Método responsável por retornar o total de uma nota fiscal
    * @param codigo Código da nota fiscal a ser retornada
    * @return Total da nota fiscal com o código informado
    */
    @Override
    public double getTotal(int codigo) throws Exception {

        if(codigo < 0) {
            throw new CodigoNotaFiscalNotSupportedException("Para obter o total o código da nota não pode ser menor que zero.");
        }

        try {
            NotaFiscal notaFiscal = getNotaFiscal(codigo);
            return notaFiscal.getTotal();
        } catch (NotaFiscalNotFoundException e) {
            throw new NotaFiscalNotFoundException("Não foi possível encontrar a nota fiscal com o código: " + codigo + " para obter o total.");
        }
    }

    /**
    * Método responsável por retornar o total de uma nota fiscal
    * @param codigo Código da nota fiscal a ser retornada
    * @return Total da nota fiscal com o código informado
    */
    @Override
    public void addItem(int codigo, Item item) throws Exception {

        if(codigo < 0) {
            throw new CodigoNotaFiscalNotSupportedException("Para adicionar um item o código da nota não pode ser menor que zero.");
        }

        if(item == null) {
            throw new ItemNotSupportedException("Para adicionar um item ele não pode ser nulo.");
        }

        try {
            NotaFiscal notaFiscal = getNotaFiscal(codigo);
            notaFiscal.add(item);
            return;
        } catch (NotaFiscalNotFoundException e) {
            throw new NotaFiscalNotFoundException("Não foi possível encontrar a nota fiscal com o código: " + codigo + " para adicionar o item.");
        }
    }

    /**
    * Método responsável por remover um item de uma nota fiscal
    * @param codigo Código da nota fiscal a ser removida
    * @return nova lista de itens da nota fiscal
    */
    @Override
    public void removeItem(int codigo, Item item) throws Exception {

        if(codigo < 0) {
            throw new CodigoNotaFiscalNotSupportedException("Para remover um item o código da nota não pode ser menor que zero.");
        }

        if(item == null) {
            throw new ItemNotSupportedException("Para remover um item ele não pode ser nulo.");
        }

        try {
            NotaFiscal notaFiscal = getNotaFiscal(codigo);
            notaFiscal.remove(item);
            return;
        } catch (NotaFiscalNotFoundException e) {
            throw new NotaFiscalNotFoundException("Não foi possível encontrar a nota fiscal com o código: " + codigo + " para remover o item.");
        }
    }

    /**
    * Método responsável por retornar as notas fiscais de um dia específico
    * @param calendar Data a ser pesquisada
    * @return Lista de notas fiscais
    */
    public ArrayList<NotaFiscal> getNotasFiscaisPorData(Calendar calendar) throws Exception {
        ArrayList<NotaFiscal> notasFiscais = new ArrayList<NotaFiscal>();

        if(calendar == null) {
            throw new NotaFiscalNotFoundException("Data não pode ser nula.");
        }

        for(NotaFiscal notaFiscal : listaNotaFiscal) {
            if(notaFiscal.getData().equals(calendar)) {
                notasFiscais.add(notaFiscal);
            }
        }
        
        if(!notasFiscais.isEmpty()) {
            return notasFiscais;
        }

        String dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String ano = String.valueOf(calendar.get(Calendar.YEAR));

        try  {
            String data = getDataFormatada(dia, mes, ano);
            throw new NotaFiscalNotFoundException("Não foi possível encontrar notas fiscais com a data: " + data + ".");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
    * Método responsável por retornar a lista de notas fiscais
    * @return Lista de notas fiscais
    */
    public ArrayList<NotaFiscal> getArray() {
        return listaNotaFiscal;
    }

    private String getDataFormatada(String dia, String mes, String ano) {
        if(dia == null || mes == null || ano == null ||  dia.trim().isEmpty() || mes.trim().isEmpty() || ano.trim().isEmpty()) {
            throw new DataNotSupportedException("Erro ao formatar data.");
        }

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

    public boolean isEmpty() {
        return listaNotaFiscal.isEmpty();
    }

}
