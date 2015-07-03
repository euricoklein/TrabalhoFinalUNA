package dirceubelem.exemplo7.to;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by Welbert on 27/06/2015.
 */

public class TOCompra extends TOBase implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static String TABLE = "compra";
    public static String[] COLUMNS = {"codigo", "data", "nome", "status", "valor", "tipo"};
    private int codigo;
    private String data;
    private String NomeCompra;
    private String status;
    private double preco_sug;
    private String tipo_compra;


    /**
     * @param codigo
     * @param data
     * @param nomeCompra
     * @param status
     * @param preco_sug
     * @param tipo_compra
     */
    public TOCompra(int codigo, String data, String nomeCompra, String status,
                    double preco_sug, String tipo_compra) {
        super();
        this.codigo = codigo;
        this.data = data;
        NomeCompra = nomeCompra;
        this.status = status;
        this.preco_sug = preco_sug;
        this.tipo_compra = tipo_compra;
    }

    public TOCompra(String data, String nomeCompra, String status,
                    double preco_sug, String tipo_compra) {

        super();
        this.data = data;
        this.NomeCompra = nomeCompra;
        this.status = status;
        this.preco_sug = preco_sug;
        this.tipo_compra = tipo_compra;

    }

    public TOCompra() {
        super();
    }

    public ContentValues getContentValues(boolean key) {
        ContentValues values = new ContentValues();
        values.put(COLUMNS[0], this.getCodigo());
        values.put(COLUMNS[1], this.data);
        values.put(COLUMNS[2], this.getNomeCompra());
        values.put(COLUMNS[3], this.getStatus());
        values.put(COLUMNS[4], this.getPreco_sug());
        values.put(COLUMNS[5], this.getTipo_compra());
        return values;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNomeCompra() {
        return NomeCompra;
    }

    public void setNomeCompra(String nomeCompra) {
        NomeCompra = nomeCompra;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPreco_sug() {
        return preco_sug;
    }

    public void setPreco_sug(double preco_sug) {
        this.preco_sug = preco_sug;
    }

    public String getTipo_compra() {
        return tipo_compra;
    }

    public void setTipo_compra(String tipo_compra) {
        this.tipo_compra = tipo_compra;
    }

}


