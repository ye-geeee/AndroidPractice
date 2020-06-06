# Chap 7 리소스 활용 및 스마트폰 크기 호환성

[Sample Code](../07_Resource)

## 리소스의 종류

앱 리소스는 모두 res 폴더 아래에 정의되어 있어야 합니다.  
앱 리소스의 폴더명은 정해져 있기 때문에 아래의 폴더명을 사용합니다.  
- drawble : 앱 이미지 및 그림
- layout: 화면 UI
- values: 문자열, 색상, 크기 등 
- menu: 액티비티 메뉴
- xml: 지정되어 있지 않는 것
- anim: 애니메이션
- raw: 바이트 단위 파일
- mipmap: 앱 아이콘 

## 리소스 활용 방법

### 애니메이션 리소스

res 폴더 밑에 anim이라는 `Animaton resource directory`를 생성합니다.  
그 아래에 `Animation resource file`을 생성하여 사용하면 됩니다.  

루트 태그는 `<set>`이고, 하위에 `<scale>, <rotate>, <alpha>, <tranparent>` 태그를 이용하여 애니메이션 효과를 지정합니다.
지정한 효과를 적용하기 위해서는 아래와 같이 코드를 작성합니다.

```java
Animation anim = AnimationUtils.loadAnimation(this, R.anim.in);
imageView.startAnimation(anim);
```

Animation 시작, 종료, 반복 관련 이벤트를 작성하기 위해서는 아래와 같은 함수가 있습니다. 


```java
anim.setAnimationListener(new Animation.AnimationListener(){
     @Override
     public void onAnimationStart(Animation animation){};
     
     @Override
     public void onAnimationEnd(Animation animation){}
     
     @Override
     public void onAnimationRepeat(Animation animation){}
});

```

### 크기, 색상 리소스

res 폴더 밑에 values 아래에 작성합니다.  
권장 파일 명에는 `strings.xml, colors.xml, styles.xml, arrays.xml, dimens.xml`

## 스타일 리소스

스타일 리소스는 values 폴더 내의 `styles.xml`에 정의합니다.  
Action bar의 테마를 바꿀 때 등에 사용을 하는 리소스입니다.(예제는 코드의 `styles.xml`과 `AndroidManifest.xml`을 참고해주세요.)  
다른 스타일을 상속받아 재정의하는 방법 또한 제공합니다.  

```xml
<style name="myStyle"></style>
<style name="mySubSttyle" parent="myStyle"></style>
```

## 스마트폰 크기 호환성

화면 크기에 따라 리소스의 폴더명을 명시합니다.  
예를 들어, 단말의 화면 크기에 따라서 `mipmap-hdpi`, `mipmap-mdpi`등으로 폴더명을 명시하고, 내부에 동일한 파일명하지만 다른 사이즈의 아이콘을 넣어 놓습니다.  
그러면 사용자의 화면에 맞는 아이콘이 노출되게 됩니다.  
