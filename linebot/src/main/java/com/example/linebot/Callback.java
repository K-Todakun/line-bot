package com.example.linebot;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@LineMessageHandler
public class Callback {

        private static final Logger log = LoggerFactory.getLogger(Callback.class);
        private int n;
        //改行コード
        String kaigyou_code_1 = System.lineSeparator();
        private List<PreExam> returning;

        // フォローイベントに対応する
        @EventMapping
        public TextMessage handleFollow(FollowEvent event) {
            // 実際の開発ではユーザIDを返信せず、フォロワーのユーザIDをデータベースに格納しておくなど
            String userId = event.getSource().getUserId();
            //return replyFirst("あなたのユーザIDは " + userId);
            return replyfirst("登録ありがとうございます！このアカウントは一問一答形式の勉強アカウントです。");
        }

      // 文章で話しかけられたとき（テキストメッセージのイベント）に対応する
     @EventMapping
     public Message handleMessage(MessageEvent<TextMessageContent> event) throws Exception {
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();

        switch (text) {
            case "英単語":
            case "社会":
            case "化学":
                return reply(text);
            case "1":
            case "2":
            case "3":
            case "4":
                return answer();
            default:
                return replyfirst("入力されたものが正しくありません。もう一度入力してください。");
        }
    }

    //switchでメソッドを仕分ける。教科選択if(text.equals("英単語")||text.equals("社会")||text.equals("化学"))、解答、
    // それ以外はもう一度入力してくださいの出力


        // 返答メッセージを作る  （教科選択）
        private  TextMessage reply(String text) throws Exception{

            E_Question question = new E_Question(text);

            n = new java.util.Random().nextInt(20);
            returning = question.selectPreExams(n);
            return new TextMessage(returning.get(0).getMondai()+ kaigyou_code_1
                    + returning.get(0).getSentaku1() +kaigyou_code_1
                    + returning.get(0).getSentaku2() + kaigyou_code_1
                    + returning.get(0).getSentaku3() +kaigyou_code_1
                    + returning.get(0).getSentaku4());
        }
          //解答表示
          private TextMessage answer() throws Exception{
             // E_Question question = new E_Question(text);
             //  List<PreExam> returning = question.selectPreExams(n);
              return new TextMessage("正解は"+returning.get(0).getAnswer()+ kaigyou_code_1
                      + returning.get(0).getKaisetu());

          }

    // 返答メッセージを作る
    private TextMessage replyfirst(String text) {
        String textFirst = "学習を始めるには英単語・社会・化学のいずれかを入力してください。回答は1,2,3,4のいずれかを入力してください。";
        return new TextMessage(text + textFirst);
    }



/*
    // 返答メッセージを作る
    private TextMessage reply(String text) {
        return new TextMessage(text);
    }

    // 文章で話しかけられたとき（テキストメッセージのイベント）に対応する
    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event) {
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        switch (text) {
            case "やあ":
                return greet();
            default:
                return reply(text);
        }
    }

    // あいさつする
    private TextMessage greet() {
        LocalTime lt = LocalTime.now();
        int hour = lt.getHour();
        if (hour >= 17) {
            return  reply("こんばんは！");
        }
        if (hour >= 11) {
            return reply("こんにちは！");
        }
        return reply("おはよう！");
    }

    // PostBackEventに対応する
    @EventMapping
    public Message handlePostBack(PostbackEvent event) {
        String actionLabel = event.getPostbackContent().getData();
        switch (actionLabel) {
            case "CY":
                return reply("イイね！");
            case "CN":
                return reply("つらたん");
            default:
                return reply("?");
        }
    }
*/

    }
/*
    // 画像メッセージを作る
    private ImageMessage replyImage(URI url) {
        // 本来は、第一引数が実際の画像URL、第二画像がサムネイルのurl
        return new ImageMessage(url, url);
    }

    // ランダムにおみくじ画像を返す
    private ImageMessage replyOmikuji() {
        var ranNum = new Random().nextInt(3);
        var uriString = "";
        switch (ranNum) {
            case 2:
                uriString = "https://3.bp.blogspot.com/-vQSPQf-ytsc/T3K7QM3qaQI/AAAAAAAAE-s/6SB2q7ltxwg/s1600/omikuji_daikichi.png";
                break;
            case 1:
                uriString = "https://2.bp.blogspot.com/-27IG0CNV-ZE/VKYfn_1-ycI/AAAAAAAAqXw/fr6Y72lOP9s/s400/omikuji_kichi.png";
                break;
            case 0:
            default:
                uriString = "https://4.bp.blogspot.com/-qCfF4H7YOvE/T3K7R5ZjQVI/AAAAAAAAE-4/Hd1u2tzMG3Q/s1600/omikuji_kyou.png";
        }
        return replyImage(URI.create(uriString));
    }
*/

