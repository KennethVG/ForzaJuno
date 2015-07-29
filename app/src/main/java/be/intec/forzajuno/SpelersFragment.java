package be.intec.forzajuno;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
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
        ListView lvSpelers = (ListView) v.findViewById(R.id.lv_spelers);

//        String dateInString = "13-10-1988";
//        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");


        try {
//            Date date = format.parse(dateInString);
//            Speler kenneth = new Speler("Kenneth", "Van Gijsel", date, "BE50 9730 9126 5718", "Aanvaller: snel", "Welvaartstraat 9", "Berlaar", 2590, "0473/40.55.49");
//            Speler matti = new Speler("Mattias", "Vercauteren", format.parse("18-06-1986"), "BE52 7506 4481 2009", "Aanvaller: balvast", "Markt 64 bus 3", "Berlaar", 2590, "0472/34.06.54");
//            Speler verto = new Speler("Kevin", "Vertommen", format.parse("21-12-1987"), null, "Verdediger: sober", "Welvaartstraat 62", "Berlaar", 2590, "0472/52.66.11");
//            Speler jef = new Speler("Jef", "Jacobs", format.parse("27-07-1988"), "BE14 0013 9831 1883", "Middenvelder: werker", "Daalstraat 39", "Berlaar", 2590, "0472/68.79.88");
//            mSpelerDao.add(kenneth);
//            mSpelerDao.add(matti);
//            mSpelerDao.add(verto);
//            mSpelerDao.add(jef);
            spelers = mSpelerDao.getAllSpelers();
            adapter = new SpelersAdapter(getActivity(), spelers);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Speler kan niet toegevoegd worden!");
        }
//        catch (ParseException p) {
//            throw new RuntimeException("Cannot format DATE!");
//        }

        if (adapter != null) {
            lvSpelers.setAdapter(adapter);
        } else {
            Toast.makeText(getActivity(), "ADAPTER IS NULL, ER IS IETS MIS!", Toast.LENGTH_LONG).show();
        }

        lvSpelers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout linearLayout = (LinearLayout) view;
                TextView tv = (TextView) linearLayout.findViewById(R.id.txtVolledigeNaam);
                String volledigeNaam = tv.getText().toString();
//                mCallBacks.onItemSelected(volledigeNaam);
                Toast.makeText(getActivity(), volledigeNaam, Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }


    private interface CallBacks {
        void onItemSelected(String item);
    }

}
