package com.bawei6.usermodule.bottonbar.lianxiren;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.common.LogUtils;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.adapter.RecyUserLianxiPhoneAdapter;
import com.bawei6.usermodule.bean.MyPhone;

import java.util.ArrayList;
import java.util.List;

public class AddLianxirenActivity extends AppCompatActivity {

    private EditText edi_name;
    private Button btn_find;
    private RecyclerView recyclerView;
    private ListView listView;

    private MyPhone myPhone;

    private List<MyPhone> phoneList=new ArrayList<>();
    private List<String> numList=new ArrayList<>();
    private Uri uri;
    Cursor query;

    private RecyUserLianxiPhoneAdapter recyUserLianxiPhoneAdapter;
    private ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lianxiren);

        edi_name=findViewById(R.id.addlian_edi);
        btn_find=findViewById(R.id.addlian_btn);
        recyclerView=findViewById(R.id.user_addlian_recy);
        listView=findViewById(R.id.user_addlian_list);

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i <str.length(); i ++){
            //转成Char类型
            numList.add(str.charAt(i)+"");
        }
        recyUserLianxiPhoneAdapter=new RecyUserLianxiPhoneAdapter(this,phoneList);
        recyclerView.setAdapter(recyUserLianxiPhoneAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,numList);
        listView.setAdapter(arrayAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String count = numList.get(i);
                for (int j = 0; j <phoneList.size()-1 ; j++) {
                    if(count==phoneList.get(i).getPhonebook_label()){
                        recyclerView.scrollToPosition(i);
                        arrayAdapter.notifyDataSetChanged();
                        recyUserLianxiPhoneAdapter.notifyDataSetChanged();
                    }
                }
            }
        });





        try {
            uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            ContentResolver contentResolver =getContentResolver();
             query = contentResolver.query(uri, null, null, null, "phonebook_label");
            String letter="";
            while (query.moveToNext()) {
                String name = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String phonebook_label = query.getString(query.getColumnIndex("phonebook_label"));
                myPhone=new MyPhone(name,phone,phonebook_label,1);
                recyUserLianxiPhoneAdapter.notifyDataSetChanged();

                if(letter.equals(phonebook_label)){
                    phoneList.add(myPhone);
                    recyUserLianxiPhoneAdapter.notifyDataSetChanged();
                }else{
                    phoneList.add(new MyPhone(null,null,phonebook_label,0));
                    phoneList.add(myPhone);
                    letter=phonebook_label;
                    recyUserLianxiPhoneAdapter.notifyDataSetChanged();
                }
            }
        }catch (Exception e){
            LogUtils.i(e.getMessage());
        }finally {
            query.close();
        }




    }
}
