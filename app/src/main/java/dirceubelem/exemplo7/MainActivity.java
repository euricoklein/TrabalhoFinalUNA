package dirceubelem.exemplo7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import dirceubelem.exemplo7.adapter.CompraAdapter;
import dirceubelem.exemplo7.dao.DAOCompra;
import dirceubelem.exemplo7.fw.DBHelper;
import dirceubelem.exemplo7.fw.SharedPreferencesHelper;
import dirceubelem.exemplo7.to.TOCompra;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {


    List<TOCompra> listacompra;
    ListView lstCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstCompra = (ListView) findViewById(R.id.listaCompra);

        boolean existeBanco = SharedPreferencesHelper.read(this, "database", "created", false);

        if (existeBanco) {

            DBHelper.createDataBase(new DBHelper.OnCreateDataBaseListener() {
                @Override
                public void onCreateDataBase(boolean success) {

                    SharedPreferencesHelper.write(MainActivity.this, "database", "created", true);
                }
            });
        }
        listacompra = ListaCompra();
        CompraAdapter adp = new CompraAdapter(listacompra, this);
        lstCompra.setAdapter(adp);
        lstCompra.setOnItemClickListener(this);

    }

    public List<TOCompra> ListaCompra() {
        DAOCompra c = new DAOCompra(this);
        int f = c.quantidade();
        List<TOCompra> lst;
        lst = c.listaCompra();
        c.close();
        return lst;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TOCompra compra = listacompra.get(position);
        Intent i = new Intent(this, ListaItens.class);
        i.putExtra("codigoCompra", compra);
        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //   getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.menu_dados_compra, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.compraInsert:
                //openSearch();
                return true;
            case R.id.action_settings:
                //    openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        /*

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);*/
    }
}
