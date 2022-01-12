package com.sohelsk.boomcash.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sohelsk.boomcash.DataModel.MessageModel;
import com.sohelsk.boomcash.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder>{

    private Context context;
    private List<MessageModel> messageList;
    private  OnItemClickListner listner;
    int counter=0;
    public MessageAdapter(Context context, List<MessageModel> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.chat_item_layout,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            MessageModel messageModel=messageList.get(position);
            holder.senderTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(counter%2==0){
                        holder.senderTimeTv.setVisibility(View.VISIBLE);
                    }else{
                        holder.senderTimeTv.setVisibility(View.GONE);
                    }
                    counter++;
                }
            });
            holder.senderImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(counter%2==0){
                        holder.senderTimeTv.setVisibility(View.VISIBLE);
                    }else{
                        holder.senderTimeTv.setVisibility(View.GONE);
                    }
                    counter++;
                }
            });
            holder.receiverTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(counter%2==0){
                        holder.receiverTimeTv.setVisibility(View.VISIBLE);
                    }else{
                        holder.receiverTimeTv.setVisibility(View.GONE);
                    }
                    counter++;
                }
            });
            holder.receiverImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(counter%2==0){
                        holder.receiverTimeTv.setVisibility(View.VISIBLE);
                    }else{
                        holder.receiverTimeTv.setVisibility(View.GONE);
                    }
                    counter++;
                }
            });



            if(messageModel.getSender().equals("user")){
                if(messageModel.getMessageType().equals("text")){
                    holder.senderTv.setVisibility(View.VISIBLE);
                    holder.senderTv.setText(""+messageModel.getMessage());
                    holder.senderTimeTv.setText(""+messageModel.getTime());
                }else if(messageModel.getMessageType().equals("image")){
                    if(!messageModel.getMessage().isEmpty()){
                        holder.senderTv.setVisibility(View.VISIBLE);
                        holder.senderTv.setText(""+messageModel.getMessage());
                    }
                    holder.senderImageView.setVisibility(View.VISIBLE);
                    holder.senderTimeTv.setText(""+messageModel.getTime());
                    Picasso.get().load(messageModel.getImageUrl())
                                .into(holder.senderImageView);

                }
            }else{
                if(messageModel.getMessageType().equals("text")) {
                    holder.receiverTv.setVisibility(View.VISIBLE);
                    holder.receiverTv.setText("" + messageModel.getMessage());
                    holder.receiverTimeTv.setText("" + messageModel.getTime());
                }else if(messageModel.getMessageType().equals("image")){
                    if(!messageModel.getMessage().isEmpty()){
                        holder.receiverTv.setVisibility(View.VISIBLE);
                        holder.receiverTv.setText(""+messageModel.getMessage());
                    }
                    holder.receiverImageView.setVisibility(View.VISIBLE);
                    holder.receiverTimeTv.setText(""+messageModel.getTime());
                    Picasso.get().load(messageModel.getImageUrl())
                            .into(holder.receiverImageView);

                }
            }





    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        TextView senderTv,senderTimeTv,receiverTv,receiverTimeTv;
        private ImageView senderImageView,receiverImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            senderTv=itemView.findViewById(R.id.message_sentTv);
            senderTimeTv=itemView.findViewById(R.id.message_SentTimeTv);
            receiverTv=itemView.findViewById(R.id.message_receiveTv);
            receiverTimeTv=itemView.findViewById(R.id.message_receive_TimeTv);
            senderImageView=itemView.findViewById(R.id.sentImageView);
            receiverImageView=itemView.findViewById(R.id.receiveImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listner!=null){
                int position=getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listner.onItemClick(position);
                }
            }
        }

    }
    public interface  OnItemClickListner{
        void onItemClick(int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner=listner;
    }


}
