package lottery.domains.content.biz.impl;

import java.util.ArrayList;
import java.util.List;
import javautils.StringUtil;
import javautils.jdbc.PageList;
import lottery.domains.content.biz.ActivitySalaryService;
import lottery.domains.content.dao.ActivitySalaryBillDao;
import lottery.domains.content.dao.UserDao;
import lottery.domains.content.entity.ActivitySalaryBill;
import lottery.domains.content.entity.User;
import lottery.domains.content.vo.activity.ActivitySalaryBillVO;
import lottery.domains.pool.LotteryDataFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitySalaryServiceImpl
  implements ActivitySalaryService
{
  @Autowired
  private UserDao uDao;
  @Autowired
  private ActivitySalaryBillDao aSalaryBillDao;
  @Autowired
  private LotteryDataFactory lotteryDataFactory;
  
  public PageList search(String username, String date, Integer type, int start, int limit)
  {
    List<Criterion> criterions = new ArrayList();
    List<Order> orders = new ArrayList();
    boolean isSearch = true;
    if (StringUtil.isNotNull(username))
    {
      User user = this.uDao.getByUsername(username);
      if (user != null) {
        criterions.add(Restrictions.eq("userId", Integer.valueOf(user.getId())));
      } else {
        isSearch = false;
      }
    }
    if (StringUtil.isNotNull(date)) {
      criterions.add(Restrictions.like("time", date, MatchMode.ANYWHERE));
    }
    if (type != null) {
      criterions.add(Restrictions.eq("type", Integer.valueOf(type.intValue())));
    }
    orders.add(Order.desc("id"));
    if (isSearch)
    {
      List<ActivitySalaryBillVO> list = new ArrayList();
      PageList pList = this.aSalaryBillDao.find(criterions, orders, start, limit);
      for (Object tmpBean : pList.getList()) {
        list.add(new ActivitySalaryBillVO((ActivitySalaryBill)tmpBean, this.lotteryDataFactory));
      }
      pList.setList(list);
      return pList;
    }
    return null;
  }
}
