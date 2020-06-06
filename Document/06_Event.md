# Chap 6 사용자 이벤트 처리

[Sample Code](../06_Event)

- Delegation Event Model(#Delegation-Event-Model)
- Hierachy Event Model(#Hierachy-Event-Model)

## Delegation Event Model

Event Source -----(Listener)-----> Event Handler
위와 같은 구주로, Gof 디자인 패턴의 Observer Pattern이라고 볼 수 있습니다.

샘플 코드는 아래와 같습니다.

``` java
sampleCheckView.setOnCheckedChangeListener(new MyEventHandler());

class MyEventHandler implements CompoundButton.OnCheckedChangeListener{
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {}
}

```

위의 코드는 안드로이드에서 자주 볼 수 있는 아래와 같은 코드와 동일한 의미이다.

``` java
sampleCheckView.setOnCheckedChangeListener(new OnCheckedChangeListener(){
    @Ovrride
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {}
});

```

## Hierachy Event Model


