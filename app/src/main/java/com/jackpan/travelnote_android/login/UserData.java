package com.jackpan.travelnote_android.login;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class UserData {

    public String name;
    public  int age;

    public int getAge() {
        return age;
    }

    public void test(){
        Observable observable = (Observable) Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {

            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {

            }
        });
    }


}
