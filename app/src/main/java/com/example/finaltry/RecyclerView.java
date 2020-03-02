package com.example.finaltry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class RecyclerView extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase db;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    RecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        dbHelper=new DBHelper(this);
        db=dbHelper.getWritableDatabase();
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new RecycleViewAdapter(this,getAllUsers());
        recyclerView.setAdapter(adapter);



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull androidx.recyclerview.widget.RecyclerView recyclerView, @NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, @NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, int direction) {
                    removeUser((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView((recyclerView));

        }
    private Cursor getAllUsers(){
        return db.query(DBHelper.User_Table,null,null,null,null,null,DBHelper.COLUMN_TIMESTAMP+" DESC");
    }

    private void removeUser(long id){
        db.delete(DBHelper.User_Table,DBHelper.COLUMN_ID+"="+id,null);
        adapter.swapCursor(getAllUsers());
    }

    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"Individual Assignment",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(getApplicationContext());
                alertDialogBuilder.setTitle("Comfirm Exit!!");
                //alertDialogBuilder.setIcon(R.drawable.alertdialog);
                alertDialogBuilder.setMessage("Are you sure, you want to exit");
                alertDialogBuilder.setCancelable(false);

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"you clicked on cancel",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    }

