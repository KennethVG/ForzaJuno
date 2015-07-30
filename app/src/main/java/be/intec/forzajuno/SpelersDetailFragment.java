package be.intec.forzajuno;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private SpelerDao dao;


    public SpelersDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volledigeNaam = getArguments().getString("vn");
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
        }

        return v;
    }


}
