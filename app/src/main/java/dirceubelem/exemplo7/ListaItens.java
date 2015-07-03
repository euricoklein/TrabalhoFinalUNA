package dirceubelem.exemplo7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import dirceubelem.exemplo7.adapter.ItemAdapter;
import dirceubelem.exemplo7.dao.DAOItem;
import dirceubelem.exemplo7.to.TOCompra;
import dirceubelem.exemplo7.to.TOItem;


public class ListaItens extends ActionBarActivity implements AdapterView.OnItemClickListener {

    ListView lstwItem;
    List<TOItem> lstItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_itens);

        TOCompra compra = (TOCompra) getIntent().getSerializableExtra("codigoCompra");
        lstwItem = (ListView) findViewById(R.id.listItem_compra);

        loadItemCompra(compra.getCodigo());
        ItemAdapter adp = new ItemAdapter(lstItem, this);
        lstwItem.setAdapter(adp);
        lstwItem.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_itens, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (item.getTitle().equals("Incluir")) {

            Intent i = new Intent(this, ItemCompra.class);
            //i.putExtra("codigoCompra", compra);
            startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }

    private void loadItemCompra(int cod_compra) {
        DAOItem i = new DAOItem(this);
        lstItem = i.listaItem(cod_compra);
        i.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
