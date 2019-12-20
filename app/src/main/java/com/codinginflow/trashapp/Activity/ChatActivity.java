package com.codinginflow.trashapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codinginflow.trashapp.Adapter.ChatAdapter;
import com.codinginflow.trashapp.Adapter.PengepulAdapter;
import com.codinginflow.trashapp.R;
import com.codinginflow.trashapp.model.Chat;
import com.codinginflow.trashapp.model.Pengepul;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    Toolbar toolbar;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    Intent intent;

    CircleImageView profile_image;
    TextView username;
    ImageButton btn_send;
    EditText sendText;

    ChatAdapter chatAdapter;
    ArrayList<Chat> chats;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbar = findViewById(R.id.al_chat_toolbar);
        setToolbar(toolbar);
        profile_image = findViewById(R.id.iv_chat_gambar);
        username = findViewById(R.id.tv_chat_nama);
        btn_send = findViewById(R.id.ib_chat_send);
        sendText = findViewById(R.id.et_chat_text);

        recyclerView = findViewById(R.id.rv_chat_message);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        intent = getIntent();
        final String namaid = intent.getStringExtra("pengepulid");
        Log.i("pengepulid",namaid);

        readPengepul(namaid);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = sendText.getText().toString();
                if(!message.equals("")){
                    sendMessage(user.getUid(),namaid,message);
                }else{
                    Toast.makeText(ChatActivity.this,"You can't send Empty message",Toast.LENGTH_LONG).show();
                }
                sendText.setText("");
            }
        });
    }

    public void setToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void readPengepul(final String namaid){
        databaseReference.child("Pengepul").child(namaid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Pengepul pgp = dataSnapshot.getValue(Pengepul.class);
                String nama = dataSnapshot.child("nama").getValue().toString();
                username.setText(nama);
                readMessage(user.getUid(),namaid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendMessage(String sender , String receiver , String msg){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",msg);

        databaseReference.child("Chat").push().setValue(hashMap);
    }

    private void readMessage(final String Idsender , final String Idreceiver){
        chats = new ArrayList<>();

        databaseReference.child("Chat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chats = new ArrayList<>();
                for (DataSnapshot d:dataSnapshot.getChildren()) {
                    String idSender = d.child("sender").getValue().toString();
                    String idreceiver = d.child("receiver").getValue().toString();

                    //Chat chat = d.getValue(Chat.class);
                    if(idreceiver.equals(Idreceiver) && idSender.equals(Idsender) ||
                        idreceiver.equals(Idsender) && idSender.equals(Idreceiver)){
                            Chat chat = new Chat(idSender,idreceiver);
                            chats.add(chat);
                        }
                        chatAdapter = new ChatAdapter(ChatActivity.this,chats);
                        recyclerView.setAdapter(chatAdapter);
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
