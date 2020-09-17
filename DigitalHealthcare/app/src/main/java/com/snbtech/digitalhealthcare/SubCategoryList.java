package com.snbtech.digitalhealthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubCategoryList extends AppCompatActivity {

    ListView lv;
    public static int pos=0;

    String heart[]={"Arrhythmia_Treatment","Aneurysm_Repair","Ventricular_Assist_Devices","Heart_Transplant"};
    String brain[]={"Biopsy","Craniotomy","MRI","Neuroendoscopy","Endonasal_Endoscopy"};
    String eye[]={"Lasik","Prk","Laser","Rle","Prelex"};
    String hair[]={"Strip_Harresting","Follicular_Unit_Extraction","Follicular_Unit_Transplant","Hair_Plantation"};
    String dentist[]={"Endodontist","Prosthodontist","Oral_Maxillofacial"};
    String cancer[]={"Blood","Skin","Mouth","Lungs"};
    String handicap[]={"Cognitive","Hearing_Loss","Vision_Loss","Invisible_Disability"};
    String eair[]={"Stapendectomy","Tympanoplasty","Myringotomy"};
    String bones[]={"Crack","Gap"};
    String stomach[]={"Roux_en_Y_Gastric_Bypass","Sleeve_gastrectomy","Duodenal_Swith_with_Biliopancreatic_Divesion"};





    public static String subcat;
    int mlcnt=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_list);

        mlcnt=Category.listcnt;

        lv=(ListView)findViewById(R.id.lv);

        if(mlcnt==1) {
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, heart);
            lv.setAdapter(arrayAdapter);
        }else if(mlcnt==2) {
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, brain);
            lv.setAdapter(arrayAdapter);
        }else if(mlcnt==3) {
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eye);
            lv.setAdapter(arrayAdapter);
        }else if(mlcnt==4) {
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hair);
            lv.setAdapter(arrayAdapter);
        }else if(mlcnt==5) {
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dentist);
            lv.setAdapter(arrayAdapter);
        }else if(mlcnt==6) {
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cancer);
            lv.setAdapter(arrayAdapter);
        }else if(mlcnt==7) {
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, handicap);
            lv.setAdapter(arrayAdapter);
        }else if(mlcnt==8) {
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eair);
            lv.setAdapter(arrayAdapter);
        }
        else if(mlcnt==9) {
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bones);
            lv.setAdapter(arrayAdapter);
        }else if(mlcnt==10) {
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stomach);
            lv.setAdapter(arrayAdapter);
        }





        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                subcat=parent.getItemAtPosition(position).toString();

                Intent i=new Intent(getApplicationContext(),AddRate.class);
                startActivity(i);


            }
        });

    }
}
