package core.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 用户鉴权信息
 * @date 2020/6/14 10:29
 * @author LinJinTang
 */
@Getter
@Setter
@NoArgsConstructor
public class UserInfo implements Serializable {

    private String username;

    private String password;

    private int status;

    List<String> role;
}
