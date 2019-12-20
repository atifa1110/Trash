package com.codinginflow.trashapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codinginflow.trashapp.R;
import com.codinginflow.trashapp.model.Chat;
import com.codinginflow.trashapp.model.Pengepul;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    public static  final int MSG_TITLE_LEFT =0;
    public static  final int MSG_TITLE_RIGHT =1;

    Context context;
    ArrayList<Chat> chats;

    FirebaseUser user;

    public ChatAdapter(Context context, ArrayList<Chat> chats){
        this.context = context;
        this.chats = chats;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TITLE_RIGHT){
            View view = LayoutInflater.from(context).inflate(R.layout.item_chat_right,parent,false);
            return new ChatAdapter.MyViewHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.item_chat_left,parent,false);
            return new ChatAdapter.MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Chat chat = chats.get(position);

        holder.message.setText(chat.getMessage());

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView message;
        public ImageView profile;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            message= itemView.findViewById(R.id.tv_chat_showmsg);
            profile = itemView.findViewById(R.id.civ_chat_image);

        }
    }

    @Override
    public int getItemViewType(int position) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(chats.get(position).getSender().equals(user.getUid())){
            return MSG_TITLE_RIGHT;
        }else{
            return MSG_TITLE_LEFT;
        }
    }
}
