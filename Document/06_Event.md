# Chap 6 사용자 이벤트 처리

[Sample Code](../06_Event)

- [Delegation Event Model](#Delegation-Event-Model)
- [Hierachy Event Model](#Hierachy-Event-Model)

## Delegation Event Model

Event Source -----(Listener)-----> Event Handler  
위와 같은 구주로, Gof 디자인 패턴의 Observer Pattern이라고 볼 수 있습니다.

샘플 코드는 아래와 같습니다.

```java
sampleCheckView.setOnCheckedChangeListener(new MyEventHandler());

class MyEventHandler implements CompoundButton.OnCheckedChangeListener{
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {}
}

```

위의 코드는 안드로이드에서 자주 볼 수 있는 아래와 같은 코드와 동일한 의미이다.

```java
sampleCheckView.setOnCheckedChangeListener(new OnCheckedChangeListener(){
    @Ovrride
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {}
});

```

## Hierachy Event Model

### Touch Event

사용자가 액티비티 내에서 손가락으로 조작하는 일을 처리하는 이벤트입니다.  
사용자가 손가락으로 상하좌우 어떤 방향으로 밀었는지 알아내는 것 또한 가능합니다.

- ACTION_DOWN: 화면이 터치된 순간 이벤트
- ACTION_UP: 화면에서 터치를 떼는 순간 이벤트
- ACTION_MOVE: 터치한 후 이동하는 순간 이벤트

```java
@Override
public boolean onTouchEvent(MotionEvent event){
    if(event.getAction()==MotionEvent.ACTION_DOWN){
        initX = getRawX();
    }
    return true;
}
```

### Key Event

사용자의 안드로이드 키보드는 이벤트를 처리할 수 없습니다.  
Key Event를 통해서는 안드로이드 하단의 버튼 이벤트를 처리하는 데에 사용합니다.

```java
@Override
public boolean onKeyDown(int keyCode, KeyEvent event){
    if(keyCode==KeyEvent.KEYCODE_BACK){}
    return super.onKeyDown(keyCode, event);
}
```
