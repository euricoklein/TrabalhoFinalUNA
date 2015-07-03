package dirceubelem.exemplo7.to;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by Welbert on 28/06/2015.
 */
public class TOItem
        extends TOBase implements Serializable {

    private static final long serialVersionUID = 1L;
    public static String TABLE = "item";
    public static String[] COLUMNS = {"codigo", "cod_compra", "nome", "info_adic", "quantidade", "valor"};
    private int cod_compra;
    private int nr_item;
    private String nome_item;
    private String inf_adic;
    private float quantidade;
    private float vr_unitario;

    public TOItem(int cod_compra, int nr_item, String nome_item, String inf_adic,
                  float quantidade, float vr_unitario) {
        super();
        this.cod_compra = cod_compra;
        this.nr_item = nr_item;
        this.nome_item = nome_item;
        this.inf_adic = inf_adic;
        this.quantidade = quantidade;
        this.vr_unitario = vr_unitario;
    }


    public TOItem() {
        super();
        // TODO Auto-generated constructor stub
    }


    public ContentValues getContentValues(boolean key) {
        ContentValues values = new ContentValues();
        values.put(COLUMNS[0], this.getNr_item());
        values.put(COLUMNS[1], this.getCod_compra());
        values.put(COLUMNS[2], this.getNome_item());
        values.put(COLUMNS[3], this.getInf_adic());
        values.put(COLUMNS[4], this.getQuantidade());
        values.put(COLUMNS[5], this.getVr_unitario());
        return values;
    }


    public int getCod_compra() {
        return cod_compra;
    }

    public void setCod_compra(int cod_compra) {
        this.cod_compra = cod_compra;
    }

    public int getNr_item() {
        return nr_item;
    }

    public void setNr_item(int nr_item) {
        this.nr_item = nr_item;
    }

    public String getNome_item() {
        return nome_item;
    }

    public void setNome_item(String nome_item) {
        this.nome_item = nome_item;
    }

    public String getInf_adic() {
        return inf_adic;
    }

    public void setInf_adic(String inf_adic) {
        this.inf_adic = inf_adic;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getVr_unitario() {
        return vr_unitario;
    }

    public void setVr_unitario(float vr_unitario) {
        this.vr_unitario = vr_unitario;
    }


}


