package dirceubelem.exemplo7;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dirceubelem.exemplo7.dao.DAOItem;
import dirceubelem.exemplo7.to.TOCompra;
import dirceubelem.exemplo7.to.TOItem;


public class ItemCompra extends ActionBarActivity {

    TOCompra c;
    private EditText edt_nmitem;
    private EditText edt_infoadic;
    private EditText edt_qtde;
    private EditText edt_vrultcomp;
    private EditText edt_qtdeultcomp;
    private List<TOItem> lstItemCompra;
    private EditText edt_valorItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item_compra);
        c = (TOCompra) getIntent().getSerializableExtra("Compras");

        lstItemCompra = new ArrayList<>();

        edt_nmitem = (EditText) findViewById(R.id.edt_nmitem);
        edt_infoadic = (EditText) findViewById(R.id.edt_infadic);
        edt_qtde = (EditText) findViewById(R.id.edt_qtdeitem);
        edt_vrultcomp = (EditText) findViewById(R.id.edt_ultvalor);
        edt_qtdeultcomp = (EditText) findViewById(R.id.edt_ultqtde);
        edt_valorItem = (EditText) findViewById(R.id.edt_valor);
    }


    public void novo_item(View v) {
        lstItemCompra.add(new TOItem(0, 0, edt_nmitem.getText().toString(), edt_infoadic.getText().toString(),
                Float.parseFloat(edt_qtde.getText().toString()), Float.parseFloat(edt_valorItem.getText().toString())));

        Toast.makeText(getApplicationContext(), "Item incluso com sucesso!", Toast.LENGTH_SHORT).show();
        ClearFields();
    }

    public void GravaItem(View v) {
        DAOItem itemR;
        try {
            itemR = new DAOItem(getApplicationContext());
            itemR.insert(lstItemCompra);
            itemR.close();
            Toast.makeText(getApplicationContext(), "Itens gravados com sucesso!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void ClearFields() {
        edt_nmitem.setText("");
        edt_infoadic.setText("");
        edt_qtde.setText("");
        edt_valorItem.setText("");
        edt_vrultcomp.setText("");
        edt_qtdeultcomp.setText("");
    }


}

