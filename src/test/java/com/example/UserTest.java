package com.example; // 👈 もしパッケージ名が違う場合はお手元のものに合わせてください

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void 正常系_ユーザー管理コード登録参照() {
        // コンストラクタの引数に管理コードを渡してインスタンスを作成
        User user = new User("U12345");
        
        // 登録した値と同じものが取得できるか検証
        assertEquals("U12345", user.getCode());
        assertThat(user.getCode()).isEqualTo("U12345");
    }

    @Test
    void 正常系_名前登録参照() {
        User user = new User("U12345");
        
        // 名前を登録
        user.setName("山田太郎");
        
        // 登録した名前と同じものが取得できるか検証
        assertEquals("山田太郎", user.getName());
        assertThat(user.getName()).isEqualTo("山田太郎");
    }

    @Test
    void 正常系_年齢登録参照() {
        User user = new User("U12345");
        
        // 正常な範囲内の年齢（例: 20歳）を登録
        user.setAge(20);
        
        // 登録した年齢と同じものが取得できるか検証
        assertEquals(20, user.getAge());
        assertThat(user.getAge()).isEqualTo(20);
    }

    @Test
    void 異常系_範囲外年齢登録() {
        User user = new User("U12345");
        
        // 設定可能範囲外の年齢（例: -5歳）を登録してみる
        user.setAge(-5);
        
        // 条件を満たさないため、初期値の「-1」のままになっているか検証
        assertEquals(-1, user.getAge());
        assertThat(user.getAge()).isEqualTo(-1);
    }

    @Test
    void 異常系_特定の順番で実行した場合の年齢登録() {
        User user = new User("U12345");
        
        // 1. 最初に正常な年齢（20歳）を登録
        user.setAge(20);
        
        // 2. 次に設定可能範囲外の年齢（250歳）を上書き登録してみる
        user.setAge(250);
        
        assertEquals(-1, user.getAge());
        assertThat(user.getAge()).isEqualTo(-1);
    }
}
