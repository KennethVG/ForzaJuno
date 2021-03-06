package be.intec.forzajuno;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.software.shell.fab.ActionButton;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import be.intec.forzajuno.adapters.SpelersAdapter;
import be.intec.forzajuno.model.Speler;
import be.intec.forzajuno.persistence.SpelerDao;
import be.intec.forzajuno.persistence.SpelerDaoORMImpl;

public class SpelersFragment extends Fragment {

    private SpelerDao mSpelerDao;
    private List<Speler> spelers;
    private SpelersAdapter adapter;
    private CallBacks mCallBacks;
    private ListView lvSpelers;

    public interface CallBacks {
        void onItemSelected(String volledigeNaam, int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Refresh the list
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            lvSpelers.setAdapter(adapter);
        }

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallBacks = (CallBacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement Callbacks interface!");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBacks = null;
    }

    public SpelersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSpelerDao = new SpelerDaoORMImpl(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_spelers, container, false);
        lvSpelers = (ListView) v.findViewById(R.id.lv_spelers);
        ActionButton actionButton = (ActionButton) v.findViewById(R.id.action_button);


        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new SpelerToevoegenFragment()).commit();
            }
        });

        try {
            if (mSpelerDao.getAllSpelers().isEmpty()) {
                addAllPlayers();
            }
            spelers = mSpelerDao.getAllSpelers();
            adapter = new SpelersAdapter(getActivity(), spelers);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Speler kan niet toegevoegd worden!");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Fout in het parsen van de datum!");
        }

        lvSpelers.setAdapter(adapter);

        lvSpelers.setOnItemClickListener(myListener);
        return v;
    }

    private AdapterView.OnItemClickListener myListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            LinearLayout linearLayout = (LinearLayout) view;
            TextView tv = (TextView) linearLayout.findViewById(R.id.txtVolledigeNaam);
            String vn = tv.getText().toString();
            mCallBacks.onItemSelected(vn, position);
        }
    };


    private void addAllPlayers() throws ParseException, SQLException {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Speler kenneth = new Speler("Kenneth", "Van Gijsel", format.parse("13-10-1988"), "BE50 9730 9126 5718", "Aanvaller: snel", "Welvaartstraat 9", "Berlaar", 2590, "0473/40.55.49");
        Speler matti = new Speler("Mattias", "Vercauteren", format.parse("18-06-1986"), "BE52 7506 4481 2009", "Aanvaller: balvast", "Markt 64 bus 3", "Berlaar", 2590, "0472/34.06.54");
        Speler verto = new Speler("Kevin", "Vertommen", format.parse("21-12-1987"), null, "Verdediger: tank", "Welvaartstraat 62", "Berlaar", 2590, "0472/52.66.11");
        Speler jef = new Speler("Jef", "Jacobs", format.parse("27-07-1988"), "BE14 0013 9831 1883", "Middenvelder: werkpaard", "Daalstraat 39", "Berlaar", 2590, "0472/68.79.88");
        Speler joni = new Speler("Joni", "Wijns", format.parse("13-07-1988"), null, "Middenvelder: inzicht", "Smidstraat 91", "Berlaar", 2590, "0473/80.60.31");
        Speler linsen = new Speler("Jeroen", "Linsen", format.parse("10-05-1989"), null, "Verdediger: kopbalsterk", "Kerselaarlaan 4", "Hallaar", 2220, "0495/71.64.65");
        Speler kerkhofs = new Speler("Thomas", "Kerkhofs", format.parse("09-12-1988"), null, "Aanvaller: snel", null, "Berlaar-Heikant", 2590, "0494/90.72.41");
        Speler voet = new Speler("Kevin", "Voet", format.parse("19-03-1988"), null, "Flankspeler: sober", "Constant Verhulststraat 44", "Berlaar", 2590, "0498/10.82.02");
        Speler gote = new Speler("Steven", "Vanderghote", format.parse("21-06-1988"), null, "Aanvaller: klungelen", "Zevenbergen 42", "Lier", 2500, "0478/38.06.52");
        Speler nick = new Speler("Nick", "Van Hove", format.parse("29-12-1988"), null, "Aanvaller: technisch", "Sint-Lambertusstraat 28B", "Kessel", 2650, "0479/63.35.58");
        Speler kleine = new Speler("Jeroen", "De Winter", format.parse("12-10-1983"), null, "Keeper: wakko-jakko", "Sint-Annastraat 3B1", "Berlaar", 2590, "0476/68.38.52");
        Speler vik = new Speler("Vik", "Van Brandt", format.parse("19-05-1988"), null, "Middenvelder: snel", "Itegembaan 14", "Berlaar", 2590, "0473/72.86.75");
        Speler danny = new Speler("Danny", "Volkaerts", format.parse("27-11-1987"), null, "Middenvelder: stofzuiger", " ", "Leuven", 3000, "0478/75.83.82");
        Speler franky = new Speler("Franky", "Van Gijsel", format.parse("20-08-1985"), null, "Aanvaller: goalgetter", "Cellebroerstraat 2", "Mechelen", 2800, "0473/30.33.79");
        Speler yves = new Speler("Yves", "yskout", format.parse("04-10-1988"), null, "Verdediger: stevig", "Achiel Cleynhenslaan 43 ", "Keerbergen", 3140, "0472/58.47.53");

        mSpelerDao.add(kenneth);
        mSpelerDao.add(matti);
        mSpelerDao.add(verto);
        mSpelerDao.add(jef);
        mSpelerDao.add(joni);
        mSpelerDao.add(kerkhofs);
        mSpelerDao.add(nick);
        mSpelerDao.add(kleine);
        mSpelerDao.add(gote);
        mSpelerDao.add(voet);
        mSpelerDao.add(vik);
        mSpelerDao.add(danny);
        mSpelerDao.add(linsen);
        mSpelerDao.add(yves);
        mSpelerDao.add(franky);
    }


}
