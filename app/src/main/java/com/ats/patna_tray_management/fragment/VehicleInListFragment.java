package com.ats.patna_tray_management.fragment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.patna_tray_management.R;
import com.ats.patna_tray_management.activity.VehicleStatusActivity;
import com.ats.patna_tray_management.adapter.VehicleInListAdapter;
import com.ats.patna_tray_management.common.CommonDialog;
import com.ats.patna_tray_management.constants.Constants;
import com.ats.patna_tray_management.model.TrayMgmtHeaderDisplayList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleInListFragment extends Fragment {

    private RecyclerView rvVehicleList;
    VehicleInListAdapter adapter;

    int yyyy, mm, dd;
    long dateMillis;

    private ArrayList<TrayMgmtHeaderDisplayList> headerDataArrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_in_list, container, false);

        rvVehicleList = view.findViewById(R.id.rvVehicleInList);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String todaysDate = sdf.format(cal.getTimeInMillis());

        getAllTrayMgmtHeaders(1);

        setHasOptionsMenu(true);

        return view;
    }

    /*public void getAllTrayMgmtHeaders(String date, int status) {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getActivity(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<TrayMgmtHeaderDisplayList>> headersByDateAndStatus = Constants.myInterface.getTrayMgmtHeadersByDateAndStatus(date, status);
            headersByDateAndStatus.enqueue(new Callback<ArrayList<TrayMgmtHeaderDisplayList>>() {
                @Override
                public void onResponse(Call<ArrayList<TrayMgmtHeaderDisplayList>> call, Response<ArrayList<TrayMgmtHeaderDisplayList>> response) {
                    try {
                        if (response.body() != null) {
                            ArrayList<TrayMgmtHeaderDisplayList> data = response.body();
                            commonDialog.dismiss();
                            headerDataArrayList = data;

                            adapter = new VehicleInListAdapter(headerDataArrayList, getContext());
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            rvVehicleList.setLayoutManager(mLayoutManager);
                            rvVehicleList.setItemAnimator(new DefaultItemAnimator());
                            rvVehicleList.setAdapter(adapter);

                        } else {
                            commonDialog.dismiss();
                            Log.e("getAllTrayMgmtHeaders", " NULL-----");
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Log.e("getAllTrayMgmtHeaders", " Exception-----" + e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<TrayMgmtHeaderDisplayList>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("getAllTrayMgmtHeaders", " OnFailure-----" + t.getMessage());
                }
            });

        } else {
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }*/

    public void getAllTrayMgmtHeaders(int status) {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getActivity(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<TrayMgmtHeaderDisplayList>> headersByDateAndStatus = Constants.myInterface.getTrayMgmtHeadersByStatus(status);
            headersByDateAndStatus.enqueue(new Callback<ArrayList<TrayMgmtHeaderDisplayList>>() {
                @Override
                public void onResponse(Call<ArrayList<TrayMgmtHeaderDisplayList>> call, Response<ArrayList<TrayMgmtHeaderDisplayList>> response) {
                    try {
                        if (response.body() != null) {
                            ArrayList<TrayMgmtHeaderDisplayList> data = response.body();
                            commonDialog.dismiss();
                            headerDataArrayList.clear();
                            headerDataArrayList = data;

                            adapter = new VehicleInListAdapter(headerDataArrayList, getContext());
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            rvVehicleList.setLayoutManager(mLayoutManager);
                            rvVehicleList.setItemAnimator(new DefaultItemAnimator());
                            rvVehicleList.setAdapter(adapter);

                        } else {
                            commonDialog.dismiss();
                            Log.e("getAllTrayMgmtHeaders", " NULL-----");
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Log.e("getAllTrayMgmtHeaders", " Exception-----" + e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<TrayMgmtHeaderDisplayList>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("getAllTrayMgmtHeaders", " OnFailure-----" + t.getMessage());
                }
            });

        } else {
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void getAllTrayMgmtHeaders(int status, final int isSameDay) {
        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getActivity(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<TrayMgmtHeaderDisplayList>> headersByDateAndStatus = Constants.myInterface.getTrayMgmtHeadersByStatus(status);
            headersByDateAndStatus.enqueue(new Callback<ArrayList<TrayMgmtHeaderDisplayList>>() {
                @Override
                public void onResponse(Call<ArrayList<TrayMgmtHeaderDisplayList>> call, Response<ArrayList<TrayMgmtHeaderDisplayList>> response) {
                    try {
                        if (response.body() != null) {
                            ArrayList<TrayMgmtHeaderDisplayList> data = response.body();
                            commonDialog.dismiss();
                            headerDataArrayList.clear();
                            //headerDataArrayList = data;

                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    if (data.get(i).getIsSameDay() == isSameDay) {
                                        headerDataArrayList.add(data.get(i));
                                    }
                                }
                            }

                            adapter = new VehicleInListAdapter(headerDataArrayList, getContext());
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            rvVehicleList.setLayoutManager(mLayoutManager);
                            rvVehicleList.setItemAnimator(new DefaultItemAnimator());
                            rvVehicleList.setAdapter(adapter);

                        } else {
                            commonDialog.dismiss();
                            Log.e("getAllTrayMgmtHeaders", " NULL-----");
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Log.e("getAllTrayMgmtHeaders", " Exception-----" + e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<TrayMgmtHeaderDisplayList>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("getAllTrayMgmtHeaders", " OnFailure-----" + t.getMessage());
                }
            });

        } else {
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.home, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.action_filter);
        item.setVisible(true);

        MenuItem item1 = menu.findItem(R.id.action_vehicle_status);
        item1.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                filterDialog();
                return true;

            case R.id.action_vehicle_status:
                startActivity(new Intent(getContext(), VehicleStatusActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void dateFilterDialog() {
        final Dialog openDialog = new Dialog(getActivity());
        openDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        openDialog.setContentView(R.layout.custom_date_filter_dialog);
        openDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        Window window = openDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.dimAmount = 0.75f;
        wlp.gravity = Gravity.TOP | Gravity.RIGHT;
        wlp.x = 100;
        wlp.y = 100;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        EditText edDate = openDialog.findViewById(R.id.edCustomFilter_Date);
        TextView tvSubmit = openDialog.findViewById(R.id.tvCustomFilter_Submit);


        openDialog.show();
    }


    public class showDateDialog extends Dialog {

        EditText edDate;
        TextView tvDate, tvSubmit;

        public showDateDialog(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            // setTitle("Filter");
            setContentView(R.layout.custom_date_filter_dialog);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

            Window window = getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.dimAmount = 0.75f;
            wlp.gravity = Gravity.TOP | Gravity.RIGHT;
            wlp.x = 100;
            wlp.y = 100;
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(wlp);

            edDate = findViewById(R.id.edCustomFilter_Date);
            tvDate = findViewById(R.id.tvCustomFilter_Date);
            tvSubmit = findViewById(R.id.tvCustomFilter_Submit);

            edDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int yr, mn, dy;
                    if (dateMillis > 0) {
                        Calendar purchaseCal = Calendar.getInstance();
                        purchaseCal.setTimeInMillis(dateMillis);
                        yr = purchaseCal.get(Calendar.YEAR);
                        mn = purchaseCal.get(Calendar.MONTH);
                        dy = purchaseCal.get(Calendar.DAY_OF_MONTH);
                    } else {
                        Calendar purchaseCal = Calendar.getInstance();
                        yr = purchaseCal.get(Calendar.YEAR);
                        mn = purchaseCal.get(Calendar.MONTH);
                        dy = purchaseCal.get(Calendar.DAY_OF_MONTH);
                    }
                    DatePickerDialog dialog = new DatePickerDialog(getActivity(), dateListener, yr, mn, dy);
                    dialog.show();
                }
            });


            tvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (edDate.getText().toString().isEmpty()) {
                        edDate.setError("Select Date");
                        edDate.requestFocus();
                    } else {
                        dismiss();

                        String selectedDate = tvDate.getText().toString();
                        getAllTrayMgmtHeaders(1);

                    }
                }
            });
        }

        DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                yyyy = year;
                mm = month + 1;
                dd = dayOfMonth;
                edDate.setText(dd + "-" + mm + "-" + yyyy);
                tvDate.setText(yyyy + "-" + mm + "-" + dd);

                Calendar calendar = Calendar.getInstance();
                calendar.set(yyyy, mm - 1, dd);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.HOUR, 0);
                dateMillis = calendar.getTimeInMillis();
            }
        };

    }

    public void filterDialog() {
        final Dialog openDialog = new Dialog(getContext());
        openDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        openDialog.setContentView(R.layout.custom_is_same_day_filter_dialog_layout);
        openDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        Window window = openDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.dimAmount = 0.75f;
        wlp.gravity = Gravity.TOP | Gravity.RIGHT;
        wlp.x = 100;
        wlp.y = 100;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        final RadioButton rbAll = openDialog.findViewById(R.id.rbFilter_All);
        final RadioButton rbRegular = openDialog.findViewById(R.id.rbFilter_Regular);
        final RadioButton rbIsSameDay = openDialog.findViewById(R.id.rbFilter_isSameDay);
        TextView tvSearch = openDialog.findViewById(R.id.tvFilter_Search);


        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbAll.isChecked()) {
                    getAllTrayMgmtHeaders(1);
                    openDialog.dismiss();
                } else if (rbRegular.isChecked()) {
                    getAllTrayMgmtHeaders(1, 0);
                    openDialog.dismiss();
                } else if (rbIsSameDay.isChecked()) {
                    getAllTrayMgmtHeaders(1, 1);
                    openDialog.dismiss();
                }

            }
        });

        openDialog.show();
    }

}
