# DBMS을 이용한 데이터 영속화

[Sample Code](../08_dbms)

## SQLite 이용

### SQLiteDatabase

SQLite는 관계형 데이터베이스로 복잡하고 구조화된 애플리케이션 데이터를 저장하고 관리합니다.  
SQLite를 사용하기 위해서는 `SQLiteDatabase` 클래스를 이용합니다.  
디비를 사용하기 위해서는 맨 처음에 객체를 불러와야 합니다.  
그리고 아래와 같이 두 가지 함수를 이용하여 DB를 제어합니다.  
- `execSQL` : insert, update등 select 문 외 나머지 SQL 문 수행  
- `rawQuery` : select SQL 문 수행

```java
SQLiteDatabase db = openOrCreateDatabase("memodb", MORE_PRIVATE, null);

db.execSQL("insert into tb_memo (title, content) values (?, ?)", new String[]{title, content});
Cursor cursor = db.rawQuery("select title, content from tb_memo order by _id desc limit 1", null);
```

여기서 `Cursor`는 데이터의 행에 대한 객체라고 할 수 있습니다.  

### SQLiteOpenHelper

데이터베이스 관리만을 목적으로 하는 클래스입니다.  
데이터 저장과 획득은 `SQLiteDatabase`의 `insert, select`문을 이용하고,  
테이블 생성이나 스키마 변경 등의 작업은 `SQLiteOpenHelper`를 이용하여 수행합니다.  

```java
DBHelper helper = new DBHelper(this);
SQLiteDatabase db = helper.getWritableDatabase();
```

## REALM 이용

Realm은 안드로이드 표준에서 제공하는 것이 아니라, 오픈소스로 만들어지고 있는 데이터베이스입니다.  

###  Realm 사용 설정

Realm을 사용하기 위해서는 프로젝트 수준의 `build.gradle`을 수정해야 합니다.  
아래와 같이 의존성을 주입해줍니다.

 ```properties
 dependencies{
    classpath 'com.android.tools.build:gradle:3.2.1'
    classpath 'io.realm:realm-gradle-plugin:3.5.0'
 }
 ```
 
### 데이터 삽입

저장할 VO Class는 아래와 같이 `RealmObject`를 상속받아야 합니다.

```java
public class MemoVO extends RealObject{};
```

Realm을 이용하여 데이터를 저장하는 방법은 아래와 같습니다.  
생성한 `RealmObject` class를 사용하여 `createObject` 함수를 통해 데이터를 저장합니다.  

```java
Realm.init(this);
Realm mRealm = Realm.getDefaultInstance();

mRealm.executeTransition(new Realm.Transaction(){
    @Override
    public void execute(Realm realm){
        MemoVO vo = realm.createObject(MemoVO.class);
    }
});
```

### 데이터 획득

저장된 데이터를 획득하기 위해서는 아래와 같은 코드를 사용합니다.    

```java
MemoVO vo = mRealm.where(MemoVO.class).equalTo("title", "hello").findFirst();
```

`mRealm.where(...)`이 반환하는 객체는 `RealmQuery`이며, 다양한 findXXX함수를 제공합니다. 
- findAll()
- findAllSorted(String fieldName, Sort sortOrder)
- findAllSorted(String[] fieldNames, Sort[] sortOrder)
- findAllSorted(String fieldName1, Sort sortOrder1, String fieldName2, Sort sortOrder2)
- findFirst()

데이터가 여러건이면 `RealmResults` 타입으로 획득 가능합니다.  

```java
RealmResults<MemoVO> results = mRealm.where(MemoVO.class).equalTo("title", "hello").findAll();
```

### 데이터 삭제

데이터 삭제는 아래와 같이 `delete` 함수를 이용합니다. 

```java
mRealm.delete(MemoVo.class);
```