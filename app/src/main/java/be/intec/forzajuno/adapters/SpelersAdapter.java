package be.intec.forzajuno.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import be.intec.forzajuno.R;
import be.intec.forzajuno.model.Speler;

public class SpelersAdapter extends BaseAdapter {

    private List<Speler> spelers;
    private Context context;
    private int[] imageIds = new int[]{R.drawable.kenneth_profiel, R.drawable.matti_profiel, R.drawable.kevin_prfiel, R.drawable.jef_profiel, R.drawable.kerk_profiel};

    public SpelersAdapter(Context context, List<Speler> spelers) {
        this.spelers = spelers;
        this.context = context;
    }

    @Override
    public int getCount() {
        return spelers.size();
    }

    @Override
    public Speler getItem(int position) {
        return spelers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.een_speler, null);
            holder = new ViewHolder();
            holder.mTxtVolledigeNaam = (TextView) convertView.findViewById(R.id.txtVolledigeNaam);
            holder.mImgProfielFoto = (ImageView) convertView.findViewById(R.id.imgProfielfoto);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Speler speler = getItem(position);

        if (speler != null) {
            holder.mTxtVolledigeNaam.setText(speler.getVoornaam() + " " + speler.getAchternaam());


            if (imageIds.length > position) {
                holder.mImgProfielFoto.setImageDrawable(context.getResources().getDrawable(imageIds[position]));
            }

        }

        return convertView;
    }

    private class ViewHolder {
        TextView mTxtVolledigeNaam;
        ImageView mImgProfielFoto;
    }
}
