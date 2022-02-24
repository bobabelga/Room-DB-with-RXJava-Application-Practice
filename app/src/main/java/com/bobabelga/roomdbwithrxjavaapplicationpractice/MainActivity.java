package com.bobabelga.roomdbwithrxjavaapplicationpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView ;
    private Button insertBtn,getBtn;
    private EditText titleEditTxt,bodyEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertBtn = findViewById(R.id.insertButton);
        getBtn = findViewById(R.id.getButton);
        titleEditTxt = findViewById(R.id.editTexttitle);
        bodyEditTxt = findViewById(R.id.editTextBody);

        recyclerView = findViewById(R.id.posts_recyclerView);
        PostsAdapter adapter = new PostsAdapter();
        recyclerView.setAdapter(adapter);
        final PostDatabase postDatabase = PostDatabase.getInstance(this);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDatabase.postDao()
                        .insertPost(new Post(new User(1,"Boba")
                                ,titleEditTxt.getText().toString()
                                ,bodyEditTxt.getText().toString()))
                        .subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDatabase.postDao().getPosts()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<Post>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<Post> posts) {
                                adapter.setPostArrayList((ArrayList<Post>) posts);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });
    }
}