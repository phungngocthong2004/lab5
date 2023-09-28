package Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luyentapchoi.R;

import java.util.ArrayList;

import Adapter.svAdapter;
import DAO.SinhvienDao;
import DTO.Sinhvien_DTO;

public class fragment_sv extends Fragment {
    SinhvienDao dao;
    ArrayList<Sinhvien_DTO>listt;
    svAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return  inflater.inflate(R.layout.frament_sv,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnthem=view.findViewById(R.id.btnthem);
        RecyclerView rc_sv=view.findViewById(R.id.rc_sv);
        EditText edten=view.findViewById(R.id.edten);
        EditText edtuoi=view.findViewById(R.id.edtuoi);

        dao=new SinhvienDao(getContext());
        listt=dao.gettAll();
        adapter=new svAdapter(getContext(),listt);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc_sv.setAdapter(adapter);
        rc_sv.setLayoutManager(linearLayoutManager);

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten =edten.getText().toString();
                int tuoi= Integer.parseInt(edtuoi.getText().toString());
                dao=new SinhvienDao(getContext());
                Sinhvien_DTO dto=new Sinhvien_DTO(ten,tuoi);
                long id=dao.add(dto);
                if (id>0){
                    Toast.makeText(getContext(), "themthanh cong", Toast.LENGTH_SHORT).show();
                    listt.clear();
                    listt.addAll(dao.gettAll());
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "them that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
