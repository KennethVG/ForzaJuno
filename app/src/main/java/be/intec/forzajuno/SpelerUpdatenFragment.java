package be.intec.forzajuno;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import be.intec.forzajuno.model.Speler;
import be.intec.forzajuno.persistence.SpelerDao;
import be.intec.forzajuno.persistence.SpelerDaoORMImpl;

public class SpelerUpdatenFragment extends Fragment {

    private Speler speler;
    private SpelerDao dao;
    private int[] imageIds = new int[]{R.drawable.kenneth_profiel, R.drawable.matti_profiel, R.drawable.kevin_prfiel, R.drawable.jef_profiel, R.drawable.kerk_profiel};

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

        dao = new SpelerDaoORMImpl(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_speler_updaten, container, false);
        final EditText adres = (EditText) v.findViewById(R.id.etxtAdres);
        final EditText postEnGemeente = (EditText) v.findViewById(R.id.etxtPostEnGemeente);
        final EditText geboorteDatum = (EditText) v.findViewById(R.id.etxtGeboortedatum);
        final EditText gsmNummer = (EditText) v.findViewById(R.id.etxtGsmNummer);
        final EditText rekeningNummer = (EditText) v.findViewById(R.id.etxtRekeningnummer);
        final EditText info = (EditText) v.findViewById(R.id.etxtInfo);
        ImageView imgProfielFoto = (ImageView) v.findViewById(R.id.imgGroteProfielfoto);

        Button btnUpdateSpeler = (Button) v.findViewById(R.id.btn_update);


        if (speler != null) {
            adres.setText(speler.getAdres());
            postEnGemeente.setText(speler.getPostcode() + " " + speler.getGemeente());
            Date date = speler.getGeboortedatum();
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            geboorteDatum.setText(format.format(date));
            gsmNummer.setText(speler.getTelefoonnummer());
            rekeningNummer.setText(speler.getRekeningNummer());
            info.setText(speler.getInfo());
            imgProfielFoto.setImageDrawable(getActivity().getResources().getDrawable(imageIds[speler.getId() - 1]));
        }


        btnUpdateSpeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Speler updaten met nieuwe tekst:
                speler.setAdres(adres.getText().toString());
                speler.setGemeente(postEnGemeente.getText().toString().split(" ")[1]);
                speler.setPostcode(Integer.parseInt(postEnGemeente.getText().toString().split(" ")[0]));
                speler.setTelefoonnummer(gsmNummer.getText().toString());
                speler.setRekeningNummer(rekeningNummer.getText().toString());
                speler.setInfo(info.getText().toString());

                try {
                    if (dao.update(speler) == 1)
                        Toast.makeText(getActivity(), "Speler geüpdate", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getActivity(), "Update MISLUKT", Toast.LENGTH_LONG).show();

                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Updaten van speler niet gelukt!");
                }

                // Hide softkeyboard:
                InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                manager.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                // Switch back to previous screen:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new SpelersFragment()).commit();

            }
        });


        return v;
    }

}
