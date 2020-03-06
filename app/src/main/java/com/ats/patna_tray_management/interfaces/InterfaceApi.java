package com.ats.patna_tray_management.interfaces;

import com.ats.patna_tray_management.model.Driver;
import com.ats.patna_tray_management.model.ErrorMessage;
import com.ats.patna_tray_management.model.FrTrayCount;
import com.ats.patna_tray_management.model.FranchiseByRoute;
import com.ats.patna_tray_management.model.Info;
import com.ats.patna_tray_management.model.RouteListData;
import com.ats.patna_tray_management.model.TrayMgmtDetailData;
import com.ats.patna_tray_management.model.TrayMgmtHeaderData;
import com.ats.patna_tray_management.model.TrayMgmtHeaderDisplayList;
import com.ats.patna_tray_management.model.Vehicle;
import com.ats.patna_tray_management.model.VehicleInTrayStatus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by MAXADMIN on 17/2/2018.
 */

public interface InterfaceApi {

    @GET("showRouteList")
    Call<RouteListData> getAllRouteList();

    @GET("getAllVehicalList")
    Call<ArrayList<Vehicle>> getAllVehicleList();

    @GET("getAllDriverList")
    Call<ArrayList<Driver>> getAllDriverList();

    @POST("traymgt/getFranchiseInRoute")
    Call<ArrayList<FranchiseByRoute>> getAllFranchiseByRoute(@Query("routeId") int routeId, @Query("tranId") int tranId);

    @POST("traymgt/saveTrayMgtHeader")
    Call<TrayMgmtHeaderData> saveTrayMgmtHeader(@Body TrayMgmtHeaderData trayMgmtHeaderData);

    @POST("traymgt/getOutTraysForFr")
    Call<ArrayList<FrTrayCount>> getFrTrayCount(@Query("frId") int frId, @Query("billDate") String billDate);

    @POST("traymgt/saveTrayMgtDetail")
    Call<Info> saveTrayMgmtDetail(@Body TrayMgmtDetailData trayMgmtDetailData);

    @POST("traymgt/getTrayMgtDetailByTranId")
    Call<ArrayList<TrayMgmtDetailData>> getTrayMgmtDetailByHeaderId(@Query("tranId") int tranId);

    @POST("traymgt/getTrayMgtDetailsByTranId")
    Call<ArrayList<VehicleInTrayStatus>> getTrayMgmtDetailByHeaderIdForIn(@Query("tranId") int tranId);

    @POST("traymgt/getTrayMgtDetailsByTranIdAndDate")
    Call<ArrayList<VehicleInTrayStatus>> getTrayMgmtDetailByHeaderIdAndDateForIn(@Query("tranId") int tranId, @Query("date") String date);

    @POST("traymgt/getLoadedVehicles")
    Call<ArrayList<TrayMgmtHeaderDisplayList>> getTrayMgmtHeadersByDateAndStatus(@Query("date") String date, @Query("vehStatus") int vehStatus);

    @POST("traymgt/updateOutVehicleData")
    Call<Info> updateVehicleOutData(@Query("tranId") int tranId, @Query("vehOutkm") float vehOutkm);

    @POST("traymgt/updateInVehicleData")
    Call<Info> updateVehicleInData(@Query("tranId") int tranId, @Query("vehInkm") float vehInkm, @Query("extraTrayIn") int extraTrayIn);

    @POST("traymgt/updateExtraOutTrays")
    Call<Info> updateVehicleOutTray(@Query("tranId") int tranId, @Query("extraOutTrays") int extraOutTrays);

    @POST("traymgt/updateDieselOfVehicle")
    Call<Info> updateDiesel(@Query("tranId") int tranId, @Query("diesel") float diesel);

    @POST("traymgt/getAllVehicles")
    Call<ArrayList<TrayMgmtHeaderDisplayList>> getAllVehicleList(@Query("date") String date);

    @POST("traymgt/getTrayMgtHeader")
    Call<TrayMgmtHeaderDisplayList> getHeaderById(@Query("tranId") int tranId);

    @POST("traymgt/getLoadedVehiclesByStatus")
    Call<ArrayList<TrayMgmtHeaderDisplayList>> getTrayMgmtHeadersByStatus(@Query("vehStatus") int vehStatus);

    @POST("traymgt/deleteTrayMgtHeader")
    Call<ErrorMessage> deleteVehicleHeaderAndDetail(@Query("tranId") int tranId);

    @GET("traymgt/getServerDate")
    Call<Info> getServerDate();

}
