package com.sohelsk.boomcash;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.sohelsk.boomcash.LocalDb.UserDb;

public class InviteFriendsActivity extends AppCompatActivity {

    private TextView referCodeTextView,referLinkTextView;
    private Button referCodeBtn,referLinkBtn;
    private UserDb userDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);
        init();


        referCodeTextView.setText("Refer Code : "+userDb.getUserData().getMyReferCode());
        referLinkTextView.setText(("https://boomcashgold.com/register?referCode="+userDb.getUserData().getMyReferCode()));
        referCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyText(""+userDb.getUserData().getMyReferCode());
            }
        });

        referLinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyText(referLinkTextView.getText().toString());
            }
        });


    }

    private  void  init(){
        userDb=new UserDb(this);
        referCodeTextView=findViewById(R.id.referCodeTVId);
        referLinkTextView=findViewById(R.id.referLinkTvId);
        referCodeBtn=findViewById(R.id.referCodeBtn);
        referLinkBtn=findViewById(R.id.referLikBtn);

        Toolbar toolbar = findViewById(R.id.AppBarId);

        ImageView backButton = (ImageView)toolbar.findViewById(R.id.backBtn);
        TextView appBarTv=toolbar.findViewById(R.id.mainTvId);
        TextView tv2=toolbar.findViewById(R.id.layoutTextId);
        tv2.setVisibility(View.GONE);
        appBarTv.setText("Invite Friends");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

    private void copyText(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("referCode", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(InviteFriendsActivity.this, "Text Copied to Clipboard.", Toast.LENGTH_SHORT).show();
    }



}