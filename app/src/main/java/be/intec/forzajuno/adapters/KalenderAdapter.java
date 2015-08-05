package be.intec.forzajuno.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import be.intec.forzajuno.R;
import be.intec.forzajuno.model.Kalender;

public class KalenderAdapter extends BaseAdapter {

    private Context context;
    private List<Kalender> kalenders;

    public KalenderAdapter(Context context, List<Kalender> kalenders) {
        this.context = context;
        this.kalenders = kalenders;
    }

    @Override
    public int getCount() {
        return kalenders.size();
    }

    @Override
    public Kalender getItem(int position) {
        return kalenders.get(position);
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
            convertView = inflater.inflate(R.layout.een_kalender, null);
            holder = new ViewHolder();
            holder.mTxtDatumUur = (TextView) convertView.findViewById(R.id.txtDatumUur);
            holder.mTxtWedstrijd = (TextView) convertView.findViewById(R.id.txtWedstrijd);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Kalender k = getItem(position);

        if (k != null) {
            holder.mTxtDatumUur.setText(k.getDate());
            holder.mTxtWedstrijd.setText(k.getMatch());
        }
        return convertView;
    }

    private class ViewHolder {
        TextView mTxtDatumUur, mTxtWedstrijd;
    }
}
