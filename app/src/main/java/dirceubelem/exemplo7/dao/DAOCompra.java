package dirceubelem.exemplo7.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import dirceubelem.exemplo7.fw.DBHelper;
import dirceubelem.exemplo7.to.TOCompra;
import dirceubelem.exemplo7.to.TOItem;

/**
 * Created by Welbert on 27/06/2015.
 */
public class DAOCompra extends DBHelper {
    private SQLiteDatabase db = null;

    public DAOCompra(Context context) {
        super(context);
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    public void insert(TOCompra t) {
        if (db == null) {
            db = this.getWritableDatabase();
        }

        db.insert(TOCompra.TABLE, null, t.getContentValues(true));
    }

    public int update(TOCompra t) {
        if (db == null) {
            db = this.getWritableDatabase();
        }

        int i = db.update(TOCompra.TABLE, t.getContentValues(false), "id = ?",
                new String[]{t.getNomeCompra()});

        return i;
    }


    public int quantidade() {

        int quantidade = 0;

        String query = "SELECT count(codigo) FROM compra ";

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

    public List<TOCompra> listaCompra() {
        List<TOCompra> lista = new LinkedList<>();
        String query = "SELECT codigo, data, nome, status, valor, tipo from compra";

        if (db == null) {
            db = this.getReadableDatabase();
        }
/*
        Cursor dados = db.query("compra", // a. table
                "codigo, data, nome, status, valor, tipo", // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                " nome ", // g. order by
                null); // h. limit*/

        Cursor dados = db.rawQuery(query, null);
        while (dados.moveToNext()) {
            lista.add(new TOCompra(Integer.parseInt(dados.getString(0)), dados.getString(1),
                    dados.getString(2), dados.getString(3),
                    Float.parseFloat(dados.getString(4)), dados.getString(5)));
        }


        return lista;
    }

    public TOCompra loadCompra(int codigoCompra) {
        TOCompra c = null;

        if (db == null) {
            db = this.getReadableDatabase();
        }
        String query = "SELECT codigo, data, nome, status, valor, tipo from compra";
        Cursor dados = db.rawQuery(query, null);
        while (dados.moveToNext()) {
            c = new TOCompra(Integer.parseInt(dados.getString(0)), dados.getString(1),
                    dados.getString(2), dados.getString(3),
                    Float.parseFloat(dados.getString(4)), dados.getString(5));
        }
        return c;

    }


    public void delete_compra(TOCompra c) {

        if (db == null) {
            db = this.getWritableDatabase();
        }
        db.delete(TOCompra.TABLE, "codigo= ?", new String[]{Integer.toString(c.getCodigo())});
        db.delete(TOItem.TABLE, "cod_compra= ?", new String[]{Integer.toString(c.getCodigo())});
        close();
    }


}
