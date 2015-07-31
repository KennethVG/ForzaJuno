package be.intec.forzajuno;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import be.intec.forzajuno.model.Speler;

public class SpelerUpdatenFragment extends Fragment {

    private Speler speler;


    public SpelerUpdatenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getArguments();

        if (extras != null) {
            speler = (Speler) extras.getSerializable("speler");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_speler_updaten, container, false);
        EditText adres = (EditText) v.findViewById(R.id.etxtAdres);
        EditText postEnGemeente = (EditText) v.findViewById(R.id.etxtPostEnGemeente);
        EditText geboorteDatum = (EditText) v.findViewById(R.id.etxtGeboortedatum);
        EditText gsmNummer = (EditText) v.findViewById(R.id.etxtGsmNummer);
        EditText rekeningNummer = (EditText) v.findViewById(R.id.etxtRekeningnummer);

        if (speler != null) {
            adres.setText(speler.getAdres());
            postEnGemeente.setText(speler.getPostcode() + " " + speler.getGemeente());
            geboorteDatum.setText(speler.getGeboortedatum().toString());
            gsmNummer.setText(speler.getTelefoonnummer());
            rekeningNummer.setText(speler.getRekeningNummer());
        }

        // Hide softkeyboard!!
        // Change EditText AND COLOR!


        return v;
    }

}
