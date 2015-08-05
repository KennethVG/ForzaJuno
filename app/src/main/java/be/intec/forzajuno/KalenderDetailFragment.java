package be.intec.forzajuno;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import be.intec.forzajuno.model.Kalender;
import be.intec.forzajuno.persistence.KalenderDao;
import be.intec.forzajuno.persistence.KalenderDaoImpl;

public class KalenderDetailFragment extends Fragment {

    private Kalender kalender;
    private KalenderDao kalenderDao;

    public KalenderDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getArguments();

        if (extras != null) {
            kalender = (Kalender) extras.getSerializable("kalender");
        }

        kalenderDao = new KalenderDaoImpl(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kalender_detail, container, false);

        Toast.makeText(getActivity(), kalender.getMatch() , Toast.LENGTH_LONG).show();

        return v;
    }


}
