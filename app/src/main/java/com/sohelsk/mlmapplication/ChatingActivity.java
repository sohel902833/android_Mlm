package com.sohelsk.mlmapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sohelsk.mlmapplication.Adapter.MessageAdapter;
import com.sohelsk.mlmapplication.DataModel.MessageModel;
import com.sohelsk.mlmapplication.DataModel.User;
import com.sohelsk.mlmapplication.LocalDb.UserDb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ChatingActivity extends AppCompatActivity {

    String message="";
    private RecyclerView recyclerView;
    private ImageView fileSelect,sendButton;
    private EditText messageEt;
    private DatabaseReference messageRef,conversationRef;
    private UserDb userDb;
    private ProgressDialog progressDialog;
    private List<MessageModel> messageList=new ArrayList<>();
    private MessageAdapter messageAdapter;

    FirebaseStorage storage;
    Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating);
        init();

        storage = FirebaseStorage.getInstance();

        messageRef=FirebaseDatabase.getInstance().getReference().child("Messages");
        conversationRef=FirebaseDatabase.getInstance().getReference().child("Conversation");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 message=messageEt.getText().toString();
                if(message.isEmpty()){
                    if(selectedImage==null){
                        Toast.makeText(ChatingActivity.this, "Write Something.", Toast.LENGTH_SHORT).show();

                    }else{
                        sendMessage(message);
                    }
                }else{
                    sendMessage(message);
                }
            }
        });

        fileSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 45);
            }
        });


    }

    private  void init(){
        progressDialog=new ProgressDialog(this);
        userDb=new UserDb(this);
        recyclerView=findViewById(R.id.chatRecyclerViewid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fileSelect=findViewById(R.id.fileImageViewid);
        sendButton=findViewById(R.id.sendMessageButton);
        messageEt=findViewById(R.id.message_box);


    }
    @Override
    protected void onStart() {
        super.onStart();
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        messageRef.child(""+userDb.getUserData().getUserId())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            messageList.clear();
                            for(DataSnapshot snapshot1:snapshot.getChildren()){
                                MessageModel message=snapshot1.getValue(MessageModel.class);
                                messageList.add(message);
                            }
                            messageAdapter=new MessageAdapter(ChatingActivity.this,messageList);
                            recyclerView.setAdapter(messageAdapter);
                            recyclerView.smoothScrollToPosition(messageList.size());
                            progressDialog.dismiss();
                        }else{
                            progressDialog.dismiss();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog.dismiss();
                    }
                });




        HashMap<String,Object> cnvMap=new HashMap<>();
        cnvMap.put("name",userDb.getUserData().getName());
        cnvMap.put("phone",userDb.getUserData().getPhone());
        cnvMap.put("userId",userDb.getUserData().getUserId());
        conversationRef.child(""+userDb.getUserData().getUserId())
                    .updateChildren(cnvMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(ChatingActivity.this, "Error..", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
    }
    private  void sendMessage(String message){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy, HH:mm:ss", Locale.getDefault());
        String formattedDate = df.format(c);



        String messageId=messageRef.push().getKey()+System.currentTimeMillis();
        HashMap<String,Object> messageMap=new HashMap<>();
        messageMap.put("message",message);
        messageMap.put("messageId",messageId);
        messageMap.put("time",formattedDate);
        messageMap.put("sender","user");


        if(selectedImage==null){
            messageMap.put("imageUrl","");
            messageMap.put("messageType","text");
            messageRef.child(""+userDb.getUserData().getUserId())
                    .child(messageId)
                    .updateChildren(messageMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                messageEt.setText("");
                            }else{
                                Toast.makeText(ChatingActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            progressDialog.setMessage("Sending Image...");
            progressDialog.show();
            StorageReference storageReference = storage.getReference().child("Profiles").child(System.currentTimeMillis()+""+userDb.getUserData().getUserId()+""+".jpg");
            storageReference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imageUri = uri.toString();
                            messageMap.put("imageUrl",imageUri);
                            messageMap.put("messageType","image");
                            messageRef.child(""+userDb.getUserData().getUserId())
                                    .child(messageId)
                                    .updateChildren(messageMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                messageEt.setText("");
                                                progressDialog.dismiss();
                                            }else{
                                                progressDialog.dismiss();
                                                Toast.makeText(ChatingActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });




                        }
                    });
                }
            });
        }










    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (data.getData() != null) {
                selectedImage = data.getData();
            }
        }
    }

}