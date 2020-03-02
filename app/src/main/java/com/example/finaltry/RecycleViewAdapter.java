package com.example.finaltry;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.finaltry.DBHelper.COLUMN_FNAME;
import static com.example.finaltry.DBHelper.COLUMN_GENDER;
import static com.example.finaltry.DBHelper.COLUMN_ID;
import static com.example.finaltry.DBHelper.COLUMN_PASSWORD;
import static com.example.finaltry.DBHelper.COLUMN_USERNAME;
import static com.example.finaltry.DBHelper.User_Table;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    public Context context;
    private Cursor cursor;

   public RecycleViewAdapter(Context context, Cursor cursor){
        this.context=context;
        this.cursor=cursor;
   }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_inflated,parent,false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        if(!cursor.moveToPosition(position)){
            return;
        }
        String usrname=cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
        String mail=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EMAIL));
        String date=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TIMESTAMP));
        final long id=cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_ID));

        holder.userName.setText(usrname);
        holder.itemView.setTag(id);
        holder.email.setText(mail);
        holder.datee.setText(date);

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
       if(cursor!=null){
           cursor.close();
        }
       cursor=newCursor;
       if (newCursor!=null){
           notifyDataSetChanged();
       }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

       public TextView userName;
        public TextView email;
        public TextView datee,fname,lname,phonenumber,gender;
        public Button profilee;
        public Context context;
        public long idd;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        userName=itemView.findViewById(R.id.username);
        email=itemView.findViewById(R.id.emailview);
            gender=itemView.findViewById(R.id.gendertextvi);
            fname=itemView.findViewById(R.id.firstnameview);
            lname=itemView.findViewById(R.id.lastnameview);
            phonenumber=itemView.findViewById(R.id.phonenumberview);
        datee=itemView.findViewById(R.id.dateview);
       // idd=cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_ID));



         itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fNAME=cursor.getString(cursor.getColumnIndex(COLUMN_FNAME));
                String lNAME=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_LNAME));
                String date=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TIMESTAMP));
                String gENDER=cursor.getString(cursor.getColumnIndex(COLUMN_GENDER));
                String mail=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EMAIL));
                String uSERNAME=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_USERNAME));
                String mobile=cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PHONENUMBER));

              Intent intent=new Intent(context, DetailUsers.class);
               intent.putExtra("Fname",fNAME);
                intent.putExtra("lname",lNAME);
                intent.putExtra("usname",uSERNAME);
                intent.putExtra("email",mail);
                intent.putExtra("phone",mobile);
                intent.putExtra("sex",gENDER);
                intent.putExtra("date",date);
                intent.setData(Uri.parse("Custom:"+userName+email+datee));
             context.startActivity(intent);
            }
        });
        }
    }
   // public void removeItem(int tag){
      // int postion=data.indexof()

    }

