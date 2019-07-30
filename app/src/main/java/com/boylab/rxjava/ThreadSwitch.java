package com.boylab.rxjava;

import android.os.AsyncTask;
import android.util.Log;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author pengle on 2019/7/30 14:42
 * Email  pengle609@163.com
 * 线程切换
 */
public class ThreadSwitch {

    public ThreadSwitch() {
    }

    public void onSwitch(final ThreadListener threadListener){
        Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        if (threadListener != null){
                            threadListener.onBackground();
                            emitter.onNext(0);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        if (integer == 0 && threadListener != null){
                            threadListener.onPostResult();
                        }
                    }
                });
    }

    public void onSwitch03(final ThreadListener threadListener){
        Observable.fromCallable(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        if (threadListener != null){
                            threadListener.onBackground();
                            return 0;
                        }
                        return -1;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        if (integer == 0 && threadListener != null){
                            threadListener.onPostResult();
                        }
                    }
                });
    }

    public void onSwitch02(final ThreadListener threadListener){
        Observable.fromPublisher(new Publisher<Integer>() {
                    @Override
                    public void subscribe(Subscriber<? super Integer> s) {
                        if (threadListener != null){
                            threadListener.onBackground();
                             s.onNext(0);
                        }

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        if (integer == 0 && threadListener != null){
                            threadListener.onPostResult();
                        }
                    }
                });
    }

    public interface ThreadListener{

        void onBackground();

        void onPostResult();

    }

}
