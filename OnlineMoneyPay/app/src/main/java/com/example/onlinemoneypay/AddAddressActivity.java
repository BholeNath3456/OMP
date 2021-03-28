package com.example.onlinemoneypay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {

    private EditText editTextCity;
    private EditText editTextLocality;
    private EditText editTextFlatNo;
    private EditText editTextPincode;
    private EditText editTextState;
    private EditText editTextLandmark;
    private EditText editTextName;
    private EditText editTextMobileNo;
    private EditText editTextAlternateMobileNo;

    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Add a new address");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextCity = findViewById(R.id.city);
        editTextLocality = findViewById(R.id.locality);
        editTextFlatNo = findViewById(R.id.flat_no);
        editTextState = findViewById(R.id.state);
        editTextPincode = findViewById(R.id.pincode);
        editTextLandmark = findViewById(R.id.landmark);
        editTextName = findViewById(R.id.name);
        editTextMobileNo = findViewById(R.id.mobile_no);
        editTextAlternateMobileNo = findViewById(R.id.alternate_mobile_no);

        saveBtn = findViewById(R.id.save_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInputs();
//                                            String fullAddress=city.getText().toString()+","+locality.getText().toString()+","+flatNo.getText().toString()+","+landmark.getText().toString();
//
//                                            Map<String ,Object> addAddress=new HashMap();
//                                            addAddress.put("list_size",(long)DBqueries.addressesModelList.size()+1);
//                                            addAddress.put("fullname_"+DBqueries.addressesModelList.size()+1,name.getText().toString()+" - "+mobileNo.getText().toString());
//                                            addAddress.put("address_"+DBqueries.addressesModelList.size()+1,fullAddress);
//                                            addAddress.put("pincode_"+DBqueries.addressesModelList.size()+1,pincode.getText().toString());
//
//                                            FirebaseFirestore.getInstance().collection("USERS").document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA").document("MY_ADDRESSES")
//                                                    .update(addAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<Void> task) {
//                                                   if(task.isSuccessful()){
//
//                                                   }else {
//                                                       String error = task.getException().getMessage();
//                                                       Toast.makeText(AddAddressActivity.this, error, Toast.LENGTH_SHORT).show();
//                                                   }
//                                                }
//                                            });
            }
        });
    }

    private void checkInputs() {

        String city = editTextCity.getText().toString().trim();
        String locality=  editTextLocality.getText().toString().trim();
        String flatNo= editTextFlatNo.getText().toString().trim();
        String pincode= editTextPincode.getText().toString().trim();
        String state=editTextState.getText().toString().trim();
        String landmark= editTextLandmark.getText().toString().trim();
        String name=editTextName.getText().toString().trim();
        String mobileNo=editTextMobileNo.getText().toString().trim();
        String alternateMobileNo=editTextAlternateMobileNo.getText().toString().trim();
        if (city.isEmpty()) {
            editTextCity.setError("City Required!");
            editTextCity.setFocusable(true);
        } else if (locality.isEmpty()) {
            editTextLocality.setError("Locality Required!");
            editTextLocality.setFocusable(true);
        } else if (flatNo.isEmpty()) {
            editTextFlatNo.setError("Flat No. Required!");
            editTextFlatNo.setFocusable(true);
        } else if (pincode.isEmpty()) {
            editTextPincode.setError("Pincode required!");
            editTextPincode.setFocusable(true);
        } else if (state.isEmpty()) {
            editTextState.setError("State Required!");
            editTextState.setFocusable(true);
        } else if (name.isEmpty()) {
            editTextName.setError("Name Required!");
            editTextName.setFocusable(true);
        } else if (mobileNo.isEmpty()) {
            editTextMobileNo.setError("Mobile Required!");
            editTextMobileNo.setFocusable(true);
        } else {
            Intent deliveryIntent = new Intent(AddAddressActivity.this, DeliveryActivity.class);
            startActivity(deliveryIntent);
            finish();
        }

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}