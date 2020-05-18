package com.tistory.comfy91.rxandroid_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.textView)  TextView textView;

    private Disposable mDisposable;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);

        // Github 예제
        // observer에서는 가장 마지막에 발행된 문자열을 textView에 띄움
        DisposableObserver<String> observer = new DisposableObserver<String>(){
            @Override
            public void onNext(String s) {
                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
        };

        // Observable 생성
        // subscribe 함수에서 onNext() 함수를 이용해 HelloWorld 문자열 전달
        mDisposable = Observable.create(new ObservableOnSubscribe<String>(){
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello World");;
                emitter.onComplete();
            }
        }).subscribeWith(observer); // 구독자 등록 -> 데이터 발행됨


        // 교재 예제
//        Observable.create(new ObservableOnSubscribe<String>(){
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("Hellow World");
//                emitter.onComplete();
//            }
//        }).subscribe(observer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!mDisposable.isDisposed()){
            mDisposable.dispose();
        }
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
    }
}
