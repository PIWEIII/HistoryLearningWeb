package com.afgk.webcom.service;

import com.afgk.webcom.bean.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Piweiii
 * @Date: 2020/11/17/14:13
 * @Description:
 */
@Repository
public class ReportService {

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 根据username，查找成绩
     * @return
     */
    public Report findReportsByUN(String username){
        Query query = new Query(Criteria.where("username").is(username));
        Report report = mongoTemplate.findOne(query,Report.class);
        return report;
    }

    public List<Report> findAllReports(){
        Query query = new Query();
        mongoTemplate.find(new org.springframework.data.mongodb.core.query.Query(new Criteria()),Report.class);
        List<Report> reports = mongoTemplate.find(query,Report.class);
        return reports;
    }

    /**
     * 增加成绩信息
     * @param report
     */
    public void addReport(Report report){
        mongoTemplate.save(report);
    }

    /**
     * 修改成绩信息
     * @param report
     */
    public void updateReport(Report report){
        Query query = new Query(Criteria.where("username").is(report.getUsername()));
        Update update = new Update().set("username",report.getUsername())
                .set("scores",report.getScores())
                .set("time",report.getTime())
                .set("avg",report.getAvg());
        mongoTemplate.updateFirst(query,update,Report.class);
    }
}
