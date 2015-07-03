package dirceubelem.exemplo7.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import dirceubelem.exemplo7.fw.DBHelper;
import dirceubelem.exemplo7.to.TOItem;

/**
 * Created by Welbert on 28/06/2015.
 */
public class DAOItem extends DBHelper {

    private SQLiteDatabase db = null;

    public DAOItem(Context context) {
        super(context);
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    public void insert(List<TOItem> lstItem) {

        if (db == null) {
            db = this.getWritableDatabase();
        }
        for (TOItem i : lstItem) {
            db.insert(TOItem.TABLE, null, i.getContentValues(true));
        }
        close();
    }

    public int update(TOItem t) {
        if (db == null) {
            db = this.getWritableDatabase();
        }

        int i = db.update(TOItem.TABLE, t.getContentValues(false), "codigo = ?",
                new String[]{Integer.toString(t.getNr_item())});

        return i;
    }


    public int quantidade(int cod_compra) {

        int quantidade = 0;

        String query = "SELECT count(codigo) FROM item WHERE cod_compra = " + Integer.toString(cod_compra);

        if (db == null) {
            db = this.getReadableDatabase();
        }

        try {
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToNext()) {
                quantidade = cursor.getInt(0);
            }
        } catch (Exception e) {
            String m = e.getMessage();
        }
        return quantidade;
    }

    public List<TOItem> listaItem(int cod_compra) {

        List<TOItem> lista = new LinkedList<>();

        if (db == null) {
            db = this.getReadableDatabase();
        }
        String query = "SELECT codigo, cod_compra, nome, info_adic, quantidade, valor from item";

        Cursor dados = db.rawQuery(query, null);

        while (dados.moveToNext()) {
            lista.add(new TOItem(Integer.parseInt(dados.getString(0)),
                    Integer.parseInt(dados.getString(1)), dados.getString(2),
                    dados.getString(3), Float.parseFloat(dados.getString(4)),
                    Float.parseFloat(dados.getString(5))));
        }
        close();
        return lista;
    }

    public void delete_compra(TOItem i) {

        if (db == null) {
            db = this.getWritableDatabase();
        }
        db.delete(TOItem.TABLE, "codigo= ?", new String[]{Integer.toString(i.getNr_item())});
        db.delete(TOItem.TABLE, "cod_compra= ?", new String[]{Integer.toString(i.getCod_compra())});
        close();
    }

}
