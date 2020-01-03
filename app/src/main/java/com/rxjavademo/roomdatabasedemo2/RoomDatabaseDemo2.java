package com.rxjavademo.roomdatabasedemo2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rxjavademo.R;
import com.rxjavademo.roomdatabasedemo2.adapter.NotesAdapter;
import com.rxjavademo.roomdatabasedemo2.db.Note;
import com.rxjavademo.roomdatabasedemo2.db.NoteDatabase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class RoomDatabaseDemo2 extends AppCompatActivity implements  NotesAdapter.OnNoteItemClick{
    //https://github.com/Pavneet-Sing/RoomDemo
    //https://www.pluralsight.com/guides/making-a-notes-app-using-room-database

    private TextView textViewMsg;
    private RecyclerView recyclerView;
    private NoteDatabase noteDatabase;
    private List<Note> notes;
    private NotesAdapter notesAdapter;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database_demo2);
        initializeVies();
        displayList();
    }

    private void displayList(){
        noteDatabase = NoteDatabase.getInstance(RoomDatabaseDemo2.this);
        new RetrieveTask(this).execute();
    }



    private static class RetrieveTask extends AsyncTask<Void,Void,List<Note>> {

        private WeakReference<RoomDatabaseDemo2> activityReference;

        // only retain a weak reference to the activity
        RetrieveTask(RoomDatabaseDemo2 context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<Note> doInBackground(Void... voids) {
            if (activityReference.get()!=null)
                return activityReference.get().noteDatabase.getNoteDao().getNotes();
            else
                return null;
        }

        @Override
        protected void onPostExecute(List<Note> notes) {
            if (notes!=null && notes.size()>0 ){
                activityReference.get().notes.clear();
                activityReference.get().notes.addAll(notes);
                // hides empty text view
                activityReference.get().textViewMsg.setVisibility(View.GONE);
                activityReference.get().notesAdapter.notifyDataSetChanged();
            }
        }
    }

    private void initializeVies(){
        textViewMsg =  findViewById(R.id.tv__empty);
        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(listener);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(RoomDatabaseDemo2.this));
        notes = new ArrayList<>();
        notesAdapter = new NotesAdapter(notes,RoomDatabaseDemo2.this);
        recyclerView.setAdapter(notesAdapter);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivityForResult(new Intent(RoomDatabaseDemo2.this,AddNoteActivity.class),100);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode > 0) {
            if (resultCode == 1) {
                notes.add((Note) data.getSerializableExtra("note"));
            } else if (resultCode == 2) {
                notes.set(pos, (Note) data.getSerializableExtra("note"));
            }
            listVisibility();
        }
    }


    @Override
    public void onNoteClick(final int pos) {
        new AlertDialog.Builder(RoomDatabaseDemo2.this)
                .setTitle("Select Options")
                .setItems(new String[]{"Delete", "Update"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                noteDatabase.getNoteDao().deleteNote(notes.get(pos));
                                notes.remove(pos);
                                listVisibility();
                                break;
                            case 1:
                                RoomDatabaseDemo2.this.pos = pos;
                                startActivityForResult(
                                        new Intent(RoomDatabaseDemo2.this,
                                                AddNoteActivity.class).putExtra("note",notes.get(pos)),
                                        100);

                                break;
                        }
                    }
                }).show();
    }

    private void listVisibility(){
        int emptyMsgVisibility = View.GONE;
        if (notes.size() == 0){ // no item to display
            if (textViewMsg.getVisibility() == View.GONE)
                emptyMsgVisibility = View.VISIBLE;
        }
        textViewMsg.setVisibility(emptyMsgVisibility);
        notesAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        noteDatabase.cleanUp();
        super.onDestroy();
    }
}
