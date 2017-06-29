package freewilder.rockme.com.freewilder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.adapters.ProfileCategoryAdapter;
import freewilder.rockme.com.freewilder.pojo.SetGetProfileCategoryWiseData;
import freewilder.rockme.com.freewilder.pojo.SetgetJsonArrayProfileCategory;

/**
 * Created by su on 5/3/17.
 */

public class ProfileServiceFragment extends Fragment {
    ArrayList<SetGetProfileCategoryWiseData> setGetProfileCategoryWiseDataArrayList;
    ArrayList<SetgetJsonArrayProfileCategory> jsonArrayProfileCategoryArrayList;
    RecyclerView rcv_profile_category;
    ProfileCategoryAdapter profileCategoryAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_service, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcv_profile_category = (RecyclerView) view.findViewById(R.id.rcv_profile_category);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rcv_profile_category.setLayoutManager(mLayoutManager);
        rcv_profile_category.setItemAnimator(new DefaultItemAnimator());

        setGetProfileCategoryWiseDataArrayList = new ArrayList<>();
        jsonArrayProfileCategoryArrayList = new ArrayList<>();


        createList();
    }

    public void createList() {
        try {

            JSONArray jsonArray = createJson();

            for (int i = 0; i < jsonArray.length(); i++) {

                SetgetJsonArrayProfileCategory setgetJsonArrayProfileCategory = new SetgetJsonArrayProfileCategory();

                setgetJsonArrayProfileCategory.setHeader(jsonArray.getJSONObject(i).getString("type"));
                setgetJsonArrayProfileCategory.setJsonArray(jsonArray.getJSONObject(i).getJSONArray("data"));

                jsonArrayProfileCategoryArrayList.add(setgetJsonArrayProfileCategory);

                for (int j = 0; j < jsonArray.getJSONObject(i).getJSONArray("data").length(); j++) {

                    if (j == 10) {
                        break;
                    }

                    SetGetProfileCategoryWiseData profileSubCategory = new SetGetProfileCategoryWiseData();

                    profileSubCategory.setNthPosition(j);

                    if (j == 0) {
                        profileSubCategory.setHeader(true);
                    } else {
                        profileSubCategory.setHeader(false);
                    }


                    if (j == 9) {
                        profileSubCategory.setFooter(true);
                    } else {
                        profileSubCategory.setFooter(false);
                    }


                    profileSubCategory.setCategoryType(jsonArray.getJSONObject(i).getString("type"));
                    profileSubCategory.setJsonObject(jsonArray.getJSONObject(i).getJSONArray("data").getJSONObject(j));


                    setGetProfileCategoryWiseDataArrayList.add(profileSubCategory);
                }

            }

            Log.i("sub category", "" + setGetProfileCategoryWiseDataArrayList.size());

            for (int p = 0; p < setGetProfileCategoryWiseDataArrayList.size(); p++) {
                Log.i("isHeader[" + p + "]", "" + setGetProfileCategoryWiseDataArrayList.get(p).isHeader());
                Log.i("isFooter[" + p + "]", "" + setGetProfileCategoryWiseDataArrayList.get(p).isFooter());
                Log.i("///////////////", "//////////////////////");
            }

            profileCategoryAdapter = new ProfileCategoryAdapter(getActivity(), ProfileServiceFragment.this, setGetProfileCategoryWiseDataArrayList);
            rcv_profile_category.setAdapter(profileCategoryAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public JSONArray createJson() {

        JSONArray jsonArrayMain = new JSONArray();
        try {
            for (int i = 0; i <10; i++) {

                JSONObject jsonCategoryObject = new JSONObject();

                jsonCategoryObject.put("type", "category " + i);

                JSONArray jsonTypeArrayData = new JSONArray();

                for (int p = 0; p < 25; p++) {

                    JSONObject jsonCategoryObjectData = new JSONObject();
                    jsonCategoryObjectData.put("name", "Product Name " + p);
                    jsonCategoryObjectData.put("price", "PLN 790");
                    jsonCategoryObjectData.put("address", "Drobin Poland");
                    jsonCategoryObjectData.put("rate", "5");

                    jsonTypeArrayData.put(jsonCategoryObjectData);
                }
                jsonCategoryObject.put("data", jsonTypeArrayData);
                jsonArrayMain.put(jsonCategoryObject);
            }

            //Log.i("jsonArrayMain", "" + jsonArrayMain);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return jsonArrayMain;
    }

    public void insertDataIntoListFromAdapter(String cateType, int takeFromNthPos, int insertListPos) {

        ArrayList<SetGetProfileCategoryWiseData> localAddList = new ArrayList<>();

        ArrayList<SetGetProfileCategoryWiseData> localAddListFinal = new ArrayList<>();

        for (int i = 0; i < jsonArrayProfileCategoryArrayList.size(); i++) {
            if (jsonArrayProfileCategoryArrayList.get(i).getHeader().equals(cateType)) {
                //Log.i("categoryType", jsonArrayProfileCategoryArrayList.get(i).getHeader());
                int count = 0;

                for (int j = takeFromNthPos+1; j < jsonArrayProfileCategoryArrayList.get(i).getJsonArray().length(); j++) {

                    try {
                        //Log.i("name taken", "" + jsonArrayProfileCategoryArrayList.get(i).getJsonArray().getJSONObject(j).getString("name"));
                        if (count == 10) {
                            break;
                        }

                        SetGetProfileCategoryWiseData profileSubCategory = new SetGetProfileCategoryWiseData();

                        profileSubCategory.setNthPosition(j);
                        profileSubCategory.setHeader(false);

                        if (count == 9) {
                            profileSubCategory.setFooter(true);
                        } else {
                            profileSubCategory.setFooter(false);
                        }

                        profileSubCategory.setCategoryType(jsonArrayProfileCategoryArrayList.get(i).getHeader());
                        profileSubCategory.setJsonObject(jsonArrayProfileCategoryArrayList.get(i).getJsonArray().getJSONObject(j));

                        localAddList.add(profileSubCategory);

                        //setGetProfileCategoryWiseDataArrayList.add(profileSubCategory);
                        //profileCategoryAdapter.notifyDataSetChanged();
                        count++;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("localAddList size", "" + localAddList.size());

                for (int q = 0; q < localAddList.size(); q++) {
                    Log.i("local jsonObject", "" + localAddList.get(q).getJsonObject());
                }


                for (int G = 0; G < insertListPos; G++) {
                    localAddListFinal.add(setGetProfileCategoryWiseDataArrayList.get(G));
                }
                for (int G = 0; G < localAddList.size(); G++) {
                    localAddListFinal.add(localAddList.get(G));
                }
                for (int G = insertListPos; G < setGetProfileCategoryWiseDataArrayList.size(); G++) {
                    localAddListFinal.add(setGetProfileCategoryWiseDataArrayList.get(G));
                }
//                for (int G = insertListPos; G < setGetProfileCategoryWiseDataArrayList.size(); G++) {
//                    localAddListFinal.add(localAddList.get(G));
//                }
            }
        }
        setGetProfileCategoryWiseDataArrayList.clear();
        setGetProfileCategoryWiseDataArrayList.addAll(localAddListFinal);
        profileCategoryAdapter.notifyDataSetChanged();
        for (int q = 0; q < localAddListFinal.size(); q++) {
            Log.i("jsonObject and category", "" + localAddListFinal.get(q).getJsonObject()+" "+localAddListFinal.get(q).getCategoryType());
        }
    }
}
