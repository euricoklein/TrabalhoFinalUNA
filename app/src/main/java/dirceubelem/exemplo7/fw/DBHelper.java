package dirceubelem.exemplo7.fw;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import dirceubelem.exemplo7.Exemplo7;

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper instance;
    private final Context context;

    public DBHelper(Context context) {
        super(context, Constant.Database.DATABASE_NAME, null,
                Constant.Database.CURRENT_REVISION);
        this.context = context;
    }

    public synchronized static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);

            try {
                instance.createDataBase();
            } catch (Exception e) {
                instance = null;
            }
        }

        return instance;
    }

    public static void createDataBase(final OnCreateDataBaseListener listener) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {
                boolean success = false;

                try {
                    success = DBHelper
                            .getInstance(Exemplo7.getInstance()) != null;
                } catch (Exception e) {
                    success = false;
                    e.printStackTrace();
                }

                return success;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (listener != null) {
                    listener.onCreateDataBase(result.booleanValue());
                }
            }

        }.execute();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        return SQLiteDatabase.openDatabase(
                Constant.Database.ABSOLUTE_DATABASE_PATH, null,
                SQLiteDatabase.OPEN_READWRITE
                        | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
    }

    private void copyDataBase() throws IOException {
        InputStream in = context.getAssets().open(
                Constant.Database.ASSETS_DATABASE_PATH + "/"
                        + Constant.Database.DATABASE_NAME);

        OutputStream out = new FileOutputStream(
                Constant.Database.ABSOLUTE_DATABASE_PATH);

        int length;
        byte[] buffer = new byte[1024];

        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }

        out.flush();
        out.close();

        in.close();
    }

    private void createDataBase() throws IOException {
        SQLiteDatabase checkDB = getReadableDatabase();
        checkDB.close();

        try {
            copyDataBase();
        } catch (IOException e) {
            throw new Error("Error copying database");
        }
    }

    public interface OnCreateDataBaseListener {
        void onCreateDataBase(boolean success);
    }

}
