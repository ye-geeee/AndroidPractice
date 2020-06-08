# DBMS을 이용한 데이터 영속화

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
