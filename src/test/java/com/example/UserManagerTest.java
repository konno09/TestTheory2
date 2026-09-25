package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

public class UserManagerTest {

    // --- ここから課題4 ---

    @Test
    void 正常系_UserManagerインスタンス同一() {
        UserManager instance1 = UserManager.getInstance();
        UserManager instance2 = UserManager.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    void 正常系_userList登録参照() {
        UserManager manager = UserManager.getInstance();
        manager.deleteAllUser(); 
        
        User user = new User("U00001");
        manager.setUserToList(user);
        
        List<User> list = manager.getUserList();
        assertThat(list).contains(user);
    }

    @Test
    void 正常系_userMap登録参照() {
        UserManager manager = UserManager.getInstance();
        manager.deleteAllUser();
        
        User user = new User("U00002");
        manager.setUserToMap(user);
        
        Map<String, User> map = manager.getUserMap();
        assertThat(map).containsValue(user);
    }

    @Test
    void 正常系_user全削除() {
        UserManager manager = UserManager.getInstance();
        User user = new User("U00003");
        manager.setUserToList(user);
        manager.setUserToMap(user);
        
        manager.deleteAllUser();
        
        assertThat(manager.getUserList()).isEmpty();
        assertThat(manager.getUserMap()).isEmpty();
    }

    @Test
    void 正常系_code指定user削除() {
        UserManager manager = UserManager.getInstance();
        manager.deleteAllUser();
        
        User user = new User("U00004");
        manager.setUserToList(user);
        manager.setUserToMap(user);
        
        manager.deleteUser("U00004");
        
        assertThat(manager.getUserList()).doesNotContain(user);
        assertThat(manager.getUserMap()).doesNotContainKey("U00004");
    }

    @Test
    void 異常系_特定管理コード削除バグ検証() {
        UserManager manager = UserManager.getInstance();
        manager.deleteAllUser();
        
        User user1 = new User("U00005");
        User user2 = new User("U00005");
        manager.setUserToList(user1);
        manager.setUserToList(user2);
        
        manager.deleteUser("U00005");
        
        assertThat(manager.getUserList()).as("削除バグの検証").doesNotContain(user1, user2);
    }

    // --- ここから課題5（追加分） ---

    @Test
    void 正常系_MapList初期生成() {
        UserManager manager = UserManager.getInstance();
        manager.deleteAllUser(); // 一度クリア
        
        // 取得したListとMapがnullではなく、中身が空であることを検証
        assertThat(manager.getUserList()).isNotNull().isEmpty();
        assertThat(manager.getUserMap()).isNotNull().isEmpty();
    }

    @Test
    void 正常系_List登録順序保持() {
        UserManager manager = UserManager.getInstance();
        manager.deleteAllUser();
        
        User firstUser = new User("U00006");
        User secondUser = new User("U00007");
        
        // 順番に登録
        manager.setUserToList(firstUser);
        manager.setUserToList(secondUser);
        
        // 登録した順番通り（firstが先、secondが次）に格納されているか検証
        assertThat(manager.getUserList()).containsExactly(firstUser, secondUser);
    }

    @Test
    void 正常系_Mapキー確認() {
        UserManager manager = UserManager.getInstance();
        manager.deleteAllUser();
        
        String userCode = "U00008";
        User user = new User(userCode);
        manager.setUserToMap(user);
        
        // ユーザー管理コード（"U00008"）をキーとしてMapに登録されているか検証
        assertThat(manager.getUserMap()).containsKey(userCode);
        assertThat(manager.getUserMap().get(userCode)).isEqualTo(user);
    }
}
