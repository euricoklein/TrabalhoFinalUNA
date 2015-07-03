package dirceubelem.exemplo7.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import dirceubelem.exemplo7.fw.DBHelper;
import dirceubelem.exemplo7.to.TOEstado;

public class DAOEstado extends DBHelper {

    private SQLiteDatabase db = null;

    public DAOEstado(Context context) {
        super(context);
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    public void insert(TOEstado t) {
        if (db == null) {
            db = this.getWritableDatabase();
        }

        db.insert(TOEstado.TABLE, null, t.getContentValues(true));
    }

    public int update(TOEstado t) {
        if (db == null) {
            db = this.getWritableDatabase();
        }

        int i = db.update(TOEstado.TABLE, t.getContentValues(false), "id = ?",
                new String[]{t.getEstado()});

        return i;
    }

    public TOEstado get(String id) {

        if (db == null) {
            db = this.getReadableDatabase();
        }
        Cursor cursor = db.query(TOEstado.TABLE, // a. table
                TOEstado.COLUMNS, // b. column names
                " id = ? ", // c. selections
                new String[]{id}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        TOEstado t = null;

        if (cursor != null) {
            if (cursor.moveToNext()) {
                t = new TOEstado(cursor);
            }
        }

        return t;
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

    public List<TOEstado> list() {
        List<TOEstado> list = new LinkedList<>();
        String query = "SELECT * FROM estaso ";
        if (db == null) {
            db = this.getReadableDatabase();
        }
/*
        Cursor cursor = db.query(TOEstado.TABLE, // a. table
                TOEstado.COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                " nome ", // g. order by
                null); // h. limit*/
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            TOEstado t = new TOEstado(cursor);
            list.add(t);
        }

        return list;
    }


}
