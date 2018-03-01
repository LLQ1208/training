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
        sql.append(" county.`name` as tname,c.class_name,u.user_name,sum(1),sum(temp.studentNum) ");//3-7
        sql.append(" from course_schedule c ");
        sql.append(" LEFT JOIN base b on c.base_id=b.id ");
        sql.append(" LEFT JOIN tecent_area_info province on province.area_id=b.procince_area_id ");
        sql.append(" LEFT JOIN tecent_area_info city on city.area_id=b.city_area_id ");
        sql.append(" LEFT JOIN tecent_area_info county on county.area_id=b.county_area_id ");
        sql.append(" LEFT JOIN user_info u on u.id=c.insert_user ");
        sql.append(" LEFT JOIN course on course.course_schedule_id=c.id ");
        sql.append(" LEFT JOIN (select course.id as courseid,sum(1) as studentNum from course , teacher_eval t ");
        sql.append(" where course.id=t.course_id ");
        sql.append(" and course.deleted=0 and t.deleted=0 ");
        sql.append(" GROUP by course.id) as temp  on temp.courseid=course.id");
        sql.append(" where ");
        sql.append("  c.deleted=0 ");
        sql.append(" and b.deleted=0 ");
        sql.append(" and course.deleted=0 ");
        if(StringUtils.isNotEmpty(searchKey)){
            sql.append(" and (c.class_name like '%").append(searchKey).append("%'");
            sql.append(" or province.`name` like '%").append(searchKey).append("%'");
            sql.append(" or city.`name` like '%").append(searchKey).append("%'");
            sql.append(" or county.`name` like '%").append(searchKey).append("%')");
        }
        if(userInfo.getUserType() == UserType.BASEADMIN.getCODE()){
            sql.append(" and c.base_id= ").append(userInfo.getBaseInfoId());
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

    @Override
    public int queryCourseNum(String searchKey, Integer provinceAreaId, UserInfo userInfo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select sum(1) as num, ");
        sql.append(" 1 as groupId ");
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
        sql.append(" and c.id=course.course_schedule_id  ");
        sql.append(" and c.deleted=0 ");
        if(StringUtils.isNotEmpty(searchKey)){
            sql.append(" and (c.class_name like '%").append(searchKey).append("%'");
            sql.append(" or province.`name` like '%").append(searchKey).append("%'");
            sql.append(" or city.`name` like '%").append(searchKey).append("%'");
            sql.append(" or county.`name` like '%").append(searchKey).append("%')");
        }
        if(userInfo.getUserType() == UserType.BASEADMIN.getCODE()){
            sql.append(" and c.base_id= ").append(userInfo.getBaseInfoId());
        }
        if(provinceAreaId != null && provinceAreaId != 0  ){
            sql.append(" and b.procince_area_id= ").append(provinceAreaId);
        }
        sql.append(" group by c.id ");
        sql.append(" order by c.id desc ");

        StringBuffer totalSql = new StringBuffer();
        totalSql.append("select sum(test.num) from (").append(sql.toString()).append(") test GROUP BY test.groupId");
        List<Object> list = this.queryBySql(totalSql.toString());
        if(null != list && list.size() > 0){
            return Integer.valueOf(list.get(0).toString());
        }
        return 0;
    }

    @Override
    public int queryStudentNum(String searchKey, Integer provinceAreaId, UserInfo userInfo) {
        StringBuffer sql = new StringBuffer();
        sql.append("select sum(1) from (");
        sql.append("select t.id as sum,1 as type  from  ");
        sql.append(" course_schedule cs, ");
        sql.append(" course, ");
        sql.append(" teacher_eval t, ");
        sql.append(" tecent_area_info province, ");
        sql.append(" tecent_area_info city, ");
        sql.append(" tecent_area_info county, ");
        sql.append(" base b ");
        sql.append(" where cs.id=course.course_schedule_id ");
        sql.append(" and course.id=t.course_id ");
        sql.append(" and cs.base_id=b.id ");
        sql.append(" and province.area_id=b.procince_area_id  ");
        sql.append(" and city.area_id=b.city_area_id ");
        sql.append(" and county.area_id=b.county_area_id  ");
        sql.append(" and course.deleted=0  ");
        sql.append(" and t.deleted=0 ");
        sql.append(" and b.deleted=0 ");
        if(StringUtils.isNotEmpty(searchKey)){
            sql.append(" and (cs.class_name like '%").append(searchKey).append("%'");
            sql.append(" or province.`name` like '%").append(searchKey).append("%'");
            sql.append(" or city.`name` like '%").append(searchKey).append("%'");
            sql.append(" or county.`name` like '%").append(searchKey).append("%')");
        }
        if(userInfo.getUserType() == UserType.BASEADMIN.getCODE()){
            sql.append(" and cs.base_id= ").append(userInfo.getBaseInfoId());
        }
        if(provinceAreaId != null && provinceAreaId != 0  ){
            sql.append(" and b.procince_area_id= ").append(provinceAreaId);
        }
        sql.append(") as temp ");
        sql.append(" group by temp.type");
        List<Object> list = this.queryBySql(sql.toString());
        if(null != list && list.size() > 0){
            return Integer.valueOf(list.get(0).toString());
        }
        return 0;
    }
}
