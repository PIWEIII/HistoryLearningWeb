package com.afgk.webcom.bean;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Piweiii
 * @Date: 2020/11/17/10:54
 * @Description:成绩单对象实例
 */
public class Report {

    private String username;
    private int[] scores = {-1,-1,-1};
    private String[] time = {"","",""};
    private double avg;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int[] getScores() {
        return scores;
    }

    /**
     * 成绩存储的FIFO算法
     * @param report
     * @param score
     */
    public void setScores(Report report,int score) {
        Queue<Integer> sQ = new LinkedList<>();
        int count = report.scores.length - 1;
        //现在所有的成绩反向加入队列
        while (count >= 0){
            sQ.offer(report.scores[count]);
            count--;
        }
        //第一个成绩出队，加入新的成绩
        sQ.poll();
        sQ.offer(score);
        //队列反向赋值给数组，就是成绩的正常顺序
        for (int i = report.scores.length - 1; i >= 0; i--) {
            int sc = Integer.parseInt(sQ.poll().toString());
            report.scores[i] = sc;
        }
    }

    public String[] getTime() {
        return time;
    }

    public void setTime(Report report,String n_time){
        Queue<String> tQ = new LinkedList<>();
        int count = report.time.length - 1;
        //时间反向加入队列
        while (count >= 0){
            tQ.offer(report.time[count]);
            count--;
        }
        //第一个成绩出队，加入新的成绩
        tQ.poll();
        tQ.offer(n_time);
        //队列反向赋值给数组，就是成绩的正常顺序
        for (int i = report.time.length - 1; i >= 0; i--) {
            String T = tQ.poll().toString();
            report.time[i] = T;
        }
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg() {
        double n_avg = 0.0;
        for (int i = 0; i < this.scores.length; i++) {
            double temp = (double)(this.scores[i] == -1 ? 0 : this.scores[i]);
            n_avg += temp;
        }
        this.avg = Double.parseDouble(String.format("%.2f",((n_avg)/3)));
    }
}
