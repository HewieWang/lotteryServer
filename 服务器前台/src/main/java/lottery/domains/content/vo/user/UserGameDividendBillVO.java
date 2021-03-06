package lottery.domains.content.vo.user;

import lottery.domains.content.entity.UserGameDividendBill;
import lottery.domains.pool.DataFactory;

/**
 * Created by Nick on 2016/11/01
 */
public class UserGameDividendBillVO {
    private String username; // 用户名
    private UserGameDividendBill bean;

    public UserGameDividendBillVO(UserGameDividendBill bean, DataFactory dataFactory) {
        UserVO userVO = dataFactory.getUser(bean.getUserId());
        if (userVO != null) {
            this.username = userVO.getUsername();
        }
        this.bean = bean;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserGameDividendBill getBean() {
        return bean;
    }

    public void setBean(UserGameDividendBill bean) {
        this.bean = bean;
    }
}
