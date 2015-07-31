package be.intec.forzajuno;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import be.intec.forzajuno.model.Speler;
import be.intec.forzajuno.persistence.SpelerDao;
import be.intec.forzajuno.persistence.SpelerDaoORMImpl;

public class SpelersDetailFragment extends Fragment {

    private String volledigeNaam;
    private int position;
    private SpelerDao dao;
    private int[] imageIds = new int[]{R.drawable.kenneth_profiel, R.drawable.matti_profiel, R.drawable.kevin_prfiel, R.drawable.jef_profiel, R.drawable.kerk_profiel};


    public SpelersDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getArguments();

        if (extras != null) {
            volledigeNaam = extras.getString("vn");
            position = extras.getInt("pos");
        }

        dao = new SpelerDaoORMImpl(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_spelers_detail, container, false);

        TextView postEnGemeente = (TextView) v.findViewById(R.id.txtPostEnGemeente);
        TextView adres = (TextView) v.findViewById(R.id.txtadres);
        TextView gsmNummer = (TextView) v.findViewById(R.id.txtGsmNummer);
        TextView rekeningNummer = (TextView) v.findViewById(R.id.txtRekeningNummer);
        TextView geboorteDatum = (TextView) v.findViewById(R.id.txtGeboortedatum);
        TextView info = (TextView) v.findViewById(R.id.txtInfo);
        ImageView profielFotoGroot = (ImageView) v.findViewById(R.id.imgGroteProfielfoto);

        Speler speler = null;
        try {
            speler = dao.getSpelerBijVolledigeNaam(volledigeNaam);
        } catch (SQLException e) {
            throw new RuntimeException("Speler niet gevonden!");
        }

        if (speler != null) {
            postEnGemeente.setText(speler.getPostcode() + " " + speler.getGemeente());
            adres.setText(speler.getAdres());
            gsmNummer.setText(speler.getTelefoonnummer());
            rekeningNummer.setText(speler.getRekeningNummer());
            Date date = speler.getGeboortedatum();
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            geboorteDatum.setText(format.format(date));
            info.setText(speler.getInfo());
            profielFotoGroot.setImageDrawable(getActivity().getResources().getDrawable(imageIds[position]));
        }

        return v;
    }


}
