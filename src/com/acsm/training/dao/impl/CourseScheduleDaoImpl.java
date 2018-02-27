package com.acsm.training.dao.impl;/**
 * Created by lq on 2018/2/26.
 */

import com.acsm.training.dao.CourseScheduleDao;
import com.acsm.training.enums.UserType;
import com.acsm.training.model.CourseSchedule;
import com.acsm.training.model.UserInfo;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.util.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-02-26
 */
@Repository
public class CourseScheduleDaoImpl extends BaseDaoImpl<CourseSchedule> implements CourseScheduleDao {
    @Override
    public CourseSchedule queryById(int courseScheduleId) {
        String hql ="from CourseSchedule where id=?";
        return (CourseSchedule) this.Queryobject(hql, courseScheduleId);
    }

    @Override
    public List<CourseSchedule> queryByArea(int provinceAreaId, int cityAreaId, int countyAreaId) {
        String hql ="from CourseSchedule where provinceAreaId=:provinceAreaId " +
                "and cityAreaId=:cityAreaId and countyAreaId=:countyAreaId and deleted=0";
        Session session = this.getSession();
        Query q = session.createQuery(hql);
        q.setInteger("provinceAreaId", provinceAreaId);
        q.setInteger("cityAreaId", cityAreaId);
        q.setInteger("countyAreaId", countyAreaId);
        return q.list();
    }

    @Override
    public PageHelper queryClassList(String searchKey, Integer provinceAreaId,UserInfo userInfo, int pageSize, int pageIndex) {
        PageHelper pageHelper = new PageHelper();
        StringBuffer sql = new StringBuffer();
        sql.append("select c.id,province.`name` as pname,city.`name` as cname, ");//0-2
        sql.append(" county.`name` as tname,c.class_name,u.user_name,sum(1) ");//3-6
        sql.append(" from course_schedule c, ");
        sql.append(" base b, ");
        sql.append(" tecent_area_info province, ");
        sql.append(" tecent_area_info city, ");
        sql.append(" tecent_area_info county, ");
        sql.append(" user_info u, ");
        sql.append(" course ");
        sql.append(" where ");
        sql.append(" c.base_id=b.id ");
        sql.append(" and b.procince_area_id = province.area_id  ");
        sql.append(" and b.city_area_id = city.area_id  ");
        sql.append(" and b.county_area_id = county.area_id  ");
        sql.append(" and c.insert_user = u.id  ");
        sql.append(" and c.id=course.course_schedule_id  ");
        sql.append(" and c.deleted=0 ");
        if(StringUtils.isNotEmpty(searchKey)){
            sql.append(" and (c.class_name like '%").append(searchKey).append("%'");
            sql.append(" or province.`name` like '%").append(searchKey).append("%'");
            sql.append(" or city.`name` like '%").append(searchKey).append("%'");
            sql.append(" or county.`name` like '%").append(searchKey).append("%')");
        }
        if(userInfo.getUserType() == UserType.BASEADMIN.getCODE()){
            sql.append(" and c.base_id= ").append(userInfo.getBaseId());
        }
        if(provinceAreaId != null && provinceAreaId != 0  ){
            sql.append(" and b.procince_area_id= ").append(provinceAreaId);
        }
        sql.append(" group by c.id ");
        sql.append(" order by c.id desc ");
        pageHelper.setTotalRow(this.queryBySql(sql.toString()).size());
        StringBuffer pageSql = new StringBuffer();
        pageSql.append(sql);
        pageSql.append(" limit ").append(pageSize * (pageIndex-1)).append(",").append(pageSize);
        pageHelper.setList(this.queryBySql(pageSql.toString()));
        return pageHelper;
    }
}
