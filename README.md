# rxjava

### rxjava做线程切换
- create
>     Observable.create(new ObservableOnSubscribe<Integer>() {
>     @Override
>     public void subscribe(ObservableEmitter<Integeremitter) throws Exception {
>     /**子线程**/
>     }
>     })
>     .subscribeOn(Schedulers.io())
>     .observeOn(AndroidSchedulers.mainThread())
>     .subscribe(new Consumer<Integer>() {
>     @Override
>     public void accept(Integer integer) throws Exception {
>     /**主线程**/
>     }
>     });
 
- fromCallable
>     Observable.fromCallable(new Callable<Integer>() {
>     @Override
>     public Integer call() throws Exception {
>     if (threadListener != null){
>     threadListener.onBackground();
>     return 0;
>     }
>     return -1;
>     }
>     })
>     .subscribeOn(Schedulers.io())
>     .observeOn(AndroidSchedulers.mainThread())
>     .subscribe(new Consumer<Integer>() {
>     @Override
>     public void accept(Integer integer) throws Exception {
>     if (integer == 0 && threadListener != null){
>     threadListener.onPostResult();
>     }
>     }
>     });


- fromPublisher
>     Observable.fromPublisher(new Publisher<Integer>() {
>     @Override
>     public void subscribe(Subscriber<? super Integers) {
>     if (threadListener != null){
>     threadListener.onBackground();
>      s.onNext(0);
>     }
>     
>     }
>     })
>     .subscribeOn(Schedulers.io())
>     .observeOn(AndroidSchedulers.mainThread())
>     .subscribe(new Consumer<Integer>() {
>     @Override
>     public void accept(Integer integer) throws Exception {
>     if (integer == 0 && threadListener != null){
>     threadListener.onPostResult();
>     }
>     }

### rxjava做计时器
- 