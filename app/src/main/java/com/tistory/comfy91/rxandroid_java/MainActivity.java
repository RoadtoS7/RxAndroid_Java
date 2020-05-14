package com.tistory.comfy91.rxandroid_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.textView)  TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Observer<String> observer = new DisposableObserver<String>(){
            @Override
            public void onNext(String s) {
                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
        };

        Observable.create(new ObservableOnSubscribe<String>(){
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hellow World");
                emitter.onComplete();
            }
        }).subscribe(observer);
    }


}
