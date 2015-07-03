package dirceubelem.exemplo7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import dirceubelem.exemplo7.to.TOCompra;


public class DadosCompra extends ActionBarActivity {

    Spinner spinner;
    private Button btn_go_item;
    private ImageButton btn_loadCalendar;
    private EditText edt_dtatual;
    private EditText edt_nomecompra;
    private EditText edt_precomax;
    private TOCompra compra;
    private char tipo_compra;
    private TextView txt_dataCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_compra);
        compra = (TOCompra) getIntent().getSerializableExtra("codigoCompra");

        spinner = (Spinner) findViewById(R.id.spTipoCompra);
        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.arTipoCompra,
                android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        edt_dtatual = (EditText) findViewById(R.id.edt_DataCompra);
        edt_nomecompra = (EditText) findViewById(R.id.edt_nomecopra);
        edt_precomax = (EditText) findViewById(R.id.edt_precomax);
        txt_dataCompra = (TextView) findViewById(R.id.txt_dtatual);
        btn_loadCalendar = (ImageButton) findViewById(R.id.btn_img_data);


        btn_loadCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerFragment.getDatePicker(new DatePickerFragment.DatePickerFragmentListener() {
                    @Override
                    public void onDateSelected(int ano, int mes, int dia) {

                        edt_dtatual.setText(dia + "/" + mes + "/" + ano);
                        txt_dataCompra.setText(dia + "/" + mes + "/" + ano);

                    }
                }).show(DadosCompra.this.getFragmentManager(), "Dialog");


            }
        });


        String _s = getIntent().getStringExtra("Data");
        edt_dtatual.setText(_s);
        btn_go_item = (Button) findViewById(R.id.btn_go_item);

        btn_go_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (compra == null) {
                    compra = new TOCompra(edt_dtatual.getText().toString(), edt_nomecompra.getText().toString(), "P".toString(),
                            Double.parseDouble(edt_precomax.getText().toString()), spinner.getSelectedItem().toString());
                }

                Intent i = new Intent(DadosCompra.this, ItemCompra.class);
                i.putExtra("Compras", compra);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dados_compra, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
