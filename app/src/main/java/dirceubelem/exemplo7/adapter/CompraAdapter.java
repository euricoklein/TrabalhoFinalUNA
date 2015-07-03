package dirceubelem.exemplo7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dirceubelem.exemplo7.R;
import dirceubelem.exemplo7.to.TOCompra;

/**
 * Created by Welbert on 27/06/2015.
 */
public class CompraAdapter extends BaseAdapter {

    private List<TOCompra> list;
    private Context context;

    public CompraAdapter(List<TOCompra> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TOCompra c = list.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.listitem, null);

        TextView txtLtipoCompra = (TextView) v.findViewById(R.id.txtLtipoCompra);
        TextView txtLDataCompra = (TextView) v.findViewById(R.id.txtLDataCompra);
        TextView txtLValor = (TextView) v.findViewById(R.id.txtLValor);
        TextView txtLstatus = (TextView) v.findViewById(R.id.txtLstatus);
        ImageView image = (ImageView) v.findViewById(R.id.imgIcon);

        if (c.getTipo_compra() == "Supermercado") {
            image.setImageResource(R.mipmap.carrinho);
        } else {
            if (c.getTipo_compra() == "Shopping") {
                image.setImageResource(R.mipmap.sacola);
            }
        }

        txtLtipoCompra.setText(c.getTipo_compra());
        txtLDataCompra.setText(c.getData());
        txtLValor.setText(Double.toString(c.getPreco_sug()));
        txtLstatus.setText(c.getStatus());

        return v;

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
}

