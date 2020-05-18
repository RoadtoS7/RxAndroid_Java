package com.tistory.comfy91.rxandroid_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * MainActivity.java = HelloWorld 문자열 표시
 * MainActivity를 람다식으로 표현
 */
public class Hello2Activity extends AppCompatActivity {
    private static final String TAG = Hello2Activity.class.getSimpleName();

    @BindView(R.id.textView2) TextView textView;

    private Unbinder mUnbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello2);

        mUnbinder = ButterKnife.bind(this);

        // 람다식으로 표현
        Observable.<String>create(s ->{
            s.onNext("Hello World!");
            s.onComplete();
        }).subscribe(o -> textView.setText(o));

        // just 사용 & 메서드 레퍼런스 사용용
//        Observable.just("Helo World!")
//                .subscribe(textView::setText);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
    }
}
