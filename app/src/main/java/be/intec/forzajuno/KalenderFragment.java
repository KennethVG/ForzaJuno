package be.intec.forzajuno;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.List;

import be.intec.forzajuno.adapters.KalenderAdapter;
import be.intec.forzajuno.model.Kalender;
import be.intec.forzajuno.persistence.KalenderDao;
import be.intec.forzajuno.persistence.KalenderDaoImpl;

public class KalenderFragment extends Fragment {


    private ListView lvKalender1516;
    private KalenderDao kalenderDao;
    private List<Kalender> kalenderList;
    private KalenderAdapter kalenderAdapter;
    private CallBacksKalender callBacksKalender;

    public interface CallBacksKalender {
        void onItemSelected(Kalender kalender);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            callBacksKalender = (CallBacksKalender) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement Callbacks interface!");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callBacksKalender = null;
    }

    public KalenderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kalenderDao = new KalenderDaoImpl(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_kalender, container, false);
        lvKalender1516 = (ListView) v.findViewById(R.id.lv_kalender_1516);

        try {
            if (kalenderDao.getFullKalender().isEmpty()) {
                fillKalender2015_2016();
            }
            kalenderList = kalenderDao.getFullKalender();
            kalenderAdapter = new KalenderAdapter(getActivity(), kalenderList);

        } catch (SQLException se) {
            throw new RuntimeException("Cannot fill kalender");
        }
        lvKalender1516.setAdapter(kalenderAdapter);
        lvKalender1516.setOnItemClickListener(myListener);

        return v;
    }

    private AdapterView.OnItemClickListener myListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            try {
                callBacksKalender.onItemSelected(kalenderDao.findKalenderById(kalenderList.get(position).get_id()));
            } catch (SQLException e) {
                throw new RuntimeException("SQL " + e.getMessage());
            }

        }
    };

    private void fillKalender2015_2016() throws SQLException {
        Kalender wedstrijd1 = new Kalender("Zo 23 aug 19.30u", "Forza Juno - JJ Van Dessel");
        Kalender wedstrijd2 = new Kalender("Ma 31 aug 22.00u", "Forza Juno - Dynamo Gestel");
        Kalender wedstrijd3 = new Kalender("Zo 6 sep 18.30u", "NC² - Forza Juno");
        Kalender wedstrijd4 = new Kalender("Vr 18 sep 21.00u", "Forza Juno - D'ouw Duvels");
        Kalender wedstrijd5 = new Kalender("Zo 20 sep 19.30u", "Forza Juno - Beerzelberg");
        Kalender wedstrijd6 = new Kalender("Zo 29 sep 22.00u", "Forza Juno - De Boeliekes");
        Kalender wedstrijd7 = new Kalender("Zo 4 okt 18.30u", "Forza Juno - Dynamo Lier");
        Kalender wedstrijd8 = new Kalender("Zo 11 okt 19.30u", "Forza Juno - Jong Valvecke");
        Kalender wedstrijd9 = new Kalender("Zo 19 okt 22.00u", "Forza Juno - De Ronnies");
        Kalender wedstrijd10 = new Kalender("Zo 25 okt 19.30u", "Forza Juno - Los Tigris");
        Kalender wedstrijd11 = new Kalender("Do 5 nov 22.00u", "Forza Juno - Heidebloem");
        Kalender wedstrijd12 = new Kalender("Di 10 nov 22.00u", "Forza Juno - PJB Waver");
        Kalender wedstrijd13 = new Kalender("Di 17 nov 22.00u", "Forza Juno - Ontmoeting");
        Kalender wedstrijd14 = new Kalender("Zo 22 nov 20.30u", "Forza Juno - De vedetjes");
        Kalender wedstrijd15 = new Kalender("Zo 29 nov 18.30u", "Forza Juno - Klikske");
        Kalender wedstrijd16 = new Kalender("Vr 11 dec 22.00u", "Forza Juno - Pink Panthers");
        Kalender wedstrijd17 = new Kalender("Zo 13 dec 21.30u", "Forza Juno - La Tirette");
        Kalender wedstrijd18 = new Kalender("Vr 18 dec 22.00u", "D'ouw duvels - Forza Juno");


        kalenderDao.addKalender(wedstrijd1);
        kalenderDao.addKalender(wedstrijd2);
        kalenderDao.addKalender(wedstrijd3);
        kalenderDao.addKalender(wedstrijd4);
        kalenderDao.addKalender(wedstrijd5);
        kalenderDao.addKalender(wedstrijd6);
        kalenderDao.addKalender(wedstrijd7);
        kalenderDao.addKalender(wedstrijd8);
        kalenderDao.addKalender(wedstrijd9);
        kalenderDao.addKalender(wedstrijd10);
        kalenderDao.addKalender(wedstrijd11);
        kalenderDao.addKalender(wedstrijd12);
        kalenderDao.addKalender(wedstrijd13);
        kalenderDao.addKalender(wedstrijd14);
        kalenderDao.addKalender(wedstrijd15);
        kalenderDao.addKalender(wedstrijd16);
        kalenderDao.addKalender(wedstrijd17);
        kalenderDao.addKalender(wedstrijd18);

    }

}
