package dirceubelem.exemplo7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import dirceubelem.exemplo7.R;
import dirceubelem.exemplo7.to.TOItem;

/**
 * Created by Welbert on 29/06/2015.
 */
public class ItemAdapter extends BaseAdapter {

    private List<TOItem> list;
    private Context context;


    public ItemAdapter(List<TOItem> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TOItem i = list.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.lista_item_compra, null);

        TextView txtnomeItem = (TextView) v.findViewById(R.id.txtnomeItem);
        EditText edtQtde = (EditText) v.findViewById(R.id.edtQtde);
        EditText edtvrItem = (EditText) v.findViewById(R.id.edtvrItem);

        txtnomeItem.setText(i.getNome_item());
        edtQtde.setText(Float.toString(i.getQuantidade()));
        edtvrItem.setText(Double.toString(i.getVr_unitario()));

        return v;
    }
}
