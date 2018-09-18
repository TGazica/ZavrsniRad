package tomislavgazica.ferit.org.zavrsnirad.ui.table;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import tomislavgazica.ferit.org.zavrsnirad.App;
import tomislavgazica.ferit.org.zavrsnirad.R;
import tomislavgazica.ferit.org.zavrsnirad.model.Table;
import tomislavgazica.ferit.org.zavrsnirad.ui.mainActivity.OnTableListener;

public class TablePicker extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.chose_table_spinner)
    Spinner choseTableSpinner;

    private ArrayList<Table> tables = new ArrayList<>();
    private Context context;

    private OnTableListener listener;

    public void setData(List<Table> tables, Context context, OnTableListener listener){
        this.tables.addAll(tables);
        this.context = context;
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table_picker, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpSpinner();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setUpSpinner() {

        ArrayList<String> tableNumbers = new ArrayList<>();

        for (int i = 0; i < tables.size(); i++) {
            tableNumbers.add(Integer.toString(tables.get(i).getTableNumber()));
        }

        Collections.sort(tableNumbers);

        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, tableNumbers);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        choseTableSpinner.setAdapter(spinnerAdapter);
    }

    @OnClick(R.id.chose_table_confirm)
    public void setTable() {

        int tableNumber = Integer.parseInt(choseTableSpinner.getSelectedItem().toString());

        for (int i = 0; i < tables.size(); i++) {
            if (tables.get(i).getTableNumber() == tableNumber) {
                App.setTable(tables.get(i));
                break;
            }
        }

        listener.tableSet();

    }

}
