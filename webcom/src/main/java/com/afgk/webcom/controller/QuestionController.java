package com.afgk.webcom.controller;

import com.afgk.webcom.bean.Report;
import com.afgk.webcom.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Piweiii
 * @Date: 2020/11/17/11:59
 * @Description:答题模块控制器
 */
@RestController
public class QuestionController {

    @Autowired
    ReportService reportService;

    /**
     * 根据用户名，查找成绩单
     * @param username
     * @return
     */
    @GetMapping("/checkReport")
    public Report checkMyReport(@RequestParam String username){
        Report report = reportService.findReportsByUN(username);
        if(report == null){
            return new Report();
        }
        return report;
    }

    /**
     * 用户上传成绩单，系统判断更新、添加成绩单
     * @param username
     * @param score
     * @param time
     * @return
     */
    @GetMapping("/updateReport")
    public void updateMyReport(  @RequestParam String score,
                                 @RequestParam String username,
                                 @RequestParam String time){
        //用户第一次提交成绩单
        if(reportService.findReportsByUN(username) == null){
            Report report = new Report();
            report.setUsername(username);
            report.setScores(report,Integer.parseInt(score));
            report.setTime(report,time);
            report.setAvg();
            reportService.addReport(report);
            return;
        }
        Report report = reportService.findReportsByUN(username);
        report.setUsername(username);
        report.setScores(report,Integer.parseInt(score));
        report.setTime(report,time);
        report.setAvg();
        reportService.updateReport(report);
        return;
    }

    /**
     * 对用户的成绩单进行排名，返回前三名用户
     * @return
     */
    @GetMapping("/getRank")
    public List<Report> getRank(){
        List<Report> reports = reportService.findAllReports();
        List<Report> rankList = new ArrayList<>();
        double[] avgs = new double[reports.size()];
        double[] ranked_avgs = new double[3];
        for (int i = 0; i < avgs.length; i++) {
            avgs[i] = reports.get(i).getAvg();
        }

        Arrays.sort(avgs);

        for (int i = 0; i < ranked_avgs.length; i++) {
            ranked_avgs[i] = avgs[avgs.length - 1 - i];
        }

        for (int i = 0; i < ranked_avgs.length; i++) {
            for(Report r : reports){
                if(r.getAvg() == ranked_avgs[i]){
                    rankList.add(r);
                    break;
                }
            }
        }
        return rankList;
    }
}
