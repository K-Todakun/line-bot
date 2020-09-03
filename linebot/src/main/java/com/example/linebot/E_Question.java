package com.example.linebot;

import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class E_Question {

    private static final String URL ="jdbc:h2:file:~/h2db/test-database;Mode=PostgreSQL;AUTO_SERVER=TRUE;;MV_STORE=false";
    private static final String USER_NAME = "b2191560";
    private static final String USER_PASS = "b2191560";
    public String kamoku;


    public E_Question(String kamoku) {
        this.kamoku= kamoku;
    }

    @EventMapping
    public List<PreExam> selectPreExams(int questionNumber) throws Exception{
        List<PreExam> returning = new ArrayList<PreExam>();
        String sql;
        switch (kamoku){
            case "英単語":
                sql = "select * from 英単語 where questionNumber = ?";
                break;
            case "社会":
                sql = "select * from 社会 where questionNumber = ?";
                break;
            default:
                sql = "select * from 化学 where questionNumber = ?";
                break;
        }
      // String sql = "select * from 社会 where questionNumber = ? ";

        try(Connection conn = DriverManager.getConnection(URL,USER_NAME,USER_PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,questionNumber);
            ResultSet results = stmt.executeQuery();
            while (results.next()){
                var col0 = results.getInt("questionNumber");
                var col1 = results.getString("mondai");
                var col2 = results.getString("sentaku1");
                var col3 = results.getString("sentaku2");
                var col4 = results.getString("sentaku3");
                var col5 = results.getString("sentaku4");
                var col6 = results.getInt("answer");
                var col7 = results.getString("kaisetu");
                var preExam = new PreExam(col0,col1,col2,col3,col4,col5,col6,col7);
                returning.add(preExam);
            }
        }
        return returning;
    }
}
